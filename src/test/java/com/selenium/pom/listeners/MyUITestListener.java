package com.selenium.pom.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.selenium.pom.base.BaseTest;

public class MyUITestListener implements ITestListener {

	private ExtentReports extentReports;
	private ExtentTest extentTest;
	private ExtentSparkReporter extentSparkReporter;

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(
				"******************* " + result.getMethod().getMethodName() + "Test Started ******************* ");
		System.out.println("******************* " + result.getMethod().getDescription() + " ******************* ");
		System.out.println(
				"******************* " + Arrays.toString(result.getMethod().getGroups()) + " ******************* ");
		extentTest = extentReports.createTest(result.getMethod().getMethodName());
		extentTest.assignCategory(result.getMethod().getGroups());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("******************* Test Success ******************* ");
		extentTest.pass("Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("******************* Test Failed ******************* ");
		extentTest.fail("Test failed");
//		try {
//			extentTest.addScreenCaptureFromPath(new BaseTest().captureScreen(result.getMethod().getMethodName()));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("******************* Test Skipped******************* ");
		extentTest.skip("Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		System.out.println("*******************  TestSuite Started******************* ");
		File reportDirectory = new File(System.getProperty("user.dir") + "/report");
		try {
			FileUtils.forceMkdir(reportDirectory);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentSparkReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/report/report" + timeStamp + ".html");

		extentSparkReporter.config().setDocumentTitle("Phoenix Automation Report"); // Title of report
		extentSparkReporter.config().setReportName("Phoenix Functional Testing"); // name of the report
		extentSparkReporter.config().setTheme(Theme.DARK);

		extentReports = new ExtentReports();

		String browser = context.getCurrentXmlTest().getParameter("browser");
		extentReports.setSystemInfo("Browser", browser);

		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extentReports.setSystemInfo("Groups", includedGroups.toString());
		}

		extentReports.attachReporter(extentSparkReporter);

	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("TestSuite Finished");
		extentReports.flush();
	}

}
