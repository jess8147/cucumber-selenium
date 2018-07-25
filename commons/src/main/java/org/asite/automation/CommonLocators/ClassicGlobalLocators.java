package org.asite.automation.CommonLocators;

import org.openqa.selenium.By;

public class ClassicGlobalLocators {


	public static class GlobalPageElements
	{
		
		public static By lnk_FirstMyActionCountPopOver = By.cssSelector("div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-actions'] span[class='multiobjcount']");
		public static By pop_firstActionsPopOverContent = By.cssSelector("div[class='popover fade left in'][style*='display: block;'] div[class='popover-content']");
		public static By css_firstActionsPopoverContentLinks = By.cssSelector("div[class='popover-content'] div[class='pop-doc-info'] a[class='completeAction'] div[style*='float:left']");
		public static By lnk_FirstProject = By.cssSelector("div[class='tree-children'] div[id*='ws_']:nth-child(1) div[class*='selected'] a[class='docList'] span[class='tree-label']");
		public static By pop_PopUpElement = By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[aria-hidden='false']");
		public static By pop_PopUpHeader1 = By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[aria-hidden='false'] h3");
		public static By pop_PopUpHeader2 = By.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[aria-hidden='false'] h2");
		public static By lbl_SuccessMessage = By.cssSelector("div[id='adoddleTopMsgBox'] span");
		public static By lnk_ActiveTab = By.cssSelector("div[id='menuNav'] li[class='active'] a");
		public static By ele_overLayProcess = By.cssSelector("div[class='overlay-filter-process']");
		public static By btn_FilterProject = By.cssSelector("div[id='headerProjectFilter'] ul[class='criteria-list'] button[filterkey='project']");
		public static By txt_FilterProjectInput = By.cssSelector("div[class*='filterui'] div[class*='filter-layer-box'] input[filterkey='project']");
		public static By lnk_FilterProjectShowAll = By.cssSelector("div[class*='filterui'] div[class*='filter-layer-box'] a[class*='show-all']");
		public static By chk_FilterProjectFirstProject = By.cssSelector("div[class='filterui-list-scroll'][style*='opacity: 1'] li[class*='check-list-item ui-menu-item']:nth-child(1) a input[type='checkbox']");
		public static By chk_FilterProjectFirstProjectSelected = By.cssSelector("div[class='filterui-list-scroll'][style*='opacity: 1'] li[class*='check-list-item ui-menu-item']:nth-child(1) a input[class='selected']");
		public static By lnk_FilterProjectClearAll = By.cssSelector("div[class*='filterui'] div[class*='filter-layer-box'] li[class*='check-list-group-actions'][style='width: 100%; box-sizing: border-box;'] a[class*='clear-all']");
		public static By lnk_FilterProjectClose = By.cssSelector("div[class*='filterui'] div[class*='filter-layer-box'] a[class*='apply-all']");
		public static By txt_WebMailUserNameInput = By.cssSelector("body[class='owaLgnBdy'] table[id='tblMid'] input[id='username']");
		public static By txt_WebMailPasswordInput = By.cssSelector("body[class='owaLgnBdy'] table[id='tblMid'] input[id='password']");
		public static By btn_WebMailSignIn = By.cssSelector("body[class='owaLgnBdy'] table[id='tblMid'] input[value='Sign in']");
		public static By img_WebMailSearchAdvancedBox = By.cssSelector("div[id='divSearchBox'] img[id='imgSAS'][title*='Show Advanced Search']");
		public static By txt_WebMailAdvancedSearchBoxFrom = By.cssSelector("div[id='divVw'] div[id='divSearchBox'] input[id='txtRcp']");
		public static By ele_WebMailFirstEmailUnreadSubject = By.cssSelector("div[class='mainView']:nth-child(2) div[id='divViewport'] div[id='gc']:nth-child(2) div[id='vr']:nth-child(1) div[id='divSubject'][class*='ur']");
		public static By lnk_WebMailEmailClickHere = By.cssSelector("table tbody tr:nth-child(4) a[target='_blank']");
		public static By lbl_WebMailEmailSendDateTime = By.cssSelector("div[class='mainView']:nth-child(2) div[id='divVw'] div[id='divConversationBody'] div[id='divExp'] span[id='spnSent']");
		public static By img_WebMailInboxExpander = By.cssSelector("div[id='Mail'] div[id='mailtree'] div[id='divTrNdCC'] img[id='ec']");
		public static By img_WebMailCheckMessages = By.cssSelector("div[class='mainView']:nth-child(2) div[id='divVw'] a[id='checkmessages'] img[id='imgToolbarButtonIcon']");
		public static By ele_WebMailContent = By.cssSelector("div[class='mainView']:nth-child(2) div[id='divVw'] div[id='divBdy'] table[width='98%']");
		public static By lnk_WebMailLVNew = By.cssSelector("table[class='mnTbl'] a[id='lnkHdrnewmsg']");
		public static By txt_WebMailLVNewToField = By.cssSelector("table[class='mnTbl'] table[class='edtMsg'] input[id='txtto']");
		public static By txt_WebMailLVNewSubject = By.cssSelector("table[class='mnTbl'] table[class='edtMsg'] input[id='txtsbj']");
		public static By txt_WebMailLVContent = By.cssSelector("table[class='mnTbl'] table[class='wh100'] td[class='h100'] textarea");
		public static By img_WebMailLVAttachFileLink = By.cssSelector("table[class='edtMsg'] img[src*='attch.png']");
		public static By btn_WebMailLVAttachButton = By.cssSelector("table[class='attchfrm'] input[id='attachbtn']");
		public static By lnk_WebMailLVAttachMentDone = By.cssSelector("table[class='mnTbl'] table[class='tbhd'] a[id='lnkHdrdone']");
		public static By ele_WebMailLVAttachFileBrowse = By.cssSelector("table[class='attchfrm'] input[id='attach']");
		public static By chk_WebMailLighVersion = By.cssSelector("table[id='tblMain'] table[id='tblMid'] tbody input[id='chkBsc']");
		public static By lnk_WebMailLVSend = By.cssSelector("table[class='mnTbl'] table[class='tbhd'] a[id='lnkHdrsend']");
		public static By lnk_WebMailLVSignout = By.cssSelector("table[class='mnTbl'] table[class='ob'] a[id='lo']");
		public static By frm_GlobalIframe = By.cssSelector("iframe");
		public static By btn_ChromePrintPreviewPrint = By.cssSelector("button[class='print default'][i18n-content='printButton']");
		public static By ele_PDFViewerMainContainer = By.cssSelector("div[id='mainContainer']");
		public static By ele_PDFViewerFirstPage =  By.cssSelector("div[id='mainContainer'] div[id='viewerContainer'] div[id='pageContainer1']");
		public static By css_PDFViewerPDFPages =  By.cssSelector("div[id='mainContainer'] div[id='viewerContainer'] div[id*='pageContainer']");
		public static By btn_Download = By.xpath(".//div[@aria-hidden='false']//button[@id='download' and text()='Download']");
		public static By lbl_PageTitle = By.cssSelector("tbody tr td[class='title']");
		public static By btn_AsiteAnnounceDismiss =  By.cssSelector("div[id='divAsiteAnnounce'] div[class='edit-actions'] input[value='Dismiss']");
		public static By ele_PleaseWaitLoadingDataMessage= By.cssSelector("div[id='loadingDataMsg'][style*='display: block']");
	}
}
