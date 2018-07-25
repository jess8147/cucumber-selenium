/**  Testdata required for this script as follows.
     1). 
     2).   
 */

package org.asite.automation.adoddle.p2.scripts;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.asite.automation.CommonLocators.AdoddleFieldLocators.FieldTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleScenarioMarkers;
import org.openqa.selenium.Keys;

public class CreateSiteLocationScripts extends AdoddleCommonAppMethods {

	private static String	projectName = "Redrow Colindale Gardens" , siteName, locationName;
	public static Logger	log	= AutomationLogger.getInstance().getLogger(CreateSiteLocationScripts.class);
	private String 	excelPath = "C:\\Users\\jasminprajapati\\Desktop\\Support\\Block Q Import 11.7.18.xls";
	static FileInputStream fs = null;
	private int startIndex,endIndex, dataPushIndex;
	public void createFieldEnabledProject(String geoGraphy) throws InterruptedException {
		log.info("Creating Field Enabled project...");
		projectName = "Automation_Field_Project" + epoch;
		clickElementAndWaitForElement(ProjectsTab.lnk_ProjectsTabLHAddProject, ProjectsTab.txt_PopCreateProjectProjectName);

		sendKeys(ProjectsTab.txt_PopCreateProjectProjectName, projectName);
		sendKeys(ProjectsTab.txt_PopCreateProjectProjectName, Keys.TAB);

		if (getCount(ProjectsTab.txt_PopCreateProjectClientInput) > 0) {
			log.info("found client input");
			findElement(ProjectsTab.txt_PopCreateProjectClientInput).sendKeys("Automation Testing");
			waitForCompletePageLoad();
			clickElementAndWait(ProjectsTab.sel_CloneProjectClientNameFirstResult);

		}

		executeJScript(AdoddleCommonJQueries.clickFieldEnabledQuery);
		selectByVisibleText(ProjectsTab.drp_PopCreateProjectGeography, geoGraphy);
		waitForCompletePageLoad();
		clickElementAndWait(ProjectsTab.btn_PopCreateProjectSave);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		collectionDataMap.put("project name", projectName);
		navigateTab(LandingPage.lnk_Files);

		if (dataCenter.equalsIgnoreCase("UK") && !getCurrentWindowURL().contains("adoddleak"))
			navigateURL(getCurrentWindowURL().contains("adoddleb") ? getCurrentWindowURL().replace("adoddleb", "adoddleak") : getCurrentWindowURL().replace("adoddled", "adoddleak"));
		else if (dataCenter.equalsIgnoreCase("US") && !getCurrentWindowURL().contains("adoddleb"))
			navigateURL(getCurrentWindowURL().contains("adoddleak") ? getCurrentWindowURL().replace("adoddleak", "adoddleb") : getCurrentWindowURL().replace("adoddled", "adoddleb"));
		else if (dataCenter.contains("AU") && !getCurrentWindowURL().contains("adoddled"))
			navigateURL(getCurrentWindowURL().contains("adoddleak") ? getCurrentWindowURL().replace("adoddleak", "adoddled") : getCurrentWindowURL().replace("adoddleb", "adoddled"));

		reloadPage();
		waitForCompletePageLoad();
		navigateTab(LandingPage.lnk_Projects);
		searchProjects(projectName);
	}

	public void selectAppAndAddToProject(String appName, String btnText) {
		waitUntilElementIsDisplayed(ProjectsTab.txt_PopAssignAppModalSearchInput);
		sendKeys(ProjectsTab.txt_PopAssignAppModalSearchInput, appName);
		waitForCompletePageLoad();
		clickElement(ProjectsTab.chk_PopAssignAppsFirstFilteredFormCheckbox);
		clickElementAndWait(ProjectsTab.btn_PopAssignAppsAddtoProject);
	}

	public void selectAppTemplateAndContextClick() {
		contextClick(ProjectsTab.ele_PopManageAppsFirstFormStatus);
		waitUntilElementIsDisplayed(ProjectsTab.ele_PopManageAppsFormStatusContextMenu);
	}

	public void addUserAsRole(String role) throws InterruptedException {
		sendKeys(ProjectsTab.txt_PopManageRolesSearchRole, role);
		waitForCompletePageLoad();
		sendKeys(ProjectsTab.txt_PopManageRolesFilteredUserInput, System.getProperty("primary.username"));
		sendKeys(ProjectsTab.txt_PopManageRolesFilteredUserInput, Keys.TAB);
		clickElement(ProjectsTab.btn_PopManageRolesSave);
		try {
			waitUntilElementIsDisplayed(GlobalPageElements.lbl_SuccessMessage);
		}
		catch(Throwable t) {
			log.error("Success message was not delivered");
		}
		waitForCompletePageLoad();
		if (isDisplayedButtonWithText("OK"))
			clickButtonWithText("OK");
	}

	public void contextClickOnFieldEnabledProject() {
		contextClickWithText(projectName);

	}

	public void verifyAddSiteOptionEnabled(String addSiteLabel) {
		waitUntilElementIsDisplayed(FieldTab.opt_ContextClickProjectAddSite);
		AutomationAssert.verifyTrue(getElementText(FieldTab.opt_ContextClickProjectAddSite).equalsIgnoreCase(addSiteLabel));
		AutomationAssert.verifyTrue(isEnabled(FieldTab.opt_ContextClickProjectAddSite));
	}

	public void getSiteLocationExcelData() throws IOException {
		String header = "Site->Location->SubLocation";
		List<String> dataList = new ArrayList<String>();
		dataList.add(header);
		fs = new FileInputStream(excelPath);
		HSSFWorkbook workbook = new HSSFWorkbook(fs);
		HSSFSheet sheet = workbook.getSheet("sheet1");
		int rowcount = excelUtils.getRowCount(sheet);
		log.info("Total Row count: "+rowcount);
		int columncount = excelUtils.getColumnCount(sheet);
		int siteNameIndex = 0;
		int locationNameIndex = 0;
		int sublocationNameIndex = 0;

		for (int index = 0; index < columncount; index++) {
			if (sheet.getRow(0).getCell(index).getStringCellValue().equalsIgnoreCase("Column2"))
				siteNameIndex = index;
			else if (sheet.getRow(0).getCell(index).getStringCellValue().equalsIgnoreCase("Column3"))
				locationNameIndex = index;
			else if (sheet.getRow(0).getCell(index).getStringCellValue().equalsIgnoreCase("Column4"))
				sublocationNameIndex = index;
		}

		for (int counter = 1; counter < rowcount; counter++) {
			if (sheet.getRow(counter) != null && sheet.getRow(counter).getCell(columncount - 1) == null) {
				startIndex = counter;
				dataPushIndex = counter;
				endIndex = rowcount - counter;
				System.out.println("Start index :"+startIndex);
				System.out.println("End index :"+endIndex);
				break;
			}
		}

		for (int index = 1; index < endIndex; index++) {
			String siteName="", subLocation="", locationName="";
			if(sheet.getRow(startIndex) != null &&  sheet.getRow(startIndex).getCell(columncount - 1) == null) {
				if(siteNameIndex !=0)
					siteName = excelUtils.isCellEmpty(sheet.getRow(startIndex).getCell(siteNameIndex)) ? "":sheet.getRow(startIndex).getCell(siteNameIndex).getStringCellValue().trim();
				if(locationNameIndex !=0)
					locationName = excelUtils.isCellEmpty(sheet.getRow(startIndex).getCell(locationNameIndex)) ? "":sheet.getRow(startIndex).getCell(locationNameIndex).getStringCellValue().trim();
				if(sublocationNameIndex !=0)
					subLocation =  excelUtils.isCellEmpty(sheet.getRow(startIndex).getCell(sublocationNameIndex)) ? "":sheet.getRow(startIndex).getCell(sublocationNameIndex).getStringCellValue().trim();

				String previousValue = dataList.get(index - 1);

				if (previousValue.split("->").length > 2  && locationName.equalsIgnoreCase(previousValue.split("->")[2])) {
					System.out.println(previousValue + "->" + subLocation);
					dataList.add(index - 1, previousValue + "->" + subLocation);
				} else {
					String data;
					if(subLocation != "")
						 data = siteName + "->" + locationName + "->" + subLocation;
					else
						data = siteName + "->" + locationName;

					System.out.println(data);
					if (!siteName.equalsIgnoreCase(""))
						dataList.add(data);

				}
				startIndex++;
			}
			else {
				continue;
			}
		}
		log.info("Data to be created :" + dataList);
		createIterativeSiteLocations(dataList, workbook);
	}
	
	
	public void createIterativeSiteLocations(List<String> list, HSSFWorkbook workbook) throws IOException {
		clickElementWithText(projectName);
		list.remove(0);
		try {
			for (String s : list) {
				List<String> siteLocation = Arrays.asList(s.split("->"));

				for (String str : siteLocation) {
					str = str.trim();
					if (siteLocation.indexOf(str) == 0 && !str.equalsIgnoreCase("")) {
						waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
						waitForCompletePageLoad();
						if (!isDisplayedElementWithText(str)) {
							contextClickOnFieldEnabledProject();
							clickAddSiteOption("Add Site");
							enterSiteName(str);
							clickElementAndWaitForInvisibilityOfElement(FieldTab.btn_PopAddSiteCreate, FieldTab.btn_PopAddSiteCreate);
							waitForElementWithText(str);
							clickElementWithText(str);
						} else {
							clickElementWithText(str);
						}
					} else if (!str.equalsIgnoreCase("")) {
						waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
						waitForCompletePageLoad();
						if (!isDisplayedElementWithText(str)) {
							contextClickWithText(siteLocation.get(siteLocation.indexOf(str) - 1));
							clickAddLocationOption("Add Location");
							enterLocationName(str);
							clickElementAndWaitForInvisibilityOfElement(FieldTab.btn_PopAddLocationCreate, FieldTab.btn_PopAddLocationCreate);
							waitForElementWithText(str);
							clickElementWithText(str);
						} else {
							clickElementWithText(str);
						}
					}
				}
				log.info("Record Created :" + s);
				HSSFSheet outSheet = workbook.getSheetAt(0);
				int lastColumn = outSheet.getRow(dataPushIndex).getPhysicalNumberOfCells();
				log.info("Last Column Index :"+lastColumn);
				HSSFCell cell = outSheet.getRow(dataPushIndex).createCell(lastColumn);
				log.info("Last Column Index  to be set");
				cell.setCellValue("Done");
				log.info("Last Column Index set done");
				dataPushIndex++;
			}
		}
		catch(Throwable t) {
			t.printStackTrace();
			AutomationAssert.verifyTrue(t.getLocalizedMessage(),false);
		}
		finally {
			fs.close();
			FileOutputStream fileOutputStream = new FileOutputStream(excelPath);
			workbook.write(fileOutputStream);
			fileOutputStream.close();
		}
	}
	
	public void clickAddSiteOption(String addSiteLabel) {
		clickElementAndWaitForElement(FieldTab.opt_ContextClickProjectAddSite, FieldTab.txt_PopAddSiteSiteName);
	}

	public void enterSiteName(String siteNameInput) {
		if(siteNameInput.equalsIgnoreCase("Automation_TestSite"))
			siteName = siteNameInput + epoch;
		else
			siteName = siteNameInput;
		
		collectionDataMap.put("site name", siteName);
		sendKeys(FieldTab.txt_PopAddSiteSiteName, siteName);
	}

	public void verifyAddSiteLocationMessage(String addSiteMessage) {
		try {
			AutomationAssert.verifyTrue(getElementText(GlobalPageElements.lbl_SuccessMessage, 10).equalsIgnoreCase(addSiteMessage));
		}
		catch (Throwable t) {
			log.error("add site message could not be verified");
		}

	}

	public void verifySiteIsAvailable() {
		clickElementWithText(projectName);
		clickElementWithText(siteName);
		AutomationAssert.verifyTrue(isDisplayedElementWithText(siteName));
		AdoddleScenarioMarkers.createSiteLocationFlag = true;
	}

	public void contextClickOnSite() {
		clickElementWithText(projectName);
		contextClickWithText(siteName);
	}

	public void clickAddLocationOption(String locationLabel) {
		waitUntilElementIsDisplayed(FieldTab.opt_ContextClickSiteAddLocation);
		AutomationAssert.verifyTrue(getElementText(FieldTab.opt_ContextClickSiteAddLocation).equalsIgnoreCase(locationLabel));
		clickElementAndWaitForElement(FieldTab.opt_ContextClickSiteAddLocation, FieldTab.txt_PopAddLocationLocationName);
	}

	public void enterLocationName(String location) {
		if(location.equalsIgnoreCase("Automation_TestLocation"))
			locationName = location + epoch;
		else
			locationName = location;
		collectionDataMap.put("location name", locationName);
		sendKeys(FieldTab.txt_PopAddLocationLocationName, locationName);
	}

	public void verifyLocationIsAvailable() {
		clickElementWithText(projectName);
		clickElementWithText(siteName);
		AutomationAssert.verifyTrue(isDisplayedElementWithText(locationName));
		deactivateProject(projectName);
	}

}
