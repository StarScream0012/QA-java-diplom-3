package page.object;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResetPasswordPage {
    private WebDriver driver;
    public ResetPasswordPage(WebDriver driver) {
        this.driver = driver;
    }
    By authLink=  By.xpath("//a[contains(text(),'Войти')]");
    @Step("Переход на форму авторизации через ссылку 'Войти' на форме восстановления пароля")
    public void clickAuthLink(){
        driver.findElement(authLink).click();
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(PageURL.LOGIN_URL,currentURL);
    }
}
