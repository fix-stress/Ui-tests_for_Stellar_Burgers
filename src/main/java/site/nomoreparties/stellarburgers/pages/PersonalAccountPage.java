package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Страница личного кабинета
public class PersonalAccountPage extends GeneralHeader {
    private final WebDriver driver;

    private static final By USER_NAME_DATA = By.xpath(".//label[text() ='Имя']/../input[@class='text input__textfield text_type_main-default input__textfield-disabled']");
    private static final By USER_LOGIN_DATA = By.xpath(".//label[text() ='Логин']/../input[@class='text input__textfield text_type_main-default input__textfield-disabled']");
    private static final By SECTION_DESCRIPTION = By.xpath(".//p[text() = 'В этом разделе вы можете изменить свои персональные данные']");
    private static final By BLOCK_WITH_TABS_PROFILE_HISTORY_EXIT = By.xpath(".//ul[@class = 'Account_list__3KQQf mb-20']");
    private static final By EXIT_BUTTON = By.xpath(".//button[text() = 'Выход']");

    public PersonalAccountPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Ожидание загрузки страницы личного кабинета
    public PersonalAccountPage waitLoadPagePersonalAccount() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(SECTION_DESCRIPTION));
        return this;
    }

    @Step("Получить информацию об имени, из профиля зарегистрированного пользователя")
    public String getNameData() {
        return driver.findElement(USER_NAME_DATA).getAttribute("value");
    }

    @Step("Получить информации о логине(почте), из профиля зарегистрированного пользователя")
    public String getLoginData() {
        return driver.findElement(USER_LOGIN_DATA).getAttribute("value");
    }

    @Step("Убедиться, что перешли в личный кабинет пользователя")
    public boolean userProfileIsDisplayed() {
        return driver.findElement(BLOCK_WITH_TABS_PROFILE_HISTORY_EXIT).isDisplayed();
    }

    @Step("Кликнуть на кнопку <<Выход>>")
    public AuthorizationFormPage clickExitButton() {
        driver.findElement(EXIT_BUTTON).click();
        return new AuthorizationFormPage(driver);
    }
}
