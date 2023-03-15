package base;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


public class Base {
    
    private enum Drivers{
        None,
        ChromeDriver,
        FirefoxDriver,
    }

    RemoteWebDriver activeDriver;
    
    @BeforeTest
    public void setUpBrowser(){
        setActiveDriver(Drivers.ChromeDriver);        
        activeDriver.manage().window().maximize();
        activeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    @AfterTest
    public void closeBrowser(){
        activeDriver.close();
        //activeDriver.quit();
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
