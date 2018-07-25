package org.asite.automation.CommonLocators;

import org.openqa.selenium.By;

public class AdoddleDashboardLocators {
	
	public static class DashboardTab {
		
		public static By	btn_HeaderUserLogin												= By.xpath(".//*[@id='header_userlogin' and @filterkey='userlogin']");
		public static By	lbl_DashboardTitle												= By.cssSelector("h2[id='dashboar-title']");
		public static By	lbl_DashboardIncompleteActionsCount								= By.cssSelector("div[id='left-nav-blocks'] a[id='sidenav-dashboard-incompleteaction'] span");
		public static By	css_DashboardTopActionForms										= By.cssSelector("div[id='commuContentContainer'] div[id='listing'] div[id='adTableBody'] div[class*='rows']");
		public static By	css_DashboardBrandingBomBardier									= By.cssSelector("span[alt='AutoTest- ! @ $ ^ & ( ) - = + { } , . ] `Org'][title='AutoTest- ! @ $ ^ & ( ) - = + { } , . ] `Org']");
		public static By	lnk_DashboardIncompleteActions									= By.cssSelector("div[id='left-nav-blocks'] a[id='sidenav-dashboard-incompleteaction']");
		public static By	css_DashboardIncompleteFormsActions								= By.cssSelector("div[id='searchcontent_Commu'] div[id='adTableBody'] div[class*='col-actions-actionName-actionTime-fixed-width'] a[class='completeAction']");
		public static By	css_DashboardIncompleteDiscussionsActions						= By.cssSelector("div[id='discussionContentContainer'] div[id='adTableBody'] div[class*='col-actions-actionName-actionTime-fixed-width'] a[class='completeAction']");
		public static By	lnk_DashboardOverdueActions										= By.cssSelector("div[id='left-nav-blocks'] a[id='sidenav-dashboard-overdue']");
		public static By	lbl_DashboardOverDueActionsCount								= By.cssSelector("div[id='left-nav-blocks'] a[id='sidenav-dashboard-overdue'] span");
		public static By	lnk_DashboardDueTodayActions									= By.cssSelector("div[id='left-nav-blocks'] a[id='sidenav-dashboard-duetoday']");
		public static By 	lbl_DashboardIncompleteDiscussionsCount							= By.cssSelector("div[id='left-nav-blocks'] a[id='sidenav-dashboard-discuss-unread'] span");
		public static By	lbl_DashboardDueTodayActionsCount								= By.cssSelector("div[id='left-nav-blocks'] a[id='sidenav-dashboard-duetoday'] span");
		/*
		public static By	css_DashboardTopActionFiles										= By.cssSelector("div[id='fileContentContainer'] div[id='listing'] div[id='adTableBody'] div[class*='rows']");
		public static By css_DashboardIncompleteFilesActions 								= By.cssSelector("div[id='fileContentContainer'] div[class*='rows'] div[id='popover-fileinfo'] a[class='completeAction']");
		public static By	css_DashboardOverdueFormsActions								= By.cssSelector("div[id='commuContentContainer'] div[id='adTableBody'] div[class*='col-actions-actionName-actionTime-fixed-width'] a[class='completeAction']");
		public static By	css_DashboardDueTodayFormsActions								= By.cssSelector("div[id='commuContentContainer'] div[id='adTableBody'] div[class*='col-actions-actionName-actionTime-fixed-width'] a[class='completeAction']");
		*/
		
		/* New Files Published Widgets */
		public static By	ele_NewFilePublishedPortlet										= By.cssSelector("div[class='portlet-content'] div[id='widgetbody-1']");
		public static By	ele_RecentFormsPortlet											= By.cssSelector("div[class='portlet-content'] div[id='widgetbody-2']");
		public static By	ele_NewFilePublishedLastLoginAxis								= By.cssSelector("div[id='widget--1'] div[id='widgetbody-1'] div[id*='highcharts'] g[class='highcharts-series-group'] g[class*='highcharts-series'] rect:nth-child(1)");
		public static By	ele_NewFilePublishedHiddenLastLoginAxis							= By.cssSelector("div[id='widget--1'] div[id='widgetbody-1'] div[id*='highcharts'] g[class*='highcharts-data-labels'] g:nth-child(1)");
		public static By	ele_NewFilePublishedHiddenTodayAxis								= By.cssSelector("div[id='widget--1'] div[id='widgetbody-1'] div[id*='highcharts'] g[class*='highcharts-data-labels'] g:nth-child(2)");
		public static By	ele_NewFilePublishedHiddenPaskWeekAxis							= By.cssSelector("div[id='widget--1'] div[id='widgetbody-1'] div[id*='highcharts'] g[class*='highcharts-data-labels'] g:nth-child(4)");
		public static By	ele_NewFilePublishedTodayAxis									= By.cssSelector("div[id='widget--1'] div[id='widgetbody-1'] div[id*='highcharts'] g[class='highcharts-series-group'] g[class*='highcharts-series'] rect:nth-child(2)");
		public static By	ele_NewFilePublishedPaskWeekAxis								= By.cssSelector("div[id='widget--1'] div[id='widgetbody-1'] div[id*='highcharts'] g[class='highcharts-series-group'] g[class*='highcharts-series'] rect:nth-child(4)");
		public static By	ele_RecentFormsLastLoginAxis									= By.cssSelector("div[title*='Recent Apps']+div[class='portlet-content'] div[id*='highcharts'] g[class='highcharts-series-group'] g[class*='highcharts-series'] rect:nth-child(1)");
		public static By	ele_RecentFormsHiddenLastLoginAxis								= By.cssSelector("div[id='widget--2'] div[id='widgetbody-2'] div[id*='highcharts'] g[class*='highcharts-data-labels'] g:nth-child(1)");
		public static By	ele_RecentFormsHiddenTodayAxis									= By.cssSelector("div[id='widget--2'] div[id='widgetbody-2'] div[id*='highcharts'] g[class*='highcharts-data-labels'] g:nth-child(2)");
		public static By	ele_RecentFormsHiddenYesterdayAxis								= By.cssSelector("div[id='widget--2'] div[id='widgetbody-2'] div[id*='highcharts'] g[class*='highcharts-data-labels'] g:nth-child(3)");
		public static By	ele_RecentFormsHiddenPaskWeekAxis								= By.cssSelector("div[id='widget--2'] div[id='widgetbody-2'] div[id*='highcharts'] g[class*='highcharts-data-labels'] g:nth-child(4)");
		public static By	ele_RecentFormsTodayAxis										= By.cssSelector("div[title*='Recent Apps']+div[class='portlet-content'] div[id*='highcharts'] g[class='highcharts-series-group'] g[class*='highcharts-series'] rect:nth-child(2)");
		public static By	ele_RecentFormsPaskWeekAxis										= By.cssSelector("div[title*='Recent Apps']+div[class='portlet-content'] div[id*='highcharts'] g[class='highcharts-series-group'] g[class*='highcharts-series'] rect:nth-child(4)");
		public static By	ele_NewFilePublishedMouseHoverFileCount							= By.xpath(".//div[contains(@id,'highcharts')]//span[contains(text(),'Files')]");
		public static By	ele_RecentFormsMouseHoverFormCount								= By.xpath(".//div[contains(@id,'highcharts')]//span[contains(text(),'Apps')]");
		
		/* Recent Forms Widgets */
		public static By	css_RecentFormsFormCheckList									= By.xpath(".//div[@index]//div[contains(@class,'filelistchkbox')]//input[@type='checkbox']");
		public static By	txt_RecentFormsDistributeFormToField							= By.xpath(".//div[contains(@id,'inptAppsDistActionTo')]//ul//li[@class='select2-search-field']//input[contains(@id,'s2id')]");
		public static By	chk_PopDashboardActionsCheckbox									= By.xpath(".//div[contains(@style,'display: block')]//input[@type='checkbox']");
		public static By	btn_PopDashboardActionsSubmitButton								= By.xpath(".//div[contains(@style,'display: block')]//input[@type='button']");
		public static By	txt_PopDashboardActionsTextareaInput							= By.xpath(".//div[contains(@style,'display: block')]//textarea");
		public static By	txt_RespondFormGroupCodeInput									= By.xpath(".//table[@class='xdLayout']//input[contains(@id,'FORMGROUPCODE')]");
		public static By	txt_RespondFormMessageStatusInput								= By.xpath(".//div[contains(@id,'myFields')]//input[contains(@id,'MSGSTATUS')]");
		/*
		public static By	ele_RecentFormsViewFormTitle									= By.xpath(".//div[@id='viewFormHeaderTable']//div[contains(@class,'firstcol')]/span[text()]");
		public static By	lbl_StatusChangePopLabel										= By.xpath(".//div[contains(@style,'display: block')][@id='FormChangeStatusModal']//h3[text()]");
		public static By	lbl_PopStatusChangeFormIDLabel									= By.xpath(".//span[@id='formID'][text()]");
		public static By	lbl_PopStatusChangeFormTitleLabel								= By.xpath(".//span[@id='formTitle'][text()]");
		public static By	lbl_PopStatusChangeFormCurrentStatusLabel						= By.xpath(".//span[@id='formStatusFrom'][text()]");
		public static By	css_ViewFormLHPanelTabList										= By.xpath(".//div[contains(@class,'left')]//div[contains(@id,'left')]//ul//li//a[contains(@id,'formnav')][text()]");
		public static By	btn_ViewFormActionDropdownButton								= By.xpath(".//button[@id='header_export'][text()='Action']");
		public static By	btn_ViewFormActionDropdownEditAndDistributeButton				= By.xpath(".//div[@id='accordion']//span[@class='form-action-li-span'][text()='Edit and Distribute']");
		public static By	lnk_PopAttachmentAndAssociationAttachmentsTab					= By.xpath(".//div[@id='pageLayoutHeader']//div[@id]//a[contains(text(),'Attachments')]");
		public static By	css_PopAttachmentAndAssociationAttachedFileList					= By.xpath(".//div[contains(@style,'display: block')]//div[@index]//div[contains(@class,'fileName')]//a");
		public static By	css_RespondFormPrePopulatedUserList								= By.xpath(".//div[contains(@style,'block')]//ul[@class='select2-choices']//li//div[@title]");
		*/

		/* Dashboard Favourite Folder Widget */
		public static By	ele_DashboardFavouriteFolderIncompleteActionsCount				= By.xpath(".//div[@id='widgetbody-3']//li[@class='list-item']//span[contains(@class,'total-widget-action-count')]");
		public static By	css_DashboardFavouriteFoldersList								= By.xpath(".//div[@id='widgetbody-3']//li[@class='list-item']//div[@title][1]");
		
		/* Dashboard Favourite Forms Widget */
		public static By	css_DashboardFavouriteFormsList									= By.xpath(".//div[@id='widgetbody-4']//li[@class='list-item']//div[@title][1]");
		public static By	ele_DashboardFirstFavouriteFormActionCount						= By.xpath(".//div[@id='widgetbody-4']//li[@class='list-item']//span[contains(@class,'total-widget-action-count')]");
		
		/* Dashboard Favourite Models Widget */
		public static By	lnk_DashboardFavouriteModelsBlankMsg							= By.cssSelector("div[class='portlet-content'] div[id='widgetbody-6'] a");
		public static By	css_DashboardFavouriteModelList									= By.cssSelector("div[class='portlet-content'] div[id='widgetbody-6'] ul[style*='list-style'] div[class*='fav-name']");
		
		/* Manage Dashboard */
		public static By	ele_MaximizeGadget												= By.cssSelector("span[title='Maximize']");
		public static By	btn_DeleteGadget												= By.xpath(".//div[@class='widget-control-container']//span[@title='Delete']");
		public static By	ele_PaiChartRootTracker											= By.cssSelector("div[class='portlet-content'] svg");
		public static By	txt_PopDashboardTitleInput										= By.xpath(".//div[contains(@style,'display: block')]//input[@id='dashboardname']");
		public static By	txt_PopDashboardDescriptionInput								= By.xpath(".//div[contains(@style,'display: block')]//textarea[@id='description']");
		public static By	drp_PopDashboardCloneDashboardDropdown							= By.xpath(".//div[contains(@style,'display: block')]//select[@id='copyfrom']");
		public static By	css_ManageDashboardsPageDashboardNameList						= By.xpath(".//div[@id='manageDashboardTable']//td[@title][1]");
		public static By	btn_SwitchDashboardButton										= By.cssSelector("button[title='Switch Dashboard']");
		public static By	lbl_DashboardTitleLabel											= By.xpath(".//h2[@id='dashboar-title']");
		public static By	lnk_ClickhereToAddGadgetsLink									= By.xpath(".//a[@id='add-widget-link'][text()='Click here']");
		public static By	lnk_AddGadgetLink												= By.xpath(".//a[@title='Add Gadget']");
		public static By	txt_PopGadgetTitleInput											= By.xpath(".//div[contains(@style,'display: block')]//input[@id='widget_title']");
		public static By	txt_PopGadgetDescriptionInput									= By.xpath(".//div[contains(@style,'display: block')]//textarea[@name='widget_description']");
		public static By	css_DashboardGadgetsTitleList									= By.xpath(".//span[@id='widget-title'][text()]");
		public static By	txt_PopShareDashboardInput										= By.xpath(".//div[contains(@id,'dashuserlist')]//ul[@class='select2-choices']//li//input[contains(@id,'s2id_autogen')]");
		public static By	ele_PopDashboardSuggestedValue									= By.xpath(".//div[@id='select2-drop']//ul//li//span[text()]");
		public static By	css_PopAddGadgetHeaderList										= By.xpath(".//div[@class='widgetListItem']//div[@class='widgetListHead']");
		public static By	drp_FilterChartGadgetFilterDropdown								= By.xpath(".//div[@id='edit-content']//div[@class='modal-body widget-edit']//select[@id='filter']");
		public static By	drp_FilterChartGadgetStatisticsTypeDropdown						= By.xpath(".//div[@id='edit-content']//div[@class='modal-body widget-edit']//select[@id='stat']");
		public static By	chk_FilterChartGadgetShowLegendsCheckbox						= By.xpath(".//div[@id='edit-content']//div[@class='modal-body widget-edit']//input[@id='showlegends']");
		public static By	btn_FilterChartGadgetUpdateButton								= By.xpath(".//div[@id='edit-content']//div[@class='modal-body widget-edit']//button[text()='Update']");
		public static By	ele_FilterChartGadgetHighchartsTracker							= By.cssSelector("div[id='playground'] svg g[class*='highcharts-series highcharts-series-0']");
		public static By	css_FilterChartGadgetHighchartsTrackerFilterTypeList			= By.cssSelector("div[id='playground'] svg g[class*='highcharts-tracker'] g tspan[style*='bold']:nth-child(3)");
		public static By	css_FilterChartGadgetHighchartsTrackerFilterTypeCountList		= By.cssSelector("div[id='playground'] svg g[class*='highcharts-tracker'] g tspan[dx='0']:nth-child(2)");
		public static By	css_PopDashboardSharedUserProjectList							= By.xpath(".//div[contains(@id,'dashuserlist')]//ul[@class='select2-choices']//li//div[text()]");
		public static By	css_PopDashboardSharedUserProjectPrevilegesList					= By.xpath(".//body[contains(@id,'subscription')]//ul[@class='context-menu-list context-menu-root']//li[contains(@class,'permission')]//span[text()]");
		/*public static By	css_FilterChartGadgetHighchartsTrackerFilterTypeListWithCounts	= By.cssSelector("div[id='playground'] svg g[class*='highcharts-tracker'] g text");*/

		/* Dashboard Model Summary Widget */
		public static By	css_ModelSummaryGadgetHighchartsTrackerModelTypeList			= By.cssSelector("div[id*='modelsummary'] svg[class='highcharts-root'] g[class='highcharts-series-group'] path[class*='highcharts-point']");
		public static By	ele_ModelSummaryGadgetOnTooltipModelType						= By.cssSelector("div[id*='modelsummary'] svg[class='highcharts-root'] g[class*='highcharts-tooltip'] text[style*='fill'] tspan:nth-child(1)");
		public static By	ele_ModelSummaryGadgetOnTooltipModelTypeCount					= By.cssSelector("div[id*='modelsummary'] svg[class='highcharts-root'] g[class*='highcharts-tooltip'] text[style*='fill'] tspan:nth-child(2)");
	}
}
