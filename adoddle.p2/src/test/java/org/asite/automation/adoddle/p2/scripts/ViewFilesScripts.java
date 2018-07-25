package org.asite.automation.adoddle.p2.scripts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleDiscussionsLocators.DiscussionsTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.CommonLocators.AdoddleModelsLocators.ModelsTab;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.lib.AdoddleCommonAppMethods.EnumList.AsiteMenu;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class ViewFilesScripts extends AdoddleCommonAppMethods {
	private int					i, j, counter;
	private boolean				flag;
	private Action				dragAndDrop;
	private String				beforeMoveElementWidth, afterMoveElementWidth;
	private WebElement			customWidthElement, resizedWidthElement;
	final private List<String>	selectedColoumList			= new ArrayList<String>();
	private List<WebElement>	tabHeaderColoumsNameList	= new ArrayList<WebElement>();
	final private List<String>	firstFilesNameList			= new ArrayList<String>();
	final private List<String>	lastFilesNameList			= new ArrayList<String>();
	final private List<String>	getDateList					= new ArrayList<String>();
	final private static Logger	log							= AutomationLogger.getInstance().getLogger(ViewFilesScripts.class);

	/***** Customized View *****/

	public void addRemoveColoumsFromTabListingPage(String addRemove, String activeTab) {

		if (addRemove.contains("removed")) {

			if (!activeTab.contains(AdoddleCommonStringPool.TAB_DISCUSSIONS)) {
				if(activeTab.contains(AsiteMenu.Projects.toString()) || activeTab.contains(AsiteMenu.Models.toString()) )
					setListingStyle("ListView");
				tabHeaderColoumsNameList = findElements(GlobalPageElements.css_AdoddleAllTabColoumsNameList);
			}
			else
				tabHeaderColoumsNameList = findElements(GlobalPageElements.css_AdoddleDiscussionsTabColoumsNameList);

			i = javaUtils.resetIndex(i, 0);

			for (WebElement coloumName : tabHeaderColoumsNameList) {
				if (coloumName.getAttribute("title").split(" - ")[0].contains(AdoddleCommonStringPool.COLUMN_REVISION) || coloumName.getAttribute("title").split(" - ")[0].contains("Version"))
					selectedColoumList.add(getElementText(coloumName));
				else
					selectedColoumList.add(coloumName.getAttribute("title").split(" - ")[0]);
				if (i == 1)
					break;
				i++;
			}
			
			collectionDataMap.put("customized column list", selectedColoumList.toString());

			log.info("selectedColoumList : " + selectedColoumList);

			clickElementAndWaitForElement(GlobalPageElements.btn_ActiveTabSettingDropdownButton, GlobalPageElements.btn_ActiveTabSettingDropdownCloseButton);
			clickElementAndWaitForElement(GlobalPageElements.btn_CustomizeButton, GlobalPageElements.btn_CustomizeDropdownSaveButton);
			
			for (String coloumField : selectedColoumList) {
				selectByVisibleText(GlobalPageElements.drp_CustomizeSelectedFieldsDropdown, coloumField);
				clickElementAndWait(GlobalPageElements.btn_CustomizeMoveToAvailableFieldsButton);
			}
		}
		else {

			clickElementAndWaitForElement(GlobalPageElements.btn_ActiveTabSettingDropdownButton, GlobalPageElements.btn_ActiveTabSettingDropdownCloseButton);
			clickElementAndWaitForElement(GlobalPageElements.btn_CustomizeButton, GlobalPageElements.btn_CustomizeDropdownSaveButton);
			
			for (String coloumField : selectedColoumList) {
				selectByVisibleText(GlobalPageElements.drp_CustomizeAvailableFieldsDropdown, coloumField);
				clickElementAndWait(GlobalPageElements.btn_CustomizeMoveToSelectedFieldsButton);
			}
		}
	}

	public void clickOnSaveButtonOfCustomizedView() {
		clickElementAndWaitForInvisibilityOfElement(GlobalPageElements.btn_CustomizeDropdownSaveButton, GlobalPageElements.btn_CustomizeDropdownSaveButton);
	}

	public void verifyAddRemoveColoums(String isDisplay, String activeTab) {
		if (!activeTab.contains(AdoddleCommonStringPool.TAB_DISCUSSIONS))
			tabHeaderColoumsNameList = findElements(GlobalPageElements.css_AdoddleAllTabColoumsNameList);
		else
			tabHeaderColoumsNameList = findElements(GlobalPageElements.css_AdoddleDiscussionsTabColoumsNameList);

		if (isDisplay.contains("not")) {

			for (String removedColoum : selectedColoumList) {
				for (WebElement coloumName : tabHeaderColoumsNameList) {

					mouseHover(coloumName);
					log.info("removedColoum : " + removedColoum);

					if (removedColoum.equalsIgnoreCase(AdoddleCommonStringPool.REV) || removedColoum.equalsIgnoreCase(AdoddleCommonStringPool.VER)) {
						log.info("coloumName : " + coloumName.getText());
						AutomationAssert.verifyTrue(!coloumName.getText().equalsIgnoreCase(removedColoum));
					}
					else {
						log.info("coloumName : " + coloumName.getAttribute("title").split(" - ")[0]);
						AutomationAssert.verifyTrue(!coloumName.getAttribute("title").split(" - ")[0].equalsIgnoreCase(removedColoum));
					}
				}
			}
		}
		else {

			boolean flag = false;
			for (String addedColoum : selectedColoumList) {
				for (WebElement coloumName : tabHeaderColoumsNameList) {

					log.info("addedColoum : " + addedColoum);

					if (addedColoum.equalsIgnoreCase(AdoddleCommonStringPool.REV) || addedColoum.equalsIgnoreCase(AdoddleCommonStringPool.VER)) {
						String value;
						if (addedColoum.equalsIgnoreCase(AdoddleCommonStringPool.REV))
							value = "Revision Code";
						else
							value = "Version Number";
						log.info("value : " + value);
						if (coloumName.getAttribute("title").split(" - ")[0].contains(value)) {
							mouseHover(coloumName);
							log.info("coloumName : " + coloumName.getText());
							AutomationAssert.verifyTrue(coloumName.getAttribute("title").split(" - ")[0].contains(value));
							flag = true;
							break;
						}

					}
					else if (coloumName.getAttribute("title").split(" - ")[0].equalsIgnoreCase(addedColoum)) {
						mouseHover(coloumName);
						log.info("coloumName : " + coloumName.getAttribute("title").split(" - ")[0]);
						AutomationAssert.verifyTrue(coloumName.getAttribute("title").split(" - ")[0].equalsIgnoreCase(addedColoum));
						flag = true;
						break;

					}
					else {
						flag = false;
					}
				}
				if (!flag) {
					AutomationAssert.verifyTrue(false);
				}
			}
		}
	}

	public void movedColoumsUsingCustomizedView(String activeTab) {
		
		clickElementAndWaitForElement(GlobalPageElements.btn_ActiveTabSettingDropdownButton, GlobalPageElements.btn_ActiveTabSettingDropdownCloseButton);
		clickElementAndWaitForElement(GlobalPageElements.btn_CustomizeButton, GlobalPageElements.btn_CustomizeDropdownSaveButton);

		i = javaUtils.resetIndex(i, 0);

		String customizedOption;
		for (String selectedColoum : selectedColoumList) {

			new Actions(getWebDriver()).keyDown(Keys.CONTROL);
			for (WebElement option : findElements(GlobalPageElements.css_CustomizeSelectedFieldsDropdownOptionsList)) {
				if (option.getText().equalsIgnoreCase(selectedColoum)) {
					option.click();
					i++;
					break;
				}
			}
			new Actions(getWebDriver()).keyUp(Keys.CONTROL);
		}

		if (!activeTab.contains(AdoddleCommonStringPool.TAB_DISCUSSIONS))
			customizedOption = "option[4]";
		else
			customizedOption = "option[5]";

		int counter = 0;
		while (!getElementText(By.xpath(".//div[@id='bodyPart']//div[@class='selectedField']//select[@class='listbox']//" + customizedOption + "")).equalsIgnoreCase(selectedColoumList.get(0))) {
			clickElement(GlobalPageElements.btn_CustomizeMoveUpButton);
			counter++;
			if (counter > 100) {
				AutomationAssert.verifyTrue(": Either Input or Locator is wrong :",false);
			}
		}
	}

	public void verifySelectedColoumOnTop(String activeTab) {
		if (!activeTab.contains(AdoddleCommonStringPool.TAB_DISCUSSIONS))
			i = javaUtils.resetIndex(i, 3);
		else
			i = javaUtils.resetIndex(i, 4);

		for (String selectedColoum : selectedColoumList) {
			log.info("Expected Top Coloum Result : " + selectedColoum);

			if (selectedColoum.equalsIgnoreCase("Rev") || selectedColoum.equalsIgnoreCase("Ver")) {
				log.info("Actual Top Coloum Result : " + getSelectedColoumOfActiveTab(i, activeTab).getText());
				AutomationAssert.verifyTrue(getSelectedColoumOfActiveTab(i, activeTab).getText().equalsIgnoreCase(selectedColoum));
			}
			else {
				log.info("Actual Top Coloum Result : " + getSelectedColoumOfActiveTab(i, activeTab).getAttribute("title").split(" - ")[0]);
				AutomationAssert.verifyTrue(getSelectedColoumOfActiveTab(i, activeTab).getAttribute("title").split(" - ")[0].equalsIgnoreCase(selectedColoum));
			}
			i++;
		}
	}

	public void getMovedColumnWidthAndCustomizeColumn(String activeTab) {
		if (!activeTab.contains(AdoddleCommonStringPool.TAB_DISCUSSIONS)) {
			if (!getCustomizeColoumDataType(3).equalsIgnoreCase("img") && !getCustomizeColoumDataType(4).equalsIgnoreCase("img")) {
				flag = true;
				getMovedColumnWidthAndCustomizeColumn1(activeTab);
			}
			else {
				flag = false;
				log.info(": It's Image Coloums, Can't Resized : Skip Test");
			}
		}
		else {
			if (!getCustomizeColoumDataType(4).equalsIgnoreCase("img") && !getCustomizeColoumDataType(5).equalsIgnoreCase("img")) {
				flag = true;
				getMovedColumnWidthAndCustomizeColumn1(activeTab);
			}
			else {
				flag = false;
				log.info(": It's Image Coloums, Can't Resized : Skip Test");
			}
		}
	}

	private void getMovedColumnWidthAndCustomizeColumn1(String activeTab) {
		WebElement resizedWidthElement2;
		customWidthElement = getCustomizedWidthColoum(activeTab);
		if (!activeTab.contains(AdoddleCommonStringPool.TAB_DISCUSSIONS)) {
			resizedWidthElement = getResizedWidthColoum(3);
			resizedWidthElement2 = getResizedWidthColoum(4);
		}
		else {
			resizedWidthElement = getResizedWidthColoum(4);
			resizedWidthElement2 = getResizedWidthColoum(5);
		}
		beforeMoveElementWidth = customWidthElement.getAttribute("style").split("width: ")[1].replace("px;", "");
		log.info("beforeMoveElementWidth :" + beforeMoveElementWidth);

		dragAndDrop = new Actions(getWebDriver()).clickAndHold(resizedWidthElement).moveToElement(resizedWidthElement2, resizedWidthElement2.getLocation().getX(), resizedWidthElement2.getLocation().getY()).release(resizedWidthElement2).build();
		dragAndDrop.perform();
	}

	public void verifyCustomizeMovedColumnWidth(String activeTab) {
		if (!flag)
			log.info(": It's Image Coloums, Can't Resized : Skip Test");
		else
			verifyCustomizeMovedColumnWidth1(activeTab);
	}

	private void verifyCustomizeMovedColumnWidth1(String activeTab) {
		customWidthElement = getCustomizedWidthColoum(activeTab);
		afterMoveElementWidth = customWidthElement.getAttribute("style").split("width: ")[1].replace("px;", "");
		log.info("afterMoveElementWidth :" + afterMoveElementWidth);

		if (!activeTab.contains(AdoddleCommonStringPool.TAB_DISCUSSIONS)) {
			i = javaUtils.resetIndex(i, 3);
			j = javaUtils.resetIndex(j, 4);
		}
		else {
			i = javaUtils.resetIndex(i, 4);
			j = javaUtils.resetIndex(j, 5);
		}
		log.info("Verify Tab Position-01 : " + getSelectedColoumOfActiveTab(i, activeTab).getAttribute("title").split(" - ")[0] + " :: " + selectedColoumList.get(0));
		log.info("Verify Tab Position-02 : " + getSelectedColoumOfActiveTab(j, activeTab).getAttribute("title").split(" - ")[0] + " :: " + selectedColoumList.get(1));

		AutomationAssert.verifyTrue(getSelectedColoumOfActiveTab(i, activeTab).getAttribute("title").split(" - ")[0].contains(selectedColoumList.get(0)));
		AutomationAssert.verifyTrue(getSelectedColoumOfActiveTab(j, activeTab).getAttribute("title").split(" - ")[0].contains(selectedColoumList.get(1)));
		AutomationAssert.verifyTrue("Width " + Integer.parseInt(beforeMoveElementWidth) + " should be less than " + Integer.parseInt(afterMoveElementWidth), Integer.parseInt(beforeMoveElementWidth) < Integer.parseInt(afterMoveElementWidth));
	}

	public void getCustomizedMovedColumnWidthAndCustomizeColumn(String activeTab) {
		if (!flag)
			log.info(": It's Image Coloums, Can't Resized : Skip Test");
		else
			getCustomizedMovedColumnWidthAndCustomizeColumn1(activeTab);
	}

	private void getCustomizedMovedColumnWidthAndCustomizeColumn1(String activeTab) {
		int customizeWidth = Integer.parseInt(afterMoveElementWidth) - Integer.parseInt(beforeMoveElementWidth);
		log.info("customizeWidth :" + customizeWidth);

		customWidthElement = getCustomizedWidthColoum(activeTab);
		if (!activeTab.contains(AdoddleCommonStringPool.TAB_DISCUSSIONS))
			resizedWidthElement = getResizedWidthColoum(3);
		else
			resizedWidthElement = getResizedWidthColoum(4);

		dragAndDrop = new Actions(getWebDriver()).clickAndHold(resizedWidthElement).moveToElement(customWidthElement, customizeWidth, customWidthElement.getLocation().getY()).release(customWidthElement).build();
		dragAndDrop.perform();
	}

	public void verifyCustomizedMovedColumnWidth(String activeTab) {
		if (!flag)
			log.info(": It's Image Coloums, Can't Resized : Skip Test");
		else
			verifyCustomizedMovedColumnWidth1(activeTab);
	}

	private void verifyCustomizedMovedColumnWidth1(String activeTab) {
		customWidthElement = getCustomizedWidthColoum(activeTab);

		String customizedColumnWidth = customWidthElement.getAttribute("style").split("width: ")[1].replace("px;", "");
		log.info("customizedColumnWidth :" + customizedColumnWidth);

		if (!activeTab.contains(AdoddleCommonStringPool.TAB_DISCUSSIONS)) {
			i = javaUtils.resetIndex(i, 3);
			j = javaUtils.resetIndex(j, 4);
		}
		else {
			i = javaUtils.resetIndex(i, 4);
			j = javaUtils.resetIndex(j, 5);
		}
		log.info("Verify Tab Position-11 : " + getSelectedColoumOfActiveTab(i, activeTab).getAttribute("title").split(" - ")[0] + " :: " + selectedColoumList.get(0));
		log.info("Verify Tab Position-12 : " + getSelectedColoumOfActiveTab(j, activeTab).getAttribute("title").split(" - ")[0] + " :: " + selectedColoumList.get(1));

		AutomationAssert.verifyTrue(getSelectedColoumOfActiveTab(i, activeTab).getAttribute("title").split(" - ")[0].contains(selectedColoumList.get(0)));
		AutomationAssert.verifyTrue(getSelectedColoumOfActiveTab(j, activeTab).getAttribute("title").split(" - ")[0].contains(selectedColoumList.get(1)));
		AutomationAssert.verifyTrue(Integer.parseInt(customizedColumnWidth) < Integer.parseInt(afterMoveElementWidth));
	}

	public void CustomizedViewTestDataCleanUp() {
		
		clickElementAndWaitForElement(GlobalPageElements.btn_ActiveTabSettingDropdownButton, GlobalPageElements.btn_ActiveTabSettingDropdownCloseButton);
		clickElementAndWaitForElement(GlobalPageElements.btn_CustomizeButton, GlobalPageElements.btn_CustomizeDropdownSaveButton);
		try {
			if (findElements(GlobalPageElements.css_CustomizedAvailableFieldsDropdownOptionsList).size() > 0) {
				new Actions(getWebDriver()).keyDown(Keys.CONTROL);
				for (WebElement option : findElements(GlobalPageElements.css_CustomizedAvailableFieldsDropdownOptionsList)) {
					option.click();
				}
				new Actions(getWebDriver()).keyUp(Keys.CONTROL);
				clickElementAndWait(GlobalPageElements.btn_CustomizeMoveToSelectedFieldsButton);
				log.info(": Test Data Cleanup Successfully :");
			}
			else
				log.info(": Test Data Already Cleanup :");
			clickOnSaveButtonOfCustomizedView();
		}
		catch (Throwable t) {
			log.info(": Failed To Cleanup TestData :");
		}
	}

	private WebElement getSelectedColoumOfActiveTab(int index, String activeTab) {
		if (!activeTab.contains(AdoddleCommonStringPool.TAB_DISCUSSIONS))
			return findElement(By.xpath(".//div[@id='adTableHead']//div[contains(@id,'columnId')][" + index + "]//span[not(contains(@class,'resize'))][not(contains(@style,'hidden'))][not(contains(@id,'ImageName'))][not(contains(@id,'ImgName'))][not(contains(@id,'favImagePath'))][not(contains(@id,'activityLockImage'))] | //div[@id='adTableHead']//div[contains(@id,'columnId')][" + index + "]//img[@title]"));
		else
			return findElement(By.xpath(".//div[@id='adTableHead']//div[contains(@id,'columnId')][" + index + "]//span[not(contains(@class,'resize'))][not(contains(@style,'hidden'))][not(contains(@id,'ImageName'))][not(contains(@id,'typeImage'))] | //div[@id='adTableHead']//div[contains(@id,'columnId')][" + index + "]//img[@title]"));
	}

	private WebElement getCustomizedWidthColoum(String activeTab) {
		if (!activeTab.contains(AdoddleCommonStringPool.TAB_DISCUSSIONS))
			i = javaUtils.resetIndex(i, 3);
		else
			i = javaUtils.resetIndex(i, 4);
		return findElement(By.xpath(".//div[@id='adTableHead']//div[contains(@id,'columnId')][" + i + "]"));
	}

	private WebElement getResizedWidthColoum(int i) {
		return findElement(By.xpath(".//div[@id='adTableHead']//div[contains(@id,'columnId')][" + i + "]//i[@class='resize']"));
	}

	private String getCustomizeColoumDataType(int i) {
		return findElement(By.xpath(".//div[@id='adTableHead']//div[contains(@id,'columnId')][" + i + "]")).getAttribute("data-coltype");
	}

	/*** Sorting Scenario ***/

	public void getLastPageFilesCount(String columnName) {
		executeJScript(AdoddleCommonJQueries.scrollDownScrollJquery);
		counter = (getSelectedColumnList(columnName).size() - 1);
		log.info("last Page Count : " + counter);
	}

	public void sortByColumnNameInSpecifiedOrder(String columnName, String order) {
		int columnCounter = 1;
		List<WebElement> columnHeaders = findElements(GlobalPageElements.css_GlobalListingHeaderColumns);

		for (WebElement wE : columnHeaders) {

			mouseHover(wE);
			log.info("Printing Column name: " + getElementText(wE));

			columnCounter++;
			log.info("columnCounter : " + columnCounter);

			if (getElementText(wE).equalsIgnoreCase(columnName)) {
				log.info("Required Column name: " + getElementText(wE));
				clickElementAndWait(wE);
				
				if(columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_ASSIGNED_DATE)) {
					clickElementAndWait(FilesTab.btn_PopContinueButton);
				}
				waitForCompletePageLoad();
				mouseHover(GlobalPageElements.ele_GlobalListingHeaderSortingIcon);
				if (order.equalsIgnoreCase("ascending")) {
					if (isDisplayed(GlobalPageElements.ele_GlobalListingHeaderAscendingIcon))
						break;
					else
						clickElementAndWait(GlobalPageElements.ele_GlobalListingHeaderDescendingIcon);

				}
				else if (order.equalsIgnoreCase("descending")) {
					if (isDisplayed(GlobalPageElements.ele_GlobalListingHeaderDescendingIcon))
						break;
					else
						clickElementAndWait(GlobalPageElements.ele_GlobalListingHeaderAscendingIcon);
				}
				break;
			}
		}
	}

	private String subStringChar = null;
	public void getFilesNameWithRespectToOrder(String filesOrder, String columnName) throws ParseException {
		if (filesOrder.contains("First")) {
			i = javaUtils.resetIndex(i, 0);
			firstFilesNameList.clear();
			for (WebElement file : getSelectedColumnList(columnName)) {
				if(columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_ID) || columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_TRANSMITTAL_NO))
					firstFilesNameList.add(getElementText(file).substring(3));
				else
					firstFilesNameList.add(getElementText(file));
				
				if (i == counter)
					break;
				i++;
			}
			log.info("firstFilesNameList : " + firstFilesNameList);
		}
		else {

			executeJScript(AdoddleCommonJQueries.scrollDownScrollJquery);
			int lastIndex = (getSelectedColumnList(columnName).size() - 1);
			log.info("lastIndex : " + lastIndex);

			lastFilesNameList.clear();
			mouseHover(getSelectedColumnLastIndex(lastIndex, columnName));
			if (columnName.contains(AdoddleCommonStringPool.COLUMN_DATE) || columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_LAST_UPDATED)) {
				for (WebElement file : getSelectedColumnList(columnName)) {
					getDateList.add(file.getText());
				}
				lastFilesNameList.addAll(doSort());
				getDateList.clear();
			}
			else {
				
				List<Integer> idList = new ArrayList<Integer>();
				for (WebElement file : getSelectedColumnList(columnName)) {
					
					if(columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_ID) || columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_TRANSMITTAL_NO)) {
						subStringChar = file.getText().substring(0, Math.min(file.getText().length(), 3));
						idList.add(Integer.parseInt(file.getText().substring(3)));
					} else
						lastFilesNameList.add(file.getText());
				}
				if(subStringChar != null)
					log.info("subStringChar : "+subStringChar);
				
				if(columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_ID) || columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_TRANSMITTAL_NO)) {
					
					Collections.sort(idList, Collections.reverseOrder());
					for (Integer id : idList)
						lastFilesNameList.add(Integer.toString(id));
				}else
					Collections.sort(lastFilesNameList, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
				
			}
			log.info("lastFilesNameList : " + lastFilesNameList);
			log.info("First Page size : " + firstFilesNameList.size() + " :: " + "Last Page size : " + lastFilesNameList.size());
		}
	}

	public void gotoLastPage() {
		while (!isDisplayed(FilesTab.lnk_FilesTabDisableNextPage)) {
			String lastPage = Integer.toString(findElements(GlobalPageElements.css_GlobalListingPaginationCount).size());
			log.info("lastPage : " + lastPage);
			clickElementAndWait(By.xpath(".//div[contains(@id,'paging') or contains(@id,'Paging')]//ul//li[contains(@title,'Page ')][" + lastPage + "]//a"));
		}
	}

	public void verifyListingOrder(String filesOrder, String order, String columnName) throws ParseException {
		List<String> actualFileList = new ArrayList<String>();
		List<String> expectedFileList = new ArrayList<String>();
		actualFileList.clear();

		if (order.contains("descending")) {
			if (filesOrder.contains("Last set as First")) {

				i = javaUtils.resetIndex(i, 0);
				for (WebElement e : getSelectedColumnList(columnName)) {
					if(columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_ID) || columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_TRANSMITTAL_NO)) {
						actualFileList.add(e.getText().substring(0, Math.min(e.getText().length(), 3)) + e.getText().substring(3).replaceFirst("^0+(?!$)", ""));
					}
					else
						actualFileList.add(e.getText());
					if (i == counter)
						break;
					i++;
				}
				log.info("actualFileList : " + filesOrder + " :: " + actualFileList);
				
				if(columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_ID) || columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_TRANSMITTAL_NO)) {
					List<String> nameList = new ArrayList<String>();
					for (String id : lastFilesNameList) {
						/*switch (id.length()) {
							case 1:
								nameList.add(subStringChar + "00" + id);
								break;
							case 2:
								nameList.add(subStringChar + "0" + id);
								break;
							default:
								nameList.add(subStringChar + id);
								break;
						}*/
						nameList.add(subStringChar+id);
					}
					
					log.info("actual LSF Desc :: "+actualFileList);
					log.info("expected LSF Desc :: "+nameList);
					javaUtils.compareListOnIndexBased(actualFileList, nameList);
					
				} else
					javaUtils.compareListOnIndexBased(actualFileList, lastFilesNameList);
			}
			else {

				executeJScript(AdoddleCommonJQueries.scrollDownScrollJquery);
				int lastIndex = (getSelectedColumnList(columnName).size() - 1);
				log.info("lastIndex : " + lastIndex);

				mouseHover(getSelectedColumnLastIndex(lastIndex, columnName));

//				if (columnName.equalsIgnoreCase("Date") || columnName.equalsIgnoreCase("Updated Date") || columnName.equalsIgnoreCase("Access Date") || columnName.equalsIgnoreCase("DatePicker") || columnName.equalsIgnoreCase("Last Updated")) {
				if (columnName.contains(AdoddleCommonStringPool.COLUMN_DATE) || columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_LAST_UPDATED)) {
					for (WebElement file : getSelectedColumnList(columnName)) {
						getDateList.add(file.getText());
					}
					actualFileList.addAll(doSort());
					getDateList.clear();
				}
				else {
					List<Integer> idList = new ArrayList<Integer>();
					for (WebElement file : getSelectedColumnList(columnName)) {
						if(columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_ID) || columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_TRANSMITTAL_NO))
							idList.add(Integer.parseInt(file.getText().substring(3)));
						else
							actualFileList.add(file.getText());
					}
					if(columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_ID) || columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_TRANSMITTAL_NO)) {
						Collections.sort(idList, Collections.reverseOrder());
						for (Integer id : idList)
							actualFileList.add(Integer.toString(id));
					} else
						Collections.sort(actualFileList, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
				}
				log.info("actualFileList : " + filesOrder + " :: " + actualFileList);

//				if (columnName.equalsIgnoreCase("Date") || columnName.equalsIgnoreCase("Updated Date") || columnName.equalsIgnoreCase("Access Date") || columnName.equalsIgnoreCase("DatePicker") || columnName.equalsIgnoreCase("Last Updated")) {
				if (columnName.contains(AdoddleCommonStringPool.COLUMN_DATE) || columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_LAST_UPDATED)) {
					for (WebElement file : getSelectedColumnList(columnName)) {
						getDateList.add(file.getText());
					}
					expectedFileList.addAll(doSort());
					getDateList.clear();
				}
				else {
					List<Integer> idList = new ArrayList<Integer>();
					for (String file : firstFilesNameList) {
						if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_ID) || columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_TRANSMITTAL_NO))
							idList.add(Integer.parseInt(file));
						else
							expectedFileList.add(file);
					}
					
					if(columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_ID) || columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_TRANSMITTAL_NO)) {
						Collections.sort(idList, Collections.reverseOrder());
						for (Integer id : idList)
							expectedFileList.add(Integer.toString(id));
						
					} else
						Collections.sort(expectedFileList, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
				}
				log.info("expectedFileList : " + filesOrder + " :: " + expectedFileList);
				
				if(columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_ID) || columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_TRANSMITTAL_NO)) {
					List<String> expectedList = new ArrayList<String>();
					for (String id : expectedFileList) {
						switch (id.length()) {
							case 1:
								expectedList.add(subStringChar + "00" + id);
								break;
							case 2:
								expectedList.add(subStringChar + "0" + id);
								break;
							default:
								expectedList.add(subStringChar + id);
								break;
						}
					}
					
					List<String> actualList = new ArrayList<String>();
					for (String id : actualFileList) {
						switch (id.length()) {
							case 1:
								actualList.add(subStringChar + "00" + id);
								break;
							case 2:
								actualList.add(subStringChar + "0" + id);
								break;
							default:
								actualList.add(subStringChar + id);
								break;
						}
					}
					
					log.info("actual FSL Desc :: "+actualList);
					log.info("expected FSL Desc :: "+expectedList);
					javaUtils.compareListOnIndexBased(actualList, expectedList);
					
				} else
					javaUtils.compareListOnIndexBased(actualFileList, expectedFileList);
			}
		}
		else {

			if (filesOrder.contains("First set as First")) {

				i = 0;
				for (WebElement file : getSelectedColumnList(columnName)) {
					actualFileList.add(file.getText());
					if (i == counter)
						break;
					i++;
				}
				log.info("actualFileList : " + filesOrder + " :: " + actualFileList);
				
				if(columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_ID) || columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_TRANSMITTAL_NO)) {
					List<String> nameList = new ArrayList<String>();
					for (String id : firstFilesNameList) {
						switch (id.length()) {
							case 1:
								nameList.add(subStringChar + "00" + id);
								break;
							case 2:
								nameList.add(subStringChar + "0" + id);
								break;
							default:
								nameList.add(subStringChar + id);
								break;
						}
					}
					
					log.info("actual FSF Aesc :: "+actualFileList);
					log.info("expected FSF Aesc :: "+nameList);
					javaUtils.compareListOnIndexBased(actualFileList, nameList);
					
				} else
					javaUtils.compareListOnIndexBased(actualFileList, firstFilesNameList);

			}
			else {

				executeJScript(AdoddleCommonJQueries.scrollDownScrollJquery);
				int lastIndex = (getSelectedColumnList(columnName).size() - 1);
				log.info("lastIndex : " + lastIndex);

				mouseHover(getSelectedColumnLastIndex(lastIndex, columnName));
//				if (columnName.equalsIgnoreCase("Date") || columnName.equalsIgnoreCase("Updated Date") || columnName.equalsIgnoreCase("Access Date") || columnName.equalsIgnoreCase("DatePicker") || columnName.equalsIgnoreCase("Last Updated")) {
				if (columnName.contains(AdoddleCommonStringPool.COLUMN_DATE) || columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_LAST_UPDATED)) {
					for (WebElement file : getSelectedColumnList(columnName)) {
						getDateList.add(file.getText());
					}
					actualFileList.addAll(doSort());
					getDateList.clear();
				}
				else {
					List<Integer> idList = new ArrayList<Integer>();
					for (WebElement file : getSelectedColumnList(columnName)) {
						if(columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_ID) || columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_TRANSMITTAL_NO))
							idList.add(Integer.parseInt(file.getText().substring(3)));
						else
							actualFileList.add(file.getText());
					}
					if(columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_ID) || columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_TRANSMITTAL_NO)) {
						Collections.sort(idList, Collections.reverseOrder());
						for (Integer id : idList)
							actualFileList.add(Integer.toString(id));
					} else
						Collections.sort(actualFileList, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
				}
				log.info("actualFileList : " + filesOrder + " :: " + actualFileList);
				
				if(columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_ID) || columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_TRANSMITTAL_NO)) {
					List<String> expectedList = new ArrayList<String>();
					for (String id : lastFilesNameList) {
						switch (id.length()) {
							case 1:
								expectedList.add(subStringChar + "00" + id);
								break;
							case 2:
								expectedList.add(subStringChar + "0" + id);
								break;
							default:
								expectedList.add(subStringChar + id);
								break;
						}
					}
					
					List<String> actualList = new ArrayList<String>();
					for (String id : actualFileList) {
						switch (id.length()) {
							case 1:
								actualList.add(subStringChar + "00" + id);
								break;
							case 2:
								actualList.add(subStringChar + "0" + id);
								break;
							default:
								actualList.add(subStringChar + id);
								break;
						}
					}
					
					log.info("actual LSL Aesc :: "+actualList);
					log.info("expected LSL Aesc :: "+expectedList);
					javaUtils.compareListOnIndexBased(actualList, expectedList);
					
				} else
					javaUtils.compareListOnIndexBased(actualFileList, lastFilesNameList);
			}
		}
	}

	private List<String> doSort() throws ParseException {
		String dateFormat = getDateFormat();
		List<String> result = new ArrayList<String>();
		List<String> dashMarkList = new ArrayList<String>();
		List<Date> dates = new ArrayList<Date>();

		for (String monthYearStr : getDateList) {
			if (!monthYearStr.equalsIgnoreCase("--"))
				dates.add(new SimpleDateFormat(dateFormat).parse(monthYearStr));
			else
				dashMarkList.add(monthYearStr);
		}
		Collections.sort(dates, new Comparator<Date>() {
			public int compare(Date o1, Date o2) {
				return -1 * o1.compareTo(o2);
			}
		});
		for (Object d : dates.toArray())
			result.add(new SimpleDateFormat(dateFormat).format((Date) d));
		result.addAll(dashMarkList);
		return result;
	}

	private String getDateFormat() {
		if (isDisplayed(GlobalPageElements.img_UkCountryLabel))
			return "dd-MMM-yyyy";
		else if (isDisplayed(GlobalPageElements.img_UsCountryLabel))
			return "MMM dd, yyyy";
		else if (isDisplayed(GlobalPageElements.img_AusCountryLabel))
			return "dd/MM/yyyy";
		else
			return "dd MMM yyyy";
	}

	private List<WebElement> getSelectedColumnList(String columnName) {
		if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_FILE_NAME))
			return findElements(FilesTab.css_NumberOfFiles);
		else if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_DOC_REF)) {
			if (findElements(FilesTab.css_DocListingDocRefList).size() > 0) {
				flag = true;
				return findElements(FilesTab.css_DocListingDocRefList);
			}
			else {
				flag = false;
				return findElements(DiscussionsTab.css_DiscussionsTabDocRefList);
			}
		}
		else if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_DOC_TITLE)) {
			if (findElements(FilesTab.css_DocListingDocTitleList).size() > 0) {
				flag = true;
				return findElements(FilesTab.css_DocListingDocTitleList);
			}
			else {
				flag = false;
				return findElements(DiscussionsTab.css_DiscussionsTabDocTitleList);
			}
		}
		else if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.REV)) {
			if (findElements(FilesTab.css_DocListingRevisionList).size() > 0) {
				flag = true;
				return findElements(FilesTab.css_DocListingRevisionList);
			}
			else {
				flag = false;
				return findElements(DiscussionsTab.css_DiscussionsTabRevList);
			}
		}
		else if (columnName.equalsIgnoreCase("LettersNumbersTextbox"))
			return findElements(FilesTab.css_DocListingLettersNumbersTextboxList);
		else if (columnName.equalsIgnoreCase("Cities"))
			return findElements(FilesTab.css_DocListingCitiesList);
		else if (columnName.contains("Radio"))
			return findElements(FilesTab.css_DocListingRadioList);
		else if (columnName.contains("DatePicker"))
			return findElements(FilesTab.css_DocListingDatePickerList);
		else if (columnName.equalsIgnoreCase("Form Title"))
			return findElements(ProjectFormsTab.css_ProjectFormsTabFormTitleList);
		else if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_ID))
			return findElements(ProjectFormsTab.css_ProjectFormsTabFormIDList);
		else if (columnName.equalsIgnoreCase("Form Type"))
			return findElements(ProjectFormsTab.css_FormsTabFormTypeList);
		else if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_LAST_UPDATED))
			return findElements(ProjectFormsTab.css_NumberOfFormsUpdatedDate);
		else if(columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_CREATED_DATE))
			return findElements(ProjectFormsTab.css_NumberOfFormsCreatedDate);
		else if (columnName.equalsIgnoreCase("Status"))
			return findElements(ProjectFormsTab.css_FormsTabStatusList);
		else if (columnName.equalsIgnoreCase("Model Name"))
			return findElements(ModelsTab.css_ModelsTabModelNameList);
		else if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_DATE))
			return findElements(ModelsTab.css_ModelsTabModelCreationDateList);
		else if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_UPDATED_DATE))
			return findElements(ModelsTab.css_ModelsTabModelUpdateDateList);
		else if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_ACCESS_DATE))
			return findElements(ModelsTab.css_ModelsTabModelAccessDateList);
		else if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_ACCESSED_BY))
			return findElements(ModelsTab.css_ModelsTabLastModelAccessUserList);
		else if (columnName.equalsIgnoreCase("Title"))
			return findElements(DiscussionsTab.css_DiscussionsTabDiscussionTitleList);
		else if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_TRANSMITTAL_NO))
			return findElements(FilesTab.css_DocListingDistributionTabTransmittalNoList);
		else if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_ASSIGNED_DATE))
			return findElements(FilesTab.css_DocListingDistributionTabAssignedDateList);
		else if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_ASSIGNED_BY))
			return findElements(ProjectFormsTab.css_FormListingDistributionTabAssignedByList);
		else {
			AutomationAssert.verifyTrue("Column not found",false);
			return null;
		}
	}

	private By getSelectedColumnLastIndex(int index, String columnName) {
		if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_FILE_NAME))
			return By.xpath(".//div[@index='" + index + "']//div[contains(@class,'Filename')]//a");
		else if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_DOC_REF)) {
			if (flag)
				return By.xpath(".//div[@index='" + index + "']//div[contains(@class,'docRef')]//a");
			else
				return By.xpath(".//div[@index='" + index + "']//div[contains(@class,'docRef')]//div");
		}
		else if (columnName.equalsIgnoreCase("Doc Title")) {
			if (flag)
				return By.xpath(".//div[@index='" + index + "']//div[contains(@class,'description')][text()]");
			else
				return By.xpath(".//div[@index='" + index + "']//div[contains(@class,'docTitle')]//div");
		}
		else if (columnName.equalsIgnoreCase("Rev")) {
			if (flag)
				return By.xpath(".//div[@index='" + index + "']//div[contains(@class,'revisionNum')]//a");
			else
				return By.xpath(".//div[@index='" + index + "']//div[contains(@class,'revisionNum')]//div");
		}
		else if (columnName.equalsIgnoreCase("LettersNumbersTextbox"))
			return By.xpath(".//div[@index='" + index + "']//div[contains(@class,'LettersNumbersTextbox')][text()]");
		else if (columnName.equalsIgnoreCase("Cities"))
			return By.xpath(".//div[@index='" + index + "']//div[contains(@class,'Cities')]//div");
		else if (columnName.contains("Radio"))
			return By.xpath(".//div[@index='" + index + "']//div[contains(@class,'Radio')][text()]");
		else if (columnName.contains("DatePicker"))
			return By.xpath(".//div[@index='" + index + "']//div[contains(@class,'DatePicker')][text()]");
		else if (columnName.equalsIgnoreCase("Form Title"))
			return By.xpath(".//div[@index='" + index + "']//div[contains(@class,'title')]//a");
		else if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_ID))
			return By.xpath(".//div[@index='" + index + "']//div[contains(@class,'code')]//a[text()]");
		else if (columnName.equalsIgnoreCase("Form Type"))
			return By.xpath(".//div[@index='" + index + "']//div[contains(@class,'formTypeName')][text()]");
		else if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_LAST_UPDATED))
			return By.xpath(".//div[@index='" + index + "']//div[contains(@class,'updated')][text()]");
		else if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_CREATED_DATE))
			return By.xpath(".//div[@index='" + index + "']//div[contains(@class,'formCreationDate')]//div[text()]");
		else if (columnName.equalsIgnoreCase("Status"))
			return By.xpath(".//div[@index='" + index + "']//div[contains(@class,'status')][text()]");
		else if (columnName.equalsIgnoreCase("Model Name"))
			return By.xpath(".//div[@index='" + index + "']//div[contains(@class,'ModelName')]//a");
		else if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_DATE))
			return By.xpath(".//div[@index='" + index + "']//div[contains(@class,'modelCreationDate')][text()]");
		else if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_UPDATED_DATE))
			return By.xpath(".//div[@index='" + index + "']//div[contains(@class,'lastUpdateDate')][text()]");
		else if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_ACCESS_DATE))
			return By.xpath(".//div[@index='" + index + "']//div[contains(@class,'lastAccessModelDate')][text()]");
		else if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_ACCESSED_BY))
			return By.xpath(".//div[@index='" + index + "']//div[contains(@class,'lastAccessBy')]//a");
		else if (columnName.equalsIgnoreCase("Title"))
			return By.xpath(".//div[@index='" + index + "']//div[contains(@class,'CommentTitle')]//a");
		else if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_TRANSMITTAL_NO))
			return By.xpath(".//div[@index='" +index+ "']//div[contains(@class,'transNum')][text()]");
		else if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_ASSIGNED_DATE))
			return By.xpath(".//div[@index='" +index+ "']//div[contains(@class,'actionDate')][text()]");
		else if (columnName.equalsIgnoreCase(AdoddleCommonStringPool.COLUMN_ASSIGNED_BY))
			return By.xpath(".//div[@index='" +index+ "']//div[contains(@class,'assignedBy')]//a");
		else {
			AutomationAssert.verifyTrue("Column not found", false);
			return null;
		}
	}

	public void verifyColumnSortingDisabled(String column1, String column2, String column3, String column4, String column5, String column6, String column7) {
		String[] columnArray = { column1, column2, column3, column4, column5, column6, column7 };
		List<WebElement> columnHeaders;
		for (String column : columnArray) {
			waitForCompletePageLoad();
			int columnCounter = 1;
			columnHeaders = findElements(GlobalPageElements.css_GlobalListingHeaderColumns);
			flag = false;
			for (WebElement wE : columnHeaders) {

				mouseHover(wE);
				log.info("Printing Column name: " + wE.getText());

				if (getElementText(wE).equalsIgnoreCase(column)) {
					flag = true;
					log.info("Required Column name: " + wE.getText());
					clickElementAndWait(wE);
					waitForCompletePageLoad();
					mouseHover(wE);
					log.info("Column Sorting Disable : " + !isDisplayed(By.xpath(".//div[@id='listing']//div[@id='adTableHead']//div[contains(@id,'columnId')][" + columnCounter + "]//div[contains(@title,'Sorted')]")));
					AutomationAssert.verifyTrue(!isDisplayed(By.xpath(".//div[@id='listing']//div[@id='adTableHead']//div[contains(@id,'columnId')][" + columnCounter + "]//div[contains(@title,'Sorted')]")));
					break;
				}
				else
					flag = false;
				columnCounter++;
			}
			if (!flag)
				AutomationAssert.verifyTrue(flag);
		}
	}
	
	
	public void scrollDownDistributionPage(String columnName)
	{
		int i=1, count = Integer.parseInt(getElementText(GlobalPageElements.lbl_ActiveTabListingCount).split(" - ")[1].trim().replace(")", ""));
		
		while (! isDisplayed(getSelectedColumnLastIndex(count-1, columnName))) {
			
			log.info("Scrolldown Count : "+i);
			executeJScript(AdoddleCommonJQueries.scrollDownScrollJquery);
			waitForCompletePageLoad();
			
			try {
				mouseHoverElement(getSelectedColumnLastIndex(count-1, columnName));
				waitForCompletePageLoad();
			} catch(Throwable t) {
				log.info(": Element not displayed :");
			}
			
			if(i==100) {
				AutomationAssert.verifyTrue("There are large amout of Distributions data more than 5000", false);
				break;
			}
			i++;
		}
	}
}