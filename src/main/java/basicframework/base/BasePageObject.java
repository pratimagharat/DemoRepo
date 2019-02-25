package basicframework.base;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import basicframework.utility.JSWaiter;
import basicframework.utility.JavaScriptMethods;

public class BasePageObject {
	
	WebDriver driver;
	protected ExtentTest test;
	WebDriverWait wait;
	WebElement elm;
	
	public BasePageObject(WebDriver driver,ExtentTest test){
		this.driver=driver;
		this.test=test;
		//wait = new WebDriverWait(driver, 40);
	}
	
	public String gatPageTitle(){
		return driver.getTitle();
	}
	
	public void clickOnElement(By locator,String wantToHighlight){
		elm=findElementsafely(locator);
		if(wantToHighlight.equalsIgnoreCase("Yes")){
			JavaScriptMethods.highlightElement(driver, elm);
		}
		elm.click();
	}
	
	public void clickOnElement(WebElement element,String wantToHighlight){
		elm=findElementsafely(element);
		if(wantToHighlight.equalsIgnoreCase("Yes")){
			JavaScriptMethods.highlightElement(driver, elm);
		}
		elm.click();
	}
	
	public void typeOnElement(By locator,String value){	
		elm=findElementsafely(locator);
		elm.clear();
		elm.sendKeys(value);
	}
	
	public void typeOnElement(WebElement element,String value){	
		elm=findElementsafely(element);
		elm.clear();
		elm.sendKeys(value);
	}
	
	public void waitForVisibilityOf(By locator,Integer...timeoutSeconds){
		int attempts =0;
		while (attempts < 2){
			try{
				waitFor(ExpectedConditions.visibilityOfElementLocated(locator),(timeoutSeconds.length >0 ?timeoutSeconds[0]:null));
				break;
			}catch(Exception e){
				attempts++;
			}
		}
	}
	
	public void waitForVisibilityOf(WebElement element,Integer...timeoutSeconds){
		int attempts =0;
		while (attempts < 2){
			try{
				waitFor(ExpectedConditions.visibilityOf(element),(timeoutSeconds.length >0 ?timeoutSeconds[0]:null));
				break;
			}catch(Exception e){
				attempts++;
			}
		}
	}
	
	public void waitForInVisibilityOf(By locator,Integer...timeoutSeconds){
		int attempts =0;
		while (attempts < 2){
			try{
				wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
				break;
			}catch(Exception e){
				attempts++;
			}
		}
	}
	
	public void waitForInVisibilityOf(WebElement element,Integer...timeoutSeconds){
		int attempts =0;
		while (attempts < 2){
			try{
				wait.until(ExpectedConditions.invisibilityOf(element));
				break;
			}catch(Exception e){
				attempts++;
			}
		}
	}
	
	public void waitForElementTobeclickable(By locator,Integer...timeoutSeconds){
		int attempts =0;
		while (attempts < 2){
			try{
				waitFor(ExpectedConditions.elementToBeClickable(locator),(timeoutSeconds.length >0 ?timeoutSeconds[0]:null));
				break;
			}catch(Exception e){
				attempts++;
			}
		}
	}
	
	public void waitForElementTobeclickable(WebElement element,Integer...timeoutSeconds){
		int attempts =0;
		while (attempts < 2){
			try{
				waitFor(ExpectedConditions.elementToBeClickable(element),(timeoutSeconds.length >0 ?timeoutSeconds[0]:null));
				break;
			}catch(Exception e){
				attempts++;
			}
		}
	}
	
	public void waitFor(ExpectedCondition<WebElement> condition, Integer timeoutInSeconds){
		timeoutInSeconds = timeoutInSeconds !=null ? timeoutInSeconds:30;
		WebDriverWait wait = new WebDriverWait(driver,timeoutInSeconds);
		wait.until(condition);
	}
	
	public void clickUsingJavaScriptExecutor(By locator,String wantToHighlight){
		elm=findElementsafely(locator);
		if(wantToHighlight.equalsIgnoreCase("Yes")){
			JavaScriptMethods.highlightElement(driver, elm);
		}
		JavaScriptMethods.clickUsingJavaScript(driver, elm);
	}
	
	public void clickUsingJavaScriptExecutor(WebElement element,String wantToHighlight){
		elm=findElementsafely(element);
		if(wantToHighlight.equalsIgnoreCase("Yes")){
			JavaScriptMethods.highlightElement(driver, elm);
		}
		JavaScriptMethods.clickUsingJavaScript(driver, elm);
	}
	
	
	// use in case of angular,ajax,jque based application
	public void waitTillThePagefullLoad(){
		JSWaiter.setDriver(driver);
		JSWaiter.waitTillThePageFullLoad();
	}
	
	public String getElementText(By locator){
		return findElementsafely(locator).getText();
	}
	
	public String getElementText(WebElement element){
		return findElementsafely(element).getText();
	}
	
	public String getElementAttributeValue(By locator,String attributeName){
		return findElementsafely(locator).getAttribute(attributeName);
	}
	
	public String getElementAttributeValue(WebElement element,String attributeName){
		return findElementsafely(element).getAttribute(attributeName);
	}
	
	public String getElementCssValue(By locator,String CssPropertyName){
		return findElementsafely(locator).getCssValue(CssPropertyName);
	}
	
	public String getElementCssValue(WebElement element,String CssPropertyName){
		return findElementsafely(element).getCssValue(CssPropertyName);
	}
	
	public WebElement findElementsafely(final By locator){
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(180, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS)
					.ignoring(StaleElementReferenceException.class,NoSuchElementException.class);
		
		WebElement foo = wait.until(new Function<WebDriver,WebElement>(){
			public WebElement apply(WebDriver driver){
				return driver.findElement(locator);
			}
		});
		return foo;
	}
	
	public WebElement findElementsafely(final WebElement element){
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(180, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS)
					.ignoring(StaleElementReferenceException.class,NoSuchElementException.class);
		
		WebElement foo = wait.until(new Function<WebDriver,WebElement>(){
			public WebElement apply(WebDriver driver){
				return element;
			}
		});
		return foo;
	}
}
