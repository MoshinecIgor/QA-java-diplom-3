package ru.yandex.praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private final WebDriver webDriver;

    public ForgotPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    //Локаторы

    private final By loginButton = By.xpath("//a[text()='Войти' and @href='/login']");
    //Локатор кнопки Войти в поле регистрации
    @Step("Нажатие на кнопку'Войти' на странице восстановления пароля")
    public void clickLoginButton() {
        webDriver.findElement(loginButton).click();
    }

}
