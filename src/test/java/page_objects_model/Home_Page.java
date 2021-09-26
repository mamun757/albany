package page_objects_model;

import command_providers.ActOn;
import command_providers.AssertThat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home_Page extends NavigationBar{
    private static final Logger LOGGER = LogManager.getLogger(Home_Page.class);

    private final By HomeValue = By.id("homeval");
    private final By DownPayment = By.id("downpayment");
    private final By RadioButton = By.xpath("//*[@id='calc']//input[@name='param[downpayment_type]'][@value='money']");
    private final By LoanAmount = By.id("loanamt");
    private final By InterestRate = By.id("intrstsrate");
    private final By LoanTerm = By.id("loanterm");
    private final By StartMonth = By.name("param[start_month]");
    private final By StartYear = By.id("start_year");
    private final By PropertyTax = By.id("pptytax");
    private final By PMI = By.id("pmi");
    private final By HOI = By.id("hoi");
    private final By HOA = By.id("hoa");
    private final By LoanType = By.name("param[milserve]");
    private final By BuyOrReDropDown = By.name("param[refiorbuy]");
    private final By CalculateButton = By.xpath("//*[@id='calc']//input[@name='cal']");

    public Home_Page(WebDriver driver){
        super(driver);
    }

    //enter home value 300000
    public Home_Page typeHomePrice(String value){
        LOGGER.debug("Entered Home price is :" + value);
        ActOn.element(driver,HomeValue).setValue(value);
        return this;
    }
    public Home_Page typeDownPayment(String value){
        ActOn.wait(driver,DownPayment).waitForElement();
        LOGGER.debug("Entered DownPayment is :" + value);
        ActOn.element(driver,DownPayment).setValue(value);
        return this;
    }

    public Home_Page clickRadioButton(){
        LOGGER.debug("Clicked on Radio Button");
        ActOn.element(driver,RadioButton).click();
        return this;
    }
    public Home_Page typeLoanAmount(String value){
        LOGGER.debug("Entered Loan Amount is :" + value);
        ActOn.element(driver,LoanAmount).setValue(value);
        return this;
    }
    public Home_Page typeInterestRates(String value){
        LOGGER.debug("Entered Interest rate is :" + value);
        ActOn.element(driver,InterestRate).setValue(value);
        return this;
    }
    public Home_Page typeLoanTerm(String value){
        LOGGER.debug("Entered LoanTerm  is :" + value);
        ActOn.element(driver,LoanTerm).setValue(value);
        return this;
    }
    public Home_Page selectMonth(String month){
        LOGGER.debug("Select Month is :" + month);
        ActOn.element(driver,StartMonth).selectValue(month);
        return this;
    }
    public Home_Page typeYear(String year){
        LOGGER.debug("Entered Year is :" + year);
        ActOn.element(driver,StartYear).setValue(year);
        return this;
    }
    public Home_Page typePropertyTax(String value){
        LOGGER.debug("Entered Property Tax is "+ value);
        ActOn.element(driver,PropertyTax).setValue(value);
        return this;
    }
    public Home_Page typePMI(String value){
        LOGGER.debug("Entered PMI is "+ value);
        ActOn.element(driver,PMI).setValue(value);
        return this;
    }
    public Home_Page typeHOI(String value){
        LOGGER.debug("Entered HOI is "+ value);
        ActOn.element(driver,HOI).setValue(value);
        return this;
    }
    public Home_Page typeHOA(String value){
        LOGGER.debug("Entered HOA is "+ value);
        ActOn.element(driver,HOA).setValue(value);
        return this;
    }
    public Home_Page typeLoanType(String value){
        LOGGER.debug("Select LoanType  is "+ value);
        ActOn.element(driver,LoanType).selectValue(value);
        return this;
    }
    public Home_Page selectBuyOrRefi(String value){
        LOGGER.debug("Select Buy or Refinance option "+ value);
        ActOn.element(driver,BuyOrReDropDown).selectValue(value);
        return this;
    }
    public Home_Page clickOnCalculateButton(){
        LOGGER.debug("Clicked on Calculator button");
        ActOn.element(driver,CalculateButton).click();
        return this;
    }
    public Home_Page validateMonthlyPayment(String totalMonthlyPayment){
        LOGGER.debug("Monthly Payment is validated by: " + totalMonthlyPayment);
        By monthlyPaymentLocator = By.xpath("//*[@id='calc']/form//h3[text()='"+totalMonthlyPayment+"']");
        AssertThat.elementAssertions(driver,monthlyPaymentLocator).elementExist();
        return this;
    }
}
