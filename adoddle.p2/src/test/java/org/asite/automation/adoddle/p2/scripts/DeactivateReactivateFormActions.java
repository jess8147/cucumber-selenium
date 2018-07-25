package org.asite.automation.adoddle.p2.scripts;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class DeactivateReactivateFormActions extends AdoddleCommonAppMethods {

	private WebElement		uncheckActionRowCheckBox	= null;
	private String			formTitle					= null;
	private String			user1, user2, user3, user4;
	private String			parentHandle				= null;
	private List<String>	distributionUserList		= null;
	private List<String>	actionList					= Arrays.asList(AdoddleCommonStringPool.ACTION_ATTACH_DOCS, AdoddleCommonStringPool.ACTION_DISTRIBUTE, AdoddleCommonStringPool.ACTION_ASSIGN_STATUS);
	private int				actionListSize				= 0;
	public static Logger	log							= AutomationLogger.getInstance().getLogger(DeactivateReactivateDocumentActions.class);

	public void createPrivateFormAndDistribute(String formType, String user1, String user2, String user3, String user4) throws InterruptedException {
		this.user1 = user1;
		this.user2 = user2;
		this.user3 = user3;
		this.user4 = user4;

		distributionUserList = Arrays.asList(user1, user2, user3, user4);
		clickFolderWithTitle(formType);
		clickFolderWithTitle(ResourceHandler.loadProperty("deactivate.reactivate.form.folder"));
		clickElementAndWaitForElement(ProjectFormsTab.btn_CreateForm, ProjectFormsTab.frm_createFormIframe);
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
		formTitle = javaUtils.getRandomString(20);

		/*if (!appBetaViewFlag)
			clickElementAndWaitForElement(ProjectFormsTab.btn_CERFormDistribute, ProjectFormsTab.txt_CERFormDistributeTo);
		else*/
			clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewCERFormDistribute, ProjectFormsTab.txt_BetaViewCERFormDistributeTo);

		actionListSize = actionList.size();
		assignFormActionsToMultipleUsers(distributionUserList, actionList);
		sendKeys(ProjectFormsTab.txt_CERFormTitle, formTitle);
		sendKeys(ProjectFormsTab.txt_CERFormTitle, Keys.TAB);
		sendKeys(ProjectFormsTab.txt_CERFormRichTextBox, javaUtils.getRandomString(30));
		sendKeys(ProjectFormsTab.txt_CERFormRichTextBox, Keys.TAB);
		clear(ProjectFormsTab.txt_EOFFormGroupCode);
		sendKeys(ProjectFormsTab.txt_EOFFormGroupCode, Keys.TAB);
		executeJScript(AdoddleCommonJQueries.scrollWindowMaxDownJQuery);
		clickElementAndWait(findElements(ProjectFormsTab.txt_CERFormDueDatePicker).get(0));
		waitUntilElementIsDisplayed(ProjectFormsTab.lnk_CreateControllerFormOnDateCalenderToday);
		clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.lnk_CreateControllerFormOnDateCalenderToday, ProjectFormsTab.lnk_CreateControllerFormOnDateCalenderToday);
		clickElement(ProjectFormsTab.btn_CreateFormSave);
		collectionDataMap.put("form title", formTitle);
	}

	public void searchCreatedPrivateForm() {
		navigateTab(LandingPage.lnk_ProjectForms);
		searchForms(formTitle);
	}

	public void deactivateReactivateActions(String aType, String user1, String user2, String user3, String user4) {
		parentHandle = clickElementAndSwitchWindow(ProjectFormsTab.lnk_FormListingFirstFormTitle);
		/*if (!appBetaViewFlag) {
			clickElementAndWaitForElement(ProjectFormsTab.lnk_FormViewLHFormHistory, ProjectFormsTab.sel_FormViewHistoryType);
			selectByVisibleText(ProjectFormsTab.sel_FormViewHistoryType, AdoddleCommonStringPool.OPTION_DISTRIBUTION);
			clickElementAndWait(findElements(ProjectFormsTab.css_FormViewHistoryResults).get(0));
			waitUntilElementIsDisplayed(ProjectFormsTab.sel_FormViewHistoryActionDropdown);
		}
		else {*/
			clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormViewFormHistory, ProjectFormsTab.sel_BetaViewFormViewHistoryType);
			selectByVisibleText(ProjectFormsTab.sel_BetaViewFormViewHistoryType, AdoddleCommonStringPool.OPTION_DISTRIBUTION);
		//}

		List<WebElement> distributionListRows = findElements(/*!appBetaViewFlag ? ProjectFormsTab.css_FormViewHistoryDistributionRowList : */ProjectFormsTab.css_BetaViewFormViewHistoryDistributionRowList);

		for (WebElement e : distributionListRows) {
			e.findElement(/*!appBetaViewFlag ? FilesTab.chk_FileHistoryRecordDetailCheckbox : */ProjectFormsTab.chk_BetaViewFormHistoryRecordCheckbox).click();
		/*	if (!appBetaViewFlag && e.findElement(FilesTab.img_FileHistoryRecordDetailDitributionUser).getAttribute("title").contains(user3)) {
				uncheckActionRowCheckBox = e.findElement(FilesTab.chk_FileHistoryRecordDetailCheckbox);
			}
			else*/ if (/*appBetaViewFlag && */e.findElement(ProjectFormsTab.lbl_BetaViewFileHistoryRecordDetailRecipient).getText().contains(user3)) {
				uncheckActionRowCheckBox = e.findElement(ProjectFormsTab.chk_BetaViewFormHistoryRecordCheckbox);
			}
		}
		uncheckActionRowCheckBox.click();
		
		/*if (aType.equalsIgnoreCase("deactivate") && !appBetaViewFlag) {
			clickElementAndWaitForElement(ProjectFormsTab.sel_FormViewHistoryActionDropdown, ProjectFormsTab.opt_FormViewHistoryDeactivateAction);
			clickElementAndWaitForElement(ProjectFormsTab.opt_FormViewHistoryDeactivateAction, ProjectFormsTab.btn_FormViewHistoryDeReactivateContinue);
			clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_FormViewHistoryDeReactivateContinue, GlobalPageElements.pop_PopUpElement);

		}
		else*/ if (aType.equalsIgnoreCase("deactivate") /*&& appBetaViewFlag*/) {
			clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormViewHistoryActionDropdown, ProjectFormsTab.opt_BetaViewFormViewHistoryDeactivateAction);
			clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.opt_BetaViewFormViewHistoryDeactivateAction, ProjectFormsTab.img_BetaViewActionLoading);
		}
		/*else if (aType.equalsIgnoreCase("reactivate") && !appBetaViewFlag) {
			clickElementAndWaitForElement(ProjectFormsTab.sel_FormViewHistoryActionDropdown, ProjectFormsTab.opt_FormViewHistoryReactivateAction);
			clickElementAndWaitForElement(ProjectFormsTab.opt_FormViewHistoryReactivateAction, ProjectFormsTab.btn_FormViewHistoryDeReactivateContinue);
			clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_FormViewHistoryDeReactivateContinue, GlobalPageElements.pop_PopUpElement);

		}
		else*/ if (aType.equalsIgnoreCase("reactivate")/* && appBetaViewFlag*/) {
			clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewFormViewHistoryActionDropdown, ProjectFormsTab.opt_BetaViewFormViewHistoryReactivateAction);
			clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.opt_BetaViewFormViewHistoryReactivateAction, ProjectFormsTab.img_BetaViewActionLoading);
		}
		/*if (!appBetaViewFlag)
			AutomationAssert.verifyTrue(aType.equalsIgnoreCase("deactivate") ? verifyAuditHistoryForDeReactivation(user1, user2, user3, user4, true) : verifyAuditHistoryForDeReactivation(user1, user2, user3, user4, false));
		else if (appBetaViewFlag)*/
			AutomationAssert.verifyTrue(aType.equalsIgnoreCase("deactivate") ? verifyAuditHistoryForDeReactivation(user1, user2, user3, user4, true, distributionListRows) : verifyAuditHistoryForDeReactivation(user1, user2, user3, user4, false, distributionListRows));

		closeCurrentWindow();
		switchPreviousWindow(parentHandle);

	}

	public boolean verifyAuditHistoryForDeReactivation(String user1, String user2, String user3, String user4, Boolean flag) {
		reloadPage();
		waitForCompletePageLoad();
		clickElementAndWaitForElement(ProjectFormsTab.lnk_FormViewLHFormHistory, ProjectFormsTab.sel_FormViewHistoryType);
		List<WebElement> historyRecordsRemarks = findElements(ProjectFormsTab.css_FormHistoryRecordsListRemarks);

		if (flag) {
			for (WebElement e : historyRecordsRemarks) {
				if (e.getText().contains("Action Deactivated")) {
					System.out.println("Deactivation Logs: " + e.getText());
					AutomationAssert.verifyTrue(e.getText().contains(user1) || e.getText().contains(user2) || e.getText().contains(user3) || e.getText().contains(user4));
				}
			}
			return true;
		}
		else {
			for (WebElement e : historyRecordsRemarks) {
				if (e.getText().contains("Action Reactivated")) {
					System.out.println("Reactivation Logs: " + e.getText());
					AutomationAssert.verifyTrue(e.getText().contains(user1) || e.getText().contains(user2) || e.getText().contains(user3) || e.getText().contains(user4));
				}
			}
			return true;
		}

	}

	public boolean verifyAuditHistoryForDeReactivation(String user1, String user2, String user3, String user4, Boolean flag, List<WebElement> distributionListRows) {
		waitUntilElementIsInvisible(By.cssSelector("div[class*='loading']"));
		if (flag) {
			for (WebElement e : distributionListRows) {
				/* AutomationAssert.verifyTrue(e.findElement(ProjectFormsTab.lbl_BetaViewFileHistoryRecordDetailActionStatus).getText().equalsIgnoreCase(e.findElement(ProjectFormsTab.lbl_BetaViewFileHistoryRecordDetailRecipient).getText().contains(user3) ? "Incomplete": "Inactive")); */
				AutomationAssert.verifyTrue(e.findElement(ProjectFormsTab.lbl_BetaViewFileHistoryRecordDetailRecipient).getText().contains(e.findElement(ProjectFormsTab.lbl_BetaViewFileHistoryRecordDetailActionStatus).getText().equalsIgnoreCase("Incomplete") ? user3 : getUserValue(e.findElement(ProjectFormsTab.lbl_BetaViewFileHistoryRecordDetailRecipient))));
			}
			return true;
		}
		else {
			for (WebElement e : distributionListRows) {
				AutomationAssert.verifyTrue(e.findElement(ProjectFormsTab.lbl_BetaViewFileHistoryRecordDetailActionStatus).getText().equalsIgnoreCase("Incomplete"));
			}
			return true;
		}
	}

	public String getUserValue(WebElement e) {
		if (e.getText().contains(user1))
			return this.user1;
		else if (e.getText().contains(user2))
			return this.user2;
		else if (e.getText().contains(user3))
			return this.user3;
		else if (e.getText().contains(user4))
			return this.user4;
		else
			return null;
	}

	public void verifyFormVisibilityAndCounts(String countFlag, String visibility, String user) {

		if (visibility.equals("Visible") && countFlag.equalsIgnoreCase("reduced")) {
			AutomationAssert.verifyTrue(isDisplayed(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle));
			if (isDisplayed(GlobalPageElements.lnk_FirstMyActionCountPopOver)) {
				mouseHover(GlobalPageElements.lnk_FirstMyActionCountPopOver);
				AutomationAssert.verifyTrue(getCount(GlobalPageElements.css_firstActionsPopoverContentLinks) < actionListSize);
			}
		}
		else if (visibility.equals("Visible") && countFlag.equalsIgnoreCase("increased")) {
			AutomationAssert.verifyTrue(isDisplayed(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle));
			mouseHoverFirstActionPopOver();
			AutomationAssert.verifyTrue(getCount(GlobalPageElements.css_firstActionsPopoverContentLinks) + " is not equal to " + actionListSize, getCount(GlobalPageElements.css_firstActionsPopoverContentLinks) == actionListSize);
		}
		else if (visibility.equals("Invisible") && countFlag.equalsIgnoreCase("reduced")) {
			AutomationAssert.verifyTrue(!isDisplayed(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle));
		}
		else if (visibility.equals("Invisible") && countFlag.equalsIgnoreCase("increased")) {
			AutomationAssert.verifyTrue(!isDisplayed(ProjectFormsTab.lnk_ProjectFormsFirstFormTitle));
		}
	}
}
