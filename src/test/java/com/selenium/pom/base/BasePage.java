package com.selenium.pom.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.pom.utilities.ConfigLoader;

public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	public void load(String endPOint) {
		driver.get(ConfigLoader.getInstance().getBaseUrl() + endPOint);
	}

}
