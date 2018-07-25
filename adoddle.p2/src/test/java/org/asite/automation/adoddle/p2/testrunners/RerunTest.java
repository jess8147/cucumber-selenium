package org.asite.automation.adoddle.p2.testrunners;

import org.asite.automation.common.utils.ReportUtils;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "json:target/cucumberRerun.json" }, dryRun = false, monochrome = true, glue = { "org.asite.automation.adoddle.p1.stepdefinitions", "org.asite.automation.common.steps" }, features = {"@target/rerun.txt"})
public class RerunTest {

	@AfterClass
	public static void attachShutDownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					ReportUtils.executeReport();
					ReportUtils.mergeExcelReports();
				}
				catch (Throwable e) {
					System.out.println("Exception occurred: " + e.getLocalizedMessage());
				}
			}
		});

	}
}