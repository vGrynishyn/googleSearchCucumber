package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import googleTest.pages.HomePage;
import googleTest.pages.SearchResPage;
import org.testng.Assert;

public class StepDefinitions {

    private SearchResPage searchPage = new SearchResPage();
    private HomePage homePage = new HomePage();

    private String url = System.getProperty("URL");
    private String pattern = System.getProperty("PATTERN");
    private String expectedDomainName = System.getProperty("EXPECTED_DOMAIN_NAME");

    @Given("I go to google.com")
    public void i_go_to_google_com() {
        homePage.openWebPage(url);
    }

    @When("I search word 'automation'")
    public void i_search_word_automation() {
        homePage.fillSearchField(pattern);
    }

    @Then("I look for expected word in search results")
    public void i_look_for_expected_word_in_search_results() {
        searchPage.clickFirstSearchResultLinks();
        Assert.assertTrue(homePage.getTitle().contains("automation"), "Browser title not contain expected result.");
    }

    @Then("I look for domain on next 5 pages")
    public void i_look_for_domain_on_next_5_pages(){
        String actualLink = searchPage
                .getSearchLinkFromResults(expectedDomainName, 5);
        Assert.assertTrue(actualLink.contains(expectedDomainName), "There is not found expected domain name(page:1-5).");
    }
}
