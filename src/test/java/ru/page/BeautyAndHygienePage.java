package ru.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BeautyAndHygienePage {
    @FindBy(xpath = "//span[contains(text(), 'Зубные щетки')]/parent::a")
    private WebElement toothbrushes;

    private WebDriver driver;
    private WebDriverWait wait;

    public BeautyAndHygienePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 10, 100);
    }

    public ToothbrushesPage getToothbrushesPage() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(toothbrushes))
                .click();

        return new ToothbrushesPage(driver);
    }
}
