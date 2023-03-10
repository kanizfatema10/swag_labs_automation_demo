package tests;

import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;


public class LoginTest{

    
    @Test()
    public void loginTest(){

        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");
    }
}


