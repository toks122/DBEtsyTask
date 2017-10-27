package com.db.am.bauhaus.project.steps;

import java.util.Map;

import com.db.am.bauhaus.project.steplib.ListProduct;
import net.thucydides.core.annotations.Steps;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class ListingSteps {
	@Steps
	ListProduct listProduct;

	@Given("^there is a listing with id (.*)$")
	public void there_is_a_listing_with_id(String listingId) {
		listProduct.set_listing_id(listingId);
	}

	@When("^^(?:.*) requests to retrieve the listing$")
	public void request_listing() {
		listProduct.request_listing();
	}

	@Then("^the listing is returned successfully$")
	public void the_listing_is_returned_successfully() {
		listProduct.the_listing_is_returned_successfully();
	}

	@Then("^the listing contains the following data$")
	public void the_listing_contains_the_following_data(Map<String,String> data) {
		listProduct.the_listing_contains_the_following_data(data);
	}
}
