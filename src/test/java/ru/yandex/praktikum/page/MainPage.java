package ru.yandex.praktikum.page;


import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofSeconds;

public class MainPage {
    private final WebDriver webDriver;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //Локаторы на главной странице
    private final By loginSuccessLocator = By.xpath("//h1[text()='Соберите бургер']");
    // Локатор надписи "Соберите бургер"
    private final By loginButtonMainPageLocator = By.xpath("//button[text()='Войти в аккаунт']");
    //Локатор кнопки "Войти в аккаунт"
    private final By buttonCheckout = By.xpath("//button[text()='Оформить заказ']");
    //Локатор кнопки "Оформить заказ"
    private final By personalAreaButtonLocator = By.xpath("//p[text()='Личный Кабинет']");
    //локатор кнопки личный кабинет
    private final By buttonBulkLocator = By.xpath("//div[span[text()='Булки']]");
    //локатор кнопки "Булки" в хэдере ингридиентов
    private final By buttonSaucesLocator = By.xpath("//div[span[text()='Соусы']]");
    //локатор кнопки "Соусы" в хэдере ингридиентов
    private final By buttonFillingsLocator = By.xpath("//div[span[text()='Начинки']]");
    //локатор кнопки "Начинки" в хэдере ингридиентов
    private final By sectionSelectedLocator = By.xpath("//div[contains(@class, 'tab_tab__1SPyG tab_tab_type_current__2BEPc')]");
    //Локатор выбранного раздела

    //локатор раздела ингридиентов "Начинки"
    @Step("Проверка успешного логина")
    public boolean isLoginSuccessful() {
        WebDriverWait wait = new WebDriverWait(webDriver, ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginSuccessLocator)).isDisplayed();
    }

    @Step("Нажимаем кнопку Войти на главной странице")
    public void clickLoginButtonMainPage() {
        webDriver.findElement(loginButtonMainPageLocator).click();
    }

    @Step("Нажимаем кнопку Личный кабинет")
    public void clickPersonalAreaButtonLocator() {
        webDriver.findElement(personalAreaButtonLocator).click();
    }

    @Step("Нажимаем кнопку Булки")
    public void clickButtonBulk() {
        webDriver.findElement(buttonBulkLocator).click();
    }

    @Step("Нажимаем кнопку Соусы")
    public void clickButtonSauces() {
        webDriver.findElement(buttonSaucesLocator).click();
    }

    @Step("Нажимаем кнопку Начинки")
    public void clickButtonFillings() {
        webDriver.findElement(buttonFillingsLocator).click();
    }

    @Step("Проверяем успешность отображения секции Булки")
    public boolean isBulkSuccessful() {
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(sectionSelectedLocator));
        Assert.assertEquals("Булки", element.getText());
        return element.isDisplayed();
    }

    @Step("Проверяем успешность отображения секции Соусы")
    public boolean isSaucesSuccessful() {
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(sectionSelectedLocator));
        Assert.assertEquals("Соусы", element.getText());
        return element.isDisplayed();
    }

    @Step("Проверяем успешность отображения секции Начинки")
    public boolean isFillingsSuccessful() {
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(sectionSelectedLocator));
        Assert.assertEquals("Начинки", element.getText());
        return element.isDisplayed();
    }

    @Step("Проверяем успешность отображения кнопки Оформить заказ")
    public boolean isButtonCheckout() {
        WebDriverWait wait = new WebDriverWait(webDriver, ofSeconds(2));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(buttonCheckout)).isDisplayed();
    }
}
