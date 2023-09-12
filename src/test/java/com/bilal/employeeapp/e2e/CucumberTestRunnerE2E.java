package com.bilal.employeeapp.e2e;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty", "json:target/cucuber.json"},
		features = {"src/test/resources"},
		glue = {"com.bilal.employeeapp.e2e"}
		)
public class CucumberTestRunnerE2E {

}


