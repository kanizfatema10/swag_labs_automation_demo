package model;

import org.openqa.selenium.By;

public class CheckoutLocators {
    public static By checkoutButton = By.id("checkout");
    public static By yourCheckoutInfo = By.xpath("//span[@class='title'] [contains(text(),'Checkout: Your Information')]");
    public static By firstName = By.id("first-name");
    public static By lastName = By.id("last-name");
    public static By postalCode = By.id("postal-code");
    public static By continueCheckoutButton = By.id("continue");
    public static By checkoutCompleteTitle = By.xpath("//span[@class='title'] [contains(text(),'Checkout: Complete!')]");
    public static By orderConfirmationMessage = By.xpath("//H2[@class='complete-header'] [contains(text(),'Thank you for your order!')]");   
    public static By errorMessageForEmptyFirstName = By.xpath("//h3[contains(text(),'Error: First Name is required')]");
    public static By errorMessageForEmptyLastName = By.xpath("//h3[contains(text(),'Error: Last Name is required')]");
    public static By errorMessageForEmptyPostalCode = By.xpath("//h3[contains(text(),'Error: Postal Code is required')]");
    public static By checkoutOverviewTitle = By.xpath("//span[@class='title'] [contains(text(),'Checkout: Overview')]");
    public static By checkoutFirstItemPrice = By.xpath("(//div[@class='inventory_item_price'])[1]");
    public static By checkoutSecondItemPrice = By.xpath("(//div[@class='inventory_item_price'])[2]");
    public static By subTotalPriceWithoutTax = By.xpath("//div[@class='summary_subtotal_label']");
    public static By itemTotal = By.xpath("//div[@class='summary_subtotal_label']");
    public static By taxAmount = By.xpath("//div[@class='summary_tax_label']");
    public static By totalPayableAmount = By.xpath("//div[@class='summary_info_label summary_total_label']");
    public static By finishButton = By.id("finish");
    public static By cancelButton = By.id("cancel");
    public static By backHomeButton = By.id("back-to-products");



}
