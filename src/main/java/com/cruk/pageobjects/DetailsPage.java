package com.cruk.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DetailsPage {
    public WebDriver driver;

    public DetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void getTitle() {
        WebElement e = driver.findElement(By.name("title"));
        Select s = new Select(e);
        s.selectByValue("mr");
    }

    public WebElement getFirstName() {
        return driver.findElement(By.id("forename"));
    }

    public WebElement getLastName() {
        return driver.findElement(By.id("surname"));
    }

    public WebElement getEmail() {
        return driver.findElement(By.id("emailAddress"));
    }

    public WebElement getPhone() {
        return driver.findElement(By.id("phoneNumber"));
    }

    public WebElement getPostalCode() {
        return driver.findElement(By.name("postalCode"));
    }

    public WebElement getAddressLine() {
        return driver.findElement(By.xpath("//input[@name='addressLine1']"));
    }

    public WebElement getTown() {
        return driver.findElement(By.xpath("//input[@name='city']"));
    }

    public void setCountry(String country) {
        WebElement nation = driver.findElement(By.id("country"));
        Select s = new Select(nation);
        s.selectByValue(country);
    }

    public WebElement manualSelect() {

/*        if (driver.findElement(By.id("onetrust-accept-btn-handler")).isDisplayed())
        {
            driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        }*/
        return driver.findElement(By.xpath("//button[text()='Enter address manually']"));
    }

    public WebElement setEmailOption(String option) {
        if (option.equalsIgnoreCase("No")) {
            return driver.findElement(By.xpath("(//span[text()='No'])[1]/preceding-sibling::div[1]"));
        } else {
            return driver.findElement(By.xpath("(//span[text()='Yes'])[1]/preceding-sibling::div[1]"));
        }
    }
}

