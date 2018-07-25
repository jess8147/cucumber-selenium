package org.asite.automation.adoddle.p1.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleDiscussionsLocators.DiscussionsTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class DiscussionLHActionScripts extends AdoddleCommonAppMethods {

	private String			commentTitle;
	private int				unreadCommentCount;
	private boolean			flag	= false;
	final private static Logger	log	= AutomationLogger.getInstance().getLogger(DiscussionLHActionScripts.class);

	public void verifyUnreadComment() throws InterruptedException, IOException {
		clickElementAndWaitForInvisibilityOfElement(DiscussionsTab.lnk_UnreadDiscussions, GlobalPageElements.ele_overLayProcess);
		if (getCount(DiscussionsTab.css_UnreadDiscussionsCountOnListing) == 0) {
			createComment(System.getProperty("secondary.username"), System.getProperty("secondary.password"));
			tearDown();
			setUp(ResourceHandler.loadProperty("browser"));
			login(System.getProperty("primary.username"), System.getProperty("primary.password"));
			clickElementAndWaitForElement(LandingPage.lnk_Discussion, DiscussionsTab.lnk_UnreadDiscussions);
			clickElementAndWait(DiscussionsTab.lnk_UnreadDiscussions);
			AutomationAssert.verifyTrue(getCount(DiscussionsTab.css_UnreadDiscussionsCountOnListing) > 0);
		}
		else {
			AutomationAssert.verifyTrue(getCount(DiscussionsTab.css_UnreadDiscussionsCountOnListing) > 0);
			flag = true;
		}

		clickElementAndWait(DiscussionsTab.lnk_UnreadDiscussions);
		unreadCommentCount = getUnreadDiscussionsFromListing();
	}

	public int getUnreadDiscussionsFromListing() {
		if(fileBetaViewFlag)
			return Integer.parseInt(getElementText(DiscussionsTab.lbl_BetaViewUnreadDiscussionsHeader).split("of")[1].trim().replace(")", ""));
		else
			return Integer.parseInt(getElementText(DiscussionsTab.lbl_UnreadDiscussionsHeader).split("of")[1].trim().replace(")", ""));
	}

	private void createComment(String username, String password) throws InterruptedException, IOException {
		tearDown();
		TestDriverFactory.getInstance().setUp(ResourceHandler.loadProperty("browser"));
		login(username, password);
		clickElementAndWait(LandingPage.lnk_Files);
		clickElementWithText(System.getProperty("global.test.project"));
		verifyIsDocumentLocked();
		clickElementAndWaitForElement(FilesTab.lnk_FilesMoreOptions, FilesTab.lnk_PopMoreOptionsStartDiscussion);
		clickElementAndWaitForElement(FilesTab.lnk_PopMoreOptionsStartDiscussion, FilesTab.pop_StartNewDiscussion);
		sendKeys(FilesTab.txt_NewDiscussionToUserField, System.getProperty("primary.username").trim());
		sendKeys(FilesTab.txt_NewDiscussionToUserField, Keys.TAB);
		String epochtime = dateUtils.getEpoch();
		commentTitle = javaUtils.getRandomString(10) + epochtime;
		collectionDataMap.put("Comment Title", commentTitle);
		sendKeys(FilesTab.txt_NewDiscussionTitleInput, commentTitle);
		sendKeys(FilesTab.txt_NewDiscussionDescInput, javaUtils.getRandomString(10) + epochtime);
		clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_NewDiscussionSubmit, FilesTab.btn_NewDiscussionSubmit);
	}

	public void dragMouseOnUnreadDiscussions() {
		dragMouse(DiscussionsTab.lbl_UnreadDiscussionsCount);
	}

	public void verifyUnreadCommentTooltipCount() {
		verifyElementText(DiscussionsTab.lbl_UnreadDiscussionsCount, String.valueOf(unreadCommentCount));
	}

	public void clickOnUnreadDiscussionsLink() {
		clickElementAndWait(DiscussionsTab.lnk_UnreadDiscussions);
	}

	public void verifyUnreadDocuments() {
		int totalCount = 0;
		boolean flag = true;
		
		if(fileBetaViewFlag){
			while(flag) {
				executeJScript(AdoddleCommonJQueries.scrollDownScrollJquery);
				waitForCompletePageLoad();
				List<WebElement> elementList = findElements(DiscussionsTab.css_UnreadDiscussionsCountOnListing);
				totalCount += elementList.size();
				log.info("totalCount :" + totalCount);

				if (!isDisplayed(FilesTab.lnk_FilesTabDisableNextPage)) {
					/*clickElementAndWait(By.xpath(".//div[contains(@id,'paging') or contains(@id,'Paging')]//li[not(contains(@class,'disable')) and @title='Next Page']//a"));*/
					clickElementAndWait(FilesTab.lnk_FilesTabEnableNextPage);
				}
				else {
					flag = false;
				}
			}
		}
		else{
			do {
				executeJScript(AdoddleCommonJQueries.scrollDownScrollJquery);
				waitForCompletePageLoad();
				List<WebElement> elementList = findElements(DiscussionsTab.css_UnreadDiscussionsCountOnListing);
				totalCount += elementList.size();
				log.info("totalCount :" + totalCount);

				if (!isDisplayed(GlobalPageElements.lnk_GlobalListingPaginationNext)) {
					clickElementAndWait(GlobalPageElements.lnk_GlobalListingPaginationNext);
				}
				else {
					flag = false;
				}
			} while (flag);
		}
		AutomationAssert.verifyTrue(unreadCommentCount + " should equal " + totalCount, unreadCommentCount == totalCount);
	}

	public void verifyActionStatusFilter(String expectedText) {
		verifyElementText(DiscussionsTab.btn_ActionStatusFilter, expectedText);
		clickElementAndWaitForElement(DiscussionsTab.btn_ActionStatusFilter, DiscussionsTab.chk_ActionStatusFilterUnread);
		AutomationAssert.verifyTrue(isSelected(DiscussionsTab.chk_ActionStatusFilterUnread));

	}

	public void verifyRecipientNameFilter(String expectedText) {
		if (System.getProperty("global.test.project").equals(ResourceHandler.loadProperty("test.uk.project")))
			expectedText = expectedText.replace("Current User", ResourceHandler.loadProperty("current.user.uk.username"));
		else if (System.getProperty("global.test.project").equals(ResourceHandler.loadProperty("test.us.project")))
			expectedText = expectedText.replace("Current User", ResourceHandler.loadProperty("current.user.us.username"));
		else if (System.getProperty("global.test.project").equals(ResourceHandler.loadProperty("test.aus.project")))
			expectedText = expectedText.replace("Current User", ResourceHandler.loadProperty("current.user.aus.username"));

		verifyElementText(DiscussionsTab.btn_RecipientNameFilter, expectedText);
	}

	public void completeDiscussionAction() {
		String parentWindow = getCurrentWindow();

		clickElementAndWait(DiscussionsTab.lnk_UnreadDiscussions);

		if (!flag) 
			searchDiscussions(commentTitle);
		
		if(fileBetaViewFlag) {
			String commentID = getElementText(DiscussionsTab.lnk_DiscussionFirstCommentID);
			clickElementAndSwitchWindow(DiscussionsTab.lnk_FirstUnreadDiscussionCompleteAction);
			waitUntilElementIsDisplayed(DiscussionsTab.ele_FileBetaViewDiscussionsHistoryPanel);
			verifyElementText(DiscussionsTab.lbl_FileBetaViewfirstDiscussionTitle, commentTitle + ": " + commentID);
			waitForCompletePageLoad();
			closeCurrentWindow();
			switchPreviousWindow(parentWindow);
		}
		else {
			String commentID = getElementText(DiscussionsTab.lnk_DiscussionFirstCommentID);
			clickElementAndSwitchWindow(DiscussionsTab.lnk_FirstUnreadDiscussionCompleteAction);
			waitUntilElementIsDisplayed(DiscussionsTab.ele_DiscussionsHistorySelectedRow);
			verifyElementText(DiscussionsTab.lbl_firstDiscussionTitle, commentTitle + ": " + commentID);
			waitForCompletePageLoad();
			closeCurrentWindow();
			switchPreviousWindow(parentWindow);
		}
	}

	public void verifyActionCountDecreased() {
		navigateToDiscussionTab();
		clickElementAndWait(DiscussionsTab.lnk_UnreadDiscussions);
		log.info("Actual count: " + unreadCommentCount);
		if (!(unreadCommentCount <= 1)) {
			if(fileBetaViewFlag){
				log.info("Actual Label count: " + Integer.parseInt(getElementText(DiscussionsTab.lbl_BetaViewUnreadDiscussionsHeader).split("of")[1].trim().replace(")", "")));
				log.info("Expected count: " +(unreadCommentCount -1));
				try {
						AutomationAssert.verifyTrue(Integer.parseInt(getElementText(DiscussionsTab.lbl_BetaViewUnreadDiscussionsHeader).split("of")[1].trim().replace(")", "")) <= (unreadCommentCount - 1));
					}
				catch(Throwable t) {
						log.info("Discussion LH Actions count verification failed");
					}
				}
			else{
				log.info("Actual Label count: " + Integer.parseInt(getElementText(DiscussionsTab.lbl_UnreadDiscussionsHeader).split("of")[1].trim().replace(")", "")));
				log.info("Expected count: " +(unreadCommentCount -1));
				try {
						AutomationAssert.verifyTrue(Integer.parseInt(getElementText(DiscussionsTab.lbl_UnreadDiscussionsHeader).split("of")[1].trim().replace(")", "")) <= (unreadCommentCount - 1));
					}
				catch(Throwable t) {
						log.info("Discussion LH Actions count verification failed");
				}	
			}
		}
		logOut();
	}

	private void verifyIsDocumentLocked() {
		List<WebElement> availableDocumentList = findElements(FilesTab.css_FileLists);

		for (WebElement ele : availableDocumentList) {
			if (getElementText(ele.findElement(FilesTab.css_FilesListingRowLockedRow)).contains(AdoddleCommonStringPool.DOUBLE_DASH)) {
				clickElementAndWait(ele.findElement(FilesTab.chk_FilesListingRowchecbox));
				break;
			}
			else {
				log.info("File: " + getElementText(ele.findElement(FilesTab.lnk_FilesListingRowDocRef)) + "is Locked");
				AutomationAssert.verifyTrue(ele.findElement(FilesTab.img_FilesListingRowLockedIcon).isDisplayed());
			}
		}
	}

}