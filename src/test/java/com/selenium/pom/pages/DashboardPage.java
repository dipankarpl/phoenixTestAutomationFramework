package com.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.selenium.pom.base.BasePage;

public class DashboardPage extends BasePage {

	public DashboardPage(WebDriver driver) {
		super(driver);
	}

	private static final By PROFILE = By
			.xpath("//mat-icon[@class='mat-icon notranslate mat-icon-no-color ng-star-inserted']//*[name()='svg']");
	private static final By SIGN_OUT = By.xpath("//span[normalize-space()='Sign out']");
	private static final By SEARCH_JOB_IMEI = By.xpath("//input[@data-placeholder='Search for a Job or IMEI']");
	private static final By FOOTER = By.xpath("//span[contains(text(),'Phoenix')]");

	public String geturl() {
		String footerText = wait.until(ExpectedConditions.visibilityOfElementLocated(FOOTER)).getText();
		if (footerText.equalsIgnoreCase("Phoenix © 2024")) {
			System.out.println(driver.getCurrentUrl());
			return driver.getCurrentUrl();
		} else
			return null;
	}

	public DashboardPage clickProfile() {
		driver.findElement(PROFILE).click();
		return this;
	}

	public DashboardPage clickSignOut() {
		driver.findElement(SIGN_OUT).click();
		return this;
	}

	public DashboardPage clickSearchJobImei(String jobid) {
		driver.findElement(SEARCH_JOB_IMEI).sendKeys(jobid,Keys.ENTER);
		return this;
	}
}
