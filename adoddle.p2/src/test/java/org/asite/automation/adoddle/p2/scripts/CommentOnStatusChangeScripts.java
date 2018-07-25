/**  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;

public class CommentOnStatusChangeScripts extends AdoddleCommonAppMethods {

	private String fileDocRef;
	private List<String> uploadFileList = new ArrayList<String>();
	public static Logger log = AutomationLogger.getInstance().getLogger(CommentOnStatusChangeScripts.class);

	public void uploadSingleDocument(String count) throws NumberFormatException, InterruptedException, IOException {
		uploadFileList = uploadDocuments(null, Integer.parseInt(count), null, null, false, 1, null, null, null, null);
	}

	public void changeDocumentStatus(String option) {
		searchFiles(strUtils.extractFileNameString(uploadFileList.get(0)));
		fileDocRef = getElementText(FilesTab.lnk_DocListingFirstDocRef);
		collectionDataMap.put("comment file", fileDocRef);
		if (option.contains(AdoddleCommonStringPool.OPT_CONTEXTCLICK)) {
			contextClick(FilesTab.lnk_DocListingFirstDocRef);
			waitUntilElementIsDisplayed(FilesTab.opt_FileContextClickEdit);
			mouseHoverAndClickElement(FilesTab.opt_FileContextClickEdit, FilesTab.opt_FileContextClickEditStatus);
		} else if (option.contains(AdoddleCommonStringPool.OPT_MOREOPTIONS)) {
			clickElementAndWait(FilesTab.chk_DocListingFirstCheckBox);
			clickElementAndWaitForElement(FilesTab.lnk_FilesTabMoreOptions, FilesTab.lnk_PopMoreOptionsStatusChange);
			clickElementAndWaitForInvisibilityOfElement(FilesTab.lnk_PopMoreOptionsStatusChange, FilesTab.lnk_PopMoreOptionsStatusChange);
		}
		waitUntilElementIsDisplayed(GlobalPageElements.pop_PopUpElement);
		performStatusChange(AdoddleCommonStringPool.STATUS_FOR_INFORMATION);
	}

	public void verifyCommentIsCreated() {
		searchFiles(strUtils.extractFileNameString(uploadFileList.get(0)));
		clickElementAndSwitchWindow(FilesTab.lnk_DocListingFirstDocRef);
		waitUntilElementIsDisplayed(FilesTab.ele_BetaFileViewDiscussionListFirstDiscussion);
		AutomationAssert.verifyTrue(getCount(FilesTab.ele_BetaFileViewDiscussionListFirstDiscussion) > 0);
		waitUntilElementContainsText(FilesTab.ele_BetaFileViewDiscussionListFirstDiscussion, "Document Status Change");
	}

}
