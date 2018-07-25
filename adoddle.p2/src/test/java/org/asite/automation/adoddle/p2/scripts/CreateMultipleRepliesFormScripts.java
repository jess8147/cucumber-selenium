package org.asite.automation.adoddle.p2.scripts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleDiscussionsLocators.DiscussionsTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleModelsLocators.ModelsTab;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.resources.AdoddleScenarioMarkers;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CreateMultipleRepliesFormScripts extends AdoddleCommonAppMethods {

	private static String formTitle, commentTitle;

	private List<String> fileDocRefs = new ArrayList<String>();
	private List<WebElement> formActionList = new ArrayList<WebElement>();
	private List<WebElement> existingDiscussionList = new ArrayList<WebElement>();
	private List<WebElement> existingFormList = new ArrayList<WebElement>();
	private List<WebElement> existingFileList = new ArrayList<WebElement>();

	final private String docRefEpoch = epoch;
	final private List<String> attachedFileList = new ArrayList<String>();
	final private List<String> associatedFileList = new ArrayList<String>();
	final private List<String> associatedDiscussionList = new ArrayList<String>();
	final private List<String> associatedFormList = new ArrayList<String>();
	final private String docRef1 = "FileDocRef1_" + docRefEpoch;
	final private String docRef2 = "FileDocRef2_" + docRefEpoch;
	final private String docRef3 = "FileDocRef3_" + docRefEpoch;
	final private String docRef4 = "FileDocRef4_" + docRefEpoch;
	final private String docRef5 = "FileDocRef5_" + docRefEpoch;
	final private String docRef6 = "FileDocRef6_" + docRefEpoch;
	final private String docRef7 = "FileDocRef7_" + docRefEpoch;
	final private String docRef8 = "FileDocRef8_" + docRefEpoch;
	final private String docRef9 = "FileDocRef9_" + docRefEpoch;
	private static List<String> associatedFileDiscussionList = new ArrayList<String>();
	private static List<String> fileAssociatedDocuments = new ArrayList<String>();
	final private static Logger log = AutomationLogger.getInstance().getLogger(CreateMultipleRepliesFormScripts.class);

	public void focusFolder(String folderName) {
		clickElementWithText(folderName);
	}

	public void validateFileCountInListing() throws InterruptedException, IOException {
		fileDocRefs = new ArrayList<String>(Arrays.asList(docRef1, docRef2, docRef3, docRef4, docRef5, docRef6,
				docRef7, docRef8, docRef9));
		uploadDocuments(null, 9, fileDocRefs, null, false, 1, null, null, null, null);
		searchFiles(docRefEpoch);
		AutomationAssert.assertTrue("Expected Document count:: 9" + " But Found "
				+ getCount(FilesTab.css_FilesListingRecords), getCount(FilesTab.css_FilesListingRecords) == 9);
	}

	public void postCommentOnFile() {
		String expectedText = "New";
		clickElementAndWaitForElement(FilesTab.chk_MultiFilesSelectionCheckbox, FilesTab.lnk_DocListingFirstDocRef);
		contextClick(FilesTab.lnk_DocListingFirstDocRef);
		mouseHoverAndClickElement(FilesTab.opt_FileContextClickNew, FilesTab.opt_FileContextClickStartDiscussion);
		verifyNewPopup(expectedText);
		verifyUserExistsInToField();
		commentTitle = "AutoTestComment_" + dateUtils.getEpoch();
		collectionDataMap.put("Comment title:: ", commentTitle);
		log.info("Comment Title::" + commentTitle);
		String commentDesc = resourceUtils.getSpecialCharString();
		sendKeys(FilesTab.txt_NewDiscussionTitleInput, commentTitle);
		sendKeys(FilesTab.txt_NewDiscussionDescInput, commentDesc);
		clickElementAndWait(FilesTab.btn_NewDiscussionSubmit);
	}

	private void verifyNewPopup(String expectedText) {
		waitUntilElementIsDisplayed(GlobalPageElements.pop_PopUpElement);
		Assert.assertTrue(isDisplayed(FilesTab.pop_StartNewDiscussion));
		verifyElementText(FilesTab.pop_StartNewDiscussionHeader, expectedText);
	}

	private void verifyUserExistsInToField() {
		try {
			Assert.assertTrue(getCount(FilesTab.css_NewDiscussionToUserCount) > 0);
		} catch (Throwable t) {
			sendKeys(FilesTab.txt_NewDiscussionToUserField, System.getProperty("secondary.username"));
			sendKeys(FilesTab.txt_NewDiscussionToUserField, Keys.TAB);
			Assert.assertTrue(getCount(FilesTab.css_NewDiscussionToUserCount) > 0);
		}

	}

	public void selectUserInToField(String user) {
		sendKeys(ProjectFormsTab.txt_BetaViewCreateRFIFormInputToField, user);
		findElement(ProjectFormsTab.txt_BetaViewCreateRFIFormInputToField).sendKeys(
				Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
	}

	public void clickOnCreateFormButtonOptions(String buttonType) {
		if (buttonType.contains("Attachment"))
			clickElementAndWait(ProjectFormsTab.btn_BetaViewCreateFormAttachmentClipIcon);
		else
			clickElementAndWait(ProjectFormsTab.btn_BetaViewCreateFormAssociationIcon);
	}

	public void clickOndropDownListOptions(String optionTxt) {
		clickLinkWithText(optionTxt);
	}

	public void validateFormAndAssignedAction() {
		searchForms(formTitle);
		waitUntilElementContainsText(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle, formTitle);
		validateUserAssignedActions();
	}

	public void selectUserAndDescription(String userName) {
		sendKeys(ProjectFormsTab.txt_BetaViewCreateRFIFormInputToField, userName);
		waitUntilElementIsDisplayed(By
				.xpath(".//div[@id='distInputTo']//input[@type='checkbox']//following::span[contains(text(),'"
						+ userName + "')]"));
		findElement(ProjectFormsTab.txt_BetaViewCreateRFIFormInputToField).sendKeys(
				Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
		sendKeys(ProjectFormsTab.txt_CreateFormYourEditableResponse,
				ResourceHandler.loadProperty("special.char.test.string"));
	}

	public void clearFieldBox() {
		List<WebElement> BetaViewfieldBoxesClearLink = findElements(ProjectFormsTab.txt_BetaViewCreateRFIFormInputToFieldCount);
		log.info("FieldBox Size" + BetaViewfieldBoxesClearLink.size());
		if (BetaViewfieldBoxesClearLink.size() > 0) {
			clickElement(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo);
			clickElementAndWait(ProjectFormsTab.lnk_BetaViewCreateFormDistributeToClearButton);
		} else
			log.info("User Field is Blank");
	}

	public void clickOnSelectFilesAndAttachDocuments() {
		sysUtils.authenticateRemoteMachine(nodeIP);
		String createFile1 = sysUtils.createRemotePdfFile(
				nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch()
						+ AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
		String createFile2 = sysUtils.createRemotePdfFile(
				nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch()
						+ AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();

		String createFile3 = sysUtils.createRemotePdfFile(
				nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch()
						+ AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();

		log.info("first file created as: " + createFile1);
		log.info("second file created as :" + createFile2);
		log.info("Third file created as :" + createFile3);

		List<String> fileList = sysUtils.getFileList(createFile1 + "," + createFile2 + "," + createFile3);
		collectionDataMap.put("Attachement List:: ", fileList.toString());
		uploadFileUsingKeys(ProjectFormsTab.btn_BetaViewCreateFormAttachmentsSelectFiles, fileList);
		waitUntilListOfElementIsDisplayed(ProjectFormsTab.css_BetaViewPopCreateFormAttachmentsFileNameList);
		List<WebElement> fileAttachList = findElements(ProjectFormsTab.css_BetaViewPopCreateFormAttachmentsFileNameList);
		for (WebElement attachFile : fileAttachList)
			attachedFileList.add(attachFile.getText());
		log.info("attachedFileList :" + attachedFileList);
		clickElementAndWait(ModelsTab.btn_PopModelCommentAttachmentsAttachButton);
		try {
			Assert.assertTrue(getElementText(ProjectFormsTab.lbl_CreateFormUploadSucessMsg).contains(
					AdoddleCommonStringPool.MSG_UPLOAD_COMPLETED));
		} catch (Throwable e) {
			log.info("Failure while attachment operation or success message not verified");
		}
	}

	public void associateFilesAndClickOnSave() {
		int maxAssociateDocCount = 0;
		clickElementWithText(ResourceHandler.loadProperty("workflow.test.files.folder"));
		waitUntilElementContainsText(ProjectFormsTab.lbl_BetaViewCreateFormFilesFolderLabel,
				ResourceHandler.loadProperty("workflow.test.files.folder"));
		existingFileList.clear();
		existingFileList = findElements(FilesTab.css_BetaViewPopAssociateFileListingCount);
		log.info("List Size:: " + existingFileList.size());
		for (int index = 0; index < existingFileList.size(); index++) {

			log.info("Existing File List::" + existingFileList.size());
			String fileDocRef = existingFileList.get(index)
					.findElement(ProjectFormsTab.lnk_BetaViewCreateFormFilesListingRowDocRef).getText();
			log.info("File DocRef:::::: " + fileDocRef);
			WebElement chk_filesListCheckbox = existingFileList.get(index).findElement(
					ProjectFormsTab.chk_BetaViewCreateFormFilesListingRowchecbox);
			if (!fileDocRefs.contains(fileDocRef)
					&& (fileAssociatedDocuments.isEmpty() || !fileAssociatedDocuments.contains(fileDocRef))) {
				chk_filesListCheckbox.click();
				fileAssociatedDocuments.add(fileDocRef);
				if (maxAssociateDocCount == 2)
					break;
				maxAssociateDocCount += 1;
			}
		}

		clickButtonWithText(AdoddleCommonStringPool.FORM_ATTACHMENT_ASSOCIATION_ASSOCIATE);
		clickButtonWithText(AdoddleCommonStringPool.FORM_ATTACHMENT_ASSOCIATION_ASSOCIATEANDCLOSE);
		waitUntilListOfElementIsDisplayed(ProjectFormsTab.css_BetaViewPopCreateFormAssociateDocsDocTitleList);
		List<WebElement> fileAssociateList = findElements(ProjectFormsTab.css_BetaViewPopCreateFormAssociateDocsDocTitleList);
		for (WebElement file : fileAssociateList)
			associatedFileList.add(file.getText());
		log.info("associatedFileList :" + associatedFileList);
		collectionDataMap.put("Association List:: ", associatedFileList.toString());
	}

	public void associateDiscussionsAndClickOnSave(String messageType) throws InterruptedException {
		int docRefIndex = 1;
		sendKeys(DiscussionsTab.frm_BetaViewDiscussionSearchFilter, commentTitle);
		sendKeys(DiscussionsTab.frm_BetaViewDiscussionSearchFilter, Keys.ENTER);
		existingDiscussionList.clear();
		waitUntilElementIsDisplayed(FilesTab.css_BetaViewPopAssociateFileListingCount);
		waitUntilElementCountToBe(FilesTab.css_BetaViewPopAssociateFileListingCount, 9);
		existingDiscussionList = findElements(FilesTab.css_BetaViewPopAssociateFileListingCount);
		AutomationAssert.verifyTrue("Expected Amessage count:: 9" + " But was " + existingDiscussionList.size(),
				existingDiscussionList.size() == 9);

		for (WebElement attribute : findElements(ProjectFormsTab.css_BetaViewCreateFormAttributeHeaderLabelList)) {
			if (attribute.getAttribute("title").contains(AdoddleCommonStringPool.DOC_REF)) {
				log.info("docRefIndex : " + docRefIndex);
				break;
			}
			docRefIndex++;
		}
		int i = 1, j = 0;
		for (WebElement checkbox : findElements(ProjectFormsTab.chk_BetaViewCreateFormFilesListingRowchecbox)) {
			if (!checkbox.isSelected()) {

				if (!getElementText(
						By.xpath(".//association//table-listing//div[@class='gbody']//ul[" + i + "]//li[" + docRefIndex
								+ "]//a[text()]")).contains("--")) {

					if (!associatedFileDiscussionList.contains(getElementText(By
							.xpath(".//association//table-listing//div[@class='gbody']//ul[" + i + "]//li["
									+ docRefIndex + "]//a[text()]")))) {

						log.info("Discussion File DocTitle : "
								+ getElementText(By.xpath(".//association//table-listing//div[@class='gbody']//ul[" + i
										+ "]//li[" + docRefIndex + "]//a[text()]")));
						checkbox.click();
						associatedFileDiscussionList.add(getElementText(By
								.xpath(".//association//table-listing//div[@class='gbody']//ul[" + i + "]//li["
										+ docRefIndex + "]//a[text()]")));
						log.info("Discussion fileDocRef List : " + associatedFileDiscussionList.toString());

						j++;
					}
				}
			}
			if (j == 3)
				break;
			i++;
		}

		/*
		 * for (int index = 0; index < existingDiscussionList.size(); index++) {
		 * 
		 * log.info("Existing Discussion List::" +
		 * existingDiscussionList.size()); log.info("Excluded File List" +
		 * fileDocRefs.size()); WebElement chk_filesListCheckbox =
		 * existingDiscussionList.get(index).findElement(
		 * ProjectFormsTab.chk_BetaViewCreateFormFilesListingRowchecbox);
		 * 
		 * if (messageType.equalsIgnoreCase("ORI0001")) {
		 * 
		 * if (index == 0 || index == 1 || index == 2)
		 * chk_filesListCheckbox.click();
		 * 
		 * else if (index == 3) break;
		 * 
		 * }
		 * 
		 * else if (messageType.equalsIgnoreCase("FWD0001")) {
		 * 
		 * if (index == 3 || index == 4 || index == 5)
		 * chk_filesListCheckbox.click();
		 * 
		 * else if (index == 6) break;
		 * 
		 * }
		 * 
		 * else {
		 * 
		 * if (index == 6 || index == 7 || index == 8)
		 * chk_filesListCheckbox.click();
		 * 
		 * else if (index == 9) break;
		 * 
		 * } }
		 */
		clickButtonWithText(AdoddleCommonStringPool.FORM_ATTACHMENT_ASSOCIATION_ASSOCIATE);
		clickButtonWithText(AdoddleCommonStringPool.FORM_ATTACHMENT_ASSOCIATION_ASSOCIATEANDCLOSE);
		waitUntilListOfElementIsDisplayed(ProjectFormsTab.css_BetaViewPopCreateFormAssociateDiscussionsTitleList);
		List<WebElement> discussionAssociateList = findElements(ProjectFormsTab.css_BetaViewPopCreateFormAssociateDiscussionsTitleList);
		for (WebElement comment : discussionAssociateList)
			associatedDiscussionList.add(comment.getText());
		log.info("associatedDiscussionList :" + associatedDiscussionList);
		collectionDataMap.put("Discussion List:: ", associatedDiscussionList.toString());
	}

	public void associateFormAndClickOnSave(String messageType) throws InterruptedException {
		clickElementWithText("Communications");
		waitForCompletePageLoad();
		waitUntilElementContainsText(ProjectFormsTab.lbl_BetaViewCreateFormFilesFolderLabel, "Communications");
		existingFormList.clear();
		existingFormList = findElements(FilesTab.css_BetaViewPopAssociateFileListingCount);
		log.info("Existing App List::" + existingFormList.size());
		for (int index = 0; index < existingFormList.size(); index++) {

			WebElement chk_AppChkBokList = existingFormList.get(index).findElement(
					ProjectFormsTab.chk_BetaViewCreateFormFilesListingRowchecbox);

			if (messageType.contains("ORI0001")) {
				if (!chk_AppChkBokList.isSelected() && index <= 1)
					chk_AppChkBokList.click();

			} else if (messageType.contains("FWD0001")) {
				if (!chk_AppChkBokList.isSelected() && index == 2 || index == 3)
					chk_AppChkBokList.click();
			} else {
				if (!chk_AppChkBokList.isSelected() && index == 4 || index == 5)
					chk_AppChkBokList.click();
			}

		}

		clickButtonWithText(AdoddleCommonStringPool.FORM_ATTACHMENT_ASSOCIATION_ASSOCIATE);
		clickButtonWithText(AdoddleCommonStringPool.FORM_ATTACHMENT_ASSOCIATION_ASSOCIATEANDCLOSE);
		waitUntilListOfElementIsDisplayed(ProjectFormsTab.css_BetaViewPopCreateFormAssociateFormsFormTitleList);
		List<WebElement> formAssociateList = findElements(ProjectFormsTab.css_BetaViewPopCreateFormAssociateFormsFormTitleList);
		for (WebElement form : formAssociateList) {
			associatedFormList.add(form.getText());
		}
		log.info("associatedFormList :" + associatedFormList);
		collectionDataMap.put("App List:: ", associatedFormList.toString());
	}

	public void clickSaveButtonOnForm() {
		clickElementAndWait(ProjectFormsTab.btn_BetaViewCreateFormAttachmentClipIcon);
		clickElement(ProjectFormsTab.btn_BetaViewCreateFormAttachmentClipIcon);
		clickElementAndWaitForElement(ProjectFormsTab.btn_CreateFormSendButton,
				ProjectFormsTab.ele_BetaViewViewFormTitle);
		waitUntilElementContainsText(ProjectFormsTab.ele_BetaViewViewFormTitle, formTitle);
		closeCurrentWindow();
		switchPreviousWindow(ClearDelegateActions.parentHandle);
		waitForCompletePageLoad();
	}

	public void validateFormAttachmentsAndAssociations() {
		formTitle = Tests_CommonStepDefinitions.formTitle;
		searchForms(formTitle);
		waitUntilElementIsDisplayed(ProjectFormsTab.img_ProjectFormsFirstFormAssociatedDiscussion);
		mouseHoverElement(ProjectFormsTab.img_ProjectFormsFirstFormAssociatedDiscussion);
		waitUntilElementIsDisplayed(ProjectFormsTab.css_RFIAssignedActionsListLink, 240);
		clickElementAndWaitForElement(ProjectFormsTab.lnk_FirstFormDiscussionLink,
				ProjectFormsTab.pop_AttachmentAndAssociationPopup);
		validateAppAttachmentsOnPopUpAttachmentAndAssociations();
		validateAppAssociationsOnPopUpAttachmentAndAssociations();
		validateAppDiscussionsOnPopUpAttachmentAndAssociations();
		validateAppsOnPopUpAttachmentAndAssociations();
		clickElementAndWait(ProjectFormsTab.pop_AttachmentAndAssociationCancel);
		AdoddleScenarioMarkers.createMultipleRepliesScriptFlag = true;

	}

	private void validateAppAttachmentsOnPopUpAttachmentAndAssociations() {
		clickElementAndWait(DiscussionsTab.lnk_PopAttachmentAndAssociationPopupAttachments);
		List<WebElement> associationElements = findElements(DiscussionsTab.css_PopAttachmentAndAssociationAttachmentsFileNames);
		List<String> actualAssociationFileDocRefs = new ArrayList<String>();
		for (WebElement e : associationElements)
			actualAssociationFileDocRefs.add(e.getText());
		AutomationAssert.verifyTrue(actualAssociationFileDocRefs.containsAll(attachedFileList));
	}

	private void validateAppAssociationsOnPopUpAttachmentAndAssociations() {
		clickElementAndWait(DiscussionsTab.lnk_PopAttachmentAndAssociationPopupFiles);
		List<WebElement> associationElements = findElements(DiscussionsTab.css_PopAttachmentAndAssociationFilesDocTitles);
		List<String> actualAssociationFileDocRefs = new ArrayList<String>();
		for (WebElement e : associationElements)
			actualAssociationFileDocRefs.add(e.getText());
		AutomationAssert.verifyTrue(actualAssociationFileDocRefs.containsAll(associatedFileList));
	}

	private void validateAppDiscussionsOnPopUpAttachmentAndAssociations() {
		clickElementAndWait(DiscussionsTab.lnk_PopAttachmentAndAssociationPopupDiscussions);
		List<WebElement> discussionIDElements = findElements(DiscussionsTab.css_PopAttachmentAndAssociationPopupFormAmessageTitles);
		List<String> actualDiscussionIDs = new ArrayList<String>();
		for (WebElement e : discussionIDElements)
			actualDiscussionIDs.add(e.getText());
		AutomationAssert.verifyTrue(actualDiscussionIDs.containsAll(associatedDiscussionList));
	}

	private void validateAppsOnPopUpAttachmentAndAssociations() {
		clickElementAndWait(DiscussionsTab.lnk_PopAttachmentAndAssociationPopupForms);
		List<WebElement> formIDElements = findElements(DiscussionsTab.css_PopAttachmentAndAssociationPopupFormTitles);
		List<String> actualformIDs = new ArrayList<String>();
		for (WebElement e : formIDElements)
			actualformIDs.add(e.getText());
		AutomationAssert.verifyTrue(actualformIDs.containsAll(associatedFormList));
	}

	public void searchAndValidateForm() {
		searchForms(formTitle);
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(
				getElementText(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle), formTitle),
				getElementText(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle).contains(formTitle));
	}

	public void validateUserAssignedActions() {
		mouseHoverElement(DiscussionsTab.css_myactionPlus);
		waitUntilElementIsDisplayed(ProjectFormsTab.css_RFIAssignedActionsListLink);
		formActionList.clear();
		formActionList = findElements(ProjectFormsTab.css_FormsFirstAdditionalActionsList);
		log.info("Action List Size:: " + formActionList.size());
		for (WebElement ele : formActionList) {

			if (ele.getText().contains("ORI001:Assign Status"))
				Assert.assertTrue(ele.getText().equalsIgnoreCase("ORI001:Assign Status"));

			else if (ele.getText().contains("ORI001:Respond"))
				Assert.assertTrue(ele.getText().equalsIgnoreCase("ORI001:Respond"));

			else if (ele.getText().contains("ORI001:For Acknowledgement"))
				Assert.assertTrue(ele.getText().equalsIgnoreCase("ORI001:For Acknowledgement"));

			else if (ele.getText().contains("ORI001:For Action"))
				Assert.assertTrue(ele.getText().equalsIgnoreCase("ORI001:For Action"));

			else if (ele.getText().contains("ORI001:Attach Docs"))
				Assert.assertTrue(ele.getText().equalsIgnoreCase("ORI001:Attach Docs"));

			else if (ele.getText().contains("ORI001:Distribute"))
				Assert.assertTrue(ele.getText().equalsIgnoreCase("ORI001:Distribute"));

			else
				Assert.assertTrue(ele.getText().equalsIgnoreCase("ORI001:For Information"));
		}
	}

	public void openFormInViewer() {
		waitUntilElementIsDisplayed(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle);
		clickElementAndSwitchWindow(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle);
	}

	public void verifyFormIsViewed() {
		waitForCompletePageLoad();
		waitUntilElementContainsText(ProjectFormsTab.ele_BetaViewViewFormTitle, formTitle);
	}

	public void clickActionAndSelectOption(String optName) {
		if (optName.contains("Edit and Distribute")) {
			clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormDetailsActionDropdownButton,
					ProjectFormsTab.lnk_BetaViewFormDetailsActionEditAndDistribute);
			try {
				clickElementAndWait(ProjectFormsTab.lnk_BetaViewFormDetailsActionEditAndDistribute);
			} catch (Throwable t) {
				log.error("ERROR: Failed to click Edit and Distribute button");
			}
		}

		else {

			clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormDetailsActionDropdownButton,
					ProjectFormsTab.lnk_BetaViewFormDetailsActionReply);
			try {
				clickElementAndWait(ProjectFormsTab.lnk_BetaViewFormDetailsActionReply);
			} catch (Throwable e) {

				log.error("ERROR: Failure while performing click operation");
			}
		}
	}

	public void verifyAllAttachmentAndAssociations(String msgID) {
		clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewViewFormAttachmentAssociationButton,
				ProjectFormsTab.drp_BetaViewViewFormAttachmentAssociationMsgID);
		selectByVisibleText(ProjectFormsTab.drp_BetaViewViewFormAttachmentAssociationMsgID, msgID);
		verifyFileAttachements();
		verifyFileAssociations();
	}

	private void verifyFileAttachements() {
		clickElementAndWait(DiscussionsTab.lnk_BetaViewPopAttachmentAndAssociationPopupAttachmentTab);
		List<WebElement> associationElements = findElements(ProjectFormsTab.css_BetaViewViewFormAttachmentAndAssociationTabAttachedFileList);
		List<String> actualAssociationFileDocRefs = new ArrayList<String>();
		for (WebElement e : associationElements)
			actualAssociationFileDocRefs.add(e.getText());

		AutomationAssert.verifyTrue(actualAssociationFileDocRefs.containsAll(attachedFileList));
	}

	private void verifyFileAssociations() {
		clickElementAndWait(DiscussionsTab.lnk_PopAttachmentAndAssociationPopupFilesTab);
		List<WebElement> associationElements = findElements(ProjectFormsTab.css_BetaViewViewFormAttachmentAndAssociationTabAssociateFileList);
		List<String> actualAssociationFileDocRefs = new ArrayList<String>();
		for (WebElement e : associationElements)
			actualAssociationFileDocRefs.add(e.getText());
		AutomationAssert.verifyTrue(actualAssociationFileDocRefs.containsAll(associatedFileList));
	}

	public void validateCompletedActions() {
		clickElementAndWait(ProjectFormsTab.btn_BetaViewFormViewFormDetailsButton);
	}

	public void validateAllFormReplies() {
		waitUntilListOfElementIsDisplayed(ProjectFormsTab.css_BetaViewFormViewHistoryResults);
		List<WebElement> repliesList = findElements(ProjectFormsTab.css_BetaViewFormViewHistoryResults);
		Assert.assertTrue("Expected List Count :: 16 But was " + repliesList.size(), repliesList.size() == 16);
	}

	public void naviagteLeftPanelAttachmentTab() {
		clickElementAndWait(ProjectFormsTab.btn_BetaViewViewFormAttachmentAssociationButton);
		waitUntilListOfElementIsDisplayed(ProjectFormsTab.css_BetaViewViewFormAttachmentAndAssociationTabAttachedFileList);
		Assert.assertTrue("History Results count: "
				+ getCount(ProjectFormsTab.css_BetaViewViewFormAttachmentAndAssociationTabAttachedFileList),
				getCount(ProjectFormsTab.css_BetaViewViewFormAttachmentAndAssociationTabAttachedFileList) > 0);
	}

	public void validateAttachmentsLHPanelRecord() {

		log.info("Covered in Above Definition <naviagteLeftPanelAttachmentTab>");

	}

	public void filterFormAttachmentResult(String filterOption, String msgID) {

		int docTitleIndex = 1;
		if (!getElementText(DiscussionsTab.lnk_BetaViewPopAttachmentAndAssociationPopupTabs).contains(filterOption)) {
			clickElementAndWait(By.xpath(".//ul[contains(@class,'nav-tabs')]//li//a//uib-tab-heading[contains(text(),'"
					+ filterOption + "')]"));
		}
		webAttachAssociateList.clear();
		if (filterOption.contains("Attachments")) {
			selectByVisibleText(ProjectFormsTab.drp_BetaViewViewFormAttachmentAssociationMsgID, msgID);
			webAttachAssociateList = findElements(ProjectFormsTab.css_BetaViewViewFormAttachmentAndAssociationTabAttachedFileList);
		}

		else {
			for (WebElement attribute : findElements(DiscussionsTab.css_BetaViewPopAttachmentAndAssociationPopupTabsType)) {
				if (attribute.getAttribute("title").contains("Doc Title")) {
					log.info("docTitleIndex : " + docTitleIndex);
					break;
				}
				docTitleIndex++;
			}

			for (WebElement docTitle : findElements(By
					.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//div[@class='ibox']//div[contains(@class,'active')]//div[@class='gbody']//ul//li["
							+ docTitleIndex + "]//span"))) {
				webAttachAssociateList.add(docTitle);
				log.info("webAssociations Documents::  " + docTitle.getText());
			}
		}
	}

	public void valdiateAttachmentFilteredResultCount(String rCount, String msgID) {

		int listingRecordCount;
		int expectedCount = Integer.parseInt(rCount);
		if (msgID.contains("ORI001") || msgID.contains("FWD001") || msgID.contains("RES001")) {
			listingRecordCount = getCount(ProjectFormsTab.css_BetaViewViewFormAttachmentAndAssociationTabAttachedFileList);
			log.info("RecordCountForMessageID_" + msgID + "::" + listingRecordCount);
			Assert.assertTrue("ExpectedRecordCount:: " + expectedCount + " 	But found:: " + listingRecordCount,
					listingRecordCount == expectedCount);
		}

		else if (rCount.contains("9") && msgID.contains("All")) {
			listingRecordCount = getCount(ProjectFormsTab.css_BetaViewViewFormAttachmentAndAssociationTabAttachedFileList);
			log.info(" ExpectedRecordCount:: " + listingRecordCount);
			Assert.assertTrue("ExpectedRecordCount:: " + expectedCount + " But found:: " + listingRecordCount,
					listingRecordCount == expectedCount);
		}

		else {
			listingRecordCount = getCount(ProjectFormsTab.css_BetaViewViewFormAttachmentAndAssociationTabAssociateFileList);
			log.info(" ExpectedRecordCount:: " + listingRecordCount);
			Assert.assertTrue("ExpectedRecordCount:: " + expectedCount + " But found:: " + listingRecordCount,
					listingRecordCount == expectedCount);
		}
	}

	public void selectAssociationsAndAttachmentsClickOnDownload() {
		clickElementAndWait(ProjectFormsTab.chk_BetaViewViewFormAttachmentAssociationMultiCheckbox);
		clickElementAndWait(ProjectFormsTab.btn_BetaViewViewFormAttachmentAssociationDownloadButton);
	}

	/* Download All Attachments and Associations */

	/****** Download Attachments & Associations ******/
	private List<WebElement> webAttachAssociateList = new ArrayList<WebElement>();
	private int i;
	final private String outputZipAttachmentFolder = nodeIP + resourceUtils.getRemoteFileDownloadPath().trim()
			+ "AutomationAttachmentZipFile".trim() + epoch.trim();
	final private String outputZipAssociationFolder = nodeIP + resourceUtils.getRemoteFileDownloadPath().trim()
			+ "AutomationAssociationZipFile".trim() + epoch.trim();
	final private String inputZipAttachmentFile = outputZipAttachmentFolder + ".zip";
	final private String inputZipAssociationFile = outputZipAssociationFolder + ".zip";
	final private List<String> lstAttachFileValLocal = new ArrayList<String>();
	final private List<String> lstAssociateFileValLocal = new ArrayList<String>();

	public void selectFilesAndClickOnDownload(String linkTab) {

		if (linkTab.equalsIgnoreCase("Attachments")) {
			mouseHoverElement(ProjectFormsTab.img_ProjectFormsFirstFormAssociatedDiscussion);
			waitUntilElementIsDisplayed(ProjectFormsTab.css_RFIAssignedActionsListLink);
			clickElementAndWaitForElement(ProjectFormsTab.lnk_FirstFormDiscussionLink,
					ProjectFormsTab.pop_AttachmentAndAssociationPopup);
		}
		clickElementAndWait(By.xpath(".//div[@id='pageLayoutHeader']//div[@id]//a[contains(text(),'" + linkTab + "')]"));

		if (getElementText(ModelsTab.lnk_PopAttachmentsAndAssociationsActiveTab).contains("Attachments")) {
			webAttachAssociateList = findElements(ModelsTab.css_PopAttachmentsAndAssociationsAttachmentsTabFileNameList);
		} else if (getElementText(ModelsTab.lnk_PopAttachmentsAndAssociationsActiveTab).contains("Files")) {
			webAttachAssociateList.clear();
			List<WebElement> fileTypeImgList;
			fileTypeImgList = findElements(ModelsTab.css_PopAttachmentsAndAssociationsFilesTabFileTypeImageList);
			i = 0;
			for (WebElement fileTypeImg : fileTypeImgList) {
				if (!fileTypeImg.getAttribute("src").contains("_fp.gif")
						&& !fileTypeImg.getAttribute("src").contains("placeholder.gif")) {
					webAttachAssociateList.add(findElement(By
							.xpath(".//div[contains(@style,'display: block')]//div[@index='" + i
									+ "']//div[contains(@class,'title')]")));
				}
				i++;
			}
		}
		clickElementAndWaitForElement(ModelsTab.chk_PopAttachmentAssociationCheckAllCheckbox,
				ModelsTab.lnk_PopDownloadLink);
		clickElement(ModelsTab.lnk_PopDownloadLink);
		selectCheckListAndClickOnDownload(linkTab);
		clickElementAndWait(ModelsTab.btn_PopDownloadButton);
	}

	private void selectCheckListAndClickOnDownload(String linkTab) {
		if (!linkTab.contains("Attachments")) {
			List<WebElement> cbList = findElements(ModelsTab.css_AppendNameStringCheckList);
			for (WebElement e : cbList) {
				if (e.isDisplayed() && !e.getAttribute("id").equalsIgnoreCase("isFolderStructure") && !e.isSelected())
					e.click();
			}
		}

	}

	public void downloadZipDocumentIntoLocal(String linkTab) throws InterruptedException, IOException {
		waitUntilElementIsInvisible(ModelsTab.pop_BatchFiles);
		if (!linkTab.contains("Attachments")) {
			// autoItUtils.downloadAutoIt(inputZipAssociationFile, nodeIP);
			sysUtils.waitForMultipleFileDownload(nodeIP, inputZipAssociationFile);
		} else {
			// autoItUtils.downloadAutoIt(inputZipAttachmentFile, nodeIP);
			sysUtils.waitForMultipleFileDownload(nodeIP, inputZipAttachmentFile);
		}
	}

	public void zipIntoUnZip(String linkTab) {
		if (!linkTab.contains("Attachments"))
			sysUtils.unZipFile(inputZipAssociationFile, sysUtils.createDirectory(outputZipAssociationFolder));
		else
			sysUtils.unZipFile(inputZipAttachmentFile, sysUtils.createDirectory(outputZipAttachmentFolder));
	}

	public void getFileNamesFromLocalFolder(String linkTab) {
		File folder;
		File[] listOfFiles;

		if (!linkTab.contains("Attachments")) {
			folder = new File(outputZipAssociationFolder);
			listOfFiles = folder.listFiles();
		} else {
			folder = new File(outputZipAttachmentFolder);
			listOfFiles = folder.listFiles();
		}
		for (i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				if (!linkTab.contains("Attachments")) {
					lstAssociateFileValLocal.add(listOfFiles[i].getName().substring(0,
							listOfFiles[i].getName().lastIndexOf(".")));
				} else {
					lstAttachFileValLocal.add(listOfFiles[i].getName().substring(0,
							listOfFiles[i].getName().lastIndexOf(".")));
				}
			}
		}
		log.info("lstAttachFileValLocal :" + lstAttachFileValLocal);
		log.info("lstAssociateFileValLocal :" + lstAssociateFileValLocal);
	}

	public void verifyFilesNameIntoSystem(String linkTab) {
		boolean flag = false;
		List<String> localFileList = new ArrayList<String>();
		List<String> webAssociationList = new ArrayList<String>();

		if (!linkTab.contains("Attachments"))
			localFileList.addAll(lstAssociateFileValLocal);
		else
			localFileList.addAll(lstAttachFileValLocal);
		collectionDataMap.put("Downloaded Associations", localFileList.toString());

		for (WebElement e : webAttachAssociateList)
			webAssociationList.add(e.getText().split("\\.")[0]);

		if (webAttachAssociateList.size() == localFileList.size()) {
			for (WebElement webFile : webAttachAssociateList) {
				String webfileName = webFile.getText().split("\\.")[0];
				for (String localFile : localFileList) {
					if (localFile.contains(webfileName)) {
						log.error("Web File Verified :" + webfileName);
						log.error("Local File Verified :" + localFile);
						flag = true;
						break;
					} else
						flag = false;
				}
				AutomationAssert.verifyTrue("ERROR: Failure while validating Downloaded Documents", flag);
			}
		}

		else {
			collectionDataMap.put("Web List Associations", webAssociationList.toString());
			AutomationAssert.verifyTrue(webAttachAssociateList.size() + "!=" + localFileList.size(), false);
		}

	}

}