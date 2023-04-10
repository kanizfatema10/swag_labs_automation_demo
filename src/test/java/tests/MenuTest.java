package tests;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.Base;
import model.Credentials;
import model.UtilMethods;
import pages.InventoryPage;
import pages.MainLoginPage;

public class MenuTest extends Base{

    RemoteWebDriver driver;
    Credentials credentials = new Credentials();
    MainLoginPage mainLoginPage;
    InventoryPage inventoryPage;


    // @Test(priority = 1, groups = {"menu"})
    // public void aboutMenuTest(){
    //     setInventoryPage();
    //     loginToSauceLabDemo();
        
    //     inventoryPage.clickOnBurgerMenu();
    //     UtilMethods.waitForSeconds(1);
    //     inventoryPage.clickOnAboutMenu();
    //     UtilMethods.waitForSeconds(2);
    //     String expectedAboutPageURL = "https://saucelabs.com/";
    //     String actualAboutPageURL = inventoryPage.getAboutPageURL();

    //     Assert.assertEquals(actualAboutPageURL, expectedAboutPageURL);
    //     System.out.println("About menu is working fine");
    // }

    @Test(priority = 1, groups = {"menu"})
    public void logoutTest(){

        ExtentTest test = extent.createTest("Menu Test", "Logout test");
        test.log(Status.INFO, "Starting test");
        
        setInventoryPage();
        loginToSauceLabDemo();

        inventoryPage.clickOnBurgerMenu();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnLogoutMenu();
        UtilMethods.waitForSeconds(1);
        String expectedLoginPageURL = "https://www.saucedemo.com/";
        String actualLoginPageURL = inventoryPage.getLoginPageURL();
        
        Assert.assertEquals(actualLoginPageURL, expectedLoginPageURL);
        // System.out.println("Logout is working fine");
        test.pass("Successfully landed on the checkout your info page");
        test.log(Status.PASS, "Test passed");
    }


    private void loginToSauceLabDemo(){

        setMainLoginPage();
        
        if(driver != null){
        mainLoginPage.openHomePage();   
        UtilMethods.waitForSeconds(0.5); 

        mainLoginPage.setUserName(credentials.loginUserNameInputText);
        UtilMethods.waitForSeconds(0.5);  
        mainLoginPage.setPassword(credentials.loginPasswordInputText);
        UtilMethods.waitForSeconds(0.5);  
        mainLoginPage.clickOnLogin();
        UtilMethods.waitForSeconds(1);
        }
    }

    private void setMainLoginPage(){
        driver = super.getActiveDriver();
        mainLoginPage = new MainLoginPage(driver);
    }

    private void setInventoryPage(){
        driver = super.getActiveDriver();
        inventoryPage = new InventoryPage(driver);
    }

}
