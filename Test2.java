package com.easy;

import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Test2 {
//Enda Brody 07/05/2018
//Purpose of this test script to run  Basic security checks	
	
//Open Chrome and navigate to Done Deal
	public static void main(String args[]) 
	{
	WebDriver driver = new ChromeDriver();
	System.out.println("Hello Google...");
	driver.get("https://www.donedeal.ie/");
// Sql Injection Attack
	WebElement Login = driver.findElement(By.xpath("//div[@class='header-actions-login_signup']//a[@ng-click='showLogin()']"));
	Login.click();
//Email
	WebElement Username = driver.findElement(By.xpath("//input[@id='email']"));
	Username.sendKeys("' or 1=1;--");
	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
//Password
	WebElement Password = driver.findElement(By.xpath("//input[@id='password']"));
	Password.sendKeys("' or 1=1;--");
	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
//Log in Button to submit user credentials 
	WebElement Loginsubmit = driver.findElement(By.xpath("//button[@id='loginButton']"));
	Loginsubmit.click();
	driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
// Different forms of Sql Injection
	Username.clear();
	Password.clear();
	Username.sendKeys("‘ or ‘abc‘=‘abc‘;– ");
	Password.sendKeys("‘ or ‘abc‘=‘abc‘;–");
	Loginsubmit.submit();
	driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
	Username.clear();
	Password.clear();
	Username.sendKeys("‘ or ‘ ‘=‘ ‘;–");
	Password.sendKeys("‘ or ‘ ‘=‘ ‘;–");
	Loginsubmit.submit();
	driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
	Username.clear();
	Password.clear();
	Username.sendKeys("\" or ‘ ‘=‘ ‘;–");
	Password.sendKeys("\" or ‘ ‘=‘ ‘;–");
	Loginsubmit.submit();
	
//SQL Injection through the URL
	driver.get("https://www.donedeal.ie/cars=1");
	driver.get("https://www.donedeal.ie/cars=' or 1=1;--");
	driver.get("https://www.donedeal.ie/cars=\" or ‘ ‘=‘ ‘;–");
	driver.get("https://www.donedeal.ie/cars=‘ or ‘abc‘=‘abc‘;–");
	driver.get("https://www.donedeal.ie/cars=‘ or ‘ ‘=‘ ‘;–");
	driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
//Cross Site Scripting Attack USING JAVA SCRIPT 
	driver.get("https://www.donedeal.ie/");
//Have to re find elements due to Stale element exception
	WebElement Login2 = driver.findElement(By.xpath("//div[@class='header-actions-login_signup']//a[@ng-click='showLogin()']"));
	Login2.click();
	WebElement Username2 = driver.findElement(By.xpath("//input[@id='email']"));
	Username2.sendKeys("<script>alert(document.cookie);</script>");
	WebElement Password2 = driver.findElement(By.xpath("//input[@id='password']"));
	Password2.sendKeys("<script>alert(document.cookie);</script>");
	WebElement Loginsubmit2 = driver.findElement(By.xpath("//button[@id='loginButton']"));
	Loginsubmit2.click();
	driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
	Username2.clear();
	Password2.clear();
	driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
//log in with valid user and xss script
	Username2.sendKeys("tempemail@aditus.info<script>alert(document.cookie);</script>");
	Password2.sendKeys("Password1<script>alert(document.cookie);</script>");
//Login with valid test user
	Username2.clear();
	Password2.clear();
	driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
	Username2.sendKeys("tempemail@aditus.info");
	Password2.sendKeys("Password1");
	Loginsubmit2.click();
	JOptionPane.showMessageDialog( null," Security test complete no issues ");
	System.out.println("End of Test");
	driver.close();
	}	
}
