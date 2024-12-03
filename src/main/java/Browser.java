import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Browser {
    public WebDriver getWebDriver(String browserName){
        ChromeOptions options = new ChromeOptions();
        //Run теста без запуска браузера
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        switch (browserName){
            default:
            case "chrome":
                return new ChromeDriver(options);
            case "yandex":
                System.setProperty("webdriver.chrome.driver", System.getenv("CHROMEDRIVER_PATH"));
                String yandexPath = System.getenv("YANDEX_BROWSER_PATH");
                options.setBinary(yandexPath);
                return new ChromeDriver(options);
        }

    }
}
