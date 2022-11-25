
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

 Background:
 Given I landed on Ecommerce Page.

  @Regression
  Scenario Outline: Positive test of Submitting the order.
    Given Logged in with username <name> and password <password>
    When I add product "<ProductName>" to cart.
    And Checkout "<ProductName>" and Submit the order.
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage.

    Examples: 
      | name                      |     password        | ProductName |
      | nishchay.angra@gmail.com  |     Nishchay@18     | ZARA COAT 3 |
     
