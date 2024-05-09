package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Страница формы регистрации
public class RegistrationFormPage extends GeneralHeader {
    private final WebDriver driver;
    private static final By TITLE_REGISTRATION = By.xpath(".//h2[text()='Регистрация']");
    private static final By NAME_FIELD = By.xpath(".//label[text()='Имя']/../input[@class='text input__textfield text_type_main-default']");
    private static final By EMAIL_FIELD = By.xpath(".//label[text()='Email']/../input[@class='text input__textfield text_type_main-default']");
    private static final By PASSWORD_FIELD = By.xpath(".//label[text()='Пароль']/../input[@class='text input__textfield text_type_main-default']");
    private static final By REGISTER_BUTTON = By.xpath(".//button[text()='Зарегистрироваться']");
    private static final By LOGIN_LINK = By.xpath(".//a[text() = 'Войти']");
    private static final By ERROR_MESSAGE_PASSWORD_FIELD = By.xpath(".//p[text() = 'Некорректный пароль']");

    public RegistrationFormPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Ожидание загрузки страницы формы регистрации
    public RegistrationFormPage waitLoadRegistrationPage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(TITLE_REGISTRATION));
        return this;
    }

    @Step("Заполнить поле Имя в форме регистрации")
    public RegistrationFormPage fillInTheNameField(String name) {
        driver.findElement(NAME_FIELD).sendKeys(name);
        return this;
    }

    @Step("Заполнить поле Email в форме регистрации")
    public RegistrationFormPage fillInTheEmailField(String email) {
        driver.findElement(EMAIL_FIELD).sendKeys(email);
        return this;
    }

    @Step("Заполнить поле Пароль в форме регистрации")
    public RegistrationFormPage fillInThePasswordField(String password) {
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        return this;
    }

    @Step("Кликнуть на кнопку <<Зарегистрироваться>>")
    public AuthorizationFormPage clickRegisterButton() {
        driver.findElement(REGISTER_BUTTON).click();
        return new AuthorizationFormPage(driver);
    }

    @Step("Кликнуть на ссылку <Войти>")
    public AuthorizationFormPage clickLoginLink() {
        driver.findElement(LOGIN_LINK).click();
        return new AuthorizationFormPage(driver);
    }

    @Step("Проверить наличие ошибки 'Некорректный пароль', в форме регистрации пользователя")
    public boolean errorMessagePasswordFieldIsDisplayed() {
        clickRegisterButton(); // Снять фокус с поля кликом на кнопку <<Зарегистрироваться>>, после ввода некорректного пароля
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE_PASSWORD_FIELD)); // Добавил ожидание, потому что иногда тест падал, не находя ошибку
        return driver.findElement(ERROR_MESSAGE_PASSWORD_FIELD).isDisplayed();
    }
}
