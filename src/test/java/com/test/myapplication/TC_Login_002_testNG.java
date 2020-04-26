package com.test.myapplication;

import com.driver.pageobject.Login;
import com.driver.utilities.DataProviderClass;
import com.driver.utilities.Excel;
import com.driver.utilities.ReportingAdapter;
import com.driver.utilities.ReportingListener;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

@Listeners(ReportingListener.class) //This listener will start Reporting class to generate Extent report.
public class TC_Login_002_testNG extends BaseClass {


    @Test(testName = "Login test name",dataProvider="dataDimension1",dataProviderClass = DataProviderClass.class)
//    ,retryAnalyzer = com.driver.utilities.MyRetry.class)
    void Login(String row) throws Exception {
        Excel.RowNum=Integer.parseInt(row);
        Login.initElements(driver,logger);
        Login.goToLogin();
        Login.setUserName(Excel.getData("UserName"));
        Login.setPassword(Excel.getData("Password"));
        Login.submitLogin();
        Assert.assertEquals(driver.getTitle().toString(),"nopCommerce demo store");
        Login.goToLogout();
    }

}