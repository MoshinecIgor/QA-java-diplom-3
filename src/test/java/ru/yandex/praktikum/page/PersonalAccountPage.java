package ru.yandex.praktikum.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;
public class PersonalAccountPage {
    private final WebDriver webDriver;

    public PersonalAccountPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //Локаторы
    private final By successProfileLocator = By.xpath("//a[@href='/account/profile' and text()='Профиль']");
    //Локатор того что на странице отображается текст "Профиль"
    private final By buttonConstructor = By.xpath("//a[contains(@class, 'AppHeader_header__link__3D_hX') and descendant::p[text()='Конструктор']]");
    //Локатор кнопки Конструктор
    private final By buttonStellarBurgers = By.cssSelector("div.AppHeader_header__logo__2D0X2");
    //Локатор кнопки StellarBurgers
    private final By buttonLogout = By.xpath("//button[text()='Выход']");
    //Локатор кнопки Выход


    //Методы
    @Step("Проверка отображения профиля")
    public boolean isSuccessProfileLocator() {
        WebDriverWait wait = new WebDriverWait(webDriver, ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successProfileLocator)).isDisplayed();
    }
    @Step("Клик на кнопку 'Конструктор'")
    public void clickButtonConstuctor() {
        webDriver.findElement(buttonConstructor).click();
    }
    @Step("Клик на кнопку 'Stellar Burgers'")
    public void clickButtonStellarBurgers() {
        webDriver.findElement(buttonStellarBurgers).click();
    }
    @Step("Клик на кнопку 'Выход'")
    public void clickButtonLogout() {
        webDriver.findElement(buttonLogout).click();
    }
}
