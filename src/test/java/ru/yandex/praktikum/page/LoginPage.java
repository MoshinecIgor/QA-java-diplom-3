package ru.yandex.praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class LoginPage {
    private final WebDriver webDriver;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    //Локаторы
    private final By emailInput = By.xpath("//input[@name='name' and preceding-sibling::label[text()='Email']]");
    // Локатор почты при аутентификации
    private final By passwordInput = By.xpath("//form[@class='Auth_form__3qKeq mb-20']//input[@name='Пароль']");
    // Локатор пароля при аутентификации
    private final By loginButton = By.xpath("//button[text()='Войти']");
    // Локатор кнопки "Войти"

    //Методы
    @Step("Вводим email: {email}")
    public void enterEmail(String email) {
        webDriver.findElement(emailInput).sendKeys(email);
    }
    @Step("Вводим пароль")
    public void enterPassword(String password) {
        webDriver.findElement(passwordInput).sendKeys(password);
    }
    @Step("Нажимаем кнопку Войти")
    public void loginButton() {
        webDriver.findElement(loginButton).click();
    }
    @Step("Проверяем успешность нажатия кнопки Войти")
    public boolean isLoginButtonSuccessful() {
        WebDriverWait wait = new WebDriverWait(webDriver, ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).isDisplayed();
    }
}
