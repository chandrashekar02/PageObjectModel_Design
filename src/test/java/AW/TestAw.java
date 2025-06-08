package AW;

import com.chandrashekar.pages.PageObjectModel.ActiveWorkspace.AW_AdvancedSearchPage;
import com.chandrashekar.pages.PageObjectModel.ActiveWorkspace.AW_DashBoardPage;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestAw {

    WebDriver driver;
    AW_DashBoardPage aw_dashBoardPage;

    //Resources
    String URL_QA = "https://teamcenter.ad005.onehc.net:10231/#/showHome";
    String URL_INT1 = "https://erlr16fx.ad005.onehc.net:10531/#/showHome";
    String authorGroup = "SCM.FOR.CT.Healthcare";
    String authorRole = "Engineer";
    int testcaseID = 97014;
    String reviewerGroup = "";
    String reviewerRole = "";
    String approverGroup = "";
    String approverRole = "";

    @BeforeTest
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.get(URL_QA);
    }

    @Test(priority = 1)
    @Description("Verify the Group and Role")
    public void test_changeGroup() throws InterruptedException {
        aw_dashBoardPage = new AW_DashBoardPage(driver);
        aw_dashBoardPage.changeGroupAndRole(authorGroup,authorRole);

        //Validate
        Assert.assertEquals(aw_dashBoardPage.getCurrentGroupText(), authorGroup);
        Assert.assertEquals(aw_dashBoardPage.getCurrentRoleText(), authorRole);
    }

    @Test(priority = 2)
    @Description("Verify that able to load the test data")
    public void testDataLoad(){
        AW_AdvancedSearchPage search = new AW_AdvancedSearchPage(driver);
        search.testData_Folder(testcaseID);
    }

}
