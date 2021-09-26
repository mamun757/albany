package page_objects_model;

import command_providers.ActOn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class RealApr extends NavigationBar {
    private static final Logger LOGGER = LogManager.getLogger(Home_Page.class);
    private final By ClickOnCalculator = By.xpath("//*[@id='CalcForm']/div[2]/label[1]");
    private final By HomeValue = By.name("HomeValue");
    private final By DownPayment = By.name("DownPayment");
    private final By DownRadioButton = By.id("DownPaymentSel0");
    private final By InterestRate = By.name("Interest");
    private final By ClickOnButton = By.name("calculate");
    private final By ActualAprRates =
            By.xpath("//*[@id='analysisDiv']/table/tbody/tr/td/strong[contains(text(),'Actual APR')]/../../td[2]/strong");

    public RealApr(WebDriver driver){
        super(driver);
        }
        public RealApr waitForPageToLoad(){
            LOGGER.debug("Wait for the RealApr Page");
            ActOn.wait(driver,ClickOnCalculator).waitForElement();
            return this;
        }

    public RealApr clickCButton(){
        LOGGER.debug("Clicked on the Button");
        ActOn.element(driver,ClickOnCalculator).click();
        return this;
    }

        public RealApr typeHomeValue(String value){
            LOGGER.debug("Entered Home value is :" + value);
            ActOn.element(driver,HomeValue).setValue(value);
            return this;
        }

    public RealApr typeDownPayment(String value){
        LOGGER.debug("Entered DownPayment is :" + value);
        ActOn.element(driver,DownPayment).setValue(value);
        return this;
    }

    public RealApr clickOnRadioButton(){
        LOGGER.debug("Clicked on the Button");
        ActOn.element(driver,DownRadioButton).click();
        return this;
    }
    public RealApr typeInterestRates(String value){
        LOGGER.debug("Entered Interest Rate is :" + value);
        ActOn.element(driver,InterestRate).setValue(value);
        return this;
    }
    public RealApr clickCalculateButton(){
        LOGGER.debug("Clicked on the Calculate Button");
        ActOn.element(driver,ClickOnButton).click();
        return this;
    }
    public RealApr validateActualAprRates(String expectedValue){
        LOGGER.debug("validate the Actual rate : " + expectedValue);
        String aprRate = ActOn.element(driver,ActualAprRates).getTextValue();
        Assert.assertEquals(aprRate,expectedValue);
        return this;
    }

}
