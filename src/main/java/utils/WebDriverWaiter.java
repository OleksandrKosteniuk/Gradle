package utils;

import driver.DriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverWaiter {
    
    protected WebDriverWait wait;

    public WebDriverWaiter(){
        wait = new WebDriverWait(DriverManager.getDriver(),20);        
    }
}
