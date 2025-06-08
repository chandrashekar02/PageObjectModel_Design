package Polarian_TestData;

import com.chandrashekar.pages.PageObjectModel.Ploarion.Polarion_Testdata.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Polarion_Main_SignOff_Task extends Polarion_SidebarPage {
    private static final Logger log = LoggerFactory.getLogger(Polarion_Main_SignOff_Task.class);

    public Polarion_Main_SignOff_Task(WebDriver driver) {
        super(driver);
    }

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        // driver.get("https://polarionqa.healthcare.siemens.com/polarion/#/project/Phase1_R10_Verification_Project1/wiki/CHANDRASHEKARLA/Product_Requirement_Specification_TemplatePRS");
        Polarion_Signoff_Tasks ps = new Polarion_Signoff_Tasks(driver);
        DocumentPage documentPage = new DocumentPage(driver);
        WorkItem workItem = new WorkItem();

        myDocument();
        Polarion_LoginPage.login(getAuthorName());
      //  documentPage.createDocument("Product Requirement Specification Template","PRS_13085","chandrashekar");
       // ps.approvedStatus();
        //Thread.sleep(10000);
       // workItem.createWorkItems("Test Case", "TC_14633");


        ps.performActionStartReview();
        logout();
        ps.reviewer_Sign();
        logout();
        ps.author_login();
        ps.performActionStartApproval();   //will change the status to "In Approval"
        logout();
       /* ps.approver_Sign();
        logout();
        ps.author_Sign();
        Thread.sleep(2000);
        driver.quit();
*/
    }
}
