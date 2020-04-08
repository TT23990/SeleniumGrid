package org.example;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginTest {

    WebDriver driver;

    @BeforeTest
    @Parameters({"browser","platform","version","host"})
    void Setup(String br,String pf,String vr, String host) throws MalformedURLException {
        DesiredCapabilities dc=new DesiredCapabilities();
        dc.setBrowserName(br);
//        dc.setVersion(vr);
//        dc.setCapability("browserName",br);
//        dc.setCapability("platform",pf);
//        dc.setCapability("version",vr);

        URL url=new URL(host);
        driver=new RemoteWebDriver(url,dc);

//        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
//        driver=new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test
    @Parameters({"UserName", "Password"})
    void Login(String user, String pwd) throws InterruptedException{

        driver.findElement(By.xpath("//a[@class='ico-login']")).click();
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//label[contains(text(),'Email:')]/following::input[1]")).sendKeys(user);
        driver.findElement(By.xpath("//label[contains(text(),'Password:')]/following::input[1]")).sendKeys(pwd);
        driver.findElement(By.xpath("//input[@class='button-1 login-button']")).click();

        Thread.sleep(5000);
        System.out.println("Title = " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(),"nopCommerce demo store");
    }

    @AfterTest
    void TearDown(){
        driver.quit();

    }
}
