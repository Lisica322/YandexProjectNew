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
    @FindBy(xpath = "//*[@data-autotest-id='cpa']")
    private WebElement yandexShop;
    @FindBy(xpath = "//*[@class='_3FCaH0jGWO _1yIm0AlO3K']")
    private WebElement closeWindow;
    @FindBy(xpath = "//div[@class ='_1UPuXOJfD4']/*")
    private List<WebElement> addToBucketList;


    public WebElement getMaxPrice() {
        return maxPrice;
    }

    public WebElement getYandexShop() {
        return yandexShop;
    }

    public void addProductToBasket(WebElement product)  {
        clickElem(addToBucketList.get(1));
        closePopUpIfExists(); //закрываем всплывашку
        clickElem(addToBucketList.get(3));
        closePopUpIfExists();
    }

    private void closePopUpIfExists() {
        if (isPopUpWindowExists()){
            clickElem(driver.findElement(By.xpath("//div[@class ='_3FCaH0jGWO _1yIm0AlO3K']")));
        }
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
    public boolean isPopUpWindowExists(){
        try{
            Thread.sleep(5*1000);
            driver.findElement(By.xpath("//*[@class=\"_2yjtqZ8Clc D-q4L_WjW8\"]"));
            return true;
        }catch (Exception e){
            System.out.println("Всплывашка не обнаружена");
        }
        return false;
    }
}
