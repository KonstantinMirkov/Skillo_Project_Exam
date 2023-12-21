package Exam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class PostModal {
    private final WebDriver driver;
    private final WebElement modalElement;
    @FindBy(className = "post-title")
    private WebElement postTitle;
    @FindBy(className = "post-user")
    private WebElement postUser;

    public PostModal(WebDriver driver) {
        this.driver = driver;
        this.modalElement = driver.findElement(By.className("post-modal"));
        PageFactory.initElements(driver, this);
    }

    public boolean isImageVisible() {
        try {
            WebElement image = modalElement.findElement(By.cssSelector(".post-modal-img img"));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(image)).isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getPostTitle() {
        return postTitle.getText();
    }

    public String getPostUser() {
        return postUser.getText();
    }
}
