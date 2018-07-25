package org.asite.automation.adoddle.p2.scripts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SingleMultipleFormsStatusChangeScripts extends AdoddleCommonAppMethods {
	private String			updatedFormStatus;
	private final static Logger	log	= AutomationLogger.getInstance().getLogger(SingleMultipleFormsStatusChangeScripts.class);

	public void selectFormApp(String project, String appGroup, String appType) {
		List<String> appTypeCollectionList = new ArrayList<String>();
		clickElementWithText(project);
		clickElementWithText(appGroup);
		clickElementWithText(appType);
		appTypeCollectionList.add(appType);
		collectionDataMap.put("AppType List", appTypeCollectionList.toString());
	}

	/* ********************** Need To Move In Common ********************** */

	public void verifyCreatedFormWithAction(String action)
	{
		collectionDataMap.put("Form Title List", formList.toString());
		searchForms(formTitle.substring(0, formTitle.length()-1));
		AutomationAssert.verifyTrue(findElements(ProjectFormsTab.css_FormListingFormTitles).size() == formList.size());
		
		int index = 0;
		for (WebElement form1 : findElements(ProjectFormsTab.css_FormListingFormTitles)) {
			AutomationAssert.verifyTrue(formList.contains(form1.getText()));
			
			if (action != null) {
				List<String> actionList1 = new ArrayList<String>();
				List<String> actionList2 = new ArrayList<String>();
				
				mouseHover(By.xpath(".//div[@index='" + index + "']//div[contains(@class,'actionName')]//span[contains(text(),'+')]"));
				waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
				
				for (WebElement actualAction : findElements(GlobalPageElements.css_firstActionsPopoverContentLinks))
					actionList1.add(actualAction.getText().split(":")[1]);
				Collections.addAll(actionList2, action.split(","));
				log.info("actual action List : " + actionList1);
				log.info("expected action List : " + actionList2);
				AutomationAssert.verifyTrue(actionList1.containsAll(actionList2));
			}
			index++;
		}
	}

	public void verifyContextOptionDisabled(String contextOption) {
		waitForCompletePageLoad();
		AutomationAssert.verifyTrue(isDisplayed(By.xpath(".//ul[@class='context-menu-list context-menu-root']//li[not(contains(@style,'display: none'))][contains(@class,'disabled')]//span[text()='" + contextOption + "']")));
		reloadPage();
		waitForCompletePageLoad();
	}

	public void verifyMoreOptionsOptionDisabled(String option) {
		waitForCompletePageLoad();
		AutomationAssert.verifyTrue(isDisplayed(By.xpath(".//div[contains(@style,'display: block')]//a[contains(@class,'disabled')]//following::strong[1][contains(text(),'" + option + "')]")));
		reloadPage();
		waitForCompletePageLoad();
	}

	/* ********************** Need To Move In Common ********************** */

	public void verifyChangeStatusDropdownAndButtonDisabledOnPopup()
	{
		waitForCompletePageLoad();
		clickElementAndWait(ProjectFormsTab.sel_PopStatusChangeDropdown);
		AutomationAssert.verifyTrue(findElements(ProjectFormsTab.css_PopStatusChangeDropdownOptionList).size() < 2);
		AutomationAssert.verifyTrue(!isEnabled(ProjectFormsTab.btn_PopChangeStatusButton));
		clickOnPopupButton("Cancel");
	}
	
	public void verifyChangeStatusHyperlinkDisabled(String status)
	{
		updatedFormStatus = status;
		verifyChangeStatusDisabled();
	}
	
	public void verifyChangeStatusDisabled() {
		switchToSecondWindow();
		waitForCompletePageLoad();
		waitUntilElementContainsText(ProjectFormsTab.btn_BetaViewViewFormStatusChangeButtonDisabled, updatedFormStatus);
		AutomationAssert.verifyTrue(!isDisplayed(ProjectFormsTab.btn_BetaViewViewFormUpdatedStatus));
	}

	public void verifyListOfFormsStatusChangeDisabled() {
		waitForCompletePageLoad();
		searchForms(formTitle.substring(0, formTitle.length()-1));
		contextClick(ProjectFormsTab.lnk_FirstFormTitle);
		clickContextMenuOptionWithText(AdoddleCommonStringPool.OPTION_EDIT);
		clickContextMenuOptionWithText(AdoddleCommonStringPool.STATUS);
		verifyChangeStatusDisabled();
		closeSecondWindow();
	}

	public void selectFormAndSelectContextClickOption(String listOfForms, String option1, String option2) {
		if (listOfForms.contains("Single"))
			searchForms(formList.get(0));
		else
			searchForms(formTitle.substring(0, formTitle.length()-1));

		waitForCompletePageLoad();
		if (listOfForms.contains("Closed")) {
			int i = 0;
			for (WebElement checkbox : findElements(ProjectFormsTab.css_FormsTabCheckboxList)) {
				if (i != 0)
					checkbox.click();
				i++;
			}
			contextClickWithLink(formList.get(1));
		}
		else {
			if (!isSelected(FilesTab.chk_MultiFilesSelectionCheckbox))
				clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);
			contextClickWithLink(formList.get(0));
		}
		clickContextMenuOption(option1, option2);
	}

	public void selectFormAndSelectMoreOptionsOption(String listOfForms, String option) {
		if (listOfForms.contains("Single"))
			searchForms(formList.get(0));
		else
			searchForms(formTitle.substring(0, formTitle.length()-1));
		
		if (!isSelected(FilesTab.chk_MultiFilesSelectionCheckbox))
			clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);
		clickElementAndWait(GlobalPageElements.lnk_MoreOptions);
		waitUntilElementContainsText(By.xpath(".//div[contains(@style,'display: block')]//a//following::strong[1][contains(text(),'" + option + "')]"), option, 5);
		if (isDisplayed(ProjectFormsTab.lnk_FormsTabPopMoreOptionsActionsChangeStatusLink))
			clickElementAndWait(ProjectFormsTab.lnk_FormsTabPopMoreOptionsActionsChangeStatusLink);
	}

	public void changeFormStatus(String listOfForms, String button, String status) {
		if (listOfForms.contains("Single"))
			switchToSecondWindow();

		if (listOfForms.contains("Single")) {

				waitUntilElementIsDisplayed(ProjectFormsTab.sel_BetaViewStatusChangeFormStatusDropdown);
				if (status != null)
					selectByVisibleText(ProjectFormsTab.sel_BetaViewStatusChangeFormStatusDropdown, status);
				else {
					selectByIndex(ProjectFormsTab.sel_BetaViewStatusChangeFormStatusDropdown, 2);
					if (updatedFormStatus != null && new Select(findElement(ProjectFormsTab.sel_BetaViewStatusChangeFormStatusDropdown)).getFirstSelectedOption().getText().equalsIgnoreCase(updatedFormStatus))
						selectByIndex(ProjectFormsTab.sel_BetaViewStatusChangeFormStatusDropdown, 3);
					if (new Select(findElement(ProjectFormsTab.sel_BetaViewStatusChangeFormStatusDropdown)).getFirstSelectedOption().getText().equalsIgnoreCase(AdoddleCommonStringPool.STATUS_CLOSED))
						selectByIndex(ProjectFormsTab.sel_BetaViewStatusChangeFormStatusDropdown, 4);
				}
				updatedFormStatus = new Select(findElement(ProjectFormsTab.sel_BetaViewStatusChangeFormStatusDropdown)).getFirstSelectedOption().getText();
				log.info("updatedFormStatus : " + updatedFormStatus);
				sendKeys(ProjectFormsTab.txt_BetaViewFormStatusChangeReasonNote, javaUtils.getRandomString(20));
				clickElementAndWait(ProjectFormsTab.btn_BetaViewPopFormStatusChangeChangeStatus);
				waitUntilElementIsInvisible(ProjectFormsTab.sel_BetaViewStatusChangeFormStatusDropdown);
		}
		else
			changeMultipleFormStatus(button, status);

		waitForCompletePageLoad();
	}
	
	private void changeMultipleFormStatus(String button, String status) {
		waitUntilElementIsDisplayed(ProjectFormsTab.sel_PopStatusChangeDropdown);
		if (status != null)
			selectByVisibleText(ProjectFormsTab.sel_PopStatusChangeDropdown, status);
		else {
			selectByIndex(ProjectFormsTab.sel_PopStatusChangeDropdown, 2);
			if (updatedFormStatus != null && new Select(findElement(ProjectFormsTab.sel_PopStatusChangeDropdown)).getFirstSelectedOption().getText().equalsIgnoreCase(updatedFormStatus))
				selectByIndex(ProjectFormsTab.sel_PopStatusChangeDropdown, 3);
			if (new Select(findElement(ProjectFormsTab.sel_PopStatusChangeDropdown)).getFirstSelectedOption().getText().equalsIgnoreCase(AdoddleCommonStringPool.STATUS_CLOSED))
				selectByIndex(ProjectFormsTab.sel_PopStatusChangeDropdown, 4);
		}
		updatedFormStatus = new Select(findElement(ProjectFormsTab.sel_PopStatusChangeDropdown)).getFirstSelectedOption().getText();
		log.info("updatedFormStatus : " + updatedFormStatus);
		sendKeys(ProjectFormsTab.txt_PopChangeStatusReasonInput, javaUtils.getRandomString(20));
		clickOnPopupButton(button);
		waitUntilElementIsInvisible(ProjectFormsTab.sel_PopStatusChangeDropdown);
	}
	
	public void verifyUpdatedStatusOnViewFormAndDisabled() {
		waitForCompletePageLoad();
		waitUntilElementContainsText(ProjectFormsTab.btn_BetaViewViewFormStatusChangeButtonDisabled, updatedFormStatus);
	}

	public void verifyUpdatedStatusOnViewForm() {
		
		waitForCompletePageLoad();
		waitUntilElementIsClickable(ProjectFormsTab.btn_BetaViewViewFormUpdatedStatus);
		try {
			waitUntilElementContainsText(ProjectFormsTab.ele_BetaViewViewFormUpdatedStatus, updatedFormStatus);
		} catch (Throwable t) {
			log.info("Failed For Waiting Status Changes");
		}
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.ele_BetaViewViewFormUpdatedStatus).contains(updatedFormStatus));
		waitForCompletePageLoad();
	}
	
	public void verifyUpdatedStatusOnViewFormWithDisabledLink() {
		waitUntilElementContainsText(ProjectFormsTab.btn_BetaViewViewFormStatusChangeButtonDisabled, updatedFormStatus);
		waitForCompletePageLoad();
	}
	

	public void verifyUpdatedStatusAndAssignedAndCompletedActions(String listOfForms, String completeActions, String assignActions) {
		waitForCompletePageLoad();
		/* Remove this bracket Code After Resolving [NOODLE-72532] & [NOODLE-72536] */
		navigateTabByText(AdoddleCommonStringPool.TAB_PROJECT_FORMS);
		waitForCompletePageLoad();
		/* Remove this bracket Code After Resolving [NOODLE-72532] & [NOODLE-72536] */
		if(listOfForms.contains("Single")) {
			searchForms(formList.get(0));
			AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.ele_FirstFormStatus).equalsIgnoreCase(updatedFormStatus));
			verifyActionAssignedAndCompleted(0, completeActions, assignActions);
		}
		else {
			searchForms(formTitle.substring(0, formTitle.length()-1));
			
			if(listOfForms.contains("Closed")) {
				int i=0;
				for (WebElement form : findElements(ProjectFormsTab.css_FormListingFormTitles)) {
					if(!form.getText().contains(formList.get(0))) {
						AutomationAssert.verifyTrue(getElementText(By.xpath(".//div[@index='"+i+"']//div[contains(@class,'status')][text()]")).equalsIgnoreCase(updatedFormStatus));
						verifyActionAssignedAndCompleted(i, completeActions, assignActions);
					}
					i++;
				}
			} else {
				int i=0;
				for (WebElement f : findElements(ProjectFormsTab.css_FormListingFormTitles)) {
					log.info("batchForm :"+f);
					AutomationAssert.verifyTrue(getElementText(By.xpath(".//div[@index='"+i+"']//div[contains(@class,'status')][text()]")).equalsIgnoreCase(updatedFormStatus));
					verifyActionAssignedAndCompleted(i, completeActions, assignActions);
					i++;
				}
			}
		}
	}
	
	private void verifyActionAssignedAndCompleted(int actionIndex, String completeActions, String assignActions) {
		if (completeActions != null)
			verifyActionCompleted(actionIndex, completeActions);
		if (assignActions != null)
			verifyActionAssigned(actionIndex, assignActions);
	}
	
	public void searchAndViewSingleForm() {
		waitForCompletePageLoad();
		searchForms(formList.get(0));
		clickElementAndWait(ProjectFormsTab.lnk_FirstFormTitle);
		switchToSecondWindow();
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.ele_BetaViewViewFormTitle).contains(formList.get(0)));
		waitForCompletePageLoad();
	}
	
	public void editAndDistributeFormWithChangeStatus() {
		clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormDetailsActionDropdownButton, ProjectFormsTab.lnk_BetaViewFormDetailsActionEditAndDistribute);
		clickElementAndWait(ProjectFormsTab.lnk_BetaViewFormDetailsActionEditAndDistribute);
		clear(ProjectFormsTab.txt_PopCreateFormGroupCode);
		waitForCompletePageLoad();
		selectByIndex(ProjectFormsTab.sel_CreateFormStatusDropdown, 2);
		if (new Select(findElement(ProjectFormsTab.sel_CreateFormStatusDropdown)).getFirstSelectedOption().getText().equalsIgnoreCase(AdoddleCommonStringPool.STATUS_CLOSED))
			selectByIndex(ProjectFormsTab.sel_CreateFormStatusDropdown, 3);
		updatedFormStatus = new Select(findElement(ProjectFormsTab.sel_CreateFormStatusDropdown)).getFirstSelectedOption().getText();
		log.info("updatedFormStatus : " + updatedFormStatus);
		clickElementAndWait(ProjectFormsTab.btn_CreateFormSendButton);
		switchDefault();
	}
	
	public void verifyChangeableStatusOnPopup() {
		waitForCompletePageLoad();
		AutomationAssert.verifyTrue(findElements(ProjectFormsTab.css_PopStatusChangeChangeableFormsList).size() == 1);
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.css_PopStatusChangeChangeableFormsList).equalsIgnoreCase(formList.get(0)));
	}
	
	public void verifyStatusAndActionForRestrictFormYesAndNo(String completeAction, String IncompleteAction) {
		waitForCompletePageLoad();
		searchForms(formTitle.substring(0, formTitle.length()-1));
		
		int i=0;
		for (WebElement form : findElements(ProjectFormsTab.css_FormListingFormTitles)) {
			if(form.getText().contains(formList.get(0))) {
				AutomationAssert.verifyTrue(getElementText(By.xpath(".//div[@index='"+i+"']//div[contains(@class,'status')][text()]")).equalsIgnoreCase(updatedFormStatus));
				verifyActionCompleted(i, completeAction);
			}
			else {
				AutomationAssert.verifyTrue(getElementText(By.xpath(".//div[@index='"+i+"']//div[contains(@class,'status')][text()]")).equalsIgnoreCase(AdoddleCommonStringPool.STATUS_OPEN));
				verifyActionAssigned(i, IncompleteAction);
			}
			i++;
		}
	}

	public void clickOnPopupButton(String buttonName) {
		clickElementAndWait(By.xpath(".//div[contains(@style,'display: block')]//button[text()='" + buttonName + "']"));
	}
	
	private void switchToSecondWindow() {
		CreateModelCommentScripts.parentWindow = getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();
	}

	private void closeSecondWindow() {
		closeCurrentWindow();
		switchPreviousWindow(CreateModelCommentScripts.parentWindow);
		waitForCompletePageLoad();
	}
}
