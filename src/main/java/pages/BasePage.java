package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DriverManager;

import java.util.List;


public class BasePage {
    protected WebDriver driver = DriverManager.getDriver();
    protected WebDriverWait wait = new WebDriverWait(driver, 15, 100);
    protected JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

    public WebDriverWait getWaiter() {
        return wait;
    }

    public BasePage() {
        PageFactory.initElements(driver, this);
    }

    public void clickElem(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void fillField(WebElement element, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).clear();
        element.sendKeys(text);
    }

    public Integer getProductPriceFromString(String price) {
        StringBuilder builder = new StringBuilder();
        for (char x : price.toCharArray()) {
            if (Character.isDigit(x)) builder.append(x);
        }
        return Integer.parseInt(builder.toString());
    }
}
