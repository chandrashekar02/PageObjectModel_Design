package AW;

import com.chandrashekar.pages.PageObjectModel.ActiveWorkspace.AW_AdvancedSearchPage;
import com.chandrashekar.pages.PageObjectModel.ActiveWorkspace.AW_DashBoardPage;
import com.chandrashekar.pages.PageObjectModel.ActiveWorkspace.AW_HomePage;
import com.chandrashekar.pages.PageObjectModel.ActiveWorkspace.AW_ItemPage;
import com.chandrashekar.utils.AW_PopupNotification;
import com.chandrashekar.utils.PropertiesReader;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckAllowedTraceLinks_88214 {
    WebDriver driver;

    private final int TestcaseID = 88214;

    private String URL_AW = PropertiesReader.readKey("URL_AW_INT1");
    private String authorGroup = PropertiesReader.readData(TestcaseID, "authorGroup");
    private String authorRole = PropertiesReader.readData(TestcaseID, "authorRole");


    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.get(URL_AW);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            //driver.quit();
        }
    }

    @Test(priority = 1)
    public void test_ChangeGroupAndRole() throws InterruptedException {
        AW_DashBoardPage aw_dashBoardPage = new AW_DashBoardPage(driver);
        aw_dashBoardPage.changeGroupAndRole(authorGroup, authorRole);
    }

    @Description("Verify that the Test data is opened.")
    @Test(priority = 2)
    public void test_TestData() {
        AW_AdvancedSearchPage aw_advancedSearchPage = new AW_AdvancedSearchPage(driver);
        aw_advancedSearchPage.testData_Folder(TestcaseID);
    }

    @Description("Verify that able to create a trace link from DIR to STE")
    @Test(priority = 3)
    public void test_CreateTraceLinkFromDIRtoSTE() {
        AW_HomePage aw_homePage = new AW_HomePage(driver);
        aw_homePage.option_createTraceLink_New("DIR", "STE");

        Assert.assertEquals(AW_PopupNotification.popupMessage(driver), "Trace link created successfully.");
    }

    @Description("Verify that able to create a trace link from TF to STE")
    @Test(priority = 4)
    public void test_CreateTraceLinkFromTFtoSTE() {
        AW_HomePage aw_homePage = new AW_HomePage(driver);
        aw_homePage.option_createTraceLink_New("TV", "STE");

        Assert.assertEquals(AW_PopupNotification.popupMessage(driver), "Trace link created successfully.");
    }

    @Description("Verify that able to create a trace link from TFV to STE")
    @Test(priority = 5)
    public void test_CreateTraceLinkFromTFVtoSTE() {
        AW_HomePage aw_homePage = new AW_HomePage(driver);
        aw_homePage.option_createTraceLink_New("TFV", "STE");

        Assert.assertEquals(AW_PopupNotification.popupMessage(driver), "Trace link created successfully.");
    }

    @Description("Verify that trace linked objects are visible under defining object")
    @Test(priority = 6)
    public void test_CheckDefiningObjects() {
        AW_ItemPage awItemPage = new AW_ItemPage(driver);

        List<String> items = awItemPage.itemRevision_Validation("Defining");

        assertThat(items).anyMatch(s -> s.contains("DIR"));
        assertThat(items).anyMatch(s -> s.contains("TFV"));
        assertThat(items).anyMatch(s -> s.contains("TF"));

    }


}
