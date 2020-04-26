//package com.test.myapplication;
//
//import com.driver.pageobject.Login;
//import org.openqa.selenium.By;
//import org.testng.Assert;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//
//import java.io.IOException;
//
////Todo add data driven support
//// - add automatic directory creation  [new File("test-output/").mkdirs();]
//// - add video recording options
//
//public class TC_Login_001 extends BaseClass{
//
//    @Test
//    @Parameters({"UserName", "Password"})
//    void Login(String user, String pwd) throws InterruptedException, IOException {
//
//        Login lp=new Login(driver);
//
//        logger.info("Opening my application");
//        lp.goToLogin();
//        logger.info("Navigating to login");
//        lp.setUserName(user);
//        logger.info("User name entered");
//        lp.setPassword(pwd);
//        logger.info("Password entered");
//        lp.submitLogin();
//        logger.info("Login details submitted");
//
//        Thread.sleep(3000);
//        System.out.println("Title = " + driver.getTitle());
//        logger.info("My Application title = "+driver.getTitle());
//        Assert.assertEquals(driver.getTitle(),"nopCommerce demo store");
//
//        captureScreen(driver,"TC_Login_001");
//
//        lp.goToLogout();
//        logger.info("Logout successful");
//    }
//}
