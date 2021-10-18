package desktop.fragments;

import abstractClasses.fragment.AbstractFragment;
import org.openqa.selenium.By;

public class BasketPopUp extends AbstractFragment {
    private static final By BASKET_CHECKOUT_BUTTON = By.xpath("//*[@class='btn btn-primary pull-right continue-to-basket string-to-localize link-to-localize']");

    public void clickOnTheBasketCheckoutButton(){
        findElement(BASKET_CHECKOUT_BUTTON).click();
    }
    
}
