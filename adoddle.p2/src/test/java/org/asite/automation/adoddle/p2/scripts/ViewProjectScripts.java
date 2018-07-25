/*  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleAdminLocators.ManageNotices;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.CommonLocators.AdoddleSuppliersLocators.SuppliersTab;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.lib.AdoddleCommonAppMethods.EnumList.AsiteMenu;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.PendingException;

public class ViewProjectScripts extends AdoddleCommonAppMethods {
	private int					/*totalCount,*/ formsCount = 0;
	private String				ownerOrgName, activeFilesCount, activeFilesCountOnHyperLink, lastUploadedFilesCount, lastFileUploadedHyperLinkDate, lastFormActivityDate, appsCount, projectStatusHyperLink;

	final private List<String>	selectedProjectList			= new ArrayList<String>();
	private List<WebElement>	favouriteProjectList		= new ArrayList<WebElement>();
	private String				beforeMoveElementWidth, afterMoveElementWidth;
	private WebElement			customWidthElement, resizedWidthElement;
	private List<WebElement>	divList						= new ArrayList<WebElement>();
	final private List<String>	beforeDivIdList				= new ArrayList<String>();
	final private List<String>	afterDivIdList				= new ArrayList<String>();
	final private List<String>	movedDivIdList				= new ArrayList<String>();
	final private static 		Logger		log				= AutomationLogger.getInstance().getLogger(ViewProjectScripts.class);

	/***** Search Project Filter  *****/

	public void clearSearchProjectFilter() {

		while (isDisplayed(GlobalPageElements.lnk_HeaderNotificationClose)) {
			log.info("Dismissing notification popup");
			clickElementAndWait(GlobalPageElements.lnk_HeaderNotificationClose);
		}

		clickElementAndWaitForElement(GlobalPageElements.btn_FilterProject, GlobalPageElements.lbl_FavouriteProjects);
		if (isDisplayed(GlobalPageElements.lnk_FilterProjectClearAll))
			clickElementAndWaitForElement(GlobalPageElements.lnk_FilterProjectClearAll, GlobalPageElements.lnk_FilterProjectDisableClear);

		clickElementAndWait(GlobalPageElements.btn_FilterProject);

		reloadPage();

		navigateTab(LandingPage.lnk_Dashboard);
	}

	public void selectProject(String project) {
		clickLinkWithText(project);
	}

	public void verifySearchProjectFilter(String project) {
		AutomationAssert.verifyTrue(getElementText(GlobalPageElements.ele_FilterProjectSelectedProjectName).contains(project));
	}

	public void verifySelectedProjectInFilesTab(String project) {
		AutomationAssert.verifyTrue(isDisplayed(FilesTab.ele_SelectedProjectInFilesTab));
		AutomationAssert.verifyTrue(getElementText(FilesTab.ele_SelectedProjectInFilesTab).contains(project));
	}

	/***** "OwnerOrg" HyperLink *****/

	public void getOwnerOrgName() {
		ownerOrgName = getElementText(ProjectsTab.lnk_ProjectTabFirstOwnerOrg);
		log.info("Owner Org Name :" + ownerOrgName);
		collectionDataMap.put("Owner Org Name", ownerOrgName);
	}

	public void clickOnOwnerOrgHyperLink() {
		clickElementAndWaitForInvisibilityOfElement(ProjectsTab.lnk_ProjectTabFirstOwnerOrg, ProjectsTab.lnk_ProjectTabFirstOwnerOrg);
	}

	public void verifyOwnerOrgName() {
		String ownerOrgInput = getElementAttributeValue(SuppliersTab.txt_OwnerOrgInput, "value");
		String ownerOrgOnSupplierTab = getElementText(SuppliersTab.ele_OwnerOrgName);
		Assert.assertEquals(ownerOrgName, ownerOrgOnSupplierTab);
		Assert.assertEquals(ownerOrgName, ownerOrgInput);
	}

	/***** "Files" HyperLink *****/

	public void selectRevisionIntoCreateFilterDropdown(String revisions) {
		clickElementAndWaitForElement(FilesTab.btn_SearchCreateFilterDropdown, FilesTab.txt_SearchCreateFilterInput);
		sendKeys(FilesTab.txt_SearchCreateFilterInput, revisions);
		clickElementAndWait(FilesTab.chk_CreateFilterRevisionsCheckbox);
	}

	public void verifyRevisionFilterDropdown(String revisions) {
		AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(FilesTab.drp_RevisionsDropdownFilter, true), isDisplayed(FilesTab.drp_RevisionsDropdownFilter));
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(FilesTab.drp_RevisionsDropdownFilter), revisions), getElementText(FilesTab.drp_RevisionsDropdownFilter).contains(revisions));
	}

	public void checkRevisionFilterList() {
		clickElementAndWait(FilesTab.btn_RevisionsDropdownFilter);
		List<WebElement> revisionList = findElements(FilesTab.css_RevisionsFilterCheckboxList);
		for (WebElement checkbox : revisionList) {
			if (!checkbox.isSelected()) {
				clickElementAndWait(checkbox);
			}
		}
		clickElementAndWaitForInvisibilityOfElement(FilesTab.lnk_RevisionsFilterCloseButton, FilesTab.lnk_RevisionsFilterCloseButton);
	}

	public void getTotalActiveFilesCount() {
		activeFilesCount = getTotalFileCount();
		log.info("Active Files Count-1 :" + activeFilesCount);
	}

	public void getTotalActiveFilesCountOnFilesHyperLink() {
		activeFilesCountOnHyperLink = getElementText(ProjectsTab.lnk_ProjectTabFirstActiveFilesHyperLink);
		log.info("Active Files Count On HyperLink-2 :" + activeFilesCountOnHyperLink);
		Assert.assertEquals(activeFilesCountOnHyperLink, activeFilesCount);
	}

	public void clickOnFilesHyperLink() {
		clickElementAndWaitForInvisibilityOfElement(ProjectsTab.lnk_ProjectTabFirstActiveFilesHyperLink, ProjectsTab.lnk_ProjectTabFirstActiveFilesHyperLink);
	}

	public void verifyRevisionFilterCheckedValue() {
		clickElementAndWait(FilesTab.btn_RevisionsDropdownFilter);
		List<WebElement> revisionList = findElements(FilesTab.css_RevisionsFilterCheckboxList);
		for (WebElement checkbox : revisionList) {
			AutomationAssert.verifyTrue(eStringUtils.getElementSelectionError(checkbox, true), isSelected(checkbox));
		}
	}

	public void verifyTotalFilesCount() {
		String totalFilesCount = getTotalFileCount();
		log.info("Total Files Count-3 :" + totalFilesCount);
		Assert.assertEquals(activeFilesCountOnHyperLink, totalFilesCount);
	}

	/***** "Last File Uploaded" HyperLink *****/

	public void getLastFileUploadedHyperLinkDate() {
		lastFileUploadedHyperLinkDate = getElementText(ProjectsTab.lnk_FirstProjectLastFileUploadedHyperLink);
		log.info("Last File Uploaded HyperLink Date :" + lastFileUploadedHyperLinkDate);
		collectionDataMap.put("Last File Uploaded HyperLink Date", lastFileUploadedHyperLinkDate);
	}

	public void selectDateIntoCreateFilterDropdown(String date) {
		clickElementAndWaitForElement(FilesTab.btn_SearchCreateFilterDropdown, FilesTab.txt_SearchCreateFilterInput);
		sendKeys(FilesTab.txt_SearchCreateFilterInput, date);
		waitUntilElementIsDisplayed(FilesTab.chk_CreateFilterDateCheckbox);
		clickElementAndWaitForInvisibilityOfElement(FilesTab.chk_CreateFilterDateCheckbox, FilesTab.chk_CreateFilterDateCheckbox);
	}

	public void verifyDateFilterDropdown(String date) {
		AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(FilesTab.drp_DateDropdownFilter, true), isDisplayed(FilesTab.drp_DateDropdownFilter));
		AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(getElementText(FilesTab.drp_DateDropdownFilter), date), getElementText(FilesTab.drp_DateDropdownFilter).contains(date));
	}

	public void setFileHyperLinkDateIntoDateFilter() {
		clickElementAndWaitForElement(FilesTab.drp_DateDropdownFilter, FilesTab.txt_DateFilterOnDateInput);
		sendKeys(FilesTab.txt_DateFilterOnDateInput, lastFileUploadedHyperLinkDate);
		clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_DateFilterDatePickerCloseButton, FilesTab.btn_DateFilterDatePickerCloseButton);
		clickElementAndWait(FilesTab.btn_DateFilterUpdateButton);
	}

	public void getLastUploadedFilesCount() {
		lastUploadedFilesCount = getTotalFileCount();
		log.info("Last Uploaded Files Count :" + lastUploadedFilesCount);
	}

	public void clickOnLastFileUploadedHyperLink() {
		clickElementAndWaitForInvisibilityOfElement(ProjectsTab.lnk_FirstProjectLastFileUploadedHyperLink, ProjectsTab.lnk_FirstProjectLastFileUploadedHyperLink);
	}

	public void verifyDateFilterSelectedDate() {
		clickElementAndWaitForElement(FilesTab.drp_DateDropdownFilter, FilesTab.txt_DateFilterOnDateInput);
		String lastUploadedDate = getElementAttributeValue(FilesTab.txt_DateFilterOnDateInput,"value");
		Assert.assertEquals(lastFileUploadedHyperLinkDate, lastUploadedDate);
		clickElementAndWaitForInvisibilityOfElement(FilesTab.btn_DateFilterCloseButton, FilesTab.btn_DateFilterCloseButton);
	}

	public void verifyAllLastUploadedFilesDateAndCount() {
		String lastUploadedFilesCount2 = getTotalFileCount();
		/*totalCount = 0;
		List<WebElement> numberOfLastUploadedFilesDate = new ArrayList<WebElement>();

		boolean flag = true;
		executeJScript(AdoddleCommonJQueries.scrollDownJquery);

		do {
			numberOfLastUploadedFilesDate = findElements(FilesTab.css_NumberOfFilesPublishDate);
			totalCount += numberOfLastUploadedFilesDate.size();
			log.info("filesTotalCount :" + totalCount);

			for (WebElement date : numberOfLastUploadedFilesDate) {
				log.info("date :" + date.getText());
				Assert.assertEquals(date.getText(), lastFileUploadedHyperLinkDate);
			}
			if (!isDisplayed(FilesTab.lnk_FilesTabDisableNextPage)) {
				log.info(": To be Continue :");
				clickElementAndWait(FilesTab.lnk_FilesTabEnableNextPage);
			}
			else {
				log.info(": Stop Here :");
				flag = false;
				break;
			}
		}
		while (flag);*/

		Assert.assertEquals(lastUploadedFilesCount, lastUploadedFilesCount2);
//		Assert.assertEquals(Integer.toString(totalCount), lastUploadedFilesCount2);
//		Assert.assertEquals(Integer.toString(totalCount), lastUploadedFilesCount);
	}

	/***** "Favourite Projects" filter *****/

	public void favouriteProjectsPreCondition()
	{
		clearSearchProjectFilter();

		while (isDisplayed(ManageNotices.btn_NoticeInfoClose))
			clickElementAndWait(ManageNotices.btn_NoticeInfoClose);
		
		clickElementAndWaitForElement(GlobalPageElements.btn_FilterProject, GlobalPageElements.lbl_FavouriteProjects);

		int index = 1;

		for (WebElement projectCheckbox : findElements(GlobalPageElements.css_SearchFilterProjectsCheckboxList)) {
			if(!projectCheckbox.isSelected())
				clickElementAndWait(projectCheckbox);
			selectedProjectList.add(findElement(By.xpath(".//ul[contains(@class,'suggested-projects')]//li["+index+"]//label")).getAttribute("title"));
			if(5 == index)
				break;
			index++;
		}

		clickElementAndWait(GlobalPageElements.btn_FilterProject);
		navigateTabByText(AsiteMenu.Projects.toString());
		waitUntilElementIsDisplayed(ProjectsTab.chk_ProjectsTabCheckAllCheckbox);

		if(findElements(ProjectsTab.css_MarkAsFavouriteProjectsImageList).size() != 0) {
			clickElementAndWait(ProjectsTab.chk_ProjectsTabCheckAllCheckbox);
			contextClickWithLink(selectedProjectList.get(0));
			clickContextMenuOption(AdoddleCommonStringPool.OPTION_REMOVE_FAVOURITE);
		}
		clearSearchProjectFilter();
	}
	
	public void selectProjectsIntoSearchProjectFilter() {
		List<WebElement>	nonFavouriteProjectsList;

		while (isDisplayed(ManageNotices.btn_NoticeInfoClose))
			clickElementAndWait(ManageNotices.btn_NoticeInfoClose);

		clickElementAndWaitForElement(GlobalPageElements.btn_FilterProject, GlobalPageElements.lbl_FavouriteProjects);

		nonFavouriteProjectsList = findElements(GlobalPageElements.css_SearchFilterNonFavouriteProjectsList);

		if (nonFavouriteProjectsList.size() == 0)
			throw new PendingException("non favorite projects are not available");

		for (int i = 1; i <= nonFavouriteProjectsList.size(); i++) {
			if (isDisplayed(By.xpath(".//ul[contains(@class,'suggested-projects')]//li[" + i + "]//input"))) {
				if (!isSelected(By.xpath(".//ul[contains(@class,'suggested-projects')]//li[" + i + "]//input"))) {
					selectedProjectList.add(findElement(By.xpath(".//ul[contains(@class,'suggested-projects')]//li[" + i + "]//label")).getAttribute("title"));
					clickElementAndWait(By.xpath(".//ul[contains(@class,'suggested-projects')]//li[" + i + "]//input"));
				}
			}
		}
		
		collectionDataMap.put("Selected Project List", selectedProjectList.toString());
		log.info("selectedProjectList :" + selectedProjectList);
		navigateTab(LandingPage.lnk_Dashboard);
		reloadPage();
	}

	public void selectMarkAsFavouriteProject(String markAsFavourite) {
		clickElementAndWait(ProjectsTab.chk_ProjectsTabCheckAllCheckbox);
		contextClickWithLink(selectedProjectList.get(0));
		clickContextMenuOptionWithText(markAsFavourite);
	}

	public void clickOnFavouriteProjectsTab() {
		clickElementAndWait(ProjectsTab.lnk_ProjectsTabFavoriteProjectsTab);
	}

	public void verifyProjectsOnFavouriteProjectsTab() {
		List<String> favouriteProjectName = new ArrayList<String>();
		favouriteProjectList = findElements(ProjectsTab.css_NumberOfProjects);
		for (WebElement favouriteProject : favouriteProjectList) {
			favouriteProjectName.add(favouriteProject.getText());
		}
		log.info("favouriteProjectName :" + favouriteProjectName);
		javaUtils.compareUnorderedList(selectedProjectList, favouriteProjectName);
	}

	public void verifyMarkAsFavouriteProjectsAndProjectsCount() {
		List<WebElement> markFavouriteImageList;
		markFavouriteImageList = findElements(ProjectsTab.css_MarkAsFavouriteProjectsImageList);
		int index = 0;
		for (String project : selectedProjectList) {
			AutomationAssert.verifyTrue(isDisplayedLinkWithText(project));
			AutomationAssert.verifyTrue(markFavouriteImageList.get(index).isDisplayed());
		}

		String projectsCount = getTotalProjectsCount();
		Assert.assertEquals(Integer.toString(selectedProjectList.size()), projectsCount);
		Assert.assertEquals(Integer.toString(favouriteProjectList.size()), projectsCount);
		Assert.assertEquals(Integer.toString(selectedProjectList.size()), Integer.toString(favouriteProjectList.size()));
	}

	public void clickOnAllProjectsTab(String allProjects) {
		clickLinkWithText(allProjects);
	}

	public void selectRemoveAsFavouriteProject(String removeAsFavourite) {
		clickElementAndWait(ProjectsTab.chk_ProjectsTabCheckAllCheckbox);
		contextClickWithLink(selectedProjectList.get(0));
		clickContextMenuOptionWithText(removeAsFavourite);
	}

	public void verifyNoProjectsInFavouriteProjectsTab() {
		for (String project : selectedProjectList) {
			AutomationAssert.verifyTrue(!isDisplayedLinkWithText(project));
		}
	}

	public void verifyRemoveAsFavouriteProjects() {
		List<WebElement> markRemoveFavouriteImageList;
		markRemoveFavouriteImageList = findElements(ProjectsTab.css_RemoveAsFavouriteProjectsImageList);
		int index = 0;
		for (String project : selectedProjectList) {
			AutomationAssert.verifyTrue(isDisplayedLinkWithText(project));
			AutomationAssert.verifyTrue(markRemoveFavouriteImageList.get(index).isDisplayed());
		}
	}

	/***** "Last Form Activity" for Project Forms *****/

	private boolean activeTab = false;
	public void gotoActiveFormTab(String tab)
	{
		if (isDisplayedLinkWithText(tab)) {
			clickLinkWithText(tab);
			waitForCompletePageLoad();
		}
		else {
			if (isDisplayed(By.xpath("//a[contains(@id, 'nav') and text()='" + tab + "']"))) {
				clickElementAndWait(By.xpath("//a[contains(@id, 'nav') and text()='" + tab + "']"));
			}
			else {
				clickElement(LandingPage.btn_More);
				if(isDisplayed(By.xpath("//a[contains(@id, 'nav') and text()='" + tab + "']")))
					clickElementAndWait(By.xpath("//a[contains(@id, 'nav') and text()='" + tab + "']"));
				else {
					activeTab = true;
					clickElement(LandingPage.btn_More);
				}
			}
		}
	}
	
	public void getLastFormActivityDate() {
		lastFormActivityDate = getElementText(ProjectsTab.ele_FirstProjectLastFormActivityDate);
		log.info("lastFormActivityDate :" + lastFormActivityDate);
		collectionDataMap.put("LastFormActivityDate", lastFormActivityDate);
	}

	public void getAppsCount() {
		appsCount = getElementText(ProjectsTab.ele_FirstProjectFormsCount);
		log.info("appsCount :" + appsCount);
	}

	/*public void verifyLastFormActivityDateAndFormsCount() throws ParseException {
		String[] formTab = { "Project Forms", "Contracts", "FM" };
		List<WebElement> numberOfLastUploadedFormsDate = new ArrayList<WebElement>();
		final DateFormat fmt = new SimpleDateFormat(getDateFormat());
		final Date input = fmt.parse(lastFormActivityDate);
		totalCount = 0;
		formsCount = 0;

		for (String tab : formTab) {
			navigateTabByText(tab);

			boolean flag = true;
			do {
				executeJScript(AdoddleCommonJQueries.scrollDownJquery);

				isListPresent = findElements(ProjectFormsTab.css_NumberOfFormsUpdatedDate).size() > 0;
				log.info(tab + " : isListPresent :" + isListPresent);

				if (isListPresent != true)
					break;

				numberOfLastUploadedFormsDate = findElements(ProjectFormsTab.css_NumberOfFormsUpdatedDate);
				totalCount += numberOfLastUploadedFormsDate.size();
				dateFormat = getDateFormat();

				for (WebElement formDate : numberOfLastUploadedFormsDate) {
					// if (!input.before(new Date(formDate.getText()))) {
					if (!input.before(new SimpleDateFormat(dateFormat).parse(formDate.getText()))) {
						log.info("Verified formDate :" + formDate.getText());
					}
					else {
						log.error(": Date Grater-Than Match Error :");
						AutomationAssert.verifyTrue(false);
					}
				}
				if (!isDisplayed(FilesTab.lnk_FilesTabDisableNextPage)) {
					log.info(": To be Continue :");
					clickElementAndWait(FilesTab.lnk_FilesTabEnableNextPage);
				}
				else {
					log.info(": Stop Here :");
					formsCount += Integer.parseInt(getTotalFormsCount());
					flag = false;
					break;
				}
			}
			while (flag);
			log.info("totalCount--> :" + Integer.toString(totalCount));
			log.info("formsCount--> :" + formsCount);
		}
		log.info("totalCount :" + Integer.toString(totalCount));
		log.info("formsCount :" + formsCount);
	}*/
	
	public void verifyLastFormActivityDateAndFormsCount() throws ParseException
	{
		if(! activeTab) {
			final DateFormat fmt = new SimpleDateFormat(getDateFormat());
			final Date input = fmt.parse(lastFormActivityDate);
			
			String dateFormat = getDateFormat();
			String VerifiedDate = getElementText(ProjectFormsTab.ele_FormsTabFirstFormCreatedDate);
			log.info("Verified formDate :" +VerifiedDate);
			AutomationAssert.verifyTrue(!input.before(new SimpleDateFormat(dateFormat).parse(VerifiedDate)));
			
			formsCount += Integer.parseInt(getTotalFormsCount());
		}
		log.info("formsCount : "+formsCount);
	}

	public void verifyTotalFormsCount()
	{
		log.info("formsCount : "+formsCount);
		log.info("appsCount : "+appsCount);
		Assert.assertEquals(formsCount+" != "+appsCount, appsCount, Integer.toString(formsCount));
	}

	/***** "Status" HyperLink *****/

	public void getProjectStatusHyperLinkValue() {
		projectStatusHyperLink = getElementText(ProjectsTab.lnk_FirstProjectStatusHyperLink);
		log.info("projectStatusHyperLink :" + projectStatusHyperLink);
		collectionDataMap.put("ProjectStatusHyperLink", projectStatusHyperLink);
	}

	public void clickOnProjectStatusHyperLink() {
		clickElementAndWait(ProjectsTab.lnk_FirstProjectStatusHyperLink);
	}

	public void verifyProjectStatusValue() {
		String statusValue = new Select(findElement(ProjectsTab.drp_EditProjectStatusDropdown)).getFirstSelectedOption().getText();
		log.info("statusValue :" + statusValue);

		Assert.assertEquals(projectStatusHyperLink, statusValue);
	}

	/***** "ListView" & "ThumbView" *****/

	public void clickOnThumbView() {
		setListingStyle("ThumbView");
		/*if (!isDisplayed(ProjectsTab.lnk_ThumbViewActiveLink)) {
			clickElementAndWait(ProjectsTab.lnk_ThumbView);
		}
		AutomationAssert.verifyTrue(isDisplayed(ProjectsTab.lnk_ThumbViewActiveLink));*/
	}

	public void verifyThumbViewListing() {
		List<WebElement> thumbViewProjectsList = findElements(ProjectsTab.css_ThumbViewProjectsList);
		for (WebElement thumViewproject : thumbViewProjectsList) {
			log.info("Thumb View Project:" + thumViewproject.getText());
			AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(thumViewproject, true), thumViewproject.isDisplayed());
		}
	}

	public void clickOnListView() {
		setListingStyle("ListView");
		/*if (!isDisplayed(ProjectsTab.lnk_ListViewActiveLink)) {
			clickElementAndWait(ProjectsTab.lnk_ListView);
		}
		AutomationAssert.verifyTrue(isDisplayed(ProjectsTab.lnk_ListViewActiveLink));*/
	}

	public void verifyListViewListing() {
		List<WebElement> listViewProjectsList = findElements(ProjectsTab.css_ListViewProjectsList);
		for (WebElement listViewProject : listViewProjectsList) {
			log.info("List View Project :" + listViewProject.getText());
			AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(listViewProject, true), listViewProject.isDisplayed());
		}
	}

	/***** Change Columns Position *****/

	/*public void getColumnPosition() {
		divList = findElements(By.xpath(".//div[@id='listing']//div[@id='adTableHead']//div[contains(@id,'columnId')]"));
		for (WebElement div : divList) {
			beforeDivIdList.add(div.getAttribute("id"));
		}
		log.info("beforeDivIdList :" + beforeDivIdList);
	}*/

	public void verifyMovableColumnPosition() {
		divList = findElements(ProjectsTab.css_ProjectsTabColumnList);
		for (WebElement div : divList) {
			afterDivIdList.add(div.getAttribute("id"));
		}
		log.info("After Div Id List :" + afterDivIdList);
		Assert.assertNotEquals(beforeDivIdList, afterDivIdList);
	}

	public void verifyMovedColumnPosition() {
		divList = findElements(ProjectsTab.css_ProjectsTabColumnList);
		for (WebElement div : divList) {
			movedDivIdList.add(div.getAttribute("id"));
		}
		log.info("Moved Div Id List :" + movedDivIdList);
		Assert.assertNotEquals(afterDivIdList, movedDivIdList);
		Assert.assertEquals(movedDivIdList, beforeDivIdList);
	}

	/***** Customize Columns Width *****/

	public void getColumnWidthAndCustomizeColumn() {
		customWidthElement = findElement(ProjectsTab.ele_ProjectsTabTypeColumn);
		resizedWidthElement = findElement(ProjectsTab.ele_ProjectsTabTypeColumnResizedElement);
		WebElement element3 = findElement(ProjectsTab.ele_ProjectsTabTypeAdjacentColumn);
		beforeMoveElementWidth = customWidthElement.getAttribute("style").split("width: ")[1].replace("px;", "");
		log.info("Before Move Element Width :" + beforeMoveElementWidth);
		dragAndDropElement(resizedWidthElement, element3);
	}

	public void verifyCustomizeColumnWidth() {
		customWidthElement = findElement(ProjectsTab.ele_ProjectsTabTypeColumn);
		afterMoveElementWidth = customWidthElement.getAttribute("style").split("width: ")[1].replace("px;", "");
		AutomationAssert.verifyTrue("Width " + Integer.parseInt(beforeMoveElementWidth) + " should be less than " + Integer.parseInt(afterMoveElementWidth), Integer.parseInt(beforeMoveElementWidth) < Integer.parseInt(afterMoveElementWidth));
	}

	public void getCustomizedColumnWidthAndCustomizeColumn() {
		int customizeWidth = Integer.parseInt(afterMoveElementWidth) - Integer.parseInt(beforeMoveElementWidth);
		log.info("Customize Width :" + customizeWidth);
		customWidthElement = findElement(ProjectsTab.ele_ProjectsTabTypeColumn);
		resizedWidthElement = findElement(ProjectsTab.ele_ProjectsTabTypeColumnResizedElement);
		dragAndDropElement(resizedWidthElement, customWidthElement);
	}

	public void verifyCustomizedColumnWidth() {
		customWidthElement = findElement(ProjectsTab.ele_ProjectsTabTypeColumn);
		String customizedColumnWidth = customWidthElement.getAttribute("style").split("width: ")[1].replace("px;", "");
		log.info("Customized Column Width :" + customizedColumnWidth);
		AutomationAssert.verifyTrue(Integer.parseInt(customizedColumnWidth) < Integer.parseInt(afterMoveElementWidth));
	}

	private String getTotalFileCount() {
		String fileCount[] = getElementText(FilesTab.lbl_FilesTabFilesCount).split("of ");
		String folderFiles = fileCount[1].replace(")", "");
		log.info("Folder Files :" + folderFiles);
		return folderFiles;
	}

	private String getTotalFormsCount() {
		String formCount[] = getElementText(ProjectFormsTab.lbl_ProjectFormsTabFormsCount).split("of ");
		return formCount[1].replace(")", "");
	}

	private String getTotalProjectsCount() {
		String projectCount[] = getElementText(ProjectsTab.lbl_ProjectsTabProjectsCount).split("of ");
		return projectCount[1].replace(")", "");
	}

	private String getDateFormat() {
		if (isDisplayed(GlobalPageElements.img_UkCountryLabel))
			return resourceUtils.getDateFormat("uk");
		else if (isDisplayed(GlobalPageElements.img_UsCountryLabel))
			return resourceUtils.getDateFormat("us");
		else if (isDisplayed(GlobalPageElements.img_AusCountryLabel))
			return resourceUtils.getDateFormat("aus");
		else
			return "dd MMM yyyy";
	}

	public void verifyShorting() {
		log.info(": It's all ready covered in previous method :");
	}
}