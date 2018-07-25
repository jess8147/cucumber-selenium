package org.asite.automation.adoddle.p2.scripts;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleClassicLocators.ClassicLocators;
import org.asite.automation.CommonLocators.AdoddleDiscussionsLocators.DiscussionsTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleModelsLocators.ModelsTab;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.CommonLocators.ClassicDocListingLocators.DocListingPage;
import org.asite.automation.CommonLocators.ClassicLandingLocators.LandingPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.DateUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class ClonedInheritanceWorkspaceObjects extends AdoddleCommonAppMethods {

	private String documentStatus, documentPoi, updatedDocumentPoi;
	private static String NewAppTemplate, AutoTest_App, AutoTest_UpatedApp;
	private static String workspaceTemplate, clonedWorkspace, inheritedWorkspace;
	private String appGroupName, Inherited_FormSubject, Cloned_FormSubject;
	private static String AutoTest_Folder, AutoTest_UpdatedFolder, AutoTest_UserRole, Autotest_UpdatedUserRole;
	final private static List<String> docStatusList = new ArrayList<String>();
	final private static List<String> poiList = new ArrayList<String>();
	private List<String> fileList = new ArrayList<String>();
	private List<WebElement> existingUserRolesList = new ArrayList<WebElement>();
	final private static Logger log = AutomationLogger.getInstance().getLogger(ClonedInheritanceWorkspaceObjects.class);

	public void updateWorkspaceTemplate(String templateIdentifier) {
		workspaceTemplate = templateIdentifier + epoch;
		sendKeys(ClassicLocators.txt_CreateEditWorkspaceNameInput, workspaceTemplate);
		collectionDataMap.put("workspace template :", workspaceTemplate);
	}

	public void verifyWorkspaceInListing(String projectType) throws InterruptedException {
		reloadPage();
		waitForPageLoad();
		if (projectType.equalsIgnoreCase("Cloned_AutomationTestProject"))
			validateWorkspaceInAllWorkspaceList(clonedWorkspace);
		else
			validateWorkspaceInAllWorkspaceList(inheritedWorkspace);
	}

	public void validateTemplateInTemplateList() {
		boolean flag = false;
		int maxWaitTimeout = 4;
		for (int index = 0; index < 5; index++) {
			switchDefault();
			waitUntilElementIsDisplayed(ClassicLocators.frm_AsiteWorkingFrame);
			switchIframe(ClassicLocators.frm_AsiteWorkingFrame);
			waitUntilElementIsDisplayed(ClassicLocators.frm_AsiteMainFrame);
			switchIframe(ClassicLocators.frm_AsiteMainFrame);
			waitForCompletePageLoad();
			clickElementAndWaitForElement(ClassicLocators.lbl_WorkspaceTemplatesTab,
					ClassicLocators.existingTemplateList);
			if (isDisplayedLinkWithText(workspaceTemplate)) {
				flag = true;
				break;
			} else {
				reloadPage();
				if (isDisplayed(GlobalPageElements.ele_PleaseWaitLoadingDataMessage) && index != maxWaitTimeout) {
					waitUntilElementIsInvisible(GlobalPageElements.ele_PleaseWaitLoadingDataMessage);
					index++;
				}
			}

		}
		AutomationAssert.verifyTrue("Template not found in template list", flag);

	}

	public void enterWorkspaceMandatoryAttributes(String ProjectIdentifier, String scenarioType) {

		boolean dataCenterType = scenarioType.contains("Like_DataCenter");

		if (ProjectIdentifier.split("_")[0].equalsIgnoreCase("Cloned")) {
			clonedWorkspace = ProjectIdentifier + epoch;
			log.info("Cloned Project::" + clonedWorkspace);
			collectionDataMap.put("ClonedProject:: ", clonedWorkspace);
			sendKeys(ClassicLocators.txt_CreateEditWorkspaceNameInput, clonedWorkspace);
			if (!isSelected(ClassicLocators.drp_CreateEditWorkspaceClient))
				selectByVisibleText(ClassicLocators.drp_CreateEditWorkspaceClient,
						resourceUtils.getAutomationClassicOrg());
			clickElementAndWaitForElement(ClassicLocators.lnk_CreateEditWorkspaceShowAdvanced,
					ClassicLocators.chk_CreateWorkspaceInheritChanges);
			if (isSelected(ClassicLocators.chk_CreateWorkspaceInheritChanges))
				clickElement(ClassicLocators.chk_CreateWorkspaceInheritChanges);

		} else {

			inheritedWorkspace = ProjectIdentifier + epoch;
			log.info("Inherited Project::" + inheritedWorkspace);
			collectionDataMap.put("InheritedProject:: ", inheritedWorkspace);
			sendKeys(ClassicLocators.txt_CreateEditWorkspaceNameInput, inheritedWorkspace);
			if (!isSelected(ClassicLocators.drp_CreateEditWorkspaceClient))
				selectByVisibleText(ClassicLocators.drp_CreateEditWorkspaceClient,
						resourceUtils.getAutomationClassicOrg());
		}

		if (dataCenterType)
			resetGeographyAsTemplate(ProjectIdentifier);
		else
			resetGeographyUnlikeTemplate(ProjectIdentifier);

	}

	private void resetGeographyAsTemplate(String projectIdentifier) {

		if (!isSelected(ClassicLocators.drp_CreateEditWorkspaceGeography)
				&& projectIdentifier.split("_")[2].equalsIgnoreCase(AdoddleCommonStringPool.UK_DC))
			selectByVisibleText(ClassicLocators.drp_CreateEditWorkspaceGeography, AdoddleCommonStringPool.UK_GEOGRAPHY);
		else if ((!isSelected(ClassicLocators.drp_CreateEditWorkspaceGeography) && projectIdentifier.split("_")[2]
				.equalsIgnoreCase(AdoddleCommonStringPool.US_DC)))
			selectByVisibleText(ClassicLocators.drp_CreateEditWorkspaceGeography, AdoddleCommonStringPool.US_GEOGRAPHY);
		else
			selectByVisibleText(ClassicLocators.drp_CreateEditWorkspaceGeography, AdoddleCommonStringPool.AUS_GEOGRAPHY);

	}

	private void resetGeographyUnlikeTemplate(String projectIdentifier) {
		if (!isSelected(ClassicLocators.drp_CreateEditWorkspaceGeography)
				&& projectIdentifier.split("_")[2].equalsIgnoreCase(AdoddleCommonStringPool.US_DC))
			selectByVisibleText(ClassicLocators.drp_CreateEditWorkspaceGeography, AdoddleCommonStringPool.US_GEOGRAPHY);
		else if ((!isSelected(ClassicLocators.drp_CreateEditWorkspaceGeography) && projectIdentifier.split("_")[2]
				.equalsIgnoreCase(AdoddleCommonStringPool.UK_DC)))
			selectByVisibleText(ClassicLocators.drp_CreateEditWorkspaceGeography, AdoddleCommonStringPool.UK_GEOGRAPHY);
		else
			selectByVisibleText(ClassicLocators.drp_CreateEditWorkspaceGeography, AdoddleCommonStringPool.UK_GEOGRAPHY);
	}

	private void validateWorkspaceInAllWorkspaceList(String workspaceName) throws InterruptedException {

		boolean flag = false;

		for (int index = 0; index < 5; index++) {

			switchDefault();
			waitUntilElementIsDisplayed(ClassicLocators.frm_AsiteWorkingFrame);
			switchIframe(ClassicLocators.frm_AsiteWorkingFrame);
			waitUntilElementIsDisplayed(ClassicLocators.frm_AsiteMainFrame);
			switchIframe(ClassicLocators.frm_AsiteMainFrame);
			waitForCompletePageLoad();
			clickElementAndWait(ClassicLocators.lbl_ALlWorkspacesTab);
			waitUtils.waitInterval(1);
			if (isDisplayedLinkWithText(workspaceName)) {
				flag = true;
				break;
			} else {
				reloadPage();
				if (isDisplayed(GlobalPageElements.ele_PleaseWaitLoadingDataMessage)) {
					waitUntilElementIsInvisible(GlobalPageElements.ele_PleaseWaitLoadingDataMessage);
					waitForCompletePageLoad();
					index++;
				}
			}

		}
		AutomationAssert.verifyTrue("Workspace not found in list as :" + workspaceName, flag);

	}

	public void focusWorkflowTemplates() {
		clickElementAndWaitForElement(ClassicLocators.lbl_WorkspaceTemplatesTab, ClassicLocators.existingTemplateList);
		validateWorkflowInstanceAndClickCloneIcon();
		waitForCompletePageLoad();
	}

	public void naviagteAndClickTemplate() {
		clickLinkWithText(workspaceTemplate);
	}

	public void verifyWorkspaceTemplateHomePage() {
		switchDefault();
		waitUntilElementIsDisplayed(ClassicLocators.frm_AsiteWorkingFrame);
		switchIframe(ClassicLocators.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(ClassicLocators.frm_AsiteHeaderFrame);
		waitUntilElementIsDisplayed(ClassicLocators.lbl_WorkspaceHeaderName);
		AutomationAssert.verifyTrue(getElementText(ClassicLocators.lbl_WorkspaceHeaderName).equalsIgnoreCase(
				workspaceTemplate));

	}

	public void validateWorkflowInstances() {
		reloadPage();
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(ClassicLocators.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(ClassicLocators.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(ClassicLocators.frm_AsiteHeaderFrame);
		clickElementAndWait(ClassicLocators.lnk_WorkspaceMyHome);
		waitForCompletePageLoad();
		clickElementAndWait(ClassicLocators.tab_WorkSpace);
		waitForCompletePageLoad();
		switchDefault();
		waitAndSwitchIframe(ClassicLocators.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(ClassicLocators.frm_AsiteMainFrame);
		waitUntilElementIsDisplayed(ClassicLocators.lbl_WorkspaceTemplatesTab);
		clickElementAndWaitForElement(ClassicLocators.lbl_WorkspaceTemplatesTab, ClassicLocators.existingTemplateList);
		validateWorkflowInstanceAndClickCloneIcon();
	}

	private void validateWorkflowInstanceAndClickCloneIcon() {
		Boolean flag = false;
		List<WebElement> templateList;
		templateList = findElements(ClassicLocators.existingTemplateList);
		for (WebElement web : templateList) {
			if (web.getText().contains(workspaceTemplate)) {
				flag = true;
				web.findElement(ClassicLocators.templateClonedLink).click();
				break;
			}
		}
		AutomationAssert.verifyTrue("Template not found in Listing as :" + workspaceTemplate, flag);
	}

	public void selectOptionfromAdminDropdown(String dropDownOption) {
		switchDefault();
		waitAndSwitchIframe(ClassicLocators.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(ClassicLocators.frm_AsiteHeaderFrame);
		selectByVisibleText(ClassicLocators.drp_AdminDropdown, dropDownOption);
		waitForCompletePageLoad();
		switchMultiFrames();
	}

	public void clickCreateStatusIcon(String imgType) {

		if (imgType.contains("Add Document Status"))
			clickElementAndWait(ClassicLocators.img_ManageStatusAddDocumentStatusIcon);
		else
			clickElementAndWait(ClassicLocators.img_ManagePoiAddPoiIcon);

	}

	public void enterDocStatusAndPoiMandatoryAttributes(String workspaceObjectIndentifier) {

		if (workspaceObjectIndentifier.split("_")[0].contains("Status")) {

			documentStatus = workspaceObjectIndentifier + dateUtils.getEpoch();
			collectionDataMap.put("DocumentStatus:: ", documentStatus);
			sendKeys(ClassicLocators.txt_ManageStatusDocumentStatus, documentStatus);
			sendKeys(ClassicLocators.txt_ManageStatusAbbreviationStatus, javaUtils.getRandomString(5));
			clickElementAndWait(ClassicLocators.img_ManageStatusAdminCheckMark);
			docStatusList.clear();
			docStatusList.add(documentStatus);

		}

		else {
			documentPoi = workspaceObjectIndentifier + dateUtils.getEpoch();
			collectionDataMap.put("DocumentPOI:: ", documentPoi);
			sendKeys(ClassicLocators.txt_ManageStatusPoi, documentPoi);
			sendKeys(ClassicLocators.txt_ManagePoiAbbreviationPoi, javaUtils.getRandomString(5));
			clickElementAndWait(ClassicLocators.img_ManagePoiAdminCheckMark);

		}

	}

	public void clickButtonOnPopup(String popUpText) {
		if (popUpText.contains("doc status"))
			clickElementAndWait(ClassicLocators.btn_ManageStatusSubmit);
		else
			clickElementAndWait(ClassicLocators.btn_ManagePoiSubmit);
	}

	public void waitUntilStatusPoiPageLoad() {
		switchMultiFrames();
		waitUntilElementIsDisplayed(ClassicLocators.img_ManageStatusPoiIcon);
		waitForCompletePageLoad();
	}

	public void validateStatusAndPoiInListing(String workspaceObjectIndentifier) {

		if (workspaceObjectIndentifier.contains("Status_WFAccess_Admin"))
			AutomationAssert.verifyTrue("Document Status:: " + documentStatus + " not found in lisiting.",
					isDisplayedLinkWithText(documentStatus));
		else
			AutomationAssert.verifyTrue("Document POI:: " + documentPoi + " not found in lisiting.",
					isDisplayedLinkWithText(documentPoi));
	}

	public void updateExistingPoiInTemplate(String existingPoi, String updatedPoi) {

		updatedDocumentPoi = updatedPoi + dateUtils.getEpoch();
		collectionDataMap.put("UpdatedDocumentPOI:: ", updatedDocumentPoi);
		AutomationAssert.verifyTrue("Existing POI:: " + existingPoi + " not found in listing.",
				isDisplayedLinkWithText(existingPoi));
		clickLinkWithText(existingPoi);
		poiList.clear();
		poiList.add(updatedDocumentPoi);
		waitUntilElementIsDisplayed(GlobalPageElements.lbl_PageTitle);
		AutomationAssert.verifyTrue(getElementText(GlobalPageElements.lbl_PageTitle).contains("purpose of issue"));
		sendKeys(ClassicLocators.txt_ManageStatusPoi, updatedDocumentPoi);
		sendKeys(ClassicLocators.txt_ManagePoiAbbreviationPoi, javaUtils.getRandomString(5));
		clickElementAndWait(ClassicLocators.btn_ManagePoiSubmit);
	}

	public void switchIntoAdoddleView() {

		reloadPage();
		waitForCompletePageLoad();
		waitAndSwitchIframe(ClassicLocators.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(ClassicLocators.frm_AsiteHeaderFrame);
		mouseHoverAndClickElement(ClassicLocators.lnk_WorkspaceSettings, ClassicLocators.lnk_AdoddleView);
		waitForCompletePageLoad();
		reloadPage();
		waitForCompletePageLoad();

	}

	public void searchWorkspaceAndContextClick(String workspaceType) {

		if (workspaceType.contains("InheritedCloned_AutoTestProject"))
			searchProjects(inheritedWorkspace);
		else
			searchProjects(clonedWorkspace);

	}

	public void validateDocStatusAndPoiInClonedAndInheritedWorkspaces(String workspaceObject, String workspace) {

		Boolean flag = false;

		waitUntilListOfElementIsDisplayed(ProjectsTab.txt_WorkspaceDocStatusAndPoiList);
		List<WebElement> workspaceDocStatusAndPoiList = findElements(ProjectsTab.txt_WorkspaceDocStatusAndPoiList);
		log.info("WorkspaceDocStatusAndPoiList List Size:: " + docStatusList.size());

		if (workspace.contains("InheritedCloned_AutoTestProject")) {

			for (WebElement web : workspaceDocStatusAndPoiList) {

				if (workspaceObject.split("_")[0].contains("Status")) {

					log.info("iterating document status :" + web.getAttribute("value"));

					if (web.getAttribute("value").contains(documentStatus)) {
						AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(web.getAttribute("value"),
								documentStatus), web.getAttribute("value").contains(documentStatus));
						AutomationAssert.verifyTrue("document status was not disabled", !web.isEnabled());
						flag = true;
						break;
					} else
						log.info("Not Expected Document Status");
				} else {

					log.info("iterating document poi :" + web.getAttribute("value"));

					if (web.getAttribute("value").contains(documentPoi))
						AutomationAssert.verifyTrue("Failure in validation",
								web.getAttribute("value").contains(documentPoi));

					else if (web.getAttribute("value").contains(updatedDocumentPoi)) {
						AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(web.getAttribute("value"),
								updatedDocumentPoi), web.getAttribute("value").contains(updatedDocumentPoi));
						AutomationAssert.verifyTrue("document poi was not disabled", !web.isEnabled());
						flag = true;
						break;

					}

					else
						log.info("Not Expected Purpose of Issue");

				}

			}

			AutomationAssert.verifyTrue("Failure while validating DocStatus And Poi.", flag);

		}

		else {

			for (WebElement web : workspaceDocStatusAndPoiList) {

				if (workspaceObject.split("_")[0].contains("Status"))
					AutomationAssert.verifyTrue(web.getAttribute("value") + " should not contain " + documentStatus,
							!web.getAttribute("value").contains(documentStatus));

				else
					AutomationAssert.verifyTrue(web.getAttribute("value") + " should not contain " + documentPoi, !web
							.getAttribute("value").contains(documentPoi));

			}

		}

		clickElementAndWaitForInvisibilityOfElement(ProjectsTab.btn_POIAndStatusCancelButton,
				GlobalPageElements.ele_overLayProcess);
		waitForCompletePageLoad();
		workspaceDocStatusAndPoiList.clear();

	}

	public void focusWorkspaceAndCreateDirectory(String directoryName) throws InterruptedException {

		String folderName;
		contextClickWithText(inheritedWorkspace);
		mouseHoverAndClickElement(FilesTab.opt_ProjectContextClickNew, FilesTab.opt_ProjectContextClickFolder);
		waitForCompletePageLoad();
		folderName = directoryName + dateUtils.getEpoch();
		waitUntilElementIsDisplayed(FilesTab.txt_PopCreateFolderFolderName);
		sendKeys(FilesTab.txt_PopCreateFolderFolderName, folderName);
		clickElementAndWait(FilesTab.btn_PopCreateFolderCreate);
		waitForElementWithText(folderName);
		AutomationAssert.verifyTrue(isDisplayedElementWithText(folderName));
		clickElementWithText(folderName);

	}

	public void publishMultipleDocuments() throws Exception {

		fileList = uploadDocuments(null, 1, null, null, false, 1, poiList, docStatusList, null, null);
		searchFiles(strUtils.extractFileNameString(fileList.get(0)));
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(
				getElementText(FilesTab.ele_FilesTabFirstFilePOI), updatedDocumentPoi),
				getElementText(FilesTab.ele_FilesTabFirstFilePOI).contains(updatedDocumentPoi));
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(
				getElementText(FilesTab.lnk_FilesTabFirstFileStatus), documentStatus),
				getElementText(FilesTab.lnk_FilesTabFirstFileStatus).contains(documentStatus));

	}

	public void clickOnWorkspaceDocuments(String wsDocs) {

		switchDefault();
		switchToMultipleFrames();
		clickLinkWithText(wsDocs);
		waitUntilElementIsInvisible(GlobalPageElements.ele_PleaseWaitLoadingDataMessage);
		waitForCompletePageLoad();

	}

	public void createUserRoleInTemplate(String role) {

		AutoTest_UserRole = role + epoch;
		switchDefault();
		switchToMultipleFrames();

		clickElementAndWait(ClassicLocators.txt_ManageUserRoleAddNewRole);
		sendKeys(ClassicLocators.txt_ManageUserRoleWorkspaceRoleName, AutoTest_UserRole);
		sendKeys(ClassicLocators.txt_ManageUserRoleSearchEmailField,
				ResourceHandler.loadProperty("test.user.rfi.builder.id"));
		clickElementAndWaitForElement(ClassicLocators.btn_ManageUserRoleSearch,
				ClassicLocators.css_ManageUserRoleSearchEmailResult);
		waitUntilElementContainsText(ClassicLocators.css_ManageUserRoleSearchEmailResult,
				ResourceHandler.loadProperty("test.user.rfi.builder.name"));
		AutomationAssert.verifyTrue(
				"Expected Search Result: " + ResourceHandler.loadProperty("test.user.rfi.builder.name")
						+ " was not found.", getElementText(ClassicLocators.css_ManageUserRoleSearchEmailResult)
						.contains(ResourceHandler.loadProperty("test.user.rfi.builder.name")));
		clickElementAndWaitForElement(ClassicLocators.chk_UserRoleSearchEmailResultCheckbox,
				ClassicLocators.btn_ManageUserRoleAddSelected);
		clickElementAndWaitForElement(ClassicLocators.btn_ManageUserRoleAddSelected,
				ClassicLocators.txt_ManageUserRoleSearchEmailField);
		sendKeys(ClassicLocators.txt_ManageUserRoleSearchEmailField,
				ResourceHandler.loadProperty("test.user.pa.builder.id"));
		clickElementAndWaitForElement(ClassicLocators.btn_ManageUserRoleSearch,
				ClassicLocators.css_ManageUserRoleSearchEmailResult);
		waitUntilElementContainsText(ClassicLocators.css_ManageUserRoleSearchEmailResult,
				ResourceHandler.loadProperty("test.user.pa.builder.name"));
		AutomationAssert.verifyTrue(
				"Expected Search Result: " + ResourceHandler.loadProperty("test.user.pa.builder.name")
						+ " was not found.", getElementText(ClassicLocators.css_ManageUserRoleSearchEmailResult)
						.contains(ResourceHandler.loadProperty("test.user.pa.builder.name")));

		clickElementAndWaitForElement(ClassicLocators.chk_UserRoleSearchEmailResultCheckbox,
				ClassicLocators.btn_ManageUserRoleAddSelected);
		clickElementAndWait(ClassicLocators.btn_ManageUserRoleAddSelected);

	}

	public void updateUserRoleInTemplate(String projectRole, String newRole) throws InterruptedException {

		existingUserRolesList = findElements(ClassicLocators.css_ManageUserRoleExistingUserRoleList);
		Autotest_UpdatedUserRole = newRole + epoch;

		log.info("UserRole List Size:: " + existingUserRolesList.size());

		for (WebElement web : existingUserRolesList) {

			String exisitingRole = web.findElement(ClassicLocators.txt_ExistingUserRoleListRoleName).getAttribute(
					"value");

			if (exisitingRole.equalsIgnoreCase(projectRole)) {

				web.findElement(ClassicLocators.txt_ExistingUserRoleListRoleName).clear();
				web.findElement(ClassicLocators.txt_ExistingUserRoleListRoleName).sendKeys(Autotest_UpdatedUserRole);
				sendKeys(ClassicLocators.txt_ManageUserRoleSearchEmailField,
						ResourceHandler.loadProperty("test.user.pa.bloggs.id"));
				clickElementAndWaitForElement(ClassicLocators.btn_ManageUserRoleSearch,
						ClassicLocators.css_ManageUserRoleSearchEmailResult);
				waitUntilElementContainsText(ClassicLocators.css_ManageUserRoleSearchEmailResult,
						ResourceHandler.loadProperty("test.user.pa.bloggs.name"));
				AutomationAssert.verifyTrue(
						"Expected Search Result: " + ResourceHandler.loadProperty("test.user.pa.bloggs.name")
								+ " was not found.",
						getElementText(ClassicLocators.css_ManageUserRoleSearchEmailResult).contains(
								ResourceHandler.loadProperty("test.user.pa.bloggs.name")));
				clickElementAndWait(ClassicLocators.chk_UserRoleSearchEmailResultCheckbox);
				web.findElement(ClassicLocators.btn_ExistingUserRoleListAddSelected).click();
				break;

			}

			else
				log.info("Expected Role not found");

		}

		clickElementAndWait(ClassicLocators.btn_ManageUserRoleSaveButton);
		if (isDisplayed(GlobalPageElements.ele_PleaseWaitLoadingDataMessage)) {
			waitUntilElementIsInvisible(GlobalPageElements.ele_PleaseWaitLoadingDataMessage);
			waitForCompletePageLoad();
		}
		existingUserRolesList.clear();
		validateCreatedAndUpdatedUserRolesInTemplate(AutoTest_UserRole, Autotest_UpdatedUserRole);

	}

	private void validateCreatedAndUpdatedUserRolesInTemplate(String newRole, String updatedRole) {

		boolean flag = false;
		switchDefault();
		switchToMultipleFrames();
		waitUntilListOfElementIsDisplayed(ClassicLocators.css_ManageUserRoleExistingUserRoleList);
		existingUserRolesList = findElements(ClassicLocators.css_ManageUserRoleExistingUserRoleList);

		for (WebElement web : existingUserRolesList) {

			String userRole = web.findElement(ClassicLocators.txt_ExistingUserRoleListRoleName).getAttribute("value");

			if (userRole.equalsIgnoreCase(newRole)) {
				flag = true;
				AutomationAssert.verifyTrue("failure while creating user role" + AutoTest_UserRole,
						userRole.contains(AutoTest_UserRole));
			}

			else if (userRole.equalsIgnoreCase(updatedRole)) {
				flag = true;
				AutomationAssert.verifyTrue("failure while creating user role" + Autotest_UpdatedUserRole,
						userRole.contains(Autotest_UpdatedUserRole));
			}

			else
				log.info("expected user role not found");

		}

		AutomationAssert.verifyTrue("Failure While creating User Roles" + AutoTest_UserRole + " "
				+ Autotest_UpdatedUserRole, flag);

	}

	public void clickOnWorkspaceHome() {
		switchDefault();
		waitAndSwitchIframe(ClassicLocators.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(ClassicLocators.frm_AsiteHeaderFrame);
		clickElementAndWait(ClassicLocators.lnk_WorkspaceHome);
		waitForPageLoad();

	}

	public void verifyDocumentListing() {
		switchToMultipleFrames();
		waitAndSwitchIframe(LandingPage.frm_AsiteNoResize);
		AutomationAssert.verifyTrue(isDisplayedFolderWithTitle(AdoddleCommonStringPool.ALL_WS_DOCS));
	}

	public void clickOnCreateNewParentFolder() {
		switchToMultipleFrames();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		clickElementAndWait(DocListingPage.lnk_CreateParentFolder);
	}

	public void verifyFolderPage(String pageTitle) {
		Assert.assertEquals(getElementText(DocListingPage.lbl_CreateFolderPageTitle).trim(), pageTitle.trim());
	}

	public void enterFolderNameAndCreateFolder(String folderIndentifier) throws InterruptedException {

		AutoTest_Folder = folderIndentifier + epoch;
		sendKeys(DocListingPage.txt_CreateFolderInput, AutoTest_Folder);
		selectByVisibleText(DocListingPage.drp_CreateFolderRoleSelect, AutoTest_UserRole);
		clickElementAndWaitForElement(DocListingPage.btn_CreateFolderRolesSubmit,
				DocListingPage.drp_CreateFolderRolesValueDropdown);
		selectByVisibleText(DocListingPage.drp_CreateFolderRolesValueDropdown, AdoddleCommonStringPool.PERMISSION_ADMIN);
		selectByVisibleText(ClassicLocators.sel_CreateParentFolderOrg,
				ResourceHandler.loadProperty("tc.test.company.private.Ltd.org"));
		selectByVisibleText(ClassicLocators.sel_CreateParentFolderAvailableUsers,
				ResourceHandler.loadProperty("test.user.tc.bloggs.name"));
		clickElementAndWait(ClassicLocators.btn_CreateParentFolderAddUserSelect);
		assignUserAndPermissionInDirectories();
		clickElementAndWait(DocListingPage.btn_CreateFolder);

	}

	private void assignUserAndPermissionInDirectories() {

		boolean flag = false;
		List<WebElement> userRoleList = findElements(ClassicLocators.css_CreateParentFolderUsersList);
		log.info("UserList Size:: " + userRoleList.size());

		for (WebElement ele : userRoleList) {

			String userName = ele.findElement(ClassicLocators.css_CreateParentFolderListOrgUser).getText();
			WebElement userPermission = ele.findElement(ClassicLocators.sel_CreateParentFolderListOrgUserPermission);

			if (userName.contains(ResourceHandler.loadProperty("test.user.tc.bloggs.name"))) {
				AutomationAssert.verifyTrue(
						eStringUtils.getContainsStringError(userName,
								ResourceHandler.loadProperty("test.user.tc.bloggs.name")),
						userName.contains(ResourceHandler.loadProperty("test.user.tc.bloggs.name")));
				selectByVisibleText(userPermission, AdoddleCommonStringPool.PERMISSION_UPLOAD);
				flag = true;
			}

			else if (userName.contains(ResourceHandler.loadProperty("test.user.pa.bloggs.name"))) {
				AutomationAssert.verifyTrue(
						eStringUtils.getContainsStringError(userName,
								ResourceHandler.loadProperty("test.user.pa.bloggs.name")),
						userName.contains(ResourceHandler.loadProperty("test.user.pa.bloggs.name")));
				selectByVisibleText(userPermission, AdoddleCommonStringPool.PERMISSION_PUBLISH_AND_LINK);
				flag = true;
			}

			else
				log.info("Permission already Assigned to User");

		}

		AutomationAssert.verifyTrue("failure while assigning user permission", flag);
		userRoleList.clear();

	}

	public void verifyCreatedFolderIntoFolderTree() {

		switchToMultipleFrames();
		waitAndSwitchIframe(LandingPage.frm_AsiteNoResize);
		clickFolderWithTitle(AutoTest_Folder);
		AutomationAssert.verifyTrue(isDisplayedFolderWithTitle(AutoTest_Folder));

	}

	public void updateExistingDirectoryInLisitng(String existingFolder, String updatedFolder) {

		AutoTest_UpdatedFolder = updatedFolder + epoch;

		while (!isDisplayed(DocListingPage.txt_CreateEditFolderInput)) {

			findElement(By.xpath("//a[contains(text(),'" + existingFolder + "')]")).click();
			switchToMultipleFrames();
			waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
			waitUntilElementIsDisplayed(DocListingPage.img_DocListingFolderMenu);
			waitForCompletePageLoad();
			mouseHoverAndClickElement(DocListingPage.img_DocListingFolderMenu, DocListingPage.img_DocListingEditFolder);

		}

		sendKeys(DocListingPage.txt_CreateEditFolderInput, AutoTest_UpdatedFolder);
		selectByVisibleText(DocListingPage.drp_CreateFolderRoleSelect, Autotest_UpdatedUserRole);
		clickElementAndWaitForElement(DocListingPage.btn_CreateFolderRolesSubmit,
				DocListingPage.drp_CreateFolderRolesValueDropdown);
		selectByVisibleText(DocListingPage.drp_CreateFolderRolesValueDropdown, AdoddleCommonStringPool.PERMISSION_ADMIN);
		selectByVisibleText(ClassicLocators.sel_CreateParentFolderOrg,
				ResourceHandler.loadProperty("tc.test.company.private.Ltd.org"));
		selectByVisibleText(ClassicLocators.sel_CreateParentFolderAvailableUsers,
				ResourceHandler.loadProperty("test.user.pa.bloggs.name"));
		clickElementAndWaitForElement(ClassicLocators.btn_CreateParentFolderAddUserSelect,
				ClassicLocators.drp_EditFolderUserRolePermissionDropdown);
		selectByVisibleText(ClassicLocators.drp_EditFolderUserRolePermissionDropdown,
				AdoddleCommonStringPool.PERMISSION_PUBLISH_AND_LINK);
		clickElementAndWait(DocListingPage.btn_CreateFolderUpdateChanges);
		switchToMultipleFrames();
		waitAndSwitchIframe(LandingPage.frm_AsiteNoResize);
		clickFolderWithTitle(AutoTest_UpdatedFolder);
		AutomationAssert.verifyTrue(isDisplayedFolderWithTitle(AutoTest_UpdatedFolder));
	}

	public void focusWorkspaceAndValidateDirectories(String workspace) throws InterruptedException {

		if (workspace.contains("InheritedCloned_AutoTestProject")) {
			clickElementWithText(inheritedWorkspace);
			waitForElementWithText(AutoTest_Folder);
			AutomationAssert.verifyTrue("Folder " + AutoTest_Folder + " not found.",
					isDisplayedElementByText(AutoTest_Folder));
			AutomationAssert.verifyTrue("Folder " + AutoTest_UpdatedFolder + " not found.",
					isDisplayedElementByText(AutoTest_UpdatedFolder));
			contextClickWithText(AutoTest_Folder);
			clickElementAndWaitForElement(FilesTab.opt_ProjectFolderContextClickEditFolder,
					FilesTab.chk_PopEditFolderDeactivate);
			validateInheritedDirectoriesRolesAndPermission();
			AutomationAssert.verifyTrue("Failure while validation", !isEnabled(FilesTab.txt_EditFolderName));
			AutomationAssert.verifyTrue("Folder Update Button should not Enabled",
					isDisplayed(FilesTab.btn_EditFolderUpdateDisabled));
			clickElementAndWait(FilesTab.btn_EditFolderCancel);
			waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
			waitForCompletePageLoad();
			contextClickWithText(AutoTest_UpdatedFolder);
			clickElementAndWaitForElement(FilesTab.opt_ProjectFolderContextClickEditFolder,
					FilesTab.chk_PopEditFolderDeactivate);
			validateInheritedDirectoriesRolesAndPermission();
			AutomationAssert.verifyTrue("Failure while validation", !isEnabled(FilesTab.txt_EditFolderName));
			AutomationAssert.verifyTrue("Folder Update Button should not Enabled",
					isDisplayed(FilesTab.btn_EditFolderUpdateDisabled));
			clickElementAndWait(FilesTab.btn_EditFolderCancel);
			waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
			waitForCompletePageLoad();

		}

		else {

			AutomationAssert.verifyTrue("Failure while validation", !isDisplayedElementByText(AutoTest_Folder));
			AutomationAssert.verifyTrue("Failure while validation", !isDisplayedElementByText(AutoTest_UpdatedFolder));

		}

	}

	private boolean isDisplayedElementByText(String textElement) {

		String elementXPath = ".//div[contains(@class,'selected')]//following::div[@style='display: block;']//span[text()='"
				+ textElement + "']";
		try {
			return isDisplayed(By.xpath(elementXPath));
		} catch (Throwable t) {
			return false;
		}
	}

	private void validateInheritedDirectoriesRolesAndPermission() {

		Boolean flag = false;
		List<WebElement> folderPermissionList;
		folderPermissionList = findElements(FilesTab.pop_EditFolderSecurityAttributesList);
		log.info("Folder Permission List Size: " + folderPermissionList.size());
		for (WebElement ele : folderPermissionList) {

			String securityObject = ele.getAttribute("title");
			String folderPermission = ele.findElement(By.cssSelector("span")).getText();

			if (securityObject.contains(AutoTest_UserRole)
					|| securityObject.contains(ResourceHandler.loadProperty("test.user.tc.bloggs.name"))) {

				if (securityObject.contains(AutoTest_UserRole))
					AutomationAssert.verifyTrue("Expected Folder Permission" + AdoddleCommonStringPool.PERMISSION_ADMIN
							+ "But was" + folderPermission,
							folderPermission.contains(AdoddleCommonStringPool.PERMISSION_ADMIN));
				else
					AutomationAssert.verifyTrue("Expected Permission: " + AdoddleCommonStringPool.PERMISSION_UPLOAD
							+ "But was " + folderPermission,
							folderPermission.contains(AdoddleCommonStringPool.PERMISSION_UPLOAD));

				flag = true;
			}

			else if (securityObject.contains(Autotest_UpdatedUserRole)
					|| securityObject.contains(ResourceHandler.loadProperty("test.user.pa.bloggs.name"))) {

				if (securityObject.contains(Autotest_UpdatedUserRole))
					AutomationAssert.verifyTrue("Expected Folder Permission" + AdoddleCommonStringPool.PERMISSION_ADMIN
							+ "But was" + folderPermission,
							folderPermission.contains(AdoddleCommonStringPool.PERMISSION_ADMIN));
				else
					AutomationAssert.verifyTrue("Expected Permission: " + AdoddleCommonStringPool.PERMISSION_UPLOAD
							+ "But was " + folderPermission,
							folderPermission.contains(AdoddleCommonStringPool.PERMISSION_PUBLISH_AND_LINK));

				flag = true;
			}

			else
				log.info("Not expected security Object");

		}

		AutomationAssert.verifyTrue("Failure while validating Folder Security Settings", flag);

	}

	public void switchToMultipleFrames() {
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteMainFrame);
	}

	public void valdiateUserRolesInInheritedWorkspace() {

		Boolean flag = false;

		clickElementAndWait(ProjectsTab.lnk_PopManageRolesManageRoles);
		List<WebElement> userRoleList = findElements(FilesTab.css_ManageRolesList);
		log.info("UserRoles List Size :" + userRoleList.size());

		for (WebElement web : userRoleList) {

			List<WebElement> roleAssignedUserList = new ArrayList<WebElement>();
			String roleName = web.findElement(FilesTab.txt_ManageRolesListRoleName).getAttribute("value");
			if (roleName.equalsIgnoreCase(AutoTest_UserRole)) {

				AutomationAssert.verifyTrue("User Role " + AutoTest_UserRole + "not found",
						roleName.equalsIgnoreCase(AutoTest_UserRole));
				AutomationAssert.verifyTrue("Inherited Role should not Enabled",
						!web.findElement(FilesTab.txt_ManageRolesListRoleName).isEnabled());

				roleAssignedUserList = web.findElements(FilesTab.css_ManageRolesAssignedRoleUsersList);
				AutomationAssert.verifyTrue("Size not expected", roleAssignedUserList.size() != 0);
				for (WebElement ele : roleAssignedUserList) {
					String roleUserName = ele.getAttribute("title");
					if (roleUserName.contains(ResourceHandler.loadProperty("test.user.rfi.builder.name")))
						AutomationAssert.verifyTrue("Failure while validation",
								roleUserName.contains(ResourceHandler.loadProperty("test.user.rfi.builder.name")));
					else
						AutomationAssert.verifyTrue("Failure while validation",
								roleUserName.contains(ResourceHandler.loadProperty("test.user.pa.builder.name")));
				}

				flag = true;

			}

			else if (roleName.equalsIgnoreCase(Autotest_UpdatedUserRole)) {

				AutomationAssert.verifyTrue("User Role " + Autotest_UpdatedUserRole + "not found",
						roleName.equalsIgnoreCase(Autotest_UpdatedUserRole));
				AutomationAssert.verifyTrue("Inherited role should not be enabled",
						!web.findElement(FilesTab.txt_ManageRolesListRoleName).isEnabled());
				roleAssignedUserList.clear();
				roleAssignedUserList = web.findElements(FilesTab.css_ManageRolesAssignedRoleUsersList);
				AutomationAssert.verifyTrue(
						eStringUtils.getInEqualityStringError(String.valueOf(roleAssignedUserList.size()), "0", false),
						roleAssignedUserList.size() != 0);
				for (WebElement ele : roleAssignedUserList) {

					String roleUserName = ele.getAttribute("title");

					if (roleUserName.contains(ResourceHandler.loadProperty("multi.project.user.name")))
						AutomationAssert.verifyTrue(
								eStringUtils.getContainsStringError(roleUserName,
										(ResourceHandler.loadProperty("multi.project.user.name"))),
								roleUserName.contains(ResourceHandler.loadProperty("multi.project.user.name")));

					else if (roleUserName.contains(ResourceHandler.loadProperty("test.user.tc.bloggs.name")))
						AutomationAssert.verifyTrue(
								eStringUtils.getContainsStringError(roleUserName,
										(ResourceHandler.loadProperty("test.user.tc.bloggs.name"))),
								roleUserName.contains(ResourceHandler.loadProperty("test.user.tc.bloggs.name")));

					else
						AutomationAssert.verifyTrue(
								eStringUtils.getContainsStringError(roleUserName,
										(ResourceHandler.loadProperty("test.user.pa.bloggs.name"))),
								roleUserName.contains(ResourceHandler.loadProperty("test.user.pa.bloggs.name")));
				}
				flag = true;

			}

			else
				log.info("Not Expected Role");

		}

		AutomationAssert.verifyTrue("failure while validating role attributes", flag);
		clickElementAndWait(ProjectsTab.btn_PopManageRolesCancel);

	}

	public void valdiateUserRolesInClonedWorkspace() {
		Boolean flag = false;

		clickElementAndWait(ProjectsTab.lnk_PopManageRolesManageRoles);
		List<WebElement> userRoleList = findElements(FilesTab.css_ManageRolesList);
		for (WebElement web : userRoleList) {
			String roleName = web.findElement(FilesTab.txt_ManageRolesListRoleName).getAttribute("value");
			AutomationAssert.verifyTrue("User Role " + AutoTest_UserRole + " not expected ",
					!roleName.equalsIgnoreCase(AutoTest_UserRole));
			AutomationAssert.verifyTrue("User Role " + Autotest_UpdatedUserRole + "not expected",
					!roleName.equalsIgnoreCase(Autotest_UpdatedUserRole));
			flag = true;

		}

		AutomationAssert.verifyTrue("failure while validating role attributes", flag);
		clickElementAndWait(ProjectsTab.btn_PopManageRolesCancel);

	}

	public void addAppTemplateInWorkspaceTemplate() {

		switchToMultipleFrames();
		List<WebElement> globalAppsAvailbaleTemplates = findElements(ClassicLocators.css_WorkspaceAppTemplateList);
		log.info("GlobalApps Size:: " + globalAppsAvailbaleTemplates.size());
		for (WebElement ele : globalAppsAvailbaleTemplates) {
			NewAppTemplate = ele.findElement(ClassicLocators.css_WorkspaceAppTemplateListAppName).getText();
			log.info("App TemplateName :" + NewAppTemplate);
			clickElementAndWait(ele.findElement(ClassicLocators.chk_WorkspaceAppTemplate));
		}
		clickElementAndWaitForElement(ClassicLocators.btn_CopySelectedFormTemplatesToWorkspace,
				ClassicLocators.css_ManageWorkspaceAppsPageTitle);
		waitUntilElementContainsText(ClassicLocators.css_ManageWorkspaceAppsPageTitle,
				AdoddleCommonStringPool.LABEL_WORKSPACE_APPS);
		AutomationAssert.verifyTrue("Page not Found", getElementText(ClassicLocators.css_ManageWorkspaceAppsPageTitle)
				.contains(AdoddleCommonStringPool.LABEL_WORKSPACE_APPS));
		globalAppsAvailbaleTemplates.clear();

	}

	public void activateAvailableAppTemplates() {

		List<WebElement> availableAppsTemplatesCheckBoxes = findElements(ClassicLocators.chk_ManageWorkspaceAppsCheckboxes);

		for (WebElement web : availableAppsTemplatesCheckBoxes) {
			if (!web.isSelected())
				web.click();
			else
				log.info("App Already Active");
		}

		clickElementAndWait(ClassicLocators.btn_ManageWorkspaceAppsSubmit);

	}

	public void updateAppTemplateInWorkspaceTemplate(String existingAppName, String updatedAppName) {

		if (updatedAppName.contains("Request_ForInformation"))
			AutoTest_App = updatedAppName + epoch;
		else
			AutoTest_UpatedApp = updatedAppName + epoch;

		waitUntilListOfElementIsDisplayed(ClassicLocators.css_ActiveWorkspaceAppTemplateList);
		List<WebElement> globalAppsAvailbaleTemplates = findElements(ClassicLocators.css_ActiveWorkspaceAppTemplateList);
		log.info("Available app template size:: " + globalAppsAvailbaleTemplates.size());
		for (WebElement ele : globalAppsAvailbaleTemplates) {

			WebElement appTemplateCheckBox = ele.findElement(ClassicLocators.chk_ActiveWorkspaceAppTemplateCheckBox);
			WebElement appTemplateEditIcon = ele.findElement(ClassicLocators.chk_ActiveWorkspaceAppTemplateEditIcon);
			String appTemplateName = ele.findElement(ClassicLocators.css_ActiveWorkspaceAppTemplateName).getText();

			if (appTemplateName.equalsIgnoreCase(NewAppTemplate)) {
				if (!appTemplateCheckBox.isSelected())
					appTemplateCheckBox.click();

				appTemplateEditIcon.click();
				break;
			}

			else if (appTemplateName.equalsIgnoreCase(existingAppName)) {

				if (!appTemplateCheckBox.isSelected())
					appTemplateCheckBox.click();

				appTemplateEditIcon.click();
				break;

			}

			else
				log.info("Workspace App Template not found");

		}
		if (isDisplayed(GlobalPageElements.ele_PleaseWaitLoadingDataMessage))
			waitUntilElementIsInvisible(GlobalPageElements.ele_PleaseWaitLoadingDataMessage);

		waitUntilElementIsDisplayed(ClassicLocators.css_EditWorkspaceInstancePageTitle);
		AutomationAssert.verifyTrue("Page not Found",
				getElementText(ClassicLocators.css_EditWorkspaceInstancePageTitle).contains("workspace form instance"));

		if (updatedAppName.contains("Request_ForInformation"))
			updateWorkspaceAppTemplate(AutoTest_App);
		else
			updateWorkspaceAppTemplate(AutoTest_UpatedApp);

		clickElementAndWait(ClassicLocators.btn_ManageWorkspaceAppsSubmit);
		if (isDisplayed(GlobalPageElements.ele_PleaseWaitLoadingDataMessage))
			waitUntilElementIsInvisible(GlobalPageElements.ele_PleaseWaitLoadingDataMessage);
		globalAppsAvailbaleTemplates.clear();
	}

	private void updateWorkspaceAppTemplate(String updateAppTemplate) {

		appGroupName = "AutoTestAppGroup" + epoch;
		String updatedAppGroupName = "AutoTestAppGroup_Updated" + epoch;

		String parentHandle = getCurrentWindow();
		sendKeys(ClassicLocators.txt_EditWorkspaceInstanceFormName, updateAppTemplate);
		sendKeys(ClassicLocators.txt_EditWorkspaceInstanceGroupCode, javaUtils.getRandomString(5));
		if (updateAppTemplate.contains(AutoTest_App))
			sendKeys(ClassicLocators.txt_EditWorkspaceInstanceFormGroupName, appGroupName);
		else
			sendKeys(ClassicLocators.txt_EditWorkspaceInstanceFormGroupName, updatedAppGroupName);

		if (!isSelected(ClassicLocators.txt_EditWorkspaceInstanceFormType))
			clickElement(ClassicLocators.txt_EditWorkspaceInstanceFormType);
		waitAndSwitchIframe(ProjectFormsTab.frm_FormUploadTemplate);
		if (updateAppTemplate.contains(AutoTest_App))
			sendKeys(ProjectFormsTab.btn_ClassicEditFormSettingsBrowse,
					ResourceHandler.loadProperty("app.rfi.xsn.path"));
		else
			sendKeys(ProjectFormsTab.btn_ClassicEditFormSettingsBrowse,
					ResourceHandler.loadProperty("app.distribution.group.xsn.path"));

		AutomationAssert.verifyTrue(
				eStringUtils.getVisibilityStringError(ProjectFormsTab.btn_ClassicEditFormSettingsUpload, true),
				isDisplayed(ProjectFormsTab.btn_ClassicEditFormSettingsUpload));
		waitUntilElementIsClickable(ProjectFormsTab.btn_ClassicEditFormSettingsUpload);
		clickElementAndWait(ProjectFormsTab.btn_ClassicEditFormSettingsUpload);
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(ProjectFormsTab.btn_UploadFormTemplateSuccessFullOK);
		clickElement(ProjectFormsTab.btn_UploadFormTemplateSuccessFullOK);
		switchPreviousWindow(parentHandle);
		switchToMultipleFrames();
		if (!isSelected(ClassicLocators.rad_EditFormSettingsResponseAllowedNo))
			clickElement(ClassicLocators.rad_EditFormSettingsResponseAllowedNo);
		List<WebElement> appActionsList;
		appActionsList = findElements(ClassicLocators.css_EditFormSettingsFormActionslist);
		for (int index = 0; index < appActionsList.size(); index++) {

			if (index == 0)
				log.info("Action Header Element need to Skip");

			else {

				WebElement actionCheckBox = appActionsList.get(index).findElement(
						ClassicLocators.chk_EditFormSettingsFormActionCheckbox);
				WebElement actionDays = appActionsList.get(index).findElement(
						ClassicLocators.sel_EditFormSettingsFormActionDueDays);
				String actionName = appActionsList.get(index).findElement(By.cssSelector("td[width='141']")).getText();

				if (actionName.contains(AdoddleCommonStringPool.ACTION_ASSIGN_STATUS)) {
					if (!actionCheckBox.isSelected())
						actionCheckBox.click();
					selectByVisibleText(actionDays, "3");
				}

				else if (actionName.contains(AdoddleCommonStringPool.ACTION_ATTACH_DOCS)) {
					if (!actionCheckBox.isSelected())
						actionCheckBox.click();
					selectByVisibleText(actionDays, "6");
				}

				else if (actionName.contains(AdoddleCommonStringPool.ACTION_DISTRIBUTE)) {
					if (!actionCheckBox.isSelected())
						actionCheckBox.click();
					selectByVisibleText(actionDays, "9");
				}

				else if (actionName.contains(AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT)) {
					if (!actionCheckBox.isSelected())
						actionCheckBox.click();
					selectByVisibleText(actionDays, "12");
				}

				else if (actionName.contains(AdoddleCommonStringPool.ACTION_FOR_INFORMATION)) {
					if (!actionCheckBox.isSelected())
						actionCheckBox.click();
					selectByVisibleText(actionDays, "16");
				}

				else {
					log.info("Not Expected action");
					if (actionCheckBox.isSelected())
						actionCheckBox.click();
				}

			}

		}

		if (!isSelected(ClassicLocators.rad_EditFormSettingsAllowDistributionYes))
			clickElement(ClassicLocators.rad_EditFormSettingsAllowDistributionYes);

		if (!isSelected(ClassicLocators.rad_EditFormSettingsAllowAttachmentYes))
			clickElement(ClassicLocators.rad_EditFormSettingsAllowAttachmentYes);

	}

	public void valdiateAppTemplateInInheritedWorkspace() {

		boolean flag = false;
		List<WebElement> workspaceAppTemplates = findElements(FilesTab.css_ManageAppsSettingsAppList);
		for (WebElement ele : workspaceAppTemplates) {
			if (ele.getText().contains(AutoTest_App)) {
				flag = true;
				AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(ele.getText(), AutoTest_App), ele
						.getText().contains(AutoTest_App));
			} else if (ele.getText().contains(AutoTest_UpatedApp)) {
				flag = true;
				AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(ele.getText(), AutoTest_UpatedApp), ele
						.getText().contains(AutoTest_UpatedApp));
			} else
				log.info("Not Expected App Template");
		}

		AutomationAssert.verifyTrue("AppTemplate not found in Listing: " + AutoTest_App + " " + AutoTest_UpatedApp,
				flag);
		clickElementAndWait(ProjectsTab.btn_ManageAppSettingsCancelButton);
		workspaceAppTemplates.clear();

	}

	public void valdiateAppTemplateClonedWorkspace() {

		boolean flag = true;
		List<WebElement> workspaceAppTemplates = findElements(FilesTab.css_ManageAppsSettingsAppList);

		for (WebElement ele : workspaceAppTemplates) {

			if (ele.getText().contains(AutoTest_App)) {
				flag = false;
				AutomationAssert.verifyTrue("Failure while validation", !ele.getText().contains(AutoTest_App));
			}

			else if (ele.getText().contains(AutoTest_UpatedApp)) {
				flag = false;
				AutomationAssert.verifyTrue("Failure while validation", !ele.getText().contains(AutoTest_UpatedApp));
			}

			else
				log.info("Not Expected App Template");

		}

		AutomationAssert.verifyTrue("AppTemplate found in Listing" + AutoTest_App + " " + AutoTest_UpatedApp, flag);
		clickElementAndWait(ProjectsTab.btn_ManageAppSettingsCancelButton);
	}

	public void navigateFormPermissionTab() throws InterruptedException {
		switchDefault();
		switchMultiFrames();
		clickElementAndWaitForElement(ClassicLocators.css_TabRoleFormPermission,
				ClassicLocators.btn_RoleFormPermissionList);
	}

	public void assignedRoleFormPermission(String createPermission, String viewFormPermission)
			throws InterruptedException {

		boolean flag = false;

		List<WebElement> formPermissionList;
		sendKeys(ClassicLocators.css_RoleFormPermissionSearchField,
				AdoddleCommonStringPool.SECURITY_ROLE_WORKSPACEADMIN);
		clickElementAndWait(ClassicLocators.btn_RoleFormPermissionSearch);
		formPermissionList = findElements(ClassicLocators.btn_RoleFormPermissionList);
		log.info("Permission List::  " + formPermissionList.size());
		for (WebElement ele : formPermissionList) {
			String formPermission = ele.getAttribute("title");
			log.info("Form Permission: " + formPermission);
			WebElement permissionCheckbox = ele.findElement(ClassicLocators.chk_RoleFormPermissionChekbox);
			if (formPermission.contains(createPermission) || formPermission.contains(viewFormPermission)) {
				if (!permissionCheckbox.isSelected()) {
					permissionCheckbox.click();
					flag = true;
				}
			}

			else
				log.info("Not Expected Permission");

		}
		clickElementAndWait(ClassicLocators.btn_RoleFormPermissionSave);
		waitForCompletePageLoad();
		AutomationAssert.verifyTrue("Failure While Assigning Permission", flag);
	}

	public void focusWorksapceAndAppTemplate(String appTemplate) {

		if (appTemplate.contains("AutoTestGroup")) {

			clickElementWithText(inheritedWorkspace);
			clickElementWithText(appGroupName);
			clickElementWithText(AutoTest_App);
		}

		else {
			clickElementWithText(clonedWorkspace);
			clickElementWithText("AutoGroupTestAppTemplate");
			clickElementWithText(appTemplate);

		}
	}

	public void createFormWithAppTemplate(String formName) throws ParseException {

		if (formName.equalsIgnoreCase("Inherited_AutoTestForm")) {

			Inherited_FormSubject = formName + epoch;
			AutomationAssert.verifyTrue(
					"Failure while validating Form XSN : Request For Information",
					getElementText(ProjectFormsTab.lbl_RequestForInformationFormTitle).contains(
							"Request For Information"));
			sendKeys(ProjectFormsTab.txt_CreateRFIFormSubject, Inherited_FormSubject);
			sendKeys(ProjectFormsTab.txt_CreateRFIFormSubject, Keys.TAB);
			sendKeys(ProjectFormsTab.txt_CreateRFIFormDescription, javaUtils.getRandomString(30));
			// if
			// (ResourceHandler.loadProperty("app.view.beta.flag").contains("true"))
			clickElementAndWait(ProjectFormsTab.btn_BetaViewCreateFormAttachmentClipIcon);
			/*
			 * else clickElementAndWait(ModelsTab.btn_CreateFormAttachment);
			 */

			clickOnSelectFilesAndAttachDocuments();

		}

		else {

			Cloned_FormSubject = formName + epoch;
			AutomationAssert.verifyTrue("Failure while validating Form XSN : Internal Ref",
					getElementText(ProjectFormsTab.lbl_RFIMobileFormTitle).contains("Internal Ref"));
			sendKeys(ProjectFormsTab.txt_AutoTestAppTemplateInternalRef, javaUtils.getRandomString(15));
			sendKeys(ProjectFormsTab.txt_AutoTestAppTemplateTitle, Cloned_FormSubject);
			sendKeys(ProjectFormsTab.txt_AutoTestAppTemplateDescription, javaUtils.getRandomString(15));
			sendKeys(ProjectFormsTab.txt_AutoTestAppTemplateDescription, Keys.TAB);
			sendKeys(ProjectFormsTab.txt_AutoTestAppTemplateRespondDueDate, DateUtils.addDaysToDate(
					AdoddleCommonStringPool.DATE_FORMAT_AUS, AdoddleCommonStringPool.TIMEZONE_ID_AUS, 5));
		}
		waitUntilElementIsClickable(ProjectFormsTab.btn_CreateFormSendButton);
		clickElementAndWait(ProjectFormsTab.btn_CreateFormSendButton);
		switchDefault();
		verifyFormSuccessMessage();

	}

	private void clickOnSelectFilesAndAttachDocuments() {

		sysUtils.authenticateRemoteMachine(nodeIP);

		String createFile1 = sysUtils.createRemotePdfFile(
				nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch()
						+ AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
		String createFile2 = sysUtils.createRemotePdfFile(
				nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch()
						+ AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();

		log.info("first file created as: " + createFile1);
		log.info("second file created as :" + createFile2);

		fileList = sysUtils.getFileList(createFile1 + "," + createFile2);
		AutomationAssert.verifyTrue("Failure while creating Dcouments", fileList.size() != 0);

		// if
		// (ResourceHandler.loadProperty("app.view.beta.flag").contains("true"))
		// {
		uploadMultipleFilesUsingKeys(ProjectFormsTab.btn_BetaViewCreateFormAttachmentsSelectFiles, fileList);
		clickOnAttach();
		/*
		 * }
		 * 
		 * else { uploadMultipleFilesUsingKeys(ProjectFormsTab.
		 * btn_CreateFormAttachmentSelectFiles, fileList);
		 * waitUntilListOfElementIsDisplayed
		 * (ModelsTab.css_PopModelCommentAttachmentsAttachedFileNameList);
		 * clickElementAndWait
		 * (ModelsTab.btn_PopModelCommentAttachmentsAttachButton); }
		 */

	}

	private void clickOnAttach() {
		clickElementAndWait(ModelsTab.btn_PopModelCommentAttachmentsAttachButton);
		try {
			waitUntilElementIsDisplayed(ProjectFormsTab.lbl_CreateFormUploadSucessMsg);
			AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lbl_CreateFormUploadSucessMsg).contains(
					"Upload Completed"));
		} catch (Throwable t) {
			log.info("Failure while attachment operation");
		}
	}

	private void verifyFormSuccessMessage() {
		try {
			verifyElementText(DiscussionsTab.lbl_FormAddSuccessMsg, "Form created successfully", 30);
		} catch (Throwable t) {
			log.error("Success Message could not be verified");
		}
		waitForCompletePageLoad();
	}

	public void validateAppInListing(String formIdentifier) {

		if (formIdentifier.equalsIgnoreCase("Inherited_AutoTestForm")) {
			searchForms(Inherited_FormSubject);
			waitUntilElementIsDisplayed(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle);
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(
					getElementText(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle), Inherited_FormSubject),
					getElementText(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle).contains(Inherited_FormSubject));
		} else {
			searchForms(Cloned_FormSubject);
			waitUntilElementIsDisplayed(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle);
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(
					getElementText(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle), Cloned_FormSubject),
					getElementText(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle).contains(Cloned_FormSubject));
		}

	}

	private void waitForPageLoad() {
		if (isDisplayed(GlobalPageElements.ele_PleaseWaitLoadingDataMessage))
			waitUntilElementIsInvisible(GlobalPageElements.ele_PleaseWaitLoadingDataMessage);
	}

	public void contextClickAndSelectMenuOption(String workspace) {

		if (workspace.equalsIgnoreCase("Inherited_AutoTestForm")) {
			clickElementWithText(inheritedWorkspace);
			contextClick(ProjectFormsTab.lnk_FirstFormTitle);
			mouseHoverAndClickElement(ProjectFormsTab.opt_FormContextClickShare,
					FilesTab.opt_FileContextClickShareDistribute);
		}

		else {
			clickElementWithText(clonedWorkspace);
			contextClick(ProjectFormsTab.lnk_FirstFormTitle);
			mouseHoverAndClickElement(ProjectFormsTab.opt_FormContextClickShare,
					FilesTab.opt_FileContextClickShareDistribute);
		}

	}

	public void validateActionInApp() {

		sendKeys(ProjectFormsTab.txt_PopUpDistributeToInput, ResourceHandler.loadProperty("test.user.tc.bloggs.name"));
		sendKeys(ProjectFormsTab.txt_PopUpDistributeToInput, Keys.ENTER);
		clickElementAndWait(ProjectFormsTab.css_PopDistributeToggleDropdowns);
		List<WebElement> appActions = findElements(ProjectFormsTab.sel_DistributeContextClickActionsList);
		for (WebElement web : appActions)
			AutomationAssert.verifyTrue("Not Expected" + AdoddleCommonStringPool.ACTION_RESPOND + "Action", !web
					.getText().contains(AdoddleCommonStringPool.ACTION_RESPOND));

		clickElementAndWait(ProjectFormsTab.ele_CreateFormToggleActionsClose);
		clickElementAndWait(ProjectFormsTab.btn_PopDistributeButtonCancel);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();

	}

	public void validateAppActionInFileViewer() {

		String parentHandel = getCurrentWindow();
		executeJScript(AdoddleCommonJQueries.expandFirstFormTitleJQuery);
		waitUntilElementIsClickable(ProjectFormsTab.lnk_FirstFormTitle);
		clickElement(ProjectFormsTab.lnk_FirstFormTitle);
		waitForSwitchWindow(2);
		switchWindow();
		// if
		// (ResourceHandler.loadProperty("app.view.beta.flag").contains("true"))
		// {
		waitUntilElementIsDisplayed(ProjectFormsTab.btn_BetaViewFormDetailsActionDropdownButton);
		clickElementAndWait(ProjectFormsTab.btn_BetaViewFormDetailsActionDropdownButton);
		List<WebElement> formViewerAssignedActions = findElements(ProjectFormsTab.css_FormViewerFormActionsList);
		for (WebElement web : formViewerAssignedActions)
			AutomationAssert.verifyTrue("Not Expected" + AdoddleCommonStringPool.ACTION_RESPOND + "Action", !web
					.getText().contains(AdoddleCommonStringPool.ACTION_RESPOND));
		/*
		 * }
		 * 
		 * else { waitUntilElementIsDisplayed(ProjectFormsTab.
		 * btn_FormDetailsActionDropdownButton);
		 * clickElementAndWait(ProjectFormsTab
		 * .btn_FormDetailsActionDropdownButton); List<WebElement>
		 * formViewerAssignedActions =
		 * findElements(ProjectFormsTab.css_FormViewerFormActionsList); for
		 * (WebElement web : formViewerAssignedActions)
		 * AutomationAssert.verifyTrue("Not Expected" +
		 * AdoddleCommonStringPool.ACTION_RESPOND + "Action", !web
		 * .getText().contains(AdoddleCommonStringPool.ACTION_RESPOND));
		 * 
		 * }
		 */

		closeCurrentWindow();
		switchWindow(parentHandel);

	}

	public void distributeAppAction() {
		sendKeys(ProjectFormsTab.txt_PopUpDistributeToInput, ResourceHandler.loadProperty("test.user.tc.bloggs.name"));
		sendKeys(ProjectFormsTab.txt_PopUpDistributeToInput, Keys.ENTER);
		clickElementAndWaitForElement(ProjectFormsTab.css_PopDistributeToggleDropdowns,
				ProjectFormsTab.drp_CreateFormAssignActionToUser);
		selectByVisibleText(ProjectFormsTab.drp_CreateFormAssignActionToUser, AdoddleCommonStringPool.ACTION_RESPOND);
		clickElementAndWaitForElement(ProjectFormsTab.ele_CreateFormToggleActionsClose,
				ProjectFormsTab.txt_PopDistributeSubjectInput);
		sendKeys(ProjectFormsTab.txt_PopDistributeSubjectInput, javaUtils.getRandomString(14));
		clickElementAndWait(ProjectFormsTab.btn_PopUpDistributeButtonDistribute);
	}

	public void searchAndValidatedAppAction() {
		clickElementWithText(clonedWorkspace);
		searchForms(Cloned_FormSubject);
		waitUntilElementIsDisplayed(ProjectFormsTab.ele_FormListingFirstFormActionDiv);
		AutomationAssert.verifyTrue(
				eStringUtils.getContainsStringError(getElementText(ProjectFormsTab.lnk_FormListingFirstFormAction),
						AdoddleCommonStringPool.ACTION_RESPOND),
				getElementText(ProjectFormsTab.lnk_FormListingFirstFormAction).contains(
						AdoddleCommonStringPool.ACTION_RESPOND));
	}

	public void performAppAssignedAction() {
		clickElementAndWait(ProjectFormsTab.lnk_FormListingFirstFormAction);
		waitForSwitchWindow(2);
		switchWindow();
		waitAndSwitchIframe(ProjectFormsTab.frm_replyFormIframe);
		sendKeys(ProjectFormsTab.txt_AutoTestAppTemplateReply, javaUtils.getRandomString(15));
		/*
		 * if
		 * (ResourceHandler.loadProperty("app.view.beta.flag").contains("true"))
		 */
		clickElementAndWait(ProjectFormsTab.btn_BetaViewCreateFormAttachmentClipIcon);
		/*
		 * else clickElementAndWait(ModelsTab.btn_CreateFormAttachment);
		 */
		clickOnSelectFilesAndAttachDocuments();
		waitUntilElementIsClickable(ProjectFormsTab.btn_CreateFormSendButton);
		clickElement(ProjectFormsTab.btn_CreateFormSendButton);
		switchDefault();
		verifyFormSuccessMessage();

	}

	public void cleanUpOperation() {

		try {
			deactivateProject(clonedWorkspace);
			deactivateInheritanceProject(inheritedWorkspace);

		} catch (Throwable e) {

			log.info("Failure while performing Clean up Operations");
		}

	}

	public void validateAndDeactivateTemplate() {

		try {
			waitAndSwitchIframe(ClassicLocators.frm_AsiteWorkingFrame);
			waitAndSwitchIframe(ClassicLocators.frm_AsiteMainFrame);
			waitUntilElementIsDisplayed(ClassicLocators.lbl_WorkspaceTemplatesTab);
			clickElementAndWait(ClassicLocators.lbl_WorkspaceTemplatesTab);
			waitUntilElementIsDisplayed(ClassicLocators.existingTemplateList);
			validateTemplateInListing();
			switchDefault();
			waitUntilElementIsDisplayed(ClassicLocators.frm_AsiteWorkingFrame);
			switchIframe(ClassicLocators.frm_AsiteWorkingFrame);
			waitAndSwitchIframe(ClassicLocators.frm_AsiteHeaderFrame);
			waitUntilElementIsDisplayed(ClassicLocators.lbl_WorkspaceHeaderName);
			AutomationAssert.verifyTrue(getElementText(ClassicLocators.lbl_WorkspaceHeaderName).equalsIgnoreCase(
					workspaceTemplate));
			selectByVisibleText(ClassicLocators.drp_AdminDropdown, AdoddleCommonStringPool.EDIT_WORKSPACE);
			waitForCompletePageLoad();
			switchDefault();
			switchIframe(ClassicLocators.frm_AsiteWorkingFrame);
			waitUntilElementIsDisplayed(ClassicLocators.frm_AsiteMainFrame);
			switchIframe(ClassicLocators.frm_AsiteMainFrame);
			waitUntilElementIsDisplayed(ClassicLocators.frm_PmFrame);
			switchIframe(ClassicLocators.frm_PmFrame);
			waitUntilElementIsDisplayed(GlobalPageElements.lbl_PageTitle);
			AutomationAssert.verifyTrue(getElementText(GlobalPageElements.lbl_PageTitle).contains(
					AdoddleCommonStringPool.PAGE_TITLE_WORKSPACE_TEMPLATE));
			selectByVisibleText(ClassicLocators.lnk_CreateEditWorkspaceTemplateStatus,
					AdoddleCommonStringPool.STATUS_CLOSED);
			waitUntilElementIsDisplayed(ClassicLocators.lnk_CreateEditWorkspaceSave);
			clickElementAndWait(ClassicLocators.lnk_CreateEditWorkspaceSave);

		} catch (Throwable t) {
			log.error("Failure in deactivating Template " + workspaceTemplate);
		}

	}

	private void validateTemplateInListing() {
		Boolean flag = false;
		List<WebElement> templateList;
		templateList = findElements(ClassicLocators.existingTemplateList);
		for (WebElement web : templateList) {
			log.info("web text: " + web.getText());
			if (web.getText().contains(workspaceTemplate)) {
				flag = true;
				web.findElement(ClassicLocators.lnk_WorkspaceTemplateName).click();
				break;
			}
		}
		if (flag)
			log.info("Template found successfully");
		else
			AutomationAssert.verifyTrue("Template not found in listing", false);
		waitForCompletePageLoad();
	}
}