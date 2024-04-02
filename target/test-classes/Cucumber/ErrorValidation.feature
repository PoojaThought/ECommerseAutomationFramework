
@tag
Feature: Error Validation
  I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline: Title of your scenario outline
   	Given I landed on ECommerce Page
    When Logged in with username <name> and password <pass>
    Then "Incorrect email  password." error message is displayed

    Examples: 
    Examples: 
      | name                     | pass        | 
      | prd.pro.tester@gmail.com |     Test@14 |   
