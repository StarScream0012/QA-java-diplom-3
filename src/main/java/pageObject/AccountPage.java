package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {
    private WebDriver driver;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }
    By logoutButton =  By.xpath("//button[contains(text(),'Выход')]");

    @Step
    public void checkLogoutButtonIsDisplayed(){
        driver.findElement(logoutButton).isDisplayed();
    }
    @Step
    public void clickLogoutButton(){
        driver.findElement(logoutButton).click();
    }
}
