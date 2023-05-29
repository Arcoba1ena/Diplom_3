package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By registrationLoginBtn = By.xpath(".//a[contains(text(),'Войти')]");
    private final By registrationText = By.xpath(".//h2[contains(text(),'Регистрация')]");
    private final By registrationBtn = By.xpath(".//button[contains(text(),'Зарегистрироваться')]");
    private final By registrationTextError = By.xpath(".//p[contains(text(),'Некорректный пароль')]");

    private By registrationFields(Integer index) {
        return By.xpath(String.format(".//fieldset[%s]/div/div/input", index));
    }

    public String getErrorTextRegistrationForm(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(registrationTextError));
        WebElement element = driver.findElement(registrationTextError);
        return element.getText();
    }

    public void clickToLoginBtn(){
        driver.findElement(registrationLoginBtn).click();
    }

    public void getRegistration(String name, String email, String password){
        driver.findElement(registrationFields(1)).sendKeys(name);
        driver.findElement(registrationFields(2)).sendKeys(email);
        driver.findElement(registrationFields(3)).sendKeys(password);
        driver.findElement(registrationBtn).click();
    }
}