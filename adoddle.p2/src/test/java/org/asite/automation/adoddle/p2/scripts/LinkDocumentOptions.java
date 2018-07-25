package org.asite.automation.adoddle.p2.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.resources.AdoddleScenarioMarkers;
import org.asite.automation.common.utils.JavaUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class LinkDocumentOptions extends AdoddleCommonAppMethods {

	private boolean							newStatusCreationFlag		= false, uploadExternalFilesFlag = false;
	private static int						oldRevision					= 1;
	private static int						newRevision					= 1;
	private static String					currentPOI;
	private static String					parentWindow;
	private static String					updatedStatus;
	private static String					updatedPOI;
	private static String					withinStaticTargetFolderName1;
	private static String					withinDynamicTargetFolderName1;
	private static String					interStaticTargetFolderName1;
	/* private static String interDynamicTargetFolderName1; */
	private static String					withinConditionalDynamicTargetFolder;
	private static String					interConditionalDynamicTargetFolder;
	private static String					targetFolderName;
	private static String					statusTitle;
	private static String					currentEpoch;
	private static String					sourceStatus;
	private static String					sourcePurpose;
	private List<String>					docTitleStrings				= null;
	private static List<String>				fileList					= null;
	private static List<String>				updatedExternals			= null;
	private static List<String> 			externalAttachments			= null;
	private static String					dataConstantState			= "Maharashtra";
	private static String					dataConstantCity			= "Pune";
	private static HashMap<String, String>	actualAttributes			= new HashMap<String, String>();
	public static HashMap<String, String>	expectedAttributes			= new HashMap<String, String>();
	List<String>							statuses					= Arrays.asList(AdoddleCommonStringPool.TRIPLE_DASH, AdoddleCommonStringPool.FOR_INFORMATION, AdoddleCommonStringPool.FOR_REVIEW, AdoddleCommonStringPool.FOR_TRAINING);
	List<String>							revisionDistributionUsers	= Arrays.asList(ResourceHandler.loadProperty("test.user.tc.bloggs.name"), ResourceHandler.loadProperty("test.user.auto.pa.builder.name"));
	static List<String>						linkDistributionUsers		= new ArrayList<String>();
	public static Logger					log							= AutomationLogger.getInstance().getLogger(LinkDocumentOptions.class);

	public void createStaticLinkTestDataFolder(String l) {
		if (l.equalsIgnoreCase(AdoddleCommonStringPool.STRING_STATIC)) {
			withinStaticTargetFolderName1 = "StaticWithinLink_" + dateUtils.getEpoch();
			createProjectFolderMoreOptions(System.getProperty("global.test.project"), withinStaticTargetFolderName1);
			clickElementWithText(System.getProperty("global.test.project"));
			waitForElementWithText(withinStaticTargetFolderName1);
			collectionDataMap.put("static-link folder within project", withinStaticTargetFolderName1);
		}
		else if (l.equalsIgnoreCase(AdoddleCommonStringPool.STRING_DYNAMIC)) {
			withinDynamicTargetFolderName1 = "DynamicWithinLink_" + dateUtils.getEpoch();
			createProjectFolderMoreOptions(System.getProperty("global.test.project"), withinDynamicTargetFolderName1);
			clickElementWithText(System.getProperty("global.test.project"));
			waitForElementWithText(withinDynamicTargetFolderName1);
			collectionDataMap.put("dynamic-link folder within project", withinDynamicTargetFolderName1);

		}
		else {
			interStaticTargetFolderName1 = "InterStaticLink_" + dateUtils.getEpoch();
			createProjectFolderMoreOptions(l, interStaticTargetFolderName1);
			clickElementWithText(l);
			waitForElementWithText(interStaticTargetFolderName1);
			interConditionalDynamicTargetFolder = ResourceHandler.loadProperty("dynamic.link.target.folder");
			/* interDynamicTargetFolderName1 = "InterDynamicLink_" + dateUtils.getEpoch(); createProjectFolderMoreOptions(gProject, interDynamicTargetFolderName1); clickElementWithText(gProject); waitForElementWithText(interDynamicTargetFolderName1); */
			collectionDataMap.put("dynamic-link folder inter project", interConditionalDynamicTargetFolder);
			AdoddleScenarioMarkers.staticLinkDocumentOptionsFlag = true;
		}

	}

	public void createConditionalLinkTestDataFolder(String projectType) {
		if (projectType.equalsIgnoreCase("within")) {
			withinConditionalDynamicTargetFolder = "conditionalLink_" + dateUtils.getEpoch();
			createProjectFolderMoreOptions(System.getProperty("global.test.project"), withinConditionalDynamicTargetFolder);
			clickElementWithText(System.getProperty("global.test.project"));
			waitForElementWithText(withinConditionalDynamicTargetFolder);
			collectionDataMap.put("conditional dynamic-link folder within project", withinConditionalDynamicTargetFolder);

		}
		else if (projectType.equalsIgnoreCase("inter")) {
			interConditionalDynamicTargetFolder = ResourceHandler.loadProperty("dynamic.link.target.folder");
		}
		uploadExternalFilesFlag = true;
	}

	public void uploadMultipleDocuments(String prefix) throws NumberFormatException, InterruptedException, IOException {
		fileList = new ArrayList<String>();
		docTitleStrings = new ArrayList<String>();
		externalAttachments = new ArrayList<String>();
		currentEpoch = dateUtils.getEpoch();
		sysUtils.authenticateRemoteMachine(nodeIP);
		for (int index = 0; index < 4; index++) {
			fileList.add(sysUtils.createRemotePdfFile(getRandomPDFFileName(prefix), nodeIP));
			docTitleStrings.add("LinkTest" + AdoddleCommonStringPool.UNDERSCORE_STRING + currentEpoch);
			if (uploadExternalFilesFlag)
				externalAttachments.add(sysUtils.createFile(getRandomTextFileName(prefix)));
			else
				externalAttachments = null;
			waitUtils.waitInterval(1);
		}
		if(externalAttachments != null)
			log.info("Original external Attachments: "+externalAttachments.toString());
		fileList = uploadDocumentsWithCustomAttributes(fileList, 4, null, docTitleStrings, false, 1, null, null, true, null, null, externalAttachments, Float.parseFloat(JavaUtils.getRandomNumber(3)), null, null, null);
	}

	public String getRandomPDFFileName(String prefix) {
		return nodeIP + resourceUtils.getTestDataFilePath() + prefix + AdoddleCommonStringPool.STRING_LINK + dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION;
	}

	public String getRandomTextFileName(String prefix) {
		return nodeIP + resourceUtils.getTestDataFilePath() + prefix + AdoddleCommonStringPool.STRING_LINK + dateUtils.getEpoch() + AdoddleCommonStringPool.TXT_EXTENSION;
	}

	public void verifyFilesCountOnFilesListing(String docTitle) throws InterruptedException, IOException {
		searchFiles(currentEpoch);
		sourceStatus = getElementText(FilesTab.lbl_DocListingFirstStatus);
		sourcePurpose = getElementText(FilesTab.lbl_DocListingFirstPoi);
		AutomationAssert.verifyTrue(getCount(FilesTab.css_DocListingFilesCount) > 0);
		oldRevision = Integer.parseInt(getElementText(FilesTab.lnk_FileListingFirstRevision));
	}

	public void selectLinkDocument() {
		waitUntilElementIsDisplayed(FilesTab.chk_DocListingCheckAll);
		clickElementAndWait(FilesTab.chk_DocListingCheckAll);
	}

	public void selectLinkOptionFromMoreOption() throws InterruptedException {
		contextClick(FilesTab.lnk_DocListingFirstFileName);
		waitUntilElementIsDisplayed(FilesTab.opt_FileContextClickLinkFiles);
		mouseHover(FilesTab.opt_FileContextClickLinkFiles);
		clickElementAndWaitForElement(FilesTab.opt_FileContextClickLinkFiles, FilesTab.pop_TargetFolderModel);
	}

	public void clickClonedTestProjectOnPopup() {
		clickPopupElementWithText(clonedProject);
	}

	public void getClonedTestProjectClick() {
		clickElementWithText(clonedProject);
	}

	public void selectDestinationFolderAndClickSubmitButton(String linkType, String pType, String btnText) {

		if (pType.equalsIgnoreCase("within")) {
			clickPopupElementWithText(System.getProperty("global.test.project"));
			if (linkType.contains("Conditional"))
				selectFolderOnPopupWindow(withinConditionalDynamicTargetFolder);
			else if (linkType.contains(AdoddleCommonStringPool.STRING_STATIC))
				selectFolderOnPopupWindow(withinStaticTargetFolderName1);
			else if (linkType.contains(AdoddleCommonStringPool.STRING_DYNAMIC))
				selectFolderOnPopupWindow(withinDynamicTargetFolderName1);
		}
		else if (pType.equalsIgnoreCase("inter")) {
			clickClonedTestProjectOnPopup();
			if (linkType.contains("Conditional") || linkType.contains(AdoddleCommonStringPool.STRING_DYNAMIC))
				selectFolderOnPopupWindow(interConditionalDynamicTargetFolder);
			else if (linkType.contains(AdoddleCommonStringPool.STRING_STATIC))
				selectFolderOnPopupWindow(interStaticTargetFolderName1);
			/*
			 * else if (linkType.contains(AdoddleCommonStringPool.STRING_DYNAMIC)) selectFolderOnPopupWindow(interDynamicTargetFolderName1);
			 */
		}
		clickButtonWithText(btnText);
	}

	public void changeSourceFileStatus(boolean flag) {
		reloadPage();
		clickElementWithText(System.getProperty("global.test.project"));
		clickElementWithText(ResourceHandler.loadProperty("custom.attributes.file.upload.folder"));
		searchFiles(currentEpoch);
		String currentStatus = getElementText(FilesTab.lbl_DocListingFirstStatus);
		mouseHover(FilesTab.chk_DocListingCheckAll);
		clickElementAndWait(FilesTab.chk_DocListingCheckAll);
		clickElementAndWaitForElement(FilesTab.lnk_FilesMoreOptions, FilesTab.lnk_PopMoreOptionsStatusChange);
		clickElementAndWaitForElement(FilesTab.lnk_PopMoreOptionsStatusChange, FilesTab.pop_FilesActionBatchChangeStatus);
		if (newStatusCreationFlag)
			updatedStatus = performBatchStatusChangeWithOpts(statusTitle, false, false, flag, currentStatus);
		else
			updatedStatus = performBatchStatusChangeWithOpts(null, false, false, flag, currentStatus);
	}

	public void changeSourceFilePOI(boolean flag) {
		searchFiles(currentEpoch);
		clickElementAndWait(FilesTab.chk_DocListingCheckAll);
		contextClick(FilesTab.lnk_DocListingFirstDocRef);
		mouseHoverAndClickElement(FilesTab.opt_FileContextClickEdit, FilesTab.opt_FileContextClickEditAttributes);
		waitUntilElementIsDisplayed(FilesTab.chk_PopEditAttributesHeaderBulkApply);
		currentPOI = getSelectedDropdownLabel(findElements(FilesTab.css_PopEditAttributesPOI).get(0));
		/* updatedPOI = performMultiDocumentEditPOI(currentPOI, null, flag); */
		log.info("Existing Purpose of Issue before edit attributes operation: " + currentPOI);
		Map<String, String> updatedMap = editCustomAttributes(null, Integer.parseInt(getValue(findElements(FilesTab.css_PopEditAttributesRevisions).get(0))), currentPOI, flag);
		updatedPOI = updatedMap.get("poi");

	}

	public void verifyUpdatedStatusInTargetFile(String pType, String lType, boolean flag) {
		if (pType.equalsIgnoreCase("within") && lType.equalsIgnoreCase(AdoddleCommonStringPool.STRING_STATIC)) {
			clickElementWithText(System.getProperty("global.test.project"));
			clickElementWithText(withinStaticTargetFolderName1);
		}
		else if (pType.equalsIgnoreCase("within") && lType.equalsIgnoreCase(AdoddleCommonStringPool.STRING_DYNAMIC)) {
			clickElementWithText(System.getProperty("global.test.project"));
			clickElementWithText(withinDynamicTargetFolderName1);
		}
		else if (pType.equalsIgnoreCase("inter") && lType.equalsIgnoreCase(AdoddleCommonStringPool.STRING_STATIC)) {
			clickElementWithText(clonedProject);
			clickElementWithText(interStaticTargetFolderName1);
		}
		else if (pType.equalsIgnoreCase("inter") && lType.equalsIgnoreCase(AdoddleCommonStringPool.STRING_DYNAMIC)) {
			clickElementWithText(clonedProject);
			clickElementWithText(interConditionalDynamicTargetFolder);
		}

		if (flag)
			AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getElementText(FilesTab.lbl_DocListingFirstStatus), updatedStatus), getElementText(FilesTab.lbl_DocListingFirstStatus).trim().equalsIgnoreCase(updatedStatus.trim()));

		if (newStatusCreationFlag)
			verifyStatusIsDeactivated(updatedStatus);
	}

	public void verifyStatusIsDeactivated(String status) {
		clickElementAndWaitForElement(FilesTab.btn_AddFiles, FilesTab.pop_UploadFile);
		uploadFileUsingKeys(FilesTab.btn_SelectFiles, sysUtils.getFileList(ResourceHandler.loadProperty("multi.files.path")));
		waitUntilElementIsDisplayed(FilesTab.chk_PopUploadFilesBulkApply);
		clickElementAndWaitForElement(FilesTab.chk_PopUploadFilesBulkApply, FilesTab.drp_PopUploadHeaderStatus);
		clickElementAndWait(FilesTab.drp_PopUploadHeaderStatus);
		List<WebElement> headerStatusOptions = findElement(FilesTab.drp_PopUploadHeaderStatus).findElements(By.tagName("option"));
		for (WebElement e : headerStatusOptions) {
			log.info("Status Option# " + e.getText());
			AutomationAssert.verifyTrue(eStringUtils.getEqualityStringError(e.getText(), statusTitle), !e.getText().trim().equalsIgnoreCase(statusTitle));
		}

	}

	public void verifyUpdatedPoiInTargetFile(String pType, String lType, boolean flag) {
		if (pType.equalsIgnoreCase("within") && lType.equalsIgnoreCase(AdoddleCommonStringPool.STRING_STATIC))
			clickElementWithText(withinStaticTargetFolderName1);
		else if (pType.equalsIgnoreCase("within") && lType.equalsIgnoreCase(AdoddleCommonStringPool.STRING_DYNAMIC))
			clickElementWithText(withinDynamicTargetFolderName1);
		else if (pType.equalsIgnoreCase("inter") && lType.equalsIgnoreCase(AdoddleCommonStringPool.STRING_STATIC)) {
			clickElementWithText(clonedProject);
			clickElementWithText(interStaticTargetFolderName1);
		}
		else if (pType.equalsIgnoreCase("inter") && lType.equalsIgnoreCase(AdoddleCommonStringPool.STRING_DYNAMIC)) {
			clickElementWithText(clonedProject);
			clickElementWithText(interConditionalDynamicTargetFolder);
		}

		List<WebElement> docListingPoiList = findElements(FilesTab.css_DocListingPurposeOfIssuesList);

		if (flag) {
			for (WebElement e : docListingPoiList)
				AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getElementText(e), updatedPOI), getElementText(e).equalsIgnoreCase(updatedPOI));
		}
		else {
			for (WebElement e : docListingPoiList)
				AutomationAssert.verifyTrue(eStringUtils.getEqualityStringError(getElementText(e), currentPOI), (getElementText(e).equalsIgnoreCase(currentPOI)));
		}
	}

	public void deactivateTestDataFolder(String lType) {
		if (lType.equalsIgnoreCase(AdoddleCommonStringPool.STRING_STATIC)) {
			waitForElementWithText(withinStaticTargetFolderName1);
			clickElementWithText(withinStaticTargetFolderName1);
			try {
				AutomationAssert.verifyTrue(getCount(FilesTab.css_FilesListingRecords) == 4);
				deactivateFile(currentEpoch);
			}
			catch (Throwable t) {
				log.info("ERROR: failure while deactivating files");
			}
			deactivateProjectFolder(System.getProperty("global.test.project"), withinStaticTargetFolderName1);
		}

		if (lType.equalsIgnoreCase(AdoddleCommonStringPool.STRING_DYNAMIC)) {
			waitForElementWithText(withinDynamicTargetFolderName1);
			clickElementWithText(withinDynamicTargetFolderName1);
			try {
				AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getCount(FilesTab.css_FilesListingRecords), 4), getCount(FilesTab.css_FilesListingRecords) == 4);
				deactivateFile(currentEpoch);
			}
			catch (Throwable t) {
				log.info("ERROR: failure while deactivating files");
			}
			deactivateProjectFolder(System.getProperty("global.test.project"), withinDynamicTargetFolderName1);
		}

		if (lType.equalsIgnoreCase("Conditional")) {
			clickElementWithText(withinConditionalDynamicTargetFolder);
			try {
				try {
					deactivateFile(currentEpoch);
				}
				catch (Throwable t) {
					log.info("ERROR: failure while deactivating conditional link files");
				}
				deactivateProjectFolder(System.getProperty("global.test.project"), withinConditionalDynamicTargetFolder);
			}
			catch (Throwable t) {
				log.error("ERROR: failure while deactivating files");
			}
		}

	}

	public void deactivateClonedProject() {
		deactivateInheritanceProject(clonedProject);
	}

	public void createDocumentTestStatus() {
		statusTitle = AdoddleCommonStringPool.STRING_DYNAMIC + AdoddleCommonStringPool.STRING_LINK + AdoddleCommonStringPool.UNDERSCORE_STRING + dateUtils.getEpoch();
		clickElementAndWaitForElement(ProjectsTab.btn_AddNewStatus, ProjectsTab.css_AddPOIAndStatusTextList);
		sendKeys(ProjectsTab.css_AddPOIAndStatusTextList, statusTitle);
		clickElementAndWaitUntilElementInvisible(ProjectsTab.btn_PopManageFormStatusesSave, GlobalPageElements.pop_PopUpElement);
		newStatusCreationFlag = true;
	}

	public void selectDynamicLinkCondition(String condition, String status) {
		selectByVisibleText(FilesTab.drp_PopLinkFilesLinkType, AdoddleCommonStringPool.STRING_DYNAMIC);
		if (!getSelectedDropdownLabel(FilesTab.drp_PopLinkDynamicCriteriaAlwaysStatus).equalsIgnoreCase(AdoddleCommonStringPool.STATUS)) {
			selectByVisibleText(FilesTab.drp_PopLinkDynamicCriteriaAlwaysStatus, AdoddleCommonStringPool.STATUS);
			selectByVisibleText(FilesTab.drp_PopLinkDyanmicCriteriaFirstCondition, condition);
			selectByVisibleText(FilesTab.drp_PopLinkDyanmicCriteriaFirstStatus, status);
		}
		else {
			clickElementAndWait(FilesTab.btn_PopLinkDynamicAddCriteria);
			selectByVisibleText(FilesTab.drp_PopLinkDyanmicCriteriaLastCondition, condition);
			selectByVisibleText(FilesTab.drp_PopLinkDyanmicCriteriaLastStatus, status);
		}

	}

	public void uploadRevisionDocuments(boolean flag) throws InterruptedException, IOException {
		updatedExternals = null;
		if (flag) {
			updatedExternals = new ArrayList<String>();
			sysUtils.authenticateRemoteMachine(nodeIP);
			for (int index = 0; index < fileList.size(); index++) {
				updatedExternals.add(sysUtils.createFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.TXT_EXTENSION));
				log.info("Created External files as: "+updatedExternals.toString());
				waitUtils.waitInterval(1);
			}
		}
		searchFiles(strUtils.extractFileNameString(fileList.get(0)));
		uploadDocumentsWithCustomAttributes(fileList, 4, null, docTitleStrings, false, oldRevision + 1, Arrays.asList(AdoddleCommonStringPool.FOR_REVIEW), statuses, true, Arrays.asList(dataConstantState), Arrays.asList(dataConstantCity), updatedExternals, Float.parseFloat(JavaUtils.getRandomNumber(2)), null, null, linkDistributionUsers);
		oldRevision = oldRevision + 1;
		newRevision = oldRevision;

		if (flag) {
			parentWindow = clickElementAndSwitchWindow(FilesTab.lnk_DocListingFirstDocRef);
			expectedAttributes = getDocumentCustomAttributes();
			log.info("Expected Attributes# " + expectedAttributes.toString());
			closeCurrentWindow();
			switchPreviousWindow(parentWindow);
		}
	}

	public void verifyUpdatedRevisionDocuments(String criteria, String pType) throws InterruptedException {

		if (pType.equalsIgnoreCase("within") && criteria.equalsIgnoreCase("revision")) {
			clickElementWithText(System.getProperty("global.test.project"));
			clickElementWithText(withinConditionalDynamicTargetFolder);
			searchFiles(strUtils.extractFileNameString(fileList.get(0)));
			log.info("Updated Revision Document: " + fileList.get(0));
			AutomationAssert.verifyTrue(Integer.parseInt(getElementText(FilesTab.lnk_FileListingFirstRevision)) + " should match " + (newRevision), Integer.parseInt(getElementText(FilesTab.lnk_FileListingFirstRevision)) == (newRevision));
			parentWindow = getCurrentWindow();
			clickElementAndSwitchWindow(FilesTab.img_DocListingFirstAttachmentIcon);
			log.info("Updated External Document: "+updatedExternals.get(0));
			waitForHTMLFileView(strUtils.extractFileNameString(updatedExternals.get(0)));
			/*switchIframe(FilesTab.frm_BravaObjectFrame);
			if(!fileBetaViewFlag)
				AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getElementText(FilesTab.ele_ViewAttachFileName2), strUtils.extractFileNameString(updatedExternals.get(0))), getElementText(FilesTab.ele_ViewAttachFileName2).equalsIgnoreCase(strUtils.extractFileNameString(updatedExternals.get(0))));
			else
				AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getElementText(FilesTab.ele_ViewAttachFileName), strUtils.extractFileNameString(updatedExternals.get(0))), getElementText(FilesTab.ele_ViewAttachFileName).equalsIgnoreCase(strUtils.extractFileNameString(updatedExternals.get(0))));*/
			closeCurrentWindow();
			switchPreviousWindow(parentWindow);
			searchFiles(strUtils.extractFileNameString(fileList.get(1)));
			log.info("Updated Revision Document: " + fileList.get(1));
			AutomationAssert.verifyTrue(Integer.parseInt(getElementText(FilesTab.lnk_FileListingFirstRevision)) + " should match " + (newRevision), Integer.parseInt(getElementText(FilesTab.lnk_FileListingFirstRevision)) == (newRevision));
		}
		else if (pType.equalsIgnoreCase("inter") && criteria.equalsIgnoreCase("attributes")) {
			navigateTab(LandingPage.lnk_Files);
			clickElementWithText(clonedProject);
			clickElementWithText(interConditionalDynamicTargetFolder);
			verifyUpdatedCustomAttributes(true);
		}

	}

	public void verifyDistributionUsers(String pType) throws InterruptedException {
		List<WebElement> distributionUserElements = new ArrayList<WebElement>();
		
		if (pType.equalsIgnoreCase("within")) {
			clickElementWithText(System.getProperty("global.test.project"));
			clickElementWithText(withinConditionalDynamicTargetFolder);
			searchFiles(strUtils.extractFileNameString(fileList.get(0)));
			clickElementAndWait(FilesTab.chk_DocListingFirstCheckBox);
			clickElementAndWaitForElement(FilesTab.lnk_FilesTabMoreOptions, FilesTab.lnk_PopMoreOptionsDistributeFiles);
			clickElementAndWaitForElement(FilesTab.lnk_PopMoreOptionsDistributeFiles, FilesTab.pop_FilesActionForDistribute);
			performDefaultDistribution(revisionDistributionUsers);
			parentWindow = getCurrentWindow();
			waitUntilElementIsDisplayed(FilesTab.lnk_DocListingFirstDocRef);
			waitForCompletePageLoad();
			contextClick(FilesTab.lnk_DocListingFirstDocRef);
			mouseHoverAndClickElement(FilesTab.opt_FileContextClickHistory, FilesTab.opt_FileContextClickHistoryDistribution);
			waitForSwitchWindow(2);
			switchWindow();
			if(!fileBetaViewFlag) {
				waitUntilElementIsDisplayed(FilesTab.drp_FileviewHistoryRevDropdown);
				selectByIndex(FilesTab.drp_FileviewHistoryRevDropdown, 1);
				waitUntilElementIsDisplayed(FilesTab.ele_FileHistoryFirstRecord);
				clickElementAndWaitForElement(FilesTab.ele_FileHistoryFirstRecord, FilesTab.drp_FileHistoryRecordDetailActionsDropdown);
				waitUntilElementCountToBeMoreThan(FilesTab.img_FileHistoryRecordDetailDitributionUser, 1);
				distributionUserElements = findElements(FilesTab.img_FileHistoryRecordDetailDitributionUser);
			} else {
				waitUntilElementIsDisplayed(FilesTab.ele_BetaFileViewHistoryPanel);
				waitUntilElementCountToBeMoreThan(FilesTab.css_BetaViewFileHistoryRecordDetailsDistributionRows, 1);
				for(WebElement e: findElements(FilesTab.css_BetaViewFileHistoryRecordDetailsDistributionRows)) 
					distributionUserElements.add(e.findElement(FilesTab.lnk_BetaViewFileHistoryRecordDetailDitributionUser));
			}
			for (WebElement e : distributionUserElements) {
				String user = null;
				user = (strUtils.splitString(!fileBetaViewFlag ? e.getAttribute("title"):e.getText(), "\\r?\\n").get(0));
				log.info("Distribution User: " + user);
				log.info("Link User List: " + linkDistributionUsers.toString());
				log.info("Revision Distribution User List: " + linkDistributionUsers.toString());
				AutomationAssert.verifyTrue("Distribution User missing: " + user, linkDistributionUsers.contains(user) || revisionDistributionUsers.contains(user));
			}
			if(!fileBetaViewFlag)
				AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getCount(FilesTab.img_FileHistoryRecordDetailDitributionUser), linkDistributionUsers.size() + revisionDistributionUsers.size()), getCount(FilesTab.img_FileHistoryRecordDetailDitributionUser) == linkDistributionUsers.size() + revisionDistributionUsers.size());
			else
				AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getCount(FilesTab.css_BetaViewFileHistoryRecordDetailsDistributionRows), linkDistributionUsers.size() + revisionDistributionUsers.size()), getCount(FilesTab.css_BetaViewFileHistoryRecordDetailsDistributionRows) == linkDistributionUsers.size() + revisionDistributionUsers.size());
			closeCurrentWindow();
			switchPreviousWindow(parentWindow);
		}
	}

	public void verifyNotUpdatedRevisionDocuments(String criteria, String pType) throws InterruptedException {
		if (pType.equalsIgnoreCase("within") && criteria.equalsIgnoreCase("revision")) {

			searchFiles(strUtils.extractFileNameString(fileList.get(2)));
			AutomationAssert.verifyTrue(getElementText(FilesTab.lnk_FileListingFirstRevision) + " should not match " + newRevision, Integer.parseInt(getElementText(FilesTab.lnk_FileListingFirstRevision)) != newRevision);
			parentWindow = getCurrentWindow();
			clickElementAndSwitchWindow(FilesTab.img_DocListingFirstAttachmentIcon);
			waitForHTMLFileView();
			String objectID = getElementAttributeValue(FilesTab.frm_BravaObjectFrame, "id");
			if(fileBetaViewFlag) {
				log.info("Actual external file name on HTML Viewer: "+(String)((JavascriptExecutor) getWebDriver()).executeScript("return document.getElementById('" + objectID + "').contentDocument.getElementById('dijit_layout_ContentPane_1').textContent"));
				log.info("Expected external file name : "+updatedExternals.get(2));
				log.info("Original external file name : "+externalAttachments.get(2));
				AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(updatedExternals.get(2), ((String)((JavascriptExecutor) getWebDriver()).executeScript("return document.getElementById('" + objectID + "').contentDocument.getElementById('dijit_layout_ContentPane_1').textContent"))), !(updatedExternals.get(2).equalsIgnoreCase((String)((JavascriptExecutor) getWebDriver()).executeScript("return document.getElementById('" + objectID + "').contentDocument.getElementById('dijit_layout_ContentPane_1').textContent"))));
			}
			else {
				log.info("Actual external file name on HTML Viewer: "+(String)((JavascriptExecutor) getWebDriver()).executeScript("return document.getElementById('" + objectID + "').contentDocument.getElementById('dijit_layout_ContentPane_1').textContent"));
				log.info("Expected external file name : "+updatedExternals.get(2));
				log.info("Original external file name : "+externalAttachments.get(2));
				AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(updatedExternals.get(2), ((String)((JavascriptExecutor) getWebDriver()).executeScript("return document.getElementById('" + objectID + "').contentDocument.getElementById('app_widget_ToolPropertiesMenuBar_0').getElementsByClassName('titleText')[0].textContent"))),!(updatedExternals.get(2).equalsIgnoreCase((String)((JavascriptExecutor) getWebDriver()).executeScript("return document.getElementById('" + objectID + "').contentDocument.getElementById('app_widget_ToolPropertiesMenuBar_0').getElementsByClassName('titleText')[0].textContent"))));
			}
			
			/*if(!fileBetaViewFlag)
				AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getElementText(FilesTab.ele_ViewAttachFileName2), strUtils.extractFileNameString(updatedExternals.get(0)), false), !getElementText(FilesTab.ele_ViewAttachFileName2).equalsIgnoreCase(strUtils.extractFileNameString(updatedExternals.get(2))));
			else
				AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getElementText(FilesTab.ele_ViewAttachFileName), strUtils.extractFileNameString(updatedExternals.get(0)), false), !getElementText(FilesTab.ele_ViewAttachFileName).equalsIgnoreCase(strUtils.extractFileNameString(updatedExternals.get(2))));*/
			
			closeCurrentWindow();
			switchPreviousWindow(parentWindow);
			searchFiles(strUtils.extractFileNameString(fileList.get(3)));
			AutomationAssert.verifyTrue(getElementText(FilesTab.lnk_FileListingFirstRevision) + " should not match " + newRevision, Integer.parseInt(getElementText(FilesTab.lnk_FileListingFirstRevision)) != newRevision);
		}
		else if (pType.equalsIgnoreCase("inter") && criteria.equalsIgnoreCase("attributes")) {
			clickElementWithText(clonedProject);
			clickElementWithText(interConditionalDynamicTargetFolder);
			verifyUpdatedCustomAttributes(false);
		}
	}

	public void hasTargetFolderLinkDocument(String linkType, String pType) {
		if (pType.equalsIgnoreCase("within")) {
			clickElementWithText(System.getProperty("global.test.project"));
			if (linkType.contains("Conditional"))
				clickElementWithText(withinConditionalDynamicTargetFolder);
			else if (linkType.equalsIgnoreCase(AdoddleCommonStringPool.STRING_STATIC))
				clickElementWithText(withinStaticTargetFolderName1);
			else if (linkType.equalsIgnoreCase(AdoddleCommonStringPool.STRING_DYNAMIC))
				clickElementWithText(withinDynamicTargetFolderName1);
		}
		else if (pType.equalsIgnoreCase("inter")) {
			navigateTab(LandingPage.lnk_Files);
			clickElementWithText(clonedProject);
			if (linkType.contains("Conditional") || linkType.equalsIgnoreCase(AdoddleCommonStringPool.STRING_DYNAMIC))
				clickElementWithText(interConditionalDynamicTargetFolder);
			else if (linkType.equalsIgnoreCase(AdoddleCommonStringPool.STRING_STATIC))
				clickElementWithText(interStaticTargetFolderName1);
			/*
			 * else if (linkType.equalsIgnoreCase(AdoddleCommonStringPool.STRING_DYNAMIC)) clickElementWithText(interDynamicTargetFolderName1);
			 */
		}

		searchFiles(currentEpoch);
		List<WebElement> linkedFileList = findElements(FilesTab.css_DocListingDocTitleList);
		for (WebElement e : linkedFileList)
			AutomationAssert.verifyTrue(getElementText(e).contains(currentEpoch));

		if (linkType.equalsIgnoreCase(AdoddleCommonStringPool.STRING_STATIC)) {
			List<WebElement> staticLinkImageList = findElements(FilesTab.css_DocListingStaticLinkImage);
			AutomationAssert.verifyTrue(staticLinkImageList.size() == 4);
		}
		else if (linkType.equalsIgnoreCase(AdoddleCommonStringPool.STRING_DYNAMIC) || linkType.equalsIgnoreCase("Conditional")) {
			List<WebElement> dynamicLinkImageList = findElements(FilesTab.css_DocListingDynamicLinkImage);
			AutomationAssert.verifyTrue(dynamicLinkImageList.size() == 4);
		}
	}

	public void selectUserAndClickButton(String linkType) throws InterruptedException {
		waitUntilElementIsDisplayed(FilesTab.txt_PopLinkFilesToField);
		sendKeys(FilesTab.txt_PopLinkFilesToField, System.getProperty("secondary.username"));
		sendKeys(FilesTab.txt_PopLinkFilesToField, Keys.TAB);
		selectByVisibleText(FilesTab.drp_PopLinkFilesLinkType, linkType);
		List<WebElement> linkPurposes = findElements(By.cssSelector("div[id='linkDocumentModal'] table[class='linkDocListTable'] tbody select[class*='ld_poi']"));
		List<WebElement> linkStatuses = findElements(By.cssSelector("div[id='linkDocumentModal'] table[class='linkDocListTable'] tbody select[class*='ld_status']"));
		
		for(WebElement e: linkPurposes) {
			selectByVisibleText(e, sourcePurpose);
		}
		
		for(WebElement e: linkStatuses) {
			selectByVisibleText(e, sourceStatus);
		}
		
		clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_PopLinkFilesSubmit, GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();
	}

	public void selectUserAndClickButton(String user1, String user2, String linkType) throws InterruptedException {
		linkDistributionUsers = listUtils.getArrayList(user1, user2);
		waitUntilElementIsDisplayed(FilesTab.txt_PopLinkFilesToField);
		sendKeys(FilesTab.txt_PopLinkFilesToField, user1);
		sendKeys(FilesTab.txt_PopLinkFilesToField, Keys.TAB);
		sendKeys(FilesTab.txt_PopLinkFilesToField, user2);
		sendKeys(FilesTab.txt_PopLinkFilesToField, Keys.TAB);
		selectByVisibleText(FilesTab.drp_PopLinkFilesLinkType, linkType);
		clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_PopLinkFilesSubmit, GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();
	}

	@Override
	public void deactivateFile(String fileName) {
		if (getCount(FilesTab.css_DocListingFilesCount) > 0) {
			clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);
			clickElementAndWaitForElement(FilesTab.lnk_FilesTabMoreOptions, FilesTab.lnk_PopFileOptionsDeactivateFiles);
			clickElementAndWaitForElement(FilesTab.lnk_PopFileOptionsDeactivateFiles, FilesTab.chk_PopDeactivateFilesEntireFile);
			clickElementAndWaitForElement(FilesTab.chk_PopDeactivateFilesEntireFile, FilesTab.btn_PopDeactivateFilesDeactivate);
			clickElementAndWaitForElement(FilesTab.btn_PopDeactivateFilesDeactivate, FilesTab.lbl_PopDeactivatedFileSuccessMsg);
			clickElementAndWait(FilesTab.btn_PopDeactivatedFileSuccessOk);
		}
	}

	public void verifyLinkDocumentIsAvailableInTargetFolder() {
		clickElementWithText(withinConditionalDynamicTargetFolder);
		AutomationAssert.verifyTrue(eStringUtils.getElementCountError(FilesTab.css_DocListingFilesCount, 1, 0), getCount(FilesTab.css_DocListingFilesCount) > 0);
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(Integer.parseInt(getElementText(FilesTab.lnk_FileListingFirstRevision)), oldRevision), Integer.parseInt(getElementText(FilesTab.lnk_FileListingFirstRevision)) == oldRevision);
	}

	/**** Common Methods ***/

	public void verifyActionAssignedToSelectedUser() throws IOException, InterruptedException {
		logOut();
		login(System.getProperty("secondary.username"), System.getProperty("secondary.password"));
		navigateTab(LandingPage.lnk_Files);
		clickElementWithText(System.getProperty("global.test.project"));
		clickElementWithText(targetFolderName);
		searchFiles(currentEpoch);
		AutomationAssert.verifyTrue(getElementText(FilesTab.lnk_FilesFirstAction).equalsIgnoreCase(AdoddleCommonStringPool.FOR_INFORMATION));
	}

	public void verifyUpdatedCustomAttributes(boolean flag) {
		if (flag) {
			searchFiles(strUtils.extractFileNameString(fileList.get(0)));
			collectionDataMap.put("Updated Attribute File", strUtils.extractFileNameString(fileList.get(0)));
			log.info("Updated Attribute File Name: " + strUtils.extractFileNameString(fileList.get(0)));
			parentWindow = clickElementAndSwitchWindow(FilesTab.lnk_DocListingFirstDocRef);
			actualAttributes = getDocumentCustomAttributes();
			AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(actualAttributes.toString(), expectedAttributes.toString()), compareUnorderedMaps(actualAttributes, expectedAttributes));
			closeCurrentWindow();
			switchPreviousWindow(parentWindow);
		}
		else {
			searchFiles(strUtils.extractFileNameString(fileList.get(2)));
			log.info("Not updated Attribute File Name: " + strUtils.extractFileNameString(fileList.get(2)));
			parentWindow = clickElementAndSwitchWindow(FilesTab.lnk_DocListingFirstDocRef);
			actualAttributes = getDocumentCustomAttributes();
			log.info(actualAttributes.toString());
			log.info(expectedAttributes.toString());
			AutomationAssert.verifyTrue(!compareUnorderedMaps(actualAttributes, expectedAttributes));
			closeCurrentWindow();
			switchPreviousWindow(parentWindow);
		}
	}

}
