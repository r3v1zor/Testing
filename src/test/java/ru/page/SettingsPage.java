package ru.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SettingsPage extends AbstractPage {
    @FindBy(css = "span._3ioN70chUh > span:nth-child(1)")
    private WebElement myCity;

    public SettingsPage(WebDriver driver) {
        super(driver);
    }

    public String getMyCityText() {
        return wait.until(ExpectedConditions.visibilityOf(myCity))
                .getText();
    }
}
