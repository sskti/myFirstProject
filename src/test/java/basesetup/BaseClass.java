package basesetup;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import utilities.Listeners;
import utilities.WindowHandler;

public class BaseClass {

	public static WebDriver driver;
	public static Properties prop;

	@BeforeMethod
	public WebDriver startBrowser() {

		String browserName = prop.getProperty("browser");
		String url = prop.getProperty("url");
		String driverPath = prop.getProperty("driverpath");
		String extensionPath = prop.getProperty("extension");
		System.out.println(driverPath + " " + extensionPath + " " + browserName);
		ChromeOptions options = new ChromeOptions();
		options.addExtensions(new File(extensionPath));
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(capabilities);
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver(options);

		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WindowHandler win = new WindowHandler(driver);
		win.windowHandler();
		Listeners.setDriver(driver);
		driver.manage().window().maximize();
		return driver;
	}

	@BeforeTest
	public void loadConfig() {
		System.out.println("cococococooc");
		prop = new Properties();
		try {

			InputStream input = new FileInputStream(System.getProperty("user.dir") + "/resources/config.properties");
			prop.load(input);
			System.out.println(prop.getProperty("browser"));

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@AfterMethod
	public void quitBrowser() {
		driver.quit();
	}

}


