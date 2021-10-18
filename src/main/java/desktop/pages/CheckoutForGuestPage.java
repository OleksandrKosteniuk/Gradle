package desktop.pages;

import abstractClasses.fragment.AbstractFragment;
import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.Map;

import static utils.WebDriverTool.*;

public class CheckoutForGuestPage extends AbstractFragment {
    private static final String CHECKOUT_FOR_GUEST_PAGE_URL = "https://www.bookdepository.com/payment";
    
    //Buttons on the Checkout page for Guest
    private static final By BUY_NOW_BUTTON = By.xpath("//*[@class='btn btn-primary full-width']");
    private static final By CONTINUE_TO_PAYMENT_BUTTON = By.xpath("//*[@id='hasSubmittedSameAddresses']");
    
    //Error messages in the Delivery Address area the Checkout page for Guest
    private static final By EMAIL_ADDRESS_ERROR_MESSAGE = By.xpath("(//*[@class='error-block'])[1]");
    private static final By FULL_NAME_ERROR_MESSAGE = By.xpath("(//*[@class='error-block'])[2]");
    private static final By ADDRESS_LINE_1_ERROR_MESSAGE = By.xpath("(//*[@class='error-block'])[4]");
    private static final By TOWN_CITY_ERROR_MESSAGE = By.xpath("(//*[@class='error-block'])[6]");
    private static final By POSCODE_ZIP_ERROR_MESSAGE = By.xpath("(//*[@class='error-block'])[8]");

    //Error messages in the Payment area on the Checkout page for Guest
    private static final By CARD_NUMBER_ERROR_MESSAGE = By.xpath("//*[@class='buynow-error-msg']");
    private static final By EXPIRY_DATE_ERROR_MESSAGE = By.xpath("//*[@class='buynow-error-msg']");
    private static final By CVV_ERROR_MESSAGE = By.xpath("//*[@class='buynow-error-msg']");
        
    //Input fields in the Delivery Address area the Checkout page for Guest
    private static final By EMAIL_ADDRESS_INPUT_FIELD = By.xpath("//*[@name='emailAddress']");
    private static final By FULL_NAME_INPUT_FIELD = By.xpath("//*[@name='delivery-fullName']");
    private static final By ADDRESS_LINE_1_INPUT_FIELD = By.xpath("//*[@name='delivery-addressLine1']");
    private static final By ADDRESS_LINE_2_INPUT_FIELD = By.xpath("//*[@name='delivery-addressLine2']");
    private static final By TOWN_CITY_INPUT_FIELD = By.xpath("//*[@name='delivery-city']");
    private static final By COUNTRY_STATE_INPUT_FIELD = By.xpath("//*[@name='delivery-county']");
    private static final By POSCODE_ZIP_INPUT_FIELD = By.xpath("//*[@name='delivery-postCode']");
    
    //Keys of the Map Expected Error messages in the Delivery Address area the Checkout page for Guest
    private static final String EMAIL_ADDRESS_EXPECTED_ERROR_MESSAGE_KEY = "Email address";
    private static final String FULL_NAME_EXPECTED_ERROR_MESSAGE_KEY = "Full name";
    private static final String ADDRESS_LINE_1_EXPECTED_ERROR_MESSAGE_KEY = "Address line 1";
    private static final String TOWN_CITY_EXPECTED_ERROR_MESSAGE_KEY = "Town/City";
    private static final String POSCODE_ZIP_EXPECTED_ERROR_MESSAGE_KEY = "Postcode/ZIP";
    
    //Input fields in the Payment area on the Checkout page for Guest
    private static final By CARD_TYPE_INPUT_FIELD = By.xpath("//*[@name='emailAddress']");
    
    //Expected error messages in the Payment area on the Checkout page for Guest
    private static final String CARD_NUMBER_EXPECTED_ERROR_MESSAGE = "Your card number is not valid.";
    private static final String NAME_ON_CARD_EXPECTED_ERROR_MESSAGE = "Invalid";
    private static final String CVV2_NUMBER_EXPECTED_ERROR_MESSAGE = "Invalid";
        
    //Checkout Summary Component
    private static final By SUB_TOTAL_OF_THE_CHECKOUT_SUMMARY = By.xpath("(//*[@class='text-right'])[1]");
    private static final By DELIVERY_OF_THE_CHECKOUT_SUMMARY = By.xpath("(//*[@class='text-right'])[2]");
    private static final By VAT_OF_THE_CHECKOUT_SUMMARY = By.xpath("//*[@class='text-right total-tax']");
    private static final By TOTAL_OF_THE_CHECKOUT_SUMMARY = By.xpath("//*[@class='text-right total-price']");

    //Keys of the Map Expected Checkout Sumary Component
    private static final String SUB_TOTAL_OF_THE_CHECKOUT_SUMMARY_KEY = "Sub-total";
    private static final String DELIVERY_OF_THE_CHECKOUT_SUMMARY_KEY = "Delivery";
    private static final String VAT_OF_THE_CHECKOUT_SUMMARY_KEY = "VAT";
    private static final String TOTAL_OF_THE_CHECKOUT_SUMMARY_KEY = "Total";
    
    //Keys of the Map Delivery Address Information
    private static final String FULL_NAME_OF_THE_MAP_DELIVERY_ADDRESS_INFORMATION_MAP = "Full name";
    private static final String DELIVERY_COUNTRY_OF_THE_MAP_DELIVERY_ADDRESS_INFORMATION_MAP = "Delivery country";
    private static final String ADDRESS_LINE_1_OF_THE_MAP_DELIVERY_ADDRESS_INFORMATION_MAP = "Address line 1";
    private static final String ADDRESS_LINE_2_OF_THE_MAP_DELIVERY_ADDRESS_INFORMATION_MAP = "Address line 2";
    private static final String TOWN_CITY_OF_THE_MAP_DELIVERY_ADDRESS_INFORMATION_MAP = "Town/City";
    private static final String COUNTRY_STATE_OF_THE_MAP_DELIVERY_ADDRESS_INFORMATION_MAP = "County/State";
    private static final String POSTCODE_STATE_OF_THE_MAP_DELIVERY_ADDRESS_INFORMATION_MAP = "Postcode";
     
    
    //Input fields in the Payment area the Checkout page for Guest
    private static final By CARD_NUMBER_OF_THE_PAYMENT_AREA= By.xpath("//*[@id='credit-card-number']");
    private static final By EXPIRY_DATE_OF_THE_PAYMENT_AREA= By.xpath("//*[@id='expiration']");
    private static final By CVV_OF_THE_PAYMENT_AREA= By.xpath("//*[@id='cvv']");
    
    //Keys of the Map Payment area the Checkout page for Guest

    private static final String CARD_TYPE_OF_THE_PAYMENT_AREA_KEY= "Card Type";
    private static final String NAME_ON_CARD_OF_THE_PAYMENT_AREA_KEY= "Name On Card";
    private static final String CARD_NUMBER_OF_THE_PAYMENT_AREA_KEY= "cardNumber";
    private static final String EXPIRY_MONTH_YEAR_OF_THE_PAYMENT_AREA_KEY= "Expiry Month/Year";
    private static final String CVV_OF_THE_PAYMENT_AREA_KEY= "Cvv";
    
    
    //IFrames
    private static final By CARD_NUMBER_IFRAME = By.xpath("//*[@name='braintree-hosted-field-number']");
    private static final By EXPIRY_DATE_IFRAME = By.xpath("//*[@id='braintree-hosted-field-expirationDate']");
    private static final By CVV_IFRAME= By.xpath("//*[@name='braintree-hosted-field-cvv']");
    
    public String getPageUrl() {
        return CHECKOUT_FOR_GUEST_PAGE_URL;
    }

    public void clickOnTheBuyNowButton(){
        findElement(BUY_NOW_BUTTON).click();
    }

    public void clickOnTheContinueToPaymentButton(){
        findElement(CONTINUE_TO_PAYMENT_BUTTON).click();
    }

    public String getEmailAddressActualErrorMessage(){
        return findElement(EMAIL_ADDRESS_ERROR_MESSAGE).getText();
    }

    public String getFullNameActualErrorMessage(){
        return findElement(FULL_NAME_ERROR_MESSAGE).getText();
    }

    public String getAddressLine1ActualErrorMessage(){
        return findElement(ADDRESS_LINE_1_ERROR_MESSAGE).getText();
    }

    public String getTownCityActualErrorMessage(){
        return findElement(TOWN_CITY_ERROR_MESSAGE).getText();
    }

    public String getPostcodeZipActualErrorMessage(){
        return findElement(POSCODE_ZIP_ERROR_MESSAGE).getText();
    }

    public String getEmailAddressExpectedErrorMessageKey(){
        return EMAIL_ADDRESS_EXPECTED_ERROR_MESSAGE_KEY;
    }

    public String getFullNameExpectedErrorMessageKey(){
        return FULL_NAME_EXPECTED_ERROR_MESSAGE_KEY;
    }

    public String getAddressLine1ExpectedErrorMessageKey(){
        return ADDRESS_LINE_1_EXPECTED_ERROR_MESSAGE_KEY;
    }

    public String getTownCityExpectedErrorMessageKey(){
        return TOWN_CITY_EXPECTED_ERROR_MESSAGE_KEY;
    }

    public String getPostcodeZipExpectedErrorMessageKey(){
        return POSCODE_ZIP_EXPECTED_ERROR_MESSAGE_KEY;
    }
    
    public String getCardNumberErrorMessage(){
        return findElement(CARD_NUMBER_ERROR_MESSAGE).getText();
    }

    public String getExpiryDateErrorMessage(){
        return findElement(EXPIRY_DATE_ERROR_MESSAGE).getText();
    }

    public String getCVVErrorMessage(){
        return findElement(CVV_ERROR_MESSAGE).getText();
    }

    public String getSubTotalOfTheCheckoutSummaryComponent(){
        return findElement(SUB_TOTAL_OF_THE_CHECKOUT_SUMMARY).getText();
    }

    public String getDeliveryOfTheCheckoutSummaryComponent(){
        return findElement(DELIVERY_OF_THE_CHECKOUT_SUMMARY).getText();
    }

    public String getVATOfTheCheckoutSummaryComponent(){
        return findElement(VAT_OF_THE_CHECKOUT_SUMMARY).getText();
    }

    public String getTotalOfTheCheckoutSummaryComponent(){
        return findElement(TOTAL_OF_THE_CHECKOUT_SUMMARY).getText();
    }

    public String getSubTotalOfTheCheckoutSummaryComponentKey(){
        return SUB_TOTAL_OF_THE_CHECKOUT_SUMMARY_KEY;
    }

    public String getDeliveryOfTheCheckoutSummaryComponentKey(){
        return DELIVERY_OF_THE_CHECKOUT_SUMMARY_KEY;
    }

    public String getVATOfTheCheckoutSummaryComponentKey(){
        return VAT_OF_THE_CHECKOUT_SUMMARY_KEY;
    }

    public String getTotalOfTheCheckoutSummaryComponentKey(){
        return TOTAL_OF_THE_CHECKOUT_SUMMARY_KEY;
    }

    public void fillInEmailAddressOnTheCheckout(String email) {
        findElement(EMAIL_ADDRESS_INPUT_FIELD).sendKeys(email);
    }
    
    public void fillInDeliveryAddressInformation(Map<String,String> deliveryAddressInformation){
        findElement(FULL_NAME_INPUT_FIELD).sendKeys(deliveryAddressInformation.get(FULL_NAME_OF_THE_MAP_DELIVERY_ADDRESS_INFORMATION_MAP));
        findElement(ADDRESS_LINE_1_INPUT_FIELD).sendKeys(deliveryAddressInformation.get(ADDRESS_LINE_1_OF_THE_MAP_DELIVERY_ADDRESS_INFORMATION_MAP));
        findElement(ADDRESS_LINE_2_INPUT_FIELD).sendKeys(deliveryAddressInformation.get(ADDRESS_LINE_2_OF_THE_MAP_DELIVERY_ADDRESS_INFORMATION_MAP));
        findElement(TOWN_CITY_INPUT_FIELD).sendKeys(deliveryAddressInformation.get(TOWN_CITY_OF_THE_MAP_DELIVERY_ADDRESS_INFORMATION_MAP));
        findElement(COUNTRY_STATE_INPUT_FIELD).sendKeys(deliveryAddressInformation.get(COUNTRY_STATE_OF_THE_MAP_DELIVERY_ADDRESS_INFORMATION_MAP));
        findElement(POSCODE_ZIP_INPUT_FIELD).sendKeys(deliveryAddressInformation.get(POSTCODE_STATE_OF_THE_MAP_DELIVERY_ADDRESS_INFORMATION_MAP));
        findElement(COUNTRY_STATE_INPUT_FIELD).click();
    }

    public void fillInCardDetailsInThePaymentArea(Map<String,String> creditCardData){
        switchToParticularIframe(findElement(CARD_NUMBER_IFRAME));
        findElement(CARD_NUMBER_OF_THE_PAYMENT_AREA).sendKeys(creditCardData.get(CARD_NUMBER_OF_THE_PAYMENT_AREA_KEY));
        //setElementValueByJs(findElement(CARD_NUMBER_OF_THE_PAYMENT_AREA), creditCardData.get(CARD_NUMBER_OF_THE_PAYMENT_AREA_KEY));
        switchToDefaultContentFromIframe();
        switchToParticularIframe(findElement(EXPIRY_DATE_IFRAME));
        findElement(EXPIRY_DATE_OF_THE_PAYMENT_AREA).sendKeys(creditCardData.get(EXPIRY_MONTH_YEAR_OF_THE_PAYMENT_AREA_KEY));
        //setElementValueByJs(findElement(EXPIRY_DATE_OF_THE_PAYMENT_AREA), creditCardData.get(EXPIRY_MONTH_YEAR_OF_THE_PAYMENT_AREA_KEY));
        switchToDefaultContentFromIframe();
        switchToParticularIframe(findElement(CVV_IFRAME));
        findElement(CVV_OF_THE_PAYMENT_AREA).sendKeys(creditCardData.get(CVV_OF_THE_PAYMENT_AREA_KEY));
        //setElementValueByJs(findElement(CVV_OF_THE_PAYMENT_AREA), creditCardData.get(CVV_OF_THE_PAYMENT_AREA_KEY));
    }
}
