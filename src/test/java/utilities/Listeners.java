package utilities;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import annotations.MyAnnotations;

public class Listeners implements ITestListener {

	static WebDriver driver;

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		Listeners.driver = driver;
	}

	public void onTestStart(ITestResult result) {
		System.out.println("Test Started " + result.getName());
		ExtReports.createReport(result.getMethod().getMethodName(), result.getMethod().getDescription());
		ExtReports.addAuthors(result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(MyAnnotations.class).authors());
		ExtReports.addCategory(result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(MyAnnotations.class).category());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Success of test cases and its details are : " + result.getName());
		ExtReports.takeScreenShotWithExtentReport(driver, Status.PASS, "Pass");
	}

	public void onTestFailure(ITestResult result) {
		// Throwable throwar = result.getThrowable();

		System.out.println("Failure of test cases and its details are : " + result.getName());
		ExtReports.takeScreenShotWithExtentReport(driver, Status.FAIL, "Fail");

	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Skip of test cases and its details are : " + result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

		System.out.println("Failure of test cases and its details are withinSuccess Percentage : " + result.getName());
	}

	public void onStart(ITestContext context) {

		ExtReports.extentReportSetUp(context.getCurrentXmlTest().getName());
		System.out.println("Test Started");
	}

	public void onFinish(ITestContext context) {

		System.out.println("test finished : " + context.getName());
		ExtReports.finishReport();

	}
}