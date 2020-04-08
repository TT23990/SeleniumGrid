package com.driver.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

    Properties prop;
    public ReadConfig() throws IOException {
        FileInputStream fis=new FileInputStream(new File("./configurations/config.properties"));
        prop=new Properties();
        prop.load(fis);
    }

    public String getApplicationURL(){
        return prop.getProperty("BaseUrl");
    }
    public String getUserName(){
        return prop.getProperty("UserName");
    }
    public String getPassword(){
        return prop.getProperty("Password");
    }
    public String getHost(){
        return prop.getProperty("host");
    }
    public String getDriver(String driver){
        return prop.getProperty(driver);
    }
    public String getTestData(String data){
        return prop.getProperty(data);
    }
}
