package org.example;

import com.driver.utilities.DataProviderClass;
import com.driver.utilities.Excel;
import com.driver.utilities.ExcelReader;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginTest {

    WebDriver driver;

    @BeforeTest
//    @Parameters({"browser","platform","version","host"})
//    void Setup(String br,String pf,String vr, String host) throws MalformedURLException {
            void Setup(){
//        DesiredCapabilities dc=new DesiredCapabilities();
//        dc.setBrowserName(br);
//        dc.setVersion(vr);
//        dc.setCapability("browserName",br);
//        dc.setCapability("platform",pf);
//        dc.setCapability("version",vr);

//        URL url=new URL(host);
//        driver=new RemoteWebDriver(url,dc);

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver=new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");
    }

    @Test(testName = "Login",dataProvider="dataDimension")
//    @Parameters({"UserName", "Password"})
//    void Login(String user, String pwd) throws Exception {
            void Login(String row) throws Exception {
        Excel.RowNum=Integer.parseInt(row);

        driver.findElement(By.xpath("//a[@class='ico-login']")).click();

        driver.manage().window().maximize();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//label[contains(text(),'Email:')]/following::input[1]"))
                .sendKeys(Excel.getData("UserName"));
        driver.findElement(By.xpath("//label[contains(text(),'Password:')]/following::input[1]"))
                .sendKeys(Excel.getData("Password"));
        driver.findElement(By.xpath("//input[@class='button-1 login-button']")).click();

        Thread.sleep(3000);

        System.out.println("Title = " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(),"nopCommerce demo store");

        driver.findElement(By.xpath("//a[@class='ico-logout']")).click();

    }

    @AfterTest
    void TearDown(){
        driver.quit();

    }

    @DataProvider(name="dataDimension")
    public Object[][] getDataDimension() throws Exception {
//        readConfig=new ReadConfig();
        Object dataDimension[][]=new Object[Excel.LastRow][1];

        for (int rowCnt = 0; rowCnt < Excel.LastRow; rowCnt++){
            dataDimension[rowCnt][0]=Integer.toString(rowCnt+1);
//            dataDimension[rowCnt][1]=Excel.getData("Run");

        }
        return dataDimension;
    }
}
