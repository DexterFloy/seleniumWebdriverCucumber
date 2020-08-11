package junit.cucumber.smoketest;

import static junit.cucumber.utility.ValuesPropertiesHandling.getConfigProperties;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static junit.cucumber.base.BaseStepDefinition.getDriver;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.cucumber.utility.WebPageHandling;

public class StepDefinitionsHomePage {

	private static final String XPATH_DIV_DATAIDENTIFIER = ".//div[@data-identifier='%s']";
	private static final String XPATH_ANCHOR_TEXT = ".//a[contains(text(),'%s')]";
	private static final String XPATH_INPUT_TYPE = ".//input[@type='%s']";	
	private static final String XPATH_DIV_CLASS = ".//div[@class='%s']"; 
	private static Properties properties = getConfigProperties();

	@When("I Click The \"(.*)\" Button")
	public void iClickTheButton(String buttonElement) {
		switch (buttonElement) {
			case "User":
				waitAndClick(10, String.format(XPATH_DIV_CLASS,"gb_oa"));
				break;
			case "Sign out":
				new WebPageHandling().getElementByXPath(String.format(XPATH_ANCHOR_TEXT, buttonElement)).click();
				break;
			default:
				fail(buttonElement + " not yet handled!");
				break;
		}
	}
	
	@When("I Insert \"(.*)\" In The \"(.*)\" Area")
	public void iEnterInTheArea(String value, String area) {
		insertIntoArea(10, String.format(XPATH_INPUT_TYPE, area), properties.getProperty(value,value));
	}

	@When("I Hit The \"(.*)\" Keyboard Key On \"(.*)\" Area")
	public void iHitKeyboardKey(String keyValue, String area) {
		switch (keyValue) {
			case "enter":
				new WebPageHandling().getElementByXPath(String.format(XPATH_INPUT_TYPE, area)).sendKeys(Keys.ENTER);
				break;
			default:
				fail(keyValue + " not yet handled!");
			}
	}
	
	@Then("I Verify That \"(.*)\" - \"(.*)\" Is Loaded")
	public void iVerifyThatSpecificElementIsLoaded(String elementName, String specificElement) {
		switch (specificElement) {
		case "mailTab":
			waitForWebElement(10,String.format(XPATH_ANCHOR_TEXT, elementName));
			break;
		default:
			fail(specificElement + " not yet handled!");
			break;
		}
	}

	@Then("I Verify That \"(.*)\" Page Is Loaded")
	public void iVerifyThatPageIsLoaded(String pageName) {
		switch (pageName) {
		case "targetSiteEnd":
			waitForWebElement(10, String.format(XPATH_DIV_DATAIDENTIFIER, properties.getProperty("username","username")));  
			assertTrue(getDriver().getCurrentUrl().contains(properties.getProperty(pageName,pageName)));
			break;
		default:
			fail(pageName + " not yet handled!");
			break;
		}
	}
	
	private void waitAndClick(int timeout, String xpath) {
		waitForWebElement(timeout, xpath);
		new WebPageHandling().getElementByXPath(xpath).click();
	}
	
	private void insertIntoArea(int timeout, String xpath, String value) {
		waitForWebElement(timeout, xpath);
		new WebPageHandling().getElementByXPath(xpath).sendKeys(value);
	}
	
	private void waitForWebElement(int timeout, String xpath) {
		WebDriverWait waiter = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
		waiter.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}

}
