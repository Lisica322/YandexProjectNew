package steps;

import cucumber.api.java.After;
import cucumber.api.java.ru.Допустим;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.BasketPage;
import pages.MainPage;
import pages.ShopPage;
import util.DriverManager;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
    public void search(String seachingItem)  {
        mainPage.getSearchLineInput();
        //while (!seachingItem.equals(mainPage.getSearchLineInput().getAttribute("type")))
        mainPage.fillField(mainPage.getSearchLineInput(), seachingItem);
        mainPage.clickElem(mainPage.getSearchButton());

    }

    @Тогда("Ограничить цену сверху до {int}")
    public void setMaxPrice(int price) throws InterruptedException {

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

    @Допустим("Добавить товаров в корзину")
    public void addTobasket() {

        WebElement product = shopPage.getElementFromGoodsList(0);
        String nameElement = shopPage.getProductName(product);
        String priceElement = shopPage.getProductPrice(product);
        shopPage.buysMap.put(nameElement, shopPage.getProductPriceFromString(priceElement));
        try {
            // shopPage.addProductToBasket(product);
            WebDriver driver = BasePage.getDriver();
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<String> catComplexes = driver.findElements(By.xpath("//*[@class='_2Qo3ODl0by cia-vs']/article//a[@title]"))
                    .stream().map(item -> item.getAttribute("title")).collect(Collectors.toList());

            for (int i = 0; i <= catComplexes.size() - 2; i = i + 2) { //вот тут для нечетных
                WebElement element = driver.findElement(By.xpath("//*[@title='" + catComplexes.get(i) + "']/../../../..")); //находим сам комплекс по тайтлу
                WebElement button = element.findElement(By.xpath("//*[@class=\"_1Jo-W3_R__ cia-vs\"]")); // находим его кнопку и проверяем что она не "единичка"
                if (button.getText().equalsIgnoreCase("В корзину")) {
                    new ShopPage().addProductToBasket(button); // кладем в корзину
                }
            }
        } catch (NoSuchElementException e) {
            Assert.fail("Товар " + nameElement + "\nНельзя добавить в корзину");
        }
    }


    @Допустим("Удалить все товары из корзины")
    public void delAllProductsFromBasker() {
        mainPage.goToBasket();
        if (!basketPage.getBucketStatusBar().equals("В корзине нет товаров")){
            basketPage.delete();
        }
    }
    @After
    public void zakrit() throws InterruptedException {
        Thread.sleep(5000);

        DriverManager.closeDriver();
    }
}