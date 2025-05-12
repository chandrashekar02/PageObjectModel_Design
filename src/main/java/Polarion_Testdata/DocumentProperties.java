package Polarion_Testdata;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.Random;
public class DocumentProperties {

    public static void main(String[] args) {


        Random random = new Random();
        int number = random.nextInt(17) + 1; // 1 to 17
        System.out.println("Random number: " + number);
    }}
