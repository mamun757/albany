package page_objects_model;

import command_providers.ActOn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationBar {
    private static final Logger LOGGER = LogManager.getLogger(NavigationBar.class);
    private final By MortgageCalLogo = By.xpath("//a/img[@alt='Logo']");
    private final By RatesLink = By.linkText("Rates");
    private final By RealAprLink = By.linkText("Real APR");

    public WebDriver driver;

    public NavigationBar(WebDriver driver){
        this.driver = driver;
    }

    //Mouse hover to Rates Link
    public NavigationBar mouseHoverToRates(){
        LOGGER.debug("Mouse hove to the Rates Link");
        ActOn.element(driver,RatesLink).mouseHover();
        return this;
    }
    //click  the real apr link
    public RealApr navigateToRealAprPage(){
        LOGGER.debug("Navigating to the real apr page");
        ActOn.element(driver,RealAprLink).click();
        return new RealApr(driver);
    }

        //Navigate to HomePage
    public Home_Page navigateToHomePage(){
        LOGGER.debug("Navigating to the Home page");
        ActOn.element(driver,MortgageCalLogo).click();
        return new Home_Page(driver);
    }

}
