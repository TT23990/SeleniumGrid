package com.driver.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {

    WebDriver ldriver;

    public Login(WebDriver rdriver){
        ldriver=rdriver;
        PageFactory.initElements(ldriver,this);
    }

    @FindBy(xpath = "//label[contains(text(),'Email:')]/following::input[1]")
    WebElement txtUserName;

    @FindBy(xpath = "//label[contains(text(),'Password:')]/following::input[1]")
    WebElement txtPassword;

    @FindBy(xpath = "//a[@class='ico-login']")
    WebElement lnkLogin;

    @FindBy(xpath = "//input[@class='button-1 login-button']")
    WebElement btnLogin;

    @FindBy(xpath = "//a[@class='ico-logout']")
    WebElement lnkLogout;

    public void setUserName(String user){
        txtUserName.sendKeys(user);
    }
    public void setPassword(String pwd){
        txtPassword.sendKeys(pwd);
    }
    public void goToLogin(){
        lnkLogin.click();
        ldriver.manage().window().maximize();
    }
    public void submitLogin(){
        btnLogin.click();
    }
    public void goToLogout(){
        lnkLogout.click();
    }

}
