package org.asite.automation.adoddle.p1.scripts;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleAdminLocators.AdminTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.JavaUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class AutoFetchAttributesScripts extends AdoddleCommonAppMethods {

	private String											fileAppender			= AdoddleCommonStringPool.EMPTY_STRING;
	private File											filePath;
	private HashMap<String, String>							attribMap				= null;
	final private HashMap<String, HashMap<String, String>>	allFileMap				= new HashMap<String, HashMap<String, String>>();
	final private List<String>								selectedAttributesText	= new ArrayList<String>();
	final private List<String>								allFileList				= new ArrayList<String>();
	final private static Logger								log						= AutomationLogger.getInstance().getLogger(AutoFetchAttributesScripts.class);
	
	public void verifyPageHeader(String pageTitle) {
		if (pageTitle.equalsIgnoreCase(AdoddleCommonStringPool.AUTO_FETCH_ATTRIBUTES))
			AutomationAssert.verifyTrue(getElementText(AdminTab.lbl_AutoFetchAttributePageHeader).contains(pageTitle));
		if (pageTitle.equalsIgnoreCase(AdoddleCommonStringPool.DESIGN_LAYOUT))
			AutomationAssert.verifyTrue(getElementText(AdminTab.lbl_DesignLayoutPageHeader).contains(pageTitle));
	}

	public void clickAutoFetchRuleEditIcon() {
		executeJScript(AdoddleCommonJQueries.getAutoFetchAttributeRuleJQuery);
		waitUntilElementIsDisplayed(AdminTab.txt_PopEditAutoFetchRuleTitleInput);
	}

	public void editExistingRuleTitle() {
		String editedRuleTitle;
		List<WebElement> selectedAttributes;
		selectedAttributes = findElements(AdminTab.css_PopEditAutoFetchRuleSelectedAttributes);

		for (WebElement e : selectedAttributes)
			selectedAttributesText.add(e.getText());

		log.info("Attributes size# " + selectedAttributes.size());

		editedRuleTitle = "Automation_P1_AutoFetch_Rule_" + dateUtils.getEpoch();
		sendKeys(AdminTab.txt_PopEditAutoFetchRuleTitleInput, editedRuleTitle);
		clickElementAndWait(AdminTab.btn_PopEditAutoFetchRuleSave);

	}

	public void verifyRuleIsSaved() {
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		waitUntilElementIsDisplayed(AdminTab.lbl_AutoFetchAttributeRulesFirstTitle);
		String count = ((JavascriptExecutor) getWebDriver()).executeScript(AdoddleCommonJQueries.getAutoFetchAttributeRuleNameJQuery).toString();
		log.info("Count :"+count);
		AutomationAssert.verifyTrue(1 == Integer.parseInt(count));
	}

	public void getValidFileAttributes(String attribType) {
		String validPOITitle 	= AdoddleCommonStringPool.FOR_REVIEW;
		String validStatusTitle = AdoddleCommonStringPool.FOR_FINAL_REVIEW;

		attribMap = new HashMap<String, String>();

		for (String attribute : selectedAttributesText) {
			if (attribute.equalsIgnoreCase(AdoddleCommonStringPool.LETTER))
				setHashAndFileAppender(AdoddleCommonStringPool.LETTER, "Auto");
			else if (attribute.equalsIgnoreCase(AdoddleCommonStringPool.INTEGER_TEXTBOX))
				setHashAndFileAppender(AdoddleCommonStringPool.INTEGER_TEXTBOX, JavaUtils.getRandomNumber(1));
			else if (attribute.equalsIgnoreCase(AdoddleCommonStringPool.DECIMAL_TEXTBOX))
				setHashAndFileAppender(AdoddleCommonStringPool.DECIMAL_TEXTBOX, JavaUtils.getRandomNumber(2) + "." + JavaUtils.getRandomNumber(2));
			else if (attribute.equalsIgnoreCase(AdoddleCommonStringPool.LETTERS_NUMBERS_TEXTBOX))
				setHashAndFileAppender(AdoddleCommonStringPool.LETTERS_NUMBERS_TEXTBOX, javaUtils.getRandomString(3) + JavaUtils.getRandomNumber(2));
			else if (attribute.equalsIgnoreCase(AdoddleCommonStringPool.EMAIL_TEXTBOX))
				setHashAndFileAppender(AdoddleCommonStringPool.EMAIL_TEXTBOX, javaUtils.getRandomString(1) + "@" + javaUtils.getRandomString(1) + "." + javaUtils.getRandomString(3));
			else if (attribute.equalsIgnoreCase(AdoddleCommonStringPool.STATES))
				setHashAndFileAppender(AdoddleCommonStringPool.STATES, (attribType.equalsIgnoreCase("Code")) ? "3" : "Maharashtra");
			else if (attribute.equalsIgnoreCase(AdoddleCommonStringPool.PURPOSE_OF_ISSUE))
				setHashAndFileAppender(AdoddleCommonStringPool.PURPOSE_OF_ISSUE, (attribType.equalsIgnoreCase("Code")) ? "RW" : validPOITitle);
			else if (attribute.equalsIgnoreCase(AdoddleCommonStringPool.STATUS))
				setHashAndFileAppender(AdoddleCommonStringPool.STATUS, (attribType.equalsIgnoreCase("Code")) ? "FFR" : validStatusTitle);
			else if (attribute.equalsIgnoreCase(AdoddleCommonStringPool.REV))
				setHashAndFileAppender(AdoddleCommonStringPool.REV, JavaUtils.getRandomNumber(1));
			else if (attribute.equalsIgnoreCase(AdoddleCommonStringPool.DOC_TITLE))
				setHashAndFileAppender(AdoddleCommonStringPool.DOC_TITLE, javaUtils.getRandomString(8));
		}

		allFileMap.put(fileAppender, attribMap);

		System.out.println("File title would be: " + fileAppender);
		System.out.println("All File Map content would be: " + allFileMap.toString());

	}

	public void getInValidFileAttributes(String attribType) {
		String invalidPOITitle	= AdoddleCommonStringPool.FOR_NOTHING;
		String invalidStatusTitle = AdoddleCommonStringPool.FOR_NOTHING_AT_ALL;
		attribMap = new HashMap<String, String>();

		for (String attribute : selectedAttributesText) {
			if (attribute.equalsIgnoreCase(AdoddleCommonStringPool.LETTER))
				setHashAndFileAppender(AdoddleCommonStringPool.LETTER, "Auto");
			else if (attribute.equalsIgnoreCase(AdoddleCommonStringPool.INTEGER_TEXTBOX))
				setHashAndFileAppender(AdoddleCommonStringPool.INTEGER_TEXTBOX, JavaUtils.getRandomNumber(1));
			else if (attribute.equalsIgnoreCase(AdoddleCommonStringPool.DECIMAL_TEXTBOX))
				setHashAndFileAppender(AdoddleCommonStringPool.DECIMAL_TEXTBOX, JavaUtils.getRandomNumber(2) + "." + JavaUtils.getRandomNumber(2));
			else if (attribute.equalsIgnoreCase(AdoddleCommonStringPool.LETTERS_NUMBERS_TEXTBOX))
				setHashAndFileAppender(AdoddleCommonStringPool.LETTERS_NUMBERS_TEXTBOX, javaUtils.getRandomString(3) + JavaUtils.getRandomNumber(2));
			else if (attribute.equalsIgnoreCase(AdoddleCommonStringPool.EMAIL_TEXTBOX))
				setHashAndFileAppender(AdoddleCommonStringPool.EMAIL_TEXTBOX, javaUtils.getRandomString(1) + "@" + javaUtils.getRandomString(1) + "." + javaUtils.getRandomString(3));
			else if (attribute.equalsIgnoreCase(AdoddleCommonStringPool.STATES))
				setHashAndFileAppender(AdoddleCommonStringPool.STATES, (attribType.equalsIgnoreCase("Code")) ? "10" : "Assam");
			else if (attribute.equalsIgnoreCase(AdoddleCommonStringPool.PURPOSE_OF_ISSUE))
				setHashAndFileAppender(AdoddleCommonStringPool.PURPOSE_OF_ISSUE, (attribType.equalsIgnoreCase("Code")) ? "BAD" : invalidPOITitle);
			else if (attribute.equalsIgnoreCase(AdoddleCommonStringPool.STATUS))
				setHashAndFileAppender(AdoddleCommonStringPool.STATUS, (attribType.equalsIgnoreCase("Code")) ? "TAD" : invalidStatusTitle);
			else if (attribute.equalsIgnoreCase(AdoddleCommonStringPool.REV))
				setHashAndFileAppender(AdoddleCommonStringPool.REV, JavaUtils.getRandomNumber(1));
			else if (attribute.equalsIgnoreCase(AdoddleCommonStringPool.DOC_TITLE))
				setHashAndFileAppender(AdoddleCommonStringPool.DOC_TITLE, javaUtils.getRandomString(8));
		}

		allFileMap.put(fileAppender, attribMap);
		System.out.println("File title would be: " + fileAppender);
		System.out.println("All File Map content would be: " + allFileMap.toString());

	}

	private void setHashAndFileAppender(String key, String val) {
		String separator = AdoddleCommonStringPool.UNDERSCORE_STRING;
		attribMap.put(key, val);
		fileAppender = (!key.equalsIgnoreCase(AdoddleCommonStringPool.DOC_TITLE)) ? fileAppender + attribMap.get(key) + separator : fileAppender + attribMap.get(key);

		if (key.equalsIgnoreCase(AdoddleCommonStringPool.STATES))
			attribMap.put(key, "Maharashtra");
		else if (key.equalsIgnoreCase(AdoddleCommonStringPool.PURPOSE_OF_ISSUE))
			attribMap.put(key, "For Review");
		else if (key.equalsIgnoreCase(AdoddleCommonStringPool.STATUS))
			attribMap.put(key, "For Final Review");
	}

	public void createUploadTestDataFile() {
		sysUtils.authenticateRemoteMachine(nodeIP);
		filePath = new File(nodeIP + ResourceHandler.loadProperty("remote.download.file.path") + fileAppender + AdoddleCommonStringPool.PDF_EXTENSION);
		allFileList.add(sysUtils.createRemotePdfFile(filePath.toString(), nodeIP));
		fileAppender = "";
	}

	public void uploadFileWithAttributesConjuction() {
		clickElementAndWait(FilesTab.btn_AddFiles);
		findElement(FilesTab.btn_PopUploadFileDistributeSelectFiles).sendKeys(filePath.toString());
		waitForCompletePageLoad();
	}

	public void verifyAutoFetchAttributes(String flag) {
		waitUntilElementIsDisplayed(FilesTab.txt_PopUploadFileDistributeRev);
		clickElement(FilesTab.txt_PopUploadFileDistributeRev);
		AutomationAssert.verifyTrue(getValue(FilesTab.txt_PopUploadFileDistributeRev).equalsIgnoreCase(attribMap.get(AdoddleCommonStringPool.REV)));

		clickElement(FilesTab.txt_DocTitleFile1);
		AutomationAssert.verifyTrue(getValue(FilesTab.txt_DocTitleFile1).equalsIgnoreCase(attribMap.get(AdoddleCommonStringPool.DOC_TITLE)));

		if (flag.equalsIgnoreCase("yes")) {
			AutomationAssert.verifyTrue("Expected# " + getSelectedDropdownLabel(FilesTab.drp_PopUploadFileDistributePurpose) + "\nActual# " + attribMap.get(AdoddleCommonStringPool.PURPOSE_OF_ISSUE), getSelectedDropdownLabel(FilesTab.drp_PopUploadFileDistributePurpose).equalsIgnoreCase(attribMap.get(AdoddleCommonStringPool.PURPOSE_OF_ISSUE)));
			AutomationAssert.verifyTrue("Expected# " + getSelectedDropdownLabel(FilesTab.drp_PopUploadFileDistributeStatus) + "\nActual# " + attribMap.get(AdoddleCommonStringPool.STATUS), getSelectedDropdownLabel(FilesTab.drp_PopUploadFileDistributeStatus).equalsIgnoreCase(attribMap.get(AdoddleCommonStringPool.STATUS)));
			AutomationAssert.verifyTrue("Expected# " + getSelectedDropdownLabel(FilesTab.drp_PopUploadFileDistributeStates) + "\nActual# " + attribMap.get(AdoddleCommonStringPool.STATES), getSelectedDropdownLabel(FilesTab.drp_PopUploadFileDistributeStates).equalsIgnoreCase(attribMap.get(AdoddleCommonStringPool.STATES)));

		}
		else {
			AutomationAssert.verifyTrue(getSelectedDropdownLabel(FilesTab.drp_PopUploadFileDistributePurpose).equalsIgnoreCase(AdoddleCommonStringPool.PLEASE_SELECT));
			AutomationAssert.verifyTrue(getSelectedDropdownLabel(FilesTab.drp_PopUploadFileDistributeStatus).equalsIgnoreCase(AdoddleCommonStringPool.PLEASE_SELECT));
			AutomationAssert.verifyTrue(getSelectedDropdownLabel(FilesTab.drp_PopUploadFileDistributeStates).equalsIgnoreCase(AdoddleCommonStringPool.PLEASE_SELECT));
		}

		clickElement(FilesTab.txt_UploadFileFirstLetterTextbox);
		AutomationAssert.verifyTrue(getValue(FilesTab.txt_UploadFileFirstLetterTextbox).equalsIgnoreCase(attribMap.get(AdoddleCommonStringPool.LETTER)));

		clickElement(FilesTab.txt_IntegerTextboxFile1);
		AutomationAssert.verifyTrue(getValue(FilesTab.txt_IntegerTextboxFile1).equalsIgnoreCase(attribMap.get(AdoddleCommonStringPool.INTEGER_TEXTBOX)));

		clickElement(FilesTab.txt_UploadFileFirstEmailTextbox);
		AutomationAssert.verifyTrue(getValue(FilesTab.txt_UploadFileFirstEmailTextbox).equalsIgnoreCase(attribMap.get(AdoddleCommonStringPool.EMAIL_TEXTBOX)));

		clickElement(FilesTab.txt_DecimalTextboxFile1);
		AutomationAssert.verifyTrue(getValue(FilesTab.txt_DecimalTextboxFile1).equalsIgnoreCase(attribMap.get(AdoddleCommonStringPool.DECIMAL_TEXTBOX)));

		clickElement(FilesTab.txt_LettersNumbersTextboxFile1);
		AutomationAssert.verifyTrue(getValue(FilesTab.txt_LettersNumbersTextboxFile1).equalsIgnoreCase(attribMap.get(AdoddleCommonStringPool.LETTERS_NUMBERS_TEXTBOX)));

		clickElementAndWait(FilesTab.btn_PopUploadFileDistributeCancel);
		waitForCompletePageLoad();
	}

	public void uploadAllFilesAllTogether() {
		clickElementAndWait(FilesTab.btn_AddFiles);
		uploadFileUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, allFileList);
		collectionDataMap.put("File List", allFileList.toString());
	}

	public void verifyAllAttributesForAllFiles() {
		String tempFileName;
		List<WebElement> revListElements = findElements(FilesTab.css_PopUploadFilesRevisions);
		List<WebElement> poiListElements = findElements(FilesTab.css_PopUploadFilesPOIs);
		index = javaUtils.resetIndex(index, 0);
		for (index = 0; index < allFileMap.size(); index++) {
			tempFileName = strUtils.extractFileNameString(allFileList.get(index)).replace(AdoddleCommonStringPool.PDF_EXTENSION, AdoddleCommonStringPool.EMPTY_STRING);
			AutomationAssert.verifyTrue("Expected Rev# " + allFileMap.get(tempFileName).get(AdoddleCommonStringPool.REV) + "\nActual Rev# " + revListElements.get(index).getAttribute("value"), allFileMap.get(tempFileName).get(AdoddleCommonStringPool.REV).equalsIgnoreCase(revListElements.get(index).getAttribute("value")));
			if (!(tempFileName.contains(AdoddleCommonStringPool.FOR_NOTHING) || tempFileName.contains("BAD"))) 	
				AutomationAssert.verifyTrue("Expected POI# " + allFileMap.get(tempFileName).get(AdoddleCommonStringPool.PURPOSE_OF_ISSUE) + "\nActual POI# " + getSelectedDropdownLabel(poiListElements.get(index)), allFileMap.get(tempFileName).get(AdoddleCommonStringPool.PURPOSE_OF_ISSUE).equalsIgnoreCase(getSelectedDropdownLabel(poiListElements.get(index))));
			else 
				AutomationAssert.verifyTrue("Expected POI# " + AdoddleCommonStringPool.PLEASE_SELECT + "\nActual POI# " + getSelectedDropdownLabel(poiListElements.get(index)), AdoddleCommonStringPool.PLEASE_SELECT.equalsIgnoreCase(getSelectedDropdownLabel(poiListElements.get(index))));	
		}

	}
}
