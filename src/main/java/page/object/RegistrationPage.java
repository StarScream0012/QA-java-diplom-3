package page.object;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;

public class RegistrationPage {
    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    By nameInput=By.xpath("//fieldset[1]//div[1]//div[1]//input[1]");
    By emailInput =By.xpath("//fieldset[2]//div[1]//div[1]//input[1]");
    By passwordInput =By.xpath("//fieldset[3]//div[1]//div[1]//input[1]");
    By registerButton =  By.xpath("//button[contains(text(),'Зарегистрироваться')]");
    By invalidPasswordMessage =  By.xpath("//p[contains(text(),'Некорректный пароль')]");
    By authLink=  By.xpath("//a[contains(text(),'Войти')]");
    @Step("Ввести данные в форму авторизации и нажать 'Зарегистрироваться'")
    public void enterCredentials(HashMap<String,String> creds){
        driver.findElement(nameInput).sendKeys(creds.get("name"));
        driver.findElement(emailInput).sendKeys(creds.get("email"));
        driver.findElement(passwordInput).sendKeys(creds.get("password"));
        driver.findElement(registerButton).click();
    }
    @Step("Переход на форму авторизации через ссылку 'Войти' на форме регистрации")
    public void clickAuthLink(){
        driver.findElement(authLink).click();
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(PageURL.LOGIN_URL,currentURL);
    }
    @Step("Проверка завершения регистрации")
    public void verifyRegistered(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.urlToBe(PageURL.LOGIN_URL));
    }
    @Step("Проверка, что отображается сообщения 'Некорректный пароль'")
    public void checkInvalidPasswordMessageAppears() {
        driver.findElement(invalidPasswordMessage).isDisplayed();
    }
}
