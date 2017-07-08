package com.demien.seltest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class App {

    static WebDriver driver;

    public static void clearAndSet(String id, String value) {
        driver.findElement(By.id(id)).clear();
        driver.findElement(By.id(id)).sendKeys(value);
    }

    public static void click(String id) {
        driver.findElement(By.id(id)).click();
    }

    public static void addDepartment(String id, String name) {
        clearAndSet("input-departments-id", id);
        clearAndSet("input-departments-name", name);
        click("submit-departments");
    }

    public static void checkTable(String id, List<String> values) {
        WebElement table = driver.findElement(By.id(id));

        List<WebElement> allRows = table.findElements(By.tagName("tr"));
        for (WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                values.remove(cell.getText());
            }
        }

        if (values.size() > 0) {
            throw new RuntimeException("Some elements are absent in table:" + Arrays.toString(values.toArray()));
        }
    }

    public static void checkTable(String id, String... values) {
        checkTable(id, new LinkedList<String>(Arrays.asList(values)));
    }

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/home/dmitry/Dev/chromedriver");
        driver = new ChromeDriver();

        driver.get("http://localhost:3000");

        addDepartment("IT", "Informational technologies");
        addDepartment("ADM", "Administration");
        addDepartment("SEQ", "Security");

        checkTable("table-departments", "IT", "ADM", "SEQ", "Informational technologies", "Administration", "Security");



    }
}
