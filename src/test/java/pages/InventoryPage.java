package pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.Base;
import model.CommonMethods;
import model.InventoryLocators;

public class InventoryPage extends Base{

    RemoteWebDriver driver;
    CommonMethods commonMethods;

    public InventoryPage(RemoteWebDriver driver){
        this.driver = driver;
        commonMethods = new CommonMethods(this.driver);
    }

    public String getAppLogoName(){
        return driver.findElement(InventoryLocators.appLogoName).getText();
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
        return driver.findElement(InventoryLocators.removeSauceLabsBackpack).getText();
    }

    public String getActualProductPageTitle(){
        return driver.findElement(InventoryLocators.inventoryPageTitle).getText();
    }

    public String getActualFirstItemName(){
        return driver.findElement(InventoryLocators.itemNameInDetailPage).getText();
    }

    public Integer getCartCurrentNumber(){
        String cartNumberString = driver.findElement(InventoryLocators.shoppingCartBadge).getText();
        return Integer.parseInt(cartNumberString);
    }

    public String getSauceLabBackpackAtCartItem(){
        return driver.findElement(InventoryLocators.sauceLabBackpackAtCartItem).getText();
    }

    public void clickOnAddToCartSauceLabsBikeLight(){
        commonMethods.clickOnButton(InventoryLocators.addToCartSauceLabBikeLight);
    }

    public void clickOnAddToCartSauceLabsBoltTshirt(){
        commonMethods.clickOnButton(InventoryLocators.addToCartSauceLabBoltTshirt);
    }

    public void clickOnRemoveSauceLabsBikeLight(){
        commonMethods.clickOnButton(InventoryLocators.removeSauceLabsBikeLight);
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

    public WebElement getInventoryItemList(){
       WebElement inventoryItemList = driver.findElement(InventoryLocators.inventoryItemList);
       return inventoryItemList;
    }

    public void clickOnProductSortButton(){
        commonMethods.clickOnButton(InventoryLocators.productFilter);
    }

    public void clickOnLowToHighPriceSort(){
        commonMethods.clickOnButton(InventoryLocators.priceLowToHighFilterOption);
    }

    public void clickOnHighToLowPriceSort(){
        commonMethods.clickOnButton(InventoryLocators.priceHighToLowFilterOption);
    }

    public List<String> getOriginalItemPriceListExtracted(WebElement itemPriceList){
        return itemPriceList.findElements(InventoryLocators.priceOfEachItem)
                                            .stream()
                                            .map(priceWithoutDollarSign -> priceWithoutDollarSign.getText().replace("$", ""))
                                            .collect(Collectors.toList());
    }

    public List<Double> convertItemPriceListToDouble(List<String> originalItemPriceList){
        return originalItemPriceList        .stream()
                                            .map(Double::parseDouble)
                                            .collect(Collectors.toList());
    }

    public List<String> convertItemPriceListToString(List<Double> doubleOriginalPriceList){
        return doubleOriginalPriceList      .stream()
                                            .map(String::valueOf)
                                            .collect(Collectors.toList());
    }

    public List<String> getSortedPriceList(WebElement itemListAfterSorting){
        return itemListAfterSorting.findElements(InventoryLocators.priceOfEachItem)
                                            .stream()
                                            .map(priceWithoutDollarSign -> priceWithoutDollarSign.getText().replace("$", ""))
                                            .collect(Collectors.toList());
    }

    public List<WebElement> getItemList(){
        return driver.findElements(InventoryLocators.inventoryItemNameList);
    }

    public List<String> getItemNameList(List<WebElement> itemList){
        return itemList
                        .stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList());
    }

    public List<String> getExpectedDefaultSortedItemList(List<String> itemNameList){

        List<String> expectedSortedItemName = new ArrayList<>(itemNameList);
        Collections.sort(expectedSortedItemName);

        return expectedSortedItemName;
    }

    public List<String> getExpectedSortedItemList(List<String> itemNameList){

        List<String> expectedSortedItemName = new ArrayList<>(itemNameList);
        Collections.sort(expectedSortedItemName, Collections.reverseOrder());

        return expectedSortedItemName;
    }

    public void clickOnZtoASortOption(){
        commonMethods.clickOnButton(InventoryLocators.zToaFilterOption);
    }

    
}
