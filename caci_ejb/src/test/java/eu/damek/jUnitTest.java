package eu.damek;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Project: caci_test
 * For:
 * Created by damekjan on 31/07/2017.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        format = { "pretty", "html:target/cucumber" },
        features = "classpath:cucumber/caci.feature"
)
public class jUnitTest {
}
