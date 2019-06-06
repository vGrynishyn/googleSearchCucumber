import googleTest.BasePage;
import googleTest.pages.HomePage;
import googleTest.utils.LogInformation;
import googleTest.utils.TestListener;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Collection;

@Listeners({TestListener.class})
public class LocalTest extends BasePage {

    @AfterMethod
    public void afterTest() {
        browser.closeBrowser();
    }

    @Test(priority = 1)
    @Parameters("pattern")
    public void searchPatternAndCheckBrowserTitleTest(@Optional(PATTERN) String pattern) {
        new HomePage()
                .openWebPage(URL)
                .searchPattern(pattern)
                .clickNeededNumOfResultLinks(0);
        Assert.assertTrue(browser.getTitle().contains(pattern));
    }

    @Test(priority = 2)
    @Parameters({"pattern", "pagesNum", "expectedDomainName"})
    public void tryToFindExpectedDomainNameTest(@Optional(PATTERN) String pattern, @Optional("5") int pagesNum, @Optional(EXPECTED_DOMAIN_NAME) String expectedDomainName) {
        String actualLink = new HomePage()
                .openWebPage(URL)
                .searchPattern(pattern)
                .getSearchLinkFromResults(expectedDomainName, pagesNum);
        Assert.assertTrue(actualLink.contains(expectedDomainName), "There is not found expected domain name(page:1-5).");
    }

    @DataProvider(name = "foundLinks", parallel = false)
    public Object[][] getLinks() {
        ArrayList<String> links = new HomePage()
                .openWebPage(URL)
                .searchPattern(PATTERN + "day")
                .getResultLinks();
        Collection<Object[][]> data = new ArrayList();
        links.forEach(item -> ((ArrayList) data).add(new Object[]{item}));
        browser.closeBrowser();
        return data.toArray(new Object[0][]);
    }

    @Test(priority = 3, dataProvider = "foundLinks")
    @Parameters("foundLink")
    public void checkIfGoogleSearchCanFindTestAutomationDayDomainTest(String foundLink) {
        LogInformation.info(String.format("Verify expected domain name '%s' is present in %s.", EXPECTED_DOMAIN_NAME, foundLink));
        Assert.assertTrue(foundLink.contains(EXPECTED_DOMAIN_NAME), String.format("Could not find expected domain name '%s' in '%s' link.", EXPECTED_DOMAIN_NAME, foundLink));
    }
}