package org.asite.automation.adoddle.p2.scripts;

import java.util.ArrayList;
import java.util.List;

import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class CustomisedViewFieldTabScripts extends AdoddleCommonAppMethods {
	
	public static By	css_AdoddleAllTabColumnsNameList			=	 By.cssSelector("div[class='divthead divtr'] div[id]");
	public static By	drp_CustomiseColumnDropDown					=	 By.cssSelector("div[class='customizeLabel'] span");
	public static By	btn_CustomiseColumnSaveButton				=	 By.cssSelector("div[class='saveCancel'] input[id='Save']");
	public static By	drp_CustomizeSelectedFieldsDropdown			=	 By.cssSelector("div[class='selectedField'] select[class='listbox']");
	public static By	drp_CustomizeAvailbaleFieldsDropdown		=	 By.cssSelector("div[class='available'] select[class='listbox']");
	public static By	btn_CustomizeMoveToAvailableFieldsButton	=	 By.cssSelector("div[id='moveToAvailableItem']");
	public static By	btn_CustomizeMoveToSelectedFieldsButton		=	 By.cssSelector("div[id='moveToSelectedItem']");
	public static By	css_CustomizeSelectedFieldsOptionsList		=	 By.cssSelector("select[class='listbox'] option");
	public static By	btn_CustomizeMoveToUpButton					=	 By.cssSelector("div[id='moveUp']");
	
	int i,j, counter;
	private Action				dragAndDrop;
	private List<String> 		removableColumnList = new ArrayList<String>();
	private String				beforeMoveElementWidth, afterMoveElementWidth;
	private WebElement			customWidthElement, resizedWidthElement, resizedWidthElement2;
	
	
	public void getRemovableColumnName(){
				
		int i=0;
		for (WebElement allColumnName : findElements(GlobalPageElements.css_AdoddleAllTabColoumsNameList)) {
			
			if(i==4)
				break;
			
			if(i==2 || i==3) {
				if(allColumnName.getText().equalsIgnoreCase("T"))
					removableColumnList.add(allColumnName.getText().replace("T", "Type"));
				else
					removableColumnList.add(allColumnName.getText());
			}
				
			i++;
		}
	}
	
	public void removeAttributesFromSelectedFields(){
		
		clickElementAndWaitForElement(drp_CustomiseColumnDropDown, btn_CustomiseColumnSaveButton);
		for (String coloumField : removableColumnList) {
			selectByVisibleText(drp_CustomizeSelectedFieldsDropdown, coloumField);
			clickElementAndWait(btn_CustomizeMoveToAvailableFieldsButton);
					
		}
	}
	
	public void clickOnSaveButtonOfCustomizedView() {
		clickElementAndWait(btn_CustomiseColumnSaveButton);
		waitUntilElementIsInvisible(GlobalPageElements.ele_overLayProcess);
	}
	
	public void verifyCustomizedAttributes(){
		
		List<String> removedColumnList = new ArrayList<String>();	
		for (WebElement ColumnName : findElements(GlobalPageElements.css_AdoddleAllTabColoumsNameList)){
			removedColumnList.add(ColumnName.getText());
		}
			
			AutomationAssert.verifyTrue("Attributes are same, not moved" , (!removedColumnList.containsAll(removableColumnList)));
			log.info("coloumName : " + removedColumnList);		
	}
	
	public void addAttributesBackToSelectedFields(){

		List<String> removedColumnList = new ArrayList<String>();
		for (WebElement ColumnName : findElements(GlobalPageElements.css_AdoddleAllTabColoumsNameList)){
			removedColumnList.add(ColumnName.getText());
		}
		clickElementAndWaitForElement(drp_CustomiseColumnDropDown, btn_CustomiseColumnSaveButton);
		for(String columnField : removableColumnList){
			selectByVisibleText(drp_CustomizeAvailbaleFieldsDropdown, columnField);
			clickElementAndWait(btn_CustomizeMoveToSelectedFieldsButton);	
		}
		clickOnSaveButtonOfCustomizedView();
		log.info("Removed column list: " + removableColumnList);
		
		List<String> allColumnList = new ArrayList<String>();
		//waitUntilElementCountToBe(GlobalPageElements.css_AdoddleAllTabColoumsNameList, removableColumnList.size());
		List<WebElement> xyzList = findElements(GlobalPageElements.css_AdoddleAllTabColoumsNameList);
		
		for (WebElement ColumnName : xyzList){
			allColumnList.add(ColumnName.getText());
		}
		
		AutomationAssert.verifyTrue("Attributes are same, not moved" , (allColumnList.containsAll(removedColumnList)));
		log.info("All expected column list after adding : " + allColumnList);
	}
	
	public void verifyAttributesAtLastPosition(){
		List<String> allColumnList = new ArrayList<String>();
		List<WebElement> columnElements = findElements(By.cssSelector("div[id='adTableHead'] div[id*='columnId']"));
		for (WebElement columnElement : columnElements){
			mouseHoverWebElement(columnElement);
			if(columnElement.getText().equalsIgnoreCase("T"))
				allColumnList.add("Type");
			else
				allColumnList.add(columnElement.getText());
		}
		log.info("All columns : " + allColumnList);
		
		AutomationAssert.verifyTrue(allColumnList.get(allColumnList.size() -1) +" != "+ removableColumnList.get(1), allColumnList.get(allColumnList.size() -1).equalsIgnoreCase(removableColumnList.get(1)));
		AutomationAssert.verifyTrue(allColumnList.get(allColumnList.size() -2) +" != "+ removableColumnList.get(0), allColumnList.get(allColumnList.size() -2).equalsIgnoreCase(removableColumnList.get(0)));
	}

	
	public void movedColoumsUsingCustomizedView() {
		clickElementAndWaitForElement(drp_CustomiseColumnDropDown, btn_CustomiseColumnSaveButton);

//		List<String> selectedColoumList = new ArrayList<String>();
		
		i = javaUtils.resetIndex(i, 0);
		for (String selectedColoum : removableColumnList) {

			new Actions(getWebDriver()).keyDown(Keys.CONTROL);
			for (WebElement option : findElements(css_CustomizeSelectedFieldsOptionsList)) {
				if (option.getText().equalsIgnoreCase(selectedColoum)) {
					option.click();
					i++;
					break;
				}
			}
			new Actions(getWebDriver()).keyUp(Keys.CONTROL);
		}

			String customizeOption = "option[5]";
			int counter = 0;
			while (!getElementText(By.xpath(".//div[@id='bodyPart']//div[@class='selectedField']//select[@class='listbox']//" + customizeOption + "")).equalsIgnoreCase(removableColumnList.get(0))) {
				clickElement(GlobalPageElements.btn_CustomizeMoveUpButton);
			
				counter++;
				if (counter > 50) {
					log.info(": There is some thing wrong Input or Locator :");
					Assert.assertTrue(false);
					break;
			}
		}
	}
	
	
	public void getMovedColumnWidthAndCustomizeColumn(String activeTab) throws InterruptedException {
		if (!getCustomizeColoumDataType(3).equalsIgnoreCase("img") && !getCustomizeColoumDataType(4).equalsIgnoreCase("img")) {
				getMovedColumnWidthAndCustomizeColumn();
			}
			else {
				log.info(": It's Image Coloums, Can't Resized : Skip Test");
			}
		}
	
	
	 public void getMovedColumnWidthAndCustomizeColumn() {
	 
		customWidthElement = getCustomizedWidthColoum();
		resizedWidthElement = getResizedWidthColoum(4);
		resizedWidthElement2 = getResizedWidthColoum(5);
		
		beforeMoveElementWidth = customWidthElement.getAttribute("style").split("width: ")[1].replace("px;", "");
		log.info("beforeMoveElementWidth :" + beforeMoveElementWidth);

		dragAndDrop = new Actions(getWebDriver()).clickAndHold(resizedWidthElement).moveToElement(resizedWidthElement2, resizedWidthElement2.getLocation().getX(), resizedWidthElement2.getLocation().getY()).release(resizedWidthElement2).build();
		dragAndDrop.perform();
	}
	
	public void verifyCustomizeMovedColumnWidth1() {
		customWidthElement = getCustomizedWidthColoum();

		afterMoveElementWidth = customWidthElement.getAttribute("style").split("width: ")[1].replace("px;", "");
		log.info("afterMoveElementWidth :" + afterMoveElementWidth);

		{
			i = javaUtils.resetIndex(i, 4);
			j = javaUtils.resetIndex(j, 5);
		}
		log.info("Verify Tab Position-01 : " + getSelectedColoumOfActiveTab(i).getAttribute("title").split(" - ")[0] + " :: " + removableColumnList.get(0));
		log.info("Verify Tab Position-02 : " + getSelectedColoumOfActiveTab(j).getAttribute("title").split(" - ")[0] + " :: " + removableColumnList.get(1));

		Assert.assertTrue(getSelectedColoumOfActiveTab(i).getAttribute("title").split(" - ")[0].contains(removableColumnList.get(0)));
		Assert.assertTrue(getSelectedColoumOfActiveTab(j).getAttribute("title").split(" - ")[0].contains(removableColumnList.get(1)));
		Assert.assertTrue("Width " + Integer.parseInt(beforeMoveElementWidth) + " should be less than " + Integer.parseInt(afterMoveElementWidth), Integer.parseInt(beforeMoveElementWidth) < Integer.parseInt(afterMoveElementWidth));
	
	
	}
	
	public WebElement getResizedWidthColoum(int i) {
		return findElement(By.xpath(".//div[@id='adTableHead']//div[contains(@id,'columnId')][" + i + "]//span[@class='resize']"));
	}
	
	public WebElement getCustomizedWidthColoum(){
		
		i = javaUtils.resetIndex(i, 4);
		return findElement(By.xpath(".//div[@id='adTableHead']//div[contains(@id,'columnId')][" + i + "]"));
	}
	
	public WebElement getSelectedColoumOfActiveTab(int index) {
		
			return findElement(By.xpath(".//div[@id='adTableHead']//div[contains(@id,'columnId')][" + index + "]//span[not(contains(@class,'resize'))][not(contains(@style,'hidden'))][not(contains(@id,'ImageName'))][not(contains(@id,'typeImage'))] | //div[@id='adTableHead']//div[contains(@id,'columnId')][" + index + "]//img[@title]"));
	}
	
	public String getCustomizeColoumDataType(int i) {
		return findElement(By.xpath(".//div[@id='adTableHead']//div[contains(@id,'columnId')][" + i + "]")).getAttribute("data-coltype");
	}

}
