package tests;

import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;
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

public class InventoryTest extends Base{

    RemoteWebDriver driver;
    Credentials credentials = new Credentials();
    Integer currentCartNumber;
    InventoryPage inventoryPage;

    @Test(priority = 1, groups = "inventory")
    public void itemDetailViewTest(){
        ExtentTest test = extent.createTest("Item Detail View Test");

        setInventoryPage();
        loginToSauceLabDemo();
        test.log(Status.INFO, "User logged in");

        inventoryPage.clickOnFirstItemLink();
        test.log(Status.INFO, "Click on the link of Sauce labs backpack");
        UtilMethods.waitForSeconds(1);

        String actualFirstItemName = inventoryPage.getActualFirstItemName();
        String expectedFirstItemName = "Sauce Labs Backpack";

        Assert.assertEquals(actualFirstItemName, expectedFirstItemName);
        test.log(Status.PASS, "Successfully landed on the item detail page");
    }

    @Test(priority = 2, groups = "inventory")
    public void backButtonToProductPageTest(){
        ExtentTest test = extent.createTest("Back Button Test of Item Detail Page");
        
        setInventoryPage();
        loginToSauceLabDemo();
        test.log(Status.INFO, "User logged in");

        inventoryPage.clickOnFirstItemLink();
        test.log(Status.INFO, "Clicked on the first item link");
        UtilMethods.waitForSeconds(1);

        inventoryPage.clickOnBackButton();
        test.log(Status.INFO, "Clicked on the back button icon");
        UtilMethods.waitForSeconds(1);

        String actualProductPageTitle = inventoryPage.getActualProductPageTitle();
        String expectedProductPageTitle = "Products";

        Assert.assertEquals(actualProductPageTitle, expectedProductPageTitle);
        test.log(Status.PASS,  "Successfully back to products page from item detail page");
    }

    @Test(priority = 3, groups = "inventory")
    public void buttonTextChangeToRemoveTest(){
        ExtentTest test = extent.createTest("Button Text Change Test: Add to cart --> Remove");
        
        setInventoryPage();
        loginToSauceLabDemo();
        test.log(Status.INFO, "User logged in");

        inventoryPage.clickOnAddToCartSaucaeLabBackpack();
        test.log(Status.INFO, "Clicked on the add to card button of Sauce Labs Backpack");
        UtilMethods.waitForSeconds(1);

        String actualRemoveButtonText = inventoryPage.getActualRemoveButtonText();  
        String expectedRemoveButtonText = "Remove";

        Assert.assertEquals(actualRemoveButtonText, expectedRemoveButtonText);
        test.log(Status.PASS,  "Button text changed from 'Add to card' to 'Remove'");

        inventoryPage.clickOnSauceLabsBackpackRemoveButton();
        UtilMethods.waitForSeconds(1);
    }

    @Test(priority = 4, groups = "inventory")
    public void cartNumberIncrementTest(){
        ExtentTest test = extent.createTest("Cart Number Increment Test");
        
        setInventoryPage();
        loginToSauceLabDemo();
        test.log(Status.INFO, "User logged in");

        inventoryPage.clickOnAddToCartSaucaeLabBackpack();
        test.log(Status.INFO, "Clicked on the add to card button of Sauce Labs Backpack");
        UtilMethods.waitForSeconds(1);

        currentCartNumber = inventoryPage.getCartCurrentNumber();

        Assert.assertTrue(currentCartNumber == 1, "Shopping cart number has not been incremented.");
        test.log(Status.PASS,  "Shopping cart number has increased successfully");

        inventoryPage.clickOnSauceLabsBackpackRemoveButton();
        UtilMethods.waitForSeconds(1);
    }

    @Test(priority = 5, groups = "inventory")
    public void addedItemOnCartDetailTest(){
        ExtentTest test = extent.createTest("View Added Item on Cart Detail Test");

        setInventoryPage();
        loginToSauceLabDemo();
        test.log(Status.INFO, "User logged in");

        inventoryPage.clickOnAddToCartSaucaeLabBackpack();
        test.log(Status.INFO, "Clicked on the add to card button of Sauce Labs Backpack");
        UtilMethods.waitForSeconds(1);

        inventoryPage.clickOnCart();
        test.log(Status.INFO, "Clicked on cart");
        UtilMethods.waitForSeconds(1);
        

        String actualAddedItemToCart = inventoryPage.getSauceLabBackpackAtCartItem();
        String expectedAddedItemToCard = "Sauce Labs Backpack";

        Assert.assertEquals(actualAddedItemToCart, expectedAddedItemToCard);
        test.log(Status.PASS, "Found the exact added item on cart detail");

        inventoryPage.clickOnContinueShoppingButton();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnSauceLabsBackpackRemoveButton();
        UtilMethods.waitForSeconds(1);
    }

    @Test(priority = 6, groups = "inventory")
    public void continueShoppingButtonTest(){
        ExtentTest test = extent.createTest("Continue Shopping Button Test");
        
        setInventoryPage();
        loginToSauceLabDemo();
        test.log(Status.INFO, "User logged in");

        inventoryPage.clickOnAddToCartSaucaeLabBackpack();
        test.log(Status.INFO, "Clicked on the add to card button of Sauce Labs Backpack");
        UtilMethods.waitForSeconds(.5);

        inventoryPage.clickOnCart();
        test.log(Status.INFO, "Clicked on cart");
        UtilMethods.waitForSeconds(1);

        inventoryPage.clickOnContinueShoppingButton();
        test.log(Status.INFO, "Clicked on the continue shopping button");
        UtilMethods.waitForSeconds(1);

        String actualProductPageTitle = inventoryPage.getActualProductPageTitle();
        String expectedProductPageTitle = "Products";

        Assert.assertEquals(actualProductPageTitle, expectedProductPageTitle);
        test.log(Status.PASS, "Successfully back to products page form cart detail page");


        inventoryPage.clickOnSauceLabsBackpackRemoveButton();
        UtilMethods.waitForSeconds(1);

    }

    @Test(priority = 7, groups = "inventory")
    public void buttonTextChangeToAddToCart(){
        ExtentTest test = extent.createTest("Button Text Change Test: Remove --> Add to cart");
        
        setInventoryPage();
        loginToSauceLabDemo();
        test.log(Status.INFO, "User logged in");

        inventoryPage.clickOnAddToCartSauceLabsBoltTshirt();
        test.log(Status.INFO, "Clicked on add to cart button of Sauce Labs Bolt T-shirt");
        UtilMethods.waitForSeconds(1);

        inventoryPage.clickOnRemoveSauceLabsBoltTshirt();
        test.log(Status.INFO, "Clicked on remove button of Sauce Labs Bolt T-shirt");
        UtilMethods.waitForSeconds(1);

        String actualAddToCartButtonText = inventoryPage.getActualAddToCartText();
        String expectedAddToCartButtonText = "Add to cart";

        Assert.assertEquals(actualAddToCartButtonText, expectedAddToCartButtonText);
        test.log(Status.PASS, "Button text has changed successfully from 'Remove' to 'Add to cart'");

    }

    @Test(priority = 8, groups = "inventory")
    public void cartNumberDecrementTest(){
        ExtentTest test = extent.createTest("Cart Number Decrement Test");

        setInventoryPage();
        loginToSauceLabDemo();
        test.log(Status.INFO, "User logged in");

        inventoryPage.clickOnAddToCartSaucaeLabBackpack();
        test.log(Status.INFO, "Clicked on the add to card button of Sauce Labs Backpack");
        UtilMethods.waitForSeconds(1);
        currentCartNumber = inventoryPage.getCartCurrentNumber();

        inventoryPage.clickOnAddToCartSauceLabsBikeLight();
        test.log(Status.INFO, "Clicked on the add to card button of Sauce Labs Bike Light");
        UtilMethods.waitForSeconds(1);
        currentCartNumber = inventoryPage.getCartCurrentNumber();

        inventoryPage.clickOnAddToCartSauceLabsBoltTshirt();
        test.log(Status.INFO, "Clicked on add to cart button of Sauce Labs Bolt T-shirt");
        UtilMethods.waitForSeconds(1);
        currentCartNumber = inventoryPage.getCartCurrentNumber();

        inventoryPage.clickOnRemoveSauceLabsBoltTshirt();
        test.log(Status.INFO, "Clicked on remove button of Sauce Labs Bolt T-shirt");
        UtilMethods.waitForSeconds(1);
        currentCartNumber = inventoryPage.getCartCurrentNumber();

        Assert.assertTrue(currentCartNumber == 2, "Shopping cart number has not been decreased.");
        test.log(Status.PASS, "Shopping cart number has decreased successfully");

    }

    @Test (priority = 9, groups = {"inventory", "inventorySort"})
    public void priceLowToHighSortingTest(){
        ExtentTest test = extent.createTest("Low to Hight Price Sorting Test");

        setInventoryPage();
        loginToSauceLabDemo();
        test.log(Status.INFO, "User logged in");

        WebElement itemPriceList = inventoryPage.getInventoryItemList();

        List<String> originalItemPriceList = inventoryPage.getOriginalItemPriceListExtracted(itemPriceList);
        System.out.println(originalItemPriceList);

        List<Double> doubleOriginalPriceList = inventoryPage.convertItemPriceListToDouble(originalItemPriceList);

        Collections.sort(doubleOriginalPriceList);

        List<String> expectedLowToHighSortedPriceList = inventoryPage.convertItemPriceListToString(doubleOriginalPriceList);
        System.out.println(expectedLowToHighSortedPriceList);

        inventoryPage.clickOnProductSortButton();
        test.log(Status.INFO, "Clicked on product sort field");
        UtilMethods.waitForSeconds(1);

        inventoryPage.clickOnLowToHighPriceSort();
        test.log(Status.INFO, "Select low to high sorting option");
        UtilMethods.waitForSeconds(1);

        WebElement itemListAfterSorting = inventoryPage.getInventoryItemList();

        List<String> actualLowToHightSortedPriceList = inventoryPage.getSortedPriceList(itemListAfterSorting);

        Assert.assertEquals(actualLowToHightSortedPriceList, expectedLowToHighSortedPriceList);
        test.log(Status.PASS, "Low to high sorting is working fine");
    }

    @Test (priority = 10, groups = {"inventory", "inventorySort"})
    public void priceHighToLowSortingTest(){
        ExtentTest test = extent.createTest("High to Low Price Sorting Test");

        setInventoryPage();
        loginToSauceLabDemo();
        test.log(Status.INFO, "User logged in");

        WebElement itemPriceList = inventoryPage.getInventoryItemList();

        List<String> originalItemPriceList = inventoryPage.getOriginalItemPriceListExtracted(itemPriceList);
        System.out.println(originalItemPriceList);

        List<Double> doubleOriginalPriceList = inventoryPage.convertItemPriceListToDouble(originalItemPriceList);

        Collections.sort(doubleOriginalPriceList, Collections.reverseOrder());

        List<String> expectedHighToLowSortedPriceList = inventoryPage.convertItemPriceListToString(doubleOriginalPriceList);
        System.out.println(expectedHighToLowSortedPriceList);

        inventoryPage.clickOnProductSortButton();
        test.log(Status.INFO, "Clicked on product sort field");
        UtilMethods.waitForSeconds(1);

        inventoryPage.clickOnHighToLowPriceSort();
        test.log(Status.INFO, "Select high to low sorting option");
        UtilMethods.waitForSeconds(1);

        WebElement itemListAfterSorting = inventoryPage.getInventoryItemList();

        List<String> actualHighToLowtSortedPriceList = inventoryPage.getSortedPriceList(itemListAfterSorting);

        Assert.assertEquals(actualHighToLowtSortedPriceList, expectedHighToLowSortedPriceList);
        test.log(Status.PASS, "High to low sorting is working fine");
    }

    @Test(priority = 11, groups = {"inventory", "inventorySort"})
    public void aToZDefaultSortingTesting(){
        ExtentTest test = extent.createTest("A to Z Sorting Test");

        setInventoryPage();
        loginToSauceLabDemo();
        test.log(Status.INFO, "User logged in");

        List<WebElement> itemList = inventoryPage.getItemList();
        List<String> actualItemNameList = inventoryPage.getItemNameList(itemList);
        List<String> expectedSortedItemList = inventoryPage.getExpectedDefaultSortedItemList(actualItemNameList);

        Assert.assertEquals(actualItemNameList, expectedSortedItemList);
        test.log(Status.PASS, "Item list is by default sorted from A to Z");
    }

    @Test(priority = 12, groups = {"inventory", "inventorySort"})
    public void zToASortingTest(){
        ExtentTest test = extent.createTest("Z to A Sorting Test");

        setInventoryPage();
        loginToSauceLabDemo();
        test.log(Status.INFO, "User logged in");

        List<WebElement> itemList = inventoryPage.getItemList();
        List<String> itemNameList = inventoryPage.getItemNameList(itemList);
        List<String> expectedSortedItemList = inventoryPage.getExpectedSortedItemList(itemNameList);
        System.out.println(expectedSortedItemList);

        inventoryPage.clickOnProductSortButton();
        test.log(Status.INFO, "Clicked on product sort field");
        UtilMethods.waitForSeconds(1);

        inventoryPage.clickOnZtoASortOption();
        test.log(Status.INFO, "Select Z to A sorting option");
        UtilMethods.waitForSeconds(1);

        List<WebElement> itemListAfterSorting = inventoryPage.getItemList();
        List<String> actualSortedItemList = inventoryPage.getItemNameList(itemListAfterSorting);
        System.out.println(actualSortedItemList);

        Assert.assertEquals(actualSortedItemList, expectedSortedItemList);
        test.log(Status.PASS, "Z to A sorting is working fine");

    }

    private void loginToSauceLabDemo(){

        driver = super.getActiveDriver();
        MainLoginPage mainLoginPage = new MainLoginPage(driver);

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


}
