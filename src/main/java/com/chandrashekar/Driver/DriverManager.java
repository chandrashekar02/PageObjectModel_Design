package com.chandrashekar.Driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    public static WebDriver driver;
    public DriverManager(WebDriver driver){
        DriverManager.driver=driver;
    }


    public static WebDriver getDriver() {
        return driver;
    }
    public static void setDriver(WebDriver driver) {
        DriverManager.driver = driver;
    }

   //start the browser
    public static void init(){

    }

    //close the browser
    public static void down(){}



}
