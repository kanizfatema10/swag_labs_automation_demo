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
        ExtentTest test = extent.createTest("Checkout Button Test");
        loginToSauceLabDemo();
        test.log(Status.INFO, "User logged in");
        setInventoryPage();
        setCheckoutPage();

        inventoryPage.clickOnAddToCartSaucaeLabBackpack();
        test.log(Status.INFO, "Clicked on add to cart button of Sauce Labs Backback");
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnAddToCartSauceLabsBikeLight();
        test.log(Status.INFO, "Clicked on add to cart button of Sauce Labs Bike Light");
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnCart();
        test.log(Status.INFO, "Clicked on cart");
        UtilMethods.waitForSeconds(1);
        checkoutPage.clickOnCheckoutButton();
        test.log(Status.INFO, "Clicked on checkout button");
        UtilMethods.waitForSeconds(1);

        String actualTitleOfCheckoutYourInfoPage = checkoutPage.getActualCheckoutInfoTitle();
        String expectedTitleOfCheckoutYourInfoPage = "Checkout: Your Information";

        try{
            Assert.assertEquals(actualTitleOfCheckoutYourInfoPage, expectedTitleOfCheckoutYourInfoPage);
            test.log(Status.PASS, "Successfully landed on the checkout your info page");
        } catch(AssertionError error){
            test.log(Status.FAIL, "" + error);
        }

 
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
        ExtentTest test = extent.createTest("Checkout info page test");
        loginToSauceLabDemo();
        test.log(Status.INFO, "User logged in");
        setInventoryPage();
        setCheckoutPage();

        inventoryPage.clickOnAddToCartSaucaeLabBackpack();
        test.log(Status.INFO, "Clicked on add to cart button of Sauce Labs Backback");
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnCart();
        test.log(Status.INFO, "Clicked on cart");
        UtilMethods.waitForSeconds(1);

        checkoutPage.clickOnCheckoutButton();
        test.log(Status.INFO, "Clicked on the checkout button");
        UtilMethods.waitForSeconds(1);

        checkoutPage.setFirstName(firstName);
        test.log(Status.INFO, "Enter first name");
        UtilMethods.waitForSeconds(1);
        checkoutPage.setLastName(lastName);
        test.log(Status.INFO, "Enter last name");
        UtilMethods.waitForSeconds(1);
        checkoutPage.setPostalCode(postalCode);
        test.log(Status.INFO, "Enter postal code");
        UtilMethods.waitForSeconds(1);

        checkoutPage.clickOnContinueCheckoutButton();
        test.log(Status.INFO, "Clicked on continue checkout button");
        UtilMethods.waitForSeconds(1);

        checkAssertion(firstName, lastName, postalCode, test);
        test.log(Status.INFO, "Tesing with first name: " + firstName + ", last name: " + lastName + ", postal code: " + postalCode );

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
        ExtentTest test = extent.createTest("Subtotal Amount Check");
        loginToSauceLabDemo();
        test.log(Status.INFO, "User logged in");
        setInventoryPage();
        setCheckoutPage();

        inventoryPage.clickOnAddToCartSaucaeLabBackpack();
        test.log(Status.INFO, "Clicked on add to cart button of Sauce Labs Backpack");
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnAddToCartSauceLabsBikeLight();
        test.log(Status.INFO, "Clicked on add to cart button of Sauce Labs Bike Light");
        UtilMethods.waitForSeconds(1);

        inventoryPage.clickOnCart();
        test.log(Status.INFO, "Clicked on cart");
        UtilMethods.waitForSeconds(1);
        checkoutPage.clickOnCheckoutButton();
        test.log(Status.INFO, "Clicked on checkout button");
        UtilMethods.waitForSeconds(1);

        checkoutPage.setFirstName("Babab");
        test.log(Status.INFO, "Enter first name");
        UtilMethods.waitForSeconds(1);
        checkoutPage.setLastName("Yogurt");
        test.log(Status.INFO, "Enter last name");
        UtilMethods.waitForSeconds(1);
        checkoutPage.setPostalCode("2397");
        test.log(Status.INFO, "Enter postal code");
        UtilMethods.waitForSeconds(1);

        checkoutPage.clickOnContinueCheckoutButton();
        test.log(Status.INFO, "Clicked on continue checkout button");
        UtilMethods.waitForSeconds(1);

        Double sauceLabsBackpackPrice = checkoutPage.getCheckoutFirstItemPrice();
        Double sauceLabsBikeLightPrice = checkoutPage.getCheckoutSecondItemPrice();

        Double actulaSubTotalPriceWithoutTax = checkoutPage.getSubTotalPriceWithoutTax();
        Double expectedSubTotalPriceWithoutTax = sauceLabsBackpackPrice + sauceLabsBikeLightPrice;

        try{
            Assert.assertEquals(actulaSubTotalPriceWithoutTax, expectedSubTotalPriceWithoutTax);
            test.log(Status.PASS, "Sub total price calculation is okay");
        } catch(AssertionError error){
            test.log(Status.FAIL, "" + error);
        }

        checkoutPage.clickOnCancelButtonFromCheckoutOverview();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnSauceLabsBackpackRemoveButton();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnRemoveSauceLabsBikeLight();
        UtilMethods.waitForSeconds(1);

    }


    @Test(priority = 4, groups = "checkout")
    public void totalPriceTest(){
        ExtentTest test = extent.createTest("Total Amount Check");
        loginToSauceLabDemo();
        setInventoryPage();
        setCheckoutPage();

        inventoryPage.clickOnAddToCartSaucaeLabBackpack();
        test.log(Status.INFO, "Clicked on add to cart button of Sauce Labs Backpack");
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnAddToCartSauceLabsBikeLight();
        test.log(Status.INFO, "Clicked on add to cart button of Sauce Labs Bike Light");
        UtilMethods.waitForSeconds(1);

        inventoryPage.clickOnCart();
        test.log(Status.INFO, "Clicked on cart");
        UtilMethods.waitForSeconds(1);
        checkoutPage.clickOnCheckoutButton();
        test.log(Status.INFO, "Clicked on checkout button");
        UtilMethods.waitForSeconds(1);

        checkoutPage.setFirstName("Babab");
        test.log(Status.INFO, "Enter first name");
        UtilMethods.waitForSeconds(1);

        checkoutPage.setLastName("Yogurt");
        test.log(Status.INFO, "Enter last name");
        UtilMethods.waitForSeconds(1);

        checkoutPage.setPostalCode("2397");
        test.log(Status.INFO, "Enter postal code");
        UtilMethods.waitForSeconds(1);

        checkoutPage.clickOnContinueCheckoutButton();
        test.log(Status.INFO, "Clicked on continue checkout button");
        UtilMethods.waitForSeconds(1);

        Double subTotalPrice = checkoutPage.getSubTotalPriceWithoutTax();
        Double taxPrice = checkoutPage.getTaxPrice();

        Double actualTotalPayableAmount = subTotalPrice + taxPrice;
        Double expectedTotalPayableAmount = checkoutPage.getTotalPayableAmount();

        try{
            Assert.assertEquals(actualTotalPayableAmount, expectedTotalPayableAmount);
            test.log(Status.PASS, "Total payable amount calculation is okay");
        } catch(AssertionError error){
            test.log(Status.FAIL, "" + error);
        }

        checkoutPage.clickOnCancelButtonFromCheckoutOverview();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnSauceLabsBackpackRemoveButton();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnRemoveSauceLabsBikeLight();
        UtilMethods.waitForSeconds(1);
    }

    @Test(priority = 5, groups = "checkout")
    public void cancelButtonTest(){
        ExtentTest test = extent.createTest("Cancel Button Test of Checkout Overview");
        loginToSauceLabDemo();
        test.log(Status.INFO, "User logged in");
        setInventoryPage();
        setCheckoutPage();

        inventoryPage.clickOnAddToCartSaucaeLabBackpack();
        test.log(Status.INFO, "Clicked on add to cart button of Sauce Labs Backpack");
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnAddToCartSauceLabsBikeLight();
        test.log(Status.INFO, "Clicked on add to cart button of Sauce Labs Bike Light");
        UtilMethods.waitForSeconds(1);

        inventoryPage.clickOnCart();
        test.log(Status.INFO, "Clicked on cart");
        UtilMethods.waitForSeconds(1);
        checkoutPage.clickOnCheckoutButton();
        test.log(Status.INFO, "Clicked on checkout button");
        UtilMethods.waitForSeconds(1);

        checkoutPage.setFirstName("Babab");
        test.log(Status.INFO, "Enter first name");
        UtilMethods.waitForSeconds(1);

        checkoutPage.setLastName("Yogurt");
        test.log(Status.INFO, "Enter last name");
        UtilMethods.waitForSeconds(1);

        checkoutPage.setPostalCode("2397");
        test.log(Status.INFO, "Enter postal code");
        UtilMethods.waitForSeconds(1);

        checkoutPage.clickOnContinueCheckoutButton();
        test.log(Status.INFO, "Clicked on continue checkout button");
        UtilMethods.waitForSeconds(1);
        checkoutPage.clickOnCancelButtonFromCheckoutOverview();

        String actualProductPageTitle = inventoryPage.getActualProductPageTitle();
        String expectedProductPageTitle = "Products";

        try{
            Assert.assertEquals(actualProductPageTitle, expectedProductPageTitle);
            test.log(Status.PASS, "Cancel button is working fine");
        } catch(AssertionError error){
            test.log(Status.FAIL, "" + error);
        }

        inventoryPage.clickOnSauceLabsBackpackRemoveButton();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnRemoveSauceLabsBikeLight();
        UtilMethods.waitForSeconds(1);
    }

    @Test(priority = 6, groups = "checkout")
    public void completeCheckoutTest(){
        ExtentTest test = extent.createTest("Checkout Complete Test");

        loginToSauceLabDemo();
        test.log(Status.INFO, "User logger in");
        setInventoryPage();
        setCheckoutPage();

        inventoryPage.clickOnAddToCartSaucaeLabBackpack();
        test.log(Status.INFO, "Clicked on add to cart button of Sauce Labs Backpack");
        UtilMethods.waitForSeconds(1);

        inventoryPage.clickOnAddToCartSauceLabsBikeLight();
        test.log(Status.INFO, "Clicked on add to cart button of Sauce Labs Bike Light");
        UtilMethods.waitForSeconds(1);

        inventoryPage.clickOnCart();
        test.log(Status.INFO, "Clicked on cart");
        UtilMethods.waitForSeconds(1);

        checkoutPage.clickOnCheckoutButton();
        UtilMethods.waitForSeconds(1);
        test.log(Status.INFO, "Clicked on checkout button");

        checkoutPage.setFirstName("Babab");
        test.log(Status.INFO, "Enter first name");
        UtilMethods.waitForSeconds(1);

        checkoutPage.setLastName("Yogurt");
        test.log(Status.INFO, "Enter last name");
        UtilMethods.waitForSeconds(1);

        checkoutPage.setPostalCode("2397");
        test.log(Status.INFO, "Enter postal code");
        UtilMethods.waitForSeconds(1);

        checkoutPage.clickOnContinueCheckoutButton();
        test.log(Status.INFO, "Clicked on the continue checkout button");
        UtilMethods.waitForSeconds(1);

        checkoutPage.clickOnFinishButton();
        test.log(Status.INFO, "Clicked on the finish button");
        UtilMethods.waitForSeconds(1);

        String actualSuccessfullCheckoutMessage = checkoutPage.getCheckoutSuccessfullMessage();
        String expectedSuccessfullCheckoutMessage = "Thank you for your order!";

        try{
            Assert.assertEquals(actualSuccessfullCheckoutMessage, expectedSuccessfullCheckoutMessage);
            test.log(Status.PASS, "Checkout has been completed successfully");
        } catch(AssertionError error){
            test.log(Status.FAIL, "" + error);
        }
    }

    @Test(priority = 7, groups = "checkout")
    public void backHomeButtonTest(){
        ExtentTest test = extent.createTest("Back Home Button Test");

        loginToSauceLabDemo();
        test.log(Status.INFO, "User logged in");
        setInventoryPage();
        setCheckoutPage();

        inventoryPage.clickOnAddToCartSaucaeLabBackpack();
        test.log(Status.INFO, "Clicked on add to cart button of Sauce Labs Backpack");
        UtilMethods.waitForSeconds(1);

        inventoryPage.clickOnAddToCartSauceLabsBikeLight();
        test.log(Status.INFO, "Clicked on add to cart button of Sauce Labs Bike Light");
        UtilMethods.waitForSeconds(1);

        inventoryPage.clickOnCart();
        test.log(Status.INFO, "Clicked on cart");
        UtilMethods.waitForSeconds(1);

        checkoutPage.clickOnCheckoutButton();
        test.log(Status.INFO, "Clicked on checkout button");
        UtilMethods.waitForSeconds(1);

        checkoutPage.setFirstName("Babab");
        test.log(Status.INFO, "Enter first name");
        UtilMethods.waitForSeconds(1);

        checkoutPage.setLastName("Yogurt");
        test.log(Status.INFO, "Enter last name");
        UtilMethods.waitForSeconds(1);

        checkoutPage.setPostalCode("2397");
        test.log(Status.INFO, "Enter postal code");
        UtilMethods.waitForSeconds(1);

        checkoutPage.clickOnContinueCheckoutButton();
        test.log(Status.INFO, "Clicked on the continue checkout button");
        UtilMethods.waitForSeconds(1);

        checkoutPage.clickOnFinishButton();
        test.log(Status.INFO, "Clicked on finish button");
        UtilMethods.waitForSeconds(1);

        checkoutPage.clickOnBackHomeButton();
        test.log(Status.INFO, "Clicked on the back home button");
        UtilMethods.waitForSeconds(1);

        String actualProductPageTitle = inventoryPage.getActualProductPageTitle();
        String expectedProductPageTitle = "Products";

        try{
            Assert.assertEquals(actualProductPageTitle, expectedProductPageTitle);
            test.log(Status.PASS, "Back home button is working fine");
        } catch(AssertionError error){
            test.log(Status.FAIL, "" + error);
        }
    }


    private void checkAssertion(String firstName, String lastName, String postalCode, ExtentTest test){
        String actualString; 
        String expectedString; 

        switch(firstName + "_+_" + lastName + "_+_" + postalCode) {
            case "_+__+_":
                test.warning("All fields are empty");
                break;
            case "_+_Jener_+_1577":
                test.warning("Error: First Name is required");
                actualString = checkoutPage.getActualErrorMessageForMissingFirstName();
                expectedString = "Error: First Name is required";          
                Assert.assertEquals(actualString, expectedString);
                break;
            case "Kylee_+__+_5655":
                test.warning("Error: Last Name is required");
                actualString = checkoutPage.getActualErrorMessageForMissingLastName();
                expectedString = "Error: Last Name is required";          
                Assert.assertEquals(actualString, expectedString);
                break;
            case "Kylee_+_Jener_+_":
                test.warning("Error: Postal Code is required");
                actualString = checkoutPage.getActualErrorMessageForMissingPostalCode();
                expectedString = "Error: Postal Code is required";          
                Assert.assertEquals(actualString, expectedString);
                break;    
            case "Kylee_+_Jener_+_8873":
                test.log(Status.INFO, "All info prived");
                actualString = checkoutPage.getActualCheckoutOverviewPageTitle();
                expectedString = "Checkout: Overview";       

                try{
                    Assert.assertEquals(actualString, expectedString);
                    test.log(Status.PASS, "Successfully landed to checkout overview page");
                } catch(AssertionError error){
                    test.log(Status.FAIL, "" + error);
                }
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
