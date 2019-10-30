package ru;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.page.LoginPage;
import ru.page.MainPage;
import ru.page.SettingsPage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AuthTest {
    private WebDriver driver;
    private String login;
    private String password;

    @Before
    public void setUp() {
        Properties properties = new Properties();

        try (FileReader fileReader = new FileReader("/home/r3v1zor/IdeaProjects/temp/" +
                "Testing/src/main/resources/account.properties")) {
            properties.load(fileReader);
        } catch (IOException exp) {
            exp.printStackTrace();
        }
        login = properties.getProperty("login");
        password = properties.getProperty("password");

        driver = new FirefoxDriver();
        driver.get("https://beru.ru");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

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

        String mainPageCityText = mainPage.getSpanCityText();

        mainPage = auth(login, password);
        mainPage.clickOnMyProfile();
        final SettingsPage settingsPage = mainPage.clickOnSettings();
        final String settingsPageCityText = settingsPage.getMyCityText();

        Assert.assertEquals(mainPageCityText, settingsPageCityText);

    }

    @Test
    public void pricesTest() {

    }

    @After
    public void tearDown() {
        driver.close();
    }

    private MainPage auth(String login, String password) {
        MainPage mainPage = new MainPage(driver);
        final LoginPage loginPage = mainPage.getLoginPage();
        return loginPage.login(login, password);
    }
}
