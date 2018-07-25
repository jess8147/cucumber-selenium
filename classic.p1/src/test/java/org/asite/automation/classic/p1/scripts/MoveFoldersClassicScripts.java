package org.asite.automation.classic.p1.scripts;

import java.util.ArrayList;
import java.util.List;

import org.asite.automation.CommonLocators.ClassicDocListingLocators.DocListingPage;
import org.asite.automation.CommonLocators.ClassicLandingLocators.LandingPage;
import org.asite.automation.common.lib.ClassicCommonAppMethods;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MoveFoldersClassicScripts extends ClassicCommonAppMethods {

	private List<String> expectedFolderList = new ArrayList<String>();
	private List<String> actualFolderList = new ArrayList<String>();
	private String parentHandle;
	private String beforeMoveFolderPath, afterMoveFolderPath, MoveFolderTime, MoveFolderTime2, MoveFolderTime3,
			verifyMoveFolderTime;

	public void validateTestFoldersInListing(String folder1, String folder2) throws InterruptedException {

		if (isDisplayedLinkWithTitle(folder1)) {
			log.info("Test Folder:: " + folder1 + "available in listing");
			if (isDisplayedLinkWithTitle(folder2))
				log.info("Test Folder:: " + folder2 + "available in listing");
			else
				moveFolderInFolderListing(folder2);

		}

		else {
			log.info("Test Folder:: " + folder2 + "available in listing");
			if (isDisplayedLinkWithTitle(folder1))
				log.info("Test Folder:: " + folder1 + "available in listing");
			else
				moveFolderInFolderListing(folder1);
		}

	}

	public void clickOnMoveFolderOption(String folderTitle) {
		beforeMoveFolderPath = getMoveFoldersPath();
		log.info("beforeMoveFolderPath :" + beforeMoveFolderPath);

		parentHandle = getCurrentWindow();
		expectedFolderList.add(folderTitle);
		getSubFolderList(folderTitle);
		switchMultiFrames();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		waitUntilElementIsDisplayed(DocListingPage.img_DocListingFolderMenu);
		waitForCompletePageLoad();

		for (int index = 0; index < 5; index++) {
			try {
				mouseHoverAndClickElement(DocListingPage.img_DocListingFolderMenu,
						DocListingPage.img_DocListingFolderMenuMoveFolders);
				waitForSwitchWindow(2);
				if (getWindowHandles().size() > 1)
					break;

			} catch (Throwable t) {
				log.error("could not find element");
			}
		}
		switchWindow();
		handleAlertPopup();
		waitForCompletePageLoad();
	}

	public void clickContinueButton(String buttonText) {
		clickElementAndWaitForInvisibilityOfElement(DocListingPage.btn_FormMoveFoldersContinue,
				DocListingPage.btn_FormMoveFoldersContinue);
	}

	public void clickMoveFilesContinueButton(String btnText) {
		clickElementAndWaitForInvisibilityOfElement(DocListingPage.btn_FormMoveFilesContinue,
				DocListingPage.btn_FormMoveFilesContinue);
	}

	public void clickFolderHavingTitle(String folderTitle) {
		clickFolderWithTitle(folderTitle);
	}

	public void verifyMoveFolderConfirmation() {
		waitUntilListOfElementIsDisplayed(DocListingPage.css_DocListingMoverFoldersFolderList);
		List<WebElement> actualFolderListElements = findElements(DocListingPage.css_DocListingMoverFoldersFolderList);
		for (WebElement folder : actualFolderListElements) {
			actualFolderList.add(folder.getText().substring(folder.getText().lastIndexOf("\\") + 1));
		}
		log.info("expectedFolderList :" + expectedFolderList);
		log.info("actualFolderList :" + actualFolderList);

		Assert.assertTrue(expectedFolderList.equals(actualFolderList));
	}

	public void getSubFolderList(String folderName) {
		switchMultiFrames();
		switchIframe(LandingPage.frm_AsiteNoResize);
		clickFolderWithTitle(folderName);
		List<WebElement> subFolderElementList = findElements(DocListingPage.css_DocListingSubFolderList);
		for (WebElement element : subFolderElementList) {
			expectedFolderList.add(element.getAttribute("title"));
		}
	}

	public void verifyFoldersMovedChildSuccess(String sourceFolder, String destinationFolder) {

		switchPreviousWindow(parentHandle);
		// moveFolderTime1 = ;
		MoveFolderTime = formatSecondsInDigits(getCurrentDateTimeWithZone("HH:mm", "WET")) + " WET";
		log.info("moveFolderTime :" + MoveFolderTime);

		/*** Seconds Plus ***/
		String secondsPlus = String.format("%02d", Integer.parseInt(MoveFolderTime.split(":")[1].split(" ")[0]) + 1)
				+ " WET";
		log.info("secondsPlus :" + secondsPlus);

		if (secondsPlus.contains("60")) {
			String hourPlus = Integer.toString(Integer.parseInt(MoveFolderTime.split(":")[0]) + 1);
			log.info("hourPlus :" + hourPlus);
			secondsPlus = "00" + " WET";
			MoveFolderTime2 = hourPlus + ":" + secondsPlus;
		} else {
			MoveFolderTime2 = MoveFolderTime.split(":")[0] + ":" + secondsPlus;
		}

		log.info("moveFolderTime2 :" + MoveFolderTime2);

		/*** Seconds Minus ***/
		String secondsMinus = String.format("%02d", Integer.parseInt(MoveFolderTime.split(":")[1].split(" ")[0]) - 1)
				+ " WET";
		log.info("secondsMinus :" + secondsMinus);

		if (secondsMinus.contains("-1")) {
			String hourMinus = Integer.toString(Integer.parseInt(MoveFolderTime.split(":")[0]) - 1);
			log.info("hourMinus :" + hourMinus);
			secondsMinus = "59" + " WET";
			MoveFolderTime3 = hourMinus + ":" + secondsMinus;
		} else {
			MoveFolderTime3 = MoveFolderTime.split(":")[0] + ":" + secondsMinus;
		}

		log.info("moveFolderTime3 :" + MoveFolderTime3);

		/*
		 * MoveFolderTime = getCurrentDateTimeWithZone("HH:mm", "WET") + " WET";
		 * log.info("MoveFolderTime :" + MoveFolderTime);
		 */

		switchMultiFrames();
		switchIframe(LandingPage.frm_AsiteNoResize);
		clickElementAndWait(By.xpath(".//td[@class='openfolder']//a[@title='" + sourceFolder + "']"));

		for (int index = 0; index < expectedFolderList.size(); index++) {
			clickFolderWithTitle(expectedFolderList.get(index));
			Assert.assertTrue(isDisplayedFolderWithTitle(expectedFolderList.get(index)));
		}
		gotoParentFolder(sourceFolder);
		afterMoveFolderPath = getMoveFoldersPath();
		log.info("afterMoveFolderPath :" + afterMoveFolderPath);
	}

	public void verifyFoldersMovedParentSuccess(String sourceFolder, String destinationFolder) {
		switchPreviousWindow(parentHandle);

		// moveFolderTime1 = ;
		MoveFolderTime = formatSecondsInDigits(getCurrentDateTimeWithZone("HH:mm", "WET")) + " WET";
		log.info("moveFolderTime :" + MoveFolderTime);

		/*** Seconds Plus ***/
		String secondsPlus = String.format("%02d", Integer.parseInt(MoveFolderTime.split(":")[1].split(" ")[0]) + 1)
				+ " WET";
		log.info("secondsPlus :" + secondsPlus);

		if (secondsPlus.contains("60")) {
			String hourPlus = Integer.toString(Integer.parseInt(MoveFolderTime.split(":")[0]) + 1);
			log.info("hourPlus :" + hourPlus);
			secondsPlus = "00" + " WET";
			MoveFolderTime2 = hourPlus + ":" + secondsPlus;
		} else {
			MoveFolderTime2 = MoveFolderTime.split(":")[0] + ":" + secondsPlus;
		}

		log.info("moveFolderTime2 :" + MoveFolderTime2);

		/*** Seconds Minus ***/
		String secondsMinus = String.format("%02d", Integer.parseInt(MoveFolderTime.split(":")[1].split(" ")[0]) - 1)
				+ " WET";
		log.info("secondsMinus :" + secondsMinus);

		if (secondsMinus.contains("-1")) {
			String hourMinus = Integer.toString(Integer.parseInt(MoveFolderTime.split(":")[0]) - 1);
			log.info("hourMinus :" + hourMinus);
			secondsMinus = "59" + " WET";
			MoveFolderTime3 = hourMinus + ":" + secondsMinus;
		} else {
			MoveFolderTime3 = MoveFolderTime.split(":")[0] + ":" + secondsMinus;
		}

		log.info("moveFolderTime3 :" + MoveFolderTime3);

		/*
		 * MoveFolderTime = getCurrentDateTimeWithZone("HH:mm", "WET") + " WET";
		 * log.info("MoveFolderTime :" + MoveFolderTime);
		 */

		switchMultiFrames();
		switchIframe(LandingPage.frm_AsiteNoResize);
		clickElementAndWait(By.xpath(".//td[@class='openfolder']//a[@title='" + sourceFolder + "']"));
		Assert.assertTrue(isDisplayedFolderWithTitle(sourceFolder));

		for (int index = 1; index < expectedFolderList.size(); index++) {
			Assert.assertTrue(isDisplayedFolderWithTitle(expectedFolderList.get(index)));
		}

		for (int index = 0; index < expectedFolderList.size(); index++) {
			clickFolderWithTitle(expectedFolderList.get(index));
			waitForCompletePageLoad();
			Assert.assertTrue(isDisplayedFolderWithTitle(expectedFolderList.get(index)));
		}
		clickElementAndWait(By.xpath(".//a[@title='" + sourceFolder + "'][@style]"));

		gotoParentFolder(sourceFolder);
		afterMoveFolderPath = getMoveFoldersPath();
		log.info("afterMoveFolderPath :" + afterMoveFolderPath);
	}

	public void clickFolderWithTitleOnLHTree(String folderString) {
		switchMultiFrames();
		switchIframe(LandingPage.frm_AsiteNoResize);
		clickFolderWithTitle(folderString);

	}

	public void switchMultiFrames() {
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteMainFrame);
	}

	public void signOutFromApp() {
		switchDefault();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitAndSwitchIframe(LandingPage.frm_AsiteHeaderFrame);
		signOut();
	}

	public String getMoveFoldersPath() {
		switchMultiFrames();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		waitUntilElementIsDisplayed(DocListingPage.lbl_ParentDocListParentFolderPath);
		waitForCompletePageLoad();
		return getElementText(DocListingPage.lbl_ParentDocListParentFolderPath);
	}

	public void gotoAuditHistoryAndClickOnAccessLink() {
		waitForCompletePageLoad();
		parentHandle = getCurrentWindow();
		clickElementAndWait(DocListingPage.lnk_DocListingFirstDocDistributionHistory);
		waitForSwitchWindow(2);
		switchWindow();
		handleAlertPopup();
		reloadPage();
		waitUntilElementIsDisplayed(DocListingPage.lnk_DocumentDistributionAccessLink);
		clickElementAndWait(DocListingPage.lnk_DocumentDistributionAccessLink);
	}

	public void verifyMoveFolderPathIntoHistory() {
		waitUntilElementIsDisplayed(DocListingPage.lbl_AccessHistoryLatestRemarks);
		String folderHistroyText = getElementText(DocListingPage.lbl_AccessHistoryLatestRemarks);

		String verifyBeforePath = folderHistroyText.split(" ")[3];
		log.info("verifyBeforePath :" + verifyBeforePath);
		String verifyAfterPath = folderHistroyText.split(" ")[5];
		log.info("verifyAfterPath :" + verifyAfterPath);

		Assert.assertEquals(beforeMoveFolderPath, verifyBeforePath);
		Assert.assertEquals(afterMoveFolderPath, verifyAfterPath);
	}

	public String formatSecondsInDigits(String time) {
		String minTime = time.split(":")[1];
		String moveFolderTime = time.split(":")[0] + ":" + String.format("%02d", Integer.parseInt(minTime));
		log.info("formattedmoveFolderTime:: " + moveFolderTime);
		return moveFolderTime;
	}

	public void afterFolderMoveDateAndTime() {

		verifyMoveFolderTime = getMoveFolderTime();
		try {
			Assert.assertEquals(MoveFolderTime, verifyMoveFolderTime);
		} catch (Throwable t) {
			try {
				Assert.assertEquals(MoveFolderTime2, verifyMoveFolderTime);
			} catch (Throwable tx) {
				Assert.assertEquals(MoveFolderTime3, verifyMoveFolderTime);
			}
		}
		Assert.assertEquals(getCurrentDateTimeWithZone("dd-MMM-yyyy", "WET"),
				getElementText(DocListingPage.lbl_AccessHistoryLatestAccessDateTime));
		closeCurrentWindow();
		switchPreviousWindow(superWindow);

	}

	public String getMoveFolderTime() {
		String moveFolderTime = findElement(DocListingPage.lbl_AccessHistoryLatestAccessDateTime).getAttribute("title");
		log.info("auditTrailmoveFolderTime: " + moveFolderTime);
		return moveFolderTime;
	}

	public void gotoParentFolder(String sourceFolder) {
		List<String> folderList = new ArrayList<String>();
		folderList.add(sourceFolder);
		folderList.add(sourceFolder);
		folderList.add(sourceFolder);
		waitForCompletePageLoad();
		for (int index = 0; index < folderList.size(); index++) {
			switchMultiFrames();
			waitAndSwitchIframe(LandingPage.frm_AsiteNoResize);
			clickFolderWithTitle(folderList.get(index));
			switchMultiFrames();
			waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
			if (isDisplayed(DocListingPage.lbl_ParentDocListParentFolderPath)) {
				break;
			}
		}
	}

	public void verifyDocListingPageTitle(String folderTitle) {
		for (int index = 0; index < 3; index++) {
			switchMultiFrames();
			waitAndSwitchIframe(LandingPage.frm_AsiteNoResize);
			clickFolderWithTitle(folderTitle);
			switchMultiFrames();
			waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
			if (isDisplayed(DocListingPage.lbl_ParentDocListFolderPath)) {
				if (getElementText(DocListingPage.lbl_ParentDocListFolderPath).contains(folderTitle))
					break;
			}
		}
	}

	public void clickOkConfirmationButton() {
		waitUntilElementIsDisplayed(DocListingPage.btn_FormConfirmationOKButton);
		clickElement(DocListingPage.btn_FormConfirmationOKButton);
	}

	public void moveFolderInFolderListing(String folder) {

		if (folder.contains("AutoMoveTestFolder2"))
			clickFolderWithTitle("AutoMoveTestFolder1");

		else
			clickFolderWithTitle("AutoMoveTestFolder2");

		verifyDocListingPageTitle(folder);
		clickFolderWithTitleOnLHTree(folder);
		clickOnMoveFolderOption(folder);
		verifyPageTitle("Move Folders");
		clickFolderWithTitle("All Workspace Documents");
		waitForCompletePageLoad();
		clickElementAndWait(DocListingPage.btn_FormMoveFoldersContinue);
		clickElementAndWait(DocListingPage.btn_FormMoveFilesContinue);
		waitUntilElementIsDisplayed(DocListingPage.btn_FormConfirmationOKButton);
		clickElement(DocListingPage.btn_FormConfirmationOKButton);
		switchPreviousWindow(parentHandle);
		switchMultiFrames();
		switchIframe(LandingPage.frm_AsiteNoResize);
		clickElementAndWait(By.xpath(".//td[@class='openfolder']//a[@title='" + folder + "']"));
		Assert.assertTrue(isDisplayedFolderWithTitle(folder));
	}

	public void clickElementAndWaitForInvisibilityOfElement(By clickLocator, By invisibilityLocator) {
		try {
			clickElementAndWait(clickLocator);
		} catch (Throwable e) {
			findElement(clickLocator).click();
			waitForCompletePageLoad();
		}
		waitUntilElementIsInvisible(invisibilityLocator);
		waitUtils.waitForCompletePageLoad();
	}

	public boolean isDisplayedLinkWithTitle(String elementText) throws InterruptedException {
		String elementXPath = "//a[@title='" + elementText + "']";
		waitUtils.waitInterval(5);
		try {
			return isDisplayed(By.xpath(elementXPath));
		} catch (Throwable t) {
			return false;
		}

	}

	public void clickFolderWithTitle(String folderText) {
		String linkCSS = "a[title=\"" + folderText + "\"]";
		WebDriverWait wait = new WebDriverWait(getWebDriver(), 3);
		try {
			clickElementAndWait(By.cssSelector(linkCSS));
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			alert.accept();
			log.error("Accepted the alert successfully.");
		} catch (Throwable e) {
			log.info("Alert not found." + e.getMessage());
			findElement(By.cssSelector(linkCSS)).click();
			waitForCompletePageLoad();
		}
	}

}