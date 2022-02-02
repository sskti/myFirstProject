package utilities;

import java.util.Set;

import org.openqa.selenium.WebDriver;

public class WindowHandler {

	WebDriver driver;

	public WindowHandler(WebDriver driver) {
		this.driver = driver;
	}

	public void windowHandler() {

		String mainWindow = driver.getWindowHandle();
		Set<String> childWindows = driver.getWindowHandles();
		for (String child : childWindows) {
			if (!mainWindow.equals(child)) {
				driver.switchTo().window(child).close();
// System.out.println("childWindow URL:"+driver.getCurrentUrl());
// driver.close();
			}
			driver.switchTo().window(mainWindow);
		}
	}
}
