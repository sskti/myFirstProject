package utilities;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtils {

	public static WebElement waitForElementToBeCLickabe(WebElement element, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(element));
		return ele;
	}

	public static Alert waitForAlert(WebDriver driver, int alertTimeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(alertTimeOut));
		Alert alt = wait.until(ExpectedConditions.alertIsPresent());
		return alt;
	}

}
