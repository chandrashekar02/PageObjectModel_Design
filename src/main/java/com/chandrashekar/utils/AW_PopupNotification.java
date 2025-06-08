package com.chandrashekar.utils;

import com.chandrashekar.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AW_PopupNotification {
    WebDriver driver;
    public AW_PopupNotification(WebDriver driver) {
        this.driver=driver;
    }

    //Locator
    private static final By popupText_Locator = By.xpath("//span[@class='noty_text']");


    //Actions
    public static String popupMessage(WebDriver driver) {
        WaitStatements.checkVisibility(driver, popupText_Locator, 5);
        String message = driver.findElement(popupText_Locator).getText().trim();
        return message;
    }
}
