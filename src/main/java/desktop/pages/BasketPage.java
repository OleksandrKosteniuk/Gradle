package desktop.pages;

import abstractClasses.fragment.AbstractFragment;
import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import static utils.WebDriverTool.clickElementByJs;
import static utils.WebDriverTool.javaScriptExecutor;

public class BasketPage extends AbstractFragment {
    
    //Page Url
    private static final String BASKET_PAGE_URL = "https://www.bookdepository.com/basket";
    
    //Summary Component
    private static final By DELIVERY_COST_IN_THE_ORDER_SUMMARY = By.xpath("//*[@class='delivery-text']/dd");
    private static final By TOTAL_IN_THE_ORDER_SUMMARY = By.xpath("//*[@class='total']/dd");
    
    //Buttons
    private static final By CHECKOUT_BUTTON = By.xpath("//div[@class='basketHeaderButtons']//a[contains(@class,'optimizely-variation-1')]");
    
    //Keys for Summary component
    private static final String DELIVERY_COST_TITLE_IN_THE_ORDER_SUMMARY = "Delivery cost";
    private static final String TOTAL_TITLE_THE_ORDER_SUMMARY = "Total";
    
    public String getPageUrl() {
        return BASKET_PAGE_URL;
    }
    
    public String getDeliveryCost(){
        return findElement(DELIVERY_COST_IN_THE_ORDER_SUMMARY).getText();
    }

    public String getOrderTotal(){
        return findElement(TOTAL_IN_THE_ORDER_SUMMARY).getText();
    }

    public String getDeliveryCostTitle(){
        return DELIVERY_COST_TITLE_IN_THE_ORDER_SUMMARY;
    }

    public String getTotalTitle(){
        return TOTAL_TITLE_THE_ORDER_SUMMARY;
    }
    
    public void clickOnTheCheckoutButton(){
        clickElementByJs(findElement(CHECKOUT_BUTTON));
    }
}
