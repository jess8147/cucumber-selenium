package org.asite.automation.adoddle.p2.scripts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CopyFolderStructure extends AdoddleCommonAppMethods {

	private static String destinationFolder, loggedInUser;
	private List<WebElement> folderStructureList = new ArrayList<WebElement>();
	private List<WebElement> folderPermissions = new ArrayList<WebElement>();
	private List<WebElement> folderPermissionList = new ArrayList<WebElement>();
	private List<String> folderList = new ArrayList<String>(Arrays.asList("AutomationTestParent_Folder",
			"AutoTestSubFolder1", "AutoTestSubFolder2", "AutoTestSubFolder3", "AutoTestSubFolder4"));
	private static List<String> folderCleanUpList = new ArrayList<String>();
	public static Logger log = AutomationLogger.getInstance().getLogger(CopyFolderStructure.class);

	public void focusWorkspaceAndSelectOption(String workspace) {

		contextClickWithText(workspace);
		waitUntilElementIsDisplayed(FilesTab.opt_ProjectContextClickNew);
		mouseHoverAndClickElement(FilesTab.opt_ProjectContextClickNew, FilesTab.opt_ProjectContextClickFolder);
		waitUntilElementIsDisplayed(FilesTab.txt_PopCreateFolderFolderName);
		loggedInUser = findElement(LandingPage.ele_LoggedInUserProfile).getAttribute("title").split(", ")[0];

	}

	public void createTestFolder(String parentFolderName) throws InterruptedException {

		destinationFolder = parentFolderName + epoch;
		folderCleanUpList.add(destinationFolder);
		collectionDataMap.put("Destination Folder: ", destinationFolder);
		sendKeys(FilesTab.txt_PopCreateFolderFolderName, destinationFolder);
		clickElementAndWait(FilesTab.btn_PopCreateFolderCreate);
		waitForElementWithText(destinationFolder);
		Assert.assertTrue(isDisplayedElementWithText(destinationFolder));

	}

	public void contextClickAndSelectOption(String parentFolder) {
		contextClickWithText(parentFolder);
		clickElementAndWait(FilesTab.opt_ProjectFolderContextClickCopy);
		mouseHoverAndClickElement(FilesTab.opt_ProjectContextClickCopyFolderStructure,
				FilesTab.opt_ProjectContextClickCopyFolderStructure);

	}

	public void selectMenuOption() {
		log.info("Covered in <contextClickAndSelectOption> Definiton");
	}

	public void selectHierarchyFolderChild(String childFolder) {

		boolean flag = false;
		folderStructureList = findElements(FilesTab.pop_CopyFolderList);

		for (WebElement web : folderStructureList) {

			String childFolderExpandLink = web.findElement(By.cssSelector("a")).getAttribute("class");
			WebElement folderCheckbox = web.findElement(FilesTab.chk_CopyFolderCheckbox);
			String folderTittle = web.findElement(FilesTab.css_CopyFolderTitle).getText();

			if (!folderTittle.contains("AutoTestSubFolder4")) {

				if (childFolderExpandLink.contains("childNotRendered") && !folderCheckbox.isSelected()) {
					flag = true;
					web.findElement(FilesTab.pop_CopyFolderSubFolder).click();
					waitForCompletePageLoad();
				}
			}

			else {
				folderCheckbox.click();
				Assert.assertTrue("Failure while validation", verifyFolderSelectionPermutations(childFolder));
			}

		}

		if (flag == true)
			selectHierarchyFolderChild(childFolder);

		folderStructureList.clear();

	}

	public boolean verifyFolderSelectionPermutations(String folderName) {

		List<WebElement> foldersCheckbox = findElements(FilesTab.chk_CopyFolderCheckbox);
		try {
			for (WebElement web : foldersCheckbox) {

				if (folderName.contains("AutoTestSubFolder4") || folderName.contains("AutomationTestParent_Folder"))
					Assert.assertTrue("Folder not checked", web.isSelected());
				else
					Assert.assertTrue("Folder not checked", !web.isSelected());
			}
			return true;
		} catch (Exception e) {
			log.info("All folders not selected");
			return false;
		}

	}

	public void validatedCopyFolderStrutureHierarchy() {
		log.info("Covered in <contextClickAndSelectOption> Definiton");
	}

	public void selectFolderPermission() {

		folderPermissions = findElements(FilesTab.pop_CopyFolderPermissionList);
		for (WebElement web : folderPermissions) {
			if (!web.isSelected())
				web.click();
			else
				log.info("Already permissions assigned");
		}

		folderPermissions.clear();
	}

	public void resetAllFolderPermission() {

		folderPermissions = findElements(FilesTab.pop_CopyFolderPermissionList);

		for (WebElement web : folderPermissions) {
			if (web.isSelected())
				web.click();

			else
				log.info("Already permissions unassigned");
		}

	}

	public void resetFolderPermission(String assignedPermission) {

		if (assignedPermission.contains(AdoddleCommonStringPool.SECURITY_ROLE_DEFAULT)) {
			if (isSelected(FilesTab.pop_CopyFolderPermissionDefault))
				clickElement(FilesTab.pop_CopyFolderPermissionDefault);
		}

		else if (assignedPermission.contains(AdoddleCommonStringPool.SECURITY_ROLES)) {
			if (isSelected(FilesTab.pop_CopyFolderPermissionRoles))
				clickElement(FilesTab.pop_CopyFolderPermissionRoles);
		}

		else {
			if (isSelected(FilesTab.pop_CopyFolderPermissionUsers))
				clickElement(FilesTab.pop_CopyFolderPermissionUsers);
		}

	}

	public void copyFolderStructureInDirectory(String folderName) {

		clickElementAndWait(FilesTab.btn_CopyFolderStructureContinue);
		clickPopupElementWithText(destinationFolder);
		clickElementAndWait(FilesTab.btn_DestinationFolderContinue);
		waitUntilElementIsDisplayed(FilesTab.btn_CopyFolderStructureOkay);
		clickElementAndWait(FilesTab.btn_CopyFolderStructureOkay);

	}

	public void validateCopiedFolderStructure() {

		clickElementWithText(destinationFolder);
		for (String folder : folderList) {

			clickElementByText(folder);
			clickElementAndWaitForElement(FilesTab.opt_ProjectFolderContextClickEditFolder,
					FilesTab.chk_PopEditFolderDeactivate);
			validateInheritedDirectoriesRolesAndPermission();
			clickElementAndWait(FilesTab.btn_EditFolderCancel);
			waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
			waitForCompletePageLoad();

		}

	}

	public void validateInheritedDirectoriesRolesAndPermission() {

		Boolean flag = false;

		folderPermissionList = findElements(FilesTab.pop_EditFolderSecurityAttributesList);
		for (WebElement permission : folderPermissionList) {

			if (permission.getText().contains(AdoddleCommonStringPool.SECURITY_ROLE_DEFAULT))
				Assert.assertTrue("Expected Folder permission",
						permission.getText().contains(AdoddleCommonStringPool.PERMISSION_DEFAULT_NO_ACCESS));

			else if (permission.getText().contains(loggedInUser)) {
				Assert.assertTrue("Expected Folder permission",
						permission.getText().contains(AdoddleCommonStringPool.PERMISSION_ADMIN));
				flag = true;
			}

			else if (permission.getText().contains(ResourceHandler.loadProperty("test.user.pa.bloggs.name"))
					|| permission.getText().contains(ResourceHandler.loadProperty("test.user.tc.bloggs.name"))
					|| permission.getText().contains(ResourceHandler.loadProperty("test.user.rfi.bloggs.name"))
					|| permission.getText().contains(ResourceHandler.loadProperty("test.user.rfi.builder.name"))
					|| permission.getText().contains(ResourceHandler.loadProperty("test.user.pa.builder.name"))) {

				if (permission.getText().contains(ResourceHandler.loadProperty("test.user.pa.bloggs.name")))
					Assert.assertTrue("Expected Folder permission " + AdoddleCommonStringPool.PERMISSION_ADMIN,
							permission.getText().contains(AdoddleCommonStringPool.PERMISSION_ADMIN));

				else if (permission.getText().contains(ResourceHandler.loadProperty("test.user.tc.bloggs.name")))
					Assert.assertTrue("Expected Folder permission " + AdoddleCommonStringPool.PERMISSION_VIEW,
							permission.getText().contains(AdoddleCommonStringPool.PERMISSION_VIEW));

				else if (permission.getText().contains(ResourceHandler.loadProperty("test.user.rfi.bloggs.name")))
					Assert.assertTrue("Expected Folder permission "
							+ AdoddleCommonStringPool.PERMISSION_DEFAULT_NO_ACCESS,
							permission.getText().contains(AdoddleCommonStringPool.PERMISSION_DEFAULT_NO_ACCESS));

				else if (permission.getText().contains(ResourceHandler.loadProperty("test.user.rfi.builder.name")))
					Assert.assertTrue("Expected Folder permission "
							+ AdoddleCommonStringPool.PERMISSION_PUBLISH_AND_LINK,
							permission.getText().contains(AdoddleCommonStringPool.PERMISSION_PUBLISH_AND_LINK));

				else
					Assert.assertTrue("Expected Folder permission " + AdoddleCommonStringPool.PERMISSION_VIEW_AND_LINK,
							permission.getText().contains(AdoddleCommonStringPool.PERMISSION_VIEW_AND_LINK));

				flag = true;

			}

			else if (permission.getText().contains(AdoddleCommonStringPool.SECURITY_ROLE_WORKSPACEADMIN)
					|| permission.getText()
							.contains(AdoddleCommonStringPool.SECURITY_ROLE_AUTOMATION_CANCEL_INVITATION)
					|| permission.getText().contains(
							AdoddleCommonStringPool.SECURITY_ROLE_AUTOMATION_DECLINE_INVITATION)
					|| permission.getText().contains(AdoddleCommonStringPool.SECURITY_ROLE_TC_TEST_ORG_ROLE)) {

				if (permission.getText().contains(AdoddleCommonStringPool.SECURITY_ROLE_WORKSPACEADMIN))
					Assert.assertTrue("Expected Folder permission " + AdoddleCommonStringPool.PERMISSION_VIEW,
							permission.getText().contains(AdoddleCommonStringPool.PERMISSION_VIEW));

				else if (permission.getText().contains(
						AdoddleCommonStringPool.SECURITY_ROLE_AUTOMATION_CANCEL_INVITATION))
					Assert.assertTrue("Expected Folder permission " + AdoddleCommonStringPool.PERMISSION_UPLOAD,
							permission.getText().contains(AdoddleCommonStringPool.PERMISSION_UPLOAD));

				else if (permission.getText().contains(
						AdoddleCommonStringPool.SECURITY_ROLE_AUTOMATION_DECLINE_INVITATION))
					Assert.assertTrue("Expected Folder permission " + AdoddleCommonStringPool.PERMISSION_VIEW_AND_LINK,
							permission.getText().contains(AdoddleCommonStringPool.PERMISSION_VIEW_AND_LINK));

				else
					Assert.assertTrue("Expected Folder permission " + AdoddleCommonStringPool.PERMISSION_VIEW,
							permission.getText().contains(AdoddleCommonStringPool.PERMISSION_VIEW));

				flag = true;
			}

			else
				log.info("Failure while validating Folder Permissions");

		}

		Assert.assertTrue("Failure While Validating Folder ACL", flag);
		folderPermissionList.clear();

	}

	public void validateAndSelectChildFolder(String folderName) {

		folderStructureList = findElements(FilesTab.pop_CopyFolderList);

		for (WebElement web : folderStructureList) {

			WebElement folderCheckbox = web.findElement(FilesTab.chk_CopyFolderCheckbox);
			String folderTittle = web.findElement(FilesTab.css_CopyFolderTitle).getText();

			if (folderTittle.contains("AutomationTestParent_Folder")) {

				if (!folderCheckbox.isSelected()) {
					folderCheckbox.click();
					Assert.assertTrue("Failure while validation", verifyFolderSelectionPermutations(folderName));

				}
			}

		}

		folderStructureList.clear();
		clickElementAndWait(FilesTab.btn_CopyFolderStructureContinue);

	}

	public void clickButtonAndValidateWorkspace(String workspace) {

		boolean flag = false;

		List<WebElement> workspaceAndFolderList = findElements(FilesTab.pop_CopyFolderDestinationFolderList);

		for (WebElement str : workspaceAndFolderList) {

			if (str.getAttribute("title").contains(workspace)) {
				flag = true;
				Assert.assertTrue("Project is enbaled", str.getAttribute("title").contains(workspace));
			}

			else if (str.getAttribute("title").contains("PlaceHolders")) {
				flag = true;
				Assert.assertTrue("Public Folder is enbaled", str.getAttribute("title").contains("PlaceHolders"));
			}

			else
				log.info("Not expected Tree element");
		}

		Assert.assertTrue("Failure while validation " + workspace + "Should grayed out", flag);

	}

	public void dismissPopupCopyFolderStructure() {
		clickElementAndWait(FilesTab.btn_DestinationFolderCancel);
		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		waitForCompletePageLoad();
	}

	public void cleanUpOperation() {
		try {
			for (String folder : folderCleanUpList)
				deactivateFolder(folder);
		} catch (Exception e) {

			log.info("Failure While Performing Clean Up Operations");
		}

	}

	public void clickElementByText(String textElement) {

		contextClick((By
				.xpath(".//div[contains(@class,'selected')]//following::div[@style='display: block;']//span[text()='"
						+ textElement + "']")));
	}
}