package org.asite.automation.classic.p1.scripts;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import org.asite.automation.CommonLocators.ClassicAssociateLocators.AttachDocPage;
import org.asite.automation.CommonLocators.ClassicAssociateLocators.DistributionPage;
import org.asite.automation.CommonLocators.ClassicDocListingLocators.DocListingPage;
import org.asite.automation.CommonLocators.ClassicLandingLocators.LandingPage;
import org.asite.automation.CommonLocators.ClassicWSLandingLocators.WSLandingPage;
import org.asite.automation.CommonLocators.ClassicWorkspaceLocators.WorkspaceTabPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.ClassicCommonAppMethods;
import org.asite.automation.common.resources.ClassicCommonStringPool;

public class ThinClientUploadClassicScripts extends ClassicCommonAppMethods {

	private String			parentHandle1, parentHandle2, attachfile1, attachfile2, fileDocRef1, fileDocRef2;
	private String			firstFileTitle, secondFileTitle;
	private String			firstFilePOI, secondFilePOI;
	private String			firstFileStatus, secondFileStatus;
	private static String	firstDocRef, secondDocRef;
	public static String 	simpleFirstDocRef, simpleSecondDocRef, simpleFilePath1, simpleFilePath2;;
	private static String	filePath1, filePath2;
	final private HashMap<String, String>	hMap	= new HashMap<String, String>();

	/*public void selectEditWorkspace(String adminDropdownList) {
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteHeaderFrame);
		selectByVisibleText(WSLandingPage.drp_AdminDropdown, adminDropdownList.trim());
		waitForCompletePageLoad();
	}*/

	public void uncheckSimpleUploadCheckbox() {
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteMainFrame);
		waitAndSwitchIframe(LandingPage.frm_PmFrame);

		waitUntilElementIsDisplayed(WorkspaceTabPage.lnk_CreateEditWorkspaceAdditionalInfoShow);
		clickElementAndWaitForElement(WorkspaceTabPage.lnk_CreateEditWorkspaceAdditionalInfoShow, WorkspaceTabPage.chk_CreateEditWorkspaceEnablePublicUpload);
		if (isSelected(WorkspaceTabPage.chk_CreateEditWorkspaceEnablePublicUpload)) {
			clickElementAndWait(WorkspaceTabPage.chk_CreateEditWorkspaceEnablePublicUpload);
			clickElementAndWait(WorkspaceTabPage.lnk_CreateEditWorkspaceAdditionalInfoSave);
		}
		clickElementAndWait(WorkspaceTabPage.lnk_CreateEditWorkspaceAdditionalInfoHide);
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteHeaderFrame);
		clickElementAndWait(WSLandingPage.lnk_WorkspaceHome);
	}

	public void clickOnTestFolder(String testFolderName) {
		switchDefault();
		switchIframe(LandingPage.frm_AsiteWorkingFrame);
		switchIframe(LandingPage.frm_AsiteMainFrame);
		waitUntilElementIsDisplayed(WSLandingPage.lnk_AutomationUploadFilesFolder);
		AutomationAssert.verifyTrue(getElementText(WSLandingPage.lnk_AutomationUploadFilesFolder).trim().equalsIgnoreCase(testFolderName.trim()));
		clickElementAndWait(WSLandingPage.lnk_AutomationUploadFilesFolder);

	}

	public void clickOnCustomAttributeTestFolder(String testFolderName) {
		switchDefault();
		switchIframe(LandingPage.frm_AsiteWorkingFrame);
		switchIframe(LandingPage.frm_AsiteMainFrame);
		waitUntilElementIsDisplayed(WSLandingPage.lnk_CustomAttributesFolder);
		AutomationAssert.verifyTrue(getElementText(WSLandingPage.lnk_CustomAttributesFolder).trim().equalsIgnoreCase(testFolderName.trim()));
		clickElementAndWait(WSLandingPage.lnk_CustomAttributesFolder);

	}

	/*public void verifyPublicStandardDocOption() {
		String parentHandle;
		switchDefault();
		switchIframe(LandingPage.frm_AsiteWorkingFrame);
		switchIframe(LandingPage.frm_AsiteHeaderFrame);
		parentHandle = getCurrentWindow();
		for (int index = 0; index < 5; index++) {
			try {
				mouseHoverAndClickElement(LandingPage.lnk_Settings, LandingPage.lnk_SettingsPrefs);
				waitForSwitchWindow(2);
				switchWindow();
				reloadPage();
				waitForCompletePageLoad();
				if (findElements(DocListingPage.frm_AsiteOptionsFrame).size() > 0)
					break;
			}
			catch (Throwable t) {
				log.error("Could not find prefs in try " + index);
			}
		}
		switchIframe(DocListingPage.frm_AsiteOptionsFrame);
		waitUntilElementIsDisplayed(DocListingPage.lnk_PrefsWindowUploadPrefs);
		clickElementAndWait(DocListingPage.lnk_PrefsWindowUploadPrefs);
		switchDefault();
		waitUntilElementIsDisplayed(DocListingPage.frm_AsitePrefsFrame);
		switchIframe(DocListingPage.frm_AsitePrefsFrame);
		clickElementAndWait(DocListingPage.rad_PrefsWindowStandardPublish);
		clickElementAndWait(DocListingPage.btn_PrefsWindowUploadPrefSubmit);
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
		switchDefault();
		switchIframe(LandingPage.frm_AsiteWorkingFrame);
		switchIframe(LandingPage.frm_AsiteMainFrame);
		switchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		waitUntilElementIsDisplayed(DocListingPage.img_DocListingMenuPublishMenu);
	}*/

	public void clickPublicStandardDocOption() {
		switchDefault();
		switchIframe(LandingPage.frm_AsiteWorkingFrame);
		switchIframe(LandingPage.frm_AsiteMainFrame);
		switchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		waitUntilElementIsDisplayed(DocListingPage.img_DocListingMenuPublishMenu);

		parentHandle1 = getCurrentWindow();
		for(int index=0;index < 5; index++)
		{
			try
			{
				mouseHoverAndClickElement(DocListingPage.img_DocListingMenuPublishMenu, DocListingPage.img_DocListingMenuPublishStandardDoc);
				waitForSwitchWindow(2);
				switchWindow();
				if(isDisplayed(DocListingPage.lbl_PublishWindowPublishDocTitle))
						break;
			}
			catch(Throwable t)
			{
				log.error("could not find element");
			}
		}
		waitForCompletePageLoad();
	}

	public void verifyPublicStandardDocWindow(String publicDocWinTitle) {
		AutomationAssert.verifyTrue(getElementText(DocListingPage.lbl_PublishWindowPublishDocTitle).contains(publicDocWinTitle));
	}

	public void clickAddFilesAndSelectFromLocal(String uploadType) throws IOException, InterruptedException {
		sysUtils.authenticateRemoteMachine(nodeIP);
		reloadPage();

		if (uploadType.equalsIgnoreCase("Thin Client Upload")) {
			filePath1 = sysUtils.createFile(nodeIP + resourceUtils.getTestDataFilePath() + getCurrentDate() + 1 + ClassicCommonStringPool.TXT_EXTENSION).trim();
			filePath2 = sysUtils.createFile(nodeIP + resourceUtils.getTestDataFilePath() + getCurrentDate() + 2 + ClassicCommonStringPool.TXT_EXTENSION).trim();
			fileDocRef1 = firstDocRef = strUtils.extractFileNameString(filePath1).replace(ClassicCommonStringPool.TXT_EXTENSION, "");
			fileDocRef2 = secondDocRef = strUtils.extractFileNameString(filePath2).replace(ClassicCommonStringPool.TXT_EXTENSION, "");
			waitUntilElementIsClickable(AttachDocPage.btn_AttachDocFirstBrowse);
			getWebDriver().findElement(AttachDocPage.btn_AttachDocFirstBrowse).sendKeys(filePath1);
			waitForCompletePageLoad();
			collectionDataMap.put("First File : ", filePath1);
			getWebDriver().findElement(AttachDocPage.btn_AttachDocSecondBrowse).sendKeys(filePath2);
			waitForCompletePageLoad();
			collectionDataMap.put("Second File : ", filePath2);
		}
		else if (uploadType.equalsIgnoreCase("Simple Upload")) {
			simpleFilePath1 = sysUtils.createFile(nodeIP + resourceUtils.getTestDataFilePath() + getCurrentDate() + 1 + ClassicCommonStringPool.TXT_EXTENSION).trim();
			simpleFilePath2 = sysUtils.createFile(nodeIP + resourceUtils.getTestDataFilePath() + getCurrentDate() + 2 + ClassicCommonStringPool.TXT_EXTENSION).trim();
			simpleFirstDocRef = strUtils.extractFileNameString(simpleFilePath1).replace(ClassicCommonStringPool.TXT_EXTENSION, "");
			simpleSecondDocRef = strUtils.extractFileNameString(simpleFilePath1).replace(ClassicCommonStringPool.TXT_EXTENSION, "");
			getWebDriver().findElement(AttachDocPage.btn_AttachDocFirstChoose).sendKeys(simpleFilePath1);
			waitForCompletePageLoad();
			collectionDataMap.put("First File : ", simpleFilePath1);
			waitUntilElementIsDisplayed(AttachDocPage.btn_AttachDocSecondChoose);
			getWebDriver().findElement(AttachDocPage.btn_AttachDocSecondChoose).sendKeys(simpleFilePath2);
			waitForCompletePageLoad();
			collectionDataMap.put("Second File : ", simpleFilePath2);
		}

	}

	public void clickOnEnterDocDetailButton(String btnText) {
		clickElementAndWait(DocListingPage.btn_PublishWindowEnterDocDetails);
		AutomationAssert.verifyTrue(getValue(DocListingPage.btn_PublishWindowEnterDocDetails).equalsIgnoreCase(btnText));
	}

	public void verifyAttributesSection() {
		waitUntilElementIsDisplayed(DocListingPage.btn_PublishWindowStartUpload);
		AutomationAssert.verifyTrue(isDisplayed(DocListingPage.ele_PublishWindowAttributesSection));

	}

	public void enterAttributesDetails() throws IOException, InterruptedException {
		parentHandle2 = getCurrentWindow();
		attachfile1 = sysUtils.createFile(System.getProperty("selenium.execution.node.ip") + resourceUtils.getTestDataFilePath() + getCurrentDate() + ClassicCommonStringPool.TXT_EXTENSION).trim();
		collectionDataMap.put("First Attachment :", attachfile1);
		attachfile2 = sysUtils.createFile(System.getProperty("selenium.execution.node.ip") + resourceUtils.getTestDataFilePath() + getCurrentDate() + ClassicCommonStringPool.TXT_EXTENSION).trim();
		collectionDataMap.put("Second Attachment :", attachfile2);

		sendKeys(DocListingPage.txt_PublishWindowAttributesFirstRev, "1");
		sendKeys(DocListingPage.txt_PublishWindowAttributesSecondRev, "2");

		firstFileTitle = getRandomString(25);
		secondFileTitle = getRandomString(25);
		sendKeys(DocListingPage.txt_PublishWindowAttributesFirstDocTitle, firstFileTitle);
		collectionDataMap.put("First file doc title :", firstFileTitle);
		sendKeys(DocListingPage.txt_PublishWindowAttributesSecondDocTitle, secondFileTitle);
		collectionDataMap.put("Second file doc title :", secondFileTitle);
		selectByIndex(DocListingPage.drp_PublishWindowAttributesFirstPOI, 2);
		firstFilePOI = getSelectedDropdownLabel(DocListingPage.drp_PublishWindowAttributesFirstPOI);
		selectByIndex(DocListingPage.drp_PublishWindowAttributesSecondPOI, 3);
		secondFilePOI = getSelectedDropdownLabel(DocListingPage.drp_PublishWindowAttributesSecondPOI);

		selectByIndex(DocListingPage.drp_PublishWindowAttributesFirstStatus, 2);
		firstFileStatus = getSelectedDropdownLabel(DocListingPage.drp_PublishWindowAttributesFirstStatus);
		selectByIndex(DocListingPage.drp_PublishWindowAttributesSecondStatus, 3);
		secondFileStatus = getSelectedDropdownLabel(DocListingPage.drp_PublishWindowAttributesSecondStatus);

		getWebDriver().findElement(DocListingPage.btn_PublishWindowAttributesFirstBrowse).sendKeys(attachfile1);
		getWebDriver().findElement(DocListingPage.btn_PublishWindowAttributesSecondBrowse).sendKeys(attachfile2);

	}

	public void clickOnDocumentDistribution() {
		waitUntilElementIsDisplayed(DocListingPage.img_PublishWindowDocDistribution);
		clickElementAndWait(DocListingPage.img_PublishWindowDocDistribution);
	}

	public void verifyPublishDocumentWindowOpens(String windowTitle) {
		waitForSwitchWindow(3);

		Set<String> handles = getWebDriver().getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentHandle1) && !windowHandle.equals(parentHandle2)) {
				getWebDriver().switchTo().window(windowHandle);
				break;
			}
		}

		waitUntilElementIsDisplayed(DistributionPage.lbl_DocDistributionTitle);
		AutomationAssert.verifyTrue(getElementText(DistributionPage.lbl_DocDistributionTitle).trim().equalsIgnoreCase(windowTitle));
	}

	public void selectUsersAndAssignAction(String user, String action) {
		selectGroupType();
		deselectAllInMultiSelect(DistributionPage.sel_DocDistributionselectIndividuals);
		selectByVisibleText(DistributionPage.sel_DocDistributionselectIndividuals, user);
		clickElementAndWaitForElement(DistributionPage.btn_DocDistributionAddToDistList, DistributionPage.drp_DocDistributionAction);
		selectByVisibleText(DistributionPage.drp_DocDistributionActionHeader, action);
		clickElementAndWait(DistributionPage.drp_DocDistributionActionHeaderDownArrow);
		waitForCompletePageLoad();
	}

	public void clickDistibuteButton() {
		clickElement(DistributionPage.btn_DocDistributionDistribute);
		switchPreviousWindow(parentHandle2);
	}

	public void verifySelectedUser() {
		log.info("verification pending");
	}

	public void clickOnStartUpload() {
		clickElementAndWait(DocListingPage.btn_PublishWindowStartUpload);
	}

	public void verifyDocumentsOnUploadSummaryPopup() {
		waitUntilElementIsDisplayed(DocListingPage.ele_PublishWindowUploadSuccessTable);
		AutomationAssert.verifyTrue(getElementText(DocListingPage.ele_PublishWindowUploadSuccessTable).contains(firstDocRef));
		AutomationAssert.verifyTrue(getElementText(DocListingPage.ele_PublishWindowUploadSuccessTable).contains(secondDocRef));

	}

	public void clickOKButtonUploadSummaryPopup() {
		waitUntilElementIsDisplayed(DocListingPage.btn_PublishWindowUploadSuccessOk);
		clickElement(DocListingPage.btn_PublishWindowUploadSuccessOk);
	}

	public void verifyUploadedDocuments() throws IOException, InterruptedException {
		switchPreviousWindow(parentHandle1);
		waitForCompletePageLoad();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitForCompletePageLoad();
		waitAndSwitchIframe(LandingPage.frm_AsiteMainFrame);
		waitForCompletePageLoad();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		waitForCompletePageLoad();

		searchFilesUsingTitle(firstFileTitle);
		waitUntilElementContainsText(DocListingPage.lnk_DocListingFirstDocRef, fileDocRef1);
		AutomationAssert.verifyTrue(getCount(DocListingPage.css_DocListingDocRefList) > 0);
		AutomationAssert.verifyTrue(getElementText(DocListingPage.lnk_DocListingFirstDocRef) + " should contain " + fileDocRef1, getElementText(DocListingPage.lnk_DocListingFirstDocRef).contains(fileDocRef1));
		AutomationAssert.verifyTrue(getElementText(DocListingPage.lnk_DocListingFirstDocTitle) + " should equal " + firstFileTitle, getElementText(DocListingPage.lnk_DocListingFirstDocTitle).equalsIgnoreCase(firstFileTitle));
		AutomationAssert.verifyTrue(getElementText(DocListingPage.lnk_DocListingFirstPOI) + " should equal " + firstFilePOI, getElementText(DocListingPage.lnk_DocListingFirstPOI).equalsIgnoreCase(firstFilePOI));
		AutomationAssert.verifyTrue(getElementText(DocListingPage.lnk_DocListingFirstStatus) + " should equal " + firstFileStatus, getElementText(DocListingPage.lnk_DocListingFirstStatus).equalsIgnoreCase(firstFileStatus));
		AutomationAssert.verifyTrue(isDisplayed(DocListingPage.img_DocListingFirstAttachmentIcon));

		searchFilesUsingTitle(secondFileTitle);
		waitUntilElementContainsText(DocListingPage.lnk_DocListingFirstDocRef, fileDocRef2);
		AutomationAssert.verifyTrue(getCount(DocListingPage.css_DocListingDocRefList) > 0);
		AutomationAssert.verifyTrue(getElementText(DocListingPage.lnk_DocListingFirstDocRef).contains(fileDocRef2));
		AutomationAssert.verifyTrue(getElementText(DocListingPage.lnk_DocListingFirstDocTitle).equalsIgnoreCase(secondFileTitle));
		AutomationAssert.verifyTrue(getElementText(DocListingPage.lnk_DocListingFirstPOI).equalsIgnoreCase(secondFilePOI));
		AutomationAssert.verifyTrue(getElementText(DocListingPage.lnk_DocListingFirstStatus).equalsIgnoreCase(secondFileStatus));
		AutomationAssert.verifyTrue(isDisplayed(DocListingPage.img_DocListingFirstAttachmentIcon));

		sysUtils.deleteFile(filePath1);
		sysUtils.deleteFile(filePath2);
		sysUtils.deleteFile(attachfile1);
		sysUtils.deleteFile(attachfile2);
	}

	public void verifyActionAssignedToUser() throws InterruptedException {
		signOut();
		login(ResourceHandler.loadProperty("secondary.username"), ResourceHandler.loadProperty("secondary.password"));
		navigateToWorkSpace(System.getProperty("global.test.project"));
		switchDefault();
		switchIframe(LandingPage.frm_AsiteWorkingFrame);
		switchIframe(LandingPage.frm_AsiteMainFrame);
		waitUntilElementIsDisplayed(WSLandingPage.lnk_AutomationUploadFilesFolder);
		clickElementAndWait(WSLandingPage.lnk_AutomationUploadFilesFolder);

		switchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		searchFilesUsingTitle(firstFileTitle);
		waitUntilElementCountToBeMoreThan(DocListingPage.css_DocListingDocRefList, 0);
		AutomationAssert.verifyTrue(getElementText(DocListingPage.lnk_DocListingFirstAction).equalsIgnoreCase(ClassicCommonStringPool.FOR_INFORMATION));

		searchFilesUsingTitle(secondFileTitle);
		waitUntilElementCountToBeMoreThan(DocListingPage.css_DocListingDocRefList, 0);
		AutomationAssert.verifyTrue(getElementText(DocListingPage.lnk_DocListingFirstAction).equalsIgnoreCase(ClassicCommonStringPool.FOR_INFORMATION));

	}

	public void clickOnCopyFileNameButton() {
		clickElementAndWait(DocListingPage.btn_PublishWindowCopyFileName);
	}

	public void verifyDocFileNameIsCopied() {
		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesFirstDocTitle).equalsIgnoreCase(fileDocRef1));
		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesSecondDocTitle).equalsIgnoreCase(fileDocRef2));
	}

	public void enterHeaderValuesAndOverwrite() throws IOException, InterruptedException {
		String	POI	= "Approval", Status = "For Approval", state = "Gujarat", city = "Ahmedabad";
		sysUtils.authenticateRemoteMachine(nodeIP);
		attachfile1 = sysUtils.createFile(System.getProperty("selenium.execution.node.ip") + resourceUtils.getTestDataFilePath() + getCurrentDate() + ClassicCommonStringPool.TXT_EXTENSION).trim();
		attachfile2 = sysUtils.createFile(System.getProperty("selenium.execution.node.ip") + resourceUtils.getTestDataFilePath() + getCurrentDate() + ClassicCommonStringPool.TXT_EXTENSION).trim();

		clickElementAndWait(DocListingPage.rad_PublishWindowOverwrite);

		hMap.put("hRevision", getRandomNumber(1));
		sendKeys(DocListingPage.txt_PublishWindowHeaderRevision, hMap.get("hRevision"));

		hMap.put("hFileTitle", getRandomString(20));
		sendKeys(DocListingPage.txt_PublishWindowHeaderDocTitle, hMap.get("hFileTitle"));
		firstFileTitle = secondFileTitle = hMap.get("hFileTitle");

		hMap.put("hFilePOI", POI);
		selectByVisibleText(DocListingPage.drp_PublishWindowHeaderPOI, hMap.get("hFilePOI"));
		firstFilePOI = POI;
		firstFileStatus = Status;
		secondFilePOI = POI;
		secondFileStatus = Status;

		hMap.put("hFileStatus", Status);
		selectByVisibleText(DocListingPage.drp_PublishWindowHeaderStatus, hMap.get("hFileStatus"));

		hMap.put("hInteger", getRandomNumber(4));
		sendKeys(DocListingPage.txt_PublishWindowHeaderInteger, hMap.get("hInteger"));

		hMap.put("hEmail", getRandomString(5) + "@" + getRandomString(4) + "." + getRandomString(3));
		sendKeys(DocListingPage.txt_PublishWindowHeaderEmail, hMap.get("hEmail"));

		hMap.put("hDecimal", getRandomNumber(3) + "." + getRandomNumber(2));
		sendKeys(DocListingPage.txt_PublishWindowHeaderDecimal, hMap.get("hDecimal"));

		hMap.put("hLettersNumbers", getRandomString(3) + getRandomNumber(2));
		sendKeys(DocListingPage.txt_PublishWindowHeaderLettersNumbers, hMap.get("hLettersNumbers"));

		hMap.put("hLetters", getRandomString(4));
		sendKeys(DocListingPage.txt_PublishWindowHeaderLetters, hMap.get("hLetters"));

		hMap.put("hMultiSelectionChk", "Option1");
		clickElementAndWait(DocListingPage.chk_PublishWindowHeaderMultiSelect1);

		hMap.put("hRadio", "Yes");
		clickElementAndWait(DocListingPage.rad_PublishWindowHeaderRadioYes);

		hMap.put("hDatePicker", getCurrentDateTimeWithZone("dd-MMM-yyyy", "IST"));
		sendKeys(DocListingPage.txt_PublishWindowHeaderDatePicker, hMap.get("hDatePicker"));

		hMap.put("hCoordinatesOne", getRandomNumber(3));
		sendKeys(DocListingPage.txt_PublishWindowHeaderCoordinatesOne, hMap.get("hCoordinatesOne"));

		hMap.put("hCoordinatesTwoX", getRandomNumber(3));
		sendKeys(DocListingPage.txt_PublishWindowHeaderCoordinatesTwoX, hMap.get("hCoordinatesTwoX"));

		hMap.put("hCoordinatesTwoY", getRandomNumber(3));
		sendKeys(DocListingPage.txt_PublishWindowHeaderCoordinatesTwoY, hMap.get("hCoordinatesTwoY"));

		hMap.put("hCoordinatesX", getRandomNumber(3));
		sendKeys(DocListingPage.txt_PublishWindowHeaderCoordinatesX, hMap.get("hCoordinatesX"));

		hMap.put("hCoordinatesY", getRandomNumber(3));
		sendKeys(DocListingPage.txt_PublishWindowHeaderCoordinatesY, hMap.get("hCoordinatesY"));

		hMap.put("hCoordinatesZ", getRandomNumber(3));
		sendKeys(DocListingPage.txt_PublishWindowHeaderCoordinatesZ, hMap.get("hCoordinatesZ"));

		hMap.put("hState", state);
		selectByVisibleText(DocListingPage.drp_PublishWindowHeaderStates, hMap.get("hState"));

		hMap.put("hCity", city);
		selectByVisibleText(DocListingPage.drp_PublishWindowHeaderCities, hMap.get("hCity"));
		getWebDriver().findElement(DocListingPage.btn_PublishWindowAttributesFirstBrowse).sendKeys(attachfile1);
		getWebDriver().findElement(DocListingPage.btn_PublishWindowAttributesSecondBrowse).sendKeys(attachfile2);

	}

	public String getRandomString(int count) {
		return javaUtils.getRandomString(count);
	}

	public void clickApplyAll() {
		clickElementAndWait(DocListingPage.btn_PublishWindowHeaderApplyToAll);
		fileDocRef1 = getValue(DocListingPage.txt_PublishWindowAttributesFirstDocRef).replace("***", "");
		fileDocRef2 = getValue(DocListingPage.txt_PublishWindowAttributesSecondDocRef).replace("***", "");
	}

	public void verifyAttributesCopied() {
		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesFirstRev).equalsIgnoreCase(hMap.get("hRevision")));
		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesSecondRev).equalsIgnoreCase(hMap.get("hRevision")));

		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesFirstDocTitle).equalsIgnoreCase(hMap.get("hFileTitle")));
		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesSecondDocTitle).equalsIgnoreCase(hMap.get("hFileTitle")));

		AutomationAssert.verifyTrue(getSelectedDropdownLabel(DocListingPage.drp_PublishWindowAttributesFirstPOI).equalsIgnoreCase(hMap.get("hFilePOI")));
		AutomationAssert.verifyTrue(getSelectedDropdownLabel(DocListingPage.drp_PublishWindowAttributesSecondPOI).equalsIgnoreCase(hMap.get("hFilePOI")));

		AutomationAssert.verifyTrue(getSelectedDropdownLabel(DocListingPage.drp_PublishWindowAttributesFirstStatus).equalsIgnoreCase(hMap.get("hFileStatus")));
		AutomationAssert.verifyTrue(getSelectedDropdownLabel(DocListingPage.drp_PublishWindowAttributesSecondStatus).equalsIgnoreCase(hMap.get("hFileStatus")));

		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesFirstInteger).equalsIgnoreCase(hMap.get("hInteger")));
		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesSecondInteger).equalsIgnoreCase(hMap.get("hInteger")));

		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesFirstEmail).equalsIgnoreCase(hMap.get("hEmail")));
		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesSecondEmail).equalsIgnoreCase(hMap.get("hEmail")));

		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesFirstDecimal).equalsIgnoreCase(hMap.get("hDecimal")));
		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesSecondDecimal).equalsIgnoreCase(hMap.get("hDecimal")));

		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesFirstLetterNumber).equalsIgnoreCase(hMap.get("hLettersNumbers")));
		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesSecondLetterNumber).equalsIgnoreCase(hMap.get("hLettersNumbers")));

		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesFirstLetter).equalsIgnoreCase(hMap.get("hLetters")));
		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesSecondLetter).equalsIgnoreCase(hMap.get("hLetters")));

		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesFirstDatePicker).equalsIgnoreCase(hMap.get("hDatePicker")));
		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesSecondDatePicker).equalsIgnoreCase(hMap.get("hDatePicker")));

		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesFirstCoordinate1).equalsIgnoreCase(hMap.get("hCoordinatesOne")));
		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesSecondCoordinate1).equalsIgnoreCase(hMap.get("hCoordinatesOne")));

		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesFirstCoordinate2X).equalsIgnoreCase(hMap.get("hCoordinatesTwoX")));
		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesFirstCoordinate2X).equalsIgnoreCase(hMap.get("hCoordinatesTwoX")));
		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesFirstCoordinate2Y).equalsIgnoreCase(hMap.get("hCoordinatesTwoY")));
		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesFirstCoordinate2Y).equalsIgnoreCase(hMap.get("hCoordinatesTwoY")));

		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesFirstCoordinate3X).equalsIgnoreCase(hMap.get("hCoordinatesX")));
		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesSecondCoordinate3X).equalsIgnoreCase(hMap.get("hCoordinatesX")));
		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesFirstCoordinate3Y).equalsIgnoreCase(hMap.get("hCoordinatesY")));
		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesSecondCoordinate3Y).equalsIgnoreCase(hMap.get("hCoordinatesY")));
		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesFirstCoordinate3Z).equalsIgnoreCase(hMap.get("hCoordinatesZ")));
		AutomationAssert.verifyTrue(getValue(DocListingPage.txt_PublishWindowAttributesSecondCoordinate3Z).equalsIgnoreCase(hMap.get("hCoordinatesZ")));

		AutomationAssert.verifyTrue(getSelectedDropdownLabel(DocListingPage.drp_PublishWindowAttributesFirstState).equalsIgnoreCase(hMap.get("hState")));
		AutomationAssert.verifyTrue(getSelectedDropdownLabel(DocListingPage.drp_PublishWindowAttributesSecondState).equalsIgnoreCase(hMap.get("hState")));
		AutomationAssert.verifyTrue(getSelectedDropdownLabel(DocListingPage.drp_PublishWindowAttributesFirstCity).equalsIgnoreCase(hMap.get("hCity")));
		AutomationAssert.verifyTrue(getSelectedDropdownLabel(DocListingPage.drp_PublishWindowAttributesSecondCity).equalsIgnoreCase(hMap.get("hCity")));

	}

	public void selectDoNotDistributeOption(String distributionOption) {
		selectByVisibleText(DocListingPage.drp_PublishWindowAttributesDistribute, distributionOption);
	}

}
