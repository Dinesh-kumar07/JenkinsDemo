package com.omrbranch.utils;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Configuration;

public class Reporting {
	public static void generateJVMReport(String jsonfile) {
		File reportDirectory = new File(System.getProperty("user.dir") + "\\target");
		Configuration config = new Configuration(reportDirectory, "jenkinsdemo");

		config.addClassifications("PlatformName", "Windows");
		config.addClassifications("PlatformVersion", "11");
		config.addClassifications("Sprint", "07");
		config.addClassifications("Environment", "QA");
		config.addClassifications("Author", "Dinesh");

		List<String> jsonFiles = new ArrayList<String>();
		jsonFiles.add(jsonfile);
		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, config);
		reportBuilder.generateReports();

	}

}
