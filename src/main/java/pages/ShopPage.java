package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopPage extends BasePage {
    public Map<String, Integer> buysMap = new HashMap<>();

    @FindBy(xpath = "//*[@class='_2Qo3ODl0by cia-vs']")
    private List<WebElement> allGoods;
    @FindBy(xpath = "//*[@class='_2yK7W3SWQ- _1f2usTwyAs'][1]")
    private WebElement maxPrice;
    @FindBy(xpath = "//*[@class='_2yK7W3SWQ- _1d02bPcWht'][1]")
    private WebElement minPrice;
    @FindBy(xpath = "//div[@class='active-filters']/descendant::span[contains(text(), 'Цена')]")
    private WebElement filterPrice;
    @FindBy(xpath = "//span[@class='show'][1]")
    private WebElement brandsBar;
    @FindBy(xpath = "//div[@class='input-wrap search-input m-low-height']/input")
    private WebElement inputBrand;
    @FindBy(xpath = "//*[@class='_2PD3NkrVA6']")
    private WebElement yandexShop;
    @FindBy(xpath = "//*[@class='_3FCaH0jGWO _1yIm0AlO3K']")
    private WebElement closeWindow;
    @FindBy(xpath = "//div[@class ='_1UPuXOJfD4']/*")
    private List<WebElement> addToBucketList;

    public WebElement getCloseWindow() {
        return closeWindow;
    }

    public WebElement getMaxPrice() {
        return maxPrice;
    }

    public WebElement getYandexShop() {
        return yandexShop;
    }

    public void addProductToBasket(WebElement product) {

        //js.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", addToBucketList);
        clickElem(addToBucketList.get(1));
        clickElem(addToBucketList.get(3));
        //clickElem(closeWindow);
        this.getWaiter().until(ExpectedConditions.textToBePresentInElement(product.findElement(By.xpath("//*[@class='_1sjUgidnzS _1DpwW9o1wj']")), "1 шт"));
    }

    public int getAllGoods() {
        int xpathCount = driver.findElements(By.xpath("//*[@data-zone-name='snippet-cell']")).size();
        //  List<WebElement> shopList = getAllGoods().findElements(By.xpath("//*[@class='_1O1OnAPlSR _29bSn5MwO8 E0C6OmNNOg _3nAkRBQgmd cia-vs cia-cs']"));
        getElementFromGoodsList(xpathCount);
        return xpathCount;
    }

    public WebElement getElementFromGoodsList(int xpathCount) {
        return allGoods.get(xpathCount);
    }

    public List<WebElement> getAddToBucketList() {
        return addToBucketList;
    }

    public String getProductPrice(WebElement product) {
        WebElement price = product.findElement(By.xpath("//*[@data-zone-name='price']"));
        return price.getText();
    }

    public String getProductName(WebElement product) {
        WebElement name = product.findElement(By.xpath("//*[@data-zone-name='title']"));
        return name.getText();
    }

    public WebElement getMinPrice() {
        return minPrice;
    }
}
