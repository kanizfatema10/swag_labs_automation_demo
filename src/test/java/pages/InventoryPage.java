package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import base.Base;
import model.CommonMethods;
import model.InventoryLocators;

public class InventoryPage extends Base{
    RemoteWebDriver driver;
    CommonMethods commonMethods;

    public InventoryPage(RemoteWebDriver driver){
        this.driver = driver;
        // if(driver== null){
        //     System.out.println("faltu");
        // }
        commonMethods = new CommonMethods(this.driver);
    }
    
    public void clickOnAboutMenu(){
        commonMethods.clickOnButton(InventoryLocators.aboutMenuText);
    }

    public String getAboutPageURL(){
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
}
