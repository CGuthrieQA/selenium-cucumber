package selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GoogleTest {

    private static RemoteWebDriver driver;

    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver(chromeCfg());

    }
    
//    @Test
//    public void testTitle() {
//    	System.out.println("testing title");
//        driver.get("https://google.com");
//        assertEquals("Google", driver.getTitle());
//    }

    @Test
    public void testKittens() {
    	System.out.println("testing title");
        driver.get("https://google.com");
        driver.findElement(By.cssSelector("q")).sendKeys("kittens");
        driver.findElement(By.cssSelector("q")).submit();
        assertEquals("kittens - Google Search", driver.getTitle());
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
        System.out.println("driver closed");
    }
    
    // Designed to return ChromeOptions to configure new ChromeDrivers in Selenium
    public static ChromeOptions chromeCfg() {
     Map<String, Object> prefs = new HashMap<String, Object>();
     ChromeOptions cOptions = new ChromeOptions();
      
     // Settings
     prefs.put("profile.default_content_setting_values.cookies", 2);
     prefs.put("network.cookie.cookieBehavior", 2);
     prefs.put("profile.block_third_party_cookies", true);

     // Create ChromeOptions to disable Cookies pop-up
     cOptions.setExperimentalOption("prefs", prefs);

     return cOptions;
     }
    
}