package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AuthorizationPage {

    private final WebDriver driver;

    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By authLoginBtn = By.xpath(".//button[contains(text(),'Войти')]");
    private final By authRegistrationBtn = By.xpath(".//a[contains(text(),'Зарегистрироваться')]");
    private final By authRestorePassword = By.xpath(".//a[contains(text(),'Восстановить пароль')]");
    private final By authEmail = By.xpath(
            ".//div[@class='input pr-6 pl-6 input_type_text input_size_default']//input");
    private final By authPassword = By.xpath(
            ".//div[@class='input pr-6 pl-6 input_type_password input_size_default']//input");

    public void clickToRegistrationBtn(){
        driver.findElement(authRegistrationBtn).click();
    }

    public void clickToRestoreBtn(){
        driver.findElement(authRestorePassword).click();
    }

    public void getAuthorization(String email, String password){
        driver.findElement(authEmail).sendKeys(email);
        driver.findElement(authPassword).sendKeys(password);
        driver.findElement(authLoginBtn).click();
    }

    public String getTextAuthLoginBtn(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(authLoginBtn));
        WebElement element = driver.findElement(authLoginBtn);
        return element.getText();
    }
}