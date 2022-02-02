package pfactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.CommonUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.CommonUtils;

public class PFBilling {

	@FindBy(how = How.XPATH, using = "//a[text()='Pay Billing']")
	WebElement pay_bill;

	@FindBy(how = How.ID, using = "customer_id")
	WebElement customer_id;

	@FindBy(how = How.XPATH, using = "//input[@name=\"submit\"]")
	WebElement submit_btn;

	@FindBy(how = How.XPATH, using = "//tbody/tr[6]/td[2]")
	WebElement totalBill;

	@FindBy(how = How.XPATH, using = "/html[1]/body[1]/section[1]/div[1]/header[1]/h1[1]")
	WebElement bill_Heading;

	WebDriver driver;

	public PFBilling(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void showBilling(String cust_id) {
		pay_bill.click();
		customer_id.sendKeys(cust_id);
		submit_btn.click();

		String heading = bill_Heading.getText();
		Assert.assertEquals(Constants.PAYBILL_HEADING, heading);

		CommonUtils.waitForElementToBeCLickabe(totalBill, driver);
		System.out.println(totalBill.findElement(By.tagName("b")).getText());
	}

	public void showBillingInvalid(int alertTimeOut) {
		pay_bill.click();
		submit_btn.click();
		CommonUtils.waitForAlert(driver, alertTimeOut);
		String alertText = driver.switchTo().alert().getText();
		System.out.println(alertText);

		if (alertText.equals(PFBillingConstants.alertTextMsg)) {

		} else {

		}
		driver.switchTo().alert().accept();
	}
}

class PFBillingConstants {
	static String alertTextMsg = "Please Correct Value Input";

}
