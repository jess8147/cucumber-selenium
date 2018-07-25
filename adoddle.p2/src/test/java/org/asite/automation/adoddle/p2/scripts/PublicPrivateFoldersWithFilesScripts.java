package org.asite.automation.adoddle.p2.scripts;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleLoginLocators.LoginPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class PublicPrivateFoldersWithFilesScripts extends AdoddleCommonAppMethods
{
	private String publicFolderA = "Public_Folder_A", publicFolderB = "Public_Folder_B",
			publicFolderC = "Public_Folder_C", publicFolderD = "Public_Folder_D", publicFolderE = "Public_Folder_E";
	private List<String> publicFileListA = new ArrayList<String>();
	private List<String> privateFileListA = new ArrayList<String>();
	private List<String> publicFileListB = new ArrayList<String>();
	private List<String> privateFileListB = new ArrayList<String>();
	private List<String> publicFileListC = new ArrayList<String>();
	private List<String> privateFileListC = new ArrayList<String>();
	private List<String> publicFileListD = new ArrayList<String>();
	private List<String> privateFileListD = new ArrayList<String>();
	private List<String> publicFileListE = new ArrayList<String>();
	private List<String> privateFileListE = new ArrayList<String>();
	
	private List<String> actualAccessableFileList = new ArrayList<String>();
	private List<String> expectedAccessableFileList = new ArrayList<String>();
	
	private boolean flag;
	private String firstWindow, secondWindow;
	private String copiedURL;
	
	/*public void setAndVerifyPublicPrivateFolder(String setReset, String folderList)
	{
		collectionDataMap.put("Public Private Folder List", folderList.toString());
		for (String folder : folderList.split(",")) {
			contextClickWithText(folder);
			clickContextMenuOptionWithText("Edit Folder");
			
			if(setReset.equalsIgnoreCase("set")) {
				if(folder.contains(publicFolderA) || folder.contains(publicFolderC) || folder.contains(publicFolderE)) {
					if(!isSelected(FilesTab.chk_PopEditFolderSetAsPublicCheckbox))
						clickElementAndWait(FilesTab.chk_PopEditFolderSetAsPublicCheckbox);
					if(!isSelected(FilesTab.chk_PopEditFolderPublicSubFoldersCheckbox))
						clickElementAndWait(FilesTab.chk_PopEditFolderPublicSubFoldersCheckbox);
				} else {
					if(isSelected(FilesTab.chk_PopEditFolderSetAsPublicCheckbox))
						clickElementAndWait(FilesTab.chk_PopEditFolderSetAsPublicCheckbox);
				}
			} else {
				if(isSelected(FilesTab.chk_PopEditFolderSetAsPublicCheckbox))
					clickElementAndWait(FilesTab.chk_PopEditFolderSetAsPublicCheckbox);
			}
			clickElementAndWait(FilesTab.btn_EditFolderUpdate);
			
			if(setReset.equalsIgnoreCase("set")) {
				if(folder.contains(publicFolderA) || folder.contains(publicFolderC) || folder.contains(publicFolderE))
					Assert.assertTrue(findElement(By.xpath(".//a[@title='"+folder+"']//span[not(text())][@class]")).getAttribute("class").contains("publicFolder"));
				else
					Assert.assertTrue(!findElement(By.xpath(".//a[@title='"+folder+"']//span[not(text())][@class]")).getAttribute("class").contains("publicFolder"));
			} else
				Assert.assertTrue(!findElement(By.xpath(".//a[@title='"+folder+"']//span[not(text())][@class]")).getAttribute("class").contains("publicFolder"));
		}
	}*/
	
	public void setAndVerifyPublicPrivateFolder(String setReset, String folderList)
	{
		collectionDataMap.put("Public Private Folder List", folderList.toString());
		for (String folder : folderList.split(",")) {
			clickElementWithText(folder);
			
			if(setReset.equalsIgnoreCase("set"))
			{
				if(folder.contains(publicFolderA) || folder.contains(publicFolderC) || folder.contains(publicFolderE)) {
					if(!findElement(By.xpath(".//a[@title='"+folder+"']//span[not(text())][@class]")).getAttribute("class").contains("publicFolder")) {
						
						contextClickWithText(folder);
						clickContextMenuOptionWithText("Edit Folder");
						if(!isSelected(FilesTab.chk_PopEditFolderSetAsPublicCheckbox))
							clickElementAndWait(FilesTab.chk_PopEditFolderSetAsPublicCheckbox);
						if(!isSelected(FilesTab.chk_PopEditFolderPublicSubFoldersCheckbox))
							clickElementAndWait(FilesTab.chk_PopEditFolderPublicSubFoldersCheckbox);
						clickElementAndWait(FilesTab.btn_EditFolderUpdate);
					} else
						log.info(": "+folder+" already Set as Public :");
					
				} else {
					
					if(findElement(By.xpath(".//a[@title='"+folder+"']//span[not(text())][@class]")).getAttribute("class").contains("publicFolder")) {
						
						contextClickWithText(folder);
						clickContextMenuOptionWithText("Edit Folder");
						if(isSelected(FilesTab.chk_PopEditFolderSetAsPublicCheckbox))
							clickElementAndWait(FilesTab.chk_PopEditFolderSetAsPublicCheckbox);
						clickElementAndWait(FilesTab.btn_EditFolderUpdate);
					} else
						log.info(": "+folder+" already Set as Private :");
				}
			}
			else
			{
				if(findElement(By.xpath(".//a[@title='"+folder+"']//span[not(text())][@class]")).getAttribute("class").contains("publicFolder")) {
					
					contextClickWithText(folder);
					clickContextMenuOptionWithText("Edit Folder");
					if(isSelected(FilesTab.chk_PopEditFolderSetAsPublicCheckbox))
						clickElementAndWait(FilesTab.chk_PopEditFolderSetAsPublicCheckbox);
					clickElementAndWait(FilesTab.btn_EditFolderUpdate);
				} else
					log.info(": "+folder+" already Set as Private :");
			}
			
			if(setReset.equalsIgnoreCase("set")) {
				if(folder.contains(publicFolderA) || folder.contains(publicFolderC) || folder.contains(publicFolderE))
					Assert.assertTrue(findElement(By.xpath(".//a[@title='"+folder+"']//span[not(text())][@class]")).getAttribute("class").contains("publicFolder"));
				else
					Assert.assertTrue(!findElement(By.xpath(".//a[@title='"+folder+"']//span[not(text())][@class]")).getAttribute("class").contains("publicFolder"));
			} else
				Assert.assertTrue(!findElement(By.xpath(".//a[@title='"+folder+"']//span[not(text())][@class]")).getAttribute("class").contains("publicFolder"));
		}
	}
	
	public void createFilter(String filter, String subFilter)
	{
		clickElementAndWait(By.xpath(".//ul//li[@listfilterkey='" + filter + "']//button[@filterkey='" + filter + "']"));
		waitUntilElementIsDisplayed(FilesTab.txt_SearchCreateFilterInput);

		for (WebElement filterVal : findElements(GlobalPageElements.css_FilterDropdownSuggestedTypeList)) {
			if (filterVal.getAttribute("title").equalsIgnoreCase(subFilter)) {
				filterVal.click();
				break;
			}
			else
				log.info("Miss-Match Filter : " + filterVal.getAttribute("title") + " :: " + subFilter);
		}
		if(isDisplayed(GlobalPageElements.css_FilterDropdownSuggestedTypeList))
			clickElementAndWait(By.xpath(".//ul//li[@listfilterkey='" + filter + "']//button[@filterkey='" + filter + "']"));
	}
	
	public void getPublicFoldersFilesAndCount(String publicFolderList)
	{
		createFilter("more", "Include Subfolders");
		createFilter("Include SubFolders", "Yes");
		
		for (String publicFolder : publicFolderList.split(",")) {
			clickElementWithText(publicFolder);
			
			for (WebElement fileName : findElements(FilesTab.css_NumberOfFiles)) {
				if(publicFolder.contains(publicFolderA)) {
					if(fileName.getText().contains("Public"))
						publicFileListA.add(fileName.getText());
					else
						privateFileListA.add(fileName.getText());
				}
				else if(publicFolder.contains(publicFolderB)) {
					if(fileName.getText().contains("Public"))
						publicFileListB.add(fileName.getText());
					else
						privateFileListB.add(fileName.getText());
				}
				else if(publicFolder.contains(publicFolderC)) {
					if(fileName.getText().contains("Public"))
						publicFileListC.add(fileName.getText());
					else
						privateFileListC.add(fileName.getText());
				}
				else if(publicFolder.contains(publicFolderD)) {
					if(fileName.getText().contains("Public"))
						publicFileListD.add(fileName.getText());
					else
						privateFileListD.add(fileName.getText());
				}
				else if(publicFolder.contains(publicFolderE)) {
					if(fileName.getText().contains("Public"))
						publicFileListE.add(fileName.getText());
					else
						privateFileListE.add(fileName.getText());
				}
			}
		}
		
		collectionDataMap.put("Public File List A", publicFileListA.toString());
		collectionDataMap.put("Public File List B", publicFileListB.toString());
		collectionDataMap.put("Public File List C", publicFileListC.toString());
		collectionDataMap.put("Public File List D", publicFileListD.toString());
		collectionDataMap.put("Public File List E", publicFileListE.toString());
		collectionDataMap.put("Private File List A", privateFileListA.toString());
		collectionDataMap.put("Private File List B", privateFileListB.toString());
		collectionDataMap.put("Private File List C", privateFileListC.toString());
		collectionDataMap.put("Private File List D", privateFileListD.toString());
		collectionDataMap.put("Private File List E", privateFileListE.toString());
		log.info("publicFileListA : "+publicFileListA +" , "+"privateFileListA : "+privateFileListA);
		log.info("publicFileListB : "+publicFileListB +" , "+"privateFileListB : "+privateFileListB);
		log.info("publicFileListC : "+publicFileListC +" , "+"privateFileListC : "+privateFileListC);
		log.info("publicFileListD : "+publicFileListD +" , "+"privateFileListD : "+privateFileListD);
		log.info("publicFileListE : "+publicFileListE +" , "+"privateFileListE : "+privateFileListE);
	}
	
	public void clickOnFolderAndSelectOption(String menuOption, String folderType)
	{
		if(folderType.contains("Parent")) {
			contextClickWithText(publicFolderA);
			flag = true;
		} else {
			clickElementWithText(publicFolderA);
			clickElementWithText(publicFolderB);
			contextClickWithText(publicFolderC);
			flag = false;
		}
		clickContextMenuOptionWithText(menuOption);
	}
	
	public void copyPublicLink() throws InterruptedException
	{
		clickElement(FilesTab.lnk_EditFolderClipIcon);
		waitUntilAlertIsPresent();
		
		Alert alert = getWebDriver().switchTo().alert();
		handleAlertWindowPopup("Copy");
		try {
			alert.dismiss();
		}
		catch (Throwable t) {
			log.error("Alert was not found");
		}
		clickElementAndWait(FilesTab.btn_EditFolderCancel);
		
		/*** Get CopiedURL ***/
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitUntilElementIsDisplayed(FilesTab.txt_FilesFilterInput);
		getWebDriver().findElement(FilesTab.txt_FilesFilterInput).clear();
		getWebDriver().findElement(FilesTab.txt_FilesFilterInput).sendKeys(Keys.CONTROL + "v");
		copiedURL = getWebDriver().findElement(FilesTab.txt_FilesFilterInput).getAttribute("value");
		log.info("copiedURL : "+copiedURL);
		getWebDriver().findElement(FilesTab.txt_FilesFilterInput).clear();
	}
	
	public void hitCopiedUrl()
	{
//		handleAlertWindowPopup("Paste");
		navigateURL(copiedURL);
		waitForCompletePageLoad();
	}
	
	public void openNewTabAndHitLoginUrl() throws InterruptedException
	{
		openedNewWindow(2);
		navigateURL(ResourceHandler.getPropertyValue("application.url"));
	}
	
	public void openedNewWindow(int numberOfWindows)
	{
		openNewTab();
		if(numberOfWindows == 2)
			firstWindow = getCurrentWindow();
		else if(numberOfWindows == 3)
			secondWindow = getCurrentWindow();
		waitForSwitchWindow(numberOfWindows);
		getWebDriver().switchTo().window(new ArrayList<String>(getWebDriver().getWindowHandles()).get(numberOfWindows-1));
		waitForCompletePageLoad();
	}
	
	public void closedOpenedWindow(int numberOfWindows)
	{
		closeCurrentWindow();
		if(numberOfWindows == 2)
			switchPreviousWindow(firstWindow);
		else if(numberOfWindows == 3)
			switchPreviousWindow(secondWindow);
		waitForCompletePageLoad();
	}
	
	public void verifyOpenPublicFolder(String projectName)
	{
		Assert.assertTrue(getElementText(FilesTab.lbl_PublicFolderText).contains(projectName));
	}
	
	@SuppressWarnings("unchecked")
	public void verifyPublicFolderFilesAndCount()
	{
		clickElementAndWaitForElement(GlobalPageElements.btn_ActiveTabSettingDropdownButton, GlobalPageElements.drp_ShowNumberOfListDropdown);
		selectByVisibleText(GlobalPageElements.drp_ShowNumberOfListDropdown, "250");
		if (isDisplayed(GlobalPageElements.btn_ActiveTabSettingDropdownCloseButton))
			clickElementAndWait(GlobalPageElements.btn_ActiveTabSettingDropdownCloseButton);
		waitUntilElementIsDisplayed(FilesTab.chk_MultiFilesSelectionCheckbox);
		
		actualAccessableFileList.clear();
		expectedAccessableFileList.clear();
		
		if(flag)
			expectedAccessableFileList.addAll(ListUtils.union(publicFileListA, privateFileListA));
		expectedAccessableFileList.addAll(ListUtils.union(publicFileListC, privateFileListC));
		expectedAccessableFileList.addAll(ListUtils.union(publicFileListE, privateFileListE));
		
		for (WebElement fileName : findElements(FilesTab.css_NumberOfFiles))
			actualAccessableFileList.add(fileName.getText());
		
		Assert.assertTrue(actualAccessableFileList.size() == expectedAccessableFileList.size());
		Assert.assertTrue(actualAccessableFileList.containsAll(expectedAccessableFileList));
	}
	
	@SuppressWarnings("unchecked")
	public void verifySelectedFolderAndAccessibleFiles()
	{
		waitForCompletePageLoad();
		if(flag)
			Assert.assertTrue(findElement(FilesTab.ele_FilesTabSelectedFolderName).getAttribute("title").equalsIgnoreCase(publicFolderA));
		else
			Assert.assertTrue(findElement(FilesTab.ele_FilesTabSelectedFolderName).getAttribute("title").equalsIgnoreCase(publicFolderC));
		
		actualAccessableFileList.clear();
		expectedAccessableFileList.clear();
		
		List<String> notAccessableFileList = new ArrayList<String>();
		
		for (WebElement fileName : findElements(FilesTab.css_NumberOfFiles))
			actualAccessableFileList.add(fileName.getText());
		
		if(flag)
			expectedAccessableFileList.addAll(ListUtils.union(publicFileListA, publicFileListB));
		expectedAccessableFileList.addAll(ListUtils.union(publicFileListC, publicFileListD));
		expectedAccessableFileList.addAll(publicFileListE);
		
		notAccessableFileList.addAll(ListUtils.union(privateFileListA, privateFileListB));
		notAccessableFileList.addAll(ListUtils.union(privateFileListC, privateFileListD));
		notAccessableFileList.addAll(privateFileListE);
		
		Assert.assertTrue(actualAccessableFileList.size() == expectedAccessableFileList.size());
		Assert.assertTrue(actualAccessableFileList.containsAll(expectedAccessableFileList));
		Assert.assertTrue(!actualAccessableFileList.containsAll(notAccessableFileList));
	}
	
	public void verifyUnauthorisedAccessPage(String unauthorisedMsg)
	{
		waitUntilElementIsDisplayed(FilesTab.lbl_UnauthorisedAccessLabel);
		Assert.assertTrue(getElementText(FilesTab.lbl_UnauthorisedAccessLabel).contains(unauthorisedMsg));
	}
	
	@SuppressWarnings("unchecked")
	public void verifyAdminFolderAccessibleFiles()
	{
		waitForCompletePageLoad();
		Assert.assertTrue(findElement(FilesTab.ele_FilesTabSelectedFolderName).getAttribute("title").equalsIgnoreCase(publicFolderA));
		
		actualAccessableFileList.clear();
		expectedAccessableFileList.clear();
		
		for (WebElement fileName : findElements(FilesTab.css_NumberOfFiles))
			actualAccessableFileList.add(fileName.getText());
		expectedAccessableFileList.addAll(ListUtils.union(publicFileListA, privateFileListA));
		
		Assert.assertTrue(actualAccessableFileList.size() == expectedAccessableFileList.size());
		Assert.assertTrue(actualAccessableFileList.containsAll(expectedAccessableFileList));
	}
	
	public void login(String username, String password)
	{
		waitAndSwitchIframe(LoginPage.frm_Iframe);
		waitUntilElementIsDisplayed(LoginPage.txt_AsiteUsername);
		findElement(LoginPage.txt_AsiteUsername).clear();
		findElement(LoginPage.txt_AsiteUsername).sendKeys(username);
		findElement(LoginPage.txt_AsitePassword).clear();
		findElement(LoginPage.txt_AsitePassword).sendKeys(password);
		waitUntilElementIsClickable(LoginPage.btn_AsiteLogin);
		clickElementAndWait(LoginPage.btn_AsiteLogin);

		if (isDisplayed(GlobalPageElements.btn_DynamicModelDisMiss))
			clickElementAndWait(GlobalPageElements.btn_DynamicModelDisMiss);
	}
	
	public void handleAlertWindowPopup(String function) {
		try {
			String psExecPath = new File("./src/test/resources/PsExec.exe").toString();
			String remoteDomain = ResourceHandler.getPropertyValue("remote.domain");
			String autoITPath = ResourceHandler.loadProperty("autoit.public.folder.pop.path");
			String remoteUser = ResourceHandler.getPropertyValue("remote.user");
			String remotePassword = ResourceHandler.getPropertyValue("remote.password");
			String cmd = psExecPath + " -i " + nodeIP + " -u " + remoteDomain + "\\" + remoteUser + " -p " + remotePassword + " " + autoITPath + " " + function + "";
			log.info("Command for Remote AutoIt execution:" + cmd);
			Process p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
		}
		catch (Exception exp) {
			exp.printStackTrace();
		}
	}
}
