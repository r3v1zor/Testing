package ru.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "#passp-field-login")
    private WebElement loginForm;
    @FindBy(css = "#passp-field-passwd")
    private WebElement passwordForm;
    @FindBy(css = "button.button2_theme_action:nth-child(1)")
    private WebElement signInButton;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public MainPage login(String login, String password) {
        wait.until(ExpectedConditions.visibilityOf(loginForm))
                .sendKeys(login);

        wait.until(ExpectedConditions.elementToBeClickable(signInButton))
                .click();

        wait.until(ExpectedConditions.visibilityOf(passwordForm))
                .sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(signInButton))
                .click();

        return new MainPage(driver);
    }
}
