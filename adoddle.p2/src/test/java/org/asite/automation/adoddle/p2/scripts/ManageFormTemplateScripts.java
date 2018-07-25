/*  Testdata required for this script as follows.
     1). ManageFormTemplate_Test.xsn should be available in TestData folder.
 */

package org.asite.automation.adoddle.p2.scripts;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ManageFormTemplateScripts extends AdoddleCommonAppMethods {

	private String			projectName				= null;
	private String			formTitle;
	private String			formGroupCode;
	private String			formGroupName;
	private String			formID;
	private String			controllerFormTitle;
	static private String	timeStamp				= null;
	private String			parentHandle			= null;
	final static private    Logger	log				= AutomationLogger.getInstance().getLogger(ManageFormTemplateScripts.class);

	public void createNewTestProject(String projectTitle) {
		timeStamp = dateUtils.getEpoch();
		projectName = projectTitle + timeStamp;
		createTestProject(projectName, dataCenter);
		navigateTab(LandingPage.lnk_Projects);
		searchProjects(projectName);
		collectionDataMap.put("Project",projectName);
		createProjectFolder(projectName, timeStamp);
		clickElementAndWaitForElement(LandingPage.lnk_Projects, ProjectsTab.txt_ProjectsFilterInput);
		searchProjects(projectName);
	}

	public void addFormTemplateToProject(String addToProjectBtn) {
		waitUntilElementIsDisplayed(ProjectsTab.pop_AssignAppsModal);
		formTitle = getElementText(ProjectsTab.lbl_PopAssignAppsFirstFormTemplateDesc);
		collectionDataMap.put("form title", formTitle);
		clickElementAndWaitForElement(ProjectsTab.chk_PopAssignAppsFirstFormCheckbox, ProjectsTab.btn_PopAssignAppsAddtoProject);
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getElementText(ProjectsTab.btn_PopAssignAppsAddtoProject).trim(), addToProjectBtn), getElementText(ProjectsTab.btn_PopAssignAppsAddtoProject).trim().equalsIgnoreCase(addToProjectBtn));
		clickElementAndWait(ProjectsTab.btn_PopAssignAppsAddtoProject);
	}

	public void searchFormTemplateAndContextClick() {
		sendKeys(ProjectsTab.txt_PopManageAppsModalSearchInput, formTitle);
		contextClick(ProjectsTab.ele_PopManageAppsFirstFormStatus);
		waitUntilElementIsDisplayed(ProjectsTab.ele_PopManageAppsFormStatusContextMenu);
	}

	public void activateForm(String activateText) {
		if (getElementText(ProjectsTab.ele_PopManageAppsFormStatusContextMenu).equalsIgnoreCase(activateText))
			clickElementAndWait(ProjectsTab.ele_PopManageAppsFormStatusContextMenu);
		waitForCompletePageLoad();
	}

	public void verifyStatusChange(String status) {
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getElementText(ProjectsTab.ele_PopManageAppsFirstFormStatus), status), getElementText(ProjectsTab.ele_PopManageAppsFirstFormStatus).equalsIgnoreCase(status));
	}

	public void searchCreatedProject() {
		searchProjects(projectName);
	}

	public void clickLHOptionMenu(String optionMenu) {
		if (optionMenu.equalsIgnoreCase(AdoddleCommonStringPool.OPTION_MANAGEROLES))
			clickElementAndWait(ProjectsTab.lnk_PopManageRolesManageRoles);
		else
			clickLinkWithText(optionMenu);
	}

	public void clickFormNameDropdownAndSearchForm() {
		clickElementAndWaitForElement(ProjectsTab.btn_PopRoleFormPermissionsFormNameSelector, ProjectsTab.txt_PopRoleFormPermissionFormNameSearch);
		sendKeys(ProjectsTab.txt_PopRoleFormPermissionFormNameSearch, formTitle);
		waitUntilElementIsDisplayed(ProjectsTab.chk_PopRoleFormPermissionFormNameFirstCheckbox);
	}

	public void selectFormAndClickClose(String closeLink) {
		clickElementAndWaitForElement(ProjectsTab.chk_PopRoleFormPermissionFormNameFirstCheckbox, GlobalPageElements.lnk_GlobalFilterCloseLink);
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getElementText(GlobalPageElements.lnk_GlobalFilterCloseLink), closeLink), getElementText(GlobalPageElements.lnk_GlobalFilterCloseLink).equalsIgnoreCase(closeLink));
		clickElementAndWait(GlobalPageElements.lnk_GlobalFilterCloseLink);
	}

	public void verifyFormIsFiltered() {
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getCount(ProjectsTab.lbl_PopRoleFormPermissionFilteredFormNameHeader), 1), getCount(ProjectsTab.lbl_PopRoleFormPermissionFilteredFormNameHeader) == 1);
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectsTab.lbl_PopRoleFormPermissionFilteredFormNameHeader), formTitle), getElementText(ProjectsTab.lbl_PopRoleFormPermissionFilteredFormNameHeader).contains(formTitle));
	}

	public void clickNewOptionOnLHPanel(String newLinkOption) {
		clickLinkWithText(newLinkOption);
		waitUntilElementIsDisplayed(ProjectFormsTab.frm_createFormIframe);
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		waitUntilElementIsDisplayed(ProjectFormsTab.txt_PopCreateFormSearchFormInput);
	}

	public void selectCreatedProject() {
		clickElementWithText(projectName);
		waitForCompletePageLoad();
	}

	public void searchFormOnCreateFormWindow() {
		sendKeys(ProjectFormsTab.txt_PopCreateFormSearchFormInput, formTitle);
		waitForCompletePageLoad();
	}

	public void verifyFormIsFilteredOnCreateFormWindow() {
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getElementText(ProjectFormsTab.lbl_PopCreateFormFirstFormName), formTitle), getElementText(ProjectFormsTab.lbl_PopCreateFormFirstFormName).equalsIgnoreCase(formTitle));
		reloadPage();

	}

	public void searchFormAndClickEditIcon() {
		sendKeys(ProjectsTab.txt_PopManageAppsModalSearchInput, formTitle);
		clickElementAndWaitForElement(ProjectsTab.img_PopManageAppsModelFirstEditImage, ProjectsTab.pop_EditAppSettings);
	}

	public void editFormNameGroupAndCodeGroupName() {
		formTitle = "TestFormName" + dateUtils.getEpoch();
		formGroupCode = javaUtils.getRandomString(4);
		collectionDataMap.put("form group code", formGroupCode);
		formGroupName = "TestFormGroupName" + timeStamp;
		collectionDataMap.put("form group", formGroupName);
		formID = timeStamp;
		collectionDataMap.put("form id", formID);
		sendKeys(ProjectsTab.txt_PopEditAppSettingsFormName, formTitle);
		sendKeys(ProjectsTab.txt_PopEditAppSettingsFormGroupCode, formGroupCode);
		sendKeys(ProjectsTab.txt_PopEditAppSettingsFormGroupName, formGroupName);
		sendKeys(ProjectsTab.txt_PopEditAppSettingsFormAppBuilderCode, formID);
	}

	public void downloadXsnAndUpload() {
		findElement(ProjectsTab.btn_PopEditAppSettingsXsnBrowseButton).sendKeys(nodeIP + ResourceHandler.loadProperty("manage.form.template.xsn.path"));
		clickElementAndWaitUntilElementInvisible(ProjectsTab.btn_PopEditAppSettingsXsnUpload, ProjectsTab.Pop_TemplateUploadWaitPopup);
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getElementText(ProjectsTab.lnk_PopEditAppSettingsDownloadFormXsn), strUtils.extractFileNameString(nodeIP + ResourceHandler.loadProperty("manage.form.template.xsn.path"))), getElementText(ProjectsTab.lnk_PopEditAppSettingsDownloadFormXsn).equalsIgnoreCase(strUtils.extractFileNameString(nodeIP + ResourceHandler.loadProperty("manage.form.template.xsn.path"))));

	}

	public void editAllResponseSettings(String radioFlag) {
		if (radioFlag.equalsIgnoreCase("yes")) {

			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsResponseAllowedYes);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsEnableDraftResponseYes);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsResponderCollabYes);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsShowResponsesYes);

		}
		else if (radioFlag.equalsIgnoreCase("no")) {

			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsResponseAllowedNo);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsEnableDraftResponseNo);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsResponderCollabNo);
			/*
			 * clickElementAndWait(ProjectsTab.rad_PopEditAppSettingsShowResponsesNo); - #Bug
			 */
		}
	}

	public void editAllFormTemplateSettings(String radioFlag, String workspaceName) {
		if (radioFlag.equalsIgnoreCase("yes")) {
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsCrossWSDataConnectYes);
			sendKeys(ProjectsTab.txt_PopEditAppSettingsCrossWSName, workspaceName);
			waitUntilElementIsDisplayed(ProjectsTab.lnk_PopEditAppSettingsCrossWSAutoComplete);
			clickElementAndWait(ProjectsTab.lnk_PopEditAppSettingsCrossWSAutoComplete);
		}
		else if (radioFlag.equalsIgnoreCase("no"))
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsCrossWSDataConnectNo);
	}

	public void editAllControllerSettings(String radioFlag) {
		if (radioFlag.equalsIgnoreCase("yes")) {
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsUserControllerYes);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsControlChangeStatusYes);
		}
		else if (radioFlag.equalsIgnoreCase("no")) {

			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsUserControllerNo);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsControlChangeStatusNo);

		}
	}

	public void editAllActionRequiredSettings(String radioFlag) throws InterruptedException {
		int counter = 0;
		List<WebElement> actions = findElements(ProjectsTab.css_PopEditAppSettingsActionRequiredCheckboxes);
		List<WebElement> actionTimeElements = findElements(ProjectsTab.css_PopEditAppSettingsActionRequiredTimeDropdowns);

		for (WebElement e : actions) {
			counter++;

			if (!e.isSelected() && counter != 8 && radioFlag.equalsIgnoreCase("yes")) {
				waitUtils.waitInterval(1);
				e.click();
			}
			else if (e.isSelected() && counter != 8 && radioFlag.equalsIgnoreCase("no")) {
				waitUtils.waitInterval(1);
				e.click();
			}
			else if (!e.isSelected() && counter == 8 && radioFlag.equalsIgnoreCase("no")) {
				waitUtils.waitInterval(1);
				e.click();
			}

		}

		counter = 0;

		for (WebElement e : actionTimeElements) {
			counter++;

			if (radioFlag.equalsIgnoreCase("yes") && counter != 8) {
				selectByVisibleText(e, "1");
			}
			else if (radioFlag.equalsIgnoreCase("yes") && counter == 8) {
				waitUtils.waitInterval(1);
				selectByIndex(ProjectsTab.drp_PopEditAppSettingsDefaultActionRequired, 1);
			}
			else if (radioFlag.equalsIgnoreCase("no") && counter != 8) {
				selectByVisibleText(e, AdoddleCommonStringPool.LABEL_PLEASE_SELECT);
			}
			else if (radioFlag.equalsIgnoreCase("no") && counter == 8) {
				selectByVisibleText(e, "1");
			}

		}
	}

	public void editAllDistributionSettings(String radioFlag) {
		if (radioFlag.equalsIgnoreCase("yes")) {
			clickElementAndWait(ProjectsTab.rad_PopEditAppSettingsDistributionOnORIMandatory);
			/* Don't Allow Distribution if Controller Form */
			/*
			 * clickElementAndWait(ProjectsTab. rad_PopEditAppSettingsDistributionAfterCreationYes); clickElementAndWait (ProjectsTab.rad_PopEditAppSettingsDistributionByOriginatorYes); clickElementAndWait (ProjectsTab.rad_PopEditAppSettingsDistributionByRecipientsYes);
			 */
		}

		else {
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsDistributionOnORINotRequired);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsDistributionAfterCreationNo);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsDistributionByOriginatorNo);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsDistributionByRecipientsNo);
		}
	}

	public void editAndForwardSettings(String radioFlag) {
		if (radioFlag.equalsIgnoreCase("yes"))
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAllowEditForwardYes);
		else {
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAllowEditForwardNo);
			clickElementIfEnabled(ProjectsTab.rad_PopEditappSettingsAllowEditORIYes);
		}

	}

	public void editAllAttachmentSettings(String radioFlag) {
		if (radioFlag.equalsIgnoreCase("yes")) {
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAllowAttachmentYes);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAutoPublishFolderYes);
			// clickElementIfEnabled(ProjectsTab.btn_PopEditAppSettingsSelectFolder);
			clickElementAndWaitForElement(ProjectsTab.btn_PopEditAppSettingsSelectFolder, ProjectsTab.lnk_PopSelectFolderProjectFirstFolder);
			clickElementAndWaitForElement(ProjectsTab.lnk_PopSelectFolderProjectFirstFolder, ProjectsTab.lbl_PopSelectFolderSelectedFolder);
			clickElementAndWaitForInvisibilityOfElement(ProjectsTab.btn_PopSelectFolderSaveButton, ProjectsTab.btn_PopSelectFolderSaveButton);
		}
		else if (radioFlag.equalsIgnoreCase("no")) {
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAllowAttachmentNo);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAutoPublishFolderNo);
		}
	}

	public void editAllFormStatusSettings(String radioFlag) {
		if (radioFlag.equalsIgnoreCase("yes"))
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsOverAllFormStatusesYes);
		else
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsOverAllFormStatusesNo);
		/* code for Form Final statues */

		List<WebElement> formStatuses = findElements(ProjectsTab.css_PopEditAppSettingsFormFinalStatusesCheckboxes);

		for (WebElement e : formStatuses) {
			if (!e.isSelected() && radioFlag.equalsIgnoreCase("yes"))
				e.click();
			else if (e.isSelected() && radioFlag.equalsIgnoreCase("no"))
				e.click();
		}

		List<WebElement> closeOutStatues = findElements(ProjectsTab.css_PopEditAppSettingsCloseOutStatuesCheckboxes);

		for (WebElement e : closeOutStatues) {
			if (!e.isSelected() && radioFlag.equalsIgnoreCase("yes"))
				e.click();
			else if (e.isSelected() && radioFlag.equalsIgnoreCase("no"))
				e.click();
		}

	}

	public void editAllAdditionalFormSettings(String radioFlag) {

		if (radioFlag.equalsIgnoreCase("yes")) {

			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAllowReopenFormsYes);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsOrigChangeStatusYes);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsFormIsPublicYes);
			if (!isSelected(ProjectsTab.chk_PopEditAppSettingsEnableSpellCheckOnRequest))
				clickElementIfEnabled(ProjectsTab.chk_PopEditAppSettingsEnableSpellCheckOnRequest);
			if (!isSelected(ProjectsTab.chk_PopEditAppSettingsEnableSpellCheckOnSavingForm))
				clickElementIfEnabled(ProjectsTab.chk_PopEditAppSettingsEnableSpellCheckOnSavingForm);

			selectByVisibleText(ProjectsTab.drp_PopEditAppSettingsRestrictStatusChange, radioFlag);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsUseFormDistrinGroupYes);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAllowAutoCreationStatusChangeYes);
			sendKeys(ProjectsTab.txt_PopEditAppSettingsNotificaitonEmailSubject, System.getProperty("primary.username"));
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAFormAvailableOfflineYes);

			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsFormContentInEmailYes);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsCanReplyViaEmailsYes);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAllowExternalAccessYes);

		}
		else if (radioFlag.equalsIgnoreCase("no")) {
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAllowReopenFormsNo);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsOrigChangeStatusNo);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsFormIsPublicNo);
			if (isSelected(ProjectsTab.chk_PopEditAppSettingsEnableSpellCheckOnRequest))
				clickElementIfEnabled(ProjectsTab.chk_PopEditAppSettingsEnableSpellCheckOnRequest);
			if (isSelected(ProjectsTab.chk_PopEditAppSettingsEnableSpellCheckOnSavingForm))
				clickElementIfEnabled(ProjectsTab.chk_PopEditAppSettingsEnableSpellCheckOnSavingForm);

			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsUseFormDistrinGroupNo);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAllowAutoCreationStatusChangeNo);
			sendKeys(ProjectsTab.txt_PopEditAppSettingsNotificaitonEmailSubject, "");
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAFormAvailableOfflineNo);

			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsFormContentInEmailNo);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsCanReplyViaEmailsNo);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAllowExternalAccessNo);
		}

	}

	public void editAllAssociationSettings(String radioFlag) {

		if (radioFlag.equalsIgnoreCase("yes")) {
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAllowFormAssociationYes);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAssocBypassSecurityYes);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAllowDocAssociationYes);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAssocExtendDocIssueYes);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAllowCommentAssocYes);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAssocBypassFolderSecurityYes);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAllowViewAssociationYes);
		}
		else if (radioFlag.equalsIgnoreCase("no")) {
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAllowFormAssociationNo);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAssocBypassSecurityNo);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAllowDocAssociationNo);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAssocExtendDocIssueNo);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAllowCommentAssocNo);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAssocBypassFolderSecurityNo);
			clickElementIfEnabled(ProjectsTab.rad_PopEditAppSettingsAllowViewAssociationNo);
		}
	}

	public void saveTheFormTemplateSettings() {
		clickElementAndWaitForElement(ProjectsTab.btn_PopEditAppSettingsSave, GlobalPageElements.btn_AlertModalOK);
		clickElementAndWaitForInvisibilityOfElement(GlobalPageElements.btn_AlertModalOK, ProjectsTab.btn_PopEditAppSettingsSave);
	}

	public void verifySavedFormTemplateSettings(String radioFlag) throws IOException, InterruptedException {

		sendKeys(ProjectsTab.txt_PopManageAppsModalSearchInput, formTitle);
		clickElementAndWaitForElement(ProjectsTab.img_PopManageAppsModelFirstEditImage, ProjectsTab.pop_EditAppSettings);
		verifyFormNameGroupAndCodeGroupName();
		verifyAllResponseSettings(radioFlag);
		verifyAllControllerSettings(radioFlag);
		verifyAllActionRequiredSettings(radioFlag);
		verifyAndForwardSettings(radioFlag);
		verifyAllAttachmentSettings(radioFlag);
		verifyAllFormStatusSettings(radioFlag);
		verifyAllAdditionalFormSettings(radioFlag);
	}

	private void verifyFormNameGroupAndCodeGroupName() {
		AutomationAssert.verifyTrue(getValue(ProjectsTab.txt_PopEditAppSettingsFormName).trim().equalsIgnoreCase(formTitle));
		AutomationAssert.verifyTrue(getValue(ProjectsTab.txt_PopEditAppSettingsFormGroupCode).trim().equalsIgnoreCase(formGroupCode));
		AutomationAssert.verifyTrue(getValue(ProjectsTab.txt_PopEditAppSettingsFormGroupName).trim().equalsIgnoreCase(formGroupName));
		AutomationAssert.verifyTrue(getValue(ProjectsTab.txt_PopEditAppSettingsFormAppBuilderCode).equalsIgnoreCase(formID));
	}

	private void verifyAllResponseSettings(String radioFlag) throws IOException, InterruptedException {
		if (radioFlag.equalsIgnoreCase("yes")) {
			verifyXSNDownload();
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsResponseAllowedYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsResponseAllowedYes));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsEnableDraftResponseYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsEnableDraftResponseYes));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsResponderCollabYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsResponderCollabYes));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsShowResponsesYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsShowResponsesYes));
		}
		else if (radioFlag.equalsIgnoreCase("no")) {
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsResponseAllowedNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsResponseAllowedNo));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsEnableDraftResponseNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsEnableDraftResponseNo));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsResponderCollabNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsResponderCollabNo));
		}
	}

	private void verifyXSNDownload() throws IOException, InterruptedException {
		String xsnFile = getElementText(ProjectsTab.lnk_PopEditAppSettingsDownloadFormXsn);
		clickElementAndWait(ProjectsTab.lnk_PopEditAppSettingsDownloadFormXsn);
		File defaultFile = new File(nodeIP + ResourceHandler.loadProperty("default.browser.download.file.path") + xsnFile);

		File file = new File(nodeIP + resourceUtils.getRemoteFileDownloadPath() + "TestXsn" + timeStamp + AdoddleCommonStringPool.XSN_EXTENSION);
		/*autoItUtils.downloadAutoIt(file.toString(), nodeIP);*/
		sysUtils.waitUntilFileIsDownloaded(defaultFile);
		defaultFile.renameTo(file);
		sysUtils.waitUntilFileIsDownloaded(file);
		for (int index = 0; index < 10; index++) {
			if (sysUtils.getFileSize(file.toString()) == sysUtils.getFileSize(nodeIP + ResourceHandler.loadProperty("manage.form.template.xsn.path")))
				break;
			else
				waitUtils.waitInterval(1);
		}
	}

	private void verifyAllControllerSettings(String radioFlag) {
		if (radioFlag.equalsIgnoreCase("yes")) {
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsUserControllerYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsUserControllerYes));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsControlChangeStatusYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsControlChangeStatusYes));
		}
		else if (radioFlag.equalsIgnoreCase("No")) {

			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsUserControllerNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsUserControllerNo));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsControlChangeStatusNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsControlChangeStatusNo));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditappSettingsAllowEditORIYes, true), isSelected(ProjectsTab.rad_PopEditappSettingsAllowEditORIYes));
		}
	}

	private void verifyAllActionRequiredSettings(String radioFlag) {
		int counter = 0;
		List<WebElement> actions = findElements(ProjectsTab.css_PopEditAppSettingsActionRequiredCheckboxes);
		List<WebElement> actionTimeElements = findElements(ProjectsTab.css_PopEditAppSettingsActionRequiredTimeDropdowns);

		for (WebElement e : actions) {
			counter++;
			if (counter != 8 && radioFlag.equalsIgnoreCase("yes"))
				AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(e, true), e.isSelected());
			else if (counter != 8 && counter != 6 && radioFlag.equalsIgnoreCase("no"))
				/* Only For Info is checked - rest are unchecked */
				AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(e, false), !e.isSelected());
		}

		counter = 0;

		for (WebElement e : actionTimeElements) {
			counter++;
			if (counter != 8 && radioFlag.equalsIgnoreCase("yes")) {
				Select dropDown = new Select(e);
				AutomationAssert.verifyTrue(dropDown.getFirstSelectedOption().getText().equalsIgnoreCase("1"));
			}
			else if (counter != 8 && radioFlag.equalsIgnoreCase("no")) {
				Select dropDown = new Select(e);
				AutomationAssert.verifyTrue(dropDown.getFirstSelectedOption().getText().equalsIgnoreCase(AdoddleCommonStringPool.PLEASE_SELECT));
			}
		}
	}

	/*public void verifyAllDistributionSettings(String radioFlag) {
		if (radioFlag.equalsIgnoreCase("yes")) {
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsDistributionOnORIMandatory, true), isSelected(ProjectsTab.rad_PopEditAppSettingsDistributionOnORIMandatory));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsDistributionAfterCreationYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsDistributionAfterCreationYes));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsDistributionByOriginatorYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsDistributionByOriginatorYes));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsDistributionByRecipientsYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsDistributionByRecipientsYes));
		}
		else {
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsDistributionOnORINotRequired, true), isSelected(ProjectsTab.rad_PopEditAppSettingsDistributionOnORINotRequired));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsDistributionAfterCreationNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsDistributionAfterCreationNo));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsDistributionByOriginatorNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsDistributionByOriginatorNo));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsDistributionByRecipientsNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsDistributionByRecipientsNo));
		}
	}*/

	private void verifyAndForwardSettings(String radioFlag) {
		if (radioFlag.equalsIgnoreCase("yes"))
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAllowEditForwardYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAllowEditForwardYes));
		else
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAllowEditForwardNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAllowEditForwardNo));
	}

	private void verifyAllAttachmentSettings(String radioFlag) {
		if (radioFlag.equalsIgnoreCase("yes")) {

			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAllowAttachmentYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAllowAttachmentYes));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAutoPublishFolderYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAutoPublishFolderYes));
		}
		else if (radioFlag.equalsIgnoreCase("no")) {

			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAllowAttachmentNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAllowAttachmentNo));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAutoPublishFolderNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAutoPublishFolderNo));
		}
	}

	private void verifyAllFormStatusSettings(String radioFlag) {
		if (radioFlag.equalsIgnoreCase("yes"))
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsOverAllFormStatusesYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsOverAllFormStatusesYes));
		else
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsOverAllFormStatusesNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsOverAllFormStatusesNo));

		List<WebElement> formStatuses = findElements(ProjectsTab.css_PopEditAppSettingsFormFinalStatusesCheckboxes);

		for (WebElement e : formStatuses) {
			if (radioFlag.equalsIgnoreCase("yes"))
				AutomationAssert.verifyTrue(e.isSelected());
			else if (radioFlag.equalsIgnoreCase("no"))
				AutomationAssert.verifyTrue(!e.isSelected());
		}

		List<WebElement> closeOutStatues = findElements(ProjectsTab.css_PopEditAppSettingsCloseOutStatuesCheckboxes);
		for (WebElement e : closeOutStatues) {
			if (radioFlag.equalsIgnoreCase("yes"))
				AutomationAssert.verifyTrue(e.isSelected());
			else if (radioFlag.equalsIgnoreCase("no"))
				AutomationAssert.verifyTrue(!e.isSelected());
		}

	}

	private void verifyAllAdditionalFormSettings(String radioFlag) {

		if (radioFlag.equalsIgnoreCase("yes")) {

			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAllowReopenFormsYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAllowReopenFormsYes));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsOrigChangeStatusYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsOrigChangeStatusYes));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsFormIsPublicYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsFormIsPublicYes));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.chk_PopEditAppSettingsEnableSpellCheckOnRequest, true), isSelected(ProjectsTab.chk_PopEditAppSettingsEnableSpellCheckOnRequest));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.chk_PopEditAppSettingsEnableSpellCheckOnSavingForm, true), isSelected(ProjectsTab.chk_PopEditAppSettingsEnableSpellCheckOnSavingForm));
			AutomationAssert.verifyTrue(getSelectedDropdownLabel(ProjectsTab.drp_PopEditAppSettingsRestrictStatusChange).trim().equalsIgnoreCase(radioFlag));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsUseFormDistrinGroupYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsUseFormDistrinGroupYes));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAllowAutoCreationStatusChangeYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAllowAutoCreationStatusChangeYes));
			AutomationAssert.verifyTrue(getValue(ProjectsTab.txt_PopEditAppSettingsNotificaitonEmailSubject).equalsIgnoreCase(System.getProperty("primary.username")));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAFormAvailableOfflineYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAFormAvailableOfflineYes));

			try {
				AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsFormContentInEmailYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsFormContentInEmailYes));
				AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsCanReplyViaEmailsYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsCanReplyViaEmailsYes));
				AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAllowExternalAccessYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAllowExternalAccessYes));
			}
			catch (Throwable t) {
				log.info("Error handling in catch block...");
			}
		}
		else if (radioFlag.equalsIgnoreCase("no")) {

			/*
			 * AutomationAssert.verifyTrue(isSelected(ProjectsTab. rad_PopEditAppSettingsAllowReopenFormsNo)); - Query
			 */
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsOrigChangeStatusNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsOrigChangeStatusNo));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsFormIsPublicNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsFormIsPublicNo));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.chk_PopEditAppSettingsEnableSpellCheckOnRequest, true), !isSelected(ProjectsTab.chk_PopEditAppSettingsEnableSpellCheckOnRequest));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.chk_PopEditAppSettingsEnableSpellCheckOnSavingForm, true), !isSelected(ProjectsTab.chk_PopEditAppSettingsEnableSpellCheckOnSavingForm));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsUseFormDistrinGroupNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsUseFormDistrinGroupNo));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAllowAutoCreationStatusChangeNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAllowAutoCreationStatusChangeNo));
			AutomationAssert.verifyTrue(getValue(ProjectsTab.txt_PopEditAppSettingsNotificaitonEmailSubject).equalsIgnoreCase(""));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAFormAvailableOfflineNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAFormAvailableOfflineNo));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsFormContentInEmailNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsFormContentInEmailNo));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsCanReplyViaEmailsNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsCanReplyViaEmailsNo));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAllowExternalAccessNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAllowExternalAccessNo));
		}

	}

	/*public void verifyAllAssociationSettings(String radioFlag) {

		if (radioFlag.equalsIgnoreCase("yes")) {
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAllowFormAssociationYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAllowFormAssociationYes));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAssocBypassSecurityYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAssocBypassSecurityYes));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAllowDocAssociationYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAllowDocAssociationYes));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAssocExtendDocIssueYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAssocExtendDocIssueYes));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAllowCommentAssocYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAllowCommentAssocYes));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAssocBypassFolderSecurityYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAssocBypassFolderSecurityYes));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAllowViewAssociationYes, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAllowViewAssociationYes));
		}
		else if (radioFlag.equalsIgnoreCase("no")) {
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAllowFormAssociationNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAllowFormAssociationNo));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAssocBypassSecurityNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAssocBypassSecurityNo));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAllowDocAssociationNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAllowDocAssociationNo));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAssocExtendDocIssueNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAssocExtendDocIssueNo));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAllowCommentAssocNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAllowCommentAssocNo));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAssocBypassFolderSecurityNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAssocBypassFolderSecurityNo));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectsTab.rad_PopEditAppSettingsAllowViewAssociationNo, true), isSelected(ProjectsTab.rad_PopEditAppSettingsAllowViewAssociationNo));
		}
	}*/

	public void closeAppSettingsWindow() {
	   /*if (!appBetaViewFlag) {
			try {
				clickElementAndWaitForInvisibilityOfElement(ProjectsTab.btn_PopEditAppSettingsClose, ProjectsTab.btn_PopEditAppSettingsCancel, 10);
			}
			catch (Throwable t) {
				clickElementAndWaitForInvisibilityOfElement(ProjectsTab.btn_PopEditAppSettingsClose, ProjectsTab.btn_PopEditAppSettingsCancel);
			}
		}
		else*/
			clickElementAndWait(ProjectsTab.btn_PopEditAppSettingsCancel);
	}

	public void searchUpdatedFormOnCreateFormWindow() {
		sendKeys(ProjectFormsTab.txt_PopCreateFormSearchFormInput, formTitle);
		waitForCompletePageLoad();
	}

	public void clickOnUpdatedFormName() {
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getElementText(ProjectFormsTab.lbl_PopCreateFormFirstFormName), formTitle), getElementText(ProjectFormsTab.lbl_PopCreateFormFirstFormName).equalsIgnoreCase(formTitle));
		clickElementAndWait(ProjectFormsTab.lbl_PopCreateFormFirstFormName);
		switchDefault();
	}

	public void verifyControllerOptionAvailability(String controlFlag) {
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		if (controlFlag.equalsIgnoreCase("yes")) {
			waitUntilElementIsDisplayed(ProjectFormsTab.drp_CreateFormControllerDropdown);
			/*if (!appBetaViewFlag) {
				AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.ele_CreateFormHeaderDiv), formTitle), getElementText(ProjectFormsTab.ele_CreateFormHeaderDiv).contains(formTitle));
				waitUntilElementIsClickable(ProjectFormsTab.btn_CreateControllerFormAttachment);
				mouseHover(ProjectFormsTab.btn_CreateControllerFormAttachment);
				clickElementAndWaitForElement(ProjectFormsTab.btn_CreateControllerFormAttachment, ProjectFormsTab.pop_CreateControllerFormAttachments);
			}
			else {*/
				AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.ele_BetaViewCreateFormHeaderDiv), formTitle), getElementText(ProjectFormsTab.ele_BetaViewCreateFormHeaderDiv).contains(formTitle));
				waitUntilElementIsClickable(ProjectFormsTab.btn_BetaViewCreateControllerFormAttachment);
				mouseHover(ProjectFormsTab.btn_BetaViewCreateControllerFormAttachment);
				clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewCreateControllerFormAttachment, ProjectFormsTab.pop_BetaViewCreateControllerFormAttachments);
			//}

			findElement(ProjectFormsTab.btn_CreateFormAttachmentSelectFiles).sendKeys(ResourceHandler.loadProperty("single.file.path"));

			/*if (!appBetaViewFlag) {
				waitUntilElementIsDisplayed(ProjectFormsTab.lbl_PopAttachFileControllerFormAttachedFile);
				clickElementAndWait(ProjectFormsTab.btn_CreateControllerFormAttachFileSave);
			}
			else*/
				clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewCreateRFIFormAttachmentsAttach, ProjectFormsTab.btn_BetaViewCreateRFIFormSave);
		}
		else {
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectFormsTab.drp_CreateFormControllerDropdown, false), !isDisplayed(ProjectFormsTab.drp_CreateFormControllerDropdown));
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(ProjectFormsTab.btn_CreateControllerFormAttachment, false), !isDisplayed(ProjectFormsTab.btn_CreateControllerFormAttachment));
		}

	}

	public void selectValidUserFromControllerDropdown() {
		selectByIndex(ProjectFormsTab.drp_CreateFormControllerDropdown, 1);
	}

	public void enterMandatoryValuesToCreateForm() throws InterruptedException {
		controllerFormTitle = "TestControllerFormTitle" + dateUtils.getEpoch();
		collectionDataMap.put("controller form", controllerFormTitle);
		sendKeys(ProjectFormsTab.txt_CreateControllerFormTitle, controllerFormTitle);
		selectByIndex(ProjectFormsTab.drp_CreateControllerFormTo, 1);
		clickElementAndWaitForElement(ProjectFormsTab.img_CreateControllerFormOnDateIcon, ProjectFormsTab.lnk_CreateControllerFormOnDateCalenderToday);
		clickElementAndWait(ProjectFormsTab.lnk_CreateControllerFormOnDateCalenderToday);
		sendKeys(ProjectFormsTab.txt_CreateControllerFormOnDate, Keys.TAB);
		waitForCompletePageLoad();
		if (isDisplayed(ProjectFormsTab.txt_CreateControllerRespondByDate)) {
			clickElementAndWaitForElement(ProjectFormsTab.img_CreateControllerRespondByDate, GlobalPageElements.lnk_DatePickerCalenderToday);
			clickElementAndWait(GlobalPageElements.lnk_DatePickerCalenderToday);
			sendKeys(ProjectFormsTab.txt_CreateControllerFormOnDate, Keys.TAB);
			waitForCompletePageLoad();
		}
	}

	public void attachExternalFileToControllerForm() {
		log.info("file attachment code is covered in verifyControllerOptionAvailability() function call");
		if (isDisplayed(ProjectFormsTab.img_CreateControllerFormAngularDatePicker)) {
			clickElementAndWaitForElement(ProjectFormsTab.img_CreateControllerFormAngularDatePicker, ProjectFormsTab.img_CreateControllerFormAngularActiveDate);
			clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.img_CreateControllerFormAngularActiveDate, ProjectFormsTab.img_CreateControllerFormAngularActiveDate);
		}
		/*if (!appBetaViewFlag)
			clickElementAndWait(ProjectFormsTab.btn_CreateControllerFormSendButton);
		else*/
			clickElementAndWait(ProjectFormsTab.btn_BetaViewCreateControllerFormSendButton);
	}

	public void verifyControllerFormIsCreated(String controlFlag) {
		switchDefault();
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		searchForms(controllerFormTitle);
		AutomationAssert.verifyTrue(eStringUtils.getElementCountError(ProjectFormsTab.lnk_FormListingFirstFormAction, 1, 0), getCount(ProjectFormsTab.lnk_FormListingFirstFormAction) > 0);
		if (controlFlag.equalsIgnoreCase("yes")) {
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.ele_FormListingFirstFormActionDiv), AdoddleCommonStringPool.ACTION_DISTRIBUTE), getElementText(ProjectFormsTab.ele_FormListingFirstFormActionDiv).contains(AdoddleCommonStringPool.ACTION_DISTRIBUTE));
			navigateTab(LandingPage.lnk_Files);
			waitForCompletePageLoad();
			clickElementWithText(projectName);
			searchFiles(strUtils.extractFileNameString(nodeIP + ResourceHandler.loadProperty("single.file.path")));
			AutomationAssert.verifyTrue(eStringUtils.getElementCountError(FilesTab.css_DocListingFilesCount, 1, 0), getCount(FilesTab.css_DocListingFilesCount) > 0);
		}
	}

	public void searchCreatedFormAndClick() {
		parentHandle = getCurrentWindow();
		searchForms(controllerFormTitle);
		executeJScript(AdoddleCommonJQueries.expandFirstFormTitleJQuery); 
		clickElementAndWait(ProjectFormsTab.lnk_FormListingFirstFormTitle);
		waitForSwitchWindow(2);
		switchWindow();
	}

	public void verifyFormIsViewed() {
		/*if (!appBetaViewFlag) {
			waitUntilElementIsDisplayed(ProjectFormsTab.ele_ControllerFormViewXDocView);
			waitForCompletePageLoad();
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.ele_ControllerFormViewXDocView), formTitle), getElementText(ProjectFormsTab.ele_ControllerFormViewXDocView).contains(formTitle));
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.ele_ControllerFormViewXDocView), controllerFormTitle), getElementText(ProjectFormsTab.ele_ControllerFormViewXDocView).contains(controllerFormTitle));
			clickElementAndWaitForElement(ProjectFormsTab.btn_ControllerFormViewActionButton, ProjectFormsTab.btn_ControllerFormViewActionsEditORI);
		}
		else {*/
			waitForCompletePageLoad();
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.ele_BetaViewControllerFormViewXDocView), formTitle), getElementText(ProjectFormsTab.ele_BetaViewControllerFormViewXDocView).contains(formTitle));
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.ele_BetaViewControllerFormViewXDocView), controllerFormTitle), getElementText(ProjectFormsTab.ele_BetaViewControllerFormViewXDocView).contains(controllerFormTitle));
			clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewControllerFormViewActionButton, ProjectFormsTab.lnk_BetaViewFormViewDetailsActionEditDraft);
		//}

	}

	public void editORIForm() {
		controllerFormTitle = "TestControllerFormTitle_Edited" + dateUtils.getEpoch();
		/*if (!appBetaViewFlag) {
			clickElementAndWait(ProjectFormsTab.btn_ControllerFormViewActionsEditORI);
			waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		}
		else*/
			clickElementAndWaitForElement(ProjectFormsTab.lnk_BetaViewFormViewDetailsActionEditDraft, ProjectFormsTab.txt_CreateControllerFormTitle);

		sendKeys(ProjectFormsTab.txt_CreateControllerFormTitle, controllerFormTitle);

		if (isDisplayed(ProjectFormsTab.btn_CreateControllerFormSendButton))
			clickElement(ProjectFormsTab.btn_CreateControllerFormSendButton);

		switchDefault();
		waitForCompletePageLoad();

		/*if (!appBetaViewFlag) {
			waitUntilElementIsDisplayed(ProjectFormsTab.lbl_ViewFormHeaderFormTitle);
			waitUntilElementContainsText(ProjectFormsTab.lbl_ViewFormHeaderFormTitle, controllerFormTitle);
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.lbl_ViewFormHeaderFormTitle), controllerFormTitle), getElementText(ProjectFormsTab.lbl_ViewFormHeaderFormTitle).contains(controllerFormTitle));
		}
		else {*/
			waitUntilElementIsDisplayed(ProjectFormsTab.lbl_BetaViewFormDetailsHeader);
			waitUntilElementContainsText(ProjectFormsTab.lbl_BetaViewFormDetailsHeader, formTitle);
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.txt_CreateControllerFormTitle), controllerFormTitle), getValue(ProjectFormsTab.txt_CreateControllerFormTitle).contains(controllerFormTitle));
		//}
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
		reloadPage();
	}

	public void closeTestProject() {
		deactivateProject(projectName);
	}
}
