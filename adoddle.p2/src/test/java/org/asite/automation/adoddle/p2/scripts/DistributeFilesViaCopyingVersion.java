/*Testdata dynamically created and deactivated */
package org.asite.automation.adoddle.p2.scripts;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.DateUtils;
import org.asite.automation.common.utils.JavaUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class DistributeFilesViaCopyingVersion extends AdoddleCommonAppMethods {
	private String userName, actionName, parentHandle, dateFormat;
	final private List<String> multipleActionList = new ArrayList<String>();
	final private List<String> multiUserList = new ArrayList<String>();
	final private List<String> multiFileList = new ArrayList<String>();
	private List<String> globalUploadFileList = new ArrayList<String>();
	final private String epoch1 = JavaUtils.getRandomNumber(10);
	final private String epoch2 = JavaUtils.getRandomNumber(10);
	final private String AutoTestFileDocRef1 = "AutoTestDistributeFile1_" + epoch1;
	final private String AutoTestFileDocRef2 = "AutoTestDistributeFile2_" + epoch1;
	final private String AutoTestFileDocRef3 = "AutoTestDistributeFile3_" + epoch1;
	final private String AutoTestFileDocRef4 = "AutoTestDistributeFile4_" + epoch2;
	final private String AutoTestFileDocRef5 = "AutoTestDistributeFile5_" + epoch2;
	final private static Logger log = AutomationLogger.getInstance().getLogger(DistributeFilesViaCopyingVersion.class);

	public void selectMultipleFileFromLocal() {
		dateFormat = getDateFormat();
		List<String> fileDocRefs = sysUtils.getFileList(AutoTestFileDocRef1 + "," + AutoTestFileDocRef2 + "," + AutoTestFileDocRef3
				+ "," + AutoTestFileDocRef4 + "," + AutoTestFileDocRef5);
		uploadDocuments(null, 5, fileDocRefs, null, false, 1, null, null);
		log.info("MultifileList  " + multiFileList);
		collectionDataMap.put("MultifileList:: ", globalUploadFileList.toString());
	}

	public void enterMandatoryFields() {

		log.info("Covered in <uploadMultipleDocuments> definition");

	}

	public void validateFileInDocumentListing() {
		searchFiles(multiFileList.get(0));
		log.info("File Name::" + multiFileList.get(0));
		AutomationAssert.verifyTrue("Expected FileList Count:: 5", multiFileList.size() == 5);
		AutomationAssert.verifyTrue(multiFileList.contains(getElementText(FilesTab.lnk_DocListingFirstFileName)));
		AutomationAssert.verifyTrue(getElementText(FilesTab.lnk_FileListingFirstVersion).contains("1"));
	}

	public void clickIconPreviousRevision(String popElementText) {
		if (popElementText.equalsIgnoreCase("Upload"))
			clickElementAndWait(FilesTab.lnk_PopUploadCopyDistribtionIcon);
		else
			clickElementAndWait(FilesTab.lnk_PopDistributeCopyDistribtionIcon);
	}

	public void validateUpdateFileRevisions() {
		log.info("Covered in <validateRevisionsInListing> definitions");

	}

	public void selectAllRevisionsAndContextClick() {
		if (!isSelected(FilesTab.chk_MultiFilesSelectionCheckbox))
			clickElement(FilesTab.chk_MultiFilesSelectionCheckbox);
		contextClick(FilesTab.lnk_DocListingFirstDocRef);
	}

	public void clickContextClickMenuOptions(String optionText) {
		if (isDisplayedElementWithText(optionText))
			mouseHoverAndClickElement(FilesTab.opt_FileContextClickShare, FilesTab.opt_FileContextClickShareDistribute);
	}

	public void verifiedSuccessMessage() {
		try {
			AutomationAssert.verifyTrue(getElementText(GlobalPageElements.lbl_SuccessMessage).equalsIgnoreCase(
					"File(s) Distributed successfully"));
		} catch (Throwable t) {
			log.error("Success Message could not be verified sucessfully");
		}
	}

	public void validateFileActionsInAuditTrail() {
		searchFiles(epoch1);
		parentHandle = getCurrentWindow();
		contextClick(FilesTab.lnk_DocListingFirstFileName);
		clickContextMenuOptionWithText(AdoddleCommonStringPool.OPTION_HISTORY);
		clickContextMenuOptionWithText(AdoddleCommonStringPool.OPTION_DISTRIBUTION);
		waitForSwitchWindow(2);
		switchWindow();
		if (fileBetaViewFlag)
			log.info("Covered in <validateCopiedUserAndAction> defination");
		else {
			clickElementAndWaitForElement(FilesTab.lnk_FileViewLHHistoryBlock, FilesTab.drp_FileViewHistoryTypeDropdown);
			selectByVisibleText(FilesTab.drp_FileViewHistoryTypeDropdown, "Distribution");
			selectByIndex(FilesTab.drp_FileviewHistoryRevDropdown, 1);
			clickElementAndWait(FilesTab.ele_FileHistoryFirstRecord);
		}
	}

	public void validateCopiedUserAndAction() {
		List<WebElement> expectedUserList = findElements(FilesTab.css_ManageRolesAssignedRoleUsersList);
		AutomationAssert.verifyTrue("Expected User list size should not be zero", expectedUserList.size() != 0);
		for (WebElement web : expectedUserList) {
			userName = web.getAttribute("title");
			actionName = web.getText();
			if (userName.contains(ResourceHandler.loadProperty("test.user.rfi.builder.name")))
				AutomationAssert.verifyTrue(actionName + " should contain Dist", actionName.contains("Dist"));
			else if (userName.contains(ResourceHandler.loadProperty("test.user.pa.builder.name")))
				AutomationAssert.verifyTrue(actionName + " should contain Action", actionName.contains("Action"));
			else if (userName.contains(ResourceHandler.loadProperty("multi.project.user.name")))
				AutomationAssert.verifyTrue(actionName + " should contain Info", actionName.contains("Info"));
			else if (userName.contains(ResourceHandler.loadProperty("test.user.tc.bloggs.name")))
				AutomationAssert.verifyTrue(actionName + " should contain Info", actionName.contains("Info"));
			else if (userName.contains(ResourceHandler.loadProperty("test.user.rfi.bloggs.name")))
				AutomationAssert.verifyTrue(actionName + " should contain Comment", actionName.contains("Comment"));
			else if (userName.contains(ResourceHandler.loadProperty("test.user.pa.bloggs.name")))
				AutomationAssert.verifyTrue(actionName + " should contain Status", actionName.contains("Status"));
			else if (userName.contains("Automation Primary"))
				AutomationAssert.verifyTrue(actionName.contains("Ack"));
			else if (userName.contains(ResourceHandler.loadProperty("test.user.auto.rfi.name")))
				AutomationAssert.verifyTrue(actionName + " should contain Status", actionName.contains("Status"));
			else
				log.info("Not expected UserName");
		}
		expectedUserList.clear();
	}

	public void validateUserDueDateAndActions() throws ParseException {
		List<WebElement> auditTrialAttributeList;
		Boolean flag = true;
		String actionDueDate;
		String expectedActionDueDate;
		if (fileBetaViewFlag) {
			reloadPage();
			waitForCompletePageLoad();
			waitUntilListOfElementIsDisplayed(FilesTab.css_BetaViewFileViewerActionDistributionRecords);
			auditTrialAttributeList = findElements(FilesTab.css_BetaViewFileViewerActionDistributionRecords);
			expectedActionDueDate = DateUtils.addDaysToDate(dateFormat, timezoneID, 2);
			for (WebElement web : auditTrialAttributeList) {
				String userName = web.findElement(FilesTab.css_BetaFileViewerRecordsUserName).getText();
				String action = web.findElement(FilesTab.css_BetaFileViewerRecordsAction).getText();
				actionDueDate = web.findElement(FilesTab.css_BetaFileViewerRecordsActionDueDate).getText();
				log.info("UserName :: " + userName);

				if (userName.contains(ResourceHandler.loadProperty("test.user.tc.bloggs.name"))
						|| userName.contains(ResourceHandler.loadProperty("multi.project.user.name")))
					AutomationAssert.verifyTrue("Expected Action:: " + AdoddleCommonStringPool.ACTION_FOR_INFORMATION,
							action.contains(AdoddleCommonStringPool.ACTION_FOR_INFORMATION));
				else if (userName.contains(ResourceHandler.loadProperty("test.user.rfi.bloggs.name")))
					AutomationAssert.verifyTrue("Expected Action:: " + AdoddleCommonStringPool.ACTION_FOR_COMMENT,
							action.contains(AdoddleCommonStringPool.ACTION_FOR_COMMENT));
				else if (userName.contains(ResourceHandler.loadProperty("test.user.pa.bloggs.name"))
						|| userName.contains(ResourceHandler.loadProperty("test.user.auto.rfi.name")))
					AutomationAssert.verifyTrue(
							"Expected Action:: " + AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE,
							action.contains(AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE));
				else if (userName.contains(ResourceHandler.loadProperty("test.user.automation.primary.name")))
					AutomationAssert.verifyTrue("Expected Action:: "
							+ AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT,
							action.contains(AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT));
				else if (userName.contains(ResourceHandler.loadProperty("test.user.auto.pa.builder.name")))
					AutomationAssert.verifyTrue("Expected Action:: " + AdoddleCommonStringPool.ACTION_FOR_ACTION,
							action.contains(AdoddleCommonStringPool.ACTION_FOR_ACTION));
				else if (userName.contains(ResourceHandler.loadProperty("test.user.rfi.builder.name")))
					AutomationAssert.verifyTrue("Expected Action:: " + AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION,
							action.contains(AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION));
				else
					flag = false;
				AutomationAssert.verifyTrue("Expected actionDueDate:: " + expectedActionDueDate + "But found:: "
						+ actionDueDate, actionDueDate.contains(expectedActionDueDate));
			}

			AutomationAssert.verifyTrue("Failure while validation userAction and dueDate", flag);
		}

		else {
			auditTrialAttributeList = findElements(FilesTab.css_FileviewHistoryDistributionAttributes);
			for (WebElement ele : auditTrialAttributeList) {
				userName = ele.findElement(FilesTab.css_FileViewerRecordsUsername).getAttribute("title");
				actionName = ele.findElement(FilesTab.css_FileViewerRecordsAction).getText();
				actionDueDate = ele.findElement(By.cssSelector("span")).getText();
				if (userName.contains(ResourceHandler.loadProperty("test.user.tc.bloggs.name"))
						|| userName.contains(ResourceHandler.loadProperty("test.user.rfi.bloggs.name"))
						|| userName.contains(ResourceHandler.loadProperty("test.user.pa.bloggs.name"))
						|| userName.contains(ResourceHandler.loadProperty("test.user.automation.primary.name"))
						|| userName.contains(ResourceHandler.loadProperty("multi.project.user.name"))) {
					expectedActionDueDate = DateUtils.addDaysToDate(dateFormat, timezoneID, 2);
					AutomationAssert.verifyTrue("Expected Date Vs Actual Date:: " + expectedActionDueDate + ":::"
							+ actionDueDate, actionDueDate.contains(expectedActionDueDate));
				}
			}

		}
		closeCurrentWindow();
		switchPreviousWindow(parentHandle);
	}

	public void searchFileInListing() {
		log.info("Covered in <selectMultipleFileInListing> definition");
	}

	public void selectMultipleFileInListing() {
		searchFiles(epoch1);
		clickElement(FilesTab.chk_MultiFilesSelectionCheckbox);
		contextClick(FilesTab.lnk_DocListingFirstFileName);
	}

	public void selectfileAndContextClick() {
		searchFiles(epoch2);
		clickElement(FilesTab.chk_MultiFilesSelectionCheckbox);
		contextClick(FilesTab.lnk_DocListingFirstFileName);
	}

	public void distributeUsersAndAction(String user1, String user2, String user3) {
		createMultiUserAndActionList(user1, user2, user3);
		AutomationAssert.verifyTrue("User And Action List:: " + multiUserList + multipleActionList,
				multiUserList.size() != 0);
		assignMultipleActionsToUsers(multiUserList, multipleActionList);
	}

	public void uploadFileRevisions() {
		log.info("GlobalFile List size:: " + globalUploadFileList.size());
		uploadDocuments(globalUploadFileList, 5, null, null, false, 2, null, null);
	}

	public void enterMandatoryAttributes() {
		log.info("Covered in <uploadMultipleRevisions> definition");
	}

	public void validateFileRevisionsInListing() {
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();
		log.info("File Name::" + multiFileList.get(0));
		searchFiles(multiFileList.get(0));
		AutomationAssert.verifyTrue(multiFileList.contains(getElementText(FilesTab.lnk_DocListingFirstFileName)));
		AutomationAssert.verifyTrue(getElementText(FilesTab.lnk_FileListingFirstVersion).contains("2"));
	}

	public void searchFileWithTitle(String fileName) {
		searchFiles(fileName);
	}

	private void assignMultipleActionsToUsers(List<String> users, List<String> actions) {

		boolean flag = false;
		List<WebElement> dropdownToggles;

		if (users.get(0).contains(ResourceHandler.loadProperty("test.user.tc.bloggs.name"))) {

			for (String str : users) {
				log.info("User List:: " + users.size());
				sendKeys(FilesTab.txt_PopUploadActionForDistributeTo, str);
				sendKeys(FilesTab.txt_PopUploadActionForDistributeTo, Keys.TAB);
			}
			index = javaUtils.resetIndex(index, 0);
			dropdownToggles = findElements(FilesTab.css_PopDistributeTogglesDropdown);
			log.info("Toogle Size:: " + dropdownToggles.size());
			for (WebElement e : dropdownToggles) {
				flag = true;
				clickElementAndWait(e);
				selectByVisibleText(FilesTab.drp_PopDistributeToggleActionDropDown, actions.get(index));
				selectDateFromCalendar();
				index++;
			}
			dropdownToggles.clear();

		} else {

			for (String user : users) {
				log.info("User List:: " + users.size());
				sendKeys(FilesTab.txt_PopFilesActionForDistributeTo, user);
				sendKeys(FilesTab.txt_PopFilesActionForDistributeTo, Keys.TAB);
			}

			index = javaUtils.resetIndex(index, 0);
			dropdownToggles = findElements(FilesTab.css_PopDistributeToggleDropdowns);
			log.info("Toogle Size:: " + dropdownToggles.size());
			for (WebElement e : dropdownToggles) {
				flag = true;
				clickElementAndWait(e);
				selectByVisibleText(FilesTab.drp_PopDistributeToggleActionDropDown, actions.get(index));
				selectDateFromCalendar();
				index++;
			}
		}
		AutomationAssert.verifyTrue(flag);
	}

	private void uploadDocuments(List<String> fileList, int count, List<String> docRefStrings,
			List<String> docTitleStrings, boolean privateFlag, int revision, List<String> poi, List<String> status) {

		boolean docRefFlag = false, docTitleFlag = false;

		if (docRefStrings != null)
			docRefFlag = docRefStrings.size() != 0;
		if (docTitleStrings != null)
			docTitleFlag = docTitleStrings.size() != 0;

		List<WebElement> revisionList, poiList, docRefList, statusList, privateCheckboxes;
		List<WebElement> docTitleList = new ArrayList<WebElement>();

		sysUtils.authenticateRemoteMachine(nodeIP);

		if (!(fileList == null)) {
			globalUploadFileList = fileList;
		} else {
			for (int index = 0; index < count; index++)
				globalUploadFileList.add(sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath()
						+ dateUtils.getEpoch() + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP));
		}

		uploadMultipleFilesUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, globalUploadFileList);

		if (!docTitleFlag)
			clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
		else
			docTitleList = findElements(FilesTab.css_PopUploadFilesDocTitlesInput);

		revisionList = findElements(FilesTab.css_PopUploadFilesRevisionsInput);
		poiList = findElements(FilesTab.css_PopUploadFilesPurposeOfIssuesDropDowns);
		statusList = findElements(FilesTab.css_PopUploadFilesStatusDropDowns);
		docRefList = findElements(FilesTab.css_PopUploadFilesDocRefsInput);

		index = javaUtils.resetIndex(index, 0);

		for (WebElement e : revisionList) {

			e.sendKeys(String.valueOf(revision));

			if (poi == null)
				selectByIndex(poiList.get(index), 1);
			else if (poi.size() == 1)
				selectByVisibleText(poiList.get(index), poi.get(0));
			else if (poi.size() > 1)
				selectByVisibleText(poiList.get(index), poi.get(index));

			if (status == null)
				selectByIndex(statusList.get(index), 1);
			else if (status.size() == 1)
				selectByVisibleText(statusList.get(index), status.get(0));
			else if (status.size() > 1)
				selectByVisibleText(statusList.get(index), status.get(index));

			if (docRefFlag) {
				docRefList.get(index).clear();
				docRefList.get(index).sendKeys(docRefStrings.get(index));
			}

			if (docTitleFlag) {
				docTitleList.get(index).clear();
				docTitleList.get(index).sendKeys(docTitleStrings.get(index));
			}

			index++;
		}

		if (privateFlag) {
			privateCheckboxes = findElements(FilesTab.css_PopUploadFilesPrivateCheckboxes);
			for (WebElement e : privateCheckboxes)
				e.click();
		}
		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);

		log.info("Global File List:: " + globalUploadFileList);
		for (String ele : globalUploadFileList)
			multiFileList.add(strUtils.extractFileNameString(ele));

	}

	private String getDateFormat() {

		if (isDisplayed(GlobalPageElements.img_UkCountryLabel))
			return AdoddleCommonStringPool.DATE_FORMAT_UK;
		else if (isDisplayed(GlobalPageElements.img_UsCountryLabel))
			return AdoddleCommonStringPool.DATE_FORMAT_US;
		else
			return AdoddleCommonStringPool.DATE_FORMAT_AUS;
	}

	private void createMultiUserAndActionList(String user1, String user2, String user3) {

		multiUserList.clear();
		multipleActionList.clear();

		if (user1.contains(ResourceHandler.loadProperty("test.user.tc.bloggs.name"))) {
			multipleActionList.add(AdoddleCommonStringPool.ACTION_FOR_INFORMATION);
			multipleActionList.add(AdoddleCommonStringPool.ACTION_FOR_COMMENT);
			multipleActionList.add(AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE);
			multiUserList.add(user1);
			multiUserList.add(user2);
			multiUserList.add(user3);
		} else if (user1.contains(ResourceHandler.loadProperty("test.user.rfi.builder.name"))) {
			multipleActionList.add(AdoddleCommonStringPool.ACTION_FOR_DISTRIBUTION);
			multipleActionList.add(AdoddleCommonStringPool.ACTION_FOR_ACTION);
			multipleActionList.add(AdoddleCommonStringPool.ACTION_FOR_STATUS_CHANGE);
			multiUserList.add(user1);
			multiUserList.add(user2);
			multiUserList.add(user3);
		} else {
			multipleActionList.add(AdoddleCommonStringPool.ACTION_FOR_ACKNOWLEDGEMENT);
			multipleActionList.add(AdoddleCommonStringPool.ACTION_FOR_INFORMATION);
			multiUserList.add(user1);
			multiUserList.add(user2);
		}

	}

}