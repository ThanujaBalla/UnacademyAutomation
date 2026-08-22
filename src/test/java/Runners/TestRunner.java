package Runners;

import org.testng.annotations.AfterSuite;

import Reports.ExtentManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Features", glue = { "StepDefinitions", "Base" },tags="@TC013A", plugin = {
		"pretty", "html:target/cucumber-report.html" }, monochrome = true)


public class TestRunner extends AbstractTestNGCucumberTests {
	@AfterSuite
	public void finishReport() {
		ExtentManager.flushReport();
		System.out.println("====================================");
		System.out.println("Extent Report generated successfully.");
		System.out.println("====================================");
	}
}