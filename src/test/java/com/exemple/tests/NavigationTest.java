package com.exemple.tests;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.exemple.tests.listeners.TestListener;

@Listeners(TestListener.class)
public class NavigationTest extends BaseTest {
    @Test
    public void testNavigation() throws Exception {
        // Inicialitzem el WebDriverWait amb un temps màxim de 5 segons
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // 1. Navegar fins a DuckDuckGo i esperar que la URL el contingui
        driver.get("https://www.duckduckgo.com/");
        wait.until(ExpectedConditions.urlContains("duckduckgo"));

        // 2. Navegar fins a Google i esperar el canvi de URL
        driver.navigate().to("https://www.google.com");
        wait.until(ExpectedConditions.urlContains("google"));

        // 3. Navegar fins a Yahoo i esperar
        driver.get("https://www.yahoo.com");
        wait.until(ExpectedConditions.urlContains("yahoo"));

        // 4. Cap enrere (Tornar a Google)
        driver.navigate().back();
        wait.until(ExpectedConditions.urlContains("google"));

        // 5. Cap endavant (Tornar a Yahoo)
        driver.navigate().forward();
        wait.until(ExpectedConditions.urlContains("yahoo"));

        // 6. Refrescar Yahoo
        // Al fer un refresh, la URL no canvia. Esperem que la pàgina es torni a carregar completament.
        String urlAbansDeRefrescar = driver.getCurrentUrl();
        driver.navigate().refresh();
        wait.until(ExpectedConditions.urlToBe(urlAbansDeRefrescar));
    }
}