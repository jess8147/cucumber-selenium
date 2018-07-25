/**  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DistributionGroupsScripts extends AdoddleCommonAppMethods {
	private int					index, j, accessCounter;
	private String				groupAccessList[]		= { "Admin", "Access to Use", "No Access" };
	private String				distGroupNameList[]		= { "DocDistGrp_Admin", "DocDistGrp_AccesstoUse", "DocDistGrp_NoAccess" };
	private String				formGroupNameList[]		= { "FormDistGrp_Admin", "FormDistGrp_AccesstoUse", "FormDistGrp_NoAccess" };
	private String				documentsActionArray[]	= { "For Acknowledgement", "For Action", "For Comment", "For Comment Coordination", "For Comment Incorp.", "For Distribution", "For Information", "For Status Change" };
	private String				formsActionArray[]		= { "Assign Status", "Attach Docs", "Distribute", "For Acknowledgement", "For Action", "For Information", "Respond", "Review Draft" };
	private String				accessToUseAction		= "For Information", noAccessAction = "For Acknowledgement", noAccessDueDate = "1", adminSaveDraftAction = "Review Draft", adminSaveDraftDueDate, draftID = "DRAFT";
	private String				distributionGroup, contextPopEdit, groupNameType, securityLevel;
	private String				formTemplateName		= "Check Form Distribution", formTitle;
	public Map<String, String>	expectedActionDaysMap	= new HashMap<String, String>();
	public Map<String, String>	actualActionDaysMap		= new HashMap<String, String>();
	public static Logger		log						= AutomationLogger.getInstance().getLogger(DistributionGroupsScripts.class);

	/******* Document Distribution Groups *******/
	public void clickOnDistributionGroups(String project, String edit, String distGrps) {
		CreateEditRoleScripts.projectName = project;
		distributionGroup = distGrps;
		contextPopEdit = edit;
		contextClickWithLink(project);
		clickContextMenuOption(edit, distGrps);
	}

	public void createDocumentDistributionGroups(String groupType, String admin, String accessToUse, String noAccess) throws InterruptedException {
		distributionGroupsDetails(distGroupNameList, groupType, admin, accessToUse, noAccess);
		collectionDataMap.put("Distribution Group List", distGroupNameList.toString());
	}

	public void clickOnSaveAll() {
		clickElementAndWait(ProjectsTab.btn_DistributionGroupSaveAll);
		waitUntilElementIsInvisible(ProjectsTab.btn_DistributionGroupSaveAll);
	}

	public void verifyDocumentDistributionGroups(String admin, String accessToUse, String noAccess) throws InterruptedException {
		verifyDistributionGroups(distGroupNameList, admin, accessToUse, noAccess);
	}

	public void clickOnDistributeFiles() {
		clickElementAndWait(FilesTab.btn_PopUploadFileDistributeFilesButton);
	}

	public void verifyDistributeTextboxes(String To, String Subject) {
		Assert.assertTrue(isDisplayed(By.xpath(".//div[@style='display: block;']//label[contains(text(),'" + To.trim() + "')]")));
		Assert.assertTrue(isDisplayed(By.xpath(".//div[@style='display: block;']//label[contains(text(),'" + Subject.trim() + "')]")));
	}

	public void enterAdminDocDistributionGroup() throws InterruptedException {
		sendKeys(FilesTab.txt_PopUploadFileDistributeTo, distGroupNameList[0] + epoch);
		sendKeys(FilesTab.txt_PopUploadFileDistributeTo, Keys.ENTER);
	}

	public void verifyUploadedFileAndAdminGroupActions() {
		searchFiles(strUtils.extractFileNameString(CreateEditRoleScripts.createFile));
		Assert.assertTrue(isDisplayed(FilesTab.lnk_FileName));
		executeJScript(AdoddleCommonJQueries.actionColWidthExpandQuery);
		waitUntilElementIsDisplayed(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		mouseHover(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
		List<WebElement> popOverElements = findElements(GlobalPageElements.css_firstActionsPopoverContentLinks);
		List<WebElement> popOverDays = findElements(GlobalPageElements.css_firstActionsPopoverDueDaysContents);

		for (int index = 0; index < popOverElements.size(); index++)
			actualActionDaysMap.put(popOverElements.get(index).getText(), popOverDays.get(index).getText().split(" ")[0].trim());

		for (Map.Entry<String, String> entry : actualActionDaysMap.entrySet())
			log.info("Actual map: " + entry.getKey() + "/" + entry.getValue());

		for (Map.Entry<String, String> entry : expectedActionDaysMap.entrySet())
			log.info("Expected map: " + entry.getKey() + "/" + entry.getValue());

		Assert.assertTrue(compareUnorderedMaps(expectedActionDaysMap, actualActionDaysMap));

	}

	public void enterAccessToUseDocDistributionGroup() throws InterruptedException {
		sendKeys(FilesTab.txt_PopUploadFileDistributeTo, distGroupNameList[1] + epoch);
		sendKeys(FilesTab.txt_PopUploadFileDistributeTo, Keys.ENTER);
	}

	public void verifyUploadedFileAndAccessToUseGroupActions(String actionType) {
		searchFiles(strUtils.extractFileNameString(CreateEditRoleScripts.createFile));
		Assert.assertTrue(isDisplayed(FilesTab.lnk_FileName));

		waitUntilElementIsDisplayed(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		mouseHover(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
		String popOverElementAction = getElementText(GlobalPageElements.css_firstActionsPopoverContentLinks);

		Assert.assertTrue(actionType.contains(popOverElementAction));
		Assert.assertTrue(accessToUseAction.contains(popOverElementAction));
	}

	public void deactivateDistributionGroup() throws InterruptedException {
		if (groupNameType.contains("Apps")) {
			log.info(": Form Groups Deactivate :");
			deactivateGroups(formGroupNameList);
		}
		else if (groupNameType.contains("Documents")) {
			log.info(": Distribution Groups Deactivate :");
			deactivateGroups(distGroupNameList);
		}
	}

	public void verifyDistributionGroupsDeactivated() throws InterruptedException {
		if (groupNameType.contains("Apps")) {
			log.info(": Form Deactivated Group Verified :");
			verifyGroupsDeactivated(formGroupNameList);
		}
		else if (groupNameType.contains("Documents")) {
			log.info(": Distribution Deactivated Group Verified :");
			verifyGroupsDeactivated(distGroupNameList);
		}
	}

	public void verifyDistributionGroupsDeactivatedOnFileUpload(String folder) throws IOException, InterruptedException {
		clickElementWithText(CreateEditRoleScripts.projectName);
		clickElementWithText(folder);
		clickElementAndWait(FilesTab.btn_DocListingAddFiles);
		clickOnSelectFilesAndUpload();
		clickOnDistributeFiles();

		for (String groupName : distGroupNameList) {
			sendKeys(FilesTab.txt_PopUploadFileDistributeTo, groupName + epoch);
			Assert.assertTrue(isDisplayed(FilesTab.ele_PopUploadFileListNoMatchesLabel));
		}
	}

	/******* Form Distribution Groups *******/

	public void createFormDistributionGroups(String groupType, String admin, String accessToUse, String noAccess) throws InterruptedException {
		distributionGroupsDetails(formGroupNameList, groupType, admin, accessToUse, noAccess);
		collectionDataMap.put("Form Distribution Group", formGroupNameList.toString());
	}

	public void verifyFormDistributionGroups(String admin, String accessToUse, String noAccess) throws InterruptedException {
		verifyDistributionGroups(formGroupNameList, admin, accessToUse, noAccess);
	}

	public void clickOnDistributeForm() {

		//if (ResourceHandler.loadProperty("app.view.beta.flag").equalsIgnoreCase("true")) {

			if (!isDisplayed(ProjectFormsTab.frm_createFormIframe)) {
				clickElementAndWait(ProjectFormsTab.btn_BetaViewPopCreateFormDistributeFormButton);
			}
			else {
				waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
				clickElementAndWait(ProjectFormsTab.btn_BetaViewPopCreateFormDistributeFormButton);
			}
		/*}
		else {

			if (!isDisplayed(ProjectFormsTab.frm_createFormIframe)) {
				clickElementAndWait(ProjectFormsTab.btn_PopCreateFormDistributeFormButton);
			}
			else {
				waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
				clickElementAndWait(ProjectFormsTab.btn_PopCreateFormDistributeFormButton);
			}
		}*/
	}

	public void verifyDistributeFormToField() {

		//if (ResourceHandler.loadProperty("app.view.beta.flag").equalsIgnoreCase("true")) 
			Assert.assertTrue(isDisplayed(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo));
		/*else 
			Assert.assertTrue(isDisplayed(ProjectFormsTab.txt_PopCreateFormDistributeTo));*/
	}

	public void enterAdminFormDistributionGroup() throws InterruptedException {

		//if (ResourceHandler.loadProperty("app.view.beta.flag").equalsIgnoreCase("true")) {
			sendKeys(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo, formGroupNameList[0] + epoch);
			waitUntilElementIsDisplayed(By.xpath(".//div[@id='distInputTo']//input[@type='checkbox']//following::span[contains(text(),'" + formGroupNameList[0] + epoch + "')]"));
			clickElementAndWaitForElement(By.xpath(".//div[@id='distInputTo']//span[contains(text(),'" + formGroupNameList[0] + epoch + "')]//preceding::input[@type='checkbox']"),ProjectFormsTab.lnk_BetaViewCreateFormDistributeToCloseButton);
			clickElementAndWait(ProjectFormsTab.lnk_BetaViewCreateFormDistributeToCloseButton);
		/*}
		else {
			sendKeys(ProjectFormsTab.txt_PopCreateFormDistributeTo, formGroupNameList[0] + epoch);
			sendKeys(ProjectFormsTab.txt_PopCreateFormDistributeTo, Keys.ENTER);
		}*/
	}

	private List<String>	formCollectionList	= new ArrayList<String>();

	public void enterFormTitleAndClearGropCodeText() throws InterruptedException {

		//if (ResourceHandler.loadProperty("app.view.beta.flag").equalsIgnoreCase("true")) {

			formTitle = "AutomationTestForm" + dateUtils.getEpoch();
			formCollectionList.add(formTitle);
			collectionDataMap.put("Form Title List", formCollectionList.toString());
			clear(ProjectFormsTab.txt_CreateFormTitle);
			sendKeys(ProjectFormsTab.txt_CreateFormTitle, formTitle);
			clear(ProjectFormsTab.txt_PopCreateFormGroupCode);
			waitForCompletePageLoad();
			executeJScript(AdoddleCommonJQueries.betaViewCreateFormScrollMaxDownQuery);
			clickElementAndWaitForElement(ProjectFormsTab.img_BetaViewCreateFormCalendar, ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
			clickElementAndWait(ProjectFormsTab.ele_CreateFormCalendarCurrentDate);

		/*}
		else {

			formTitle = "AutomationTestForm" + dateUtils.getEpoch();
			clear(ProjectFormsTab.txt_CreateFormTitle);
			sendKeys(ProjectFormsTab.txt_CreateFormTitle, formTitle);
			clear(ProjectFormsTab.txt_PopCreateFormGroupCode);
			waitForCompletePageLoad();
			clickElementAndWaitForElement(ProjectFormsTab.img_CreateFormCalendar, ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
			clickElementAndWait(ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
		}*/
	}

	public void clickOnSend() {
		try {
			clickElementAndWait(ProjectFormsTab.btn_CreateFormSendButton);
		}
		catch (Throwable t) {
			log.error("by passing error");
		}
		acceptAlertAndWait();
		switchDefault();
		waitForCompletePageLoad();
	}

	public void verifyCreatedFormAndAdminFormGroupActions() {
		searchForms(formTitle);
		Assert.assertTrue(isDisplayed(ProjectFormsTab.lnk_FirstFormTitle));
		waitUntilElementIsDisplayed(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		mouseHoverElement(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
		List<WebElement> popOverElements = findElements(GlobalPageElements.css_firstActionsPopoverContentLinks);
		List<WebElement> popOverDays = findElements(GlobalPageElements.css_firstActionsPopoverDueDaysContents);

		for (int index = 0; index < popOverElements.size(); index++)
			actualActionDaysMap.put(popOverElements.get(index).getText().split(":")[1], popOverDays.get(index).getText().split(" ")[0].trim());

		for (Map.Entry<String, String> entry : actualActionDaysMap.entrySet())
			log.info("Actual map: " + entry.getKey() + "/" + entry.getValue());

		for (Map.Entry<String, String> entry : expectedActionDaysMap.entrySet())
			log.info("Expected map: " + entry.getKey() + "/" + entry.getValue());

		Assert.assertTrue("Expected: " + expectedActionDaysMap.toString() + "\nActual: " + actualActionDaysMap.toString(), compareUnorderedMaps(expectedActionDaysMap, actualActionDaysMap));

	}

	public void clickOnCreateNewFormLink(String newFormLink) {
		clickLinkWithText(newFormLink);
	}

	public void searchFormTypeAndSelectForm(String formType) {
		if (isDisplayed(ProjectFormsTab.frm_createFormIframe))
			waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);

		sendKeys(ProjectFormsTab.txt_PopCreateFormNameSearchFilter, formType);
		collectionDataMap.put("Workflow FormType", formType);
		clickElementAndWait(ProjectFormsTab.ele_PopCreateFormFirstFormName);
	}

	public void searchFormTypeAndSelectForm2(String formType) {
		if (isDisplayed(ProjectFormsTab.frm_createFormIframe))
			waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);

		sendKeys(ProjectFormsTab.txt_PopCreateFormNameSearchFilter, formType);
		sendKeys(ProjectFormsTab.txt_PopCreateFormPorjectNameSearchFilter, CreateEditRoleScripts.projectName);
		clickElementAndWait(ProjectFormsTab.ele_PopCreateFormFirstFormName);
	}

	public void clickOnSaveDraftAndHandlePopup(String saveDraft) {
		clickElement(ProjectFormsTab.btn_BetaViewCreateFormSaveDraft);
		acceptAlertAndWait();
		switchDefault();
		waitForCompletePageLoad();
	}

	public void verifyCreatedFormDraftAndAction(String actionType) {
		searchForms(formTitle);
		Assert.assertTrue(getElementText(ProjectFormsTab.lnk_FirstFormDraftID).contains(draftID));
		waitUntilElementIsDisplayed(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		mouseHoverElement(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
		String popOverElementAction = getElementText(GlobalPageElements.css_firstActionsPopoverContentLinks);
		String popOverDays = getElementText(GlobalPageElements.css_firstActionsPopoverDueDaysContents);
		Assert.assertTrue(popOverElementAction.contains(actionType));
		Assert.assertTrue(popOverElementAction.contains(adminSaveDraftAction));
		Assert.assertTrue(popOverDays.contains(adminSaveDraftDueDate));
	}

	public void enterAccessToUseFormDistributionGroup() throws InterruptedException {

		//if (ResourceHandler.loadProperty("app.view.beta.flag").equalsIgnoreCase("true")) {
			sendKeys(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo, formGroupNameList[1] + epoch);
			waitUntilElementIsDisplayed(By.xpath(".//div[@id='distInputTo']//input[@type='checkbox']//following::span[contains(text(),'" + formGroupNameList[1] + epoch + "')]"));
			clickElementAndWaitForElement(By.xpath(".//div[@id='distInputTo']//span[contains(text(),'" + formGroupNameList[1] + epoch + "')]//preceding::input[@type='checkbox']"), ProjectFormsTab.lnk_BetaViewCreateFormDistributeToCloseButton);
			clickElementAndWait(ProjectFormsTab.lnk_BetaViewCreateFormDistributeToCloseButton);
		/*}
		else {
			sendKeys(ProjectFormsTab.txt_PopCreateFormDistributeTo, formGroupNameList[1] + epoch);
			sendKeys(ProjectFormsTab.txt_PopCreateFormDistributeTo, Keys.ENTER);
		}*/
	}

	public void verifyCreatedFormAndAccessToUseFormGroupActions(String actionType) {
		searchForms(formTitle);
		Assert.assertTrue(isDisplayed(ProjectFormsTab.lnk_FirstFormTitle));
		waitUntilElementIsDisplayed(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		mouseHoverElement(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
		String popOverElementAction = getElementText(GlobalPageElements.css_firstActionsPopoverContentLinks);
		Assert.assertTrue(popOverElementAction.contains(actionType));
		Assert.assertTrue(popOverElementAction.contains(accessToUseAction));
	}

	public void enterNoAccessFormDistributionGroup() throws InterruptedException {
		//if (ResourceHandler.loadProperty("app.view.beta.flag").equalsIgnoreCase("true"))
			sendKeys(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo, formGroupNameList[2] + epoch);
		/*else
			sendKeys(ProjectFormsTab.txt_PopCreateFormDistributeTo, formGroupNameList[2] + epoch);*/
	}

	public void verifyNoAccessFormGroupDeactived() throws InterruptedException {

		//if (ResourceHandler.loadProperty("app.view.beta.flag").equalsIgnoreCase("true")) {
			Assert.assertTrue(isDisplayed(ProjectFormsTab.ele_BetaViewPopCreateFormNoDistributeMatchFoundLabel));
			sendKeys(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo, Keys.ESCAPE);
		/*}
		else {
			Assert.assertTrue(isDisplayed(FilesTab.ele_PopUploadFileListNoMatchesLabel));
			sendKeys(ProjectFormsTab.txt_PopCreateFormDistributeTo, Keys.ESCAPE);
		}*/
	}

	public void clickOnCloseForm() {
		waitForCompletePageLoad();

		//if (ResourceHandler.loadProperty("app.view.beta.flag").equalsIgnoreCase("true")) 
			clickElementAndWait(ProjectFormsTab.btn_BetaViewPopCreateFormCancelButton);
		/*else 
			clickElementAndWait(ProjectFormsTab.btn_PopCreateFormCloseFormButton);*/
		
		switchDefault();
	}

	public void verifyDistributionGroupsDeactivatedOnProjectForms(String formFolder, String formType) throws IOException, InterruptedException {
		clickElementWithText(CreateEditRoleScripts.projectName);
		clickElementWithText(formFolder);
		clickElementWithText(formType);
		clickElementAndWait(ProjectFormsTab.btn_CreateForm);
		clickOnDistributeForm();

		//if (ResourceHandler.loadProperty("app.view.beta.flag").equalsIgnoreCase("true")) {

			for (String groupName : formGroupNameList) {
				sendKeys(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo, groupName + epoch);
				Assert.assertTrue(isDisplayed(ProjectFormsTab.ele_BetaViewPopCreateFormNoDistributeMatchFoundLabel));
			}

		/*}
		else {

			for (String groupName : formGroupNameList) {
				sendKeys(ProjectFormsTab.txt_PopCreateFormDistributeTo, groupName + epoch);
				Assert.assertTrue(isDisplayed(FilesTab.ele_PopUploadFileListNoMatchesLabel));
			}
		}*/
	}

	/******* Common Methods For Distribution Groups *******/

	public void clickOnSelectFilesAndUpload() throws IOException, InterruptedException {
		sysUtils.authenticateRemoteMachine(nodeIP);
		CreateEditRoleScripts.createFile = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
		log.info("file created as: " + CreateEditRoleScripts.createFile);
		List<String> fileList = sysUtils.getFileList(CreateEditRoleScripts.createFile);
		uploadFileUsingKeys(FilesTab.btn_SelectFiles, fileList);
	}

	public void distributionGroupsDetails(String[] groupNameList, String groupType, String admin, String accessToUse, String noAccess) throws InterruptedException {
		groupNameType = groupType;
		accessCounter = 0;
		for (String groupName : groupNameList) {
			clickElementAndWait(ProjectsTab.btn_AddDistributionGroup);
			sendKeys(ProjectsTab.txt_FirstDistributionGroupInput, groupName + epoch);
			selectByVisibleText(ProjectsTab.drp_FirstDistributionGroupTypeDropdown, groupType);
			enterFormType();

			if (groupName.contains(admin)) {
				if (groupType.contains("Documents"))
					assignedActionsToGroup(documentsActionArray);
				else if (groupType.contains("Apps"))
					assignedActionsToGroup(formsActionArray);

				distributionGroupSecurityAccess(accessCounter);
			}
			else if (groupName.contains(accessToUse)) {
				sendKeys(ProjectsTab.txt_FirstDistributionUserInput, System.getProperty("primary.username"));
				sendKeys(ProjectsTab.txt_FirstDistributionUserInput, Keys.ENTER);

				clickElementAndWait(ProjectsTab.btn_DistributionUserDropdownToggleButton);
				selectByVisibleText(ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown, accessToUseAction);
				clickElement(ProjectsTab.ele_DistributionGroupContextMenuCloseButton);

				distributionGroupSecurityAccess(accessCounter);
			}
			else if (groupName.contains(noAccess)) {
				sendKeys(ProjectsTab.txt_FirstDistributionUserInput, System.getProperty("primary.username"));
				sendKeys(ProjectsTab.txt_FirstDistributionUserInput, Keys.ENTER);

				clickElementAndWait(ProjectsTab.btn_DistributionUserDropdownToggleButton);
				selectByVisibleText(ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown, noAccessAction);
				selectByVisibleText(ProjectsTab.sel_DistributionGroupContextMenuActionDaysDropdown, noAccessDueDate);
				clickElement(ProjectsTab.ele_DistributionGroupContextMenuCloseButton);

				distributionGroupSecurityAccess(accessCounter);
			}
			accessCounter++;
		}
	}

	public void enterFormType() throws InterruptedException {
		if (isDisplayed(ProjectsTab.txt_FirstFormTypeDistributionInput)) {
			sendKeys(ProjectsTab.txt_FirstFormTypeDistributionInput, formTemplateName);
			sendKeys(ProjectsTab.txt_FirstFormTypeDistributionInput, Keys.ENTER);
		}
		else {
			log.info("Document Distribution Group Selected");
		}
	}

	public void assignedActionsToGroup(String[] actionsList) throws InterruptedException {
		j = 0;
		for (index = 1; index <= actionsList.length; index++) {
			sendKeys(ProjectsTab.txt_FirstDistributionUserInput, System.getProperty("primary.username"));
			sendKeys(ProjectsTab.txt_FirstDistributionUserInput, Keys.ENTER);

			clickElementAndWait(By.xpath(".//div[@class='dist-content']//div[1]//div[@class='distList']//ul[@class='select2-choices']//li[" + index + "]//button"));
			selectByVisibleText(ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown, actionsList[j]);
			selectByIndex(ProjectsTab.sel_DistributionGroupContextMenuActionDaysDropdown, index);
			if (!actionsList[j].equalsIgnoreCase(adminSaveDraftAction))
				expectedActionDaysMap.put(actionsList[j], Integer.toString(index));

			if (new Select(findElement(ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown)).getFirstSelectedOption().getText().contains(adminSaveDraftAction)) {
				adminSaveDraftDueDate = new Select(findElement(ProjectsTab.sel_DistributionGroupContextMenuActionDaysDropdown)).getFirstSelectedOption().getText();
				log.info("adminSaveDraftDueDate :" + adminSaveDraftDueDate);
			}

			clickElement(ProjectsTab.ele_DistributionGroupContextMenuCloseButton);
			j++;
		}
	}

	public void distributionGroupSecurityAccess(int accessCounter) {
		List<WebElement> securityList = findElements(ProjectsTab.css_DistributionGroupSecurityLatestGroupUsersList);

		for (WebElement e : securityList) {
			securityLevel = e.getText();
			clickElementAndWait(e);
			if (securityLevel.contains(AdoddleCommonStringPool.SECURITY_ROLE_DEFAULT) || securityLevel.contains(AdoddleCommonStringPool.SECURITY_ROLE_WORKSPACEADMIN))
				clickElement(By.xpath(".//ul[not(contains(@style,'display: none'))]//span[text()='" + groupAccessList[0] + "']"));
			else
				clickElement(By.xpath(".//ul[not(contains(@style,'display: none'))]//span[text()='" + groupAccessList[accessCounter] + "']"));
		}
	}

	public void verifyDistributionGroups(String[] groupNameList, String admin, String accessToUse, String noAccess) throws InterruptedException {
		for (String groupName : groupNameList) {
			clear(ProjectsTab.txt_DistributionGroupSearchFilter);
			sendKeys(ProjectsTab.txt_DistributionGroupSearchFilter, groupName + epoch);

			if ((groupName + epoch).contains(admin))
				Assert.assertTrue(findElement(ProjectsTab.txt_FirstDistributedGroupInput).getAttribute("value").contains(groupName + epoch));
			else if ((groupName + epoch).contains(accessToUse))
				Assert.assertTrue(findElement(ProjectsTab.txt_FirstDisabledDistributedGroup).getAttribute("value").contains(groupName + epoch));
			else if ((groupName + epoch).contains(noAccess))
				Assert.assertTrue(!isDisplayed(ProjectsTab.txt_FirstDistributedGroupInput));
			else
				Assert.assertTrue(false);
		}
	}

	public void deactivateGroups(String[] groupNameList) throws InterruptedException {
		clickOnDistributionGroups(CreateEditRoleScripts.projectName, contextPopEdit, distributionGroup);
		for (String groupName : groupNameList) {
			clear(ProjectsTab.txt_DistributionGroupSearchFilter);
			sendKeys(ProjectsTab.txt_DistributionGroupSearchFilter, groupName + epoch);

			clickElementAndWait(ProjectsTab.lnk_FirstDistributionGroupRemoveAdminRoleLink);
			clickElementAndWaitForElement(ProjectsTab.lnk_FirstDistributionGroupDefaultSecurity, ProjectsTab.opt_PopFileStatusNewStatusRoleNoAccess);
			clickElementAndWait(ProjectsTab.opt_PopFileStatusNewStatusRoleNoAccess);

			clickElementAndWait(ProjectsTab.lnk_FirstDistributionGroupFirstRemovableSecurityUser);
			sendKeys(ProjectsTab.txt_FirstDistributionGroupSecurityUserInput, ResourceHandler.loadProperty("test.user.tc.bloggs.id"));
			sendKeys(ProjectsTab.txt_FirstDistributionGroupSecurityUserInput, Keys.ENTER);

			clickElementAndWait(ProjectsTab.ele_FirstDistributionGroupFirstSecurityUser);
			clickElement(ProjectsTab.opt_PopFileStatusNewStatusRoleAdmin);
		}

		clickOnSaveAll();
	}

	public void verifyGroupsDeactivated(String[] groupNameList) throws InterruptedException {
		clickOnDistributionGroups(CreateEditRoleScripts.projectName, contextPopEdit, distributionGroup);
		for (String groupName : groupNameList) {
			clear(ProjectsTab.txt_DistributionGroupSearchFilter);
			sendKeys(ProjectsTab.txt_DistributionGroupSearchFilter, groupName + epoch);
			Assert.assertNotEquals(true, isDisplayed(ProjectsTab.txt_FirstDistributedGroupInput));
		}
		clickElementAndWait(ProjectsTab.btn_ManageAppSettingsCancelButton);
	}
}