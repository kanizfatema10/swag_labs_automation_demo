package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import base.Base;
import model.CheckoutLocators;
import model.CommonMethods;

public class CheckoutPage extends Base{
    RemoteWebDriver driver;
    CommonMethods commonMethods;

    public CheckoutPage(RemoteWebDriver driver){
        this.driver = driver;
        commonMethods = new CommonMethods(driver);
    }

    public void clickOnCheckoutButton(){
        commonMethods.clickOnButton(CheckoutLocators.checkoutButton);
    }

    public String getActualCheckoutInfoTitle(){
        return driver.findElement(CheckoutLocators.yourCheckoutInfo).getText();
    }

    public String getActualErrorMessageForMissingFirstName(){
        return driver.findElement(CheckoutLocators.errorMessageForEmptyFirstName).getText();
    } 

    public String getActualErrorMessageForMissingLastName(){
        return driver.findElement(CheckoutLocators.errorMessageForEmptyLastName).getText();
    } 

    public String getActualErrorMessageForMissingPostalCode(){
        return driver.findElement(CheckoutLocators.errorMessageForEmptyPostalCode).getText();
    } 

    public void setFirstName(String firstName){
        commonMethods.setText(CheckoutLocators.firstName, firstName);
    }

    public void setLastName(String lastName){
        commonMethods.setText(CheckoutLocators.lastName, lastName);
    }

    public void setPostalCode(String postalCode){
        commonMethods.setText(CheckoutLocators.postalCode, postalCode);
    }

    public void clickOnContinueCheckoutButton(){
        commonMethods.clickOnButton(CheckoutLocators.continueCheckoutButton);
    }

    public void clickOnCancelCheckoutButton(){
        commonMethods.clickOnButton(CheckoutLocators.cancelButton);
    }

    public String getActualCheckoutOverviewPageTitle(){
        return driver.findElement(CheckoutLocators.checkoutOverviewTitle).getText();
    }

    public Double getCheckoutFirstItemPrice(){
        String priceString = driver.findElement(CheckoutLocators.checkoutFirstItemPrice)
                                .getAttribute("innerText")
                                .replace("$", "")
                                .trim();
        return Double.parseDouble(priceString);
    }

    public Double getCheckoutSecondItemPrice(){
        String priceString = driver.findElement(CheckoutLocators.checkoutSecondItemPrice)
                                .getAttribute("innerText")
                                .replace("$", "")
                                .trim();
        return Double.parseDouble(priceString);
    }

    public Double getSubTotalPriceWithoutTax(){
        String priceString = driver.findElement(CheckoutLocators.subTotalPriceWithoutTax)
                                .getAttribute("innerText")
                                .replace("Item total: $", "")
                                .trim();
        return Double.parseDouble(priceString);
    }

    public Double getTaxPrice(){
        String priceString = driver.findElement(CheckoutLocators.taxAmount)
                                .getAttribute("innerText")
                                .replace("Tax: $", "")
                                .trim();
        return Double.parseDouble(priceString);
    }

    public Double getTotalPayableAmount(){
        String priceString = driver.findElement(CheckoutLocators.totalPayableAmount)
                                .getAttribute("innerText")
                                .replace("Total: $", "")
                                .trim();
        return Double.parseDouble(priceString);
    
    }

    public void clickOnFinishButton(){
        commonMethods.clickOnButton(CheckoutLocators.finishButton);
    }

    public String getCheckoutSuccessfullMessage(){
        return driver.findElement(CheckoutLocators.orderConfirmationMessage).getText();
    }

    public void clickOnBackHomeButton(){
        commonMethods.clickOnButton(CheckoutLocators.backHomeButton);
    }

    public void clickOnCancelButtonFromCheckoutOverview(){
        commonMethods.clickOnButton(CheckoutLocators.cancelButton);
    }

}
