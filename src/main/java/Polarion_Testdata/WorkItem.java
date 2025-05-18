package Polarion_Testdata;

import Polarion_Testdata.Utils.WaitStatements;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;
import java.util.List;
import java.util.Scanner;

public class WorkItem extends DocumentPage{

    public WorkItem(){
        super(driver);
    }


    private final By requirementsHeading_Locator = By.xpath("//h2[text()='Requirements']");
    private final By mandatoryParagraph_Locator = By.xpath("//h2[contains(text(),'Requirements')]");
    private final By workItemArrowButton_Locator = By.xpath("//div[@title='Mark/Unmark Text as a Requirement']//td[@class='polarion-MenuButton-ArrowCell']");
    private final By workItemTypeList_Locator = By.xpath("//div[@class='polarion-MenuButton-Menu polarion-MenuButton-Menu-vertical polarion-MenuButton-MenuWithIcon']//td//span");
    private final By documentIframe_Locator = By.xpath("//iframe[@class='polarion-dle-RichTextArea']");
    private final By workItemName_Locator = By.xpath("//span[@class='polarion-dle-workitem-title']");

//this will handle single work item
    private void workItem(String workItemType, String workItemName){
        location_Requirement();
        document_WorkItem(workItemType, workItemName);
    }
//this will handle multiple work items
    private void workItem(String workItemType, int numberOfWorkItems, String workItemName){
        for (int i=1; i<=numberOfWorkItems; i++ ) {
            location_Requirement();
            document_WorkItem(workItemType,numberOfWorkItems, workItemName);
        }
    }


    public void createWorkItems(String workItemType, String workItemName){
        Scanner sc = new Scanner(System.in);
        System.out.println("Required Work Items? : if 'Yes' press 'Y' else 'N'");
        String workItemRequired = sc.next();
        if(workItemRequired.equalsIgnoreCase("Y")){
            System.out.println("How many work items?");
            int numberOfWorkItems = sc.nextInt();
            if(numberOfWorkItems==1){
                workItem(workItemType, workItemName);
            }else if(numberOfWorkItems>1){
                workItem(workItemType, numberOfWorkItems, workItemName);
            }
        }
    }

    public void switchFrameToDocumentBody(){
        driver.switchTo().frame(driver.findElement(documentIframe_Locator));
    }

    private void location_Requirement(){
        switchFrameToDocumentBody();
        WebElement requirement = driver.findElement(requirementsHeading_Locator);
        requirement.click();
        requirement.sendKeys(Keys.END);
        requirement.sendKeys(Keys.ENTER);
    }

    public void document_WorkItem(String workItemType, String workItemName){
        this.workItemType=workItemType;
        try {
            Thread.sleep(2000);
            driver.switchTo().parentFrame();
            WaitStatements.checkClickable(driver,5,workItemArrowButton_Locator);
            driver.findElement(workItemArrowButton_Locator).click();
            List<WebElement> workItems = driver.findElements(workItemTypeList_Locator);
            for(WebElement workItem : workItems){
                if(workItem.getText().
                        equalsIgnoreCase(workItemType)){
                    workItem.click();
                    break;
                }
            }
            workItemNameAndDescription(workItemName);
        } catch (Exception e) {
            System.out.println("Unable to create work item..."+e.getMessage());
        }
    }

    public void document_WorkItem(String workItemType, int numberOfWorkItems, String workItemName){
        this.workItemType=workItemType;
        try {
            Thread.sleep(2000);
            driver.switchTo().parentFrame();
            WaitStatements.checkClickable(driver,5,workItemArrowButton_Locator);
            driver.findElement(workItemArrowButton_Locator).click();
            List<WebElement> workItems = driver.findElements(workItemTypeList_Locator);
            for(WebElement workItem : workItems){
                if(workItem.getText().
                        equalsIgnoreCase(workItemType)){
                    workItem.click();
                    break;
                }
            }
            workItemNameAndDescription(workItemName, numberOfWorkItems);
        } catch (Exception e) {
            System.out.println("Unable to create work item..."+e.getMessage());
        }
    }



    public void workItemConfigure(String workItemName){
        document_WorkItem("Configure...", workItemName);
    }

    public void insertReferencedWorkItem(String workItemName){
        document_WorkItem("Insert Referenced Work Items", workItemName);
    }


    public void workItemNameAndDescription(String workItemName){
        switchFrameToDocumentBody();
        WaitStatements.checkClickable(driver,2,driver.findElement(workItemName_Locator));
        WebElement workItemTitle = driver.findElement(workItemName_Locator);
        workItemTitle.click();
        workItemTitle.sendKeys(Keys.END);
        workItemTitle.sendKeys(workItemName);
        workItemTitle.sendKeys(Keys.ENTER);
        workItemTitle.sendKeys(workItemName);
    }

    public void workItemNameAndDescription(String workItemName, int numberOfWorkItems){
        switchFrameToDocumentBody();
        for(int j=1; j<=numberOfWorkItems; j++) {
            By workItemName_Locator = By.xpath("//span[@class='polarion-dle-workitem-title']//span[@title="+"'"+numberOfWorkItems+"'"+"]");

            WaitStatements.checkClickable(driver, 2, driver.findElement(workItemName_Locator));
            WebElement workItemTitle = driver.findElement(workItemName_Locator);
            workItemTitle.click();
            workItemTitle.sendKeys(Keys.END);
            workItemTitle.sendKeys(workItemName + "_" + j);
            workItemTitle.sendKeys(Keys.ENTER);
            workItemTitle.sendKeys(workItemName + "_" + j);
            workItemTitle.sendKeys(Keys.ENTER);
        }
    }

}
