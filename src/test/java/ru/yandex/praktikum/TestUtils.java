package ru.yandex.praktikum;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import ru.yandex.praktikum.page.LoginPage;

import java.util.Random;

public class TestUtils {//Здесь описаны финальные переменные которые использу в тесте, генерация Почты и метод по удалению пользователя через токен, который мы получаем при авторизации
    public static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";
    public static final String REGISTRATION_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";
    public static final String FORGOT_PASSWORD_PAGE_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    public static final String TEST_EMAIL = "moshinec.igor@gmail.com";
    public static final String TEST_PASSWORD = "220791";
    public static final String TEST_NAME = "IgorTest";
    public static void login(LoginPage loginPage) {
        loginPage.enterEmail(TEST_EMAIL);
        loginPage.enterPassword(TEST_PASSWORD);
        loginPage.loginButton();
    }
    public static String generateUniqueEmail() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder emailPrefix = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            emailPrefix.append(characters.charAt(random.nextInt(characters.length())));
        }
        return "testuser" + emailPrefix.toString() + "@example.com";
    }
    public static void deleteUser(String token) {
        Response response = RestAssured.given()
                .header("Authorization", token)
                .delete("https://stellarburgers.nomoreparties.site/api/auth/user");

        int statusCode = response.getStatusCode();
        if (statusCode != 200 && statusCode != 202) {
            throw new AssertionError("Expected status code <200 or 202> but was <" + statusCode + ">");
        }
    }
}
