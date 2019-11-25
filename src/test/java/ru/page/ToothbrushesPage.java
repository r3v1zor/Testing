package ru.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ToothbrushesPage {
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

    private WebDriver driver;
    private WebDriverWait wait;

    public ToothbrushesPage(WebDriver driver) throws InterruptedException {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void setFilter(Integer prFrom, Integer prTo) {

        wait.until(ExpectedConditions.visibilityOf(priceFrom))
                .sendKeys(prFrom.toString());

        wait.until(ExpectedConditions.visibilityOf(priceTo))
                .sendKeys(prTo.toString());

        wait.until((ExpectedCondition<Boolean>) driver ->
                search.getAttribute("data-zone-data").contains(Integer.toString(24)));
    }

    public void checkToothBrushes(Integer prFrom, Integer prTo) {
        listOfToothBrushes.forEach(item -> {
            int price = Integer.parseInt(item.findElement(By.xpath(".//span[contains(@data-tid, 'c3eaad93')]")).getText().replaceAll("\\s+", ""));
            boolean condition = prFrom <= price && price <= prTo;
            Assert.assertTrue(condition);
        });
    }

    public void chooseToothBrush() {
        WebElement toothbrush = listOfToothBrushes.get(listOfToothBrushes.size() - 2);

        WebElement button = toothbrush.findElement(By.xpath(".//button"));
        button.click();

        wait.until(ExpectedConditions.stalenessOf(button));

        wait.until((ExpectedCondition<Boolean>) driver ->
                toothbrush.getAttribute("innerHTML").contains("В корзине"));
    }

    public CartPage goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cart))
                .click();

        return new CartPage(driver);
    }
}
