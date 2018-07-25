package org.asite.automation.classic.p1.scripts;

import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.ClassicLandingLocators.LandingPage;
import org.asite.automation.CommonLocators.ClassicMyHomeLocators.MyHomePage;
import org.asite.automation.CommonLocators.ClassicWSLandingLocators.WSLandingPage;
import org.asite.automation.CommonLocators.ClassicWorkspaceLocators.WorkspaceTabPage;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.ClassicCommonAppMethods;

public class CreateEditWorkSpaceClassicScripts extends ClassicCommonAppMethods {

	final private String projectName = "AutomationProject", projectNameEdited = "AutomationProject_Edited",
			epoch = getCurrentDate();
	private String dropDownEditWorkspace;

	public void clickOnWorkSpaceTab() {
		clickElementAndWaitForElement(LandingPage.lnk_MyHome, MyHomePage.tab_WorkSpace);
		clickElementAndWait(MyHomePage.tab_WorkSpace);
		waitForCompletePageLoad();
	}

	public void clickOnAddNewWorkspace() {
		switchDefault();
		switchIframe(LandingPage.frm_AsiteWorkingFrame);
		switchIframe(LandingPage.frm_AsiteMainFrame);
		clickElementAndWaitForElement(WorkspaceTabPage.lnk_AddNewWorkspace, LandingPage.frm_PmFrame);
		waitAndSwitchIframe(LandingPage.frm_PmFrame);
	}

	public void verifyCreateEditWorkspacePage(String pageTitle) {
		AutomationAssert.verifyTrue(
				eStringUtils.getInEqualityStringError(getElementText(GlobalPageElements.lbl_PageTitle), (pageTitle)),
				getElementText(GlobalPageElements.lbl_PageTitle).equalsIgnoreCase(pageTitle));
	}

	public void inputWorkSpaceName() {
		waitUntilElementIsDisplayed(WorkspaceTabPage.txt_CreateEditWorkspaceNameInput);
		sendKeys(WorkspaceTabPage.txt_CreateEditWorkspaceNameInput, projectName + epoch);
		collectionDataMap.put("Project Name", projectName + epoch);

		if (trainingFlag)
			selectByVisibleText(WorkspaceTabPage.drp_CreateEditWorkspaceClient, "Asite Solutions Ltd");
		else
			selectByVisibleText(WorkspaceTabPage.drp_CreateEditWorkspaceClient, "Automation Testing Classic");

	}

	public void selectInstalledApplication() {
		selectByIndex(WorkspaceTabPage.drp_CreateEditWorkspaceInstalledApp, 1);
	}

	public void selectGeaography(String Geography) {
		selectByVisibleText(WorkspaceTabPage.drp_CreateEditWorkspaceGeography, Geography);

	}

	public void selectViewer(String viewer) {
		selectByVisibleText(WorkspaceTabPage.drp_CreateEditWorkspaceViewer, viewer);
	}

	public void clickSaveLink() {
		clickElementAndWait(WorkspaceTabPage.lnk_CreateEditWorkspaceSave);
		waitForCompletePageLoad();
	}

	public void verifyWorkspaceIsSaved() {
		waitUntilElementIsInvisible(WorkspaceTabPage.lnk_CreateEditWorkspaceSave);
	}

	public void clickBackImage() {
		clickElementAndWait(WorkspaceTabPage.lnk_CreateEditWorkspaceBack);
		reloadPage();
		waitForCompletePageLoad();
	}

	public void verifyWorkspaceInFavorite() throws InterruptedException {
		boolean flag = false;
		for (int index = 0; index < 5; index++) {
			try {
				switchMultiFrames();
				waitUntilElementIsInvisible(GlobalPageElements.ele_PleaseWaitLoadingDataMessage);
				AutomationAssert.verifyTrue(isDisplayedLinkWithText(projectName + epoch));
				if (isDisplayedLinkWithText(projectName + epoch)) {
					flag = true;
					break;
				}
			} catch (Throwable t) {
				waitUtils.waitInterval(15);
				reloadPage();
				waitUntilElementIsInvisible(GlobalPageElements.ele_PleaseWaitLoadingDataMessage);
				waitForCompletePageLoad();
				index++;
			}
		}
		AutomationAssert.verifyTrue(flag);
	}

	public void clickOnCreatedWorkspace() {
		clickLinkWithText(projectName + epoch);
	}

	public void verifyWorkspaceHomePage() {
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteHeaderFrame);
		AutomationAssert.verifyTrue(getElementText(WSLandingPage.lbl_WorkspaceHeaderName).equalsIgnoreCase(
				projectName + epoch));
	}

	public void selectEditWorkspace(String dropdownOption) {
		dropDownEditWorkspace = dropdownOption;
		selectByVisibleText(WSLandingPage.drp_AdminDropdown, dropDownEditWorkspace);
		waitForCompletePageLoad();
		switchMultiFrames();
		waitAndSwitchIframe(LandingPage.frm_PmFrame);
	}

	public void editWorkspaceAndSave() {
		waitUntilElementIsDisplayed(WorkspaceTabPage.txt_CreateEditWorkspaceNameInput);
		sendKeys(WorkspaceTabPage.txt_CreateEditWorkspaceNameInput, projectNameEdited + epoch);
		clickElementAndWaitForInvisibilityOfElement(WorkspaceTabPage.lnk_CreateEditWorkspaceSave,
				WorkspaceTabPage.lnk_CreateEditWorkspaceSave);
	}

	public void verifyWorkspaceIsEdited() {
		reloadPage();
		waitForCompletePageLoad();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteHeaderFrame);
		waitUntilElementIsDisplayed(WSLandingPage.lbl_WorkspaceHeaderName);
		AutomationAssert.verifyTrue(getElementText(WSLandingPage.lbl_WorkspaceHeaderName).equalsIgnoreCase(
				projectNameEdited + epoch));
	}

	public void closeUpdatedWorkSpace(String closeOptionText) {
		selectByVisibleText(WSLandingPage.drp_AdminDropdown, dropDownEditWorkspace);
		waitForCompletePageLoad();
		switchMultiFrames();
		waitAndSwitchIframe(LandingPage.frm_PmFrame);
		selectByVisibleText(WorkspaceTabPage.drp_CreateEditWorkspaceStatus, closeOptionText);
		clickElementAndWaitForInvisibilityOfElement(WorkspaceTabPage.lnk_CreateEditWorkspaceSave,
				WorkspaceTabPage.lnk_CreateEditWorkspaceSave);
	}

	public void verifyWorkspaceIsClosed() {
		AutomationAssert.verifyTrue(!isDisplayedLinkWithText(projectNameEdited + epoch));
	}
}