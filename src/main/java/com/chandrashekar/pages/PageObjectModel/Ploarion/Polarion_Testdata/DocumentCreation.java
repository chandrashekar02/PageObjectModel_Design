package com.chandrashekar.pages.PageObjectModel.Ploarion.Polarion_Testdata;

import com.chandrashekar.utils.WaitStatements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Set;

public class DocumentCreation extends Polarion_SidebarPage {
    public DocumentCreation(WebDriver driver) {
        super(driver);
    }

    private int i;

    //Locators
    private final By liveDoc_Locator = By.xpath("//*[@class='polarion-JTreeNode-LabelBig']//td[contains(text(),'LiveDoc Templates')]");
    private final By home_Locator = By.xpath("//td[@class='polarion-JTreeNode-LabelTdBig']//td[contains(text(),'Home')]");
    private final By reportAndTools_Locator = By.xpath("//td[@class='polarion-JTreeNode-LabelTdBig']//td[contains(text(),'Reports & Tools')]");
    private final By reportAndToolsDropDown_Locator = By.xpath("//tr[@title='Reports & Tools']//td[@class='polarion-JTreeNode-OpenIconTd']");
    private final By documentAndPages_Locator = By.xpath("//td[@class='polarion-JTreeNode-LabelTdBig']//td[contains(text(),'Documents & Pages')]");
    private final By documentAndPagesDropDown_Locator = By.xpath("//tr[@title='Documents & Pages']//td[@class='polarion-JTreeNode-OpenIconTdBack']");
    private final By workItem_Locator = By.xpath("//td[@class='polarion-JTreeNode-LabelTdBig']//td[contains(text(),'Work Items')]");
    private final By workItemDropDown_Locator = By.xpath("//tr[@title='Work Items']//td[@class='polarion-JTreeNode-OpenIconTdBack']");
    private final By collections_Locator = By.xpath("//td[@class='polarion-JTreeNode-LabelTdBig']//td[contains(text(),'Collections')]");
    private final By filterTitle_locator = By.xpath("//div[@class='dataTables_scrollHeadInner']//input[@placeholder='Filter Title']");
    private final By docTitleNameList_Locator = By.xpath("//div[@class='dataTables_scrollBody']/table[@class='avasis-dataTable hover order-column cell-border compact dataTable no-footer']//tr//td[3]//a");
    private final By reuseDocument_Locator = By.xpath("//div[@class='dataTables_scrollBody']/table[@class='avasis-dataTable hover order-column cell-border compact dataTable no-footer']//tr//td[5]");
    private final By documentTitle_Locator = By.xpath("//input[@name='targetTitle']");
    private final By targetSpace_Locator = By.id("targetSpace");
    private final By targetSpaceValue_Locator = By.xpath("//option[starts-with(@value,'AnisaSh')]");
    private final By createButton_Locator = By.xpath("//button[text()='Create']");


    //Actions
    public void createDocument(String docType, String docName, String spaceName) {
        WaitStatements.checkVisibility(driver, liveDoc_Locator, 5);
        WebElement liveDoc = driver.findElement(liveDoc_Locator);
        liveDoc.click();
        WaitStatements.checkPresence(driver, 15, filterTitle_locator);
        WebElement filterTitle = driver.findElement(filterTitle_locator);
        filterTitle.sendKeys(docType);

        List<WebElement> documentNames = driver.findElements(docTitleNameList_Locator);
        for (WebElement documentName : documentNames) {
            if (documentName.getText().equalsIgnoreCase(docType)) {
                driver.findElement(reuseDocument_Locator).click();
                break;
            } else {
                System.out.println("The Document is not found. Please try with correct name...");
            }
        }
        targetSpace(docName, spaceName);
    }

    public void targetSpace(String docName, String spaceName) {
        WebElement docTitle = driver.findElement(documentTitle_Locator);
        docTitle.clear();
        docTitle.sendKeys(docName);
        WebElement targetSpaceList = driver.findElement(targetSpace_Locator);
        Select sc = new Select(targetSpaceList);
        targetSpaceList.click();
        List<WebElement> options = sc.getOptions();

        for (WebElement option : options) {
            String optionName = option.getText().toLowerCase().trim();
            String a = spaceName.toLowerCase();
            if (optionName.contains(a)) {
                sc.selectByContainsVisibleText(option.getText());
                driver.findElement(createButton_Locator).click();
                break;
            }
        }
        switchWindow();
    }

    public static void switchWindow() {
        String parentWindow = driver.getWindowHandle();   //Parent window
        Set<String> windows = driver.getWindowHandles();  //List of all windows
        for (String window : windows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                System.out.println(driver.getCurrentUrl());
                System.out.println(driver.getTitle());
                break;
            }
        }
    }
}
