package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Страница формы восстановления пароля
public class PasswordRecoveryFormPage extends GeneralHeader {
    private final WebDriver driver;
    private static final By LOGIN_LINK = By.xpath(".//a[text() = 'Войти']");

    public PasswordRecoveryFormPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Кликнуть на ссылку <Войти>")
    public AuthorizationFormPage clickLoginLink() {
        driver.findElement(LOGIN_LINK).click();
        return new AuthorizationFormPage(driver);
    }
}
