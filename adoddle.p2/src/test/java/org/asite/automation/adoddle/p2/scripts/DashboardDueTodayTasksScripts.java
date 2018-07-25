package org.asite.automation.adoddle.p2.scripts;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.openqa.selenium.By;


public class DashboardDueTodayTasksScripts extends AdoddleCommonAppMethods {

	public void verifyWidgetTitle(String widgetTitle) {
		AutomationAssert.verifyTrue(getElementText(By.cssSelector("div[id='playground'] span[title='Files - Incomplete Tasks']")).equalsIgnoreCase(widgetTitle));
	}
	
	public void distributeActionsTo(String user) {
		contextClick(FilesTab.lnk_DocListingFirstDocRef);
		mouseHoverAndClickElement(FilesTab.opt_FileContextClickShare, FilesTab.opt_FileContextClickShareDistribute);
		assignActionsToUser(user);
	}
	
	public void verifyWidgetActions(String widget) {
		mouseHoverAndClickElement(By.cssSelector("div[title='widget-desc-files-incomplete-actions']+div[class='portlet-content'] svg"), By.cssSelector("div[title='widget-desc-files-incomplete-actions'] span[title='Maximize']"));
		try {
			waitUtils.waitInterval(1);
		} catch (InterruptedException e) {
			e.getLocalizedMessage();
		}
	}
}
