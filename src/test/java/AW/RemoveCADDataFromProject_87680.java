package AW;


import com.chandrashekar.pages.PageObjectModel.ActiveWorkspace.AW_AdvancedSearchPage;
import com.chandrashekar.pages.PageObjectModel.ActiveWorkspace.AW_DashBoardPage;
import com.chandrashekar.pages.PageObjectModel.ActiveWorkspace.AW_HomePage;
import com.chandrashekar.pages.PageObjectModel.ActiveWorkspace.TestData;
import com.chandrashekar.utils.AW_PopupNotification;
import com.chandrashekar.utils.PropertiesReader;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RemoveCADDataFromProject_87680 {
    WebDriver driver;

    private final int TestcaseID = 87680;

    private String URL_AW = PropertiesReader.readKey("URL_AW_INT2");
    private String authorGroup = PropertiesReader.readData(TestcaseID, "authorGroup");      //"Support.Functions.Healthcare";
    private String authorRole = PropertiesReader.readData(TestcaseID, "authorRole");  // "Supporter";
    private String objectName = PropertiesReader.readData(TestcaseID, "objectName");  // "Model assigned to Hardware_FOR_CT_read";
    private String projectName1 = PropertiesReader.readData(TestcaseID, "projectName");  //"CMI_ERL_MR_read";
    private String projectName2 = PropertiesReader.readData(TestcaseID, "projectName2");   //"Hardware_FOR_CT_read";



        @BeforeTest
        public void setup() {
            driver = new ChromeDriver();
            driver.get(URL_AW);
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

        @Description("Verify that the object is opened from the Test data")
        @Test(priority = 2)
        public void test_TestData() {
            AW_AdvancedSearchPage aw_advancedSearchPage = new AW_AdvancedSearchPage(driver);
            TestData testData = aw_advancedSearchPage.testData_Folder(TestcaseID);
            testData.open_Object_Item(objectName);
        }

    @Description("Verify that the project 'CMI_ERL_MR_read' is available")
    @Test(priority = 3)
    public void test_CheckProjectPresence() {
        AW_HomePage aw_homePage = new AW_HomePage(driver);
        aw_homePage.remove_AssignedProject(projectName1);

        assertThat(AW_PopupNotification.popupMessage(driver)).contains('"' + objectName + '"' + " is updated with project(s).");
    }

    @Description("Verify that the project 'Hardware_FOR_CT_read' is removed")
    @Test(priority = 4)
    public void test_RemoveProjectFromRevision() {
        AW_HomePage aw_homePage = new AW_HomePage(driver);
        TestData testData = new TestData(driver);
        testData.select_Revision(objectName, 002);
        aw_homePage.remove_AssignedProject(projectName2);

        assertThat(AW_PopupNotification.popupMessage(driver)).contains('"' + objectName + '"' + " is updated with project(s).");
    }


}
