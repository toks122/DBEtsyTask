Feature: Listings

  @webservices
  Scenario: Listing should provide key information
    Given there is a listing with id 553562134
    When John requests to retrieve the listing
    Then the listing is returned successfully
    And the listing contains the following data 
	| results.state 			| active  |   
	| results.category_id	| 69151567|	
	| results.title			| 925 Sterling Silver Dog Necklace with Silver Chain, Animal Jewelry Pet Gift for Men or Women, Puppy Dog Pendant Silver |
	