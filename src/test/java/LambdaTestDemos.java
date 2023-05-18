import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class LambdaTestDemos {
    public static WebDriver driver;
    @BeforeClass
    public void setup() {
        System.setProperty("web-driver.chrome.driver", "\"C:\\Users\\baciu\\Desktop\\chromedriver_win32\\chromedriver.exe\"");
        driver = new ChromeDriver();
    }

    @AfterClass
    public void teardown(){
        driver.quit();
    }

    @Test
    public void singleInputField() {
        driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");
        driver.manage().window().maximize();
        driver.findElement(By.id("user-message")).sendKeys("test");
        driver.findElement(By.id("showInput")).click();
        WebElement yourMessage = driver.findElement(By.id("message"));
        Assert.assertEquals(true, yourMessage.isDisplayed());
    }


    @Test
    public void singleInputFieldEmpty() {
        driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");
        driver.manage().window().maximize();
        driver.findElement(By.id("showInput")).click();
        WebElement yourMessage = driver.findElement(By.id("message"));
        Assert.assertEquals(false, yourMessage.isDisplayed());
    }

    @Test
    public void twoInputFieldValidInputs() {
        driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");
        driver.findElement(By.id("sum1")).sendKeys("12");
        driver.findElement(By.id("sum2")).sendKeys("12");
        WebElement getValuesOne = driver.findElement(By.xpath("//*[@id=\"gettotal\"]/button"));
        getValuesOne.click();
        WebElement messageDisplayed = driver.findElement(By.id("addmessage"));
        Assert.assertEquals(true, messageDisplayed.isDisplayed());
    }

    @Test
    public void twoInputFieldInvalidInput() {
        driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");
        driver.findElement(By.id("sum1")).sendKeys("Timi");
        driver.findElement(By.id("sum2")).sendKeys("Pop");
        WebElement getValuesOne = driver.findElement(By.xpath("//*[@id=\"gettotal\"]/button"));
        getValuesOne.click();
        WebElement ErrorMessageDisplayed = driver.findElement(By.id("addmessage"));
        Assert.assertTrue(ErrorMessageDisplayed.isDisplayed());
    }

    @Test
    public void singleCheckboxClickDemo() {
        driver.get("https://www.lambdatest.com/selenium-playground/checkbox-demo");
        WebElement checkbox = driver.findElement(By.id("isAgeSelected"));
        checkbox.click();
        WebElement successMessage = driver.findElement(By.id("txtAge"));
        Assert.assertTrue(successMessage.isDisplayed());
    }

    @Test
    public void singleCheckboxDemoTwo() {
        driver.get("https://www.lambdatest.com/selenium-playground/checkbox-demo");
        WebElement checkbox = driver.findElement(By.id("isAgeSelected"));
        WebElement successMessage = driver.findElement(By.id("txtAge"));
        Assert.assertFalse(successMessage.isDisplayed());
    }

    @Test
    public void multipleCheckboxDemoCheckAllAndUncheckAll() {
        driver.get("https://www.lambdatest.com/selenium-playground/checkbox-demo");
        WebElement checkAllButton = driver.findElement(By.id("box"));
        WebElement checkboxOne = driver.findElement(By.id("ex1-check1"));
        WebElement checkboxTwo = driver.findElement(By.id("ex1-check2"));
        WebElement checkboxThree = driver.findElement(By.id("ex1-check3"));
        WebElement checkboxFour = driver.findElement(By.xpath("//*[@id=\"ex1-check3\"]"));

        Assert.assertFalse(checkboxOne.isSelected());
        Assert.assertFalse(checkboxTwo.isSelected());
        Assert.assertFalse(checkboxThree.isSelected());
        Assert.assertFalse(checkboxFour.isSelected());

        checkAllButton.click();
        Assert.assertTrue(checkboxOne.isSelected());
        Assert.assertTrue(checkboxTwo.isSelected());
        Assert.assertTrue(checkboxThree.isSelected());
        Assert.assertTrue(checkboxFour.isSelected());

        checkAllButton.click();
        Assert.assertFalse(checkboxOne.isSelected());
        Assert.assertFalse(checkboxTwo.isSelected());
        Assert.assertFalse(checkboxThree.isSelected());
        Assert.assertFalse(checkboxFour.isSelected());
    }

   /*
    @Test
    public void clickOnTextOptionsToSelectChebox() {
        //this test will fail because the id for the fourth checkbox is the same id as the third's checkbox - bug on the lambda website
        driver.get("https://www.lambdatest.com/selenium-playground/checkbox-demo");
        WebElement optionOne = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/section[3]/div/div/div[2]/div[2]/div[2]/div/div[2]/div[1]/label"));
        optionOne.click();
        WebElement optionTwo = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/section[3]/div/div/div[2]/div[2]/div[2]/div/div[2]/div[2]/label"));
        optionTwo.click();
        WebElement optionThree = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/section[3]/div/div/div[2]/div[2]/div[2]/div/div[2]/div[3]/label"));
        optionThree.click();
        WebElement optionFour = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/section[3]/div/div/div[2]/div[2]/div[2]/div/div[2]/div[4]/label"));
        optionFour.click();

        WebElement checkboxOneEx2 = driver.findElement(By.id("ex1-check1"));
        WebElement checkboxTwoEx2 = driver.findElement(By.id("ex1-check2"));
        WebElement checkboxThreeEx2 = driver.findElement(By.id("ex1-check3"));
        WebElement checkboxFourEx2 = driver.findElement(By.id("ex1-check3"));

        Assert.assertTrue(checkboxOneEx2.isSelected());
        Assert.assertTrue(checkboxTwoEx2.isSelected());
        Assert.assertTrue(checkboxThreeEx2.isSelected());
        Assert.assertTrue(checkboxFourEx2.isSelected());
    }

    */

    @Test
    public void radioButtonMaleFemaleValueCheck() {
        driver.get("https://www.lambdatest.com/selenium-playground/radiobutton-demo");
        WebElement maleRadioButton = driver.findElement(By.name("optradio"));
        maleRadioButton.click();
        WebElement getCheckedValueButton = driver.findElement(By.id("buttoncheck"));
        getCheckedValueButton.click();
        WebElement messageDisplayed = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/section[3]/div/div/div[2]/div[1]/div[2]/p[2]"));
        Assert.assertTrue(messageDisplayed.isDisplayed());

        WebElement femaleText = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/section[3]/div/div/div[2]/div[1]/div[2]/label[2]"));
        femaleText.click();
        getCheckedValueButton.click();
        Assert.assertTrue(messageDisplayed.isDisplayed());
    }

    @Test
    public void clickGetCheckValueButtonWithoutGenderSelected(){
        driver.get("https://www.lambdatest.com/selenium-playground/radiobutton-demo");
        WebElement messageDisplayedEx2 = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/section[3]/div/div/div[2]/div[1]/div[2]/p[2]"));
        Assert.assertFalse(messageDisplayedEx2.isDisplayed());

        WebElement getCheckedValueButtonEx2 = driver.findElement(By.id("buttoncheck"));
        getCheckedValueButtonEx2.click();

        messageDisplayedEx2 = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/section[3]/div/div/div[2]/div[1]/div[2]/p[2]"));
        Assert.assertEquals("Radio button is Not checked", messageDisplayedEx2.getText());
    }

    @Test
    public void groupRadioButtonsDemos() {
        driver.get("https://www.lambdatest.com/selenium-playground/radiobutton-demo");
        WebElement genderTextDisplayed = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/section[3]/div/div/div[2]/div[2]/div[2]/div/div[2]/p[1]/span"));
        WebElement ageGroupTextDisplayed = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/section[3]/div/div/div[2]/div[2]/div[2]/div/div[2]/p[2]/span"));
        Assert.assertFalse(genderTextDisplayed.isDisplayed());
        Assert.assertFalse(ageGroupTextDisplayed.isDisplayed());

        WebElement maleRadioButton = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/section[3]/div/div/div[2]/div[2]/div[2]/div/div[1]/div[1]/label[1]/input"));
        maleRadioButton.click();
        WebElement ageGroupFiveToFifteen = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/section[3]/div/div/div[2]/div[2]/div[2]/div/div[1]/div[2]/label[2]/input"));
        ageGroupFiveToFifteen.click();
        WebElement getValuesButton = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/section[3]/div/div/div[2]/div[2]/div[2]/div/div[1]/button"));
        getValuesButton.click();

        Assert.assertTrue(genderTextDisplayed.isDisplayed());
        Assert.assertTrue(ageGroupTextDisplayed.isDisplayed());
    }
}