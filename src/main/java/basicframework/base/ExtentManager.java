package basicframework.base;

import java.util.LinkedList;
import java.util.List;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.SessionStatusStats;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	static ExtentHtmlReporter htmlReporter;
	static ExtentReports extent;
	
	public static ExtentReports getInstance(){
		return extent;
	}
	
	public static synchronized void createInstance(String filefullPath,String appName,String executionEnvironment){
		htmlReporter = new ExtentHtmlReporter(filefullPath);
		//chart location
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		
		// make the chart visible on report open
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		// report title
		htmlReporter.config().setDocumentTitle(appName);
		
		
		//encoading default UTF-8
		htmlReporter.config().setEncoding("UTF-64");
		htmlReporter.config().setReportName(appName+" Automation Report");
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		// for environment section
		extent.setSystemInfo("Operating system", System.getProperty("os.name"));
		extent.setSystemInfo("Execution Environment", executionEnvironment);
		
	}
	
	public static String getTestCasesStatus(){
		SessionStatusStats ss=htmlReporter.getStatusCount();
		String ssStatus = String.valueOf(ss.getParentCount())+
				"@"+String.valueOf(ss.getParentCountPass())+
				"@"+String.valueOf(ss.getParentCountFail());
		
		System.out.println(ssStatus);
		return ssStatus;
	}
	
	public static List<String> getFailedTestCasesList(){
		List<String> failedTestcaseList = new LinkedList<String>();
		List<Test> ss= htmlReporter.getTestList();
		
		for(int i=0;i<ss.size();i++){
			Status s= ss.get(i).getStatus();
			
			if(s.toString().equals("fail")){
				System.out.println(s);
				System.out.println(ss.get(i).getDescription());
				failedTestcaseList.add(ss.get(i).getDescription());	
			}
		}	
		return failedTestcaseList;
	}
}

