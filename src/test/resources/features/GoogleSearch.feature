Feature: google search testing

    Scenario: google search some word
        Given I go to google.com
        When I search word 'automation'
        Then I look for expected word in search results

    Scenario: Verify existing domain (“testautomationday.com”)
        Given I go to google.com
        When I search word 'automation'
        Then I look for domain on next 5 pages