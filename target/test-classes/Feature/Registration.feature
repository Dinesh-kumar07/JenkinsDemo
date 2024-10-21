Feature: Verifying Login Automation

  Scenario Outline: Verifying User 
    Given User  is on registration page
    When User login "<username>","<emailId>","<password>" and "<confirm password>"
    Then User should verify success message after Login "Registration Successful"

    Examples: 
      | username   | emailId                     | password   | confirm password |
      | manivannan | manimailupuli3696@gmail.com | ManiV@3696 | ManiV@3696       |
