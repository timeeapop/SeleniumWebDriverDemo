import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Keys;

public class FasttrackITSiteDemo {
    public static WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("web-driver.chrome.driver", "\"C:\\Users\\baciu\\Desktop\\chromedriver_win32\\chromedriver.exe\"");
        driver = new ChromeDriver();
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

    @Test
    public void accessFirstItem() {
        driver.get("https://fasttrackit-test.netlify.app/#/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[1]/div/div[1]/a")).click();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/product/1")); // checks if the user was redirected to the right link after tapping on the first item

        WebElement firstItemsTitleName = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div/div/div/h1/small"));
        WebElement firstItemsPicture = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div[1]/img"));
        WebElement firstItemsDescription = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div[2]/p"));
        WebElement firstItemsPriceEuros = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div[3]/p[1]"));
        WebElement addToCartButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div[3]/button[1]"));
        WebElement addToWishListButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/div[3]/button[2]"));
        Assert.assertEquals(true, firstItemsTitleName.isDisplayed());
        Assert.assertEquals(true, firstItemsPicture.isDisplayed());
        Assert.assertEquals(true, firstItemsDescription.isDisplayed());
        Assert.assertEquals(true, firstItemsPriceEuros.isDisplayed());
        Assert.assertEquals(true, addToCartButton.isDisplayed());
        Assert.assertEquals(true, addToWishListButton.isDisplayed());
    }

    @Test
    public void getBackToMainPageAfterAccessingFirstItemOnPage() {
        driver.get("https://fasttrackit-test.netlify.app/#/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[1]/div/div[1]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/nav/a")).click();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("https://fasttrackit-test.netlify.app/#/"));
    }

    @Test(priority = 1)
    public void refreshMainPage() {
        driver.get("https://fasttrackit-test.netlify.app/#/");
        driver.manage().window().maximize();

        String initialURL = driver.getCurrentUrl();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[3]/nav/div/div/div[2]/button")));
        button.click();

        String refreshURL = driver.getCurrentUrl();
        Assert.assertEquals(initialURL, refreshURL);
    }

    @Test
    public void checkEmptyCartAndReturnToMainPage() {
        driver.get("https://fasttrackit-test.netlify.app/#/");
        driver.manage().window().maximize();
        WebElement emptyCartIcon = driver.findElement(By.xpath("//*[@id=\"responsive-navbar-nav\"]/div[2]/span/a[1]"));
        emptyCartIcon.click();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/cart"));

        WebElement pageTitle = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div/div/div/h1/small"));
        Assert.assertEquals(true, pageTitle.isDisplayed());

        WebElement messageDisplayed = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]"));
        Assert.assertEquals("How about adding some products in your cart?", messageDisplayed.getText());

        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/nav/a")).click();
        String currentUrlAfterCheckingEmptyCart = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("https://fasttrackit-test.netlify.app/#/"));
    }

    @Test
    public void checkEmptyWishlistAndReturnToMainPage() {
        driver.get("https://fasttrackit-test.netlify.app/#/");
        driver.manage().window().maximize();
        WebElement emptyWishlistIcon = driver.findElement(By.xpath("//*[@id=\"responsive-navbar-nav\"]/div[2]/span/a[2]"));
        emptyWishlistIcon.click();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/wishlist"));

        WebElement pageTitle = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div/div/div/h1/small"));
        Assert.assertEquals(true, pageTitle.isDisplayed());

        WebElement emptyListNoItems = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]"));
        Assert.assertEquals("", emptyListNoItems.getText());

        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/nav/a")).click();
        String currentUrlAfterCheckingEmptyCart = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("https://fasttrackit-test.netlify.app/#/"));
    }

    @Test
    public void clickOnHelpButtonToCheckInfo() {
        driver.get("https://fasttrackit-test.netlify.app/#/");
        driver.manage().window().maximize();
        WebElement helpButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/nav/div/div/div[1]/button"));
        helpButton.click();

        WebElement modalDialog = driver.findElement(By.xpath("//div[contains(@class, 'modal-dialog')]"));
        Assert.assertTrue(modalDialog.isDisplayed());
        /*
        updated the XPath for the modal dialog using the contains function to target a div element that has a class containing "modal-dialog."
        This helps to identify the modal dialog element even if it doesn't have a unique ID or other identifiable attributes.
         */
        WebElement modalTitle = modalDialog.findElement(By.xpath(".//h3"));
        Assert.assertEquals("Valid usernames", modalTitle.getText());
        /*
        Then, the modalTitle element is located within the modalDialog element using the relative XPath ".//h3".
        This locates the h3 element inside the modal dialog.
         */
    }
    @Test
    public void accessLogInModal() {
        driver.get("https://fasttrackit-test.netlify.app/#/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[@id=\"responsive-navbar-nav\"]/div[2]/span/span/span/button")).click();

        WebElement modalLogInDialog = driver.findElement(By.xpath("//div[contains(@class, 'modal-dialog')]"));
        WebElement modalTitleFromLogInDialog = driver.findElement(By.xpath("//div[contains(@class, 'modal-title h4')]"));
        Assert.assertEquals("Login", modalTitleFromLogInDialog.getText());
    }

    @Test (priority = 2)
    public void logInWithValidUsernameAndValidPassword() {
        driver.get("https://fasttrackit-test.netlify.app/#/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[@id=\"responsive-navbar-nav\"]/div[2]/span/span/span/button")).click();

        WebElement modalLogInDialog = driver.findElement(By.xpath("//div[contains(@class, 'modal-dialog')]"));
        WebElement modalTitleFromLogInDialog = driver.findElement(By.xpath("//div[contains(@class, 'modal-title h4')]"));
        Assert.assertEquals("Login", modalTitleFromLogInDialog.getText());

        WebElement usernameInputField = driver.findElement(By.id("user-name"));
        usernameInputField.sendKeys("dino");
        WebElement passwordInputField = driver.findElement(By.id("password"));
        passwordInputField.sendKeys("choochoo");
        WebElement loginButton = modalLogInDialog.findElement(By.className("btn-primary"));
        loginButton.click();
        WebElement saluteMessageDisplayed = driver.findElement(By.xpath("//*[@id=\"responsive-navbar-nav\"]/div[2]/span/span/span/a"));
        Assert.assertEquals("dino", saluteMessageDisplayed.getText());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-link")));
        logoutButton.click();
    }

    @Test (priority = 3)
    public void logInWithValidUsernameAndValidPasswordEnterKeyboardSimulation() {
        driver.get("https://fasttrackit-test.netlify.app/#/");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"responsive-navbar-nav\"]/div[2]/span/span/span/button")));
        loginButton.click();

        WebElement modalLogInDialog = driver.findElement(By.xpath("//div[contains(@class, 'modal-dialog')]"));
        WebElement modalTitleFromLogInDialog = driver.findElement(By.xpath("//div[contains(@class, 'modal-title h4')]"));
        Assert.assertEquals("Login", modalTitleFromLogInDialog.getText());

        WebElement usernameInputField = driver.findElement(By.id("user-name"));
        usernameInputField.sendKeys("dino");

        WebElement passwordInputField = driver.findElement(By.id("password"));
        passwordInputField.sendKeys("choochoo");

        passwordInputField.sendKeys(Keys.ENTER);

        WebElement saluteMessageDisplayed = driver.findElement(By.xpath("//*[@id=\"responsive-navbar-nav\"]/div[2]/span/span/span/a"));
        Assert.assertEquals("dino", saluteMessageDisplayed.getText());
    }
}
