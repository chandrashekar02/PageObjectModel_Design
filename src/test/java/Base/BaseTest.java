//package Base;
//
//import com.chandrashekar.base.BaseClass;
//import com.chandrashekar.pages.PageObjectModel.ActiveWorkspace.AW_AdvancedSearchPage;
//import com.chandrashekar.pages.PageObjectModel.ActiveWorkspace.AW_DashBoardPage;
//import com.chandrashekar.pages.PageObjectModel.ActiveWorkspace.TestData;
//import com.chandrashekar.utils.PropertiesReader;
//import io.qameta.allure.Description;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
//public class BaseTest {
//
//WebDriver driver;
//    private int TestcaseID;
//
//    //Setter
//    public void setTestcaseID(int TestcaseID){
//        this.TestcaseID = TestcaseID;
//    }
//
//    private String authorGroup = PropertiesReader.readData(TestcaseID, "authorGroup");      //"Support.Functions.Healthcare";
//    private String authorRole = PropertiesReader.readData(TestcaseID, "authorRole");  // "Supporter";
//    private String objectName = PropertiesReader.readData(TestcaseID, "objectName");  // "Model assigned to Hardware_FOR_CT_read";
//    private String projectName1 = PropertiesReader.readData(TestcaseID, "projectName");  //"CMI_ERL_MR_read";
//    private String projectName2 = PropertiesReader.readData(TestcaseID, "projectName2");   //"Hardware_FOR_CT_read";
//
//    //Getters
//    public String getProjectName1() {
//        return projectName1;
//    }
//
//    public WebDriver getDriver() {
//        return driver;
//    }
//
//    public int getTestcaseID() {
//        return TestcaseID;
//    }
//
//    public String getAuthorGroup() {
//        return authorGroup;
//    }
//
//    public String getAuthorRole() {
//        return authorRole;
//    }
//
//    public String getObjectName() {
//        return objectName;
//    }
//
//    public String getProjectName2() {
//        return projectName2;
//    }
//
//
//
//    public void setup(String URL_AW) {
//        driver = new ChromeDriver();
//        driver.get(URL_AW);
//    }
//
//    @AfterTest
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//
//    public void test_ChangeGroupAndRole(String authorGroup, String authorRole) throws InterruptedException {
//        AW_DashBoardPage aw_dashBoardPage = new AW_DashBoardPage(driver);
//        aw_dashBoardPage.changeGroupAndRole(authorGroup, authorRole);
//    }
//
//
//    public void test_TestData(int TestcaseID, String objectName) {
//        AW_AdvancedSearchPage aw_advancedSearchPage = new AW_AdvancedSearchPage(driver);
//        TestData testData = aw_advancedSearchPage.testData_Folder(TestcaseID);
//        testData.open_Object_Item(objectName);
//    }
//}
