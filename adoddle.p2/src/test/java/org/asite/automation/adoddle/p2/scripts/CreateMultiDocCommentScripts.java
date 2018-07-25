/**  Testdata required for this script as follows.
     1). Testdata files should be available on Test machine for attachments.
     2). At-least 3 discussions, Forms and documents available in project.
 */

package org.asite.automation.adoddle.p2.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleDiscussionsLocators.DiscussionsTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.resources.AdoddleScenarioMarkers;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CreateMultiDocCommentScripts extends AdoddleCommonAppMethods {

	private static String commentTitle = "AutomationTest_CommentTitle", commentDesc, parentWindow;
	private boolean countFlag;
	List<String> fileList = new ArrayList<String>();
	List<String> expectedFormIDs = new ArrayList<String>();
	List<String> expectedAssociationFiles = new ArrayList<String>();
	List<String> expectedDiscussionIDs = new ArrayList<String>();
	List<String> expectedAttachmentFiles = new ArrayList<String>();
	List<String> commentOnFiles = new ArrayList<String>();
	List<String> attachmentFiles = new ArrayList<String>();
	List<Double> fileSizeListExpected = new ArrayList<Double>();
	List<WebElement> fileSizeElements = new ArrayList<WebElement>();
	public static Logger log = AutomationLogger.getInstance().getLogger(CreateMultiDocCommentScripts.class);

	public void verifyUserIsLoggedIn() throws InterruptedException {
		verifyLogin();
	}

	public void navigateFilesTab() {
		navigateTab(LandingPage.lnk_Files);
	}

	public void contextClickMultipleDocuments() throws InterruptedException, IOException {
		clickElementWithText(ResourceHandler.loadProperty("application.file.upload.folder"));
		for (int index = 0; index < 2; index++) {
			commentOnFiles.add("MultiDocCommentTitle" + epoch);
			waitUtils.waitInterval(1);
		}
		fileList = uploadDocuments(null, 2, null, commentOnFiles, false, 1, null, null, null, null);
		collectionDataMap.put("Comment Documents", fileList.toString());
		navigateFilesTab();
		searchFiles("MultiDocCommentTitle" + epoch);
		clickElement(FilesTab.chk_DocListingFirstCheckBox);
		clickElement(FilesTab.chk_DocListingSecondCheckBox);
		contextClick(FilesTab.lnk_DocListingFirstDocRef);
	}

	public void hoverMouseOnNew() {
		mouseHover(FilesTab.opt_FileContextClickNew);
	}

	public void verifyOptionText(String expectedText) {
		verifyElementText(FilesTab.opt_FileContextClickNew, expectedText);
	}

	public void verifyStartNewDiscussionText(String expectedText) {
		verifyElementText(FilesTab.opt_FileContextClickStartDiscussion, expectedText);
	}

	public void clickOnContextMenuElement(String elementText) {
		clickContextMenuOptionWithText(elementText);
	}

	public void verifyNewPopup(String expectedText) {
		waitUntilElementIsDisplayed(GlobalPageElements.pop_PopUpElement);
		AutomationAssert.verifyTrue(isDisplayed(FilesTab.pop_StartNewDiscussion));
		verifyElementText(FilesTab.pop_StartNewDiscussionHeader, expectedText);
	}

	public void verifyUserExistsInToField(String expectedText) throws InterruptedException {

		try {
			AutomationAssert.verifyTrue(getCount(FilesTab.css_NewDiscussionToUserCount) > 0);
		} catch (Throwable t) {
			sendKeys(FilesTab.txt_NewDiscussionToUserField, System.getProperty("secondary.username"));
			sendKeys(FilesTab.txt_NewDiscussionToUserField, Keys.TAB);
			AutomationAssert.verifyTrue(getCount(FilesTab.css_NewDiscussionToUserCount) > 0);
		}
		verifyElementText(FilesTab.lbl_NewDiscussionToField, expectedText);
	}

	public void enterTitleText() throws InterruptedException {
		commentTitle = commentTitle + dateUtils.getEpoch();
		commentDesc = javaUtils.getRandomString(25);
		sendKeys(FilesTab.txt_NewDiscussionTitleInput, commentTitle);
		sendKeys(FilesTab.txt_NewDiscussionDescInput, commentDesc);
		collectionDataMap.put("Comment Title", commentTitle);
		log.info("Collected Data: "+collectionDataMap.toString());
	}

	public void verifyTitleLabel(String expectedLabel) {

		verifyElementText(FilesTab.lbl_NewDiscussionTitleField, expectedLabel);
	}

	public void associateAtleastOneDocument(int count) throws InterruptedException {
		/*if (TestDriverFactory.scenario.getName().equalsIgnoreCase(AdoddleScenariosPool.CREATE_COMMENT_WITH_ATTACHMENTS_ASSOCICATIONS))*/
		clickElementAndWait(FilesTab.btn_NewDiscussionMoreOptions);
		clickElementAndWaitForElement(FilesTab.lnk_NewDiscussionMoreOptionssAssociateDoc, FilesTab.pop_AssociateFileModel);
		
		try {
			AutomationAssert.verifyTrue(getCount(FilesTab.css_PopAssociateFileListingCount) >= count);
		} catch (Throwable t) {
			log.info("TESTDATA: Files count is not enough");
			clickElementAndWait(FilesTab.btn_AssociateFileCancel);
			clickElementAndWait(FilesTab.btn_NewDiscussionMoreOptionsClose);
			return;
		}

		sendKeys(FilesTab.txt_PopAssociateFileSelectFileSearch, "BatchPrintAutomationTestDataFile");
		sendKeys(FilesTab.txt_PopAssociateFileSelectFileSearch, Keys.ENTER);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);

		List<WebElement> filesListCheckboxElements = findElements(By.cssSelector("div[id='docListingSection'] div[id='assocFileContent'] div[id='listing'] div[id='adTableBody'] div[class*='rows'] input[class='selectRow']")); 
		List<WebElement> filesListDocRefElements = findElements(By.cssSelector("div[id='docListingSection'] div[id='assocFileContent'] div[id='listing'] div[id='adTableBody'] div[class*='rows'] div[class*='col-docRef'] a"));
		
		for (int index = 0; index < count ; index++) {
			clickElementAndWait(filesListCheckboxElements.get(index));
			expectedAssociationFiles.add(getElementText(filesListDocRefElements.get(index)));
		}
		
		clickElementAndWait(FilesTab.btn_PopAssociateFileSelectFileSave);
		waitUntilElementIsDisplayed(FilesTab.pop_AssociatedDocListModel);
		clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_PopAssociateDocsListModelSave, FilesTab.btn_PopAssociateDocsListModelSave);
		//waitUntilElementIsDisplayed(FilesTab.lnk_NewDiscussionShowFiles);

	}

	public void associateAtleastOneDiscussion(int count) {

		clickElementAndWaitForElement(FilesTab.btn_NewDiscussionMoreOptions, FilesTab.lnk_NewDiscussionMoreOptionsAssociateDiscussion);
		clickElementAndWaitForElement(FilesTab.lnk_NewDiscussionMoreOptionsAssociateDiscussion, FilesTab.pop_AssociateDiscussionModel);

		try {
			AutomationAssert.verifyTrue(getCount(FilesTab.css_PopAssociateDiscussionListingCount) >= count);
		} catch (Throwable t) {
			log.info(t.getStackTrace());
			log.info("TESTDATA: Discussions count is not enough");
			clickElementAndWaitForElement(FilesTab.btn_AssociateDiscussionCancel, FilesTab.btn_NewDiscussionMoreOptionsClose);
			clickElementAndWait(FilesTab.btn_NewDiscussionMoreOptionsClose);
			return;
		}

		List<WebElement> discussionListCheckboxElements = findElements(By.cssSelector("div[id='discussListingSection'] div[id='assocDiscussionsContent'] div[id='listing'] div[id='adTableBody'] div[class*='rows'] input[class='selectRow']")); 
		List<WebElement> discussionListIDElements = findElements(By.cssSelector("div[id='discussListingSection'] div[id='assocDiscussionsContent'] div[id='listing'] div[id='adTableBody'] div[class*='rows'] div[class*='col-code-fixed-width'] a"));
		for (int index = 0; index < count; index++) {
			clickElementAndWait(discussionListCheckboxElements.get(index));
			expectedDiscussionIDs.add(getElementText(discussionListIDElements.get(index)).split("\\(")[0].trim());
		}
		clickElementAndWait(FilesTab.btn_AssociateDiscussionSave);
		waitUntilElementIsDisplayed(FilesTab.pop_AssociatedDiscussionListModel);
		clickElementAndWait(FilesTab.btn_AssociateDiscussionListModelSave);
		waitUntilElementIsDisplayed(FilesTab.lnk_NewDiscussionShowDiscussions);
	}

	public void associateAtleastOneForm(int count) {

		clickElementAndWaitForElement(FilesTab.btn_NewDiscussionMoreOptions, FilesTab.lnk_NewDiscussionMoreOptionsAssociateForm);
		clickElementAndWaitForElement(FilesTab.lnk_NewDiscussionMoreOptionsAssociateForm, FilesTab.pop_AssociateFormModel);

		try {
			AutomationAssert.verifyTrue(getCount(FilesTab.css_PopAssociateFormListingCount) >= count);
		} catch (Throwable t) {
			log.info(t.getStackTrace());
			log.info("TESTDATA: Forms count is not enough");
			clickElementAndWait(FilesTab.btn_AssociateFormCancel);
			clickElementAndWait(FilesTab.btn_NewDiscussionMoreOptionsClose);
			return;
		}

		List<WebElement> formListCheckboxElements = findElements(By.cssSelector("div[id='assocCommsListingSection'] div[id='assocCommsContent'] div[id='listing'] div[id='adTableBody'] div[class*='rows'] input[class='selectRow']")); 
		List<WebElement> formListIDElements = findElements(By.cssSelector("div[id='assocCommsListingSection'] div[id='assocCommsContent'] div[id='listing'] div[id='adTableBody'] div[class*='rows'] div[class*='col-code-fixed-width'] a"));
		
		for (int index = 0; index < count; index++) {
			clickElementAndWait(formListCheckboxElements.get(index));
			if (getElementText(formListIDElements.get(index)).contains("("))
				expectedFormIDs.add(getElementText(formListIDElements.get(index)).split("\\(")[0]);
			else
				expectedFormIDs.add(getElementText(formListIDElements.get(index)));
		}

		clickElementAndWait(FilesTab.btn_AssociateFormSave);
		waitUntilElementIsDisplayed(FilesTab.pop_AssociatedFormListModel);
		clickElementAndWait(FilesTab.btn_AssociateFormListModelSave);
		waitUntilElementIsDisplayed(FilesTab.lnk_NewDiscussionShowForms);

	}

	public void clickSubmitButton(String expectedText) {
		waitUntilElementIsDisplayed(FilesTab.btn_NewDiscussionSubmit);
		clickElementAndWait(FilesTab.btn_NewDiscussionSubmit);
	}

	public void verifySuccessMessage() {
		try {
			waitUntilElementIsDisplayed(FilesTab.lbl_FileAddCommentSuccessMsg);
			verifyElementText(FilesTab.lbl_FileAddCommentSuccessMsg,
					AdoddleCommonStringPool.MSG_COMMENT_CREATED_SUCCESS);
		} catch (Throwable t) {
			log.error(t.getStackTrace());
			log.error("Success Message could not be verified");
		}

	}

	public void verifyCommentOnDiscussionTab(String flag, String expectedText) {
		navigateToDiscussionTab();
		searchDiscussions(commentTitle);
		if(!flag.contains("Multi"))
			waitUntilElementCountToBe(DiscussionsTab.css_DiscussionListingDiscussionsCount, 1);
		countFlag = flag.contains("Multi") ? (getCount(DiscussionsTab.css_DiscussionListingDiscussionsCount) > 1)
				: (getCount(DiscussionsTab.css_DiscussionListingDiscussionsCount) == 1);
		AutomationAssert.verifyTrue(String.valueOf(getCount(DiscussionsTab.css_DiscussionListingDiscussionsCount)), countFlag);
		AutomationAssert.verifyTrue(getElementText(DiscussionsTab.lnk_DiscussionFirstCommentTitle).equalsIgnoreCase(commentTitle));
		if (flag.contains("Private"))
			AutomationAssert.verifyTrue(isDisplayed(DiscussionsTab.img_DiscussionFirstCommentPrivateImage));
		AutomationAssert.verifyTrue(getElementText(DiscussionsTab.lnk_DiscussionFirstCommentTitle).equalsIgnoreCase(
				commentTitle));

		AdoddleScenarioMarkers.createPrivateCommentFlag = true;
	}

	public void verifyCommentOnFilesDetails(String singleMultiFlag) {
		for (String fileName : commentOnFiles) {
			parentWindow = getCurrentWindow();

			searchFiles(fileName);
			waitUntilElementIsDisplayed(FilesTab.lnk_DocListingFirstDocRef);
			clickElementAndSwitchWindow(FilesTab.lnk_DocListingFirstDocRef);
			
			try {
				if(!fileBetaViewFlag)
					clickElementAndWait(FilesTab.lnk_FileViewLHDiscussionBlock);
			} catch (Throwable t) {
				waitUntilElementIsDisplayed(FilesTab.lnk_ViewModelBlock);
				clickElementAndWait(FilesTab.lnk_ViewModelBlock);
			}
			
			if(!fileBetaViewFlag)
				clickElementAndWaitForElement(DiscussionsTab.lnk_firstDiscussionAttachmentLink, DiscussionsTab.pop_AttachmentAndAssociationPopup);
			else {
				clickElementAndWaitForElement(DiscussionsTab.lnk_BetafirstDiscussionExpandComment, DiscussionsTab.lnk_BetafirstDiscussionAttachmentLink);
				clickElementAndWaitForElement(DiscussionsTab.lnk_BetafirstDiscussionAttachmentLink, DiscussionsTab.lnk_BetaAttachmentAndAssociationFooter);
			}

			verifyFileAssociations();
			verifyAssociationForms();
			verifyAssociationDiscussions();

			if (singleMultiFlag.contains("Single"))
				verifyAttachments();

			sendActionKeys(Keys.ESCAPE);

			closeCurrentWindow();
			switchPreviousWindow(parentWindow);
		}

	}

	public void verifyFileAssociations() {
		clickElementAndWait(fileBetaViewFlag ? DiscussionsTab.lnk_BetaFooterAttachmentAndAssociationPopupFiles : DiscussionsTab.lnk_PopAttachmentAndAssociationPopupFiles);
		waitUntilElementIsInvisible(GlobalPageElements.ele_LoadingCircle);
		List<WebElement> associationElements = findElements(fileBetaViewFlag ? DiscussionsTab.css_BetaAttachmentAndAssociationFilesDocRefs : DiscussionsTab.css_PopAttachmentAndAssociationFilesDocRefs);
		List<String> actualAssociationFileDocRefs = new ArrayList<String>();
		
		for (WebElement e : associationElements)
			actualAssociationFileDocRefs.add(e.getText());

		for (String docRef : expectedAssociationFiles)
			AutomationAssert.verifyTrue(actualAssociationFileDocRefs.toString() + " should contain " + docRef,
					actualAssociationFileDocRefs.contains(docRef));

	}

	public void verifyAttachments() {

		clickElementAndWaitForInvisibilityOfElement(DiscussionsTab.lnk_PopAttachmentAndAssociationPopupAttachments, GlobalPageElements.ele_LoadingCircle);
		List<WebElement> attachmentElements = findElements(DiscussionsTab.css_PopAttachmentAndAssociationAttachmentsFileNames);
		List<String> actualAttachmentFileNames = new ArrayList<String>();

		for (WebElement e : attachmentElements)
			actualAttachmentFileNames.add(e.getText());

		for (String attachment : expectedAttachmentFiles)
			AutomationAssert.verifyTrue(actualAttachmentFileNames.toString() + " should contain " + attachment,
					actualAttachmentFileNames.contains(attachment));

		for (String attachment : actualAttachmentFileNames)
			AutomationAssert.verifyTrue(expectedAttachmentFiles.toString() + " should contain " + attachment,
					expectedAttachmentFiles.contains(attachment));

	}

	public void verifyAssociationForms() {
		clickElementAndWait(fileBetaViewFlag ? DiscussionsTab.lnk_BetaFooterAttachmentAndAssociationPopupForms : DiscussionsTab.lnk_PopAttachmentAndAssociationPopupForms);
		waitUntilElementIsInvisible(GlobalPageElements.ele_LoadingCircle);
		if(fileBetaViewFlag)
			waitUntilElementIsDisplayed(DiscussionsTab.chk_BetaFooterAttachmentsAndAssociationHeaderCheck);
		waitUntilElementCountToBe(fileBetaViewFlag ? DiscussionsTab.css_BetaFooterAttachmentAndAssociationPopupFormIDs : DiscussionsTab.css_PopAttachmentAndAssociationPopupFormIDs, 3);
		List<WebElement> formIDElements = findElements(fileBetaViewFlag ? DiscussionsTab.css_BetaFooterAttachmentAndAssociationPopupFormIDs : DiscussionsTab.css_PopAttachmentAndAssociationPopupFormIDs);
		List<String> actualformIDs = new ArrayList<String>();

		for (WebElement e : formIDElements)
			actualformIDs.add(e.getText());

		for (String formID : expectedFormIDs)
			AutomationAssert.verifyTrue(actualformIDs.toString() + " should contain " + formID,
					actualformIDs.contains(formID));

		for (String formID : actualformIDs)
			AutomationAssert.verifyTrue(expectedFormIDs.toString() + " should contain " + formID,
					expectedFormIDs.contains(formID));
	}

	public void verifyAssociationDiscussions() {
		clickElementAndWait(fileBetaViewFlag ? DiscussionsTab.lnk_BetaFooterAttachmentAndAssociationDiscussions : DiscussionsTab.lnk_PopAttachmentAndAssociationPopupDiscussions);
		if(fileBetaViewFlag)
			waitUntilElementIsDisplayed(DiscussionsTab.chk_BetaFooterAttachmentsAndAssociationHeaderCheck);
		waitUntilElementCountToBeMoreThan(fileBetaViewFlag ? DiscussionsTab.css_BetaFooterAttachmentAndAssociationPopupDiscussionIDs : DiscussionsTab.css_PopAttachmentAndAssociationPopupDiscussionIDs, 2);
		List<WebElement> discussionIDElements = findElements(fileBetaViewFlag ? DiscussionsTab.css_BetaFooterAttachmentAndAssociationPopupDiscussionIDs : DiscussionsTab.css_PopAttachmentAndAssociationPopupDiscussionIDs);
		List<String> actualDiscussionIDs = new ArrayList<String>();

		for (WebElement e : discussionIDElements) {
			waitUntilElementContainsText(e, "COM");
			actualDiscussionIDs.add(e.getText());
		}

		for (String dID : expectedDiscussionIDs) {
			AutomationAssert.verifyTrue(actualDiscussionIDs.toString() + " should contain " + dID,
					actualDiscussionIDs.contains(dID.split("\\(")[0].trim()));
		}

		for (String dID : actualDiscussionIDs)
			AutomationAssert.verifyTrue(expectedDiscussionIDs.toString() + " should contain " + dID,
					expectedDiscussionIDs.contains(dID.split("\\(")[0].trim()));
	}

	public void verifyMultiCommentAssociation() {
		log.info("covered in previous definition");
	}

	public void verifyMultiCommentAssociationForms() {
		log.info("covered in previous definition");
	}

	public void verifyMultiCommentAssociationDiscussions() {
		log.info("covered in previous definition");
	}

	public void verifyCommentIsNotAvailable() {
		searchComments(commentTitle);
		AutomationAssert.verifyTrue(!isDisplayed(DiscussionsTab.lnk_DiscussionFirstCommentTitle));
		AutomationAssert.verifyTrue(getCount(DiscussionsTab.css_DiscussionListingDiscussionsCount) == 0);
	}
}
