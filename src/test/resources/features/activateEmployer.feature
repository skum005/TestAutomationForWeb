@employer
Feature: Activate Employer

  @activate_employer
  Scenario: ActivateEmployer;As an admin I should be able to activate a new employer
    Given User is on login page
    When User logs in with username as "santosh.sri55" and password as "Testing@1234"
    And User searches for an organisation register number "09783278"
    And User fills organisation details as below
      | tradingName    | postcode | industryType  | numOfWorkers | payeeRefNumber | emailAddress    | howDidYouFindUs |
      | TestAutomation | HX3 6BP  | Manufacturing | 50 - 249     | 123/A57867     | dummy@dummy.com | TPR             |
    And User agrees to terms of use & privacy policy of NowPensions
    And User clicks on continue button


