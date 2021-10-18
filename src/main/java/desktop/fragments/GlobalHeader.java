package desktop.fragments;

import abstractClasses.fragment.AbstractFragment;
import abstractClasses.page.AbstractPage;
import org.openqa.selenium.By;

public class GlobalHeader extends AbstractFragment {
    
    private static final By SEARCH_INPUT_FIELD = By.xpath("//*[@class='text-input']");
    private static final By SEARCH_BUTTON = By.xpath("//*[@class='header-search-btn']");
    
    public void fillInKeywordForSearching(String keywordForSearching){
        findElement(SEARCH_INPUT_FIELD).sendKeys(keywordForSearching);
    }
    
    public void clickOnTheSearchButton(){
        findElement(SEARCH_BUTTON).click();
    }
}
