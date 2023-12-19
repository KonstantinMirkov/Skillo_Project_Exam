package Exam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4200/users/login";
    private final WebDriver driver;
    @FindBy(id = "sign-in-button")
    private WebElement signInButton;
    @FindBy(id = "defaultLoginFormUsername")
    private WebElement usernameField;
    @FindBy(id = "defaultLoginFormPassword")
    private WebElement passwordField;
    @FindBy(className = "h4")
    private WebElement signInFormTitle;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        populateUsername(username);
        populatePassword(password);
        clickSignIn();
    }

    public void navigateTo() {
        this.driver.get(LoginPage.PAGE_URL);
    }

    public void clickSignIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
    }

    public void populatePassword(String password) {
        passwordField.sendKeys(password);
    }

    public void populateUsername(String username) {
        usernameField.sendKeys(username);
    }

    public String getSignInElementText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(signInFormTitle));
        return signInFormTitle.getText();
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlToBe(LoginPage.PAGE_URL));
    }

    public boolean isUserNotFoundDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Wait until the error message is visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-label=\"User not found\"]")));
            System.out.println("Error message for User not found is displayed");
            return true;
        } catch (Exception e) {
            System.out.println("Error message for User not found is not displayed");
            return false;
        }
    }

    public boolean isInvalidUsernameDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Wait until the error message is visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-label=\"UsernameOrEmail cannot be empty\"]")));
            System.out.println("Error message for invalid username is displayed");
            return true;
        } catch (Exception e) {
            System.out.println("Error message for invalid username is not displayed");
            return false;
        }
    }

    public boolean isInvalidPasswordDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Wait until the error message is visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-label=\"Ivalid password\"]")));
            System.out.println("Error message for invalid password is displayed");
            return true;
        } catch (Exception e) {
            System.out.println("Error message for invalid password is not displayed");
            return false;
        }
    }
}
