import api.ApiHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.AccountPage;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegistrationPage;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class MainPageTest {
    private WebDriver driver;
    private MainPage objMainPage;
    @Before
    public void setUp(){
        Browser browser=new Browser();
        driver = browser.getWebDriver("yandex");
        // переход на страницу тестового приложения
        driver.get("https://stellarburgers.nomoreparties.site/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        objMainPage=new MainPage(driver);
    }
    @Test
    public void openBunsMenuTab(){
        objMainPage.clickConstructorLink();
        objMainPage.checkBunsTabOpened();

        objMainPage.openSauceTab();
        objMainPage.openBunsTab();
        objMainPage.checkBunsTabOpened();
    }
    @Test
    public void openSauceMenuTab(){
        objMainPage.clickConstructorLink();
        objMainPage.openSauceTab();
        objMainPage.checkSauceTabOpened();
    }
    @Test
    public void openFillingMenuTab(){
        objMainPage.clickConstructorLink();
        objMainPage.openFillingTab();
        objMainPage.checkFillingTabOpened();
    }
    @After
    public void quit(){
        driver.quit();
    }
}
