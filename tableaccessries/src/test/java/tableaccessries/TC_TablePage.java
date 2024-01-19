package tableaccessries;

import org.testng.annotations.Test;

import dev.failsafe.Timeout;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TC_TablePage {
	WebDriverWait wait;
	@Test
	public void tablethings_Test() throws InterruptedException {
		WebDriverManager.chromedriver().setup();   //Setup New Chrome
		WebDriver driver = new ChromeDriver();     //driver
		driver.manage().window().maximize();       //Maximize Chrome Window
		
		driver.get("https://www.webstaurantstore.com/");//open link
		//Search and open Stainless Work Table
		driver.findElement(By.id("searchval")).sendKeys("stainless work table");
		driver.findElement(By.xpath("//*[@id=\"awesomplete_list_1\"]/li[2]/span[1]")).click();
		//Get List Element
		driver.findElements(By.xpath("//div[@id=\"product_listing\"]/div/div/a/span"));
		List<WebElement> elements = driver.findElements(By.xpath("//div[@class='exampleClass']"));
        // Loop through the list of elements
        for (WebElement element : elements) {
            // Get the text of each element
            String elementText = element.getText();
            //Check that the keyword table is present
            assert elementText.contains("Table") : "Assertion failed: The text should contain 'table'";
            // Print the text to the console
            System.out.println("Element Text: " + elementText);
        }
        List<WebElement> itemList = driver.findElements(By.xpath("//*[@id=\"ProductBoxContainer\"]/div[4]/form/div/div/input[2]"));
        // Check if there are items in the list
        if (!itemList.isEmpty()) {
            // Click on the last item
            itemList.get(itemList.size() - 1).click();
        } else {
            // Log a message or take appropriate action if the list is empty
            System.out.println("The list is empty.");
        }

        // Click on view cart
        driver.findElement(By.xpath("//*[@id=\"watnotif-wrapper\"]/div/p/div[2]/div[2]/a[1]")).click();
        WebElement cartelement = driver.findElement(By.xpath("//*[@class = 'quantityInput input-mini']"));
       // Verify the element in the cart
        String cartelementText = cartelement.getAttribute("value");
        System.out.println("Element Text: " + cartelementText);
        assert cartelementText.equals("1") : "Cart value is not equal to 1";
        //Make Cart empty
        driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div/div[1]/div/button")).click();
        driver.findElement(By.xpath("//*[@id=\"td\"]/div[11]/div/div/div/footer/button[1]")).click();
        //Verify cart is empty or not 
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Your cart is empty.')]")));
		WebElement yourcartempty = driver.findElement(By.xpath("//*[contains(text(), 'Your cart is empty.')]"));
	    String yourCartEmptyText = yourcartempty.getText();
	    assert yourCartEmptyText.contains("Your") : "Your Cart is not Empty";
        
        // Close the browser
        driver.quit();
	}

}
