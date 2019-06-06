package googleTest;

import googleTest.utils.Browser;
import org.openqa.selenium.support.PageFactory;

import static googleTest.utils.Driver.getWebDriver;

public abstract class BasePage {

    public static Browser browser = new Browser();
    protected final String URL = "https://www.google.com.ua/";
    protected final String PATTERN = "automation";
    protected final String EXPECTED_DOMAIN_NAME = "testautomationday.com";

    public BasePage(){
        PageFactory.initElements(getWebDriver(), this);
    }

}
