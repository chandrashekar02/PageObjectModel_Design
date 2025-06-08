package com.chandrashekar.pages.PageObjectModel.Ploarion.Polarion_Testdata.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class WaitStatements {

    public static WebDriver driver;
    static WebDriverWait wait;

    public WaitStatements(WebDriver driver) {
        WaitStatements.driver = driver;
    }


    public static void waitJVM(int time) throws InterruptedException {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }


    public static void pageLoadWait(WebDriver driver, int time) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(time));
    }


    public static void implicitWait(WebDriver driver, int time) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
    }

    public static void fluentWait(WebElement element) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(Exception.class);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void fluentWait(WebDriver driver, By locator) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(Exception.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void stalenessWaitElement(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    public static void stale(WebDriver driver, By element, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
    }


    public static void checkVisibility(WebDriver driver, WebElement element, int time) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOf(element));

    }


    public static void checkVisibility(WebDriver driver, By located, int time) {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(time));
            wait.until(ExpectedConditions.visibilityOfElementLocated(located));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void checkPresence(WebDriver driver, int time, By located) {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(time));
            wait.until(ExpectedConditions.presenceOfElementLocated(located));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void checkClickable(WebDriver driver, int time, By located) {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(time));
            wait.until(ExpectedConditions.elementToBeClickable(located));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void checkPresence(WebDriver driver, int time, WebElement element) {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(time));
            wait.until(ExpectedConditions.elementToBeSelected(element));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void checkClickable(WebDriver driver, int time, WebElement element) {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(time));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void refreshElement(WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
    }

    public static void refreshElement(By Locator) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(Locator)));
    }

    public static void js(WebDriver driver, int time) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(WebDriver -> Objects.equals(js.executeScript("return document.readyState"), "complete"));
    }

    public static WebElement waitElementToBeSelected(WebDriver driver, WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeSelected(element));
        return element;
    }

}
