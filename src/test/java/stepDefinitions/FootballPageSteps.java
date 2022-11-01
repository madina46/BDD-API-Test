package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.FootballPage;
import utility.LeagueTableRow;
import utility.PropertiesReader;

import java.util.List;
import java.util.Optional;

public class FootballPageSteps {
    private WebDriver driver = Hooks.driver;
    private WebDriverWait wait;
    private FootballPage footballPage;

    private String gdprXpath = "//div[@class='footer_GqesM']//button[2]";

    public FootballPageSteps() throws Exception {
        PropertiesReader propertiesReader = new PropertiesReader();
        this.wait = new WebDriverWait(driver, propertiesReader.getTimeout());
        // Wait for the GDPR button to fade away.
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByXPath(gdprXpath)));
        driver.findElement(new By.ByXPath(gdprXpath)).click();
    }

    @Given("I am visiting the FootballPage")
    public void visitTheFootballPage() throws Exception {
        this.footballPage = new FootballPage(driver, wait);
    }

    @And("navigate to sports tab")
    public void navigateToSports() {
        this.footballPage.navigateTo("sports");
    }

    @And("navigate to football tab")
    public void navigateToFootball() {
        this.footballPage.navigateTo("football");
    }

    @And("navigate to headline article")
    public void navigateToHeadLineArticle() {
        this.footballPage.navigateTo("headline");
    }

    @Then("^traverse to the gallery image$")
    public void traverseToGallery() {
        this.footballPage.openGallery();
    }

    @Then("should be able to move next")
    public void traverseNext() {
        this.footballPage.moveNextGallery();
    }

    @Then("should be able to move prev")
    public void traversePrev() {
        this.footballPage.movePrevGallery();
    }

    @Then("should be able to share")
    public void share() {
        String modelTitle = this.footballPage.share("facebook");
        Assertions.assertEquals("Facebook", modelTitle);
    }

    @Then("should be able to watch video in fullscreen")
    public void watchVideoInFullScreen() {
        boolean hasFullScreen = this.footballPage.setVideoFullScreen();
        Assertions.assertTrue(hasFullScreen);
    }

    @Then("should be able to back to normal screen")
    public void watchVideoInNormalScreen() {
        boolean hasNormalScreen = this.footballPage.setVideoSmallScreen();
        Assertions.assertTrue(hasNormalScreen);
    }

    @Then("display the position Premier League Table for {string}")
    public void displayThePremierLeagueTable(String team) {

        List<LeagueTableRow> table = this.footballPage.getpremierLeagueTable();


        LeagueTableRow teamRow = table.stream().filter(r -> r.Team.equals(team)).findFirst().get();

        System.out.println("==================================================");
        System.out.print(fixedLengthString(teamRow.Pos, 5));
        System.out.print(fixedLengthString(teamRow.Team, 10));
        System.out.print(fixedLengthString(teamRow.Point, 5));
    }

    public static String fixedLengthString(String string, int length) {
        return String.format("%1$" + length + "s", string);
    }
}
