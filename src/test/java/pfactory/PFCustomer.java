package pfactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.CommonUtils;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import utilities.ExtReports;

public class PFCustomer {

	WebDriver driver;
	public static String new_Customer_Id;

	@FindBy(how = How.CSS, using = "a[href = \"addcustomer.php\"]")
	WebElement addCustomerBtn;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Pending')]")
	WebElement pendingRadioBotton;

	@FindBy(how = How.CSS, using = "input[name=fname]")
	WebElement firstName;

	@FindBy(how = How.CSS, using = "input[name=lname]")
	WebElement lastName;

	@FindBy(how = How.CSS, using = "input[name=emailid]")
	WebElement emailId;

	@FindBy(how = How.XPATH, using = "//textarea[@id='message']")
	WebElement address;

	@FindBy(how = How.ID, using = "telephoneno")
	WebElement mobileNo;

	@FindBy(how = How.CSS, using = "input[name=submit")
	WebElement submitBtn;

	@FindBy(how = How.XPATH, using = "//body/section[@id='one']/div[1]/div[1]/div[2]/h3[1]/a[1]")
	WebElement add_plan_to_cust;

	@FindBy(how = How.XPATH, using = "//*[@id=\"main\"]/div/ul/li/a")
	WebElement home;

	@FindBy(xpath = "//input[@id='customer_id']")
	WebElement cust_id1;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Home')]")
	WebElement home_button;

	@FindBy(how = How.XPATH, using = "//input[@value='submit']")
	WebElement tariffPlanCustomer;

	@FindBy(how = How.XPATH, using = "//body/section[@id='main']/div[1]/form[1]/div[2]/input[1]")
	WebElement addPlanToCustomer;

	@FindBy(how = How.XPATH, using = "//tbody/tr[1]/td[2]")
	WebElement customerId;

	public PFCustomer(WebDriver cucoDriver) {
		this.driver = cucoDriver;
		PageFactory.initElements(driver, this);
	}

	public String addCustomer(String first_name, String last_name, String email, String address1, String phone_no)
			throws Exception {
		addCustomerBtn.click();
		pendingRadioBotton.click();
		firstName.sendKeys(first_name);
		lastName.sendKeys(last_name);
		emailId.sendKeys(email);
		address.sendKeys(address1);
		mobileNo.sendKeys(phone_no);

		ExtReports.takeScreenShotWithExtentReport(driver, Status.PASS, "BeforeSubmit");

		submitBtn.click();

		ExtReports.takeScreenShotWithExtentReport(driver, Status.PASS, "AfterSubmit");

		WebElement id = customerId.findElement(By.tagName("h3"));
		System.out.println(id.getText());
		new_Customer_Id = id.getText();
		return id.getText();
	}

	public String addCustomerInvalid(int alertTimeOut) {
		submitBtn.click();

		String alertText = driver.switchTo().alert().getText();
		Assert.assertEquals(Constants.ADDCUSTOMER_ALERT, alertText);
		driver.switchTo().alert().accept();

		return alertText;
	}

	public void addTariffToCustomer(String cust_id) {
		add_plan_to_cust.click();
		cust_id1.sendKeys(cust_id);
		tariffPlanCustomer.click();
		addPlanToCustomer.click();
		home_button.click();

	}

	public static String getNew_Customer_Id() {
		return new_Customer_Id;
	}

	public static void setNew_Customer_Id(String new_Customer_Id) {
		PFCustomer.new_Customer_Id = new_Customer_Id;
	}

	public String getTitle() {
		addCustomerBtn.click();
		return driver.getTitle();
	}
}
