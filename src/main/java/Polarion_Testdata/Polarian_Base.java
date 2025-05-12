package Polarion_Testdata;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Polarian_Base {

    protected static WebDriver driver;
    protected static WebDriverWait wait;
    FluentWait<WebDriver> wait1;


    public Polarian_Base(WebDriver driver) {
        Polarian_Base.driver = driver;
        driver.manage().window().maximize();

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        this.wait1 = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(Exception.class);

        PageFactory.initElements(driver, this);

    }
}
