package org.asite.automation.adoddle.p2.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleDiscussionsLocators.DiscussionsTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.resources.AdoddleScenarioMarkers;
import org.asite.automation.common.utils.JavaUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class LockUnlockActivity extends AdoddleCommonAppMethods {

	private static String folderName1, folderName2;
	private List<WebElement> userList = new ArrayList<WebElement>();
	private List<WebElement> activityList = new ArrayList<WebElement>();
	private List<WebElement> webFileList = new ArrayList<WebElement>();
	private List<String> epochList = new ArrayList<String>();
	private List<String> ChildDirectoryfileList = new ArrayList<String>();
	private static String epoch1 = JavaUtils.getRandomNumber(15);
	private static String epoch2 = JavaUtils.getRandomNumber(15);
	private static String epoch3 = JavaUtils.getRandomNumber(15);
	private static String epoch4 = JavaUtils.getRandomNumber(15);
	private static String epoch5 = JavaUtils.getRandomNumber(15);
	private static String epoch6 = JavaUtils.getRandomNumber(15);
	private static String epoch7 = JavaUtils.getRandomNumber(15);

	private static List<String> multiFileUploadList = new ArrayList<String>();
	private static List<String> ParentDirectoryfileList = new ArrayList<String>();
	public static Logger log = AutomationLogger.getInstance().getLogger(LockUnlockActivity.class);

	public void createSubDirectoryAndRestFolderPermission(String directoryName) {

		if (directoryName.contains("Auto_LockParentFolder")) {

			folderName1 = directoryName + dateUtils.getEpoch();
			sendKeys(FilesTab.txt_PopCreateFolderFolderName, folderName1);
			resetFolderPermission(directoryName);
			clickElementAndWait(FilesTab.btn_PopCreateFolderCreate);
			Assert.assertTrue(isDisplayedElementWithText(folderName1));

		} else {

			folderName2 = directoryName + dateUtils.getEpoch();
			sendKeys(FilesTab.txt_PopCreateFolderFolderName, folderName2);
			resetFolderPermission(directoryName);
			clickElementAndWait(FilesTab.btn_PopCreateFolderCreate);
			Assert.assertTrue(isDisplayedElementWithText(folderName2));

		}

	}

	public void resetFolderPermission(String folderName) {

		clickElementAndWait(FilesTab.btn_PopUpAddFolderSecurity);
		if (folderName.contains("Auto_LockSubFolder"))
			clickElement(FilesTab.lnk_PopUpAddFolderCancelRole);
		sendKeys(FilesTab.sel_PopUpAddFolderSelectRole, AdoddleCommonStringPool.SECURITY_ROLE_TC_TEST_ORG_ROLE);
		waitUntilElementIsDisplayed(FilesTab.css_PopUpAddFolderSelectedRoleResult);
		if (getElementText(FilesTab.css_PopUpAddFolderSelectedRoleResult).contains(
				AdoddleCommonStringPool.SECURITY_ROLE_TC_TEST_ORG_ROLE))
			clickElement(FilesTab.css_PopUpAddFolderSelectedRoleResult);

		userList = findElements(FilesTab.css_PopUpAddFolderExistingRoleList);
		for (WebElement web : userList)
			if (web.getAttribute("title").contains(AdoddleCommonStringPool.SECURITY_ROLE_TC_TEST_ORG_ROLE)) {
				web.findElement(FilesTab.css_PopUpAddFolderRolePrivilege).click();
				waitUntilElementIsDisplayed(FilesTab.css_PopUpAddFolderFolderPermission);
				break;

			}
		if (folderName.contains("Auto_LockParentFolder"))
			selectByVisibleText(FilesTab.drp_PopUpAddFolderPermission, AdoddleCommonStringPool.PERMISSION_ADMIN);
		else
			selectByVisibleText(FilesTab.drp_PopUpAddFolderPermission, AdoddleCommonStringPool.PERMISSION_UPLOAD);

	}

	public void clickCreateButton() {

		log.info("Covered in <createSubDirectoryAndResetFolderPermission> Definition");

	}

	public void valdiateFolderInListing() {
		log.info("Covered in <createSubDirectoryAndResetFolderPermission> Definition");

	}

	public void contextClickParentDirectory() {
		contextClickWithText(folderName1);
		waitUntilElementIsDisplayed(FilesTab.opt_ProjectContextClickNew);

	}

	public void focusFolderInListing(String folderName) {

		if (folderName.contains("Auto_LockParentFolder")) {
			clickElementWithText(folderName1);
			collectionDataMap.put("AutoLockPrimaryFolder:: ", folderName1);
		}

		else {
			clickElementWithText(folderName2);
			collectionDataMap.put("AutoLockSubFolder:: ", folderName2);
		}

	}

	public void publishMultipleDocumentsInDirectories(String directoryName) throws InterruptedException, IOException {

		epochList = sysUtils.getFileList(epoch1 + "," + epoch2 + "," + epoch3 + "," + epoch4 + "," + epoch5 + ","
				+ epoch6 + "," + epoch7);
		log.info("EpochList size" + epochList.size());
		if (directoryName.contains("Auto_LockParentFolder"))
			uploadMultipleDocuments(null, 20, 1);
		else
			uploadMultipleDocuments(multiFileUploadList, 0, 1);

		for (String file : multiFileUploadList)
			ParentDirectoryfileList.add(strUtils.extractFileNameString(file));

		log.info("FileList Element" + ParentDirectoryfileList);
		collectionDataMap.put("multiDocumentList:: ", ParentDirectoryfileList.toString());

	}

	public void validateDocumentsInDirectory() {
		log.info("Covered in <publishMultipleDocumentsInDirectories> in Definition");

	}

	public void searchDocumentsAndDistribute() throws InterruptedException {

		for (String epoch : epochList) {

			searchFiles(epoch);
			validateDocumentsInListing();
			if (epoch.contains(epoch1) || epoch.contains(epoch2) || epoch.contains(epoch3)) {

				clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);
				contextClick(FilesTab.lnk_DocListingFirstDocRef);
				mouseHoverAndClickElement(FilesTab.opt_FileContextClickShare,
						FilesTab.opt_FileContextClickShareDistribute);
				sendKeys(FilesTab.txt_PopFilesActionForDistributeTo,
						ResourceHandler.loadProperty("test.user.rfi.bloggs.name"));
				sendKeys(FilesTab.txt_PopFilesActionForDistributeTo, Keys.ENTER);
				clickElementAndWait(FilesTab.css_PopDistributeToggleDropdowns);
				waitUntilElementIsDisplayed(FilesTab.drp_PopDistributeToggleActionDropDown);
				if (epoch.contains(epoch1))
					selectByVisibleText(FilesTab.drp_PopDistributeToggleActionDropDown,
							AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION);
				else if (epoch.contains(epoch2))
					selectByVisibleText(FilesTab.drp_PopDistributeToggleActionDropDown,
							AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE);
				else
					selectByVisibleText(FilesTab.drp_PopDistributeToggleActionDropDown,
							AdoddleCommonStringPool.ACTION_FOR_COMMENT);
				if (isDisplayed(GlobalPageElements.lnk_DatePickerCalenderToday))
					clickElementAndWaitForInvisibilityOfElement(GlobalPageElements.lnk_DatePickerCalenderToday,
							GlobalPageElements.lnk_DatePickerCalenderToday);
				else {
					for (WebElement e : findElements(By
							.cssSelector("table[class='ui-datepicker-calendar'] tbody tr td")))
						log.info("\nClass Attributes for Date Pickers: " + e.getAttribute("class"));
				}

				sendKeys(FilesTab.txt_PopFilesActionForDistributeSubject, javaUtils.getRandomString(20));
				clickElementAndWait(FilesTab.btn_PopFilesActionForDistribute);
				waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
				waitForCompletePageLoad();

			}

		}

	}

	public void validateDocumentsInListing() {

		webFileList = findElements(FilesTab.css_FilesListingRowList);

		for (WebElement web : webFileList) {
			String file = web.findElement(FilesTab.lnk_FilesListingRowFileName).getText();
			log.info("FileName:: " + file);
			Assert.assertTrue("Document not available in Listing", ParentDirectoryfileList.contains(file));
		}
	}

	public void searchDocumentsAndLockedActivity() throws InterruptedException {
		reloadPage();
		waitForCompletePageLoad();
		navigateTab(LandingPage.lnk_Files);
		for (String epoch : epochList) {

			searchFiles(epoch);
			clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);
			contextClick(FilesTab.lnk_DocListingFirstFileName);
			Assert.assertTrue("Element is not Enabled", isEnabled(FilesTab.opt_FilesListingContextClickActivityLocks));
			clickElementAndWait(FilesTab.opt_FilesListingContextClickActivityLocks);
			waitUntilElementIsDisplayed(GlobalPageElements.pop_PopUpElement);
			verifyPopUpWithText("Activity Locks");
			if (epoch.contains(epoch1))
				lockedMultipleBatchActivity(AdoddleCommonStringPool.ACTIVITY_FILE_DISTRIBUTION);
			else if (epoch.contains(epoch2))
				lockedMultipleBatchActivity(AdoddleCommonStringPool.ACTIVITY_UPDATE_STATUS);
			else if (epoch.contains(epoch3))
				lockedMultipleBatchActivity(AdoddleCommonStringPool.ACTIVITY_COMMENTING);
			else if (epoch.contains(epoch4))
				lockedMultipleBatchActivity(AdoddleCommonStringPool.ACTIVITY_REVISION_UPLOAD);
			else if (epoch.contains(epoch5))
				lockedMultipleBatchActivity(AdoddleCommonStringPool.ACTIVITY_EDIT_ATTRIBUTE);
			else if (epoch.contains(epoch6))
				lockedMultipleBatchActivity("Lock All Activity");
			else
				log.info("Documents(s) should remain unlocked");

		}

		AdoddleScenarioMarkers.lockUnlockActivityFlag = true;

	}

	public void lockedMultipleBatchActivity(String lockedActivityName) throws InterruptedException {

		activityList = findElements(FilesTab.css_PopupActivityLocksActivitiesLists);

		for (WebElement web : activityList) {

			WebElement chkBox = web.findElement(FilesTab.chk_PopupActivityLocksmultiActivityLockChekbox);
			String activityName = web.findElement(FilesTab.css_PopupActivityLocksActivityName).getText();
			WebElement LockedIcon = web.findElement(FilesTab.css_PopupActivityLocksActivityLockSlider);
			log.info("Activity Name:: " + lockedActivityName);
			log.info("Activity Name:: " + activityList);
			if (activityName.contains(lockedActivityName)) {
				chkBox.click();
				LockedIcon.click();
			}

			else if (lockedActivityName.contains("Lock All"))
				clickElementAndWait(FilesTab.btn_PopupActivityLocksLockAll);

			else
				log.info("Documents should not locked");

		}

		clickElementAndWait(FilesTab.btn_PopupActivityLocksSave);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();
		validateIsDocumentsLocked(lockedActivityName);

	}

	public void validateIsDocumentsLocked(String activityName) {
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitForCompletePageLoad();
		webFileList = findElements(FilesTab.css_FilesListingRowList);

		for (WebElement web : webFileList) {

			WebElement activityLockedIcon = web.findElement(FilesTab.img_DocListingActivityLockIconInstance);
			mouseHoverWebElement(activityLockedIcon);
			waitUntilElementIsDisplayed(FilesTab.pop_docListingLockedActivityPopUp);
			if (activityName.contains(AdoddleCommonStringPool.ACTIVITY_FILE_DISTRIBUTION))
				Assert.assertTrue(
						"Failure in validation",
						getElementText(FilesTab.css_DocListingActivities).contains(
								AdoddleCommonStringPool.ACTIVITY_FILE_DISTRIBUTION));

			else if (activityName.contains(AdoddleCommonStringPool.ACTIVITY_UPDATE_STATUS))
				Assert.assertTrue(
						"Failure in validation",
						getElementText(FilesTab.css_DocListingActivities).contains(
								AdoddleCommonStringPool.ACTIVITY_UPDATE_STATUS));

			else if (activityName.contains(AdoddleCommonStringPool.ACTIVITY_COMMENTING))
				Assert.assertTrue(
						"Failure in validation",
						getElementText(FilesTab.css_DocListingActivities).contains(
								AdoddleCommonStringPool.ACTIVITY_COMMENTING));

			else if (activityName.contains(AdoddleCommonStringPool.ACTIVITY_REVISION_UPLOAD))
				Assert.assertTrue(
						"Failure in validation",
						getElementText(FilesTab.css_DocListingActivities).contains(
								AdoddleCommonStringPool.ACTIVITY_REVISION_UPLOAD));

			else if (activityName.contains(AdoddleCommonStringPool.ACTIVITY_EDIT_ATTRIBUTE))
				Assert.assertTrue(
						"Failure in validation",
						getElementText(FilesTab.css_DocListingActivities).contains(
								AdoddleCommonStringPool.ACTIVITY_EDIT_ATTRIBUTE));

			else {

				Assert.assertTrue("Failure in validation expected Count:: 5 but was "

				+ getCount(FilesTab.css_DocListingActivities), getCount(FilesTab.css_DocListingActivities) == 5);

				break;
			}

		}

	}

	public void clickContextClickMenuOption(String contextClickOption) {

		if (contextClickOption.contains("Activity Locks")) {
			Assert.assertTrue("Element is not Enabled", isEnabled(FilesTab.opt_FilesListingContextClickActivityLocks));
			clickElementAndWait(FilesTab.opt_FilesListingContextClickActivityLocks);
			waitUntilElementIsDisplayed(GlobalPageElements.pop_PopUpElement);
			waitForCompletePageLoad();
		}

		else {
			Assert.assertTrue("Element is not Enabled", isEnabled(FilesTab.opt_FileContextClickActions));
			mouseHoverAndClickElement(FilesTab.opt_FileContextClickActions,
					FilesTab.opt_FileContextClickActionsForAcknowledgement);
			waitUntilElementIsDisplayed(GlobalPageElements.pop_PopUpElement);
			waitForCompletePageLoad();

		}

	}

	public void selectMultipleDocumentsAndContextClick() throws InterruptedException {

		clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);
		contextClick(FilesTab.lnk_DocListingFirstDocRef);

	}

	public void selectDocumentsAndContextClick() {

		webFileList = findElements(FilesTab.css_FilesListingRowList);
		for (int index = 0; index < webFileList.size(); index++) {

			String file = webFileList.get(index).findElement(FilesTab.lnk_FilesListingRowFileName).getText();
			log.info("FileName::" + file);
			if (ParentDirectoryfileList.get(7).contains(file) || ParentDirectoryfileList.get(8).contains(file)) {

				webFileList.get(index).findElement(FilesTab.chk_FilesListingRowcheckbox).click();

			}

			else
				log.info("File missing or not listed");

		}

		contextClickWithLink(ParentDirectoryfileList.get(8));
	}

	public void validateMultipleDocumentsAndAction(String actionName) {

		Boolean flag = false;
		webFileList = findElements(FilesTab.css_FilesListingRowList);
		log.info("WebFileList Size:: " + webFileList.size());
		for (WebElement web : webFileList) {

			String file = web.findElement(FilesTab.lnk_FilesListingRowFileName).getText();
			WebElement fileAction = web.findElement(By.cssSelector("div[class*='actionTime-fixed-width'] span"));

			if (ParentDirectoryfileList.contains(file)) {
				flag = true;
				mouseHoverWebElement(fileAction);
				Assert.assertTrue("Action not completed successfully",
						isDisplayed(findElement(DiscussionsTab.img_RFIActionCompletionIcon)));
			} else {
				log.info("Failure(s) in validating file action");
				flag = false;
			}

		}

		Assert.assertTrue(flag);

	}

	public void performFileAction() throws InterruptedException {

		sendKeys(FilesTab.txt_PopFilesActionForDistributeTo, ResourceHandler.loadProperty("test.user.tc.bloggs.name"));
		sendKeys(FilesTab.txt_PopFilesActionForDistributeTo, Keys.ENTER);
		sendKeys(FilesTab.txt_PopFilesActionForDistributeSubject, javaUtils.getRandomString(20));
		clickElementAndWait(FilesTab.btn_PopFilesActionForDistribute);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();
	}

	public void lockBatchDocuments(String btntext) throws InterruptedException {

		if (btntext.contains("Lock All")) {
			waitUntilElementIsClickable(FilesTab.btn_PopupActivityLocksLockAll);
			clickElementAndWait(FilesTab.btn_PopupActivityLocksLockAll);

		} else {
			waitUntilElementIsClickable(FilesTab.btn_PopupActivityLocksUnLockAll);
			clickElementAndWait(FilesTab.btn_PopupActivityLocksUnLockAll);
		}

		clickElementAndWait(FilesTab.btn_PopupActivityLocksSave);
		navigateTab(LandingPage.lnk_Files);
		clickElementWithText(folderName1);
		waitForCompletePageLoad();

	}

	public void validateLockedActivitiesOnDocuments() {

		log.info("Covered in <validateLockedDocumentsInListing> Definition");

	}

	public void validateLockedAndUnlockedDocuments(String scenarioType) {

		waitUntilElementIsDisplayed(GlobalPageElements.pop_PopUpElement);

		if (scenarioType.contains("Distribution"))
			webFileList = findElements(FilesTab.pop_DocListingDistributeDocumentList);

		else if (scenarioType.contains("Commenting")) {
			executeJScript(AdoddleCommonJQueries.scrollDownScrollJquery);
			webFileList = findElements(FilesTab.pop_DocListingNewCommentDocumentList);
		}

		else
			webFileList = findElements(FilesTab.pop_DocListingStatusChangeDocumentList);

		log.info("Size::: " + webFileList.size());

		if (scenarioType.contains("beforeActionCompletion"))
			Assert.assertTrue("ExpectedWebFilelist size:: 24 But found:: " + webFileList.size(),
					webFileList.size() == 24);
		else
			Assert.assertTrue("ExpectedWebFilelist size:: 20 But found:: " + webFileList.size(),
					webFileList.size() == 20);

		webFileList.clear();

	}

	public void distributeUnlockedDocuments(String userName) throws InterruptedException {

		sendKeys(FilesTab.txt_PopFilesActionForDistributeTo, userName);
		sendKeys(FilesTab.txt_PopFilesActionForDistributeTo, Keys.ENTER);
		sendKeys(FilesTab.txt_PopFilesActionForDistributeSubject, javaUtils.getRandomString(20));
		clickElementAndWait(FilesTab.btn_PopFilesActionForDistribute);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();

	}

	public void searchDocumentsWithEpochs(String docSuffix) {

		if (docSuffix.contains("Epoch1"))
			searchFiles(epoch1);

		else if (docSuffix.contains("Epoch2"))
			searchFiles(epoch2);

		else
			searchFiles(epoch3);

	}

	public void validateWebPageMessage(String msgTxt) {

		String expectedWebpageMessage = getElementText(FilesTab.css_PopupWebpageMessage);
		log.info("WebPage Message" + expectedWebpageMessage);
		clickElementAndWait(FilesTab.btn_PopupWebpageMessageOkay);

	}

	public void selectMultipleDocumentAndContextClick() {

		clickElement(FilesTab.chk_MultiFilesSelectionCheckbox);
		contextClick(FilesTab.lnk_DocListingFirstFileName);
		clickElement(FilesTab.opt_FilesListingContextClickActivityLocks);

	}

	public void validateSubDirectoryDocumentListAndClickContinue() {

		String expectedWebpageMessage = getElementText(FilesTab.css_PopupWebpageMessagefromwebpage);

		log.info(expectedWebpageMessage);
		for (index = 0; index < ChildDirectoryfileList.size(); index++)
			Assert.assertTrue("ExpectedWebpage Message" + ChildDirectoryfileList.get(index),
					expectedWebpageMessage.contains(ChildDirectoryfileList.get(index)));
		clickElementAndWait(FilesTab.btn_PopupWebpageMessageContinue);

	}

	public void validateUnLockedDocumentsInListing() throws InterruptedException {

		webFileList = findElements(FilesTab.css_FilesListingRowList);
		for (WebElement web : webFileList) {
			String file = web.findElement(FilesTab.lnk_FilesListingRowFileName).getText();
			if (ParentDirectoryfileList.contains(file) || ChildDirectoryfileList.contains(file))
				Assert.assertTrue("Expected Locked Icon missing as:: ",
						web.findElement(FilesTab.css_FilesListingRowLockedRow).getText().contains("--"));
		}

		webFileList.clear();

	}

	public void resetFilterSearch() throws InterruptedException {
		navigateTab(LandingPage.lnk_Files);
		clickElementWithText(folderName1);
		waitForCompletePageLoad();

	}

	public void publishMultipleDocumentsRevisions() throws Exception {

		log.info("UplaodFile list" + multiFileUploadList);
		uploadMultipleFilesUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, multiFileUploadList);

	}

	public void validatePopElementMessage(String headertext) {

		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitForCompletePageLoad();
		Assert.assertTrue(isDisplayed(GlobalPageElements.pop_PopUpElement));
		Assert.assertTrue(getElementText(
				By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='adoddleAlertUI'] h3"))
				.contains(headertext));

	}

	public void validateRestrictedFileListAndWebPageAlert() {

		log.info("Covered in <publishMultipleDocumentsRevisions> Definition");
	}

	public void uploadMultipleDocuments(List<String> uploadfileList, int count, int rev) throws InterruptedException,
			IOException {

		String fileName;
		int j = 1;
		String fileRevision = String.valueOf(rev);

		clickElementAndWaitForElement(FilesTab.btn_AddFiles, FilesTab.pop_UploadFile);

		if (uploadfileList != null)
			uploadMultipleFilesUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, uploadfileList);

		else {
			sysUtils.authenticateRemoteMachine(nodeIP);
			for (String epoch : epochList) {
				System.out.print("Epoch::" + epoch);
				for (int i = 1; i <= 2; i++) {
					fileName = sysUtils.createRemotePdfFile(
							nodeIP + resourceUtils.getTestDataFilePath() + j + "_" + epoch
									+ AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
					multiFileUploadList.add(fileName);
					j += 1;
				}
			}
			log.info("multiFileUploadList : " + multiFileUploadList);
			uploadMultipleFilesUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, multiFileUploadList);
		}
		enterHeaderAttributeValues(fileRevision);
		clickElementAndWait(FilesTab.btn_PopUploadFileUpload);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);

	}

	public void enterHeaderAttributeValues(String fileRevision) {

		clickElementAndWait(FilesTab.chk_PopUploadFilesBulkApply);
		clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
		sendKeys(FilesTab.txt_PopUploadHeaderRevInput, fileRevision);
		selectByIndex(FilesTab.drp_PopUploadHeaderPurposeOfIssue, 1);
		selectByIndex(FilesTab.drp_PopUploadHeaderStatus, 1);
		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
		clickElementAndWait(FilesTab.btn_PopUploadApplytoAll);
		waitForCompletePageLoad();

	}

	public void updateMultiDocumentsStatus(String fileStatus) {

		waitUntilElementIsDisplayed(FilesTab.sel_StatusChangeStatusDropdown);
		Assert.assertTrue(isDisplayed(FilesTab.sel_StatusChangeStatusDropdown));
		selectByVisibleText(FilesTab.sel_StatusChangeStatusDropdown, fileStatus);
		sendKeys(FilesTab.txt_StatusChangeReasonNote, javaUtils.getRandomString(10));
		clickElementAndWait(FilesTab.btn_PopFileStatusChangeStatusChange);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();

	}

	public void createCommentAndDistribute(String commnetTitle, String user) throws InterruptedException {

		waitUntilElementIsDisplayed(FilesTab.pop_StartNewDiscussion);
		sendKeys(FilesTab.txt_NewDiscussionToUserField, user);
		sendKeys(FilesTab.txt_NewDiscussionToUserField, Keys.TAB);
		sendKeys(FilesTab.txt_NewDiscussionTitleInput, commnetTitle + epoch);
		sendKeys(FilesTab.txt_NewDiscussionDescInput, javaUtils.getRandomString(10) + epoch);
		clickElement(FilesTab.btn_NewDiscussionSubmit);
		try {
			waitUntilElementIsDisplayed(FilesTab.lbl_FileAddCommentSuccessMsg);
		} catch (Throwable t) {
			log.error("Create comment success message verification failed");
		}
		waitForCompletePageLoad();

	}

	public void contextClickMenuOptions(String menuOption1, String menuOption2) {

		if (menuOption1.contains("Share") && menuOption2.contains("Distribute Files")) {
			Assert.assertTrue("Element is not Enabled", isEnabled(FilesTab.opt_FileContextClickShare));
			mouseHoverAndClickElement(FilesTab.opt_FileContextClickShare, FilesTab.opt_FileContextClickShareDistribute);
		}

		else if (menuOption1.contains("New") && menuOption2.contains("Discussion")) {

			Assert.assertTrue("Element is not Enabled", isEnabled(FilesTab.opt_FileContextClickNew));
			mouseHoverAndClickElement(FilesTab.opt_FileContextClickNew, FilesTab.opt_FileContextClickStartDiscussion);

		} else {
			Assert.assertTrue("Element is not Enabled", isEnabled(FilesTab.opt_FileContextClickEdit));
			if (menuOption2.contains("Status"))
				mouseHoverAndClickElement(FilesTab.opt_FileContextClickEdit, FilesTab.opt_FileContextClickEditStatus);
			else
				mouseHoverAndClickElement(FilesTab.opt_FileContextClickEdit,
						FilesTab.opt_FileContextClickEditAttributes);
		}

		waitUntilElementIsDisplayed(GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();

	}

	public void validatedWebFileFilteredList(String scenario) {

		waitUntilElementIsDisplayed(GlobalPageElements.pop_PopUpElement);
		if (scenario.contains("Revision Upload"))
			webFileList = findElements(FilesTab.pop_UploadDocumentList);

		else
			webFileList = findElements(FilesTab.pop_DocListingEditAttributesDocumentList);

		Assert.assertTrue("ExpectedWebFilelist size:: 10 But found:: " + webFileList.size(), webFileList.size() == 10);

	}

	public void editMultipleFileAttributes() {

		clickElementAndWait(FilesTab.chk_PopEditAttributesHeaderBulkApply);
		selectByVisibleText(FilesTab.drp_PopEditAttributesHeaderPurposeOfIssue,
				AdoddleCommonStringPool.STATUS_FOR_APPROVAL);
		clickElement(FilesTab.chk_EditAttributesHeaderPublishAsPrivate);
		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
		clickElementAndWait(FilesTab.chk_PopEditAttributesApplyAll);
		clickElementAndWait(FilesTab.btn_PopEditAttributesAssignAttributes);
		waitForCompletePageLoad();
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);

	}

	public void publishedAndValidateDocumentRevisions() {

		clickElementAndWait(FilesTab.chk_PopUploadFilesBulkApply);
		clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
		sendKeys(FilesTab.txt_PopUploadHeaderRevInput, "2");
		selectByIndex(FilesTab.drp_PopUploadHeaderPurposeOfIssue, 1);
		selectByIndex(FilesTab.drp_PopUploadHeaderStatus, 1);
		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
		clickElementAndWait(FilesTab.btn_PopUploadApplytoAll);
		waitForCompletePageLoad();
		validateDoucumentRevisions();

	}

	public void validateDoucumentRevisions() {

		log.info("WebFileList Size:: " + webFileList.size());
		for (String epoch : epochList) {
			searchFiles(epoch);
			webFileList = findElements(FilesTab.css_FilesListingRowList);
			for (WebElement ele : webFileList) {

				String webFileVersion = ele.findElement(By.cssSelector("div[class*='col-issueNo-fixed-width'] a"))
						.getText();
				log.info("FileVersion:: " + webFileVersion);

				if (epoch.contains(epoch1) || epoch.contains(epoch2) || epoch.contains(epoch3)
						|| epoch.contains(epoch4) || epoch.contains(epoch6) || epoch.contains(epoch7)) {

					Assert.assertTrue("ExpectedFileVersion:: 2 ", webFileVersion.contains("2"));

				} else {

					Assert.assertTrue("ExpectedFileVersion:: 1 ", webFileVersion.contains("1"));

				}
			}

		}

	}

	public void deactivateLockUnlockActivityFolder() {
		try {
			deactivateProjectFolder(System.getProperty("global.test.project"), folderName1);
		} catch (Throwable t) {
			log.error("ERROR: Unable to deactivate activity folder");
		}
	}
}