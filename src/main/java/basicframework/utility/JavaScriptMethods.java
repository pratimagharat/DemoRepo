package basicframework.utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class JavaScriptMethods {
	
	// this method is used to scroll vertically
	// if we want to scroll horizontal than use(400,0)
	public static void scrollingTo(WebDriver driver){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,500)");
	}

	public static void scrollIntoView(WebDriver driver,WebElement elm){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",elm);
	}
	
	public static void highlightElement(WebDriver driver,WebElement elm){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style','border:2px solid red;');",elm);
	}
	
	public static void typeUsingJavaScript(WebDriver driver,WebElement elm,String valueToType){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].value='"+valueToType+"';",elm);
	}
	
	public static void clickUsingJavaScript(WebDriver driver,WebElement elm){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",elm);
	}
	
	public static String getTitleUsingJavaScript(WebDriver driver){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		String stext=js.executeScript("return document.title;").toString();
		return stext;
	}
}
