package ru.page;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.ScreenshotUtils;

public class SettingsPage extends AbstractPage {
    @FindBy(css = "span._3ioN70chUh > span:nth-child(1)")
    private WebElement myCity;

    public SettingsPage(EventFiringWebDriver driver) {
        super(driver);
    }

    @Step(value = "Получаем значение города")
    public String getMyCityText() {
        JavascriptExecutor js = driver;
        js.executeScript("arguments[0].style.backgroundColor = '#c3c327'", myCity);
        ScreenshotUtils.takesScreenshot("city", driver);
        return wait.until(ExpectedConditions.visibilityOf(myCity))
                .getText();
    }

    @Step(value = "Проверяем равенство города на главной странице и в настройках")
    public void checkEqualityWithMainPageCity(String mainPageCityText) {
        String cityText = getMyCityText();
        Assert.assertEquals(cityText, mainPageCityText);
    }
}
