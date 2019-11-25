package ru.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SettingsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "span._3ioN70chUh > span:nth-child(1)")
    private WebElement myCity;

    public SettingsPage() {
    }

    public SettingsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public String getMyCityText() {
        return wait.until(ExpectedConditions.visibilityOf(myCity))
                .getText();
    }
}
