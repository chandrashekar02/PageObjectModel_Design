package com.chandrashekar.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StaleElementsHandle {

    public void clickSafe(WebDriver driver, By locator) {
        int tries = 0;
        while (tries < 3) {
            try {
                driver.findElement(locator);
                return;
            } catch (Exception e) {
                tries++;
            }
        }
    }
}