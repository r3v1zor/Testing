package ru;

import org.junit.Test;
import ru.page.MainPage;

public class AuthTest extends WebDriverSettings {
    @Test
    public void authTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.auth(login, password);
        mainPage.clickOnMyProfile();
        mainPage.isLoginVisible();
        mainPage.isSignInButtonChangeOnMyProfile();
    }
}
