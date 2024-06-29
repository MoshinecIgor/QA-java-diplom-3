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
import static ru.yandex.praktikum.TestUtils.*;
@DisplayName("Вход")
public class LoginTest {
    private WebDriver webDriver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private ForgotPasswordPage forgotPasswordPage;

    @Before
    public void setup() {
        webDriver = WebDriverFactory.getDriver();
        webDriver.get(MAIN_PAGE_URL);
        mainPage = new MainPage(webDriver);
        loginPage = new LoginPage(webDriver);
        registrationPage = new RegistrationPage(webDriver);
        forgotPasswordPage = new ForgotPasswordPage(webDriver);
    }

    @Test //вход по кнопке «Войти в аккаунт» на главной
    @Description("На главной странице есть кнопка 'Войти в акканут', нужно проверить что она работает")
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной странице")
    public void testSuccessfulLoginButtonMainPage() {
        mainPage.clickLoginButtonMainPage();
        TestUtils.login(loginPage);
        assertTrue(mainPage.isButtonCheckout()); //isButtonCheckout()
    }
    @Test//вход через кнопку «Личный кабинет»
    @Description("Через кнопку Личный кабинет тоже есть возможность залогиниться, нужно это проверить")
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void testSuccessfulLoginMainPage() {
        mainPage.clickPersonalAreaButtonLocator();
        TestUtils.login(loginPage);
        assertTrue(mainPage.isButtonCheckout());
    }

    @Test//вход через кнопку в форме регистрации
    @Description("В форме регистрации есть кнопка Вход. Нужно проверить можно ли через неё залогиниться ")
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginFromRegistrationPage() {
        webDriver.get(REGISTRATION_PAGE_URL);
        registrationPage.loginButtonRegistrationPage();
        TestUtils.login(loginPage);
        assertTrue(mainPage.isButtonCheckout());
    }

    @Test//вход через кнопку в форме восстановления пароля
    @Description("В форме восстановления пароля есть кнопка Вход. Нужно проверить можно ли через неё залогиниться")
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginFromForgotPasswordPage() {
        webDriver.get(FORGOT_PASSWORD_PAGE_URL);
        forgotPasswordPage.clickLoginButton();
        TestUtils.login(loginPage);
        assertTrue(mainPage.isButtonCheckout());
    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

}
