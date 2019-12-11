package ru.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.ScreenshotUtils;

public class LoginPage extends AbstractPage {
    @FindBy(css = "#passp-field-login")
    private WebElement loginForm;
    @FindBy(css = "#passp-field-passwd")
    private WebElement passwordForm;
    @FindBy(css = "button.button2_theme_action:nth-child(1)")
    private WebElement signInButton;

    public LoginPage(EventFiringWebDriver driver) {
        super(driver);
    }


    public MainPage login(String login, String password) {
        enterLoginInForm(login);
        clickOnSignInButton();
        enterPasswordInForm(password);
        clickOnSignInButton();
        return new MainPage(driver);
    }

    @Step(value = "Вводим логин")
    private void enterLoginInForm(String login) {
        wait.until(ExpectedConditions.visibilityOf(loginForm))
                .sendKeys(login);
    }

    @Step(value = "Нажимаем на кнопку 'Войти'")
    private void clickOnSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
    }

    @Step(value = "Вводим пароль")
    private void enterPasswordInForm(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordForm))
                .sendKeys(password);
    }
}
