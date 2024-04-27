package org.web.cucumber;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.web.selectors.PageSelector;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
//import java.util.logging.Level;
//import java.util.logging.Logger;

@Slf4j
public class WebSteps {


    public static WebDriver driver;

    //    private final static Logger LOGGER = Logger.getLogger("MyLogger");
// -Dorg.slf4j.simpleLogger.defaultLogLevel=debug
    @SneakyThrows
    @Given("I read from data table as List:")
    public void readList(DataTable dataTable) {
        List<String> strings = dataTable.asList();
        log.info("reading list of strings: {}", strings);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(strings.get(0), "a", "element [0] error!");
        softAssert.assertEquals(strings.get(1), "b", "element [1] error!");
        softAssert.assertEquals(strings.get(2), "c", "element [2] error!");
        softAssert.assertEquals(strings.get(3), "d", "element [3] error!");
        softAssert.assertEquals(strings.get(4), "e", "element [4] error!");
        softAssert.assertAll();
    }

    @Given("I read from data table as Map:")
    public void readMap(DataTable dataTable) {
        dataTable.asMap().entrySet().forEach(e -> {
            System.out.println(e.getKey() + " : " + e.getValue());
        });
    }

    @Given("I load {string}")
    public void loadUrl(String url) {
        driver.get(url);
    }

    @Given("I click button with selector {} with index {int}")
    public void findElementsAndClickByIndex(PageSelector pageSelector, int index) {
        List<WebElement> elements = driver.findElements(pageSelector.getSelector());
        if (!elements.isEmpty()) {
            elements.get(index).click();
        }
    }

    @Given("I set text for element {} to {string}")
    public void setTextElement(PageSelector pageSelector, String textValue) {
        driver.findElement(pageSelector.getSelector()).click();
        driver.findElement(pageSelector.getSelector()).sendKeys(textValue);
    }

    @Given("I send key {} to {} element")
    public void clickOnElement(Keys keys, PageSelector pageSelector) {
        driver.findElement(pageSelector.getSelector()).sendKeys(keys);
    }

    @Then("I can see at least {int} {} elements containing text {string}")
    public void verifyElementsWithTextCount(int count, PageSelector pageSelector, String text) {
        boolean containsText = new WebDriverWait(driver, Duration.ofSeconds(5L))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(pageSelector.getSelector(), count))
                .stream()
                .anyMatch(webElement -> webElement.getText().toUpperCase().contains(text.toUpperCase()));
        Assert.assertTrue(containsText, "Text not found!");
    }

    @Given("Test iframe")
    public void smth() {
        driver.get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_iframe_height_width");
        WebElement element = driver.findElement(By.id("runbtn"));
        element.sendKeys(Keys.CONTROL + "T");
        Actions a = new Actions(driver);
        a.keyDown(Keys.CONTROL);
        a.keyDown("T");
        a.release();
        a.build().perform();
        System.out.println("a");
    }
}
