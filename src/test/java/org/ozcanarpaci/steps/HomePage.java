package org.ozcanarpaci.steps;

import com.aventstack.extentreports.Status;
import org.ozcanarpaci.base.BaseDriver;
import org.ozcanarpaci.commonMethods.CommonMethods;
import org.ozcanarpaci.utilies.ExtentReport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends BaseDriver {

    CommonMethods methods;
    final static By theLatestNews = By.xpath("//header[@class=\"post-block__header\"]");
    final static By theLatestNewsImage = By.xpath("//article[@cmp-ltrk=\"The Latest - Home\"]//footer//img");
    final static By theLatestNewsAuthor = By.xpath("//article[@cmp-ltrk=\"The Latest - Home\"]//span[@class=\"river-byline__authors\"]");


    @Test
    void eachNewsHasAnAuthor() {
        methods = new CommonMethods();

        //Making a comparison between the number of news elements and the number of author elements.
        Assertions.assertEquals(methods.findElements(theLatestNews).size(),
                methods.findElements(theLatestNewsAuthor).size(),
                "There are "+methods.findElements(theLatestNews).size()+" news but "+ methods.findElements(theLatestNewsAuthor).size()+" Author");

        //Per news author, check if the item contains the author's name.
        for (WebElement element : methods.findElements(theLatestNewsAuthor)) {
            String elementText = element.getText();
            Assertions.assertNotNull(elementText,element +" element has not Author");
            ExtentReport.logTest(Status.PASS, "Test successes");
        }
    }

    @Test
    void eachNewsHasImage() {
        methods = new CommonMethods();

        //Making a comparison between the number of news elements and the number of Image elements.
        Assertions.assertEquals(methods.findElements(theLatestNews).size(),
                methods.findElements(theLatestNewsImage).size(),
                "There are "+methods.findElements(theLatestNews).size()+" news but "+ methods.findElements(theLatestNewsImage).size()+" Image");

        //Per news author, check if the item contains the Image.
        for (WebElement element : methods.findElements(theLatestNewsImage)) {
            String elementText = element.getText();
            Assertions.assertNotNull(elementText,element +" element has not Image");
            ExtentReport.logTest(Status.PASS, "Test successes.");
        }
    }


}
