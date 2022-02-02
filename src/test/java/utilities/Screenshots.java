package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.util.Base64;
import org.apache.poi.util.IOUtils;

public class Screenshots {

	public static String captureScreenshot(WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File("D:/testImages/" + System.currentTimeMillis() + ".png");
		String errssflpath = Dest.getAbsolutePath();
		Files.copy(scrFile.toPath(), Dest.toPath());
		return errssflpath;
	}

	public static String captureScreenShotInBase64(WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File(System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png");
		String errssflpath = Dest.getAbsolutePath();
		Files.copy(scrFile.toPath(), Dest.toPath());
		byte[] byteImage = IOUtils.toByteArray(new FileInputStream(Dest));
		return Base64.getEncoder().encodeToString(byteImage);
	}

}
