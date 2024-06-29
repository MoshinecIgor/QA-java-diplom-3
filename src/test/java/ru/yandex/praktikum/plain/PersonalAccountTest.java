package ru.yandex.praktikum.plain;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.TestUtils;
import ru.yandex.praktikum.WebDriverFactory;
import ru.yandex.praktikum.page.*;

import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.TestUtils.MAIN_PAGE_URL;
@DisplayName("Переход в личный кабинет")
public class PersonalAccountTest {
    private WebDriver webDriver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private PersonalAccountPage personalAccountPage;

    @Before
    public void setup() {
        webDriver = WebDriverFactory.getDriver();
        webDriver.get(MAIN_PAGE_URL);
        mainPage = new MainPage(webDriver);
        loginPage = new LoginPage(webDriver);
        personalAccountPage = new PersonalAccountPage(webDriver);
    }

    @Test//Переход в личный кабинет:Проверь переход по клику на «Личный кабинет» когда пользователь авторизован
    @Description("В ТЗ страдает формулировка, по этому проверяем переход по клику на «Личный кабинет» когда пользователь авторизован")
    @DisplayName("Переход в личный кабинет")
    public void testGoToPersonalAccountPage() {
        mainPage.clickLoginButtonMainPage();
        TestUtils.login(loginPage);
        assertTrue(mainPage.isLoginSuccessful());
        mainPage.clickPersonalAreaButtonLocator();
        assertTrue(personalAccountPage.isSuccessProfileLocator());
    }
    @Test//переход по клику на «Конструктор».
    @Description("Нужно проверить что переход по клику на «Конструктор» работает и страница конструктора открвается")
    @DisplayName("Переход по клику на «Конструктор».")
    public void testGoToConstuctor() {
        mainPage.clickLoginButtonMainPage();
        TestUtils.login(loginPage);
        mainPage.clickPersonalAreaButtonLocator();
        personalAccountPage.clickButtonConstuctor();
        assertTrue(mainPage.isLoginSuccessful());
    }
    @Test//переход по клику на логотип Stellar Burgers.
    @Description("Нужно проверить что переход по клику на «Stellar Burgers» работает и страница конструктора открвается")
    @DisplayName("Переход по клику на логотип Stellar Burgers.")
    public void testGoToCStellarBurgers() {
        mainPage.clickLoginButtonMainPage();
        TestUtils.login(loginPage);
        mainPage.clickPersonalAreaButtonLocator();
        personalAccountPage.clickButtonStellarBurgers();
        assertTrue(mainPage.isLoginSuccessful());
    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
