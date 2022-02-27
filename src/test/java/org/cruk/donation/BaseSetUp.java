package org.cruk.donation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseSetUp {

    public WebDriver driver;
    public Properties prop;

    // Json Elements declared
    String amount;
    String donationType;
    String motivation;
    String cancerType;
    String firstName;
    String lastName;
    String email;
    String phone;
    String postcode;
    String address1;
    String town;
    String country;
    String emailOptIn;
    String cardNumber;
    String cvv;
    String cardExpiry;
    String giftAid;

    public static Logger log = LogManager.getLogger(BaseSetUp.class.getName());
    SoftAssert softAssert = new SoftAssert();

    public WebDriver initializeDriver() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/data.properties");
        prop.load(fis);
        String browserName = prop.getProperty("browser");
        String chromePath = prop.getProperty("chromePath");
        String geckoPath = prop.getProperty("firefoxPath");
        String waitTime = prop.getProperty("implicitlyWait");
        int time = Integer.parseInt(waitTime);
        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", chromePath);
            driver = new ChromeDriver();
        } else if (browserName.equals("firefox")) {
            System.setProperty("webdriver.chrome.driver", geckoPath);
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
        return driver;
    }

    @BeforeTest
    public void Reader() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("../CrukAssignment/donor.json"));
            JSONObject jsonObject = (JSONObject) obj;
            amount = (String) jsonObject.get("amount");
            donationType = (String) jsonObject.get("donationType");
            motivation = (String) jsonObject.get("motivation");
            cancerType = (String) jsonObject.get("cancerType");
            firstName = (String) jsonObject.get("firstname");
            lastName = (String) jsonObject.get("lastname");
            email = (String) jsonObject.get("email");
            phone = (String) jsonObject.get("phone");
            emailOptIn = (String) jsonObject.get("emailOptIn");
            cardNumber = (String) jsonObject.get("cardNumber");
            cvv = (String) jsonObject.get("cvv");
            cardExpiry = (String) jsonObject.get("cardExpiry");
            giftAid = (String) jsonObject.get("giftaid");
            JSONObject homeAddress = (JSONObject) jsonObject.get("homeAddress");
            address1 = (String) homeAddress.get("address1");
            postcode = (String) homeAddress.get("postcode");
            town = (String) homeAddress.get("town");
            country = (String) homeAddress.get("country");

        } catch (Exception e) {
            log.error("Exception while executing method Reader " + e);
            softAssert.fail();
        }

    }
}
