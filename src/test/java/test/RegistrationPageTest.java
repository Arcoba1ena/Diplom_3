package test;

import api.request.Request;
import api.functions.UserLoginFunctions;
import api.models.response.UserResponseModel;

import io.qameta.allure.junit4.DisplayName;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.Test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.chrome.ChromeDriver;

import ui.pages.MainPage;
import ui.pages.RegistrationPage;
import ui.pages.AuthorizationPage;

import static api.functions.Util.deserialize;
import static api.functions.UserDeleteFunctions.getUserDelete;

@RunWith(Parameterized.class)
public class RegistrationPageTest {

    @Before
    public void setConfig() {
        WebDriverManager.chromedriver().setup();
        Request request = new Request();
        request.apiEndPoint();
    }

    private WebDriver driver;
    private final String name;
    private final String email;
    private final String password;

    public RegistrationPageTest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Parameters(name = "Тестовые данные: {0},{1},{2}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {"usersTests", "usersTests@yandex.ru", "uniqueP@sw0rd!"}
        };
    }

    public void getStarted() {
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site");
    }

    @Test
    @DisplayName("Регистрация пользователя - позитивный сценарий")
    public void checkRegistration() {
        getStarted();

        MainPage mainPage = new MainPage(driver);
        mainPage.waitLoadMainPages();
        mainPage.clickToLoginBtn();

        AuthorizationPage authorization = new AuthorizationPage(driver);
        authorization.clickToRegistrationBtn();

        RegistrationPage registration = new RegistrationPage(driver);
        registration.getRegistration(name, email, password);

        UserLoginFunctions userLogin = new UserLoginFunctions();
        UserResponseModel responseLogin = deserialize(userLogin.getUserLogin(email, password, 200),
                UserResponseModel.class);

        getUserDelete(responseLogin.getAccessToken());

        Assert.assertEquals("Войти", authorization.getTextAuthLoginBtn());
    }

    @Test
    @DisplayName("Регистрация пользователя - некорректный пароль")
    public void checkUnValidRegistration() {
        getStarted();

        MainPage mainPage = new MainPage(driver);
        mainPage.waitLoadMainPages();
        mainPage.clickToLoginBtn();

        AuthorizationPage authorization = new AuthorizationPage(driver);
        authorization.clickToRegistrationBtn();

        RegistrationPage registration = new RegistrationPage(driver);
        registration.getRegistration(name, email, password.replace("queP@sw0rd!", ""));

        Assert.assertEquals("Некорректный пароль", registration.getErrorTextRegistrationForm());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}