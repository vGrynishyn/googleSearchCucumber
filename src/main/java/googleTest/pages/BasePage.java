package googleTest.pages;

import googleTest.utils.Browser;
import org.openqa.selenium.support.PageFactory;

import static googleTest.utils.Driver.getWebDriver;

public abstract class BasePage {

    public static Browser browser = new Browser();

    public BasePage() {
        PageFactory.initElements(getWebDriver(), this);
    }

    public String getTitle() {
        return browser.getTitle();
    }
}
