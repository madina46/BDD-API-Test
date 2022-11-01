package utility;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseClass {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    public BaseClass(WebDriver driver, WebDriverWait wait) {

        BaseClass.driver = driver;
        BaseClass.wait = wait;
    }

    protected void WaitUntilElementVisible(WebElement element) {

        wait.until(ExpectedConditions.visibilityOf(element));
    }
}