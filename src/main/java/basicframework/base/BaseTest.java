package basicframework.base;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.model.Test;

import basicframework.utility.ExcelDataReadWrite;
import basicframework.utility.SendEmail;

public class BaseTest {
	protected static String sourceFolder;
	protected static ConfigfileReader configfileReader;
	protected static WebDriver driver;
	protected static ExcelDataReadWrite datareader;
	protected static String currentDateTime;
	protected static String reportingDirectory;
	protected static String htmlReportName;
	
	static {
		
		// fetch current date and time from calendar
		Calendar calendar= Calendar.getInstance();
		SimpleDateFormat formater= new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		currentDateTime=formater.format(calendar.getTime());
		
		// find source folder
		if(System.getProperty("user.dir").contains("target"))
			sourceFolder = System.getProperty("user.dir").replace("/target", "");
		else
			sourceFolder = System.getProperty("user.dir");
		
		// read config file
		configfileReader = new ConfigfileReader(sourceFolder);
		configfileReader.loadPropertiesFile();
		
		// fetch applicatmon Name
		String appName= configfileReader.getApplicationName();
		
		// load excel Data
		try {
			datareader= new ExcelDataReadWrite(sourceFolder);
			datareader.readExcelData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// creating reporting structure
		reportingDirectory=CreateDirectoryStructure.getReportingDirectory(
				appName,
				sourceFolder,currentDateTime);
		
		htmlReportName=reportingDirectory+appName+"_"+currentDateTime+".html";
		
		//initialise extent report
		ExtentManager.createInstance(htmlReportName, appName, configfileReader.getEnvironment());
	}
	
	
	/*@DataProvider(name="InputData")
	public Object[][] ExcelInputData(Method m) throws Exception{
		String sheetname=m.getAnnotation(Test.class).description().toString().split(",")[0].split("=")[1].trim();
		Object[][] data = new Object[datareader.getsheetData(sheetname).size()][2];  //initialize the array dimension
		
		Set<String> keyset = datareader.getsheetData(sheetname).keySet();
		Iterator<String> key = keyset.iterator();
		
		int counter =0;
		while(key.hasNext()){
			String  TC_ID= key.next().toString();
			data[counter][0]=TC_ID;
			data[counter][1]=datareader.getsheetData(sheetname).get(TC_ID);
			counter++;
		}
		return data;
	}
	*/
	
	public void InvokeBrowser(){
		
		if(configfileReader.getBrowserName().equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver",sourceFolder+"/Drivers/chromedriver.exe");
			System.setProperty("webdriver.chrome.args","--disable-logging");
			System.setProperty("webdriver.chrome.silentOutput","true");
			driver = new ChromeDriver();
		}
		else if(configfileReader.getBrowserName().equalsIgnoreCase("IE")){
			System.setProperty("webdriver.ie.driver",sourceFolder+"/Drivers/IEDriverServer.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability("requireWindowFocus", true);
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, false);
			capabilities.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR, 1);
			capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new InternetExplorerDriver(capabilities);
		}
		else if(configfileReader.getBrowserName().equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver",sourceFolder+"/Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void postOperation(Method m,ITestResult result) {
		getResult(m,result);
	}
	
	private void getResult(Method m,ITestResult result) {
		Calendar calendar= Calendar.getInstance();
		SimpleDateFormat formater= new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		currentDateTime=formater.format(calendar.getTime());
		String finalScreenShotName;
		MediaEntityModelProvider mediaModel;
		if(result.getStatus()==ITestResult.SUCCESS){
			
			try {
				finalScreenShotName = ScreenShot.capture(driver,reportingDirectory+"ScreenShot",result.getName());
				mediaModel = MediaEntityBuilder.createScreenCaptureFromPath("ScreenShot/"+finalScreenShotName).build();
				ExtentTestManager.getTest().pass("Screen shot  for refrence :",mediaModel);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(result.getStatus()==ITestResult.SKIP){
			
		}else if(result.getStatus()==ITestResult.FAILURE){
			try {
				finalScreenShotName = ScreenShot.capture(driver,reportingDirectory+"ScreenShot",result.getName());
				mediaModel = MediaEntityBuilder.createScreenCaptureFromPath("ScreenShot/"+finalScreenShotName).build();
				ExtentTestManager.getTest().fail("Screen shot  for refrence :",mediaModel);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@AfterClass(alwaysRun=true)
	public void endClasses(){
		ExtentManager.getInstance().flush();
	}
	
	@AfterTest(alwaysRun=true)
	public void endTest(){
		try{
			//driver.close();
		}catch(Exception e){
			System.out.println(e.getCause());
		}
	}
	
	@AfterSuite(alwaysRun = true)
	public void tearDown(){
		List<String> failedTestCases=ExtentManager.getFailedTestCasesList();
		String[] testCasesStatus = ExtentManager.getTestCasesStatus().split("@");
		System.out.println(configfileReader.getEmailRequired());
		if(configfileReader.getEmailRequired().equalsIgnoreCase("true")){
			try {
				SendEmail.mailTrigger(reportingDirectory, configfileReader, testCasesStatus, failedTestCases);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}
}
