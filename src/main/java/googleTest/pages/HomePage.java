package googleTest.pages;

import googleTest.BasePage;
import googleTest.utils.LogInformation;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = "input[name=q]")
    private WebElement searchInput;

    /**
     * Open webpage
     * @param url
     * @return
     */
    public HomePage openWebPage(String url){
        LogInformation.info(String.format("Open '%s' address", url));

        browser.openWebPage(url);
        if (!searchInput.isDisplayed())
            LogInformation.error(String.format("Web page %s has not been loaded.", url));
        return this;
    }

    /**
     * Search for specified pattern
     * @param pattern
     * @return
     */
    public SearchResPage searchPattern(String pattern){
        LogInformation.info(String.format("Search for '%s' pattern", pattern));
        searchInput.sendKeys(pattern + Keys.ENTER);
        return new SearchResPage();
    }
}
