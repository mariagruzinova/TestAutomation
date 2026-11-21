package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;
import utils.DriverFactory;
import utils.DriverManager;

public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.createDriver("chrome");
        DriverManager.setDriver(driver);
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}