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

import org.openqa.selenium.support.ui.WebDriverWait;
import ui.pages.MainPage;
import ui.pages.PersonalAreaPage;
import ui.pages.AuthorizationPage;

import static api.functions.Util.deserialize;
import static api.functions.UserDeleteFunctions.getUserDelete;

@RunWith(Parameterized.class)
public class PersonalAreaPageTest {

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

    public PersonalAreaPageTest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Parameters(name = "Тестовые данные: {0},{1},{2}")
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
    @DisplayName("Переход в личный кабинет - переход по клику на «Личный кабинет»")
    public void checkPersonalAreaForm() {
        getStarted();

        MainPage mainPage = new MainPage(driver);
        mainPage.waitLoadMainPages();
        mainPage.clickToPersonalAreaBtn();

        AuthorizationPage authorization = new AuthorizationPage(driver);
        authorization.getAuthorization(email, password);
        mainPage.clickToPersonalAreaBtn();

        PersonalAreaPage personalArea = new PersonalAreaPage(driver);
        Assert.assertEquals("Профиль",  personalArea.personalAreaProfileBtn());
    }

    @Test
    @DisplayName("Переход в личный кабинет - по кнопке конструктор")
    public void checkPersonalAreaFromConstructor() {
        getStarted();

        MainPage mainPage = new MainPage(driver);
        mainPage.waitLoadMainPages();
        mainPage.clickToPersonalAreaBtn();

        AuthorizationPage authorization = new AuthorizationPage(driver);
        authorization.getAuthorization(email, password);
        mainPage.clickToPersonalAreaBtn();

        PersonalAreaPage personalArea = new PersonalAreaPage(driver);
        personalArea.clickToConstructorBtn();

        Assert.assertEquals("Соберите бургер", mainPage.getTextHeaderConstructor());
    }

    @Test
    @DisplayName("Переход в личный кабинет - по нажатию на логотип")
    public void checkPersonalAreaFormLogo() {
        getStarted();

        MainPage mainPage = new MainPage(driver);
        mainPage.waitLoadMainPages();
        mainPage.clickToPersonalAreaBtn();

        AuthorizationPage authorization = new AuthorizationPage(driver);
        authorization.getAuthorization(email, password);
        mainPage.clickToPersonalAreaBtn();

        PersonalAreaPage personalArea = new PersonalAreaPage(driver);
        personalArea.clickToLogoBtn();

        Assert.assertEquals("Соберите бургер", mainPage.getTextHeaderConstructor());
    }

    @Test
    @DisplayName("Переход в личный кабинет - нажатие на кнопку Выход")
    public void checkPersonalAreaSignOut() {
        getStarted();

        MainPage mainPage = new MainPage(driver);
        mainPage.waitLoadMainPages();
        mainPage.clickToPersonalAreaBtn();

        AuthorizationPage authorization = new AuthorizationPage(driver);
        authorization.getAuthorization(email, password);
        mainPage.clickToPersonalAreaBtn();

        PersonalAreaPage personalArea = new PersonalAreaPage(driver);
        personalArea.clickToSignOut();

        Assert.assertEquals("Войти", authorization.getTextAuthLoginBtn());
    }

    @After
    public void teardown() {
        driver.quit();
        getUserDelete(response.getAccessToken());
    }
}