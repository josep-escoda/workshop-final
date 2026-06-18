// PROVES AMB FindBy i Wait WebElement

package com.exemple.tests.pages;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SliderPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // 1. Definim els WebElements utilitzant l'anotació @FindBy
    @FindBy(css = ".sliderContainer input")
    private WebElement sliderInput;

    @FindBy(id = "range")
    private WebElement sliderValueResult;

    // Constructor
    public SliderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        
        // línia IMPRESCINDIBLE per inicialitzar els @FindBy:
        PageFactory.initElements(driver, this);
    }

    /**
     * Obté el valor numèric actual que mostra la pàgina al costat del slider.
     */
    public String getSliderValue() {
        // Ara passem directament el WebElement al 'wait'
        return wait.until(ExpectedConditions.visibilityOf(sliderValueResult)).getText();
    }

    /**
     * Mou el slider utilitzant les tecles de direcció fins a assolir el valor objectiu.
     */
    public void moveSliderToValue(String targetValue) {
        // Cliquem una vegada per donar el focus al component
        wait.until(ExpectedConditions.elementToBeClickable(sliderInput)).click();
        
        int maxPasos = 15; 
        int pasActual = 0;
        
        while (!getSliderValue().equals(targetValue) && pasActual < maxPasos) {
            
            double valorActualNum = Double.parseDouble(getSliderValue());
            double valorObjectiuNum = Double.parseDouble(targetValue);
            
            // Decidim la direcció
            if (valorActualNum < valorObjectiuNum) {
                sliderInput.sendKeys(Keys.ARROW_RIGHT);
            } else {
                sliderInput.sendKeys(Keys.ARROW_LEFT);
            }
            
            // LA CLAU: Esperem de forma explícita usant el WebElement del @FindBy.
            // Com que busquem que el text de la pantalla hagi canviat i ja NO sigui el d'abans,
            // calculem quin serà el següent valor teòric per posar-lo a l'espera dinàmica.
            double seguentValorTeoric = (valorActualNum < valorObjectiuNum) ? valorActualNum + 0.5 : valorActualNum - 0.5;
            
            // Això fa que si és 2.5 mostri "2.5", però si és 2.0 mostri només "2"
            java.text.DecimalFormatSymbols symbols = new java.text.DecimalFormatSymbols(java.util.Locale.US);
            java.text.DecimalFormat df = new java.text.DecimalFormat("#.#", symbols);
            String textEsperat = df.format(seguentValorTeoric);
            
            // Espera dinàmica basada exclusivament en l'objecte de PageFactory
            wait.until(ExpectedConditions.textToBePresentInElement(sliderValueResult, textEsperat));
            
            pasActual++;
        }
    }
}