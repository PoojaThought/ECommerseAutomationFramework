
@tag
Feature: Purchase order from ECommerce site
  I want to use this template for my feature file

	Background:
	Given I landed on ECommerce Page

  @Regression
  Scenario Outline: Positive test of Submitting the order
    Given Logged in with username <name> and password <pass>
    When I add product <prodName> into Cart
    And Checkout <prodName> and submit the order  
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page 

    Examples: 
      | name                     | pass          |  prodName          |
      | prd.pro.tester@gmail.com |     Test@1234 |     ZARA COAT 3    |
     
