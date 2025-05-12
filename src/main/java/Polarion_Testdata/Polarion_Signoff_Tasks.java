package Polarion_Testdata;

import org.openqa.selenium.WebDriver;

public class Polarion_Signoff_Tasks extends Polarion_SidebarPage{

    //This page will handle the Sign-off tasks
    public Polarion_Signoff_Tasks(WebDriver driver) {
        super(driver);
    }

    public void reviewer_Sign(){
        matchURL();
        try {
            login(getReviewerName());
            approval_Process();
            electronicSignature();
            System.out.println("Reviewer is signed the document");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void author_login(){
        matchURL();
        try{
            login(getAuthorName());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void author_Sign(){
        matchURL();
        try {
            login(getAuthorName());
            approval_Process();
            electronicSignature();
            System.out.println("Author signed the Document");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void approver_Sign() {
        matchURL();
        try {
            login(getApproverName());
            approval_Process();
            electronicSignature();
            System.out.println("Approver signed the Document");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void approvedStatus()
    {
        Polarion_Signoff_Tasks ps = new Polarion_Signoff_Tasks(driver);
        performActionStartReview();
        logout();
        ps.reviewer_Sign();
        logout();
        ps.author_login();
        ps.performActionStartApproval();
        logout();
        ps.approver_Sign();
        logout();
        ps.author_Sign();
        driver.quit();
    }

}
