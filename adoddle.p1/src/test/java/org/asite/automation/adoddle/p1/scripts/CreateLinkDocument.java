package org.asite.automation.adoddle.p1.scripts;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.JavaUtils;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CreateLinkDocument extends AdoddleCommonAppMethods {

	private String					linkFileName, parentHandle, secondaryFilePath;
	private static String			targetFolderName;
	private int						oldRevision;
	private int						updatedRevision;
	private HashMap<String, String>	expectedAttributes			= new HashMap<String, String>();
	private HashMap<String, String>	expectedStaticAttributes	= new HashMap<String, String>();
	final private static Logger		log							= AutomationLogger.getInstance().getLogger(CreateLinkDocument.class);

	public void navigateToFilesTab() {
		navigateTab(LandingPage.lnk_Files);
	}

	public void verifyFolderCountInProject(String lType) {
		setLinkTestFolder(System.getProperty("global.test.project"), lType);
		clickElementWithText(System.getProperty("global.test.project"));
		AutomationAssert.verifyTrue(getCount(FilesTab.css_FilesProjectSubFolders) > 1);
	}

	private void setLinkTestFolder(String project, String lType) {
		clickElementWithText(project);
		if (!lType.equalsIgnoreCase(AdoddleCommonStringPool.STRING_DYNAMIC))
			targetFolderName = resourceUtils.getStaticLinkTestDataFolder();
		else {
			targetFolderName = lType + AdoddleCommonStringPool.UNDERSCORE_STRING + dateUtils.getEpoch();
			createProjectFolder(project, targetFolderName);
			collectionDataMap.put("Target Link Folder", targetFolderName);
		}
		navigateTab(LandingPage.lnk_Files);

	}

	public void verifyFilesCountOnFilesListing(String lType) throws InterruptedException, IOException {
		parentHandle = getCurrentWindow();
		if (!lType.equalsIgnoreCase(AdoddleCommonStringPool.STRING_DYNAMIC))
			linkFileName = uploadDocumentsWithCustomAttributes(null, 1, null, null, false, 1, Arrays.asList(AdoddleCommonStringPool.FOR_INFORMATION), Arrays.asList(AdoddleCommonStringPool.FOR_APPROVAL), true, null, null, null, Float.parseFloat(JavaUtils.getRandomNumber(3)), null, null, null).get(0);
		else
			linkFileName = resourceUtils.getLinkDocTestDataFile();
		log.info("Link testdata file name: " + linkFileName);
		linkFileName = strUtils.extractFileNameString(linkFileName);
		collectionDataMap.put("Linked File Name", linkFileName);
		searchFiles(linkFileName);
		AutomationAssert.verifyTrue(getCount(FilesTab.css_DocListingFilesCount) > 0);
		oldRevision = Integer.parseInt(getElementText(FilesTab.lnk_FileListingFirstRevision));
		clickElementAndSwitchWindow(FilesTab.lnk_DocListingFirstDocRef);
		expectedStaticAttributes = getDocumentCustomAttributes();
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
		log.info("Expected Static Link Custom Attributes: " + expectedStaticAttributes);

	}

	public void selectLinkDocument() {
		waitUntilElementIsDisplayed(FilesTab.chk_DocListingFirstCheckBox);
		clickElementAndWait(FilesTab.chk_DocListingFirstCheckBox);
	}

	public void selectLinkOptionFromMoreOption() {
		contextClick(FilesTab.lnk_DocListingFirstFileName);
		clickContextMenuOption(AdoddleCommonStringPool.LABEL_LINK_FILES);
		waitUntilElementIsDisplayed(FilesTab.pop_TargetFolderModel);
	}

	public void selectDestinationFolderAndClickSubmitButton(String lType, String btnText) {
		if (lType.equalsIgnoreCase(AdoddleCommonStringPool.STRING_STATIC))
			selectFolderOnPopupWindow(resourceUtils.getDynamicLinkTestDataFolder());
		selectFolderOnPopupWindow(targetFolderName);
		clickButtonWithText(btnText);
	}

	public void selectUserAndClickButton(String btnText, String linkType) {
		waitUntilElementIsDisplayed(FilesTab.txt_PopLinkFilesToField);
		sendKeys(FilesTab.txt_PopLinkFilesToField, System.getProperty("secondary.username"));
		sendKeys(FilesTab.txt_PopLinkFilesToField, Keys.TAB);
		selectByVisibleText(FilesTab.drp_PopLinkFilesLinkType, linkType);
		AutomationAssert.verifyTrue(btnText.equalsIgnoreCase(getElementText(FilesTab.btn_PopLinkFilesSubmit)));
		clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_PopLinkFilesSubmit, GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();
	}

	public void verifyLinkDocumentIsAvailableInTargetFolder(String lType) {
		HashMap<String, String>	actualStaticAttributes;
		clickElementWithText(resourceUtils.getDynamicLinkTestDataFolder());
		if (lType.equalsIgnoreCase(AdoddleCommonStringPool.STRING_STATIC))
			clickElementWithText(targetFolderName);
		searchFiles(linkFileName);
		AutomationAssert.verifyTrue(getCount(FilesTab.css_DocListingFilesCount) > 0);
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(Integer.parseInt(getElementText(FilesTab.lnk_FileListingFirstRevision)), oldRevision), Integer.parseInt(getElementText(FilesTab.lnk_FileListingFirstRevision)) == oldRevision);
		clickElementAndWait(FilesTab.lnk_DocListingFirstFileName);
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
		actualStaticAttributes = getDocumentCustomAttributes();
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(expectedStaticAttributes.toString(), actualStaticAttributes.toString()), compareUnorderedMaps(expectedStaticAttributes, actualStaticAttributes));
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
	}

	public void verifyActionAssignedToSelectedUser() throws InterruptedException {
		logOut();
		login(System.getProperty("secondary.username"), System.getProperty("secondary.password"));
		navigateTab(LandingPage.lnk_Files);
		clickElementWithText(System.getProperty("global.test.project"));
		clickElementWithText(resourceUtils.getDynamicLinkTestDataFolder());
		clickElementWithText(targetFolderName);
		searchFiles(linkFileName);
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getElementText(FilesTab.lnk_FilesFirstAction), AdoddleCommonStringPool.FOR_INFORMATION), getElementText(FilesTab.lnk_FilesFirstAction).equalsIgnoreCase(AdoddleCommonStringPool.FOR_INFORMATION));
	}

	public void uploadRevision() {
		parentHandle = getCurrentWindow();
		searchFiles(resourceUtils.getLinkDocTestDataFile());
		oldRevision = Integer.parseInt(getElementText(FilesTab.lnk_FileListingFirstRevision));
		clickElementAndWait(FilesTab.lnk_DocListingFirstFileName);
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
		expectedAttributes = getDocumentCustomAttributes();
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
		clickElementAndWait(FilesTab.btn_AddFiles);
		sysUtils.authenticateRemoteMachine(nodeIP);
		findElement(FilesTab.btn_PopUploadFileDistributeSelectFiles).sendKeys(resourceUtils.getRemoteLinkDocTestDataFile());
		sysUtils.authenticateRemoteMachine(nodeIP);
		secondaryFilePath = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + epoch + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP);
		waitUntilElementIsDisplayed(FilesTab.txt_PopUploadFileDistributeRev);
		findElement(FilesTab.btn_PopUploadFileAttachSecondaryFiles).sendKeys(secondaryFilePath);
		waitUntilElementIsClickable(GlobalPageElements.pop_PopUpElement);
		waitUntilElementIsDisplayed(FilesTab.txt_PopUploadFileDistributeRev);
		enterCustomAttributes();

	}

	private void enterCustomAttributes() {
		String x2, y2, x3, y3, z3 /*,letter, email*/;
		log.info("Old revision of file: " + oldRevision);
		updatedRevision = ++oldRevision;
		log.info("Updated revision of file: " + updatedRevision);

		sendKeys(FilesTab.txt_PopUploadFileDistributeRev, String.valueOf(updatedRevision));
		selectByIndex(FilesTab.drp_PopUploadFileDistributePurpose, 1);
		selectByIndex(FilesTab.drp_PopUploadFileDistributeStatus, 2);
		sendKeys(FilesTab.txt_DecimalTextboxFile1, JavaUtils.getRandomNumber(3) + "." + JavaUtils.getRandomNumber(2));
		findElement(FilesTab.txt_DecimalTextboxFile1).click();
		expectedAttributes.put("DecimalTextbox", findElement(FilesTab.txt_DecimalTextboxFile1).getAttribute("title"));
		sendKeys(FilesTab.txt_UploadFileFirstLetterTextbox, javaUtils.getRandomString(4));
		findElement(FilesTab.txt_UploadFileFirstEmailTextbox).click();
		findElement(FilesTab.txt_UploadFileFirstLetterTextbox).click();
		expectedAttributes.put("Letter", findElement(FilesTab.txt_UploadFileFirstLetterTextbox).getAttribute("title"));
		expectedAttributes.put("MultiSelectionCheckbox", getElementText(FilesTab.sel_MultiSelectionCheckboxFile1));

		clickElementAndWait(FilesTab.txt_DatePickerFile1);
		/*
		 * if(!isDisplayed(ProjectFormsTab. lnk_CreateFormDistributionActionActiveDate)) clickElementAndWaitForElement(GlobalPageElements.lnk_DatePickerNext, ProjectFormsTab.lnk_CreateFormDistributionActionActiveDate); clickElementAndWait (ProjectFormsTab.lnk_CreateFormDistributionActionActiveDate);
		 */
		expectedAttributes.put("DatePicker", findElement(FilesTab.txt_DatePickerFile1).getAttribute("value"));

		sendKeys(FilesTab.txt_Coordinates1DimensionXFile1, JavaUtils.getRandomNumber(3));
		findElement(FilesTab.txt_Coordinates1DimensionXFile1).click();
		expectedAttributes.put("Coordinates1Dimension", findElement(FilesTab.txt_Coordinates1DimensionXFile1).getAttribute("value"));

		sendKeys(FilesTab.txt_Coordinates2DimensionXFile1, JavaUtils.getRandomNumber(3));
		sendKeys(FilesTab.txt_Coordinates2DimensionYFile1, JavaUtils.getRandomNumber(3));
		findElement(FilesTab.txt_Coordinates2DimensionXFile1).click();
		x2 = findElement(FilesTab.txt_Coordinates2DimensionXFile1).getAttribute("value");
		findElement(FilesTab.txt_Coordinates2DimensionYFile1).click();
		y2 = findElement(FilesTab.txt_Coordinates2DimensionYFile1).getAttribute("value");
		expectedAttributes.put("Coordinates2Dimension", x2 + "," + y2);

		sendKeys(FilesTab.txt_Coordinates3DimensionXFile1, JavaUtils.getRandomNumber(3));
		sendKeys(FilesTab.txt_Coordinates3DimensionYFile1, JavaUtils.getRandomNumber(3));
		sendKeys(FilesTab.txt_Coordinates3DimensionZFile1, JavaUtils.getRandomNumber(3));

		findElement(FilesTab.txt_Coordinates3DimensionXFile1).click();
		x3 = findElement(FilesTab.txt_Coordinates3DimensionXFile1).getAttribute("value");
		findElement(FilesTab.txt_Coordinates3DimensionYFile1).click();
		y3 = findElement(FilesTab.txt_Coordinates3DimensionYFile1).getAttribute("value");
		findElement(FilesTab.txt_Coordinates3DimensionZFile1).click();
		z3 = findElement(FilesTab.txt_Coordinates3DimensionZFile1).getAttribute("value");
		expectedAttributes.put("Coordinates3Dimension", x3 + "," + y3 + "," + z3);
		
		clickElementAndWait(FilesTab.btn_PopUploadFileDistributeUpload);
		if (isDisplayed(FilesTab.btn_PopUploadFileSameRevisionContinue))
			clickElementAndWait(FilesTab.btn_PopUploadFileSameRevisionContinue);
		waitForCompletePageLoad();

	}

	public void verifyUploadedRevision() {
		HashMap<String, String>	actualAttributes;
		boolean remarkFlag = false;
		clickElementWithText(resourceUtils.getDynamicLinkTestDataFolder());
		waitUntilElementIsDisplayed(FilesTab.lnk_DocListingFirstFileName);
		searchFiles(resourceUtils.getLinkDocTestDataFile());
		AutomationAssert.verifyTrue(getCount(FilesTab.css_DocListingFilesCount) > 0);
		AutomationAssert.verifyTrue(Integer.parseInt(getElementText(FilesTab.lnk_FileListingFirstRevision)) == updatedRevision);
		clickElementAndSwitchWindow(FilesTab.lnk_DocListingFirstFileName);
		try {
			waitForHTMLFileView();
		} catch (InterruptedException e1) {
			log.error(e1.getLocalizedMessage());
		}
		actualAttributes = getDocumentCustomAttributes();
		Assert.assertEquals(expectedAttributes, actualAttributes);

		if(!fileBetaViewFlag) {
			clickElementAndWait(FilesTab.lnk_FileViewLHHistoryBlock);
			List<WebElement> fileHistorySubInfoElements = findElements(FilesTab.css_FileViewHistorySubInfoRows);

			for (WebElement e : fileHistorySubInfoElements) {
				if (getElementText(e).contains(AdoddleCommonStringPool.ADDED_SECONDARY_FILE_HISTORY_REMARKS)) {
					AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(e), strUtils.extractFileNameString(secondaryFilePath)), getElementText(e).contains(strUtils.extractFileNameString(secondaryFilePath)));
					clickElementAndWait(e);
					remarkFlag = true;
					break;
				}
			}
		}
		else {
			clickElementAndWaitForElement(FilesTab.btn_BetaFileViewMoreOptions, FilesTab.btn_BetaFileViewFileHistory);
			clickElementAndWaitForElement(FilesTab.btn_BetaFileViewFileHistory, FilesTab.ele_BetaFileViewHistoryPanel);
			waitUntilElementIsInvisible(GlobalPageElements.ele_LoadingCircle);
			waitUntilElementIsInvisible(GlobalPageElements.ele_Loader);
			for(int i=0; i < 3; i++) {
				selectByVisibleText(FilesTab.drp_BetaFileViewHistoryTypeDropdown, "Access");
				if(getSelectedDropdownLabel(FilesTab.drp_BetaFileViewHistoryTypeDropdown).contains("Access"))
					break;
				else {
					try {
						waitUtils.waitInterval(2);
					} catch (InterruptedException e) {
						log.error(e.getLocalizedMessage());
					}
				}
			}
			clickElementAndWaitForElement(FilesTab.img_BetaFileViewMaximizePanel, FilesTab.drp_BetaFileViewHistoryTypeDropdown);
			waitUntilElementIsInvisible(GlobalPageElements.ele_Loader);
			waitUntilElementIsInvisible(GlobalPageElements.ele_LoadingCircle);
			waitUntilElementCountToBeMoreThan(FilesTab.css_BetaViewFileHistoryAccessRemarksRecords, 1);
			List<WebElement> remarksList = findElements(FilesTab.css_BetaViewFileHistoryAccessRemarksRecords);
			for(WebElement e: remarksList) {
				if(getElementText(e).contains(AdoddleCommonStringPool.ADDED_SECONDARY_FILE_HISTORY_REMARKS)) {
					AutomationAssert.verifyTrue(getElementText(e).contains(strUtils.extractFileNameString(secondaryFilePath)));
					remarkFlag = true;
					break;
				}
				else
				{
					remarkFlag = false;
				}
			}
		}
		AutomationAssert.verifyTrue("Remarks history not verified successfully", remarkFlag);
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
	}

	public void verifySecondaryFileAttachment() {
		mouseHover(FilesTab.img_FileListingFirstAttachmentImage);
		clickElementAndWait(FilesTab.img_FileListingFirstAttachmentImage);
		waitForSwitchWindow(2);
		switchWindow();
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(FilesTab.frm_BravaObjectFrame);
		switchIframe(FilesTab.frm_BravaObjectFrame);
		if(!fileBetaViewFlag) {
			waitUntilElementIsDisplayed(FilesTab.ele_ViewAttachFileName2);
			AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getElementText(FilesTab.ele_ViewAttachFileName2), strUtils.extractFileNameString(secondaryFilePath)), getElementText(FilesTab.ele_ViewAttachFileName2).equalsIgnoreCase(strUtils.extractFileNameString(secondaryFilePath)));
		}
		else {
			waitUntilElementIsDisplayed(FilesTab.ele_ViewAttachFileName);
			AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getElementText(FilesTab.ele_ViewAttachFileName), strUtils.extractFileNameString(secondaryFilePath)), getElementText(FilesTab.ele_ViewAttachFileName).equalsIgnoreCase(strUtils.extractFileNameString(secondaryFilePath)));
		}
	}

	public void deactivateTestDataFolder() {
		try {
			deactivateProjectFolder(System.getProperty("global.test.project"), targetFolderName);
		}
		catch (Throwable t) {
			log.info("failed to deactivate folder");
		}
	}

}
