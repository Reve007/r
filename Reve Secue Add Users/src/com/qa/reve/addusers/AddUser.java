package com.qa.reve.addusers;
import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class AddUser 
{
	WebDriver driver;
	String Email;
	String Phone;
	
By User = By.xpath(".//*[@id='users']/a");
By AddUser = By.xpath(".//*[@id='contentWindow']/div/div/button"); 
By email = By.xpath(".//*[@id='emailID']");
By phone = By.xpath(".//*[@id='contactNumber']");
By Country = By.xpath(".//*[@id='countryCodes']");

 public AddUser (WebDriver driver)
 {
	this.driver = driver;
   }
 
 public void clickusers() throws InterruptedException
 {  
	WebElement element = driver.findElement(User);
    JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", element);
	executor.executeScript("alert('User clicked');");
	Thread.sleep(2000);
	driver.switchTo().alert().accept();
  }
 
 public void clickaddUser() throws InterruptedException
 {
	 WebElement element = driver.findElement(AddUser);
     JavascriptExecutor js = (JavascriptExecutor)driver;
	 js.executeScript("arguments[0].click();", element);
	 js.executeScript("alert('Add user clicked');");
	 Thread.sleep(2000);
	 driver.switchTo().alert().accept();
	 Thread.sleep(5000);
 }
 
 public void enterEmailid() throws BiffException, IOException, InterruptedException 
 {   
	    FileInputStream fis = new FileInputStream("email.xls");
	    Workbook wb = Workbook.getWorkbook(fis);
	    Sheet sh= wb.getSheet(0);
	    int rows=sh.getRows();
	    System.out.println("no.of row:" + rows);
	    
	 try
	 {
	    for(int i=1; i<rows; i++)
	     {
	         Email=sh.getCell(0,i).getContents();
	         Phone=sh.getCell(1,i).getContents();
	        
	        Thread.sleep(5000);
	        WebElement element1 = driver.findElement(email);
	        WebDriverWait wait = new WebDriverWait(driver,30);
	        wait.until(ExpectedConditions.elementToBeClickable(element1)).sendKeys(Email);
	        Thread.sleep(5000);
	        
	        Select sel = new Select(driver.findElement(Country));
	  	    sel.selectByVisibleText("India");
	  	    Thread.sleep(5000);
	        

	        WebElement element2 = driver.findElement(phone);
	        WebDriverWait wait1 = new WebDriverWait(driver, 30);
	        wait1.until(ExpectedConditions.elementToBeClickable(element2)).sendKeys(Phone);
	       // driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Phone);
	        Thread.sleep(5000);
	        
	        driver.findElement(By.xpath(".//*[@id='addingU']/div[3]/button[2]")).click();
	        Thread.sleep(6000);
	        
	     }   
	 }
	    
	 catch(Exception e)
	   {
	     
	  }
  }
	public void base() throws InterruptedException, BiffException, IOException
	{
		clickusers();
		clickaddUser();
		enterEmailid();
	}
}
