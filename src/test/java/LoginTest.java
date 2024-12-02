import api.ApiHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.AccountPage;
import pageObject.LoginPage;
import pageObject.RegistrationPage;
import pageObject.ResetPasswordPage;

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
        driver = browser.getWebDriver("yandex");
        // переход на страницу тестового приложения
        driver.get("https://stellarburgers.nomoreparties.site/");

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        objLoginPage=new LoginPage(driver);
        objRegPage = new RegistrationPage(driver);
        objResetPassPage = new ResetPasswordPage(driver);
        objAccountPage = new AccountPage(driver);
        userData= ApiHelper.getNewUser();
    }
    @Test
    public void loginWithAuthButton(){
        objLoginPage.clickAuthButton();
        loginAndCheckLoggedIn();
    }
    @Test
    public void loginWithAccountButton(){
        objLoginPage.goToAuthByAccountButton();
        loginAndCheckLoggedIn();
    }
    @Test
    public void loginWithAuthLinkFromRegPage(){
        objLoginPage.goToAuthByAccountButton();
        objLoginPage.openRegistrationPage();
        objRegPage.clickAuthLink();
        loginAndCheckLoggedIn();
    }
    @Test
    public void loginWithAuthLinkFromResetPasswordPage(){
        objLoginPage.goToAuthByAccountButton();
        objLoginPage.openResetPasswordPage();
        objResetPassPage.clickAuthLink();
        loginAndCheckLoggedIn();
    }
    @Test
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
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/login"));
    }
    @After
    public void deleteUser(){
        ApiHelper.deleteUser(userData);
        driver.quit();
    }
}
