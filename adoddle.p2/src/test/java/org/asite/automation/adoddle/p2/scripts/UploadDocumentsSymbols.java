package org.asite.automation.adoddle.p2.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.asite.automation.CommonLocators.AdoddleDiscussionsLocators.DiscussionsTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleModelsLocators.ModelsTab;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.JavaUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class UploadDocumentsSymbols extends AdoddleCommonAppMethods {

	private String searchEpoch1 = javaUtils.getRandomString(5) + JavaUtils.getRandomNumber(10);
	private String searchEpoch2 = javaUtils.getRandomString(5) + JavaUtils.getRandomNumber(10);
	private String searchEpoch3 = javaUtils.getRandomString(5) + JavaUtils.getRandomNumber(10);

	private String docRef1 = " File!^@$&()+-{}=[]`.._DocRef1_ " + searchEpoch1 + " ";
	private String docRef2 = " File!^@$&()+-{}=[]`.._DocRef2_ " + searchEpoch2 + " ";
	private String docRef3 = " File!^@$&()+-{}=[]`.._DocRef3_ " + searchEpoch3 + " ";

	private String docTitle1 = "Doc~`!@$%^&*();:|\\[]{}-+=,/?._Title" + searchEpoch1;
	private String docTitle2 = "Doc~`!@$%^&*();:|\\[]{}-+=,/?._Title" + searchEpoch1;
	private String docTitle3 = "Doc~`!@$%^&*();:|\\[]{}-+=,/?._Title" + searchEpoch3;

	private List<String> multidocRefList = new ArrayList<String>(Arrays.asList(docRef1, docRef2, docRef3));
	private List<String> multidocTitleList = new ArrayList<String>(Arrays.asList(docTitle1, docTitle2, docTitle3));

	private List<String> docStatusList = new ArrayList<String>(Arrays.asList("For!@$^&()_+-{}=[]`Information.",
			"ForStatus!@$^&()_+-{}=[]`Change.", "For!@$^&()_+-{}=[]`Action."));

	private List<String> docPoiList = new ArrayList<String>(Arrays.asList("For!@$^&()_+-{}=[]`Review.",
			"For!@$^&()_+-{}=[]`Training.", "For!@$^&()_+-{}=[]`Acknowledgement."));

	private List<String> distributionList = new ArrayList<String>(Arrays.asList(
			"AutoTest!@$^&()_+-{}=[]`DistributionGroup.", "AutoTest!^@$&()+-{}=[]`._Role.",
			"AutoTest- ! @ $ ^ & ( ) - = + { } , . ] `Org", "TC Bloggs"));
	private List<String> actionsList = new ArrayList<String>(Arrays.asList("For Comment", "For Status Change",
			"For Distribution"));

	private List<String> multiDocmentList = new ArrayList<String>();
	private List<String> revisionFileList = new ArrayList<String>();
	private List<String> symbolsAttachmentsList = new ArrayList<String>();
	private List<String> associatedFileList = new ArrayList<String>();
	private List<String> attachedFileList = new ArrayList<String>();
	private List<String> fileLists = new ArrayList<String>();
	private List<String> searchEpochList = new ArrayList<String>(
			Arrays.asList(searchEpoch1, searchEpoch2, searchEpoch3));
	private String commentTitle, formSubject, formDescription, extension;
	private String currentWindowHandle = null;

	public void focusFolderAndWorkspace(String workspace, String folder) {
		clickElementWithText(workspace);
		clickElementWithText(folder);
	}

	public void selectMultipleFilesAndUpload() throws InterruptedException, IOException {

		multiDocmentList = uploadDocuments(null, 3, multidocRefList, multidocTitleList, false, 1, docPoiList,
				docStatusList, distributionList, actionsList);
		for (String file : multiDocmentList)
			fileLists.add(strUtils.extractFileNameString(file));
		collectionDataMap.put("Uplaoded fileList:: ", fileLists.toString());
		collectionDataMap.put("DocRef List:: ", multidocRefList.toString());
		collectionDataMap.put("DocTitle List:: ", multidocTitleList.toString());
	}

	public void publishDocumentRevisions() throws InterruptedException, IOException {

		int index = 0;
		revisionFileList = uploadDocuments(multiDocmentList, 3, null, null, false, 2, docPoiList, docStatusList,
				distributionList, null);

		for (String file : revisionFileList) {

			searchFiles(strUtils.extractFileNameString(file));
			Assert.assertTrue("Failure while validating FileDocRef", getElementText(FilesTab.lnk_FilesListingRowDocRef)
					.contains(multidocRefList.get(index).trim()));
			Assert.assertTrue("Failure while validation docTitle",
					getElementText(FilesTab.ele_FilesTabFirstFileDocTitle).contains(multidocTitleList.get(index)));
			Assert.assertTrue("Failure while validation docStatus",
					getElementText(FilesTab.lnk_FilesTabFirstFileStatus).contains(docStatusList.get(index)));
			Assert.assertTrue("Failure while validation docPOI", getElementText(FilesTab.ele_FilesTabFirstFilePOI)
					.contains(docPoiList.get(index)));

			index += 1;

		}

	}

	public void createMultiDocComment(String comment) throws InterruptedException {
		String expectedText = "New";
		findElement(FilesTab.txt_FilesFilterInput).clear();
		sendKeys(FilesTab.txt_FilesFilterInput, Keys.ENTER);
		waitForCompletePageLoad();
		waitUntilListOfElementIsDisplayed(FilesTab.css_FilesListingRecords);
		List<WebElement> existingfileLists = findElements(FilesTab.css_FilesListingRecords);
		for (WebElement file : existingfileLists) {
			String fileName = file.findElement(FilesTab.lnk_FilesListingRowFileName).getText();
			if (fileLists.contains(fileName))
				file.findElement(FilesTab.chk_FilesListingRowchecbox).click();
		}
		contextClick(FilesTab.lnk_DocListingFirstDocRef);
		mouseHoverAndClickElement(FilesTab.opt_FileContextClickNew, FilesTab.opt_FileContextClickStartDiscussion);
		verifyNewPopup(expectedText);
		verifyUserExistsInToField();
		commentTitle = comment + dateUtils.getEpoch();
		log.info("Comment Title::" + commentTitle);
		collectionDataMap.put("CommentTitle:: ", commentTitle);
		sendKeys(FilesTab.txt_NewDiscussionTitleInput, commentTitle);
		sendKeys(FilesTab.txt_NewDiscussionDescInput, ResourceHandler.loadProperty("special.char.test.string"));
		waitUntilElementIsDisplayed(FilesTab.btn_NewDiscussionSubmit);
		clickElementAndWait(FilesTab.btn_NewDiscussionSubmit);
	}

	public void searchDocumentAndClickOnTitle() {
		searchFiles(searchEpoch2);
		currentWindowHandle = clickElementAndSwitchWindow(FilesTab.lnk_DocListingFirstFileName);
	}

	public void clickCreateCommentLink(String buttonType) {
		clickElementAndWaitForElement(FilesTab.lnk_BetaViewStartNewDiscussion,
				GlobalPageElements.txt_BetaViewPopCreateDiscussionDistributeTo);
	}

	public void verifyaMessageForm() {
		log.info("Covered in previous Definition <clickCreateCommentLink>");
	}

	public void clickOnCreateFormHeaderButtonOptions(String buttonType) {
		clickElementAndWait(By.xpath(".//button[@title='" + buttonType + "']"));
	}

	public void enterCommentMandatoryAttributes(String commentTitle) {
		sendKeys(GlobalPageElements.txt_BetaViewPopCreateDiscussionTitle, commentTitle);
		collectionDataMap.put("FileViewer comment title:: ", commentTitle);
		sendKeys(GlobalPageElements.txt_BetaViewPopCreateDiscussionTextarea,
				ResourceHandler.loadProperty("special.char.test.string"));
	}

	public void clickOnSelectFilesAndAttachDocuments() {
		String createFile1, createFile2;
		sysUtils.authenticateRemoteMachine(nodeIP);
		createFile1 = sysUtils.createRemotePdfFile(
				nodeIP + ResourceHandler.loadProperty("auto.create.specialCharacter.testdata.filepath")
						+ dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
		createFile2 = sysUtils.createRemotePdfFile(
				nodeIP + ResourceHandler.loadProperty("auto.create.specialCharacter.testdata.filepath")
						+ dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();

		symbolsAttachmentsList = sysUtils.getFileList(createFile1 + "," + createFile2);
		log.info("FileList:: " + symbolsAttachmentsList.toString());
		uploadFileUsingKeys(ProjectFormsTab.btn_BetaViewCreateFormAttachmentsSelectFiles, symbolsAttachmentsList);
		waitUntilListOfElementIsDisplayed(ProjectFormsTab.css_BetaViewPopCreateFormAttachmentsFileNameList);
		List<WebElement> fileAttachList = findElements(ProjectFormsTab.css_BetaViewPopCreateFormAttachmentsFileNameList);
		for (WebElement attachFile : fileAttachList) {
			attachedFileList.add(attachFile.getText());
		}
		log.info("attached files list :" + attachedFileList);
		collectionDataMap.put("attachedFileList", attachedFileList.toString());
		clickElementAndWait(ModelsTab.btn_PopModelCommentAttachmentsAttachButton);

	}

	public void associateCommentDocuments(String folder, String workspace) {
		clickElementWithText(workspace);
		clickElementWithText(folder);
		waitUntilElementContainsText(ProjectFormsTab.lbl_BetaViewCreateFormFilesFolderLabel, folder);
		List<WebElement> fileListingCheckBoxes = findElements(ProjectFormsTab.chk_BetaViewCreateFormFilesListingRowchecbox);
		for (int counter = 0; counter < 3; counter++) {
			if (!isSelected(fileListingCheckBoxes.get(counter)))
				clickElementAndWait(fileListingCheckBoxes.get(counter));
		}
		clickButtonWithText(AdoddleCommonStringPool.FORM_ATTACHMENT_ASSOCIATION_ASSOCIATEANDCLOSE);
		waitUntilListOfElementIsDisplayed(ProjectFormsTab.css_BetaViewPopCreateFormAssociateDocsDocTitleList);
		List<WebElement> fileAssociateList = findElements(ProjectFormsTab.css_BetaViewPopCreateFormAssociateDocsDocTitleList);
		for (WebElement file : fileAssociateList) {
			associatedFileList.add(file.getText());
		}
		collectionDataMap.put("associatedFileList", associatedFileList.toString());
		log.info("associatedFileList :" + associatedFileList);

	}

	public void saveCommentAndCloseWindow() {
		clickElementAndWait(FilesTab.btn_BetaFileViewNewDiscussionSubmit);
	}

	public void verifySuccessMessage() {
		try {
			verifyElementText(FilesTab.lbl_FileAddCommentSuccessMsg,
					AdoddleCommonStringPool.MSG_COMMENT_CREATED_SUCCESS, 20);

		} catch (Throwable t) {
			log.error("Success Message could not be verified");
		}

		closeCurrentWindow();
		switchPreviousWindow(currentWindowHandle);

	}

	public void verifyNewPopup(String expectedText) {
		waitUntilElementIsDisplayed(GlobalPageElements.pop_PopUpElement);
		Assert.assertTrue(isDisplayed(FilesTab.pop_StartNewDiscussion));
		verifyElementText(FilesTab.pop_StartNewDiscussionHeader, expectedText);
	}

	public void verifyUserExistsInToField() throws InterruptedException {
		try {
			Assert.assertTrue(getCount(FilesTab.css_NewDiscussionToUserCount) > 0);
		} catch (Throwable t) {
			sendKeys(FilesTab.txt_NewDiscussionToUserField, ResourceHandler.loadProperty("test.user.tc.bloggs.name"));
			sendKeys(FilesTab.txt_NewDiscussionToUserField, Keys.TAB);
			Assert.assertTrue(getCount(FilesTab.css_NewDiscussionToUserCount) > 0);
		}
	}

	public void validateCommentInListing(String title) throws InterruptedException {
		clickElementAndWaitForElement(GlobalPageElements.drp_GlobalListingCreateFilter,
				GlobalPageElements.txt_GlobalListingCreateFilterSearch);
		sendKeys(GlobalPageElements.txt_GlobalListingCreateFilterSearch, AdoddleCommonStringPool.FILTER_COMMENT_TITLE);
		clickElementAndWaitForElement(GlobalPageElements.chk_GlobalListingCreateFilterFilteredFirstCheckbox,
				FilesTab.lbl_DocListingCreatedFirstFilterCommentTitle);
		findElement(FilesTab.lbl_DocListingCreatedFirstFilterCommentTitle).sendKeys(commentTitle);
		log.info("Searching Adoddle file(s) with commentTitle matching with: " + commentTitle);
		sendKeys(FilesTab.lbl_DocListingCreatedFirstFilterCommentTitle, Keys.ENTER);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitForCompletePageLoad();
		Assert.assertTrue(getElementText(DiscussionsTab.lnk_DiscussionFirstCommentTitle).equalsIgnoreCase(commentTitle));

	}

	public void createAppAndValidate(String formTitle) throws InterruptedException, IOException {

		formSubject = formTitle + epoch;
		collectionDataMap.put("AppTitle:: ", formSubject);
		formDescription = javaUtils.getRandomString(30);
		waitUntilElementIsClickable(ProjectFormsTab.txt_BetaViewCreateRFIFormInputToField);

		// if
		// (ResourceHandler.loadProperty("app.view.beta.flag").contains("true"))
		// {
		sendKeys(ProjectFormsTab.txt_BetaViewCreateRFIFormInputToField,
				ResourceHandler.loadProperty("test.user.tc.bloggs.name"));
		waitUntilElementIsDisplayed(By
				.xpath(".//div[@id='distInputTo']//input[@type='checkbox']//following::span[contains(text(),'"
						+ ResourceHandler.loadProperty("test.user.tc.bloggs.name") + "')]"));
		findElement(ProjectFormsTab.txt_BetaViewCreateRFIFormInputToField).sendKeys(
				Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
		/*
		 * }
		 * 
		 * else { sendKeys(ProjectFormsTab.txt_CreateRFIFormInputToField,
		 * ResourceHandler.loadProperty("test.user.tc.bloggs.name"));
		 * sendKeys(ProjectFormsTab.txt_CreateRFIFormInputToField, Keys.TAB);
		 * 
		 * }
		 */
		sendKeys(ProjectFormsTab.txt_CreateRFIFormSubject, formSubject);
		sendKeys(ProjectFormsTab.txt_CreateRFIFormSubject, Keys.TAB);
		sendKeys(ProjectFormsTab.txt_CreateRFIFormDescription, formDescription);
		attachFilesToForm();
		waitUntilElementIsClickable(ProjectFormsTab.btn_CreateFormSendButton);
		clickElement(ProjectFormsTab.btn_CreateFormSendButton);
		switchDefault();
		verifyFormSuccessMessage();
		searchForms(formSubject.split("~")[1]); // NOODLE-78854
		waitUntilElementIsDisplayed(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle);
		Assert.assertTrue("Form Title:",
				getElementText(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle).contains(formSubject));
	}

	public void attachFilesToForm() throws IOException, InterruptedException {

		// if
		// (ResourceHandler.loadProperty("app.view.beta.flag").contains("true"))
		clickElementAndWait(ProjectFormsTab.btn_BetaViewCreateFormAttachmentClipIcon);
		/*
		 * else clickElementAndWait(ProjectFormsTab.btn_CreateFormAttachment);
		 */
		// if
		// (ResourceHandler.loadProperty("app.view.beta.flag").contains("true"))
		// {
		uploadFileUsingKeys(ProjectFormsTab.btn_BetaViewCreateFormAttachmentsSelectFiles, symbolsAttachmentsList);
		clickOnAttach();
		/*
		 * } else {
		 * uploadFileUsingKeys(ProjectFormsTab.btn_CreateFormAttachmentsSelectFiles
		 * , fileList);
		 * clickElementAndWait(ModelsTab.btn_PopModelCommentAttachmentsAttachButton
		 * ); }
		 */

	}

	public void clickOnAttach() {
		clickElementAndWait(ModelsTab.btn_PopModelCommentAttachmentsAttachButton);
		try {
			waitUntilElementIsDisplayed(ProjectFormsTab.lbl_CreateFormUploadSucessMsg);
			Assert.assertTrue(getElementText(ProjectFormsTab.lbl_CreateFormUploadSucessMsg)
					.contains("Upload Completed"));
		} catch (Exception e) {
			log.info("Failure while attachment operation");
		}
	}

	public void verifyFormSuccessMessage() {
		isDisplayed(ProjectFormsTab.txt_FormListingFormSearchInput);

		try {
			waitUntilElementIsDisplayed(DiscussionsTab.lbl_FormAddSuccessMsg);
			verifyElementText(DiscussionsTab.lbl_FormAddSuccessMsg, AdoddleCommonStringPool.FORM_SUCCESS_MESSAGE, 20);
		} catch (Throwable t) {
			log.error(t.getStackTrace());
			log.error("Success Message could not be verified");
		}
		waitForCompletePageLoad();
	}

	public void validateDocumentsWithAttiributes(String validationScenario) {

		int index = 0;

		for (String epoch : searchEpochList) {

			searchFiles(epoch);
			Assert.assertTrue("Failure while validation docRef " + getElementText(FilesTab.lnk_FilesListingRowDocRef),
					getElementText(FilesTab.lnk_FilesListingRowDocRef).contains(multidocRefList.get(index).trim()));
			Assert.assertTrue("Failure while validation docTitle",
					getElementText(FilesTab.ele_FilesTabFirstFileDocTitle).contains(multidocTitleList.get(index)));
			Assert.assertTrue("Failure while validation docStatus",
					getElementText(FilesTab.lnk_FilesTabFirstFileStatus).contains(docStatusList.get(index)));
			Assert.assertTrue("Failure while validation docPOI", getElementText(FilesTab.ele_FilesTabFirstFilePOI)
					.contains(docPoiList.get(index)));
			Assert.assertTrue(
					"Expected Workflow Status: " + AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING,
					getElementText(FilesTab.lnk_workflowStatus).contains(
							AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING));
			Assert.assertTrue("Failure while validation",
					getElementText(FilesTab.ele_workflowStage).contains(AdoddleCommonStringPool.ACTION_FOR_ACTION));
			if (validationScenario.contains("postActionValidation"))
				Assert.assertTrue(
						"Failure while validation",
						getElementText(FilesTab.lnk_FilesFirstAction).contains(
								AdoddleCommonStringPool.ACTION_FOR_COMMENT));
			else
				log.info("Action Distributing User");

			index += 1;

		}

	}

	public void downloadPublishDocumentRevisions() throws InterruptedException {
		for (String file : revisionFileList) {
			searchFiles(strUtils.extractFileNameString(file));
			String fileName = getElementText(FilesTab.lnk_FileName);
			extension = ".".concat(fileName.substring(fileName.lastIndexOf(".") + 1));
			clickElementAndWait(FilesTab.lnk_FilesListingFirstFileTypeIcon);
			collectionDataMap.put("Download File", fileName);
			String downloadPath = nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim() + epoch
					+ extension;
			sysUtils.waitForSingleDirectFileDownload(nodeIP, fileName, downloadPath);
			collectionDataMap.put("Downloaded File", downloadPath.toString());

		}

	}

	public void deactivateAllFiles() throws IOException {

		try {
			log.info("File Size:::" + fileLists.size());
			for (String file : fileLists) {

				deactivateFile(file);
			}

		} catch (Throwable t) {
			log.error("failure in deactivating file");
		}
	}

}