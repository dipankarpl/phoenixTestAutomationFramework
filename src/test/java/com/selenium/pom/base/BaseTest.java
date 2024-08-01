package com.selenium.pom.base;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
	public Logger logger;
	private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	private void setDriver(WebDriver driver) {
		this.driver.set(driver);
	}

	protected WebDriver getDriver() {
		return this.driver.get();
	}

	@Parameters({ "os", "browser", "execution_mode" })
	@BeforeMethod(groups = { "smoke", "regression" })
	public void startDriver(@Optional String os, @Optional String browser, @Optional String execution_mode)
			throws MalformedURLException {

		logger = LogManager.getLogger(this.getClass());

		String browserName = null;
		String operatingSystem = null;
		String executionMode = null;

		if (os != null) {
			operatingSystem = System.getProperty("os", os);
		} else
			operatingSystem = "windows";

		if (browser != null) {
			browserName = System.getProperty("browser", browser);
		} else
			browserName = "chrome";

		if (execution_mode != null) {
			executionMode = System.getProperty("execution_mode", execution_mode);
		} else
			executionMode = "local";

		if (executionMode.equalsIgnoreCase("local")) {
			switch (browserName) {
			case "edge":
				setDriver(new EdgeDriver());
				break;
			case "chrome":
				setDriver(new ChromeDriver());
				break;
			case "firefox":
				setDriver(new FirefoxDriver());
				break;
			default:
				System.out.println("no browser provided");
			}
		} else {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			switch (operatingSystem) {
			case "windows":
				capabilities.setPlatform(Platform.WINDOWS);
				break;
			case "mac":
				capabilities.setPlatform(Platform.MAC);
				break;
			case "linux":
				capabilities.setPlatform(Platform.LINUX);
				break;
			default:
				System.out.println("no os provided");
			}

			switch (browserName) {
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "firefox":
				capabilities.setBrowserName("firefox");
				break;
			default:
				System.out.println("no browser provided");
			}
			setDriver(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities));
		}

		System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " + "DRIVER = " + getDriver());
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}

	@AfterMethod(groups = { "smoke", "regression" })
	/*
	 * public void tearDown(@Optional String browser, ITestResult result) throws
	 * IOException { String timeStamp = new
	 * SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); if (result.getStatus()
	 * == ITestResult.FAILURE) { File destFile = new
	 * File(System.getProperty("user.dir") + "\\screenshots\\" +
	 * result.getTestClass().getRealClass().getSimpleName() + "_" +
	 * result.getMethod().getMethodName() + timeStamp + ".png");
	 * captureScreen(destFile); } getDriver().close(); }
	 */
	public void tearDown() {
		getDriver().close();
	}

	private void captureScreen(File destFile) throws IOException {
		TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
		File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, destFile);
	}

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;

	}

}
