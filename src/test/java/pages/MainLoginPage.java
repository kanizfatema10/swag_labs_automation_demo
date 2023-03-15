package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import base.Base;
import model.CommonMethods;
import model.Credentials;
import model.MainLoginLocators;

public class MainLoginPage extends Base {
    
    RemoteWebDriver driver;
    CommonMethods commonMethods;
    Credentials credentials = new Credentials();
    

    public MainLoginPage(RemoteWebDriver driver){
        this.driver = driver;
        commonMethods = new CommonMethods(this.driver);
    }

    public void openHomePage(){
        driver.get(credentials.baseUrl);
    }

    public void setEmail(String loginUserName){
        commonMethods.setText(MainLoginLocators.loginUserNameField, loginUserName);
    }

    public void setPassword(String loginPassword){
        commonMethods.setText(MainLoginLocators.loginPasswordField, loginPassword);
    }

    public void clickOnLogin(){
        commonMethods.clickOnButton(MainLoginLocators.loginButtonField);
    }

}