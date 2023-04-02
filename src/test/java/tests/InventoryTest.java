package tests;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.Base;
import model.Credentials;
import model.UtilMethods;
import pages.InventoryPage;
import pages.MainLoginPage;

public class InventoryTest extends Base{
    RemoteWebDriver driver;
    Credentials credentials = new Credentials();
    Integer currentCartNumber;

    @Test
    public void inventoryTest(){
        driver = super.getActiveDriver();
        MainLoginPage mainLoginPage = new MainLoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);

        mainLoginPage.openHomePage();   
        UtilMethods.waitForSeconds(0.5); 

        mainLoginPage.setUserName(credentials.loginUserNameInputText);
        UtilMethods.waitForSeconds(0.5);  
        mainLoginPage.setPassword(credentials.loginPasswordInputText);
        UtilMethods.waitForSeconds(0.5);  
        mainLoginPage.clickOnLogin();
        UtilMethods.waitForSeconds(1);

        inventoryPage.clickOnFirstItemLink();
        UtilMethods.waitForSeconds(1);
        String actualFirstItemName = inventoryPage.getActualFirstItemName();
        String expectedFirstItemName = "Sauce Labs Backpack";

        Assert.assertEquals(actualFirstItemName, expectedFirstItemName);
        System.out.println("Item name on product page and item name on detail page matches");

        
        inventoryPage.clickOnBackButton();
        UtilMethods.waitForSeconds(1);
        String actualProductPageTitle = inventoryPage.getActualProductPageTitle();
        String expectedProductPageTitle = "Products";

        Assert.assertEquals(actualProductPageTitle, expectedProductPageTitle);
        System.out.println("Successfully back to products page");


        inventoryPage.clickOnAddToCardSaucaeLabBackpack();
        UtilMethods.waitForSeconds(1);
        String actualRemoveButtonText = inventoryPage.getActualRemoveButtonText();  
        String expectedRemoveButtonText = "Remove";

        Assert.assertEquals(actualRemoveButtonText, expectedRemoveButtonText);
        System.out.println("Button text changed from 'Add to card' to 'Remove'");

        currentCartNumber = inventoryPage.getCartCurrentNumber();

        Assert.assertTrue(currentCartNumber == 1, "Shopping cart number has not been incremented.");
        System.out.println("Shopping cart number has increased successfully");

        inventoryPage.clickOnCart();
        UtilMethods.waitForSeconds(1);
        String actualAddedItemToCart = inventoryPage.getSauceLabBackpackAtCartItem();
        String expectedAddedItemToCard = "Sauce Labs Backpack";

        Assert.assertEquals(actualAddedItemToCart, expectedAddedItemToCard);
        System.out.println("Found the exact added item on cart detail");

        inventoryPage.clickOnContinueShoppingButton();
        UtilMethods.waitForSeconds(1);
        inventoryPage.clickOnAddToCartSauceLabsBikeLight();
        UtilMethods.waitForSeconds(1);
        currentCartNumber = inventoryPage.getCartCurrentNumber();

        inventoryPage.clickOnAddToCartSauceLabsBoltTshirt();
        UtilMethods.waitForSeconds(1);
        currentCartNumber = inventoryPage.getCartCurrentNumber();

        inventoryPage.clickOnRemoveSauceLabsBoltTshirt();
        UtilMethods.waitForSeconds(1);
        currentCartNumber = inventoryPage.getCartCurrentNumber();
        String actualAddToCartButtonText = inventoryPage.getActualAddToCartText();
        String expectedAddToCartButtonText = "Add to cart";

        Assert.assertEquals(actualAddToCartButtonText, expectedAddToCartButtonText);
        System.out.println("Button text has changed successfully from 'Remove' to 'Add to cart'");

        Assert.assertTrue(currentCartNumber == 2, "Shopping cart number has not been decreased.");
        System.out.println("Shopping cart number has decreased successfully");

    }

}
