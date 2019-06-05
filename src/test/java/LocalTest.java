import googleTest.BasePage;
import googleTest.pages.HomePage;
import googleTest.utils.Browser;
import googleTest.utils.LogInformation;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Collection;

import static googleTest.BasePage.browser;

/**
 * @author vGrynishyn
 * @since 31/09/2017
 */

//@Listeners({TestListener.class})
public class LocalTest extends BasePage {

    private static final String URL = "https://www.google.com.ua/";
    private static final String PATTERN = "automation";
    private static final String EXPECTED_DOMAIN_NAME = "testautomationday.com";


    @AfterMethod
    public static void afterTest(){
        browser.closeBrowser();
    }

    @Test
    @Parameters("pattern")
    public void searchPatternAndCheckBrowserTitleTest(@Optional(PATTERN) String pattern){
        new HomePage()
                .openWebPage(URL)
                .searchPattern(pattern)
                .clickNeededNumOfResultLinks(0);
        Assert.assertTrue(browser.getTitle().contains(pattern));
    }

    @Test
    @Parameters({"pattern", "pagesNum", "expectedDomainName"})
    public void tryToFindExpectedDomainNameTest(@Optional(PATTERN) String pattern, @Optional("5") int pagesNum, @Optional(EXPECTED_DOMAIN_NAME) String expectedDomainName){
        String actualLink = new HomePage()
                .openWebPage(URL)
                .searchPattern(pattern)
                .getSearchLinkFromResults(expectedDomainName, pagesNum);
        Assert.assertTrue(actualLink.contains(expectedDomainName), "There is not found expected domain name(page:1-5).");
    }

    @Test(dataProvider = "foundLinks")
    @Parameters("linkText")
    public void checkIfGoogleSearchCanFindTestAutomationDayDomainTest(String foundLink){
        LogInformation.info(String.format("Verify expected domain name '%s' is present in %s.",EXPECTED_DOMAIN_NAME, foundLink));
        Assert.assertTrue(foundLink.contains(EXPECTED_DOMAIN_NAME), String.format("Could not find expected domain name '%s' in '%s' link.", EXPECTED_DOMAIN_NAME, foundLink));
    }

    @DataProvider(name = "foundLinks", parallel = true)
    public Object[][] getLinks() {
        ArrayList<String> links = (new HomePage())
                .openWebPage(URL)
                .searchPattern(PATTERN+"day")
                .getResultLinks();
        Collection<Object[]> data = new ArrayList();
        links.forEach(item -> data.add(new Object[]{item}));
        browser.closeBrowser();
        return (Object[][]) links.toArray();
    }
}