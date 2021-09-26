package automation_test.mortgage_calculator_parameterized;

import automation_test.mortgage_calculator.CalculateRates;
import command_providers.ActOn;
import data_providers.DataProviderClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_objects_model.Home_Page;
import utilities.ReadConfigFiles;

public class CalculateRates_Parameterized {
    private static final Logger LOGGER = LogManager.getLogger(CalculateRates_Parameterized.class);
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

    @Test(dataProvider = "RealAprRates", dataProviderClass = DataProviderClass.class)
    public void CalculateRealApr( String homePrice, String downPayment, String interestRate, String aprRate){
        new Home_Page(driver)
                .mouseHoverToRates()
                .navigateToRealAprPage()
                .waitForPageToLoad()
                .typeHomeValue(homePrice)
                .clickOnRadioButton()
                .typeDownPayment(downPayment)
                .typeInterestRates(interestRate)
                .clickCalculateButton()
                .validateActualAprRates(aprRate);
    }

    @AfterMethod
    public void closeTestBrowser() {
        ActOn.browser(driver).closeBrowser();
        LOGGER.info("Browser is successfully closed");
        LOGGER.info("-----End Test-(Calculate Rates)--");
    }
}
