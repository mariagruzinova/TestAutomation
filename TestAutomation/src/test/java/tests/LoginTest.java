package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void setUpLogin(){
        loginPage.open();
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][] {
                {"standard_user", "wrong_password"},
                {"wrong_user", "secret_sauce"}
        };
    }

    @Test
    public void validLoginTest() {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(
                loginPage.isLoginSuccessful(),
                "Login was not successful"
        );
    }

    @Test(dataProvider = "invalidLoginData")
    public void incorrectPassword(String username, String password) {
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.isLoginUnSuccessful(), "Error message should be displayed");
        Assert.assertEquals(loginPage.getErrorMessage(),"Epic sadface: Username and password do not match any user in this service", "Incorrect error message");
    }
}
