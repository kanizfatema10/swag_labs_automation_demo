package model;

import org.openqa.selenium.By;

public class InventoryLocators {
    public static By appLogoName = By.cssSelector(".app_logo");
    public static By burgerMenuIcon = By.id("react-burger-menu-btn");
    public static By aboutMenuText = By.id("about_sidebar_link");
    public static By logOutMenuText = By.id("logout_sidebar_link");
    public static By burgerMenuCrossIcon = By.id("react-burger-cross-btn");
    public static By inventoryPageTitle = By.xpath("    //span[@class='title'] [contains(text(),'Products')]");
    public static By productFilter = By.xpath("//select[@class='product_sort_container']");
    public static By aTozFilterOption = By.xpath("//select/option[contains(text(),'Name (A to Z)')]");
    public static By zToaFilterOption = By.xpath("//select/option[contains(text(),'Name (Z to A)')]");
    public static By priceLowToHighFilterOption = By.xpath("//select/option[contains(text(),'Price (low to high)')]");
    public static By priceHighToLowFilterOption = By.xpath("//select/option[contains(text(),'Price (high to low)')]");
    public static By firstItemLink = By.xpath("//div[@class='inventory_item_name'][contains(text(),'Sauce Labs Backpack')]");
    public static By itemNameInDetailPage = By.xpath("//div[@class='inventory_details_name large_size'] [contains(text(),'Sauce Labs Backpack')]");
    public static By backToProductButton = By.id("back-to-products");
    public static By addToCardSauceLabsBackpack = By.id("add-to-cart-sauce-labs-backpack");
    public static By removeSauceLabsBackpack = By.id("remove-sauce-labs-backpack");
    public static By addToCartSauceLabBoltTshirt = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    public static By removeSauceLabBoltTshirt = By.id("remove-sauce-labs-bolt-t-shirt");
    public static By addToCartSauceLabBikeLight = By.id("add-to-cart-sauce-labs-bike-light");
    public static By shoppingCartLink = By.id("shopping_cart_container");
    public static By shoppingCartBadge = By.className("shopping_cart_badge");
    public static By yourCartTitle = By.xpath("//span[@class='title'] [contains(text(),'Your Cart')]");
    public static By sauceLabBackpackAtCartItem = By.xpath("(//div[@class='cart_item']//div[@class='inventory_item_name'])[contains(text(),'Sauce Labs Backpack')]");
    public static By continueShoppingButton = By.id("continue-shopping");
    public static By checkoutButton = By.id("checkout");
    public static By inventoryItemList = By.className("inventory_list");
    public static By priceOfEachItem = By.xpath("//div[@class='inventory_item_price']");
    public static By inventoryItemNameList = By.className("inventory_item_name");





}
