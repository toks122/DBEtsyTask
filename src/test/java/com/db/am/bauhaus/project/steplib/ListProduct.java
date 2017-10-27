package com.db.am.bauhaus.project.steplib;

import com.db.am.bauhaus.project.services.Listing;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

import java.util.List;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by oluseunorebajo on 26/10/2017.
 */
public class ListProduct extends ScenarioSteps {

	private static final long serialVersionUID = 1L;
	private Listing listing;
    private Response response;
    private ValidatableResponse validatableResponse;

    @Step
    public void send_listing_request()  {
        listing.sendListingRequest();
    }

    @Step
    public void the_listing_contains_the_following_data(Map<String,String> data){
        validatableResponse = response.then();
        for (Map.Entry<String, String> field : data.entrySet()) {
			if(StringUtils.isNumeric(field.getValue())){
				validatableResponse.body(field.getKey(), hasItem(Integer.parseInt(field.getValue())));
			}
			else{
				validatableResponse.body(field.getKey(), hasItem(field.getValue()));
			}
		}
    }

    @Step
    public void the_listing_is_returned_successfully(){
        response.then().statusCode(200);
		response.then().body("count",  is(1));
    }

    @Step
    public void set_listing_id(String listingId) {
        listing.setListingId(listingId);
    }

    @Step
    public void request_listing() {
       listing.sendListingRequest();
       response = listing.getListingResponse();
    }

    @Step
    public void set_product_tags(String tagName){
        listing.setProductTag(tagName);
    }

	public void verifyProductTag() {
		String tag = listing.getProductTag();
		List<List<String>> tagSets = response.path("results.tags");
		for(List<String> tagSet : tagSets) {
            System.out.println("tags are:"+tagSet);
            assertThat(tagSet, hasItem(tag));
		}
	}
}
