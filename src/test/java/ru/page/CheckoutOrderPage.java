package ru.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutOrderPage {
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

    private WebDriver driver;
    private WebDriverWait wait;


    public CheckoutOrderPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 10, 100);
    }

    public void chooseDeliveryType() {
        wait.until(ExpectedConditions.elementToBeClickable(expressDelivery))
                .click();
    }

    public boolean checkTotalPrice() {
        String buff = totalItems.findElement(By.xpath(".//span[@data-auto = 'value']")).getText();
        int totalItemsPrice = priceToInt(buff);

        buff = totalDelivery.findElement(By.xpath("(.//span[@data-auto = 'value'])")).getText();
        System.out.println(buff);
        System.out.println(buff.matches("[\\d\\s]+."));
        int totalDeliveryPrice = buff.matches("[\\d\\s]+.") ? priceToInt(buff) : 0;

        buff = totalPrice.findElement(By.className("_1oBlNqVHPq")).getText();
        int summaryPrice = priceToInt(buff);

        return totalItemsPrice + totalDeliveryPrice == summaryPrice;
    }

    public CartPage backToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cart))
                .click();

        return new CartPage(driver);
    }

    private Integer priceToInt(String price) {
        return Integer.parseInt(price.substring(0, price.length() - 2).replaceAll("\\s+", ""));
    }
}
