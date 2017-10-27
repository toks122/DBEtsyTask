package com.db.am.bauhaus.project.steps;

import com.db.am.bauhaus.project.SearchFor;
import com.db.am.bauhaus.project.SearchTarget;
import com.db.am.bauhaus.project.SessionVar;
import com.db.am.bauhaus.project.pages.MainSearchPage;
import com.db.am.bauhaus.project.steplib.ListProduct;
import com.db.am.bauhaus.project.steplib.SearchUser;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Steps;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.containsText;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;

/**
 * Created by ongshir on 05/10/2016.
 */
public class SearchSteps {

    @Before
    public void before() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Steps
    SearchUser user;

    @Steps
    ListProduct listProduct;

    MainSearchPage mainSearchPage;
    private String query;

    @Given("^John is viewing the Etsy landing page$")
    public void goto_landing_page() {
        mainSearchPage.open();
    }

    @Given("^([^\\s]+) is viewing the Etsy landing page \\(screenplay\\)$")
    public void goto_landing_page_screenplay(String theUser) {
        theActorCalled(theUser).attemptsTo(Open.browserOn().the(mainSearchPage));
    }



    @When("^he searches for (.*) from the input box$")
    public void search_from_input_box(String query) {
        this.query = query;
        user.search_from_input_box(query);
    }
    
    @When("^he searches for (.*) -> (.*) from the drop-down menu$")
    public void search_from_dropdown_menu(String menu, String subitem) {
        query = subitem;
        user.search_from_dropdown_menu(menu, subitem);
    }
    
    @When("^he searches for (.*) from the Shop By Category icons$")
    public void search_from_category_icons(String query) {
        this.query = query;
        user.search_from_category_icons(query);
    }

    @When("^he searches for a product from the input box \\(screenplay\\)$")
    public void search_from_input_box_screenplay() {
        theActorInTheSpotlight().attemptsTo(SearchFor.randomText());
    }

    @Then("^the result should be displayed$")
    public void verify_search_result() {
        user.verify_result_for_top_categories(query);
        user.verify_result_for_all_categories(query);
    }
    
    @Then("^the Top Categories should all contain the search query$")
    public void verify_top_categories_search_result() {
        user.verify_result_for_top_categories(query);
    }
    
    @Then("^the All Categories breadcrumb should contain the search query$")
    public void verify_all_categories_search_result() {
        user.verify_result_for_all_categories(query);
    }

    @Then("^the result should be displayed \\(screenplay\\)$")
    public void verify_search_result_screenplay() {
        String searchText = Serenity.sessionVariableCalled(SessionVar.SEARCH_TEXT).toString();
        theActorInTheSpotlight().should(
                seeThat("the top categories header ", the(SearchTarget.TOP_CATEGORIES_HEADER), containsText(searchText)),
                seeThat("the all categories header ", the(SearchTarget.ALL_CATEGORIES_HEADER), containsText(searchText))
        );
    }

    @Given("^there are products tagged as (.*)$")
    public void thereAreProductsTagged(String value) {
        listProduct.set_product_tags(value);
    }

    @When("^(?:.*) searches for products with this tag$")
    public void searchForProductsWithThisTag() {
        listProduct.request_listing();
    }

    @Then("^all listings returned should contain the tag$")
    public void allListingsReturnedShouldContainTag() {
    		listProduct.verifyProductTag();
    }
}
