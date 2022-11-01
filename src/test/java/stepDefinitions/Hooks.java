package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utility.PropertiesReader;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void openBrowser(Scenario scenario) throws Exception {
        PropertiesReader propertiesReader = new PropertiesReader();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--headless",
                "--window-size=1440,768",
                "--disable-gpu");

        System.setProperty("webdriver.chrome.driver",propertiesReader.getDriverPath());

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(propertiesReader.getTimeout(), TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(propertiesReader.getTimeout(), TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(PropertiesReader.getValue("url"));
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        if(scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            } catch (WebDriverException noSupportScreenshot) {
                System.err.println(noSupportScreenshot.getMessage());
            }
        }
        driver.quit();
    }
}
