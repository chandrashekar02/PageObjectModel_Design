package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class GroupRole_Page extends Base_Class{
    public GroupRole_Page(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//div[@class='aw-layout-flexRowContainer aw-header-searchView']//button[@aria-label='Your Profile']")
    WebElement profile;
    @FindBy(xpath = "//ul[@role='listbox']//span")   //it will open group list
    List<WebElement> groupList;
    @FindBy(xpath = "//span[contains(text(),'Group')]")    //this is the Text 'group' to locate group link
    WebElement groupText;
    WebElement groupName;
    String expectedGroup;
    String currentGroup;

    public void groupChange(String expectedGroup){
        wait.until(ExpectedConditions.visibilityOf(profile)).click();
        groupName = driver.findElement(with(By.tagName("a")).below(groupText));
        currentGroup = groupName.getText();
        this.expectedGroup = expectedGroup;
        System.out.println("Current group is :"+currentGroup);
        System.out.println("Changing the group to :"+expectedGroup);

        if(!currentGroup.equals(expectedGroup)){
            groupName.click();
            for(WebElement group:groupList){
                if(group.getText().equals(expectedGroup)){
                    group.click();
                    break;
                }
            }
        }
    }





}
