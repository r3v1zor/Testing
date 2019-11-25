package ru;

import com.tngtech.java.junit.dataprovider.DataProvider;
import org.junit.Assert;
import org.junit.Test;
import ru.page.*;

public class ToothbrushTest extends WebDriverSettings{
    @DataProvider
    public static Object[][] dataProviderCities() {
        return new Object[][] {{"Хвалынск"}, {"Энгельс"}};
    }


    @Test
    public void test() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnCatalog();

        BeautyAndHygienePage beautyAndHygienePage = mainPage.goToBeautyAndHygienePage();
        ToothbrushesPage toothbrushesPage = beautyAndHygienePage.getToothbrushesPage();

        toothbrushesPage.setFilter(999, 1999);
        toothbrushesPage.checkToothBrushes(999, 1999);
        toothbrushesPage.chooseToothBrush();

        CartPage cartPage = toothbrushesPage.goToCart();
        CheckoutOrderPage checkoutOrderPage = cartPage.checkoutOrder();

        checkoutOrderPage.chooseDeliveryType();
        Assert.assertTrue(checkoutOrderPage.checkTotalPrice());

        cartPage = checkoutOrderPage.backToCart();
        cartPage.addToothBrushes(2999);

        checkoutOrderPage = cartPage.checkoutOrder();
        checkoutOrderPage.chooseDeliveryType();
        Assert.assertTrue(checkoutOrderPage.checkTotalPrice());
    }
}
