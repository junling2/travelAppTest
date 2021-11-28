package com.travelAppTest.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	Properties properties;

    public ReadConfig() {
        File src = new File("./Configuration/config.properties");

        try {
            FileInputStream fis = new FileInputStream(src);
            properties = new Properties();
            properties.load(fis);
        } catch (Exception e) {
            System.out.println("Exception occured: " + e.getMessage());
        }
    }

    public String getApplicationURL() {
        return properties.getProperty("baseURL");
    }

    public String getUserName() {
        return properties.getProperty("username");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

}
