package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import googleTest.BasePage;
import googleTest.pages.SearchResPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class StepDefinitions extends BasePage {

    private SearchResPage searchPage = new SearchResPage();

    @FindBy(css = "input[name=q]")
    private WebElement searchInput;

    @Given("I go to google.com")
    public void i_go_to_google_com() {
        browser.openWebPage(URL);
    }

    @When("I search word 'automation'")
    public void i_search_word_automation() {
        searchInput.sendKeys(PATTERN + Keys.ENTER);
    }

    @Then("I look for expected word in search results")
    public void i_look_for_expected_word_in_search_results() {
        new SearchResPage().clickFirstSearchResultLinks();
        Assert.assertTrue(browser.getTitle().contains("automation"), "Browser title not contain expected result.");
    }

    @Then("I look for domain on next 5 pages")
    public void i_look_for_domain_on_next_5_pages(){
        String actualLink = searchPage
                .getSearchLinkFromResults(EXPECTED_DOMAIN_NAME, 5);
        Assert.assertTrue(actualLink.contains(EXPECTED_DOMAIN_NAME), "There is not found expected domain name(page:1-5).");
    }
}
