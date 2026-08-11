package HealthCare;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class LoginTest {

    WebDriver driver;
    ExtentSparkReporter ext;
    ExtentReports rep;
    ExtentTest v ;
    @BeforeClass
    public void Blaunch() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.openemr.io/openemr/interface/login/login.php?site=default");
        String reportPath = System.getProperty("user.dir") + "/target/ExtentReport.html";
       ext=new ExtentSparkReporter(reportPath);
       ext.config().setReportName("Healthcare Automation Report");
       ext.config().setDocumentTitle("Healthcare Test Execution Report");
       ext.config().setTheme(Theme.DARK);
       
         rep=new ExtentReports();
        rep.attachReporter(ext);
        rep.setSystemInfo("bowser", "chrome");
        rep.setSystemInfo("OS", "Window");
        
        
        ///////////////////////////////////////////////////////////
        
    }

    @Test(priority = 1,retryAnalyzer = RetryAnalyzer.class)
    public void login() throws InterruptedException, IOException {
    	
    	 v = rep.createTest("login");
    	 v.assignAuthor("Tested By shailaja");
    	 v.assignCategory("Smoke Testing");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.findElement(By.id("authUser")).sendKeys("admin");
        
        v.info("enterde usrer");
        
        
        driver.findElement(By.id("clearPass")).sendKeys("pass");
        
        v.info("enterde pass");
        
        driver.findElement(By.id("login-button")).click();
        v.pass("login successfully");

        wait.until(ExpectedConditions.urlContains("main.php"));

        try {

            wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(),'Patient')]")
            )).click();

        } catch (Exception e) {

            takeScreenshot.takeScreenshot1(driver, "Jenkins_Failure");
            throw e;
        }

        Thread.sleep(3000);

        WebElement newSearch = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(text(),'New/Search')]")));

        newSearch.click();
    }

    @Test(priority = 2)
    public void Search_or_Add_Patient() {
    	 v = rep.createTest("Search");
    	 v.assignAuthor("Tested By subhash");
    	 v.assignCategory("sanity Testing");
        int totalFrames = driver.findElements(By.tagName("iframe")).size();//4

        for (int i = 0; i < totalFrames; i++) {

           driver.switchTo().defaultContent();    //
            driver.switchTo().frame(i);

            if (driver.findElements(By.id("form_fname")).size() > 0) {

                driver.findElement(By.id("form_fname")).sendKeys("Shailaja");
                driver.findElement(By.id("form_mname")).sendKeys("N");
                driver.findElement(By.id("form_lname")).sendKeys("Khandekar");
                driver.findElement(By.id("form_DOB")).sendKeys("01/01/1998");

                Select gender = new Select(driver.findElement(By.id("form_sex")));
                gender.selectByVisibleText("Female");

                driver.findElement(By.id("create")).click();

                break;
            }
        }
    }
    @AfterClass
    public void teardown() {
    	rep.flush();
    	
    	    driver.quit();
    	
    }
}