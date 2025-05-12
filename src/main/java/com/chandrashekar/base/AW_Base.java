package com.chandrashekar.base;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class AW_Base {
    public static WebDriver driver;

    //Constructor
    public AW_Base(WebDriver driver) {
        AW_Base.driver = driver;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
}
