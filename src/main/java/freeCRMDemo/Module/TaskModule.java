package freeCRMDemo.Module;

import java.util.LinkedHashMap;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import basicframework.base.ConfigfileReader;
import freeCRMDemo.PO.LoginPO;
import freeCRMDemo.PO.TaskPO;

public class TaskModule 
{
	WebDriver driver;
	ConfigfileReader configfileReader;
	ExtentTest test;
	public TaskModule(WebDriver driver,ConfigfileReader configfileReader,ExtentTest test){
		this.driver=driver;
		this.configfileReader=configfileReader;
		this.test=test;
	}
	public void MoveToTaskTab(LinkedHashMap<String, String> testCaseData) throws Exception
	{
		TaskPO tp = new TaskPO(driver, test);
		tp.TaskTab();
		tp.clickOnNewTask();
		tp.keywords(testCaseData.get("Keywords"));
		tp.extend(testCaseData.get("extend"));
		tp.Complition(testCaseData.get("Complition"));
		//tp.status(testCaseData.get("status"));
		//tp.types("Call");
		tp.notes("vfd");
		tp.company("dd");
		tp.tag("fgfg");
		//tp.userAsigned("Narendra Soni");
		//tp.owner("Narendra Soni");
		//tp.priority("High");
		//tp.view("Default View");
		//tp.savedSearch("Select Saved Search");
		
		driver.switchTo().defaultContent();
		
	}
	

}
