package utilities;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtReports {

	static ExtentTest test;
	static ExtentReports extent;

	public static void extentReportSetUp(String name) {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter("./extentReports.html");
		extent = new ExtentReports();
		htmlReporter.config().setReportName(name);
		extent.attachReporter(htmlReporter);
	}

	public static void takeScreenShotWithExtentReport(WebDriver driver, Status status, String message) {

		try {
			test.log(status, message, MediaEntityBuilder
					.createScreenCaptureFromBase64String(Screenshots.captureScreenShotInBase64(driver)).build());
		} catch (Exception e) {
			System.out.println("Error message : " + e.getMessage());
		}

	}

	public static void createReport(String reportName, String reportDiscription) {
		test = extent.createTest(reportName, reportDiscription).assignDevice("Chrome");
	}

	public static void addAuthors(String[] authors) {
		for (String temp : authors) {
			test.assignAuthor(temp);
		}
	}

	public static void addCategory(String[] category) {
		for (String temp : category) {
			test.assignCategory(temp);
		}
	}

	public static void finishReport() {
		extent.flush();
	}
}
