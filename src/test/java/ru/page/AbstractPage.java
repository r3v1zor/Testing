package ru.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
    @FindBy(xpath = "//a[contains(@href, '/login')]")
    protected WebElement signInButton;

    @FindBy(xpath = "button._1FEpprw_Km")
    protected WebElement myProfileButton;

    protected EventFiringWebDriver driver;
    protected WebDriverWait wait;

    public AbstractPage(EventFiringWebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30, 100);
        PageFactory.initElements(driver, this);
    }

    @Step(value = "Авторизация...")
    public void auth(String login, String password) {
        clickOnMyProfile();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(login, password);
    }

    @Step(value = "Нажимаем на 'Мой профиль'")
    public void clickOnMyProfile() {
        wait.until(ExpectedConditions.visibilityOf(signInButton))
                .click();
    }
}
