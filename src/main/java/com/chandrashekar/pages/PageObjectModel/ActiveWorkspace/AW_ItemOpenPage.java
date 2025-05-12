package com.chandrashekar.pages.PageObjectModel.ActiveWorkspace;

import com.chandrashekar.utils.Helper;
import com.chandrashekar.utils.WaitStatements;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class AW_ItemOpenPage extends AW_HomePage {

    public AW_ItemOpenPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    String open;
    public String childItemName;

    //Locators
    By add_TopControlMenu_Locator = By.xpath("//button[@button-id='Awb0AddGroup']");
    By add_ListOptions_Locator = By.xpath("//div[@anchor='Awb0AddGroup']//div[@class='aw-popup-cellContentContainer']");
    By childList_Locator = By.xpath("//*[@aria-level='2']//*[@class='aw-splm-tableTreeCommandCell aw-jswidgets-tablecell']//*[@class='aw-splm-tableCellText']");
    By subChildList_Locator = By.xpath("//*[@aria-level='3']//*[@class='aw-splm-tableTreeCommandCell aw-jswidgets-tablecell']//*[@class='aw-splm-tableCellText']");
    By Type_DropdownText_New = By.xpath("//div[@class='sw-row sw-sectionTitleContainer']//div[contains(text(),'Type')]");
    //Actions

    public void add_TopControlMenu(String AddOptions) {
        WebElement add = driver.findElement(add_TopControlMenu_Locator);
        add.click();
        WaitStatements.refreshElement(add_ListOptions_Locator);
        Helper.getElements(driver, AddOptions, add_ListOptions_Locator);
    }

    public void child_add_TopControlMenu(String childObjectName) throws InterruptedException {
        WebElement documentation = driver.findElement(By.xpath("//button[@button-id='Awp0DisplayToggles']"));
        WaitStatements.fluentWait(documentation);
        WaitStatements.checkVisibility(driver, By.xpath("//button[@button-id='Awp0DisplayToggles']"), 60);
        add_TopControlMenu("child");
        create_Item(childObjectName);
    }
}
