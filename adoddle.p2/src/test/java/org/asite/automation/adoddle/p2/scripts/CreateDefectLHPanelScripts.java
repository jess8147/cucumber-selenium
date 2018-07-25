/*  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFieldLocators.FieldTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.JavaUtils;
import org.openqa.selenium.Keys;

public class CreateDefectLHPanelScripts extends AdoddleCommonAppMethods {

	private String			formTitle, parentWindow;
	private int				siteOpenCount, siteResolvedCount, siteVerifiedCount, locOpenCount, locResolvedCount, locVerifiedCount, x, y;
	final private static Logger	log	= AutomationLogger.getInstance().getLogger(CreateDefectLHPanelScripts.class);

	public void searchFormOnCreateFormWindow(String form) {
		log.info("Searching Form on Create Form window...");
		sendKeys(ProjectFormsTab.txt_PopCreateFormSearchFormInput, form);
		waitForCompletePageLoad();
	}

	public void verifyFormIsFilteredOnCreateFormWindow(String formTitle) {
		AutomationAssert.verifyTrue(getElementText(ProjectFormsTab.lbl_PopCreateFormFirstFormName).equalsIgnoreCase(formTitle));
	}

	public void clickOnFilteredForm(String formTitle) {
		AutomationAssert.verifyTrue(formTitle.equalsIgnoreCase(getElementText(ProjectFormsTab.lbl_PopCreateFormFirstFormName)));
		clickElementAndWait(ProjectFormsTab.lbl_PopCreateFormFirstFormName);
	}

	public void enterMandatoryFields(String siteLocation) {

		waitUntilElementIsDisplayed(FieldTab.txt_CreateDefectFormTitle);
		waitForCompletePageLoad();
		formTitle = siteLocation + epoch;
		sendKeys(FieldTab.txt_CreateDefectFormTitle, formTitle);
		sendKeys(FieldTab.txt_CreateDefectFormTitle, Keys.TAB);
		if (siteLocation.contains("LHS") || siteLocation.contains("MPS")) {
			//selectByIndex(FieldTab.drp_CreateDefectFormLocation, 1);
			selectByVisibleText(FieldTab.drp_CreateDefectFormLocation, siteLocation+"|"+siteLocation);
		}
		else if(siteLocation.contains("LHL") || siteLocation.contains("MPL")){
			selectByVisibleText(FieldTab.drp_CreateDefectFormLocation, siteLocation+"|"+siteLocation.replace("Location", "Site")+">"+siteLocation);
		}
		else
			selectByIndex(FieldTab.drp_CreateDefectFormLocation, 1);
		
		collectionDataMap.put("Defect", formTitle);
		waitForCompletePageLoad();
		sendKeys(FieldTab.txt_CreateDefectFormTitle, Keys.TAB);
		selectByIndex(FieldTab.drp_CreateDefectFormType, 1);
		waitForCompletePageLoad();
		sendKeys(FieldTab.txt_CreateDefectFormDescription, javaUtils.getRandomString(20));
		sendKeys(FieldTab.txt_CreateDefectFormTitle, Keys.TAB);
		waitForCompletePageLoad();
		selectByVisibleText(FieldTab.drp_CreateDefectFormAssignTo, ResourceHandler.loadProperty("defect.field.contractor.user"));
		sendKeys(FieldTab.txt_CreateDefectExpectedDueDays, "1");
		sendKeys(FieldTab.txt_CreateDefectFormTitle, Keys.TAB);
		waitForCompletePageLoad();
	}

	public void attachExternalFilesToDefect() {
		/*if(!appBetaViewFlag) {
			clickElementAndWait(FieldTab.btn_CreateDefectFormAttachment);
			findElement(ProjectFormsTab.btn_CreateFormAttachmentSelectFiles).sendKeys(ResourceHandler.loadProperty("single.file.path"));
			waitUntilElementIsDisplayed(FieldTab.lbl_PopCreateDefectSelectedFileSize);
			clickElement(FieldTab.btn_PopCreateDefectAttachmentSave);
		}
		else {*/
			clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewCreateFormAttachment, ProjectFormsTab.btn_BetaViewCreateFormAttachFileSave);
			findElement(ProjectFormsTab.btn_CreateFormAttachmentSelectFiles).sendKeys(ResourceHandler.loadProperty("single.file.path"));
			clickElementAndWaitForInvisibilityOfElement(ProjectFormsTab.btn_BetaViewCreateFormAttachFileSave, ProjectFormsTab.btn_BetaViewCreateRFIFormAttachmentsClose);
		//}
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();
	}

	public void clickOnSendButton(String buttonText) {
		waitUntilElementIsInvisible(GlobalPageElements.img_XDocCallBackProcess);
		AutomationAssert.verifyTrue(buttonText.equalsIgnoreCase(getElementText(FieldTab.btn_BetaViewCreateEditDefectSaveButton)));
		clickElementAndWait(/*!appBetaViewFlag? FieldTab.btn_CreateEditDefectSaveButton:*/FieldTab.btn_BetaViewCreateEditDefectSaveButton);
	}

	public void verifyDefectIsCreated() throws InterruptedException {
		for (int index = 0; index < 3; index++) {

			try {
				searchDefects(formTitle);
				AutomationAssert.verifyTrue(getCount(FieldTab.css_FieldListingDefectsCount) > 0);
				break;
			}
			catch (Throwable t) {
				waitUtils.waitInterval(1);
			}
		}

		AutomationAssert.verifyTrue(getElementText(FieldTab.lnk_FieldListingDefectsFirstTitle).equalsIgnoreCase(formTitle));
	}

	public void verifyStatusOnListing(String status, String color) {
		waitUntilElementContainsText(FieldTab.lbl_FieldListingDefectsFirstStatus, status);

		if("red".equalsIgnoreCase(color)) {
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementAttributeValue(FieldTab.lbl_FieldListingDefectsFirstStatus, "style"),("background-color: rgb(255, 0, 0)")), getElementAttributeValue(FieldTab.lbl_FieldListingDefectsFirstStatus, "style").contains("background-color: rgb(255, 0, 0)"));
		}
		else if ("green".equalsIgnoreCase(color)) {
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementAttributeValue(FieldTab.lbl_FieldListingDefectsFirstStatus, "style"), ("background-color: rgb(0, 255, 0)")), getElementAttributeValue(FieldTab.lbl_FieldListingDefectsFirstStatus, "style").contains("background-color: rgb(0, 255, 0)"));
		}
		else if ("blue".equalsIgnoreCase(color)) {
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementAttributeValue(FieldTab.lbl_FieldListingDefectsFirstStatus, "style"), ("background-color: rgb(0, 0, 255)")), getElementAttributeValue(FieldTab.lbl_FieldListingDefectsFirstStatus, "style").contains("background-color: rgb(0, 0, 255)"));
		}

			AutomationAssert.verifyTrue(getElementText(FieldTab.lbl_FieldListingDefectsFirstStatus).equalsIgnoreCase(status));
	}

	public void closeCurrentWindowAndSwitchPrevious() {
		closeCurrentWindow();
		switchPreviousWindow(parentWindow);
	}

	public void verifyUserAction(String action) {
		AutomationAssert.verifyTrue(getElementText(FieldTab.lnk_FieldListingDefectsFirstAction).equalsIgnoreCase(action));
		AutomationAssert.verifyTrue(findElement(FieldTab.lbl_FieldListingDefectsFirstAction).findElements(GlobalPageElements.css_LinkElements).size() > 0);
	}

	public void performAssignStatusAction() {
		parentWindow = clickElementAndSwitchWindow(FieldTab.lnk_FieldListingDefectsFirstAction);
	}

	public void verifyFormViewWindowOpens() {
		parentWindow = getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();
		waitUntilElementIsDisplayed(FieldTab.lbl_DefectViewHeaderText);
		log.info("Header Text: " + getElementText(FieldTab.lbl_DefectViewHeaderText));
		AutomationAssert.verifyTrue(getElementText(FieldTab.lbl_DefectViewHeaderText).contains("View Form"));
		AutomationAssert.verifyTrue(getElementText(FieldTab.lbl_DefectViewHeaderText).contains(formTitle));

	}

	public void verifyDefectStatusOnFormView(String defectStatus) {
		waitUntilElementIsDisplayed(FieldTab.lbl_DefectViewStatusValue);
		AutomationAssert.verifyTrue(getElementText(FieldTab.lbl_DefectViewStatusValue).trim().equalsIgnoreCase(defectStatus));

	}

	public void verifyDefectStatusOnListing(String defectStatus) {
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		searchDefects(formTitle);
		AutomationAssert.verifyTrue(getElementText(FieldTab.lbl_FieldListingDefectsFirstStatus).trim().equalsIgnoreCase(defectStatus));

	}

	public void changeDefectStatus(String newStatus) {
		waitAndSwitchIframe(ProjectFormsTab.frm_BetaViewReplyFormIframe);
		waitUntilElementIsDisplayed(FieldTab.drp_EditDefectFormStatus);

		selectByVisibleText(FieldTab.drp_EditDefectFormStatus, newStatus);

		if (isDisplayed(FieldTab.txt_EditDefectFormComment)) {
			sendKeys(FieldTab.txt_EditDefectFormComment, javaUtils.getRandomString(20));
			sendKeys(FieldTab.txt_EditDefectFormComment, Keys.TAB);
		}

		waitForCompletePageLoad();

		if (newStatus.equalsIgnoreCase("open") || newStatus.equalsIgnoreCase("resolved")) {
			sysUtils.authenticateRemoteMachine(nodeIP);
			String attachment = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
			/*if(!appBetaViewFlag) {
				clickElementAndWait(FieldTab.btn_CreateDefectFormAttachment);
				findElement(ProjectFormsTab.btn_CreateFormAttachmentSelectFiles).sendKeys(attachment.toString());
				waitUntilElementIsDisplayed(FieldTab.lbl_PopCreateDefectSelectedFileSize);
				clickElementAndWait(FieldTab.btn_PopCreateDefectAttachmentSave);
			}
			else {*/
				clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewCreateFormAttachment, ProjectFormsTab.btn_BetaViewCreateFormAttachFileSave);
				findElement(ProjectFormsTab.btn_CreateFormAttachmentSelectFiles).sendKeys(attachment);
				clickElementAndWaitForElement(ProjectFormsTab.btn_BetaViewCreateFormAttachFileSave, ProjectFormsTab.btn_BetaViewCreateRFIFormSave);
			//}
			waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
			waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
			waitForCompletePageLoad();
		}

		try {
			/*if(!appBetaViewFlag)
				clickElementAndWaitForInvisibilityOfElement(FieldTab.btn_CreateEditDefectSaveButton, FieldTab.btn_CreateEditDefectSaveButton, 10);
			else*/
				clickElementAndWaitForInvisibilityOfElement(FieldTab.btn_BetaViewCreateEditDefectSaveButton, FieldTab.btn_BetaViewCreateEditDefectSaveButton, 10);
		}
		catch (Throwable t) {
			/*if(!appBetaViewFlag)
				clickElementAndWait(FieldTab.btn_CreateEditDefectSaveButton);
			else*/
				clickElementAndWait(FieldTab.btn_BetaViewCreateEditDefectSaveButton);
		}

		switchDefault();
		closeCurrentWindowAndSwitchPrevious();

	}

	public void verifyDefectCountManagement(String status, String location) {
		if (status.equalsIgnoreCase("Open") && location.equalsIgnoreCase("Site")) {
				if(getElementAttributeValue(FieldTab.lbl_SelectedSiteOpenDefectCount, "title").contains("("))
					AutomationAssert.verifyTrue("Actual# " + Integer.parseInt(findElement(FieldTab.lbl_SelectedSiteOpenDefectCount).getAttribute("title").split(" ")[1].replace("(", "").replace(")", "")) + "\nExpected# " + (siteOpenCount + 1), Integer.parseInt(findElement(FieldTab.lbl_SelectedSiteOpenDefectCount).getAttribute("title").split(" ")[1].replace("(", "").replace(")", "")) >= (siteOpenCount + 1));
				else {
					log.info("Open Site Defect Previous count: "+siteOpenCount);
					log.info("Open Site Defect Current count: "+getElementText(FieldTab.lbl_SelectedSiteOpenDefectCount));
					AutomationAssert.verifyTrue(Integer.parseInt(getElementText(FieldTab.lbl_SelectedSiteOpenDefectCount)) + " should equal " + (siteOpenCount + 1), Integer.parseInt(getElementText(FieldTab.lbl_SelectedSiteOpenDefectCount)) == (siteOpenCount + 1));
				}
		}
		else if (status.equalsIgnoreCase("Resolved") && location.equalsIgnoreCase("Site")) {
			if(getElementAttributeValue(FieldTab.lbl_SelectedSiteResolvedDefectCount, "title").contains("("))
				AutomationAssert.verifyTrue("Actual# " + Integer.parseInt(findElement(FieldTab.lbl_SelectedSiteResolvedDefectCount).getAttribute("title").split(" ")[1].replace("(", "").replace(")", "")) + "\nExpected# " + (siteResolvedCount + 1), Integer.parseInt(findElement(FieldTab.lbl_SelectedSiteResolvedDefectCount).getAttribute("title").split(" ")[1].replace("(", "").replace(")", "")) == (siteResolvedCount + 1));
			else {
				log.info("Resolved Site Defect Previous count: "+siteResolvedCount);
				log.info("Resolved Site Defect Current count: "+getElementText(FieldTab.lbl_SelectedSiteResolvedDefectCount));
				AutomationAssert.verifyTrue(Integer.parseInt(getElementText(FieldTab.lbl_SelectedSiteResolvedDefectCount)) + " should equal " + (siteResolvedCount + 1), Integer.parseInt(getElementText(FieldTab.lbl_SelectedSiteResolvedDefectCount)) == (siteResolvedCount + 1));
			}
		}
		else if (status.equalsIgnoreCase("Verified") && location.equalsIgnoreCase("Site")) {
			if(getElementAttributeValue(FieldTab.lbl_SelectedSiteVerifiedDefectCount, "title").contains("("))
				AutomationAssert.verifyTrue("Actual# " + Integer.parseInt(findElement(FieldTab.lbl_SelectedSiteVerifiedDefectCount).getAttribute("title").split(" ")[1].replace("(", "").replace(")", "")) + "\nExpected# " + (siteVerifiedCount + 1), Integer.parseInt(findElement(FieldTab.lbl_SelectedSiteVerifiedDefectCount).getAttribute("title").split(" ")[1].replace("(", "").replace(")", "")) >= (siteVerifiedCount + 1));
			else {
				log.info("Verified Site Defect Previous count: "+siteVerifiedCount);
				log.info("Verified Site Defect Current count: "+getElementText(FieldTab.lbl_SelectedSiteVerifiedDefectCount));
				AutomationAssert.verifyTrue(Integer.parseInt(getElementText(FieldTab.lbl_SelectedSiteVerifiedDefectCount)) == (siteVerifiedCount + 1));
				
			}
		}
		else if (status.equalsIgnoreCase("Open") && location.equalsIgnoreCase("Location")) {
			if(getElementAttributeValue(FieldTab.lbl_SelectedLocOpenDefectCount, "title").contains("("))
				AutomationAssert.verifyTrue("Actual# " + Integer.parseInt(findElement(FieldTab.lbl_SelectedLocOpenDefectCount).getAttribute("title").split(" ")[1].replace("(", "").replace(")", "")) + "\nExpected# " + (locOpenCount + 1), Integer.parseInt(findElement(FieldTab.lbl_SelectedLocOpenDefectCount).getAttribute("title").split(" ")[1].replace("(", "").replace(")", "")) == (locOpenCount + 1));
			else {
				log.info("Open Location Defect Previous count: "+locOpenCount);
				log.info("Open Location Defect Current count: "+getElementText(FieldTab.lbl_SelectedLocOpenDefectCount));
				AutomationAssert.verifyTrue(getElementText(FieldTab.lbl_SelectedLocOpenDefectCount) + " does not equal " + (locOpenCount + 1), Integer.parseInt(getElementText(FieldTab.lbl_SelectedLocOpenDefectCount)) == (locOpenCount + 1));
			}
		}
		else if (status.equalsIgnoreCase("Resolved") && location.equalsIgnoreCase("Location")) {
			if(getElementAttributeValue(FieldTab.lbl_SelectedLocResolvedDefectCount, "title").contains("("))
				AutomationAssert.verifyTrue("Actual# " + Integer.parseInt(findElement(FieldTab.lbl_SelectedLocResolvedDefectCount).getAttribute("title").split(" ")[1].replace("(", "").replace(")", "")) + "\nExpected# " + (locResolvedCount + 1), Integer.parseInt(findElement(FieldTab.lbl_SelectedLocResolvedDefectCount).getAttribute("title").split(" ")[1].replace("(", "").replace(")", "")) == (locResolvedCount + 1));
			else {
				log.info("Resolved Location Defect Previous count: "+locResolvedCount);
				log.info("Resolved Location Defect Current count: "+getElementText(FieldTab.lbl_SelectedLocResolvedDefectCount));
				AutomationAssert.verifyTrue(Integer.parseInt(getElementText(FieldTab.lbl_SelectedLocResolvedDefectCount)) == (locResolvedCount + 1));
			}	
		}
		else if (status.equalsIgnoreCase("Verified") && location.equalsIgnoreCase("Location")) {
			if(getElementAttributeValue(FieldTab.lbl_SelectedLocVerifiedDefectCount, "title").contains("("))
				AutomationAssert.verifyTrue("Actual# " + Integer.parseInt(findElement(FieldTab.lbl_SelectedLocVerifiedDefectCount).getAttribute("title").split(" ")[1].replace("(", "").replace(")", "")) + "\nExpected# " + (locVerifiedCount + 1), Integer.parseInt(findElement(FieldTab.lbl_SelectedLocVerifiedDefectCount).getAttribute("title").split(" ")[1].replace("(", "").replace(")", "")) == (locVerifiedCount + 1));
			else {
				log.info("Verified Location Defect Previous count: "+locVerifiedCount);
				log.info("Verified Location Defect Current count: "+getElementText(FieldTab.lbl_SelectedLocVerifiedDefectCount));
				AutomationAssert.verifyTrue(Integer.parseInt(getElementText(FieldTab.lbl_SelectedLocVerifiedDefectCount)) == (locVerifiedCount + 1));
			}
		}
	}

	public void collectSiteDefectCounts() {
		try {
			siteOpenCount = Integer.parseInt(getElementText(FieldTab.lbl_SelectedSiteOpenDefectCount));
		}
		catch (Throwable t) {
			siteOpenCount = Integer.parseInt(findElement(FieldTab.lbl_SelectedSiteOpenDefectCount).getAttribute("title").split(" ")[1].replace("(", "").replace(")", ""));
		}

		try {
			siteResolvedCount = Integer.parseInt(getElementText(FieldTab.lbl_SelectedSiteResolvedDefectCount));
		}
		catch (Throwable t) {
			siteResolvedCount = Integer.parseInt(findElement(FieldTab.lbl_SelectedSiteResolvedDefectCount).getAttribute("title").split(" ")[1].replace("(", "").replace(")", ""));
		}

		try {
			siteVerifiedCount = Integer.parseInt(getElementText(FieldTab.lbl_SelectedSiteVerifiedDefectCount));
		}
		catch (Throwable t) {
			siteVerifiedCount = Integer.parseInt(findElement(FieldTab.lbl_SelectedSiteVerifiedDefectCount).getAttribute("title").split(" ")[1].replace("(", "").replace(")", ""));
		}
	}

	public void collectLocationDefectCounts() {
		try {
			locOpenCount = Integer.parseInt(getElementText(FieldTab.lbl_SelectedLocOpenDefectCount));
		}
		catch (Throwable t) {
			locOpenCount = Integer.parseInt(findElement(FieldTab.lbl_SelectedLocOpenDefectCount).getAttribute("title").split(" ")[1].replace("(", "").replace(")", ""));
		}

		try {
			locResolvedCount = Integer.parseInt(getElementText(FieldTab.lbl_SelectedLocResolvedDefectCount));
		}
		catch (Throwable t) {
			locResolvedCount = Integer.parseInt(findElement(FieldTab.lbl_SelectedLocResolvedDefectCount).getAttribute("title").split(" ")[1].replace("(", "").replace(")", ""));
		}

		try {
			locVerifiedCount = Integer.parseInt(getElementText(FieldTab.lbl_SelectedLocVerifiedDefectCount));
		}
		catch (Throwable t) {
			locVerifiedCount = Integer.parseInt(findElement(FieldTab.lbl_SelectedLocVerifiedDefectCount).getAttribute("title").split(" ")[1].replace("(", "").replace(")", ""));
		}
	}

	public void clickOnCreateDefectIcon() {

		while (true) {
			x = Integer.parseInt(JavaUtils.getRandomNumber(3));
			y = Integer.parseInt(JavaUtils.getRandomNumber(3));
			if (x < 1000 && y < 650)
				break;
		}

		log.info("Map Defect X co-ordinates: " + x + "\nMap Defect Y co-ordinates: " + y);
		waitAndSwitchIframe(FieldTab.frm_PlanViewerIframe);
		waitUntilElementIsDisplayed(FieldTab.ele_DrawingDocumentViewerCanvas);
		clickElementAndWait(FieldTab.img_PlanViewCreateDefectIcon);
		/*Actions builder = new Actions(getWebDriver());
		builder.moveToElement(findElement(FieldTab.ele_DrawingDocumentViewerCanvas), x, y).click().build().perform();*/
		mouseHoverClick(FieldTab.ele_DrawingDocumentViewerCanvas, x, y);
		switchDefault();
		waitUntilElementIsDisplayed(ProjectFormsTab.frm_createFormIframe);
		waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
	}

	public void clickOnCreatedDefectMapIcon() {
		String parentMapWindow;

		try {
			parentMapWindow = getCurrentWindow();
			waitUntilElementIsDisplayed(FieldTab.ele_DrawingDocumentViewerCanvas);
			/*Actions builder = new Actions(driver);
			builder.moveToElement(findElement(FieldTab.ele_DrawingDocumentViewerCanvas), x, y).click().build().perform();*/
			mouseHoverClick(FieldTab.ele_DrawingDocumentViewerCanvas, x, y);
			waitForSwitchWindow(2);
			switchWindow();
			closeCurrentWindow();
			switchPreviousWindow(parentMapWindow);
		}
		catch (Throwable t) {
			log.error("failure in map defect click operation");
		}
	}

}
