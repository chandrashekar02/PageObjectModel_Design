package com.chandrashekar.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class BaseClass {
    protected WebDriver driver;

    //Constructor
    public BaseClass(WebDriver driver) {
        WebDriverManager.chromedriver().setup();
        this.driver = driver;
        PageFactory.initElements(driver,this);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void setDriver(WebDriver driver){
        this.driver = driver;
    }

    public WebDriver getDriver(){
        return driver;
    }


}
