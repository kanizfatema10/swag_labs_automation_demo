package base;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class Base {
    
    private enum Drivers {
        None,
        ChromeDriver,
        FirefoxDriver,
    }

    private RemoteWebDriver activeDriver;

    protected static ExtentReports extent = new ExtentReports();
    private ExtentSparkReporter sparkReporter = new ExtentSparkReporter("src/test/reports/swag_labs_automation_test.html");

    @BeforeMethod
    public void setUpBrowser() {
        setActiveDriver(Drivers.ChromeDriver);
        activeDriver.manage().window().maximize();
        activeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    @AfterMethod
    public void closeBrowser(){
        activeDriver.close();
        //activeDriver.quit();
    }

    @BeforeTest
    public void beforeTest(){
        extent.attachReporter(sparkReporter);
    }

    @AfterTest
    public void afterTest(){
        extent.flush();
        try {
            Desktop.getDesktop().browse(new File("src/test/reports/swag_labs_automation_test.html").toURI());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    private void setActiveDriver(Drivers driver){
        
        if(driver == Drivers.FirefoxDriver){
            activeDriver = new FirefoxDriver();
        }
        else if(driver == Drivers.ChromeDriver){
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            activeDriver = new ChromeDriver(chromeOptions);
        }
    }

    public RemoteWebDriver getActiveDriver(){
        return activeDriver;
    }

}
