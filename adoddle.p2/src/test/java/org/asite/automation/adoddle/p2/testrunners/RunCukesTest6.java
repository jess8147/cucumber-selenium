package org.asite.automation.adoddle.p2.testrunners;
import org.asite.automation.common.utils.ReportUtils;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"json:target/cucumber6.json"}, 
		dryRun = false,
		monochrome  = true,
		tags = {"@P2T6"},
		glue = {"org.asite.automation.adoddle.p2.stepdefinitions","org.asite.automation.common.steps"},
		features = {"src"}
		)
		
public class RunCukesTest6 {
	
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