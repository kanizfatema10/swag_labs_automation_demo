package model;

import org.testng.annotations.DataProvider;

public class TestData {

    Credentials credentials = new Credentials();

    @DataProvider(name = "inputs")

    public Object[][] getData() {
    // return a two-dimensional object array with test data
    return new Object[][] { 
        { "test", credentials.loginPasswordInputText }, 
        { credentials.loginUserNameInputText, "1234" }, 
        { credentials.loginUserNameInputText, credentials.loginPasswordInputText} 
    };
}

    
}
