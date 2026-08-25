package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Features", glue = { "StepDefinitions", "Base" },tags="@TC013A", plugin = {
		"pretty", "html:target/cucumber-report.html" }, monochrome = true)

public class TestRunner extends AbstractTestNGCucumberTests {
}