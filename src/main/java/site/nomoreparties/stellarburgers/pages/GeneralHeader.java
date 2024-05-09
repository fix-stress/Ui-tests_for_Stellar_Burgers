package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GeneralHeader {
    private final WebDriver driver;
    protected static final By CONSTRUCTOR_BUTTON = By.xpath(".//p[text() = 'Конструктор']");
    protected static final By LOGO = By.xpath(".//div[@class = 'AppHeader_header__logo__2D0X2']");
    protected static final By PERSONAL_ACCOUNT_BUTTON = By.xpath(".//p[text()='Личный Кабинет']");

    public GeneralHeader(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Кликнуть на кнопку <<Личный кабинет>> в хедере(пользователь авторизован)")
    public PersonalAccountPage clickPersonalAccountButtonWithAuthorization() {
        driver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
        return new PersonalAccountPage(driver);
    }

    @Step("Кликнуть на кнопку <<Личный кабинет>> в хедере(пользователь не авторизован )")
    public AuthorizationFormPage clickPersonalAccountButtonWithoutAuthorization() {
        driver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
        return new AuthorizationFormPage(driver);
    }

    @Step("Кликнуть на кнопку <<Конструктор>> в хедере")
    public HomePage clickConstructorButton() {
        driver.findElement(CONSTRUCTOR_BUTTON).click();
        return new HomePage(driver);
    }

    @Step("Кликнуть на логотип сервиса в хедере")
    public HomePage clickLogo() {
        driver.findElement(LOGO).click();
        return new HomePage(driver);
    }


}


