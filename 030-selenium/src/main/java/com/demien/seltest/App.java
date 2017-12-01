package com.demien.seltest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

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

    public static void select(String id, String option) {
        Select select = new Select(driver.findElement(By.id(id)));
        select.selectByVisibleText(option);
    }

    public static void addDepartment(String id, String name) {
        clearAndSet("input-departments-id", id);
        clearAndSet("input-departments-name", name);
        click("submit-departments");
    }

    public static void addUser(String id, String name, String email, String department) {
        clearAndSet("input-users-id", id);
        clearAndSet("input-users-name", name);
        clearAndSet("input-users-email", email);
        select("select-users-department", department);
        click("submit-users");
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
        checkTable(id, new LinkedList<>(Arrays.asList(values)));
    }

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "d:/dev/selenium/chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("http://localhost:3000");

        addDepartment("IT", "Informational technologies");
        addDepartment("ADM", "Administration");
        addDepartment("SEQ", "Security");

        checkTable("table-departments", "IT", "ADM", "SEQ", "Informational technologies", "Administration", "Security");

        addUser("JBL", "Joe Black", "joe.black@email.com", "Security");
        addUser("HSEB", "Huan Sebastyan", "huan.sebastyan@email.com", "Administration");

        checkTable("table-users", "JBL", "Joe Black", "joe.black@email.com", "Security", "HSEB", "Huan Sebastyan", "huan.sebastyan@email.com", "Administration");

    }
}
