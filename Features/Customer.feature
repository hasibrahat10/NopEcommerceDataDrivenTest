Feature: Customer

  Background: Below are the common steps for each scenario
      Given User Launch Chrome browser
      When User open URL "http://admin-demo.nopcommerce.com/login"
      And User enter Email as "admin@yourstore.com" and Password as "admin"
      And Click on Login
      Then User can view Dashboard

#  Scenario: Add a new Customer

#    When User click on customers Menu
#    And click on customers Menu Item
#    And click on Add new button
#    Then User can view Add new customer page
#    When User enter customer info
#    And click on Save button
#    Then User can view confirmation message "The new customer has been added successfully."
#    And close browser


    Scenario: Search Customer by Email ID

      When User click on customers Menu
      And click on customers Menu Item
      And Enter customer Email
      When Click on search button
      Then User should found Email in the Search table
      And close browser


#  Scenario: Search Customer By Name
#
#        When User click on customers Menu
#        And click on customers Menu Item
#        And Enter customer First Name
#        And Enter customer Last Name
#        Then Click on search button
#        Then User should found Name in the Search table
#        And close browser