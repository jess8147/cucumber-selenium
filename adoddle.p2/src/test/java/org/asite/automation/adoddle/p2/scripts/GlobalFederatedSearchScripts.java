package org.asite.automation.adoddle.p2.scripts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.asite.automation.CommonLocators.AdoddleDashboardLocators.DashboardTab;
import org.asite.automation.CommonLocators.AdoddleDiscussionsLocators.DiscussionsTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleModelsLocators.ModelsTab;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.lib.AdoddleCommonAppMethods.EnumList.AsiteMenu;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.resources.AdoddleScenarioMarkers;
import org.asite.automation.common.utils.JavaUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GlobalFederatedSearchScripts extends AdoddleCommonAppMethods
{
	final private List<String> contentSearchFilesList = new ArrayList<String>();
	final private List<String> contentSearchFormsList = new ArrayList<String>();
	final private List<String> contentSearchDiscussionsList = new ArrayList<String>();
	final private List<String> contentSearchModelsList = new ArrayList<String>();
	private List<String>	multiFileUploadList	= null, multiFormCreatedList = null;
	private String contentSearchForm = null, contentSearchDiscussion = null, contentSearchDiscussionFile = null, contentSearchModel = null;
	private boolean projectListFlag = false;
	
	public void getContentSearchTextTestData()
	{
		contentSearchFilesList.add("FederatedSearchExcelFile.xlsx");
		contentSearchFilesList.add("FederatedSearchPDFFile.pdf");
		contentSearchFilesList.add("FederatedSearchTextFile.txt");
		contentSearchFilesList.add("FederatedSearchWordFile.docx");
		contentSearchFilesList.add("FederatedSearchDocTitleFile.pdf");
		contentSearchFilesList.add("FederatedSearchRevisionNotesFile.pdf");
		log.info("contentSearchFilesList :"+contentSearchFilesList);
		collectionDataMap.put("contentSearchFilesList", contentSearchFilesList.toString());

		contentSearchFormsList.add("FederatedSearch_Description_FormTitle");
		contentSearchFormsList.add("FederatedSearch_InterRef_FormTitle");
		contentSearchFormsList.add("FederatedSearch_CustomField_FormTitle");
		log.info("contentSearchFormsList :"+contentSearchFormsList);
		collectionDataMap.put("contentSearchFormsList", contentSearchFormsList.toString());

		contentSearchDiscussionsList.add("FederatedSearch_Description_CommentTitle");
		contentSearchDiscussionsList.add("FederatedSearch_ReplyDescription_CommentTitle");
		log.info("contentSearchDiscussionsList :"+contentSearchDiscussionsList);
		collectionDataMap.put("contentSearchDiscussionsList", contentSearchDiscussionsList.toString());

		contentSearchModelsList.add("FederatedSearch_Description_ModelTitle");
		log.info("contentSearchModelsList :"+contentSearchModelsList);
		collectionDataMap.put("contentSearchModelsList", contentSearchModelsList.toString());
	}

	public void globallySearchContentSearchText()
	{
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		sendKeys(GlobalPageElements.txt_GlobalSearchInput, ResourceHandler.loadProperty("content.search.text.string"));
		log.info("Searching Content Search Text: " + ResourceHandler.loadProperty("content.search.text.string"));
		collectionDataMap.put("Content Search Text", ResourceHandler.loadProperty("content.search.text.string"));
		clickElementAndWaitForInvisibilityOfElement(GlobalPageElements.btn_GlobalSearchButton, GlobalPageElements.ele_overLayProcess);
		waitForCompletePageLoad();
	}

	public void searchContentSearchTextOnActiveTab(String activeTab)
	{
		if(activeTab.contains(AdoddleCommonStringPool.TAB_FILES))
			searchFiles(ResourceHandler.loadProperty("content.search.text.string"));
		else if(activeTab.contains(AdoddleCommonStringPool.TAB_DISCUSSIONS))
			searchDiscussions(ResourceHandler.loadProperty("content.search.text.string"));
		else if(activeTab.contains(AdoddleCommonStringPool.TAB_PROJECT_FORMS))
			searchForms(ResourceHandler.loadProperty("content.search.text.string"));
		else if(activeTab.contains(AdoddleCommonStringPool.TAB_MODELS))
			searchModels(ResourceHandler.loadProperty("content.search.text.string"));
	}

	public void verifyContentSearchTextData(String tabWiseData)
	{
		List<String> contentSearchedDataList = new ArrayList<String>();

		if(tabWiseData.contains(AdoddleCommonStringPool.TAB_FILES)) {

			if(isDisplayed(GlobalPageElements.lnk_GlobalSearchFilesLHTab))
				clickElementAndWait(GlobalPageElements.lnk_GlobalSearchFilesLHTab);

			for (WebElement fileName : findElements(FilesTab.css_NumberOfFiles))
				contentSearchedDataList.add(fileName.getText());

			log.info("contentSearchedDataList :"+contentSearchedDataList);
			
			
			if(multiFileUploadList != null) {
				if(!contentSearchFilesList.contains(multiFileUploadList)) {
					contentSearchFilesList.addAll(multiFileUploadList);
				} else
					log.info(": Files already added in list :");
			}
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(contentSearchedDataList.toString(), contentSearchFilesList.toString()), contentSearchFilesList.containsAll(contentSearchedDataList));

		} else if(tabWiseData.contains("Comments")) {

			if(isDisplayed(GlobalPageElements.lnk_GlobalSearchDiscussionsLHTab))
				clickElementAndWait(GlobalPageElements.lnk_GlobalSearchDiscussionsLHTab);

			for (WebElement commentTitle : findElements(DiscussionsTab.css_DiscussionsTabDiscussionTitleList))
				contentSearchedDataList.add(commentTitle.getText());

			log.info("contentSearchedDataList :"+contentSearchedDataList);

			if(contentSearchDiscussion != null) {
				if(!contentSearchDiscussionsList.contains(contentSearchDiscussion))
					contentSearchDiscussionsList.add(contentSearchDiscussion);
				else
					log.info(": Comment already added in list :");
			}
			Assert.assertTrue(contentSearchDiscussionsList.containsAll(contentSearchedDataList));

		} else if(tabWiseData.contains(AdoddleCommonStringPool.TAB_PROJECT_FORMS)) {

			if(isDisplayed(GlobalPageElements.lnk_GlobalSearchAppsLHTab))
				clickElementAndWait(GlobalPageElements.lnk_GlobalSearchAppsLHTab);

			List<WebElement> formList;
			if(isDisplayed(ProjectFormsTab.txt_FormListingFormSearchInput))
				formList = findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList);
			else
				formList = findElements(GlobalPageElements.css_GlobalSearchAppsTitleList);

			for (WebElement formTitle : formList)
				contentSearchedDataList.add(formTitle.getText());

			log.info("contentSearchedDataList :"+contentSearchedDataList);

			if(multiFormCreatedList != null) {
				if(!contentSearchFormsList.contains(multiFormCreatedList))
					contentSearchFormsList.addAll(multiFormCreatedList);
				else
					log.info(": Form already added in list :");
			}
			AutomationAssert.verifyTrue(contentSearchFormsList.toString()+" != "+contentSearchedDataList.toString(), contentSearchFormsList.containsAll(contentSearchedDataList));

		} else if(tabWiseData.contains(AdoddleCommonStringPool.TAB_MODELS)) {

			if(isDisplayed(GlobalPageElements.lnk_GlobalSearchModelsLHTab))
				clickElementAndWait(GlobalPageElements.lnk_GlobalSearchModelsLHTab);

			List<WebElement> modelList;
			if(isDisplayed(ModelsTab.txt_filterModelInput)) {
				setListingStyle("ListView");
				modelList = findElements(ModelsTab.css_ModelsTabModelNameList);
			} else
				modelList = findElements(GlobalPageElements.css_GlobalSearchModelsTitleList);

			for (WebElement modelTitle : modelList)
				contentSearchedDataList.add(modelTitle.getText());
			
			if(contentSearchModel != null) {
				if(!contentSearchModelsList.contains(contentSearchModel))
					contentSearchModelsList.add(contentSearchModel);
				else
					log.info(": Model already added in list :");
			}
			log.info("contentSearchedDataList :"+contentSearchedDataList);
			Assert.assertTrue(contentSearchModelsList.containsAll(contentSearchedDataList));
		}
	}

	public void uploadMultiFilesForContentSearchTextData(String user, String actionList)
	{
		String searchedFile = resourceUtils.getTestDataFilePath() + epoch;
		log.info("searchedFile : " + searchedFile);
		
		multiFileUploadList = new ArrayList<String>();
		List<String> fileList = new ArrayList<String>();
		
		sysUtils.authenticateRemoteMachine(nodeIP);
		int i=0;
		for (String fileName : Arrays.asList("file1","file2", "file3")) {
			
			if(i != 0)
				fileName = sysUtils.createRemotePdfFile(nodeIP + searchedFile + i + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
			else
				fileName = createPdfFile(nodeIP + searchedFile + i + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
			fileList.add(fileName);
			i++;
		}
		uploadFileUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, fileList);
		
		for (String fileName : fileList)
			multiFileUploadList.add(strUtils.extractFileNameString(fileName));
		collectionDataMap.put("FileUploadList", multiFileUploadList.toString());
		
		clickElementAndWait(FilesTab.chk_PopUploadFilesBulkApply);
		clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
		sendKeys(FilesTab.txt_PopUploadHeaderRevInput, "1");
		selectByIndex(FilesTab.drp_PopUploadHeaderPurposeOfIssue, 1);
		selectByIndex(FilesTab.drp_PopUploadHeaderStatus, 1);
		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
		clickElementAndWait(FilesTab.btn_PopUploadApplytoAll);
		
		int j=1;
		for (WebElement docRef : findElements(By.xpath(".//tbody//tr//input[@elementtype='docRef']"))) {
			String docRefVal = docRef.getAttribute("value");
			if(docRefVal.substring(docRefVal.length()-1).equalsIgnoreCase("1")) {
				clear(By.xpath(".//tbody//tr["+j+"]//input[@elementtype='doctitle']"));
				sendKeys(By.xpath(".//tbody//tr["+j+"]//input[@elementtype='doctitle']"), ResourceHandler.loadProperty("content.search.text.string"));
			} else if(docRefVal.substring(docRefVal.length()-1).equalsIgnoreCase("2")) {
				clear(By.xpath(".//tbody//tr["+j+"]//input[@elementtype='Rev Notes']"));
				sendKeys(By.xpath(".//tbody//tr["+j+"]//input[@elementtype='Rev Notes']"), ResourceHandler.loadProperty("content.search.text.string"));
			} else
				log.info(": This is Content Search Text File :");
			j++;
		}
		distributeFileToSingleUserWithMultipleActions(user, actionList);
		
		clickElementAndWait(FilesTab.btn_PopUploadFileUpload);
		waitForCompletePageLoad();
		waitUntilElementIsClickable(FilesTab.lnk_FileName);
	}
	
	public void distributeFileToSingleUserWithMultipleActions(String user, String actionList)
	{
		clickElementAndWaitForElement(FilesTab.btn_PopUploadFileDistributeFilesButton, FilesTab.txt_PopUploadFileDistributeTo);
		
		int i = 1;
		for (String action : actionList.split(",")) {
			
			sendKeys(FilesTab.txt_PopUploadFileDistributeTo, user);
			sendKeys(FilesTab.txt_PopUploadFileDistributeTo, Keys.ENTER);
			clickElementAndWaitForElement(By.xpath(".//div[contains(@id,'s2id_inptDistTo')]//ul[@class='select2-choices']//li[" + i + "]//button[@class='btn dropdown-toggle']"), ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown);
			selectByVisibleText(ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown, action.trim());
			selectDateFromCalendar();
			i++;
		}
	}

	private void enterMendatoryAttributesAndClickOnUpload()
	{
		clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
		sendKeys(FilesTab.txt_RevFile1, "1");
		selectByIndex(FilesTab.drp_PurposeOfIssueFile1, 1);
		selectByIndex(FilesTab.drp_StatusFile1, 1);
		clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_PopUploadFileUpload, GlobalPageElements.pop_PopUpElement);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitForCompletePageLoad();
	}

	public void uploadFileForContentSearchDiscussion()
	{
		sysUtils.authenticateRemoteMachine(nodeIP);
		String contentSearchFilePath = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
		log.info("content Search Discussion File :" + contentSearchFilePath);
		collectionDataMap.put("content Search Discussion File", contentSearchFilePath);
		List<String> fileList = sysUtils.getFileList(contentSearchFilePath);
		uploadFileUsingKeys(FilesTab.btn_SelectFiles, fileList);
		contentSearchDiscussionFile = strUtils.extractFileNameString(contentSearchFilePath);

		enterMendatoryAttributesAndClickOnUpload();
	}

	public void createContentSearchTextDataComment(String user)
	{
		searchFiles(contentSearchDiscussionFile);
		contextClick(FilesTab.lnk_FileName);
		clickContextMenuOption(AdoddleCommonStringPool.OPTION_NEW, AdoddleCommonStringPool.OPTION_START_A_DISCUSSION);

		waitUntilElementIsDisplayed(FilesTab.pop_StartNewDiscussion);
		verifyElementText(FilesTab.pop_StartNewDiscussionHeader, AdoddleCommonStringPool.OPTION_NEW);

		sendKeys(FilesTab.txt_NewDiscussionToUserField, user);
		sendKeys(FilesTab.txt_NewDiscussionToUserField, Keys.ENTER);

		contentSearchDiscussion = dateUtils.getEpoch();
		log.info("content Search Discussion :" + contentSearchDiscussion);
		collectionDataMap.put("content Search Discussion", contentSearchDiscussion);

		sendKeys(FilesTab.txt_NewDiscussionTitleInput, contentSearchDiscussion);
		sendKeys(FilesTab.txt_NewDiscussionDescInput, ResourceHandler.loadProperty("content.search.text.string"));
		clickElementAndWait(FilesTab.btn_NewDiscussionSubmit);
	}
	
	public void createContentSearchTextDataForm(String stringContentType, String user, String actionList)
	{
		if(contentSearchForm == null)
			contentSearchForm = dateUtils.getEpoch();
		if(multiFormCreatedList == null)
			multiFormCreatedList = new ArrayList<String>();
		
		clickElementAndWait(ProjectFormsTab.btn_CreateForm);
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		
		distributeSingleUserWithMultipleActions(user, actionList);
		waitForCompletePageLoad();
		
		if(stringContentType.contains("Description")) {
			
			multiFormCreatedList.add(contentSearchForm+"0");
			
			clear(ProjectFormsTab.txt_CreateRFIFormSubject);
			sendKeys(ProjectFormsTab.txt_CreateRFIFormSubject, contentSearchForm+"0");
			sendKeys(ProjectFormsTab.txt_CreateRFIFormSubject, Keys.TAB);
			
			sendKeys(ProjectFormsTab.txt_CreateRFIFormDescription, ResourceHandler.loadProperty("content.search.text.string"));
			sendKeys(ProjectFormsTab.txt_CreateRFIFormSubject, Keys.TAB);
			waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		}
		else if(stringContentType.contains("Internal Ref") || stringContentType.contains("Custom Field")) {
			
			waitUntilElementIsDisplayed(ProjectFormsTab.txt_CreateFormTitle);
			clear(ProjectFormsTab.txt_CreateFormTitle);
			
			if(stringContentType.contains("Internal Ref")) {
				sendKeys(ProjectFormsTab.txt_CreateFormTitle, contentSearchForm+"1");
				sendKeys(By.xpath(".//input[contains(@id,'USERREF')][@title='Enter Your Internal Reference No.']"), ResourceHandler.loadProperty("content.search.text.string"));
				multiFormCreatedList.add(contentSearchForm+"1");
			} else {
				FormIncompleteActionsScripts.formTitle = contentSearchForm+"2";
				sendKeys(ProjectFormsTab.txt_CreateFormTitle, FormIncompleteActionsScripts.formTitle);
				multiFormCreatedList.add(FormIncompleteActionsScripts.formTitle);
			}
			clear(ProjectFormsTab.txt_PopCreateFormGroupCode);
			waitForCompletePageLoad();

			executeJScript(AdoddleCommonJQueries.betaViewCreateFormScrollMaxDownQuery);
			waitUntilElementIsClickable(ProjectFormsTab.img_BetaViewCreateFormCalendar);
			clickElementAndWait(ProjectFormsTab.img_BetaViewCreateFormCalendar);
			clickElementAndWait(ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
		}
		clickElementAndWait(ProjectFormsTab.btn_CreateFormSendButton);
		switchDefault();
		
		if(stringContentType.contains("Custom Field")) {
			
			waitForCompletePageLoad();
			waitUntilElementIsClickable(ProjectFormsTab.lnk_FormListingFirstFormTitle);
			
			searchForms(FormIncompleteActionsScripts.formTitle);
			new FormsTabCreateWorkflowScripts().contextClickAndSelectContextOption(AdoddleCommonStringPool.OPTION_NEW, "Reply All");
			new RecentFormsScripts().switchToSecondWindow();
			
			waitForCompletePageLoad();
			waitAndSwitchIframe(ProjectFormsTab.frm_BetaViewReplyFormIframe);
			
			waitUntilElementIsDisplayed(ProjectFormsTab.css_BetaViewRespondFormPrePopulatedUserList);
			clickElementAndWait(By.xpath(".//div[@id='distInputTo']//dist-select//div[@class='dropdown-toggle']/ul//li//span[1][text()]//following-sibling::span//i[contains(@class,'close')]"));
			waitUntilElementIsInvisible(By.xpath(".//div[@id='distInputTo']//dist-select//div[@class='dropdown-toggle']/ul//li//span[1][text()]//following-sibling::span//i[contains(@class,'close')]"));
			
			sendKeys(ProjectFormsTab.txt_RespondFormRespondMessageInput, ResourceHandler.loadProperty("content.search.text.string"));
			clear(DashboardTab.txt_RespondFormGroupCodeInput);
			
			waitUntilElementIsDisplayed(ProjectFormsTab.btn_CreateFormSendButton);
			clickAndHoldElement(ProjectFormsTab.btn_CreateFormSendButton);
			try {waitUtils.waitInterval(1);} catch(Throwable t) {log.info(": WaitInterval Failed :");}
			releaseElementClick(ProjectFormsTab.btn_CreateFormSendButton);
			waitForCompletePageLoad();
			switchDefault();
			waitForCompletePageLoad();
			waitUntilElementContainsValue(ProjectFormsTab.txt_RespondFormRespondMessageInput, ResourceHandler.loadProperty("content.search.text.string"));
			new FormIncompleteActionsScripts().closeSecondWindow();
		}
	}
	
	public void distributeSingleUserWithMultipleActions(String user, String actionList)
	{
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(ProjectFormsTab.btn_CreateFormSendButton);
		
		if(!isDisplayed(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo)) {
			clickElementAndWait(ProjectFormsTab.btn_BetaViewPopCreateFormDistributeFormButton);
			waitUntilElementIsDisplayed(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo);
		}
		sendKeys(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo, user);
		waitUntilElementIsDisplayed(By.xpath(".//div[@id='distInputTo']//input[@type='checkbox']//following::span[contains(text(),'" + user + "')]"));
		
		int loop = 0;
		while(findElements(By.xpath(".//div[@id='distInputTo']//ul[@class='user-list']//li[not(@ng-if)]//a[@title='Add Clone']")).size() != actionList.split(",").length) {
			clickElementAndWait(By.xpath(".//div[@id='distInputTo']//ul[@class='user-list']//li[not(@ng-if)][1]//a[@title='Add Clone']"));
			if(loop > 10)
				break;
			loop++;
		}
		
		int count = 0;
		for (WebElement distributionRow : findElements(By.xpath(".//div[@id='distInputTo']//ul[@class='user-list']//li[not(@ng-if)]"))) {
			
			if(!isSelected(distributionRow.findElement(By.xpath(".//input[@type='checkbox']"))))
				clickElementAndWait(distributionRow.findElement(By.xpath(".//input[@type='checkbox']")));
			waitUntilElementIsDisplayed(distributionRow.findElement(By.xpath(".//select")));
			selectByVisibleText(distributionRow.findElement(By.xpath(".//select")), actionList.split(",")[count].trim());
			count++;
		}
		clickElementAndWait(ProjectFormsTab.lnk_BetaViewCreateFormDistributeToCloseButton);
	}

	
	
	/*public void createContentSearchTextDataForm()
	{
		clickElementAndWait(ProjectFormsTab.btn_CreateForm);
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		sendKeys(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo, System.getProperty("secondary.username"));
		waitUntilElementIsDisplayed(By.xpath(".//div[@id='distInputTo']//input[@type='checkbox']//following::span[contains(text(),'" + System.getProperty("secondary.username") + "')]"));
		clickElementAndWait(By.xpath(".//div[@id='distInputTo']//span[contains(text(),'" + System.getProperty("secondary.username") + "')]//preceding::input[@type='checkbox']"));
		selectByVisibleText(ProjectFormsTab.drp_BetaViewPopCreateFormSelectedUserActionDropdown, AdoddleCommonStringPool.ACTION_FOR_INFORMATION);
		clickElementAndWait(ProjectFormsTab.lnk_BetaViewCreateFormDistributeToCloseButton);

		contentSearchForm = dateUtils.getEpoch();
		log.info("contentSearchForm :" + contentSearchForm);

		waitForCompletePageLoad();
		sendKeys(ProjectFormsTab.txt_CreateRFIFormSubject, contentSearchForm);
		sendKeys(ProjectFormsTab.txt_CreateRFIFormSubject, Keys.TAB);
		sendKeys(ProjectFormsTab.txt_CreateRFIFormDescription, ResourceHandler.loadProperty("content.search.text.string"));
		sendKeys(ProjectFormsTab.txt_CreateRFIFormSubject, Keys.TAB);
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		clickElementAndWait(ProjectFormsTab.btn_CreateFormSendButton);
		switchDefault();
		reloadPage();
	}*/

	private String createPdfFile(String filePath, String nodeIP) {
		log.info("Creating PDF file as: "+ filePath);
		sysUtils.createDirectory(filePath.substring(0, filePath.lastIndexOf("\\")));
		try {
			OutputStream file = new FileOutputStream(new File(filePath));
			Document document = new Document();
			PdfWriter.getInstance(document, file);
			document.open();

			document.add(new Paragraph(ResourceHandler.loadProperty("content.search.text.string")));
			document.add(new Paragraph(dateUtils.getEpoch()));
			Thread.sleep(1000);
			document.close();
			file.close();
		}
		catch (Exception e) {
			log.info(e.getLocalizedMessage());
		}
		sysUtils.waitUntilFileExists(new File(filePath));
		return filePath;
	}
	
	public void searchProjectAndAddAccessToUser(String fSearchUser, String distributeUser)
	{
		log.info("clonedProject : "+clonedProject);
		waitForCompletePageLoad();
		searchProjects(clonedProject);
		contextClickWithLink(clonedProject);
		clickContextMenuOption(AdoddleCommonStringPool.OPTION_EDIT, AdoddleCommonStringPool.ROLE_USER_ACCESS);
		
		sendKeys(ProjectsTab.txt_CreateRolesSearchFilter, AdoddleCommonStringPool.SECURITY_ROLE_WORKSPACEADMIN);
		Assert.assertTrue(findElement(ProjectsTab.txt_PopManageRolesFirstFilteredRoleInput).getAttribute("value").contains(AdoddleCommonStringPool.SECURITY_ROLE_WORKSPACEADMIN));
		
		for (String user : Arrays.asList(fSearchUser,distributeUser)) {
			sendKeys(ProjectsTab.txt_PopManageRolesFirstAssignUserInput, user);
			sendKeys(ProjectsTab.txt_PopManageRolesFirstAssignUserInput, Keys.ENTER);
		}
		clickElementAndWait(ProjectsTab.btn_ManageRolesTabSaveButton);
		waitUntilElementIsClickable(ProjectsTab.btn_ManageUserDetailsPopupCancelButton);
		clickElementAndWait(ProjectsTab.btn_ManageUserDetailsPopupCancelButton);
	}
	
	public void clickOnProjectAndFolderAndAddFiles(String folderName)
	{
		clickElementWithText(clonedProject);
		clickElementWithText(folderName);
		clickElementAndWait(FilesTab.btn_DocListingAddFiles);
	}
	
	public void selectFormApp(String appGroup, String appType)
	{
		new SingleMultipleFormsStatusChangeScripts().selectFormApp(clonedProject, appGroup, appType);
	}
	
	public void deactivateAllClonedProjects(String parentProject)
	{
		navigateTabByText(AsiteMenu.Projects.toString());
		
		List<String> projectnameList = new ArrayList<String>();
		if(findElements(ProjectsTab.css_NumberOfProjects).size() > 0) {
			
			if(findElements(ProjectsTab.css_NumberOfProjects).size() > 1) {
				projectListFlag = true;
				for (WebElement testDataClonedProject : findElements(ProjectsTab.css_NumberOfProjects)) {
					if(!testDataClonedProject.getText().equalsIgnoreCase(parentProject))
						projectnameList.add(testDataClonedProject.getText());
				}
			} else {
				AdoddleScenarioMarkers.federatedSearchStatusFlag = true;
				log.info(": TestData already cleanup :");
			}
			
		} else
			Assert.assertTrue(""+parentProject+" Missing : ",false);
		
		if(projectListFlag) {
			
			for (String testClonedProject : projectnameList) {
				breakInheritanceAndClosedClonedProject(testClonedProject);
			}
			AdoddleScenarioMarkers.federatedSearchStatusFlag = true;
		} else {
			AdoddleScenarioMarkers.federatedSearchStatusFlag = true;
			log.info(": TestData already cleanup :");
		}
	}
	
	public void breakInheritanceAndClosedClonedProject(String project)
	{
		String inheritanceJQuery = "$('#inheritanceTable').find('input[name=\"chkWorkspaceObjectInherit\"]:checked').not(':disabled').click()";
		String countJQuery = "return $('#inheritanceTable').find('input[name=\"chkWorkspaceObjectInherit\"]:checked').not(':disabled').length";
		searchProjects(project);
		contextClick(ProjectsTab.lnk_ProjectsListFirstProject);
		mouseHoverAndClickElement(ProjectsTab.opt_ProjectContextClickEdit, ProjectsTab.opt_ProjectContextClickEditProject);
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(ProjectsTab.drp_PopEditProjectStatus);
		selectByVisibleText(ProjectsTab.drp_PopEditProjectStatus, AdoddleCommonStringPool.STATUS_CLOSED);
		
		clickElementAndWait(ProjectsTab.lnk_PopEditProjectInheritanceOption);
		int counter = 0;
		while (!((JavascriptExecutor) getWebDriver()).executeScript(countJQuery).toString().equalsIgnoreCase("0")) {
			System.out.println("Number of Inheritance checks: " + ((JavascriptExecutor) getWebDriver()).executeScript(countJQuery));
			executeJScript(inheritanceJQuery);
			counter++;
			if (counter > 10)
				break;
		}
		clickElementAndWaitForInvisibilityOfElement(ProjectsTab.btn_PopEditProjectEdit, ProjectsTab.btn_PopEditProjectEdit);
		waitForCompletePageLoad();
	}
	
	
	
	/******* Action Performed *******/
	
	public void clickOnGlobalSearchLhTab(String lhTab)
	{
		if(findElements(By.xpath(".//a[contains(@id,'sidenav-search')][not(contains(@id,'adoddle'))]")).size() != 1) {
			
			if(lhTab.equalsIgnoreCase(AdoddleCommonStringPool.TAB_FILES)) {
				clickElementAndWait(GlobalPageElements.lnk_GlobalSearchDiscussionsLHTab);
				clickElementAndWait(GlobalPageElements.lnk_GlobalSearchFilesLHTab);
			}
			else if(lhTab.equalsIgnoreCase(AdoddleCommonStringPool.TAB_DISCUSSIONS)) {
				clickElementAndWait(GlobalPageElements.lnk_GlobalSearchFilesLHTab);
				clickElementAndWait(GlobalPageElements.lnk_GlobalSearchDiscussionsLHTab);
			}
			else if(lhTab.equalsIgnoreCase(AdoddleCommonStringPool.TAB_PROJECT_FORMS)) {
				clickElementAndWait(GlobalPageElements.lnk_GlobalSearchFilesLHTab);
				clickElementAndWait(GlobalPageElements.lnk_GlobalSearchAppsLHTab);
			}
			else if(lhTab.equalsIgnoreCase(AdoddleCommonStringPool.TAB_MODELS)) {
				clickElementAndWait(GlobalPageElements.lnk_GlobalSearchFilesLHTab);
				clickElementAndWait(GlobalPageElements.lnk_GlobalSearchModelsLHTab);
			}
		}
		else {
			
			if(lhTab.equalsIgnoreCase(AdoddleCommonStringPool.TAB_FILES))
				clickElementAndWait(GlobalPageElements.lnk_GlobalSearchFilesLHTab);
			else if(lhTab.equalsIgnoreCase(AdoddleCommonStringPool.TAB_DISCUSSIONS))
				clickElementAndWait(GlobalPageElements.lnk_GlobalSearchDiscussionsLHTab);
			else if(lhTab.equalsIgnoreCase(AdoddleCommonStringPool.TAB_PROJECT_FORMS))
				clickElementAndWait(GlobalPageElements.lnk_GlobalSearchAppsLHTab);
			else if(lhTab.equalsIgnoreCase(AdoddleCommonStringPool.TAB_MODELS))
				clickElementAndWait(GlobalPageElements.lnk_GlobalSearchModelsLHTab);
		}
	}
	
	public void verifyDistributedFilesWithActions(String actionList)
	{
		waitForCompletePageLoad();
		Assert.assertTrue((findElements(FilesTab.css_NumberOfFiles).size()) == (multiFileUploadList.size()));
		
		int actionIndex=0;
		for (WebElement fileName : findElements(FilesTab.css_NumberOfFiles)) {
			Assert.assertTrue(multiFileUploadList.contains(fileName.getText()));
			verifyActionsAssigned(actionIndex, actionList);
			actionIndex++;
		}
	}
	
	public void verifyDistributedFormsWithActions(String actionList)
	{
		waitForCompletePageLoad();
		Assert.assertTrue((findElements(GlobalPageElements.css_GlobalSearchAppsTitleList).size()) == (multiFormCreatedList.size()));
		
		int actionIndex=0;
		for (WebElement formTitle : findElements(GlobalPageElements.css_GlobalSearchAppsTitleList)) {
			Assert.assertTrue(multiFormCreatedList.contains(formTitle.getText()));
			verifyActionsAssigned(actionIndex, actionList);
			actionIndex++;
		}
	}
	
	public void verifyActionsAssigned(int actionIndex, String action)
	{
		List<String> actionList1 = new ArrayList<String>();
		List<String> actionList2 = new ArrayList<String>();

		if(findElements(By.xpath(".//a[contains(@id,'sidenav-search')][not(contains(@id,'adoddle'))]")).size() != 1) {
			waitUntilElementIsDisplayed(By.xpath(".//div[contains(@style,'display: block')]//div[@index='"+actionIndex+"']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
			mouseHover(By.xpath(".//div[contains(@style,'display: block')]//div[@index='"+actionIndex+"']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
		} else {
			waitUntilElementIsDisplayed(By.xpath(".//div[@index='"+actionIndex+"']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
			mouseHover(By.xpath(".//div[@index='"+actionIndex+"']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
		}
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);

		for (WebElement actualAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
			if (actualAction.getText().contains(":"))
				actionList1.add(actualAction.getText().split(":")[1].trim());
			else
				actionList1.add(actualAction.getText().trim());
		}
		for (String expectedAction : action.split(","))
			actionList2.add(expectedAction.trim());
		log.info("actual action List : " + actionList1);
		log.info("expected action List : " + actionList2);
		Assert.assertTrue(actionList1.containsAll(actionList2));
	}
	
	public void verifyActionCompleted(int actionIndex, String action)
	{
		List<String> actualActionList = new ArrayList<String>();
		List<String> actualIncompleteActionList = new ArrayList<String>();
		List<String> expectedActionList = new ArrayList<String>();

		for (String expectedAction : action.split(","))
			expectedActionList.add(expectedAction);
		waitForCompletePageLoad();
		
		if(findElements(By.xpath(".//a[contains(@id,'sidenav-search')][not(contains(@id,'adoddle'))]")).size() != 1)
			mouseHoverElement(By.xpath(".//div[contains(@style,'display: block')]//div[@index='" + actionIndex + "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
		else
			mouseHoverElement(By.xpath(".//div[@index='" + actionIndex + "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);

		for (WebElement popCompleteAction : findElements(GlobalPageElements.css_firstActionPopoverActionsCompletedList)) {
			if (popCompleteAction.getText().contains(":"))
				actualActionList.add(popCompleteAction.getText().split(":")[1]);
			else
				actualActionList.add(popCompleteAction.getText());
		}

		if (findElements(GlobalPageElements.css_firstActionsPopoverContentLinks).size() > 0) {
			for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
				if (popHoverAction.getText().contains(":"))
					actualIncompleteActionList.add(popHoverAction.getText().split(":")[1]);
				else
					actualIncompleteActionList.add(popHoverAction.getText());
			}
			Assert.assertTrue(!actualIncompleteActionList.contains(expectedActionList));
		}
		else
			Assert.assertTrue(true);

		Assert.assertTrue(actualActionList.containsAll(expectedActionList));
	}
	
	public void selectSingleFileAndPerformForInformation() throws Exception
	{
		int index=0; boolean fileCondition = false, actionCondition = false;
		
		for (WebElement file : findElements(FilesTab.css_NumberOfFiles)) {
			
			waitUntilElementIsDisplayed(file);
			String singleFile = file.getText().split("\\.")[0];
			
			if(singleFile.substring(singleFile.length()-1).equalsIgnoreCase("0")) {
				
				fileCondition = true;
				
				if(findElements(By.xpath(".//a[contains(@id,'sidenav-search')][not(contains(@id,'adoddle'))]")).size() != 1) {
					waitUntilElementIsDisplayed(By.xpath(".//div[contains(@style,'display: block')]//div[@index='"+index+"']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
					mouseHoverElement(By.xpath(".//div[contains(@style,'display: block')]//div[@index='"+index+"']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
				} else {
					waitUntilElementIsDisplayed(By.xpath(".//div[@index='"+index+"']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
					mouseHoverElement(By.xpath(".//div[@index='"+index+"']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
				}
				waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
				
				for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
					if (popHoverAction.getText().equalsIgnoreCase(AdoddleCommonStringPool.ACTION_FOR_INFORMATION)) {
						
						actionCondition = true;
						clickElementAndWait(popHoverAction);
						new NewFilesPublishedScripts().verifyFileOpened();
						new NewFilesPublishedScripts().closeSecondWindow();
						break;
					}
				}
			}
			index++;
		}
		if(!fileCondition)
			Assert.assertTrue("Distributed File Not Displayed :",fileCondition);
		else {
			if(!actionCondition)
				Assert.assertTrue("Distributed Action Not Displayed :",actionCondition);
		}
	}
	
	private boolean actionCondition = false;
	public void selectSingleFormAndPerformAction(String action) throws Exception
	{
		int index=0; boolean formCondition = false;
		
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitForCompletePageLoad();
		waitUntilListOfElementIsDisplayed(GlobalPageElements.css_GlobalSearchAppsTitleList);
		waitUntilElementIsClickable(findElements(GlobalPageElements.css_GlobalSearchAppsTitleList).get(0));
		
		for (WebElement form : findElements(GlobalPageElements.css_GlobalSearchAppsTitleList)) {
			
			waitUntilElementIsDisplayed(form);
			String singleForm = form.getText();
			
			if((singleForm.substring(singleForm.length()-1).equalsIgnoreCase("0") && action.equalsIgnoreCase(AdoddleCommonStringPool.ACTION_FOR_INFORMATION)) || singleForm.substring(singleForm.length()-1).equalsIgnoreCase("2") && action.equalsIgnoreCase(AdoddleCommonStringPool.ACTION_RESPOND)) {
				
				formCondition = true;
				performSingleFormAction(index, action.trim(), singleForm);
			}
			index++;
		}
		if(!formCondition)
			Assert.assertTrue("Distributed Form Not Displayed :",formCondition);
		else {
			if(!actionCondition)
				Assert.assertTrue("Distributed Action Not Displayed :",actionCondition);
		}
		actionCondition = false;
	}
	
	public void performSingleFormAction(int index, String action, String form)
	{
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitForCompletePageLoad();
		
		if(findElements(By.xpath(".//a[contains(@id,'sidenav-search')][not(contains(@id,'adoddle'))]")).size() != 1) {
			waitUntilElementIsDisplayed(By.xpath(".//div[contains(@style,'display: block')]//div[@index='"+index+"']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
			mouseHoverElement(By.xpath(".//div[contains(@style,'display: block')]//div[@index='"+index+"']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
		} else {
			waitUntilElementIsDisplayed(By.xpath(".//div[@index='"+index+"']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
			mouseHoverElement(By.xpath(".//div[@index='"+index+"']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
		}
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
		
		for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
			
			if (popHoverAction.getText().contains(action.trim())) {
				
				actionCondition = true;
				clickElementAndWait(popHoverAction);
				FormIncompleteActionsScripts.formTitle = form;
				
				if(action.contains(AdoddleCommonStringPool.ACTION_FOR_INFORMATION)) {
					new FormIncompleteActionsScripts().performForInformationAction();
					break;
				} else if(action.contains(AdoddleCommonStringPool.ACTION_RESPOND)) {
					new FormIncompleteActionsScripts().performRespondAction();
					break;
				}
			}
		}
	}
	
	public void verifyFileActionCompletion(String action, String listOfFiles)
	{
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitForCompletePageLoad();
		
		/* Need Remove */	/**Reason : #NOODLE-84187**/
		clickOnGlobalSearchLhTab(AdoddleCommonStringPool.TAB_FILES);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitForCompletePageLoad();
		/* Need Remove */
		
		waitUntilListOfElementIsDisplayed(FilesTab.css_NumberOfFiles);
		waitUntilElementIsClickable(FilesTab.lnk_FileName);
		waitForCompletePageLoad();
		
		int index=0;
		for (WebElement file : findElements(FilesTab.css_NumberOfFiles)) {
			
			String singleFile = file.getText().split("\\.")[0];
			if(listOfFiles.equalsIgnoreCase("Single")) {
				
				if(singleFile.substring(singleFile.length()-1).equalsIgnoreCase("0")) {
					verifyActionCompleted(index, action);
					break;
				}
			} else
				verifyActionCompleted(index, action);
			index++;
		}
	}
	
	public void verifyFormActionCompletion(String action, String listOfForms)
	{
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitForCompletePageLoad();
		
		/* Need Remove */	/**Reason : #NOODLE-84187**/
		clickOnGlobalSearchLhTab(AdoddleCommonStringPool.TAB_PROJECT_FORMS);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitForCompletePageLoad();
		/* Need Remove */
		
		waitUntilListOfElementIsDisplayed(GlobalPageElements.css_GlobalSearchAppsTitleList);
		waitUntilElementIsClickable(findElements(GlobalPageElements.css_GlobalSearchAppsTitleList).get(0));
		waitForCompletePageLoad();
		
		int index=0;
		for (WebElement form : findElements(GlobalPageElements.css_GlobalSearchAppsTitleList)) {
			
			String singleForm = form.getText();
			if(listOfForms.equalsIgnoreCase("Single")) {
				
				if((singleForm.substring(singleForm.length()-1).equalsIgnoreCase("0") && action.contains(AdoddleCommonStringPool.ACTION_FOR_INFORMATION)) || singleForm.substring(singleForm.length()-1).equalsIgnoreCase("2") && action.contains(AdoddleCommonStringPool.ACTION_RESPOND)) {
					verifyActionCompleted(index, action);
					break;
				}
			} else
				verifyActionCompleted(index, action);
			index++;
		}
	}
	
	public By getCheckAll()
	{
		waitForCompletePageLoad();
		if(findElements(By.xpath(".//a[contains(@id,'sidenav-search')][not(contains(@id,'adoddle'))]")).size() != 1) {
			waitUntilElementIsClickable(By.xpath(".//div[contains(@style,'display: block')]//div[@id='listing']//input[@class='checkall'][@type='checkbox']"));
			return By.xpath(".//div[contains(@style,'display: block')]//div[@id='listing']//input[@class='checkall'][@type='checkbox']");
		}
		else {
			waitUntilElementIsClickable(By.xpath(".//div[@id='listing']//input[@class='checkall'][@type='checkbox']"));
			return By.xpath(".//div[@id='listing']//input[@class='checkall'][@type='checkbox']");
		}
	}
	
	public By getRowList()
	{
		waitForCompletePageLoad();
		if(findElements(By.xpath(".//a[contains(@id,'sidenav-search')][not(contains(@id,'adoddle'))]")).size() != 1) {
			waitUntilElementIsClickable(By.xpath(".//div[contains(@style,'display: block')]//div[@id='listing']//input[@class='checkall'][@type='checkbox']"));
			return By.xpath(".//div[contains(@style,'display: block')]//div[@id='listing']//div[@index='0']");
		}
		else {
			waitUntilElementIsClickable(By.xpath(".//div[@id='listing']//input[@class='checkall'][@type='checkbox']"));
			return By.xpath(".//div[@id='listing']//div[@index='0']");
		}
	}
	
	public void selectAllAndPerformContextMenuOption(String option1, String option2)
	{
		By checkAll = getCheckAll();
		By rowList = getRowList();
		
		if(!isSelected(checkAll))
			clickElementAndWait(checkAll);
		contextClick(rowList);
		clickContextMenuOption(option1, option2);
	}
	
	public void createComment()
	{
		waitUntilElementIsDisplayed(FilesTab.pop_StartNewDiscussion);
		verifyElementText(FilesTab.pop_StartNewDiscussionHeader, AdoddleCommonStringPool.OPTION_NEW);

		sendKeys(FilesTab.txt_NewDiscussionTitleInput, dateUtils.getEpoch());
		sendKeys(FilesTab.txt_NewDiscussionDescInput, javaUtils.getRandomString(10)+JavaUtils.getRandomNumber(10));
		clickElementAndWait(FilesTab.btn_NewDiscussionSubmit);
		waitForCompletePageLoad();
	}
	
	public void changeStatus(String status)
	{
//		waitUntilElementIsDisplayed(ProjectFormsTab.sel_PopStatusChangeDropdown);
		waitUntilElementIsDisplayed(By.xpath(".//div[contains(@style,'display: block')]//select[contains(@id,'tatusChange')]"));
		selectByVisibleText(By.xpath(".//div[contains(@style,'display: block')]//select[contains(@id,'tatusChange')]"), status);
		sendKeys(ProjectFormsTab.txt_PopChangeStatusReasonInput, javaUtils.getRandomString(20));
		new SingleMultipleFormsStatusChangeScripts().clickOnPopupButton("Change Status");
		waitUntilElementIsInvisible(By.xpath(".//div[contains(@style,'display: block')]//select[contains(@id,'tatusChange')]"));
	}
	
	public void verifyDistributedUnReadDiscussion()
	{
		waitForCompletePageLoad();
		Assert.assertTrue(getElementText(DiscussionsTab.css_DiscussionsTabDiscussionTitleList).equalsIgnoreCase(contentSearchDiscussion));
		verifyActionsAssigned(0, "Read");
	}
	
	public void performDistributedUnReadDiscussion() throws InterruptedException
	{
		int index=0;
		if(findElements(By.xpath(".//a[contains(@id,'sidenav-search')][not(contains(@id,'adoddle'))]")).size() != 1) {
			waitUntilElementIsDisplayed(By.xpath(".//div[contains(@style,'display: block')]//div[@index='"+index+"']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
			mouseHoverElement(By.xpath(".//div[contains(@style,'display: block')]//div[@index='"+index+"']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
		} else {
			waitUntilElementIsDisplayed(By.xpath(".//div[@index='"+index+"']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
			mouseHoverElement(By.xpath(".//div[@index='"+index+"']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
		}
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
		
		for (WebElement popHoverAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks)) {
			clickElementAndWait(popHoverAction);
			
			new NewFilesPublishedScripts().switchToSecondWindow();
			waitUntilElementIsDisplayed(DiscussionsTab.ele_FileBetaViewDiscussionsHistoryPanel);
			waitForCompletePageLoad();
			Assert.assertTrue(getElementText(DiscussionsTab.lbl_FileBetaViewfirstDiscussionTitle).contains(contentSearchDiscussion));
			waitForCompletePageLoad();
			waitForHTMLFileView();
			new NewFilesPublishedScripts().closeSecondWindow();
			waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
			waitForCompletePageLoad();
		}
	}
	
	
	
	/*** Models ***/
	
	private String modelDescription, workset, mappingFolder;
	public void createContentSearchTextDataModel()
	{
		waitUntilElementIsDisplayed(ModelsTab.lnk_AddModelsTab);
		clickElementAndWait(ModelsTab.lnk_AddModelsTab);
		
		contentSearchModel = dateUtils.getEpoch();
		modelDescription = ResourceHandler.loadProperty("content.search.text.string");
		workset = "AutoWorkset";
		mappingFolder = "ModelMappingFolder"+epoch;
		
		new ViewModelsScripts().createModel(contentSearchModel, modelDescription, clonedProject, workset, mappingFolder);
	}
	
	public void uploadIFCFile()
	{
		searchModels(contentSearchModel);
		setListingStyle("ListView");
		contextClickWithLink(contentSearchModel);
		clickContextMenuOption("Upload");
		
		verifyPopUpWithText("Upload Model File");
		new ViewModelsScripts().uploadIFCFile(workset);
		new ViewModelsScripts().verifyActivityCentrePopup("Activity Centre");
		new ViewModelsScripts().verifyMergingAndLoadingProcessOnActivityCentrePopup();
		new ViewModelsScripts().clickOnOK();
	}
	
	public void verifyCreatedContentSearchTextModel()
	{
		waitForCompletePageLoad();
		Assert.assertTrue(getElementText(GlobalPageElements.css_GlobalSearchModelsTitleList).equalsIgnoreCase(contentSearchModel));
	}
	
	public void mouseHoverAndClickOnViewModel()
	{
		mouseHoverAndClickElement(GlobalPageElements.css_GlobalSearchModelsTitleList, ModelsTab.lnk_ModelsTabFirstModelThumbViewViewFile);
		new NewFilesPublishedScripts().switchToSecondWindow();
	}
	
	public void verifyModelViewed() throws Exception
	{
		new ViewModelsScripts().viewModel(contentSearchModel);
	}
	
	/*
	public void deactivateAutoCreatedData(String activeTab)
	{
		boolean flag = false;
		List<String> autoCreatedDataList = new ArrayList<String>();

		if(activeTab.contains(AdoddleCommonStringPool.TAB_FILES)) {

			String deactivatedFilename = null;
			for (WebElement checkbox : findElements(FilesTab.css_FilesTabFilesCheckboxList)) {
				if(!contentSearchFilesList.contains(checkbox.getAttribute("filename"))) {
					flag = true;
					if(deactivatedFilename == null)
						deactivatedFilename = checkbox.getAttribute("filename");
					clickElementAndWait(checkbox);
				}
			}

			if(flag) {
				contextClickWithLink(deactivatedFilename);
				deactivateFiles();
			} else
				log.info(": Files Tab auto created Data already Cleaned up :");
		}
		else if(activeTab.contains(AdoddleCommonStringPool.TAB_DISCUSSIONS)) {

			int i=0;
			for (WebElement commentTitle : findElements(DiscussionsTab.css_DiscussionsTabDiscussionTitleList)) {
				if(!contentSearchDiscussionsList.contains(commentTitle.getText())) {
					flag = true;
					autoCreatedDataList.add(getElementText(By.xpath(".//div[@index='"+i+"']//div[contains(@class,'docRef')]//a")));
				}
				i++;
			}

			if(flag) {
				navigateTabByText(AsiteMenu.Files.toString());
				for (String docRef : autoCreatedDataList) {
					searchFiles(docRef);
					contextClickWithLink(docRef);
					deactivateFiles();
				}
			} else
				log.info(": Discussions Tab auto created Data already Cleaned up :");
		}
		else if(activeTab.contains(AdoddleCommonStringPool.TAB_PROJECT_FORMS)) {

			for (WebElement formTitle : findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList)) {
				if(!contentSearchFormsList.contains(formTitle.getText())) {
					flag = true;
					break;
				}
			}

			if(flag)
				deactivateForms();
			else
				log.info(": Forms Tab auto created Data already Cleaned up :");

			AdoddleScenarioMarkers.federatedSearchStatusFlag = true;
		}
	}
	*/
}