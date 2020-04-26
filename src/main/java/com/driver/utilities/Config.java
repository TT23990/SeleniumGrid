package com.driver.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

final public class Config {

    final static Properties prop=new Properties();
    static {
        try {
            FileInputStream fis=new FileInputStream(new File("./configurations/config.properties"));
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getApplicationURL(){
        return prop.getProperty("BaseUrl");
    }
    public static String getUserName(){
        return prop.getProperty("UserName");
    }
    public static String getPassword(){
        return prop.getProperty("Password");
    }
    public static String getHost(){
        return prop.getProperty("host");
    }
    public static String getDriver(String driver){
        return prop.getProperty(driver);
    }
    public static String getTestData(String data){
        return prop.getProperty(data);
    }
    public static Boolean isGrid(){
        return Boolean.valueOf(prop.getProperty("grid"));
    }
}
