package AW;

import com.chandrashekar.pages.PageObjectModel.ActiveWorkspace.AW_DashBoardPage;
import com.chandrashekar.pages.PageObjectModel.ActiveWorkspace.AW_HomePage;
import com.chandrashekar.pages.PageObjectModel.ActiveWorkspace.AW_ItemOpenPage;
import com.chandrashekar.utils.AW_PopupNotification;
import com.chandrashekar.utils.PropertiesReader;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateRequirementObject_87733 {

    private WebDriver driver;
    AW_DashBoardPage aw_dashBoardPage;
    AW_HomePage aw_homePage;
    AW_ItemOpenPage awItemOpenPage;

    //Resources
    private String URL_AW = PropertiesReader.readKey("URL_AW_INT2");     //"https://teamcenter.ad005.onehc.net:10231/";
    private String authorGroup = PropertiesReader.readKey("TC.87733.authorGroup");
    private String authorRole = PropertiesReader.readKey("TC.87733.authorRole");
    private String objectName = PropertiesReader.readKey("TC.87733.objectName");
    private String childObjectName = PropertiesReader.readKey("TC.87733.childObjectName");
    private String subChildObjectName = PropertiesReader.readKey("TC.87733.subChildObjectName");
    private String FolderName = PropertiesReader.readKey("TC.87733.FolderName");


    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.get(URL_AW);

    }

    @Test(priority = 1)
    @Description("Verify the Group and Role")
    public void test_ChangeGroupAndRole() throws InterruptedException {
        AW_DashBoardPage aw_dashBoardPage = new AW_DashBoardPage(driver);
        aw_dashBoardPage.changeGroupAndRole(authorGroup, authorRole);

        //Validate
        Assert.assertEquals(aw_dashBoardPage.getCurrentGroupText(), authorGroup);
        Assert.assertEquals(aw_dashBoardPage.getCurrentRoleText(), authorRole);
    }

    @Test(priority = 2)
    @Description("Verify that able to create a folder")
    public void test_FolderCreation()  {
        aw_dashBoardPage = new AW_DashBoardPage(driver);
        aw_homePage = aw_dashBoardPage.homePage();
        aw_homePage.create_NewFolder(FolderName);
        Assert.assertEquals(AW_PopupNotification.popupMessage(driver), '"' + FolderName + '"' + " was added.");
    }

    @Test(priority = 3)
    @Description("Verify that the User Need Specification is cerated")
    public void test_RequiredObjectCreation() throws InterruptedException {
        aw_homePage.create_NewItem(objectName);
        aw_homePage.openObject();
    }

    @Test(priority = 4)
    @Description("Verify that able to create a User need Paragraph as a child of the User Need specification")
    public void test_ChildCreation() throws InterruptedException {
        awItemOpenPage = new AW_ItemOpenPage(driver);
        awItemOpenPage.child_add_TopControlMenu(childObjectName);

        String actualText = AW_PopupNotification.popupMessage(driver);
        Assert.assertTrue(actualText.contains(childObjectName) && actualText.contains(objectName), "Popup message should contain 'child' added to 'parent' item" + actualText);
    }

    @Test(priority = 5)
    @Description("Verify that able to create a User need as a child of the User Need Paragraph")
    public void test_SubChildCreation() throws InterruptedException {
        awItemOpenPage = new AW_ItemOpenPage(driver);
        awItemOpenPage.child_add_TopControlMenu(subChildObjectName);

        String actualText = AW_PopupNotification.popupMessage(driver);
        Assert.assertTrue(actualText.contains(subChildObjectName) && actualText.contains(childObjectName), "Popup message should contain 'Sub child' added to 'child' item" + actualText);
    }

    @AfterTest
    public void tearDown() {
         driver.quit();
    }

}
