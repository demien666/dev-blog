package com.demien.seltest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/home/dmitry/Dev/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://mysku.ru");


    }
}
