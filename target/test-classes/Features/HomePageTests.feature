Feature: HomePage Tests

  Scenario: Home page entries
    Given I navigate to the ANZ Home Loan Page
    When I set details as
      | Application Type | Single |
      | Dependents       | 0      |
      | Property type    | Home   |
    And I set earnings as
      | Annual Income | 80000 |
      | OtherIncome   | 10000 |
    And I set expenses as
      | Living Expenses      | 500   |
      | Other Loan repayment | 100   |
      | Credit Card Limits   | 10000 |
    Then I should get the borrowing estimate as "$482,000"


  Scenario: Start Over
    Given I navigate to the ANZ Home Loan Page
    When I use the Start Over option
    Then The borrowing limit should be reset to "$0"


  Scenario: 1$ Entry
    Given I navigate to the ANZ Home Loan Page
    And I set expenses as
      | Living Expenses | 1 |
    Then I verify the prompt to contact helpline




