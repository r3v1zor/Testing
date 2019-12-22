package ru;

import org.junit.Test;
import ru.page.*;

public class ToothbrushTest extends WebDriverSettings {
    @Test
    public void test() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnCatalog();

        BeautyAndHygienePage beautyAndHygienePage = mainPage.goToBeautyAndHygienePage();
        ToothbrushesPage toothbrushesPage = beautyAndHygienePage.goToToothbrushesPage();

        toothbrushesPage.setFilter(999, 1999);
        toothbrushesPage.checkToothBrushes(999, 1999);
        toothbrushesPage.chooseToothBrush();

        CartPage cartPage = toothbrushesPage.goToCart();

        CheckoutOrderPage checkoutOrderPage = cartPage.goToCheckoutOrder();
        checkoutOrderPage.checkPrice();

        cartPage = checkoutOrderPage.backToCart();
        cartPage.addToothBrushes(2999);

        checkoutOrderPage = cartPage.goToCheckoutOrder();
        checkoutOrderPage.checkPrice();
    }
}
