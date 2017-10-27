Feature: Search

  @ui @pageobject
  Scenario: Should be able to search for a product from the input box
    Given John is viewing the Etsy landing page
    When he searches for craft from the input box
    Then the Top Categories should all contain the search query
    And the All Categories breadcrumb should contain the search query

  @ui @screenplay
  Scenario: Should be able to search for a product from the input box (screenplay)
    Given John is viewing the Etsy landing page (screenplay)
    When he searches for a product from the input box (screenplay)
    Then the result should be displayed (screenplay)

  @ui @pageobject
  Scenario: Should be able to search for a product from the drop-down menu
 	Given John is viewing the Etsy landing page
  	When he searches for Home & Living -> Clocks from the drop-down menu
  	Then the All Categories breadcrumb should contain the search query 

  @ui @pageobject
  Scenario: Should be able to search for a product from the icons
	Given John is viewing the Etsy landing page
	When he searches for Clothing from the Shop By Category icons
	Then the All Categories breadcrumb should contain the search query

  @webservices
  Scenario: Should be able to search for products by tags
    Given there are products tagged as olu amu
    When John searches for products with this tag
    Then all listings returned should contain the tag
