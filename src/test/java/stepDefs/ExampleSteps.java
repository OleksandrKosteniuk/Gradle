package stepDefs;

import com.codeborne.selenide.junit.ScreenShooter;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.testng.annotations.Report;
import io.cucumber.java.After;
import io.cucumber.java.BeforeStep;
import abstractClasses.page.AbstractPage;
import desktop.fragments.BasketPopUp;
import desktop.fragments.GlobalHeader;
import desktop.pages.CheckoutForGuestPage;
import desktop.pages.HomePage;
import desktop.pages.SearchResultPage;
import desktop.pages.BasketPage;
import driver.DriverManager;
import io.cucumber.java.Scenario;
import io.cucumber.java.Transpose;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class ExampleSteps extends AbstractPage {

    //Fragments creation
    private GlobalHeader globalHeader = new GlobalHeader();
    private BasketPopUp basketPopUp = new BasketPopUp();
    
    //Pages creation
    private HomePage homePage = new HomePage();
    private SearchResultPage searchResultPage = new SearchResultPage();
    private BasketPage basketPage = new BasketPage();
    private CheckoutForGuestPage checkoutPageForGuest = new CheckoutForGuestPage();

    /*
    @RegisterExtension
    public static ScreenShooterExtension screenShooterExtension = new ScreenShooterExtension().to("target/selenide");
    */
    
    @After
    public void getScreenshot(Scenario scenario) throws IOException{
        Date currentDate = new Date();
        String screenshotFileName = currentDate.toString().replace(" ", "-").replace(":", "-");
        File screenshotFile = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);

        if (scenario.isFailed()){
            FileUtils.copyFile(screenshotFile,new File(".//screenshot//"+screenshotFileName+".png"));
        }
    }
    
    @Given("I am an anonymous customer with clear cookies")
    public void cleanCookiesInTheBrowser() {
        open();
        getWebDriver().manage().deleteAllCookies();
    }
    
    @When("I open the Home page")
    public void openHomePage() {
        homePage.openWebsiteUrl(homePage.getPageUrl());
        assertThat(getWebDriver().getCurrentUrl().contains(homePage.getPageUrl()))
                .overridingErrorMessage("Home page is not opened")
                .isTrue();
    }

    @When("I search for {string}")
    public void searchByBookTitle(String keywordForSearching) {
        globalHeader.fillInKeywordForSearching(keywordForSearching);
        globalHeader.clickOnTheSearchButton();
    }

    @When("I am redirected to a Search Result page")
    public void userIsRedirectedToSearchResultPage() {
        assertThat(getWebDriver().getCurrentUrl().contains(searchResultPage.getPageUrl()))
                .overridingErrorMessage("User is not redirected to the Search Result page")
                .isTrue();
    }

    @When("Search results contain the following products")
    public void searchResultPageContainsTheFollowingProducts(List<String> expectedBookTitles) {
        assertThat(searchResultPage.isBooksArePresentOnTheSearchResultPage(expectedBookTitles, searchResultPage.findBookTitlesByXpath()))
        .overridingErrorMessage("Search Result page does not contain any expected Book title")
                .isTrue();
    }

    @When("I apply the following search filters")
    public void applyTheFollowingSearchFilters(Map<String, String> refinementFilters) {
        searchResultPage.selectRefinementFilters(refinementFilters);
    }

    @When("Search results contain only the following products")
    public void searchResultPageContainsOnlyTheExpectedBookTitles(List<String> expectedBookTitles) {
        assertThat(searchResultPage.areOnlyExpectedBookTitlesDisplayedOnTheSearchResultPage(expectedBookTitles,searchResultPage.findBookTitlesByXpath()))
        .overridingErrorMessage("Search Result page does not contain the expected Book Titles")
                .isTrue();
    }

    @When("I click Add to basket button for product with name Thinking in Java")
    public void clickAddToBasketButtonBelowTheThinkingInJava() {
        searchResultPage.clickOnTheAddToBasketButtonBelowTheThinkingInJava();
    }

    @When("I select Basket Checkout button in basket pop-up")
    public void clickOnTheBasketCheckoutButtonInTheBasketPopUp() {
        basketPopUp.clickOnTheBasketCheckoutButton();
    }

    @When("I am redirected to the Basket page")
    public void userIsRedirectedToBasketPage() {
        assertThat(getWebDriver().getCurrentUrl().contains(basketPage.getPageUrl()))
                .overridingErrorMessage("User is not redirected to the Basket page")
                .isTrue();
    }

    @When("Basket order summary is as following:")
    public void basketOrderSummaryIsAsFollowing(@Transpose Map<String,String> expectedValuesInTheOrderSummaryComponent) {
        assertThat(basketPage.getDeliveryCost())
                .overridingErrorMessage("Delivery cost does not equal to expected value")
                .isEqualTo(expectedValuesInTheOrderSummaryComponent.get(basketPage.getDeliveryCostTitle()));

        assertThat(basketPage.getOrderTotal())
                .overridingErrorMessage("Order total does not equal to expected value")
                .isEqualTo(expectedValuesInTheOrderSummaryComponent.get(basketPage.getTotalTitle()));
    }

    @When("I click Checkout button on Basket page")
    public void clickONTheCheckoutButtonOnTheBasketPage() {
        basketPage.clickOnTheCheckoutButton();
    }

    @When("I am redirected to the Checkout page")
    public void userIsRedirectedToTheCheckoutPage() {
        assertThat(getWebDriver().getCurrentUrl().contains(checkoutPageForGuest.getPageUrl()))
                .overridingErrorMessage("User is not redirected to the Checkout for Guest page")
                .isTrue();
    }

    @When("I click Continue to payment button")
    public void ClickBuyNowButton() {
        checkoutPageForGuest.clickOnTheBuyNowButton();
    }

    @When("the following validation error messages are displayed on Delivery Address form:")
    public void areTheFollowingErrorMessagesDisplayedOnDeliveryAddressForm(Map<String,String> expectedErrorMessagesOnTheDeliveryAddressForm) {
        assertThat(checkoutPageForGuest.getEmailAddressActualErrorMessage())
                .overridingErrorMessage("Email Address error message is incorrect")
                .isEqualTo(expectedErrorMessagesOnTheDeliveryAddressForm.get(checkoutPageForGuest.getEmailAddressExpectedErrorMessageKey()));

        assertThat(checkoutPageForGuest.getFullNameActualErrorMessage())
                .overridingErrorMessage("Full name error message is incorrect")
                .isEqualTo(expectedErrorMessagesOnTheDeliveryAddressForm.get(checkoutPageForGuest.getFullNameExpectedErrorMessageKey()));

        assertThat(checkoutPageForGuest.getAddressLine1ActualErrorMessage())
                .overridingErrorMessage("Address line 1 error message is incorrect")
                .isEqualTo(expectedErrorMessagesOnTheDeliveryAddressForm.get(checkoutPageForGuest.getAddressLine1ExpectedErrorMessageKey()));

        assertThat(checkoutPageForGuest.getTownCityActualErrorMessage())
                .overridingErrorMessage("Town/city error message is incorrect")
                .isEqualTo(expectedErrorMessagesOnTheDeliveryAddressForm.get(checkoutPageForGuest.getTownCityExpectedErrorMessageKey()));

        assertThat(checkoutPageForGuest.getPostcodeZipActualErrorMessage())
                .overridingErrorMessage("Postalcode/Zip error message is incorrect")
                .isEqualTo(expectedErrorMessagesOnTheDeliveryAddressForm.get(checkoutPageForGuest.getPostcodeZipExpectedErrorMessageKey()));
    }

    @When("the following validation error messages are displayed on Payment form:")
    public void areTheFollowingErrorMessagesDisplayedOnPaymentForm(List<String> expectedErrorMessagesOnThePaymentForm) {
        assertThat(checkoutPageForGuest.getCardNumberErrorMessage().contains(expectedErrorMessagesOnThePaymentForm.get(0)))
                .overridingErrorMessage("Card Number error message is incorrect")
                .isTrue();

        assertThat(checkoutPageForGuest.getExpiryDateErrorMessage().contains(expectedErrorMessagesOnThePaymentForm.get(1)))
                .overridingErrorMessage("Expiry Date error message is incorrect")
                .isTrue();

        assertThat(checkoutPageForGuest.getCVVErrorMessage().contains(expectedErrorMessagesOnThePaymentForm.get(2)))
                .overridingErrorMessage("CVV error message is incorrect")
                .isTrue();
    }

    @When("Checkout order summary is as following:")
    public void checkoutOrderSummaryIsAsFollowing(@Transpose Map<String,String> expectedValuesInTheCheckoutSummaryComponent) {
        assertThat(checkoutPageForGuest.getSubTotalOfTheCheckoutSummaryComponent())
                .overridingErrorMessage("Sub-total does not equal to expected value in the Checkout Summary Component")
                .isEqualTo(expectedValuesInTheCheckoutSummaryComponent.get(checkoutPageForGuest.getSubTotalOfTheCheckoutSummaryComponentKey()));

        assertThat(checkoutPageForGuest.getDeliveryOfTheCheckoutSummaryComponent())
                .overridingErrorMessage("Delivery does not equal to expected value in the Checkout Summary Component")
                .isEqualTo(expectedValuesInTheCheckoutSummaryComponent.get(checkoutPageForGuest.getDeliveryOfTheCheckoutSummaryComponentKey()));
        
        assertThat(checkoutPageForGuest.getVATOfTheCheckoutSummaryComponent())
                .overridingErrorMessage("VAT does not equal to expected value in the Checkout Summary Component")
                .isEqualTo(expectedValuesInTheCheckoutSummaryComponent.get(checkoutPageForGuest.getVATOfTheCheckoutSummaryComponentKey()));

        assertThat(checkoutPageForGuest.getTotalOfTheCheckoutSummaryComponent())
                .overridingErrorMessage("Total does not equal to expected value in the Checkout Summary Component")
                .isEqualTo(expectedValuesInTheCheckoutSummaryComponent.get(checkoutPageForGuest.getTotalOfTheCheckoutSummaryComponentKey()));
    }

    @When("I checkout as a new customer with email {string}")
    public void fillInEmailAddressOnTheCheckout(String email) {
        checkoutPageForGuest.fillInEmailAddressOnTheCheckout(email);
    }

    @When("I fill delivery address information manually:")
    public void fillInDeliveryAddressInformation(@Transpose Map<String,String> deliveryAddressInformation) {
        checkoutPageForGuest.fillInDeliveryAddressInformation(deliveryAddressInformation);
    }

    @When("there is no validation error messages displayed on Delivery Address form")
    public void areValidationErrorMessagesDisplayedOnDeliveryAddressForm() {
        assertThat(checkoutPageForGuest.getEmailAddressActualErrorMessage().isEmpty())
                .overridingErrorMessage("Email Address error message is displayed")
                .isTrue();

        assertThat(checkoutPageForGuest.getFullNameActualErrorMessage().isEmpty())
                .overridingErrorMessage("Full name error message is displayed")
                .isTrue();

        assertThat(checkoutPageForGuest.getAddressLine1ActualErrorMessage().isEmpty())
                .overridingErrorMessage("Address line 1 error message is displayed")
                .isTrue();

        assertThat(checkoutPageForGuest.getTownCityActualErrorMessage().isEmpty())
                .overridingErrorMessage("Town/city error message is incorrect")
                .isTrue();

        assertThat(checkoutPageForGuest.getPostcodeZipActualErrorMessage().isEmpty())
                .overridingErrorMessage("Postalcode/Zip error message is incorrect")
                .isTrue();
    }

    @When("I enter my card details")
    public void fillInCardDetailsInThePaymentArea(Map<String,String> creditCardData) {
        checkoutPageForGuest.fillInCardDetailsInThePaymentArea(creditCardData);
    }
}
