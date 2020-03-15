package testRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
<<<<<<< HEAD
        features = {".//Features/Login.feature"},
//features = {"./Features/"},
=======
//        features = ".//Features/Customer.feature",
        features = {".//Features/Login.feature",".//Features/Customer.feature"},
>>>>>>> f8aec9595d4c623bbf4dde9b793dca52eee7732a
        glue = "stepDefinitions",
        monochrome = true,
        plugin = {"pretty", "html:test-output"}
)
public class TestRun {
}
