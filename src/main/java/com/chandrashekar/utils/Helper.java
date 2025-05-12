package com.chandrashekar.utils;

import com.chandrashekar.base.AW_Base;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Helper extends AW_Base {

    public Helper(WebDriver driver) {
        super(driver);
    }

    private static final int tries = 3;

    public static void getElements(WebDriver driver, String ItemName, By listElements) {

        for (int i = 0; i <= tries; i++) {
            try {
                List<WebElement> elements = driver.findElements(listElements);
                for (WebElement element : elements) {
                    WaitStatements.checkClickable(driver, 5, element);
                    if (element.getText().equalsIgnoreCase(ItemName)) {
                        WaitStatements.checkClickable(driver, 5, element);
                        element.click();
                        break;
                    }
                }
            } catch (StaleElementReferenceException e) {
            }
        }

    }
}