import googleTest.pages.HomePage;
import googleTest.utils.LogInformation;
import googleTest.utils.TestListener;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Collection;

import static googleTest.BasePage.browser;

@Listeners({TestListener.class})
public class LocalTest{

    private HomePage homePage;
    private String url;
    private String pattern;
    private String expectedDomainName;

    @BeforeTest
    public void beforeTest() {
        homePage = new HomePage();
        url = System.getProperty("URL");
        pattern = System.getProperty("PATTERN");
        expectedDomainName = System.getProperty("EXPECTED_DOMAIN_NAME");
    }

    @AfterTest
    public void afterTest() {
        homePage.closeWindow();
    }

    @Test(priority = 1)
    @Parameters("pattern")
    public void searchPatternAndCheckBrowserTitleTest(@Optional("automation") String pattern) {
        homePage
                .openWebPage(url)
                .searchPattern(pattern)
                .clickNeededNumOfResultLinks(0);
        Assert.assertTrue(homePage.getTitle().contains(pattern));
    }

    @Test(priority = 2)
    @Parameters({"pattern", "pagesNum", "expectedDomainName"})
    public void tryToFindExpectedDomainNameTest(@Optional("automation") String pattern, @Optional("5") int pagesNum, @Optional("testautomationday.com") String expectedDomainName) {
        String actualLink = homePage
                .openWebPage(url)
                .searchPattern(pattern)
                .getSearchLinkFromResults(expectedDomainName, pagesNum);
        Assert.assertTrue(actualLink.contains(expectedDomainName), "There is not found expected domain name(page:1-5).");
    }

    @Test(priority = 3, dataProvider = "foundLinks")
    @Parameters("foundLink")
    public void checkIfGoogleSearchCanFindTestAutomationDayDomainTest(String foundLink) {
        LogInformation.info(String.format("Verify expected domain name '%s' is present in %s.", expectedDomainName, foundLink));
        Assert.assertTrue(foundLink.contains(expectedDomainName), String.format("Could not find expected domain name '%s' in '%s' link.", expectedDomainName, foundLink));
    }

    @DataProvider(name = "foundLinks", parallel = false)
    public Object[][] getLinks() {
        ArrayList<String> links = homePage
                .openWebPage(url)
                .searchPattern(pattern + "day")
                .getResultLinks();
        Collection<Object[][]> data = new ArrayList();
        links.forEach(item -> ((ArrayList) data).add(new Object[]{item}));
        browser.closeBrowser();
        return data.toArray(new Object[0][]);
    }
}