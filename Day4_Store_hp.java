package seleniumPractiseSessions;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day4_Store_hp {

	public static void main(String[] args) throws InterruptedException {
		
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
		
		//1.Goto url
		driver.get(" https://store.hp.com/in-en/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
		
		/*try {
			driver.findElementByXPath("//span[@class='optly-modal-close close-icon']").click();
		}
		catch (NoSuchElementException exception) {
			System.out.println("Exception1 is catched");
		}*/
		
		/*boolean cookies=driver.findElementByXPath("//button[text()='Accept Cookies']").isDisplayed();
		if(cookies==true)
		{ driver.findElementByXPath("//button[text()='Accept Cookies']").click();}
		Thread.sleep(3000);*/
		
		//2.Mouse over on Laptops menu and click on Pavilion
		//driver.findElementByXPath("//span[@class='optly-modal-close close-icon']").click();
		WebElement laptop = driver.findElementByXPath("(//span[text()='Laptops'])[1]");
		Actions laptops=new Actions(driver);
		laptops.moveToElement(laptop).perform();
		
	    String mainWinHandle = driver.getWindowHandle();     // main window
		String subWinHandle = null;

		Set<String> allHandle = driver.getWindowHandles(); 
		Iterator<String> iterator = allHandle.iterator();
		while (iterator.hasNext()){
			subWinHandle = iterator.next();
		}
		driver.switchTo().window(subWinHandle); // switch to popup 

		//Write code to close Ad or skip                                            

		driver.switchTo().window(mainWinHandle);
		
		Thread.sleep(3000);
		try {
      driver.findElementByXPath("//button[@class='optanon-allow-all accept-cookies-button']").click();
		}
		catch (NoSuchElementException exception) {
			System.out.println("Exception2 is catched");
		}
        driver.findElementByXPath("//span[@class='optly-modal-close close-icon']").click();
        Thread.sleep(2000);
		driver.findElementByXPath("(//span[text()='Pavilion'])[1]").click();
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("scroll(0, 500)");
		
		//3.Under SHOPPING OPTIONS -->Processor -->Select Intel Core i7
		driver.findElementByXPath("(//span[text()='Processor'])[2]").click();
		driver.findElementByXPath("(//li[@class='item'])[6]").click();
		try {
			driver.findElementByXPath("//button[text()='Accept Cookies']").click();
		}
		catch (NoSuchElementException exception) {
			System.out.println("Exception2 is catched");
		}
		
		//4.Hard Drive Capacity -->More than 1TB
		JavascriptExecutor executor1=(JavascriptExecutor)driver;
		executor1.executeScript("scroll(0, 300)");
		Thread.sleep(2000);
		/*try {
			driver.findElementByXPath("//button[text()='Accept Cookies']").click();
		}
		catch (NoSuchElementException exception) {
			System.out.println("Exception2 is catched");
		}*/
		Thread.sleep(3000);
		driver.findElementByXPath("(//li[@class='item'])[17]").click();
		
		//5.Select Sort By: Price: Low to High
		WebElement sortcombo = driver.findElementByXPath("(//select[@class='sorter-options option-new'])[1]");
		Select sortcomboprice = new Select(sortcombo);
		sortcomboprice.selectByValue("price_asc");
		Thread.sleep(3000);

		//6.Print the First resulting Product Name and Price
		List<WebElement> firstlap = driver.findElementsByXPath("(//img[@class='product-image-photo'])[1]");
		WebElement lapname = firstlap.get(0);
		System.out.println(lapname);
		String price = driver.findElementByXPath("//span[@id='product-price-9580']").getText();
		String prc=price.replaceAll("\\D", "");
		int price1 = Integer.parseInt(prc);
		System.out.println(price1);

		//7.Click on Add to Cart
		Thread.sleep(3000);
		driver.findElementByXPath("(//span[text()='Add To Cart'])[1]").click();


		//8.Click on Shopping Cart icon --> Click on View and Edit Cart
		Thread.sleep(3000);
		driver.findElementByXPath("//a[@class='action showcart']").click();
		driver.findElementByXPath("//a[@class='action primary viewcart']").click();


		//9.Check the Shipping Option --> Check availability at Pincode
		driver.findElementById("standard_delivery").click();
		driver.findElementByXPath("//input[@placeholder='Pincode']").sendKeys("600097");
		driver.findElementByXPath("//button[text()='check']").click();
		Thread.sleep(5000);
				

		//10.Verify the order Total against the product price
		String ordervalue = driver.findElementByXPath("//tr[@class='grand totals']").getText();
		String ordval =ordervalue.replaceAll("\\D", "");
		int price2 = Integer.parseInt(ordval);

			if (price1==price2) {
					System.out.println("Order Total and Product  Price Matched");
					driver.findElementByXPath("(//span[text()='Proceed to Checkout'])[1]").click();
				}
			else
					System.out.println("Order Total and Product  Price Matched are not matchd");


		//11.Proceed to Checkout if Order Total and Product Price matches
		driver.findElementByXPath("//span[text()='Proceed to Checkout']").click();
				
				
	    //12. Click on Place Order
	    driver.findElementByXPath("(//span[text()='Place Order'])[3]").click();
				
				
		 //13. Capture the Error message and Print
		 String errormsg = driver.findElementByXPath("//div[@class='message notice']").getText();
		 System.out.println(errormsg);
				
		 //14.Close Browser
				driver.close();

			
		
		

	}

}
