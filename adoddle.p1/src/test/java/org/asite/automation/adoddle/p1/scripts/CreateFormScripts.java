/* Author: Jasmin Prajapati */

package org.asite.automation.adoddle.p1.scripts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleDiscussionsLocators.DiscussionsTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.steps.Tests_CommonStepDefinitions;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CreateFormScripts extends AdoddleCommonAppMethods {

	private String			formSubject					= "AutomationTest_Subject_Form", epochTime, formDescription, parentWindow;
	List<String>			expectedAssociationFiles	= new ArrayList<String>();
	List<String>			expectedAttachmentFiles		= new ArrayList<String>();
	List<Double>			fileSizeListExpected		= new ArrayList<Double>();
	List<WebElement>		fileSizeElements			= new ArrayList<WebElement>();
	public static Logger	log							= AutomationLogger.getInstance().getLogger(CreateFormScripts.class);

	public void navigateProjectFormsTab() {
		navigateTab(LandingPage.lnk_ProjectForms);
	}

	public void focusFormTemplate(String formTemplateName) {
		clickLinkWithTitle(formTemplateName);
	}

	public void clickCreateForm(String createFormLinkText) {
		verifyElementText(ProjectFormsTab.btn_CreateForm, createFormLinkText);
		clickElementAndWait(ProjectFormsTab.btn_CreateForm);
	}

	public void verifyCreateFormPopup(String expectedText) {
		try {
			Assert.assertTrue(isDisplayed(ProjectFormsTab.pop_CreateFormWindow));
		}
		catch (Throwable t) {
			log.error(t.getStackTrace());
			log.error("Create Form Window not found");
		}
//		verifyElementText(ProjectFormsTab.lbl_CreateFormWindowHeader, expectedText);
		Assert.assertTrue(isDisplayed(ProjectFormsTab.pop_CreateFormWindow));
		switchIframe(ProjectFormsTab.frm_createFormIframe);
		switchDefault();
	}

	public void verifyCreateFormPopupIsOpen() {
		waitUntilElementIsDisplayed(ProjectFormsTab.pop_CreateFormWindow);
		switchIframe(ProjectFormsTab.frm_createFormIframe);
	}

	public void verifyFormUsersSelected(String expectedText) throws InterruptedException {
		
		//if(appBetaViewFlag)
			sendKeys(ProjectFormsTab.txt_BetaViewCreateRFIFormInputToField, System.getProperty("primary.username").toString());
		/*else
			sendKeys(ProjectFormsTab.txt_FormInputToField, System.getProperty("primary.username").toString());*/
		
		//if(appBetaViewFlag){
			if(!isSelected(ProjectFormsTab.chk_BetaViewSelectDistributeUserCheckBox)){
				
				clickElementAndWait(ProjectFormsTab.chk_BetaViewSelectDistributeUserCheckBox);
			}
				clickElement(ProjectFormsTab.lnk_CreateFormDistributeToCloseButton);
		/*}
		else{
				clickElementAndWait(ProjectFormsTab.txt_SelectDistributeUser);
		}*/
	}

	public void attachFilesToForm() throws IOException, InterruptedException {
		//if(appBetaViewFlag){
			
			clickElementAndWait(ProjectFormsTab.btn_BetaViewCreateFormAttachment);
			List<String> fileList = sysUtils.getFileList(ResourceHandler.loadProperty("multi.files.path"));
			for (String f : fileList) {
				File file = new File(f);
				expectedAttachmentFiles.add(file.getName());
				fileSizeListExpected.add(sysUtils.getFileSize(f));
			}
			log.info("Create form attachment file(s) list: "+expectedAttachmentFiles.toString());
			collectionDataMap.put("Attachment Files", expectedAttachmentFiles.toString());
			uploadFileUsingKeys(ProjectFormsTab.btn_BetaViewCreateFormAttachmentsSelectFiles, fileList);
			waitUntilElementIsDisplayed(ProjectFormsTab.btn_BetaViewCreateFormAttachFileSave);
			collectionDataMap.put("Attachment Files", fileList.toString());
			Assert.assertTrue(getCount(FilesTab.lbl_BetaViewNewDiscussionAttachmentsFileSizeElements) > 0);
			fileSizeElements = findElements(FilesTab.lbl_BetaViewNewDiscussionAttachmentsFileSizeElements);

			for (int index = 0; index != fileSizeListExpected.size(); index++) {
				try {
					Assert.assertTrue(Double.parseDouble(fileSizeElements.get(index).getText().replace("KB", "")) == Math.round(fileSizeListExpected.get(index) * 100.0) / 100.0);
				}
				catch (Throwable t) {
					log.error(t.getStackTrace());
					log.error("File size did not match for external file attachments");
					log.error("Adoddle Attachment size:::" + Double.parseDouble(fileSizeElements.get(index).getText().replace("KB", "")));
					log.error("Actual File Size:::" + Math.round(fileSizeListExpected.get(index) * 100.0) / 100.0);
				}

			}

			clickElementAndWait(ProjectFormsTab.btn_BetaViewCreateFormAttachFileSave);
			
		/*}
		
		else{
				clickElementAndWait(ProjectFormsTab.btn_CreateFormAttachment);
				List<String> fileList = sysUtils.getFileList(ResourceHandler.loadProperty("multi.files.path"));
				for (String f : fileList) {
					File file = new File(f);
					expectedAttachmentFiles.add(file.getName());
					fileSizeListExpected.add(sysUtils.getFileSize(f));
				}

				uploadFileUsingKeys(ProjectFormsTab.btn_CreateFormAttachmentsSelectFiles, fileList);
				waitUntilElementIsDisplayed(ProjectFormsTab.btn_CreateFormAttachFileSave);
				Assert.assertTrue(getCount(FilesTab.lbl_NewDiscussionAttachmentsFileSizeElements) > 0);
				fileSizeElements = findElements(FilesTab.lbl_NewDiscussionAttachmentsFileSizeElements);

				for (int index = 0; index != fileSizeListExpected.size(); index++) {
					try {
						Assert.assertTrue(Double.parseDouble(fileSizeElements.get(index).getText().replace("KB", "")) == Math.round(fileSizeListExpected.get(index) * 100.0) / 100.0);
					}
					catch (Throwable t) {
						log.error(t.getStackTrace());
						log.error("File size did not match for external file attachments");
						log.error("Adoddle Attachment size:::" + Double.parseDouble(fileSizeElements.get(index).getText().replace("KB", "")));
						log.error("Actual File Size:::" + Math.round(fileSizeListExpected.get(index) * 100.0) / 100.0);
					}

				}

				clickElementAndWait(ProjectFormsTab.btn_CreateFormAttachFileSave);
		}*/
	}

	public void associateSingleDocument(int count) {
		log.info("TestData collected: "+collectionDataMap.toString());
		//if(appBetaViewFlag){
			
			clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewCreateFormMoreOptions, ProjectFormsTab.lnk_BetaViewCreateFormAssociateDoc);
			clickElementAndWaitForElement(ProjectFormsTab.lnk_BetaViewCreateFormAssociateDoc, ProjectFormsTab.btn_BetaViewCreateFormAssociatedDocSave);
			waitUntilElementIsDisplayed(ProjectFormsTab.chk_BetaViewSingleFileAssociateDoc);

			// clickElement(ProjectFormsTab.chk_CreateFormAssociateDocFirstCheckBox);

			try {
				Assert.assertTrue(getCount(FilesTab.css_PopAssociateFileListingCount) > count);
			}
			catch (Throwable t) {
				log.info(t.getStackTrace());
				log.info("TESTDATA: Files count is not enough");
			}

			List<WebElement>  filesListCheckBoxes = findElements(ProjectFormsTab.css_BetaViewCreateFormAssociationCheckboxList);
			List<WebElement> filesListDocRefs = findElements(ProjectFormsTab.css_BetaViewCreateFormAssociationDocRefList);
			List<String> actualAssociationsFileNames = new ArrayList<String>();
			
			log.info("List size: "+filesListCheckBoxes.size());
			log.info("Doc Ref List size: "+filesListDocRefs.size());
			
			for (int index = 1; index != count + 1; index++) {
				clickElementAndWait(filesListCheckBoxes.get(index -1));
				expectedAssociationFiles.add(getElementText(filesListDocRefs.get(index -1)));
			}
			
			for (WebElement e : filesListDocRefs)
			actualAssociationsFileNames.add(e.getText());
			
			
			log.info("Expected Association list: "+actualAssociationsFileNames.toString());
			collectionDataMap.put("Associated Files", expectedAssociationFiles.toString());
			clickElementAndWait(ProjectFormsTab.btn_BetaViewCreateFormAssociatedAndCloseDocSave);
		/*}
		else{
			
			clickElementAndWaitForElement(ProjectFormsTab.btn_CreateFormMoreOptions, ProjectFormsTab.lnk_CreateFormAssociateDoc);
			clickElementAndWaitForElement(ProjectFormsTab.lnk_CreateFormAssociateDoc, ProjectFormsTab.btn_CreateFormAssociatedDocSave);
			waitUntilElementIsClickable(ProjectFormsTab.btn_CreateFormAssociatedDocSave);
			waitUntilElementIsDisplayed(ProjectFormsTab.chk_SingleFileAssociateDoc);

			// clickElement(ProjectFormsTab.chk_CreateFormAssociateDocFirstCheckBox);

			try {
				Assert.assertTrue(getCount(FilesTab.css_PopAssociateFileListingCount) > count);
			}
			catch (Throwable t) {
				log.info(t.getStackTrace());
				log.info("TESTDATA: Files count is not enough");
			}

			List<WebElement>  filesListCheckBoxes = findElements(ProjectFormsTab.css_CreateFormAssociationCheckboxList);
			List<WebElement> filesListDocRefs = findElements(ProjectFormsTab.css_CreateFormAssociationDocRefList);
			log.info("List size: "+filesListCheckBoxes.size());
			log.info("Doc Ref List size: "+filesListDocRefs.size());
		
			for (int index = 1; index != count + 1; index++) {
				clickElementAndWait(filesListCheckBoxes.get(index -1));
				expectedAssociationFiles.add(getElementText(filesListDocRefs.get(index -1)));
			}
		
			log.info("Expected Association list: "+expectedAssociationFiles.toString());
			clickElementAndWait(ProjectFormsTab.btn_CreateFormAssociatedDocSave);
			clickElementAndWait(ProjectFormsTab.btn_AssociateDocFinalSave);
		
		}*/
	}

	public void clickSaveButton() {
		
		//if(appBetaViewFlag){
			clickElementAndWait(ProjectFormsTab.btn_BetaViewCreateFormSave);
			switchDefault();
		/*}
		else{
		clickElementAndWait(ProjectFormsTab.btn_CreateFormSave);
		switchDefault();
		}*/
	}

	public void verifyFormSuccessMessage() {
		isDisplayed(ProjectFormsTab.txt_filterProjectForm);
		try {
			verifyElementText(ProjectFormsTab.lbl_FormAddSuccessMsg, AdoddleCommonStringPool.FORM_SUCCESS_MESSAGE, 10);
		}
		catch (Throwable t) {
			log.error("Success Message could not be verified");
		}
		waitForCompletePageLoad();
	}

	public void verifyFormIsCreated() {
		formSubject = Tests_CommonStepDefinitions.formTitle;
		waitUntilElementIsDisplayed(ProjectFormsTab.txt_filterProjectForm);
		searchForms(formSubject);
		verifyElementText(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle, formSubject);
	}

	public void verifyAttachedDocumentOfForm() {
		
		//if(appBetaViewFlag){
			
			parentWindow = getCurrentWindow();
			clickElementAndWait(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle);
			waitForSwitchWindow(2);
			switchWindow();
			waitUntilElementIsDisplayed(ProjectFormsTab.lnk_BetaViewFormDetailsAttachmentsLink);
			clickElementAndWait(ProjectFormsTab.lnk_BetaViewFormDetailsAttachmentsLink);
			waitUntilElementIsDisplayed(ProjectFormsTab.lbl_BetaViewFormAssociateAndAttachmentDiv);
			clickElementAndWait(DiscussionsTab.lnk_BetaViewPopAttachmentAndAssociationPopupAttachments);

			List<WebElement> attachmentElements = findElements(DiscussionsTab.css_BetaViewPopAttachmentAndAssociationAttachmentsFileNames);
			List<String> actualAttachmentFileNames = new ArrayList<String>();

			for (WebElement e : attachmentElements)
				actualAttachmentFileNames.add(e.getText());
			
				
			for (String attachment : expectedAttachmentFiles){
				
				log.info("Expected List elements: " + expectedAttachmentFiles);
				log.info("Attachment List elements: " + attachment);
				Assert.assertTrue(actualAttachmentFileNames.contains(attachment));
			}
			for (String attachment : actualAttachmentFileNames)
				Assert.assertTrue(expectedAttachmentFiles.contains(attachment));

			
			List<String> ulFileList = new ArrayList<String>();
			for (WebElement ulFile : findElements(DiscussionsTab.css_BetaViewPopAttachmentAndAssociationAttachmentsFileNames)) {
				ulFileList.add(ulFile.getText());
			}
			
			Assert.assertTrue(ulFileList.containsAll(expectedAttachmentFiles));
		/*}
		
			else{
			parentWindow = getCurrentWindow();
			clickElementAndWait(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle);
			waitForSwitchWindow(2);
			switchWindow();
			waitUntilElementIsDisplayed(ProjectFormsTab.lnk_FormDetailsAttachmentsLink);
			clickElementAndWait(ProjectFormsTab.lnk_FormDetailsAttachmentsLink);
			waitUntilElementIsDisplayed(ProjectFormsTab.lbl_ViewFormActiveTabLabel);
			clickElementAndWait(DiscussionsTab.lnk_PopAttachmentAndAssociationPopupAttachments);
	
			List<WebElement> attachmentElements = findElements(DiscussionsTab.css_PopAttachmentAndAssociationAttachmentsFileNames);
			List<String> actualAttachmentFileNames = new ArrayList<String>();
	
			for (WebElement e : attachmentElements)
				actualAttachmentFileNames.add(e.getText());
	
			for (String attachment : expectedAttachmentFiles)
				Assert.assertTrue(actualAttachmentFileNames.contains(attachment));
	
			for (String attachment : actualAttachmentFileNames)
				Assert.assertTrue(expectedAttachmentFiles.contains(attachment));
	
			
			List<String> ulFileList = new ArrayList<String>();
			for (WebElement ulFile : findElements(DiscussionsTab.css_PopAttachmentAndAssociationAttachmentsFileNames)) {
				ulFileList.add(ulFile.getText());
			}
			
			Assert.assertTrue(ulFileList.containsAll(expectedAttachmentFiles));
		}*/	
	}

	public void verifyAssociatedDocumentOfForm() { 
	
	//if(appBetaViewFlag){
		
		clickElementAndWait(DiscussionsTab.lnk_BetaViewPopAttachmentAndAssociationPopupFiles);
		waitUntilElementIsDisplayed(DiscussionsTab.css_AttachmentsAndAssociationsDiv);
		
		List<WebElement> associatedDocRefElements = findElements(DiscussionsTab.css_BetaViewPopAttachmentAndAssociationFilesDocRefs);
		List<String> actualAssociatedDocRefs = new ArrayList<String>();
		
		for (WebElement e : associatedDocRefElements)
			actualAssociatedDocRefs.add(e.getText());

		for (String attachment : expectedAssociationFiles){
			
			log.info("Expected List elements: " + expectedAssociationFiles);
			log.info("Actual List elements: " + actualAssociatedDocRefs);
			log.info("Association List elements: " + attachment);
			Assert.assertTrue(actualAssociatedDocRefs.contains(attachment));
		}	
		
		for (String attachment : actualAssociatedDocRefs)
			Assert.assertTrue(expectedAssociationFiles.contains(attachment));

		closeCurrentWindow();
		switchPreviousWindow(parentWindow);
	}
	
	/*else{
		
		clickElementAndWait(DiscussionsTab.lnk_PopAttachmentAndAssociationPopupFiles);

		List<WebElement> associatedDocRefElements = findElements(DiscussionsTab.css_PopAttachmentAndAssociationFilesDocRefs);
		List<String> actualAssociatedDocRefs = new ArrayList<String>();

		for (WebElement e : associatedDocRefElements)
			actualAssociatedDocRefs.add(e.getText());

		for (String attachment : expectedAssociationFiles)
			Assert.assertTrue(actualAssociatedDocRefs.contains(attachment));

		for (String attachment : actualAssociatedDocRefs)
			Assert.assertTrue(expectedAssociationFiles.contains(attachment));

		closeCurrentWindow();
		switchPreviousWindow(parentWindow);

		}
	}*/
	
	public void verifyAssociationReferences() throws InterruptedException {
		navigateTab(LandingPage.lnk_Files);
		searchFiles(expectedAssociationFiles.get(0));
		clickElementAndWait(FilesTab.lnk_DocListingFirstFileName);
		waitForSwitchWindow(2);
		switchWindow();
		verifyFileInHTMLViewer();
		clickElementAndWait(FilesTab.lnk_FileViewLHAssociationBlock);

		logOut();
	}
}
