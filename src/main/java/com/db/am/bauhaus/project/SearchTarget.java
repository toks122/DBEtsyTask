package com.db.am.bauhaus.project;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

import com.db.am.bauhaus.project.locators.ElementLocators;

/**
 * Created by ongshir on 06/04/2017.
 */
public class SearchTarget {
    public final static Target INPUT_BOX = Target.the("the search input box").located(By.id(ElementLocators.SEARCH_INPUT_BOX_ID));
    public final static Target INPUT_BOX_BUTTON = Target.the("the search input button").located(By.xpath(ElementLocators.SEARCH_BUTTON_XPATH));
    public final static Target TOP_CATEGORIES_HEADER = Target.the("the top categories header").located(By.xpath(ElementLocators.SEARCH_TOP_CATEGORIES_HEADER_XPATH));
    public final static Target ALL_CATEGORIES_HEADER = Target.the("the all categories header").located(By.xpath(ElementLocators.SEARCH_ALL_CATEGORIES_HEADER_XPATH));
}
