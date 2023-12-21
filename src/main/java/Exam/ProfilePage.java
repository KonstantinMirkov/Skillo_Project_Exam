package Exam;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class ProfilePage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4200/users/";
    @FindBy(className = "profile-stat-count")
    private WebElement profilePostsCounter;
    @FindBy(id = "followers")
    private WebElement profileFollowersCounter;
    @FindBy(id = "following")
    private WebElement profileFollowingCounter;
    @FindBy(css = "label.btn-all")
    private WebElement allButton;
    @FindBy(css = "i.far")
    private WebElement newPostButton;
    @FindBy(tagName = "app-post")
    private List<WebElement> posts;

    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.urlContains(ProfilePage.PAGE_URL));
    }

    public boolean isPostCounterVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(profilePostsCounter)).isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isFollowersCounterVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(profileFollowersCounter)).isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isFollowingCounterVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(profileFollowingCounter)).isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getPostsCount() {
        return profilePostsCounter.getText();
    }

    public String getFollowersCount() {
        return profileFollowersCounter.getText();
    }

    public String getFollowingCount() {
        return profileFollowingCounter.getText();
    }

    public int getPostCount() {
        return posts.size();
    }

    public void clickPost(int postIndex) {
        posts.get(postIndex).click();
    }

    public void clickAllButton() {
        allButton.click();
    }

    public void clickNewPostButton() {
        newPostButton.click();
    }
}
