package test;

import ui.pages.MainPage;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import io.qameta.allure.junit4.DisplayName;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class MainPageTest {

    private final String nameFirst;
    private final String nameSecond;

    public MainPageTest(String nameFirst, String nameSecond) {
        this.nameFirst = nameFirst;
        this.nameSecond = nameSecond;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0},{1}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Начинки","Соусы"},
                {"Соусы","Булки"},
                {"Соусы","Начинки"}
        };
    }

    @Before
    public void setConfig() {
        WebDriverManager.chromedriver().setup();
    }

    private WebDriver driver;

    public void getStarted() {
        driver = new ChromeDriver();
        driver.get("https://stellarburgers.nomoreparties.site");
    }

    @Test
    @DisplayName("Раздел «Конструктор» - переход по клику на категорию")
    public void checkConstructor() {
        getStarted();

        MainPage mainPage = new MainPage(driver);
        mainPage.waitLoadMainPages();
        mainPage.selectConstructor(nameFirst);
        mainPage.selectConstructor(nameSecond);
        Assert.assertEquals(nameSecond, mainPage.getConstructorResultHeader(nameSecond));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}