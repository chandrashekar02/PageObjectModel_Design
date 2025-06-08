package com.chandrashekar.utils;

import com.chandrashekar.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class Helper extends BaseClass {

    public Helper(WebDriver driver) {
        super(driver);
    }

    private static final int tries = 3;

    public static WebElement getElements(WebDriver driver, String ItemName, By listElements) {

        for (int i = 0; i <= tries; i++) {
            try {
                List<WebElement> elements = driver.findElements(listElements);
                for (WebElement element : elements) {
                    WaitStatements.checkClickable(driver, 5, element);
                    if (element.getText().equalsIgnoreCase(ItemName)) {
                        WaitStatements.checkClickable(driver, 5, element);
                        return element;
                    }
                }
            } catch (StaleElementReferenceException ignored) {
            }
        }
        return null;
    }

    public static void dragAndDrop(WebDriver driver, WebElement source, WebElement target){
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source,target).build().perform();
    }

}