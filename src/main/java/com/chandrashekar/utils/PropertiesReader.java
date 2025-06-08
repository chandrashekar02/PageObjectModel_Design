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


    public static String readData(int testcaseNumber, String name) {
        Properties p = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\AW.properties");
            p = new Properties();
            p.load(fileInputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String prefix = "TC." + testcaseNumber + ".";

        assert p != null;
        return p.getProperty(prefix + name);
    }


    public static void main(String[] args) {
          int TestcaseID = 87680;
        System.out.println(PropertiesReader.readData(TestcaseID,"authorGroup"));

    }
}

