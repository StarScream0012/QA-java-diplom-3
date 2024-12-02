package pageObject;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;

public class LoginPage {
    private WebDriver driver;
    By accountButton= By.xpath("//p[contains(text(),'Личный Кабинет')]");
    By emailInput = By.xpath("//input[@name='name']");
    By passwordInput = By.xpath("//input[@name='Пароль']");
    By loginButton =  By.xpath("//button[contains(text(),'Войти')]");
    By registrationLink = By.xpath("//a[contains(text(),'Зарегистрироваться')]");
    By resetPasswordLink = By.xpath("//a[contains(text(),'Восстановить пароль')]");
    By authButton =  By.xpath("//button[contains(text(),'Войти в аккаунт')]");

    public LoginPage(WebDriver driver) {

            this.driver = driver;
    }
    @Step("Переход на форму авторизации через кнопку 'Личный кабинет'")
    public void goToAuthByAccountButton(){
        driver.findElement(accountButton).click();
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals("https://stellarburgers.nomoreparties.site/login",currentURL);
    }
    @Step("Переход в профиль")
    public void goToProfileByAccountButton(){
        driver.findElement(accountButton).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/account"));
    }
    @Step("Переход на форму авторизации через кнопку 'Войти в аккаунт'")
    public void clickAuthButton(){
        driver.findElement(authButton).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/login"));

    }
    @Step("Переход на форму регистрации")
    public void openRegistrationPage(){
        driver.findElement(registrationLink).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/register"));
    }
    @Step("Переход на форму регистрации")
    public void openResetPasswordPage(){
        driver.findElement(resetPasswordLink).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/forgot-password"));
    }
    @Step("Переход на форму авторизации через кнопку 'Войти в аккаунт'")
    public void login(HashMap<String,String> userData){
        driver.findElement(emailInput).sendKeys(userData.get("email"));
        driver.findElement(passwordInput).sendKeys(userData.get("password"));
        driver.findElement(loginButton).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));

    }

}
