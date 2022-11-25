package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/Cucumber",glue="rahulshettyacademy.StepDefinitions",
monochrome=true,tags = "@Regression",plugin= {"html:target/cucmber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

	public static void main(String[] args) {
		System.out.println("Enter Array elements");
	}

}
