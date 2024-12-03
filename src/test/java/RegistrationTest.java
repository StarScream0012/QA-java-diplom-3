import api.ApiHelper;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.object.LoginPage;
import page.object.PageURL;
import page.object.RegistrationPage;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class RegistrationTest {
    private WebDriver driver;
    private LoginPage objLoginPage;
    private RegistrationPage objRegPage;
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
    }
    @Test
    @Description("Проверка регистрации")
    public void registrationTest(){
        objLoginPage.goToAuthByAccountButton();
        objLoginPage.openRegistrationPage();
        HashMap<String,String> userData= ApiHelper.generateRegitrationData(true);
        objRegPage.enterCredentials(userData);
        objRegPage.verifyRegistered();

        ApiHelper.deleteUser(userData);
    }
    @Test
    @Description("Проверка регистрации с неправильным паролем")
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
