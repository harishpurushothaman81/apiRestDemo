package runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/functionalTests",
		glue = {"stepDefinitions"},
		tags = "@mytag",
		plugin = { "pretty", "json:target/Destination/cucumber.json",
				"junit:target/Destination/Cucumber.xml",
		"html:target/Destination"},
		monochrome = true,
		strict = true
		)

public class TestRunner {

}
