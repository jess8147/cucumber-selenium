/**  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleDiscussionsLocators.DiscussionsTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class CommentAssociationsForm extends AdoddleCommonAppMethods {

	private String formSubject = "Automation_Comment_Subject";
	private String formDescription;
	private List<String> commentList1 = new ArrayList<String>();
	private List<String> commentList2 = new ArrayList<String>();
	public static Logger log = AutomationLogger.getInstance().getLogger(CommentAssociationsForm.class);

	public void validateUnreadCommentListing() {
		log.info("ListCount::" + getCount(DiscussionsTab.lnk_DiscussionCommentIDs));
		Assert.assertTrue(getCount(DiscussionsTab.lnk_DiscussionCommentIDs) > 0);
	}

	public void selectMultipleComment(String optionName) {

		if (optionName.contains(AdoddleCommonStringPool.OPTION_PROJECT_FORM)) {
			addCommentsList1();
			clickElementAndWait(DiscussionsTab.chk_DocListingFirstCheckBox);
			clickElementAndWait(DiscussionsTab.chk_DocListingSecondCheckBox);
			contextClick(DiscussionsTab.lnk_DiscussionFirstCommentID);
			mouseHover(DiscussionsTab.opt_FileContextClickNew);
			clickContextMenuOptionWithText(optionName);

		} else {

			addCommentsList1();
			clickElementAndWait(DiscussionsTab.chk_DocListingFirstCheckBox);
			clickElementAndWait(DiscussionsTab.chk_DocListingSecondCheckBox);
			clickElementAndWait(FilesTab.lnk_FilesTabMoreOptions);

		}
		
		collectionDataMap.put("comment associations", commentList1.toString());
	}

	public void selectAvailableOption() {
		clickElementAndWait(DiscussionsTab.lnk_OptionsStartWorkflow);
	}

	public void verifyCreateFormPopup(String expectedText) {
		try {
			Assert.assertTrue(isDisplayed(ProjectFormsTab.pop_CreateFormWindow));
		} catch (Throwable t) {
			log.error(t.getStackTrace());
			log.error("Create Form Window not found");
		}
		switchIframe(ProjectFormsTab.frm_createFormIframe);
		verifyElementText(ProjectFormsTab.lbl_CreateFormHeader, expectedText);
		switchDefault();
	}

	public void createForm(String formName) {

		switchIframe(ProjectFormsTab.frm_createFormIframe);
		waitUntilElementIsDisplayed(DiscussionsTab.txt_formSearchFilter);
		sendKeys(DiscussionsTab.txt_formSearchFilter, formName);
		waitUntilElementContainsText(DiscussionsTab.css_formSearchResult, "Request For Information");
		clickElementAndWait(DiscussionsTab.css_formSearchResult);

	}

	public void validateNewForm() {
		log.info("Covered in Above Definiton");

	}

	public void selectUserAndSubject(String userName) throws InterruptedException {

		formSubject = formSubject + dateUtils.getEpoch();
		collectionDataMap.put("form", formSubject);
		formDescription = javaUtils.getRandomString(30);
		//if (ResourceHandler.loadProperty("app.view.beta.flag").contains("true")) {
			waitUntilElementIsDisplayed(DiscussionsTab.txt_BetaViewFormInputToField);
			sendKeys(DiscussionsTab.txt_BetaViewFormInputToField, userName);
			findElement(DiscussionsTab.txt_BetaViewFormInputToField).sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));
		/*} else {
			waitUntilElementIsDisplayed(DiscussionsTab.txt_FormInputToField);
			sendKeys(DiscussionsTab.txt_FormInputToField, userName);
			sendKeys(DiscussionsTab.txt_FormInputToField, Keys.TAB);

		}*/
		sendKeys(DiscussionsTab.txt_CreateFormSubject, formSubject);
		sendKeys(DiscussionsTab.txt_CreateFormSubject, Keys.TAB);
		sendKeys(DiscussionsTab.txt_CreateFormDescription, formDescription);
		sendKeys(DiscussionsTab.txt_CreateFormSubject, Keys.TAB);

	}

	public void clickSaveButton() {

		waitUntilElementIsClickable(ProjectFormsTab.btn_CreateFormSendButton);
		clickElement(ProjectFormsTab.btn_CreateFormSendButton);
		switchDefault();
		verifyFormSuccessMessage();

	}

	public void validateFormAndDiscussions() {

		searchForms(formSubject);
		verifyElementText(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle, formSubject);
		mouseHover(ProjectFormsTab.img_ProjectFormsFirstFormAssociatedDiscussion);
		waitUntilElementIsDisplayed(ProjectFormsTab.lnk_FirstFormDiscussionLink);
		clickElementAndWait(ProjectFormsTab.lnk_FirstFormDiscussionLink);
		waitUntilElementIsDisplayed(ProjectFormsTab.pop_AttachmentAndAssociationPopup);
		addCommentsList2();
		Assert.assertTrue(commentList2.containsAll(commentList1));

	}

	public void verifyCreateFormPopupIsOpen() {
		try {
			Assert.assertTrue(isDisplayed(ProjectFormsTab.pop_CreateFormWindow));
		} catch (Throwable t) {
			log.error(t.getStackTrace());
			log.error("Create Form window not found");
		}
		switchIframe(ProjectFormsTab.frm_createFormIframe);
		Assert.assertTrue(isDisplayed(DiscussionsTab.txt_FormInputToField));
	}

	public void verifyFormSuccessMessage() {
		isDisplayed(ProjectFormsTab.txt_FormListingFormSearchInput);

		try {
			waitUntilElementIsDisplayed(DiscussionsTab.lbl_FormAddSuccessMsg);
			verifyElementText(DiscussionsTab.lbl_FormAddSuccessMsg, "Form created successfully");
		} catch (Throwable t) {
			log.error(t.getStackTrace());
			log.error("Success Message could not be verified");
		}
		waitForCompletePageLoad();
	}

	public void addCommentsList1() {

		for (int i = 1; i < 3; i++) {

			commentList1.add(findElement(
					By.cssSelector("div[id ='listing'] div[class*='rows']:nth-child(" + i
							+ ") div[class*='col-oriCommentTitle-fixed-width'] a")).getText().split("_")[1]);

		}

		log.info("CommentList1 size::" + commentList1.size());
	}

	public void addCommentsList2() {

		for (int i = 1; i < 3; i++) {

			commentList2
					.add(findElement(
							By.cssSelector("div[id='biDirectionAssociationModal'] div[class*='innerContainer'] div[id='adTableBody'] :nth-child("
									+ i + ") div[class*='col-commentTitle-fixed-width'] a")).getText().split("_")[1]);

		}

		log.info("CommentList2 size::" + commentList2.size());
	}

}
