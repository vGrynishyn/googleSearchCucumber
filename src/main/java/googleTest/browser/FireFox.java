package googleTest.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFox {

    private WebDriver driver;
    public WebDriver getDriver(){
        return this.driver;
    }

    public FireFox(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }
}
