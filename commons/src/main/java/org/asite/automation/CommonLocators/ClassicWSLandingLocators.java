package org.asite.automation.CommonLocators;

import org.openqa.selenium.By;

public class ClassicWSLandingLocators {

	public static class WSLandingPage
	{
		public static By frm_WorkSpaceFrame = By.cssSelector("frame[id='workspace']");
		public static By lnk_MailBoxFolder = By.cssSelector("div[id='wrapper'] td[id='DocList'] table[id='tblProjFolderList'] a[title='DocumentMailboxFolder']");
		public static By lnk_PublicFolder = By.cssSelector("div[id='wrapper'] td[id='DocList'] table[id='tblProjFolderList'] a[title='PublicFolder']");
		public static By lnk_AutomationUploadFilesFolder = By.cssSelector("div[id='wrapper'] td[id='DocList'] table[id='tblProjFolderList'] a[title='AutomationUploadFiles']");
		public static By lnk_CustomAttributesFolder = By.cssSelector("div[id='wrapper'] td[id='DocList'] table[id='tblProjFolderList'] a[title='CustomAttributes']");
		public static By lbl_WorkspaceHeaderName = By.cssSelector("body[class='headerBar'] tr[class='headerMain'] span[id='projectName']");
		public static By drp_AdminDropdown = By.cssSelector("body[class='headerBar'] td[class='headerBar'] select[id='projAdminDD']");
		public static By lnk_WorkspaceHome = By.cssSelector("div[id='topMenu'] a[title='Workspace Home']");
	}
	
	
}
