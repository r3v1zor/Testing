package ru.page;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutOrderPage extends AbstractPage {
    @FindBy(xpath = "//div[contains(@data-auto, 'DELIVERY')]")
    private WebElement expressDelivery;

    @FindBy(xpath = "//div[contains(@data-auto, 'total-price')]")
    private WebElement totalPrice;

    @FindBy(xpath = "//div[@data-auto = 'total-delivery']")
    private WebElement totalDelivery;

    @FindBy(xpath = "//div[@data-auto = 'total-items']")
    private WebElement totalItems;

    @FindBy(xpath = "//a[contains(@href, 'cart')]")
    private WebElement cart;

    @FindBy(xpath = "//div[@data-auto='total-discount']")
    private WebElement discount;

    @FindBy(xpath = "//div[@data-zone-name='CheckoutSummaryBox']")
    private WebElement checkoutSummaryBox;

    public CheckoutOrderPage(EventFiringWebDriver driver) {
        super(driver);
    }

    @Step(value = "Выбираем тип доставки 'Курьером'")
    public void chooseDeliveryType() {
        wait.until(ExpectedConditions.elementToBeClickable(expressDelivery))
                .click();
    }

    @Step("Проверяем, что Цена товара + Стоимость доставки = Итоговая стоимость")
    public void checkPrice() {
        chooseDeliveryType();
        checkTotalPrice();
    }

    @Step(value = "Проверяем корректность стоимости итоговой цены товара")
    public void checkTotalPrice() {
        String buff = totalItems.findElement(By.xpath(".//span[@data-auto = 'value']")).getText();
        int totalItemsPrice = priceToInt(buff);

        int totalDiscount = 0;
        if (checkoutSummaryBox.getAttribute("innerHTML").contains("total-discount")) {
            buff = discount.findElement(By.xpath(".//span[@data-tid='52906e8d']"))
                    .getText();

            totalDiscount -= priceToInt(buff);
        }

        buff = totalDelivery.findElement(By.xpath("(.//span[@data-auto = 'value'])")).getText();
        int totalDeliveryPrice = buff.matches("[\\d\\s]+.") ? priceToInt(buff) : 0;

        buff = totalPrice.findElement(By.className("_1oBlNqVHPq")).getText();
        int summaryPrice = priceToInt(buff);

        Assert.assertEquals(totalItemsPrice - totalDiscount + totalDeliveryPrice, summaryPrice);
    }

    @Step(value = "Переходим обратно в корзину")
    public CartPage backToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cart))
                .click();

        return new CartPage(driver);
    }

    private Integer priceToInt(String price) {
        return Integer.parseInt(price.substring(0, price.length() - 2).replaceAll("\\s+", ""));
    }
}
