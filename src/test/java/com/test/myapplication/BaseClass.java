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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public WebDriver driver;
    Logger logger;
    TestData td;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
//    @Parameters({"browser","platform","version","host"})
//    void Setup(String br,String pf,String vr, String host) throws IOException, BiffException, InterruptedException {
    void Setup(ITestContext context){

        PropertyConfigurator.configure("Log4j.properties");
        logger=Logger.getLogger("Test"); //printed in log file

        logger.info("***************** Before class  ***********************");
        DesiredCapabilities dc=new DesiredCapabilities();
        dc.setBrowserName("chrome");
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

        System.setProperty("webdriver.chrome.driver", Config.getDriver("chrome"));
        driver=new ChromeDriver();

        context.setAttribute("Driver",driver);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        driver.get(Config.getApplicationURL()); //Application url
        driver.manage().window().maximize();
    }

    @AfterClass
    void TearDown(){
        driver.quit();
        logger.info("******************** After Class ************************");
    }

//    public void captureScreen() throws IOException {
//        TakesScreenshot ts = (TakesScreenshot) driver;
//        File source = ts.getScreenshotAs(OutputType.FILE);
//        String targetLink=System.getProperty("user.dir")+"/test-output/screenshot/"+getTimeStamp()+".png";
//        File target = new File(targetLink);
//        FileUtils.copyFile(source, target);
//        System.out.println("Screenshot taken");
//        Reporting.TargetFile=targetLink;
//    }

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

    private String getTimeStamp(){
        return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
    }

}
