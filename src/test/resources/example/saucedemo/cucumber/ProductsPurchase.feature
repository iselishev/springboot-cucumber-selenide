Feature: Products Purchase flow checks

  Background: User is logged into app
    Given User logs into SourceDemo successfully

  Scenario: Check Products info is displayed correctly
    Given total amount of products available is known
    And PRODUCTS page is displayed
    Then amount of products displayed is as expected
    And following products have the details as listed below
      | name                              | price  | description                                                                                                                                               |
      | Sauce Labs Backpack               | $29.99 | carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.                    |
      | Sauce Labs Bolt T-Shirt           | $15.99 | Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.           |
      | Test.allTheThings() T-Shirt (Red) | $15.99 | This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton. |


  Scenario: Check Cart badge displays products added into the cart
    Given PRODUCTS page is displayed
    And User clicks at 'Add to cart' for the following products
    |Sauce Labs Backpack|
    |Sauce Labs Onesie  |
    And Cart icon displays 2
    When User clicks at 'Remove' for the following products
    |Sauce Labs Onesie  |
    Then Cart icon displays 1

  Scenario: Check Products are correctly displayed in the Cart
    Given PRODUCTS page is displayed
    And User adds 2 products into cart
    And added products are stored as ADDED_PRODUCTS
    When User clicks at Cart icon
    And User is at YOUR CART page
    Then products in cart match the ADDED_PRODUCTS

  Scenario: Check Item Total price is correctly calculated
    Given PRODUCTS page is displayed
    And User adds 3 products into cart
    And added products are stored as ADDED_PRODUCTS
    And User clicks at Cart icon
    And User is at YOUR CART page
    When User clicks at 'Checkout'
    And User fills in checkout info
      |John|Smith|10001|
    And User clicks at 'Continue'
    Then total products price is displayed in the 'Item total'

  Scenario: Check end-to-end flow for Products purchase
    Given PRODUCTS page is displayed
    And User adds 3 products into cart
    And added products are stored as ADDED_PRODUCTS
    And User clicks at Cart icon
    And User is at YOUR CART page
    When User clicks at 'Checkout'
    And User fills in checkout info
    |John|Smith|10001|
    And User clicks at 'Continue'
    And User clicks at 'Finish'
    Then User is at CHECKOUT: COMPLETE page
    And Checkout message 'THANK YOU FOR YOUR ORDER' is displayed