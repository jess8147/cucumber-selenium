/*   Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class FormPermissionsScripts extends AdoddleCommonAppMethods {
	private String roleName = null;
	final private String privateForm = "Automation Private Form", publicForm = "Automation Public Form";
	final private String privateFormFolder = "Private Form Folder", publicFormFolder = "Public Form Folder";
	private boolean permissionFlag = false;
	final private static Logger log = AutomationLogger.getInstance().getLogger(FormPermissionsScripts.class);

	public void createNewRole() {
		roleName = "Form_Permission_Role" + epoch;
		sendKeys(ProjectsTab.txt_PopManageRolesCreateNewRoleInput, roleName);
		collectionDataMap.put("Role Name", roleName);
	}

	public void verifyCreatedRole() {
		clickElement(ProjectsTab.lnk_PopRolePrivileges);
		clickElementAndWait(ProjectsTab.lnk_PopManageRoles);
		sendKeys(ProjectsTab.txt_CreateRolesSearchFilter, roleName);
		Assert.assertTrue(getElementAttributeValue(ProjectsTab.txt_PopManageRolesFirstFilteredRoleInput,"value")
				.contains(roleName));
	}

	public void clickOnFormPermissionsLHpanelTab() {
		clickElementAndWait(ProjectsTab.lnk_PopManageRolesFormPermissionLHPanelLink);
	}

	public void assignUserToCreatedRole() {
		String orgUserID = "pa_builder@auto.com";
		sendKeys(ProjectsTab.txt_CreateRolesSearchFilter, roleName);
		sendKeys(ProjectsTab.txt_PopManageRolesFirstAssignUserInput, orgUserID);
		sendKeys(ProjectsTab.txt_PopManageRolesFirstAssignUserInput, Keys.ENTER);
		collectionDataMap.put("Assigned User", orgUserID);
	}

	public void assignedPermissionToForms(String noAccess, String viewAllOrgs) {
		String[] accessPermissionArray = { noAccess, viewAllOrgs };

		clickElementAndWait(ProjectsTab.btn_FormPermissionRoleNameFilterButton);
		isClearDisplayed();
		sendKeys(ProjectsTab.txt_PopManageRolesSearchFilter, roleName);
		clickElementAndWaitForElement(ProjectsTab.chk_SelectedRoleCheckbox, GlobalPageElements.lnk_FilterClose);
		clickElementAndWait(GlobalPageElements.lnk_FilterClose);

		for (String accessPermission : accessPermissionArray) {
			clickElementAndWait(ProjectsTab.btn_RoleFormPermissionFilterButton);
			isClearDisplayed();
			List<WebElement> formPermissionList = findElements(ProjectsTab.css_RoleFormAccessPermissionLabelList);
			index = javaUtils.resetIndex(index, 1);
			for (WebElement permission : formPermissionList) {
				log.info("title :" + permission.getAttribute("title"));
				if (permission.getAttribute("title").contains(accessPermission)) {
					clickElementAndWait(By
							.xpath(".//div[@id='assignee-suggestions']//ul[contains(@class,'all-suggested-projects')]//li["
									+ index + "]//label[@title]//input"));
					break;
				}
				index++;
			}
			clickElementAndWaitForElement(GlobalPageElements.lnk_FilterClose,
					ProjectsTab.btn_FormPermissionFormNameFilterButton);
			clickElementAndWait(ProjectsTab.btn_FormPermissionFormNameFilterButton);
			isClearDisplayed();
			if (accessPermission.contains("No Access")) {
				if (!isSelected(By
						.xpath(".//div[@id='assignee-suggestions']//ul[contains(@class,'suggested-projects')]//li//label[@title='"
								+ publicForm + "']//input")))
					clickElementAndWait(By
							.xpath(".//div[@id='assignee-suggestions']//ul[contains(@class,'suggested-projects')]//li//label[@title='"
									+ publicForm + "']//input"));
			} else {

				if (!isSelected(By
						.xpath(".//div[@id='assignee-suggestions']//ul[contains(@class,'suggested-projects')]//li//label[@title='"
								+ privateForm + "']//input")))
					clickElementAndWait(By
							.xpath(".//div[@id='assignee-suggestions']//ul[contains(@class,'suggested-projects')]//li//label[@title='"
									+ privateForm + "']//input"));
			}
			clickElementAndWait(GlobalPageElements.lnk_FilterClose);
			List<WebElement> formAccessCheckList = findElements(ProjectsTab.css_FormPermissionFormAccessCheckList);
			for (WebElement checkbox : formAccessCheckList)
				if (!checkbox.getAttribute("class").contains("checked"))
					checkbox.click();
		}
	}

	public void clickOnSaveAndCancel() {
		clickElementAndWait(ProjectsTab.btn_ManageRolesFormPopupSaveButton);
		if (!permissionFlag)
			clickElementAndWait(ProjectsTab.btn_PopMessageFormWebpageOkButton);
		clickElementAndWaitForElement(ProjectsTab.lnk_PopManageRolesFormPermissionLHPanelLink,
				ProjectsTab.btn_ManageRolesPopupCancelButton);
		clickElementAndWait(ProjectsTab.btn_ManageRolesPopupCancelButton);
	}

	public void verifyPublicFormDisabled() {
		clickElementWithText(globalProjectTitle);
		collectionDataMap.put("public form folder", publicFormFolder);
		collectionDataMap.put("public form", publicForm);
		AutomationAssert.verifyTrue("\nERROR: Folder " + publicFormFolder + " should not be displayed",
				!isDisplayedElementWithText(publicFormFolder));
		AutomationAssert.verifyTrue("\nERROR: Form " + publicForm + " should not be displayed",
				!isDisplayedElementWithText(publicForm));
	}

	public void verifyPrivateFormEnabled() {
		clickElementWithText(globalProjectTitle);
		collectionDataMap.put("private form folder", privateFormFolder);
		collectionDataMap.put("private form", privateForm);
		AutomationAssert.verifyTrue("\nERROR: Folder " + privateFormFolder + "  should be displayed",
				isDisplayedElementWithText(privateFormFolder));
		clickElementWithText(privateFormFolder);
		AutomationAssert.verifyTrue("\nERROR: Form " + privateForm + "  should be displayed",
				isDisplayedElementWithText(privateForm));
		clickElementWithText(privateForm);
		AutomationAssert.verifyTrue("\nERROR: Element " + ProjectFormsTab.lbl_ProjectFormsTabFormsCount.toString()
				+ " should be displayed", isDisplayed(ProjectFormsTab.lbl_ProjectFormsTabFormsCount));
		List<WebElement> formList = findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList);
		Assert.assertEquals(getTotalFormsCount(), Integer.toString(formList.size()));
	}

	public void unAssignedPermissionInToForms(String noAccess, String viewAllOrgs) {
		String[] accessPermissionArray = { noAccess, viewAllOrgs };
		clickElementAndWait(ProjectsTab.btn_FormPermissionRoleNameFilterButton);
		isClearDisplayed();
		sendKeys(ProjectsTab.txt_PopManageRolesSearchFilter, roleName);
		clickElementAndWaitForElement(ProjectsTab.chk_SelectedRoleCheckbox, GlobalPageElements.lnk_FilterClose);
		clickElementAndWait(GlobalPageElements.lnk_FilterClose);

		for (String accessPermission : accessPermissionArray) {
			clickElementAndWait(ProjectsTab.btn_RoleFormPermissionFilterButton);
			isClearDisplayed();
			List<WebElement> formPermissionList = findElements(ProjectsTab.css_RoleFormAccessPermissionLabelList);
			index = javaUtils.resetIndex(index, 1);
			for (WebElement permission : formPermissionList) {
				if (permission.getAttribute("title").contains(accessPermission)) {
					clickElementAndWait(By
							.xpath(".//div[@id='assignee-suggestions']//ul[contains(@class,'all-suggested-projects')]//li["
									+ index + "]//label[@title]//input"));
					break;
				}
				index++;
			}
			clickElementAndWaitForElement(GlobalPageElements.lnk_FilterClose,
					ProjectsTab.btn_FormPermissionFormNameFilterButton);
			clickElementAndWait(ProjectsTab.btn_FormPermissionFormNameFilterButton);
			isClearDisplayed();
			if (accessPermission.contains(AdoddleCommonStringPool.NO_ACCESS)) {

				if (!isSelected(By
						.xpath(".//div[@id='assignee-suggestions']//ul[contains(@class,'suggested-projects')]//li//label[@title='"
								+ publicForm + "']//input")))
					clickElementAndWait(By
							.xpath(".//div[@id='assignee-suggestions']//ul[contains(@class,'suggested-projects')]//li//label[@title='"
									+ publicForm + "']//input"));
			} else {

				if (!isSelected(By
						.xpath(".//div[@id='assignee-suggestions']//ul[contains(@class,'suggested-projects')]//li//label[@title='"
								+ privateForm + "']//input")))
					clickElementAndWait(By
							.xpath(".//div[@id='assignee-suggestions']//ul[contains(@class,'suggested-projects')]//li//label[@title='"
									+ privateForm + "']//input"));
			}
			clickElementAndWait(GlobalPageElements.lnk_FilterClose);
			List<WebElement> formAccessCheckList = findElements(ProjectsTab.css_FormPermissionFormAccessCheckList);
			for (WebElement checkbox : formAccessCheckList)
				if (checkbox.getAttribute("class").contains("checked"))
					checkbox.click();
		}
		permissionFlag = true;
	}

	public void verifyPrivateFormListingDisabled() {
		clickElementWithText(globalProjectTitle);
		clickElementWithText(privateFormFolder);
		clickElementWithText(privateForm);
		Assert.assertTrue(!isDisplayed(ProjectFormsTab.lbl_ProjectFormsTabFormsCount));
		Assert.assertTrue(!isDisplayed(ProjectFormsTab.css_ProjectFormsTabFormTitleList));
	}

	public void verifyPublicFormEnabled() {
		clickElementWithText(globalProjectTitle);
		Assert.assertTrue(isDisplayedElementWithText(publicFormFolder));
		clickElementWithText(publicFormFolder);
		Assert.assertTrue(isDisplayedElementWithText(publicForm));
		clickElementWithText(publicForm);
		Assert.assertTrue(isDisplayed(ProjectFormsTab.lbl_ProjectFormsTabFormsCount));
		List<WebElement> formList = findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList);
		Assert.assertEquals(getTotalFormsCount(), Integer.toString(formList.size()));
	}

	public void deactivateUserIntoCreatedRole() {
		searchProjects(CreateEditRoleScripts.projectName);
		contextClickWithLink(CreateEditRoleScripts.projectName);
		clickContextMenuOptionWithText(AdoddleCommonStringPool.OPTION_EDIT);
		clickElementWithText(AdoddleCommonStringPool.ROLE_USER_ACCESS);
		sendKeys(ProjectsTab.txt_CreateRolesSearchFilter, roleName);
		clickElementAndWaitForElement(ProjectsTab.lnk_FirstAssignedUserRemoveLink,
				ProjectsTab.btn_PopWarningContinueButton);
		clickElementAndWaitForElement(ProjectsTab.btn_PopWarningContinueButton,
				ProjectsTab.btn_ManageRolesTabSaveButton);
		clickElementAndWait(ProjectsTab.btn_ManageRolesTabSaveButton);
	}

	private String getTotalFormsCount() {
		return getElementText(ProjectFormsTab.lbl_ProjectFormsTabFormsCount).split("-")[1].trim().split(" of")[0]
				.trim();
	}

	private void isClearDisplayed() {
		if (isDisplayed(GlobalPageElements.lnk_FilterProjectClearAll)) {
			mouseHover(ProjectsTab.lnk_PopRolePrivileges);
			clickElementAndWaitForElement(GlobalPageElements.lnk_FilterProjectClearAll,
					GlobalPageElements.lnk_FilterProjectDisableClear);
		} else
			mouseHover(ProjectsTab.lnk_PopRolePrivileges);
	}
}