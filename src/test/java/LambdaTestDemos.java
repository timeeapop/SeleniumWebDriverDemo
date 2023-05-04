import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LambdaTestDemos {
    public static WebDriver driver;
    @BeforeClass
    public void setup(){
        System.setProperty("web-driver.chrome.driver", "\"C:\\Users\\baciu\\Desktop\\chromedriver_win32\\chromedriver.exe\"");
        driver = new ChromeDriver();
    }

    /*@AfterClass
    public void teardown(){
        driver.quit();
    }
     */

    @Test
    public void singleInputField (){
        driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");
        driver.manage().window().maximize();
        driver.findElement(By.id("user-message")).sendKeys("test");
        driver.findElement(By.id("showInput")).click();
        WebElement yourMessage = driver.findElement(By.id("message"));
        Assert.assertEquals(true, yourMessage.isDisplayed());
    }


    @Test
    public void singleInputFieldEmpty (){
        driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");
        driver.manage().window().maximize();
        driver.findElement(By.id("showInput")).click();
        WebElement yourMessage = driver.findElement(By.id("message"));
        Assert.assertEquals(false, yourMessage.isDisplayed());
    }

    @Test
    public void twoInputFieldValidInputs (){
        driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");
        driver.findElement(By.id("sum1")).sendKeys("12");
        driver.findElement(By.id("sum2")).sendKeys("12");
        WebElement getValuesOne = driver.findElement(By.xpath("//*[@id=\"gettotal\"]/button"));
        getValuesOne.click();
        WebElement messageDisplayed = driver.findElement(By.id("addmessage"));
        Assert.assertEquals(true, messageDisplayed.isDisplayed());
    }

    @Test
    public void twoInputFieldInvalidInputs (){
        driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");
        driver.findElement(By.id("sum1")).sendKeys("Timi");
        driver.findElement(By.id("sum2")).sendKeys("Pop");
        WebElement getValuesOne = driver.findElement(By.xpath("//*[@id=\"gettotal\"]/button"));
        getValuesOne.click();
        WebElement ErrorMessageDisplayed = driver.findElement(By.id("addmessage"));
        Assert.assertTrue(ErrorMessageDisplayed.isDisplayed());
    }

    @Test
    public void singleCheckboxClickDemo () {
        driver.get("https://www.lambdatest.com/selenium-playground/checkbox-demo");
        WebElement checkbox = driver.findElement(By.id("isAgeSelected"));
        checkbox.click();
        WebElement successMessage = driver.findElement(By.id("txtAge"));
        Assert.assertTrue(successMessage.isDisplayed());
    }



}