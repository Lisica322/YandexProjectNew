package util;

import cucumber.api.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverManager {
    private static WebDriver driver;
    private static String driverPath;
    private static final Properties PROPERTIES = new Properties();

    private static void initDriver() {

        try {
            PROPERTIES.load(new FileInputStream(new File("src/main/resources/webdriver.properties")));
        } catch (IOException e) {
            System.out.println("Не найден файл с properties");
        }
        driverPath = PROPERTIES.getProperty("webdriver.chrome.driver"); //
        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static WebDriver getDriver(String url) {
        if (driver == null)
            initDriver();
        driver.get(url);
        return driver;
    }
    public static WebDriver getDriver(){
        if(driver == null) {
            initDriver();
            driver.get(PROPERTIES.getProperty("url"));
        }
        return driver;
    }
    public String getDriverPath() {
        return driverPath;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
