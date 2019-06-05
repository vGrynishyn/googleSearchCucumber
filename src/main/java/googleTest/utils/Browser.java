package googleTest.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Browser {

    public void openWebPage(String url) {
        LogInformation.info(String.format("Loading googleTest.browser with %s", url));
        WebDriver wb = Driver.getWebDriver();
        wb.manage().window().maximize();
        wb.get(url);
    }

    public String getTitle() {
        LogInformation.info("Get googleTest.browser title.");
        return Driver.getWebDriver().getTitle().toLowerCase();
    }

    public void closeBrowser() {
        if (Driver.getWebDriver(false) != null) {
            LogInformation.info("Close googleTest.browser.");
            Driver.getWebDriver().quit();
            Driver.setWebDriver(null);
        }
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) Driver.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
