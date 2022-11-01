Feature: As a user, I want to read sports news

  @DailyMail @HomePage
  Scenario: Verify Homepage
    Given I am visiting the Homepage
    Then The weather day should be the present day with correct date.

  @DailyMail @Sports
  Scenario: Verify Sport nav color
    Given I am visiting the Homepage
    And navigate to sports
    Then the primary and secondary nav has same color.
