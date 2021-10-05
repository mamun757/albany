package automation_test.mortgage_calculator;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.LoggerForParallelTests;
import listeners.RetryFailedTests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_objects_model.Home_Page;
import utilities.ReadConfigFiles;

public class CalculateRates extends LoggerForParallelTests {
    private static final Logger LOGGER = LogManager.getLogger(CalculateRates.class);
    WebDriver driver;

   @BeforeMethod
    public void openBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        LOGGER.info("-----Start Test-(Calculate Rates)--");
        String url = ReadConfigFiles.getPropertyValues("MortgageCalculatorUrl");
        ActOn.browser(driver).openBrowser(url);
        LOGGER.info("Browser is successfully initiated with the URL: " + url);
    }

    @Test(retryAnalyzer = RetryFailedTests.class)
    public void CalculateRealApr(){
        new Home_Page(driver)
                .mouseHoverToRates()
                .navigateToRealAprPage()
                .waitForPageToLoad()
                .typeHomeValue("200000")
                .clickOnRadioButton()
                .typeDownPayment("15000")
                .typeInterestRates("3")
                .clickCalculateButton()
                .validateActualAprRates("3.130%");
    }

    @AfterMethod
    public void closeTestBrowser() {
        ActOn.browser(driver).closeBrowser();
        LOGGER.info("Browser is successfully closed");
        LOGGER.info("-----End Test-(Calculate Rates)--");
    }
}
