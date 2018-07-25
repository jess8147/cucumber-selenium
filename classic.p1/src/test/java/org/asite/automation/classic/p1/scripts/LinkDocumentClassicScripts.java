package org.asite.automation.classic.p1.scripts;

import java.util.HashMap;
import java.util.List;

import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.ClassicAssociateLocators.AttachDocPage;
import org.asite.automation.CommonLocators.ClassicAssociateLocators.DistributionPage;
import org.asite.automation.CommonLocators.ClassicDocListingLocators.DocListingPage;
import org.asite.automation.CommonLocators.ClassicLandingLocators.LandingPage;
import org.asite.automation.CommonLocators.ClassicMyHomeLocators.MyHomePage;
import org.asite.automation.CommonLocators.ClassicWSLandingLocators.WSLandingPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.ClassicCommonAppMethods;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.resources.ClassicCommonStringPool;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class LinkDocumentClassicScripts extends ClassicCommonAppMethods {
	private String parentHandle1, distributionUser, testFileName,
			myAction;
	private static String destinationFolder = null;
	final private String staticDestinationFolder = "StaticLink_Test_Folder" + epoch;
	final private String dynamicDestinationFolder = "DynamicLink_Test_Folder" + epoch;
	final private HashMap<String, String> revExpectedHMap = new HashMap<String, String>();
	private HashMap<String, String> expectedHMap = new HashMap<String, String>();
	private String currentRevision;

	public void clickTestDataFolder(String folderTitle, String linkType) {
		String folderName;
		createDestinationFolder(linkType);
		setCustomAttributesOnFolder(linkType);
		validateCustomAttributesOnFolder(linkType);
		folderName = folderTitle;
		switchMultiFrames();
		clickFolderWithTitle(folderName);
	}

	private void setCustomAttributesOnFolder(String linkType) {
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteHeaderFrame);
		selectByVisibleText(WSLandingPage.drp_AdminDropdown, ClassicCommonStringPool.MANAGE_CONFIG_ATTRIBS);
		waitForCompletePageLoad();
		switchMultiFrames();
		waitUntilElementIsDisplayed(DocListingPage.tab_CreateCustomAttributesSet);
		clickElementAndWaitForElement(DocListingPage.tab_CreateCustomAttributesSet,
				DocListingPage.lnk_EditCustomAttributesSetFirstEditLink);
		if(linkType.equalsIgnoreCase("Dynamic")) {
			log.info("Dynamic Attribute Set : "+getElementText(By.cssSelector("div[id='projectCustomAttributeList'] form[name='myForm'] tr[class*='trbg']:nth-child(2)")));
			clickElementAndWait(DocListingPage.lnk_EditCustomAttributesSetFirstEditLink);
		}
		else {
			log.info("Static Attribute Set : "+getElementText(By.cssSelector("div[id='projectCustomAttributeList'] form[name='myForm'] tr[class*='trbg']:nth-child(3)")));
			clickElementAndWait(DocListingPage.lnk_EditCustomAttributesSetStaticEditLink);
		}
		
		waitAndSwitchIframe(DocListingPage.frm_CreateCustomAttributeFrame);
		executeJScript("window.scrollBy(0,600)");
		waitForCompletePageLoad();
		waitUtils.getWebDriverWait().until(
				ExpectedConditions.numberOfElementsToBeMoreThan(DocListingPage.css_CreateCustomAttributeSetFolderList,
						6));
		List<WebElement> elements = findElements(DocListingPage.css_CreateCustomAttributeSetFolderList);
		for (WebElement e : elements) {
			if (e.getAttribute("title").trim().equalsIgnoreCase(destinationFolder)) {
				clickElementAndWait(e);
				clickCheckBoxWithValue(e.getAttribute("id"));
				waitForCompletePageLoad();
			}
		}
		clickElementAndWaitForInvisibilityOfElement(DocListingPage.btn_CreateCustomAttributeSetSave,
				GlobalPageElements.ele_PleaseWaitLoadingDataMessage);
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteHeaderFrame);
		clickElementAndWait(WSLandingPage.lnk_WorkspaceHome);
	}

	private void validateCustomAttributesOnFolder(String linkType) {

		boolean flag = false;
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteHeaderFrame);
		waitForCompletePageLoad();
		selectByVisibleText(WSLandingPage.drp_AdminDropdown, ClassicCommonStringPool.MANAGE_CONFIG_ATTRIBS);
		waitForCompletePageLoad();
		switchMultiFrames();
		waitUntilElementIsDisplayed(DocListingPage.tab_CreateCustomAttributesSet);
		clickElementAndWaitForElement(DocListingPage.tab_CreateCustomAttributesSet,
				DocListingPage.lnk_EditCustomAttributesSetFirstEditLink);
		
		if(linkType.equalsIgnoreCase("Dynamic")) {
			log.info("Dynamic Attribute Set : "+getElementText(By.cssSelector("div[id='projectCustomAttributeList'] form[name='myForm'] tr[class*='trbg']:nth-child(2)")));
			clickElementAndWait(DocListingPage.lnk_EditCustomAttributesSetFirstEditLink);
		}
		else {
			log.info("Static Attribute Set : "+getElementText(By.cssSelector("div[id='projectCustomAttributeList'] form[name='myForm'] tr[class*='trbg']:nth-child(3)")));
			clickElementAndWait(DocListingPage.lnk_EditCustomAttributesSetStaticEditLink);
		}
		waitAndSwitchIframe(DocListingPage.frm_CreateCustomAttributeFrame);
		executeJScript("window.scrollBy(0,400)");
		waitForCompletePageLoad();
		waitUtils.getWebDriverWait().until(
				ExpectedConditions.numberOfElementsToBeMoreThan(DocListingPage.css_CreateCustomAttributeSetFolderList,
						6));
		List<WebElement> elements = findElements(DocListingPage.css_CreateCustomAttributeSetFolderList);
		for (WebElement e : elements) {
			if (e.getAttribute("title").trim().equalsIgnoreCase(destinationFolder)) {
				isCustomAppliedOnFolder(e.getAttribute("id"), destinationFolder);
				waitForCompletePageLoad();
				flag = true;
			}

		}

		AutomationAssert.verifyTrue("Custom Attributes failed to apply on folder" + destinationFolder, flag);
		waitUntilElementIsInvisible(GlobalPageElements.ele_PleaseWaitLoadingDataMessage);
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteHeaderFrame);
		clickElementAndWait(WSLandingPage.lnk_WorkspaceHome);

	}

	private void isCustomAppliedOnFolder(String checkBoxValue, String folder) {
		String chkCSS;
		chkCSS = "input[type='checkbox'][value='" + checkBoxValue + "']";
		waitUntilElementIsDisplayed(By.cssSelector(chkCSS));
		AutomationAssert.verifyTrue("Custom Attributes failed to apply on folder " + folder,
				isSelected(By.cssSelector(chkCSS)));
	}

	public void selectFileToLink(String testFileTitle) throws InterruptedException {
		testFileName = testFileTitle;
		searchFilesUsingTitle(testFileTitle);
		expectedHMap = getDocumentCustomAttributes();
		clickElementAndWait(DocListingPage.chk_DocListingFirstDocCheckbox);

	}

	private HashMap<String, String> getDocumentCustomAttributes() {
		String parentHandle2;
		HashMap<String, String> attribMap = new HashMap<String, String>();
		parentHandle1 = getCurrentWindow();
		clickElementAndSwitchWindow(DocListingPage.lnk_DocListingFirstDocDistributionHistory);
		waitForSwitchWindow(2);
		switchWindow();
		handleAlertPopup();
		reloadPage();
		parentHandle2 = getCurrentWindow();
		waitUntilElementIsDisplayed(DocListingPage.lnk_DocDistributionViewCustomAttributesFirst);
		clickElement(DocListingPage.lnk_DocDistributionViewCustomAttributesFirst);
		waitForSwitchWindow(3);
		switchToThirdWindow(parentHandle1, parentHandle2);

		waitUntilListOfElementIsDisplayed(DocListingPage.css_DocAttributesAttribNames);
		List<WebElement> expectedAttributes = findElements(DocListingPage.css_DocAttributesAttribNames);
		List<WebElement> expectedAttributesValues = findElements(DocListingPage.css_DocAttributesAttribValues);

		if (expectedAttributes.size() == expectedAttributesValues.size()) {
			for (int index = 0; index < expectedAttributes.size(); index++) {
				attribMap.put(expectedAttributes.get(index).getText(), expectedAttributesValues.get(index).getText());
				log.info("Attribute Key: " + expectedAttributes.get(index).getText());
				log.info("Attribute Value: " + expectedAttributesValues.get(index).getText());

			}
		} else
			log.error("size of attributes and values are not equal");

		closeCurrentWindow();
		switchPreviousWindow(parentHandle2);
		closeCurrentWindow();
		switchPreviousWindow(parentHandle1);
		switchMultiFrames();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		return attribMap;
	}

	public void selectLinkDocument() {

		for (int index = 0; index < 3; index++) {
			try {
				mouseHoverAndClickElement(DocListingPage.img_DocListingMenuEditDocuments,
						DocListingPage.img_DocListingMenuLinkDocument);
				waitForSwitchWindow(2);
				switchWindow();
				waitUntilElementIsDisplayed(DocListingPage.lbl_LinkDocWindowSelectLocTitle);
				if (isDisplayed(DocListingPage.lbl_LinkDocWindowSelectLocTitle))
					break;
			} catch (Throwable t) {
				log.error("Element for Window Select Location Title not found");
			}
		}

		waitForCompletePageLoad();
	}

	public void selectDestinationFolder() {
		clickFolderWithTitle(destinationFolder);
	}

	public void clickCreateLinkButton() {
		clickElementAndWait(DocListingPage.btn_FormLinkFolderCreateLinkButton);
	}

	public void selectLinkType(String linkType) {
		selectByVisibleText(DocListingPage.drp_LinkSelectionWindowFirstLinkType, linkType);
	}

	public void selectUserFromList(String company, String user) {
		String distributionCompany;
		distributionUser = user;
		distributionCompany = company;
		selectGroupType();
		selectByVisibleText(DistributionPage.sel_DocDistributionselectCompany, distributionCompany);

		waitForCompletePageLoad();

		deselectAllInMultiSelect(DistributionPage.sel_DocDistributionselectIndividuals);
		selectByVisibleText(DistributionPage.sel_DocDistributionselectIndividuals, distributionUser);
	}

	public void verifyUserIsAddedToDistList() {
		waitUntilElementIsDisplayed(DistributionPage.drp_DocDistributionTo);
		AutomationAssert.verifyTrue(getElementText(DistributionPage.drp_DocDistributionTo).contains(distributionUser));
	}

	public void selectActionAndDate(String action) {
		myAction = action;
		waitUntilElementIsDisplayed(DistributionPage.drp_DocDistributionAction);
		selectByVisibleText(DistributionPage.drp_DocDistributionAction, action);
		sendKeys(DistributionPage.txt_DocDistributionActionDate, getCurrentDateTimeWithZone("dd-MMM-yyyy", "IST"));
		waitForCompletePageLoad();

	}

	public void clickContinueButton() {
		clickElementAndWait(DocListingPage.btn_LinkSelectionWindowContinueLeft);
	}

	public void verifyOperationSuccess() {
		log.info("not implemented as success criteria is covered further");
		switchPreviousWindow(parentHandle1);
		waitForCompletePageLoad();
	}

	public void verifyDocumentIsLinked() {
		HashMap<String, String> actualHMap;
		switchMultiFrames();
		waitAndSwitchIframe(LandingPage.frm_AsiteNoResize);
		clickFolderWithTitle(destinationFolder);
		switchMultiFrames();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		waitUntilElementIsDisplayed(DocListingPage.lnk_DocListingFirstDocRef);
		AutomationAssert.verifyTrue(getElementText(DocListingPage.lbl_DocListingFirstFileDocTitle).equalsIgnoreCase(
				testFileName));
		actualHMap = getDocumentCustomAttributes();
		Assert.assertEquals(expectedHMap, actualHMap);
	}

	public void verifyValidActionIsAssigned() throws InterruptedException {
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteHeaderFrame);
		signOut();
		login(System.getProperty("secondary.username"), System.getProperty("secondary.password"));
		if (trainingFlag) {
			clickElementAndWait(MyHomePage.tab_Training);
			switchMultiFrames();
		}
		navigateToWorkSpace(projectTitle);
		clickFolderWithTitle(destinationFolder);
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		AutomationAssert
				.verifyTrue(getElementText(DocListingPage.lnk_DocListingFirstAction).equalsIgnoreCase(myAction));

	}

	private void createDestinationFolder(String lType) {
		switchMultiFrames();
		clickLinkWithText(AdoddleCommonStringPool.ALL_WS_DOCS);
		waitForCompletePageLoad();
		switchMultiFrames();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		waitUntilElementIsDisplayed(DocListingPage.lnk_CreateParentFolder);
		clickElementAndWait(DocListingPage.lnk_CreateParentFolder);
		if(lType.equalsIgnoreCase("static")) {
			sendKeys(DocListingPage.txt_CreateFolderInput, staticDestinationFolder);
			destinationFolder = staticDestinationFolder;
		}
		else {
			sendKeys(DocListingPage.txt_CreateFolderInput, dynamicDestinationFolder);
			destinationFolder = dynamicDestinationFolder;
		}
		collectionDataMap.put("Destination Folder Name :", destinationFolder);
		selectByVisibleText(DocListingPage.drp_CreateFolderRoleSelect, "Workspace - Administrator");
		clickElementAndWait(DocListingPage.btn_CreateFolderRolesSubmit);
		selectByVisibleText(DocListingPage.drp_CreateFolderRolesValueDropdown, "Admin");
		clickElementAndWait(DocListingPage.btn_CreateFolder);
	}

	private void deActivateFolder() throws ElementNotFoundException {
		String parentHandle = getCurrentWindow();
		try {
			switchMultiFrames();
			waitAndSwitchIframe(LandingPage.frm_AsiteNoResize);
			clickFolderWithTitle(destinationFolder);
			switchMultiFrames();
			waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
			mouseHoverAndClickElement(DocListingPage.img_DocListingFolderMenu,
					DocListingPage.img_DocListingFolderMenuEditFolder);
			waitForCompletePageLoad();
			if (!isSelected(DocListingPage.chk_DeactivateFolderSelect))
				clickElementAndWait(DocListingPage.chk_DeactivateFolderSelect);
			clickElementAndWait(DocListingPage.btn_CreateFolderUpdateChanges);
			waitUtils.waitInterval(3);
			if (isDisplayed(By
					.xpath(".//div[@id='divMain']//form[@name='frm']//td[contains(text(),'folder cannot be deactivated')]"))) {
				waitUntilElementIsDisplayed(DocListingPage.img_DeactivateFolderClearActions);
				clickElementAndWait(DocListingPage.img_DeactivateFolderClearActions);
				waitForSwitchWindow(2);
				switchWindow();
				clickElement(DocListingPage.btn_DeactivateFolderClearActionsContinue);
				waitForSwitchWindow(1);
				switchPreviousWindow(parentHandle);
				switchMultiFrames();
				waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
			}
		} catch (Throwable t) {
			log.info("folder can be deactivated");
		}

		clickElementAndWait(DocListingPage.btn_CreateFolderContinue);
	}

	public void uploadRevision(String revisionFileName, String folderText) throws InterruptedException {
		String revisionFilePath;
		switchMultiFrames();
		waitAndSwitchIframe(LandingPage.frm_AsiteNoResize);
		clickFolderWithTitle(folderText);
		switchMultiFrames();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		searchFilesUsingTitle(revisionFileName);
		collectionDataMap.put("Revision File Name :", revisionFileName);
		currentRevision = getElementText(DocListingPage.lnk_DocListingFirstDocRevision);
		waitUntilElementIsDisplayed(DocListingPage.img_DocListingMenuPublishMenu);
		mouseHoverAndClickElement(DocListingPage.img_DocListingMenuPublishMenu,
				DocListingPage.img_DocListingMenuPublishStandardDoc);
		waitForSwitchWindow(2);
		switchWindow();
		revisionFilePath = ResourceHandler.loadProperty("dynamic.link.testdata.filepath");
		reloadPage();
		collectionDataMap.put("Revision filepath:: ", revisionFilePath);
		sendKeys(AttachDocPage.btn_AttachDocFirstBrowse, revisionFilePath);
		waitForCompletePageLoad();

		waitUntilElementIsClickable(DocListingPage.btn_PublishWindowEnterDocDetails);
		clickElementAndWait(DocListingPage.btn_PublishWindowEnterDocDetails);
		enterDocumentDetails(revisionFileName);

		clickElement(DocListingPage.btn_PublishWindowStartUpload);
		handleAlertPopup();
		waitUntilElementIsDisplayed(DocListingPage.btn_PublishWindowUploadSuccessOk);
		clickElement(DocListingPage.btn_PublishWindowUploadSuccessOk);
		switchPreviousWindow(parentHandle1);

	}

	private void enterDocumentDetails(String revisionFileTitle) {
		String x1, x2, y2, x3, y3, z3;
		waitUntilElementIsDisplayed(DocListingPage.rad_PublishWindowOverwrite);
		clickElementAndWait(DocListingPage.rad_PublishWindowOverwrite);

		sendKeys(DocListingPage.txt_PublishWindowHeaderRevision, String.valueOf(Integer.parseInt(currentRevision) + 1));
		sendKeys(DocListingPage.txt_PublishWindowHeaderDocTitle, revisionFileTitle);
		selectByIndex(DocListingPage.drp_PublishWindowHeaderPOI, 2);
		selectByIndex(DocListingPage.drp_PublishWindowHeaderStatus, 2);

		revExpectedHMap.put("IntegerTextbox", expectedHMap.get("IntegerTextbox"));
		sendKeys(DocListingPage.txt_PublishWindowHeaderInteger, revExpectedHMap.get("IntegerTextbox"));

		revExpectedHMap.put("EmailTextbox", getRandomString(5) + "@" + getRandomString(4) + "." + getRandomString(3));
		sendKeys(DocListingPage.txt_PublishWindowHeaderEmail, revExpectedHMap.get("EmailTextbox"));

		revExpectedHMap.put("DecimalTextbox", getRandomNumber(3) + "." + getRandomNumber(2));
		sendKeys(DocListingPage.txt_PublishWindowHeaderDecimal, revExpectedHMap.get("DecimalTextbox"));

		revExpectedHMap.put("LettersNumbersTextbox", expectedHMap.get("LettersNumbersTextbox"));
		sendKeys(DocListingPage.txt_PublishWindowHeaderLettersNumbers, revExpectedHMap.get("LettersNumbersTextbox"));

		revExpectedHMap.put("Letters", getRandomString(4));
		sendKeys(DocListingPage.txt_PublishWindowHeaderLetters, revExpectedHMap.get("Letters"));

		revExpectedHMap.put("Multi selection checkbox", expectedHMap.get("Multi selection checkbox"));
		clickElementAndWait(DocListingPage.chk_PublishWindowHeaderMultiSelect1);

		revExpectedHMap.put("Radio", expectedHMap.get("Radio"));
		clickElementAndWait(DocListingPage.rad_PublishWindowHeaderRadioYes);

		revExpectedHMap.put("DatePicker", getCurrentDateTimeWithZone("dd-MMM-yyyy", "IST"));
		sendKeys(DocListingPage.txt_PublishWindowHeaderDatePicker, revExpectedHMap.get("DatePicker"));

		x1 = getRandomNumber(3);
		revExpectedHMap.put("Coordinates1Dimension", "(X):(" + x1 + ")");
		sendKeys(DocListingPage.txt_PublishWindowHeaderCoordinatesOne, x1);

		x2 = getRandomNumber(3);
		y2 = getRandomNumber(3);
		revExpectedHMap.put("Coordinates2Dimension", "(X,Y):(" + x2 + "," + y2 + ")");
		sendKeys(DocListingPage.txt_PublishWindowHeaderCoordinatesTwoX, x2);
		sendKeys(DocListingPage.txt_PublishWindowHeaderCoordinatesTwoY, y2);

		x3 = getRandomNumber(3);
		y3 = getRandomNumber(3);
		z3 = getRandomNumber(3);
		revExpectedHMap.put("Coordinates3Dimension", "(X,Y,Z):(" + x3 + "," + y3 + "," + z3 + ")");
		sendKeys(DocListingPage.txt_PublishWindowHeaderCoordinatesX, x3);
		sendKeys(DocListingPage.txt_PublishWindowHeaderCoordinatesY, y3);
		sendKeys(DocListingPage.txt_PublishWindowHeaderCoordinatesZ, z3);

		revExpectedHMap.put("States", "Gujarat (1)");
		selectByVisibleText(DocListingPage.drp_PublishWindowHeaderStates, "Gujarat");

		revExpectedHMap.put("Cities", "Ahmedabad (1)");
		selectByVisibleText(DocListingPage.drp_PublishWindowHeaderCities, "Ahmedabad");
		selectByVisibleText(DocListingPage.drp_PublishWindowAttributesDistribute,
				ClassicCommonStringPool.DO_NOT_DISTRIB);

		clickElementAndWait(DocListingPage.btn_PublishWindowHeaderApplyToAll);
		waitForCompletePageLoad();

	}

	public String getRandomString(int count) {
		return javaUtils.getRandomString(count);
	}

	public void verifyRevisionInLinkedFolder(String revisedFileName) {
		HashMap<String, String> revActualHMap;
		switchMultiFrames();
		waitAndSwitchIframe(LandingPage.frm_AsiteNoResize);
		clickFolderWithTitle(destinationFolder);
		switchMultiFrames();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		waitUntilElementIsDisplayed(DocListingPage.lnk_DocListingFirstDocRef);
		AutomationAssert.verifyTrue(getElementText(DocListingPage.lbl_DocListingFirstFileDocTitle).equalsIgnoreCase(
				revisedFileName));
		AutomationAssert.verifyTrue(
				"Expected# " + getElementText(DocListingPage.lnk_DocListingFirstDocRevision) + "\nActual# "
						+ String.valueOf(Integer.parseInt(currentRevision) + 1),
				getElementText(DocListingPage.lnk_DocListingFirstDocRevision).equalsIgnoreCase(
						String.valueOf(Integer.parseInt(currentRevision) + 1)));
		revActualHMap = getDocumentCustomAttributes();
		Assert.assertEquals(revExpectedHMap, revActualHMap);
	}

	public void deactivateTestdataFolder() {
		try {
			deActivateFolder();
		} catch (Throwable e) {

			log.info("Failure while perform clean up operation");
		}
	}

	public void verifyPageTitle(String winTitle) {
		waitUntilElementContainsText(GlobalPageElements.lbl_PageTitle, winTitle);
		log.info("Page title Actual::" + getElementText(GlobalPageElements.lbl_PageTitle));
		log.info("Page title Expected::" + winTitle);
		AutomationAssert.verifyTrue(getElementText(GlobalPageElements.lbl_PageTitle).equalsIgnoreCase(winTitle));
	}
}
