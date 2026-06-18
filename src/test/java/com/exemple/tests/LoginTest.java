package com.exemple.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.exemple.tests.listeners.TestListener;
import com.exemple.tests.pages.LoginPage;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void navigateToLogin() {
        driver.get("http://the-internet.herokuapp.com/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLogin() {
        // Capturem les variables de Maven. 
        String usuari = System.getProperty("user", "tomsmith");
        String contrasenya = System.getProperty("password", "SuperSecretPassword!");
        
        // Executem el login amb les credencials rebudes
        loginPage.login(usuari, contrasenya);
        
        String message = loginPage.getFlashMessageText();
        
        // Assegurem que l'inici de sessió hagi estat correcte
        Assert.assertTrue(message.contains("You logged into a secure area!"), 
                "L'inici de sessió ha fallat. Missatge rebut: " + message);
    }
}