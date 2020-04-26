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
import org.testng.IInvokedMethod;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

final public class ReportingAdapter extends TestListenerAdapter {
    private ExtentHtmlReporter htmlReporter;
    private ExtentReports extent;
    private ExtentTest logger;

    public void onStart(ITestContext testContext) {
        new File("test-output/screenshot/").mkdirs();
        new File("test-output/recordings/").mkdirs();
        String repName="Test-Report-"+getTimeStamp()+".html";
        htmlReporter=new ExtentHtmlReporter("test-output/"+repName);//specify location of the report
        htmlReporter.loadXMLConfig("extent-config.xml");
        extent=new ExtentReports();

        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host name","localhost");
        extent.setSystemInfo("Environemnt","QA_Adapter");
        extent.setSystemInfo("user","admin");

        htmlReporter.config().setDocumentTitle("Selenium Test Project"); // Tile of report
        htmlReporter.config().setReportName("Functional Test Automation Report"); // name of the report
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
        htmlReporter.config().setTheme(Theme.DARK);
    }

    public void onTestSuccess(ITestResult tr) {
        logger=extent.createTest(tr.getName()); // create new entry in th report
        logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN)); // send the passed information to the report with GREEN color highlighted
    }

    public void onTestFailure(ITestResult tr) {
        logger=extent.createTest(tr.getName()); // create new entry in th report
        logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED)); // send the passed information to the report with GREEN color highlighted

        WebDriver driver= (WebDriver) tr.getTestContext().getAttribute("Driver");
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

    public void onTestSkipped(ITestResult tr){
        logger=extent.createTest(tr.getName()); // create new entry in th report
        logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
    }

    public void onFinish(ITestContext testContext){
        extent.flush();
    }

    private String getTimeStamp(){
        return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
    }
}