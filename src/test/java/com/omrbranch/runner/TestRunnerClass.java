package com.omrbranch.runner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.omrbranch.utils.Reporting;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src\\test\\resources\\Feature\\Registration.feature", glue = "com.steps", plugin = {
		"pretty", "junit:target\\reports.xml", "html:target\\report.html",
		"json:target\\report.json" }, publish = true, monochrome = false, stepNotifications = true, dryRun = false)
public class TestRunnerClass {

	@AfterClass
	public static void afterExecution() {
		Reporting.generateJVMReport(System.getProperty("user.dir") + "\\target\\report.json");

	}

}
