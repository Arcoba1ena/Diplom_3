package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAreaPage {
    private final WebDriver driver;
    public PersonalAreaPage(WebDriver driver) {
        this.driver = driver;
    }
    private final By personalAreaExitBtn = By.xpath(".//button[contains(text(),'Выход')]");
}