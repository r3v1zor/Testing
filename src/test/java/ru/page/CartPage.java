package ru.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends AbstractPage {
    @FindBy(xpath = "//span[contains(@data-tid, '365cd6d7')]")
    private WebElement untilFreeDelivery;

    @FindBy(xpath = "//div[contains(@data-tid, '95256ff1')]//button")
    private WebElement checkoutOrder;

    @FindBy(xpath = "//div[contains(@data-auto, 'total-items')]")
    private WebElement totalItems;

    @FindBy(xpath = "//span[contains(text(), '+')]/../..")
    private WebElement addToothbrushButton;

    public CartPage(EventFiringWebDriver driver) {
        super(driver);
    }

    @Step(value = "Переходим к оформлению заказа")
    public CheckoutOrderPage checkoutOrder() {
        wait.until(ExpectedConditions.or(ExpectedConditions.elementToBeClickable(checkoutOrder),
                ExpectedConditions.elementToBeClickable(checkoutOrder)));

        checkoutOrder.click();

        return new CheckoutOrderPage(driver);
    }

    @Step(value = "Увеличиваем количество щеток, пока стоимость не превысит {priceTo}")
    public void addToothBrushes(int priceTo) {
        String buff = totalItems.findElement(By.xpath(".//span[@data-auto = 'value']")).getText();

        int price = Integer.parseInt(buff.substring(0, buff.length() - 2).replaceAll("\\s+", ""));

        for (int i = price; i <= priceTo; i += price) {
            addToothBrush();
        }
    }

    private void addToothBrush() {
        String prevPrice = totalItems.findElement(By.xpath(".//span[@data-auto = 'value']")).getText();

        addToothbrushButton.click();

        wait.until((ExpectedCondition<Boolean>) driver ->
                !totalItems.findElement(By.xpath(".//span[@data-auto = 'value']")).getText().equals(prevPrice));

    }
}
