package org.web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FrameObject {

    private WebDriver driver;


    public void switchTo() {
        WebElement contentFrame = driver.findElement(By.id("iframeResult"));
        driver.switchTo().frame(contentFrame);

        WebElement innerFrame = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(innerFrame);
    }

    @FrameElement(frames = "iframeResult.frame1")
    public void clickSomethingInFrame1() {

    }

    @FrameElement(frames = "iframeResult.frame2")
    public void clickSomethingInFrame2() {

    }
}
