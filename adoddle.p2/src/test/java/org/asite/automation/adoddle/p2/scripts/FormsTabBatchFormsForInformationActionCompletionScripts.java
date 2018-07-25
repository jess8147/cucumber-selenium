package org.asite.automation.adoddle.p2.scripts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleModelsLocators.ModelsTab;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class FormsTabBatchFormsForInformationActionCompletionScripts extends AdoddleCommonAppMethods
{
	final private List<String> formTitleList = new ArrayList<String>();
	private String formTitle;
	
	public void clearFormList()
	{
		formTitleList.clear();
		formTitle = null;
		index = 0;
	}
	
	public void verifyCreatedFormWithAction(String action)
	{
		collectionDataMap.put("Form Title List", formTitleList.toString());
		
		for (String fTitle : formTitleList) {
			searchForms(fTitle);
			if(!fTitle.endsWith("0")) {
				List<String> actionList1 = new ArrayList<String>();
				List<String> actionList2 = new ArrayList<String>();

				waitUntilElementIsDisplayed(GlobalPageElements.lnk_FirstMyActionCountPopOver);
				mouseHoverElement(GlobalPageElements.lnk_FirstMyActionCountPopOver);
				waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);

				for (WebElement actualAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks))
					actionList1.add(actualAction.getText().split(":")[1]);
				Collections.addAll(actionList2, action.split(","));
				Assert.assertTrue(eStringUtils.getInEqualityStringError(actionList1.toString(), actionList2.toString()), actionList1.size() == actionList2.size());
				Assert.assertTrue(eStringUtils.getInEqualityStringError(actionList1.toString(), actionList2.toString()), actionList1.containsAll(actionList2));
			}
			Assert.assertTrue(getElementText(ProjectFormsTab.lnk_FirstFormTitle).equalsIgnoreCase(fTitle));
		}
	}
	
	public void selectBatchFormsAndSelectContextClickOption(String option1, String option2)
	{
		searchForms(formTitle.substring(0, formTitle.length()-1));
		waitForCompletePageLoad();
		if (!isSelected(FilesTab.chk_MultiFilesSelectionCheckbox))
			clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);
		contextClickWithLink(formTitleList.get(0));
		clickContextMenuOption(option1, option2);
	}
	
	public void selectFormAndSelectMoreOptionsOption()
	{
		searchForms(formTitle.substring(0, formTitle.length()-1));
		if (!isSelected(FilesTab.chk_MultiFilesSelectionCheckbox))
			clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);
		clickElementAndWaitForElement(ProjectFormsTab.lnk_FormListingMoreOptions, ProjectFormsTab.lnk_FormsTabPopMoreOptionsActionsForInformationLink);
		clickElementAndWait(ProjectFormsTab.lnk_FormsTabPopMoreOptionsActionsForInformationLink);
	}
	
	public void verifyForInformationActionFormsOnPopup()
	{
		List<String> actualFormList = new ArrayList<String>();
		List<String> expectedFormList = new ArrayList<String>();
		waitForCompletePageLoad();
		for (WebElement fileName : findElements(ModelsTab.css_PopAssociateFormsFormTitleList))
			actualFormList.add(getElementText(fileName));
		log.info("actualFormList :" + actualFormList);
		
		for (String form : formTitleList) {
			if(!form.endsWith("0"))
				expectedFormList.add(form);
		}
		log.info("expectedFormList :" + expectedFormList);
		Assert.assertTrue(eStringUtils.getInEqualityStringError(actualFormList.size(), expectedFormList.size()), actualFormList.size() == expectedFormList.size());
		Assert.assertTrue(eStringUtils.getContainsStringError(actualFormList.toString(), expectedFormList.toString()), actualFormList.containsAll(expectedFormList));
	}
	
	public void verifyFormsActionCompleted(String action)
	{
		for (String formTitle : formTitleList) {
			if(!formTitle.endsWith("0")) {
				searchForms(formTitle);
				verifyActionCompleted(0, action);
			}
		}
	}
	
	
	/***** Create Form *****/
	
	public void createForm(String distributeUsers, String actions) throws InterruptedException {
		clickElementAndWait(ProjectFormsTab.btn_CreateForm);
		switchDefault();

		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		if (distributeUsers != null) {
			distributeUsersWithSingleAction(distributeUsers, actions);
		}
		formTitle = "AutoForm" + epoch + index;
		log.info("formTitle :" + formTitle);
		formTitleList.add(formTitle);
		index++;

		sendKeys(ProjectFormsTab.txt_CreateFormTitle, formTitle);
		clear(ProjectFormsTab.txt_PopCreateFormGroupCode);
		waitForCompletePageLoad();
		executeJScript(AdoddleCommonJQueries.betaViewCreateFormScrollMaxDownQuery);
		clickElementAndWaitForElement(ProjectFormsTab.img_BetaViewCreateFormCalendar, ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
		clickElementAndWait(ProjectFormsTab.ele_CreateFormCalendarCurrentDate);
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_CreateFormSendButton, ProjectFormsTab.btn_CreateFormSendButton);
		switchDefault();
		log.info("formTitleList : " + formTitleList);
	}
}
