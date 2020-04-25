package com.test.myapplication;

import com.driver.pageobject.Login;
import com.driver.utilities.Excel;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

@Listeners(Reporting.class)
public class TC_Login_003 extends BaseClass {


    @Test(testName = "Login test name",dataProvider="dataDimension")
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

    @DataProvider(name="dataDimension")
    public Object[][] getDataDimension(Method tr) throws Exception {
        Excel.setWorkBook(tr);
        Object dataDimension[][]=new Object[Excel.LastRow][1];

        for (int rowCnt = 0; rowCnt < Excel.LastRow; rowCnt++){
            dataDimension[rowCnt][0]=Integer.toString(rowCnt+1);
//            dataDimension[rowCnt][1]=Excel.getData("Run");

        }
        return dataDimension;
    }

}