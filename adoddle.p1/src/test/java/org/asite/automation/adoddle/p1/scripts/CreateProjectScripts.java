package org.asite.automation.adoddle.p1.scripts;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.lib.AdoddleCommonAppMethods.EnumList.DashboardLeftPanel;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.junit.Assert;

public class CreateProjectScripts extends AdoddleCommonAppMethods {

	private String			projectTitle	= "AutomaticTest_Project", editSuffix = "_Edited";
	public static Logger	log				= AutomationLogger.getInstance().getLogger(CreateProjectScripts.class);

	public void dashboardLeftMenu(DashboardLeftPanel leftPanel) throws InterruptedException {
		String leftMenu = leftPanel.toString().replace('_', ' ');
		Assert.assertTrue(isDisplayedLinkWithText(leftMenu));
	}

	public void verifyAddProject(String expectedText) {
		Assert.assertTrue(isDisplayed(ProjectsTab.lnk_AddProject));
		verifyElementText(ProjectsTab.lnk_AddProject, expectedText);
	}

	public void clickOnAddProject() throws InterruptedException {
		clickElementAndWait(ProjectsTab.lnk_AddProject);
	}

	public void verifyCreateProjectPopup(String expectedText) {
		verifyPopUpWithText(expectedText);
	}

	public void editProject() throws InterruptedException {
		waitForCompletePageLoad();
		sendKeys(ProjectsTab.txt_PopCreateProjectProjectName, projectTitle + editSuffix + epoch);
	}

	public void statusDropdownSelect() throws InterruptedException {
		waitUntilDropdownContainsValue(ProjectsTab.drp_PopEditProjectStatus, AdoddleCommonStringPool.STATUS_CLOSED, 120);
		selectByVisibleText(ProjectsTab.drp_PopEditProjectStatus, AdoddleCommonStringPool.STATUS_CLOSED);
	}

	public void clickOnEditSave() throws InterruptedException {
		clickElementAndWaitUntilElementInvisible(ProjectsTab.btn_PopEditProjectEdit, GlobalPageElements.ele_overLayProcess);
	}

	public void verifyChangedProjectName() throws InterruptedException {
		searchProjects(projectTitle + editSuffix + epoch);
		Assert.assertEquals(getElementText(ProjectsTab.lnk_ProjectsFirstProject), projectTitle + editSuffix + epoch);
	}

	public void createProjectDetails() throws InterruptedException {
		sendKeys(ProjectsTab.txt_PopCreateProjectProjectName, projectTitle + epoch);
		if (getCount(ProjectsTab.txt_PopCreateProjectClientInput) > 0) {
			log.info("found client input");

			if (ResourceHandler.getPropertyValue("application.url").contains("www.asite.com") || ResourceHandler.getPropertyValue("application.url").contains("wwwqa.asite.com"))
				getWebDriver().findElement(ProjectsTab.txt_PopCreateProjectClientInput).sendKeys(System.getProperty("primary.user.org").replace(".", ""));
			else
				getWebDriver().findElement(ProjectsTab.txt_PopCreateProjectClientInput).sendKeys(System.getProperty("primary.user.org"));

			waitForCompletePageLoad();
			clickElementAndWait(ProjectsTab.sel_CloneProjectClientNameFirstResult);
		}
	}

	public void geographyDropdownSelect(String DC) throws InterruptedException {
		selectByVisibleText(ProjectsTab.drp_PopCreateProjectGeography, DC);
	}

	public void clickOnCreate() throws InterruptedException {
		clickElementAndWait(ProjectsTab.btn_PopCreateProjectSave);
	}

	public void clickOnEditProject() throws InterruptedException {
		reloadPage();
		searchProjects(projectTitle + epoch.trim());
		contextClickWithLink(projectTitle + epoch.trim());
		clickContextMenuOption(AdoddleCommonStringPool.OPTION_EDIT, AdoddleCommonStringPool.OPTION_PROJECT);
	}

	public void clickOnEditedProject() throws InterruptedException {
		contextClickWithLink(projectTitle + editSuffix + epoch.trim());
		clickContextMenuOption(AdoddleCommonStringPool.OPTION_EDIT, AdoddleCommonStringPool.OPTION_PROJECT);
	}

	public void verifyProjectName() throws InterruptedException {
		searchProjects(projectTitle + epoch);
		collectionDataMap.put("Project Title", projectTitle+epoch);
		Assert.assertEquals(getElementText(ProjectsTab.lnk_ProjectsFirstProject), projectTitle + epoch);
	}

	public void verifyProjectClosed() throws InterruptedException {
		searchProjects(projectTitle + editSuffix + epoch);
		collectionDataMap.put("Edited Project Title", projectTitle + editSuffix + epoch);
		Assert.assertEquals(0, getCount(ProjectsTab.css_ProjectRowsCount));
	}

	public void loggerInfo() {
		log.info("SKIP STEPS : <======IN QA \"AU01\" IS NOT ALLOWED======>");
	}
}
