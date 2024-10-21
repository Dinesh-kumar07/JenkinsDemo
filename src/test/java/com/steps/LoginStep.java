package com.steps;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.omrbranch.baseclass.BaseClass;

import io.cucumber.java.en.*;


public class LoginStep extends BaseClass {

	

	@Given("User  is on registration page")
	public void user_is_on_registration_page() {
//		driver = new ChromeDriver();
//		driver.get("http://192.168.1.3:8080/register/");
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement element = driver.findElement(By.xpath("//h2[text()='Registration Form']"));
		String text = element.getText();
		System.out.println(text);
		Assert.assertEquals("verify","Registration Form", text);
	}

	@When("User login {string},{string},{string} and {string}")
	public void user_login_and(String string, String string2, String string3, String string4) {

		driver.findElement(By.id("username")).sendKeys("manivannan");
		driver.findElement(By.id("email")).sendKeys("manimailupuli3696@gmail.com");
		driver.findElement(By.id("password")).sendKeys("ManiV@3696");
		driver.findElement(By.id("confirmPassword")).sendKeys("ManiV@3696");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	@Then("User should verify success message after Login {string}")
	public void user_should_verify_success_message_after_login(String string) {
		WebElement element = driver.findElement(By.xpath("//h2[text()='Registration Successful']"));
		String text = element.getText();
		System.out.println(text);
		Assert.assertEquals("verify","Registration Successful", text);
		
	}

}
