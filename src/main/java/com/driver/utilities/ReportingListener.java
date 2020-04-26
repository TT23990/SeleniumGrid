package com.driver.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.annotations.ITest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

final public class ReportingListener implements ITestListener {
    private ExtentHtmlReporter htmlReporter;
    private ExtentReports extent;
    private ExtentTest logger;

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test has started : "+result.getName());
        logger=extent.createTest(result.getName()); // create new entry in th report
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test has passed successfully : "+result.getName());
//        logger=extent.createTest(result.getName()); // create new entry in th report
        logger.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN)); // send the passed information to the report with GREEN color highlighted

    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test has failed : "+result.getName());
//        logger=extent.createTest(result.getName()); // create new entry in th report
        logger.log(Status.FAIL,MarkupHelper.createLabel(result.getName(),ExtentColor.RED)); // send the passed information to the report with GREEN color highlighted

        WebDriver driver= (WebDriver) result.getTestContext().getAttribute("Driver");
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String targetDest="screenshot/"+getTimeStamp()+".png";
        File target = new File(System.getProperty("user.dir")+"/test-output/"+targetDest);
        try {
            FileUtils.copyFile(source, target);
            logger.fail("Screenshot is below: " + logger.addScreenCaptureFromPath("./"+targetDest));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Screenshot taken");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test has skipped : "+result.getName());
//        logger=extent.createTest(result.getName()); // create new entry in th report
        logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));
    }

    @Override
    public void onStart(ITestContext context) {
        new File("test-output/screenshot/").mkdirs();
        new File("test-output/recordings/").mkdirs();

        String repName="Test-Report-"+getTimeStamp()+".html";
        htmlReporter=new ExtentHtmlReporter("test-output/"+repName);//specify location of the report
        htmlReporter.loadXMLConfig("extent-config.xml");
        extent=new ExtentReports();

        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host name","local");
        extent.setSystemInfo("Environment","QA");
        extent.setSystemInfo("user","test");

        htmlReporter.config().setDocumentTitle("Selenium Test Project"); // Tile of report
        htmlReporter.config().setReportName("Functional Test Automation Report"); // name of the report
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
        htmlReporter.config().setTheme(Theme.DARK);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    private String getTimeStamp(){
        return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
    }

}
