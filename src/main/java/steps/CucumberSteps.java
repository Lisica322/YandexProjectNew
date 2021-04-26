package steps;

import cucumber.api.java.After;
import cucumber.api.java.ru.Допустим;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasketPage;
import pages.MainPage;
import pages.ShopPage;
import util.DriverManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CucumberSteps {
    private BasketPage basketPage = new BasketPage();
    private MainPage mainPage = new MainPage();
    private ShopPage shopPage = new ShopPage();


    @Допустим("Перейти на сайт {string}")
    public void goToUrl(String url) {
        DriverManager.getDriver(url);
    }

    @Тогда("Выполнить авторизацию {string} {string}")
    public void logInStep(String login, String password) {
        mainPage.logIn(login, password);
    }

    @Когда("Выполнить поиск по {string}")
    public void search(String seachingItem) {

        //while (!seachingItem.equals(mainPage.getSearchLineInput().getAttribute("type")))
        mainPage.fillField(mainPage.getSearchLineInput(), seachingItem);
        mainPage.clickElem(mainPage.getSearchButton());
        mainPage.vkladka();
    }

    @Тогда("Ограничить цену сверху до {int}")
    public void setMaxPrice(int price) {
        WebElement autofill = shopPage.getMaxPrice();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < autofill.getAttribute("value").length(); i++) {
            builder.append("\b");
        }

        shopPage.getMaxPrice().sendKeys(builder.toString() + price + "\n");
    }

    @Тогда("Ограничить цену снизу до {int}")
    public void setMinPrice(int price) {
        WebElement autofill = shopPage.getMinPrice();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < autofill.getAttribute("value").length(); i++) {
            builder.append("\b");
        }

        shopPage.getMinPrice().sendKeys(builder.toString() + price + "\n");
        shopPage.clickElem(shopPage.getYandexShop());
    }

    @Допустим("Добавить {int} товаров в корзину. Условие - {string}")
    public void addTobasket(int count, String rule) {
        int i, step;
        if (rule.equals("нечетные")) {
            i = 0;
            step = 2;
            count *= 2;
        } else if (rule.equals("четные")) {
            i = 1;
            step = 2;
            count *= 2;
            count++;
        } else {
            i = 1;
            step = 1;
        }
        for (; i < count; i += step) {

            WebElement product = shopPage.getElementFromGoodsList(i);
            String nameElement = shopPage.getProductName(product);
            String priceElement = shopPage.getProductPrice(product);
            shopPage.buysMap.put(nameElement, shopPage.getProductPriceFromString(priceElement));
            try {
                shopPage.addProductToBasket(product);
            } catch (NoSuchElementException e) {
                Assert.fail("В магазин " + nameElement + "\nНельзя добавить в корзину");
            }
        }
    }

    @After
    public void zakrit() throws InterruptedException {
        Thread.sleep(10000);
        DriverManager.closeDriver();
    }
}