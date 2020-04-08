package com.test.myapplication;

import com.driver.pageobject.Login;
import com.driver.utilities.XLUtils;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_Login_DDT_002 extends BaseClass{

    @Test(dataProvider="LoginData")
    public void loginDDT(String user,String pwd) throws InterruptedException, IOException {
        Login lp=new Login(driver);

        lp.goToLogin();
        lp.setUserName(user);
        logger.info("user name provided");
        lp.setPassword(pwd);
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


    @DataProvider(name="LoginData")
    Object[][] getData() throws IOException {
//        String path=System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xlsx";

        int rownum= XLUtils.getRowCount(readConfig.getTestData("data-path"), readConfig.getTestData("data-sheet"));
        int colcount=XLUtils.getCellCount(readConfig.getTestData("data-path"),readConfig.getTestData("data-sheet"),1);

        String logindata[][]=new String[rownum][colcount];

        for(int i=1;i<=rownum;i++) {
            for(int j=0;j<colcount;j++) {
                logindata[i-1][j]=XLUtils.getCellData(readConfig.getTestData("data-path"),readConfig.getTestData("data-sheet"), i,j);//1 0
            }
        }
        return logindata;
    }
}
