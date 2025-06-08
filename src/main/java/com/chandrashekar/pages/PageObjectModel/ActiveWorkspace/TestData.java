package com.chandrashekar.pages.PageObjectModel.ActiveWorkspace;

import com.chandrashekar.base.BaseClass;
import com.chandrashekar.utils.WaitStatements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class TestData extends BaseClass {
    public TestData(WebDriver driver) {
        super(driver);
    }

    AW_HomePage aw_homePage;


    //Locators
    By txt_ObjectTitleList = By.xpath("//div[@class='sw-row sw-primary-workarea align-self-stretch flex-auto']//span[@celltitleid='CellTitle']");
    By btn_Item = By.xpath("//div[@class='ui-grid-cell' and @aria-colindex='2']");
    By btn_revision = By.xpath("//div[@aria-colindex='3']//div[contains(@title,'001') and @class='aw-splm-tableCellText']");
    By txt_objectTitle = By.xpath("//h1[@data-locator='aw-layout-locationTitle']");
    By tbl_ContentsTable = By.xpath("//div[@class='aw-splm-table aw-jswidgets-grid aw-layout-flexColumn aw-widgets-droppable aw-jswidgets-selectionEnabled']");


    //This will get the object revision from the opened test data folder.
    public WebElement select_ObjectRevision_FromTestDataFolder(String objectName) {
            WaitStatements.checkClickable(driver, 5, txt_ObjectTitleList);
            List<WebElement> list_ObjectTitle = driver.findElements(txt_ObjectTitleList);
            for (WebElement objectTitle : list_ObjectTitle) {
                if (objectTitle.getText().equalsIgnoreCase(objectName)) {
                    objectTitle.click();
                    return objectTitle;
                }
            }

            return null;
        }


    public WebElement getObject(String objectName) {
        By object_Locator = By.xpath("//div[@class='aw-splm-tableContainer']//div[contains(text(),'" + objectName + "')]");
        WaitStatements.checkClickable(driver, 5, object_Locator);
        return driver.findElement(object_Locator);
    }

    //This will select the required element from the content table.
    public WebElement select_ItemsFromContents(String objectName) {
        List<WebElement> list_ObjectTitle = driver.findElements(txt_ObjectTitleList);

        for (WebElement objectTitle : list_ObjectTitle) {
            if (objectTitle.isSelected()) {
                select_ObjectRevision_FromTestDataFolder(objectTitle.getText()).click();
                WebElement selectedObject = getObject(objectName);
                selectedObject.click();
                return selectedObject;
            } else if(driver.findElement(tbl_ContentsTable).isDisplayed()){
                WebElement selectedObject = getObject(objectName);
                selectedObject.click();
                return selectedObject;
            }
        }
        return null;
    }

    //This will select the object and open the Item.
    public void open_Object_Item(String objectName) {
        WebElement item = select_ItemsFromContents(objectName);
        item.click();
        aw_homePage = new AW_HomePage(driver);
        aw_homePage.open();
        WaitStatements.checkVisibility(driver,txt_objectTitle,5);

    }

    public WebElement getRevision(int revisionNumber){
        return driver.findElement(By.xpath("//div[@aria-colindex='3']//div[contains(@title,'"+revisionNumber+"') and @class='aw-splm-tableCellText']"));
    }

    public WebElement select_Revision(String objectName, int revisionNumber){
        getObject(objectName);
        WebElement revisionElement = getRevision(revisionNumber);
        revisionElement.click();
        return revisionElement;
    }

    public void open_Revision(String objectName, int revisionNumber){
        open_Object_Item(objectName);
        select_Revision(objectName,revisionNumber);
        aw_homePage.openObject(select_Revision(objectName));
        WaitStatements.checkVisibility(driver,txt_objectTitle,5);
    }


    //For single revision
    //This will select the revision.
    public WebElement select_Revision(String objectName){
        open_Object_Item(objectName);
        WebElement revisionElement = driver.findElement(btn_revision);
        revisionElement.click();
        return revisionElement;
    }

    public void open_Revision(String objectName){
        open_Object_Item(objectName);
        select_Revision(objectName);
        aw_homePage.openObject(select_Revision(objectName));
        WaitStatements.checkVisibility(driver,txt_objectTitle,5);
    }


    //Multiple revisions
    //This will get the revision element from the Object table.
    private WebElement getRevisionElement(String revisionNumber){
        return driver.findElement(By.xpath("//div[@aria-colindex='3']//div[contains(@title,'"+revisionNumber+"') and @class='aw-splm-tableCellText']"));
    }

    //this will select the required revision if table has the multiple revisions.
    public WebElement select_Revision(String objectName, String revisionNumber){
        open_Object_Item(objectName);
        WebElement revisionElement = getRevisionElement(revisionNumber);
        revisionElement.click();
        return revisionElement;
    }



    //this will open the required revision if table has the multiple revisions.
    public void open_Revision(String objectName, String revisionNumber){
        open_Object_Item(objectName);
        select_Revision(objectName,revisionNumber);
        aw_homePage.openObject(select_Revision(objectName,revisionNumber));
    }

    //validate
    public String objectTitle(){
        return driver.findElement(txt_objectTitle).getText();
    }


}
