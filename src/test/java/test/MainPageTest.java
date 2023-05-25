package test;

import ui.pages.MainPage;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import io.qameta.allure.junit4.DisplayName;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class MainPageTest {

    private final String buns;
    private final String sauce;
    private final String filling;

    private final String URL = "https://stellarburgers.nomoreparties.site";

    public MainPageTest(String sauce, String filling, String buns) {
        this.buns = buns;
        this.sauce = sauce;
        this.filling = filling;
    }

    @Parameters(name = "Тестовые данные: {0}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Соусы", "Начинки", "Булки"},
        };
    }

    @Before
    public void setConfig() {
        getStarted("chrome");
    }

    private WebDriver driver;

    public void getStarted(String browserName) {
        if(browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if(browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if(browserName.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        } else {
            throw new IllegalArgumentException("Invalid browser name: " + browserName);
        }
        driver.get(URL);
    }

    @Test
    @DisplayName("Раздел «Конструктор» - переход по клику на категорию")
    public void checkConstructor() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitLoadMainPages();
        mainPage.selectCategories(filling);
        mainPage.selectCategories(sauce);
        mainPage.selectCategories(buns);
        Assert.assertEquals("Булки", mainPage.getTextNoActiveCategories());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}