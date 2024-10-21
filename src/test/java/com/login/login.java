package com.login;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

public class login {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();

		driver.get("http://192.168.1.3:8080/register/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		driver.findElement(By.id("username")).sendKeys("manivannan");
		driver.findElement(By.id("email")).sendKeys("manimailupuli3696@gmail.com");
		driver.findElement(By.id("password")).sendKeys("ManiV@3696");
		driver.findElement(By.id("confirmPassword")).sendKeys("ManiV@3696");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		WebElement element = driver.findElement(By.xpath("//h2[@text()='Registration Successful']"));
		String text = element.getText();
		System.out.println(text);
		Assert.assertEquals("Registration Successful", text);
		
	}

}
