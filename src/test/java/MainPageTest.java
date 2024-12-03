import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.object.MainPage;
import page.object.PageURL;

import java.util.concurrent.TimeUnit;

public class MainPageTest {
    private WebDriver driver;
    private MainPage objMainPage;
    @Before
    public void setUp(){
        Browser browser=new Browser();
        String browserName = System.getenv("BROWSER");
        driver = browser.getWebDriver(browserName);
        // переход на страницу тестового приложения
        driver.get(PageURL.MAIN_URL);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        objMainPage=new MainPage(driver);
    }
    @Test
    @Description("Открыть панель 'Булки'")
    public void openBunsMenuTab(){
        objMainPage.clickConstructorLink();
        objMainPage.checkBunsTabOpened();

        objMainPage.openSauceTab();
        objMainPage.openBunsTab();
        objMainPage.checkBunsTabOpened();
    }
    @Test
    @Description("Открыть панель 'Соусы'")
    public void openSauceMenuTab(){
        objMainPage.clickConstructorLink();
        objMainPage.openSauceTab();
        objMainPage.checkSauceTabOpened();
    }
    @Test
    @Description("Открыть панель 'Начинка'")
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
