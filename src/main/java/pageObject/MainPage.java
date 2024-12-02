package pageObject;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;

public class MainPage {
    private WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    By constructorLink =  By.xpath("//p[contains(text(),'Конструктор')]");
    By bunsTab =  By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[1]");
    By sauceTab =  By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[2]");
    By fillingTab =  By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[3]");
    @Step
    public void clickConstructorLink(){
        driver.findElement(constructorLink).click();

    }
    @Step
    public void openBunsTab(){
        driver.findElement(bunsTab).click();

    }
    @Step
    public void openSauceTab(){
        driver.findElement(sauceTab).click();

    }
    @Step
    public void openFillingTab(){
        driver.findElement(fillingTab).click();

    }
    @Step("Проверка, что вкладка  Булки активна")
    public void checkBunsTabOpened(){
        String classes = driver.findElement(bunsTab).getAttribute("class");
        Assert.assertTrue(
                Arrays.asList(classes.split(" ")).contains("tab_tab_type_current__2BEPc"));

    }
    @Step("Проверка, что вкладка  Соусы активна")
    public void checkSauceTabOpened(){
        String classes = driver.findElement(sauceTab).getAttribute("class");
        Assert.assertTrue(
                Arrays.asList(classes.split(" ")).contains("tab_tab_type_current__2BEPc"));

    }
    @Step("Проверка, что вкладка  Булки активна")
    public void checkFillingTabOpened(){
        String classes = driver.findElement(fillingTab).getAttribute("class");
        Assert.assertTrue(
                Arrays.asList(classes.split(" ")).contains("tab_tab_type_current__2BEPc"));

    }

}