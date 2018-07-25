package org.asite.automation.adoddle.p2.scripts;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HighMediumLowFlagsScripts extends AdoddleCommonAppMethods {
	private int				i;
	final private List<String>	highFlagForms		= new ArrayList<String>();
	final private List<String>	mediumFlagForms		= new ArrayList<String>();
	final private List<String>	lowFlagForms		= new ArrayList<String>();
	final private List<String>	allFlagFormsList	= new ArrayList<String>();
	final private static Logger	log					= AutomationLogger.getInstance().getLogger(HighMediumLowFlagsScripts.class);

	public void getFormNameList(String project, String appFolder, String appType) {
		clickElementWithText(project);
		clickElementWithText(appFolder);
		clickElementWithText(appType);
		collectionDataMap.put("Flag Form Type", appType);

		List<WebElement> formList = findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList);

		i = javaUtils.resetIndex(i, 0);

		label:
		for (WebElement file : formList) {
			switch (i) {
				case 0:
				case 1:
					highFlagForms.add(file.getText());
					break;
				case 2:
				case 3:
					mediumFlagForms.add(file.getText());
					break;
				case 4:
				case 5:
					lowFlagForms.add(file.getText());
					break;
				default:
					break label;
			}
			i++;
		}
		log.info("high Flag Forms :" + highFlagForms);
		log.info("medium Flag Forms :" + mediumFlagForms);
		log.info("low Flag Forms :" + lowFlagForms);
	}

	public void selectMoreFormsForFlag(String project, String appFolder, String appType, String flag) {
		clickElementWithText(project);
		clickElementWithText(appFolder);
		clickElementWithText(appType);

		log.info("flagType :" + flag);
		if (flag.contains(AdoddleCommonStringPool.FLAG_HIGH)) {
			setFlagCheckList(highFlagForms);
			collectionDataMap.put("High Flag FormList", highFlagForms.toString());
		}
		else if (flag.contains(AdoddleCommonStringPool.FLAG_MEDIUM)) {
			setFlagCheckList(mediumFlagForms);
			collectionDataMap.put("Medium Flag FormList", mediumFlagForms.toString());
		}
		else if (flag.contains(AdoddleCommonStringPool.FLAG_LOW)) {
			setFlagCheckList(lowFlagForms);
			collectionDataMap.put("Low Flag FormList", lowFlagForms.toString());
		}
		else
			log.info(": No Flags Selected :");
	}

	private void setFlagCheckList(List<String> flagList) {
		for (String form1 : flagList) {
			i = 0;
			for (WebElement form2 : findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList)) {
				if (form2.getText().equalsIgnoreCase(form1)) {
					if (!isSelected(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'chkbox')]//input[@type='checkbox']"))) {
						clickElement(By.xpath(".//div[@index='" + i + "']//div[contains(@class,'chkbox')]//input[@type='checkbox']"));
						break;
					}
				}
				i++;
			}
			waitForCompletePageLoad();
		}
	}

	public void contextClickAndSelectFlagType(String flag, String flagType) {
		if (flagType.contains(AdoddleCommonStringPool.FLAG_HIGH))
			contextClickWithLink(highFlagForms.get(0));
		else if (flagType.contains(AdoddleCommonStringPool.FLAG_MEDIUM))
			contextClickWithLink(mediumFlagForms.get(0));
		else if (flagType.contains(AdoddleCommonStringPool.FLAG_LOW))
			contextClickWithLink(lowFlagForms.get(0));
		else if (flagType.contains(AdoddleCommonStringPool.OPTION_CLEAR_FLAG)) {
			if (!isSelected(FilesTab.chk_MultiFilesSelectionCheckbox))
				clickElementAndWait(FilesTab.chk_MultiFilesSelectionCheckbox);
			contextClickWithLink(allFlagFormsList.get(0));
		}
		/*clickContextMenuOptionWithText(flag);
		clickContextMenuOptionWithText(flagType);*/
		clickContextMenuOption(flag, flagType);
	}

	public void verifyFlagTypeAndForms(String flag) {
		if (flag.contains(AdoddleCommonStringPool.FLAG_HIGH))
			flagTypeAndForms(highFlagForms, flag);
		else if (flag.contains(AdoddleCommonStringPool.FLAG_MEDIUM))
			flagTypeAndForms(mediumFlagForms, flag);
		else if (flag.contains(AdoddleCommonStringPool.FLAG_LOW))
			flagTypeAndForms(lowFlagForms, flag);
		else if (flag.contains(AdoddleCommonStringPool.NO_FLAG))
			flagTypeAndForms(allFlagFormsList, flag);
	}

	private void flagTypeAndForms(List<String> list, String flag) {
		List<WebElement> formList;
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
		waitUntilElementIsClickable(findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList).get(0));
		formList = findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList);

		List<WebElement> flagTypeList =  findElements(FilesTab.css_FilesTabFlagTypeList);

		Assert.assertTrue(list.size() <= formList.size());
		i = javaUtils.resetIndex(i, 0);
		for (WebElement formTitle : formList) {
			Assert.assertTrue(list.toString() + " does not contain " + getElementText(formTitle), list.contains(getElementText(formTitle)));
			Assert.assertTrue(flagTypeList.get(i) + " title property does not contain " + flag, flagTypeList.get(i).getAttribute("title").contains(flag));
			i++;
		}
	}

	public void verifyAllFlagFilterForms() {
		List<String> formTitleList = new ArrayList<String>();
		allFlagFormsList.addAll(highFlagForms);
		allFlagFormsList.addAll(mediumFlagForms);
		allFlagFormsList.addAll(lowFlagForms);
		log.info("allFlagFormsList :" + allFlagFormsList);

		List<WebElement> formList = findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList);
		for (WebElement formTitle : formList) {
			formTitleList.add(getElementText(formTitle));
		}
		log.info("formTitleList :" + formTitleList);

		if (formList.size() == allFlagFormsList.size())
			Assert.assertTrue(allFlagFormsList.containsAll(formTitleList));
		else {
			Assert.assertTrue("List Size not matched",false);
		}
	}

	/*** Set Flags on Multiple Apptypes ***/

	public void setFlagsOnMultipleApptypes(String flag, String highFlag, String midFlag, String lowFlag) {
		waitForCompletePageLoad();
		for (WebElement appType : findElements(ProjectFormsTab.css_FormsTabAppTypesGroupList)) {
			clickElementAndWait(appType);

			if (isDisplayed(ProjectFormsTab.lnk_FirstFormTitle)) {
				if (highFlagForms.size() < 2) {
					highFlagForms.add(getElementText(ProjectFormsTab.lnk_FirstFormTitle));
					selectFlagOnContextClick(flag, highFlag);
				}
				else if (mediumFlagForms.size() < 2) {
					mediumFlagForms.add(getElementText(ProjectFormsTab.lnk_FirstFormTitle));
					selectFlagOnContextClick(flag, midFlag);
				}
				else if (lowFlagForms.size() < 2) {
					lowFlagForms.add(getElementText(ProjectFormsTab.lnk_FirstFormTitle));
					selectFlagOnContextClick(flag, lowFlag);
				}
				else
					break;
			}
		}
		collectionDataMap.put("High Flag Form List", highFlagForms.toString());
		collectionDataMap.put("Medium Flag Form List", mediumFlagForms.toString());
		collectionDataMap.put("Low Flag Form List", lowFlagForms.toString());
	}

	private void selectFlagOnContextClick(String flag, String flagType) {
		contextClick(ProjectFormsTab.lnk_FirstFormTitle);
		clickContextMenuOptionWithText(flag);
		clickContextMenuOptionWithText(flagType);
	}
}
