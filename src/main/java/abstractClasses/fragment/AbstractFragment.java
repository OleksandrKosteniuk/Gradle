package abstractClasses.fragment;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.WebDriverWaiter;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static driver.DriverManager.getDriver;

public abstract class AbstractFragment extends WebDriverWaiter {

    private WebElement rootElement;

    public AbstractFragment() {
        PageFactory.initElements(getDriver(), this);
    }

    public void setRootElement(WebElement element) {
        this.rootElement = element;
    }

    public WebElement getRootElement() {
        return rootElement;
    }
    
    private Select newSelect (By by){
        return new Select(findElement(by));
    } 
    
    protected void selectByVisibleText(By by, String text){
        newSelect(by).selectByVisibleText(text);
    }

    protected WebElement findElement(By by){
        return $(by).shouldBe(exist);
    }

    protected ElementsCollection findElements(By by){
        return $$(by).shouldBe(CollectionCondition.sizeGreaterThan(0));
    }

    protected void waitAndClickOnElement(By by){
        $(by).click();
    }

    protected SelenideElement waitVisibility(By by){
        return $(by).shouldBe(visible);
    }
    
}
