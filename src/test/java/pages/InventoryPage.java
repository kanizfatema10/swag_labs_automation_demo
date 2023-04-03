package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import base.Base;
import model.CommonMethods;
import model.InventoryLocators;

public class InventoryPage extends Base{

    RemoteWebDriver driver;
    CommonMethods commonMethods;

    public InventoryPage(RemoteWebDriver driver){
        //driver = super.getActiveDriver();
        this.driver = driver;
        commonMethods = new CommonMethods(this.driver);
    }
    
    public void clickOnAboutMenu(){
        commonMethods.clickOnButton(InventoryLocators.aboutMenuText);
    }

    public String getAboutPageURL(){
        return commonMethods.getCurrentPageURL();
    }

    public String getLoginPageURL(){
        return commonMethods.getCurrentPageURL();
    }

    public void clickOnLogoutMenu(){
        commonMethods.clickOnButton(InventoryLocators.logOutMenuText);
    }

    public String getLogoutPageURL(){
        return commonMethods.getCurrentPageURL();
    }

    public void clickOnBurgerMenu(){
        commonMethods.clickOnButton(InventoryLocators.burgerMenuIcon);
    }

    public void clickOnFirstItemLink(){
        commonMethods.clickOnButton(InventoryLocators.firstItemLink);
    }

    public void clickOnBackButton(){
        commonMethods.clickOnButton(InventoryLocators.backToProductButton);
    }

    public void clickOnAddToCartSaucaeLabBackpack(){
        commonMethods.clickOnButton(InventoryLocators.addToCardSauceLabsBackpack);
    }

    public void clickOnCart(){
        commonMethods.clickOnButton(InventoryLocators.shoppingCartLink);
    }

    public String getActualRemoveButtonText(){
        String actualRemoveButtonText = driver.findElement(InventoryLocators.removeSauceLabsBackpack).getText();
        return actualRemoveButtonText;
    }

    public String getActualProductPageTitle(){
        String actualProductPageTitle = driver.findElement(InventoryLocators.inventoryPageTitle).getText();
        return actualProductPageTitle;
    }

    public String getActualFirstItemName(){
        String actualFirstItemName = driver.findElement(InventoryLocators.itemNameInDetailPage).getText();
        return actualFirstItemName;
    }

    public Integer getCartCurrentNumber(){
        String cartNumberString = driver.findElement(InventoryLocators.shoppingCartBadge).getText();
        int cartCurrentNumberInt = Integer.parseInt(cartNumberString);
        return cartCurrentNumberInt;
    }

    public String getSauceLabBackpackAtCartItem(){
        String actualAddedItemToCart = driver.findElement(InventoryLocators.sauceLabBackpackAtCartItem).getText();
        return actualAddedItemToCart;
    }

    public void clickOnAddToCartSauceLabsBikeLight(){
        commonMethods.clickOnButton(InventoryLocators.addToCartSauceLabBikeLight);
    }

    public void clickOnAddToCartSauceLabsBoltTshirt(){
        commonMethods.clickOnButton(InventoryLocators.addToCartSauceLabBoltTshirt);
    }

    public void clickOnRemoveSauceLabsBoltTshirt(){
        commonMethods.clickOnButton(InventoryLocators.removeSauceLabBoltTshirt);
    }

    public String getActualAddToCartText(){
        String actualAddToCartButtonText = driver.findElement(InventoryLocators.addToCartSauceLabBoltTshirt).getText();
        return actualAddToCartButtonText;
    }

    public void clickOnContinueShoppingButton(){
        commonMethods.clickOnButton(InventoryLocators.continueShoppingButton);
    }

    public void clickOnSauceLabsBackpackRemoveButton(){
        commonMethods.clickOnButton(InventoryLocators.removeSauceLabsBackpack);
    }
}