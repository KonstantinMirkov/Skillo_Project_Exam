package Exam;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class TestNewPost extends TestBase {
    @DataProvider(name = "getUsersForPosting")
    public Object[][] getUsersForPosting() {
        File postPicture = new File("src\\main\\resources\\upload\\image_upload.jpg");
        String caption = "Custom caption for posting image";

        return new Object[][]{
                {"imeil@abv.bg", "Parola12345", "ime12", postPicture, caption}
        };
    }

    @Test(dataProvider = "getUsersForPosting")
    public void testCreatePost(String user, String password, String username, File file, String caption) {
        WebDriver driver = super.getDriver();

        // Login to the application
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo();
        loginPage.login(user, password);

        // Click New post
        Header header = new Header(driver);
        header.clickNewPost();

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
        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded(), "The Profile URL is not correct!");
        Assert.assertEquals(profilePage.getPostCount(), 1, "The number of Posts is incorrect!");
        profilePage.clickPost(0);

        // Validate image is visible, the caption is the same and the username is the same
        PostModal postModal = new PostModal(driver);
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(postModal.getPostTitle(), caption);
        Assert.assertEquals(postModal.getPostUser(), username);
    }
}
