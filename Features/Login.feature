Feature: Login
  Scenario: Successful Login with Valid credentials
    Given User Launch Chrome browser
    When User open URL "https://thisheart.co/login"
    And User enter Email as "hhrahat2019@gmail.com" and Password as "h@sib12345@"
    And Click on Login
#    Then Page title should be "Dashboard / nopCommerce administration"
#    When User click on Log out link
#    Then Page title should be "Your store. Login"
    And close browser

#   Scenario Outline: Login Data Driven
#     Given User Launch Chrome browser
#     When User open URL "https://admin-demo.nopcommerce.com/login"
#     And User enter Email as "<email>" and Password as "<password>"
#     And Click on Login
#     Then Page title should be "Dashboard / nopCommerce administration"
#     When User click on Log out link
#     Then Page title should be "Your store. Login"
#     And close browser
#
#     Examples:
#     | email | password |
#     | admin@yourstore.com  |  admin |
#     | admin1@yourstore.com  |  admin123 |