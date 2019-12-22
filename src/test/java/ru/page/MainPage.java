package ru.page;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.ScreenshotUtils;

import javax.annotation.Nullable;

public class MainPage extends AbstractPage {
    @FindBy(css = "._1FEpprw_Km")
    private WebElement menu;

    @FindBy(css = "._2SFylIV5m5 > div:nth-child(2) > span:nth-child(1)")
    private WebElement login;

    @FindBy(xpath = "//span[@data-auto = 'region-form-opener']")
    private WebElement citySpan;

    @FindBy(css = "._2_45qYuGVN ._2JDvXzYsUI")
    private WebElement cityInputForm;

    @FindBy(id = "react-autowhatever-region--item-0")
    private WebElement cityItem;

    @FindBy(css = "div.A6Py--JRNc:nth-child(2) > button:nth-child(1)")
    private WebElement acceptButton;

    @FindBy(xpath = "//a[@href='/my/settings?track=menu']")
    private WebElement settingsButton;

    @FindBy(css = "._301_b-LBxR button")
    private WebElement catalog;

    @FindBy(xpath = "//a[contains(@title, 'Красота и гигиена')]")
    private WebElement beautyAndHygiene;

    @FindBy(xpath = "//a[contains(@title, 'Зубные щетки')]")
    private WebElement toothBrushes;

    private int index = 0;

    public MainPage(EventFiringWebDriver driver) {
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

    @Step("Проверяем, что логин отображается")
    public void isLoginVisible() {
        wait.until(ExpectedConditions.visibilityOf(login));
        JavascriptExecutor js = driver;
        js.executeScript("arguments[0].style.backgroundColor = '#c3c327'", login);
        ScreenshotUtils.takesScreenshot("login", driver);
        Assert.assertTrue(login.isDisplayed());
    }

    @Step(value = "Заходим в настройки")
    public SettingsPage clickOnSettings() {
        wait.until(ExpectedConditions.elementToBeClickable(settingsButton))
                .click();

        return new SettingsPage(driver);
    }

    public void moveCursorOnProfile() {
        wait.until(ExpectedConditions.visibilityOf(myProfileButton));
        wait.until((ExpectedCondition<Boolean>) webDriver -> {
            new Actions(driver).moveToElement(menu).build().perform();
            return settingsButton.isDisplayed();
        });

    }

    @Step(value = "Меняем город на '{city}'")
    public void changeCity(String city) {
        clickOnChangeCitySpan();
        deleteCityFromInput();
        enterCityName(city);
        clickOnElementInCityList();
        clickOnAcceptButton();
    }

    @Step(value = "Нажимаем на кнопку смены города")
    private void clickOnChangeCitySpan() {
        wait.until(ExpectedConditions.elementToBeClickable(citySpan))
                .click();
    }

    @Step(value = "Удаляем город из поля для ввода текста")
    private void deleteCityFromInput() {
        wait.until(ExpectedConditions.visibilityOf(cityInputForm))
                .sendKeys(Keys.chord(Keys.CONTROL + "a" + Keys.DELETE));
    }

    @Step(value = "Вводим по букве из города, пока он не появится в списке")
    private void enterCityName(String city) {
        wait.until((ExpectedCondition<Boolean>) driver -> {
            cityInputForm.sendKeys(String.valueOf(city.charAt(index++)));
            return cityItem.getText().startsWith(city);
        });
    }

    @Step(value = "Выбираем нужный город")
    private void clickOnElementInCityList() {
        cityItem.click();
    }

    @Step(value = "Нажимаем на кнопку 'Хорошо'")
    private void clickOnAcceptButton() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptButton)).click();
    }

    @Step(value = "Кликаем по каталогу")
    public void clickOnCatalog() {
        wait.until(ExpectedConditions.elementToBeClickable(catalog))
                .click();
    }

    @Step(value = "Переходим на страницу 'Красота и гигиена'")
    public BeautyAndHygienePage goToBeautyAndHygienePage() {
        wait.until(ExpectedConditions.elementToBeClickable(beautyAndHygiene)).click();
        return new BeautyAndHygienePage(driver);
    }

    @Step(value = "Получаем значение из кнопки смены города")
    public String getSpanCityText(String city) {
        wait.until(ExpectedConditions.textToBePresentInElement(citySpan, city));
        JavascriptExecutor js = driver;
        js.executeScript("arguments[0].style.backgroundColor = '#c3c327'", signInButton);
        ScreenshotUtils.takesScreenshot("city", driver);
        return citySpan.getText();
    }

    @Step(value = "Проверяем, что кнопка 'Войти' сменилась на 'Мой профиль'")
    public void isSignInButtonChangeOnMyProfile() {
        JavascriptExecutor js = driver;
        js.executeScript("arguments[0].style.backgroundColor = '#c3c327'", signInButton);
        ScreenshotUtils.takesScreenshot("My Profile", driver);
        Assert.assertEquals(getSignInButtonText(), "Мой профиль");
    }
}
