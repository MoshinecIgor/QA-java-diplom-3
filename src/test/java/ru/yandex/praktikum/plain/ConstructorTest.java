package ru.yandex.praktikum.plain;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.WebDriverFactory;
import ru.yandex.praktikum.page.MainPage;

import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.TestUtils.MAIN_PAGE_URL;

@DisplayName("Раздел 'Конструктор'")
public class ConstructorTest {
    private WebDriver webDriver;
    private MainPage mainPage;

    @Before
    public void setup() {
        webDriver = WebDriverFactory.getDriver();
        webDriver.get(MAIN_PAGE_URL);
        mainPage = new MainPage(webDriver);
    }

    @Test//Проверь, что работают переходы к разделам:«Булки»,«Соусы»,«Начинки».
    @Description("В один тест проверку засуну, вызываю зважды клик на Булки чтобы проверить что скролл вверх тоже работает и секция булок успешно появляется")
    @DisplayName("Работают переходы к разделам:«Булки»,«Соусы»,«Начинки»")
    public void testCheckTransitionToIngridientsSections() {
        mainPage.isBulkSuccessful();
        mainPage.clickButtonSauces();
        mainPage.isSaucesSuccessful();
        mainPage.clickButtonFillings();
        mainPage.isFillingsSuccessful();
        mainPage.clickButtonBulk();
        assertTrue(mainPage.isBulkSuccessful());

    }
    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
