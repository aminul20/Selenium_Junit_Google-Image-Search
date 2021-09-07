import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class TestSelenium_Google_Image_Search {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setup(){
        System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
        FirefoxOptions ops=new FirefoxOptions();
//        ops.addArguments("--headless");//run test without opening browser
        ops.addArguments("--headed");
        driver=new FirefoxDriver(ops);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void searchImageOnGoogle(){
        driver.get("https://www.google.com/ncr");
        driver.findElement(By.xpath("//a[contains(text(),'Images')]")).click();
        driver.findElement(By.xpath("//div[@class='ZaFQO']")).click();
//        driver.findElement(By.xpath("//input[@id='Ycyxxc']")).sendKeys("image_url_here"); //if search by image link
        driver.findElement(By.xpath("//a[contains(text(),'Upload an image')]")).click();

        File file = new File("./src/test/resources/qa_image.jpg");
//        driver.findElement(By.cssSelector("#awyMjb")).sendKeys(file.getAbsolutePath());
        driver.findElement(By.name("encoded_image")).sendKeys(file.getAbsolutePath());
//        System.out.println(file.getAbsolutePath()); //show absolute path of file in console

        Boolean status = driver.findElement(By.xpath("//img[@class='GMzDwb']")).isDisplayed();
        Assert.assertEquals(status,true);
    }

    @After
    public void finishTest(){
//        driver.close();
    }
}

