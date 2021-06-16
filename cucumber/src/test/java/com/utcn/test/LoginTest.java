package com.utcn.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"pretty", "html:target/cucumber-reports"},
    features = "classpath:/features/login.feature",
    glue = "com.utcn.steps")
class LoginTest {}
