Feature: As a user, I want to read football news

  @DailyMail @Sports @Football
  Scenario: Verify that sports page loads
    Given I am visiting the FootballPage
    Then navigate to sports tab

  @DailyMail @Sports @Football
  Scenario: Verify that football page loads
    Given I am visiting the FootballPage
    And navigate to sports tab
    Then navigate to football tab

  @DailyMail @Sports @Football
  Scenario: Verify that headline article loads
    Given I am visiting the FootballPage
    And navigate to sports tab
    And navigate to football tab
    Then navigate to headline article

  @DailyMail @Sports @Football
  Scenario: Verify gallery loads full
    Given I am visiting the FootballPage
    And navigate to sports tab
    And navigate to football tab
    And navigate to headline article
    And traverse to the gallery image
    Then should be able to move next
    Then should be able to move prev

  @DailyMail @Sports @Football
  Scenario: Verify gallery can be shared
    Given I am visiting the FootballPage
    And navigate to sports tab
    And navigate to football tab
    And navigate to headline article
    And traverse to the gallery image
    Then should be able to move next
    Then should be able to move prev
    Then should be able to share

  @DailyMail @Sports @Football
  Scenario: Verify gallery video loads, play, maximise and minimise
    Given I am visiting the FootballPage
    And navigate to sports tab
    And navigate to football tab
    And navigate to headline article
    Then should be able to watch video in fullscreen
    Then should be able to back to normal screen

    # Note: Premier league table structure change on website so could not complete the test, it fails at mo.
  @DailyMail @Sports @Football @PremierLeagueTable
  Scenario: Verify the Premier League Table
    Given I am visiting the FootballPage
    And navigate to sports tab
    And navigate to football tab
    And navigate to headline article
    Then display the position Premier League Table for 'Liverpool'
