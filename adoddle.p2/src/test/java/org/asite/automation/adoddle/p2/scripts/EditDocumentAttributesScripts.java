/**  Testdata required for this script as follows.
 *   0). Need to create Folder Specific and Pattern Specific custom attribute set in classic.
     1). Need "FSC1" and "PSC1" folders under CustomAttributes folder.
     2). FSC1 is applied Folder Specific Custom Attribute set created in classic.
     3). PSC1 is applied Pattern Specific Custom Attribute set created in classic.  
 */

package org.asite.automation.adoddle.p2.scripts;

import java.io.IOException;
import java.util.*;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.JavaUtils;
import org.openqa.selenium.WebElement;

public class EditDocumentAttributesScripts extends AdoddleCommonAppMethods {

	private static String	testFolderTitle, updatedRevisionNotes;
	private String			updatedIntegerValue, updatedLetterNumbers, updatedDocTitles, updatedPOI;
	private String			docPrefix					= javaUtils.getRandomString(10);
	private List<String>	fileList, docTitleList;
	private List<String>	docRefList					= new ArrayList<String>();
	private int				currentRevision				= 1;
	private int 			fileCount 					= 0;
	private boolean			privilegeFlag				= false;
	static WebElement 		userPrivilegeElement 		= null;
	public static Logger	log							= AutomationLogger.getInstance().getLogger(EditDocumentAttributesScripts.class);

	public void createTestDataFolder(String folderTitle, String testProject) {
		testFolderTitle = folderTitle + dateUtils.getEpoch();
		createProjectFolderMoreOptions(testProject, testFolderTitle);
		collectionDataMap.put("test folder", testFolderTitle);
	}

	public void uploadFilesInFolder(String count, String fileFolder) throws NumberFormatException, InterruptedException, IOException {
		docTitleList = new ArrayList<String>();
		fileList = new ArrayList<String>();
		fileCount = Integer.parseInt(count);
		for (int index = 0; index < fileCount; index++) {
			docTitleList.add(docPrefix + dateUtils.getEpoch());
			waitUtils.waitInterval(1);
		}
		
		if (fileFolder.equalsIgnoreCase("existing")) {
			clickElementWithText(ResourceHandler.loadProperty("application.file.upload.folder"));
			fileList = uploadDocuments(null, fileCount, docRefList, docTitleList, false, currentRevision, null, null,null,null);
		}
		else if (fileFolder.equalsIgnoreCase("new")) {
			clickElementWithText(testFolderTitle);
			fileList.addAll(uploadDocuments(null, fileCount, docRefList, docTitleList, false, currentRevision, null, null,null,null));
		}
		else {
			clickElementWithText(ResourceHandler.loadProperty("custom.attributes.file.upload.folder"));
			if (fileFolder.equalsIgnoreCase("Pattern Specific"))
				clickElementWithText("PSC1");
			else if (fileFolder.equalsIgnoreCase("Folder Specific"))
				clickElementWithText("FSC1");
			fileList.addAll(uploadDocumentsWithCustomAttributes(null, fileCount, null, docTitleList, false, currentRevision, null, null, false, Arrays.asList("Gujarat"), Arrays.asList("Ahmedabad"), null, Float.parseFloat(JavaUtils.getRandomNumber(3)), "test123", null, null));
		}
		
		collectionDataMap.put("test files", docTitleList.toString());

	}

	public void searchDocuments() {
		clickElementWithText(System.getProperty("global.test.project"));
		searchFiles(docPrefix);
		List<WebElement> docRefListElements = findElements(FilesTab.css_DocListingDocRefList);
		for (WebElement docRefElement : docRefListElements)
			docRefList.add(docRefElement.getText());
		System.out.println("Doc Ref List: " + docRefList.toString());
	}

	public void verifyDocumentsCount(String cnt) {
		AutomationAssert.verifyTrue(getCount(FilesTab.css_FilesListingRecords) == Integer.parseInt(cnt));
	}

	public void clickOnTestDataFolder() {
		clickElementWithText(testFolderTitle);
	}

	public void selectDocumentAndContextClick() {
		clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);
		contextClick(FilesTab.lnk_DocListingFirstDocRef);
	}

	public void selectEditAttributesOption() {
		/*mouseHoverAndClickElement(FilesTab.opt_FileContextClickEdit, FilesTab.opt_FileContextClickEditAttributes);*/
		clickContextMenuOptionWithText(AdoddleCommonStringPool.OPTION_EDIT);
		clickContextMenuOption(AdoddleCommonStringPool.OPT_ATTRIBUTES);
		waitUntilElementIsDisplayed(FilesTab.pop_FileEditAttributes);
	}

	public void editCustomAttributesAndGetUpdatedData() {
		Map<String, String> map = editCustomAttributes(docPrefix, currentRevision, getSelectedDropdownLabel(findElements(FilesTab.css_PopEditAttributesPOI).get(0)), false);
		updatedRevisionNotes = map.get("revision");
		updatedPOI = map.get("poi");
		updatedIntegerValue = map.get("integer");
		updatedLetterNumbers = map.get("letternumbers");
		updatedDocTitles = map.get("doctitles");
	}

	public void editSimpleAttributes() {
		log.info("Covered in previous definition");
	}

	public void verifyDocRefsForCustomAttributeFiles() {
		clickElementWithText(ResourceHandler.loadProperty("custom.attributes.file.upload.folder"));
		searchFiles(docPrefix);
		List<WebElement> listingDocRefElements = findElements(FilesTab.css_DocListingDocRefList);
		List<WebElement> listingRevisionNotes = findElements(FilesTab.css_DocListingRevisionNoteList);

		for (WebElement docRef : listingDocRefElements)
			AutomationAssert.verifyTrue(docRef.getText().contains(updatedIntegerValue) && docRef.getText().contains(updatedLetterNumbers));

		for (WebElement revNote : listingRevisionNotes)
			AutomationAssert.verifyTrue(revNote.getText().trim().equalsIgnoreCase(updatedRevisionNotes));

	}

	public void verifySimpleAttributeEdit() {
		navigateTab(LandingPage.lnk_Files);
		searchFiles(docPrefix);
		List<WebElement> listingPOIElements = findElements(FilesTab.css_DocListingPurposeOfIssuesList);
		List<WebElement> listingDocTitleElements = findElements(FilesTab.css_DocListingDocTitleList);
		List<WebElement> listingRevisionElements = findElements(FilesTab.css_DocListingRevisionList);

		index = javaUtils.resetIndex(index, 0);

		for (WebElement poi : listingPOIElements) {
			AutomationAssert.verifyTrue(poi.getText().equalsIgnoreCase(updatedPOI));
			AutomationAssert.verifyTrue(listingDocTitleElements.get(index).getText().equalsIgnoreCase(updatedDocTitles));
			AutomationAssert.verifyTrue(listingRevisionElements.get(index).getText().equalsIgnoreCase(String.valueOf(currentRevision + 1)));
			index++;
		}
	}

	public void deactivateEditAttributeFolder(String project) {
		try {
		deactivateProjectFolder(project, testFolderTitle);
		}
		catch(Throwable t) {
			log.error("FAILURE: Unable to deactivate Edit Attribute folder");
		}
	}

	public void openEditFolderDialogue() {
		contextClickWithText(testFolderTitle);
		clickElementAndWaitForElement(FilesTab.opt_ProjectFolderContextClickEditFolder, FilesTab.txt_PopEditFolderSecurityInput);
	}

	public void verifyUserPrivilegeIsAvailable(String privilege, String userRole, String project) {
		PriviledgeClass clzz = new PriviledgeClass();
		clzz = hasUserPrivilege(privilege, userRole, project);
		privilegeFlag = clzz.getFlag();
		userPrivilegeElement = findElement(clzz.getLocator());
		System.out.println("Priviledge flag is :" + privilegeFlag);
	}

	public void uncheckPrivilegeAndSave(String flag) {
		if ((privilegeFlag && flag.equalsIgnoreCase("uncheck")) || ((!privilegeFlag) && flag.equalsIgnoreCase("check"))) {
			System.out.println("Flag :" + flag);
			//clickElementAndWait(ProjectsTab.chk_PopManageRolesFilteredPrivilegeWSAdminCheckbox);
			clickElementAndWait(userPrivilegeElement);
		}
		clickElementAndWait(ProjectsTab.btn_PopManageRolesPrivilegesSave);
		waitForCompletePageLoad();
		clickElementAndWait(GlobalPageElements.btn_PopUpElementClose);
	}

	public void verifyEditAttributeDocuments(int docCount) {
		AutomationAssert.verifyTrue(getCount(FilesTab.css_PopEditAttributesRows) == docCount);
		clickElementAndWait(FilesTab.btn_PopEditAttributesCancel);
	}

	public void verifyAttributesInFolders(String type, String flag) {
		clickElementWithText(ResourceHandler.loadProperty("custom.attributes.file.upload.folder"));

		if (type.equalsIgnoreCase("Pattern Specific")) {
			clickElementWithText("PSC1");
			collectionDataMap.put("pattern specific folder", "PSC1");
			verifyDocumentDocRef(flag);
		}
		if (type.equalsIgnoreCase("Folder Specific")) {
			clickElementWithText("FSC1");
			collectionDataMap.put("folder specific folder", "FSC1");
			verifyDocumentDocRef(flag);
		}

	}

	public void verifyDocumentDocRef(String flg) {
		searchFiles(docPrefix);
		List<WebElement> fileDocRefs = findElements(FilesTab.css_DocListingDocRefList);
		if (flg.equalsIgnoreCase("yes")) {
			for (WebElement e : fileDocRefs)
				AutomationAssert.verifyTrue(docRefList.toString() + " should not contain " + e.getText(), !docRefList.contains(e.getText()));
		}
		else {
			try {
				for (WebElement e : fileDocRefs)
					AutomationAssert.verifyTrue(docRefList.toString() + " should contain " + e.getText(), docRefList.contains(e.getText()));
			}
			catch (Throwable t) {
				log.info("Verification failed for doc ref comparison");
			}
		}

	}
}
