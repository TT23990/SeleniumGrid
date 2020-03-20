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

    RemoteWebDriver driver;
    DesiredCapabilities dc;

//	WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    void Setup(String br) throws MalformedURLException {

        dc=new DesiredCapabilities();

        if(br.equals("Chrome")){
            dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
            dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
        }else if(br.equals("Firefox")){
            dc.setCapability(CapabilityType.BROWSER_NAME,BrowserType.FIREFOX);
            dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);
        }
        //URL url=new URL("http://localhost:4444/wd/hub");
        URL url=new URL("http://18.191.220.167:4444//wd/hub");
        driver=new RemoteWebDriver(url,dc);

//        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
//        driver=new ChromeDriver();

		driver.get("https://demo.nopcommerce.com/");

    }

    @Test
    void Login() throws InterruptedException{
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//a[@class='ico-login']")).click();
        driver.findElement(By.id("Email")).sendKeys("seleniumtest@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("Test@123");
        driver.findElement(By.xpath("//input[@class='button-1 login-button']")).click();

        Thread.sleep(5000);

        Assert.assertEquals(driver.getTitle(),"nopCommerce demo store");
    }

    @AfterTest
    void TearDown(){
        driver.quit();

    }
}
