package webTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;

public class BasePage {
    protected static WebDriver driver;
    final Properties properties = new Properties();

    public BasePage() {
        BasePage.driver = driver;
        try {
            properties.load(new FileInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("pageObjects/page.properties")).getPath()));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean isElementPresent(By locatorKey) {
        try {
            driver.findElement(locatorKey);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean waitElementToBeClickable(String selector) {
        boolean clickable=false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(selector)));
            clickable = true;
        }
        catch (TimeoutException e) {
            e.printStackTrace();
        }
        return clickable;
    }
}
