@login
Feature: Login Feature

  Scenario: ValidLogin;Validate login functionality
    Given User is on landing page
    When User clicks on MyMessages link
    And User logs in with username as "test_user" and password as "1234"
    Then My messages page should be displayed

  Scenario: NewsPageTest;Validate news page
    Given User is on landing page
    When User clicks on News link