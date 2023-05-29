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

    private final By mainPagePersonalArea = By.xpath(".//p[contains(text(),'Личный Кабинет')]");
    private final By mainPageLogo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a");
    private final By mainPageLoginBtn = By.xpath(".//button[contains(text(),'Войти в аккаунт')]");
    private final By mainPageCheckoutBtn = By.xpath(".//button[contains(text(),'Оформить заказ')]");
    private final By mainPageConstructorText = By.xpath(".//h1[contains(text(),'Соберите бургер')]");
    private final By mainPageCategoriesText = By.xpath(
            ".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");

    private By getMainPageConstructors(String categories) {
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

    public void selectCategories(String categories){
        driver.findElement(getMainPageConstructors(categories)).click();
    }

    public String getTextAuthCheckoutBtn(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(mainPageCheckoutBtn));
        WebElement element = driver.findElement(mainPageCheckoutBtn);
        return element.getText();
    }

    public String getTextHeaderConstructor(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(mainPageConstructorText));
        WebElement element = driver.findElement(mainPageConstructorText);
        return element.getText();
    }

    public String getTextNoActiveCategories(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(mainPageCategoriesText));
        WebElement element = driver.findElement(mainPageCategoriesText);
        return element.getText();
    }
}