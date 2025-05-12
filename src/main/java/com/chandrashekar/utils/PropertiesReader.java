package com.chandrashekar.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReader {

    public static String readKey(String key) {

        Properties p = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\AW.properties");
            p = new Properties();
            p.load(fileInputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return p.getProperty(key);
    }

    public static void main(String[] args) {
        System.out.println(PropertiesReader.readKey("URL_AW_INT1"));
    }
}
