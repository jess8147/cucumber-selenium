/*  Testdata required for this script as follows.
     1). AutomationBulkApplyTestFile1.pdf  and AutomationBulkApplyTestFile2.pdf files in CustomAttributes folder of Project
     2). Both files should be active.  
 */

package org.asite.automation.adoddle.p2.scripts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.JavaUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class UploadFilesBulkApplyScripts extends AdoddleCommonAppMethods {

	private String							parentHandle, key, value;
	private int								existingFileRevision;
	final private HashMap<String, String>	expectedAttributes		= new HashMap<String, String>();
	final private HashMap<String, String>	actualAttributes		= new HashMap<String, String>();
	final private List<String>				docRefSetAttributesList	= Arrays.asList("IntegerTextbox", "Cities", "States", "LettersNumbersTextbox");
	final private List<String>				newFileList				= new ArrayList<String>();
	private List<String>					revisionFileList		= new ArrayList<String>();
	private List<WebElement>				customAttributeElements	= new ArrayList<WebElement>();
	final private static Logger				log						= AutomationLogger.getInstance().getLogger(UploadFilesBulkApplyScripts.class);

	public void getCurrentRevision() {
		revisionFileList = sysUtils.getFileList(ResourceHandler.loadProperty("multi.revision.files.path"));
		searchFiles(strUtils.extractFileNameString(revisionFileList.get(0)));
		existingFileRevision = Integer.parseInt(getElementText(FilesTab.lnk_FileListingFirstRevision).trim());
	}

	public void clickAddFilesButton(String btnText) {
		AutomationAssert.verifyTrue(getElementAttributeValue(FilesTab.btn_AddFiles, "title").contains(btnText));
		clickElementAndWaitForElement(FilesTab.btn_AddFiles, GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();

	}

	public void selectMultipleRevisionFiles() {
		uploadFileUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, revisionFileList);
	}

	public void selectMultipleNewFiles() throws InterruptedException {
		sysUtils.authenticateRemoteMachine(nodeIP);
		for (int index = 0; index < 2; index++) {
			String fileName = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP);
			newFileList.add(fileName);
			waitUtils.waitInterval(1);
		}
		uploadFileUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, newFileList);
	}

	public void enterHeaderValuesAndApplyAll() {

		clickElementAndWait(FilesTab.chk_PopUploadFilesBulkApply);
		List<WebElement> applyBlankElements = findElements(FilesTab.css_PopUploadFileHeaderApplyDown);
		for (WebElement e : applyBlankElements) {
			clickElementAndWait(e);
		}

		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);

		for (WebElement e : findElements(FilesTab.css_PopUploadFilesDocTitlesInput))
			AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getValue(e), ""), getValue(e).equalsIgnoreCase(""));
		for (WebElement e : findElements(FilesTab.css_PopUploadCustomAttributesFilesEmailTextBoxes))
			AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getValue(e), ""), getValue(e).equalsIgnoreCase(""));
		for (WebElement e : findElements(FilesTab.css_PopUploadCustomAttributesFilesLetters))
			AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getValue(e), ""), getValue(e).equalsIgnoreCase(""));
		for (WebElement e : findElements(FilesTab.css_PopUploadCustomAttributesFilesCoordinatesX))
			AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getValue(e), ""), getValue(e).equalsIgnoreCase(""));
		for (WebElement e : findElements(FilesTab.css_PopUploadCustomAttributesFilesCoordinatesXXX))
			AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getValue(e), ""), getValue(e).equalsIgnoreCase(""));
		for (WebElement e : findElements(FilesTab.css_PopUploadCustomAttributesFilesCoordinatesYYY))
			AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getValue(e), ""), getValue(e).equalsIgnoreCase(""));
		for (WebElement e : findElements(FilesTab.css_PopUploadCustomAttributesFilesCoordinatesZZZ))
			AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getValue(e), ""), getValue(e).equalsIgnoreCase(""));
		for (WebElement e : findElements(FilesTab.css_PopUploadCustomAttributesFilesDecimalBoxes))
			AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getValue(e), ""), getValue(e).equalsIgnoreCase(""));
		for (WebElement e : findElements(FilesTab.css_PopUploadCustomAttributesFilesDatePickers))
			AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getValue(e), ""), getValue(e).equalsIgnoreCase(""));
		for (WebElement e : findElements(FilesTab.css_PopUploadCustomAttributesFilesCoordinatesXX))
			AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getValue(e), ""), getValue(e).equalsIgnoreCase(""));
		for (WebElement e : findElements(FilesTab.css_PopUploadCustomAttributesFilesCoordinatesYY))
			AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getValue(e), ""), getValue(e).equalsIgnoreCase(""));

		clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
		enterHeaderAttributeValues();
		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
		clickElementAndWait(FilesTab.btn_PopUploadApplytoAll);
	}

	private void enterHeaderAttributeValues() {
		String integerTextBoxValue, emailValue, letterValue, x, xx, yy, xxx, yyy, zzz, xyz, xy, decimalValue, lettersNumberValue;

		sendKeys(FilesTab.txt_PopUploadHeaderRevInput, String.valueOf(existingFileRevision + 1));
		selectByIndex(FilesTab.drp_PopUploadHeaderPurposeOfIssue, 1);
		selectByIndex(FilesTab.drp_PopUploadHeaderStatus, 1);
		integerTextBoxValue = JavaUtils.getRandomNumber(3);
		sendKeys(FilesTab.txt_PopUploadHeaderIntegerTextBox, integerTextBoxValue);
		emailValue = javaUtils.getRandomString(4) + AdoddleCommonStringPool.AT_STRING + javaUtils.getRandomString(4) + AdoddleCommonStringPool.DOT_STRING + javaUtils.getRandomString(3);
		sendKeys(FilesTab.txt_PopUploadHeaderEmailTextBox, emailValue);
		letterValue = javaUtils.getRandomString(3);
		sendKeys(FilesTab.txt_PopUploadHeaderLetterBox, letterValue);
		clickElement(FilesTab.rad_PopUploadHeaderRadioYes);
		expectedAttributes.put("Radio", "Yes");

		x = JavaUtils.getRandomNumber(3);
		xxx = JavaUtils.getRandomNumber(3);
		yyy = JavaUtils.getRandomNumber(3);
		zzz = JavaUtils.getRandomNumber(3);

		sendKeys(FilesTab.txt_PopUploadHeader1DCoordinatesX, x);
		sendKeys(FilesTab.txt_PopUploadHeader3DCoordinatesX, xxx);
		sendKeys(FilesTab.txt_PopUploadHeader3DCoordinatesY, yyy);
		sendKeys(FilesTab.txt_PopUploadHeader3DCoordinatesZ, zzz);

		selectByIndex(FilesTab.drp_PopUploadHeaderStates, 1);
		selectByIndex(FilesTab.drp_PopUploadHeaderCities, 1);

		decimalValue = JavaUtils.getRandomNumber(2) + AdoddleCommonStringPool.DOT_STRING + JavaUtils.getRandomNumber(2);
		sendKeys(FilesTab.txt_PopUploadHeaderDecimalTextBox, decimalValue);

		lettersNumberValue = javaUtils.getRandomString(3) + JavaUtils.getRandomNumber(3);
		sendKeys(FilesTab.txt_PopUploadHeaderLettersNumbersBox, lettersNumberValue);

		clickElementAndWaitForElement(FilesTab.img_PopUploadHeaderDatePicker, GlobalPageElements.lnk_DatePickerCalenderToday);
		clickElementAndWait(GlobalPageElements.lnk_DatePickerCalenderToday);

		clickElementAndWaitForElement(FilesTab.sel_PopUploadHeaderMultiSelectionBox, FilesTab.chk_PopUploadHeaderMultiSelectionOption1);
		clickElementAndWait(FilesTab.chk_PopUploadHeaderMultiSelectionOption1);

		xx = JavaUtils.getRandomNumber(3);
		sendKeys(FilesTab.txt_PopUploadHeader2DCoordinatesX, xx);

		yy = JavaUtils.getRandomNumber(3);
		sendKeys(FilesTab.txt_PopUploadHeader2DCoordinatesY, yy);

		mouseHoverClick(FilesTab.txt_PopUploadHeaderIntegerTextBox);
		expectedAttributes.put("IntegerTextbox", getValue(FilesTab.txt_PopUploadHeaderIntegerTextBox));

		mouseHoverClick(FilesTab.txt_PopUploadHeaderEmailTextBox);
		expectedAttributes.put("EmailTextbox", getValue(FilesTab.txt_PopUploadHeaderEmailTextBox));

		clickElementAndWait(FilesTab.txt_PopUploadHeaderLetterBox);
		expectedAttributes.put("Letter", getValue(FilesTab.txt_PopUploadHeaderLetterBox));
		expectedAttributes.put("Coordinates1Dimension", getValue(FilesTab.txt_PopUploadHeader1DCoordinatesX));

		//clickElementAndWait(FilesTab.txt_PopUploadHeader3DCoordinatesX);
		mouseHoverClick(FilesTab.txt_PopUploadHeader1DCoordinatesX);
		xyz = getValue(FilesTab.txt_PopUploadHeader3DCoordinatesX);
		clickElementAndWait(FilesTab.txt_PopUploadHeader3DCoordinatesY);
		xyz = xyz + AdoddleCommonStringPool.COMMA_STRING + getValue(FilesTab.txt_PopUploadHeader3DCoordinatesY);
		clickElementAndWait(FilesTab.txt_PopUploadHeader3DCoordinatesZ);
		xyz = xyz + AdoddleCommonStringPool.COMMA_STRING + getValue(FilesTab.txt_PopUploadHeader3DCoordinatesZ);
		expectedAttributes.put("Coordinates3Dimension", xyz);
		expectedAttributes.put("States", getSelectedDropdownLabel(FilesTab.drp_PopUploadHeaderStates));
		expectedAttributes.put("Cities", getSelectedDropdownLabel(FilesTab.drp_PopUploadHeaderCities));

		clickElementAndWait(FilesTab.txt_PopUploadHeaderDecimalTextBox);
		expectedAttributes.put("DecimalTextbox", getValue(FilesTab.txt_PopUploadHeaderDecimalTextBox));

		clickElementAndWait(FilesTab.txt_PopUploadHeaderLettersNumbersBox);
		expectedAttributes.put("LettersNumbersTextbox", getValue(FilesTab.txt_PopUploadHeaderLettersNumbersBox));
		expectedAttributes.put("MultiSelectionCheckbox", getElementText(FilesTab.sel_PopUploadHeaderMultiSelectionBox));
		clickElementAndWait(FilesTab.txt_PopUploadHeader2DCoordinatesX);
		xy = getValue(FilesTab.txt_PopUploadHeader2DCoordinatesX);
		clickElementAndWait(FilesTab.txt_PopUploadHeader2DCoordinatesY);
		xy = xy + AdoddleCommonStringPool.COMMA_STRING + getValue(FilesTab.txt_PopUploadHeader2DCoordinatesY);
		expectedAttributes.put("Coordinates2Dimension", xy);
		log.info("Expected Custom Attributes map# \n" + expectedAttributes.toString());
	}

	public void selectDistributionUser() throws InterruptedException {
		sendKeys(FilesTab.txt_PopUploadFileDistributeTo, System.getProperty("primary.username"));
		sendKeys(FilesTab.txt_PopUploadFileDistributeTo, Keys.TAB);
		waitForCompletePageLoad();
		clickElementAndWait(FilesTab.btn_PopUploadFileDistributeUpload);
		if (isDisplayed(FilesTab.btn_PopUploadSameRevisionContinue))
			clickElementAndWait(FilesTab.btn_PopUploadSameRevisionContinue);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
	}

	public void verifyNewFilesAttributes() {
		parentHandle = getCurrentWindow();
		navigateTab(LandingPage.lnk_Files);
		searchFiles(strUtils.extractFileNameString(newFileList.get(0)).replace(".pdf", ""));
		collectionDataMap.put("File", strUtils.extractFileNameString(newFileList.get(0)));
		clickElementAndSwitchWindow(FilesTab.lnk_DocListingFirstDocRef);
		if (!fileBetaViewFlag)
			clickElementAndWait(FilesTab.lnk_FileViewerHeaderShowMore);
		else {
			clickElementAndWaitForElement(FilesTab.btn_BetaFileViewMoreOptions, FilesTab.btn_BetaFileViewFileDetails);
			clickElementAndWaitForElement(FilesTab.btn_BetaFileViewFileDetails, FilesTab.ele_BetaViewFileCustomAtrributesTable);
		}

		customAttributeElements = findElements(!fileBetaViewFlag ? FilesTab.css_FileViewerFileAttributes : FilesTab.css_BetaFileViewerFileAttributes);
		if (!fileBetaViewFlag) {
			for (WebElement e : customAttributeElements) {
				key = e.getText().split(":")[0].trim();
				value = e.getText().split(":")[1].trim().split(" ")[0];
				if (!key.equalsIgnoreCase("DatePicker"))
					actualAttributes.put(key, value);
			}
		}
		else {
			for (WebElement e : customAttributeElements) {
				key = getElementText(e.findElement(By.tagName("th")));
				value = getElementText(e.findElement(By.tagName("td"))).trim().split(" ")[0];
				if (!key.equalsIgnoreCase("DatePicker"))
					actualAttributes.put(key, value);
			}
		}

		log.info("New File Actual Attributes map# \n" + actualAttributes.toString());
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
		javaUtils.compareMapList(expectedAttributes, actualAttributes);
	}

	public void verifyRevisionFilesAttributes() {

		navigateTab(LandingPage.lnk_Files);
		searchFiles(strUtils.extractFileNameString(revisionFileList.get(0)));
		collectionDataMap.put("File", strUtils.extractFileNameString(revisionFileList.get(0)));

		clickElementAndSwitchWindow(FilesTab.lnk_DocListingFirstDocRef);
		if (!fileBetaViewFlag)
			clickElementAndWait(FilesTab.lnk_FileViewerHeaderShowMore);
		else {
			clickElementAndWaitForElement(FilesTab.btn_BetaFileViewMoreOptions, FilesTab.btn_BetaFileViewFileDetails);
			clickElementAndWaitForElement(FilesTab.btn_BetaFileViewFileDetails, FilesTab.ele_BetaViewFileCustomAtrributesTable);
		}

		customAttributeElements = findElements(!fileBetaViewFlag ? FilesTab.css_FileViewerFileAttributes : FilesTab.css_BetaFileViewerFileAttributes);
		if (!fileBetaViewFlag) {
			for (WebElement e : customAttributeElements) {
				key = e.getText().split(":")[0].trim();
				value = e.getText().split(":")[1].trim().split(" ")[0];

				if (!(docRefSetAttributesList).contains(key))
					actualAttributes.put(key, value);
				else
					actualAttributes.put(key, expectedAttributes.get(key));
			}
		}
		else {
			for (WebElement e : customAttributeElements) {
				key = getElementText(e.findElement(By.tagName("th")));
				value = getElementText(e.findElement(By.tagName("td"))).trim().split(" ")[0];
				if (!(docRefSetAttributesList).contains(key))
					actualAttributes.put(key, value);
				else
					actualAttributes.put(key, expectedAttributes.get(key));
			}
		}

		log.info("Revision File Expected Attributes map# \n" + expectedAttributes.toString());
		log.info("Revision File Actual Attributes map# \n" + actualAttributes.toString());
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
		javaUtils.compareMapList(expectedAttributes, actualAttributes);
	}
}
