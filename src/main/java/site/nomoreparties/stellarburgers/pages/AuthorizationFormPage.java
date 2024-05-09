package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Страница формы авторизации
public class AuthorizationFormPage extends GeneralHeader {
    private final WebDriver driver;
    private static final By TITLE_LOGIN = By.xpath(".//h2[text() = 'Вход']");
    private static final By EMAIL_FIELD = By.xpath(".//label[text() = 'Email']/../input[@class='text input__textfield text_type_main-default']");
    private static final By PASSWORD_FIELD = By.xpath(".//label[text() = 'Пароль']/../input[@class='text input__textfield text_type_main-default']");
    private static final By REGISTER_LINK = By.xpath(".//a[text() = 'Зарегистрироваться']");
    private static final By PASSWORD_RECOVERY_LINK = By.xpath(".//a[text() = 'Восстановить пароль']");
    private static final By LOGIN_BUTTON = By.xpath(".//button[text() = 'Войти']");

    public AuthorizationFormPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Ожидание загрузки страницы авторизации
    public AuthorizationFormPage waitLoadPageAuthorizationForm() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(TITLE_LOGIN));
        return this;
    }

    @Step("Заполнить поле Email в форме авторизации")
    public AuthorizationFormPage fillInTheEmailField(String email) {
        driver.findElement(EMAIL_FIELD).sendKeys(email);
        return this;
    }

    @Step("Заполнить поле Пароль в форме авторизации")
    public AuthorizationFormPage fillInThePasswordField(String password) {
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        return this;
    }

    @Step("Кликнуть на кнопку <<Войти>>")
    public HomePage clickEnterButton() {
        driver.findElement(LOGIN_BUTTON).click();
        return new HomePage(driver);
    }

    @Step("Кликнуть на ссылку <Зарегистрироваться>")
    public RegistrationFormPage clickRegisterLink() {
        driver.findElement(REGISTER_LINK).click();
        return new RegistrationFormPage(driver);
    }

    @Step("Кликнуть на ссылку <Восстановить пароль>")
    public PasswordRecoveryFormPage clickPasswordRecoveryLink() {
        driver.findElement(PASSWORD_RECOVERY_LINK).click();
        return new PasswordRecoveryFormPage(driver);

    }

    @Step("Убедиться, что перешли на страницу авторизации пользователя")
    public boolean titleLoginIsDisplayed() {
        return driver.findElement(TITLE_LOGIN).isDisplayed();
    }


}
