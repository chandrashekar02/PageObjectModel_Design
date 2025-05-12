package com.chandrashekar.pages.PageObjectModel.ActiveWorkspace;

import com.chandrashekar.base.AW_Base;
import com.chandrashekar.utils.WaitStatements;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class AW_DashBoardPage extends AW_Base {

    public AW_DashBoardPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //class variables
    private String currentGroupText;
    private WebElement groupElement;
    private String currentRoleText;
    private WebElement roleElement;


    //Getters
    public String getCurrentGroupText() {
        return currentGroupText;
    }

    public String getCurrentRoleText() {
        return currentRoleText;
    }

    //Locators
    private final By homeTitle = (By.xpath("//h1[@class='aw-layout-locationTitle']"));
    private final By profile = (By.xpath("//button[@class='sw-avatar-generic sw-avatar-xxsmall']"));
    private final By groupText_Locator = By.xpath("//span[contains(text(),'Group')]");
    private final By roleText_Locator = By.xpath("//span[contains(text(),'Role')]");
    private final By group_Role_List = By.xpath("//ul[@role='listbox']//span");
    private final By search_Locator = By.xpath("//input[@class='aw-uiwidgets-searchBox']");
    private final By advancedSearch_Locator = By.linkText("Advanced Search");
    private final By sidebarHome_Locator = By.xpath("//button[@button-id='Awp0GoHome']");
    private final By sidebarInbox_Locator = By.xpath("//button[@button-id='Awp0GoInbox']");
    private static final By sidebarExplorer_Locator = By.xpath("//button[@button-id='Awp0ShowRootFolders']");
    private final By sidebarReports_Locator = By.xpath("//button[@button-id='Awp0GoReports']");
    private final By sidebarBack_Locator = By.xpath("//div[@anchor='aw_globalNavigationbar']//button[@command-id='Awp0GoBack']");
    private final By newPartTile_Locator = By.xpath("//div[@title='New Part']");

    //Actions
    public void profile() {                  //home page profile button
        WaitStatements.checkPresence(driver, 60, homeTitle);
        WaitStatements.checkClickable(driver, 10, profile);
        driver.findElement(profile).click();                                                                        //to open profile
    }

    private void currentGroup() {                  //it will get the current group name
        profile();
        WaitStatements.checkVisibility(driver, groupText_Locator, 5);
        WebElement groupText = driver.findElement(groupText_Locator);
        groupElement = driver.findElement(with(By.tagName("a")).below(groupText));
        currentGroupText = groupElement.getText();
    }

    public void changeGroupAndRole(String expectedGroup, String expectedRole) throws InterruptedException {                  //need to pass the expected group name
        currentGroup();

        if (!currentGroupText.equals(expectedGroup)) {                                           //if the current group is not same as expected
            groupElement.click();
            List<WebElement> groupList = driver.findElements(group_Role_List);
            for (WebElement groupName : groupList)                                              //it will check one by one group name
            {
                try {
                    if (groupName.getText().equals(expectedGroup)) {                            //if the group name matches then it will click
                        currentGroupText = groupName.getText();
                        groupName.click();
                        break;
                    }
                } catch (Exception e) {
                    System.out.println(e.getClass());
                }
            }
            Thread.sleep(1000);
        }
        roleChange(expectedRole);
    }

    private void currentRole() {
        profile();
        WaitStatements.checkVisibility(driver, roleText_Locator, 5);
        WebElement roleText = driver.findElement(roleText_Locator);
        roleElement = driver.findElement(with(By.tagName("a")).below(roleText));
        currentRoleText = roleElement.getText();
    }

    private void roleChange(String expectedRole) throws InterruptedException {                  //need to pass the expected group name
        currentRole();

        if (!currentRoleText.equals(expectedRole)) {                                                           //if the current group is not same as expected
            roleElement.click();
            List<WebElement> roleList = driver.findElements(group_Role_List);
            for (WebElement roleName : roleList)                      //it will check one by one group name
            {
                try {
                    if (roleName.getText().equals(expectedRole)) {                    //if the group name matches then it will click
                        currentRoleText = roleName.getText();
                        roleName.click();
                        WaitStatements.pageLoadWait(driver, 5);
                        break;
                    }
                } catch (Exception e) {
                    System.out.println(e.getClass());
                }
            }
        } else {
            driver.findElement(By.xpath("//*[@class='sw-logoImage']")).click();
        }
        System.out.println("Login Group is : " + currentGroupText + "\tas a : " + currentRoleText + " role");
        Thread.sleep(1000);
    }


    public void search_DashBoardPage() {
        try {
            driver.findElement(search_Locator).click();
            driver.findElement(advancedSearch_Locator).click();
        } catch (StaleElementReferenceException e) {
            search_DashBoardPage();
        }
    }

    public void homePage() {
        driver.findElement(newPartTile_Locator).click();
        WebElement close = driver.findElement(By.xpath("//button[@command-id='Awp0CloseCommandPanel']"));
        close.click();
    }








/*
//For trying purpose only
    WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        AW_AdvancedSearchPage obj = new AW_AdvancedSearchPage(driver);

        obj.groupChange("SCM.FOR.CT.Healthcare", "Engineer");

        obj.searchAW();

        obj.selectingFolder(97014);
    }
*/

}
