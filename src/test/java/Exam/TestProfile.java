package Exam;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class TestProfile extends TestBase {
    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {
        return new Object[][]{
                {"imeil@abv.bg", "Parola12345"}
        };
    }

    @DataProvider(name = "getUsersForPosting")
    public Object[][] getUsersForPosting() {
        File postPicture = new File("src\\main\\resources\\upload\\image_upload.jpg");
        String caption = "Custom caption for posting image";

        return new Object[][]{
                {"imeil@abv.bg", "Parola12345", "ime12", postPicture, caption}
        };
    }

    @Test(dataProvider = "getUsers")
    public void testNumberOfPostsCounter(String username, String password) {
        WebDriver driver = super.getDriver();

        // Login to the application
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.login(username, password);

        //Click profile
        Header header = new Header(driver);
        header.clickProfile();

        ProfilePage profilePage = new ProfilePage(driver);

        // Validate profile page URL
        profilePage.isUrlLoaded();

        // Validate Number Of Posts is visible
        profilePage.isPostCounterVisible();
        // Print number of posts
        System.out.println("Number of posts: " + profilePage.getPostsCount());
    }

    @Test(dataProvider = "getUsers")
    public void testNumberOfFollowersCounter(String username, String password) {
        WebDriver driver = super.getDriver();

        // Login to the application
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.login(username, password);

        //Click profile
        Header header = new Header(driver);
        header.clickProfile();

        ProfilePage profilePage = new ProfilePage(driver);

        // Validate profile page URL
        profilePage.isUrlLoaded();

        // Validate Number Of Posts is visible
        profilePage.isFollowersCounterVisible();

        // Print number of followers
        System.out.println("Number of followers: " + profilePage.getFollowersCount());
    }

    @Test(dataProvider = "getUsers")
    public void testNumberOfFollowingCounter(String username, String password) {
        WebDriver driver = super.getDriver();

        // Login to the application
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.login(username, password);

        //Click profile
        Header header = new Header(driver);
        header.clickProfile();

        ProfilePage profilePage = new ProfilePage(driver);

        // Validate profile page URL
        profilePage.isUrlLoaded();

        // Validate Number Of Posts is visible
        profilePage.isFollowingCounterVisible();

        // Print number of following
        System.out.println("Number of following: " + profilePage.getFollowingCount());
    }

    @Test(dataProvider = "getUsersForPosting")
    public void testCreatePostFromProfile(String user, String password, String username, File file, String caption) {
        WebDriver driver = super.getDriver();

        // Login to the application
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.login(user, password);

        // Click profile
        Header header = new Header(driver);
        header.clickProfile();

        ProfilePage profilePage = new ProfilePage(driver);

        // Validate profile url is correct
        Assert.assertTrue(profilePage.isUrlLoaded(), "The Profile URL is not correct!");

        // Click All and click New post button
        profilePage.clickAllButton();
        profilePage.clickNewPostButton();

        // Validate post url is loaded
        PostPage postPage = new PostPage(driver);
        Assert.assertTrue(postPage.isUrlLoaded(), "The POST URL is not correct!");

        // Upload image
        postPage.uploadPicture(file);
        Assert.assertTrue(postPage.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(file.getName(), postPage.getImageName(), "The image name is incorrect!");

        // Populate post caption
        postPage.populatePostCaption(caption);

        // Click create post
        postPage.clickCreatePost();

        // Validate profile url is correct and the number of posts is correct
        Assert.assertTrue(profilePage.isUrlLoaded(), "The Profile URL is not correct!");
        profilePage.clickPost(0);

        // Validate image is visible, the username is the same
        PostModal postModal = new PostModal(driver);
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(postModal.getPostUser(), username);
    }
}
