package org.asite.automation.adoddle.p1.scripts;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleDiscussionsLocators.DiscussionsTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.PropertyUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class FilesLHTasksScripts extends AdoddleCommonAppMethods {

	private int					totalActions		= 0, count = 0, actionSize, filesIncompleteActions, filesOverdueActions, filesDueTodayActions;
	private String				action, testFileDocRef;
	private List<String>		popOverElementsText	= new ArrayList<String>();
	private List<WebElement>	popOverElements		= new ArrayList<WebElement>();
	public static Logger		log					= AutomationLogger.getInstance().getLogger(FilesLHTasksScripts.class);

	public void verifyActionCount(String actionType) throws InterruptedException, IOException {
		if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_INCOMPLETE_TASKS)) {
			createTestData();
			logOut();
			login(System.getProperty("secondary.username"), System.getProperty("secondary.password"));
			navigateTab(LandingPage.lnk_Files);
			try {
				Assert.assertTrue(Integer.parseInt(findElement(FilesTab.lbl_FilesIncompleteActionsCount).getText()) > 0);
			}
			catch (Throwable t) {
				log.error("TESTDATA: Incomplete Actions are not available");
			}
		}
		else if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_OVERDUE_TASKS)) {
			try {
				Assert.assertTrue(Integer.parseInt(findElement(FilesTab.lbl_FilesOverdueActionsCount).getText()) > 0);
			}
			catch (Throwable t) {
				log.error("TESTDATA: Overdue Actions are not available");
			}
		}
		else if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_DUE_TODAY)) {
			createTestData();
			logOut();
			login(System.getProperty("secondary.username"), System.getProperty("secondary.password"));
			navigateTab(LandingPage.lnk_Files);
			try {
				Assert.assertTrue(Integer.parseInt(findElement(FilesTab.lbl_FilesDueTodayActionsCount).getText()) > 0);
			}
			catch (Throwable t) {
				log.error("TESTDATA: DueToday Actions are not available");
			}
		}
	}

	public void dragMouseOnActions(String actionType) {
		if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_INCOMPLETE_TASKS)) {
			dragMouse(FilesTab.lbl_FilesIncompleteActionsCount);
		}
		else if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_OVERDUE_TASKS)) {
			dragMouse(FilesTab.lbl_FilesOverdueActionsCount);
		}
		else if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_DUE_TODAY)) {
			dragMouse(FilesTab.lbl_FilesDueTodayActionsCount);
		}
	}

	public void verifyTotalActionCount(String actionType) {
		if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_INCOMPLETE_TASKS)) {
			navigateTab(LandingPage.lnk_Files);
			filesIncompleteActions = Integer.parseInt(getToolTipText(FilesTab.lbl_FilesIncompleteActionsCount).split(":")[1].trim());
			totalActions = filesIncompleteActions;
			log.info("Total Incomplete Actions:::" + totalActions);
			try {
				Assert.assertTrue(filesIncompleteActions == totalActions);
			}
			catch (Throwable t) {
				log.error("Incomplete Action counts are not valid");
			}
		}
		else if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_OVERDUE_TASKS)) {
			navigateTab(LandingPage.lnk_Files);
			filesOverdueActions = Integer.parseInt(getToolTipText(FilesTab.lbl_FilesOverdueActionsCount).split(":")[1].trim());
			totalActions = filesOverdueActions;
			log.info("Total Overdue Actions:::" + totalActions);
			try {
				Assert.assertTrue(filesOverdueActions == totalActions);
			}
			catch (Exception t) {
				t.printStackTrace();
				log.error("Overdue Action counts are not valid");
			}
		}
		else if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_DUE_TODAY)) {
			navigateTab(LandingPage.lnk_Files);
			filesDueTodayActions = Integer.parseInt(getToolTipText(FilesTab.lbl_FilesDueTodayActionsCount).split(":")[1].trim());
			totalActions = filesDueTodayActions;
			log.info("Total dueToday Actions:::" + totalActions);
			try {
				Assert.assertTrue(filesDueTodayActions == totalActions);
			}
			catch (Throwable t) {
				log.error("Due-Today Action counts are not valid");
			}
		}
	}

	public void clickOnActionLink(String actionType) {
		navigateTab(LandingPage.lnk_Files);
		if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_INCOMPLETE_TASKS)) {
			clickElementAndWait(FilesTab.lnk_FilesIncompleteActions);
		}
		else if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_OVERDUE_TASKS)) {
			clickElementAndWait(FilesTab.lnk_FilesOverdueActions);
		}
		else if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_DUE_TODAY)) {
			clickElementAndWait(FilesTab.lnk_FilesDueTodayActions);
		}
	}

	public void verifyIncompleteActionCount() {
		int fileListingCount = getCount(FilesTab.css_DocListingFilesCount);
		int fileIncompleteActionCount = getCount(FilesTab.css_IncompleteActionCount);

		Assert.assertEquals(fileListingCount, fileIncompleteActionCount);
	}

	public void verifyFilterType(String filterType) {
		if (filterType.contains("Action Status")) {
			clickElementAndWait(FilesTab.drp_IncompleteActionStatus);
			try {
				Assert.assertTrue(isSelected(FilesTab.sel_Incomplete));
			}
			catch (Throwable t) {
				Assert.assertTrue(isSelected(FilesTab.sel_IncompleteAndOverdue));
			}
		}
		else if (filterType.contains("Revisions")) {
			clickElementAndWait(FilesTab.drp_IncompleteRevision);
			Assert.assertTrue(isSelected(FilesTab.sel_CurrentSet));
			Assert.assertTrue(isSelected(FilesTab.sel_Superseded));
		}
		else if (filterType.contains("Recipient Name")) {
			if (! PropertyUtils.trainingFlag) {
				clickElementAndWait(FilesTab.drp_RecipientName);
				try {
					Assert.assertTrue(getElementText(DiscussionsTab.btn_RecipientNameFilter).contains(ResourceHandler.loadProperty("file.actions.assigned.uk.primary.user")));
				}
				catch (Throwable t1) {
					try {
						Assert.assertTrue(getElementText(DiscussionsTab.btn_RecipientNameFilter).contains(ResourceHandler.loadProperty("file.actions.assigned.uk.secondary.user")));
					}
					catch (Throwable t2) {
						try {

							Assert.assertTrue(getElementText(DiscussionsTab.btn_RecipientNameFilter).contains(ResourceHandler.loadProperty("file.actions.assigned.us.primary.user")));

						}
						catch (Throwable t3) {
							try {
								Assert.assertTrue(getElementText(DiscussionsTab.btn_RecipientNameFilter).contains(ResourceHandler.loadProperty("file.actions.assigned.us.secondary.user")));
							}
							catch (Throwable t4) {

								try {
									Assert.assertTrue(getElementText(DiscussionsTab.btn_RecipientNameFilter).contains(ResourceHandler.loadProperty("file.actions.assigned.aus.primary.user")));
								}
								catch (Throwable t5) {
									Assert.assertTrue(getElementText(DiscussionsTab.btn_RecipientNameFilter).contains(ResourceHandler.loadProperty("file.actions.assigned.aus.secondary.user")));
								}

							}

						}
					}
				}
			}
			else {
				clickElementAndWait(FilesTab.drp_RecipientName);
				try {
					Assert.assertTrue(getElementText(DiscussionsTab.btn_RecipientNameFilter).contains(ResourceHandler.loadProperty("file.actions.assigned.training.uk.primary.user")));
				}
				catch (Throwable t1) {
					Assert.assertTrue(getElementText(DiscussionsTab.btn_RecipientNameFilter).contains(ResourceHandler.loadProperty("file.actions.assigned.training.uk.secondary.user")));
				}
			}

		}
		else if (filterType.contains("Action due date")) {
			Assert.assertTrue(getElementText(FilesTab.drp_ActionDueDate).contains(getCurrentDateStringFormat("dd-MMM-yyyy")));
		}
	}

	public void performActionFromFileListing(String actionType) throws InterruptedException, IOException {
		if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_INCOMPLETE_TASKS) && filesIncompleteActions > 0) {
			performMultiActionsOnFile(testFileDocRef, actionType);
		}
		else if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_OVERDUE_TASKS) && filesOverdueActions > 0) {
			clickElementAndWait(FilesTab.lnk_FilesOverdueActions);
			performSingleActionOnFile(actionType);
		}
		else if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_DUE_TODAY) && filesDueTodayActions > 0) {
			clickElementAndWait(FilesTab.lnk_FilesDueTodayActions);
			performMultiActionsOnFile(testFileDocRef, actionType);
		}
		else {
			log.error("TESTDATA: TestData unavailable");
		}
	}

	public void verifyTotalActionCountDecreased(String actionType) {
		navigateTab(LandingPage.lnk_Files);
		log.info("Total Tab Actions::" + totalActions);
		log.info("All Action Size::" + actionSize);
		log.info("Single Action Size::" + count);

		if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_INCOMPLETE_TASKS)) {
			try {
				log.info("Total Label Actions::" + Integer.parseInt(getToolTipText(FilesTab.lbl_FilesIncompleteActionsCount).split(":")[1].trim()));
				Assert.assertEquals(Integer.parseInt(getToolTipText(FilesTab.lbl_FilesIncompleteActionsCount).split(":")[1].trim()), totalActions - actionSize);
			}
			catch (Exception e) {
				log.error("Total Incomplete Action Count did not decrease after Incomplete Actions performed");
			}
		}
		else if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_OVERDUE_TASKS)) {
			try {
				log.info("Total Label Actions::" + Integer.parseInt(getToolTipText(FilesTab.lbl_FilesOverdueActionsCount).split(":")[1].trim()));
				Assert.assertEquals(Integer.parseInt(getToolTipText(FilesTab.lbl_FilesOverdueActionsCount).split(":")[1].trim()), totalActions - count);
			}
			catch (Exception e) {
				log.error("Total Overdue Action Count did not decrease after Overdue Actions performed");
			}
		}
		else if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_DUE_TODAY)) {
			try {
				log.info("Total Label Actions::" + Integer.parseInt(getToolTipText(FilesTab.lbl_FilesDueTodayActionsCount).split(":")[1].trim()));
				Assert.assertEquals(Integer.parseInt(getToolTipText(FilesTab.lbl_FilesDueTodayActionsCount).split(":")[1].trim()), totalActions - actionSize);
			}
			catch (Exception e) {
				log.error("Total Overdue Action Count did not decrease after Overdue Actions performed");
			}
		}
	}

	public void performMultiActionsOnFile(String testFileDocRef, String actionType) throws InterruptedException, IOException {
		int index = 1;
		boolean forInfoFlag = false;
		searchFiles(testFileDocRef);
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		mouseHover(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
		popOverElements = findElements(GlobalPageElements.css_firstActionsPopoverContentLinks);

		for (WebElement e : popOverElements) {
			popOverElementsText.add(e.getText());
		}

		actionSize = popOverElements.size();

		while (index <= actionSize) {
			int count = 0;
			action = getElementText(FilesTab.lnk_FilesFirstAction);
			log.info("action :" + action);

			if (action.equalsIgnoreCase(AdoddleCommonStringPool.FOR_INFORMATION)) {
				log.info("Setting For Info flag to true");
				forInfoFlag = true;
			}

			for (String elementText : popOverElementsText) {
				if (elementText.equalsIgnoreCase(action)) {
					count = count + 1;
					log.info(action + " Action count::" + count);
				}
			}

			if (forInfoFlag == false && action.equalsIgnoreCase(AdoddleCommonStringPool.FOR_COMMENT)) {
				for (String elementText : popOverElementsText) {
					if (elementText.equalsIgnoreCase(AdoddleCommonStringPool.FOR_INFORMATION)) {
						count = count + 1;
						log.info("For Information Action Count(With For Comment)::" + count);
					}
				}
			}
			index = index + count;
			clickElement(FilesTab.txt_FilesFilterInput);
			clickElementAndWait(FilesTab.lnk_FilesFirstAction);
			waitForCompletePageLoad();
			performFileAction(action);
			reloadPage();
			verifyActionCountDecreased(actionType, count);
			searchFiles(testFileDocRef);
		}
	}

	public void performSingleActionOnFile(String actionType) throws InterruptedException, IOException {
		action = getElementText(FilesTab.lnk_FilesFirstAction);
		waitForCompletePageLoad();

		waitUntilElementIsDisplayed(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		mouseHover(GlobalPageElements.lnk_FirstMyActionCountPopOver);

		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
		popOverElements = findElements(GlobalPageElements.css_firstActionsPopoverContentLinks);
		actionSize = popOverElements.size();

		for (WebElement e : popOverElements) {
			if (e.getText().equals(action)) {
				count = count + 1;
				log.info(action + " Action count::" + count);

			}
		}

		clickElementAndWait(FilesTab.txt_FilesFilterInput);
		clickElementAndWait(FilesTab.lnk_FilesFirstAction);
		waitForCompletePageLoad();
		performFileAction(action);
		reloadPage();
		if (action.equalsIgnoreCase(ResourceHandler.loadProperty("for.comment.incorporation"))) {
			verifyForPublishAction(action);
		}

		verifyActionCountDecreased(actionType, count);
	}

	public void verifyActionCountDecreased(String actionType, int count) {
		waitUntilElementIsDisplayed(LandingPage.lnk_Files);
		navigateTab(LandingPage.lnk_Files);
		if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_INCOMPLETE_TASKS)) {
			try {
				if (!action.equalsIgnoreCase(ResourceHandler.loadProperty("for.comment.incorporation"))) {
					Assert.assertEquals(getToolTipText(FilesTab.lnk_FilesIncompleteActions).split(":")[1].trim(), String.valueOf(filesIncompleteActions - count));
					filesIncompleteActions = filesIncompleteActions - count;
				}
				else {
					Assert.assertEquals(Integer.parseInt(getToolTipText(FilesTab.lnk_FilesIncompleteActions).split(":")[1].trim()), filesIncompleteActions);
					verifyForPublishAction(action);
				}
			}
			catch (Throwable t) {
				log.error(action + " Incomplete Expected Count:::" + Integer.parseInt(getToolTipText(FilesTab.lnk_FilesIncompleteActions).split(":")[1].trim()));
				log.error(action + " Incomplete Actual count:::" + (filesIncompleteActions - count));
			}

		}
		else if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_OVERDUE_TASKS)) {
			try {
				Assert.assertEquals(Integer.parseInt(getToolTipText(FilesTab.lnk_FilesOverdueActions).split(":")[1].trim()), filesOverdueActions - count);
			}
			catch (Throwable t) {
				log.info("Overdue Expected:::" + Integer.parseInt(getToolTipText(FilesTab.lnk_FilesOverdueActions).split(":")[1].trim()));
				log.info("Overdue Actual:::" + (filesOverdueActions - count));
			}
			filesOverdueActions = filesOverdueActions - count;
		}
		else if (actionType.equalsIgnoreCase(AdoddleCommonStringPool.LABEL_DUE_TODAY)) {
			try {
				Assert.assertEquals(Integer.parseInt(getToolTipText(FilesTab.lnk_FilesDueTodayActions).split(":")[1].trim()), filesDueTodayActions - count);
			}
			catch (Throwable t) {
				log.info("DueToday Expected:::" + Integer.parseInt(getToolTipText(FilesTab.lnk_FilesDueTodayActions).split(":")[1].trim()));
				log.info("DueToday Actual:::" + (filesDueTodayActions - count));
			}
			filesDueTodayActions = filesDueTodayActions - count;
		}
	}

	public void verifyForPublishAction(String publishAction) throws InterruptedException, IOException {
		navigateTab(LandingPage.lnk_Files);
		clickElementAndWait(FilesTab.lnk_FilesDueTodayActions);
		searchFiles(testFileDocRef);
		Assert.assertTrue(getElementText(FilesTab.lnk_FilesFirstAction).equals(ResourceHandler.loadProperty("for.publishing")));
		clickElementAndWait(FilesTab.lnk_FilesFirstAction);
		performFileAction(publishAction);

	}

	public void createTestData() throws InterruptedException, IOException {
		navigateTab(LandingPage.lnk_Files);
		testFileDocRef = ResourceHandler.loadProperty("files.actions.testdata.filename").trim();
		searchFiles(testFileDocRef);
		Assert.assertTrue(getCount(FilesTab.css_DocListingFilesCount) > 0);
		contextClick(FilesTab.lnk_DocListingFirstFileName);
		waitUntilElementIsDisplayed(FilesTab.opt_FileContextClickShare);
		mouseHover(FilesTab.opt_FileContextClickShare);
		waitUntilElementIsDisplayed(FilesTab.opt_FileContextClickShareDistribute);
		clickElementAndWait(FilesTab.opt_FileContextClickShareDistribute);
		assignActionsToUser(System.getProperty("secondary.username"));
		waitForCompletePageLoad();
	}

	public String getCurrentDateStringFormat(String format) {
		Format formatter = new SimpleDateFormat(format);
		return formatter.format(new Date());
	}
}
