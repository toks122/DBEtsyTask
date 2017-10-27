package com.db.am.bauhaus.project.services;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.util.SystemEnvironmentVariables;

import java.util.Map;

import org.openqa.selenium.remote.http.HttpMethod;

import static io.restassured.RestAssured.given;



/**
 * Created by oluseunorebajo on 26/10/2017.
 */
public class Request {
    private String url;
    private HttpMethod method;
    private Map<String, String> getParameters;
    private Map<String, String> postParameters;
    private Map<String, String> headers;
    public Response response;

    public Request(HttpMethod method,String url,Map<String, String> headers,  Map<String, String> getParameters,Map<String, String> postParameters ){
        this.method = method;
        this.url = url;
        this.headers = headers;
        this.getParameters = getParameters;
        this.postParameters = postParameters;
    }

    private RequestSpecification buildRequest(){
        RequestSpecification requestSpecification;
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        requestSpecification =
                requestSpecBuilder
                        .setBaseUri(url)
                        .addHeaders(headers)
                        .build();
        return given().log().all().spec(requestSpecification).param("api_key", SystemEnvironmentVariables.createEnvironmentVariables().getProperty("api.key"));
    }

    public Response sendRequest(){
        switch (method){
            case GET:
                response = buildRequest().params(getParameters).when().get().then().extract().response();
                break;
            case POST:
                response = buildRequest().params(postParameters).when().post().then().extract().response();
                break;
            case DELETE:
                response = buildRequest().params(postParameters).when().delete().then().extract().response();
                break;
        }
        return response;
    }

    public Response getResponse(){
        return response;
    }
}
