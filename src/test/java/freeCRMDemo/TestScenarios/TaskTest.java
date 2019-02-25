package freeCRMDemo.TestScenarios;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import basicframework.base.BaseTest;
import basicframework.base.ExtentTestManager;
import basicframework.base.Reporter;
import freeCRMDemo.Module.LoginModule;
import freeCRMDemo.Module.TaskModule;

public class TaskTest extends BaseTest
{

	@Test
	public void ClickToTaskTab() throws IOException, InterruptedException{

		// when we want to execute with one set of data
		/*ExtentTest test=ExtentTestManager.createTest("ClickOnNewTask", "This test Case is responsible to validate Task Tab functionality", "Regression");
		LinkedHashMap<String, String> testCaseData=datareader.getTestCaseData("searchparameter", "TC_01");

		TaskModule tm = new TaskModule(driver, configfileReader,test);
		try {
			tm.MoveToTaskTab(testCaseData);
		} catch (Exception e) {
			e.printStackTrace();
		}*/


		
		// when we want execute with multiple set of data
		String sheetName="searchparameter";
		
		Set<String> keyset=datareader.getsheetData(sheetName).keySet();
		Iterator<String> key= keyset.iterator();

		while(key.hasNext()){
			String TC_ID=key.next().toString();
			LinkedHashMap<String, String> testCaseData=datareader.getsheetData(sheetName).get(TC_ID);
			ExtentTest test=ExtentTestManager.createTest(testCaseData.get("TC_ID")+"_"+testCaseData.get("TestCaseDescription"),testCaseData.get("TestCaseDescription"), testCaseData.get("Category"));

			TaskModule tm = new TaskModule(driver, configfileReader,test);
			try {
				tm.MoveToTaskTab(testCaseData);

				Thread.sleep(10000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/*if(driver.getTitle().contains("CRMPRO")){
			test.log(Status.PASS, "user is Succeffully navigated to new task");
		}else{
			Reporter.failTestStepWithOutException(driver, test, "user is not able to navigated to new task", reportingDirectory, "TaskTest");
		}*/
	}
}