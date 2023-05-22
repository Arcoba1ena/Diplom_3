package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAreaPage {

    private final WebDriver driver;

    public PersonalAreaPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By personalAreaProfileBtn = By.xpath(".//a[contains(text(),'Профиль')]");
    private final By personalAreaSignOutBtn = By.xpath(".//button[contains(text(),'Выход')]");
    private final By personalAreaConstructorBtn = By.xpath(".//p[contains(text(),'Конструктор')]");
    private final By personalAreaLogoBtn = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    public String personalAreaProfileBtn(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(personalAreaProfileBtn));
        WebElement element = driver.findElement(personalAreaProfileBtn);
        return element.getText();
    }

    public void clickToSignOut(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(personalAreaSignOutBtn));
        driver.findElement(personalAreaSignOutBtn).click();
    }

    public void clickToConstructorBtn(){
        driver.findElement(personalAreaConstructorBtn).click();
    }

    public void clickToLogoBtn(){
        driver.findElement(personalAreaLogoBtn).click();
    }
}