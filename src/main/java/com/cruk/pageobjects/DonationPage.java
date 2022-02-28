package com.cruk.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DonationPage {
    public WebDriver driver;

    public DetailsPage getDetails() {
        DetailsPage detailsPage = new DetailsPage(driver);
        return detailsPage;
    }

    public PaymentPage getPaymentDetails() {
        PaymentPage paymentPage = new PaymentPage(driver);
        return paymentPage;
    }

    public DonationPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getAmount() {
        return driver.findElement(By.id("otherAmount"));
    }

    public WebElement getDonationType(String donation) {
        return driver.findElement(By.xpath("//span[text()='" + donation + "']/preceding-sibling::div[1]"));
    }

    public WebElement getMotivation() {
        return driver.findElement(By.xpath("//select[@name='motivation']"));
    }

    public void setCancerType(String type) {
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(8));
        w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@id='destinationRadioGroup']/div)[2]/label/div[2]")));
        driver.findElement(By.xpath("(//div[@id='destinationRadioGroup']/div)[2]/label/div[2]")).click();
        WebElement cancerType = driver.findElement(By.cssSelector("select[name='restriction']"));
        Select s = new Select(cancerType);
        s.selectByVisibleText(type);
    }

    public WebElement getMemory() {
        return driver.findElement(By.cssSelector("input#inMemoryName"));
    }

    public void submit() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

}
