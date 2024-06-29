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
@DisplayName("Выход из аккаунта")
public class LogoutTest {
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
    @Test// выход по кнопке «Выйти» в личном кабинете.
    @Description("В личном кабинете есть кнопка Выйти. Нужно проверить её работу")
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете.")
    public void testLogout() {
        mainPage.clickLoginButtonMainPage();
        TestUtils.login(loginPage);
        mainPage.clickPersonalAreaButtonLocator();
        personalAccountPage.isSuccessProfileLocator();
        personalAccountPage.clickButtonLogout();
        assertTrue(loginPage.isLoginButtonSuccessful());
    }
    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
