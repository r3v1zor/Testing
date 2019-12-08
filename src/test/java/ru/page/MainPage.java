package ru.page;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends AbstractPage {
    @FindBy(css = ".pFhTbV17qj")
    private WebElement signInButton;

    @FindBy(css = "._3odNv2Dw2n")
    private WebElement menu;

    @FindBy(css = "._2SFylIV5m5 > div:nth-child(2) > span:nth-child(1)")
    private WebElement login;

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

    @FindBy(css = "._301_b-LBxR button")
    private WebElement catalog;

    @FindBy(xpath = "//a[contains(@title, 'Красота и гигиена')]")
    private WebElement beautyAndHygiene;

    @FindBy(xpath = "//a[contains(@title, 'Зубные щетки')]")
    private WebElement toothBrushes;

    private int index = 0;

    public MainPage(WebDriver driver) {
        super(driver);
    }


    public LoginPage getLoginPage() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton))
                .click();

        return new LoginPage(driver);
    }

    public String getSignInButtonText() {
        wait.until(ExpectedConditions.visibilityOf(signInButton));

        return signInButton.getText();
    }

    public void clickOnMyProfile() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton))
                .click();
    }

    public void isLoginVisible() {
        wait.until(ExpectedConditions.visibilityOf(login));
        Assert.assertTrue(login.isDisplayed());
    }

    public SettingsPage clickOnSettings() {
        wait.until(ExpectedConditions.elementToBeClickable(settingsButton))
                .click();

        return new SettingsPage(driver);
    }

    public void moveCursorOnProfile() {
        wait.until(ExpectedConditions.and(ExpectedConditions.elementToBeClickable(signInButton),
                ExpectedConditions.visibilityOf(signInButton)));
        new Actions(driver).moveToElement(menu).perform();
        signInButton.click();
    }

    public void changeCity(String city) {
        wait.until(ExpectedConditions.elementToBeClickable(citySpan))
                .click();

        wait.until(ExpectedConditions.visibilityOf(cityInputForm))
                .sendKeys(Keys.chord(Keys.CONTROL + "a" + Keys.DELETE));

        wait.until((ExpectedCondition<Boolean>) driver -> {
            cityInputForm.sendKeys(String.valueOf(city.charAt(index++)));
            return cityItem.getText().startsWith(city);
        });

        cityItem.click();

        wait.until(ExpectedConditions.elementToBeClickable(acceptButton)).click();
    }

    public void clickOnCatalog() {
        wait.until(ExpectedConditions.elementToBeClickable(catalog))
                .click();
    }

    public BeautyAndHygienePage goToBeautyAndHygienePage() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(beautyAndHygiene)).click();

        return new BeautyAndHygienePage(driver);
    }


    public String getSpanCityText(String city) {
        wait.until(ExpectedConditions.textToBePresentInElement(citySpan, city));

        return citySpan.getText();
    }

    public void isSignInButtonChangeOnMyProfile() {
        Assert.assertEquals(getSignInButtonText(), "Мой профиль");
    }
}
