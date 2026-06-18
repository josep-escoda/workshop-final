package com.exemple.tests.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RedirectPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Selector de l'enllaç que provoca la redirecció
    private By redirectLink = By.id("redirect");

    public RedirectPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public ResultatRedirectPage clickRedirect() {
        wait.until(ExpectedConditions.elementToBeClickable(redirectLink)).click();
        return new ResultatRedirectPage(driver);
    }
}