package model;

import org.openqa.selenium.By;

public class MainLoginLocators {
    public static By loginUserNameField = By.id("user-name");
    public static By loginPasswordField = By.id("password");
    public static By loginButtonField = By.id("login-button");
    public static By errorMessageForInvalidCredential = By.cssSelector("[data-test='error']");

}
