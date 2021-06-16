package com.utcn.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"pretty", "html:target/cucumber-reports-register"},
    features = "classpath:/features/register.feature",
    glue = "com.utcn.steps")
public class RegisterTest {}
