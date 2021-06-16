package com.utcn.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
 @CucumberOptions(features = "classpath:/features/register.feature", glue = "com.utcn.steps", strict = true)
public class RegisterTest {}
