package com.chandrashekar.pages.PageObjectModel.ActiveWorkspace;

import com.chandrashekar.utils.AW_PopupNotification;
import com.chandrashekar.utils.Helper;
import com.chandrashekar.utils.WaitStatements;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class AW_HomePage extends AW_DashBoardPage {

    public AW_HomePage(WebDriver driver) {
        super(driver);
        AW_HomePage.driver = driver;
    }


    //Locators
    private final By home_MoreCommands_Locator = By.xpath("//button[@button-id='Awp0MoreGroup']");
    private final By moreCommandsOptions_Locator = By.xpath("//div[@anchor='Awp0MoreGroup']//li");
    private final By option_New_Locator = By.xpath("//div[@anchor='Awp0NewGroup']//li");
    private final By itemNameInput_Locator = By.xpath("//div[@class='sw-lov-container aw-widgets-droppable']//input[@class='sw-aria-border sw-property-val']");
    private final By itemNameValues_Locator = By.xpath("//ul[@aria-label='typeListProp']//div[@class='sw-cell-valName']");
    private final By itemNameText_locator = By.xpath("//*[@name='object_name']");
    private final By itemId_locator = By.xpath("//*[@name='item_id']");
    private final By addButton_Locator = By.xpath("//button[@class='sw-button ']");
    private static final By openHome_Locator = By.xpath("//button[@button-id='Awp0OpenGroup']");
    private static final By openHomeList_Locator = By.xpath("//div[@anchor='Awp0OpenGroup']//div[@class='aw-popup-cellContentContainer']");
    private static final By objectOpenButton_locator = By.xpath("//button[@class='aw-commands-commandIconButton aw-commands-command aw-commandId-Awp0ShowObjectCellForObjectNavigation']");


    //moreCommands_Home method will click the 'more commands' and get the options under that.
    public void moreCommands_Home(String optionName) throws InterruptedException {
        WaitStatements.checkClickable(driver, 2, home_MoreCommands_Locator);
        WebElement moreCommands = driver.findElement(home_MoreCommands_Locator);
        moreCommands.click();
        List<WebElement> moreCommandsOptions = driver.findElements(moreCommandsOptions_Locator);
        for (WebElement moreCommandsOption : moreCommandsOptions) {
            if (moreCommandsOption.getText().equalsIgnoreCase(optionName)) {
                moreCommandsOption.click();
                break;
            }
        }
    }

    //This is the 'New' option under 'More Commands'.
    private void option_New_MoreCommands(String optionName) throws InterruptedException {
        moreCommands_Home("new");
        List<WebElement> new_Options = driver.findElements(option_New_Locator);
        for (WebElement new_Option : new_Options) {
            if (new_Option.getText().equalsIgnoreCase(optionName)) {
                new_Option.click();
                break;
            }
        }
    }

    /*
    //Not yet used
        //This is the 'Edit' option under 'More Commands'.
        private void option_Edit_MoreCommands(String optionName) {
            moreCommands_Home("edit");
        }

        //This is the 'Manage' option under 'More Commands'.
        private void option_Manage_MoreCommands(String optionName) {
            moreCommands_Home("Manage");
        }

        //This is the 'Share' option under 'More Commands'.
        private void option_Share_MoreCommands(String optionName) {
            moreCommands_Home("Share");
        }

        //This is the 'View' option under 'More Commands'.
        private void option_view_MoreCommands(String optionName) {
            moreCommands_Home("view");
        }

        //This is the 'Import/Export' option under 'More Commands'.
        private void option_ImportOrExport_MoreCommands(String optionName) {
            moreCommands_Home("Import/Export");
        }
    */

    //************* Options from the 'New' ***************
//This is the 'add' option from the 'New'
    public void create_NewFolder(String FolderName) throws InterruptedException {
        option_New_MoreCommands("add");
        WebElement itemNameInput = driver.findElement(itemNameInput_Locator);
        itemNameInput.sendKeys("Folder");
        List<WebElement> itemNameValues = driver.findElements(itemNameValues_Locator);

        for (WebElement itemNameValue : itemNameValues) {
            if (itemNameValue.getText().equalsIgnoreCase("Folder")) {
                itemNameValue.click();
                break;
            }
        }
        WaitStatements.checkClickable(driver, 5, itemNameText_locator);
        WebElement itemNameText = driver.findElement(itemNameText_locator);
        itemNameText.sendKeys(FolderName);
        driver.findElement(addButton_Locator).click();
        System.out.println(AW_PopupNotification.popupMessage(driver));
        open("open");
        WaitStatements.waitJVM(3000);
    }

    public void create_Item(String ItemName) {

        WebElement itemNameInput = driver.findElement(itemNameInput_Locator);

        itemNameInput.clear();
        itemNameInput.sendKeys(ItemName);
        Helper.getElements(driver, ItemName, itemNameValues_Locator);

        WebElement itemNameText = driver.findElement(itemNameText_locator);
        WaitStatements.refreshElement(itemNameText);
        itemNameText.clear();
        itemNameText.sendKeys(ItemName);
        driver.findElement(addButton_Locator).click();
        System.out.println(AW_PopupNotification.popupMessage(driver));
    }

    public void create_NewItem(String ItemName) throws InterruptedException {
        option_New_MoreCommands("add");
        try {
            create_Item(ItemName);
        } catch (StaleElementReferenceException e) {
            create_Item(ItemName);
        }
    }

/*
    //options under add option from the New.
    public void paletteOption_add_New(String ItemName) {
    }

    public void searchOption_add_New(String ItemName) {
    }

    //This is the 'Create Change' option from the 'New'
    public void createChange_New(String ItemName) {
        option_New("Create Change");
        System.out.println("** Code Not implemented **");
    }

    //This is the 'Create Specification' option from the 'New'
    public void createSpecification_New(String ItemName) {
        option_New("Create Specification");
        System.out.println("** Code Not implemented **");
    }

    //This is the 'Create Trace link' option from the 'New'
    public void createTraceLink_New(String ItemName) {
        option_New("Create Trace Link");
        System.out.println("** Code Not implemented **");
    }

    //This is the 'generate TraceLink Matrix' option from the 'New'
    public void generateTraceLinkMatrix_New(String ItemName) {
        option_New("generate Trace Link Matrix");
        System.out.println("** Code Not implemented **");
    }

    //This is the 'Generate Report' option from the 'New'
    public void generateReport_New(String ItemName) {
        option_New("Generate Report");
        System.out.println("** Code Not implemented **");
    }
//****************** end 'New' options*****************
*/

    //This method is used to open the object in same/different window/Tab.
    public static void open(String open) throws InterruptedException {
        driver.findElement(openHome_Locator).click();
        WaitStatements.stale(driver, openHomeList_Locator, 2);
        List<WebElement> openList = driver.findElements(openHomeList_Locator);
        try {
            for (WebElement openListValue : openList) {

                if (openListValue.getText().equalsIgnoreCase(open)) {
                    openListValue.click();
                    break;
                } else {
                    System.out.println("Unable to open");
                }
            }
        } catch (Exception e) {
            System.out.println("unable to open object" + e.getMessage());
        }
    }

    //this is used to open any object
    public static void openObject(WebElement element) {
        Actions action = new Actions(driver);
        try {
            WebElement openButton = driver.findElement(objectOpenButton_locator);
            action.moveToElement(element).click(openButton).build().perform();
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }

    public static void openObject() {
        try {
            WebElement openButton = driver.findElement(objectOpenButton_locator);
            openButton.click();
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }

/*
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://teamcenter.ad005.onehc.net:10231/");
        AW_HomePage aw_homePage = new AW_HomePage(driver);
        AW_DashBoardPage aw_dashBoardPage = new AW_DashBoardPage(driver);
        aw_dashBoardPage.changeGroupAndRole("Systems_Engineering.ERL.CV.Healthcare", "Design_Input_Engineer");
        aw_homePage.homePage();
        aw_homePage.create_NewFolder("Test1");
        aw_homePage.create_NewItem("user need specification");
        AW_ItemOpenPage awItemOpenPage = new AW_ItemOpenPage(driver);
        openObject();
        awItemOpenPage.child_add_TopControlMenu("User Need");

    }

    public static void openFolder() throws InterruptedException {
        WebElement openFolder = driver.findElement(By.xpath("//div[@class='aw-jswidgets-tableNonEditContainer aw-layout-flexRowContainer']//*[contains(text(),'87832')]"));
        WaitStatements.checkClickable(driver, 20, openFolder);
        openFolder.click();

        open("open");
    }

 */
}

