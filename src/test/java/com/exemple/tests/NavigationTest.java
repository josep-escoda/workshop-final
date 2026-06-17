package com.exemple.tests;

import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {
    @Test //Opciones de Navegación
    public void testNavigation() throws Exception {
        driver.get("https://www.duckduckgo.com/"); //Navegar hasta duckduckgo
        Thread.sleep(2000);
        driver.navigate().to("https://www.google.com"); //Navegar hasta Google
        Thread.sleep(2000);
        driver.get("https://www.yahoo.com"); //Navegar hasta Yahoo
        Thread.sleep(2000);
        driver.navigate().back(); //Hacia atrás (Volver a Google)
        Thread.sleep(2000);
        driver.navigate().forward(); //Hacia adelante (Volver a Yahoo)
        Thread.sleep(2000);
        driver.navigate().refresh(); //Refrescar Yahoo
        Thread.sleep(2000);
    }
}