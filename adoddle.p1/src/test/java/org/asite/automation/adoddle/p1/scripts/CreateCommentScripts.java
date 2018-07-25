/* @author: Jasmin Prajapati  & Ravi Singh */

package org.asite.automation.adoddle.p1.scripts;

import java.io.File;
import java.io.IOException;
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
import org.asite.automation.common.resources.AdoddleScenarioMarkers;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CreateCommentScripts extends AdoddleCommonAppMethods {

	private String					inputZipAttachmentFile		= null;
	private String					inputZipAssociationFile		= null;
	static private String			fileName, parentHandle;
	static private String			commentTitle				= null;
	static private String			outputZipAttachmentFolder	= null;
	static private String			outputZipAssociationFolder	= null;
	final private List<String>		expectedFormIDs				= new ArrayList<String>();
	final private List<String>		expectedAssociationFiles	= new ArrayList<String>();
	final private List<String>		expectedDiscussionIDs		= new ArrayList<String>();
	final private List<String>		expectedAttachmentFiles		= new ArrayList<String>();
	final private List<Double>		fileSizeListExpected		= new ArrayList<Double>();
	final private List<String>		lstAttachFileValLocal		= new ArrayList<String>();
	final private List<String>		lstAssociateFileValLocal	= new ArrayList<String>();
	static private List<WebElement>	webAttachAssociateList	= null;
	private static Logger		log							= AutomationLogger.getInstance().getLogger(CreateCommentScripts.class);

	public CreateCommentScripts() {
		log = AutomationLogger.getInstance().getLogger(this.getClass());
	}

	public void verifyUserIsLoggedIn() {
		verifyLogin();
	}

	public void navigateFilesTab() {
		parentHandle = getCurrentWindow();
		navigateTab(LandingPage.lnk_Files);
	}

	public void verifyDocumentAvailable() {
		fileName = ResourceHandler.loadProperty("create.comment.testdata.filename");
		searchFiles(fileName);
		AutomationAssert.verifyTrue(getCount(FilesTab.css_DocListingFilesCount) > 0);
	}

	public void contextClickDocument() {
		contextClick(FilesTab.lnk_DocListingFirstDocRef);
	}

	public void verifyNewPopup(String expectedText) {
		waitForCompletePageLoad();
		AutomationAssert.verifyTrue(isDisplayed(FilesTab.pop_StartNewDiscussion));
		verifyElementText(FilesTab.pop_StartNewDiscussionHeader, expectedText);
	}

	public void verifyUserExistsInToField(String expectedText) {
		if(isDisplayed(FilesTab.btn_NewDiscussionRemoveAllRecipients))
			clickElementAndWait(FilesTab.btn_NewDiscussionRemoveAllRecipients);
		waitUntilElementCountToBe(FilesTab.css_NewDiscussionToUserCount, 0);
		sendKeys(FilesTab.txt_NewDiscussionToUserField, System.getProperty("secondary.username"));
		sendKeys(FilesTab.txt_NewDiscussionToUserField, Keys.TAB);
		clickElementAndWaitForElement(FilesTab.ele_NewDiscussionToUserDropdownToggle, FilesTab.drp_NewDiscussionToUserToggleActions);
		selectByVisibleText(FilesTab.drp_NewDiscussionToUserToggleActions, AdoddleCommonStringPool.FOR_INFORMATION);
		clickElementAndWait(ProjectFormsTab.lnk_CreateFormDistributionActionActiveDate);
		AutomationAssert.verifyTrue(getCount(FilesTab.css_NewDiscussionToUserCount) > 0);
		verifyElementText(FilesTab.lbl_NewDiscussionToField, expectedText);
	}

	public void enterTitleText() {
		commentTitle = javaUtils.getRandomString(20);
		collectionDataMap.put("Comment Title", commentTitle);
		String commentDesc = javaUtils.getRandomString(20) + dateUtils.getEpoch();
		sendKeys(FilesTab.txt_NewDiscussionTitleInput, commentTitle);
		sendKeys(FilesTab.txt_NewDiscussionDescInput, commentDesc);
		collectionDataMap.put("Comment Title", commentTitle);
	}

	public void verifyTitleLabel(String expectedLabel) {
		verifyElementText(FilesTab.lbl_NewDiscussionTitleField, expectedLabel);
	}

	public void attachAtLeastOneDocument() {
		List<WebElement> fileSizeElements;
		clickElementAndWaitForElement(FilesTab.btn_NewDiscussionMoreOptions, FilesTab.pop_NewDiscussionMoreOptionsDialog);
		clickElementAndWaitForElement(FilesTab.lnk_NewDiscussionMoreOptionsAttachment, FilesTab.pop_AttachmentFileModel);
		List<String> fileList = sysUtils.getFileList(ResourceHandler.loadProperty("multi.files.path"));
		for (String f : fileList) {
			File file = new File(f);
			expectedAttachmentFiles.add(file.getName());
			fileSizeListExpected.add(sysUtils.getFileSize(f));
		}
		log.info("Create comment attachment file(s) list: "+expectedAttachmentFiles.toString());
		collectionDataMap.put("Attachment Files", expectedAttachmentFiles.toString());
		uploadFileUsingKeys(FilesTab.btn_NewDiscussionAttachmentSelectFiles, fileList);
		waitUntilElementIsDisplayed(FilesTab.btn_NewDiscussionAttachmentSave);
		collectionDataMap.put("Attachment Files", fileList.toString());
		AutomationAssert.verifyTrue(getCount(FilesTab.css_NewDiscussionAttachmentCount) > 0);
		fileSizeElements = findElements(FilesTab.lbl_NewDiscussionAttachmentsFileSizeElements);

		for (int index = 0; index != fileSizeListExpected.size(); index++) {
			try {
				AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(Double.parseDouble(fileSizeElements.get(index).getText().replace("KB", "")) , Math.round(fileSizeListExpected.get(index) * 100.0) / 100.0) , Double.parseDouble(fileSizeElements.get(index).getText().replace("KB", "")) == Math.round(fileSizeListExpected.get(index) * 100.0) / 100.0);
			}
			catch (Throwable t) {
				log.error("Adoddle Attachment size :" + Double.parseDouble(fileSizeElements.get(index).getText().replace("KB", "")));
				log.error("Downloaded local File Size :" + Math.round(fileSizeListExpected.get(index) * 100.0) / 100.0);
			}

		}

		clickElementAndWait(FilesTab.btn_NewDiscussionAttachmentSave);
	}

	public void associateAtleastOneDocument(int count) {
		String 	fileAssociation = "FileAssociation";
		clickElementAndWaitForElement(FilesTab.btn_NewDiscussionMoreOptions, FilesTab.pop_NewDiscussionMoreOptionsDialog);
		clickElementAndWaitForElement(FilesTab.lnk_NewDiscussionMoreOptionssAssociateDoc, FilesTab.pop_AssociateFileModel);
		try {
			AutomationAssert.verifyTrue(getCount(FilesTab.css_PopAssociateFileListingCount) >= count);
		}
		catch (Throwable t) {
			log.error("TESTDATA ERROR: Files count is not enough");
			clickElementAndWait(FilesTab.btn_AssociateFileCancel);
			clickElementAndWait(FilesTab.btn_NewDiscussionMoreOptionsClose);
			return;
		}
		clickPopupElementWithText(System.getProperty("global.test.project"));
		clickElementAndWaitForElement(FilesTab.lnk_AssociateFileListFolder, FilesTab.txt_AssociateFilesSearch);
		sendKeys(FilesTab.txt_AssociateFilesSearch, fileAssociation);
		sendKeys(FilesTab.txt_AssociateFilesSearch, Keys.ENTER);
		waitUntilElementIsDisplayed(FilesTab.chk_AssociateFilesDocListingCheckboxes);
		List<WebElement> filesListCheckboxes = findElements(FilesTab.chk_AssociateFilesDocListingCheckboxes);
		List<WebElement> fileListDocRefs = findElements(FilesTab.css_PopAssociateFilesDocRefs);

		for (int index = 1; index != count + 1; index++) {
			filesListCheckboxes.get(index - 1).click();
			expectedAssociationFiles.add(fileListDocRefs.get(index - 1).getText());
		}
		log.info("Create Comment Association File(s) DocRef List: "+expectedAssociationFiles.toString());
		collectionDataMap.put("Associated Files", expectedAssociationFiles.toString());
		clickElementAndWaitForElement(FilesTab.btn_AssociateFileSave, FilesTab.pop_AssociatedDocListModel);
		clickElementAndWaitForElement(FilesTab.btn_AssociateFileListFinalSave, FilesTab.lnk_NewDiscussionShowFiles);
	}

	public void associateAtleastOneDiscussion(int count) {
		String 	DiscussionsAssociations	= "DiscussionsAssociations";
		clickElementAndWaitForElement(FilesTab.btn_NewDiscussionMoreOptions, FilesTab.pop_NewDiscussionMoreOptionsDialog);
		clickElementAndWaitForElement(FilesTab.lnk_NewDiscussionMoreOptionsAssociateDiscussion, FilesTab.pop_AssociateDiscussionModel);

		try {
			AutomationAssert.verifyTrue(getCount(FilesTab.css_PopAssociateDiscussionListingCount) >= count);
		}
		catch (Throwable t) {
			log.error("TESTDATA ERROR: Discussions count is not enough");
			clickElementAndWaitForElement(FilesTab.btn_AssociateDiscussionCancel, FilesTab.btn_NewDiscussionMoreOptionsClose);
			clickElementAndWait(FilesTab.btn_NewDiscussionMoreOptionsClose);
			return;
		}

		sendKeys(FilesTab.txt_AssociateDiscussionsSearch, DiscussionsAssociations);
		sendKeys(FilesTab.txt_AssociateDiscussionsSearch, Keys.ENTER);

		List<WebElement> discussionListCheckboxes = findElements(FilesTab.css_PopAssociateDiscussionsCheckBoxes);
		List<WebElement> discussionListIDs = findElements(FilesTab.css_PopAssociateDiscussionsIDs);

		for (int index = 1; index != count + 1; index++) {
			discussionListCheckboxes.get(index - 1).click();
			expectedDiscussionIDs.add(discussionListIDs.get(index - 1).getText().split("\\(")[0]);
		}
		log.info("Create Comment Associated discussions IDs: "+expectedDiscussionIDs.toString());
		collectionDataMap.put("Associated Discussions", expectedDiscussionIDs.toString());
		clickElementAndWaitForElement(FilesTab.btn_AssociateDiscussionSave, FilesTab.pop_AssociatedDiscussionListModel);
		clickElementAndWaitForElement(FilesTab.btn_AssociateDiscussionListModelSave, FilesTab.lnk_NewDiscussionShowDiscussions);
	}

	public void associateAtleastOneForm(int count) {

		clickElementAndWaitForElement(FilesTab.btn_NewDiscussionMoreOptions, FilesTab.pop_NewDiscussionMoreOptionsDialog);
		clickElementAndWaitForElement(FilesTab.lnk_NewDiscussionMoreOptionsAssociateForm, FilesTab.pop_AssociateFormModel);

		try {
			AutomationAssert.verifyTrue(getCount(FilesTab.css_PopAssociateFormListingCount) >= count);
		}
		catch (Throwable t) {
			log.error("TESTDATA ERROR: Forms count is not enough");
			clickElementAndWait(FilesTab.btn_AssociateFormCancel);
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
		log.info("Create Comment Associated form(s) IDs: "+expectedFormIDs.toString());
		collectionDataMap.put("Associated Apps", expectedFormIDs.toString());
		clickElementAndWaitForElement(FilesTab.btn_AssociateFormSave, FilesTab.pop_AssociatedFormListModel);
		clickElementAndWaitForElement(FilesTab.btn_AssociateFormListModelSave, FilesTab.lnk_NewDiscussionShowForms);

	}

	public void clickSubmitButton(String expectedText) {
		AutomationAssert.verifyTrue(expectedText.equalsIgnoreCase(getElementText(FilesTab.btn_NewDiscussionSubmit)));
		clickElementAndWait(FilesTab.btn_NewDiscussionSubmit);
	}

	public void verifySuccessMessage() {
		verifyElementText(FilesTab.lbl_FileAddCommentSuccessMsg, AdoddleCommonStringPool.COMMENT_SUCCESS_MESSAGE);
	}

	public void verifyCommentOnDiscussionTab(String expectedText) {
		navigateTabByText(AdoddleCommonStringPool.TAB_DISCUSSIONS);
		AutomationAssert.verifyTrue(expectedText.equalsIgnoreCase(getElementText(GlobalPageElements.lnk_ActiveSubTab)));
		waitUntilElementIsDisplayed(DiscussionsTab.txt_DiscussionSearchFilter);
		searchComments(commentTitle);
		AutomationAssert.verifyTrue(getElementText(DiscussionsTab.lnk_DiscussionFirstCommentTitle).equalsIgnoreCase(commentTitle));
		AdoddleScenarioMarkers.createCommentP1Flag = true;
	}

	public void verifyCommentOnFilesDetails() {
		searchFiles(fileName);
		clickElementAndSwitchWindow(FilesTab.lnk_DocListingFirstDocRef);

		if(fileBetaViewFlag){
			waitUntilElementIsDisplayed(FilesTab.ele_BetaFileViewDiscussionListFirstDiscussion);
			clickElementAndWaitForElement(FilesTab.ele_BetaFileViewDiscussionListFirstDiscussionOpen, FilesTab.ele_BetaFileViewFirstDiscussionAttachmentsAndAssociations);
			clickElementAndWaitForElement(FilesTab.ele_BetaFileViewFirstDiscussionAttachmentsAndAssociations, DiscussionsTab.pop_FileBetaViewAttachmentAndAssociationPopup);
		}
		else{
			try {
				clickElementAndWait(FilesTab.lnk_FileViewLHDiscussionBlock);
			}
			catch (Throwable t) {
				clickElementAndWait(FilesTab.lnk_ViewModelBlock);
			}
			clickElementAndWaitForElement(DiscussionsTab.lnk_firstDiscussionAttachmentLink, DiscussionsTab.pop_AttachmentAndAssociationPopup);
		}
	}

	public void verifyFileAssociations() {
		if(fileBetaViewFlag){
			clickElementAndWaitForInvisibilityOfElement(DiscussionsTab.lnk_FileBetaViewAttachmentAndAssociationPopupFiles, GlobalPageElements.ele_LoadingCircle);
			waitUntilElementCountToBeMoreThan(DiscussionsTab.css_FileBetaViewAttachmentAndAssociationFilesDocRefs, 1);

			List<WebElement> associationElements = findElements(DiscussionsTab.css_FileBetaViewAttachmentAndAssociationFilesDocRefs);
			List<String> actualAssociationFileDocRefs = new ArrayList<String>();

			for (WebElement e : associationElements)
				actualAssociationFileDocRefs.add(getElementText(e));

			log.info("Association Actual Files: "+actualAssociationFileDocRefs.toString());

			for (String docRef : expectedAssociationFiles)
				AutomationAssert.verifyTrue(actualAssociationFileDocRefs.contains(docRef));
		}
		else{
			clickElementAndWait(DiscussionsTab.lnk_PopAttachmentAndAssociationPopupFiles);
			List<WebElement> associationElements = findElements(DiscussionsTab.css_PopAttachmentAndAssociationFilesDocRefs);
			List<String> actualAssociationFileDocRefs = new ArrayList<String>();

			for (WebElement e : associationElements)
				actualAssociationFileDocRefs.add(e.getText());

			for (String docRef : expectedAssociationFiles)
				AutomationAssert.verifyTrue(actualAssociationFileDocRefs.contains(docRef));
		}
	}

	public void verifyAttachments() {

		if(fileBetaViewFlag){
			waitUntilElementIsDisplayed(FilesTab.ele_BetaFileViewFirstDiscussionAttachmentsAndAssociations);
			clickElementAndWaitForInvisibilityOfElement(DiscussionsTab.pop_FileBetaViewAttachmentPopup, GlobalPageElements.ele_LoadingCircle);
			List<WebElement> attachmentElements = findElements(DiscussionsTab.css_FileBetaViewAttachmentAndAssociationAttachmentsFileNames);
			List<String> actualAttachmentFileNames = new ArrayList<String>();

			for (WebElement e : attachmentElements)
				actualAttachmentFileNames.add(e.getText());

			for (String attachment : expectedAttachmentFiles){
				AutomationAssert.verifyTrue(actualAttachmentFileNames.contains(attachment));
			}

			for (String attachment : actualAttachmentFileNames)
				AutomationAssert.verifyTrue(expectedAttachmentFiles.contains(attachment));

			closeCurrentWindow();
			switchPreviousWindow(parentHandle);
		}
		else{
			clickElementAndWait(DiscussionsTab.lnk_PopAttachmentAndAssociationPopupAttachments);
			List<WebElement> attachmentElements = findElements(DiscussionsTab.css_PopAttachmentAndAssociationAttachmentsFileNames);
			List<String> actualAttachmentFileNames = new ArrayList<String>();

			for (WebElement e : attachmentElements)
				actualAttachmentFileNames.add(e.getText());

			for (String attachment : expectedAttachmentFiles)
				AutomationAssert.verifyTrue(actualAttachmentFileNames.contains(attachment));

			for (String attachment : actualAttachmentFileNames)
				AutomationAssert.verifyTrue(expectedAttachmentFiles.contains(attachment));

			closeCurrentWindow();
			switchPreviousWindow(parentHandle);
		}
	}

	public void verifyAssociationForms() {
		if(fileBetaViewFlag){
			clickElementAndWaitForInvisibilityOfElement(DiscussionsTab.lnk_FileBetaViewAttachmentAndAssociationPopupForms, GlobalPageElements.ele_LoadingCircle);
			waitUntilElementCountToBe(DiscussionsTab.css_FileBetaViewAttachmentAndAssociationFormIDs, 3);
			waitUntilElementIsDisplayed(DiscussionsTab.lnk_FileBetaViewAttachmentAndAssociationPopupFirstFormID);
			List<WebElement> formIDElements = findElements(DiscussionsTab.css_FileBetaViewAttachmentAndAssociationPopupFormIDs);
			List<String> actualformIDs = new ArrayList<String>();

			for (WebElement e : formIDElements)
				actualformIDs.add(e.getText());

			for (String formID : expectedFormIDs)
				AutomationAssert.verifyTrue(actualformIDs.contains(formID));

			for (String formID : actualformIDs)
				AutomationAssert.verifyTrue(expectedFormIDs.contains(formID));
		}
		else{
			clickElementAndWait(DiscussionsTab.lnk_PopAttachmentAndAssociationPopupForms);

			List<WebElement> formIDElements = findElements(DiscussionsTab.css_PopAttachmentAndAssociationPopupFormIDs);
			List<String> actualformIDs = new ArrayList<String>();

			for (WebElement e : formIDElements)
				actualformIDs.add(e.getText());

			for (String formID : expectedFormIDs)
				AutomationAssert.verifyTrue(actualformIDs.contains(formID));

			for (String formID : actualformIDs)
				AutomationAssert.verifyTrue(expectedFormIDs.contains(formID));
		}
	}

	public void verifyAssociationDiscussions() {

		if(fileBetaViewFlag){
			clickElementAndWaitForInvisibilityOfElement(DiscussionsTab.lnk_FileBetaViewAttachmentAndAssociationPopupDiscussions, GlobalPageElements.ele_LoadingCircle);
			waitUntilElementIsDisplayed(DiscussionsTab.lnk_FileBetaViewAttachmentAndAssociationFirstDiscussionID);
			List<WebElement> discussionIDElements = findElements(DiscussionsTab.css_FileBetaViewAttachmentAndAssociationDiscussionIDs);
			List<String> actualDiscussionIDs = new ArrayList<String>();

			for (WebElement e : discussionIDElements)
				actualDiscussionIDs.add(e.getText());

			for (String dID : expectedDiscussionIDs)
				AutomationAssert.verifyTrue(actualDiscussionIDs.contains(dID));

			for (String dID : actualDiscussionIDs)
				AutomationAssert.verifyTrue(expectedDiscussionIDs.contains(dID));
		}
		else{
			clickElementAndWait(DiscussionsTab.lnk_PopAttachmentAndAssociationPopupDiscussions);

			List<WebElement> discussionIDElements = findElements(DiscussionsTab.css_PopAttachmentAndAssociationPopupDiscussionIDs);
			List<String> actualDiscussionIDs = new ArrayList<String>();

			for (WebElement e : discussionIDElements)
				actualDiscussionIDs.add(e.getText());

			for (String dID : expectedDiscussionIDs)
				AutomationAssert.verifyTrue(actualDiscussionIDs.contains(dID));

			for (String dID : actualDiscussionIDs)
				AutomationAssert.verifyTrue(expectedDiscussionIDs.contains(dID));
		}
	}
	/****** Download Attachments & Associations ******/

	public void searchCreatedComment() {

		searchDiscussions(commentTitle);

	}

	public void mouseHoverAndClickAssociationLink() {
		mouseHoverAndClickElement(DiscussionsTab.img_firstDiscussionAssociationLink, DiscussionsTab.lnk_firstDiscussionAssociationAttachementLink);
	}

	public void verifyPopupHeader(String headerName) {
		AutomationAssert.verifyTrue(getElementText(GlobalPageElements.lbl_PopUpHeader1).contains(headerName));
	}

	public void selectFilesAndClickOnDownload(String lnkText) {
		webAttachAssociateList = new ArrayList<WebElement>();

		if ("attachments".equalsIgnoreCase(lnkText))
			clickElementAndWait(DiscussionsTab.lnk_PopAttachmentAndAssociationsAttachments);
		else if ("files".equalsIgnoreCase(lnkText))
			clickElementAndWait(DiscussionsTab.lnk_PopAttachmentAndAssociationPopupFiles);

		if (getElementText(DiscussionsTab.lnk_PopAttachmentsAndAssociationsActiveTab).contains(lnkText)) {
			webAttachAssociateList = findElements(DiscussionsTab.css_PopAttachmentsAndAssociationsAttachmentsTabFileNameList);
		}
		else if (getElementText(DiscussionsTab.lnk_PopAttachmentsAndAssociationsActiveTab).contains("Files")) {
			webAttachAssociateList = findElements(DiscussionsTab.css_PopAssociateFormsFormTitleList);
		}
		clickElementAndWait(DiscussionsTab.chk_PopAttachmentAssociationCheckAllCheckbox);
		clickElementAndWaitForElement(DiscussionsTab.lnk_PopDownloadLink, DiscussionsTab.btn_PopDownloadButton);
	}

	public void selectCheckListAndClickOnDownload(String linkTab) {
		if (!linkTab.contains("Attachments")) {

			List<WebElement> cbList = findElements(DiscussionsTab.css_AppendNameStringCheckList);
			for (WebElement e : cbList) {
				if (e.isDisplayed() && !e.getAttribute("id").equalsIgnoreCase("isFolderStructure") && !e.isSelected())
					e.click();
			}
		}
		clickElementAndWait(DiscussionsTab.btn_PopDownloadButton);
	}

	public void downloadZipDocumentIntoLocal(String linkTab) throws InterruptedException, IOException {
		File file;
		waitUntilElementIsInvisible(DiscussionsTab.pop_BatchFiles);

		if (!linkTab.contains("Attachments")) {
			outputZipAssociationFolder = nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim() + "AutomationAssociationZipFile".trim() + dateUtils.getEpoch();			inputZipAssociationFile = outputZipAssociationFolder + AdoddleCommonStringPool.ZIP_EXTENSION;
			log.info("Attachment output zip association folder: "+outputZipAssociationFolder);
			log.info("Attachment output zip association folder: "+inputZipAssociationFile);
			//autoItUtils.downloadAutoIt(inputZipAssociationFile, nodeIP);
			sysUtils.waitForMultipleFileDownload(nodeIP, inputZipAssociationFile);
			file = new File(inputZipAssociationFile);
		}
		else {
			outputZipAttachmentFolder = nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim() + "AutomationAttachmentZipFile".trim() + dateUtils.getEpoch();
			inputZipAttachmentFile = outputZipAttachmentFolder + AdoddleCommonStringPool.ZIP_EXTENSION;
			log.info("Attachment output zip attachment folder: "+outputZipAttachmentFolder);
			log.info("Attachment output zip attachment folder: "+inputZipAttachmentFile);
			//autoItUtils.downloadAutoIt(inputZipAttachmentFile, nodeIP);
			sysUtils.waitForMultipleFileDownload(nodeIP, inputZipAttachmentFile);
			file = new File(inputZipAttachmentFile);
		}
		waitUntilFileIsDownloaded(file);
		AutomationAssert.verifyTrue(file.exists());
	}

	public void zipIntoUnZip(String linkTab) {
		if (!linkTab.contains("Attachments")) {
			inputZipAssociationFile = outputZipAssociationFolder + AdoddleCommonStringPool.ZIP_EXTENSION;
			sysUtils.unZipFile(inputZipAssociationFile, outputZipAssociationFolder);
		}
		else {
			inputZipAttachmentFile = outputZipAttachmentFolder + AdoddleCommonStringPool.ZIP_EXTENSION;
			sysUtils.unZipFile(inputZipAttachmentFile, outputZipAttachmentFolder);
		}
	}

	public void getFileNamesFromLocalFolder(String linkTab) {
		File folder;
		File[] listOfFiles;

		if (!linkTab.contains("Attachments")) {
			log.info("Getting association names : "+outputZipAssociationFolder);
			folder = new File(outputZipAssociationFolder);
			listOfFiles = folder.listFiles();
		}
		else {
			log.info("Getting attachment names : "+outputZipAttachmentFolder);
			folder = new File(outputZipAttachmentFolder);
			listOfFiles = folder.listFiles();
		}

		assert listOfFiles != null;
		for (File listOfFile : listOfFiles) {
			if (listOfFile.isFile()) {
				if (!linkTab.contains("Attachments")) {
					lstAssociateFileValLocal.add(listOfFile.getName().substring(0, listOfFile.getName().lastIndexOf(".")));
				} else {
					lstAttachFileValLocal.add(listOfFile.getName().substring(0, listOfFile.getName().lastIndexOf(".")));
				}
			}
		}

		log.info("Downloaded Attachment Files List :" + lstAttachFileValLocal);
		log.info("Downloaded Associated Files List :" + lstAssociateFileValLocal);
	}

	public void verifyFilesNameIntoSystem(String linkTab) {
		boolean flag = false;
		List<String> localFileList = new ArrayList<String>();

		if (! linkTab.contains("Attachments")) {
			localFileList.addAll(lstAssociateFileValLocal);
		}
		else
			localFileList.addAll(lstAttachFileValLocal);

		for (WebElement webFile : webAttachAssociateList) {
			for (String localFile : localFileList) {
				if (localFile.contains(webFile.getText().split("\\.")[0])) {
					log.info("Web File Verified :" + webFile.getText().split("\\.")[0]);
					log.info("Local File Verified :" + localFile);
					flag = true;
					break;
				}
				else
					flag = false;
			}
			if (!flag)
				AutomationAssert.verifyTrue(false);
		}

	}

	public void loginWithSecondaryUser() throws InterruptedException {
		try {
			logOut();
		}
		catch (Throwable t) {
			log.info("error while logging out..probably already logged out");
		}
		login(System.getProperty("secondary.username"), System.getProperty("secondary.password"));
	}

	public void verifyActionIsAssigned() {
		searchFiles(fileName);
		AutomationAssert.verifyTrue(getElementText(FilesTab.lnk_FilesFirstAction).equalsIgnoreCase(AdoddleCommonStringPool.FOR_INFORMATION));
	}
}