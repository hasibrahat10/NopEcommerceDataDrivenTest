package testRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
<<<<<<< HEAD
      features = ".//Features/Login.feature",
        //features = {".//Features/Login.feature",".//Features/Customer.feature"},
=======

       features = ".//Features/Customer.feature",
        //features = {".//Features/Login.feature",".//Features/Customer.feature"},

>>>>>>> 37079e81bc4ea690a8218d5750678d24dfbe9655
        glue = "stepDefinitions",
        monochrome = true,
        plugin = {"pretty", "html:test-output"}
)
public class TestRun {
}
