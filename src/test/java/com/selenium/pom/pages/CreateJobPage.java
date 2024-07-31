package com.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.selenium.pom.base.BasePage;
import com.selenium.pom.objects.CreateJobPOJO;

public class CreateJobPage extends BasePage {

	public CreateJobPage(WebDriver driver) {
		super(driver);
	}

	private static final By IMEI = By.xpath("//input[@formcontrolname='imeiNo1']");
	private static final By FIRST_NAME = By.xpath("//input[@formcontrolname='firstName']");
	private static final By LAST_NAME = By.xpath("//input[@formcontrolname='lastName']");
	private static final By CONTACT_NUMBER = By.xpath("//input[@formcontrolname='contactNo']");
	private static final By EMAIL = By.xpath("//input[@formcontrolname='emailId']");
	private static final By SUBMIT = By.xpath("//span[normalize-space()='Submit']");

	public CreateJobPage createJob(CreateJobPOJO data) {
		driver.findElement(IMEI).sendKeys(String.valueOf(data.getImei()));
		driver.findElement(FIRST_NAME).sendKeys(data.getFname());
		driver.findElement(LAST_NAME).sendKeys(data.getLname());
		driver.findElement(CONTACT_NUMBER).sendKeys(String.valueOf(data.getContact()));
		driver.findElement(EMAIL).sendKeys(data.getEmail());
		return this;
	}

	public Boolean submitButton() {
		return driver.findElement(SUBMIT).isDisplayed();

	}

}
