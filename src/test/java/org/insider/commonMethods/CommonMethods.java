package org.insider.commonMethods;

import com.aventstack.extentreports.Status;
import org.insider.base.BaseDriver;
import org.insider.utilies.ExtentReport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class CommonMethods {

   // private final WebDriverWait wait;
    FluentWait<WebDriver> wait;
    long pollingEveryValue=10;
    long waitElementTimeout=30;
    WebDriver driver;


    public CommonMethods() {
        this.driver= BaseDriver.driver;
        wait = setFluentWait(waitElementTimeout);
    }

    public FluentWait<WebDriver> setFluentWait(long timeout){
        FluentWait<WebDriver> fluentWait= new FluentWait<>(driver);
        fluentWait.withTimeout(Duration.ofMillis(timeout))
                .pollingEvery(Duration.ofMillis(pollingEveryValue))
                .ignoring(NoSuchElementException.class);
        return fluentWait;
    }

    public WebElement findElement(By locator) {
        WebElement element= wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        if (element==null){
            ExtentReport.logTest(Status.FAIL, "Test Failed: Element not found");
        }
        return  element;
    }

    public String getElementText(By locator){
        return findElement(locator).getText();
    }

    public List<WebElement> findElements(By locator){
        return driver.findElements(locator);
    }


    public void goToUrl(String url) {
        driver.get(url);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void clickElement(By locator) {

        findElement(locator).click();
    }

    public String spliteString(String text){

        String[] splitText= text.split(" office");
        return splitText[0];
    }

    public void hoverElement(By locator){
        WebElement element= findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void switchToNewTab(int tabNumber){
        driver.switchTo().window(driver.getWindowHandles().toArray()[tabNumber].toString());
    }

    public void setWaitForSeconds(int time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollDown(By locator){
        Actions actions = new Actions(driver);
        actions.moveToElement(findElement(locator));
        actions.perform();
    }

}
