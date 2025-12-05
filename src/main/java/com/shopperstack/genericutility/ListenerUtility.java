package com.shopperstack.genericutility;

import java.time.LocalDateTime;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerUtility implements ITestListener, ISuiteListener {
	public ExtentReports report;
	public ExtentTest test;
	String time = LocalDateTime.now().toString().replace(":", "_");
	//public static ExtentSparkReporter spark;
	public void onStart(ISuite suite) {
		
		// Spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./HTML Reports/"+time+"report.html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// add Env info &create Test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Os", "Windows 10");
		report.setSystemInfo("Browser", "Chrome-100");
		System.out.println("Login to app");
		Reporter.log("Report Configuration", true);
	}

	public void onFinish(ISuite suite) {
		Reporter.log("Report Backup", true);
		report.flush();
	}

	public void onTestStart(ITestResult result) {
		Reporter.log("======" + result.getMethod().getMethodName() + "====Start====", true);
		test=report.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		Reporter.log("======" + result.getMethod().getMethodName() + "====End====", true);
	}

	public void onTestFailure(ITestResult result) {

	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext result) {

	}

	public void onFinish(ITestContext result) {
	}
}
