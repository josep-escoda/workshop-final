package com.exemple.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage  {
    public void waitPageToLoad(WebDriver driver, long secondsTimeout, WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(secondsTimeout));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }
}