package freeCRMDemo.PO;

import java.awt.Robot;
import java.awt.AWTException;
import java.awt.Desktop.Action;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;

import basicframework.base.BasePageObject;

public class TaskPO extends BasePageObject
{
	By ClickToNewTask = By.xpath("//a[@title='Tasks']");
	By keywords = By.name("cs_keyword");
	By Extend = By.name("cs_extended");
	By complition = By.name("cs_completion");
	By notes = By.name("cs_notes");
	By company = By.name("cs_company_name");
	By tag = By.name("tag");


	WebDriver driver;
	ExtentTest test;
	public TaskPO(WebDriver driver,ExtentTest test)
	{
		super(driver, test);
		this.driver=driver;
		this.test= test;
	}

	public void TaskTab() throws Exception
	{
		driver.switchTo().frame("mainpanel");
		Actions action = new Actions(driver);
		WebElement MoveToTask = driver.findElement(ClickToNewTask);
		action.moveToElement(MoveToTask).build().perform();
		//driver.findElement(ClickToNewTask).click();

	}
	public void clickOnNewTask()
	{
		clickOnElement(ClickToNewTask,"Yes");
	}
	public void keywords(String keyword)
	{
		typeOnElement(keywords,keyword);
	}
	public void extend(String extend)
	{
		typeOnElement(Extend,extend);
	}
	public void Complition(String com)
	{
		typeOnElement(complition,com);
	}
	public void notes(String note)
	{
		typeOnElement(notes,note);
	}
	public void company(String companies)
	{
		typeOnElement(company,companies);
	}
	public void tag(String tag1)
	{		
		typeOnElement(tag,tag1);
	}
	public void status(String st )
	{
		WebElement Selectstatus = driver.findElement(By.name("cs_status"));
		Select status = new Select(Selectstatus);
		status.selectByVisibleText(st);
	}
	public void types(String typ)
	{
		WebElement Selecttype = driver.findElement(By.name("cs_task_type"));
		Select type = new Select(Selecttype);
		type.selectByVisibleText(typ);
	}
	public void userAsigned(String Uasigned)
	{
		WebElement SelectuserAsigned = driver.findElement(By.name("cs_by_assigned"));
		Select userAsigned = new Select(SelectuserAsigned);
		userAsigned.selectByVisibleText(Uasigned);
	}
	public void owner(String owner)
	{
		WebElement Selectowner = driver.findElement(By.name("by_user"));
		Select Owner = new Select(Selectowner);
		Owner.selectByVisibleText(owner);
	}
	public void priority(String prty)
	{
		WebElement Selectpriority = driver.findElement(By.name("cs_priority"));
		Select priority = new Select(Selectpriority);
		priority.selectByVisibleText(prty);;
	}
	public void view(String v)
	{
		WebElement SelectView = driver.findElement(By.name("view_id"));
		Select view = new Select(SelectView);
		view.selectByVisibleText(v);;
	}
	public void savedSearch(String ss)
	{
		WebElement SelectSearch = driver.findElement(By.name("cs_saved_search_id"));
		Select search = new Select(SelectSearch);
		search.selectByVisibleText(ss);;
	}
}


