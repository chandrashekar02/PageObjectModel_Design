package Practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Base_Class {

    WebDriver driver;
    String BaseURL;
    WebDriverWait wait;

    public Base_Class(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    public void navigateToURL(String BaseURL) {
        this.BaseURL = BaseURL;
        driver.get(BaseURL);
    }

}
