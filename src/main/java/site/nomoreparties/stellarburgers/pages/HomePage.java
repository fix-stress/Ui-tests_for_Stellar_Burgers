package site.nomoreparties.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Главная страница
public class HomePage extends GeneralHeader {

    private final WebDriver driver;

    private static final String PAGE_URL = "https://stellarburgers.nomoreparties.site/";
    private static final By TITLE_ASSEMBLE_BURGER = By.xpath(".//h1[text()='Соберите бургер']");
    private static final By CHECK_OUT_ORDER_BUTTON = By.xpath(".//button[text() = 'Оформить заказ']");
    private static final By LOGIN_BUTTON = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private static final By BUNS_SECTION_IS_ACTIVE = By.xpath(".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text() = 'Булки']");
    private static final By SAUCES_SECTION_IS_ACTIVE = By.xpath(".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text() = 'Соусы']");
    private static final By FILINGS_SECTION_IS_ACTIVE = By.xpath(".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text() = 'Начинки']");
    private static final By BUNS_SECTION_IS_INACTIVE = By.xpath(".//div[@class = 'tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[text() = 'Булки']");
    private static final By SAUCES_SECTION_IS_INACTIVE = By.xpath(".//div[@class = 'tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[text() = 'Соусы']");
    private static final By FILINGS_SECTION_IS_INACTIVE = By.xpath(".//div[@class = 'tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[text() = 'Начинки']");

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Открыть главную страницу сервиса")
    public HomePage open() {
        driver.get(PAGE_URL);
        return this;
    }

    // Ожидание загрузки главной страницы сервиса
    public HomePage waitLoadHomePage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(TITLE_ASSEMBLE_BURGER));
        return this;
    }


    @Step("Кликнуть на кнопку <<Войти в аккаунт>> на главной странице")
    public AuthorizationFormPage clickLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
        return new AuthorizationFormPage(driver);
    }

    @Step("Проверить наличие кнопки <<Оформить заказ>> на главной странице, после авторизации")
    public boolean checkOutOrderButtonIsDisplayed() {
        return driver.findElement(CHECK_OUT_ORDER_BUTTON).isDisplayed();
    }

    @Step("Проверить наличие заголовка конструктора 'Соберите бургер', на главной странице сервиса")
    public boolean titleAssembleBurgerIsDisplayed() {
        return driver.findElement(TITLE_ASSEMBLE_BURGER).isDisplayed();
    }

    @Step("Кликнуть на раздел конструктора <Булки>")
    public HomePage clickBunSection() {
        driver.findElement(BUNS_SECTION_IS_INACTIVE).click();
        return this;
    }

    @Step("Кликнуть на раздел конструктора <Соусы>")
    public HomePage clickSauceSection() {
        driver.findElement(SAUCES_SECTION_IS_INACTIVE).click();
        return this;
    }

    @Step("Кликнуть на раздел конструктора <Начинки>")
    public HomePage clickFilingSection() {
        driver.findElement(FILINGS_SECTION_IS_INACTIVE).click();
        return this;
    }

    @Step("Убедиться, что после клика на вкладку <Булки> она изменила состояние на выбрана/активна")
    public boolean bunsSectionIsActive() {
        return driver.findElement(BUNS_SECTION_IS_ACTIVE).isDisplayed();
    }

    @Step("Убедиться, что после клика на вкладку <Соусы> она изменила состояние на выбрана/активна")
    public boolean saucesSectionIsActive() {
        return driver.findElement(SAUCES_SECTION_IS_ACTIVE).isDisplayed();
    }

    @Step("Убедиться, что после клика на вкладку <Начинки> она изменила состояние на выбрана/активна")
    public boolean filingsSectionIsActive() {
        return driver.findElement(FILINGS_SECTION_IS_ACTIVE).isDisplayed();
    }
}
