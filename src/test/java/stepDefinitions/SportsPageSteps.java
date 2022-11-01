package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.SportsPage;
import utility.DateHelper;
import utility.PropertiesReader;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SportsPageSteps {

    private WebDriver driver = Hooks.driver;
    private WebDriverWait wait;
    private SportsPage sportsPage;
    private  String gdprXpath = "//div[@class='footer_GqesM']//button[2]";
    public SportsPageSteps() throws Exception {
        PropertiesReader propertiesReader = new PropertiesReader();
        this.wait = new WebDriverWait(driver, propertiesReader.getTimeout());
        // Wait for the GDPR button to fade away.
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByXPath(gdprXpath)));
        driver.findElement(new By.ByXPath(gdprXpath)).click();
    }

    @Given("^I am visiting the Homepage$")
    public void visitTheHomePage() {
        this.sportsPage = new SportsPage(driver, wait);
    }

    @Then("^The weather day should be the present day with correct date\\.$")
    public void theWeatherDayShouldBeThePresentDayWithCorrectDate() {
        String weatherDay = this.sportsPage.getWeatherText();

        String pattern = "EEEE, MMM ";
        Calendar now = Calendar.getInstance();
        String stringDate = new SimpleDateFormat(pattern).format(now.getTime());
        stringDate = stringDate + now.get(Calendar.DAY_OF_MONTH) + DateHelper.getOrdinal(now.get(Calendar.DAY_OF_MONTH)) + " " + now.get(Calendar.YEAR);

        Assertions.assertEquals(stringDate, weatherDay);
    }

    @And("navigate to sports")
    public void navigateToSports() {
        this.sportsPage.navigateTo("sports");
    }

    @Then("the primary and secondary nav has same color.")
    public void thePrimaryAndSecondaryNavHasSameColor() {
        String sportBGColor = this.sportsPage.getMenuColor("sports");
        String footballBGColor = this.sportsPage.getMenuColor("football");
        Assertions.assertEquals(sportBGColor, footballBGColor);
    }
}
