import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IfElseTest {
    @Test
    public void testPageTitle() {
        System.out.println("Launching Chrome Browser..");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://google.com");

        //Verifying the page title
        String expPageTtitle = "Google";
        boolean flag = false;
        if(driver.getTitle().equalsIgnoreCase(expPageTtitle)){//ignoreCase se refera ca ignora daca ii scris cu litera mare sau cu litera mica
            flag = true;
            //This method will return TRUE when the page title matches with specified string
            System.out.println("Page title matched");
        }
        Assert.assertTrue(flag, "Page title is not matching " + "with expected");
        driver.quit();
    }
}
