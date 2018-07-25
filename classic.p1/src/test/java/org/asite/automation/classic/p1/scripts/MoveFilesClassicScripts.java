package org.asite.automation.classic.p1.scripts;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.ClassicDocListingLocators.DocListingPage;
import org.asite.automation.CommonLocators.ClassicLandingLocators.LandingPage;
import org.asite.automation.common.lib.ClassicCommonAppMethods;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import cucumber.api.PendingException;

public class MoveFilesClassicScripts extends ClassicCommonAppMethods {

	private String parentHandle;
	private int moveFilesCount, actualFileCount, initialDocCount;
	private String beforeMoveFilesPath, afterMoveFilesPath, verifyMoveFilesTime, moveFilesTime1, moveFilesTime2,
			moveFilesTime3;

	public void getDocumentCountDetails(String folderName) {
		String searchResultsLabel;
		switchMultiFrames();
		switchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		searchResultsLabel = getElementText(DocListingPage.lbl_DocListingSearchResultsCount);
		initialDocCount = Integer.parseInt(searchResultsLabel.substring(searchResultsLabel.lastIndexOf(" ") + 1));
		log.info("Initial Doc Count::" + initialDocCount);
		switchMultiFrames();
		switchIframe(LandingPage.frm_AsiteNoResize);

	}

	public void selectMultipleFilesOnDocListing() {
		beforeMoveFilesPath = getMoveFilesFolderPath();
		log.info("beforeMoveFilesPath :" + beforeMoveFilesPath);

		switchMultiFrames();
		switchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		moveFilesCount = getCount(DocListingPage.css_DocListingDocRefList);
		log.info("Move Files Count: " + moveFilesCount);
		if (moveFilesCount == 0)
			throw new PendingException("Move Files: Testdata not available in folder");
		clickElementAndWait(DocListingPage.chk_DocListingDirecAccessCheckAll);

	}

	public void clickOnDocumentDropdownOptions() {
		parentHandle = getCurrentWindow();

		for (int index = 0; index < 2; index++) {
			try {
				mouseHoverAndClickElement(DocListingPage.img_DocListingMenuEditDocuments,
						DocListingPage.img_DocListingMenuMoveDocuments);
				waitForSwitchWindow(2);
				switchWindow();
				waitUntilElementIsDisplayed(GlobalPageElements.lbl_PageTitle);
				if (isDisplayed(GlobalPageElements.lbl_PageTitle))
					break;
			} catch (Throwable t) {
				log.error("could not find element");
			}
		}

		waitForCompletePageLoad();
	}

	public void verifyMoveFileConfirmation() {
		List<WebElement> movingFileListElements = findElements(DocListingPage.css_DocListingMovingFilesListDocRefs);
		Assert.assertTrue(moveFilesCount == movingFileListElements.size());

	}

	public String formatSecondsInDigits(String time) {
		String minTime = time.split(":")[1];
		String moveFileTime = time.split(":")[0] + ":" + String.format("%02d", Integer.parseInt(minTime));
		log.info("formattedmoveFileTime:: " + moveFileTime);
		return moveFileTime;
	}

	public void verifyDocumentsMovedSuccess(String destinationFolderTitle) {
		String actualResultLabel;
		waitForSwitchWindow(1);
		switchPreviousWindow(parentHandle);

		// moveFilesTime1 = ;
		moveFilesTime1 = formatSecondsInDigits(getCurrentDateTimeWithZone("HH:mm", "WET")) + " WET";
		log.info("moveFilesTime1 :" + moveFilesTime1);

		/*** Seconds Plus ***/
		String secondsPlus = String.format("%02d", Integer.parseInt(moveFilesTime1.split(":")[1].split(" ")[0]) + 1)
				+ " WET";
		log.info("secondsPlus :" + secondsPlus);

		if (secondsPlus.contains("60")) {
			String hourPlus = Integer.toString(Integer.parseInt(moveFilesTime1.split(":")[0]) + 1);
			log.info("hourPlus :" + hourPlus);
			secondsPlus = "00" + " WET";
			moveFilesTime2 = hourPlus + ":" + secondsPlus;
		} else {
			moveFilesTime2 = moveFilesTime1.split(":")[0] + ":" + secondsPlus;
		}

		log.info("moveFilesTime2 :" + moveFilesTime2);

		/*** Seconds Minus ***/
		String secondsMinus = String.format("%02d", Integer.parseInt(moveFilesTime1.split(":")[1].split(" ")[0]) - 1)
				+ " WET";
		log.info("secondsMinus :" + secondsMinus);

		if (secondsMinus.contains("-1")) {
			String hourMinus = Integer.toString(Integer.parseInt(moveFilesTime1.split(":")[0]) - 1);
			log.info("hourMinus :" + hourMinus);
			secondsMinus = "59" + " WET";
			moveFilesTime3 = hourMinus + ":" + secondsMinus;
		} else {
			moveFilesTime3 = moveFilesTime1.split(":")[0] + ":" + secondsMinus;
		}

		log.info("moveFilesTime3 :" + moveFilesTime3);

		// moveFilesTime2 = getBeforeCurrentDateTimeWithZone("WET") + " WET";
		// moveFilesTime3 = getAfterCurrentDateTimeWithZone("WET") + " WET";
		switchMultiFrames();
		switchIframe(LandingPage.frm_AsiteNoResize);
		clickFolderWithTitle(destinationFolderTitle);
		switchMultiFrames();
		switchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		waitUntilElementContainsText(DocListingPage.lbl_DocListingFolderPath, destinationFolderTitle);
		actualResultLabel = getElementText(DocListingPage.lbl_DocListingSearchResultsCount);
		actualFileCount = Integer.parseInt(actualResultLabel.substring(actualResultLabel.lastIndexOf(" ") + 1));
		Assert.assertTrue(actualFileCount + "Equals " + initialDocCount + moveFilesCount,
				actualFileCount == initialDocCount + moveFilesCount);

		afterMoveFilesPath = getMoveFilesFolderPath();
		log.info("afterMoveFilesPath :" + afterMoveFilesPath);
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

	public String getMoveFilesFolderPath() {
		switchMultiFrames();
		waitAndSwitchIframe(LandingPage.frm_AsiteWorkspaceFrame);
		waitUntilElementIsDisplayed(DocListingPage.lbl_ParentDocListFolderPath);
		return getElementText(DocListingPage.lbl_ParentDocListFolderPath);
	}

	public void gotoAuditHistoryAndClickOnAccessLink(String accessLink) {
		parentHandle = getCurrentWindow();
		clickElementAndSwitchWindow(DocListingPage.lnk_DocListingFirstDocDistributionHistory);
		handleAlertPopup();
		clickLinkWithText(accessLink);
	}

	public void verifyMoveFilesPathIntoHistory() {
		waitUntilElementIsDisplayed(DocListingPage.lbl_AccessHistoryLatestRemarks);
		String fileHistroyText = getElementText(DocListingPage.lbl_AccessHistoryLatestRemarks);

		String verifyBeforePath = fileHistroyText.split(" ")[3];
		log.info("verifyBeforePath :" + verifyBeforePath);
		String verifyAfterPath = fileHistroyText.split(" ")[5];
		log.info("verifyAfterPath :" + verifyAfterPath);

		Assert.assertEquals(beforeMoveFilesPath, verifyBeforePath);
		Assert.assertEquals(afterMoveFilesPath, verifyAfterPath);
	}

	public void afterFilesMoveDateAndTime() {
		verifyMoveFilesTime = getMoveFilesTime();

		try {
			Assert.assertEquals(moveFilesTime1, verifyMoveFilesTime);
		} catch (Throwable t) {
			try {
				Assert.assertEquals(moveFilesTime2, verifyMoveFilesTime);
			} catch (Throwable tx) {
				Assert.assertEquals(moveFilesTime3, verifyMoveFilesTime);
			}
		}
		Assert.assertEquals(getCurrentDateTimeWithZone("dd-MMM-yyyy", "WET"),
				getElementText(DocListingPage.lbl_AccessHistoryLatestAccessDateTime));
		closeCurrentWindow();
		switchPreviousWindow(superWindow);
	}

	public String getMoveFilesTime() {
		String moveFilesTime = findElement(DocListingPage.lbl_AccessHistoryLatestAccessDateTime).getAttribute("title");
		log.info("moveFilesTime :" + moveFilesTime);
		return moveFilesTime;
	}

	public String getBeforeCurrentDateTimeWithZone(String timeZone) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone(timeZone));
		cal.add(Calendar.MINUTE, 1);
		return cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
	}

	public String getAfterCurrentDateTimeWithZone(String timeZone) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone(timeZone));
		cal.add(Calendar.MINUTE, -1);
		return cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
	}
}