package com.test.myapplication;

import com.driver.utilities.CommonMethods;
import com.driver.utilities.ExcelReader;
import com.driver.utilities.Config;
import com.driver.utilities.TestData;
import jxl.read.biff.BiffException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;
import org.testng.internal.Arguments;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public WebDriver driver;
    Logger logger;

    @BeforeClass
    @Parameters({"browser","platform","version"})
    void Setup(String br,String pf, String vr,ITestContext context) throws MalformedURLException {

        PropertyConfigurator.configure("Log4j.properties");
        logger=Logger.getLogger("Test"); //printed in log file
        logger.info("***************** Before Class  ***********************");
        logger.info("{Browser :"+br+ ", Version :"+vr+", Platform :"+pf+"}\nHost :"+Config.getHost());
        System.out.println("{Browser :"+br+ ", Version :"+vr+", Platform :"+pf+"}\nHost :"+Config.getHost());
//        System.out.println("Host :"+Config.getHost());

        driver=getDriver(br,pf,vr);

        context.setAttribute("Driver",driver);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        driver.get(Config.getApplicationURL()); //Application url
//        driver.manage().window().maximize();
    }

    @AfterClass
    void TearDown(){
        driver.quit();
        logger.info("******************** After Class ************************");
    }

    private WebDriver getDriver(String br, String pf, String vr) throws MalformedURLException {
        if(Config.isGrid()){
            DesiredCapabilities dc=new DesiredCapabilities();
//        dc.setBrowserName(br);
//        dc.setVersion(vr);
            dc.setCapability("browserName",br);
            dc.setCapability("platform",pf);
            dc.setCapability("version",vr);

//            URL url=new URL(Config.getHost());
            return new RemoteWebDriver(new URL(Config.getHost()),dc);
        }else{
            br=br.equalsIgnoreCase("firefox")?"gecko":br;
            System.setProperty("webdriver."+br+".driver",Config.getDriver(br));

            return br.equalsIgnoreCase("chrome")? new ChromeDriver(new ChromeOptions().addArguments("--start-maximized")):
                    br.equalsIgnoreCase("ie")?new InternetExplorerDriver():new FirefoxDriver();
        }

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
