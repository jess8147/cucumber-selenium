/**  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleDiscussionsLocators.DiscussionsTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CreatePrivateCommentScripts extends AdoddleCommonAppMethods {

	private static String	fileName, parentHandle;
	private String			replyTitle;
	List<String>			expectedFormIDs				= new ArrayList<String>();
	List<String>			expectedAssociationFiles	= new ArrayList<String>();
	List<String>			expectedDiscussionIDs		= new ArrayList<String>();
	List<String>			expectedAttachmentFiles		= new ArrayList<String>();
	List<Double>			fileSizeListExpected		= new ArrayList<Double>();
	List<WebElement>		fileSizeElements			= new ArrayList<WebElement>();
	public static Logger	log							= AutomationLogger.getInstance().getLogger(CreatePrivateCommentScripts.class);

	public void verifyUserIsLoggedIn() throws InterruptedException {
		verifyLogin();
	}

	public void navigateFilesTab() {
		navigateTab(LandingPage.lnk_Files);
	}

	public void verifyDocumentAvailable() throws InterruptedException, IOException {
		List<String> fileList = new ArrayList<String>();
		fileName = javaUtils.getRandomString(10)+epoch;
		sysUtils.authenticateRemoteMachine(nodeIP);
		fileList.add(sysUtils.createRemotePdfFile(nodeIP+resourceUtils.getTestDataFilePath()+fileName+AdoddleCommonStringPool.PDF_EXTENSION, nodeIP));
		uploadDocuments(fileList, 1, null, null, false, 1, null, null, null, null);
		searchFiles(fileName);
		collectionDataMap.put("file name", fileName);
		AutomationAssert.verifyTrue(getCount(FilesTab.css_DocListingFilesCount) > 0);
	}

	public void contextClickDocument() {
		contextClick(FilesTab.lnk_DocListingFirstDocRef);
	}

	public void contextClickFirstDiscussion() {
		contextClick(DiscussionsTab.lnk_DiscussionFirstCommentTitle);
	}

	public void clickOnContextMenuElement(String elementText) {
		clickContextMenuOptionWithText(elementText);
		waitUntilElementIsDisplayed(GlobalPageElements.pop_PopUpElement);
	}

	public void verifyNewPopup(String expectedText) {

		AutomationAssert.verifyTrue(isDisplayed(FilesTab.pop_StartNewDiscussion));
		verifyElementText(FilesTab.pop_StartNewDiscussionHeader, expectedText);
	}

	public void verifyUserExistsInToField(String expectedText, String user1, String user2) throws InterruptedException {
		
		while (getCount(FilesTab.css_NewDiscussionToUserCount) > 0) {
			clickElementAndWait(FilesTab.btn_NewDiscussionRemoveAllRecipients);
		}
		
		sendKeys(FilesTab.txt_NewDiscussionToUserField, user1);
		sendKeys(FilesTab.txt_NewDiscussionToUserField, Keys.TAB);
		sendKeys(FilesTab.txt_NewDiscussionToUserField, user2);
		sendKeys(FilesTab.txt_NewDiscussionToUserField, Keys.TAB);
		AutomationAssert.verifyTrue(getCount(FilesTab.css_NewDiscussionToUserCount) > 0);
		verifyElementText(FilesTab.lbl_NewDiscussionToField, expectedText);
	}

	public void attachAtLeastOneDocument() throws Exception {
		clickElementAndWaitForElement(FilesTab.btn_NewDiscussionMoreOptions, FilesTab.lnk_NewDiscussionMoreOptionsAttachment);
		clickElementAndWaitForElement(FilesTab.lnk_NewDiscussionMoreOptionsAttachment,FilesTab.pop_AttachmentFileModel);

		List<String> fileList = sysUtils.getFileList(ResourceHandler.loadProperty("multi.files.path"));
		for (String f : fileList) {
			File file = new File(f);
			expectedAttachmentFiles.add(file.getName());
			fileSizeListExpected.add(sysUtils.getFileSize(f));
		}

		uploadFileUsingKeys(FilesTab.btn_NewDiscussionAttachmentSelectFiles, fileList);
		collectionDataMap.put("attachments", fileList.toString());
		waitUntilElementIsDisplayed(FilesTab.btn_NewDiscussionAttachmentSave);
		AutomationAssert.verifyTrue(getCount(FilesTab.css_NewDiscussionAttachmentCount) > 0);
		fileSizeElements = findElements(FilesTab.lbl_NewDiscussionAttachmentsFileSizeElements);

		for (int index = 0; index != fileSizeListExpected.size(); index++) {
			try {
				AutomationAssert.verifyTrue(Double.parseDouble(fileSizeElements.get(index).getText().replace("KB", "")) == Math.round(fileSizeListExpected.get(index) * 100.0) / 100.0);
			}
			catch (Throwable t) {
				log.error(t.getStackTrace());
				log.error("File size did not match for external file attachments");
				log.error("Adoddle Attachment size:::" + Double.parseDouble(fileSizeElements.get(index).getText().replace("KB", "")));
				log.error("Actual File Size:::" + Math.round(fileSizeListExpected.get(index) * 100.0) / 100.0);
			}

		}

		clickElementAndWait(FilesTab.btn_NewDiscussionAttachmentSave);
	}

	public void getWindowHandle() {
		parentHandle = getCurrentWindow();
	}

	public void checkPrivateCheckbox() {
		clickElementAndWait(FilesTab.chk_NewDiscussionMarkAsPrivate);
	}

	public void performReplyAction(String replyUser) throws InterruptedException {
			waitUntilElementIsDisplayed(FilesTab.txt_BetaFileViewReplyDiscussionToUserField);
			replyTitle = getValue(FilesTab.txt_BetaFileViewNewDiscussionTitle);
			collectionDataMap.put("reply title", replyTitle);
			clickElementAndWaitForElement(FilesTab.txt_BetaFileViewReplyDiscussionToUserField, FilesTab.lnk_BetaFileViewReplyDiscussionClearToField);
			clickElementAndWait(FilesTab.lnk_BetaFileViewReplyDiscussionClearToField);
			sendKeys(FilesTab.txt_BetaFileViewReplyDiscussionToUserField, replyUser);
			sendKeys(FilesTab.txt_BetaFileViewReplyDiscussionToUserField, Keys.TAB);
			clickElementAndWait(FilesTab.chk_BetaFileViewReplyDiscussionFilteredUserFirstCheck);
			sendKeys(FilesTab.txt_BetaFileViewReplyDiscussionDescInput, javaUtils.getRandomString(20));
			clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_BetaFileViewReplyDiscussionSubmit, FilesTab.btn_BetaFileViewReplyDiscussionSubmit);
	}

	public void verifyReplyIsSuccessful() {
		if(!fileBetaViewFlag) {
			waitUntilElementIsDisplayed(FilesTab.ele_DiscussionsSelectedSubComment);
			AutomationAssert.verifyTrue(getElementText(FilesTab.ele_DiscussionsSelectedSubComment).contains(replyTitle));
		}
		else {
			waitUntilElementIsDisplayed(FilesTab.ele_BetaFileViewDiscussionsSubComment);
			AutomationAssert.verifyTrue(getElementText(FilesTab.ele_BetaFileViewDiscussionsSubComment).contains(replyTitle));
		}
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
	}

	public void verifyPrivateDiscussionReply(String flag) {
		parentHandle = getCurrentWindow();
		AutomationAssert.verifyTrue(getElementText(DiscussionsTab.lnk_DiscussionFirstCommentID).split("\\(2").length == 2);
		clickElementAndSwitchWindow(DiscussionsTab.lnk_DiscussionFirstAction);
		if (flag.equalsIgnoreCase("yes")) {
			waitUntilElementIsDisplayed(FilesTab.ele_BetaViewDiscussionsSubCommentOfSelectedComment);
			AutomationAssert.verifyTrue(getElementText(!fileBetaViewFlag? FilesTab.ele_DiscussionsSubCommentOfSelectedComment: FilesTab.ele_BetaViewDiscussionsSubCommentOfSelectedComment).contains(replyTitle));
			closeCurrentWindow();
			switchPreviousWindow(parentHandle);
		}
		else {
			waitUntilElementIsDisplayed(FilesTab.ele_BetaFileViewDiscussionListFirstDiscussion);
			AutomationAssert.verifyTrue(!isDisplayed(FilesTab.ele_BetaViewDiscussionsSubCommentOfSelectedComment));
		}

	}

	public void verifyReplyIsAvailableOnAMessages(String user, String responder) {
		searchUserGroupEntity(responder);
		log.info(getElementText(DiscussionsTab.lbl_AMessageTabChatPanelLastMessage)+"=="+(replyTitle));
		AutomationAssert.verifyTrue(getElementText(DiscussionsTab.lbl_AMessageTabChatPanelLastMessage).contains(replyTitle));
	}

}
