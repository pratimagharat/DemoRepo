package freeCRMDemo.PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import basicframework.base.BasePageObject;

public class LoginPO extends BasePageObject{
	
	WebDriver driver;
	public LoginPO(WebDriver driver,ExtentTest test){
		super(driver, test);
		this.driver=driver;
	}
	
	By userName= By.xpath("//input[@name='username']");
	By password=By.xpath("//input[@name='password']");
	By loginButton= By.xpath("//input[@value='Login']");
	
	
	public void setUserName(String uName){
		typeOnElement(userName,uName);
	}
	
	public void setPassword(String pName){
		typeOnElement(password,pName);
	}
	public void clickOnLoginButton(){
		clickOnElement(loginButton,"Yes");
	}

}
