package site.nomoreparties.stellarburgers.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverConstructor {
    private String browserName;

    public WebDriverConstructor() {
        this.browserName = System.getProperty("browser");
    }

    // Команда запуска тестов в Яндекс браузере mvn clean test -Dbrowser=yandex
    public WebDriver getWebDriver() {
        if (browserName == null) {
            browserName = "chrome";
        }
        WebDriver driver;
        switch (browserName) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "/Users/danil/WebDriver/bin/chromedriver.exe");
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                break;
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "/Users/danil/WebDriver/bin/yandexdriver.exe");
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                break;
            default:
                throw new RuntimeException("Некорректный браузер");
        }
        return driver;
    }
}

