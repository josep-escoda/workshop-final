package com.exemple.tests;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    public WebDriver driver;
    public static JavascriptExecutor jse;

    @BeforeClass(alwaysRun = true) //Inicialización del navegador
    public void setUp() throws Exception {

        String browser = System.getProperty("browser", "chrome");

        if (browser != null && browser.equalsIgnoreCase("firefox")) {
            // SI APAREIX ERROR DE PROFILE --> TMPDIR=$HOME mvn clean test -Dtest=RedirectTest -Dbrowser=firefox
            FirefoxOptions options = new FirefoxOptions();
            // Evita l'ús de la memòria compartida que a vegades també xoca amb Snap
            options.addArguments("--disable-dev-shm-usage");

            driver = new FirefoxDriver(options);

        } else if (browser != null && browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser != null && browser.equalsIgnoreCase("chromeheadless")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new"); // Recomanat en versions recents
            options.addArguments("--no-sandbox"); // Necessari en molts entorns Linux
            options.addArguments("--disable-dev-shm-usage"); // SOLUCIONA EL PROBLEMA DE MEMÒRIA  
            driver = new ChromeDriver(options);
        } else {
            // Selenium 4.6.0+ descobreix automàticament el driver sense configurar res
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox"); // Necessari en molts entorns Linux
            options.addArguments("--disable-dev-shm-usage"); // SOLUCIONA EL PROBLEMA DE MEMÒRIA  
            driver = new ChromeDriver(options);
        }
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        jse = (JavascriptExecutor) driver;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    @AfterClass(alwaysRun = true) //El cierre del navegador
    public void tearDown() throws Exception {
        driver.quit();
    }
}