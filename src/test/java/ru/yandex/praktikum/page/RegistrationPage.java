package ru.yandex.praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class RegistrationPage {
    private final WebDriver webDriver;

    public RegistrationPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    // Локаторы
    private final By nameInput = By.xpath("//input[@name='name']");
    // Локатор имени при регистрации
    private final By emailInput = By.xpath("//input[@name='name' and preceding-sibling::label[text()='Email']]");
    // Локатор почты при регистрации
    private final By passwordInput = By.xpath("//form[@class='Auth_form__3qKeq mb-20']//input[@name='Пароль']");
    // Локатор пароля при регистрации
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    // Локатор кнопки "Зарегистрироваться"
    private final By registrationSuccessLocator = By.xpath("//*[contains(text(),'Вход')]");
    // Локатор надписи "Вход"
    private final By passwordErrorPasswordLocator = By.xpath("//*[contains(text(),'Некорректный пароль')]");
    // Локатор ошибки для некорректного пароля

    private final By loginButtonRegistrationPage = By.xpath("//a[text()='Войти' and @href='/login']");
    //Локатор кнопки "Войти" на странице регистрации

    // Методы
    @Step("Ввод имени: {name}")
    public void enterName(String name) {
        webDriver.findElement(nameInput).sendKeys(name);
    }
    @Step("Ввод email: {email}")
    public void enterEmail(String email) {
        webDriver.findElement(emailInput).sendKeys(email);
    }
    @Step("Ввод пароля")
    public void enterPassword(String password) {
        webDriver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Нажатие на кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        webDriver.findElement(registerButton).click();
    }
    @Step("Проверка успешной регистрации")
    public boolean isRegistrationSuccessful() {
        WebDriverWait wait = new WebDriverWait(webDriver, ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(registrationSuccessLocator)).isDisplayed();
    }
    @Step("Проверка ошибки некорректного пароля")
    public boolean isPasswordErrorDisplayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordErrorPasswordLocator)).isDisplayed();
    }
    @Step("Нажатие на кнопку 'Войти' на странице регистрации")
    public void loginButtonRegistrationPage() {
        webDriver.findElement(loginButtonRegistrationPage).click();
    }
    @Step("Получение токена")
    public String getToken() {
        return (String) ((JavascriptExecutor) webDriver).executeScript("return localStorage.getItem('accessToken');");
    }
}
