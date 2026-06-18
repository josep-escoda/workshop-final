package com.exemple.tests.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultatRedirectPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ResultatRedirectPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Retorna la URL actual per poder verificar-la al test
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // Verifica que un text específic de la pàgina estigui present (per assegurar que ha carregat)
    public boolean isPageLoaded() {
        return wait.until(ExpectedConditions.urlContains("/status_codes"));
    }
}