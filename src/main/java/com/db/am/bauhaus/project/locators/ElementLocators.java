package com.db.am.bauhaus.project.locators;

public class ElementLocators {

	public final static String SEARCH_INPUT_BOX_ID = "search-query";
	public final static String SEARCH_BUTTON_XPATH = "//button[@value='Search']";
	public final static String SEARCH_TOP_CATEGORIES_HEADER_XPATH = "//span[contains(@class, 'guided-search-button-wrapper')]/a";
	public final static String SEARCH_ALL_CATEGORIES_HEADER_XPATH = "//*[contains(@itemtype, 'Breadcrumb')]/..";
	
	public final static String MAIN_MENU_ITEM_XPATH_FORMAT = "//li[contains(@class, 'top-nav-item')]/*[contains(text(),  '%s')]";
	public final static String MAIN_MENU_SUBITEM_XPATH_FORMAT = "//div[contains(@class, 'sub-nav-container') and @aria-hidden='false']/descendant::a[normalize-space(text())='%s']";
	
	public final static String SHOP_BY_CATEGORY_BLOCK_XPATH_FORMAT = "//span[text()='%s']/ancestor::div[contains(@class, 'vesta-hp-category-card')]";
}
