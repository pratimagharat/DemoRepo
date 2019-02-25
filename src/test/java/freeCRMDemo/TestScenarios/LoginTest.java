package freeCRMDemo.TestScenarios;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import basicframework.base.BaseTest;
import basicframework.base.ExtentTestManager;
import basicframework.base.Reporter;
import freeCRMDemo.Module.LoginModule;
import freeCRMDemo.PO.LoginPO;

public class LoginTest extends BaseTest{
	
	
	@Test
	public void LoginToGmail() throws IOException, InterruptedException{
		InvokeBrowser();
		ExtentTest test=ExtentTestManager.createTest("LoginToApplication", "This test Case is responsible to validate login functionality", "smokeTest");
		
		LoginModule lm = new LoginModule(driver, configfileReader,test);
		lm.LaunchApplication();
		lm.LoginToApplication();
		
		if(driver.getTitle().contains("CRMPRO")){
			test.log(Status.PASS, "user is Succeffully navigated to Home Page");
		}else{
			Reporter.failTestStepWithOutException(driver, test, "user is not able to navigated to Home Page", reportingDirectory, "LoginTest");
		}
		
	}
}
