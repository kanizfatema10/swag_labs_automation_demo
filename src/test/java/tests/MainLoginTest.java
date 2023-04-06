package tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base;
import model.Credentials;
import model.InventoryLocators;
import model.MainLoginLocators;
import model.TestData;
import model.UtilMethods;
import pages.MainLoginPage;


public class MainLoginTest extends Base{

    RemoteWebDriver driver;
    Credentials credentials = new Credentials();   

    @Test(dataProvider = "inputs", dataProviderClass = TestData.class)
    public void mainLoginTest(String userName, String password){

        driver = super.getActiveDriver();
        MainLoginPage mainLoginPage = new MainLoginPage(driver);

        mainLoginPage.openHomePage();   
        UtilMethods.waitForSeconds(0.5); 

        mainLoginPage.setUserName(userName);
        UtilMethods.waitForSeconds(0.5);  
        mainLoginPage.setPassword(password);
        UtilMethods.waitForSeconds(0.5);  
        mainLoginPage.clickOnLogin();
        UtilMethods.waitForSeconds(1);
        
        checkAssertion(userName, password);
    }

    private void checkAssertion(String userName, String password){
        WebElement webElementToCheck;
        String actualString; 
        String expectedString; 

        switch(userName +"_+_"+ password) {
            case "standard_user_+_secret_sauce":
                System.out.println(".........................all right.............");
                webElementToCheck = driver.findElement(InventoryLocators.appLogoName);
                actualString = webElementToCheck.getText();
                expectedString = "Swag Labs";
                Assert.assertEquals(actualString, expectedString);
                break;
            case "_+_":
            case "_+_secret_sauce":
                System.out.println(".........................Epic sadface: Username is required.............");
                webElementToCheck = driver.findElement(MainLoginLocators.errorMessageForEmptyUserName);
                actualString = webElementToCheck.getText();
                expectedString = "Epic sadface: Username is required";          
                Assert.assertEquals(actualString, expectedString);
                break;
            case "standard_user_+_":
                System.out.println(".........................Epic sadface: Password is required.............");
                webElementToCheck = driver.findElement(MainLoginLocators.errorMessageForEmptyPassword);
                actualString = webElementToCheck.getText();
                expectedString = "Epic sadface: Password is required";          
                Assert.assertEquals(actualString, expectedString);
                break;     
            default:
                System.out.println(".........................wrong.............");
                webElementToCheck = driver.findElement(MainLoginLocators.errorMessageForWrongCredential);
                actualString = webElementToCheck.getText();
                expectedString = "Epic sadface: Username and password do not match any user in this service";          
                Assert.assertEquals(actualString, expectedString);
          }
    }
}
    

    


