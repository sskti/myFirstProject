package testpages;

import java.util.Random;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import annotations.MyAnnotations;
import basesetup.BaseClass;
import pfactory.PFBilling;
import utilities.Log;

@Listeners(utilities.Listeners.class)
public class TestPageBilling extends BaseClass {
//	Logger log = LogManager.getLogger(TestPageBilling.class);
// WebDriver driver;
	static ExtentTest extentTest;

	@MyAnnotations(category= {}, authors= {})    
	@Test
	public void testBillDetails() {
		System.out.println("hihih");
		String id = UUID.randomUUID().toString();
		System.out.println(id);
		utilities.Listeners.setDriver(driver);
		//below code initialize all web elments as well as return the object of the class.
//		PFBilling pbing = PageFactory.initElements(driver, PFBilling.class);
		PFBilling pfBill = new PFBilling(driver);
		pfBill.showBilling("438015");
		
//		log.info("BILLING PASSES");
		Log.info("Sanskruti");
	}
	

	@Test
	public void testBillInavlidDetails() {

		String alertTime = prop.getProperty("alerttimeout");
		PFBilling pfBill = new PFBilling(driver);
		pfBill.showBillingInvalid(Integer.parseInt(alertTime));
	}

}
