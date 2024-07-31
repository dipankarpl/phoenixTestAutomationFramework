package com.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.selenium.pom.base.BasePage;

public class GlobalListPage extends BasePage {

	public GlobalListPage(WebDriver driver) {
		super(driver);
	}

	private static final By CREATE_JOB = By.xpath("//span[normalize-space()='Create Job']");

	public CreateJobPage clickCreateJOb() {
		driver.findElement(CREATE_JOB).click();
		return new CreateJobPage(driver);
	}
}
