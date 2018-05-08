package com.easy;

import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

//Enda Brody 07/05/2018
public class Test1 {
	public static void main(String args[]) {
//Open Chrome and navigate to Done Deal
		WebDriver driver = new ChromeDriver();
		System.out.println("Hello Google...");
		driver.get("https://www.donedeal.ie/");
//Select car and Motor tab
		WebElement carTab = driver.findElement(By.linkText("Cars & Motor"));
		carTab.click();
//Select the price Filter
		WebElement price = driver.findElement(By.xpath("(//button[@type='button'])[2]"));
		price.click();
//Wait 30 seconds to give ui time to load
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//Select 500 min price  from drop down list
		Select FromPrice = new Select(driver.findElement(By.xpath("//select[@ng-options='price.value as price.displayName for price in searchHero.priceRange']")));
		FromPrice.selectByVisibleText("€500");
//wait 10 seconds
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//Select 1000 max price list
		Select ToPrice = new Select(driver.findElement(By.xpath("//select[@ng-options='price.value as price.displayName for price in searchHero.priceRange | filter:searchHero.priceChoiceFilter']")));
		ToPrice.selectByVisibleText("€1,000");
//select the done button
		WebElement done = driver.findElement(By.xpath("//button[@type='button'][contains(text(),'Done')]"));
		done.click();
//Select search using the criteria given
		WebElement Findsearch = driver.findElement(By.xpath("//button[@type='submit']//span[@class='icon icon-white-glass']"));
		Findsearch.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//Select the Location Drop Down
		WebElement area = driver.findElement(By.xpath("//div[@class='nested-filter-container']//button[@type='button']"));
		area.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//Check the Clare box
		WebElement county = driver.findElement(By.cssSelector("#searchContent > div.search-column-1 > div.refine-filters-container.ng-pristine.ng-valid.ng-valid-maxlength > div > ul > li:nth-child(1) > ng-switch > div > div > div > ul > li:nth-child(10)"));
		county.click();
//Click the done button
		WebElement done2 = driver.findElement(By.xpath("//button[@type='button'][contains(text(),'Done')]"));
		done2.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//Set add to Highest Price first
		Select range = new Select(driver.findElement(By.xpath("//select[@id='sort']")));
		range.selectByVisibleText("High Price");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//Checks the top ad amount is €1000
		String topAdPrice = null;
		topAdPrice = driver.findElement(By.xpath("//a[@href='https://www.donedeal.ie/cars-for-sale/pricedrop-02-mk4-golf-comfortline-low-mileage/18230753']//div[contains(@class,'card')]//div[@class='card__body']//div[@class='card__body-2-of-2']")).getText();
		if (!topAdPrice.equals("€1,000"))
		{
			JOptionPane.showMessageDialog(null, "Not fliterd by price test failed" );
			
		}
		else 
			JOptionPane.showMessageDialog( null," Price of the top item " + topAdPrice);
		
		// Checks Filter is set to Clare
		String locationFilter = null;
		locationFilter = driver.findElement(By.xpath("//div[@class='nested-filter-container']")).getText();
		if (!locationFilter.equals("Clare"))
		{
			JOptionPane.showMessageDialog(null, "Not fliterd by county test Failed" );	
		}
		else 
			JOptionPane.showMessageDialog( null," The Location filter is set to " + locationFilter);		
		System.out.println("End of Test");
		driver.close();
		
	}
	
}


