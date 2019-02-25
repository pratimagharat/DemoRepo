package basicframework.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionClass {
	
	public static void moveToElement(WebDriver driver,WebElement elm){
		Actions builder = new Actions(driver);
		builder.moveToElement(elm).build().perform();
	}

}
