package ru.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;

    @FindBy(css = ".pFhTbV17qj")
    private WebElement signInButton;


    @FindBy(css = "._2SFylIV5m5 > div:nth-child(2) > span:nth-child(1)")
    private WebElement loginSpan;

    @FindBy(css = ".EsYwYP7LNa > span:nth-child(1) > span:nth-child(2)")
    private WebElement citySpan;

    @FindBy(css = "._2_45qYuGVN ._2JDvXzYsUI")
    private WebElement cityInputForm;

    @FindBy(id = "react-autowhatever-region--item-0")
    private WebElement cityItem;

    @FindBy(css = "div.A6Py--JRNc:nth-child(2) > button:nth-child(1)")
    private WebElement acceptButton;

    @FindBy(css = "ul.T3jKK6NbAR:nth-child(4) > ul:nth-child(1) > li:nth-child(3) > a:nth-child(1)")
    private WebElement settingsButton;

    private int index = 0;

    public MainPage() {
    }

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    public LoginPage getLoginPage() {
        new WebDriverWait(driver, 10, 100)
                .until(ExpectedConditions.elementToBeClickable(signInButton))
                .click();

        return new LoginPage(driver);
    }

    public String getSignInButtonText() {
        new WebDriverWait(driver, 10, 100)
                .until(ExpectedConditions.visibilityOf(signInButton));

        return signInButton.getText();
    }

    public void clickOnMyProfile() {
        new WebDriverWait(driver, 10, 100)
                .until(ExpectedConditions.elementToBeClickable(signInButton))
                .click();
    }

    public boolean isLoginVisible() {
        new WebDriverWait(driver, 10, 100)
                .until(ExpectedConditions.visibilityOf(loginSpan));

        return loginSpan.isDisplayed();
    }

    public SettingsPage clickOnSettings() {
        new WebDriverWait(driver, 10, 100)
                .until(ExpectedConditions.elementToBeClickable(settingsButton))
                .click();

        return new SettingsPage(driver);
    }

    public void changeCity(String city) {
        new WebDriverWait(driver, 10, 100)
                .until(ExpectedConditions.elementToBeClickable(citySpan))
                .click();

        new WebDriverWait(driver, 10, 100)
                .until(ExpectedConditions.visibilityOf(cityInputForm))
                .sendKeys(Keys.chord(Keys.CONTROL + "a" + Keys.DELETE));

        new WebDriverWait(driver, 10, 100)
                .until(
                        (ExpectedCondition<Boolean>) driver -> {
                            cityInputForm.sendKeys(String.valueOf(city.charAt(index++)));
                            return cityItem.getText().startsWith(city);
                        }
                );

        cityItem.click();

        new WebDriverWait(driver, 10, 100)
                .until(ExpectedConditions.elementToBeClickable(acceptButton))
                .click();

    }

    public String getSpanCityText() {
        new WebDriverWait(driver, 10, 100)
                .until(ExpectedConditions.elementToBeClickable(citySpan));

        return citySpan.getText();
    }
}
