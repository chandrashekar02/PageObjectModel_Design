package com.chandrashekar.pages.PageObjectModel.Ploarion.Polarion_Testdata;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Scanner;

public class Polarion_LoginPage extends Polarian_Base {

    //This page will handle login/logout process.

    //Variables
    private static String usernameInput;
    private static String passwordInput;
    private static String authorName;
    private static String reviewerName;
    private static String approverName;
    private static String docLink;

    public static String getApproverName() {return approverName;}
    public static String getAuthorName() {return authorName;}

    public static String getReviewerName() {return reviewerName;}

    public static String getPasswordInput() {return passwordInput;}

    public static String getUsernameInput() {return usernameInput;}

    //Elements
    @FindBy(id = "j_username")
    private static WebElement username;

    @FindBy(id = "j_password")
    private static WebElement password;

    @FindBy(id = "submitButton")
    private static WebElement loginButton;

    @FindBy(xpath = "//div[@title='Show Settings']")
    private static WebElement show_setting;

    @FindBy(xpath = " //div[text()= 'Logout']")
    private static WebElement logoutButton;

    private static final By homeLogo = By.xpath("//div[@title='Home']");


    public Polarion_LoginPage(WebDriver driver) {
        super(driver);
    }

    //Navigation process to the Documents.
    public static void login(String name) {
        try {
            usernameInput = name;
            wait.until(ExpectedConditions.elementToBeClickable(username));
            username.sendKeys(usernameInput);
            passwordInput = name;
            password.sendKeys(passwordInput);
            loginButton.click();
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }

    public static void logout() {
        try {
            Thread.sleep(5000);
            wait.until(ExpectedConditions.visibilityOf(show_setting)).click();
            wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void myDocument() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the Document link: ");
            docLink = sc.nextLine();
            driver.get(docLink);
            System.out.println("Enter Author name: ");
            authorName = sc.nextLine();
            System.out.println("Enter Reviewer name: ");
            reviewerName = sc.nextLine();
            System.out.println("Enter Approver name: ");
            approverName = sc.nextLine();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void matchURL() {
        if (driver.getCurrentUrl().equals(docLink)) {
            System.out.println("URL is matched");
        } else {
            driver.get(docLink);
        }

    }
}
