package com.exemple.tests.listeners;

import com.exemple.tests.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("El test " + result.getName() + " ha fallat. Capturant pantalla...");

        // 1. Obtenim la instància del driver des del test que ha fallat
        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseTest) currentClass).getDriver();

        if (driver != null) {
            // 2. Convertim el driver a TakesScreenshot
            TakesScreenshot screenshoter = (TakesScreenshot) driver;
            File sourceFile = screenshoter.getScreenshotAs(OutputType.FILE);

            // 3. Creem un nom de fitxer únic usant el nom del test i la data/hora actual
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = result.getName() + "_" + timestamp + ".png";

            // 4. Definim la ruta on es guardarà (carpeta 'screenshots' a l'arrel del projecte)
            File destinationFile = new File(System.getProperty("user.dir") + "/screenshots/" + fileName);

            try {
                // Copiem el fitxer temporal a la ruta definitiva
                FileUtils.copyFile(sourceFile, destinationFile);
                System.out.println("Captura d'evidència guardada a: " + destinationFile.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("No s'ha pogut guardar la captura de pantalla: " + e.getMessage());
            }
        }
    }
}