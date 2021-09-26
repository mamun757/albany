package selenium_basic_test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CalculateMonthlyPayment {
    private static final Logger LOGGER = LogManager.getLogger(TestngTest.class);
    WebDriver driver;
    Select select;

    @BeforeMethod
    public void openBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        LOGGER.info("-----Start Test-(Calculate Monthly Payment)--");
        driver.get("https://www.mortgagecalculator.org/");
        driver.manage().window().maximize();
    }

    public void enterMortgageData(){
        driver.findElement(By.id("homeval")).clear();
        driver.findElement(By.id("homeval")).sendKeys("300000");

        driver.findElement(By.id("downpayment")).clear();
        driver.findElement(By.id("downpayment")).sendKeys("60000");

        driver.findElement(By.xpath("//*[@id='calc']//input[@name='param[downpayment_type]'][@value='money']")).click();

        driver.findElement(By.id("loanamt")).clear();
        driver.findElement(By.id("loanamt")).sendKeys("240000");

        driver.findElement(By.id("intrstsrate")).clear();
        driver.findElement(By.id("intrstsrate")).sendKeys("3");

        driver.findElement(By.id("loanterm")).clear();
        driver.findElement(By.id("loanterm")).sendKeys("30");

        select = new Select(driver.findElement(By.name("param[start_month]")));
        select.selectByVisibleText("Nov");

        driver.findElement(By.id("start_year")).clear();
        driver.findElement(By.id("start_year")).sendKeys("2021");

        driver.findElement(By.id("pptytax")).clear();
        driver.findElement(By.id("pptytax")).sendKeys("5000");

        driver.findElement(By.id("pmi")).clear();
        driver.findElement(By.id("pmi")).sendKeys("0.5");

        driver.findElement(By.id("hoi")).clear();
        driver.findElement(By.id("hoi")).sendKeys("1000");

        driver.findElement(By.id("hoa")).clear();
        driver.findElement(By.id("hoa")).sendKeys("100");

        select = new Select(driver.findElement(By.name("param[milserve]")));
        select.selectByVisibleText("FHA");

        select = new Select(driver.findElement(By.name("param[refiorbuy]")));
        select.selectByVisibleText("Buy");



    }
    @Test
    public void calculatePayment(){
        enterMortgageData();
        driver.findElement(By.xpath("//*[@id='calc']//input[@name='cal']")).click();

        boolean paymentExists = driver.findElements(By.xpath("//*[@id='calc']/form//h3[text()='$1,611.85']")).size() >0;
        Assert.assertTrue(paymentExists,"Total monthly payment is wrong");
    }

    @AfterMethod
    public void closeBrowser(){

        driver.quit();
        LOGGER.info("-----End Test-(Calculate Monthly Payment)--");
    }

}
