package org.collections.web.util;

import org.collections.web.page.FrameElement;
import org.openqa.selenium.WebDriver;

public class FrameSwitcher {

    private WebDriver driver;

    public void smth() {
        String s = this.getClass().getMethods()[0].getAnnotation(FrameElement.class).frames();
        String[] selectors = s.split("\\.");
        driver.switchTo().defaultContent();
        for (String selector : selectors) {
            driver.switchTo().frame(selector);
        }
    }
}
