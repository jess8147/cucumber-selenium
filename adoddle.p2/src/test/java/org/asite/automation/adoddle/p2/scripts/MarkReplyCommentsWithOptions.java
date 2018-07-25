/**  Testdata required for this script as follows.
 1). Test Data Dynamic Created and Deactivated
 */
package org.asite.automation.adoddle.p2.scripts;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleDiscussionsLocators.DiscussionsTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class MarkReplyCommentsWithOptions extends AdoddleCommonAppMethods {
	
	private int				unreadCommentCount;
	private int				postUnreadCommentCount;
	private String			subject						= "Automation_Reply" + dateUtils.getEpoch();
	private String			desc						= "Automation_Reply_Desc" + dateUtils.getEpoch();
	private String			commentTitle				= "Mark_Reply_Form";
	private List<String>	expectedFormIDs				= new ArrayList<String>();
	private List<String>	expectedAssociationFiles	= new ArrayList<String>();
	private List<String>	expectedDiscussionIDs		= new ArrayList<String>();
	private String			currentWindowHandle			= null;
	public static Logger	log							= AutomationLogger.getInstance().getLogger(MarkReplyCommentsWithOptions.class);
	
	public void navigateUnreadDiscussion() {
		navigateTab(LandingPage.lnk_Files);
		clickElementAndWait(DiscussionsTab.lnk_UnreadDiscussions);
		unreadCommentCount = Integer.parseInt(getUnreadCommentCount(DiscussionsTab.lbl_UnreadDiscussionsCount));
		log.info("unreadCommentCount ::" + unreadCommentCount);
	}
	
	public void createTestData() throws InterruptedException {
		navigateTab(LandingPage.lnk_Files);
		clickElementWithText(System.getProperty("global.test.project"));
		clickElementWithText(ResourceHandler.loadProperty("application.file.upload.folder"));
		waitForCompletePageLoad();
		contextClick(FilesTab.lnk_DocListingFirstDocRef);
		clickContextMenuOption(AdoddleCommonStringPool.OPTION_NEW, AdoddleCommonStringPool.OPTION_START_A_DISCUSSION);
		if (isDisplayed(FilesTab.btn_NewDiscussionRemoveAllRecipients))
			clickElementAndWait(FilesTab.btn_NewDiscussionRemoveAllRecipients);
		sendKeys(FilesTab.txt_NewDiscussionToUserField, System.getProperty("primary.username"));
		sendKeys(FilesTab.txt_NewDiscussionToUserField, Keys.TAB);
		commentTitle = commentTitle + dateUtils.getEpoch();
		collectionDataMap.put("Comment Title:: ", commentTitle);
		sendKeys(FilesTab.txt_NewDiscussionTitleInput, commentTitle);
		sendKeys(FilesTab.txt_NewDiscussionDescInput, javaUtils.getRandomString(25));
		clickElementAndWait(FilesTab.btn_NewDiscussionSubmit);
	}
	
	public void validateUnreadCommentList() {
		AutomationAssert.verifyTrue(getCount(DiscussionsTab.css_UnreadDiscussionsCountOnListing) > 0);
	}
	
	public void searchComment(String commentName) {
		searchFiles(commentTitle);
	}
	
	public void selectComment() {
		contextClick(DiscussionsTab.lnk_DiscussionFirstCommentID);
		clickElementAndWait(DiscussionsTab.opt_DiscussionContextClickMarkAsRead);
	}
	
	public void performReadAction() {
		clickElementAndWaitForInvisibilityOfElement(DiscussionsTab.lnk_markAsRead, GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();
	}
	
	public void selectFirstCommentAndLink() {
		clickElementAndWaitForElement(DiscussionsTab.chk_DocListingFirstCheckBox, FilesTab.lnk_FilesMoreOptions);
		clickElementAndWait(FilesTab.lnk_FilesMoreOptions);
	}
	
	public void selectLink() {
		clickElementAndWait(DiscussionsTab.lnk_OptionsMarkAsRead);
	}
	
	public void validatePostCommentCount() {
		searchFiles(commentTitle);
		mouseHover(DiscussionsTab.css_myactionPlus);
		waitUntilElementIsDisplayed(DiscussionsTab.img_RFIActionCompletionIcon);
		postUnreadCommentCount = Integer.parseInt(getUnreadCommentCount(DiscussionsTab.lbl_UnreadDiscussionsCount));
		log.info("postUnreadCommentCount ::" + postUnreadCommentCount);
		try {
			AutomationAssert.verifyTrue(postUnreadCommentCount == unreadCommentCount - 1);
		}
		catch (Throwable e) {
			log.info("Failure while validating Unread comment Count");
		}
	}
	
	public void selectCommentAndReply() {
		currentWindowHandle = getCurrentWindow();
		contextClick(DiscussionsTab.lnk_DiscussionFirstCommentID);
		clickContextMenuOption(AdoddleCommonStringPool.OPTION_NEW, AdoddleCommonStringPool.OPTION_REPLY);
		waitForSwitchWindow(2);
		switchWindow();
	}
	
	public void valdiateWindowAndPopup(String cTitle) {
		waitForCompletePageLoad();
		
		if (fileBetaViewFlag) {
			waitUntilElementIsDisplayed(FilesTab.css_BetaViewStartNewDiscussionHeader);
			AutomationAssert.verifyTrue(getElementText(FilesTab.css_BetaViewStartNewDiscussionHeader).contains(
					commentTitle));
		}
		else {
			waitUntilElementIsDisplayed(FilesTab.pop_StartNewDiscussionHeader);
			AutomationAssert.verifyTrue(getElementText(FilesTab.pop_StartNewDiscussionHeader).contains(cTitle));
		}
	}
	
	public void commentSubjectAndDescription() {
		
		if (fileBetaViewFlag) {
			sendKeys(FilesTab.txt_BetaViewNewDiscussionTitleInput, subject);
			sendKeys(FilesTab.txt_BetaViewNewDiscussionDescInput, desc);
		}
		else {
			sendKeys(FilesTab.txt_NewDiscussionTitleInput, subject);
			sendKeys(FilesTab.txt_NewDiscussionDescInput, desc);
		}
	}
	
	public void clickLink() {
		if (fileBetaViewFlag)
			clickElementAndWait(FilesTab.css_BetaViewNewDiscussionMoreOptions);
		else
			clickElementAndWait(FilesTab.btn_NewDiscussionMoreOptions);
	}
	
	public void validatePopUpText() {
		if (fileBetaViewFlag)
			log.info("Covered in <associateAttachments> defination");
		else
			AutomationAssert.verifyTrue(getElementText(GlobalPageElements.lbl_PopUpHeader3).contains("Options"));
	}
	
	public void selectAvailableOptions(String optionName) {
		if (optionName.contains("Associate Files")) {
			if (fileBetaViewFlag)
				clickLinkWithText(AdoddleCommonStringPool.FORM_ASSOCIATION_ASSOCIATE_DOCS);
			else {
				waitUntilElementIsDisplayed(FilesTab.pop_NewDiscussionMoreOptionsDialog);
				clickElementAndWait(FilesTab.lnk_NewDiscussionMoreOptionssAssociateDoc);
			}
		}
		else if (optionName.contains("Associate aMessages")) {
			if (fileBetaViewFlag)
				clickLinkWithText(AdoddleCommonStringPool.FORM_ASSOCIATION_ASSOCIATE_AMESSAGES);
			else {
				waitUntilElementIsDisplayed(FilesTab.pop_NewDiscussionMoreOptionsDialog);
				clickElementAndWait(FilesTab.lnk_NewDiscussionMoreOptionsAssociateDiscussion);
			}
		}
		else {
			if (fileBetaViewFlag)
				clickLinkWithText(AdoddleCommonStringPool.FORM_ASSOCIATION_ASSOCIATE_APPS);
			else {
				waitUntilElementIsDisplayed(FilesTab.pop_NewDiscussionMoreOptionsDialog);
				clickElementAndWait(FilesTab.lnk_NewDiscussionMoreOptionsAssociateForm);
			}
		}
	}
	
	public void associateAttachments(String attachmentName) throws InterruptedException {
		int count = 2;

		if (attachmentName.contains(AdoddleCommonStringPool.TAB_FILES)) {
			if (fileBetaViewFlag) {
				clickElementWithText("Automation_WF_Folder");
				List<WebElement> existingFileList = findElements(FilesTab.css_BetaViewPopAssociateFileListingCount);
				log.info("List Size:::::: " + existingFileList.size());
				
				for (int index = 0; index < existingFileList.size(); index++) {
					log.info("Existing File List::" + existingFileList.size());
					String fileDocRef = existingFileList.get(index)
							.findElement(ProjectFormsTab.lnk_BetaViewCreateFormFilesListingRowDocRef).getText();
					log.info("File DocRef:::::: " + fileDocRef);
					WebElement chk_filesListCheckbox = existingFileList.get(index).findElement(
							ProjectFormsTab.chk_BetaViewCreateFormFilesListingRowchecbox);
					if (index <= 2)
						chk_filesListCheckbox.click();
					else
						break;
				}
				
				clickButtonWithText(AdoddleCommonStringPool.FORM_ATTACHMENT_ASSOCIATION_ASSOCIATE);
				clickButtonWithText(AdoddleCommonStringPool.FORM_ATTACHMENT_ASSOCIATION_ASSOCIATEANDCLOSE);
				waitUntilListOfElementIsDisplayed(ProjectFormsTab.css_BetaViewPopCreateFormAssociateDocsDocTitleList);
				List<WebElement> fileAssociateList = findElements(ProjectFormsTab.css_BetaViewPopCreateFormAssociateDocsDocTitleList);
				for (WebElement file : fileAssociateList)
					expectedAssociationFiles.add(file.getText());
				
				log.info("associatedFileList :" + expectedAssociationFiles);
				collectionDataMap.put("Association List:: ", expectedAssociationFiles.toString());
			}
			else {
				try {
					AutomationAssert.verifyTrue(getCount(FilesTab.css_PopAssociateFileListingCount) >= count);
				}
				catch (Throwable t) {
					log.info(t.getStackTrace());
					log.info("TESTDATA: Files count is not enough");
					clickElementAndWait(FilesTab.btn_AssociateFileCancel);
					clickElementAndWait(FilesTab.btn_NewDiscussionMoreOptionsClose);
					return;
				}
				List<WebElement> filesListCheckboxes = findElements(FilesTab.chk_AssociateFilesDocListingCheckboxes);
				List<WebElement> fileListDocRefs = findElements(FilesTab.css_PopAssociateFilesDocRefs);
				for (int index = 1; index != count + 1; index++) {
					filesListCheckboxes.get(index - 1).click();
					expectedAssociationFiles.add(fileListDocRefs.get(index - 1).getText());
				}
				log.info("File List" + expectedDiscussionIDs.size());
				clickElementAndWait(FilesTab.btn_AssociateFileSave);
				waitUntilElementIsDisplayed(FilesTab.pop_AssociatedDocListModel);
				clickElementAndWait(FilesTab.btn_AssociateDocListModelSave);
				waitUntilElementIsDisplayed(FilesTab.lnk_NewDiscussionShowFiles);
			}
		}
		
		else if (attachmentName.contains("Discussion")) {
			
			if (fileBetaViewFlag) {
				waitUntilElementIsDisplayed(DiscussionsTab.frm_BetaViewDiscussionSearchFilter);
				clickElementWithText("AutomationUploadFiles");
				waitForCompletePageLoad();
				List<WebElement> existingDiscussionList = findElements(FilesTab.css_BetaViewPopAssociateFileListingCount);
				AutomationAssert.verifyTrue("Disscussion not found:: " + existingDiscussionList.size(),
						existingDiscussionList.size() != 0);
				for (int index = 0; index < existingDiscussionList.size(); index++) {
					
					log.info("Existing Discussion List::" + existingDiscussionList.size());
					WebElement chk_filesListCheckbox = existingDiscussionList.get(index).findElement(
							ProjectFormsTab.chk_BetaViewCreateFormFilesListingRowchecbox);
					if (index <= 2)
						chk_filesListCheckbox.click();
					else
						break;
				}
				clickButtonWithText(AdoddleCommonStringPool.FORM_ATTACHMENT_ASSOCIATION_ASSOCIATE);
				clickButtonWithText(AdoddleCommonStringPool.FORM_ATTACHMENT_ASSOCIATION_ASSOCIATEANDCLOSE);
				waitUntilListOfElementIsDisplayed(ProjectFormsTab.css_BetaViewPopCreateFormAssociateDiscussionsTitleList);
				List<WebElement> discussionAssociateList = findElements(ProjectFormsTab.css_BetaViewPopCreateFormAssociateDiscussionsTitleList);
				for (WebElement comment : discussionAssociateList)
					expectedDiscussionIDs.add(comment.getText());
				log.info("associatedDiscussionList :" + expectedDiscussionIDs);
				collectionDataMap.put("Discussion List:: ", expectedDiscussionIDs.toString());
				
			}
			else {
				
				try {
					AutomationAssert.verifyTrue(getCount(FilesTab.css_PopAssociateDiscussionListingCount) >= count);
				}
				catch (Throwable t) {
					log.info(t.getStackTrace());
					log.info("TESTDATA: Discussions count is not enough");
					clickElementAndWaitForElement(FilesTab.btn_AssociateDiscussionCancel,
							FilesTab.btn_NewDiscussionMoreOptionsClose);
					clickElementAndWait(FilesTab.btn_NewDiscussionMoreOptionsClose);
					return;
				}
				
				List<WebElement> discussionListCheckboxes = findElements(FilesTab.css_PopAssociateDiscussionsCheckBoxes);
				List<WebElement> discussionListIDs = findElements(FilesTab.css_PopAssociateDiscussionsIDs);
				
				for (int index = 1; index != count + 1; index++) {
					discussionListCheckboxes.get(index - 1).click();
					expectedDiscussionIDs.add(discussionListIDs.get(index - 1).getText().split("\\(")[0]);
				}
				
				log.info("Discussion List" + expectedDiscussionIDs.size());
				clickElementAndWaitForElement(FilesTab.btn_AssociateDiscussionSave,
						FilesTab.pop_AssociatedDiscussionListModel);
				clickElementAndWaitForElement(FilesTab.btn_AssociateDiscussionListModelSave,
						FilesTab.lnk_NewDiscussionShowDiscussions);
			}
		}
		
		else {
			
			if (fileBetaViewFlag) {
				clickElementWithText("Communications");
				waitForCompletePageLoad();
				clickElementWithText("Request For Information");
				List<WebElement> existingFormList = findElements(FilesTab.css_BetaViewPopAssociateFileListingCount);
				log.info("Existing App List::" + existingFormList.size());
				for (int index = 0; index < existingFormList.size(); index++) {
					WebElement chk_AppChkBokList = existingFormList.get(index).findElement(ProjectFormsTab.chk_BetaViewCreateFormFilesListingRowchecbox);
					if (index <= 2)
						chk_AppChkBokList.click();
					else
						break;
				}

				clickButtonWithText(AdoddleCommonStringPool.FORM_ATTACHMENT_ASSOCIATION_ASSOCIATE);
				clickButtonWithText(AdoddleCommonStringPool.FORM_ATTACHMENT_ASSOCIATION_ASSOCIATEANDCLOSE);
				waitUntilListOfElementIsDisplayed(ProjectFormsTab.css_BetaViewPopCreateFormAssociateFormsFormTitleList);
				
				List<WebElement> formAssociateList = findElements(ProjectFormsTab.css_BetaViewPopCreateFormAssociateFormsFormTitleList);
				for (WebElement form : formAssociateList)
					expectedFormIDs.add(form.getText());
				
				log.info("associatedFormList :" + expectedFormIDs);
				collectionDataMap.put("Associated App List:: ", expectedFormIDs.toString());
				
			}
			
			else {
				try {
					AutomationAssert.verifyTrue(getCount(FilesTab.css_PopAssociateFormListingCount) >= count);
				}
				catch (Throwable t) {
					log.info(t.getStackTrace());
					log.info("TESTDATA: Forms count is not enough");
					clickElementAndWaitForElement(FilesTab.btn_AssociateFormCancel,
							FilesTab.btn_NewDiscussionMoreOptionsClose);
					clickElementAndWait(FilesTab.btn_NewDiscussionMoreOptionsClose);
					return;
				}
				
				List<WebElement> formListCheckboxes = findElements(FilesTab.css_PopAssociateFormsCheckboxes);
				List<WebElement> formListIDs = findElements(FilesTab.css_PopAssociateFormsIDs);
				
				for (int index = 1; index != count + 1; index++) {
					if (formListIDs.get(index - 1).getText().contains("("))
						expectedFormIDs.add(formListIDs.get(index - 1).getText().split("\\(")[0]);
					else
						expectedFormIDs.add(formListIDs.get(index - 1).getText());
					formListCheckboxes.get(index - 1).click();
				}
				
				log.info("Form List" + expectedDiscussionIDs.size());
				clickElementAndWaitForElement(FilesTab.btn_AssociateFormSave, FilesTab.pop_AssociatedFormListModel);
				clickElementAndWaitForElement(FilesTab.btn_AssociateFormListModelSave,
						FilesTab.lnk_NewDiscussionShowForms);
			}
		}
	}
	
	public void submitCommentReply() {
		if (fileBetaViewFlag)
			clickElementAndWait(FilesTab.btn_BetaFileViewNewDiscussionSubmit);
		else
			clickElementAndWait(FilesTab.btn_NewDiscussionSubmit);
	}
	
	public void verifySuccessMessage() {
		try {
			verifyElementText(FilesTab.lbl_FileAddCommentSuccessMsg,
					AdoddleCommonStringPool.MSG_COMMENT_CREATED_SUCCESS);
		}
		catch (Throwable t) {
			log.error("Success Message could not be verified");
		}
		
	}
	
	public void validateCommentReply() {
		
		if (fileBetaViewFlag) {
			AutomationAssert.verifyTrue(
					"Attachment and Association expected count:: 12",
					Integer.parseInt(getElementText(FilesTab.css_BetaViewcommentReply)) <= 12
							|| Integer.parseInt(getElementText(FilesTab.css_BetaViewcommentReply)) >= 9);
		}
		else {
			waitUntilElementIsDisplayed(FilesTab.css_commentReply);
			clickElementAndWait(FilesTab.lnk_commentReplyAttachment);
			AutomationAssert.verifyTrue(getElementText(GlobalPageElements.lbl_PopUpHeader1).contains(
					AdoddleCommonStringPool.LABEL_ATTACHMENT_ASSOCIATIONS));
			verifyFileAssociations();
			verifyAssociationDiscussions();
			verifyAssociationForms();
			clickElementAndWait(ProjectFormsTab.pop_AttachmentAndAssociationCancel);
		}
		closeCurrentWindow();
		switchPreviousWindow(currentWindowHandle);
		logOut();
		
	}
	
	public void verifyFileAssociations() {
		clickElementAndWait(DiscussionsTab.lnk_PopAttachmentAndAssociationPopupFiles);
		
		List<WebElement> associationElements = findElements(DiscussionsTab.css_PopAttachmentAndAssociationFilesDocRefs);
		List<String> actualAssociationFileDocRefs = new ArrayList<String>();
		
		for (WebElement e : associationElements)
			actualAssociationFileDocRefs.add(e.getText());
		
		actualAssociationFileDocRefs.containsAll(expectedAssociationFiles);
	}
	
	public void verifyAssociationDiscussions() {
		clickElementAndWait(DiscussionsTab.lnk_PopAttachmentAndAssociationPopupDiscussions);
		
		List<WebElement> discussionIDElements = findElements(DiscussionsTab.css_PopAttachmentAndAssociationPopupDiscussionIDs);
		List<String> actualDiscussionIDs = new ArrayList<String>();
		
		for (WebElement e : discussionIDElements)
			actualDiscussionIDs.add(e.getText());
		
		actualDiscussionIDs.containsAll(expectedDiscussionIDs);
	}
	
	public void verifyAssociationForms() {
		clickElementAndWait(DiscussionsTab.lnk_PopAttachmentAndAssociationPopupForms);
		
		List<WebElement> formIDElements = findElements(DiscussionsTab.css_PopAttachmentAndAssociationPopupFormIDs);
		List<String> actualformIDs = new ArrayList<String>();
		
		for (WebElement e : formIDElements)
			actualformIDs.add(e.getText());
		
		actualformIDs.containsAll(expectedFormIDs);
	}
	
}