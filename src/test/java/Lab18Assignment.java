import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Lab18Assignment {
    public String baseUrl= "http://www.google.com/";
    public WebDriver driver = new ChromeDriver();
    @Test
    public void clickOnImageSection() {
        //navigati pe google, faceti o cautare dupa orice doriti si dati click pe sectiunea de imagini

        driver.get(baseUrl);
        driver.manage().window().maximize();
        //Accept cookies
        WebElement cookies = driver.findElement(By.id("L2AGLb"));
        cookies.click();
        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));
        // Enter something to search for
        element.sendKeys("Selenium");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();
        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());
        WebElement images= driver.findElement(By.xpath("//*[@id=\"hdtb-msb\"]/div[1]/div/div[2]/a"));
        images.click();
        driver.quit();
    }

    @Test
    public void clickOnFirstResult(){
        //navigati pe google, faceti o cautare dupa orice doriti si dati click pe primul rezultat
        driver.get(baseUrl);
        driver.manage().window().maximize();
        WebElement cookies = driver.findElement(By.id("L2AGLb"));
        cookies.click();
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Selenium for dummies");
        element.submit();
        System.out.println("Page title is: " + driver.getTitle());
        WebElement firstLink = driver.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div/div/div/div[1]/div/a/h3"));
        firstLink.click();
        driver.quit();
    }

    @Test
    public void removeSearch(){
        //navigati pe google, faceti o cautare dupa orice doriti si stergeti ce ati cautat folosind butonul x din bara de search
        driver.get(baseUrl);
        driver.manage().window().maximize();
        WebElement cookies = driver.findElement(By.id("L2AGLb"));
        cookies.click();
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Main coon cat");
        element.submit();
        System.out.println("Page title is: " + driver.getTitle());
        WebElement closeButton = driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[1]/div[1]/div[2]/div/div[3]/div[1]/div"));
        closeButton.click();
        driver.quit();
    }
}

