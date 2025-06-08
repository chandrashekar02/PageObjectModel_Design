package com.chandrashekar.pages.PageObjectModel.ActiveWorkspace;

import com.chandrashekar.base.BaseClass;
import com.chandrashekar.utils.WaitStatements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.util.ArrayList;
import java.util.List;

public class AW_ItemPage extends BaseClass {
    public AW_ItemPage(WebDriver driver) {
        super(driver);
    }


    //Locators
    By link_TraceLink_Tab = By.linkText("Trace Link");


    public void traceLink_Tab() {
        WaitStatements.checkClickable(driver, 5, link_TraceLink_Tab);
        driver.findElement(link_TraceLink_Tab).click();
    }

    //This method will give list of elements from the table (i.e, Defining or Complying)
    public List<WebElement> getItemsNameFromTable(String tableName) {
        By txt_ObjectColumn = By.xpath("//details[@caption='" + tableName + "']//div[text()='Object']");
        By itemFromTable = By.xpath("//div[@aria-colindex='2']//div[@class='aw-splm-tableCellText']");
        WaitStatements.checkVisibility(driver,txt_ObjectColumn,5);
        return driver.findElements(RelativeLocator.with(itemFromTable).below(txt_ObjectColumn));
    }

    //this will get revision item from the Defining table.
    public WebElement itemRevision_FromDefining_Table(String itemName) {
        traceLink_Tab();

        List<WebElement> items = getItemsNameFromTable("Defining");
        for (WebElement item : items) {
            if(item.getText().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    //this will get revision item from the Complying table.
    public WebElement itemRevision_FromComplying_Table(String itemName) {
        traceLink_Tab();
        List<WebElement> items = getItemsNameFromTable("Complying");
        for (WebElement item : items) {
            if(item.getText().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }


    //validate
    public List<String> itemRevision_Validation(String tableName) {
        traceLink_Tab();
        List<String> items = new ArrayList<>();
        List<WebElement> elements = getItemsNameFromTable(tableName);

        int index= 0;
        while(index<elements.size()) {
            items.add(elements.get(index).getText());
           index++;
        }
        return items;
    }



    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://erlr16fx.ad005.onehc.net:10531/#/com.siemens.splm.clientfx.tcui.xrt.showObject?s_uid=AQthkgTVqjf%24pA&uid=AImhkgTVqjf%24pA");
        Thread.sleep(20000);
        AW_ItemPage awItemPage = new AW_ItemPage(driver);
        System.out.println(awItemPage.itemRevision_Validation("Defining"));

    }





}
