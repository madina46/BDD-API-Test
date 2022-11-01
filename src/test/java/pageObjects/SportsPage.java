package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.BaseClass;

public class SportsPage extends BaseClass {

    public SportsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    private final String weatherWrapper = "//div[@id='weather-wrapper']";
    private final String mainMenu = "//ul[@class='nav-primary cleared bdrgr3 cnr5']";
    // todo: replace with 'nav-primary^primary'
    private final String secondaryMenu = "//div[@class='sport nav-secondary-container']";

    private  final  String secondaryNav = "//div[contains(@class, 'sport nav-secondary-container')]//div[contains(@data-track-module, 'nav-secondary^secondary')]";
//Locators
    @FindBy(xpath = weatherWrapper + "//strong")
    private WebElement weatherText;

    @FindBy(xpath = secondaryNav)
    private WebElement secondaryNavBar;
    @FindBy(xpath = mainMenu + "//a[@href='/sport/index.html']")
    private WebElement sportsLink;

    @FindBy(xpath = secondaryMenu + "//a[@href='/sport/football/index.html']")
    private WebElement footballLink;

    public String getWeatherText() {

        WaitUntilElementVisible(this.weatherText);
        return this.weatherText.getText();
    }
    public void navigateTo(String menuName) {
        if (menuName.equals("sports")) {
            WaitUntilElementVisible(this.sportsLink);
            this.sportsLink.click();
        }

        if (menuName.equals("football")) {
            WaitUntilElementVisible(this.footballLink);
            this.footballLink.click();
        }
    }

    public String getMenuColor(String menuName) {
        if (menuName.equals("sports")) {
            WaitUntilElementVisible(this.sportsLink);
            return this.sportsLink.getCssValue("background-color");
        }

        if (menuName.equals("football")) {
            WaitUntilElementVisible(this.secondaryNavBar);
            return this.secondaryNavBar.getCssValue("background-color");
        }

        return null;
    }
}