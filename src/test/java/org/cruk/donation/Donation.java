package org.cruk.donation;

import com.cruk.pageobjects.DonationPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class Donation extends BaseSetUp {

    public static Logger log = LogManager.getLogger(Donation.class.getName());

    @BeforeMethod
    public void initialize() throws IOException {
        driver = initializeDriver();
        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();
    }

    @Test
    public void donateWithCard() {
        try {
            DonationPage donate = new DonationPage(driver);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(18));
            handleCookies();
            donate.getAmount().sendKeys(amount);
            wait.until(ExpectedConditions.elementToBeClickable(donate.getDonationType(donationType)));
            donate.getDonationType(donationType).click();
            Select dropdown = new Select(donate.getMotivation());
            dropdown.selectByVisibleText(motivation);
            donate.getMemory().sendKeys("Test");
            donate.setCancerType(cancerType);
            donate.submit();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("forename")));
            enterDetails(donate);
            enterPaymentDetails(donate);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(), 'Thank you')]")));
            String s = driver.findElement(By.xpath("//h2[contains(text(), 'Thank you')]")).getText();
            Assert.assertTrue(s.contains(amount));
        } catch (Exception e) {
            log.error("Exception while executing method donateWithCard " + e);
            softAssert.fail();
        }
    }

    public void enterDetails(DonationPage donate) {
        try {
            donate.getDetails().getTitle();
            donate.getDetails().getFirstName().sendKeys(firstName);
            donate.getDetails().getLastName().sendKeys(lastName);
            donate.getDetails().getEmail().sendKeys(email);
            donate.getDetails().getPhone().sendKeys(phone);
            donate.getDetails().manualSelect().click();
            donate.getDetails().getAddressLine().sendKeys(address1);
            donate.getDetails().getTown().sendKeys(town);
            donate.getDetails().getPostalCode().sendKeys(postcode);
            donate.getDetails().setCountry(country);
            donate.getDetails().setEmailOption(emailOptIn).click();
            donate.submit();
        } catch (Exception e) {
            log.error("Exception while executing method enterDetails " + e);
            softAssert.fail();
        }
    }

    public void enterPaymentDetails(DonationPage donate) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='How would you like to donate?']")));
            donate.getPaymentDetails().getPaymentType().click();
            donate.getPaymentDetails().getCardHolderName().sendKeys(firstName + " " + lastName);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("braintree-hosted-field-number")));
            donate.getPaymentDetails().setCardDetails(cardNumber, "braintree-hosted-field-number");
            donate.getPaymentDetails().setCardDetails(cardExpiry, "braintree-hosted-field-expirationDate");
            donate.getPaymentDetails().setCardDetails(cvv, "braintree-hosted-field-cvv");
            donate.getPaymentDetails().getGiftAid(giftAid);
            donate.submit();
        } catch (Exception e) {
            log.error("Exception while executing method PaymentDetails" + e);
            softAssert.fail();
        }
    }

    public void handleCookies() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler")));
            driver.findElement(By.id("onetrust-pc-btn-handler")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("accept-recommended-btn-handler")));
            driver.findElement(By.id("accept-recommended-btn-handler")).click();
        } catch (Exception e) {
            log.error("Exception while handling Cookies" + e);
            softAssert.fail();
        }
    }

        @AfterTest
        public void closeWindows () {
            driver.close();
        }
    }
