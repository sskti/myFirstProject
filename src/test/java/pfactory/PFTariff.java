package pfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.CommonUtils;

public class PFTariff {

	WebDriver driver;

	@FindBy(how = How.XPATH, using = "//a[text()='Add Tariff Plan']")
	WebElement add_Tariff;

	@FindBy(how = How.ID, using = "rental1")
	WebElement rental;

	@FindBy(how = How.ID, using = "local_minutes")
	WebElement local_minutes;

	@FindBy(how = How.ID, using = "inter_minutes")
	WebElement inter_minutes;

	@FindBy(how = How.ID, using = "sms_pack")
	WebElement sms_pack;

	@FindBy(how = How.ID, using = "sms_pack")
	WebElement local_per_min;

	@FindBy(how = How.ID, using = "minutes_charges")
	WebElement minutes_charges;

	@FindBy(how = How.ID, using = "inter_charges")
	WebElement inter_charges;

	@FindBy(how = How.ID, using = "sms_charges")
	WebElement sms_charges;

	@FindBy(how = How.XPATH, using = "//input[@value='submit']")
	WebElement submit;

	@FindBy(how = How.XPATH, using = "//*[@id=\"main\"]/div/ul/li/a")
	WebElement home;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Add Tariff Plan to Customer')]")
	WebElement add_triff_plan_cust;

	@FindBy(how = How.XPATH, using = "//*[@id=\"main\"]/div/h2")
	WebElement cogratulationsMessage;

	@FindBy(how = How.XPATH, using = "/html[1]/body[1]/section[1]/div[1]/header[1]/h1[1]")
	WebElement titleTariff;

	public PFTariff(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void createTariff(String ren, String localMinutes, String interMinutes, String smsPack, String minuteCharges,
			String interCharges, String smsCharges) {
		add_Tariff.click();
		rental.sendKeys(ren);
		local_minutes.sendKeys(localMinutes);
		inter_minutes.sendKeys(interMinutes);
		sms_pack.sendKeys(smsPack);
		minutes_charges.sendKeys(minuteCharges);
		inter_charges.sendKeys(interCharges);
		sms_charges.sendKeys(smsCharges);

		submit.click();

		String title = titleTariff.getText();
		Assert.assertEquals(Constants.ADDTARIFF_HEADING, title);

		CommonUtils.waitForElementToBeCLickabe(cogratulationsMessage, driver);

		String message = cogratulationsMessage.getText();
		Assert.assertEquals(Constants.CONGRATSMSGON_TARIFF, message);

		home.click();

	}

	public void createTariffInvalid(int alertTimeOut) {
		add_Tariff.click();
		submit.click();
		CommonUtils.waitForAlert(driver, alertTimeOut);
		String alertText = driver.switchTo().alert().getText();
		System.out.println(alertText);
		driver.switchTo().alert().accept();
	}

}