/*  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleModelsLocators.ModelsTab;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.JavaUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CreateModelFormScripts extends AdoddleCommonAppMethods {
	private int					i, viewNameIndex, fileNameIndex, docTitleIndex;
	private String				secondWindow, viewModelName;
	private boolean 			associateFlag				= false;
	final private String		modelFormTitle				= "AutomationTestModelForm" + epoch;
	final private String		modelFormAction 			= AdoddleCommonStringPool.FOR_INFORMATION;
	final private String[]		viewNoAccessFormList		= { "Model Form Access No", "Model Form NoAccess No", "Model Form NoAccess Yes", "Model Form Access Yes" };
	final private List<String>	attachedFileList			= new ArrayList<String>();
	final private List<String>	associatedFileList			= new ArrayList<String>();
	final private List<String>	associatedDiscussionList	= new ArrayList<String>();
	final private List<String>	associatedFormList			= new ArrayList<String>();
	final private List<String>	associatedViewList			= new ArrayList<String>();
	final private static 		Logger	log					= AutomationLogger.getInstance().getLogger(CreateModelFormScripts.class);

	public void contextClickOnModelViewAndSelectOption(String menuOption) {
		String modelIndex = JavaUtils.getRandomNumber(1);
		if (modelIndex.contains("0")) {
			int viewIndex = Integer.parseInt(modelIndex) + 1;
			modelIndex = Integer.toString(viewIndex);
		}
		log.info("modelIndex :" + modelIndex);
		clickElementAndWaitForElement(By.xpath(".//tr[@id='project-blocks']//td[contains(@title,'View Name')][" + modelIndex + "]//img"), ModelsTab.btn_ViewModelViewsCloseButton);
		viewModelName = getElementAttributeValue(ModelsTab.txt_ViewModelViewInput, "value");
		log.info("viewModelName :" + viewModelName);
		takeScreenShot(TestDriverFactory.scenario);
		clickElement(ModelsTab.btn_ViewModelViewsCloseButton);
		contextClick(By.xpath(".//tr[@id='project-blocks']//td[contains(@title,'View Name')][" + modelIndex + "]//img"));
		clickContextMenuOptionWithText(menuOption);
	}

	public void enterFormDistributionTextField() {
		sendKeys(ProjectFormsTab.txt_BetaViewPopCreateFormDistributeTo, System.getProperty("secondary.username"));
		clickElementAndWaitForElement(By.xpath(".//div[@id='distInputTo']//span[contains(text(),'" + System.getProperty("secondary.username") + "')]//preceding::input[@type='checkbox']"), ProjectFormsTab.drp_BetaViewPopCreateFormSelectedUserActionDropdown);
		selectByVisibleText(ProjectFormsTab.drp_BetaViewPopCreateFormSelectedUserActionDropdown, modelFormAction);
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.lnk_BetaViewCreateFormDistributeToCloseButton, ProjectFormsTab.lnk_BetaViewCreateFormDistributeToCloseButton);
	}

	public void clickOnCreateFormHeaderButtonOptions(String buttonType)  {
		clickElementAndWait(By.xpath(".//button[@title='"+buttonType+"']"));
	}

	public void clickOnSelectFilesAndAttachDocuments() {
		String			createFile1, createFile2;
		sysUtils.authenticateRemoteMachine(nodeIP);
		createFile1 = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
		createFile2 = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
		List<String> fileList = sysUtils.getFileList(createFile1 + "," + createFile2);
		uploadFileUsingKeys(ProjectFormsTab.btn_BetaViewCreateFormAttachmentsSelectFiles, fileList);
		waitUntilListOfElementIsDisplayed(ProjectFormsTab.css_BetaViewPopCreateFormAttachmentsFileNameList);
		List<WebElement> fileAttachList = findElements(ProjectFormsTab.css_BetaViewPopCreateFormAttachmentsFileNameList);
		for (WebElement attachFile : fileAttachList) {
			attachedFileList.add(attachFile.getText());
		}
		log.info("attached files list :" + attachedFileList);
		collectionDataMap.put("attachedFileList", attachedFileList.toString());
		clickElementAndWait(ModelsTab.btn_PopModelCommentAttachmentsAttachButton);
	}

	public void verifyOptionsPopup() {
			log.info(": Not Implemented in BetaView :");
	}

	public void associateFilesAndClickOnSave(String project, String folder) {
		waitUntilListOfElementIsDisplayed(ProjectFormsTab.chk_BetaViewCreateFormFilesListingRowchecbox);
		waitForCompletePageLoad();
		clickElementAndWait(By.xpath(".//div[@title='" + project + "']"));
		clickElementAndWait(By.xpath(".//div[@title='" + folder + "']"));
		waitUntilListOfElementIsDisplayed(ProjectFormsTab.chk_BetaViewCreateFormFilesListingRowchecbox);
		waitForCompletePageLoad();
		List<WebElement> fileListingCheckBoxes = findElements(ProjectFormsTab.chk_BetaViewCreateFormFilesListingRowchecbox);
		for (int counter = 0; counter < 3; counter++) {
			if(! isSelected(fileListingCheckBoxes.get(counter)))
				clickElementAndWait(fileListingCheckBoxes.get(counter));
		}
		clickButtonWithText(AdoddleCommonStringPool.FORM_ATTACHMENT_ASSOCIATION_ASSOCIATEANDCLOSE);
		waitUntilListOfElementIsDisplayed(ProjectFormsTab.css_BetaViewPopCreateFormAssociateDocsDocTitleList);
		List<WebElement> fileAssociateList = findElements(ProjectFormsTab.css_BetaViewPopCreateFormAssociateDocsDocTitleList);
		for (WebElement file : fileAssociateList) {
			associatedFileList.add(file.getText());
		}
		collectionDataMap.put("associatedFileList", associatedFileList.toString());
		log.info("associatedFileList :" + associatedFileList);
	}

	public void associateDiscussionsAndClickOnSave() {
		waitUntilListOfElementIsDisplayed(ProjectFormsTab.chk_BetaViewCreateFormFilesListingRowchecbox);
		waitForCompletePageLoad();
		int docTitleIndex = 1;
		for (WebElement attribute : findElements(ProjectFormsTab.css_BetaViewCreateFormAttributeHeaderLabelList)) {
		if (attribute.getAttribute("title").contains(AdoddleCommonStringPool.DOC_TITLE)) {
				log.info("docTitleIndex : " + docTitleIndex);
				break;
			}
			docTitleIndex++;
		}
		int i = 1, j = 0;
		for (WebElement checkbox : findElements(ProjectFormsTab.chk_BetaViewCreateFormFilesListingRowchecbox)) {
				if (!checkbox.isSelected()) {
				if (!getElementText(By.xpath(".//association//table-listing//div[@class='gbody']//ul[" + i + "]//li[" + docTitleIndex + "]//span[text()]")).contains("--")) {
					if (!associatedFileList.contains(getElementText(By.xpath(".//association//table-listing//div[@class='gbody']//ul[" + i + "]//li[" + docTitleIndex + "]//span[text()]")))) {
						log.info("Discussion File DocTitle : " + getElementText(By.xpath(".//association//table-listing//div[@class='gbody']//ul[" + i + "]//li[" + docTitleIndex + "]//span[text()]")));
						checkbox.click();
						if(!associateFlag)
							associatedFileList.add(getElementText(By.xpath(".//association//table-listing//div[@class='gbody']//ul[" + i + "]//li[" + docTitleIndex + "]//span[text()]")));
						j++;
					}
				}
			}
			if (j == 2)
				break;
			i++;
		}
		clickButtonWithText(AdoddleCommonStringPool.FORM_ATTACHMENT_ASSOCIATION_ASSOCIATEANDCLOSE);
		waitUntilListOfElementIsDisplayed(ProjectFormsTab.css_BetaViewPopCreateFormAssociateDiscussionsTitleList);
		List<WebElement> fileAssociateList = findElements(ProjectFormsTab.css_BetaViewPopCreateFormAssociateDiscussionsTitleList);
		for (WebElement file : fileAssociateList) {
			associatedDiscussionList.add(file.getText());
		}
		collectionDataMap.put("associatedFileList-2", associatedFileList.toString());
		collectionDataMap.put("associatedDiscussionList", associatedDiscussionList.toString());
		log.info("associatedFileList :" + associatedFileList);
		log.info("associatedDiscussionList :" + associatedDiscussionList);
	}

	public void associateFormAndClickOnSave() {
		waitUntilListOfElementIsDisplayed(ProjectFormsTab.chk_BetaViewCreateFormFilesListingRowchecbox);
		waitForCompletePageLoad();
		List<WebElement> fileListingCheckBoxes = findElements(ProjectFormsTab.chk_BetaViewCreateFormFilesListingRowchecbox);
		for (int counter = 0; counter < 3; counter++) {
			if(! isSelected(fileListingCheckBoxes.get(counter)))
				clickElementAndWait(fileListingCheckBoxes.get(counter));
		}
		clickButtonWithText(AdoddleCommonStringPool.FORM_ATTACHMENT_ASSOCIATION_ASSOCIATEANDCLOSE);
		waitUntilListOfElementIsDisplayed(ProjectFormsTab.css_BetaViewPopCreateFormAssociateFormsFormTitleList);
		List<WebElement> fileAssociateList = findElements(ProjectFormsTab.css_BetaViewPopCreateFormAssociateFormsFormTitleList);
		for (WebElement file : fileAssociateList) {
			associatedFormList.add(file.getText());
		}
		collectionDataMap.put("associatedFormList", associatedFormList.toString());
		log.info("associatedFormList :" + associatedFormList);
	}

	public void associateViewAndClickOnSave() {
		waitUntilElementIsClickable(findElements(ModelsTab.css_BetaViewPopAssociateViewList).get(0));
		try {waitUntilListOfElementIsDisplayed(ModelsTab.css_BetaViewPopAssociateViewList);} catch(Throwable t) {log.info(": waitInterval Failed :");}
		waitForCompletePageLoad();
		List<WebElement> modelViews = findElements(ModelsTab.css_BetaViewPopAssociateViewList);
		for(int counter = 0; counter < 2; counter++) {
			if(! modelViews.get(counter).getText().equalsIgnoreCase(viewModelName)) {
				associatedViewList.add(modelViews.get(counter).getText());
				modelViews.get(counter).click();
			}
		}
		collectionDataMap.put("associatedViewList", associatedViewList.toString());
		log.info("associatedViewList :" + associatedViewList);
		clickButtonWithText(AdoddleCommonStringPool.FORM_ATTACHMENT_ASSOCIATION_ASSOCIATEANDCLOSE);
	}

	public void enterFormDetails() {
		sendKeys(ProjectFormsTab.txt_CreateFormTitle, modelFormTitle);
		collectionDataMap.put("Model Form", modelFormTitle);
		log.info("modelFormTitle :" + modelFormTitle);
		clear(ProjectFormsTab.txt_PopCreateFormGroupCode);
		waitForCompletePageLoad();
		executeJScript(AdoddleCommonJQueries.betaViewCreateFormScrollMaxDownQuery);
		clickElementAndWaitForElement(ProjectFormsTab.img_BetaViewCreateFormCalendar, ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
		clickElementAndWait(ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
	}

	public void verifyModelFormOnFormsTab(String lhTab) {
		clickLinkWithText(lhTab);
		Assert.assertTrue(isDisplayed(ModelsTab.ele_FormsLHTabFirstFormTitle));
		Assert.assertTrue(getElementText(ModelsTab.ele_FormsLHTabFirstFormTitle).contains(modelFormTitle));
	}

	public void gotoFormDetails() {
		clickElementAndWaitForElement(ModelsTab.ele_FormsLHTabFirstFormTitle, ModelsTab.ele_ViewFormsAttachIcon);
		clickElementAndWaitForElement(ModelsTab.ele_ViewFormsAttachIcon, ModelsTab.lnk_ViewFormAttachment);
		clickElementAndWait(ModelsTab.lnk_ViewFormAttachment);
	}

	public void verifyAttachmentsAndAssociations(String viewType, String attachments, String files, String amessages, String apps, String views)
	{
		String[] headerLinkNameList = { attachments, files, amessages, apps, views };
		List<WebElement> webTextLinkList;

		if(viewType.contains("Form")) {
			
			/***************** Model Form *****************/
			
			for (String link : headerLinkNameList) {
				if(!isDisplayed(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//ul[contains(@class,'nav-tabs')]//li[contains(@class,'active')]//a//uib-tab-heading[contains(text(),'"+link+"')]")))
					clickElementAndWait(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//ul[contains(@class,'nav-tabs')]//li//a//uib-tab-heading[contains(text(),'"+link+"')]"));
				waitUntilListOfElementIsDisplayed(GlobalPageElements.css_BetaViewPopAttachmentsAssociationsActiveTabHeaderColumnNameList);
				
				if (getElementText(GlobalPageElements.lnk_BetaViewPopAttachmentsAndAssociationsActiveTab).contains(attachments)) {
					
					fileNameIndex = 1;
					for (WebElement attribute : findElements(GlobalPageElements.css_BetaViewPopAttachmentsAssociationsActiveTabHeaderColumnNameList)) {
						if (attribute.getAttribute("title").contains("File Name")) {
							log.info("fileNameIndex : " + fileNameIndex);
							break;
						}
						fileNameIndex++;
					}
					webTextLinkList = findElements(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//div[contains(@class,'active')]//div[@class='gbody']//ul//li["+fileNameIndex+"]//a[text()]"));
					compareTextWebList(webTextLinkList, attachedFileList);
				}
				else if (getElementText(GlobalPageElements.lnk_BetaViewPopAttachmentsAndAssociationsActiveTab).contains(files)) {
					
					docTitleIndex = 1;
					for (WebElement attribute : findElements(GlobalPageElements.css_BetaViewPopAttachmentsAssociationsActiveTabHeaderColumnNameList)) {
						if (attribute.getAttribute("title").contains("Doc Title")) {
							log.info("docTitleIndex : " + docTitleIndex);
							break;
						}
						docTitleIndex++;
					}
					webTextLinkList = findElements(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//div[contains(@class,'active')]//div[@class='gbody']//ul//li["+docTitleIndex+"]//span[text()]"));
					compareTextWebList(webTextLinkList, associatedFileList);
				}
				else if (getElementText(GlobalPageElements.lnk_BetaViewPopAttachmentsAndAssociationsActiveTab).contains(amessages)) {
					
					int titleIndex = 1;
					for (WebElement attribute : findElements(GlobalPageElements.css_BetaViewPopAttachmentsAssociationsActiveTabHeaderColumnNameList)) {
						if (attribute.getAttribute("title").contains("Title")) {
							log.info("titleIndex : " + titleIndex);
							break;
						}
						titleIndex++;
					}
				}
				else if (getElementText(GlobalPageElements.lnk_BetaViewPopAttachmentsAndAssociationsActiveTab).contains(apps)) {
					
					int titleIndex = 1;
					for (WebElement attribute : findElements(GlobalPageElements.css_BetaViewPopAttachmentsAssociationsActiveTabHeaderColumnNameList)) {
						if (attribute.getAttribute("title").contains("Title")) {
							log.info("titleIndex : " + titleIndex);
							break;
						}
						titleIndex++;
					}
					webTextLinkList = findElements(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//div[contains(@class,'active')]//div[@class='gbody']//ul//li["+titleIndex+"]//span[text()]"));
					compareTextWebList(webTextLinkList, associatedFormList);
				}
				else if (getElementText(GlobalPageElements.lnk_BetaViewPopAttachmentsAndAssociationsActiveTab).contains(views)) {
					
					viewNameIndex = 1;
					for (WebElement attribute : findElements(GlobalPageElements.css_BetaViewPopAttachmentsAssociationsActiveTabHeaderColumnNameList)) {
						if (attribute.getText().contains("View Name")) {
							log.info("viewNameIndex : " + viewNameIndex);
							break;
						}
						viewNameIndex++;
					}
					webTextLinkList = findElements(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//div[contains(@class,'active')]//div[@class='gbody']//ul//li["+viewNameIndex+"]//a[text()]"));
					associatedViewList.add(viewModelName);
					compareTextWebList(webTextLinkList, associatedViewList);
				}
			}
			
		}
		else {
			
			/***************** Model Comment *****************/
			
			for (String link : headerLinkNameList) {
				if(!isDisplayed(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//ul[contains(@class,'nav-tabs')]//li[contains(@class,'active')]//a[contains(text(),'"+link+"')]")))
					clickElementAndWait(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//ul[contains(@class,'nav-tabs')]//li//a[contains(text(),'"+link+"')]"));
				waitUntilListOfElementIsDisplayed(GlobalPageElements.css_BetaViewPopAMessagesActiveTabHeaderColumnNameList);
				
				if (getElementText(GlobalPageElements.lnk_BetaViewPopAMessagesActiveTab).contains(attachments)) {
					
					fileNameIndex = 1;
					for (WebElement attribute : findElements(GlobalPageElements.css_BetaViewPopAMessagesActiveTabHeaderColumnNameList)) {
						if (attribute.getText().contains("File Name")) {
							log.info("fileNameIndex : " + fileNameIndex);
							break;
						}
						fileNameIndex++;
					}
					webTextLinkList = findElements(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//div[@class='gbody']//ul//li["+fileNameIndex+"]//a[text()]"));
					compareTextWebList(webTextLinkList, attachedFileList);
				}
				else if (getElementText(GlobalPageElements.lnk_BetaViewPopAMessagesActiveTab).contains(files)) {
					
					docTitleIndex = 1;
					for (WebElement attribute : findElements(GlobalPageElements.css_BetaViewPopAMessagesActiveTabHeaderColumnNameList)) {
						if (attribute.getText().contains("Doc Title")) {
							log.info("docTitleIndex : " + docTitleIndex);
							break;
						}
						docTitleIndex++;
					}
					webTextLinkList = findElements(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//div[@class='gbody']//ul//li["+docTitleIndex+"]//a[text()]"));
					compareTextWebList(webTextLinkList, associatedFileList);
				}
				else if (getElementText(GlobalPageElements.lnk_BetaViewPopAMessagesActiveTab).contains(amessages)) {
					
					int titleIndex = 1;
					for (WebElement attribute : findElements(GlobalPageElements.css_BetaViewPopAMessagesActiveTabHeaderColumnNameList)) {
						if (attribute.getText().contains("Title")) {
							log.info("titleIndex : " + titleIndex);
							break;
						}
						titleIndex++;
					}
				}
				else if (getElementText(GlobalPageElements.lnk_BetaViewPopAMessagesActiveTab).contains(apps)) {
					
					int titleIndex = 1;
					for (WebElement attribute : findElements(GlobalPageElements.css_BetaViewPopAMessagesActiveTabHeaderColumnNameList)) {
						if (attribute.getText().contains("Title")) {
							log.info("titleIndex : " + titleIndex);
							break;
						}
						titleIndex++;
					}
					webTextLinkList = findElements(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//div[@class='gbody']//ul//li["+titleIndex+"]//a[text()]"));
					compareTextWebList(webTextLinkList, associatedFormList);
				}
				else if (getElementText(GlobalPageElements.lnk_BetaViewPopAMessagesActiveTab).contains(views)) {
					
					viewNameIndex = 1;
					for (WebElement attribute : findElements(GlobalPageElements.css_BetaViewPopAMessagesActiveTabHeaderColumnNameList)) {
						if (attribute.getText().contains("View Name")) {
							log.info("viewNameIndex : " + viewNameIndex);
							break;
						}
						viewNameIndex++;
					}
					webTextLinkList = findElements(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//div[@class='gbody']//ul//li["+viewNameIndex+"]//a[text()]"));
					associatedViewList.add(viewModelName);
					compareTextWebList(webTextLinkList, associatedViewList);
				}
			}
		}
	}

	public void clickAndVerifyAssociatedAllViews()
	{
		String viewName;
		String viewModelWindow;
		List<WebElement> webViewLinkList;
		webViewLinkList = findElements(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//div[contains(@class,'active')]//div[@class='gbody']//ul//li["+viewNameIndex+"]//a[text()]"));

		for (WebElement webView : webViewLinkList) {
			viewName = getElementText(webView);
			log.info("viewName :" + viewName);
			webView.click();
			viewModelWindow = getCurrentWindow();
			switchToWindow(4);
			waitUntilElementIsDisplayed(ModelsTab.scr_ModelViewerCanvasScreen);
			takeScreenShot(TestDriverFactory.scenario);
			closeCurrentWindow();
			switchPreviousWindow(viewModelWindow);
			waitForCompletePageLoad();
		}
	}

	public void verifyAssociatedViews() {
		log.info(": steps covered in previous method :");
	}

	public void verifyModelFormAndActionOnProjectFormsTab() {
		searchForms(modelFormTitle);
		Assert.assertTrue(getElementText(ProjectFormsTab.lnk_FirstFormTitle).contains(modelFormTitle));
		Assert.assertTrue(getElementText(FilesTab.lnk_FilesFirstAction).contains(modelFormAction));
	}

	public void verifyCreateFormPopup() {
		waitUntilElementIsDisplayed(ProjectFormsTab.pop_CreateFormWindow);
	}

	public void verifyAssociateViewsLink(String viewLink, String displayText) {
		if (displayText.contains("not"))
			Assert.assertTrue(!isDisplayedLinkWithText(viewLink));
		else
			Assert.assertTrue(isDisplayedLinkWithText(viewLink));
	}

	public void closeFormPopup() {
		clickElementAndWait(ProjectFormsTab.btn_BetaViewPopCreateFormCancelButton);
		switchDefault();
	}

	/****** Download Attachments & Associations ******/

	private List<WebElement>	webAttachAssociateList		= new ArrayList<WebElement>();
	final private String		outputZipAttachmentFolder	= nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim() + "AutomationAttachmentZipFile".trim() + epoch.trim();
	final private String		outputZipAssociationFolder	= nodeIP + ResourceHandler.loadProperty("remote.download.file.path").trim() + "AutomationAssociationZipFile".trim() + epoch.trim();
	final private String		inputZipAttachmentFile		= outputZipAttachmentFolder + ".zip";
	final private String		inputZipAssociationFile		= outputZipAssociationFolder + ".zip";
	final private List<String>	lstAttachFileValLocal		= new ArrayList<String>();
	final private List<String>	lstAssociateFileValLocal	= new ArrayList<String>();

	public void selectFilesAndClickOnDownload(String linkTab, String viewType) {
		
		if(viewType.contains("Form")) {
			
			/***************** Model Form *****************/
			
			if(!isDisplayed(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//ul[contains(@class,'nav-tabs')]//li[contains(@class,'active')]//a//uib-tab-heading[contains(text(),'"+linkTab+"')]")))
				clickElementAndWait(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//ul[contains(@class,'nav-tabs')]//li//a//uib-tab-heading[contains(text(),'"+linkTab+"')]"));
			
			if (getElementText(GlobalPageElements.lnk_BetaViewPopAttachmentsAndAssociationsActiveTab).contains("Attachments")) {
				webAttachAssociateList = findElements(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//div[contains(@class,'active')]//div[@class='gbody']//ul//li["+fileNameIndex+"]//a[text()]"));
			}
			else if (getElementText(GlobalPageElements.lnk_BetaViewPopAttachmentsAndAssociationsActiveTab).contains("Files")) {
				webAttachAssociateList.clear();
				List<WebElement> fileTypeImgList;
				
				int typeIndex = 1;
				for (WebElement attribute : findElements(GlobalPageElements.css_BetaViewPopAttachmentsAssociationsActiveTabHeaderColumnNameList)) {
					if (attribute.getAttribute("title").contains("Type")) {
						log.info("typeIndex : " + typeIndex);
						break;
					}
					typeIndex++;
				}
				fileTypeImgList = findElements(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//div[contains(@class,'active')]//div[@class='gbody']//ul//li["+typeIndex+"]//img"));
				i = 1;
				for (WebElement fileTypeImg : fileTypeImgList) {
					if (!fileTypeImg.getAttribute("src").contains("_fp.gif") && !fileTypeImg.getAttribute("src").contains("placeholder.gif")) {
						webAttachAssociateList.add(findElement(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//div[contains(@class,'active')]//div[@class='gbody']//ul["+i+"]//li["+docTitleIndex+"]//span[text()]")));
					}
					i++;
				}
			}
			if(!isSelected(GlobalPageElements.chk_BetaViewPopAttachmentsAssociationsActiveTabCheckAllCheckbox))
				clickElementAndWait(GlobalPageElements.chk_BetaViewPopAttachmentsAssociationsActiveTabCheckAllCheckbox);
		}
		else
		{
			/***************** Model Comment *****************/
			
			if(!isDisplayed(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//ul[contains(@class,'nav-tabs')]//li[contains(@class,'active')]//a[contains(text(),'"+linkTab+"')]")))
				clickElementAndWait(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//ul[contains(@class,'nav-tabs')]//li//a[contains(text(),'"+linkTab+"')]"));
			
			if (getElementText(GlobalPageElements.lnk_BetaViewPopAMessagesActiveTab).contains("Attachments")) {
				webAttachAssociateList = findElements(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//div[@class='gbody']//ul//li["+fileNameIndex+"]//a[text()]"));
			}
			else if (getElementText(GlobalPageElements.lnk_BetaViewPopAMessagesActiveTab).contains("Files")) {
				webAttachAssociateList.clear();
				List<WebElement> fileTypeImgList;
				
				int typeIndex = 1;
				for (WebElement attribute : findElements(GlobalPageElements.css_BetaViewPopAMessagesActiveTabHeaderColumnNameList)) {
					if (attribute.getText().contains("Type")) {
						log.info("typeIndex : " + typeIndex);
						break;
					}
					typeIndex++;
				}
				fileTypeImgList = findElements(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//div[@class='gbody']//ul//li["+typeIndex+"]//img"));
				i = 1;
				for (WebElement fileTypeImg : fileTypeImgList) {
					if (!fileTypeImg.getAttribute("src").contains("_fp.gif") && !fileTypeImg.getAttribute("src").contains("placeholder.gif")) {
						webAttachAssociateList.add(findElement(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//div[@class='gbody']//div["+i+"]//ul//li["+docTitleIndex+"]//a[text()]")));
					}
					i++;
				}
			}
			if(!isSelected(GlobalPageElements.chk_BetaViewPopAMessagesActiveTabCheckAllCheckbox))
				clickElementAndWait(GlobalPageElements.chk_BetaViewPopAMessagesActiveTabCheckAllCheckbox);
		}
		clickElementAndWait(GlobalPageElements.btn_BetaViewPopDownloadPreferencesButton);
	}

	public void selectCheckListAndClickOnDownload(String linkTab) {
		
		if (!linkTab.contains("Attachments")) {
			
			waitUntilElementIsDisplayed(GlobalPageElements.btn_BetaViewPopDownloadPreferencesDownloadButton);
			waitUntilElementIsClickable(GlobalPageElements.btn_BetaViewPopDownloadPreferencesDownloadButton);
			
			for (WebElement e : findElements(GlobalPageElements.css_BetaViewPopDownloadPreferencesCheckList)) {
				if(e.isDisplayed() && !e.isSelected() && (e.getAttribute("name").contains("isRenameWithDocRef") || e.getAttribute("name").contains("isAppendDocTitle") || e.getAttribute("name").contains("isAppenedIssNo") || e.getAttribute("name").contains("isAppenedRevNo")))
					e.click();
			}
		}
		clickElementAndWait(GlobalPageElements.btn_BetaViewPopDownloadPreferencesDownloadButton);
	}

	public void downloadZipDocumentIntoLocal(String linkTab) throws InterruptedException {
		if (!linkTab.contains("Attachments"))
			sysUtils.waitForMultipleFileDownload(nodeIP, inputZipAssociationFile);
		else
			sysUtils.waitForMultipleFileDownload(nodeIP, inputZipAttachmentFile);
	}

	public void zipIntoUnZip(String linkTab) {
		sysUtils.authenticateRemoteMachine(nodeIP);
		if (!linkTab.contains("Attachments"))
			sysUtils.unZipFile(inputZipAssociationFile, sysUtils.createDirectory(outputZipAssociationFolder));
		else
			sysUtils.unZipFile(inputZipAttachmentFile, sysUtils.createDirectory(outputZipAttachmentFolder));
	}

	public void getFileNamesFromLocalFolder(String linkTab) {
		File folder;
		File[] listOfFiles;

		if (!linkTab.contains("Attachments")) {
			folder = new File(outputZipAssociationFolder);
			listOfFiles = folder.listFiles();
		}
		else {
			folder = new File(outputZipAttachmentFolder);
			listOfFiles = folder.listFiles();
		}
		for (i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				if (!linkTab.contains("Attachments")) {
					lstAssociateFileValLocal.add(listOfFiles[i].getName().substring(0, listOfFiles[i].getName().lastIndexOf(".")));
				}
				else {
					lstAttachFileValLocal.add(listOfFiles[i].getName().substring(0, listOfFiles[i].getName().lastIndexOf(".")));
				}
			}
		}
		log.info("lstAttachFileValLocal :" + lstAttachFileValLocal);
		log.info("lstAssociateFileValLocal :" + lstAssociateFileValLocal);
	}

	public void verifyFilesNameIntoSystem(String linkTab) {
		boolean flag = false;
		List<String> localFileList = new ArrayList<String>();

		if (!linkTab.contains("Attachments")) {
			localFileList.addAll(lstAssociateFileValLocal);
		}
		else
			localFileList.addAll(lstAttachFileValLocal);

		for (WebElement webFile : webAttachAssociateList) {
			for (String localFile : localFileList) {
				String webText = webFile.getText().split("\\.")[0];
				if (localFile.contains(webText)) {
					log.error("Web File Verified :" + webText);
					log.error("Local File Verified :" + localFile);
					flag = true;
					break;
				}
				else
					flag = false;
			}
			if (!flag)
				Assert.assertTrue(false);
		}
	}

	private void compareTextWebList(List<WebElement> webList, List<String> textList) {
		if (textList.size() == webList.size()) {

			boolean flag = false;
			for (WebElement actualValue : webList) {

				for (String expectedValue : textList) {

					if (actualValue.getText().contains(expectedValue)) {

						log.error("actualValue :" + actualValue.getText());
						log.error("expectedValue :" + expectedValue);
						flag = true;
						break;
					}
					else {

						log.error("not Verified");
						flag = false;
					}
				}
				if (!flag) {

					log.error("List Value not Matched");
					Assert.assertTrue(false);
				}
			}
		}
		else {

			Assert.assertTrue("Weblist size " + webList.size() + " != textlist size " + textList.size(), false);
		}
	}
	
	
	/***************************** NEW SCRIPT *******************************/
	
	public void navigateViewManager() {
		clickElementAndWait(ModelsTab.btn_ModelsTabViewModelViewsManager);
		Assert.assertTrue("Failure while validating Model View Manager", getElementText(GlobalPageElements.lbl_BetaViewActiveTabDropdownPopupLabel).contains(AdoddleCommonStringPool.VIEWS_MANAGER));
	}
	
	public void selectModelViewAndClickDiscussion(String commentForm) throws Exception
	{
		String modelIndex = JavaUtils.getRandomNumber(1);
		if (modelIndex.contains("0")) {
			int viewIndex = Integer.parseInt(modelIndex) + 1;
			modelIndex = Integer.toString(viewIndex);
		}
		log.info("modelIndex :" + modelIndex);
		
		viewModelName = getElementText(By.xpath(".//div[@id='model-view-page']//main[@class='open']//div[@class='ibox']//ul//li[contains(@class,'dd-item')]["+modelIndex+"]//a"));
		log.info("viewModelName :" + viewModelName);
		clickElementAndWait(By.xpath(".//div[@id='model-view-page']//main[@class='open']//div[@class='ibox']//ul//li[contains(@class,'dd-item')]["+modelIndex+"]//span[contains(@title,'Select View')]//img"));
		waitUntilElementIsDisplayed(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//div[@class='ibox']//button[@title='"+commentForm+"'][not(@disabled)]"));
		waitUtils.waitInterval(1);
		takeScreenShot(TestDriverFactory.scenario);
		
		findElement(By.cssSelector("body")).sendKeys(Keys.ESCAPE);
		waitForCompletePageLoad();
		
		if(!isDisplayed(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//div[@class='ibox']//button[@title='"+commentForm+"'][not(@disabled)]")))
			clickElementAndWait(ModelsTab.btn_ModelsTabViewModelViewsManager);
		clickElementAndWait(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//div[@class='ibox']//button[@title='"+commentForm+"'][not(@disabled)]"));
	}
	
	public void clickOnAssociateFiles() {
		clickLinkWithText(AdoddleCommonStringPool.FORM_ASSOCIATION_ASSOCIATE_DOCS);
	}

	public void clickOnAssociateDiscussions(String formComment) {
		if(formComment.contains("Comment"))
			associateFlag = true;
		clickLinkWithText(AdoddleCommonStringPool.FORM_ASSOCIATION_ASSOCIATE_AMESSAGES);
	}

	public void clickOnAssociateForms() {
		clickLinkWithText(AdoddleCommonStringPool.FORM_ASSOCIATION_ASSOCIATE_APPS);
	}

	public void clickOnAssociateViews() {
		clickLinkWithText(AdoddleCommonStringPool.MODEL_ASSOCIATION_ASSOCIATE_VIEWS);
	}
	
	public void verifyDropdownPopup(String popup)
	{
		if(popup.equalsIgnoreCase("Create Form")) {
			Assert.assertTrue(getElementText(GlobalPageElements.lbl_BetaViewActiveTabDropdownPopupSubLabel).contains(popup));
		} else {
			Assert.assertTrue(getElementText(GlobalPageElements.lbl_BetaViewActiveTabDropdownPopupLabel).contains(popup));
		}
	}
	
	public void verifyViewFormType(String viewForm)
	{
		waitForCompletePageLoad();
		for (String formType : viewNoAccessFormList) {
			sendKeys(ProjectFormsTab.txt_BetaViewPopCreateFormNameSearchFilter, formType);
			if (!formType.equalsIgnoreCase(viewForm))
				Assert.assertTrue(!isDisplayed(ProjectFormsTab.ele_BetaViewViewFilePopCreateFormFirstFormName));
			else
				Assert.assertTrue(isDisplayed(ProjectFormsTab.ele_BetaViewViewFilePopCreateFormFirstFormName));
		}
	}
	
	public void searchAndSelectFormType(String formType)
	{
		sendKeys(ProjectFormsTab.txt_BetaViewPopCreateFormNameSearchFilter, formType);
		collectionDataMap.put("Model FormType", formType);
		clickElementAndWait(ProjectFormsTab.ele_BetaViewViewFilePopCreateFormFirstFormName);
		secondWindow = getCurrentWindow();
		switchToWindow(3);
	}
	
	public void verifyCreatedFormOnViewer()
	{
		waitForCompletePageLoad();
		Assert.assertTrue(getElementText(ProjectFormsTab.ele_BetaViewViewFormTitle).contains(modelFormTitle));
	}
	
	public void clickOnViewFormAttachmentClipIcon()
	{
		clickElementAndWait(ProjectFormsTab.lnk_BetaViewFormDetailsAttachmentsLink);
	}
	
	public void closeViewModelWindow()
	{
		if(!associateFlag) {
			closeCurrentWindow();
			switchPreviousWindow(secondWindow);
			waitForCompletePageLoad();
		}
		closeCurrentWindow();
		switchPreviousWindow(CreateModelCommentScripts.parentWindow);
		waitForCompletePageLoad();
	}
}
