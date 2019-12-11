package util;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScreenshotUtils {
    @Attachment(value = "{screenshotName}", type = "image/png")
    public static byte[] takesScreenshot(String screenshotName, WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{screenshotName}", type = "image/png")
    public static byte[] takesScreenshot(String screenshotName, WebDriver driver, WebElement element) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
