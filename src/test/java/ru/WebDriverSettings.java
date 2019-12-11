package ru;

import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import ru.handler.EventHandler;
import util.ScreenshotUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WebDriverSettings {
    protected static EventFiringWebDriver driver;
    protected String login;
    protected String password;

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            Properties properties = new Properties();

            try (FileReader fileReader = new FileReader("C:\\Users\\Vladislav\\IdeaProjects\\Testing\\src\\main\\resources\\account.properties")) {
                properties.load(fileReader);
            } catch (IOException exp) {
                exp.printStackTrace();
            }
            login = properties.getProperty("login");
            password = properties.getProperty("password");

            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vladislav\\IdeaProjects\\Testing\\chromedriver.exe");

            WebDriver webdriver = new ChromeDriver();
            driver = new EventFiringWebDriver(webdriver);

            EventHandler handler = new EventHandler();
            driver.register(handler);
            driver.manage().window().maximize();
            driver.get("https://beru.ru");

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        @Override
        protected void failed(Throwable e, Description description) {
            ScreenshotUtils.takesScreenshot("failed", driver);
        }

        @Override
        protected void finished(Description description) {
            driver.close();
        }
    };
}
