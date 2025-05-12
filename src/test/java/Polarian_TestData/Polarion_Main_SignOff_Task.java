package Polarian_TestData;

import Polarion_Testdata.DocumentPage;
import Polarion_Testdata.Polarion_LoginPage;
import Polarion_Testdata.Polarion_SidebarPage;
import Polarion_Testdata.Polarion_Signoff_Tasks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Polarion_Main_SignOff_Task extends Polarion_SidebarPage {
    private static final Logger log = LoggerFactory.getLogger(Polarion_Main_SignOff_Task.class);

    public Polarion_Main_SignOff_Task(WebDriver driver) {
        super(driver);
    }

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        // driver.get("https://polarionqa.healthcare.siemens.com/polarion/#/project/Phase1_R10_Verification_Project1/wiki/CHANDRASHEKARLA/Product_Requirement_Specification_TemplatePRS");
        Polarion_Signoff_Tasks ps = new Polarion_Signoff_Tasks(driver);
        DocumentPage documentPage = new DocumentPage(driver);

        myDocument();
        Polarion_LoginPage.login(getAuthorName());
        documentPage.createDocument("Product Requirement Specification Template","PRS","chandrashekar");
        ps.approvedStatus();















      /*  ps.performActionStartReview();
        logout();
        ps.reviewer_Sign();
        logout();
        ps.author_login();
        ps.performActionStartApproval();   //will change the status to "In Approval"
        logout();
        ps.approver_Sign();
        logout();
        ps.author_Sign();
        Thread.sleep(5000);
        driver.quit();
*/
    }
}
