package ru;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.page.MainPage;
import ru.page.SettingsPage;

@RunWith(DataProviderRunner.class)
public class ChangeCityTest extends WebDriverSettings {
    @DataProvider
    public static Object[][] dataProviderCities() {
        return new Object[][] {{"Хвалынск"}, {"Энгельс"}};
    }


    @Test
    @UseDataProvider(value = "dataProviderCities")
    public void changeCity(String city) {
        MainPage mainPage = new MainPage(driver);
        mainPage.changeCity(city);

        String mainPageCityText = mainPage.getSpanCityText(city);

        mainPage = auth(login, password);
        mainPage.moveCursorOnProfile();
        final SettingsPage settingsPage = mainPage.clickOnSettings();
        final String settingsPageCityText = settingsPage.getMyCityText();

        Assert.assertEquals(mainPageCityText, settingsPageCityText);
    }
}