//package AW;
//
//import com.chandrashekar.base.BaseClass;
//import com.chandrashekar.pages.PageObjectModel.ActiveWorkspace.AW_DashBoardPage;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
//public class BaseTestClass  {
//
//
//
//
//    String authorGroup;
//    String authorRole;
//
//
//    @BeforeTest
//    public void setup() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.get("https://erlr16fx.ad005.onehc.net:10631/#/showHome");
//    }
//
//    @AfterTest
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//
//    @Test(priority = 1)
//    public void test_ChangeGroupAndRole() throws InterruptedException {
//        AW_DashBoardPage aw_dashBoardPage = new AW_DashBoardPage(driver);
//        aw_dashBoardPage.changeGroupAndRole(authorGroup, authorRole);
//    }
//
//}
