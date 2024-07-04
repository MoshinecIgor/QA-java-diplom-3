package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebDriverFactory {

    public static WebDriver getDriver() {
        Properties properties = new Properties();
        try (InputStream resourceAsStream = WebDriverFactory.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (resourceAsStream == null) {
                throw new RuntimeException("config.properties file not found in classpath");
            }
            properties.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String browser = properties.getProperty("browser");
        WebDriver driver;
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver");
                driver = new ChromeDriver();
                break;
            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }
        return driver;
    }
}