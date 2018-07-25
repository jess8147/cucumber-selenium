/*  Test data required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleModelsLocators.ModelsTab;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.base.TestDriverFactory;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.JavaUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class ViewModelsScripts extends AdoddleCommonAppMethods {

	private int							i, thumbViewModelsCount;
	private boolean						flag;
	private String						beforeFavouriteModelsCount, afterFavouriteModelsCount;
	private String						favouriteModel, recentModel, parentWindow;
	private String						createFile1, dateFormat;
	private String						publisherHyperLinkValue, updatedByHyperLinkValue, accessedByHyperLinkValue, editModelHyperLinkValue;
	private String						publishDate, updateDate;

	private List<WebElement>			listViewModelsList			= new ArrayList<WebElement>();
	final private List<String>			allModelsNameList			= new ArrayList<String>();
	final private List<String>			allRecentModelsNameList		= new ArrayList<String>();
	final private Map<String, String>	editModelMapList			= new HashMap<String, String>();
	final private String				scrollDownJquery			= "$('div.innerContainer').scrollTop($('div.innerContainer').scroll())";
	final private String 				ifcDocTitle 				= "AutomationTestSingleModelTitle" + epoch;
	final private String				modelName					= "AutoTestModel" + epoch, worksetName = "AutomationWorkset", modelNameEdited = "AutoTestModelEdited" + epoch;
	private static String				modelMapFolderName			= "AutoModelMapFolder";
	final private static Logger			log							= AutomationLogger.getInstance().getLogger(ViewModelsScripts.class);

	/******* ListView ThumbView *******/

	public void verifyThumbViewListing() {
		List<WebElement>	thumbViewModelsList;

		thumbViewModelsCount = 0;
		flag = true;

		do {
			executeJScript(scrollDownJquery);
			thumbViewModelsList = findElements(ModelsTab.css_ThumbViewModelsList);
			thumbViewModelsCount += thumbViewModelsList.size();

			i = 1;
			for (WebElement thumbView : thumbViewModelsList) {
				Assert.assertTrue(i + ": thumbView :" + thumbView.getText(), thumbView.isDisplayed());
				i++;
			}
			if (!isDisplayed(ModelsTab.lnk_ModelsTabDisableNextPage)) {
				clickElementAndWait(ModelsTab.lnk_ModelsTabEnableNextPage);
			}
			else {
				flag = false;
				break;
			}
		}
		while (flag);
		log.info("thumbViewModelsCount :" + Integer.toString(thumbViewModelsCount));
	}

	public void verifyListViewListing() {
		navigateTab(LandingPage.lnk_Models);
		int listViewModelsCount = 0;
		flag = true;

		do {
			executeJScript(scrollDownJquery);
			listViewModelsList = findElements(ModelsTab.css_UserModelNameList);
			listViewModelsCount += listViewModelsList.size();

			i = 1;
			for (WebElement listView : listViewModelsList) {
				Assert.assertTrue(i + ": listView :" + listView.getText(), listView.isDisplayed());
				i++;
			}
			if (!isDisplayed(ModelsTab.lnk_ModelsTabDisableNextPage)) {
				clickElementAndWait(ModelsTab.lnk_ModelsTabEnableNextPage);
			}
			else {
				flag = false;
				break;
			}
		}
		while (flag);
		log.info("listViewModelsCount :" + Integer.toString(listViewModelsCount));
		Assert.assertEquals(Integer.toString(thumbViewModelsCount), Integer.toString(listViewModelsCount));
	}

	/******* Favourite models *******/

	public void beforeFavouriteModelsCount() {
		beforeFavouriteModelsCount = getTotalFavouriteModelsCount();
		log.info("beforeFavouriteModelsCount :" + beforeFavouriteModelsCount);
	}

	private String favouriteString() {
		if (isDisplayed(GlobalPageElements.img_UkCountryLabel))
			return AdoddleCommonStringPool.STRING_FAV_UK;
		else if (isDisplayed(GlobalPageElements.img_UsCountryLabel))
			return AdoddleCommonStringPool.STRING_FAV_US;
		else if (isDisplayed(GlobalPageElements.img_AusCountryLabel))
			return AdoddleCommonStringPool.STRING_FAV_AUS;
		else
			return null;
	}

	public void selectAddAsFavouriteModel(String addAsFavourite) {
		favouriteModel = getElementText(By.xpath(".//div[@index='" + JavaUtils.getRandomNumber(1) + "']//div[contains(@class,'userModelName')]//a"));
		log.info("Favorite model title :" + favouriteModel);
		collectionDataMap.put("Favourite Model", favouriteModel);

		searchModels(favouriteModel);
		if (!isDisplayed(ProjectsTab.css_MarkAsFavouriteProjectsImageList)) {
			contextClickWithLink(favouriteModel);
			clickContextMenuOption(addAsFavourite + favouriteString());
			flag = true;
		}
	}

	public void clickOnFavouriteModelsTab() {
		clickElementAndWait(ModelsTab.lnk_FavoriteModelsTab);
	}

	public void verifyModelOnFavouriteModelsTab() {
		searchModels(favouriteModel);
		Assert.assertTrue(getElementText(ModelsTab.lnk_ModelsTabFirstModel).contains(favouriteModel));
	}

	public void verifyAddAsFavouriteModelAndCountIncreased() {
		Assert.assertTrue(isDisplayed(ProjectsTab.css_MarkAsFavouriteProjectsImageList));
		if (flag) {
			afterFavouriteModelsCount = getTotalFavouriteModelsCount();
			Assert.assertTrue(Integer.parseInt(afterFavouriteModelsCount) > Integer.parseInt(beforeFavouriteModelsCount));
		}
		else {
			afterFavouriteModelsCount = beforeFavouriteModelsCount;
			Assert.assertEquals(Integer.parseInt(afterFavouriteModelsCount), Integer.parseInt(beforeFavouriteModelsCount));
		}
		log.info("afterFavouriteModelsCount :" + afterFavouriteModelsCount);
	}

	public void clickOnAllModelsTab() {
		clickElementAndWait(ModelsTab.lnk_AllModelsTab);
	}

	public void selectRemoveAsFavouriteModel(String removeAsFavourite) {
		searchModels(favouriteModel);
		contextClickWithLink(favouriteModel);
		clickContextMenuOption(removeAsFavourite + favouriteString());
	}

	public void verifyRemovedModelOnFavouriteModelsTab() {
		searchModels(favouriteModel);
		AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(ModelsTab.lnk_ModelsTabFirstModel, false), !isDisplayed(ModelsTab.lnk_ModelsTabFirstModel));
	}

	public void verifyFavouriteModelsTabCountDecreased() {
		String getTabCount = getTotalFavouriteModelsCount();
		log.info("getTabCount :" + getTabCount);

		Assert.assertTrue(Integer.parseInt(getTabCount) < Integer.parseInt(afterFavouriteModelsCount));
	}

	public void verifyModelRemoveAsFavouriteModel() {
		searchModels(favouriteModel);
		AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(ModelsTab.lnk_ModelsTabFirstModel, true), isDisplayed(ModelsTab.lnk_ModelsTabFirstModel));
		AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(ProjectsTab.css_RemoveAsFavouriteProjectsImageList, true), isDisplayed(ProjectsTab.css_RemoveAsFavouriteProjectsImageList));
	}

	private String getTotalFavouriteModelsCount() {
		mouseHover(ModelsTab.ele_ModelsTabFavoriteModelsCount);
		return getElementText(ModelsTab.ele_ModelsTabFavoriteModelsCount);
	}

	/******* Recent models *******/

	public void getAllModelsNameList() {
		executeJScript(scrollDownJquery);
		listViewModelsList = findElements(ModelsTab.css_UserModelNameList);

		i = javaUtils.resetIndex(i, 1);

		for (WebElement model : listViewModelsList) {
			allModelsNameList.add(model.getText());
			i++;
		}
		log.info("allModelsNameList :" + allModelsNameList);
	}

	public void clickOnRecentModelsTab() {
		clickElementAndWait(ModelsTab.lnk_RecentModelsTab);
	}

	public void getAllRecentModelsNameList() {
		executeJScript(scrollDownJquery);
		listViewModelsList = findElements(ModelsTab.css_UserModelNameList);

		i = javaUtils.resetIndex(i, 1);

		for (WebElement model : listViewModelsList) {
			allRecentModelsNameList.add(model.getText());
			i++;
		}
		log.info("Recent models list :" + allRecentModelsNameList);
	}

	public void searchModelInRescentModelsTab() {

		for (String allTabModelName : allModelsNameList) {

			if (allRecentModelsNameList.contains(allTabModelName))
				flag = true;
			else {
				flag = false;
				recentModel = allTabModelName;
				break;
			}

		}
	}

	public void verifyModelNotListedInRecentModelsTab() {
		searchModels(recentModel);
		collectionDataMap.put("Recent Model", recentModel);
		AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getCount(ModelsTab.css_ListViewModelsList), 0), getCount(ModelsTab.css_ListViewModelsList) == 0);
	}

	public void searchModelInAllModelsTab() {
		searchModels(recentModel);
		parentWindow = clickElementAndSwitchWindow(ModelsTab.css_UserModelNameList);
	}

	public void verifyOpenedModel() {
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ModelsTab.ele_ModelsTabViewModelName), recentModel), getElementText(ModelsTab.ele_ModelsTabViewModelName).contains(recentModel));
	}

	public void closeViewModelWindow() {
		closeCurrentWindow();
		switchPreviousWindow(parentWindow);
		waitForCompletePageLoad();
	}

	public void verifyModelInRecentModelsTab() {
		try {
			AutomationAssert.verifyTrue(eStringUtils.getEqualityStringError(getElementText(ModelsTab.lnk_ModelsTabFirstModel), recentModel), getElementText(ModelsTab.lnk_ModelsTabFirstModel).equalsIgnoreCase(recentModel));
		}
		catch (Throwable t) {
			AutomationAssert.verifyTrue(eStringUtils.getEqualityStringError(getElementText(ModelsTab.lnk_ModelsTabLastModel), recentModel), getElementText(ModelsTab.lnk_ModelsTabLastModel).equalsIgnoreCase(recentModel));
			clickElementAndWaitForElement(ModelsTab.ele_ModelsTabListingHeaderAccessDate, ModelsTab.img_ModelsTabListingAccessDateAscending);
			clickElementAndWait(ModelsTab.img_ModelsTabListingAccessDateAscending);
			waitUntilElementContainsText(ModelsTab.lnk_ModelsTabFirstModel, recentModel);
		}
	}

	/******* Single Model *******/

	public void clickOnSelectFilesAndUploadIFC() throws IOException {
		sysUtils.authenticateRemoteMachine(nodeIP);
		createFile1 = sysUtils.copyIFCfile(nodeIP + ResourceHandler.loadProperty("single.ifc.issue1.file.path"), nodeIP + ResourceHandler.loadProperty("single.ifc.issue1.copy.file.path") + epoch);
		log.info("ifc file copied as : " + createFile1);
		collectionDataMap.put("ifc file issue1", createFile1);
		List<String> fileList = sysUtils.getFileList(createFile1);
		uploadFileUsingKeys(FilesTab.btn_SelectFiles, fileList);
	}

	public void enterMendatoryAttributes(String rev) {
		sendKeys(FilesTab.txt_RevFile1, rev);
		sendKeys(FilesTab.txt_DocTitleFile1, ifcDocTitle);
		selectByIndex(FilesTab.drp_PurposeOfIssueFile1, 2);
		selectByIndex(FilesTab.drp_StatusFile1, 2);
	}

	public void verifyActivityCentrePopup(String activityCentre) {
		/*if (isDisplayed(ModelsTab.lnk_PopModelUploadDashButton))
			clickElement(ModelsTab.lnk_PopModelUploadDashButton);
		if (isDisplayed(ModelsTab.lnk_PopModelUploadPlusButton))
			clickElement(ModelsTab.lnk_PopModelUploadPlusButton);*/
		waitUntilElementIsDisplayed(FilesTab.pop_ActivityCentre);
		AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(FilesTab.pop_ActivityCentre, true), isDisplayed(FilesTab.pop_ActivityCentre));
		verifyElementText(FilesTab.lbl_ActivityCentrePopLabel, activityCentre);
	}

	public void verifyMergingAndLoadingProcessOnActivityCentrePopup() {
		waitUntilElementIsDisplayed(FilesTab.img_popActivityCentreLoadingIcon);
		waitUntilElementIsDisplayed(FilesTab.img_popActivityCentreMergingIcon);
	}

	public void clickOnOK() {
		clickElement(FilesTab.btn_popActivityCentreOk);
		reloadPage();
		waitForCompletePageLoad();
	}

	public void verifyUploadedIFCFileWithRevisionAndIssueNum(String rev, String issueNumber) {
		navigateTab(LandingPage.lnk_Files);

		collectionDataMap.put("revision", rev);
		collectionDataMap.put("issue number", issueNumber);
		if (issueNumber.contains("1"))
			searchFiles(strUtils.extractFileNameString(createFile1));
		else
			searchFiles(strUtils.extractFileNameString(createFile1));

		AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(FilesTab.lnk_FileName, true), isDisplayed(FilesTab.lnk_FileName));
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(FilesTab.lnk_FilesTabFirstFileRev), rev), getElementText(FilesTab.lnk_FilesTabFirstFileRev).contains(rev));
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(FilesTab.lnk_FilesTabFirstFileIssueNumber), issueNumber), getElementText(FilesTab.lnk_FilesTabFirstFileIssueNumber).contains(issueNumber));
	}

	public void verifyIFCFileTypeIcon(String ifcFileType) {
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(findElement(FilesTab.img_FirstFileTypeIcon).getAttribute("src"), ifcFileType), findElement(FilesTab.img_FirstFileTypeIcon).getAttribute("src").contains(ifcFileType));
	}

	public void clickOnSelectFilesAndUploadIFCForContentChange() throws IOException {
		String createFile2;
		sysUtils.authenticateRemoteMachine(nodeIP);
		createFile2 = sysUtils.copyIFCfile(nodeIP + ResourceHandler.loadProperty("single.ifc.issue2.file.path"), nodeIP + ResourceHandler.loadProperty("single.ifc.issue2.copy.file.path") + epoch);
		log.info("ifc file copied as : " + createFile2);
		collectionDataMap.put("ifc file issue2", createFile2);
		List<String> fileList = sysUtils.getFileList(createFile2);
		uploadFileUsingKeys(FilesTab.btn_SelectFiles, fileList);
	}

	public void searchSingleModel() {
		setListingStyle("ListView");
		dateFormat = getDateFormat();
		searchModels(ifcDocTitle);
	}

	public void verifySingleModel(String modelType) {
		AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(ModelsTab.lnk_ModelsTabFirstModel, true), isDisplayed(ModelsTab.lnk_ModelsTabFirstModel));
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(ModelsTab.lnk_ModelsTabFirstModel), ifcDocTitle), getElementText(ModelsTab.lnk_ModelsTabFirstModel).contains(ifcDocTitle));
		mouseHover(ModelsTab.img_FirstModelTypeIcon);
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(findElement(ModelsTab.img_FirstModelTypeIcon).getAttribute("title"), modelType), findElement(ModelsTab.img_FirstModelTypeIcon).getAttribute("title").contains(modelType));
	}

	public void clickOnIFCFile() {

		setListingStyle("ListView");
		try {
			parentWindow = getCurrentWindow();
			clickElementAndWait(ModelsTab.lnk_ModelsTabFirstModel);
			waitForSwitchWindow(2, 10);
		}
		catch (Throwable t) {
			expandModelNameColumn();
			clickElementAndWait(ModelsTab.lnk_ModelsTabFirstModel);
			parentWindow = getCurrentWindow();
			waitForSwitchWindow(2);
		}
		switchWindow();
	}

	public void verifyIFCFileAsSingleModel() throws Exception {
		viewModel(ifcDocTitle);
	}

	public void verifyUploadedAllVersionOfSingleModel() {

		waitForCompletePageLoad();
		waitUntilElementIsClickable(ModelsTab.lnk_BetaViewModelViewerWorksetExpand);
		clickElementAndWait(ModelsTab.lnk_BetaViewModelViewerWorksetExpand);

		List<WebElement> issueNumberList;
		issueNumberList = findElements(ModelsTab.css_ModelsTabOpenedModelIssueNumberList);

		i = 0;
		for (WebElement modelIssue : issueNumberList) {
			if (i == 0)
				Assert.assertTrue(modelIssue.getText().contains("v2 - " + dateUtils.getCurrentDateTimeWithZone(dateFormat, "WET")));
			else
				Assert.assertTrue(modelIssue.getText().contains("v1 - " + dateUtils.getCurrentDateTimeWithZone(dateFormat, "WET")));
			i++;
		}
	}

	/******* Models ListView Hyperlinks *******/

	public void searchRandomModel() {
		String randomModelName = getElementText(By.xpath(".//div[@index='" + JavaUtils.getRandomNumber(1) + "']//div[contains(@class,'userModelName')]//a"));
		log.info("randomModelName :" + randomModelName);
		collectionDataMap.put("Searched Model Name", randomModelName);
		searchModels(randomModelName);
	}

	public void getHyperLinkValue(String hyperLinkType) {
		if (hyperLinkType.contains("Publisher")) {
			publisherHyperLinkValue = getElementText(ModelsTab.lnk_ModelsTabFirstModelPublisher);
			log.info("publisher name :" + publisherHyperLinkValue);
		}
		else if (hyperLinkType.contains("Updated By")) {
			updatedByHyperLinkValue = getElementText(ModelsTab.lnk_ModelsTabFirstModelLastUpdatedUser);
			log.info("last updated name :" + updatedByHyperLinkValue);
		}
		else if (hyperLinkType.contains("Accessed By")) {
			accessedByHyperLinkValue = getElementText(ModelsTab.lnk_ModelsTabFirstModelLastAccessUser);
			log.info("last accessed name :" + accessedByHyperLinkValue);
		}
		else {
			log.info("Wrong value set for hyperLink, please check again");
			Assert.assertTrue(false);
		}
	}

	public void clickOnHyperLink(String hyperLinkType) {
		if (hyperLinkType.contains(AdoddleCommonStringPool.LABEL_PUBLISHER))
			clickElementAndWait(ModelsTab.lnk_ModelsTabFirstModelPublisher);
		else if (hyperLinkType.contains(AdoddleCommonStringPool.LABEL_UPDATED_BY))
			clickElementAndWait(ModelsTab.lnk_ModelsTabFirstModelLastUpdatedUser);
		else if (hyperLinkType.contains(AdoddleCommonStringPool.LABEL_ACCESSED_BY))
			clickElementAndWait(ModelsTab.lnk_ModelsTabFirstModelLastAccessUser);
	}

	public void verifyHperLinkUserOnContactDetailsPopup(String hyperLinkType) {
		waitForCompletePageLoad();
		waitUntilElementIsDisplayed(ModelsTab.ele_PopContactDetailsUser);
		String contactDetailsUserName = findElement(ModelsTab.ele_PopContactDetailsUser).getAttribute("title");
		log.info("contact details username :" + contactDetailsUserName);
		if (hyperLinkType.contains(AdoddleCommonStringPool.LABEL_PUBLISHER))
			Assert.assertTrue(contactDetailsUserName.contains(publisherHyperLinkValue));
		else if (hyperLinkType.contains(AdoddleCommonStringPool.LABEL_UPDATED_BY))
			Assert.assertTrue(contactDetailsUserName.contains(updatedByHyperLinkValue));
		else if (hyperLinkType.contains(AdoddleCommonStringPool.LABEL_ACCESSED_BY))
			Assert.assertTrue(contactDetailsUserName.contains(accessedByHyperLinkValue));
		else
			Assert.assertTrue(false);
		clickElementAndWait(GlobalPageElements.img_AsiteLogoImage);
	}

	public void closePopup() throws InterruptedException {
		sendKeys(ModelsTab.btn_PopContactDetailsCloseButton, Keys.ESCAPE);
	}

	/******* Models tab ListView *******/

	private String getLoggInUser() {
		return findElement(LandingPage.ele_LoggedInUserProfile).getAttribute("title").split(", ")[0].split(" ")[0];
	}

	public void clickOnAddModelButton(String listingStyle) {
		publisherHyperLinkValue = getLoggInUser();
		log.info("publisherHyperLinkValue :" + publisherHyperLinkValue);
		setListingStyle(listingStyle);
		/* if (!listingStyle.contains("ThumbView")) { if (!isDisplayed(ProjectsTab.lnk_ListViewActiveLink)) clickElementAndWait(ProjectsTab.lnk_ListView); } else { if (!isDisplayed(ProjectsTab.lnk_ThumbViewActiveLink)) clickElementAndWait(ProjectsTab.lnk_ThumbView); } */
		clickElementAndWait(ModelsTab.lnk_AddModelsTab);
	}

	public void createNewModel()
	{
		modelMapFolderName = modelMapFolderName + epoch;
		createModel(modelName, javaUtils.getRandomString(10), System.getProperty("global.test.project"), worksetName, modelMapFolderName);
	}
	
	public void createModel(String model, String modelDescription, String project, String workset, String mappingFolder)
	{
		waitUntilElementIsDisplayed(ModelsTab.txt_PopAddModelTitle);
		sendKeys(ModelsTab.txt_PopAddModelTitle, model);
		collectionDataMap.put("model title", model);
		sendKeys(ModelsTab.txt_PopAddModelDescription, modelDescription);
		selectByVisibleText(ModelsTab.drp_PopAddModelProject, project);
		selectByIndex(ModelsTab.drp_PopAddModelUnits, 1);
		clickElementAndWaitForElement(ModelsTab.btn_PopAddModelAddWorkSet, ModelsTab.pop_AddModelAddWorkSet);
		sendKeys(ModelsTab.txt_PopAddWorkSetName, workset);
		collectionDataMap.put("workset name", workset);
		selectByIndex(ModelsTab.txt_PopAddWorkSetDiscipline, 1);
		clickElementAndWaitForElement(ModelsTab.lnk_PopAddWorkSetModelWorkSetTreeParent, ModelsTab.btn_PopAddWorkSetCreateFolder);
		clickElementAndWaitForElement(ModelsTab.btn_PopAddWorkSetCreateFolder, ModelsTab.btn_PopAddWorkSetCreateFolderLeftView);
		sendKeys(ModelsTab.txt_PopAddWorkSetCreateFolderInput, mappingFolder);
		collectionDataMap.put("model map folder title", mappingFolder);
		clickElementAndWaitForInvisibilityOfElement(ModelsTab.btn_PopAddWorkSetSave, ModelsTab.pop_AddModelAddWorkSet);
		sendKeys(ModelsTab.txt_PopAddModelLongitude, JavaUtils.getRandomNumber(2));
		sendKeys(ModelsTab.txt_PopAddModelLatitude, String.valueOf(Math.abs(Integer.parseInt(JavaUtils.getRandomNumber(2)) - 10)));
		sendKeys(ModelsTab.txt_PopAddModelElevation, JavaUtils.getRandomNumber(2));
		clickElementAndWait(ProjectsTab.btn_POIAndStatusSaveButton);
		waitUntilElementIsInvisible(ProjectsTab.btn_POIAndStatusSaveButton);
	}

	public void verifyModelHyperLinkAndCurrentDateForModelCreater() {
		searchModels("");
		searchModels(modelName);
		setListingStyle("ListView");
		/*
		 * if (!isDisplayed(ProjectsTab.lnk_ListViewActiveLink)) clickElementAndWait(ProjectsTab.lnk_ListView);
		 */
		List<String> hyperLinkList = new ArrayList<String>();
		hyperLinkList.add(getElementText(ModelsTab.lnk_ModelsTabFirstModelPublisher));
		hyperLinkList.add(getElementText(ModelsTab.lnk_ModelsTabFirstModelLastUpdatedUser));
		hyperLinkList.add(getElementText(ModelsTab.lnk_ModelsTabFirstModelLastAccessUser));
		for (String linkName : hyperLinkList) {
			Assert.assertEquals(publisherHyperLinkValue + " != " + linkName, publisherHyperLinkValue, linkName);
		}
		verifyModelDate();
	}

	public void verifyModelTimeAfterCreatingModel() {
		publishDate = getPublishDate();
		log.info("publishDate :" + publishDate);
		Assert.assertEquals(publishDate, getLastUpdateDate());
		Assert.assertEquals(publishDate, getLastAccessDate());
	}

	/*public void verifyModelHyperLinkAndCurrentDateTimeBeforeFileUpload() {
		updatedByHyperLinkValue = getLoggInUser();
		log.info("updatedByHyperLinkValue :" + updatedByHyperLinkValue);
		searchModels(modelName);
		verifyModelHyperLinkAndCurrentDateForModelCreater();
		Assert.assertEquals(publishDate, getPublishDate());
		Assert.assertEquals(publishDate, getLastUpdateDate());
		Assert.assertEquals(publishDate, getLastAccessDate());
	}*/

	public void contextClickOnModelAndSelectOption(String model, String menuOption) {
		setListingStyle("ListView");
		/* if (!isDisplayed(ProjectsTab.lnk_ListViewActiveLink)) clickElementAndWait(ProjectsTab.lnk_ListView); */
		if (model.contains("Edit"))
			contextClickWithLink(modelNameEdited);
		else
			contextClickWithLink(modelName);
		clickContextMenuOption(menuOption);
	}

	public void uploadIFCFile() {
		uploadIFCFile(worksetName);
	}
	
	public void uploadIFCFile(String workset)
	{
		selectByVisibleText(ModelsTab.drp_PopUploadModelFilesWorkset, workset);
		List<String> fileList = sysUtils.getFileList(ResourceHandler.loadProperty("single.ifc.file.path"));
		collectionDataMap.put("model ifc filename", fileList.toString());
		uploadFileUsingKeys(ModelsTab.btn_ModelUploadFileSelectFiles, fileList);
		clickElement(ModelsTab.btn_ModelUploadfileUpload);
	}

	public void verifyModelHyperLinkAndCurrentDateAfterFileUpload() {
		List<String> hyperLinkList = new ArrayList<String>();
		navigateTab(LandingPage.lnk_Models);
		searchModels("");
		searchModels(modelName);
		setListingStyle("ListView");
		/* if (!isDisplayed(ProjectsTab.lnk_ListViewActiveLink)) clickElementAndWait(ProjectsTab.lnk_ListView); */
		Assert.assertEquals(publisherHyperLinkValue, getElementText(ModelsTab.lnk_ModelsTabFirstModelPublisher));
		hyperLinkList.add(getElementText(ModelsTab.lnk_ModelsTabFirstModelLastUpdatedUser));
		hyperLinkList.add(getElementText(ModelsTab.lnk_ModelsTabFirstModelLastAccessUser));

		for (String linkName : hyperLinkList) {
			Assert.assertEquals(updatedByHyperLinkValue + " != " + linkName, updatedByHyperLinkValue, linkName);
		}
		verifyModelDate();
	}

	public void verifyModelTimeAfterFileUpload() {
		updateDate = getLastUpdateDate();
		log.info("updateDate :" + updateDate);
		Assert.assertNotEquals(getPublishDate(), updateDate);
		Assert.assertEquals(publishDate, getPublishDate());
		Assert.assertEquals(updateDate, getLastUpdateDate());
		Assert.assertEquals(updateDate, getLastAccessDate());
	}

	public void verifyModelHyperLinkAndCurrentDateTimeBeforeView() {
		accessedByHyperLinkValue = getLoggInUser();
		log.info("accessedByHyperLinkValue :" + accessedByHyperLinkValue);
		verifyModelHyperLinkAndCurrentDateAfterFileUpload();
		Assert.assertNotEquals(getPublishDate(), updateDate);
		Assert.assertEquals(publishDate, getPublishDate());
		Assert.assertEquals(updateDate, getLastUpdateDate());
		Assert.assertEquals(updateDate, getLastAccessDate());
	}

	public void clickOnModel() {
		/* if (!new Select(findElement(ModelsTab.drp_ModelsTabViewer)).getFirstSelectedOption().getText().contains("Browser-Rendering")) { selectByValue(ModelsTab.drp_ModelsTabViewer, "3"); waitInterval(7); } */
		clickOnIFCFile();
	}

	public void verifyViewModel() throws Exception {
		viewModel(modelName);
	}

	public void verifyModelHyperLinkAndCurrentDateAfterView() {
		searchModels("");
		searchModels(modelName);
		setListingStyle("ListView");
		/* if (!isDisplayed(ProjectsTab.lnk_ListViewActiveLink)) clickElementAndWait(ProjectsTab.lnk_ListView); */
		Assert.assertEquals(publisherHyperLinkValue, getElementText(ModelsTab.lnk_ModelsTabFirstModelPublisher));
		Assert.assertEquals(updatedByHyperLinkValue, getElementText(ModelsTab.lnk_ModelsTabFirstModelLastUpdatedUser));
		Assert.assertEquals(accessedByHyperLinkValue, getElementText(ModelsTab.lnk_ModelsTabFirstModelLastAccessUser));
	}

	public void verifyModelTimeAfterView() {
		String accessDate = getLastAccessDate();
		log.info("accessDate :" + accessDate);
		Assert.assertNotEquals(getPublishDate(), accessDate);
		Assert.assertNotEquals(getPublishDate(), updateDate);
		Assert.assertNotEquals(getLastUpdateDate(), accessDate);
		Assert.assertEquals(publishDate, getPublishDate());
		Assert.assertEquals(updateDate, getLastUpdateDate());
		Assert.assertEquals(accessDate, getLastAccessDate());
	}

	public void verifyModelHyperLinkAndCurrentDateTimeBeforeEditModel() {
		editModelHyperLinkValue = getLoggInUser();
		log.info("editModelHyperLinkValue :" + editModelHyperLinkValue);
		verifyModelHyperLinkAndCurrentDateAfterView();
	}

	public void editCreatedModel() {
		sendKeys(ModelsTab.txt_PopAddModelTitle, modelNameEdited);
		collectionDataMap.put("model name edited", modelNameEdited);
		sendKeys(ModelsTab.txt_PopAddModelDescription, javaUtils.getRandomString(10));
		sendKeys(ModelsTab.txt_PopAddModelLongitude, JavaUtils.getRandomNumber(2));
		sendKeys(ModelsTab.txt_PopAddModelLatitude, String.valueOf(Math.abs(Integer.parseInt(JavaUtils.getRandomNumber(2)) - 10)));
		sendKeys(ModelsTab.txt_PopAddModelElevation, JavaUtils.getRandomNumber(2));
		editModelMapList.put("modelTitle", findElement(ModelsTab.txt_PopAddModelTitle).getAttribute("value"));
		editModelMapList.put("description", findElement(ModelsTab.txt_PopAddModelDescription).getAttribute("value"));
		editModelMapList.put("longitude", findElement(ModelsTab.txt_PopAddModelLongitude).getAttribute("value"));
		editModelMapList.put("latitude", findElement(ModelsTab.txt_PopAddModelLatitude).getAttribute("value"));
		editModelMapList.put("elevation", findElement(ModelsTab.txt_PopAddModelElevation).getAttribute("value"));
		clickElementAndWaitForInvisibilityOfElement(ProjectsTab.btn_POIAndStatusSaveButton, ProjectsTab.btn_POIAndStatusSaveButton);
		waitForCompletePageLoad();
	}

	public void verifyEditedModel() {
		waitForCompletePageLoad();
		waitUntilElementIsClickable(ProjectsTab.btn_POIAndStatusSaveButton);
		Map<String, String> editedModelMapList = new HashMap<String, String>();
		editedModelMapList.put("modelTitle", findElement(ModelsTab.txt_PopModelPropertiesModelTitleEdit).getAttribute("value"));
		editedModelMapList.put("description", findElement(ModelsTab.txt_PopAddModelDescription).getAttribute("value"));
		editedModelMapList.put("longitude", findElement(ModelsTab.txt_PopAddModelLongitude).getAttribute("value"));
		editedModelMapList.put("latitude", findElement(ModelsTab.txt_PopAddModelLatitude).getAttribute("value"));
		editedModelMapList.put("elevation", findElement(ModelsTab.txt_PopAddModelElevation).getAttribute("value"));
		log.info(editedModelMapList);
		javaUtils.compareMapList(editedModelMapList, editModelMapList);
	}

	public void closePropertiesPopup() throws InterruptedException {
		sendKeys(ModelsTab.btn_ClosePopupButton, Keys.ESCAPE);
	}

	public void mouseHoverOnEditModelAndSelectProperties() {
		searchModels(modelNameEdited);
		mouseHoverAndClickOnProperties();
	}

	public void verifyModelHyperLinkAndCurrentDateTimeAfterEditModel() {
		searchModels("");
		searchModels(modelNameEdited);
		setListingStyle("ListView");
		/* if (!isDisplayed(ProjectsTab.lnk_ListViewActiveLink)) clickElementAndWait(ProjectsTab.lnk_ListView); */
		List<String> hyperLinkList = new ArrayList<String>();
		Assert.assertEquals(publisherHyperLinkValue, getElementText(ModelsTab.lnk_ModelsTabFirstModelPublisher));
		hyperLinkList.add(getElementText(ModelsTab.lnk_ModelsTabFirstModelLastUpdatedUser));
		hyperLinkList.add(getElementText(ModelsTab.lnk_ModelsTabFirstModelLastAccessUser));
		for (String linkName : hyperLinkList) {
			Assert.assertEquals(editModelHyperLinkValue + " != " + linkName, editModelHyperLinkValue, linkName);
		}
	}

	public void viewModel(String modelName) throws Exception {
		Assert.assertTrue(getElementText(ModelsTab.ele_ModelsTabViewModelName).contains(modelName));
		/* if (!new Select(findElement(ModelsTab.drp_ModelsViewModelViewerRenderer)).getFirstSelectedOption().getText().contains("Browser-Rendering")) selectByVisibleText(ModelsTab.drp_ModelsViewModelViewerRenderer, "Browser-Rendering"); */
		try {
			waitUntilElementIsDisplayed(ModelsTab.lbl_ViewIFCFileSuccessMessage);
		}
		catch (Throwable t) {
			log.info("unable to verify success message");
		}
		Assert.assertTrue("Unable to view model successfully", isDisplayed(ModelsTab.scr_ModelViewerCanvasScreen));
		takeScreenShot(TestDriverFactory.scenario);
	}

	private void verifyModelDate() {
		List<String> modelDateList = new ArrayList<String>();
		modelDateList.add(getElementText(ModelsTab.ele_ModelsTabFirstModelPublishDate));
		modelDateList.add(getElementText(ModelsTab.ele_ModelsTabFirstModelLastUpdateDate));
		modelDateList.add(getElementText(ModelsTab.ele_ModelsTabFirstModelLastAccessDate));
		for (String modelDate : modelDateList) {
			Assert.assertEquals(dateUtils.getCurrentDateTimeWithZone(getDateFormat(), "WET") + " " + modelDate, dateUtils.getCurrentDateTimeWithZone(getDateFormat(), "WET"), modelDate);
		}
	}

	private String getDateFormat() {
		if (isDisplayed(GlobalPageElements.img_UkCountryLabel))
			return AdoddleCommonStringPool.DATE_FORMAT_UK;
		else if (isDisplayed(GlobalPageElements.img_UsCountryLabel))
			return AdoddleCommonStringPool.DATE_FORMAT_US;
		else if (isDisplayed(GlobalPageElements.img_AusCountryLabel))
			return AdoddleCommonStringPool.DATE_FORMAT_AUS;
		else
			return "dd MMM yyyy";
	}

	private String getPublishDate() {
		return findElement(ModelsTab.ele_ModelsTabFirstModelPublishDate).getAttribute("title").split(" WET")[0];
	}

	private String getLastUpdateDate() {
		return findElement(ModelsTab.ele_ModelsTabFirstModelLastUpdateDate).getAttribute("title").split(" WET")[0];
	}

	private String getLastAccessDate() {
		return findElement(ModelsTab.ele_ModelsTabFirstModelLastAccessDate).getAttribute("title").split(" WET")[0];
	}

	/******* Models tab ThumbView *******/

	public void verifyModelOnModelListing(String listingStyle) {
		waitUntilElementIsClickable(ModelsTab.lnk_AllModelsTab);
		setListingStyle(listingStyle);
		searchModels(modelName);
		if (!listingStyle.contains("ThumbView"))
			Assert.assertTrue(isDisplayed(ModelsTab.lnk_ModelsTabFirstModel));
		else
			Assert.assertTrue(isDisplayed(ModelsTab.ele_ModelsTabThumbViewFirstModel));

		/* if (!listingStyle.contains("ThumbView")) { if (!isDisplayed(ProjectsTab.lnk_ListViewActiveLink)) { clickElementAndWait(ProjectsTab.lnk_ListView); searchModels(modelName); Assert.assertTrue(isDisplayed(ModelsTab.lnk_ModelsTabFirstModel)); } } else { if (!isDisplayed(ProjectsTab.lnk_ThumbViewActiveLink)) { clickElementAndWait(ProjectsTab.lnk_ThumbView); searchModels(modelName); Assert.assertTrue(isDisplayed(ModelsTab.ele_ModelsTabThumbViewFirstModel)); } } */
	}

	public void verifyModelHyperLinkAndCurrentDateTimeBeforeFileUpload() {
		updatedByHyperLinkValue = getLoggInUser();
		log.info("updatedByHyperLinkValue :" + updatedByHyperLinkValue);
		verifyModelHyperLinkAndCurrentDateForModelCreater();
		Assert.assertEquals(publishDate, getPublishDate());
		Assert.assertEquals(publishDate, getLastUpdateDate());
		Assert.assertEquals(publishDate, getLastAccessDate());
	}

	public void mouseHoverAndUploadFileInModel() {
		setListingStyle("ThumbView");
		/* if (!isDisplayed(ProjectsTab.lnk_ThumbViewActiveLink)) clickElementAndWait(ProjectsTab.lnk_ThumbView); */
		mouseHoverAndClickElement(ModelsTab.ele_ModelsTabThumbViewFirstModel, ModelsTab.lnk_ModelsTabFirstModelThumbViewUploadFile);
	}

	public void mouseHoverAndClickOnViewModel() {
		setListingStyle("ThumbView");
		/* if (!isDisplayed(ProjectsTab.lnk_ThumbViewActiveLink)) clickElementAndWait(ProjectsTab.lnk_ThumbView); */

		/*
		 * if (!new Select(findElement(ModelsTab.drp_ModelsTabViewer)).getFirstSelectedOption().getText().contains("Browser-Rendering")) { selectByValue(ModelsTab.drp_ModelsTabViewer, "3"); waitInterval(7); }
		 */
		mouseHoverAndClickElement(ModelsTab.ele_ModelsTabThumbViewFirstModel, ModelsTab.lnk_ModelsTabFirstModelThumbViewViewFile);
		parentWindow = getCurrentWindow();
		waitForSwitchWindow(2);
		switchWindow();
	}

	public void mouseHoverAndClickOnProperties() {
		setListingStyle("ThumbView");
		/* if (!isDisplayed(ProjectsTab.lnk_ThumbViewActiveLink)) clickElementAndWait(ProjectsTab.lnk_ThumbView); */
		mouseHoverAndClickElement(ModelsTab.ele_ModelsTabThumbViewFirstModel, ModelsTab.lnk_lnk_ModelsTabFirstModelThumbViewProperties);
		waitForCompletePageLoad();
	}

	public void verifyEditedModelOnModelListing(String listingStyle) {
		waitUntilElementIsClickable(ModelsTab.lnk_AllModelsTab);
		searchModels("");
		setListingStyle(listingStyle);
		searchModels(modelNameEdited);
		if (!listingStyle.contains("ThumbView"))
			Assert.assertTrue(isDisplayed(ModelsTab.lnk_ModelsTabFirstModel));
		else
			Assert.assertTrue(isDisplayed(ModelsTab.ele_ModelsTabThumbViewFirstModel));

		/* if (!listingStyle.contains("ThumbView")) { if (!isDisplayed(ProjectsTab.lnk_ListViewActiveLink)) { clickElementAndWait(ProjectsTab.lnk_ListView); searchModels(modelNameEdited); Assert.assertTrue(isDisplayed(ModelsTab.lnk_ModelsTabFirstModel)); } } else { if (!isDisplayed(ProjectsTab.lnk_ThumbViewActiveLink)) { clickElementAndWait(ProjectsTab.lnk_ThumbView); searchModels(modelNameEdited); Assert.assertTrue(isDisplayed(ModelsTab.ele_ModelsTabThumbViewFirstModel)); } } */
	}

	public void deactivateMappingFolder() {
		try {
			collectionDataMap.put("deactivated mapping folder", modelMapFolderName);
			deactivateProjectFolder(System.getProperty("global.test.project"), modelMapFolderName);
		}
		catch (Throwable t) {
			log.error("failure while deactivating folder");
		}
	}

	private void expandModelNameColumn() {
		executeJScript(AdoddleCommonJQueries.expandFirstModelNameJQuery);
		waitForCompletePageLoad();
	}
}
