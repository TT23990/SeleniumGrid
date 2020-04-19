package com.driver.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {

    WebDriver ldriver;

    public Login(WebDriver rdriver){
        ldriver=rdriver;
        PageFactory.initElements(ldriver,this);
    }

    @FindBy(xpath = "//label[contains(text(),'Email:')]/following::input[1]")
    @CacheLookup
    WebElement txtUserName;

    @FindBy(xpath = "//label[contains(text(),'Password:')]/following::input[1]")
    @CacheLookup
    WebElement txtPassword;

    @FindBy(xpath = "//a[@class='ico-login']")
    @CacheLookup
    WebElement lnkLogin;

    @FindBy(xpath = "//input[@class='button-1 login-button']")
    @CacheLookup
    WebElement btnLogin;

    @FindBy(xpath = "//a[@class='ico-logout']")
    @CacheLookup
    WebElement lnkLogout;

    public void setUserName(String user){
        txtUserName.sendKeys(user);
    }
    public void setPassword(String pwd){
        txtPassword.sendKeys(pwd);
    }
    public void goToLogin(){
        lnkLogin.click();
    }
    public void submitLogin(){
        btnLogin.click();
    }
    public void goToLogout(){
        lnkLogout.click();
    }

}
