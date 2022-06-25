package org.chargebee.assignment.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfigProperty {

    private Properties prop;

    public ReadConfigProperty() {

        File file = new File("Config/config.properties");
        try {
            FileInputStream fis = new FileInputStream(file);
            prop = new Properties();
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getBrowser(){
        return prop.getProperty("browser");
    }
    public String getAppUrl(){
        return prop.getProperty("appUrl");
    }

    public String getPropertyValue(String propertyName){
        return prop.getProperty(propertyName);
    }
}
