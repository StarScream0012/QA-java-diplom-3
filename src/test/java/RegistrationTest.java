import api.ApiHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.LoginPage;
import pageObject.RegistrationPage;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class RegistrationTest {
    private WebDriver driver;
    private LoginPage objLoginPage;
    private RegistrationPage objRegPage;
    @Before
    public void setUp(){
        Browser browser=new Browser();
        driver = browser.getWebDriver("yandex");
        // переход на страницу тестового приложения
        driver.get("https://stellarburgers.nomoreparties.site/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        objLoginPage=new LoginPage(driver);
        objRegPage = new RegistrationPage(driver);
    }
    @Test
    public void registrationTest(){
        objLoginPage.goToAuthByAccountButton();
        objLoginPage.openRegistrationPage();
        HashMap<String,String> userData= ApiHelper.generateRegitrationData(true);
        objRegPage.enterCredentials(userData);
        objRegPage.verifyRegistered();

        ApiHelper.deleteUser(userData);
    }
    @Test
    public void registrationInvalidPasswordTest(){
        objLoginPage.goToAuthByAccountButton();
        objLoginPage.openRegistrationPage();
        HashMap<String,String> userData=ApiHelper.generateRegitrationData(false);
        objRegPage.enterCredentials(userData);
        objRegPage.checkInvalidPasswordMessageAppears();
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
