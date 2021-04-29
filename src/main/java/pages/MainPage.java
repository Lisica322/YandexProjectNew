package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainPage extends BasePage {
    @FindBy(xpath = "//*[@id='header-search']")
    private WebElement searchLineInput;
    @FindBy(xpath = "//*[@type='submit']")
    private WebElement searchButton;
    @FindBy(xpath = "//*[contains(text(),'Корзина')]")
    private WebElement goToBasket;
    //Autoriz
    @FindBy(xpath = "//*[contains(text(), 'Войти')]")
    private WebElement logInForm;
    @FindBy(xpath = "//*[@name='login']")
    private WebElement input;
    @FindBy(xpath = "//*[@type='submit']")
    private WebElement clickButtonNext;
    @FindBy(xpath = "//*[@id='passp-field-passwd']")
    private WebElement passwordInput;
    @FindBy(xpath = "//*[@id ='rmdLtr']")
    private WebElement clickButtonLatter;

  public void logIn(String login, String password) {
  //      driver.navigate().refresh();

        clickElem(logInForm);
       ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        fillField(input, login);
        clickElem(clickButtonNext);
        fillField(passwordInput, password);
        clickElem(clickButtonNext);
        driver.switchTo().window(tabs.get(0));
    }

  /*  public void vkladka(){
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }*/

    public void goToBasket(){
        /*wait.until(ExpectedConditions.elementToBeClickable(goToBasket));*/
        clickElem(goToBasket);
    }

    public WebElement getSearchLineInput() {
        driver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.MILLISECONDS);
        return searchLineInput;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }
}
