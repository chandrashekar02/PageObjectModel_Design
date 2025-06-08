package com.chandrashekar.pages.PageObjectModel.Ploarion.Polarion_Testdata;

import com.chandrashekar.pages.PageObjectModel.Ploarion.Polarion_Testdata.Utils.Polarion_PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class Polarion_SidebarPage extends Polarion_LoginPage {

    //This page will handle the document sidebar, approval process,
// & changing the status of the document.
    public Polarion_SidebarPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='polarion-dle-toolbar-ButtonWithMenu-Steady polarion-Button-shared polarion-dle-toolbar-GroupItem-first polarion-dle-toolbar-GroupItem polarion-dle-toolbar-GroupItem-last']")
    private WebElement sidebar;
    @FindBy(xpath = "//td[contains(@title,'Reviews/Approvals')]")
    WebElement review_Approve_sidebar;

    @FindBy(xpath = "//td[contains(@title,'Signatures')]")
    private WebElement signature;
    @FindBy(xpath = "//td[contains(@title,'Document Properties')]")
    private WebElement documentProperty_sidebar;
    @FindBy(xpath = "//td[@id='FIELD_status']//table[@class='polarion-JSSelector-Combo']")
    private WebElement documentStatusField;
    @FindBy(xpath = "//td[contains(text(),'Perform action Start Review')]")
    private WebElement performActionStartReview;
    @FindBy(xpath = "//td[contains(text(),'Perform action Incorporate Findings')]")
    private WebElement performActionIncorporateFindings;
    @FindBy(xpath = "//td[contains(text(),'Perform action Start Approval')]")
    private WebElement performActionStartApproval;
    @FindBy(xpath = "//td[@id='FIELD_sh_documentReviewer']//table[@class='polarion-JSSelector-Combo']")
    private WebElement reviewerField;
    private final By reviewerList_Locator = By.xpath("//table[@class='polarion-JComboBox-ItemsTable']//td[2]");
    private final By addReviewer_Locator = By.xpath("//td[@id='FIELD_sh_documentReviewer']//img[@class='polarion-MultiSelector-Icon']");
    private final By approverList_Locator = By.xpath("//table[@class='polarion-JComboBox-ItemsTable']//td[2]");
    private final By confidentiality_Locator = By.xpath("//td[@id='FIELD_sh_confidentiality']//table[@class='polarion-JSSelector-Combo']");
    private final By documentPropertiesHead_Locator = By.xpath("//div[@class='polarion-SidebarPanel-header-label']");
    @FindBy(xpath = "//td[@id='FIELD_sh_documentApprover']//table[@class='polarion-JSSelector-Combo']")
    private WebElement approverField;
    @FindBy(xpath = "//input[@class='polarion-JComboBox-SearchBox']")
    private static WebElement roleInputField;
    private final By addApprover_Locator = By.xpath("//td[@id='FIELD_sh_documentApprover']//img[@class='polarion-MultiSelector-Icon']");
    private final By organizationField_Locator = By.xpath("//td[@id='FIELD_sh_documentOrganization']//table[@class='polarion-JSSelector-Combo']");
    private static final By documentTypeField_Locator = By.xpath("//td[@id='FIELD_sh_documentTypeName']//table[@class='polarion-JSSelector-Combo']");
    private final By documentTypeList_Locator = By.xpath("//table[@class='polarion-JComboBox-ItemsTable']//tr//td[2]");
    private final By legalEntity_Locator = By.xpath("//td[@id='FIELD_sh_legalEntity']//input[@class='polarion-JComboBox-Input']");
    @FindBy(xpath = "//*[@id='polarion-SignatureSidebar-controlButton-sign']")
    private WebElement sign_Button;
    private static final By saveButton =By.id("gwt-debug-toolbar-save");
    @FindBy(xpath = "//td[contains(@title,'Work Item Properties')]")
    private WebElement workItemProperties;
    @FindBy(xpath = "//td[@title='Show All Comments Sidebar']")
    private WebElement allComments;
    @FindBy(xpath = "//div[@class='polarion-dle-SignaturesEntryPoint-contextButton polarion-Button-shared']")
    private WebElement startApprovalButton;
    @FindBy(id = "polarion-SignatureSidebar-controlButton-sign")
    private WebElement sidebar_signButton;
    @FindBy(id = "gwt-debug-polarion-SignatureDialog-password")
    private WebElement eSignPassword;
    @FindBy(xpath = "//*[text()='Sign']/parent::div")
    private WebElement eSignButton;


    //Sidebar options
    public void show_sidebar() {
        wait.until(ExpectedConditions.elementToBeClickable(sidebar)).click();
    }

    public void clickShow_sidebar(WebElement option) {
        show_sidebar();
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    public void reviewAndApproval_sidebar() {
        clickShow_sidebar(review_Approve_sidebar);
    }

    public void signature_Sidebar() {
        clickShow_sidebar(signature);
    }

    public void documentProperty_Sidebar() {
        clickShow_sidebar(documentProperty_sidebar);
    }

    public void workItemProperty_Sidebar() {
        clickShow_sidebar(workItemProperties);
    }

    public void allComments_Sidebar() {
        clickShow_sidebar(allComments);
    }

    //Document Approval process
    public void approval_Process() {
        signature_Sidebar();
        wait.until(ExpectedConditions.elementToBeClickable(startApprovalButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(sidebar_signButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    //Electronic sign
    public void electronicSignature() {
        wait.until(ExpectedConditions.elementToBeClickable(eSignPassword)).sendKeys(getPasswordInput());
        eSignButton.click();

    }

    public void performActionStartApproval() {
        try {
            documentProperty_Sidebar();
            wait.until(ExpectedConditions.elementToBeClickable(documentStatusField)).click();
            wait.until(ExpectedConditions.elementToBeClickable(performActionStartApproval)).click();
            wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
            System.out.println("Document status is changed to 'performActionStartApproval'");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void performActionStartReview() {
        DocumentPage documentPage = new DocumentPage(driver);
        try {
            //documentProperty_Sidebar();
            documentPage.fillDocumentProperties_Sidebar();
            wait.until(ExpectedConditions.elementToBeClickable(documentStatusField)).click();
            wait.until(ExpectedConditions.elementToBeClickable(performActionStartReview)).click();
            wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
            System.out.println("Document status is changed to 'performActionStartReview'");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void performActionStartReview(String secondReviewer, String secondApprover) {
        DocumentPage documentPage = new DocumentPage(driver);
        try {
            //documentProperty_Sidebar();
            documentPage.fillDocumentProperties_Sidebar(secondReviewer, secondApprover);
            wait.until(ExpectedConditions.elementToBeClickable(documentStatusField)).click();
            wait.until(ExpectedConditions.elementToBeClickable(performActionStartReview)).click();
            wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
            System.out.println("Document status is changed to 'performActionStartReview'");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void performActionIncorporateFindings() {
        DocumentPage documentPage = new DocumentPage(driver);
        try {
            //documentProperty_Sidebar();
            documentPage.fillDocumentProperties_Sidebar();
            wait.until(ExpectedConditions.elementToBeClickable(documentStatusField)).click();
            wait.until(ExpectedConditions.elementToBeClickable(performActionIncorporateFindings)).click();
            wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
            System.out.println("Document status is changed to 'Perform action Incorporate Findings'");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String convertNames(String name) {
        String pName = null;
        try {
            pName = Polarion_PropertiesReader.readKey(name);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Input.." + e.getClass());
        }
        return pName;
    }

    public void reviewer() {
        String reviewerName = convertNames(getReviewerName());
        reviewerField.click();
        wait.until(ExpectedConditions.elementToBeClickable(roleInputField));
        roleInputField.sendKeys(reviewerName);
        List<WebElement> reviewerList = driver.findElements(reviewerList_Locator);
        for (WebElement reviewer : reviewerList) {
            if (reviewer.getText().equalsIgnoreCase(reviewerName)) {
                reviewer.click();
                break;
            }
        }
    }

    public void reviewer(String secondReviewerName) {
        reviewer();
        String reviewerName = convertNames(secondReviewerName);
        driver.findElement(addReviewer_Locator).click();
        driver.findElement(By.xpath("//td[@id='LABEL_sh_documentReviewer']")).click();
        reviewerField.click();
        wait.until(ExpectedConditions.elementToBeClickable(roleInputField));
        roleInputField.sendKeys(reviewerName);
        List<WebElement> reviewerList = driver.findElements(reviewerList_Locator);
        for (WebElement reviewer : reviewerList) {
            if (reviewer.getText().equalsIgnoreCase(reviewerName)) {
                reviewer.click();
                break;
            }
        }
    }

    public void approver() {
        try {
            String approverName = Polarion_PropertiesReader.readKey(getApproverName());
            approverField.click();
            wait.until(ExpectedConditions.elementToBeClickable(roleInputField));
            roleInputField.sendKeys(approverName);
            List<WebElement> approverList = driver.findElements(approverList_Locator);
            for (WebElement approver : approverList) {
                if (approver.getText().equalsIgnoreCase(approverName)) {
                    approver.click();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Approver not found..\t"+e.getMessage());
        }
    }

    public void approver(String secondApproverName) {
        approver();
        try {
            String approverName = convertNames(secondApproverName);
            driver.findElement(addApprover_Locator).click();
            driver.findElement(By.xpath("//td[@id='LABEL_sh_documentReviewer']")).click();
            approverField.click();
            wait.until(ExpectedConditions.elementToBeClickable(roleInputField));
            roleInputField.sendKeys(approverName);
            List<WebElement> approverList = driver.findElements(approverList_Locator);
            for (WebElement approver : approverList) {
                if (approver.getText().equalsIgnoreCase(approverName)) {
                    approver.click();
                    break;
                }
            }
        } catch (Exception e) {
        System.out.println("Approver not found..\t"+e.getMessage());
    }
    }

    public void confidentiality(){
        driver.findElement(confidentiality_Locator).click();
        roleInputField.sendKeys("Restricted");
    }
    public void organizationField() {
        driver.findElement(organizationField_Locator).click();
        try {
            wait.until(ExpectedConditions.elementToBeClickable(roleInputField)).sendKeys("ITL");
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }

    public void documentTypeField(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(documentTypeField_Locator))).click();
        wait.until(ExpectedConditions.elementToBeClickable(roleInputField)).sendKeys(name);
        driver.findElement(documentPropertiesHead_Locator).click();
    }

    public void legalEntity() {
        driver.findElement(legalEntity_Locator).click();
        wait.until(ExpectedConditions.elementToBeClickable(roleInputField)).sendKeys("Siemens Healthineers AG");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//table[@class='polarion-JComboBox-ItemsTable']//tr[1]")))).click();
    }

    public static void save(){
        driver.findElement(saveButton).click();
    }

}

