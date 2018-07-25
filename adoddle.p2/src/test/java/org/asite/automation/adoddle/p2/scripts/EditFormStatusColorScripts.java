/**  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFieldLocators.FieldTab;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class EditFormStatusColorScripts extends AdoddleCommonAppMethods {

	private String			rgbColorCode	= null;
	public static Logger	log				= AutomationLogger.getInstance().getLogger(EditFormStatusColorScripts.class);

	public void verifyProjectIsAvailable(String projectName) {
		searchProjects(projectName);
		Assert.assertTrue(getCount(ProjectsTab.css_NumberOfProjects) > 0);
		Assert.assertTrue(getElementText(ProjectsTab.lnk_ProjectsListFirstProject).equalsIgnoreCase(projectName));
	}

	public void searchFormStatus(String formStatus) {
		sendKeys(ProjectsTab.txt_PopManageFormStatusesStatusNameFilter, formStatus);
		waitForCompletePageLoad();
		List<WebElement> statusRows = findElements(By.cssSelector("table[id='formStatusTable'] tbody tr[entityid]"));

		for (WebElement e : statusRows) {

			if (e.findElements(By.cssSelector("input[class='formStatusName'][style*='color: rgb']")).size() > 0) {
				e.findElement(By.cssSelector("a[class='showSettingDiv'] img")).click();
				break;
			}

		}

	}

	public void changeStatusColor(String color) {
		waitUntilElementIsDisplayed(ProjectsTab.img_PopManageFormStatusesBGColorPicker);
		clickElementAndWaitForElement(ProjectsTab.img_PopManageFormStatusesBGColorPicker, ProjectsTab.txt_PopBGColorPickerBlueInput);
		if (color.equalsIgnoreCase("blue")) {
			sendKeys(ProjectsTab.txt_PopBGColorPickerRedInput, "0");
			sendKeys(ProjectsTab.txt_PopBGColorPickerGreenInput, "0");
			sendKeys(ProjectsTab.txt_PopBGColorPickerBlueInput, "255");
		}
		else if (color.equalsIgnoreCase("red")) {
			sendKeys(ProjectsTab.txt_PopBGColorPickerRedInput, "255");
			sendKeys(ProjectsTab.txt_PopBGColorPickerGreenInput, "0");
			sendKeys(ProjectsTab.txt_PopBGColorPickerBlueInput, "0");
		}
		else if (color.equalsIgnoreCase("green")) {
			sendKeys(ProjectsTab.txt_PopBGColorPickerRedInput, "0");
			sendKeys(ProjectsTab.txt_PopBGColorPickerGreenInput, "255");
			sendKeys(ProjectsTab.txt_PopBGColorPickerBlueInput, "0");
		}
		clickElementAndWait(ProjectsTab.btn_PopBGColorPickerOKButton);
		clickElementAndWait(ProjectsTab.btn_PopManageFormStatusesBGColorSave);

	}

	public void verifySavedFormStatus() {
		Assert.assertTrue(!isDisplayed(ProjectsTab.img_PopManageFormStatusesBGColorPicker));
		Assert.assertTrue(!isDisplayed(ProjectsTab.btn_PopManageFormStatusesBGColorSave));
		clickElementAndWait(ProjectsTab.btn_PopManageFormStatusesSave);

	}

	public void searchDefectWithStatus(String status) {
		clickElementAndWaitForElement(FieldTab.btn_FieldListingStatusFilter, FieldTab.txt_FieldListingStatusFilterSearchInput);
		waitForCompletePageLoad();
		sendKeys(FieldTab.txt_FieldListingStatusFilterSearchInput, status);
		waitForCompletePageLoad();
		clickElementAndWait(FieldTab.chk_FieldListingStatusFilteredFirstRecord);
		clickElementAndWait(FieldTab.txt_SearchDefectInput);
		List<WebElement> statusList = findElements(FieldTab.css_FieldListingDefectsStatus);
		log.info("Expected defect status: " + status);
		for (WebElement e : statusList) {
			log.info("Actual defect status: " + e.getText());
			Assert.assertTrue(e.getText().equalsIgnoreCase(status));
		}

	}

	public void verifyExistingDefectsColor(String color) {
		rgbColorCode = getColorCode(color);

		List<WebElement> statusList = findElements(FieldTab.css_FieldListingDefectsStatus);
		for (WebElement e : statusList)
			Assert.assertTrue(e.getAttribute("style").contains(rgbColorCode));

	}

	public void verifyCreatedDefectColor(String color) {
		rgbColorCode = getColorCode(color);
		Assert.assertTrue(findElement(FieldTab.lbl_FieldListingDefectsFirstStatus).getAttribute("style").contains(rgbColorCode));
	}

	public String getColorCode(String color) {
		if (color.equalsIgnoreCase("red"))
			return "background-color: rgb(255, 0, 0)";
		else if (color.equalsIgnoreCase("blue"))
			return "background-color: rgb(0, 0, 255)";
		else if (color.equalsIgnoreCase("green"))
			return "background-color: rgb(0, 255, 0)";
		else
			return null;

	}
}
