package com.db.am.bauhaus.project.services;

import io.restassured.response.Response;
import net.serenitybdd.core.pages.PageObject;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.remote.http.HttpMethod;

import static com.db.am.bauhaus.project.services.Service.constructUrl;

/**
 * Created by oluseunorebajo on 26/10/2017.
 */
public class Listing extends PageObject {
	private String endpoint;
	
	private Map<String,String> mapForHeaders;
	private Map<String,String> getParams;
	private Response response;

	public Listing(){
		mapForHeaders = new HashMap<>();
		getParams = new HashMap<>();
		mapForHeaders.put("Content-Type","application/json");
		mapForHeaders.put("Accept","application/json");
		endpoint = "listings/active";
	}

	public void sendListingRequest() {
		String url = constructUrl(endpoint);
		Request request = new Request(HttpMethod.GET, url, mapForHeaders, getParams, null);
		response = request.sendRequest();
	}

	public Response getListingResponse() {
		return response;
	}

	public void setListingId(String listingId) {
		endpoint = "listings/" + listingId;
	}

	public void setProductTag(String tagName) {
		getParams.put("tags", tagName);
	}
	
	public String getProductTag() {
		return getParams.get("tags");
	}
}
