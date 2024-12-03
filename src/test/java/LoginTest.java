import api.ApiHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.object.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class LoginTest {
    private WebDriver driver;
    private LoginPage objLoginPage;
    private RegistrationPage objRegPage;
    private ResetPasswordPage objResetPassPage;
    private AccountPage objAccountPage;
    HashMap<String,String> userData;

    @Before
    public void setUp(){
        Browser browser=new Browser();
        String browserName = System.getenv("BROWSER");
        driver = browser.getWebDriver(browserName);
        // переход на страницу тестового приложения
        driver.get(PageURL.MAIN_URL);

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        objLoginPage=new LoginPage(driver);
        objRegPage = new RegistrationPage(driver);
        objResetPassPage = new ResetPasswordPage(driver);
        objAccountPage = new AccountPage(driver);
        userData= ApiHelper.getNewUser();
    }
    @Test
    @Description("Вход по кнопке 'Войти в аккаунт'")
    public void loginWithAuthButton(){
        objLoginPage.clickAuthButton();
        loginAndCheckLoggedIn();
    }
    @Test
    @Description("Вход по кнопке 'Личный кабинет'")
    public void loginWithAccountButton(){
        objLoginPage.goToAuthByAccountButton();
        loginAndCheckLoggedIn();
    }
    @Test
    @Description("Вход по ссылке 'Войти' на форме регистрации")
    public void loginWithAuthLinkFromRegPage(){
        objLoginPage.goToAuthByAccountButton();
        objLoginPage.openRegistrationPage();
        objRegPage.clickAuthLink();
        loginAndCheckLoggedIn();
    }
    @Test
    @Description("Вход по ссылке 'Войти' на форме восстановления пароля")
    public void loginWithAuthLinkFromResetPasswordPage(){
        objLoginPage.goToAuthByAccountButton();
        objLoginPage.openResetPasswordPage();
        objResetPassPage.clickAuthLink();
        loginAndCheckLoggedIn();
    }
    @Test
    @Description("Выход из аккаунта")
    public void logoutTest(){
        objLoginPage.goToAuthByAccountButton();
        loginAndCheckLoggedIn();
        objAccountPage.clickLogoutButton();
        checkLoggedOut();

    }
    @Step("Войти и проверить, что авторизация прошла успешно")
    public void loginAndCheckLoggedIn(){
        objLoginPage.login(userData);
        objLoginPage.goToProfileByAccountButton();
        objAccountPage.checkLogoutButtonIsDisplayed();

    }
    @Step("Проверка, что выход выполнен")
    public void checkLoggedOut()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(PageURL.LOGIN_URL));
    }
    @After
    @Step("Удалить пользователя")
    public void deleteUser(){
        ApiHelper.deleteUser(userData);
        driver.quit();
    }
}

