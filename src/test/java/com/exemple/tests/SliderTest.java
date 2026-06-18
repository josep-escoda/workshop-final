package com.exemple.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.exemple.tests.listeners.TestListener;
import com.exemple.tests.pages.SliderPage;

@Listeners(TestListener.class)
public class SliderTest extends BaseTest {
    private SliderPage sliderPage;

    @BeforeMethod
    public void navigateToSliderPage() {
        driver.get("http://the-internet.herokuapp.com/horizontal_slider");
        sliderPage = new SliderPage(driver);
    }

    @Test
    public void testMoureSlider() {
        String valorObjectiu = System.getProperty("valor", "4.5");
        //String valorObjectiu = "4.5";
        
        // Executem l'acció a la Page Object
        sliderPage.moveSliderToValue(valorObjectiu);
        
        // Obtenim el resultat real de la pàgina
        String valorActual = sliderPage.getSliderValue();
        
        // Verifiquem que realment s'ha situat a 4.5
        Assert.assertEquals(valorActual, valorObjectiu, 
                "El slider no s'ha situat al valor correcte.");
    }
}