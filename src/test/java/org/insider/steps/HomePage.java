package org.insider.steps;

import com.aventstack.extentreports.Status;
import org.insider.base.BaseDriver;
import org.insider.commonMethods.CommonMethods;
import org.insider.utilies.ExtentReport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class HomePage extends BaseDriver {

    CommonMethods methods;
    final static By homePageFistProductDemoBtn = By.xpath("//div[@id=\"desktop_hero_24\"]//a[text()=\"Get a Demo\"]");
    final static By insiderLogoPng = By.xpath("(//img[@src=\"https://useinsider.com/assets/img/logo-old.png\"])[1]");
    final static By companyDropDown = By.xpath("//a[@id=\"navbarDropdownMenuLink\" and contains(text(),'Company')]");
    final static By careersDropDownSub= By.xpath("//a[text()=\"Careers\"]");
    final static By locationBodyText= By.xpath("//h3[contains(text(),'Our Locations')]/following-sibling::p");
    final static By locationOffices= By.xpath("//div[@id=\"location-slider\"]//ul//li");
    final static By locationLifeInsider= By.xpath("//h2[text()=\"Life at Insider\"]");
    final static By locationTeamsHeader= By.xpath("//section[@id=\"career-find-our-calling\"]//h3[contains(text(),'Find your calling')]");
    final static By locationTeamsSeeAll= By.xpath("//section[@id=\"career-find-our-calling\"]//a[text()=\"See all teams\"]");


    @Test
    public void insiderCareerPageControls(){
        methods= new CommonMethods();
        methods.goToUrl("https://useinsider.com/");


        //Assertions for home page opened
        Assertions.assertEquals(methods.getTitle(),"#1 Leader in Individualized, Cross-Channel CX — Insider",
                "Title is not as expected"+"Actual Title: "+methods.getTitle());
        Assertions.assertTrue(driver.getCurrentUrl().contains("https://useinsider.com/"));
        methods.findElements(homePageFistProductDemoBtn);
        methods.findElements(insiderLogoPng);

        // Go to Careers page and check Career page, its Locations, Teams, and Life at Insider blocks are open or not
        methods.clickElement(companyDropDown);
        methods.clickElement(careersDropDownSub);
        Assertions.assertTrue(driver.getCurrentUrl().contains("https://useinsider.com/careers/"));
        int locationCount= methods.findElements(locationOffices).size();
        int locationCountText=Integer.parseInt(methods.spliteString(methods.findElement(locationBodyText).getText()));
        if (locationCount!=locationCountText){
            ExtentReport.logTest(Status.FAIL,"Location count is not as expected: Actual Location Count: "+locationCount+"Expected Location Count: "+locationCountText);
        }
        Assertions.assertEquals(locationCount,locationCountText,
                "Location count is not as expected"+"Actual Location Count: "+locationCount+"Expected Location Count: "+locationCountText);
        methods.findElement(locationLifeInsider);
        methods.findElement(locationTeamsHeader);
        methods.findElement(locationTeamsSeeAll);
    }



}
