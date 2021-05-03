Feature:Login Page Checks

  Background: Navigate to the home page
    Given the user passes to the SourceDemo Home page


  Scenario Outline: Check User get error messages when wrong credentials
    Given User fills in username '<username>' and password '<password>'
    When User clicks 'Login'
    Then Error message is displayed '<message>'
    Examples:
      | username        | password     | message                                                                   |
      | test            | test         | Epic sadface: Username and password do not match any user in this service |
      | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.                       |


  Scenario: Check User can login to SourceDemo successfully
    When User logs into SourceDemo successfully
    Then PRODUCTS page is displayed

