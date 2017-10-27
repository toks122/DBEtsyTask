package com.db.am.bauhaus.project;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.thucydides.core.annotations.Step;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;

/**
 * Created by ongshir on 06/04/2017.
 */
public class SearchFor implements Task {

    private String searchText;
    private final static List<String> textList = Arrays.asList("apple", "orange", "banana", "kiwi");

    @Step("{0} enters search text #searchText")
    public <T extends Actor> void performAs(T theUser) {
        theUser.should(
                seeThat("the search input box", the(SearchTarget.INPUT_BOX), isVisible())
        );

        theUser.attemptsTo(
                Enter.theValue(searchText).into(SearchTarget.INPUT_BOX),
                Click.on(SearchTarget.INPUT_BOX_BUTTON)
        );
        Serenity.setSessionVariable(SessionVar.SEARCH_TEXT).to(searchText);
    }

    public static SearchFor randomText() {
        String selectedText = textList.get(new Random().nextInt(textList.size()));
        return Instrumented.instanceOf(SearchFor.class).withProperties(selectedText);
    }

    public static SearchFor textCalled(String searchText) {
        return Instrumented.instanceOf(SearchFor.class).withProperties(searchText);
    }

    public SearchFor(String searchText) {
        this.searchText = searchText;
    }

}
