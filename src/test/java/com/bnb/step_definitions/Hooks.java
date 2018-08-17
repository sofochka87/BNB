package com.bnb.step_definitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.bnb.utilities.*;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import static com.bnb.utilities.TestConstants.logger;
public class Hooks {

	@Before
	public void setUp() throws Exception{
		Driver.getDriver().manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		// Driver.getDriver().manage().window().fullscreen();
		// Driver.getDriver().get(ConfigurationReader.getProperty("url"));
	}

	// @Before("@amazon_check")
	// public void setUpAmazon() {
	// Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	// Driver.getDriver().manage().window().fullscreen();
	// Driver.getDriver().get("http://amazon.com");
	// }

	@Before("@db")
	public void setUpDBConnection() {
		BrowserUtils.waitFor(5);
		System.out.println("HHHHHEEEELLLLOOOO");
		logger.info("Creating Connection");
		DBUtils.createConnection();
	}

	@After("@db")
	public void tearDownDBConnection() {
		logger.info("Destroying connection");
		DBUtils.destroy();
	}

	@After
	public void tearDown(Scenario scenario)throws Exception {
		// only takes a screenshot if the scenario fails
		if (scenario.isFailed()) {
			// taking a screenshot
			final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
			// adding the screenshot to the report
			scenario.embed(screenshot, "image/png");
		}
		Driver.closeDriver();
	}

}