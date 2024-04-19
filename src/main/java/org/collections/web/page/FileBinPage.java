package org.collections.web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class FileBinPage extends AbstractPage {

    private final static String URL = "https://filebin.net/";

    public FileBinPage(WebDriver driver) {
        super(driver, URL);
    }

    public void initFileUpload() {
        WebElement e = driver.findElement(By.id("fileField"));
        new Actions(driver).moveToElement(e).click().build().perform();
    }

    public void uploadFile(String file) {
        driver.findElement(By.xpath("//input[@type='file']"))
                .sendKeys(file);
    }
}
