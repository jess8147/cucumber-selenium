/*  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;
import org.asite.automation.CommonLocators.AdoddleDiscussionsLocators.DiscussionsTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleModelsLocators.ModelsTab;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.junit.Assert;
import org.openqa.selenium.By;

public class CreateModelCommentScripts extends AdoddleCommonAppMethods {

	public static String parentWindow;
	final private static String modelTitleName = "AutomationTest_CommentModel";
	final private String viewModelCommentTitle = "AutoTestModelComment" + epoch;

	public void clickOnModel()
	{
		clickElementAndWait(ModelsTab.ele_CustomizeModelListingOptions);
		if (!isDisplayed(ProjectsTab.lnk_ListViewActiveLink))
			clickElementAndWait(ProjectsTab.lnk_ListView);
		searchModels(modelTitleName);
		collectionDataMap.put("TestData Model", modelTitleName);
		parentWindow  = clickElementAndSwitchWindow(ModelsTab.lnk_ModelsTabFirstModel);
	}

	public void verifyViewModel() {
		Assert.assertTrue(getElementText(ModelsTab.ele_ModelsTabViewModelName).contains(modelTitleName));
		waitUntilElementIsDisplayed(ModelsTab.lbl_ViewIFCFileSuccessMessage);
		Assert.assertTrue(isDisplayed(ModelsTab.scr_ModelViewerCanvasScreen));
	}

	public void verifyCreatedCommentOnViewModelTab(String drpdownPopup)
	{
		waitForCompletePageLoad();
		waitUntilElementContainsText(GlobalPageElements.lbl_BetaViewActiveTabDropdownPopupLabel, drpdownPopup, 60);
		waitUntilElementIsDisplayed(By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//div[@class='ibox']//ul[1]//li//span[contains(@title,'"+viewModelCommentTitle+"')]"));
	}

	public void clickOnFirstAttachAssociateHyperLink()
	{
		clickElementAndWait(ModelsTab.img_ViewModelFirstCommentAssociationsClipIcon);
	}

	public void verifyUnReadModelCommentOnDiscussionTab()
	{
		searchComments(viewModelCommentTitle);
		Assert.assertTrue(getElementText(DiscussionsTab.lnk_DiscussionFirstCommentTitle).contains(viewModelCommentTitle));
		waitUntilElementIsDisplayed(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		mouseHover(GlobalPageElements.lnk_FirstMyActionCountPopOver);
		waitUntilElementIsDisplayed(GlobalPageElements.pop_firstActionsPopOverContent);
		Assert.assertTrue(isDisplayed(GlobalPageElements.css_firstActionsPopoverContentLinks));
		Assert.assertTrue(isDisplayed(GlobalPageElements.css_firstActionsPopoverDueDaysContents));
	}

	public void selectUserAndEnterCommentDetails()
	{
		sendKeys(GlobalPageElements.txt_BetaViewPopCreateDiscussionDistributeTo, System.getProperty("secondary.username"));
		clickElementAndWait(By.xpath(".//main[@class='open']//div[@class='ibox']//span[contains(text(),'"+System.getProperty("secondary.username")+"')]//preceding::input[@type='checkbox']"));
		clickElementAndWait(GlobalPageElements.lnk_BetaViewPopCreateDiscussionDistributeToCloseButton);
		sendKeys(GlobalPageElements.txt_BetaViewPopCreateDiscussionTitle, viewModelCommentTitle);
		collectionDataMap.put("Model Comment", viewModelCommentTitle);
		sendKeys(GlobalPageElements.txt_BetaViewPopCreateDiscussionTextarea, javaUtils.getRandomString(15));
	}
	
	public void clickSubmitDisscussionLink()
	{
		clickElementAndWait(FilesTab.btn_BetaFileViewNewDiscussionSubmit);
	}

}