package automation_test.php_travels;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.LoggerForParallelTests;
import listeners.RetryFailedTests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ReadConfigFiles;

public class VerifyTitle extends LoggerForParallelTests {
    private static final Logger LOGGER = LogManager.getLogger(VerifyTitle.class);
    WebDriver driver;

   @BeforeMethod
    public void openTestBrowser(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        LOGGER.info("-----Start Test-(Verify Php Travels Titles)--");
        String url = ReadConfigFiles.getPropertyValues("PHPTravelUrl");
        ActOn.browser(driver).openBrowser(url);
        LOGGER.debug("Browser starts with the URL: " + url);
    }

    @Test(retryAnalyzer = RetryFailedTests.class)
    public void phpTitle(){
        String expectedTitle = "Demo Script Test drive - PHPTRAVELS";
        String actualTitle = ActOn.browser(driver).captureTitle();
        Assert.assertEquals(actualTitle, expectedTitle);

    }
    @AfterMethod
    public void closeTestBrowser() {
        ActOn.browser(driver).closeBrowser();
        LOGGER.info("-----End Test-(Verify Php Travels Titles)--");
    }
}
