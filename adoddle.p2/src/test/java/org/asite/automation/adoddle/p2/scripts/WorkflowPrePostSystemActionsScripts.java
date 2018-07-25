/**  Testdata required for this script as follows.
     1). Project Template should be available for cloning.
 */

package org.asite.automation.adoddle.p2.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleWorkflowsLocators.WorkflowsTab;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.JavaUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class WorkflowPrePostSystemActionsScripts extends AdoddleCommonAppMethods {

	private int					documentCount					= 0;
	private int					resultIndex						= 0;
	private int					fileIndex						= 0;
	private static String		folderTitle						= "Automation_WF_GroovyPrePost";
	private String				groovyScriptTriggerTitle		= "Automation_WF_P2_GroovyPrePost_Trigger";
	private String				groovyScriptSystemActionTitle	= "Groovy_PrePost_SystemAction";
	private String				groovyCondition1, groovyCondition2, groovyCondition3, systemTaskTitle, poiKey, statusKey, currentProject;
	private Vector<String>		uploadFileList					= new Vector<String>();
	private Vector<String>		groovyConditionsList			= new Vector<String>();
	private List<String>		fileList;
	private List<String>		distributionUserList			= new ArrayList<String>();
	private List<WebElement>	revisionElements				= new ArrayList<WebElement>();
	private List<WebElement>	docRefElements					= new ArrayList<WebElement>();
	private List<WebElement>	attachFilesElements				= new ArrayList<WebElement>();
	private List<WebElement>	poiListElements					= new ArrayList<WebElement>();
	private List<WebElement>	statusListElements				= new ArrayList<WebElement>();
	private Map<String, String>	triggerValues					= new HashMap<String, String>();
	public static Logger		log								= AutomationLogger.getInstance().getLogger(WorkflowPrePostSystemActionsScripts.class);

	public void createWorkflowTestFolder(String wsTitle) {
		currentProject = wsTitle;
		folderTitle = folderTitle + dateUtils.getEpoch();
		if (wsTitle != null)
			createProjectFolderMoreOptions(wsTitle, folderTitle);
		else {
			createProjectFolderMoreOptions(clonedProject, folderTitle);
			collectionDataMap.put("Cloned Project", clonedProject);
		}
		collectionDataMap.put("Folder Title", folderTitle);

	}

	public void enableSimpleUpload() {
		contextClickWithText(folderTitle);
		clickElementAndWaitForElement(FilesTab.opt_ProjectFolderContextClickEditFolder, FilesTab.chk_PopEditFolderEnabledSimpleUpload);
		clickElementAndWaitForElement(FilesTab.chk_PopEditFolderEnabledSimpleUpload, FilesTab.btn_PopEditFolderUpdate);
		clickElementAndWait(FilesTab.btn_PopEditFolderUpdate);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
	}

	public void enableUploadAttibutes() {

		if (isDisplayed(WorkflowsTab.btn_PopFileUploadGroovyResultsOK))
			clickElementAndWait(WorkflowsTab.btn_PopFileUploadGroovyResultsOK);
		clickElementAndWait(FilesTab.btn_PopUploadFileAttibutes);
		revisionElements = findElements(FilesTab.css_PopUploadFilesRevisionsInput);
		poiListElements = findElements(FilesTab.css_PopUploadFilesPurposeOfIssuesDropDowns);
		statusListElements = findElements(FilesTab.css_PopUploadFilesStatusDropDowns);
		attachFilesElements = findElements(FilesTab.css_PopUploadFilesAttachExternalFileButtons);
		docRefElements = findElements(FilesTab.css_PopUploadFilesDocRefsInput);
		clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);

		for (int counter = 0; counter < revisionElements.size(); counter++) {
			revisionElements.get(counter).clear();
			revisionElements.get(counter).sendKeys(javaUtils.getRandomString(3));
			selectByIndex(poiListElements.get(counter), 1);
			selectByIndex(statusListElements.get(counter), 1);

		}
	}

	public void verifyGroovyScript() {
		navigateTab(LandingPage.lnk_Workflows);
		clickElementAndWaitForElement(WorkflowsTab.lnk_WorkflowsTriggersLHPanelSystemActionLink, WorkflowsTab.lnk_WorkflowsFirstSystemActionName);
		clickLinkWithText(groovyScriptSystemActionTitle);
		List<WebElement> prePostSystemActionMessages = findElements(WorkflowsTab.css_PopGroovyEditActionTextAreaStrings);
		for (WebElement e : prePostSystemActionMessages) {
			if (e.getText().length() > 10) {
				log.info(e.getText().replace("\"", ""));
				groovyConditionsList.add(e.getText().replace("\"", ""));
			}
		}
		groovyCondition1 = groovyConditionsList.get(0);
		groovyCondition2 = groovyConditionsList.get(1);
		groovyCondition3 = groovyConditionsList.get(2);
		clickElementAndWait(GlobalPageElements.btn_PopUpElementClose);
	}

	public void updatePrePostWorkflowTrigger() throws InterruptedException {
		clickElementAndWaitForElement(WorkflowsTab.lnk_WorkflowsTriggersLHPanelTriggerLink, WorkflowsTab.lnk_WorkflowsTriggersFirstTriggerName);
		clickLinkWithText(groovyScriptTriggerTitle);
		clickElementAndWait(WorkflowsTab.lnk_EditTriggerValueClear);
		sendKeys(WorkflowsTab.txt_WorkflowsConfigureTriggerValue, folderTitle);
		clickElementAndWaitForElement(WorkflowsTab.opt_WorkflowsConfigureSystemActionToDropdownFirstResult, WorkflowsTab.btn_WorkflowsConfigureTriggerCreate);
		if (!getSelectedDropdownLabel(WorkflowsTab.drp_WorkflowsConfigureTriggerActionMode).equalsIgnoreCase("pre")) {
			selectByVisibleText(WorkflowsTab.drp_WorkflowsConfigureTriggerActionMode, "Pre");
			selectByVisibleText(WorkflowsTab.drp_WorkflowsConfigureTriggerAction, groovyScriptSystemActionTitle);
		}
		clickElementAndWaitForInvisibilityOfElement(WorkflowsTab.btn_WorkflowsConfigureTriggerCreate, GlobalPageElements.pop_PopUpElement);
	}

	public void openUploadDialog(String count) throws InterruptedException {
		navigateTab(LandingPage.lnk_Files);
		clickElementWithText(currentProject);
		clickElementWithText(folderTitle);
		documentCount = Integer.parseInt(count);
		sysUtils.authenticateRemoteMachine(nodeIP);
		for (int index = 0; index < documentCount; index++) {
			uploadFileList.add(sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP));
			waitUtils.waitInterval(1);
		}

		clickElementAndWait(FilesTab.btn_AddFiles);
		uploadFileUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, uploadFileList);
		clickElementAndWait(FilesTab.btn_PopUploadFileUpload);
	}

	public void applyFileUploadConditions(String conditions, String files) {

		if (files.equalsIgnoreCase("all") || files.equalsIgnoreCase("first"))
			fileIndex = files.equalsIgnoreCase("all") ? 0 : 1;
		else
			fileIndex = files.equalsIgnoreCase("second") ? 2 : 3;

		try {
			waitUntilElementIsDisplayed(WorkflowsTab.btn_PopFileUploadGroovyResultsOK, 10);
		}
		catch (Throwable t) {
			log.info("Groovy Popup not visible");
		}
		if (isDisplayed(WorkflowsTab.btn_PopFileUploadGroovyResultsOK))
			clickElementAndWait(WorkflowsTab.btn_PopFileUploadGroovyResultsOK);
		waitUntilElementIsInvisible(WorkflowsTab.pop_FileUploadGroovyConditionResults);

		applyConditions(conditions, fileIndex);
	}

	public void validateGroovyScriptConditions(String conditions, String files) throws InterruptedException {
		waitUtils.waitInterval(1);
		if (!isDisplayed(WorkflowsTab.pop_FileUploadGroovyConditionResults))
			clickElementAndWait(FilesTab.btn_PopUploadFileUpload);
		waitUntilElementIsDisplayed(WorkflowsTab.pop_FileUploadGroovyConditionResults);
		List<WebElement> conditionResultsFiles = findElements(WorkflowsTab.css_PopGroovyResultsFileList);
		List<WebElement> conditionResultsReasons = findElements(WorkflowsTab.css_PopGroovyResultsFailureReasonList);

		if (files.equalsIgnoreCase("all") || files.equalsIgnoreCase("first"))
			resultIndex = javaUtils.resetIndex(resultIndex, 0);
		else
			resultIndex = files.equalsIgnoreCase("second") ? 1 : 2;

		if (conditions.equalsIgnoreCase("all")) {
			if (files.equalsIgnoreCase("all")) {
				for (WebElement e : conditionResultsFiles) {
					AutomationAssert.verifyTrue(e.getText() + " should equal " + strUtils.extractFileNameString(uploadFileList.get(resultIndex)), e.getText().equalsIgnoreCase(strUtils.extractFileNameString(uploadFileList.get(resultIndex))));
					AutomationAssert.verifyTrue(conditionResultsReasons.get(resultIndex).getText().contains(groovyCondition1) && conditionResultsReasons.get(resultIndex).getText().contains(groovyCondition2) && conditionResultsReasons.get(resultIndex).getText().contains(groovyCondition3));
					resultIndex++;
				}

			}
			else if (files.equalsIgnoreCase("first") || files.equalsIgnoreCase("second") || files.equalsIgnoreCase("third")) {
				int increamentCounter = 0;
				List<WebElement> rows = findElements(WorkflowsTab.css_PopGroovyResultsRows);

				for (WebElement row : rows) {
					if (uploadFileList.get(resultIndex).contains(row.getText()))
						break;
					increamentCounter++;
				}

				log.info("What's increament counter: " + increamentCounter);
				AutomationAssert.verifyTrue(uploadFileList.get(resultIndex) + " should contain " + conditionResultsFiles.get(increamentCounter).getText(), conditionResultsFiles.get(increamentCounter).getText().equalsIgnoreCase(strUtils.extractFileNameString(uploadFileList.get(resultIndex))));
				AutomationAssert.verifyTrue(conditionResultsReasons.get(increamentCounter).getText().contains(groovyCondition1) && conditionResultsReasons.get(increamentCounter).getText().contains(groovyCondition2) && conditionResultsReasons.get(increamentCounter).getText().contains(groovyCondition3));
			}
		}
		else if (conditions.equalsIgnoreCase("zC")) {
			int increamentCounter = 0;
			List<WebElement> rows = findElements(By.cssSelector("div[id='fileUploadfundingServiceTable'] table[class*='table-body'] tbody tr td[class='file-name']"));

			for (WebElement row : rows) {
				if (uploadFileList.get(resultIndex).contains(row.getText()))
					break;
				increamentCounter++;
			}

			log.info("what's increament counter: " + increamentCounter);
			if (files.equalsIgnoreCase("first") || files.equalsIgnoreCase("second") || files.equalsIgnoreCase("third")) {
				AutomationAssert.verifyTrue(conditionResultsFiles.get(increamentCounter).getText().equalsIgnoreCase(strUtils.extractFileNameString(uploadFileList.get(resultIndex))));
				AutomationAssert.verifyTrue(conditionResultsReasons.get(increamentCounter).getText() + " should contain " + groovyCondition3, conditionResultsReasons.get(increamentCounter).getText().contains(groovyCondition3));

			}
		}

	}

	public void verifyUploadIsSuccessful() {
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();
		for (String file : uploadFileList) {
			searchFiles(strUtils.extractFileNameString(file));
			AutomationAssert.verifyTrue(getCount(FilesTab.css_DocListingFilesCount) == 1);

		}
	}

	public void deactivateGroovyFolder(String project) {
		try {
			deactivateProjectFolder(project, folderTitle);
		}
		catch (Throwable t) {
			log.info("Error while deactivating groovy script folder");
		}
	}

	public void applyConditions(String whichCondition, int fIndex) {

		if (whichCondition.equalsIgnoreCase("all")) {
			attachFilesElements.get(fIndex - 1).sendKeys(uploadFileList.get(fIndex - 1));
			docRefElements.get(fIndex - 1).clear();
			docRefElements.get(fIndex - 1).sendKeys("ASITE" + AdoddleCommonStringPool.UNDERSCORE_STRING + dateUtils.getEpoch());
			revisionElements.get(fIndex - 1).clear();
			revisionElements.get(fIndex - 1).sendKeys(JavaUtils.getRandomNumber(3));

		}
		else if (whichCondition.equalsIgnoreCase("xC")) {
			attachFilesElements.get(fIndex - 1).sendKeys(uploadFileList.get(fIndex - 1));
		}
		else if (whichCondition.equalsIgnoreCase("yC")) {
			docRefElements.get(fIndex - 1).clear();
			docRefElements.get(fIndex - 1).sendKeys("ASITE" + AdoddleCommonStringPool.UNDERSCORE_STRING + dateUtils.getEpoch());
		}
		else if (whichCondition.equalsIgnoreCase("zC")) {
			revisionElements.get(fIndex - 1).clear();
			revisionElements.get(fIndex - 1).sendKeys(JavaUtils.getRandomNumber(3));
		}

		clickElementAndWait(FilesTab.btn_PopUploadFileUpload);

	}

	public void verifyPrePostActions(String preOption, String postOption) {

		clickElementWithText(clonedProject);
		clickElementAndWaitForElement(WorkflowsTab.lnk_WorkflowsTriggersLHPanelSystemActionLink, WorkflowsTab.lnk_WorkflowsFirstSystemActionName);

		String groovyTaskCountQuery = "return $(\"div[id='workflowList'] div[id='listing'] div[class*='rows']:contains('Execute Groovy Script')\").find(\"div[class*='col-name-fixed-width'] a\").length";
		String totalTaskCountQuery = "return $(\"div[id='workflowList'] div[id='listing'] div[class*='rows']\").find(\"div[class*='col-name-fixed-width'] a\").length";

		Long groovyTaskCount = (Long) ((JavascriptExecutor) getWebDriver()).executeScript(groovyTaskCountQuery);
		log.info("Groovy Task Counts: " + groovyTaskCount);
		Long totalTaskCount = (Long) ((JavascriptExecutor) getWebDriver()).executeScript(totalTaskCountQuery);
		log.info("Total Task Counts: " + totalTaskCount);

		clickElementAndWaitForElement(WorkflowsTab.lnk_WorkflowsTriggersLHPanelTriggerLink, WorkflowsTab.lnk__WorkflowsCreateTriggerLink);
		clickElementAndWaitForElement(WorkflowsTab.lnk__WorkflowsCreateTriggerLink, WorkflowsTab.txt_WorkflowsConfigureTriggerName);
		selectByVisibleText(WorkflowsTab.drp_WorkflowsConfigureTriggerActionMode, preOption);
		clickElementAndWait(WorkflowsTab.drp_WorkflowsConfigureTriggerAction);
		AutomationAssert.verifyTrue(getCount(WorkflowsTab.css_WorkflowsConfigureTriggerActionOptions) == groovyTaskCount);
		selectByVisibleText(WorkflowsTab.drp_WorkflowsConfigureTriggerActionMode, postOption);
		AutomationAssert.verifyTrue(getCount(WorkflowsTab.css_WorkflowsConfigureTriggerActionOptions) == totalTaskCount);
		clickElementAndWait(WorkflowsTab.drp_WorkflowsConfigureTriggerAction);
		clickElementAndWait(GlobalPageElements.btn_PopUpElementClose);

	}

	public void createSystemTaskOfType(String taskType) throws InterruptedException {
		distributionUserList = Arrays.asList("RFI Builder", "RFI Bloggs", "Auto RFI");
		systemTaskTitle = createProjectSystemTask(clonedProject, taskType, distributionUserList);
	}

	public void createSystemTrigger(String folderOption, String poiOption, String statusOption, String event, String actionMode) {
		poiKey = poiOption;
		statusKey = statusOption;
		triggerValues.clear();
		triggerValues.put(folderOption, folderTitle);
		triggerValues.put(poiOption, AdoddleCommonStringPool.STATUS_FOR_INFORMATION);
		triggerValues.put(statusOption, AdoddleCommonStringPool.STATUS_FOR_FINAL_REVIEW);
		createProjectSystemTrigger(clonedProject, systemTaskTitle, triggerValues, event, actionMode);
	}

	public void publishDocuments(String fileCount, String condition) throws NumberFormatException, InterruptedException, IOException {
		fileList = new ArrayList<String>();
		clickElementWithText(clonedProject);
		clickElementWithText(folderTitle);

		log.info("Favorable POI: " + triggerValues.get("Purpose of Issue"));
		log.info("Favorable Status: " + triggerValues.get("Document Status"));

		if (condition.equalsIgnoreCase("favorable"))
			fileList = uploadDocuments(null, Integer.parseInt(fileCount), null, null, false, 1, Arrays.asList(triggerValues.get(poiKey)), Arrays.asList(triggerValues.get(statusKey)), null, null);
		else {
			fileList.add(uploadDocuments(null, 1, null, null, false, 1, Arrays.asList(triggerValues.get(poiKey)), Arrays.asList(AdoddleCommonStringPool.STATUS_FOR_APPROVAL), null, null).get(0));
			fileList.add(uploadDocuments(null, 1, null, null, false, 1, Arrays.asList(AdoddleCommonStringPool.STATUS_FOR_APPROVAL), Arrays.asList(triggerValues.get(statusKey)), null, null).get(0));
		}
	}

	public void verifyActionsDistribution(boolean flag) {

		String parentHandle = getCurrentWindow();

		for (String file : fileList) {
			searchFiles(strUtils.extractFileNameString(file));
			clickElementAndSwitchWindow(FilesTab.lnk_DocListingFirstFileName);
			waitForCompletePageLoad();
			if (!fileBetaViewFlag)
				verifyActionsInClassicView(flag);
			else
				verifyActionsInBetaView(flag);
			closeCurrentWindow();
			switchPreviousWindow(parentHandle);
		}
	}

	public void verifyActionsInClassicView(boolean flag) {
		clickElementAndWaitForElement(FilesTab.lnk_FileViewLHHistoryBlock, FilesTab.drp_FileViewHistoryTypeDropdown);
		selectByVisibleText(FilesTab.drp_FileViewHistoryTypeDropdown, "Distribution");
		if (flag) {
			waitUntilElementIsDisplayed(FilesTab.ele_FileHistoryFirstRecord);
			clickElementAndWaitForElement(FilesTab.ele_FileHistoryFirstRecord, FilesTab.drp_FileHistoryRecordDetailActionsDropdown);
			List<WebElement> distributionRows = findElements(FilesTab.css_FileHistoryRecordDetailsDistributionRows);
			AutomationAssert.verifyTrue(distributionRows.size() == 3);

			for (WebElement e : distributionRows) {
				AutomationAssert.verifyTrue(e.findElement(FilesTab.img_FileHistoryRecordDetailDitributionUser).getAttribute("title") + " does not contain " + distributionUserList.toString(), e.findElement(FilesTab.img_FileHistoryRecordDetailDitributionUser).getAttribute("title").contains(distributionUserList.get(0)) || e.findElement(FilesTab.img_FileHistoryRecordDetailDitributionUser).getAttribute("title").contains(distributionUserList.get(1)) || e.findElement(FilesTab.img_FileHistoryRecordDetailDitributionUser).getAttribute("title").contains(distributionUserList.get(2)));
			}
		}
		else
			AutomationAssert.verifyTrue(!isDisplayed(FilesTab.ele_FileHistoryFirstRecord));
	}

	public void verifyActionsInBetaView(boolean flag) {
		String distUser;
		clickElementAndWaitForElement(FilesTab.btn_BetaFileViewMoreOptions, FilesTab.btn_BetaFileViewFileHistory);
		clickElementAndWaitForElement(FilesTab.btn_BetaFileViewFileHistory, FilesTab.ele_BetaFileViewHistoryPanel);
		clickElementAndWaitForElement(FilesTab.img_BetaFileViewMaximizePanel, FilesTab.lbl_BetaFileViewHeaderRemarks);
		selectByVisibleText(FilesTab.drp_BetaFileViewHistoryTypeDropdown, "Distribution");

		if (flag) {
			waitUntilElementIsDisplayed(FilesTab.ele_BetaViewFileHistoryDistributionFirstRecord);
			List<WebElement> distributionRows = findElements(FilesTab.css_BetaViewFileHistoryRecordDetailsDistributionRows);
			AutomationAssert.verifyTrue(distributionRows.size() == 3);
			for (WebElement e : distributionRows) {
				distUser = getElementText(e.findElement(FilesTab.lnk_BetaViewFileHistoryRecordDetailDitributionUser));
				AutomationAssert.verifyTrue(distUser.contains(distributionUserList.get(0)) || distUser.contains(distributionUserList.get(1)) || distUser.contains(distributionUserList.get(2)));
			}
		}
		else
			AutomationAssert.verifyTrue(!isDisplayed(FilesTab.ele_FileHistoryFirstRecord));
	}

	public void deactivateClonedProject() {
		deactivateInheritanceProject(clonedProject);
	}

}
