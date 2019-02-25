package basicframework.base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;

public class Reporter {
	
	public static void failTestStepWithException(WebDriver driver,
			ExtentTest test,String stepDescription,String reportingDirectory,
			String screenShotName) throws IOException{
		
		String finalScreenShotName=ScreenShot.capture(driver,reportingDirectory+"ScreenShot",screenShotName);
		MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath("ScreenShot/"+finalScreenShotName).build();
		
		test.log(Status.FAIL, stepDescription,mediaModel);
		throw new RuntimeException(stepDescription);
	}
	
	public static void failTestStepWithOutException(WebDriver driver,
			ExtentTest test,String stepDescription,String reportingDirectory,
			String screenShotName) throws IOException{
		
		String finalScreenShotName=ScreenShot.capture(driver,reportingDirectory+"ScreenShot",screenShotName);
		MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath("ScreenShot/"+finalScreenShotName).build();
		
		test.log(Status.FAIL, stepDescription,mediaModel);
	}
	
	public static void failTestStepWithOutScreenShot(
			ExtentTest test,String stepDescription) {
		
		test.log(Status.FAIL, stepDescription);
	}
	
	public static void passTestStepWithScreenShot(WebDriver driver,
			ExtentTest test,String stepDescription,String reportingDirectory,
			String screenShotName) throws IOException{
		
		String finalScreenShotName=ScreenShot.capture(driver,reportingDirectory+"ScreenShot",screenShotName);
		MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath("ScreenShot/"+finalScreenShotName).build();
		
		test.log(Status.PASS, stepDescription,mediaModel);
	}
	
	public static void passTestStepWithOutScreenShot(
			ExtentTest test,String stepDescription) {
		
		test.log(Status.PASS, stepDescription);
	}
	
	
	public static void testStepInfo(
			ExtentTest test,String stepDescription){
		
		test.log(Status.INFO, stepDescription);
	}

}
