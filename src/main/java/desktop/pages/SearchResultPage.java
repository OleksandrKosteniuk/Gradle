package desktop.pages;

import abstractClasses.fragment.AbstractFragment;
import abstractClasses.page.AbstractPage;
import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
//import static org.assertj.core.api.Assertions.assertThat;

public class SearchResultPage extends AbstractFragment {
    
    //page URL
    private static final String SEARCH_RESULT_PAGE_URL = "https://www.bookdepository.com/search";
    
    //General Book title
    private static final By BOOK_TITLE = By.xpath("//*[@class='title']");
    
    //Add to Basket Button
    private static final By ADD_TO_BASKET_BUTTON = By.xpath("//*[@class='btn btn-sm btn-primary add-to-basket']");
    private static final By ADD_TO_BASKET_BELOW_THINKING_IN_JAVA_BUTTON = By.xpath("//div[@class='book-item'][contains(.,'Thinking in Java')]//a[contains(@class,'add-to-basket')]");
        
    //Refinement filters
    private static final By REFINE_RESULTS_BUTTON = By.xpath("//*[contains(button,'Refine results')]");
    private static final By PRICE_RANGE_REFINEMENT_FILTER = By.xpath("//*[@id='filterPrice']");
    private static final By AVAILABILITY_REFINEMENT_FILTER = By.xpath("//*[@id='filterAvailability']");
    private static final By LANGUAGE_REFINEMENT_FILTER = By.xpath("//*[@id='filterLang']");
    private static final By FORMAT_REFINEMENT_FILTER = By.xpath("//*[@id='filterFormat']");
    
    //Currency Selector
    private static final By CURRENCY_SELECTOR_FILTER = By.xpath("//div[contains(@class,'secondary-header')]//select[contains(@id,'selectCurrency')]");
    
    //Keys for Map refinement filters
    private static final String CURRENCY_SELECTOR = "Currency selector";
    private static final String PRICE_RANGE = "Price range";
    private static final String AVAILABILITY = "Availability";
    private static final String LANGUAGE = "Language";
    private static final String FORMAT = "Format";
    
    public String getPageUrl() {
        return SEARCH_RESULT_PAGE_URL;
    }
       
    public List<String> findBookTitlesByXpath() {
        return findElements(BOOK_TITLE).stream().map(WebElement::getText).collect(Collectors.toList());
    }
        
    public boolean isBooksArePresentOnTheSearchResultPage(List<String> expectedBookTitles, List<String> actualBookTitles){
        for (String expectedBookTitle: expectedBookTitles){
            for (String actualBookTitle: actualBookTitles){
                if (expectedBookTitle.matches(actualBookTitle)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean areOnlyExpectedBookTitlesDisplayedOnTheSearchResultPage(List<String> expectedBookTitles, List<String> actualBookTitles){
        return actualBookTitles.containsAll(expectedBookTitles);
    }

    public void clickOnTheRefineResultsButton(){
        findElement(REFINE_RESULTS_BUTTON).click();
    }
    
    public void setCurrencySelector(String currencySelector){
        selectByVisibleText(CURRENCY_SELECTOR_FILTER,currencySelector);
    }
    
    public void setPriceRefinementFilter (String priceRefinementFilter){
        selectByVisibleText(PRICE_RANGE_REFINEMENT_FILTER,priceRefinementFilter);
    }

    public void setAvailabilityRefinementFilter (String availabilityRefinementFilter){
        selectByVisibleText(AVAILABILITY_REFINEMENT_FILTER,availabilityRefinementFilter);
    }
    
    public void setLanguageRefinementFilter (String languageRefinementSelector){
        selectByVisibleText(LANGUAGE_REFINEMENT_FILTER,languageRefinementSelector);
    }

    public void setFormateRefinementFilter (String formatRefinementSelector){
        selectByVisibleText(FORMAT_REFINEMENT_FILTER,formatRefinementSelector);
    }

    public void selectRefinementFilters (Map<String, String> refinementFilters){
        setCurrencySelector(refinementFilters.get(CURRENCY_SELECTOR));
        setPriceRefinementFilter(refinementFilters.get(PRICE_RANGE));
        setAvailabilityRefinementFilter(refinementFilters.get(AVAILABILITY));
        setLanguageRefinementFilter(refinementFilters.get(LANGUAGE));
        setFormateRefinementFilter(refinementFilters.get(FORMAT));
        clickOnTheRefineResultsButton();
    }

    public void clickOnTheAddToBasketButtonBelowTheThinkingInJava(){
        findElement(ADD_TO_BASKET_BELOW_THINKING_IN_JAVA_BUTTON).click();
    }
}
