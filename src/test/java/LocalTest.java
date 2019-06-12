import googleTest.pages.HomePage;
import googleTest.utils.LogInformation;
import googleTest.utils.TestListener;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Collection;

@Listeners({TestListener.class})
public class LocalTest {

    private String url;
    private String pattern;
    private String expectedDomainName;

    @BeforeTest
    public void beforeTest() {
        url = System.getProperty("URL");
        pattern = System.getProperty("PATTERN");
        expectedDomainName = System.getProperty("EXPECTED_DOMAIN_NAME");
    }

    @AfterTest
    public void afterTest() {
        new HomePage().closeWindow();
    }

    @Test(priority = 1)
    @Parameters("pattern")
    public void searchPatternAndCheckBrowserTitleTest(@Optional("automation") String pattern) {
        String browserTitlenew = new HomePage()
                .openWebPage(url)
                .searchPattern(pattern)
                .clickNeededNumOfResultLinks(0)
                .getTitle();
        Assert.assertTrue(browserTitlenew.contains(pattern), "There ara not expected pattern in browser title");
    }

    @Test(priority = 2)
    @Parameters({"pattern", "pagesNum", "expectedDomainName"})
    public void tryToFindExpectedDomainNameTest(@Optional("automationday") String pattern, @Optional("5") int pagesNum, @Optional("testautomationday.com") String expectedDomainName) {
        String actualLink = new HomePage()
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

    @DataProvider(name = "foundLinks", parallel = true)
    public Object[][] getLinks() {
        ArrayList<String> links = new HomePage()
                .openWebPage(url)
                .searchPattern(pattern + "day")
                .getResultLinks();
        Collection<Object[][]> data = new ArrayList();
        links.forEach(item -> ((ArrayList) data).add(new Object[]{item}));
        return data.toArray(new Object[0][]);
    }
}