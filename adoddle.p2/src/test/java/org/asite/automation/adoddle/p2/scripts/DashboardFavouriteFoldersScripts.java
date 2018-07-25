/*  Testdata required for this script as follows.
 * 		Scenario : Set Folder as Favourite Folder and Verify Incomplete Actions Count of Favourite Folder
      1). Folder : "Automation_Favourite_Folder", Sub-Folder : "Automation_Favourite_Sub_Folder".
      2). Both Folders have files that have incomplete Actions.
 */
package org.asite.automation.adoddle.p2.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleDashboardLocators.DashboardTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleModelsLocators.ModelsTab;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DashboardFavouriteFoldersScripts extends AdoddleCommonAppMethods {
	private int				i, filesTabCount, dashboardWidgetCount, beforeIncompleteActionsCount;
	private Boolean			isListPresent;
	private boolean			flag;
	private String			file, filterFile, latestRevFile, previousRev, latestRev;
	private static String	parentFolder, subFolder;
	final private List<String>	multiFileUploadList	= new ArrayList<String>();
	final private static Logger	log				= AutomationLogger.getInstance().getLogger(DashboardFavouriteFoldersScripts.class);

	/******* Favourite Folder With Incomplete Actions Count *******/

	public void clickOnDashboardWidgetHyperLink(String favFolderWidgetLink) {
		waitForCompletePageLoad();
		if (isDisplayedLinkWithText(favFolderWidgetLink))
			clickLinkWithText(favFolderWidgetLink);
		else
			navigateTab(LandingPage.lnk_Files);
	}

	public void addRemoveFavouriteFolder(String folder, String addRemoveFavourite) {
		collectionDataMap.put("Favourite Folder Name", folder);
		contextClickWithLink(folder);
		clickContextMenuOptionWithText(addRemoveFavourite);
	}

	public void verifyFavouriteFolderOnFilesTab(String folder, String setRemove) {
		waitForCompletePageLoad();
		log.info("Favourite Folder on Files Tab : " + findElement(By.xpath(".//a[@title='" + folder + "']//span[1][@class]")).getAttribute("class").contains("fav"));

		if (setRemove.contains("set"))
			Assert.assertTrue(findElement(By.xpath(".//a[@title='" + folder + "']//span[1][@class]")).getAttribute("class").contains("fav"));
		else
			Assert.assertTrue(!findElement(By.xpath(".//a[@title='" + folder + "']//span[1][@class]")).getAttribute("class").contains("fav"));
	}

	public void verifyFavouriteFolderOnDashboardWidget(String folder, String isDisplay) {
		waitForCompletePageLoad();
		log.info("Favourite Folder on Dashboard : " + isDisplayed(By.xpath(".//div[@id='widgetbody-3']//li[@class='list-item']//div[@title='" + folder + "']")));

		if (isDisplay.contains("not"))
			Assert.assertTrue(!isDisplayed(By.xpath(".//div[@id='widgetbody-3']//li[@class='list-item']//div[@title='" + folder + "']")));
		else
			Assert.assertTrue(isDisplayed(By.xpath(".//div[@id='widgetbody-3']//li[@class='list-item']//div[@title='" + folder + "']")));
	}

	public void clickOnDashboardFavouriteFolderWidget(String folder) {
		clickElementAndWait(By.xpath(".//div[@id='widgetbody-3']//li[@class='list-item']//div[@title='" + folder + "']"));
	}

	private void selectFilter(String filter) {
		waitUntilElementIsDisplayed(FilesTab.txt_SearchCreateFilterInput);
		if (isDisplayed(GlobalPageElements.lnk_FilterProjectClearAll))
			clickElementAndWaitForElement(GlobalPageElements.lnk_FilterProjectClearAll, GlobalPageElements.lnk_FilterProjectDisableClear);
		waitForCompletePageLoad();
		if (!filter.contains(",")) {
			sendKeys(FilesTab.txt_SearchCreateFilterInput, filter);
			if (filter.equalsIgnoreCase("Status"))
				clickElement(By.xpath(".//a[@title='" + filter + "']//input[@type='checkbox']"));
			else
				clickElement(By.xpath(".//a[contains(@title,'" + filter + "')]//input[@type='checkbox']"));
		}
		else {
			for (String subFilter : filter.split(",")) {
				if (!isSelected(By.xpath(".//div[@id='assignee-suggestions']//label[@title='" + subFilter + "']//input[@type='checkbox']")))
					clickElementAndWait(By.xpath(".//div[@id='assignee-suggestions']//label[@title='" + subFilter + "']//input[@type='checkbox']"));
			}
		}
		if (isDisplayed(GlobalPageElements.lnk_FilterClose))
			clickElementAndWait(GlobalPageElements.lnk_FilterClose);
		else
			waitForCompletePageLoad();
	}

	private void deselectFilterOption(String filter) {
		sendKeys(FilesTab.txt_SearchCreateFilterInput, filter);
		waitUntilElementIsDisplayed(By.xpath(".//a[contains(@title,'" + filter + "')]//input[@type='checkbox']"));

		if (isSelected(By.xpath(".//a[contains(@title,'" + filter + "')]//input[@type='checkbox']")))
			clickElement(By.xpath(".//a[contains(@title,'" + filter + "')]//input[@type='checkbox']"));

		if (isDisplayed(GlobalPageElements.lnk_FilterClose))
			clickElementAndWait(GlobalPageElements.lnk_FilterClose);
		else
			waitForCompletePageLoad();
	}

	public void filesTabIncompleteActionsCount() {
		filesTabCount = getIncompleteActionsCountOfFilesTab();
		log.info("filesTabCount : " + filesTabCount);
	}

	public void verifyIncompleteActionsCountOfDashboardFolderWidget() {
		String dashboardCount = getElementText(DashboardTab.ele_DashboardFavouriteFolderIncompleteActionsCount);
		dashboardWidgetCount = Integer.parseInt(dashboardCount);
		log.info("dashboard widget count : " + dashboardWidgetCount);
		collectionDataMap.put("Files Tab Favourite Folder Count", Integer.toString(filesTabCount));
		collectionDataMap.put("Dashboard Favourite Folder Count", Integer.toString(dashboardWidgetCount));
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(dashboardWidgetCount, filesTabCount), dashboardWidgetCount == filesTabCount);
	}

	public void clickOnFavouriteFolderIncompleteCounts() {
		clickElementAndWait(DashboardTab.ele_DashboardFavouriteFolderIncompleteActionsCount);
	}

	public void verifyWidgetFromFilesFavouriteFolderActionsCount() {
		int totalCount = getIncompleteActionsCountOfFilesTab();
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(totalCount, dashboardWidgetCount), totalCount == dashboardWidgetCount);
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(dashboardWidgetCount, filesTabCount), dashboardWidgetCount == filesTabCount);
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(totalCount, filesTabCount), totalCount == filesTabCount);
	}

	private int getIncompleteActionsCountOfFilesTab() {
		int totalCount;
		isListPresent = findElements(GlobalPageElements.css_FilesTabMyActionCountPopOverPlusImageList).size() > 0;

		if (! isListPresent) {
			totalCount = 0;
		}
		else {
			executeJScript(AdoddleCommonJQueries.scrollDownJquery);
			waitForCompletePageLoad();
			totalCount = 0;
			for (WebElement actionPlusImg : findElements(GlobalPageElements.css_FilesTabMyActionCountPopOverPlusImageList)) {
				String plusVal = actionPlusImg.getText().trim().replace("+", "");
				if (plusVal.isEmpty()) {
					totalCount += 1;
				}
				else {
					totalCount += Integer.parseInt(plusVal) + 1;
				}
			}
			log.info("total action count: " + totalCount);
		}
		return totalCount;
	}

	/******* Favourite Folder Actions Count with Color code *******/

	private void createFolder(String folder, String folderType) {
		clickContextMenuOptionWithText("New");
		clickContextMenuOptionWithText(folder);
		sendKeys(FilesTab.txt_PopCreateFolderFolderName, folderType);
		clickElementAndWaitForElement(FilesTab.btn_PopCreateFolderCreate, By.xpath(".//span[text()='" + folderType + "']"));
	}

	public void createParentAndSubFolderAndSetAsFavourite(String addAsFavourite) {
		epoch = dateUtils.getEpoch();
		parentFolder = "AutoParentFolder" + epoch;
		subFolder = "AutoSubFolder" + epoch;

		collectionDataMap.put("Favourite Parent Folder", parentFolder);
		collectionDataMap.put("Favourite Sub Folder", subFolder);

		clickElementWithText(System.getProperty("global.test.project"));
		contextClickWithText(System.getProperty("global.test.project"));

		createFolder("Folder", parentFolder);
		contextClickWithText(parentFolder);
		createFolder("Sub-Folder", subFolder);

		addRemoveFavouriteFolder(parentFolder, addAsFavourite);
	}

	public void verifyParentFavouriteFolderOnFilesTab(String setRemove) {
		verifyFavouriteFolderOnFilesTab(parentFolder, setRemove);
	}

	public void verifyParentFavouriteFolderOnDashboardWidget(String isDisplay) {
		verifyFavouriteFolderOnDashboardWidget(parentFolder, isDisplay);
	}

	public void clickOnDashboardFavouriteFolderWidget() {
		clickOnDashboardFavouriteFolderWidget(parentFolder);
	}

	public void uploadFilesInFavouriteFolder(String folder) {
		String[] fileList = { file, file, file, file };
		if (folder.contains("Sub folder"))
			clickElementWithText(subFolder);
		clickElementAndWait(FilesTab.btn_DocListingAddFiles);

		i = 1;
		epoch = dateUtils.getEpoch();
		multiFileUploadList.clear();
		sysUtils.authenticateRemoteMachine(nodeIP);
		for (String fileName : fileList) {
			fileName = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + epoch + i + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
			multiFileUploadList.add(fileName);
			i++;
		}
		if (folder.contains("Sub folder"))
			collectionDataMap.put("Sub Folder FilePath List", multiFileUploadList.toString());
		else
			collectionDataMap.put("Parent Folder FilePath List", multiFileUploadList.toString());
		uploadFileUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, multiFileUploadList);

		clickElementAndWait(FilesTab.chk_PopUploadFilesBulkApply);
		enterMendatoryAttributes();
	}

	private void enterMendatoryAttributes() {
		clickElementAndWaitForElement(FilesTab.btn_PopUploadCopyDocRef, FilesTab.txt_FileUploadRevisionHeaderInput);
		sendKeys(FilesTab.txt_FileUploadRevisionHeaderInput, "1");
		selectByIndex(FilesTab.drp_PopUploadHeaderPurposeOfIssue, 1);
		selectByIndex(FilesTab.drp_PopUploadHeaderStatus, 1);
		executeJScript(AdoddleCommonJQueries.scrollMaxLeftJQuery);
		clickElementAndWait(FilesTab.btn_PopUploadApplytoAll);
	}

	public void verifyUploadedFiles() {
		List<String> actualFileList = new ArrayList<String>();
		List<String> expectedFileList = new ArrayList<String>();

		sysUtils.authenticateRemoteMachine(nodeIP);
		log.info("searched File : " + (strUtils.extractFileNameString(nodeIP + resourceUtils.getTestDataFilePath() + epoch)));
		searchFiles(strUtils.extractFileNameString((nodeIP + resourceUtils.getTestDataFilePath() + epoch)));
		for (WebElement uiFile : findElements(FilesTab.css_NumberOfFiles)) {
			actualFileList.add(uiFile.getText());
		}
		log.info("actual file list : " + actualFileList);

		for (String localFile : multiFileUploadList) {
			expectedFileList.add(strUtils.extractFileNameString(localFile));
		}
		log.info("expected file list : " + expectedFileList);
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(actualFileList.toString(), expectedFileList.toString()), actualFileList.containsAll(expectedFileList));
		searchFiles("");
	}

	public void selectFilesForDistribute(String fileSelection, String folder, String option1, String option2) {
		String file1, file2;
		if (fileSelection.contains("other")) {
			file1 = "2";
			file2 = "3";
		}
		else {
			file1 = "1";
			file2 = "2";
		}

		if (folder.contains("Parent folder")) {
			waitUntilElementIsDisplayed(FilesTab.btn_IncludeSubFoldersFilterButton);
			clickElementAndWait(FilesTab.btn_IncludeSubFoldersFilterButton);
			deselectFilterOption("Yes");
		}
		else
			clickElementWithText(subFolder);

		clickElementAndWait(FilesTab.lbl_FilesTabFilesCount);
		waitUntilElementIsClickable(FilesTab.lnk_FileName);
		List<WebElement> fileDocRefList = findElements(FilesTab.lnk_DocRefList);
		i = 0;
		String fileDocRef = null;
		for (WebElement docRef : fileDocRefList) {
			if (docRef.getText().endsWith(file1) || docRef.getText().endsWith(file2)) {
				fileDocRef = docRef.getText();
				log.info("file doc ref :" + fileDocRef);
				clickElementAndWait(By.xpath(".//div[@index='" + i + "']//input[@type='checkbox']"));
			}
			i++;
		}
		contextClick(By.xpath(".//div[@index]//div[contains(@class,'docRef')]//a[text()='" + fileDocRef + "']"));
		clickContextMenuOption(option1, option2);
	}

	public void assignFilesActionsToUsers(String action1, String action2, String forInfo, String dueDays, String userId) {
		List<String> actionArrayList = new ArrayList<String>();
		actionArrayList.add(action1);

		if (action2 != null)
			actionArrayList.add(action2);
		if (forInfo != null)
			actionArrayList.add(forInfo);

		i = 1;
		for (String action : actionArrayList) {
			sendKeys(FilesTab.txt_PopDistributeToAccessField, userId);
			sendKeys(FilesTab.txt_PopDistributeToAccessField, Keys.ENTER);
			if (!action.equalsIgnoreCase(forInfo)) {
				clickElementAndWait(By.xpath(".//div[contains(@style,'block')]//ul[@class='select2-choices']//li[" + i + "]//button"));
				selectByVisibleText(ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown, action);
				selectCustomizedDateFromCalendar(Integer.parseInt(dueDays));
			}
			i++;
		}
	}

	public void getIncompleteActionsCountOfFilesTabFavouriteFolder() {
		clickElementAndWait(FilesTab.btn_SearchCreateFilterDropdown);
		selectFilter(AdoddleCommonStringPool.FILTER_ACTION_STATUS);
		clickElementAndWait(FilesTab.btn_ActionStatusFilterButton);
		selectFilter("Incomplete");
		filesTabCount += getIncompleteActionsCountOfFilesTab();
		log.info("files tab incomplete action count :" + filesTabCount);
	}

	public void getIncompleteActionsCount(String folder) {
		if (folder.contains("Parent folder")) {
			waitUntilElementIsDisplayed(FilesTab.btn_IncludeSubFoldersFilterButton);
			clickElementAndWait(FilesTab.btn_IncludeSubFoldersFilterButton);
			selectFilter("Yes");
		}
		clickElementWithText(parentFolder);
		createFilter(AdoddleCommonStringPool.FILTER_ACTION_STATUS, "Incomplete");

		filesTabCount = getIncompleteActionsCountOfFilesTab();
		log.info("files tab incomplete action count  :" + filesTabCount);
	}

	public void verifyDashboardFavouriteFolderActionsCountAndColorCode(String color) {
		List<WebElement> favouriteFolderList = findElements(DashboardTab.css_DashboardFavouriteFoldersList);
		i = 1;
		for (WebElement folder : favouriteFolderList) {
			if (folder.getText().contains(parentFolder)) {

				String count = getElementText(By.xpath(".//div[@id='widgetbody-3']//li[@class='list-item'][" + i + "]//span[contains(@class,'total-widget-action-count')]"));
				dashboardWidgetCount = Integer.parseInt(count);

				String colorCode = findElement(By.xpath(".//div[@id='widgetbody-3']//li[@class='list-item'][" + i + "]//span[contains(@class,'total-widget-action-count')]")).getAttribute("class");
				log.info("actual code : " + colorCode + ":::: expected code : " + color);
				Assert.assertTrue(colorCode.contains(color));
				break;
			}
			i++;
		}
		log.info("dashboardWidgetCount : " + dashboardWidgetCount);
		Assert.assertTrue(dashboardWidgetCount == filesTabCount);
	}

	public void getTotalIncompleteActionsCountOfFilesTabFavouriteFolder() {
		executeJScript(AdoddleCommonJQueries.scrollMaxLeftWindowJQuery);
		waitForCompletePageLoad();

		waitUntilElementIsDisplayed(FilesTab.chk_MultiFilesSelectionCheckbox);
		clickElementWithText(parentFolder);
		if (isSelected(FilesTab.chk_MultiFilesSelectionCheckbox))
			clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);

		// filesTabCount = getIncompleteActionsCountOfFileDistributionPage();
		filesTabCount = getIncompleteActionsCountOfFilesTab();
		log.info("filesTabCount :" + filesTabCount);
	}

	public void uploadFileUsingNextRevision(String userID, String action) {
		waitForCompletePageLoad();
		latestRevFile = getElementText(FilesTab.lnk_FileName);
		previousRev = getElementText(FilesTab.lnk_FileListingFirstRevision);

		collectionDataMap.put("Latest Rev File", latestRevFile);
		collectionDataMap.put("Previous Rev File", previousRev);
		log.info("latestRevFile : " + latestRevFile);
		log.info("previousRev : " + previousRev);

		latestRev = Integer.toString((Integer.parseInt(previousRev) + 1));
		collectionDataMap.put("Latest Rev", latestRev);
		log.info("latestRev : " + latestRev);

		clickElementAndWait(FilesTab.btn_DocListingAddFiles);

		sysUtils.authenticateRemoteMachine(nodeIP);
		String testDataPath = nodeIP + resourceUtils.getTestDataFilePath();
		multiFileUploadList.clear();
		multiFileUploadList.add((testDataPath + latestRevFile).trim());

		collectionDataMap.put("Uploaded File Second Rev", multiFileUploadList.toString());
		uploadFileUsingKeys(FilesTab.btn_PopUploadFileDistributeSelectFiles, multiFileUploadList);

		waitUntilElementIsClickable(FilesTab.btn_PopUploadCopyDocRef);
		clickElementAndWait(FilesTab.btn_PopUploadCopyDocRef);
		sendKeys(FilesTab.txt_RevisionFile1, latestRev);
		selectByIndex(FilesTab.drp_PurposeOfIssueFile1, 1);
		selectByIndex(FilesTab.drp_StatusFile1, 1);

		clickElementAndWait(FilesTab.btn_PopUploadFileDistributeFiles);
		sendKeys(FilesTab.txt_PopUploadFileDistributeTo, userID);
		sendKeys(FilesTab.txt_PopUploadFileDistributeTo, Keys.ENTER);

		clickElementAndWait(FilesTab.btn_PopDistributeUserToggleButton);
		selectByVisibleText(ProjectsTab.sel_DistributionGroupContextMenuActionsDropdown, action);
		clickElementAndWait(FilesTab.lnk_PopDatePickerAssignedCurrentDate);
	}

	public void verifyUploadedFileWithBothRevision(String action) {
		searchFiles(latestRevFile);
		Assert.assertTrue(findElements(FilesTab.css_NumberOfFiles).size() == 2);

		int i = 0;
		for (WebElement file : findElements(FilesTab.css_NumberOfFiles)) {
			Assert.assertTrue(file.getText().contains(latestRevFile));
			if (getElementText(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'revisionNum')]//a")).contains(latestRev)) {
				Assert.assertTrue(getElementText(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'revisionNum')]//a")).contains(latestRev));
				Assert.assertTrue(!getElementText(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'revisionNum')]//a")).contains(previousRev));
				Assert.assertTrue(getElementText(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'actionName')]//a")).contains(action));
			}
			else {
				Assert.assertTrue(getElementText(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'revisionNum')]//a")).contains(previousRev));
				Assert.assertTrue(!getElementText(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'revisionNum')]//a")).contains(latestRev));
			}
			i++;
		}
	}

	public void verifyDashboardIncompleteActionsCountAfterRevisionUpload() {
		String dashboardCount = getElementText(DashboardTab.ele_DashboardFavouriteFolderIncompleteActionsCount);
		log.info("dashboardCount : " + dashboardCount);
		log.info("dashboardWidgetCount : " + dashboardWidgetCount);
		Assert.assertTrue(Integer.parseInt(dashboardCount) > dashboardWidgetCount);
	}

	/******* Filter Preferences & Favourite Folders *******/

	public void setFilterPreferences(String setRemove) {
		while (isDisplayed(GlobalPageElements.lnk_HeaderNotificationClose))
			clickElementAndWait(GlobalPageElements.lnk_HeaderNotificationClose);
		clickElementAndWaitForElement(LandingPage.btn_UserProfile, LandingPage.lnk_Preferences);
		clickElementAndWaitForElement(LandingPage.lnk_Preferences, LandingPage.lnk_FilterPreferences);
		clickElementAndWait(LandingPage.lnk_FilterPreferences);
		waitForCompletePageLoad();

		if (setRemove.contains("set")) {
			if (!isSelected(LandingPage.chk_RememberAppliedFilterCheckbox))
				clickElementAndWait(LandingPage.chk_RememberAppliedFilterCheckbox);
		}
		else {
			if (isSelected(LandingPage.chk_RememberAppliedFilterCheckbox))
				clickElementAndWait(LandingPage.chk_RememberAppliedFilterCheckbox);
		}
		clickElementAndWait(LandingPage.btn_PopUserPreferencesSubmitButton);
		clickElementAndWait(ModelsTab.btn_ClosePopupButton);
		waitForCompletePageLoad();
	}

	public void removeAllSetFilters() {
		isListPresent = findElements(FilesTab.css_CreatedFiltersRemoveButtonList).size() > 0;
		if (isListPresent) {
			for (WebElement filterRemoveButton : findElements(FilesTab.css_CreatedFiltersRemoveButtonList)) {
				filterRemoveButton.click();
				waitForCompletePageLoad();
			}
		}
		else
			log.info(": filters all ready removed :" + false);
	}

	public void uploadFilesUsingDifferentStatus(String status) throws InterruptedException, IOException {
		String[] fileList = { file, file };
		multiFileUploadList.clear();
		sysUtils.authenticateRemoteMachine(nodeIP);
		for (String fileName : fileList) {
			fileName = sysUtils.createRemotePdfFile(nodeIP + resourceUtils.getTestDataFilePath() + epoch + i + AdoddleCommonStringPool.PDF_EXTENSION, nodeIP).trim();
			multiFileUploadList.add(fileName);
			i++;
		}
		collectionDataMap.put("Filter Preferences FileList", multiFileUploadList.toString());
		log.info("multiFileUploadList : " + multiFileUploadList);
		uploadDocuments(multiFileUploadList, 2, null, null, false, 1, null, Arrays.asList(status), null, null);
	}

	public void verifyListFilesStatusValue(String status) {
		List<WebElement> fileStatusList = findElements(FilesTab.css_DocListingStatusList);
		List<WebElement> fileNameList = findElements(FilesTab.css_NumberOfFiles);

		log.info("fileNameList :" + fileNameList.size() + " fileStatusList :" + fileStatusList.size());
		Assert.assertTrue(fileNameList.size() == fileStatusList.size());

		for (WebElement fileStatus : fileStatusList) {
			Assert.assertTrue(fileStatus.getText().contains(status));
		}
	}

	public void searchStatusFile(String status) {
		searchFiles("");
		filterFile = getElementText(FilesTab.lnk_FileName);
		log.info("filterFile : " + filterFile);

		searchFiles(filterFile);
		collectionDataMap.put("Filter Preferences TestData File", filterFile);
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(FilesTab.lnk_FilesTabFirstFileStatus), status), getElementText(FilesTab.lnk_FilesTabFirstFileStatus).contains(status));
	}

	public void verifySetFiltersAndFilterSelection(String filter1, String option1, String filter2, String option2, String filter3, String option3, String filter4, String option4) {
		List<String> expectedFilterList = new ArrayList<String>();
		List<String> expectedFilterOptionList = new ArrayList<String>();

		expectedFilterList.add(filter1);
		expectedFilterOptionList.add(option1);
		if (filter2 != null) {
			expectedFilterList.add(filter2);
			expectedFilterOptionList.add(option2);
		}
		if (filter3 != null) {
			expectedFilterList.add(filter3);
			expectedFilterOptionList.add(option3);
		}
		if (filter4 != null) {
			expectedFilterList.add(filter4);
			expectedFilterOptionList.add(option4);
		}

		i = 0;
		for (WebElement filter : findElements(FilesTab.css_CreatedFiltersList)) {
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(filter.getAttribute("filterkey"), expectedFilterList.get(i)), filter.getAttribute("filterkey").contains(expectedFilterList.get(i)));
			clickElementAndWait(filter);
			if (!filter.getAttribute("filterkey").contains(AdoddleCommonStringPool.FILTER_REVISIONS)) {
				AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(FilesTab.css_FilesTabFilterSelectedTypeList), expectedFilterOptionList.get(i)), getElementText(FilesTab.css_FilesTabFilterSelectedTypeList).contains(expectedFilterOptionList.get(i)));
			}
			else {
				AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(FilesTab.lbl_FilterTypeFirstSelectedOption), expectedFilterOptionList.get(i).split(",")[0]), getElementText(FilesTab.lbl_FilterTypeFirstSelectedOption).contains(expectedFilterOptionList.get(i).split(",")[0]));
				AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(FilesTab.lbl_FilterTypeSecondSelectedOption), expectedFilterOptionList.get(i).split(",")[1]), getElementText(FilesTab.lbl_FilterTypeSecondSelectedOption).contains(expectedFilterOptionList.get(i).split(",")[1]));
			}
			clickElementAndWait(filter);
			i++;
		}
	}

	public void verifyPreviousFileAndSearchFileInput() {
		String fileInputValue = findElement(FilesTab.txt_FilesFilterInput).getAttribute("value");
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(fileInputValue, filterFile), fileInputValue.contains(filterFile));
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(findElements(FilesTab.css_NumberOfFiles).size(), 1), findElements(FilesTab.css_NumberOfFiles).size() == 1);
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(FilesTab.lnk_FileName), filterFile), getElementText(FilesTab.lnk_FileName).contains(filterFile));
	}

	public void verifyResetSearchFileInputAndFileListing() {
		waitUntilElementIsDisplayed(FilesTab.txt_FilesFilterInput);
		String fileInputValue = findElement(FilesTab.txt_FilesFilterInput).getAttribute("value");
		AutomationAssert.verifyTrue(fileInputValue.isEmpty() && !fileInputValue.contains(filterFile));
		AutomationAssert.verifyTrue(findElements(FilesTab.css_DocListingDocRefList).size() > 1);
	}

	private int getIncompleteActionsCountOfFileDistributionPage() {
		return Integer.parseInt(getElementText(FilesTab.lbl_FilesTabFilesCount).split("-")[1].trim().replace(")", ""));
	}

	public void verifyFilesTabDropdown(String filesDropdown) {
		waitForCompletePageLoad();
		Assert.assertTrue(new Select(findElement(FilesTab.drp_TabTypeDropdown)).getFirstSelectedOption().getText().equalsIgnoreCase(filesDropdown));
	}

	public void createFilter(String filter, String subFilter) {
		if (getElementText(GlobalPageElements.lnk_ActiveTab).equalsIgnoreCase(AdoddleCommonStringPool.TAB_PROJECT_FORMS))
			searchForms("");
		boolean filterFlag = false;
		if (findElements(FilesTab.css_CreatedFiltersList).size() < 0) {
			filterFlag = false;
		}
		else {
			for (WebElement filterKey : findElements(FilesTab.css_CreatedFiltersList)) {
				if (filterKey.getAttribute("filterkey").contains(filter)) {
					filterFlag = true;
					break;
				}
				else
					filterFlag = false;
			}
		}

		if (!filterFlag) {
			clickElementAndWait(FilesTab.btn_SearchCreateFilterDropdown);
			selectFilter(filter);
		}
		if (filter.contains("Task Status"))
			clickElementAndWait(FilesTab.btn_ActionStatusFilterButton);
		else
			clickElementAndWait(By.xpath(".//div[contains(@id,'moreFilterCell')]//button[@filterkey='" + filter + "']"));
		selectFilter(subFilter);
	}

	public void clearActions(String selectedTab, String clearActionOption) {
		if (!isSelected(FilesTab.chk_DocListingCheckAll))
			clickElementAndWait(FilesTab.chk_DocListingCheckAll);

		if (selectedTab.equalsIgnoreCase("Files"))
			contextClick(FilesTab.lnk_UploadedFileDocRef);
		else
			contextClick(ProjectFormsTab.lnk_FirstFormTitle);
		waitForCompletePageLoad();
		clickContextMenuOption(clearActionOption);
	}

	public void removeFilter(String filterList) {
		waitForCompletePageLoad();
		for (String filter : filterList.split(",")) {
			clickElementAndWait(By.cssSelector("div[id='filterCells'] ul li[listfilterkey='" + filter + "']+i[class*='icon-remove']"));
		}
	}

	public void createFilterWithSubFilter(String filter1, String subFilter1, String filter2, String subFilter2, String filter3, String subFilter3, String filter4, String subFilter4) {
		createFilter(filter1, subFilter1);
		createFilter(filter2, subFilter2);
		createFilter(filter3, subFilter3);
		createFilter(filter4, subFilter4);
	}

	public void performActionOnDistributionPage(String action) throws InterruptedException, IOException {
		boolean flag = false;
		for (WebElement actionType : findElements(FilesTab.lnk_myActionList)) {

			if (actionType.getText().equalsIgnoreCase(action)) {
				flag = true;
				if (getElementText(GlobalPageElements.lnk_ActiveTab).equalsIgnoreCase(AdoddleCommonStringPool.TAB_FILES)) {
					clickElementAndWait(actionType);
					performFileAction(action);
				}
				else {
					clickElementAndWait(actionType);
					performFormAction(action);
				}
				break;
			}
		}
		if (!flag)
			Assert.assertTrue(action + " Incomplete Action not found on Distribution Page :", false);
		waitForCompletePageLoad();
	}

	public void getIncompleteActionsCountOfDistributionPage() {
		beforeIncompleteActionsCount = getIncompleteActionsCountOfFileDistributionPage();
		log.info("before incomplete actions count on files tab : " + beforeIncompleteActionsCount);
	}

	public void verifyIncompleteActionsCountOfDistributionPage() {
		int afterIncompleteActionsCount = getIncompleteActionsCountOfFileDistributionPage();
		log.info("after incomplete actions count on files tab  : " + afterIncompleteActionsCount);
		Assert.assertTrue(beforeIncompleteActionsCount + ">!" + afterIncompleteActionsCount, beforeIncompleteActionsCount > afterIncompleteActionsCount);
		getIncompleteActionsCountOfDistributionPage();
	}

	public void selectFilesForPerformActions(String option1, String option2) {
		executeJScript(AdoddleCommonJQueries.scrollMaxLeftWindowJQuery);
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(FilesTab.chk_MultiFilesSelectionCheckbox);
		clickElementWithText(parentFolder);
		if (!isSelected(FilesTab.chk_MultiFilesSelectionCheckbox))
			clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);

		contextClick(FilesTab.lnk_FileName);
		clickContextMenuOption(option1, option2);
	}

	/******* TestData Cleanup *******/

	public void removeTestDataFavouriteFolder() {
		String favFolderType;
		waitForCompletePageLoad();
		flag = false;
		List<WebElement> favFolderList = findElements(DashboardTab.css_DashboardFavouriteFoldersList);
		if (favFolderList.size() != 0) {
			for (WebElement folder : favFolderList) {
				favFolderType = folder.getText();
				log.info("favourite folder type : " + favFolderType);
				collectionDataMap.put("cleaned favourite folder", favFolderType);
				clickElementAndWait(folder);
				waitForElementWithText(favFolderType);
				addRemoveFavouriteFolder(favFolderType, AdoddleCommonStringPool.OPTION_REMOVE_FAVOURITE);
				navigateTab(LandingPage.lnk_Dashboard);
				flag = true;
				break;
			}
		}
		else
			log.info("forms already removed from favourite");

		if (flag)
			removeTestDataFavouriteFolder();

	}

	public void deactivateTestDataFolder() {
		try {
			log.info("deactivating parent folder :" + parentFolder);
			collectionDataMap.put("deactivated favourite folder", parentFolder);
			deactivateProjectFolder(System.getProperty("global.test.project"), parentFolder);
		}
		catch (Throwable t) {
			log.error("ERROR: Deactivating folder failed");
		}
	}

	public void removeTestDataFavouriteForms(String user) {
		String favFormType;
		waitForCompletePageLoad();
		flag = false;
		List<WebElement> favFormList = findElements(DashboardTab.css_DashboardFavouriteFormsList);
		if (favFormList.size() != 0) {

			for (WebElement formtype : favFormList) {
				favFormType = formtype.getText();
				log.info("favourite form type : " + favFormType);
				collectionDataMap.put("cleared favourite form type", favFormType);
				if (isDisplayed(DashboardTab.ele_DashboardFirstFavouriteFormActionCount)) {
					clickElementAndWait(DashboardTab.ele_DashboardFirstFavouriteFormActionCount);
					waitForElementWithText(favFormType);
					clickElementAndWait(GlobalPageElements.lnk_ActiveTabDistributionTab);
//					createFilter(AdoddleCommonStringPool.FILTER_ACTION_STATUS, AdoddleCommonStringPool.ACTION_STATUS_INCOMPLETE);
//					createFilter(AdoddleCommonStringPool.FILTER_RECIPIENT_NAME, user);

					if (!isSelected(FilesTab.chk_DocListingCheckAll))
						clickElementAndWait(FilesTab.chk_DocListingCheckAll);

					contextClick(ProjectFormsTab.lnk_FirstFormTitle);
					waitForCompletePageLoad();
					clickContextMenuOption(AdoddleCommonStringPool.OPTION_CLEAR_ACTIONS);
				}
				else
					clickElementAndWait(formtype);

				contextClickWithText(favFormType);
				clickContextMenuOptionWithText(AdoddleCommonStringPool.OPTION_REMOVE_FAVOURITE);
				navigateTabByText(AdoddleCommonStringPool.TAB_DASHBOARD);
				flag = true;
				break;
			}
		}
		else
			log.info(": Forms All ready removed form Favourite :");

		if (flag)
			removeTestDataFavouriteForms(user);
	}
	
	public void removeActionsCountFromTestDataForm()
	{
		waitForCompletePageLoad();
		if (isDisplayed(DashboardTab.ele_DashboardFirstFavouriteFormActionCount)) {
			clickElementAndWait(DashboardTab.ele_DashboardFirstFavouriteFormActionCount);
			clickElementAndWait(GlobalPageElements.lnk_ActiveTabDistributionTab);

			if (!isSelected(FilesTab.chk_DocListingCheckAll))
				clickElementAndWait(FilesTab.chk_DocListingCheckAll);

			contextClick(ProjectFormsTab.lnk_FirstFormTitle);
			waitForCompletePageLoad();
			clickContextMenuOption(AdoddleCommonStringPool.OPTION_CLEAR_ACTIONS);
			navigateTabByText(AdoddleCommonStringPool.TAB_DASHBOARD);
		} else
			log.info(": All actions all ready cleared :");
	}
}
