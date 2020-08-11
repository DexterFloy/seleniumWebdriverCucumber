package junit.cucumber.utility;

import static junit.cucumber.base.BaseStepDefinition.getDriver; 
import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class WebPageHandling {

	public WebElement getElementByXPath(String xPathToSearchFor) {
		try {
			return getDriver().findElement(By.xpath(xPathToSearchFor));
		} catch (NoSuchElementException e) {
			fail("Element <<"+ xPathToSearchFor +">> not found!");
			return null;
		}
	}
}
