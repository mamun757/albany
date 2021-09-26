package automation_test.mortgage_calculator_parameterized;

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
import utilities.SqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CalculateMonthlyPaymentParameterized {
    private static final Logger LOGGER = LogManager.getLogger(CalculateMonthlyPaymentParameterized.class);
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
        ResultSet rs = SqlConnector.readData("select * from monthly_mortgage");
        try {
            while (rs.next()){
                LOGGER.info("-------Start of " + rs.getRow() + " Iteration");
                new Home_Page(driver)
                        .typeHomePrice(rs.getString("homevalue"))
                        .typeDownPayment(rs.getString("downpayment"))
                        .clickRadioButton()
                        .typeLoanAmount(rs.getString("loanamount"))
                        .typeInterestRates(rs.getString("interestrate"))
                        .typeLoanTerm(rs.getString("loanterm"))
                        .selectMonth(month)
                        .typeYear(year)
                        .typePropertyTax(rs.getString("propertytax"))
                        .typePMI(rs.getString("pmi"))
                        .typeHOI(rs.getString("homeownerinsurance"))
                        .typeHOA(rs.getString("monthlyhoa"))
                        .typeLoanType(rs.getString("loantype"))
                        .selectBuyOrRefi(rs.getString("buyorrefi"))
                        .clickOnCalculateButton()
                        .validateMonthlyPayment(rs.getString("totalmonthlypayment"));
            }
        }catch (SQLException e){
            LOGGER.error("SQL Query error :"+e.getMessage());
        }
    }

    @AfterMethod
    public void closeTestBrowser() {
        ActOn.browser(driver).closeBrowser();
        LOGGER.info("Browser is closed");
        LOGGER.info("---------------End Test-(CalculateMonthlyPaymentFramework)");
    }

}
