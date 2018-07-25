package org.asite.automation.adoddle.p2.scripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleModelsLocators.ModelsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;

public class PublishPDFviaWorkflow extends AdoddleCommonAppMethods {

	private String fileName;
	private File downloadFilePath;
	private List<String> systemMultiDocumentsList = new ArrayList<String>();
	public static List<String> uploadMultiDocumentsList = new ArrayList<String>();

	public void focusSubFolderInFolder(String subFolder, String folder) {
		clickElementWithText(folder);
		clickElementWithText(subFolder);

	}

	public void uploadDocument(String uploadDocument) throws IOException, InterruptedException {

		String fileExtension;

		sysUtils.authenticateRemoteMachine(nodeIP);
		systemMultiDocumentsList = sysUtils.getFileListOfSystemFolder(nodeIP
				+ ResourceHandler.loadProperty("print.multi.document.testdata.filepath"));
		uploadMultiDocumentsList.clear();
		log.info("Documents Name" + systemMultiDocumentsList);
		for (String str : systemMultiDocumentsList) {

			fileExtension = "." + str.split("\\.")[1];
			sysUtils.authenticateRemoteMachine(nodeIP);
			log.info("String " + str);
			log.info("Renamed Document Name: " + strUtils.extractFileNameString(str));

			if (strUtils.extractFileNameString(str).contains(uploadDocument)) {

				fileName = copyAndRenameMultipleDocuments(
						nodeIP + ResourceHandler.loadProperty("print.multi.document.testdata.filepath") + str, nodeIP
								+ ResourceHandler.loadProperty("remote.copy.file.folderpath"), fileExtension);

				log.info("String " + fileName);
				uploadMultiDocumentsList.add(fileName);

			}

			else
				log.info("Expected Document not found in Local Directory");
		}

		log.info("Documents Name" + uploadMultiDocumentsList);
		uploadMultipleFilesUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, uploadMultiDocumentsList);

	}

	public void enterMandatoryAttributesAndUploadSecondaryFile(String secondaryFile) {

		clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
		sendKeys(FilesTab.txt_PopUploadRevision, "1");
		selectByIndex(FilesTab.drp_PopUploadPurposeOfIssue, 1);
		selectByIndex(FilesTab.drp_PopUploadStatus, 1);
		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
		findElement(FilesTab.btn_PopUploadAttachSecondaryFile).sendKeys(
				ResourceHandler.loadProperty("secondary.file.path"));

	}

	public void valdiateDocumentInListing(String docRef) {

		searchFiles(strUtils.extractFileNameString(uploadMultiDocumentsList.get(0)));
		Assert.assertTrue(
				"Expected DocRef: " + docRef,
				getElementText(FilesTab.lnk_FilesListingRowDocRef).contains(
						strUtils.extractFileNameString(fileName).split("\\.")[0]));
		Assert.assertTrue("Invalid Document Revision",
				getElementText(FilesTab.lnk_FileListingFirstVersion).contains("1"));

	}

	public void distributeDocument(String primaryUser, String secondaryUser) throws InterruptedException {

		List<String> usersList = new ArrayList<String>(Arrays.asList(primaryUser, secondaryUser));
		List<String> usersActionList = new ArrayList<String>(
				Arrays.asList(AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE));

		contextClick(FilesTab.lnk_DocListingFirstDocRef);
		mouseHoverAndClickElement(FilesTab.opt_FileContextClickShare, FilesTab.opt_FileContextClickShareDistribute);
		assignFileActionsToMultipleUsers(usersList, usersActionList);
		sendKeys(FilesTab.txt_PopFilesActionForDistributeSubject, javaUtils.getRandomString(15));
		clickElementAndWait(FilesTab.btn_PopFilesActionForDistribute);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();

	}

	public void createMultipleDiscussionsOnDocument() throws InterruptedException {

		String expectedText = "New";
		String commentTitle1 = "Architectural Correction Design";
		String commentTitle2 = "Delayed Architectural Correction Design";
		String commentTitle3 = "Review Architectural Correction Design";
		String commentTitle4 = "Final Architectural Design";
		String commentAttachmentFilePath1 = ResourceHandler.loadProperty("attachment.file.path1");
		String commentAttachmentFilePath2 = ResourceHandler.loadProperty("attachment.file.path2");

		List<String> commentTitleList = new ArrayList<String>(Arrays.asList(commentTitle1, commentTitle2,
				commentTitle3, commentTitle4));

		for (String title : commentTitleList) {

			searchFiles(strUtils.extractFileNameString(uploadMultiDocumentsList.get(0)));
			contextClick(FilesTab.lnk_DocListingFirstDocRef);
			mouseHoverAndClickElement(FilesTab.opt_FileContextClickNew, FilesTab.opt_FileContextClickStartDiscussion);
			verifyNewPopup(expectedText);
			verifyUserExistsInToField();

			if (title.equalsIgnoreCase("Architectural Correction Design")
					|| title.equalsIgnoreCase("Delayed Architectural Correction Design"))
				enterMandatoryCommentDetails(title, null);
			else if (title.equalsIgnoreCase("Review Architectural Correction Design"))
				enterMandatoryCommentDetails(title, commentAttachmentFilePath1);
			else
				enterMandatoryCommentDetails(title, commentAttachmentFilePath2);

			waitUntilElementIsDisplayed(FilesTab.btn_NewDiscussionSubmit);
			clickElementAndWait(FilesTab.btn_NewDiscussionSubmit);
			waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
			waitForCompletePageLoad();

		}

	}

	public void verifyNewPopup(String expectedText) {

		waitUntilElementIsDisplayed(GlobalPageElements.pop_PopUpElement);
		Assert.assertTrue(isDisplayed(FilesTab.pop_StartNewDiscussion));
		verifyElementText(FilesTab.pop_StartNewDiscussionHeader, expectedText);
	}

	public void verifyUserExistsInToField() throws InterruptedException {
		try {

			if (getCount(FilesTab.css_NewDiscussionToUserCount) > 0) {

				waitUntilListOfElementIsDisplayed(FilesTab.css_NewDiscussionToUsersCloselink);
				List<WebElement> existingUserList = findElements(FilesTab.css_NewDiscussionToUsersCloselink);
				for (WebElement closeIcon : existingUserList)
					closeIcon.click();
			}
			Assert.assertTrue(getCount(FilesTab.css_NewDiscussionToUserCount) > 0);
		} catch (Throwable t) {
			sendKeys(FilesTab.txt_NewDiscussionToUserField, ResourceHandler.loadProperty("test.user.tc.bloggs.name"));
			sendKeys(FilesTab.txt_NewDiscussionToUserField, Keys.TAB);
			Assert.assertTrue(getCount(FilesTab.css_NewDiscussionToUserCount) > 0);
		}
	}

	public void enterMandatoryCommentDetails(String commentTitle, String attachmentFilePath)
			throws InterruptedException {

		String greetingMessage = "Hello Automation,";
		String endMessage1 = "Warm & Regards,";
		String endMessage2 = "Automation Team";
		String commentDesc1 = "Please Ensure and review following correction i have drawn on Architectural Design Layout as mentioned Below.";
		String commentDesc2 = "We have been expecting response in time-frame as Response is delayed so we are escalating this issue to your management.";
		String commentDesc3 = "We have reviewed your Design and Advised following correction as mentioned in attachments.";
		String commentDesc4 = "We have reviewed your Design and approved the same for Implementation Phase.";

		waitUntilElementIsDisplayed(FilesTab.txt_NewDiscussionTitleInput);
		waitUntilElementIsDisplayed(FilesTab.txt_NewDiscussionDescInput);
		findElement(FilesTab.txt_NewDiscussionTitleInput).sendKeys(commentTitle);
		findElement(FilesTab.txt_NewDiscussionDescInput).sendKeys(greetingMessage);
		findElement(FilesTab.txt_NewDiscussionDescInput).sendKeys(Keys.ENTER);
		findElement(FilesTab.txt_NewDiscussionDescInput).sendKeys(Keys.ENTER);
		findElement(FilesTab.txt_NewDiscussionDescInput).sendKeys(Keys.ENTER);
		findElement(FilesTab.txt_NewDiscussionDescInput).sendKeys(Keys.ENTER);

		if (commentTitle.equalsIgnoreCase("Architectural Correction Design")) {

			findElement(FilesTab.txt_NewDiscussionDescInput).sendKeys(commentDesc1);
			findElement(FilesTab.txt_NewDiscussionDescInput).sendKeys(Keys.ENTER);
			findElement(FilesTab.txt_NewDiscussionDescInput).sendKeys("1.Wood flooring");
			findElement(FilesTab.txt_NewDiscussionDescInput).sendKeys(Keys.ENTER);
			findElement(FilesTab.txt_NewDiscussionDescInput).sendKeys("2.Raised panel door");
			findElement(FilesTab.txt_NewDiscussionDescInput).sendKeys(Keys.ENTER);
			findElement(FilesTab.txt_NewDiscussionDescInput).sendKeys("3.Residential Electrical Term Connector");

		}

		else if (commentTitle.equalsIgnoreCase("Delayed Architectural Correction Design"))
			findElement(FilesTab.txt_NewDiscussionDescInput).sendKeys(commentDesc2);

		else {

			if (commentTitle.equalsIgnoreCase("Review Architectural Correction Design"))
				findElement(FilesTab.txt_NewDiscussionDescInput).sendKeys(commentDesc3);
			else
				findElement(FilesTab.txt_NewDiscussionDescInput).sendKeys(commentDesc4);

			clickOnSelectFilesAndAttachDocuments(attachmentFilePath);
		}

		findElement(FilesTab.txt_NewDiscussionDescInput).sendKeys(Keys.ENTER);
		findElement(FilesTab.txt_NewDiscussionDescInput).sendKeys(Keys.ENTER);
		findElement(FilesTab.txt_NewDiscussionDescInput).sendKeys(Keys.ENTER);
		findElement(FilesTab.txt_NewDiscussionDescInput).sendKeys(endMessage1);
		findElement(FilesTab.txt_NewDiscussionDescInput).sendKeys(Keys.ENTER);
		findElement(FilesTab.txt_NewDiscussionDescInput).sendKeys(endMessage2);

	}

	public void clickOnSelectFilesAndAttachDocuments(String attachmentFilePath) throws InterruptedException {

		clickElementAndWaitForElement(FilesTab.btn_NewDiscussionMoreOptions, FilesTab.lnk_NewDiscussionMoreOptionsAttachment);
		clickElementAndWaitForElement(FilesTab.lnk_NewDiscussionMoreOptionsAttachment, FilesTab.pop_AttachmentFileModel);
		findElement(ModelsTab.btn_PopModelAttachmentsSelectFiles).sendKeys(attachmentFilePath);
		clickElementAndWait(ModelsTab.btn_PopModelCommentAttachmentsAttachButton);
		waitUntilElementIsInvisible(FilesTab.pop_AttachmentFileModel);
		//clickElementAndWait(FilesTab.btn_NewDiscussionMoreOptionsClose);
		waitForCompletePageLoad();

	}

	public void searchDocumentAndValidateAction(String dueAction) throws InterruptedException {

		searchFiles(strUtils.extractFileNameString(uploadMultiDocumentsList.get(0)));
		Assert.assertTrue(
				"Expected DocRef: " + strUtils.extractFileNameString(fileName).split("\\.")[0],
				getElementText(FilesTab.lnk_FilesListingRowDocRef).contains(
						strUtils.extractFileNameString(fileName).split("\\.")[0]));
		if (dueAction.contains(AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE))
			Assert.assertTrue(
					"Expected Action on document: " + AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE,
					getElementText(FilesTab.lnk_FilesFirstAction).contains(
							AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE));
		else
			Assert.assertTrue("Expected Action on document: " + AdoddleCommonStringPool.ACTION_FOR_ACTION,
					getElementText(FilesTab.lnk_FilesFirstAction).contains(AdoddleCommonStringPool.ACTION_FOR_ACTION));

		clickElementAndWait(FilesTab.lnk_FilesFirstAction);
		waitUntilElementIsDisplayed(GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();
	}

	public void performDueAction(String pendingAction) {

		waitUntilElementIsDisplayed(FilesTab.sel_StatusChangedDropdown);
		selectByVisibleText(FilesTab.sel_StatusChangedDropdown, AdoddleCommonStringPool.STATUS_FOR_REVIEW);
		sendKeys(FilesTab.txt_StatusChangeTextArea, javaUtils.getRandomString(10));
		clickElementAndWait(FilesTab.btn_ChangeStatus);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();
	}

	public void searchDocumentAndEditAttributes(String poiStatus) {

		searchFiles(strUtils.extractFileNameString(uploadMultiDocumentsList.get(0)));
		Assert.assertTrue(
				"Expected DocRef: " + strUtils.extractFileNameString(fileName).split("\\.")[0],
				getElementText(FilesTab.lnk_FilesListingRowDocRef).contains(
						strUtils.extractFileNameString(fileName).split("\\.")[0]));
		contextClick(FilesTab.lnk_DocListingFirstDocRef);
		waitUntilElementIsDisplayed(FilesTab.opt_FileContextClickEdit);
		mouseHoverAndClickElement(FilesTab.opt_FileContextClickEdit, FilesTab.opt_FileContextClickEditAttributes);
		waitUntilElementIsDisplayed(FilesTab.pop_FileEditAttributes);
		selectByVisibleText(FilesTab.css_PopEditAttributesPOI, AdoddleCommonStringPool.POI_FOR_REVIEW);
		clickElementAndWaitForElement(FilesTab.btn_PopEditAttributesAssignAttributes,
				FilesTab.btn_PopupConfirmUIContinue);
		clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_PopupConfirmUIContinue,
				GlobalPageElements.pop_PopUpElement);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();

	}

	public void applyDcoumentFilter() throws InterruptedException {

		navigateTab(LandingPage.lnk_Files);
		clickElementAndWait(GlobalPageElements.drp_GlobalListingCreateFilter);
		waitUntilElementIsDisplayed(GlobalPageElements.txt_GlobalListingCreateFilterSearch);
		sendKeys(GlobalPageElements.txt_GlobalListingCreateFilterSearch, "Revisions");
		clickElement(GlobalPageElements.chk_GlobalListingCreateFilterFilteredFirstCheckbox);
		waitUntilElementIsDisplayed(FilesTab.lbl_DocListingCreatedFirstFilterDropDownLabel);
		clickElementAndWaitForElement(FilesTab.lbl_DocListingCreatedFirstFilterDropDownLabel,
				GlobalPageElements.txt_GlobalListingCreatedFilterDropDownSearch);
		sendKeys(GlobalPageElements.txt_GlobalListingCreatedFilterDropDownSearch, "Superseded");
		if (!isSelected(GlobalPageElements.chk_GlobalListingCreateFilterFilteredFirstCheckbox))
			clickElementAndWait(GlobalPageElements.chk_GlobalListingCreateFilterFilteredFirstCheckbox);
		clickElementAndWait(FilesTab.lnk_FilterClose);
		waitForCompletePageLoad();
		searchFiles(strUtils.extractFileNameString(uploadMultiDocumentsList.get(0)).split("\\.")[0]);

	}

	public void validatePublishedDocumentIssue() {

		log.info("Covered in <validateDocumentWorkflowStatus> Definition");

	}

	public void validateDocumentWorkflowStatus(String wStatus, String wStage) throws InterruptedException {

		boolean flag = false;
		waitUntilListOfElementIsDisplayed(FilesTab.css_FileLists);
		List<WebElement> webfileList = findElements(FilesTab.css_FileLists);

		for (WebElement file : webfileList) {
			flag = true;
			if (file.findElement(FilesTab.lnk_FilesListingRowFileType).getAttribute("src").contains("jpg.gif")) {
				Assert.assertTrue(file.findElement(FilesTab.css_workflowStatus).getText().contains(wStatus));
				Assert.assertTrue(file.findElement(FilesTab.css_workflowStage).getText().contains(wStage));
				Assert.assertTrue("File Version: ", file.findElement(FilesTab.lnk_FilesListingRowVersion).getText()
						.contains("1"));
				file.findElement(FilesTab.css_workflowStatus).click();
				waitUntilElementIsDisplayed(GlobalPageElements.pop_PopUpElement);
				clickElementAndWait(FilesTab.btn_WorkflowStatusCancel);
				waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
				waitForCompletePageLoad();
			}

			else {

				Assert.assertTrue("Invalid Document: ", file.findElement(FilesTab.lnk_FilesListingRowDocRef).getText()
						.contains(strUtils.extractFileNameString(uploadMultiDocumentsList.get(0)).split("\\.")[0]));
				Assert.assertTrue(file.findElement(FilesTab.lnk_FilesListingRowFileType).getAttribute("src")
						.contains("pdf.gif"));
				Assert.assertTrue("File Version: ", file.findElement(FilesTab.lnk_FilesListingRowVersion).getText()
						.contains("2"));
			}

		}

		Assert.assertTrue("Workflow Status verification failed on superseded Version", flag);

	}

	public void validateDocumentInListing() throws InterruptedException {

		boolean flag = false;
		for (int index = 0; index <= 10; index++) {
			navigateTab(LandingPage.lnk_Files);
			searchFiles(strUtils.extractFileNameString(uploadMultiDocumentsList.get(0)).split("\\.")[0]);
			if (getElementText(FilesTab.lnk_FileListingFirstVersion).contains("2")) {
				flag = true;
				Assert.assertTrue(findElement(FilesTab.img_DocListingFirstTypeIcon).getAttribute("src").contains(
						"pdf.gif"));
				break;
			}

		}

		Assert.assertTrue("Document PDF failed to publish", flag);

	}

	public void downloadAndValidateDcoument(String fileName) throws IOException, InterruptedException {
		String docTitle = getElementText(FilesTab.lnk_DocListingFirstFileName);
		clickElementAndWait(FilesTab.img_DocListingFirstTypeIcon);
		File defaultdownloadFile = new File(nodeIP + resourceUtils.getDefaultDownloadDir() + docTitle);
		waitUntilFileIsDownloaded(defaultdownloadFile);
		downloadFilePath = new File(nodeIP + ResourceHandler.loadProperty("remote.download.file.path") + fileName
				+ epoch + AdoddleCommonStringPool.PDF_EXTENSION);
		defaultdownloadFile.renameTo(downloadFilePath);
		log.info("Download File: " + downloadFilePath.toString());
		collectionDataMap.put("Download FilePath: ", downloadFilePath.toString());
		/*autoItUtils.downloadAutoIt(downloadFilePath.toString(), nodeIP);*/
		waitUntilFileIsDownloaded(downloadFilePath);
		AutomationAssert.verifyTrue(downloadFilePath.exists());

	}

	public void compareFileSizeInLocal() throws IOException, InterruptedException {

		boolean flag = false;
		double fileSizeDifference;
		sysUtils.authenticateRemoteMachine(nodeIP);

		double localBatchPrintFileSize = sysUtils.getFileSize(nodeIP
				+ ResourceHandler.loadProperty("bulk.batch.print.testdata.filepath") + dataCenter
				+ AdoddleCommonStringPool.PDF_EXTENSION);

		double savedBatchPrintFileSize = sysUtils.getFileSize(downloadFilePath.toString());
		localBatchPrintFileSize = Math.round(localBatchPrintFileSize * 1000.0) / 1000.0;
		log.info("LocalFileSize :" + localBatchPrintFileSize);

		savedBatchPrintFileSize = Math.round(savedBatchPrintFileSize * 1000.0) / 1000.0;
		log.info("DowloadedFileSize: " + savedBatchPrintFileSize);

		fileSizeDifference = Math.round((savedBatchPrintFileSize - localBatchPrintFileSize) * 1000.0) / 1000.0;
		log.info("BatchFileSize Difference: " + fileSizeDifference);

		if (savedBatchPrintFileSize != 0) {
			flag = true;
			log.info("BatchFileDifference :: " + fileSizeDifference);
			Assert.assertTrue("BatchFileDifference not expected as:: " + fileSizeDifference,
					validateFileSizeRange(fileSizeDifference));

		}

		else
			Assert.assertTrue("BatchAutoTestFile not saved successfully", flag);

	}

	public boolean validateFileSizeRange(double fileSizeDifference) {

		return fileSizeDifference <= 5.0 && fileSizeDifference >= -5.0;

	}

	public String copyAndRenameMultipleDocuments(String sourceFilePath, String destFilePath, String fileExtension) {
		InputStream inStream = null;
		OutputStream outStream = null;
		destFilePath = destFilePath + epoch + fileExtension;

		try {
			File source = new File(sourceFilePath);
			File target = new File(destFilePath);
			inStream = new FileInputStream(source);
			outStream = new FileOutputStream(target);

			byte[] buffer = new byte[1024];
			int length;

			while ((length = inStream.read(buffer)) > 0) {
				outStream.write(buffer, 0, length);
			}
			inStream.close();
			outStream.close();

			log.info("File Copied successfully!");
		} catch (IOException e) {
			log.info("failure in copying file");
		}
		return destFilePath;
	}

}