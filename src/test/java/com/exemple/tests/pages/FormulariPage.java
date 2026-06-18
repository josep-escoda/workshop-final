package com.exemple.tests.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormulariPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // 1. SELECTORS NETS AMB @FindBy
    
    // El camp de text (Escriu un text)
    @FindBy(xpath = "//div[contains(@data-params, 'Escriu un text')]//input[@type='text']")
    private WebElement inputText;

    // El botó final d'enviar
    @FindBy(xpath = "//span[contains(text(), 'Envia') or contains(text(), 'Enviar')]")
    private WebElement botoEnviar;

    // El missatge d'èxit de la pantalla final
    @FindBy(css = ".vHW8K")
    private WebElement missatgeExit;

    // Constructor
    public FormulariPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // 2. MÈTODES D'ACCIÓ

    /**
     * Escriu text a la caixa de resposta
     */
    public void escriureText(String text) {
        wait.until(ExpectedConditions.visibilityOf(inputText)).sendKeys(text);
    }

    /**
     * Clica un dels botons rodons de la Pregunta 1
     * @param textRadio Valors possibles del teu formulari: "Opció 1", "Opció 2" o "Opció 3"
     */
    public void seleccionarRadioPregunta1(String textRadio) {
        String xpathRadio = String.format(
            "//div[contains(@data-params, 'Pregunta 1')]//div[@role='radio' and @data-value='%s']", 
            textRadio
        );
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathRadio))).click();
    }

    /**
     * Envia el formulari
     */
    public void enviarFormulari() {
        wait.until(ExpectedConditions.elementToBeClickable(botoEnviar)).click();
    }

    /**
     * Obté el text de confirmació del final
     */
    public String obtenirMissatgeConfirmacio() {
        return wait.until(ExpectedConditions.visibilityOf(missatgeExit)).getText();
    }
}