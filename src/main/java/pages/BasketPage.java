package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.DriverManager;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BasketPage extends BasePage{
    @FindBy(xpath = "//*[@class='b_3FCaH0jGWO b_1yIm0AlO3K']")
    private  WebElement delete;
    @FindBy(xpath = "//*[@class='b_1c3hhfQqz7 b_36SPc70Ljz']")
    private  WebElement bucketStatusBar;

    public void delete() {
        wait.until(ExpectedConditions.elementToBeClickable(delete));
        clickElem(delete);

    }

    public WebElement getBucketStatusBar() {
        return bucketStatusBar;
    }
}
