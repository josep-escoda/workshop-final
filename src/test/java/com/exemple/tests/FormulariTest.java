package com.exemple.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.exemple.tests.listeners.TestListener;
import com.exemple.tests.pages.FormulariPage;

@Listeners(TestListener.class)
public class FormulariTest extends BaseTest {
    private FormulariPage formulariPage;

    @BeforeMethod
    public void navegarAlFormulari() {
        // Fem servir la teva nova URL sense desplegable
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSeId1ae54ryzMVZfnyWZpyq5FCv5xJE-2SqUXLgVjp1Ub6klg/viewform?usp=sharing&ouid=115114521982253508842");
        formulariPage = new FormulariPage(driver);
    }

    @Test
    public void testOmplirProvaSeleniumComplet() {
        // 1. Definim dades de prova netes
        String textAInserir = "Projecte de QA finalitzat amb èxit per en Josep.";
        String radioASeleccionar = "Opció 2"; // Pots canviar a "Opció 1" o "Opció 3"

        // 2. Execució d'accions basades en el patró POM
        formulariPage.escriureText(textAInserir);
        formulariPage.seleccionarRadioPregunta1(radioASeleccionar);
        
        // 3. Enviem les respostes
        formulariPage.enviarFormulari();

        // 4. Validació del resultat final (Assert)
        String missatgeReal = formulariPage.obtenirMissatgeConfirmacio();
        Assert.assertTrue(missatgeReal.toLowerCase().contains("registrat") || 
                          missatgeReal.toLowerCase().contains("resposta"), 
                "L'enviament del formulari simplificat ha fallat.");
    }
}