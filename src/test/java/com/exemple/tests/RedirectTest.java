package com.exemple.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.exemple.tests.listeners.TestListener;
import com.exemple.tests.pages.RedirectPage;
import com.exemple.tests.pages.ResultatRedirectPage;

@Listeners(TestListener.class)
public class RedirectTest extends BaseTest {
    private RedirectPage redirectPage;

    @BeforeMethod
    public void navigateToRedirector() {
        driver.get("http://the-internet.herokuapp.com/redirector");
        redirectPage = new RedirectPage(driver);
    }

    @Test
    public void testRedireccioCorrecta() {
        // Cliquem i capturem la pàgina de destí que ens retorna el mètode
        ResultatRedirectPage statusPage;
        statusPage = redirectPage.clickRedirect();
        
        // 1. Validació: Esperem i comprovem que la URL hagi canviat correctament
        Assert.assertTrue(statusPage.isPageLoaded(), "La pàgina de codis d'estat no s'ha carregat.");
        
        // 2. Validació: Verifiquem la URL tant si la resposta és http com si és https
        //    ja que alguns navegadors sempre opten per la navegació segura
        String urlActual = (statusPage.getCurrentUrl()).replaceFirst("^https?://", "");
        String urlEsperada = "the-internet.herokuapp.com/status_codes";
                
        Assert.assertEquals(urlActual, urlEsperada, 
                "La URL després de la redirecció no és la correcta. Trobada: " + urlActual);
    }
}