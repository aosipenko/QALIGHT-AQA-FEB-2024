package org.collections.web.page;

import org.openqa.selenium.WebDriver;

public class W3SchoolsPage extends AbstractPage {

    public final static String WIKI_URL = "https://www.w3schools.com/html/tryit.asp?filename=tryhtml_images_trulli";

    public W3SchoolsPage(WebDriver driver) {
        super(driver, WIKI_URL);
    }

    public void smth() {

    }
}
