package ru.page;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ToothbrushesPage extends AbstractPage {
    @FindBy(xpath = "//span[@data-auto = 'filter-range-min']//input")
    private WebElement priceFrom;

    @FindBy(xpath = "//span[@data-auto = 'filter-range-max']//input")
    private WebElement priceTo;

    @FindBy(css = "._1uhsh_io8o ._1RjY7YIluf")
    private List<WebElement> listOfToothBrushes;

    @FindBy(css = ".preloadable__preloader.preloadable__preloader_visibility_visible.preloadable__paranja")
    private WebElement loadCurtain;

    @FindBy(css = ".n-pager")
    private WebElement searchStat;

    @FindBy(xpath = "//a[contains(@href, 'cart')]")
    private WebElement cart;

    @FindBy(xpath = "//div[@data-zone-name = 'SearchSerp']")
    private WebElement search;

    public ToothbrushesPage(EventFiringWebDriver driver) {
        super(driver);
    }

    @Step(value = "Устанавливаем значения фильтра")
    public void setFilter(Integer prFrom, Integer prTo) {
        setPriceFrom(prFrom);
        setPriceTo(prTo);
        waitUntilRefresh();
    }

    @Step(value = "Устанавливаем значение стартовой цены")
    private void setPriceFrom(Integer prFrom) {
        wait.until(ExpectedConditions.visibilityOf(priceFrom))
                .sendKeys(prFrom.toString());
    }

    @Step(value = "Ожидаем пока загрузится список щеток текущей цены")
    private void waitUntilRefresh() {
        wait.until((ExpectedCondition<Boolean>) driver ->
                search.getAttribute("data-zone-data").contains(Integer.toString(22)));
    }

    @Step(value = "Устанавливаем значение конечной цены")
    private void setPriceTo(Integer prTo) {
        wait.until(ExpectedConditions.visibilityOf(priceTo))
                .sendKeys(prTo.toString());
    }

    @Step(value = "Проверяем, что стоимость всех щеток находится в пределах выставленной цены")
    public void checkToothBrushes(Integer prFrom, Integer prTo) {
        listOfToothBrushes.forEach(item -> {
            int price = Integer.parseInt(item.findElement(By.xpath(".//span[contains(@data-tid, 'c3eaad93')]")).getText().replaceAll("\\s+", ""));
            boolean condition = prFrom <= price && price <= prTo;
            Assert.assertTrue(condition);
        });
    }

    @Step(value = "Выбираем предпоследнюю щетку из каталога")
    public void chooseToothBrush() {
        driver.executeScript("scrollTo(0, document.body.scrollHeight / 2)");
        WebElement toothbrush = listOfToothBrushes.get(12);//listOfToothBrushes.size() - 2);
        WebElement button = toothbrush.findElement(By.xpath(".//button"));
        button.click();

        wait.until(ExpectedConditions.stalenessOf(button));

        wait.until((ExpectedCondition<Boolean>) driver ->
                toothbrush.getAttribute("innerHTML").contains("В корзине"));
    }

    @Step(value = "Переходим в корзину")
    public CartPage goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cart))
                .click();

        return new CartPage(driver);
    }
}
