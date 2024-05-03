package site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.api.client.UserClient;
import site.nomoreparties.stellarburgers.api.generationtestdata.UserGenerator;
import site.nomoreparties.stellarburgers.api.pojo.User;
import site.nomoreparties.stellarburgers.api.pojo.UserCredentials;
import site.nomoreparties.stellarburgers.drivers.WebDriverConstructor;
import site.nomoreparties.stellarburgers.pages.HomePage;
import site.nomoreparties.stellarburgers.pages.PersonalAccountPage;
import site.nomoreparties.stellarburgers.pages.RegistrationFormPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DisplayName("Регистрация")
public class RegistrationTests {
    private WebDriver driver;
    private RegistrationFormPage registrationFormPage;
    private User user;
    private final static UserClient userClient = new UserClient();
    boolean skipDeleteUser = false;

    @Before
    public void setUp() {
        WebDriverConstructor webDriverConstructor = new WebDriverConstructor();
        driver = webDriverConstructor.getWebDriver();
        user = UserGenerator.randomUser();
        registrationFormPage = new HomePage(driver)
                .open()
                .waitLoadHomePage()
                .clickPersonalAccountButtonWithoutAuthorization()
                .clickRegisterLink();
    }

    @After
    public void tearDown() {
        driver.quit();
        if (!skipDeleteUser) {
            ValidatableResponse loginResponse = userClient.loginUser(UserCredentials.from(user));
            String accessToken = userClient.getAccessToken(loginResponse);
            userClient.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void successfulRegistrationTest() {
        PersonalAccountPage personalAccountPage = registrationFormPage
                .fillInTheNameField(user.getName())
                .fillInTheEmailField(user.getEmail())
                .fillInThePasswordField(user.getPassword())
                .clickRegisterButton()
                .waitLoadPageAuthorizationForm()
                .fillInTheEmailField(user.getEmail()) // Авторизация созданным пользователем
                .fillInThePasswordField(user.getPassword())
                .clickEnterButton()
                .waitLoadHomePage()
                .clickPersonalAccountButtonWithAuthorization()
                .waitLoadPagePersonalAccount();

        String actualName = personalAccountPage.getNameData(); // Проверили, что в личном кабинете отображается имя зарегистрированного пользователя
        assertEquals(user.getName(), actualName);
        String actualLogin = personalAccountPage.getLoginData(); // Проверили, что в личном кабинете отображается почта зарегистрированного пользователя
        assertEquals(user.getEmail(), actualLogin);
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля")
    @Description("Минимальный пароль 6 символов")
    public void errorWithInvalidPasswordTest() {
        user.setPassword(UserGenerator.invalidRandomPassword());
        boolean errorIsDisplayed = registrationFormPage
                .waitLoadRegistrationPage()
                .fillInTheNameField(user.getName())
                .fillInTheEmailField(user.getEmail())
                .fillInThePasswordField(user.getPassword())
                .errorMessagePasswordFieldIsDisplayed();
        assertTrue(errorIsDisplayed); // Проверили, что при вводе некорректного пароля отображается ошибка
        skipDeleteUser = true;
    }
}
