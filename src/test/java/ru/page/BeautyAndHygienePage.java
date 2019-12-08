package ru.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BeautyAndHygienePage extends AbstractPage {
    @FindBy(xpath = "//span[contains(text(), 'Зубные щетки')]/parent::a")
    private WebElement toothbrushes;

    public BeautyAndHygienePage(WebDriver driver) {
        super(driver);
    }

    public ToothbrushesPage getToothbrushesPage() {
        wait.until(ExpectedConditions.elementToBeClickable(toothbrushes))
                .click();

        return new ToothbrushesPage(driver);
    }
}
