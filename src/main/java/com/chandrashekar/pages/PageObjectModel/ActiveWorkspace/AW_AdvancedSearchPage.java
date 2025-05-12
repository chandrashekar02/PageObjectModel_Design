package com.chandrashekar.pages.PageObjectModel.ActiveWorkspace;

import com.chandrashekar.utils.WaitStatements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class AW_AdvancedSearchPage extends AW_DashBoardPage {

    public AW_AdvancedSearchPage(WebDriver driver) {
        super(driver);
        // this.driver = driver;
    }


    private final By advanceQuery_Locator = By.xpath("//input[@aria-label='Advanced Query Name']");
    private final By advanceQueryList_locator = By.xpath("//ul[@aria-label='awp0AdvancedQueryName']//div[@class='sw-cell-valName']");
    private final By searchButton_Locator = By.xpath("//button[@class='sw-button ']");
    private final By folderList_Locator = By.xpath("//ul[@class='aw-widgets-cellListWidget sw-column flex-shrink ']//span[@class='aw-widgets-cellListCellTitle']");
    private final By folderDateModifiedList_Locator = By.xpath("//label[text()='Date Modified:']");
    private final By openIcon = By.xpath("//button[@button-id='Awp0ShowObjectCell']");
    private final By advanceSearch_MoreCommands_Locator = By.xpath("//button[@class='aw-commands-commandToolbarIconButton ']");
    private final By advanceSearchTile_locator = By.xpath("//div[@data-locator='tile-container-Awp0AdvancedSearchTile']");

    /*
    From the 'Search' input 'Advanced Search' is opened.
    'advanceQuery' will be clicked and take the input to search value from the list.
    If value matches with the required option then it will click on the option.

     */

    private void advancedSearch(String searchValue) {
        // aw_homePage.searchAW();
        // WebElement advanceQuery = driver.findElement(advanceQuery_Locator);
        WebElement advanceQuery = driver.findElement(advanceSearchTile_locator);
        advanceQuery.click();
        advanceQuery.sendKeys(searchValue);

        List<WebElement> advanceQueryList = driver.findElements(advanceQueryList_locator);
        for (WebElement adanceQueryValue : advanceQueryList) {
            try {
                if (adanceQueryValue.getText().equalsIgnoreCase(searchValue)) {
                    adanceQueryValue.click();
                }
            } catch (Exception e) {
                System.out.println(e.getClass());
            }
        }
    }

    /*
    Here FindFolder option is selected from 'advanceSearch' method.
    Takes the Testcase number as an input and click the search button.
     */
    public void findFolder(int testcaseID) {
        advancedSearch("SH FindFolder");
        driver.findElement(By.xpath("//textarea[@name='Name']"))
                .sendKeys(testcaseID + "*");
        driver.findElement(searchButton_Locator).click();
    }

    public void selectingFolder(int testcaseID) {
        findFolder(testcaseID);
        try {
            WaitStatements.checkClickable(driver, 5, openIcon);
            driver.findElement(with(openIcon)).click();
            System.out.println("Test data folder is opened");
        } catch (Exception e) {
            System.out.println("Folder not found");
        }
        //Need to change this to check the date which is the latest one.
        /*
        List<WebElement> folderList = driver.findElements(folderList_Locator);

        List<WebElement> folderDateModifiedList = driver.findElements(folderDateModifiedList_Locator);
        for(WebElement dateModified : folderDateModifiedList){
            String date = dateModified.getText();
            if(date  dateModified.getText())

         */
    }
}


