package utils;

import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class WebDriverTool {
    public static <T> T javaScriptExecutor(String script,Object... var2){
        return (T) ((JavascriptExecutor) DriverManager.getDriver()).executeScript(script,var2);
    }

    public static void clickElementByJs(WebElement element){
        javaScriptExecutor("arguments[0].click()",element);
    }

    public static void setElementValueByJs(WebElement element, String value){
        javaScriptExecutor("arguments[0].value=arguments[1]",element,value);
    }

    public static void switchToDefaultContentFromIframe (){
        DriverManager.getDriver().switchTo().defaultContent();
    }
    public static void switchToParticularIframe(WebElement frame) {
    DriverManager.getDriver().switchTo().frame(frame);
}
}
