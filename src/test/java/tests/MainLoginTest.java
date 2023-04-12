package tests;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.Base;
import model.Credentials;
import model.TestData;
import model.UtilMethods;
import pages.InventoryPage;
import pages.MainLoginPage;


public class MainLoginTest extends Base{

    RemoteWebDriver driver;
    MainLoginPage mainLoginPage;
    InventoryPage inventoryPage;
    Credentials credentials = new Credentials();   

    @Test(dataProvider = "inputs", dataProviderClass = TestData.class, groups = "login")
    public void mainLoginTest(String userName, String password){
        ExtentTest test = extent.createTest("Login Functionality Test");

        setMainLoginPage();
        setInventoryPage();

        mainLoginPage.openHomePage();  
        test.log(Status.INFO, "Open home page"); 
        UtilMethods.waitForSeconds(0.5); 

        mainLoginPage.setUserName(userName);
        test.log(Status.INFO, "Enter user name"); 
        UtilMethods.waitForSeconds(0.5);  

        mainLoginPage.setPassword(password);
        test.log(Status.INFO, "Enter password"); 
        UtilMethods.waitForSeconds(0.5);  

        mainLoginPage.clickOnLogin();
        test.log(Status.INFO, "Click on login"); 
        UtilMethods.waitForSeconds(1);
        
        checkAssertion(userName, password, test);
        test.log(Status.INFO, "Tesing with user name: " + userName + ", password: " + password);
    }

    private void checkAssertion(String userName, String password, ExtentTest test){
        String actualString; 
        String expectedString; 

        switch(userName +"_+_"+ password) {
            case "standard_user_+_secret_sauce":
                test.log(Status.INFO, "Valid credential");
                actualString = inventoryPage.getAppLogoName();
                expectedString = "Swag Labs";

                try{
                    Assert.assertEquals(actualString, expectedString);
                    test.log(Status.PASS, "Successfully logged into the inventory page");
                } catch(AssertionError error){
                    test.log(Status.FAIL, "" + error);
                }
                
                break;
            case "_+_":
                test.log(Status.WARNING, "Both username and password is empty");
                break;
            case "_+_secret_sauce":
                test.log(Status.WARNING, "Epic sadface: Username is required");
                actualString = mainLoginPage.getErrorMessageForEmptyUsername();
                expectedString = "Epic sadface: Username is required";          
                Assert.assertEquals(actualString, expectedString);
                break;
            case "standard_user_+_":
            test.log(Status.WARNING, "Epic sadface: Password is required");
                actualString = mainLoginPage.getErrorMessageForEmptyPassword();
                expectedString = "Epic sadface: Password is required";          
                Assert.assertEquals(actualString, expectedString);
                break;     
            default:
                test.log(Status.WARNING, "Wrong credential");
                actualString = mainLoginPage.getErrorMessageForWrongCredential();
                expectedString = "Epic sadface: Username and password do not match any user in this service";          
                Assert.assertEquals(actualString, expectedString);
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
    

    


