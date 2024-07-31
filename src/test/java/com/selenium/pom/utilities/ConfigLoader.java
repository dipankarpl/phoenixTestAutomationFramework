package com.selenium.pom.utilities;

import java.util.Properties;

import com.selenium.pom.constants.EnvType;

public class ConfigLoader {
	private final Properties properties;
	private static ConfigLoader configLoader;

	private ConfigLoader() {
		String env = System.getProperty("env", String.valueOf(EnvType.SIT));
		switch (env) {
		case "SIT":
			properties = PropertyUtils.propertyLoader("src/test/resources/config/config_" + env + ".properties");
			break;
		case "PROD":
			properties = PropertyUtils.propertyLoader("src/test/resources/config/config" + env + ".properties");
			break;
		default:
			throw new IllegalStateException("Invalid env type: " + env);
		}
	}

	public static ConfigLoader getInstance() {
		if (configLoader == null) {
			configLoader = new ConfigLoader();
		}
		return configLoader;
	}

	public String getBaseUrl() {
		String prop = properties.getProperty("url");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property baseUrl is not specified in the config.properties file");
	}

	public String getUsername(String user) {
		String prop = properties.getProperty("user"+user);
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property username is not specified in the config.properties file");
	}

	public String getPassword(String user) {
		String prop = properties.getProperty("password"+user);
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property password is not specified in the config.properties file");
	}
}
