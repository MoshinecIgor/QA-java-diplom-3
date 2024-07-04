package ru.yandex.praktikum.plain;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.praktikum.TestUtils;
import ru.yandex.praktikum.WebDriverFactory;
import ru.yandex.praktikum.page.LoginPage;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.page.RegistrationPage;


import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.TestUtils.*;
@DisplayName("Регистрация")
public class RegistrationTest {
    private WebDriver webDriver;
    private RegistrationPage registrationPage;
    private MainPage mainPage;
    private String userToken;
    private LoginPage loginPage;
    private String userEmail;
    private static final Logger logger = LoggerFactory.getLogger(RegistrationTest.class);

    @Before
    public void setup() {
        webDriver = WebDriverFactory.getDriver();
        webDriver.get(REGISTRATION_PAGE_URL);
        registrationPage = new RegistrationPage(webDriver);
        mainPage = new MainPage(webDriver);
        loginPage = new LoginPage(webDriver);

    }

    @Test // Успешная регистрация
    @Description("Нужно проверить успешную регистрацию. Так как у нас нет доступа к бд, будем проверять что при логирования появляется кнопка Оформить заказ на странице конструктора")
    @DisplayName("Успешная регистрация")
    public void testSuccessfulRegistration() {
        userEmail = TestUtils.generateUniqueEmail();
        registrationPage.enterName(TEST_NAME);
        registrationPage.enterEmail(userEmail);
        registrationPage.enterPassword(TEST_PASSWORD);
        registrationPage.clickRegisterButton();
        assertTrue(registrationPage.isRegistrationSuccessful());
        loginPage.enterEmail(userEmail);
        loginPage.enterPassword(TEST_PASSWORD);
        loginPage.loginButton();
        assertTrue(mainPage.isButtonCheckout());
        userToken = registrationPage.getToken();
        logger.info("Registration and login successful. Token: " + userToken);
    }

    @Test // Ошибка для некорректного пароля. Минимальный пароль — шесть символов.
    @Description("Попробуем зарегистрироваться с паролем из пяти символов")
    @DisplayName("Ошибка для некорректного пароля. Минимальный пароль — шесть символов.")
    public void testInvalidRegistration() {
        String uniqueEmail = TestUtils.generateUniqueEmail();
        registrationPage.enterName(TEST_NAME);
        registrationPage.enterEmail(uniqueEmail);
        registrationPage.enterPassword("12345");
        registrationPage.clickRegisterButton();
        assertTrue(registrationPage.isPasswordErrorDisplayed());
    }


    @After
    public void tearDown() {
        if (userToken != null) {
            TestUtils.deleteUser(userToken);
            logger.info("User with token " + userToken + " has been deleted.");
        }
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
