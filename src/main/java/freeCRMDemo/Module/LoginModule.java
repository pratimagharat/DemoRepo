package freeCRMDemo.Module;

import java.util.LinkedHashMap;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import basicframework.base.ConfigfileReader;
import freeCRMDemo.PO.LoginPO;

public class LoginModule {
	WebDriver driver;
	ConfigfileReader configfileReader;
	ExtentTest test;
	public LoginModule(WebDriver driver,ConfigfileReader configfileReader,ExtentTest test){
		this.driver=driver;
		this.configfileReader=configfileReader;
		this.test=test;
	}
	
	public void LaunchApplication(){
		
		String url = null;
		if(configfileReader.getEnvironment().equalsIgnoreCase("QA"))
			url=configfileReader.getQAUrl();
		else if(configfileReader.getEnvironment().equalsIgnoreCase("DEV"))
			url=configfileReader.getDevUrl();
		else if (configfileReader.getEnvironment().equalsIgnoreCase("UAT"))
			url=configfileReader.getUATUrl();
		
		driver.get(url);
		if(driver.getTitle().contains("Free CRM")){
			test.log(Status.PASS, "user is able to launch "+url);
		}else{
			test.log(Status.FAIL, "user is not able to launch "+url);
		}
		
	}

	public void LoginToApplication() throws InterruptedException{
		LoginPO lp = new LoginPO(driver, test);
		lp.setUserName(configfileReader.getUserId());
		test.log(Status.PASS, "user is able to enter userName in userId field");
		lp.setPassword(configfileReader.getPassword());
		test.log(Status.PASS, "user is able to enter password in password field");
		Thread.sleep(10000);
		lp.clickOnLoginButton();
		test.log(Status.PASS, "user is successfully able to click on login button");
	}
}
