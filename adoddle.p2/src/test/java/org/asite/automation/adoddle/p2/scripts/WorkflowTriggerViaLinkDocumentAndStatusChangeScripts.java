/**  Testdata required for this script as follows.
     1). Workflow Def : Automation_WF_P2_LinkDocument
     2). Workflow Trigger: Automation_WF_P2_LinkDocument_Trigger  with docStatus as "For Approval" and poi as "For Information"
     3). Clear Action System Task
 */

package org.asite.automation.adoddle.p2.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleWorkflowsLocators.WorkflowsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.JavaUtils;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class WorkflowTriggerViaLinkDocumentAndStatusChangeScripts extends AdoddleCommonAppMethods {

	private String				currentWindowHandle	= null;
	private String				LinkFileDocRef1		= "S-LinkFile1_" + epoch1;
	private String				LinkFileDocRef2		= "S-LinkFile2_" + epoch1;
	private String				LinkFileDocRef3		= "S-LinkFile3_" + epoch1;
	private String				LinkFileDocRef4		= "S-LinkFile4_" + epoch1;
	private String				LinkFileDocRef5		= "S-LinkScenario2File1_" + epoch2;
	private String				LinkFileDocRef6		= "S-LinkScenario2File2_" + epoch2;
	private String				LinkFileDocRef7		= "D-LinkFile1_" + epoch3;
	private String				LinkFileDocRef8		= "D-LinkFile2_" + epoch3;
	private String				LinkFileDocRef9		= "D-LinkFile3_" + epoch3;
	private String				LinkFileDocRef10	= "D-LinkFile4_" + epoch3;
	private String				StatusFileDocRef1	= "S-ChangeFile1_" + epoch1;
	private String				StatusFileDocRef2	= "S-ChangeFile2_" + epoch1;
	private String				StatusFileDocRef3	= "S-ChangeFile3_" + epoch1;
	private String				StatusFileDocRef4	= "S-ChangeFile4_" + epoch1;
	private String				userName			= ResourceHandler.loadProperty("test.user.rfi.builder.name");

	private static String		folderName1, folderName2, folderName3;
	private static String		epoch1				= JavaUtils.getRandomNumber(14);
	private static String		epoch2				= JavaUtils.getRandomNumber(14);
	private static String		epoch3				= JavaUtils.getRandomNumber(14);

	private static String		searchEpoch1		= "S-LinkScenario1_" + epoch1;
	private static String		searchEpoch2		= "S-LinkScenario2_" + epoch2;
	private static String		searchEpoch3		= "D-LinkScenario3_" + epoch3;

	private static List<String>	epochList			= new ArrayList<String>();

	private List<String>		List1, List2, List3;
	private List<String>		fileList			= new ArrayList<String>();
	private List<String>		docStatusList, docPoiList, fileDocRefs, customDocTitleList;

	private List<WebElement>	docRefList, myActionList;

	public static Logger		log					= AutomationLogger.getInstance().getLogger(WorkflowTriggerViaLinkDocumentAndStatusChangeScripts.class);

	public void clickOnProjectName(String projectName) {
		log.info("Project Name" + projectName);
		waitForCompletePageLoad();
		contextClickWithText(projectName);
		waitUntilElementIsDisplayed(FilesTab.opt_ProjectContextClickNew);
	}

	public void clickOncreateNewFolder() {
		mouseHoverAndClickElement(FilesTab.opt_ProjectContextClickNew, FilesTab.opt_ProjectContextClickFolder);
		waitForCompletePageLoad();
	}

	public void createFolder(String folder) {
		if (folder.contains("WF_LinkDocumentPrimaryFolder")) {
			folderName1 = folder + dateUtils.getEpoch();
			collectionDataMap.put("Folder1:: ", folderName1);
			waitForCompletePageLoad();
			sendKeys(FilesTab.txt_PopCreateFolderFolderName, folderName1);
		}

		else if (folder.contains("WF_LinkDocumentSecondryFolder")) {
			folderName2 = folder + dateUtils.getEpoch();
			collectionDataMap.put("Folder2:: ", folderName2);
			waitForCompletePageLoad();
			sendKeys(FilesTab.txt_PopCreateFolderFolderName, folderName2);
		}
		else {
			folderName3 = folder + dateUtils.getEpoch();
			collectionDataMap.put("Folder3:: ", folderName3);
			waitForCompletePageLoad();
			sendKeys(FilesTab.txt_PopCreateFolderFolderName, folderName3);
		}

		clickElementAndWait(FilesTab.btn_PopCreateFolderCreate);
		epochList = sysUtils.getFileList(searchEpoch1 + "," + searchEpoch2 + "," + searchEpoch3);

	}

	public void verifyFolderName(String folderName) throws InterruptedException {

		navigateTab(LandingPage.lnk_Files);

		if (folderName.contains("WF_LinkDocumentPrimaryFolder")) {
			log.info("Folder: " + folderName1);
			waitForElementWithText(folderName1);
		}
		else if (folderName.contains("WF_LinkDocumentSecondryFolder")) {
			log.info("Folder: " + folderName2);
			waitForElementWithText(folderName2);
		}
		else {
			log.info("Folder: " + folderName3);
			waitForElementWithText(folderName3);
		}

	}

	public void selectFilesAndUpload(String scenarioName) throws IOException, InterruptedException {

		if (scenarioName.contains("LinkDocument")) {

			docStatusList = new ArrayList<String>(Arrays.asList(AdoddleCommonStringPool.TRIPLE_DASH, AdoddleCommonStringPool.TRIPLE_DASH, AdoddleCommonStringPool.TRIPLE_DASH, AdoddleCommonStringPool.TRIPLE_DASH, AdoddleCommonStringPool.TRIPLE_DASH, AdoddleCommonStringPool.STATUS_FOR_APPROVAL, AdoddleCommonStringPool.STATUS_FOR_APPROVAL, AdoddleCommonStringPool.STATUS_FOR_REVIEW, AdoddleCommonStringPool.STATUS_FOR_REVIEW, AdoddleCommonStringPool.STATUS_FOR_TRAINING));

			docPoiList = new ArrayList<String>(Arrays.asList(AdoddleCommonStringPool.TRIPLE_DASH, AdoddleCommonStringPool.TRIPLE_DASH, AdoddleCommonStringPool.TRIPLE_DASH, AdoddleCommonStringPool.TRIPLE_DASH, AdoddleCommonStringPool.POI_FOR_INFORMATION, AdoddleCommonStringPool.TRIPLE_DASH, AdoddleCommonStringPool.POI_FOR_INFORMATION, AdoddleCommonStringPool.POI_FOR_INFORMATION, AdoddleCommonStringPool.POI_FOR_APPROVAL, AdoddleCommonStringPool.POI_FOR_REVIEW));

			fileDocRefs = new ArrayList<String>(Arrays.asList(LinkFileDocRef1, LinkFileDocRef2, LinkFileDocRef3, LinkFileDocRef4, LinkFileDocRef5, LinkFileDocRef6, LinkFileDocRef7, LinkFileDocRef8, LinkFileDocRef9, LinkFileDocRef10));

			List1 = sysUtils.getFileList(LinkFileDocRef1 + "," + LinkFileDocRef2 + "," + LinkFileDocRef3 + "," + LinkFileDocRef4);

			List2 = sysUtils.getFileList(LinkFileDocRef5 + "," + LinkFileDocRef6);

			uploadDocuments(null, 10, fileDocRefs, null, false, 1, docPoiList, docStatusList, null, null);

		}

		else if (scenarioName.contains("StatusChange")) {

			List<String> distributionUserList = new ArrayList<String>(Arrays.asList(ResourceHandler.loadProperty("test.user.rfi.bloggs.name")));

			List<String> actionUserList = new ArrayList<String>(Arrays.asList(AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE));

			fileDocRefs = new ArrayList<String>(Arrays.asList(StatusFileDocRef1, StatusFileDocRef2, StatusFileDocRef3, StatusFileDocRef4));

			List3 = sysUtils.getFileList(StatusFileDocRef1 + "," + StatusFileDocRef2 + "," + StatusFileDocRef3 + "," + StatusFileDocRef4);

			uploadDocuments(null, 4, fileDocRefs, null, false, 1, null, null, distributionUserList, actionUserList);

		}

		else {

			customDocTitleList = sysUtils.getFileList("CusttomAttribute1_" + epoch1 + "," + "CusttomAttribute2_" + epoch1 + "," + "CusttomAttribute3_" + epoch1 + "," + "CusttomAttribute4_" + epoch1);

			List<String> statesList = new ArrayList<String>(Arrays.asList(AdoddleCommonStringPool.CUSTOM_ATTRIBUTE_STATE_GUJARAT, AdoddleCommonStringPool.CUSTOM_ATTRIBUTE_STATE_RAJASTHAN, AdoddleCommonStringPool.CUSTOM_ATTRIBUTE_STATE_GUJARAT, AdoddleCommonStringPool.CUSTOM_ATTRIBUTE_STATE_MAHARASHTRA));

			List<String> citiesList = new ArrayList<String>(Arrays.asList(AdoddleCommonStringPool.CUSTOM_ATTRIBUTE_CITY_AHEMDABAD, AdoddleCommonStringPool.CUSTOM_ATTRIBUTE_CITY_JAIPUR, AdoddleCommonStringPool.CUSTOM_ATTRIBUTE_CITY_SURAT, AdoddleCommonStringPool.CUSTOM_ATTRIBUTE_CITY_MUMBAI));

			fileList = uploadDocumentsWithCustomAttributes(null, 4, null, customDocTitleList, false, 1, null, null, false, statesList, citiesList, null, Integer.parseInt(JavaUtils.getRandomNumber(3)), javaUtils.getRandomString(3), null, null);
			collectionDataMap.put("fileListWithCustomAttributes:: ", fileList.toString());
		}

	}

	public void checkFileListingOrder() {

		if (getElementText(FilesTab.lnk_DocListingFirstDocRef).contains(LinkFileDocRef4)) {
			clickElementAndWaitForElement(FilesTab.css_FileOrder, FilesTab.lnk_DocListingFirstDocRef);
			waitUntilElementContainsText(FilesTab.lnk_DocListingFirstDocRef, LinkFileDocRef1);
		}

		else if (getElementText(FilesTab.lnk_DocListingFirstDocRef).contains(StatusFileDocRef4)) {
			clickElementAndWaitForElement(FilesTab.css_FileOrder, FilesTab.lnk_DocListingFirstDocRef);
			waitUntilElementContainsText(FilesTab.lnk_DocListingFirstDocRef, StatusFileDocRef1);
		}
		else
			log.info("Order Already in Ascending");

	}

	public void editFilesAttributes(String scenarioName) {

		if (scenarioName.contains("LinkDocument")) {

			searchFiles(searchEpoch1.split("_")[1]);
			checkFileListingOrder();
			clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);
			contextClick(FilesTab.lnk_DocListingFirstDocRef);
			mouseHoverAndClickElement(FilesTab.opt_FileContextClickEdit, FilesTab.opt_FileContextClickEditAttributes);
			waitUntilElementIsDisplayed(FilesTab.chk_PopUploadFilesBulkApply);
			selectByVisibleText(FilesTab.Pop_EditAttributesFirstPurposeofIssue, AdoddleCommonStringPool.FOR_INFORMATION);
			selectByVisibleText(FilesTab.Pop_EditAttributesSecondPurposeofIssue, AdoddleCommonStringPool.FOR_INFORMATION);
			selectByVisibleText(FilesTab.Pop_EditAttributesThirdPurposeofIssue, AdoddleCommonStringPool.FOR_REVIEW);
			selectByVisibleText(FilesTab.Pop_EditAttributesFourthPurposeofIssue, AdoddleCommonStringPool.FOR_TRAINING);
			clickElementAndWaitForElement(FilesTab.btn_PopEditAttributesAssignAttributes, FilesTab.btn_PopupConfirmUIContinue);
			clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_PopupConfirmUIContinue, GlobalPageElements.pop_PopUpElement);
			waitForCompletePageLoad();

			for (String list : List1) {

				searchFiles(list);
				contextClick(FilesTab.lnk_DocListingFirstDocRef);
				mouseHoverAndClickElement(FilesTab.opt_FileContextClickEdit, FilesTab.opt_FileContextClickEditStatus);
				if (getElementText(FilesTab.lnk_DocListingFirstDocRef).contains(LinkFileDocRef1) || getElementText(FilesTab.lnk_DocListingFirstDocRef).contains(LinkFileDocRef3))
					selectByVisibleText(FilesTab.sel_StatusChangedDropdown, AdoddleCommonStringPool.FOR_APPROVAL);
				else
					selectByVisibleText(FilesTab.sel_StatusChangedDropdown, AdoddleCommonStringPool.FOR_REVIEW);

				sendKeys(FilesTab.txt_StatusChangeTextArea, javaUtils.getRandomString(5));
				clickElementAndWait(FilesTab.btn_ChangeStatus);
				waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
				waitForCompletePageLoad();
			}

		}

		else if (scenarioName.contains("StatusChange")) {

			searchFiles(searchEpoch1.split("_")[1]);
			checkFileListingOrder();
			clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);
			contextClick(FilesTab.lnk_DocListingFirstDocRef);
			mouseHoverAndClickElement(FilesTab.opt_FileContextClickEdit, FilesTab.opt_FileContextClickEditAttributes);
			waitUntilElementIsDisplayed(FilesTab.chk_PopUploadFilesBulkApply);
			selectByVisibleText(FilesTab.Pop_EditAttributesFirstPurposeofIssue, AdoddleCommonStringPool.FOR_REVIEW);
			selectByVisibleText(FilesTab.Pop_EditAttributesSecondPurposeofIssue, AdoddleCommonStringPool.FOR_INFORMATION);
			selectByVisibleText(FilesTab.Pop_EditAttributesThirdPurposeofIssue, AdoddleCommonStringPool.FOR_REVIEW);
			selectByVisibleText(FilesTab.Pop_EditAttributesFourthPurposeofIssue, AdoddleCommonStringPool.FOR_TRAINING);
			clickElementAndWaitForElement(FilesTab.btn_PopEditAttributesAssignAttributes, FilesTab.btn_PopupConfirmUIContinue);
			clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_PopupConfirmUIContinue, GlobalPageElements.pop_PopUpElement);
			waitForCompletePageLoad();

		}

		else
			log.info("Covered in previous Definition");

	}

	public void focusOnProject(String ProjectName) {
		log.info("Project:::" + ProjectName);
		clickElementWithText(ProjectName);
		waitUntilElementIsDisplayed(WorkflowsTab.lnk_WorkflowsTriggersLHPanelTriggerLink);
		clickElementAndWait(WorkflowsTab.lnk_WorkflowsTriggersLHPanelTriggerLink);
	}

	public void clickExistingTrigger(String trigger) {
		clickLinkWithText(trigger);
		waitUntilElementIsDisplayed(WorkflowsTab.txt_WorkflowsConfigureTriggerName);

	}

	public void editExistingTrigger(String folderValue) {

		clearExistingTriggerValue();

		if (folderValue.equalsIgnoreCase("Automation_WF_LinkDocument")) {
			sendKeys(WorkflowsTab.txt_WorkflowsConfigureTriggerValue, folderName1);
			waitUntilElementContainsText(WorkflowsTab.css_WorkflowsConfigureTriggerValueSearchResult, folderName1);
		}
		else {
			sendKeys(WorkflowsTab.txt_WorkflowsConfigureTriggerValue, folderName3);
			waitUntilElementContainsText(WorkflowsTab.css_WorkflowsConfigureTriggerValueSearchResult, folderName3);
		}
		clickElementAndWait(WorkflowsTab.css_WorkflowsConfigureTriggerValueSearchResult);
		clickElementAndWait(WorkflowsTab.btn_WorkflowsConfigureTriggerCreate);
	}

	public void clearExistingTriggerValue() {

		List<WebElement> closeIcons = findElements(WorkflowsTab.lnk_EditTriggerValueClear);
		log.info("Fields Need to Clear:: " + closeIcons.size());
		try {
			for (WebElement web : closeIcons)
				web.click();
		}
		catch (Exception error) {
			log.error("Failure(s) While Cleaning Data");
		}

	}

	public void clickOnProjectFolder(String projectName, String folderName) {
		clickElementWithText(projectName);
		clickElementWithText(folderName);
	}

	public void searchLinkedDocumentFile() throws InterruptedException {

		for (String ep : epochList) {
			searchFiles(ep.split("_")[1]);
			log.info("Epoch List size: " + epochList.size());
			clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);
			contextClick(FilesTab.lnk_DocListingFirstDocRef);
			clickElementAndWait(FilesTab.opt_FileContextClickLinkFiles);
			Assert.assertTrue(getElementText(FilesTab.Pop_TargetFolderHeader).contains("Target Folder"));
			if (getElementText(FilesTab.lnk_DocListingFirstDocRef).contains(LinkFileDocRef4) || getElementText(FilesTab.lnk_DocListingSecondDocRef).contains(LinkFileDocRef3) || getElementText(FilesTab.lnk_DocListingFirstDocRef).contains(LinkFileDocRef1) || getElementText(FilesTab.lnk_DocListingSecondDocRef).contains(LinkFileDocRef2)) {
				selectFolderOnPopupWindow(folderName1);
				applyLinkToDocument(AdoddleCommonStringPool.STRING_STATIC);
			}

			else if (getElementText(FilesTab.lnk_DocListingFirstDocRef).contains(LinkFileDocRef6) || getElementText(FilesTab.lnk_DocListingSecondDocRef).contains(LinkFileDocRef5) || getElementText(FilesTab.lnk_DocListingFirstDocRef).contains(LinkFileDocRef5) || getElementText(FilesTab.lnk_DocListingSecondDocRef).contains(LinkFileDocRef6)) {
				selectFolderOnPopupWindow(folderName2);
				applyLinkToDocument(AdoddleCommonStringPool.STRING_STATIC);
			}

			else {
				selectFolderOnPopupWindow(folderName1);
				applyLinkToDocument(AdoddleCommonStringPool.STRING_DYNAMIC);
			}
			clickElementAndWait(FilesTab.btn_PopLinkFilesSubmit);
			verifyLinkSuccess();
			waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
			waitForCompletePageLoad();
		}

	}

	public void applyLinkToDocument(String linkType) throws InterruptedException {
		clickElementAndWait(FilesTab.btn_PopTargetFolderSelect);
		Assert.assertTrue(getElementText(FilesTab.Pop_LinkFileHeader).contains(AdoddleCommonStringPool.LABEL_LINK_FILES));
		waitUntilElementIsDisplayed(FilesTab.txt_PopLinkFilesToField);
		sendKeys(FilesTab.txt_PopLinkFilesToField, userName);
		sendKeys(FilesTab.txt_PopLinkFilesToField, Keys.TAB);
		if (getElementText(FilesTab.drp_PopLinkFilesActiveLink).contains(linkType))
			log.info("Message: Required Link is already Active ");
		else
			selectByVisibleText(FilesTab.drp_PopLinkFilesLinkType, linkType);
	}

	public void validatePopup() {
		log.info("Covered in <searchLinkedDocumentFile> Definition");
	}

	public void linkFile() {
		log.info("Covered in <searchLinkedDocumentFile> Definition");
	}

	public void selectTargetFolder(String btnText) {
		log.info("Covered in <searchLinkedDocumentFile> Definition");
	}

	public void linkFileToTargetFolder() throws InterruptedException {
		log.info("Covered in <searchLinkedDocumentFile> Definition");
	}

	public void verifyCretaedLink() {
		log.info("Covered in <searchLinkedDocumentFile> Definition");
	}

	public void TargetFolder(String targetFolder) {

		if (targetFolder.contains("WF_StatusChangeFolder"))
			clickElementWithText(folderName3);
		else
			clickElementWithText(targetFolder);
	}

	public void focusProjectAndTagetFolder(String projectName) {
		clickElementWithText(projectName);
		clickElementWithText(folderName1);

	}

	public void checkWorkflowStatusAndStage(String scenarioName) throws InterruptedException {

		currentWindowHandle = getCurrentWindow();

		if (scenarioName.contains("LinkDocument")) {

			int i = 0, k = 0;

			docRefList = findElements(FilesTab.lnk_DocRefList);

			myActionList = findElements(FilesTab.lnk_myActionList);

			for (WebElement ele : docRefList) {

				if (ele.getText().contains(LinkFileDocRef1) || ele.getText().contains(LinkFileDocRef2) || ele.getText().contains(LinkFileDocRef3)) {

					log.info(ele.getText() + " Vs " + docRefList.get(i));
					log.info("ActionListSize: " + myActionList.size());
					log.info("Action Name1: " + myActionList.get(k).getText());
					log.info("Doc Ref1: " + docRefList.get(i).getText());
					Assert.assertTrue(myActionList.get(k).getText().contains(AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION));
					clickElementAndSwitchWindow(ele);
					if (fileBetaViewFlag) {
						clickElementAndWaitForElement(FilesTab.btn_BetaViewViewFileMoreOptionsButton, FilesTab.btn_BetaViewViewFileWorkflowsButton);
						clickElementAndWaitForElement(FilesTab.btn_BetaViewViewFileWorkflowsButton, FilesTab.lnk_BetaViewFileViewerWorkflowStatus);
						waitUntilElementContainsText(FilesTab.lnk_BetaViewFileViewerWorkflowStatus, AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING);
					}
					else {
						clickElementAndWaitForElement(FilesTab.lnk_FileViewerWorkflow, FilesTab.lnk_FileViewerWorkflowStatus);
						waitUntilElementContainsText(FilesTab.lnk_FileViewerWorkflowStatus, AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING);
						clickElementAndWaitForElement(FilesTab.lnk_FileViewerWorkflowStatus, FilesTab.btn_WorkflowStatusCancel);
						clickElementAndWait(FilesTab.btn_WorkflowStatusCancel);
					}
					k++;
				}

				else {

					log.info(ele.getText() + " Vs " + docRefList.get(i));
					waitUntilListElementIsClickable(ele);
					clickElementAndSwitchWindow(ele);
					if (fileBetaViewFlag) {
						clickElementAndWaitForElement(FilesTab.btn_BetaViewViewFileMoreOptionsButton, FilesTab.btn_BetaViewViewFileWorkflowsButton);
						clickElementAndWaitForElement(FilesTab.btn_BetaViewViewFileWorkflowsButton, FilesTab.css_BetaViewFileViewerWorkflowNoRecordsMessage);
						waitUntilElementContainsText(FilesTab.css_BetaViewFileViewerWorkflowNoRecordsMessage, AdoddleCommonStringPool.WORKFLOW_STATUS_NO_RECORD_MESSAGE);
					}

					else {
						clickElementAndWaitForElement(FilesTab.lnk_FileViewerWorkflow, FilesTab.css_FileViewerWorkflowNoRecordsMessage);
						waitUntilElementContainsText(FilesTab.css_FileViewerWorkflowNoRecordsMessage, AdoddleCommonStringPool.WORKFLOW_STATUS_NO_RECORD_MESSAGE);
					}

				}

				i++;

				closeCurrentWindow();
				switchPreviousWindow(currentWindowHandle);

			}

			clickElementWithText(folderName2);
			waitForCompletePageLoad();

			for (String file : List2) {

				searchFiles(file.split("_")[0]);

				if (getElementText(FilesTab.lnk_DocListingFirstDocRef).contains(LinkFileDocRef5)) {

					Assert.assertTrue(getElementText(FilesTab.lnk_FilesFirstAction).contains(AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION));
					clickElementAndSwitchWindow(FilesTab.lnk_DocListingFirstDocRef);
					if (fileBetaViewFlag) {
						clickElementAndWaitForElement(FilesTab.btn_BetaViewViewFileMoreOptionsButton, FilesTab.btn_BetaViewViewFileWorkflowsButton);
						clickElementAndWaitForElement(FilesTab.btn_BetaViewViewFileWorkflowsButton, FilesTab.lnk_BetaViewFileViewerWorkflowStatus);
						waitUntilElementContainsText(FilesTab.lnk_BetaViewFileViewerWorkflowStatus, AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING);
					}

					else {
						clickElementAndWaitForElement(FilesTab.lnk_FileViewerWorkflow, FilesTab.lnk_FileViewerWorkflowStatus);
						waitUntilElementContainsText(FilesTab.lnk_FileViewerWorkflowStatus, AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING);
						clickElementAndWaitForElement(FilesTab.lnk_FileViewerWorkflowStatus, FilesTab.btn_WorkflowStatusCancel);
						clickElementAndWait(FilesTab.btn_WorkflowStatusCancel);
					}

				}

				else {

					Assert.assertTrue(!isDisplayed(FilesTab.lnk_FilesFirstAction));
					clickElementAndWait(FilesTab.lnk_DocListingFirstDocRef);
					waitForSwitchWindow(2);
					switchWindow();
					if (fileBetaViewFlag) {
						clickElementAndWaitForElement(FilesTab.btn_BetaViewViewFileMoreOptionsButton, FilesTab.btn_BetaViewViewFileWorkflowsButton);
						clickElementAndWaitForElement(FilesTab.btn_BetaViewViewFileWorkflowsButton, FilesTab.css_BetaViewFileViewerWorkflowNoRecordsMessage);
						waitUntilElementContainsText(FilesTab.css_BetaViewFileViewerWorkflowNoRecordsMessage, AdoddleCommonStringPool.WORKFLOW_STATUS_NO_RECORD_MESSAGE);
					}

					else {
						clickElementAndWaitForElement(FilesTab.lnk_FileViewerWorkflow, FilesTab.css_FileViewerWorkflowNoRecordsMessage);
						waitUntilElementContainsText(FilesTab.css_FileViewerWorkflowNoRecordsMessage, AdoddleCommonStringPool.WORKFLOW_STATUS_NO_RECORD_MESSAGE);
					}

				}

				closeCurrentWindow();
				switchPreviousWindow(currentWindowHandle);

			}

		}

		else if (scenarioName.contains("StatusChange")) {

			searchFiles(searchEpoch1.split("_")[1]);
			checkFileListingOrder();
			docRefList = findElements(FilesTab.lnk_DocRefList);

			int i = 0;

			for (WebElement ele : docRefList) {

				if (ele.getText().contains(StatusFileDocRef1)) {

					log.info(ele.getText() + " Vs " + docRefList.get(i));
					log.info("DocListSize: " + docRefList.size());
					log.info("Doc Ref1:::" + docRefList.get(i).getText());
					clickElementAndSwitchWindow(ele);
					if (fileBetaViewFlag) {
						clickElementAndWaitForElement(FilesTab.btn_BetaViewViewFileMoreOptionsButton, FilesTab.btn_BetaViewViewFileWorkflowsButton);
						clickElementAndWaitForElement(FilesTab.btn_BetaViewViewFileWorkflowsButton, FilesTab.lnk_BetaViewFileViewerWorkflowStatus);
						waitUntilElementContainsText(FilesTab.lnk_BetaViewFileViewerWorkflowStatus, AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING);
					}

					else {
						clickElementAndWaitForElement(FilesTab.lnk_FileViewerWorkflow, FilesTab.lnk_FileViewerWorkflowStatus);
						waitUntilElementContainsText(FilesTab.lnk_FileViewerWorkflowStatus, AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING);
						clickElementAndWaitForElement(FilesTab.lnk_FileViewerWorkflowStatus, FilesTab.btn_WorkflowStatusCancel);
						clickElementAndWait(FilesTab.btn_WorkflowStatusCancel);
					}

				}

				else {

					log.info(ele.getText() + "Vs" + docRefList.get(i));
					clickElementAndSwitchWindow(ele);
					if (fileBetaViewFlag) {
						clickElementAndWaitForElement(FilesTab.btn_BetaViewViewFileMoreOptionsButton, FilesTab.btn_BetaViewViewFileWorkflowsButton);
						clickElementAndWaitForElement(FilesTab.btn_BetaViewViewFileWorkflowsButton, FilesTab.css_BetaViewFileViewerWorkflowNoRecordsMessage);
						waitUntilElementContainsText(FilesTab.css_BetaViewFileViewerWorkflowNoRecordsMessage, AdoddleCommonStringPool.WORKFLOW_STATUS_NO_RECORD_MESSAGE);
					}

					else {
						clickElementAndWaitForElement(FilesTab.lnk_FileViewerWorkflow, FilesTab.css_FileViewerWorkflowNoRecordsMessage);
						waitUntilElementContainsText(FilesTab.css_FileViewerWorkflowNoRecordsMessage, AdoddleCommonStringPool.WORKFLOW_STATUS_NO_RECORD_MESSAGE);
					}

				}

				i++;

				closeCurrentWindow();
				switchPreviousWindow(currentWindowHandle);

			}

		}

		else {
			for (String ele : fileList) {
				searchFiles(strUtils.extractFileNameString(ele));
				if (getElementText(FilesTab.css_DocListingDocTitleList).contains(customDocTitleList.get(0)) || getElementText(FilesTab.css_DocListingDocTitleList).contains(customDocTitleList.get(2))) {
					log.info("fileListSize:::" + fileList.size());
					Assert.assertTrue(getElementText(FilesTab.lnk_FilesFirstAction).contains(AdoddleCommonStringPool.ACTION_FOR_COMMENT));
					clickElementAndSwitchWindow(FilesTab.lnk_DocListingFirstDocRef);
					if (fileBetaViewFlag) {
						clickElementAndWaitForElement(FilesTab.btn_BetaViewViewFileMoreOptionsButton, FilesTab.btn_BetaViewViewFileWorkflowsButton);
						clickElementAndWaitForElement(FilesTab.btn_BetaViewViewFileWorkflowsButton, FilesTab.lnk_BetaViewFileViewerWorkflowStatus);
						Assert.assertTrue(getElementText(FilesTab.lnk_BetaViewFileViewerWorkflowStatus).contains(AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING));
					}
					else {
						clickElementAndWaitForElement(FilesTab.lnk_FileViewerWorkflow, FilesTab.lnk_FileViewerWorkflowStatus);
						waitUntilElementContainsText(FilesTab.lnk_FileViewerWorkflowStatus, AdoddleCommonStringPool.WORKFLOW_STATUS_RUNNING);
						clickElementAndWaitForElement(FilesTab.lnk_FileViewerWorkflowStatus, FilesTab.btn_WorkflowStatusCancel);
						clickElementAndWait(FilesTab.btn_WorkflowStatusCancel);
					}
					closeCurrentWindow();
					switchPreviousWindow(currentWindowHandle);
					waitForCompletePageLoad();

				}

				else {

					log.info("fileListSize:::" + fileList.size());
					clickElementAndSwitchWindow(FilesTab.lnk_DocListingFirstDocRef);
					if (fileBetaViewFlag) {
						clickElementAndWaitForElement(FilesTab.btn_BetaViewViewFileMoreOptionsButton, FilesTab.btn_BetaViewViewFileWorkflowsButton);
						clickElementAndWaitForElement(FilesTab.btn_BetaViewViewFileWorkflowsButton, FilesTab.css_BetaViewFileViewerWorkflowNoRecordsMessage);
						waitUntilElementContainsText(FilesTab.css_BetaViewFileViewerWorkflowNoRecordsMessage, AdoddleCommonStringPool.WORKFLOW_STATUS_NO_RECORD_MESSAGE);
					}

					else {
						clickElementAndWaitForElement(FilesTab.lnk_FileViewerWorkflow, FilesTab.css_FileViewerWorkflowNoRecordsMessage);
						waitUntilElementContainsText(FilesTab.css_FileViewerWorkflowNoRecordsMessage, AdoddleCommonStringPool.WORKFLOW_STATUS_NO_RECORD_MESSAGE);
					}
					closeCurrentWindow();
					switchPreviousWindow(currentWindowHandle);
					waitForCompletePageLoad();

				}

			}

		}
	}

	public void verifyFileAction(String action) {
		log.info("Covered in <checkWorkflowStatusAndStage> definition");
	}

	public void clickOnMyAction() {
		log.info("Covered in <performStatusChange> definition");
	}

	public void performActionStatusChange() {

		for (String file : List3) {
			searchFiles(file);
			contextClick(FilesTab.lnk_DocListingFirstDocRef);
			mouseHoverAndClickElement(FilesTab.opt_FileContextClickEdit, FilesTab.opt_FileContextClickEditStatus);
			if (getElementText(FilesTab.lnk_DocListingFirstDocRef).contains(StatusFileDocRef1))
				selectByVisibleText(FilesTab.sel_StatusChangedDropdown, AdoddleCommonStringPool.STATUS_FOR_APPROVAL);
			else if (getElementText(FilesTab.lnk_DocListingFirstDocRef).contains(StatusFileDocRef2))
				selectByVisibleText(FilesTab.sel_StatusChangedDropdown, AdoddleCommonStringPool.STATUS_FOR_REVIEW);
			else if (getElementText(FilesTab.lnk_DocListingFirstDocRef).contains(StatusFileDocRef3))
				selectByVisibleText(FilesTab.sel_StatusChangedDropdown, AdoddleCommonStringPool.STATUS_FOR_INFORMATION);
			else
				selectByVisibleText(FilesTab.sel_StatusChangedDropdown, AdoddleCommonStringPool.STATUS_FOR_TRAINING);

			sendKeys(FilesTab.txt_StatusChangeTextArea, javaUtils.getRandomString(5));
			clickElementAndWait(FilesTab.btn_ChangeStatus);
			waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
			waitForCompletePageLoad();
		}

	}

	public void deactivateTestFiles() {

		try {

			for (String epoc : epochList)

			{
				searchFiles(epoc.split("_")[1]);
				log.info("Epoch size:: " + epochList.size());
				if (isDisplayed(FilesTab.css_fileSearchFailureMsg))
					break;

				else {
					clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);
					clickElementAndWaitForElement(FilesTab.lnk_FilesTabMoreOptions, GlobalPageElements.pop_PopUpElement);
					clickElementAndWaitForElement(FilesTab.lnk_PopFileOptionsDeactivateFiles, FilesTab.chk_PopDeactivateFilesEntireFile);
					clickElementAndWait(FilesTab.chk_PopDeactivateFilesEntireFile);
					clickElementAndWait(FilesTab.btn_PopDeactivateFilesDeactivate);
					clickElementAndWait(FilesTab.btn_PopDeactivatedFileSuccessOk);
					waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
					waitForCompletePageLoad();

				}

			}

		}
		catch (Throwable e) {

			log.error("Failure while Deactivating Files");

		}

	}

	public void deactivateFolder(String folderName, String projectTitle)

	{
		try {
			if (folderName.equalsIgnoreCase("WF_LinkDocumentPrimaryFolder"))
				deactivateProjectFolder(projectTitle, folderName1);
			else if (folderName.equalsIgnoreCase("WF_LinkDocumentSecondryFolder"))
				deactivateProjectFolder(projectTitle, folderName2);
			else
				deactivateProjectFolder(projectTitle, folderName3);

		}
		catch (Throwable e) {

			log.error("Failure while Deactivating Folders");
		}

	}

}