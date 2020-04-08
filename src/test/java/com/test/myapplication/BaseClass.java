package com.test.myapplication;

import com.driver.utilities.ReadConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    WebDriver driver;
    Logger logger;
    ReadConfig readConfig;


    @BeforeClass
    @Parameters({"browser","platform","version","host"})
    void Setup(String br,String pf,String vr, String host) throws IOException {
        readConfig=new ReadConfig();
        PropertyConfigurator.configure("Log4j.properties");
        logger=Logger.getLogger("Test"); //printed in log file


        DesiredCapabilities dc=new DesiredCapabilities();
        dc.setBrowserName(br);
//        dc.setVersion(vr);
//        dc.setCapability("browserName",br);
//        dc.setCapability("platform",pf);
//        dc.setCapability("version",vr);

//        URL url=new URL(host);
//        driver=new RemoteWebDriver(url,dc);

//        System.setProperty("webdriver.gecko.driver", readConfig.getDriver("firefox"));
//        driver=new FirefoxDriver();
//        System.setProperty("webdriver.ie.driver", readConfig.getDriver("ie"));
//        driver=new InternetExplorerDriver();

        System.setProperty("webdriver.chrome.driver", readConfig.getDriver("chrome"));
        driver=new ChromeDriver();


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(readConfig.getApplicationURL());
    }

    @AfterClass
    void TearDown(){
        driver.quit();
    }

    public void captureScreen(WebDriver driver, String tname) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File("test-output/screenshot/" + tname + ".png");
        FileUtils.copyFile(source, target);
        System.out.println("Screenshot taken");
    }

//    These methods are used to generate random data for test execution.
    public String randomString(int length)
    {
        String generatedstring= RandomStringUtils.randomAlphabetic(length);
        return(generatedstring);
    }

    public static String randomNumber(int length) {
        String generatedString2 = RandomStringUtils.randomNumeric(length);
        return (generatedString2);
    }
}
