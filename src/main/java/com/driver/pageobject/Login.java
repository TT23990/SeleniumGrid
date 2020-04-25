package com.driver.pageobject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Login {

    private static WebDriver wd;
    private static Logger logger;

    @FindBy(xpath = "//label[contains(text(),'Email:')]/following::input[1]")
    @CacheLookup
    private static WebElement txtUserName;

    @FindBy(xpath = "//label[contains(text(),'Password:')]/following::input[1]")
    @CacheLookup
    private static WebElement txtPassword;

    @FindBy(xpath = "//a[@class='ico-login']")
    @CacheLookup
    private static WebElement lnkLogin;

    @FindBy(xpath = "//input[@class='button-1 login-button']")
    @CacheLookup
    private static WebElement btnLogin;

    @FindBy(linkText = "Log out")
    @CacheLookup
    private static WebElement lnkLogout;

    public static void initElements(WebDriver remoteDriver,Logger remoteLogger){
        wd=remoteDriver;
        logger=remoteLogger;
        PageFactory.initElements(wd,Login.class);
        logger.info("****************** Initializing test execution *********************");
    }
    public static void goToLogin() throws InterruptedException {
        wd.navigate().refresh();
        Thread.sleep(1000);
        lnkLogin.click();
        logger.info("Navigated to login screen");
    }
    public static void setUserName(String user) {
        txtUserName.sendKeys(user);
        logger.info("Username: "+user+" entered");
    }
    public static void setPassword(String pwd){
        txtPassword.sendKeys(pwd);
        logger.info("Password entered");
    }
    public static void submitLogin() throws InterruptedException {
        btnLogin.click();
        Thread.sleep(2000);
        logger.info("Login credentials submitted");
    }
    public static void goToLogout(){
        try {
            lnkLogout.click();
            logger.info("Application logout selected");
        }catch (Exception e){
            logger.warn("Unable to select logout");
        }
    }

}
