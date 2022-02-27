package com.cruk.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentPage {
    public WebDriver driver;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getPaymentType() {
        return driver.findElement(By.xpath("//div[@id='paymentRadioGroup']/div[1]"));
    }

    public WebElement getCardHolderName() {
        return driver.findElement(By.id("cardholderName"));
    }

    public void setCardDetails(String cardDetail, String id) {
        WebElement iframeElement = driver.findElement(By.id(id));
        driver.switchTo().frame(iframeElement);
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
        w.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/form/input[1]")));
        driver.findElement(By.xpath("//html/body/form/input[1]")).sendKeys(cardDetail);
        driver.switchTo().defaultContent();
    }

    public void getGiftAid(String aid) {
        if (aid.equalsIgnoreCase("yes"))
            driver.findElement(By.xpath("//label[@for='giftAid1']")).click();
    }
}
