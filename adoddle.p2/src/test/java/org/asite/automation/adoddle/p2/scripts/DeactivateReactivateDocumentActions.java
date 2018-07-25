package org.asite.automation.adoddle.p2.scripts;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.resources.AdoddleScenarioMarkers;
import org.asite.automation.common.resources.AdoddleScenariosPool;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class DeactivateReactivateDocumentActions extends AdoddleCommonAppMethods {

	private static String		folderTitle			= "AutomationTestFolder", fileTitle, workspaceTitle = null, parentHandle = null;
	private boolean				privateUploadFlag	= true;
	private List<String>		actionList			= Arrays.asList("For Action", "For Status Change", "For Acknowledgement");
	private List<String>		userList			= null;
	private static List<String>	privateDocument;
	private int					actionListSize		= 0;
	public static Logger		log					= AutomationLogger.getInstance().getLogger(DeactivateReactivateDocumentActions.class);

	public void createAutomationFolder(String project) {
		workspaceTitle = project;
		folderTitle = folderTitle + AdoddleCommonStringPool.UNDERSCORE_STRING + dateUtils.getEpoch();
		collectionDataMap.put("Folder Title", folderTitle);
		createProjectFolderMoreOptions(workspaceTitle, folderTitle);
	}

	public void openFolderEditDialog() {
		contextClickWithText(folderTitle);
		clickElementAndWaitForElement(FilesTab.opt_ProjectFolderContextClickEditFolder, FilesTab.txt_PopEditFolderSecurityInput);
	}

	public void editAccessToAutomationFolder(String access, String user) throws InterruptedException {

		if (!access.equalsIgnoreCase(AdoddleCommonStringPool.PERMISSION_UPLOAD)) {
			sendKeys(FilesTab.txt_PopEditFolderSecurityInput, user);
			sendKeys(FilesTab.txt_PopEditFolderSecurityInput, Keys.TAB);
		}

		List<WebElement> securityUsersList = findElements(FilesTab.css_PopEditFolderSecurityUsersList);
		for (WebElement e : securityUsersList) {
			if (e.getText().contains(user)) {
				log.info("User name with access: " + e.getText());
				e.click();
				waitForCompletePageLoad();
				waitUntilElementIsDisplayed(FilesTab.drp_PopEditFolderUserFolderPermissionDropdown);
				selectByVisibleText(FilesTab.drp_PopEditFolderUserFolderPermissionDropdown, access);
				clickElementAndWait(FilesTab.txt_PopEditFolderFolderName);
			}
		}
	}

	public void saveUpdatedFolder() {
		clickElementAndWait(FilesTab.btn_PopEditFolderUpdate);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
	}

	public void publishDocumentAndDistribute(String firstUser, String secondUser, String thirdUser, String forthUser) throws InterruptedException, IOException {
		userList = Arrays.asList(firstUser, secondUser, thirdUser, forthUser);
		privateDocument = uploadDocuments(null, 1, null, null, privateUploadFlag, 1, null, null,null,null);
		fileTitle = strUtils.extractFileNameString(privateDocument.get(0));
		searchFiles(fileTitle);
		collectionDataMap.put("File Title", fileTitle);
		contextClick(FilesTab.lnk_DocListingFirstDocRef);
		mouseHoverAndClickElement(FilesTab.opt_FileContextClickShare, FilesTab.opt_FileContextClickShareDistribute);
		waitUntilElementIsDisplayed(GlobalPageElements.pop_PopUpElement);
		actionListSize = actionList.size();
		assignFileActionsToMultipleUsers(userList, actionList);
		clickElementAndWait(FilesTab.btn_PopFilesActionForDistribute);
		clickElementWithText(workspaceTitle);
	}

	public void uploadDocumentRevisionAndDistribute(String firstUser, String secondUser, String thirdUser, String forthUser) throws InterruptedException, IOException {
		clickElementWithText(folderTitle);
		userList = Arrays.asList(firstUser, secondUser, thirdUser, forthUser);
		uploadDocuments(privateDocument, 1, null, null, privateUploadFlag, 2, null, null,null,null);
		searchFiles(fileTitle);
		contextClick(FilesTab.lnk_DocListingFirstDocRef);
		mouseHoverAndClickElement(FilesTab.opt_FileContextClickShare, FilesTab.opt_FileContextClickShareDistribute);
		waitUntilElementIsDisplayed(GlobalPageElements.pop_PopUpElement);
		actionListSize = actionList.size();
		/* assignActionsToMultipleUsers(userList, actionList); */
		clickElementAndWait(FilesTab.lnk_PopDistributePreviousRevisionUsers);
		clickElementAndWait(FilesTab.btn_PopFilesActionForDistribute);
		clickElementWithText(workspaceTitle);
	}

	public void searchDocument() {
		navigateTab(LandingPage.lnk_Files);
		clickElementWithText(workspaceTitle);
		searchFiles(fileTitle);
	}

	public void deactivateActions(String action, String firstUser, String secondUser, String thirdUser, String forthUser, String rev) throws InterruptedException {
		WebElement uncheckActionRowCheckBox = null;
		parentHandle = getCurrentWindow();
		openFileInViewerWindow();
		if(!fileBetaViewFlag) {
			clickElementAndWaitForElement(FilesTab.lnk_FileViewLHHistoryBlock, FilesTab.drp_FileViewHistoryTypeDropdown);
			selectByIndex(FilesTab.drp_FileViewHistoryTypeDropdown, 1);
			selectByIndex(FilesTab.drp_FileviewHistoryRevDropdown, 1);
			waitUntilElementIsDisplayed(FilesTab.ele_FileHistoryFirstRecord);
			clickElementAndWaitForElement(FilesTab.ele_FileHistoryFirstRecord, FilesTab.drp_FileHistoryRecordDetailActionsDropdown);
		}
		else 
		{
			clickElementAndWaitForElement(FilesTab.btn_BetaFileViewMoreOptions, FilesTab.btn_BetaFileViewFileHistory);
			clickElementAndWaitForElement(FilesTab.btn_BetaFileViewFileHistory, FilesTab.ele_BetaFileViewHistoryPanel);
			waitUntilElementCountToBeMoreThan(FilesTab.css_BetaViewFileHistoryRecordDetailsDistributionRows, 10);
			if(TestDriverFactory.scenario.getName().contains(AdoddleScenariosPool.REACTIVATE_DOCUMENT_ACTIONS)) 
			{
				for(int counter =0; counter < 5; counter++) {
					log.info("Selected History revision dropdown value : "+getSelectedDropdownLabel(FilesTab.drp_BetaFileViewHistoryRevDropdown).trim());
					if(getSelectedDropdownLabel(FilesTab.drp_BetaFileViewHistoryRevDropdown).trim().equalsIgnoreCase("All")) {
						log.info("Trying to select dropdown value other than All");
						selectByVisibleText(FilesTab.drp_BetaFileViewHistoryRevDropdown, "2: "+dateUtils.getCurrentDateTimeWithZone("dd-MMM-yyyy", "wet"));
						waitUtils.waitInterval(1);
					}
					else {
						log.info("Breaking with Selected History revision dropdown value : "+getSelectedDropdownLabel(FilesTab.drp_BetaFileViewHistoryRevDropdown).trim());
						break;
					}
				}
			}
		}
		waitUntilElementCountToBe(!fileBetaViewFlag ? FilesTab.css_FileHistoryRecordDetailsDistributionRows: FilesTab.css_BetaViewFileHistoryRecordDetailsDistributionRows, 12);	
		List<WebElement> distributionListRows = findElements(!fileBetaViewFlag ? FilesTab.css_FileHistoryRecordDetailsDistributionRows: FilesTab.css_BetaViewFileHistoryRecordDetailsDistributionRows);
		log.info("Distribution List row size: "+distributionListRows.size());
		for (WebElement e : distributionListRows) {
			e.findElement(! fileBetaViewFlag ? FilesTab.chk_FileHistoryRecordDetailCheckbox : FilesTab.chk_BetaViewFileHistoryRecordDetailCheckbox).click();
			if(!fileBetaViewFlag) {
				log.info("Checking boxes...classic view");
				if(e.findElement(FilesTab.img_FileHistoryRecordDetailDitributionUser).getAttribute("title").contains(thirdUser))
					uncheckActionRowCheckBox = e.findElement(FilesTab.chk_FileHistoryRecordDetailCheckbox);
			}
			else {
				log.info("Checking boxes...beta view");	
			if (e.findElement( FilesTab.lnk_BetaViewFileHistoryRecordDetailDitributionUser).getText().contains(thirdUser)) 
				uncheckActionRowCheckBox = e.findElement(FilesTab.chk_BetaViewFileHistoryRecordDetailCheckbox);
			}
		}
		uncheckActionRowCheckBox.click();
		if (action.equalsIgnoreCase("deactivate")) {
			if(!fileBetaViewFlag) {
				clickElementAndWaitForElement(FilesTab.drp_FileHistoryRecordDetailActionsDropdown, FilesTab.opt_FileHistoryRecordDetailActionsDeactivate);
				clickElementAndWaitForElement(FilesTab.opt_FileHistoryRecordDetailActionsDeactivate, FilesTab.btn_FileHistoryDeReactivateActionsContinue);
			}
			else {
				if(isDisplayed(FilesTab.drp_BetaFileViewHistoryRevDropdown))
					selectByIndex(FilesTab.drp_BetaFileViewHistoryRevDropdown, 1);
				clickElementAndWaitForElement(FilesTab.img_BetaViewFileHistoryRecordDetailActionDropdown, FilesTab.opt_BetaViewFileHistoryRecordDetailActionsDeactivate);
				clickElementAndWait(FilesTab.opt_BetaViewFileHistoryRecordDetailActionsDeactivate);
				waitUntilElementContainsText(FilesTab.lbl_BetaViewFileHistoryRecordsFirstStatus, "Inactive");
			}
			
		}
		else if (action.equalsIgnoreCase("reactivate")) {
			if(!fileBetaViewFlag) {
				clickElementAndWaitForElement(FilesTab.drp_FileHistoryRecordDetailActionsDropdown, FilesTab.opt_FileHistoryRecordDetailActionsReactivate);
				clickElementAndWaitForElement(FilesTab.opt_FileHistoryRecordDetailActionsReactivate, FilesTab.btn_FileHistoryDeReactivateActionsContinue);
			}
			else {
				clickElementAndWaitForElement(FilesTab.img_BetaViewFileHistoryRecordDetailActionDropdown, FilesTab.opt_BetaViewFileHistoryRecordDetailActionsReactivate);
				clickElementAndWait(FilesTab.opt_BetaViewFileHistoryRecordDetailActionsReactivate);
				waitUntilElementContainsText(FilesTab.lbl_BetaViewFileHistoryRecordsFirstStatus, "Incomplete");
			}
		}
		if(!fileBetaViewFlag)
			clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_FileHistoryDeReactivateActionsContinue, GlobalPageElements.pop_PopUpElement);
		verifyAuditHistoryForDeReactivation(firstUser, secondUser, thirdUser, forthUser, true);
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
	}

	public void verifyDocumentVisibilityAndCounts(String countFlag, String visibility, String user) {

		if (visibility.equals("Visible") && countFlag.equalsIgnoreCase("reduced")) {
			AutomationAssert.verifyTrue(isDisplayed(FilesTab.lnk_DocListingFirstDocRef));
			if (isDisplayed(GlobalPageElements.lnk_FirstMyActionCountPopOver)) {
				mouseHover(GlobalPageElements.lnk_FirstMyActionCountPopOver);
				AutomationAssert.verifyTrue(getCount(GlobalPageElements.css_firstActionsPopoverContentLinks) + " should be less than " + actionListSize, getCount(GlobalPageElements.css_firstActionsPopoverContentLinks) < actionListSize);
			}

		}
		else if (visibility.equals("Visible") && countFlag.equalsIgnoreCase("increased")) {
			AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(FilesTab.lnk_DocListingFirstDocRef, true), isDisplayed(FilesTab.lnk_DocListingFirstDocRef));
			mouseHoverFirstActionPopOver();
			AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getCount(GlobalPageElements.css_firstActionsPopoverContentLinks), actionListSize), getCount(GlobalPageElements.css_firstActionsPopoverContentLinks) == actionListSize);

			if (user.equalsIgnoreCase(ResourceHandler.loadProperty("test.user.rfi.bloggs.name")))
				AdoddleScenarioMarkers.deactivateReactivateDocActionsFlag = true;
		}
		else if (visibility.equals("Invisible") && countFlag.equalsIgnoreCase("reduced")) {
			AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(FilesTab.lnk_DocListingFirstDocRef, false), !isDisplayed(FilesTab.lnk_DocListingFirstDocRef));

		}
		else if (visibility.equals("Invisible") && countFlag.equalsIgnoreCase("increased")) {
			AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(FilesTab.lnk_DocListingFirstDocRef, false), !isDisplayed(FilesTab.lnk_DocListingFirstDocRef));
		}

	}

	public void verifyDocumentRevision(String countFlag, String revisionFlag) {
		if (revisionFlag.equalsIgnoreCase("current"))
			AutomationAssert.verifyTrue(getElementText(FilesTab.lnk_FileListingFirstVersion).equalsIgnoreCase("2"));
		else if (revisionFlag.equalsIgnoreCase("previous"))
			AutomationAssert.verifyTrue(getElementText(FilesTab.lnk_FileListingFirstVersion).equalsIgnoreCase("1"));
	}

	/*** Common Script Methods ***/

	public void verifyAuditHistoryForDeReactivation(String user1, String user2, String user3, String user4, boolean deactivationFlag) {
		reloadPage();
		waitForCompletePageLoad();
		if(!fileBetaViewFlag) {
			waitUntilElementIsDisplayed(FilesTab.lnk_FileViewLHHistoryBlock);
			clickElementAndWaitForElement(FilesTab.lnk_FileViewLHHistoryBlock, FilesTab.drp_FileViewHistoryTypeDropdown);
		}
		else {
			clickElementAndWaitForElement(FilesTab.btn_BetaFileViewMoreOptions, FilesTab.btn_BetaFileViewFileHistory);
			clickElementAndWaitForElement(FilesTab.btn_BetaFileViewFileHistory, FilesTab.ele_BetaFileViewHistoryPanel);
			clickElementAndWaitForElement(FilesTab.img_BetaFileViewMaximizePanel, FilesTab.lbl_BetaFileViewHeaderRemarks);
		}
		
		List<WebElement> historyRecordsRemarks = findElements(!fileBetaViewFlag ? FilesTab.css_FileHistoryRecordsListRemarks: FilesTab.css_BetaViewFileHistoryRecordsListRemarks);

		if (deactivationFlag) {
			for (WebElement e : historyRecordsRemarks) {
				if (e.getText().contains("Action Deactivated")) {
					System.out.println("Deactivation Logs: " + e.getText());
					AutomationAssert.verifyTrue(e.getText().contains(user1) || e.getText().contains(user2) || e.getText().contains(user3) || e.getText().contains(user4));
				}
			}
		}
		else {
			for (WebElement e : historyRecordsRemarks) {
				if (e.getText().contains("Action Reactivated")) {
					System.out.println("Reactivation Logs: " + e.getText());
					AutomationAssert.verifyTrue(e.getText().contains(user1) || e.getText().contains(user2) || e.getText().contains(user3) || e.getText().contains(user4));
				}
			}
		}

	}

	public void deactivateFolder(String projectTitle) {
		try {
			deactivateProjectFolder(projectTitle, folderTitle);
		}
		catch (Throwable t) {
			log.error("Error while deactivating folder");

		}
	}

}