package Practice;/*
package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class INT {
    public static void main(String[] args) throws InterruptedException {


        WebDriver driver = new ChromeDriver();
        driver.get("https://teamcenter.ad005.onehc.net:10231/#/showHome");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        //  Thread.sleep(50);

        try {
            WebElement profile = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='aw-layout-flexRowContainer aw-header-searchView']//button[@aria-label='Your Profile']"))));
            profile.click();
        }catch (Exception e){
            System.out.println(e.getClass());
        }


        WebElement groupText = driver.findElement(By.xpath("//span[contains(text(),'Group')]"));
        WebElement groupName = driver.findElement(with(By.tagName("a")).below(groupText));
        String currentGroup = groupName.getText();
        String expectedGroup = "ITL.Healthcare";


        if(!currentGroup.equals(expectedGroup)){
            groupName.click();

            List<WebElement> groupList = driver.findElements(By.xpath("//ul[@role='listbox']//span"));
            for(WebElement group:groupList){
                if(group.getText().equals(expectedGroup)){
                    group.click();
                    break;
                }
            }
        }

    }
}
*/


import com.chandrashekar.utils.WaitStatements;
import org.openqa.selenium.WebElement;

import java.util.List;
class INT{
public static void main(String[] args) {

                //need to pass the expected group name
String abc = "null";
String bcd = "null";


            for (int i=0; i<5; i++) {
                //it will check one by one group name

                    if (i==2) {                    //if the group name matches then it will click

                        System.out.println("click");
                        break;
                    }

            }
        }
    }
