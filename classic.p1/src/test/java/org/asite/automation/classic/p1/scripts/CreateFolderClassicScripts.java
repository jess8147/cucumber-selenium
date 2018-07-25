package org.asite.automation.classic.p1.scripts;

import org.asite.automation.CommonLocators.ClassicDocListingLocators.DocListingPage;
import org.asite.automation.CommonLocators.ClassicLandingLocators.LandingPage;
import org.asite.automation.common.lib.ClassicCommonAppMethods;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class CreateFolderClassicScripts extends ClassicCommonAppMethods {
	private String folderName, folderNameEdited;

	public void clickOnWorkspaceDocuments(String wsDocs) {
		switchDefault();
		switchToMultipleFrames();
		clickLinkWithText(wsDocs);
		waitForCompletePageLoad();
	}

	public void verifyDocumentListing(String folderPath) {
		switchDefault();
		switchToMultipleFrames();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		waitUntilElementIsDisplayed(DocListingPage.lbl_ParentDocListFolderPath);
		Assert.assertTrue(getElementText(DocListingPage.lbl_ParentDocListFolderPath).contains(folderPath));
	}

	public void clickOnCreateNewParentFolder() {
		clickElementAndWait(DocListingPage.lnk_CreateParentFolder);
	}

	public void verifyFolderPage(String pageTitle) {
		Assert.assertEquals(getElementText(DocListingPage.lbl_CreateFolderPageTitle).trim(), pageTitle.trim());
	}

	public void enterFolderNameAndCreateFolder() {
		folderName = "TestFolder";
		folderNameEdited = "TestFolderEdited";
		sendKeys(DocListingPage.txt_CreateFolderInput, folderName.trim() + epoch);
		clickElementAndWait(DocListingPage.btn_CreateFolder);
		collectionDataMap.put("Folder Name", folderName.trim() + epoch);
	}

	public void verifyCreatedFolderIntoFolderTree() {
		switchToMultipleFrames();
		waitAndSwitchIframe(LandingPage.frm_AsiteNoResize);
		clickFolderWithTitle(folderName + epoch);
		Assert.assertTrue(isDisplayedFolderWithTitle(folderName + epoch));

	}

	public void verifyUpdatedFolderIntoFolderTree() {
		switchToMultipleFrames();
		waitAndSwitchIframe(LandingPage.frm_AsiteNoResize);
		clickFolderWithTitle(folderNameEdited + epoch);
		Assert.assertTrue(isDisplayedFolderWithTitle(folderNameEdited + epoch));
	}

	public void clickOnCreatedFolderAndEditFolder() {
		clickOnEditFolder(folderName + epoch);
	}

	public void clickOnUpdatedFolderAndEditFolder() {
		clickOnEditFolder(folderNameEdited + epoch);
	}

	public void clickOnEditFolder(String folderTitle) {
		while (!isDisplayed(DocListingPage.txt_CreateEditFolderInput)) {

			clickFolderWithTitle(folderTitle);
			switchToMultipleFrames();
			waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
			waitUntilElementIsDisplayed(DocListingPage.img_DocListingFolderMenu);
			waitForCompletePageLoad();
			mouseHoverAndClickElement(DocListingPage.img_DocListingFolderMenu, DocListingPage.img_DocListingEditFolder);

		}

	}

	public void enterEditFolderNameAndUpdateChanges() {
		sendKeys(DocListingPage.txt_CreateEditFolderInput, folderNameEdited + epoch);
		clickOnUpdateChanges();
	}

	public void clickOnUpdateChanges() {
		clickElementAndWait(DocListingPage.btn_CreateFolderUpdateChanges);
	}

	public void selectDeactivateCheckboxAndUpdateChanges() {
		if (!isSelected(DocListingPage.chk_DeactivateFolderSelect)) {
			clickElementAndWait(DocListingPage.chk_DeactivateFolderSelect);
		}
		clickOnUpdateChanges();
	}

	public void verifyDeactivateFolderPage(String folderTitle) {
		waitUntilElementIsDisplayed(DocListingPage.lbl_DeactivateFolderTitle);
		Assert.assertTrue(getElementText(DocListingPage.lbl_DeactivateFolderTitle).equalsIgnoreCase(folderTitle));
	}

	public void clickOnContinue() {
		clickElementAndWait(DocListingPage.btn_CreateFolderContinue);
	}

	public void verifyFolderDeactivated() {
		switchToMultipleFrames();
		waitAndSwitchIframe(LandingPage.frm_AsiteNoResize);
		Assert.assertTrue(!isDisplayedFolderWithTitle(folderNameEdited.trim() + epoch));

	}

	public void switchToMultipleFrames() {
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteMainFrame);
	}

	public void mouseHoverAndMoveElement(int offSet1, int offSet2, By locator1, By locator2) {
		Actions action = new Actions(getWebDriver());
		action.moveToElement(findElement(locator1)).perform();
		action.moveByOffset(offSet1, offSet2).perform();
		mouseHover(locator2);
	}

	/**
	 * Checks if is displayed folder with title.
	 * 
	 * @param folderTitle
	 *            the folder title
	 * @return true, if is displayed folder with title
	 */
	public boolean isDisplayedFolderWithTitle(String folderTitle) {
		String linkCSS = "a[title=\"" + folderTitle + "\"]";
		return isDisplayed(By.cssSelector(linkCSS));

	}
}