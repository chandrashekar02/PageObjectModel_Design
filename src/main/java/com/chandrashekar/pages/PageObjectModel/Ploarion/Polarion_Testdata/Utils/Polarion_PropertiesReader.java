package com.chandrashekar.pages.PageObjectModel.Ploarion.Polarion_Testdata.Utils;

import java.io.FileInputStream;
import java.util.Properties;

public class Polarion_PropertiesReader {

    public static String readKey(String key) {

        Properties p = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\Polarion.properties");
            p = new Properties();
            p.load(fileInputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return p.getProperty(key);
    }

    public static void main(String[] args) {
        System.out.println(Polarion_PropertiesReader.readKey("pauthor3"));
    }
}
