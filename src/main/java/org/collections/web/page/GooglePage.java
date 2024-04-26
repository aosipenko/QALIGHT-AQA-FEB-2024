package org.collections.web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GooglePage extends AbstractPage {

    public final static String GOOGLE_URL = "https://google.com/";
    private static final String COOKIES_FORM_XPATH =
            "//a[contains(@href,'https://policies.google.com/technologies/cookies')]/../../../..//button";
    private static final By cookieButtonsLocator = By.xpath(COOKIES_FORM_XPATH);

    public GooglePage(WebDriver driver) {
        super(driver, GOOGLE_URL);
    }

    public void acceptCookiesIfPresent() {
        List<WebElement> elements = driver.findElements(cookieButtonsLocator);
        if (!elements.isEmpty()) {
            elements.get(3).click();
        }
    }

    public void setSearchText(String value) {
        WebElement searchInput = driver.findElement(By.name("q"));
        searchInput.sendKeys(value);
    }

    public void performSearch() {
        WebElement searchInput = driver.findElement(By.name("q"));
        searchInput.sendKeys(Keys.ENTER);
    }

    public void feelingLucky() {
        List<WebElement> luckyButtons = driver.findElements(By.name("btnI"));
        for (WebElement e : luckyButtons) {
            if (e.isDisplayed()) {
                e.click();
                break;
            }
        }
    }

    public List<WebElement> getSearchHeaders() {
        return new WebDriverWait(driver, Duration.ofSeconds(5L))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//a/h3"), 2));
    }

    public void actions() {
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.ARROW_UP).pause(Duration.ofSeconds(5)).keyUp(Keys.ARROW_UP)
                .build().perform();
    }
}
