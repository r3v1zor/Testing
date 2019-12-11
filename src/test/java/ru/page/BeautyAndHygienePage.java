package ru.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BeautyAndHygienePage extends AbstractPage {
    @FindBy(xpath = "//span[contains(text(), 'Зубные щетки')]/parent::a")
    private WebElement toothbrushes;

    public BeautyAndHygienePage(EventFiringWebDriver driver) {
        super(driver);
    }

    @Step(value = "Переходим на страницу зубных щеток")
    public ToothbrushesPage goToToothbrushesPage() {
        wait.until(ExpectedConditions.elementToBeClickable(toothbrushes))
                .click();

        return new ToothbrushesPage(driver);
    }
}
