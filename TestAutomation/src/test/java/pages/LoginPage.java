package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {


    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By passwordError = By.xpath("//*[contains(text(), 'Epic sadface')]");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    public void enterUsername(String username) {
        clear(usernameField);
        sendKeys(usernameField, username);
    }

    public void enterPassword(String password) {
        clear(passwordField);
        sendKeys(passwordField, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public void clearLoginForm() {
        clear(usernameField);
        clear(passwordField);
    }

    public boolean isLoginSuccessful() {
        return wait.until(
                ExpectedConditions.urlContains("inventory.html")
        );
    }

    public String getErrorMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordError)).getText();
    }

    public boolean isLoginUnSuccessful() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordError)).isDisplayed();
    }
}