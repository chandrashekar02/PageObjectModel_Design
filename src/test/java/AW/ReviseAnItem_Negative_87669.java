package AW;

import com.chandrashekar.pages.PageObjectModel.ActiveWorkspace.AW_AdvancedSearchPage;
import com.chandrashekar.pages.PageObjectModel.ActiveWorkspace.AW_DashBoardPage;
import com.chandrashekar.pages.PageObjectModel.ActiveWorkspace.AW_HomePage;
import com.chandrashekar.pages.PageObjectModel.ActiveWorkspace.TestData;
import com.chandrashekar.utils.AW_PopupNotification;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ReviseAnItem_Negative_87669 {
    WebDriver driver;

    String authorGroup = "Engineering.CAI.DX.Healthcare";
    String authorRole = "Engineer";
    int TestcaseID = 87669;


    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://erlr16fx.ad005.onehc.net:10631/#/showHome");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 1)
    public void test_ChangeGroupAndRole() throws InterruptedException {
        AW_DashBoardPage aw_dashBoardPage = new AW_DashBoardPage(driver);
        aw_dashBoardPage.changeGroupAndRole(authorGroup, authorRole);
    }

    @Description("Verify that the object is selected from the Test data")
    @Test(priority = 2)
    public void test_TestData() {
        AW_AdvancedSearchPage aw_advancedSearchPage = new AW_AdvancedSearchPage(driver);
        TestData testData = aw_advancedSearchPage.testData_Folder(TestcaseID);
        testData.select_ObjectRevision_FromTestDataFolder("Part");
    }

    @Description("Verify that the Revise the Part Item")
    @Test(priority = 3)
    public void test_ReviseItem() {
        AW_HomePage aw_homePage = new AW_HomePage(driver);
        aw_homePage.option_revise_New();

        Assert.assertEquals(AW_PopupNotification.popupMessage(driver), "Working Revisions limit exceeded for Item. The Maximum number of working revisions allowed is: 1");
    }

}
