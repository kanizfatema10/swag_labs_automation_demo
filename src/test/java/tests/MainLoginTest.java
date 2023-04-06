package tests;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

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

    @Test(dataProvider = "inputs", dataProviderClass = TestData.class)
    public void mainLoginTest(String userName, String password){

        setMainLoginPage();
        setInventoryPage();

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
        String actualString; 
        String expectedString; 

        switch(userName +"_+_"+ password) {
            case "standard_user_+_secret_sauce":
                System.out.println("valid credential");
                actualString = inventoryPage.getAppLogoName();
                expectedString = "Swag Labs";
                Assert.assertEquals(actualString, expectedString);
                break;
            case "_+_":
            case "_+_secret_sauce":
                System.out.println("Epic sadface: Username is required");
                actualString = mainLoginPage.getErrorMessageForEmptyUsername();
                expectedString = "Epic sadface: Username is required";          
                Assert.assertEquals(actualString, expectedString);
                break;
            case "standard_user_+_":
                System.out.println("Epic sadface: Password is required");
                actualString = mainLoginPage.getErrorMessageForEmptyPassword();
                expectedString = "Epic sadface: Password is required";          
                Assert.assertEquals(actualString, expectedString);
                break;     
            default:
                System.out.println("wrong credential");
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
    

    


