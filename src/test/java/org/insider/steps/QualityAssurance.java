package org.insider.steps;

import com.aventstack.extentreports.Status;
import org.insider.base.BaseDriver;
import org.insider.commonMethods.CommonMethods;
import org.insider.utilies.ExtentReport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class QualityAssurance extends BaseDriver {
    CommonMethods methods;
    ExtentReport extentReport;
    final static By QAPageSeeAllJobs = By.xpath("//a[text()=\"See all QA jobs\"]");
    final static By QAPageFilterLocation = By.id("select2-filter-by-location-container");
    final static By QAPageFilterDepartment = By.id("select2-filter-by-department-container");
    final static By QAPageFilterLocationListIst = By.xpath("//li[text()=\"Istanbul, Turkey\"]");
    final static By QAPageQAJobListFirstItem = By.xpath("//div[@data-team=\"qualityassurance\"][1]");
    final static By QAPageQAJobListFirstItemViewRole = By.xpath("//div[@data-team=\"qualityassurance\"][1]//a[text()=\"View Role\"]");
    final static By QAPageQAJobListFirstItemTitle = By.xpath("//div[@data-team=\"qualityassurance\"][1]//p");
    final static By LeverApplicationJopTitle = By.xpath("//h2");
    final static By LeverApplicationApplyForJobBtn = By.xpath("//div[@class=\"section page-centered posting-header\"]//a[text()=\"Apply for this job\"]");
    final static By acceptCookies = By.xpath("//a[text()=\"Accept All\"]");
    final static By getQAPageQAJobListTitle = By.xpath("//div[@id=\"jobs-list\"]/div//p");
    final static By getQAPageQAJobListLocation = By.xpath("//div[@id=\"jobs-list\"]/div//div[@class=\"position-location text-large\"]");


    @Test
    public void insiderQualityAssurancePageControls() {
        methods = new CommonMethods();
        extentReport = new ExtentReport();
        methods.goToUrl("https://useinsider.com/careers/quality-assurance/");

        // Search Quality Assurance jobs in Istanbul
        methods.clickElement(acceptCookies);
        methods.clickElement(QAPageSeeAllJobs);
        methods.scrollDown(QAPageFilterLocation);
        methods.clickElement(QAPageFilterLocation);
        methods.setWaitForSeconds(3);
        methods.clickElement(QAPageFilterLocationListIst);
        methods.setWaitForSeconds(1);
        Assertions.assertTrue(methods.getElementText(QAPageFilterLocation).contains("Istanbul, Turkey"),
                "Location is not as expected" + " Actual Location: " + methods.getElementText(QAPageFilterLocation));

        ExtentReport.logTest(Status.PASS, "Location is as expected");
        Assertions.assertTrue(methods.getElementText(QAPageFilterDepartment).contains("Quality Assurance"),
                "Department is not as expected" + "Actual Department: " + methods.getElementText(QAPageFilterDepartment));
        ExtentReport.logTest(Status.PASS, "Department is as expected");

        methods.setWaitForSeconds(3);
        methods.scrollDown(QAPageQAJobListFirstItemViewRole);


        //Assertions for job list title contains Quality Assurance
        for (WebElement element : methods.findElements(getQAPageQAJobListTitle)) {

            Assertions.assertTrue(element.getText().contains("Quality Assurance"),
                    "Job list is not as expected" + " Actual Job List: " + element.getText());
            ExtentReport.logTest(Status.PASS, "Job list is as expected" + " Actual Job List: " + element.getText());
        }

        //Assertions for job list location contains Istanbul, Turkey
        for (WebElement element : methods.findElements(getQAPageQAJobListLocation)) {
            Assertions.assertTrue(element.getText().contains("Istanbul, Turkey"),
                    "Job list is not as expected" + " Actual Job List: " + element.getText());
            ExtentReport.logTest(Status.PASS, "Job list is as expected" + " Actual Job List: " + element.getText());

        }

        //Assertions for redirected application for job page
        methods.setFluentWait(3);
        methods.hoverElement(QAPageQAJobListFirstItem);
        methods.clickElement(QAPageQAJobListFirstItemViewRole);
        String jobTitle = methods.getElementText(QAPageQAJobListFirstItemTitle);
        methods.switchToNewTab(1);
        methods.findElement(LeverApplicationApplyForJobBtn);
        Assertions.assertEquals(methods.getElementText(LeverApplicationJopTitle), jobTitle,
                "Job title is not as expected" + " Actual Job Title: " + methods.getElementText(LeverApplicationJopTitle) + "Expected Job Title: " + jobTitle);
        ExtentReport.logTest(Status.PASS, "Job title is as expected" + " Actual Job Title: " + methods.getElementText(LeverApplicationJopTitle) + "Expected Job Title: " + jobTitle);


    }

}
