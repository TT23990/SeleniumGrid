package com.driver.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;

import java.util.prefs.Preferences;

public class TestNGListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test has started : "+result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test has passed successfully : "+result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test has failed : "+result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test has skipped : "+result.getName());
    }

}
