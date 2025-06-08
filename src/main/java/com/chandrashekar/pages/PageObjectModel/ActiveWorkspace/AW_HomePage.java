package com.chandrashekar.pages.PageObjectModel.ActiveWorkspace;

import com.chandrashekar.base.BaseClass;
import com.chandrashekar.utils.AW_PopupNotification;
import com.chandrashekar.utils.Helper;
import com.chandrashekar.utils.WaitStatements;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SourceType;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class AW_HomePage extends BaseClass {

    public AW_HomePage(WebDriver driver) {
        super(driver);
    }


    //Locators
    private final By home_MoreCommands_Locator = By.xpath("//button[@button-id='Awp0MoreGroup']");
    private final By moreCommandsOptions_Locator = By.xpath("//div[@anchor='Awp0MoreGroup']//li");
    private final By option_New_Locator = By.xpath("//div[@anchor='Awp0NewGroup']//li");
    private static final By itemNameInput_Locator = By.xpath("//div[@class='sw-lov-container aw-widgets-droppable']//input[@class='sw-aria-border sw-property-val']");
    private static final By itemNameValues_Locator = By.xpath("//ul[@aria-label='typeListProp']//div[@class='sw-cell-valName']");
    private static final By itemNameText_locator = By.xpath("//*[@name='object_name']");
    private final By itemId_locator = By.xpath("//*[@name='item_id']");
    private static final By addButton_Locator = By.xpath("//button[@class='sw-button ']");
    private static final By openHome_Locator = By.xpath("//button[@button-id='Awp0OpenGroup']");
    private static final By openHomeList_Locator = By.xpath("//div[@anchor='Awp0OpenGroup']//div[@class='aw-popup-cellContentContainer']");
    private final By btn_Open_locator = By.xpath("//button[@class='aw-commands-commandIconButton aw-commands-command aw-commandId-Awp0ShowObjectCell']");
    private final By summaryHeader = By.xpath("//div[@class='sw-summary-headerTitle']");
    private final By btn_Revise = By.xpath("//button[@class='sw-button ']");
    private final By option_Manage_Locator = By.xpath("//div[@anchor='Awp0ManageGroup']//div[@class='aw-popup-cellContentContainer']");
    private final By list_assignedProjects = By.xpath("//ul[@class='aw-widgets-cellListWidget sw-column flex-shrink ']//span[@class='aw-widgets-cellListCellTitle']");
    private final By btn_RemoveProject = By.xpath("//span[@iconid='cmdRemove']");
    private final By txt_Available_project = By.xpath("//div[@title='Available']");
    private final By btn_Add_End_TraceLink = By.xpath("//button[@command-id='Arm0AddForEndItemListCmd']");
    private By txt_AddEnd = By.xpath("//div[@class='aw-panel-caption align-self-stretch']");
    private By btn_Create = By.xpath("//button[@type='button']//div[text()='Create']");
    private By btn_CloseCommandPanel = By.xpath("//button[@data-command-id='Awp0CloseCommandPanel']");
    private By location_parentObject_Tracelink = By.xpath("//div[@class='sw-row aw-requirementstracelink-cell']");
    private final By btn_Add_Start_TraceLink = By.xpath("//button[@command-id='Arm0AddForStartItemListCmd']");
    private By btn_Swap_TraceLink = By.xpath("//button[@command-id='Arm0SwapTracelinkObjectsCmd']");
    private final By inpt_TraceLinkType = By.xpath("//input[@title='Trace Link']");
    private final By option_DefaultType_TraceLink = By.xpath("//ul[@aria-label='traceLinkType']//div[@title='Default Type']");
    private final By dragAndDrop_Location_EndTraceLink = By.xpath("//details[@caption='End']//div[@class='aw-base-scrollPanel requirements-Droppable-Lists']");


    //moreCommands_Home method will click the 'more commands' and get the options under that.
    public void moreCommands_Home(String optionName) {
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


    //*****************************   NEW   **********************

    //This is the 'New' option under 'More Commands' and take input options which are available under 'New' option.
    private void option_New_MoreCommands(String optionName) {
        moreCommands_Home("new");
        List<WebElement> new_Options = driver.findElements(option_New_Locator);
        for (WebElement new_Option : new_Options) {
            if (new_Option.getText().equalsIgnoreCase(optionName)) {
                new_Option.click();
                break;
            }
        }
    }

    public void saveAs() {
        option_New_MoreCommands("Save As");
    }

    public void option_revise_New() {
        option_New_MoreCommands("Revise");
        WaitStatements.checkClickable(driver, 3, btn_Revise);
        driver.findElement(btn_Revise).click();
    }

    public void option_revisionBaseline_New() {
        option_New_MoreCommands("Revision Baseline");
    }


    //This function will create a Tracelink between 2 objects, Typ is "Tracelink" via "drag and drop".
    public void option_createTraceLink_New(String sourceObjectName, String targetObjectName) {
        TestData testData = new TestData(driver);
        //Selection of source item revision

        try {
            testData.select_ObjectRevision_FromTestDataFolder(sourceObjectName);
        } catch (Exception e) {
            System.out.println("Something went wrong..." + e.getMessage());
        }
        option_New_MoreCommands("Create Trace Link");           //it will click on the 'create trace link' from the option.  //this is the 'add' option from the 'End' tracelink
        WaitStatements.checkVisibility(driver, location_parentObject_Tracelink, 5);
        //Selection of target item revision
        try {
            //getting and clicking the target object
            WebElement sourceObject = testData.select_ObjectRevision_FromTestDataFolder(targetObjectName);
            //wait until the target location is clickable.
            WaitStatements.checkClickable(driver, 10, dragAndDrop_Location_EndTraceLink);

            WebElement target = driver.findElement(dragAndDrop_Location_EndTraceLink);
            //Target object is dragged and pasted under target location
            Helper.dragAndDrop(driver, sourceObject, target);
        } catch (NoSuchElementException e) {
            System.out.println("No object found..." + e.fillInStackTrace());
        }
        WaitStatements.checkClickable(driver, 5, btn_Create);
        driver.findElement(btn_Create).click();

    }

    //"Add" option from the "END" (child) tracelink window.
    private void addOption_End_TraceLink() {
        WaitStatements.checkClickable(driver, 5, btn_Add_End_TraceLink);
        driver.findElement(btn_Add_End_TraceLink).click();
        WaitStatements.checkVisibility(driver, txt_AddEnd, 5);

    }

    //"Add" option from the "Start" (Parent) tracelink window.
    private void addOption_Start_TraceLink() {
        WaitStatements.checkClickable(driver, 5, btn_Add_Start_TraceLink);
        driver.findElement(btn_Add_Start_TraceLink).click();
        WaitStatements.checkVisibility(driver, txt_AddEnd, 5);
    }

    //It is a "Default" tracelink type.
    private void default_traceLink() {
        driver.findElement(inpt_TraceLinkType).click();
        WaitStatements.checkClickable(driver, 5, option_DefaultType_TraceLink);
        driver.findElement(option_DefaultType_TraceLink).click();

    }


    public void option_generateTraceLinkMatrix_New() {
        option_New_MoreCommands("Generate Trace Link Matrix");
    }

    public void option_generateReport_New() {
        option_New_MoreCommands("Generate Report");
    }

    public void option_createAliasID_New() {
        option_New_MoreCommands("Create Alias ID");
    }


    //*****************************   EDIT   **********************

    //This is the 'Edit' option under 'More Commands'.
    private void option_Edit_MoreCommands(String optionName) {
        moreCommands_Home("edit");
    }


    //*****************************   Manage   **********************

    //This is the 'Manage' option under 'More Commands'.
    private void option_Manage_MoreCommands(String optionName) {
        moreCommands_Home("Manage");
        List<WebElement> options_Manage = driver.findElements(option_Manage_Locator);
        for (WebElement option : options_Manage) {
            if (option.getText().equalsIgnoreCase(optionName)) {
                option.click();
            }
        }

    }

    public void option_AddToMyChanges_Mange() {
        option_Manage_MoreCommands("Add to My Changes");
    }

    public void option_MarkAsSuspect_Mange() {
        option_Manage_MoreCommands("Mark as Suspect");
    }

    public void option_PinToHome_Mange() {
        option_Manage_MoreCommands("Pin to Home");
    }

    public void option_AddToFavorites_Mange() {
        option_Manage_MoreCommands("Add to Favorites");
    }

    public void option_Projects_Mange() {
        option_Manage_MoreCommands("Projects");
    }


    //Available projects and Assigned projects
    public String assignedProjects(String projectName) {
        option_Projects_Mange();
        String projectNames = null;
        WaitStatements.checkVisibility(driver, list_assignedProjects, 5);
        try {
            List<WebElement> assignedProjects = driver.findElements(RelativeLocator.with(list_assignedProjects).above(txt_Available_project));
            for (WebElement assignedProject : assignedProjects) {
                System.out.println(assignedProject);
                projectNames = assignedProject.getText();
                if (assignedProject.getText().equalsIgnoreCase(projectName)) {
                    assignedProject.click();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("No projects assigned...");
        }
        return projectNames;
    }


    public void remove_AssignedProject(String projectName) {
        assignedProjects(projectName);
        WaitStatements.checkVisibility(driver, btn_RemoveProject, 5);
        driver.findElement(btn_RemoveProject).click();
        WebElement btn_Save = driver.findElement(addButton_Locator);
        btn_Save.click();
    }

    public void add_NewProjects() {

    }


    //*****************************   SHARE   **********************

    //This is the 'Share' option under 'More Commands'.
    private void option_Share_MoreCommands(String optionName) {
        moreCommands_Home("Share");
    }

    //*****************************   VIEW   **********************

    //This is the 'View' option under 'More Commands'.
    private void option_view_MoreCommands(String optionName) {
        moreCommands_Home("view");
    }

    //*****************************   IMPORT/EXPORT   **********************

    //This is the 'Import/Export' option under 'More Commands'.
    private void option_ImportOrExport_MoreCommands(String optionName) {
        moreCommands_Home("Import/Export");
    }


    //************* Options from the 'New' ***************
//This is the 'add' option from the 'New'
    public void create_NewFolder(String FolderName) {
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
        openOptions("open");
        WaitStatements.checkVisibility(driver, summaryHeader, 5);
        // WaitStatements.waitJVM(3000);
    }

    public void create_Item(String ItemName) {
        WebElement itemNameInput = driver.findElement(itemNameInput_Locator);
        itemNameInput.clear();
        itemNameInput.sendKeys(ItemName);
        Objects.requireNonNull(Helper.getElements(driver, ItemName, itemNameValues_Locator)).click();

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
        } catch (Exception e) {
            e.fillInStackTrace();
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
    private void openOptions(String open) {
        WaitStatements.checkClickable(driver, 5, openHome_Locator);
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

    public void open() {
        openOptions("Open");
    }

    //this is used to open any object
    public void openObject(WebElement element) {
        Actions action = new Actions(driver);
        try {
            WebElement openButton = driver.findElement(btn_Open_locator);
            action.moveToElement(element).click(openButton).build().perform();
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }

    public void openObject() {
        try {
            WaitStatements.checkClickable(driver, 5, btn_Open_locator);
            WebElement openButton = driver.findElement(btn_Open_locator);
            openButton.click();
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }


    //only for test
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://erlr16fx.ad005.onehc.net:10631/#/com.siemens.splm.clientfx.tcui.xrt.showObject?uid=AwqhDOpuongsCC");
        AW_HomePage aw_homePage = new AW_HomePage(driver);
        AW_DashBoardPage aw_dashBoardPage = new AW_DashBoardPage(driver);
        aw_dashBoardPage.changeGroupAndRole("Support.Functions.Healthcare", "Supporter");
     /*   aw_homePage.homePage();
        aw_homePage.create_NewFolder("Test1");
        aw_homePage.create_NewItem("user need specification");
        AW_ItemOpenPage awItemOpenPage = new AW_ItemOpenPage(driver);
        openObject();
        awItemOpenPage.child_add_TopControlMenu("User Need");
*/
        aw_homePage.remove_AssignedProject("CMI_ERL_MR_read");
        Thread.sleep(5000);
    }

/*
    public static void openFolder() throws InterruptedException {
        WebElement openFolder = driver.findElement(By.xpath("//div[@class='aw-jswidgets-tableNonEditContainer aw-layout-flexRowContainer']//*[contains(text(),'87832')]"));
        WaitStatements.checkClickable(driver, 20, openFolder);
        openFolder.click();

        open("open");
    }

 */
}

