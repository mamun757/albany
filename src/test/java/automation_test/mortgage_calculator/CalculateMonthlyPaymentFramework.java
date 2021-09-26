package automation_test.mortgage_calculator;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_objects_model.Home_Page;
import utilities.DateUtils;
import utilities.ReadConfigFiles;


public class CalculateMonthlyPaymentFramework {
    private static final Logger LOGGER = LogManager.getLogger(CalculateMonthlyPaymentFramework.class);
    WebDriver driver;

    @BeforeMethod
    public void browserInitialization(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        LOGGER.info("-----Start Test-(CalculateMonthlyPaymentFramework)--");
        String url = ReadConfigFiles.getPropertyValues("MortgageCalculatorUrl");
        ActOn.browser(driver).openBrowser(url);
        LOGGER.info("Browser is successfully initiated with the URL: " + url);
    }

    @Test
    public void calculateMonthlyPayment(){
        String Date = DateUtils.returnNextMonth();
        String[] dateArray = Date.split("-");
        String month = dateArray[0];
        String year = dateArray[1];
       new Home_Page(driver)
               .typeHomePrice("300000")
               .typeDownPayment("60000")
               .clickRadioButton()
               .typeLoanAmount("240000")
               .typeInterestRates("3")
               .typeLoanTerm("30")
               .selectMonth(month)
               .typeYear(year)
               .typePropertyTax("5000")
               .typePMI("0.5")
               .typeHOI("1000")
               .typeHOA("100")
               .typeLoanType("FHA")
               .selectBuyOrRefi("Buy")
               .clickOnCalculateButton()
               .validateMonthlyPayment("$1,611.85");

    }

    @AfterMethod
    public void closeTestBrowser() {
        ActOn.browser(driver).closeBrowser();
        LOGGER.info("Browser is closed");
        LOGGER.info("---------------End Test-(CalculateMonthlyPaymentFramework)");
    }

}

