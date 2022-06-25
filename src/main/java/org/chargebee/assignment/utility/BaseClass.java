package org.chargebee.assignment.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseClass {

    public ReadConfigProperty readPropertyFile;
    public ReadExcelFile readExcelFile;
    public Logger LOG;
    public ExtentReports reports;
    public ExtentTest testSteps;
    public BrowserEngine browserEngine;

    public BaseClass() {
        browserEngine = new BrowserEngine();
        readPropertyFile = new ReadConfigProperty();
        readExcelFile = new ReadExcelFile();
        LOG = LogManager.getLogger(this.getClass().getName());
        ExtentSparkReporter reporter = new ExtentSparkReporter(new File(System.getProperty("user.dir")+"/ExtentReports/Report_" + getCurrentDateTime() + "_.html"));
        reports = new ExtentReports();
        reports.attachReporter(reporter);

    }

    public String captureScreenShot(WebDriver driver){
        String path = System.getProperty("user.dir")+"/Screenshots/Screenshot_"+"_"+getCurrentDateTime() + "_.png";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(src, new File(path));
            LOG.info("ScreenShot Captured");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public String getCurrentDateTime() {
        return LocalDateTime.now(Clock.systemUTC()).format(DateTimeFormatter.ofPattern("uuuu-MM-dd_HH_mm_ss"));
    }
}
