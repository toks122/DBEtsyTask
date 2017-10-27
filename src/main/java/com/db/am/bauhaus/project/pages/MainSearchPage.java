package com.db.am.bauhaus.project.pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.db.am.bauhaus.project.locators.ElementLocators;

/**
 * Created by ongshir on 05/10/2016.
 */
@DefaultUrl("/")
public class MainSearchPage extends PageObject {
	

    @FindBy(id = ElementLocators.SEARCH_INPUT_BOX_ID)
    WebElementFacade inputBox;

    @FindBy(xpath = ElementLocators.SEARCH_BUTTON_XPATH)
    WebElementFacade searchButton;
    
    public MainSearchPage(WebDriver driver) {
        super(driver);
    }

    public void searchFromInputBox(String searchText) {
        inputBox.waitUntilPresent().sendKeys(searchText);
        searchButton.click();
    }
    
    public void searchFromDropDownMenu(String menu, String subitem) {    		
    		WebElement menuElement = getDriver().findElement(By.xpath(String.format(ElementLocators.MAIN_MENU_ITEM_XPATH_FORMAT, menu)));
    		new Actions(getDriver()).moveToElement(menuElement).build().perform();
    		element(By.xpath(String.format(ElementLocators.MAIN_MENU_SUBITEM_XPATH_FORMAT, subitem))).click();
    }
    
	public void searchFromCategoryIcons(String categoryTitle) {
		List<WebElementFacade> categories = findAll(By.xpath(String.format(ElementLocators.SHOP_BY_CATEGORY_BLOCK_XPATH_FORMAT, categoryTitle)));

		//Handles responsiveness of app. There are two of these in the DOM - one for mobile and one for web. Click the one that's visible
		for(WebElementFacade category : categories) {
			if(category.isCurrentlyVisible()) {
				((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", category);
				category.click();
				return;
			}
		}
	}

    public List<String> getTopCategoriesHeader() {
        List<WebElementFacade> buttons = getGuidedSearchButtons();
        List<String> labels = new ArrayList<String>();
        for(WebElementFacade button : buttons) {
        		labels.add(button.getText());
        }
        return labels;
    }

    public String getAllCategoriesHeader() {
        return find(By.xpath(ElementLocators.SEARCH_ALL_CATEGORIES_HEADER_XPATH)).getText();
    }
     
    private List<WebElementFacade> getGuidedSearchButtons(){
    		return findAll(By.xpath(ElementLocators.SEARCH_TOP_CATEGORIES_HEADER_XPATH));
    }
}
