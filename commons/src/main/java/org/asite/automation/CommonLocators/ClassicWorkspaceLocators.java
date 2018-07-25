package org.asite.automation.CommonLocators;

import org.openqa.selenium.By;

public class ClassicWorkspaceLocators {
	
	public static class WorkspaceTabPage
	{
		public static By lnk_AddNewWorkspace 						= By.cssSelector("div[id='wrapper'] div[id='main'] div[id='rh_content'] div[id='proj_list'] td[class='tabEmpty'] img[title='Add New Workspace']");
		public static By txt_CreateEditWorkspaceNameInput 			= By.cssSelector("div[id='projectBasicDetailsTable'] div[id='basicProjInfoTab'] input[id='projectName']");
		public static By drp_CreateEditWorkspaceClient 				= By.cssSelector("div[id='projectBasicDetailsTable'] div[id='basicProjInfoTab'] select[id='hashedOwnerOrgID']");
		public static By drp_CreateEditWorkspaceInstalledApp 		= By.cssSelector("div[id='projectBasicDetailsTable'] div[id='basicProjInfoTab'] select[id='newSpaceTypeId']");
		public static By drp_CreateEditWorkspaceGeography 			= By.cssSelector("div[id='projectBasicDetailsTable'] div[id='basicProjInfoTab'] select[id='dataCenterId']");
		public static By drp_CreateEditWorkspaceViewer 				= By.cssSelector("div[id='projectBasicDetailsTable'] div[id='basicProjInfoTab'] select[id='viewerTypeId']");
		public static By drp_CreateEditWorkspaceStatus 				= By.cssSelector("div[id='projectBasicDetailsTable'] div[id='basicProjInfoTab'] select[id='statusID']");
		public static By lnk_CreateEditWorkspaceSave 				= By.cssSelector("div[id='projectBasicDetailsTable'] div[id='basicSaveCancel'] a[title='Save changes to this section']");
		public static By lnk_CreateEditWorkspaceBack 				= By.cssSelector("div[id='tblCreateEditTitle'] tbody td[align='right'] a[target='_parent'] img[src*='i_back.gif']");
		public static By lnk_WorkspaceSettingsShow 					= By.xpath(".//a[@id='wsSettingsDetailsShow' and text()='show']");
		public static By chk_EnableFileCompare 						= By.xpath(".//input[contains(@id,'checkCompare') and @type='checkbox']");
		public static By lnk_WorkspaceSettingsSave 					= By.xpath(".//div[@id='wsSettingsSaveCancel']//a[text()='Save']");
		public static By lnk_WorkspaceSettingsHide 					= By.xpath(".//a[@id='wsSettingsDetailsHide' and text()='hide']");
		public static By lnk_CreateEditWorkspaceAdditionalInfoShow 	= By.cssSelector("div[id='projectBasicDetailsTable'] a[id='projectAdditionalDetailsShow']");
		public static By chk_CreateEditWorkspaceEnablePublicUpload 	= By.cssSelector("input[id='checkEnableSimpleUpload']");
		public static By lnk_CreateEditWorkspaceAdditionalInfoSave	= By.cssSelector("div[id='projectBasicDetailsTable'] div[id='additionalSaveCancel'] a[title='Save changes to this section']");
		public static By lnk_CreateEditWorkspaceAdditionalInfoHide	= By.cssSelector("div[id='projectBasicDetailsTable'] a[id='projectAdditionalDetailsHide']");
	}
	

}
