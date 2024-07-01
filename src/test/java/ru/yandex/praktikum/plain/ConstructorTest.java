package ru.yandex.praktikum.plain;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.WebDriverFactory;
import ru.yandex.praktikum.page.MainPage;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
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

    @Test
    @Description("Я сперва перехожу к разделу начинок так как по умолчанию и так выбрана секция булок, а нужно проверить именно переход. Ставим задержку чтобы успела отработать анимация")
    @DisplayName("Работает переход к разделам:«Булки»")
    public void testCheckTransitionToBulkSection() throws InterruptedException {
        mainPage.clickButtonFillings();
        sleep(1000);
        mainPage.clickButtonBulk();
        assertTrue(mainPage.isBulkSuccessful());
    }

    @Test
    @Description("Проверяем что переход к разделу 'Соусы' успешно работает")
    @DisplayName("Работает переход к разделам:«Соусы»")
    public void testCheckTransitionToSaucesSection() {
        mainPage.clickButtonSauces();
        assertTrue(mainPage.isSaucesSuccessful());
    }

    @Test
    @Description("Проверяем что переход к разделу 'Начинки' успешно работает")
    @DisplayName("Работает переход к разделам:«Начинки»")
    public void testCheckTransitionToFillingsSections() {
        mainPage.clickButtonFillings();
        assertTrue(mainPage.isFillingsSuccessful());
    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
