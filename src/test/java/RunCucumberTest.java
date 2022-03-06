

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features",
        plugin = { "json:target/Reports/cucumber.json" },
        publish = true
)
public class RunCucumberTest {
}
