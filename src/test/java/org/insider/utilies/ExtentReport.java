package org.insider.utilies;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReport{


    protected static ExtentReports extentReports; //Raporlamayı başlatır
    protected static ExtentSparkReporter extentHtmlReporter; //Raporun formatını belirler
    public static ExtentTest extentTest;

    public static void extentReport() {

        extentReports = new ExtentReports();
        String date = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = "TestOutputs/report/extentReport_" + date + ".html";
        extentHtmlReporter = new ExtentSparkReporter(dosyaYolu);
        extentReports.attachReporter(extentHtmlReporter);
        extentReports.setSystemInfo("Name", "Test");
        extentReports.setSystemInfo("Browser", "Chrome");
        extentReports.setSystemInfo("Tester", "Özcan Arpaci");
        extentHtmlReporter.config().setDocumentTitle("Extent Report");
        extentHtmlReporter.config().setReportName("Test Raporu");
        extentTest = extentReports.createTest("Test Raporu", "Test Raporu");
    }

    public static void logTest(Status status, String message) {
        extentTest.log(status, MarkupHelper.createLabel(message, ExtentColor.BLUE));
    }

    public static void tearDown() {
        extentReports.flush();
    }
}
