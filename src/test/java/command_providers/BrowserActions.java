package command_providers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class BrowserActions {
    private WebDriver driver;

    public BrowserActions(WebDriver driver){
        this.driver = driver;
    }

    //open Browser
    public BrowserActions openBrowser(String url){
        driver.manage().deleteAllCookies();
        driver.get(url);
        driver.manage().window().maximize();
        return this;
    }

    //Close Browser
    public BrowserActions closeBrowser(){
        driver.quit();
        return this;
    }

    //capture the Title of the Browser
    public String captureTitle(){
        return driver.getTitle();
    }
}
