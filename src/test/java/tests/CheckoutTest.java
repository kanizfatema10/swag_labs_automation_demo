package tests;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
//import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import base.Base;
import model.Credentials;
import model.TestData;
import model.UtilMethods;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.MainLoginPage;


public class CheckoutTest extends Base{
    
    RemoteWebDriver driver;
    Credentials credentials = new Credentials();
    InventoryPage inventoryPage;
    CheckoutPage checkoutPage;
    MainLoginPage mainLoginPage;


    @Test(priority = 1, groups = "checkout")
    public void checkoutButtonTest(){
        // ExtentTest test = extent.createTest("Checking the checkout button");
        loginToSauceLabDemo();
        setInventoryPage();
        setCheckoutPage();

        inventoryPage.clickOnAddToCartSaucaeLabBackpack();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnAddToCartSauceLabsBikeLight();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnCart();
        UtilMethods.waitForSeconds(1);
        checkoutPage.clickOnCheckoutButton();
        UtilMethods.waitForSeconds(1);

        String actualTitleOfCheckoutYourInfoPage = checkoutPage.getActualCheckoutInfoTitle();
        String expectedTitleOfCheckoutYourInfoPage = "Checkout: Your Information";

        Assert.assertEquals(actualTitleOfCheckoutYourInfoPage, expectedTitleOfCheckoutYourInfoPage);
        //System.out.println("Successfully landed on the checkout your info page");
        // test.pass("Successfully landed on the checkout your info page");
 

        checkoutPage.clickOnCancelCheckoutButton();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnContinueShoppingButton();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnSauceLabsBackpackRemoveButton();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnRemoveSauceLabsBikeLight();
        UtilMethods.waitForSeconds(1);

    }

    @Test(priority = 2, dataProvider = "checkoutInfo", dataProviderClass = TestData.class, groups = "checkout")
    public void checkoutInformationTest(String firstName, String lastName, String postalCode){

        loginToSauceLabDemo();
        setInventoryPage();
        setCheckoutPage();

        inventoryPage.clickOnAddToCartSaucaeLabBackpack();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnCart();
        UtilMethods.waitForSeconds(1);

        checkoutPage.clickOnCheckoutButton();
        UtilMethods.waitForSeconds(1);

        checkoutPage.setFirstName(firstName);
        UtilMethods.waitForSeconds(1);
        checkoutPage.setLastName(lastName);
        UtilMethods.waitForSeconds(1);
        checkoutPage.setPostalCode(postalCode);
        UtilMethods.waitForSeconds(1);

        checkoutPage.clickOnContinueCheckoutButton();
        UtilMethods.waitForSeconds(1);

        checkAssertion(firstName, lastName, postalCode);

        if (!firstName.isEmpty() && !lastName.isEmpty() && !postalCode.isEmpty()) {
            checkoutPage.clickOnCancelCheckoutButton();
            UtilMethods.waitForSeconds(1);
            inventoryPage.clickOnSauceLabsBackpackRemoveButton(); 
            UtilMethods.waitForSeconds(1);
            return;
        }

        checkoutPage.clickOnCancelCheckoutButton();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnContinueShoppingButton();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnSauceLabsBackpackRemoveButton();
        UtilMethods.waitForSeconds(1);

        
    }

    @Test(priority = 3, groups = "checkout")
    public void totalPriceWithoutTaxTest(){
        loginToSauceLabDemo();
        setInventoryPage();
        setCheckoutPage();

        inventoryPage.clickOnAddToCartSaucaeLabBackpack();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnAddToCartSauceLabsBikeLight();
        UtilMethods.waitForSeconds(1);

        inventoryPage.clickOnCart();
        UtilMethods.waitForSeconds(1);
        checkoutPage.clickOnCheckoutButton();
        UtilMethods.waitForSeconds(1);

        checkoutPage.setFirstName("Babab");
        UtilMethods.waitForSeconds(1);
        checkoutPage.setLastName("Yogurt");
        UtilMethods.waitForSeconds(1);
        checkoutPage.setPostalCode("2397");
        UtilMethods.waitForSeconds(1);

        checkoutPage.clickOnContinueCheckoutButton();
        UtilMethods.waitForSeconds(1);

        Double sauceLabsBackpackPrice = checkoutPage.getCheckoutFirstItemPrice();
        Double sauceLabsBikeLightPrice = checkoutPage.getCheckoutSecondItemPrice();

        Double actulaSubTotalPriceWithoutTax = checkoutPage.getSubTotalPriceWithoutTax();
        Double expectedSubTotalPriceWithoutTax = sauceLabsBackpackPrice + sauceLabsBikeLightPrice;

        Assert.assertEquals(actulaSubTotalPriceWithoutTax, expectedSubTotalPriceWithoutTax);
        System.out.println("Sub total price calculation is okay");

        checkoutPage.clickOnCancelButtonFromCheckoutOverview();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnSauceLabsBackpackRemoveButton();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnRemoveSauceLabsBikeLight();
        UtilMethods.waitForSeconds(1);

    }


    @Test(priority = 4, groups = "checkout")
    public void totalPriceTest(){
        loginToSauceLabDemo();
        setInventoryPage();
        setCheckoutPage();

        inventoryPage.clickOnAddToCartSaucaeLabBackpack();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnAddToCartSauceLabsBikeLight();
        UtilMethods.waitForSeconds(1);

        inventoryPage.clickOnCart();
        UtilMethods.waitForSeconds(1);
        checkoutPage.clickOnCheckoutButton();
        UtilMethods.waitForSeconds(1);

        checkoutPage.setFirstName("Babab");
        UtilMethods.waitForSeconds(1);
        checkoutPage.setLastName("Yogurt");
        UtilMethods.waitForSeconds(1);
        checkoutPage.setPostalCode("2397");
        UtilMethods.waitForSeconds(1);

        checkoutPage.clickOnContinueCheckoutButton();
        UtilMethods.waitForSeconds(1);

        Double subTotalPrice = checkoutPage.getSubTotalPriceWithoutTax();
        Double taxPrice = checkoutPage.getTaxPrice();

        Double actualTotalPayableAmount = subTotalPrice + taxPrice;
        Double expectedTotalPayableAmount = checkoutPage.getTotalPayableAmount();

        Assert.assertEquals(actualTotalPayableAmount, expectedTotalPayableAmount);
        System.out.println("Total payable amount calculation is okay");

        checkoutPage.clickOnCancelButtonFromCheckoutOverview();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnSauceLabsBackpackRemoveButton();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnRemoveSauceLabsBikeLight();
        UtilMethods.waitForSeconds(1);
    }

    @Test(priority = 5, groups = "checkout")
    public void cancelButtonTest(){
        loginToSauceLabDemo();
        setInventoryPage();
        setCheckoutPage();

        inventoryPage.clickOnAddToCartSaucaeLabBackpack();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnAddToCartSauceLabsBikeLight();
        UtilMethods.waitForSeconds(1);

        inventoryPage.clickOnCart();
        UtilMethods.waitForSeconds(1);
        checkoutPage.clickOnCheckoutButton();
        UtilMethods.waitForSeconds(1);

        checkoutPage.setFirstName("Babab");
        UtilMethods.waitForSeconds(1);
        checkoutPage.setLastName("Yogurt");
        UtilMethods.waitForSeconds(1);
        checkoutPage.setPostalCode("2397");
        UtilMethods.waitForSeconds(1);

        checkoutPage.clickOnContinueCheckoutButton();
        UtilMethods.waitForSeconds(1);
        checkoutPage.clickOnCancelButtonFromCheckoutOverview();

        String actualProductPageTitle = inventoryPage.getActualProductPageTitle();
        String expectedProductPageTitle = "Products";

        Assert.assertEquals(actualProductPageTitle, expectedProductPageTitle);
        System.out.println("Cancel button is working fine");

        inventoryPage.clickOnSauceLabsBackpackRemoveButton();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnRemoveSauceLabsBikeLight();
        UtilMethods.waitForSeconds(1);
    }

    @Test(priority = 6, groups = "checkout")
    public void completeCheckoutTest(){
        loginToSauceLabDemo();
        setInventoryPage();
        setCheckoutPage();

        inventoryPage.clickOnAddToCartSaucaeLabBackpack();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnAddToCartSauceLabsBikeLight();
        UtilMethods.waitForSeconds(1);

        inventoryPage.clickOnCart();
        UtilMethods.waitForSeconds(1);
        checkoutPage.clickOnCheckoutButton();
        UtilMethods.waitForSeconds(1);

        checkoutPage.setFirstName("Babab");
        UtilMethods.waitForSeconds(1);
        checkoutPage.setLastName("Yogurt");
        UtilMethods.waitForSeconds(1);
        checkoutPage.setPostalCode("2397");
        UtilMethods.waitForSeconds(1);

        checkoutPage.clickOnContinueCheckoutButton();
        UtilMethods.waitForSeconds(1);
        checkoutPage.clickOnFinishButton();
        UtilMethods.waitForSeconds(1);

        String actualSuccessfullCheckoutMessage = checkoutPage.getCheckoutSuccessfullMessage();
        String expectedSuccessfullCheckoutMessage = "Thank you for your order!";

        Assert.assertEquals(actualSuccessfullCheckoutMessage, expectedSuccessfullCheckoutMessage);
        System.out.println("Checkout has been completed successfully");
    }

    @Test(priority = 7, groups = "checkout")
    public void backHomeButtonTest(){
        loginToSauceLabDemo();
        setInventoryPage();
        setCheckoutPage();

        inventoryPage.clickOnAddToCartSaucaeLabBackpack();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnAddToCartSauceLabsBikeLight();
        UtilMethods.waitForSeconds(1);

        inventoryPage.clickOnCart();
        UtilMethods.waitForSeconds(1);
        checkoutPage.clickOnCheckoutButton();
        UtilMethods.waitForSeconds(1);

        checkoutPage.setFirstName("Babab");
        UtilMethods.waitForSeconds(1);
        checkoutPage.setLastName("Yogurt");
        UtilMethods.waitForSeconds(1);
        checkoutPage.setPostalCode("2397");
        UtilMethods.waitForSeconds(1);

        checkoutPage.clickOnContinueCheckoutButton();
        UtilMethods.waitForSeconds(1);
        checkoutPage.clickOnFinishButton();
        UtilMethods.waitForSeconds(1);

        checkoutPage.clickOnBackHomeButton();
        UtilMethods.waitForSeconds(1);

        String actualProductPageTitle = inventoryPage.getActualProductPageTitle();
        String expectedProductPageTitle = "Products";

        Assert.assertEquals(actualProductPageTitle, expectedProductPageTitle);
        System.out.println("Back home button is working fine");
    }


    private void checkAssertion(String firstName, String lastName, String postalCode){
        String actualString; 
        String expectedString; 

        switch(firstName + "_+_" + lastName + "_+_" + postalCode) {
            case "_+_":
            case "_+_Jener_+_1577":
                System.out.println("Error: Firstname is required");
                actualString = checkoutPage.getActualErrorMessageForMissingFirstName();
                expectedString = "Error: First Name is required";          
                Assert.assertEquals(actualString, expectedString);
                break;
            case "Kylee_+__+_5655":
                System.out.println("Error: Lastname is required");
                actualString = checkoutPage.getActualErrorMessageForMissingLastName();
                expectedString = "Error: Last Name is required";          
                Assert.assertEquals(actualString, expectedString);
                break;
            case "Kylee_+_Jener_+_":
                System.out.println("Error: Postal code is required");
                actualString = checkoutPage.getActualErrorMessageForMissingPostalCode();
                expectedString = "Error: Postal Code is required";          
                Assert.assertEquals(actualString, expectedString);
                break;    
            case "Kylee_+_Jener_+_8873":
                actualString = checkoutPage.getActualCheckoutOverviewPageTitle();
                expectedString = "Checkout: Overview";          
                Assert.assertEquals(actualString, expectedString);
                System.out.println("Successfully landed to checkout overview page");

        }

    }

    
    private void loginToSauceLabDemo(){

        driver = super.getActiveDriver();
        mainLoginPage = new MainLoginPage(driver);

        mainLoginPage.openHomePage();   
        UtilMethods.waitForSeconds(0.5); 

        mainLoginPage.setUserName(credentials.loginUserNameInputText);
        UtilMethods.waitForSeconds(0.5);  
        mainLoginPage.setPassword(credentials.loginPasswordInputText);
        UtilMethods.waitForSeconds(0.5);  
        mainLoginPage.clickOnLogin();
        UtilMethods.waitForSeconds(1);

    }

    private void setInventoryPage(){
        driver = super.getActiveDriver();
        inventoryPage = new InventoryPage(driver);
    }

    private void setCheckoutPage(){
        driver = super.getActiveDriver();
        checkoutPage = new CheckoutPage(driver);
    }


}
