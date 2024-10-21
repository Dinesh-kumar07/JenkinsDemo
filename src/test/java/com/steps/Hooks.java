package com.steps;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.omrbranch.baseclass.BaseClass;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks  extends BaseClass {
	
	@Before
	public void beforeScenario(Scenario scenario) throws FileNotFoundException, IOException {
		System.out.println("before .....");
		driver = new ChromeDriver();
		driver.get("http://192.168.1.3:8080/register/");
		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		scenario.attach(screenshot2(), "image/png", scenario.getName());
		
	}

	@After
	public void afterScenario(Scenario scenario) throws InterruptedException, SocketException {
		System.out.println("After .....");
		deleteCookies();
		scenario.attach(screenshot2(), "image/png", scenario.getName());
		Thread.sleep(5000);
//		closeWindow();
	}
}
