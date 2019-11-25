package ru;

import org.junit.Assert;
import org.junit.Test;
import ru.page.MainPage;
import ru.page.SettingsPage;

public class AuthTest extends WebDriverSettings {
    @Test
    public void authTest() {
        MainPage mainPage = auth(login, password);
        String text = mainPage.getSignInButtonText();
        Assert.assertEquals(text, "Мой профиль");
        mainPage.clickOnMyProfile();
        Assert.assertTrue(mainPage.isLoginVisible());
    }

    @Test
    public void cityTest() {
        String city = "Хвалынск";

        MainPage mainPage = new MainPage(driver);
        mainPage.changeCity(city);

        String mainPageCityText = mainPage.getSpanCityText(city);

        mainPage = auth(login, password);
        mainPage.clickOnMyProfile();
        final SettingsPage settingsPage = mainPage.clickOnSettings();
        final String settingsPageCityText = settingsPage.getMyCityText();

        Assert.assertEquals(mainPageCityText, settingsPageCityText);
    }
}
