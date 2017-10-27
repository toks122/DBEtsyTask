package com.db.am.bauhaus.project.steplib;

import com.db.am.bauhaus.project.pages.MainSearchPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Every.everyItem;


/**
 * Created by ongshir on 05/10/2016.
 */
public class SearchUser extends ScenarioSteps {

	private static final long serialVersionUID = 1L;

	MainSearchPage mainSearchPage;

    @Step
    public void search_from_input_box(String query) {
        mainSearchPage.searchFromInputBox(query);
    }
    
    @Step
    public void search_from_dropdown_menu(String menu, String subitem) {
        mainSearchPage.searchFromDropDownMenu(menu, subitem);
    }
    
    @Step
	public void search_from_category_icons(String query) {
    		mainSearchPage.searchFromCategoryIcons(query);
	}

    @Step
    public void verify_result_for_top_categories(String query) {
    		assertThat(mainSearchPage.getTopCategoriesHeader(), everyItem(containsString(query)));
     }

    @Step
    public void verify_result_for_all_categories(String query) {
        assertThat(mainSearchPage.getAllCategoriesHeader(), containsString(query));
    }
}
