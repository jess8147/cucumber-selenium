/**  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.junit.Assert;
import org.openqa.selenium.Keys;

public class CreateFieldEnabledProjectScripts extends AdoddleCommonAppMethods {

	public static Logger	log	= AutomationLogger.getInstance().getLogger(CreateFieldEnabledProjectScripts.class);

	public void verifyAddProjectButton() {
		Assert.assertTrue(isDisplayed(ProjectsTab.lnk_ProjectsTabLHAddProject));
	}

	public void clickOnAddProjectButton() {
		clickElementAndWait(ProjectsTab.lnk_ProjectsTabLHAddProject);
	}

	public void enterProjectDetails(String geoGraphy) throws InterruptedException {
		sendKeys(ProjectsTab.txt_PopCreateProjectProjectName, "AutomationTestProject" + epoch);
		sendKeys(ProjectsTab.txt_PopCreateProjectProjectName, Keys.TAB);
		/*
		 * sendKeys(ProjectsTab.txt_PopCreateProjectDescription, javaUtils.getRandomString(25)); sendKeys(ProjectsTab.txt_PopCreateProjectDescription, Keys.TAB);
		 */
		selectByVisibleText(ProjectsTab.drp_PopCreateProjectGeography, geoGraphy);
	}

	public void selectCheckboxFieldEnabled(String fieldEnabled) {
		if (!isSelected(ProjectsTab.chk_PopCreateProjectFieldEnabled))
			clickElementAndWait(ProjectsTab.chk_PopCreateProjectFieldEnabled);
	}

	public void clickCreateProjectButton(String createButtonText) {
		clickElementAndWait(ProjectsTab.btn_PopCreateProjectSave);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
	}

	public void verifyProjectIsCreated() {
		searchProjects("AutomationTestProject" + epoch);
		Assert.assertTrue(getElementText(ProjectsTab.lnk_ProjectsTabFirstProjectName).equalsIgnoreCase("AutomationTestProject" + epoch));

	}

	public void contextClickOnCreatedProject() {
		contextClick(ProjectsTab.lnk_ProjectsTabFirstProjectName);
		waitUntilElementIsDisplayed(ProjectsTab.opt_ProjectContextClickEdit);

	}

	public void selectEditProjectOption() {
		mouseHoverAndClickElement(ProjectsTab.opt_ProjectContextClickEdit, ProjectsTab.opt_ProjectContextClickEditProject);
		waitUntilElementIsDisplayed(ProjectsTab.pop_ProjectsTabEditProject);
	}

	public void verifyFieldEnabledCheckbox(String fieldEnabled) {
		waitForCompletePageLoad();
		Assert.assertTrue(!isEnabled(ProjectsTab.chk_PopEditProjectFieldEnabled));
	}

	public void clickProjectNameLink() {
		clickElementAndWait(ProjectsTab.lnk_ProjectsTabFirstProjectName);
	}

	public void verifyFieldTabIsDisplayingProject() {
		try {
			Assert.assertTrue(isDisplayed(LandingPage.lnk_Field));
		}
		catch (Throwable t) {
			clickElementAndWaitForElement(LandingPage.btn_More, LandingPage.lnk_Field);
		}
		clickElementAndWait(LandingPage.lnk_Field);
		Assert.assertTrue(isDisplayedElementWithText("AutomationTestProject" + epoch));

	}

	public void closeFieldProject() {
		clickElementAndWait(LandingPage.lnk_Projects);
		searchProjects("AutomationTestProject" + epoch);
		contextClick(ProjectsTab.lnk_ProjectsTabFirstProjectName);
		waitUntilElementIsDisplayed(ProjectsTab.opt_ProjectContextClickEdit);
		mouseHoverAndClickElement(ProjectsTab.opt_ProjectContextClickEdit, ProjectsTab.opt_ProjectContextClickEditProject);
		waitUntilElementIsDisplayed(ProjectsTab.pop_ProjectsTabEditProject);
		waitForCompletePageLoad();
		selectByVisibleText(ProjectsTab.drp_EditProjectStatusDropdown, "Closed");
		clickElementAndWait(ProjectsTab.btn_PopEditProjectSave);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);

	}
}
