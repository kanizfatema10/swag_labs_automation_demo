package tests;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base;
import model.Credentials;
import model.MainLoginLocators;
import model.UtilMethods;
import pages.MainLoginPage;


public class MainLoginTest extends Base{

    RemoteWebDriver driver;
    Credentials credentials = new Credentials();   
    
    @Test
    public void loginMainTest(){
        
         driver = super.getActiveDriver();
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        MainLoginPage mainLoginPage = new MainLoginPage(driver);

        mainLoginPage.openHomePage();   
        UtilMethods.waitForSeconds(0.5);  
        mainLoginPage.setEmail(credentials.loginUserNameInputText);
        UtilMethods.waitForSeconds(0.5);  
        mainLoginPage.setPassword(credentials.loginPasswordInputText);
        UtilMethods.waitForSeconds(0.5);  
        mainLoginPage.clickOnLogin();
        UtilMethods.waitForSeconds(3);



        WebElement findAppLogoNameClass = driver.findElement(MainLoginLocators.appLogoName);
        String actualAppLogoName = findAppLogoNameClass.getText();
        String expectedAppLogoName = "Swag Labs";


        Assert.assertEquals(actualAppLogoName, expectedAppLogoName);

    }
}


