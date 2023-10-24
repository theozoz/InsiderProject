package org.commencis.commonMethods;

import com.aventstack.extentreports.Status;
import org.commencis.base.BaseDriver;
import org.commencis.utilies.ExtentReport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    long waitElementTimeout=2000;
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
            ExtentReport.logTest(Status.FAIL, "Test başarısız. Element bulunamadı");
        }
        return  element;
    }

    public String getElementText(By locator){
        return findElement(locator).getText();
    }

    public List<WebElement> findElements(By locator){
        return driver.findElements(locator);
    }

    public WebElement getRandElementFromList(By locator){
        Random rand= new Random();
        int size=rand.nextInt(1,driver.findElements(locator).size());
        WebElement element=driver.findElements(locator).get(size);
        return element;
    }

    public boolean verifyLink(String url) {
        driver.get(url);
        String title = driver.getTitle();
        boolean isValid = !title.isEmpty() && !title.contains("404") && !title.contains("error");
        return isValid;
    }
}
