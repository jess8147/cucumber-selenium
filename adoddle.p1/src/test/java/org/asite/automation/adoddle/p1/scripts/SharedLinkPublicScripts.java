package org.asite.automation.adoddle.p1.scripts;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.junit.Assert;

public class SharedLinkPublicScripts extends AdoddleCommonAppMethods {

	public static Logger	log	= AutomationLogger.getInstance().getLogger(SharedLinkPublicScripts.class);

	public void verifyDocumentHasPublicAccess(String sharedFileName) throws InterruptedException {
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(FilesTab.lbl_FileShareViewerTopHeader);
		Assert.assertTrue(getElementText(FilesTab.lbl_FileShareViewerTopHeader).contains(sharedFileName));
		waitForHTMLFileView();
	}
}
