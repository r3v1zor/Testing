package ru;

import com.tngtech.java.junit.dataprovider.DataProvider;
import org.junit.Assert;
import org.junit.Test;
import ru.page.*;

public class ToothbrushTest extends WebDriverSettings {
    @DataProvider
    public static Object[][] dataProviderPrices() {
        return new Object[][]{{999, 1999}, {888, 1888}};
    }


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

        CheckoutOrderPage checkoutOrderPage = cartPage.checkoutOrder();
        checkoutOrderPage.checkPrice();

        cartPage = checkoutOrderPage.backToCart();
        cartPage.addToothBrushes(2999);

        checkoutOrderPage = cartPage.checkoutOrder();
        checkoutOrderPage.checkPrice();
    }
}
