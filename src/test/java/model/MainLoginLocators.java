package model;

import org.openqa.selenium.By;

public class MainLoginLocators {
    public static By loginUserNameField = By.id("user-name");
    public static By loginPasswordField = By.id("password");
    public static By loginButtonField = By.id("login-button");
    public static By errorMessageForWrongCredential = By.xpath("//h3[contains(text(),'Epic sadface: Username and password do not match any user in this service')]");
    public static By errorMessageForEmptyUserName = By.xpath("//h3[contains(text(),'Epic sadface: Username is required')]");
    public static By errorMessageForEmptyPassword = By.xpath("//h3[contains(text(),'Epic sadface: Password is required')]");

}
