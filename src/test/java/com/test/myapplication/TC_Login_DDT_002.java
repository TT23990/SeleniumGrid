package com.test.myapplication;

import com.driver.pageobject.Login;
import com.driver.utilities.DataProviderClass;
import com.driver.utilities.ExcelReader;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

//@Listeners(com.driver.utilities.TestNGListener.class) //Add into TestNG.xml
public class TC_Login_DDT_002 extends BaseClass{

    @Test (testName = "Login",dataProvider="dataDimension",dataProviderClass = DataProviderClass.class)
//    @Parameters({"UserName", "Password"})
    public void loginDDT(String row) throws InterruptedException, IOException {
        Login lp=new Login(driver);

        lp.goToLogin();
//        lp.setUserName(td.getLoginUser().get(Integer.parseInt(row))); //ReadCellData
        lp.setUserName(ExcelReader.ReadCellData("UserName",Integer.parseInt(row)+1));
        logger.info("user name provided");
        lp.setPassword(ExcelReader.ReadCellData("Password",Integer.parseInt(row)+1));
//        lp.setUserName(td.getLoginUser().get(Integer.parseInt(row)));
        logger.info("password provided");
        lp.submitLogin();

        Thread.sleep(3000);
        try {
            lp.goToLogout();
        }catch (NoSuchElementException e){
            logger.warn("Login failed");
            captureScreen(driver,"loginDDT");
            Assert.assertTrue(false);
        }

//        if(isAlertPresent()==true) {
//            driver.switchTo().alert().accept();//close alert
//            driver.switchTo().defaultContent();
//            Assert.assertTrue(false);
//            logger.warn("Login failed");
//        } else {
//            Assert.assertTrue(true);
//            logger.info("Login passed");
//            lp.goToLogout();
//            Thread.sleep(3000);
//            driver.switchTo().alert().accept();//close logout alert
//            driver.switchTo().defaultContent();
//
//        }


    }

    //user defined method created to check alert is presetn or not
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch(NoAlertPresentException e) {
            return false;
        }
    }

}
