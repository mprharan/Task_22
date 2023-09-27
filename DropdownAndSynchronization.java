package task_22;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DropdownAndSynchronization {

	public static WebDriver driver = null;

	@Test
	public void task() throws InterruptedException, IOException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.navigate().to("https://phptravels.com/demo/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		String expected = " Thank you!";
		
		WebElement fname = driver.findElement(By.name("first_name"));
		fname.sendKeys("Hariharan");

		WebElement lname = driver.findElement(By.name("last_name"));
		lname.sendKeys("R");

		WebElement bname = driver.findElement(By.name("business_name"));
		bname.sendKeys("Donjon");

		WebElement mail = driver.findElement(By.name("email"));
		mail.sendKeys("test123@gmail.com");

		WebElement num1 = driver.findElement(By.id("numb1"));
		WebElement num2 = driver.findElement(By.id("numb2"));
		String one = num1.getText();
		String two = num2.getText();
		int sum1 = 0;
		for (int i = 0; i < one.length(); i++) {
			if (Character.isDigit(one.charAt(i)))
				sum1 = sum1 + Character.getNumericValue(one.charAt(i));
		}
		int sum2 = 0;
		for (int i = 0; i < two.length(); i++) {
			if (Character.isDigit(two.charAt(i)))
				sum2 = sum2 + Character.getNumericValue(two.charAt(i));
		}
		
		int sum = sum1 + sum2;
		
		String s=String.valueOf(sum);
		
		WebElement result = driver.findElement(By.id("number"));
		result.sendKeys(s);
		
		WebElement submit = driver.findElement(By.id("demo"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", submit);
		
		WebElement msg = driver.findElement(By.xpath("//div[@class='completed']/child::h2"));
		String actual = msg.getText();
		if(actual.equalsIgnoreCase(expected)) {
			System.out.println("Verified");
		}
		
		TakesScreenshot scs1 = (TakesScreenshot) driver;
		File ScreenshotAs = scs1.getScreenshotAs(OutputType.FILE);
		File Destination = new File("D:\\Selenium scs\\task.png");
		FileHandler.copy(ScreenshotAs, Destination);
		driver.close();
		
	}

}
