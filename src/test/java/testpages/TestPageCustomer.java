package testpages;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import basesetup.BaseClass;
import pfactory.PFCustomer;
import utilities.ExcelData;

@Listeners(utilities.Listeners.class)
public class TestPageCustomer extends BaseClass {
	
	Logger log = LogManager.getLogger(TestPageCustomer.class);

	@Test(description = "Test Add Customer", dataProvider = "getCustomerData")
	public void testAddCustomer(String firstname, String lastname, String email, String address, String phoneno)
			throws Exception {

		System.out.println(firstname + " " + lastname + " " + email + " " + address + " " + phoneno);
		PFCustomer pfcust = new PFCustomer(driver);
		pfcust.addCustomer(firstname, lastname, email, address, phoneno);
	}

	@Test(description = "Invalid Customer")
	public void testInvalidCustomerCreation() {

		String alertTime = prop.getProperty("alerttimeout");
		PFCustomer pfcust = new PFCustomer(driver);
		String title = pfcust.getTitle();
		assertEquals(title, "Guru99 Telecom Add Customer");
		String message = pfcust.addCustomerInvalid(Integer.parseInt(alertTime));
		assertEquals(message, "please fill all fields");
	}

	@Test(description = "Test Assign")
	public void testAssignTariff() {

		String alertTime = prop.getProperty("alerttimeout");
		PFCustomer pfcust = new PFCustomer(driver);
		pfcust.addTariffToCustomer("438015");
	}

	@DataProvider(name = "getCustomerData")
	public String[][] getData() {
		String dataPath = prop.getProperty("exceldatapath1");
		String[][] data = ExcelData.getData("", 0, 0, dataPath);
		return data;
	}

}