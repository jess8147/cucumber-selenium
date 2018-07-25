package org.asite.automation.adoddle.p2.scripts;

import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.openqa.selenium.By;

public class AMesssageLoadTestScripts extends AdoddleCommonAppMethods{

	public void sendTestMessageToChanel(String channel, String time, String duration) throws InterruptedException {
		String message = null;
		int limitCounter = 0;
		
		if(duration.contains("h"))
			limitCounter = (Integer.parseInt(duration.replace("h", ""))*3600)/(Integer.parseInt(time)+1);
		else if(duration.contains("m"))
			limitCounter = (Integer.parseInt(duration.replace("m", ""))*60)/(Integer.parseInt(time)+1);
		else if(duration.contains("s"))
			limitCounter = (Integer.parseInt(duration.replace("s", "")))/(Integer.parseInt(time)+1);
			
		log.info("Duration counter limit :"+limitCounter);
		
		navigateTab(LandingPage.lnk_Discussion);
		clickElementAndWaitForElement(By.cssSelector("li[title*='"+channel+"']"), By.cssSelector("section[class='chat-panel'] div[class*='fullwidth-box-top']"));
		waitUntilElementContainsText(By.cssSelector("section[class='chat-panel'] div[class*='fullwidth-box-top']"), channel);
		for(int counter=0; counter < limitCounter; counter++) {
			try{
			message = "This is Automation test message on "+channel+" at "+dateUtils.getEpoch();
			log.info(message);
			String query = "$(\"div[class*='ql-editor']\").text(\""+message+"\")";
			executeJScript(query);
			waitForCompletePageLoad();
			clickElementAndWait(By.cssSelector("a[class='send-btn']"));
			if(Integer.parseInt(time) > 0)
				waitUtils.waitInterval(Integer.parseInt(time));
			}
			catch(Throwable t) {
				log.info("Error while sending message "+message);
			}
			
		}
		
	}
	
	
	public void sendTestMessageToUser(String user, String time, String duration) throws InterruptedException {
		String message = null;
		
		int limitCounter = 0;
		
		if(duration.contains("h"))
			limitCounter = (Integer.parseInt(duration.replace("h", ""))*3600)/(Integer.parseInt(time)+1);
		else if(duration.contains("m"))
			limitCounter = (Integer.parseInt(duration.replace("m", ""))*60)/(Integer.parseInt(time)+1);
		else if(duration.contains("s"))
			limitCounter = (Integer.parseInt(duration.replace("s", "")))/(Integer.parseInt(time)+1);
			
		log.info("Duration counter limit :"+limitCounter);
	
		
		navigateTab(LandingPage.lnk_Discussion);
		clickElementAndWaitForElement(By.cssSelector("a[class='search-btn'] i"), By.cssSelector("input[placeholder='Search...']"));
		sendKeys(By.cssSelector("input[placeholder='Search...']"), user);
		clickElementAndWaitForElement(By.cssSelector("li[title*='"+user+"']"), By.cssSelector("section[class='chat-panel'] h2"));
		waitUntilElementContainsText(By.cssSelector("section[class='chat-panel'] h2"), user);
		for(int counter=0; counter < limitCounter; counter++) {
			try{
			message = "Automation test message "+counter+" - "+dateUtils.getEpoch();
			String query = "$(\"div[class*='ql-editor']\").text(\""+message+"\")";
			executeJScript(query);
			clickElement(By.cssSelector("a[class='send-btn']"));
			if(counter%50 == 0) {
				reloadPage();
				waitForCompletePageLoad();
			}
			
			if(Integer.parseInt(time) > 0)
				waitUtils.waitInterval(Integer.parseInt(time));
			}
			catch(Throwable t) {
				log.info("Error while sending message "+message);
			}
			
		}
		
	}
}
