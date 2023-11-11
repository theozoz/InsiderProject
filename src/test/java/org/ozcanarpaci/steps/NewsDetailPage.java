package org.ozcanarpaci.steps;

import com.aventstack.extentreports.Status;
import org.ozcanarpaci.base.BaseDriver;
import org.ozcanarpaci.commonMethods.CommonMethods;
import org.ozcanarpaci.utilies.ExtentReport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class NewsDetailPage extends BaseDriver {
    CommonMethods methods;
    final static By newsDetailNewsTitle = By.xpath("//h1[@class=\"article__title\"]");
    final static By newsDetailNewsContentLink = By.xpath("//div[@class=\"article-content\"]//a");


    @Test
    void checkBrowserTitleAndNewsTitle() throws InterruptedException {
        methods = new CommonMethods();
        WebElement element = methods.getRandElementFromList(HomePage.theLatestNews);
        if (element.isEnabled()) {
            element.click();
            //get news title and browser title
            Thread.sleep(2000);
            String newsTitle = methods.getElementText((newsDetailNewsTitle));
            String browserTitle = driver.getTitle();
            // news title and browser title
            //report example
            try {
                Assertions.assertEquals(browserTitle,newsTitle,"Titles are not same");
                ExtentReport.logTest(Status.PASS, "Test Test successes.");
            } catch (AssertionError e) {
                ExtentReport.logTest(Status.FAIL, "Test failed: " + e.getMessage()+"\n browserTitle: "+browserTitle+"\nnewsTitle: "+newsTitle);
                throw e;
            }

        } else {
            ExtentReport.logTest(Status.FAIL,"News detail page - checkBrowserTitleAndNewsTitle - element not found");
            Assertions.fail(element + " Element not found");
        }

    }

    @Test
    void verifyLinks(){
        methods = new CommonMethods();
        List<String> urls = new ArrayList<>();

        WebElement element = methods.getRandElementFromList(HomePage.theLatestNews);
        if (element.isEnabled()) {
            element.click();
            List<WebElement> links = methods.findElements(newsDetailNewsContentLink);
            for (WebElement link : links) {
                String href = link.getAttribute("href");
                if (href != null && !href.isEmpty()) {
                    urls.add(href);
                    ExtentReport.logTest(Status.INFO, " INFO - Url is added");
                } else {
                    Assertions.fail("Url is empty" + link);
                }
            }
            for (int i = 0; i < urls.size(); i++) {
                try {
                    Assertions.assertTrue(methods.verifyLink(urls.get(i)), "Link is not verified " + urls.get(i));
                    ExtentReport.logTest(Status.PASS, "Test Test successes.");
                } catch (AssertionError e) {
                    ExtentReport.logTest(Status.FAIL, "Test failed: " + e.getMessage());
                    throw e;
                }

            }
        }else {
            ExtentReport.logTest(Status.FAIL,"News detail page - verifyLinks - Element not found");
            Assertions.fail("Element not found"+ element);
        }
    }

}



