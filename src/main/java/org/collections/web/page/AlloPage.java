package org.collections.web.page;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.Command;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v122.network.Network;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;

import java.time.Duration;

@Slf4j
public class AlloPage extends AbstractPage {

    private final static String ALLO_UA_URL = "https://allo.ua/";

    private final static By PROFILE_BTN =
            By.xpath("//*[@class='mh-profile']/button");

    public AlloPage(WebDriver driver) {
        super(driver, ALLO_UA_URL);
    }

    public void hoverOverElement() {
        WebElement btn = driver.findElement(PROFILE_BTN);
        Actions actions = new Actions(driver);
        actions.moveToElement(btn)
                .pause(Duration.ofSeconds(10))
                .build()
                .perform();

        System.out.println("done");
    }

    public void chromeStuff() {
        WebDriver devToolDriver = new Augmenter().augment(driver);
        DevTools devTools = ((HasDevTools) devToolDriver).getDevTools();

        devTools.createSessionIfThereIsNotOne();
        devTools.send(new Command<>("Network.enable", ImmutableMap.of()));

        devTools.addListener(Network.responseReceived(), listener -> {
            if (listener.getResponse().getUrl().contains("allo.ua")) {
                log.info("Response URL: {}", listener.getResponse().getUrl());
            }
        });

        devTools.addListener(Network.requestWillBeSent(), listener -> {
            if (!listener.getRequest().getUrl().contains("allo.ua")) {
                log.info("Request URL: {}", listener.getRequest().getUrl());
            }
        });
    }
}
