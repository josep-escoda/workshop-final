package com.exemple.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.exemple.tests.listeners.TestListener;
import com.exemple.tests.pages.LoginPage;

@Listeners(TestListener.class)
public class LoginTestErroni extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void navigateToLogin() {
        driver.get("http://the-internet.herokuapp.com/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginErroni() {
        // Capturem les variables de Maven. 
        String usuari = System.getProperty("user", "NO USER");
        String contrasenya = System.getProperty("password", "NO PASSWORD");
        
        // Executem el login amb les credencials rebudes
        loginPage.login(usuari, contrasenya);
        
        String message = loginPage.getFlashMessageText();
        
        // Assegurem que l'inici de sessió hagi estat correcte
        Assert.assertTrue(message.contains("Your username is invalid!"), 
                "L'inici de sessió ha fallat. Missatge rebut: " + message);
    }
}