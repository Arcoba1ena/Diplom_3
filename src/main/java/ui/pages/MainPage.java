package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage {

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By mainPageConstructorBtn = By.xpath(".//p[contains(text(),'Конструктор')]");
    private final By mainPagePersonalArea = By.xpath(".//p[contains(text(),'Личный Кабинет')]");
    private final By mainPageLogo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a");
    private final By mainPageLoginBtn = By.xpath(".//button[contains(text(),'Войти в аккаунт')]");
    private final By mainPageCheckoutBtn = By.xpath(".//button[contains(text(),'Оформить заказ')]");

    private By mainPageConstructors(String categories) {
        return By.xpath(String.format(".//span[contains(text(),'%s')]", categories));
    }

    public void waitLoadMainPages(){
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(mainPageLogo));
    }

    public void clickToLoginBtn(){
        driver.findElement(mainPageLoginBtn).click();
    }

    public void clickToPersonalAreaBtn(){
        driver.findElement(mainPagePersonalArea).click();
    }

    public String getTextAuthCheckoutBtn(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(mainPageCheckoutBtn));
        WebElement element = driver.findElement(mainPageCheckoutBtn);
        return element.getText();
    }
}