package testpages;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import basesetup.BaseClass;
import pfactory.PFTariff;
import utilities.ExcelData;


@Listeners(utilities.Listeners.class)
public class TestPageTariff extends BaseClass {
	
	@Test(dataProvider = "TariffData")
	public void testTariffCreation(String rental, String localmins, String intermins, String smspacks,
			String mincharges, String intercharges, String smscharges) throws IOException {

		PFTariff pfTariff = new PFTariff(driver);
		System.out.println(rental);
		pfTariff.createTariff(rental, localmins, intermins, smspacks, mincharges, intercharges, smscharges);

	}

	@Test
	public void testTariffInvalidCreation() {
		String alertTime = prop.getProperty("alerttimeout");
		PFTariff pfTariffInvalid = new PFTariff(driver);
		pfTariffInvalid.createTariffInvalid(Integer.parseInt(alertTime));
	}

	@DataProvider(name = "TariffData")
	public String[][] getData() {
		
		String dataPath = prop.getProperty("exceldatapath1");
		String[][] dataa = ExcelData.primaryKeyGetData("99", "", dataPath);
		return dataa;
	}

}