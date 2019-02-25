package basicframework.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ScreenShot {

	public static String capture(WebDriver driver, String fileSavePath, String fileName) throws IOException {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatar= new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss_SSS");
		fileName = fileName+"_"+ formatar.format(calendar.getTime())+".png";

		TakesScreenshot ts= (TakesScreenshot) driver;
		File Source = ts.getScreenshotAs(OutputType.FILE);
		String dest= fileSavePath+"/"+fileName;
		File destination = new File(dest);
		FileUtils.copyFile(Source, destination);
		return fileName;
	}

	public static String captureFullPage(WebDriver driver, String fileSavePath, String fileName) throws IOException {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatar= new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss_SSS");
		fileName = fileName+"_"+ formatar.format(calendar.getTime())+".png";

		Screenshot screenShot = new AShot().shootingStrategy
				(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);

		String dest= fileSavePath+"/"+fileName;
		File destination = new File(dest);
		ImageIO.write(screenShot.getImage(), "png", destination);
		return fileName;
	}
}
