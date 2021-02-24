package cucumber.stepdefs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GoogleStepDef {
	
	private static RemoteWebDriver driver;
	//private static WebElement target;
	
	@Before
	public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver(chromeCfg());
	}
	
	@Given("that I can access {string}")
	public void that_i_can_access(String string) {
		driver.get("https://" + string);
	}
	
	@When("I search for {string}")
	public void i_search_for(String string) {
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[2]/div[1]/div[1]/div/div[2]/input")).sendKeys(string);
      driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[2]/div[1]/div[3]/center/input[1]")).submit();
	}
	
	@When("I select the images tab")
	public void i_select_the_images_tab() {
		driver.findElement(By.xpath("//*[@id=\"hdtb-msb\"]/div[1]/div/div[2]/a")).click();
	}
	
	@Then("I should be able to view images of {string}")
	public void i_should_be_able_to_view_images_of(String string) {
		assertEquals(string + " - Google Search", driver.getTitle());
	}
	
	@After
    public static void tearDown() {
        driver.quit();
        System.out.println("driver closed");
    }
	
	public static ChromeOptions chromeCfg() {
	     Map<String, Object> prefs = new HashMap<String, Object>();
	     ChromeOptions cOptions = new ChromeOptions();
	      
	     // Settings
	     prefs.put("profile.default_content_setting_values.cookies", 2);
	     prefs.put("network.cookie.cookieBehavior", 2);
	     prefs.put("profile.block_third_party_cookies", true);

	     // Create ChromeOptions to disable Cookies pop-up
	     cOptions.setExperimentalOption("prefs", prefs);
	     // cOptions.setHeadless(headless);

	     return cOptions;
     }
}
