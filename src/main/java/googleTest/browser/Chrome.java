package googleTest.browser;

import googleTest.utils.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Chrome {
    private WebDriver driver;

    public WebDriver getDriver() {
        return this.driver;
    }

    public Chrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(Driver.BrowserType.findByName(Driver.BrowserType.CHROME.getName()).getOptions());
        this.driver = new ChromeDriver(options);
    }
}
