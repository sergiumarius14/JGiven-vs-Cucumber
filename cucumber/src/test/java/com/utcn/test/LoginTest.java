package com.utcn.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"pretty", "html:target/cucumber-reports-login"},
    features = "classpath:/features/login.feature",
    glue = "com.utcn.steps")
public class LoginTest {}
