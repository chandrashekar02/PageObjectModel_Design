package Polarion_Testdata;

import Polarion_Testdata.Utils.RandomString;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.security.Key;

public class DocumentPage extends DocumentCreation {

    public DocumentPage(WebDriver driver) {
        super(driver);
    }
    String workItemType;

    //Locators
    private final By stausText_Locator = By.id("LABEL_status");
    private final By productNameField_Locator = By.xpath("//td[@id='FIELD_sh_productname']//input[@class='polarion-JSTextEditor-Active']");
    private final By productVersionField_Locator = By.xpath("//td[@id='FIELD_sh_productversion']//input[@class='polarion-JSTextEditor-Active']");
    private final By sapDocumentIDField_Locator = By.xpath("//td[@id='FIELD_sh_SAP_docidentifier']//input[@class='polarion-JSTextEditor-Active']");
    private final By copyrightYearField_Locator = By.xpath("//td[@id='FIELD_sh_copyrightyear']//input[@class='polarion-JSAbstractTextEditor-Active']");
    private final By modificationField_Locator = By.xpath("//td[@id='FIELD_sh_changeContent']//textarea[@class='polarion-JSTextAreaEditor-Active']");

    //Actions
    public void fill_TextField() {
        driver.findElement(productNameField_Locator).sendKeys(RandomString.stringGenerator());
        driver.findElement(productVersionField_Locator).sendKeys(RandomString.stringGenerator());
        driver.findElement(sapDocumentIDField_Locator).sendKeys(RandomString.sapID());
        driver.findElement(modificationField_Locator).sendKeys(RandomString.stringGenerator());
        driver.findElement(copyrightYearField_Locator).sendKeys(randomNumber());
    }

    public String randomNumber() {
        int randomNumbers = (int) (Math.random() * 10000);
        String a = String.valueOf(randomNumbers);
        return a;
    }

    public void documentProperties() {
        Polarion_SidebarPage polarionSignOffPage = new Polarion_SidebarPage(driver);
        polarionSignOffPage.documentProperty_Sidebar();
      //  performActionStartReview();
        reviewer();
        approver();
        documentTypeField("Product Requirement Specification");
        fill_TextField();
        confidentiality();
        organizationField();
    }

    public void fillDocumentProperties_Sidebar() {
        documentProperties();
    }

    public void fillDocumentProperties_Sidebar(String reviewer, String approver) {
        documentProperties();
    }


}
