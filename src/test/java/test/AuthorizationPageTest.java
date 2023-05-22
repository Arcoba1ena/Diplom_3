package test;

import api.request.Request;
import api.functions.UserCreateFunctions;
import api.models.response.UserResponseModel;

import io.qameta.allure.junit4.DisplayName;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import ui.pages.MainPage;
import ui.pages.AuthorizationPage;
import ui.pages.RegistrationPage;
import ui.pages.RestorePage;

import static api.functions.Util.deserialize;
import static api.functions.UserDeleteFunctions.getUserDelete;

@RunWith(Parameterized.class)
public class AuthorizationPageTest {

    @Before
    public void setConfig() {
        WebDriverManager.chromedriver().setup();
        Request request = new Request();
        request.apiEndPoint();
        getCreateUser();
    }

    private WebDriver driver;

    private final String name;
    private final String email;
    private final String password;

    public AuthorizationPageTest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Parameters(name = "Тестовые данные: {0},{1}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {"UsersTests", "usersTests@yandex.ru", "uniqueP@sw0rd!"}
        };
    }

    public void getStarted() {
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site");
    }

    private UserResponseModel response;

    public void getCreateUser() {
        UserCreateFunctions userCreate = new UserCreateFunctions();
        response = deserialize(userCreate.getUserCreate(name, email, password, 200), UserResponseModel.class);
    }

    @Test
    @DisplayName("Авторизация пользователя - вход по кнопке «Войти в аккаунт» на главной")
    public void checkLoginFromMainPage() {
        getStarted();

        MainPage mainPage = new MainPage(driver);
        mainPage.waitLoadMainPages();
        mainPage.clickToLoginBtn();

        AuthorizationPage authorization = new AuthorizationPage(driver);
        authorization.getAuthorization(email, password);

        Assert.assertEquals("Оформить заказ", mainPage.getTextAuthCheckoutBtn());
    }

    @Test
    @DisplayName("Авторизация пользователя - вход через кнопку «Личный кабинет»")
    public void checkLoginFromPersonalAreaPage() {
        getStarted();

        MainPage mainPage = new MainPage(driver);
        mainPage.waitLoadMainPages();
        mainPage.clickToPersonalAreaBtn();

        AuthorizationPage authorization = new AuthorizationPage(driver);
        authorization.getAuthorization(email, password);

        Assert.assertEquals("Оформить заказ", mainPage.getTextAuthCheckoutBtn());
    }

    @Test
    @DisplayName("Авторизация пользователя - вход через кнопку в форме регистрации")
    public void checkLoginFromRegistrationForm() {
        getStarted();

        MainPage mainPage = new MainPage(driver);
        mainPage.waitLoadMainPages();
        mainPage.clickToLoginBtn();

        AuthorizationPage authorization = new AuthorizationPage(driver);
        authorization.clickToRegistrationBtn();

        RegistrationPage registration = new RegistrationPage(driver);
        registration.clickToLoginBtn();

        authorization.getAuthorization(email, password);

        Assert.assertEquals("Оформить заказ", mainPage.getTextAuthCheckoutBtn());
    }

    @Test
    @DisplayName("Авторизация пользователя - вход через кнопку в форме восстановления пароля.")
    public void checkLoginFromRestoreForm() {
        getStarted();

        MainPage mainPage = new MainPage(driver);
        mainPage.waitLoadMainPages();
        mainPage.clickToLoginBtn();

        AuthorizationPage authorization = new AuthorizationPage(driver);
        authorization.clickToRestoreBtn();

        RestorePage restorePage = new RestorePage(driver);
        restorePage.clickToLoginBtn();

        authorization.getAuthorization(email, password);

        Assert.assertEquals("Оформить заказ", mainPage.getTextAuthCheckoutBtn());
    }

    @After
    public void teardown() {
        driver.quit();
        getUserDelete(response.getAccessToken());
    }
}