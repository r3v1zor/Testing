package ru.page;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
    @FindBy(css = ".pFhTbV17qj")
    protected WebElement signInButton;

    protected WebDriver driver;
    protected WebDriverWait wait;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10, 100);
        PageFactory.initElements(driver, this);
    }

    @Step(value = "Авторизация...")
    public void auth(String login, String password) {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton))
                .click();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(login, password);
    }

    @Attachment(value = "Снимок ''{0}''", type = "image/png")
    public byte[] takeScreenShot(String value) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Снимок ''{0}''", type = "image/png")
    public byte[] takeScreenShot(String value, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('font', 'yellow')", element);

        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
