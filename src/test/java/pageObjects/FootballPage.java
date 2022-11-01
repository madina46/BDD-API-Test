package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.BaseClass;
import utility.LeagueTableRow;

import java.util.*;

import static java.lang.Integer.parseInt;

public class FootballPage extends BaseClass {

    private boolean isGalleryOpen = false;

    public FootballPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }
//Locators
    @FindBy(xpath = "//ul[@class='nav-primary cleared bdrgr3 cnr5']//a[@href='/sport/index.html']")
    private WebElement sportsLink;

    @FindBy(xpath = "//div[@class='sport nav-secondary-container']//a[@href='/sport/football/index.html']")
    private WebElement footballLink;

    @FindBy(xpath = "//div[@class='article article-tri-headline']//h2/a[1]")
    private WebElement headlineLink;

    @FindBy(xpath = "//div[contains(@class, 'artSplitter')][1]//div[@class='image-wrap']")
    private WebElement galleryButton;

    @FindBy(xpath = "//div[contains(@class, 'paginationButtons')]//button[contains(@class, 'previousButton')]")
    private WebElement prevButton;

    @FindBy(xpath = "//div[contains(@class, 'paginationButtons')]//button[contains(@class, 'nextButton')]")
    private WebElement nextButton;

    @FindBy(xpath = "//div[contains(@class, 'galleryWrapper')][1]//button[contains(@class,'closeButton')]")
    private WebElement closeButton;

    @FindBy(xpath = "//div[contains(@class, 'artSplitter')][1]//li[contains(@class,'shareIcon')]")
    private WebElement shareicon;
    @FindBy(xpath = "//div[contains(@class, 'artSplitter')][1]//li[contains(@class,'linkItem')][1]")
    private WebElement facebook;

    @FindBy(xpath = "//div[contains(@id, 'footballco-video-player-container')]")
    private WebElement videoPlayer;

    @FindBy(xpath = "//div[@class='vjs-control-bar']//button[contains(@class, 'vjs-fullscreen-control')]")
    private WebElement fullScreenButtons;

    @FindBy(xpath = "//div[contains(@class, 'competitionTable')]/table")
    private WebElement premierLeagueTable;

    public String getTitle() {
        return this.driver.getTitle();
    }

    public void navigateTo(String menuName) {
        if (menuName.equals("sports")) {
            WaitUntilElementVisible(sportsLink);
            this.sportsLink.click();
        }

        if (menuName.equals("football")) {
            WaitUntilElementVisible(footballLink);
            this.footballLink.click();
        }

        if (menuName.equals("headline")) {
            WaitUntilElementVisible(this.headlineLink);
            this.headlineLink.click();
        }
    }

    public void openGallery() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", this.galleryButton);
        this.galleryButton.isDisplayed();
        this.galleryButton.click();
        this.isGalleryOpen = true;
    }

    public void moveNextGallery() {
        if (this.isGalleryOpen) {
            this.nextButton.click();
        }
    }

    public void movePrevGallery() {
        if (this.isGalleryOpen) {
            this.prevButton.click();
        }
    }

    public String share(String media) {
        String modelDialogTitle = "";

        //ensure gallery closed
        if (this.isGalleryOpen) {
            this.closeButton.isDisplayed();
            this.closeButton.click();
        }

        if (media.equals("facebook")) {

            String mwh = driver.getWindowHandle();

            this.shareicon.isDisplayed();
            this.shareicon.click();
            this.facebook.isDisplayed();
            this.facebook.click();

            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            for (String popupHandle : driver.getWindowHandles()) {
                if (!popupHandle.contains(mwh)) {
                    driver.switchTo().window(popupHandle);
                }
            }

            modelDialogTitle = driver.getTitle();

            driver.close();

            //Back to main windows
            driver.switchTo().window(mwh);
        }
        return  modelDialogTitle;
    }

    public boolean setVideoFullScreen() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", this.videoPlayer);

        Actions actions = new Actions(driver);//Hovering on main menu
        actions.moveToElement(this.videoPlayer);
        this.videoPlayer.click();
        WaitUntilElementVisible(this.fullScreenButtons);
        this.fullScreenButtons.click();

        return true;
    }

    public boolean setVideoSmallScreen(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", this.videoPlayer);

        Actions actions = new Actions(driver);//Hovering on main menu
        actions.moveToElement(this.videoPlayer);
        this.videoPlayer.click();
        WaitUntilElementVisible(this.fullScreenButtons);
        this.fullScreenButtons.click();

        return true;
    }

    public List<LeagueTableRow> getpremierLeagueTable(){
        WaitUntilElementVisible(this.premierLeagueTable);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", this.premierLeagueTable);

        List<LeagueTableRow> list = new ArrayList<LeagueTableRow>();
        List<WebElement> trs = this.premierLeagueTable.findElements(By.xpath("//tbody/tr"));
        Iterator<WebElement> itr = trs.iterator();
        while(itr.hasNext())
        {
            WebElement tr = itr.next();
            String pos = tr.findElement(By.xpath("td[1]")).getText();
            String team = tr.findElement(By.xpath("td[2]")).getText();
            String point = tr.findElement(By.xpath("td[3]")).getText();
           list.add(new LeagueTableRow(pos, team, point));
        }

        return  list;
    }
}