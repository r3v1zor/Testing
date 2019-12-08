package util;

import io.qameta.allure.Attachment;
import org.openqa.selenium.*;

public class ScreenshotUtils {
    @Attachment(value = "Снимок ''{0}''", type = "image/png")
    public static byte[] takesScreenshot(String screenshotName, WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Снимок ''{0}''", type = "image/png")
    public static byte[] takesScreenshot(String screenshotName, WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('font', 'yellow')", element);

        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
