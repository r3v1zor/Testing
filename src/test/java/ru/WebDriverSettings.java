package ru;

import io.qameta.allure.Attachment;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.page.LoginPage;
import ru.page.MainPage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WebDriverSettings {
    protected static WebDriver driver;
    protected String login;
    protected String password;

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            Properties properties = new Properties();

            try (FileReader fileReader = new FileReader("/home/r3v1zor/IdeaProjects/temp/" +
                    "Testing/src/main/resources/account.properties")) {
                properties.load(fileReader);
            } catch (IOException exp) {
                exp.printStackTrace();
            }
            login = properties.getProperty("login");
            password = properties.getProperty("password");

            System.setProperty("webdriver.chrome.driver", "/home/r3v1zor/apps/chromedriver");
            driver = new ChromeDriver();
            driver.get("https://beru.ru");

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        @Override
        protected void failed(Throwable e, Description description) {
            takeScreenShot();
        }

        @Override
        protected void finished(Description description) {
            driver.close();
        }
    };

    public static void takesScreenShot(String filepath, WebElement element) {

    }

    @Attachment(value = "Снимок ''{0}''", type = "image/png")
    public static byte[] takeScreenShot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Снимок ''{0}''", type = "image/png")
    public static byte[] takeScreenShot(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('font', 'yellow')", element);

        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    static MainPage auth(String login, String password) {
        MainPage mainPage = new MainPage(driver);
        final LoginPage loginPage = mainPage.getLoginPage();
        return loginPage.login(login, password);
    }
}
