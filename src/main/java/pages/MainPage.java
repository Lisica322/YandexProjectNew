package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainPage extends BasePage {
    @FindBy(xpath = "//*[@id='header-search']")
    private WebElement searchLineInput;
    @FindBy(xpath = "//*[@type='submit']")
    private WebElement searchButton;
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

    public void vkladka(){
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }

    public WebElement getSearchLineInput() {
        return searchLineInput;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }
}
