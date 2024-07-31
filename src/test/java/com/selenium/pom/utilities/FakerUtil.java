package com.selenium.pom.utilities;

import com.github.javafaker.Faker;

public class FakerUtil {
	Faker faker=new Faker();
	
	public long randomIMEI() {
		return faker.number().randomNumber(14, true);
	}
	public String randomFirstName() {
		return faker.name().firstName();
	}
	public String randomLastName() {
		return faker.name().lastName();
	}
	public long randomContact() {
		return faker.number().randomNumber(10, true);
	}

}
