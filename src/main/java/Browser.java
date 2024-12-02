import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Browser {
    public WebDriver getWebDriver(String browserName){
        ChromeOptions options = new ChromeOptions();
        //Run теста без запуска браузера
        // options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        switch (browserName){
            default:
            case "chrome":
                options.setBinary("C:/chrome2/chrome-win64/chrome.exe");
                return new ChromeDriver(options);
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe/");
                options.setBinary("C:/Users/Admin/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
                return new ChromeDriver(options);
        }

    }
}
