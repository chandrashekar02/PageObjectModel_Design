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

import static com.chandrashekar.utils.PropertiesReader.readKey;

public class CreateRequirementObject_87733 {

    WebDriver driver;
    AW_HomePage aw_homePage;
    AW_ItemOpenPage awItemOpenPage;

    //Resources
    String URL_Int1 = "https://teamcenter.ad005.onehc.net:10231/";
    String URL_QA = "https://teamcenter.ad005.onehc.net:10231/";
    String authorGroup = "Systems_Engineering.ERL.CV.Healthcare";
    String authorRole = "Design_Input_Engineer";
    String objectName = "User Need Specification";
    String childObjectName = "User Need Paragraph";
    String subChildObjectName = "User Need";
    String FolderName = "87733_AW";


    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        //driver.get(PropertiesReader.readKey("URL_AW_INT1"));
        driver.get(URL_Int1);

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
    public void test_FolderCreation() throws InterruptedException {
        aw_homePage = new AW_HomePage(driver);
        aw_homePage.homePage();
        aw_homePage.create_NewFolder(FolderName);
        Assert.assertEquals(AW_PopupNotification.popupMessage(driver), '"' + FolderName + '"' + " was added.");
    }

    @Test(priority = 3)
    @Description("Verify that the User Need Specification is cerated")
    public void test_RequiredObjectCreation() throws InterruptedException {
        aw_homePage = new AW_HomePage(driver);
        aw_homePage.create_NewItem(objectName);
        AW_HomePage.openObject();
    }

    @Test(priority = 4)
    @Description("Verify that able to create a User need Paragraph as a child of the User Need specification")
    public void test_ChildCreation() throws InterruptedException {
        awItemOpenPage = new AW_ItemOpenPage(driver);
        awItemOpenPage.child_add_TopControlMenu(childObjectName);

        String actualText = AW_PopupNotification.popupMessage(driver);
        Assert.assertTrue(actualText.contains(childObjectName) && actualText.contains(objectName), "Popup message should contain 'child' added to 'parent' item"+ actualText);
    }

    @Test(priority = 5)
    @Description("Verify that able to create a User need as a child of the User Need Paragraph")
    public void test_SubChildCreation() throws InterruptedException {
        awItemOpenPage = new AW_ItemOpenPage(driver);
        awItemOpenPage.child_add_TopControlMenu(subChildObjectName);

        String actualText = AW_PopupNotification.popupMessage(driver);
        Assert.assertTrue(actualText.contains(subChildObjectName) && actualText.contains(childObjectName), "Popup message should contain 'Sub child' added to 'child' item"+ actualText);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}
