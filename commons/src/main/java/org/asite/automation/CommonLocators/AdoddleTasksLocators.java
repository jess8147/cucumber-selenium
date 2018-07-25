package org.asite.automation.CommonLocators;

import org.openqa.selenium.By;

public class AdoddleTasksLocators {

	public static class TasksTab {

	
		public static By	txt_TasksFilterInput						= By.cssSelector("div[class*='tasks-main'] div[class='channel-task-header'] input[class*='task-search']");
		public static By	lbl_TaskCompleted							= By.cssSelector("div[class*='ui-notification'] div[class*='bold-msg ']");
		public static By	btn_CompleteTask							= By.cssSelector("button[title*='Complete Task']");
		public static By	css_TaskListingTaskName						= By.cssSelector("div[class*='item-row'] div[class='taskname'] a");
		public static By	lbl_TasksTaskPanelHeader					= By.cssSelector("div[class*='tasks-main'] section[class='tasks-panel'] h2");
		public static By	css_AdhocTaskListingTaskName				= By.cssSelector("div[class='taskname'] input[class*='taskname-input']");
		public static By	css_TaskListingTasksCount					= By.cssSelector("div[class*='tasks-main'] div[class='channel-task-list'] div[class*='task-list'] div[class*='drop-target-row']");
		public static By	css_AdhocTaskListingTasksCount				= By.cssSelector("div[class*='tasks-main'] div[class='channel-task-list'] div[class*='task-list'] div[class*='drop-target-row'][style*='cursor: pointer']");
		public static By	img_BetaViewViewFileActionCheckMark			= By.cssSelector("div[class*='action-list-component'] button i[class*='fa-check']");
		public static By	txt_BetaViewViewFileForActionTextArea		= By.cssSelector("div[class*='action-list-component'] li[class*='opened'] textarea");
		public static By	drp_BetaViewViewFileActionDropdown			= By.cssSelector("div[class*='action-list-component'] ul[class*='action-list-dropdown']");
		public static By	btn_BetaViewViewFileActionComplete			= By.xpath(".//div[contains(@class,'action-list-component')]//li[contains(@class,'opened')]//button[contains(text(),'Complete')]");
		public static By	lbl_BetaViewViewFileStatusChange			= By.xpath(".//custom-common-dropdown[@dd-title='Status Change']//main[@class='open']//h5");
		public static By	sel_BetaViewViewFileOldStatus				= By.xpath(".//custom-common-dropdown[@dd-title='Status Change']//main[@class='open']//strong[contains(@class,'old-status')]");
		public static By	sel_BetaViewViewFileStatusChangeDropdown	= By.xpath(".//custom-common-dropdown[@dd-title='Status Change']//main[@class='open']//select[@ng-model='status']");
		public static By	txt_BetaViewViewFileStatusChangeTextarea	= By.xpath(".//custom-common-dropdown[@dd-title='Status Change']//main[@class='open']//textarea");
		public static By	btn_BetaViewViewFileStatusChangeSave		= By.xpath(".//custom-common-dropdown[@dd-title='Status Change']//main[@class='open']//button[contains(text(),'Save')]");
		public static By	txt_BetaViewViewFileDistributeToUserField	= By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//input[contains(@class,'dist-inpt')]");
		public static By	txt_BetaViewViewFileDistributeSubject		= By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//input[@name='subject']");
		public static By	btn_BetaViewViewFileDistribute				= By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//button[contains(text(),'Distribute')]");
		public static By	btn_TasksGroupCreateLink					= By.cssSelector("div[class*='tasks-main'] a[class='create-btn']");
		public static By	txt_TasksGroupName							= By.cssSelector("div[class='channel-details'] form[class*='ng-pristine'] input[formcontrolname='channelName']");
		public static By	txt_TasksGroupProject						= By.xpath(".//adoddle-channel//form//label[text()='Project']//following::select2[not(@class)]//span[@role='combobox']//ul//li//input");
		public static By	css_TasksGroupProjectSearchResult			= By.xpath(".//span[@class='select2-results']//ul[@class='select2-results__options']//span");
		public static By	css_TasksGroupMemembersSearchResult			= By.xpath(".//span[@class='select2-results']//ul[@class='select2-results__options']//div");
		public static By	txt_TasksGroupMembers						= By.xpath(".//adoddle-channel//form//label[text()='Members']//following::span[@role='combobox']//ul//li//input");
		public static By	txt_TasksGroupDescriptions					= By.cssSelector("div[class='channel-details'] form[class='ng-dirty ng-valid ng-touched'] textarea[placeholder='Description']");
		public static By	btn_TasksGroupSave							= By.cssSelector("div[class='channel-details'] input[class*='save-channel']");
		public static By	lbl_TasksGroupNotificationMessage			= By.cssSelector("div[class*='simple-notification'] div[class*='sn-content']");
		public static By	txt_TasksGroupTaskTitle						= By.cssSelector("div[class*='tasks-main'] div[class='channel-task-list'] input[class*='taskname'][placeholder='Write a Task Title. Press Enter to create new']");
		public static By	lnk_TasksGroupAddTaskLink					= By.cssSelector("div[class*='tasks-main'] div[class='channel-task-list'] div[class='add-task-icon'] a[class*='view-task-details']");
		public static By	txt_TasksGroupSummary						= By.xpath(".//adoddle-task //form//label[contains(text(),'Task Title')]//following::input[@formcontrolname='taskSummary']");
		public static By	css_AdhocTaskListingTaskAsignee				= By.cssSelector("div[class*='item-row-right'] div[class*='task-assignee']");
		public static By	css_AdhocTaskListingTaskAsigneeClearLink	= By.cssSelector("div[class*='dropdown-menu show'] span[class*='choice__remove'] i");
		public static By	txt_AdhocTaskListingTaskAsigneeInput		= By.cssSelector("div[class*='dropdown-menu show'] input[placeholder='Assignee']");
		public static By	txt_AdhocTaskListingTaskAsigneeResult		= By.cssSelector("span[class='select2-results'] ul[id*='results'] div");
		public static By	btn_AdhocTaskListingTaskStatus				= By.cssSelector("div[class*='task-status'] button[title*='Task Status']");
		public static By	css_AdhocTaskListingTasksStatuses			= By.cssSelector("div[class*='task-status'] div[class*='dropdown-menu right'] button");
		public static By	btn_TasksFilter								= By.cssSelector("div[class*='tasks-main'] div[class='channel-task-header'] div[class*='task-filter'] button");
		public static By	css_TasksFilterDropdown						= By.cssSelector("div[class*='tasks-main'] div[class='channel-task-header'] div[class*='task-filter'] div[class*='task-filter']");
		public static By	lbl_TasksFilterDropdownStatusAll			= By.cssSelector("div[class*='task-filter'] div[class*='btn-group-toggle'][role='group'] label[title='All']");
		public static By	lbl_TasksFilterDropdownAsigneeAll			= By.cssSelector("div[class*='task-filter'] div[class*='btn-group-toggle'][role='group'] label[title='Valid for Group Tasks only.']");
		public static By	css_TasksGroupsList							= By.cssSelector("div[class*='tasks-main'] ul[style='display: block;'] li");
		public static By	css_TasksGroupEditIcon						= By.cssSelector("span[class*='fa-edit']");
		public static By	css_TasksEditGroupInactiveSwitchTab			= By.xpath(".//adoddle-channel//form//label[contains(text(),'Active')]//span[@class='switch']");
	}

}