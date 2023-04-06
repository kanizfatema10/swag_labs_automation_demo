package model;

import org.testng.annotations.DataProvider;

public class TestData {

    Credentials credentials = new Credentials();

    @DataProvider(name = "inputs")

    public Object[][] getData() {
    // return a two-dimensional object array with test data
    return new Object[][] { 
        {"",""},
        {"test","1234"},
        { "test", credentials.loginPasswordInputText }, 
        { credentials.loginUserNameInputText, "1234" }, 
        { "", credentials.loginPasswordInputText }, 
        { credentials.loginUserNameInputText, "" }, 
        { credentials.loginUserNameInputText, credentials.loginPasswordInputText} 
    };
}

    @DataProvider(name = "checkoutInfo")

    public Object[][] getCheckoutInfoInput() {
    // return a two-dimensional object array with test data
    return new Object[][] { 
        {"","",""},
        // {"","Jener","1577"},
        // { "Kylee", "","5655" }, 
        // { "Kylee", "Jener", "" }, 
        { "Kylee", "Jener", "8873" }, 
    };
}
    
}
