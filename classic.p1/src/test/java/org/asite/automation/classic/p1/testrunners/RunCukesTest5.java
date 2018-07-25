package org.asite.automation.classic.p1.testrunners;
import org.asite.automation.common.utils.ReportUtils;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"json:target/cucumber5.json"}, 
		dryRun = false,
		monochrome  = true,
		tags = {"@P1T5"},
		glue = {"org.asite.automation.classic.p1.stepdefinitions"},
		features = {"src"}
		)
		
public class RunCukesTest5 {
	
	@AfterClass
	 public static void attachShutDownHook(){
      Runtime.getRuntime().addShutdownHook(new Thread() {
          @Override
          public void run() 
          {
           try 
           {
        	   ReportUtils.executeReport();
        	   ReportUtils.mergeExcelReports();
           } 
           catch(Throwable e)
           {
                  System.out.println("Exception occurred: " + e.getLocalizedMessage());
                  e.printStackTrace();
           } 
          }
      });

  }
}