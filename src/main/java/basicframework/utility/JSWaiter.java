package basicframework.utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JSWaiter {

	private static WebDriver jsWaitdriver;
	private static WebDriverWait jsWait;
	private static JavascriptExecutor jsExec;

	// Get the driver
	public static void setDriver(WebDriver driver){
		jsWaitdriver=driver;
		jsWait= new WebDriverWait(jsWaitdriver,10);
		jsExec=(JavascriptExecutor) jsWaitdriver;
	}

	// wait for Jquery load
	public static void waitForJQueryLoad(){

		ExpectedCondition<Boolean> jQueryLoad= 
				driver -> ((Long)((JavascriptExecutor) jsWaitdriver)
						.executeScript("return jQuery.active") ==0);

				for(int i=0;i<5;i++){
					//Get query is ready
					boolean jqueryReady=(boolean) jsExec.executeScript("return jQuery.active ==0");
					// wait untile is ready
					if(! jqueryReady){
						//wait for to jquery to load
						jsWait.until(jQueryLoad);
					}else{
						break;
					}

					sleep(1000);
				}
	}

	public static void waitUntilJSReady(){
		WebDriverWait Wait= new WebDriverWait(jsWaitdriver,15);
		JavascriptExecutor jsExec= (JavascriptExecutor)jsWaitdriver;

		// wait for javascript to load
		ExpectedCondition<Boolean> jsLoad= 
				driver -> ((JavascriptExecutor) jsWaitdriver)
				.executeScript("return document.readyState").toString().equals("complete");

				for(int i=0;i<5;i++){
					//Get query is ready
					boolean jsReady=(boolean) jsExec.executeScript("return document.readyState").toString().equals("complete");
					// wait untile is ready
					if(! jsReady){
						//wait for to jquery to load
						Wait.until(jsLoad);
					}else{
						break;
					}

					sleep(1000);
				}

	}

	public static void waitUntilJQueryReady(){
		JavascriptExecutor jsExec= (JavascriptExecutor)jsWaitdriver;

		//first check that JQuery is defined on the page. if it is than wait for AJAX

		Boolean jQueryDefined= (Boolean)jsExec.executeScript("return typeof jQuery !='undefined'");

		if(jQueryDefined == true){
			waitForJQueryLoad();
			waitUntilJSReady();
		}else{
			System.out.println("jQuery is not defined on this sites");
		}
	}

	//wait untill JQuery, Angular and JS is Ready
	public static void waitTillThePageFullLoad(){
		waitUntilJQueryReady();

	}

	// wait for Angular to load
	public static void waitForAngularLoad(){
		WebDriverWait wait= new WebDriverWait(jsWaitdriver,15);
		JavascriptExecutor jsExec= (JavascriptExecutor)jsWaitdriver;

		String angularReadyScript= "return angular.element(document).injector().get('$http').pendingRequests.length ==0";

		// wait for angular to load
		ExpectedCondition<Boolean> angularLoad= 
				driver -> Boolean.valueOf(((JavascriptExecutor) driver)
						.executeScript(angularReadyScript).toString());

				// Get Angular is ready
				boolean angularReady= Boolean.valueOf(jsExec.executeScript(angularReadyScript).toString());

				//wait angular until is ready
				if(! angularReady){
					System.out.println("Angular is not Ready");
					wait.until(angularLoad);
				}else{
					System.out.println("Angular is Ready");
				}
	}

	// wait for Angular to load
	public static void waitUntilAngularReady(){

		JavascriptExecutor jsExec= (JavascriptExecutor)jsWaitdriver;

		//first check that angular is defined on the page.if it is than wait for angular to load
		boolean angularundefined= (Boolean) jsExec.executeScript(" return window.angular == undefined");
		if(! angularundefined){
			Boolean angularInjectorUndefined = (Boolean) jsExec.executeScript("return angular.element(document).injector() ==undefined");
			if(!angularInjectorUndefined){
				//wait angular load
				waitForAngularLoad();
				// wait for JS Load
				waitUntilJSReady();
				
				sleep(2000);
			}else{
				System.out.println("angular injector is not defined on this site");
			}
		}else{
			System.out.println("Angular is not defined on this site");
		}

	}


	private static void sleep(Integer seconds) {
		long secondsLong=(long) seconds;
		try {
			Thread.sleep(secondsLong);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
