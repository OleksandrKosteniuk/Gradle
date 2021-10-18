package abstractClasses.page;

import static com.codeborne.selenide.Selenide.*;

import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.WebDriverWaiter;

import java.util.List;

import static driver.DriverManager.getDriver;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public abstract class AbstractPage extends WebDriverWaiter {

    private String pageUrl;
    
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPageUrl() {
        return pageUrl;
    }
    
    public void openWebsiteUrl(String url) {
        open(url);
    }    
}
