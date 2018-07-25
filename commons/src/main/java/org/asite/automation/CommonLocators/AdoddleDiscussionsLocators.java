package org.asite.automation.CommonLocators;

import org.openqa.selenium.By;

public class AdoddleDiscussionsLocators {

	public static class DiscussionsTab {
		public static By	btn_ActionStatusFilter											= By.cssSelector("div[id='docListingSection'] button[filterkey='Comment Status'] span");
		public static By	chk_ActionStatusFilterUnread									= By.cssSelector("div[class*='filterui'] li[class='check-list-item'] label[alt='Unread'] input");
		public static By	chk_PopAttachmentAssociationCheckAllCheckbox					= By.xpath(".//div[contains(@style,'display: block')]//input[@class='checkall']");
		public static By	css_AppendNameStringCheckList									= By.xpath(".//div[@class='othersContainer']//div/input[@type='checkbox']");
		public static By	btn_PopDownloadButton											= By.xpath(".//div[contains(@style,'display: block')]//button[text()='Download']");
		public static By	lnk_PopDownloadLink												= By.xpath(".//div[contains(@style,'display: block')]//a[text()='Download']");
		public static By	pop_BatchFiles													= By.xpath(".//*[@id='myModal-downloadProgressBar' and @aria-hidden='false']");
		public static By	img_firstDiscussionAssociationLink								= By.xpath(".//div[@index='0']//div[contains(@class,'attachmentImageName')]//img");
		public static By	lnk_firstDiscussionAssociationAttachementLink					= By.cssSelector("div[class='popover-content'] div[id='Attachments'] a");
		public static By	lnk_PopAttachmentAndAssociationsAttachments						= By.xpath(".//div[@id='pageLayoutHeader']//div[@id]//a[contains(text(),'Attachments')]");
		public static By	lnk_PopAttachmentsAndAssociationsActiveTab						= By.cssSelector("div[id='biDirectionAssociationModal'] div[id='pageLayoutHeader'] div[id='Attachments'] a");
		public static By	css_PopAttachmentsAndAssociationsAttachmentsTabFileNameList		= By.xpath(".//div[@aria-hidden='false']//div[@index]//div[contains(@class,'fileName')]//a");
		public static By	css_PopAssociateFormsFormTitleList								= By.xpath(".//div[contains(@style,'display: block')]//div[@index]//div[contains(@class,'title')]//div");
		public static By	css_DiscussionListingDiscussionsCount							= By.cssSelector("div[class*='discussionsListContent'] div[class*='activeDiscussionsListing'] div[id='listing'] div[id='adTableBody'] div[class*='rows']");
		public static By	btn_RecipientNameFilter											= By.cssSelector("div[id='docListingSection'] button[filterkey='Recipient'] span");
		public static By	lnk_FirstUnreadDiscussionCompleteAction							= By.cssSelector("div[id='adTableBody'] div[class*='rows']:nth-child(1) a[class= 'completeAction']");
		public static By	ele_DiscussionsHistorySelectedRow								= By.cssSelector("table[class*=' discussRowSelected'] div[class='commentInfo']");
		public static By	ele_FileBetaViewDiscussionsHistoryPanel							= By.cssSelector("div[class*='ibox-content clearfix'] div[class*='file-comment-wrapper']");
		public static By	lbl_firstDiscussionTitle										= By.cssSelector("div[id='assocCommsList'] table[class*='tblCommentsTree']:nth-child(1) div[class='commentInfo'] div[id='ctitle']");
		public static By	lbl_FileBetaViewfirstDiscussionTitle							= By.cssSelector("div[class='file-comment-wrapper'] ul[class*='dd-list'] li[class*='dd-item ng-scope'] div[class='body'] a[class*='comment-title'] span[title*='COM']");
		public static By	lbl_UnreadDiscussionsHeader										= By.cssSelector("div[id='docListingSection'] div[id='listingHeaderSection'] div[id='discussionslist']");
		public static By	lbl_BetaViewUnreadDiscussionsHeader								= By.cssSelector("div[class='tab-listing-footer'] div[id='fileDoclist']");

		/* Create Comment */
		public static By	frm_BetaViewDiscussionSearchFilter								= By.cssSelector("div[class='listing-container'] span[class*='preselected-filters'] input");
		public static By	lnk_DiscussionFirstCommentTitle									= By.cssSelector("div[id='listing'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-oriCommentTitle'] a");
		public static By	img_DiscussionFirstCommentPrivateImage							= By.cssSelector("div[class*='activeDiscussionsListing'] div[id='adTableBody'] div[class*='col-typeImage-fixed-width'] img[src*='private_comment.png']");
		public static By	lnk_firstDiscussionAttachmentLink								= By.cssSelector("div[id='assocCommsList'] table[class*='tblCommentsTree']:nth-child(1) div[id='commRightIcons'] a[id='Files'] img");
		public static By	lnk_BetafirstDiscussionExpandComment							= By.cssSelector("i[ng-click*='expandComment']");
		public static By	lnk_BetafirstDiscussionAttachmentLink							= By.cssSelector("div[id='fileViewer'] div[class='file-comment-wrapper'] li[ng-if*='data.totalAttachAssoc'] a");
		public static By	lnk_BetaAttachmentAndAssociationFooter							= By.cssSelector("div[id='fileViewer'] div[class='file-comment-wrapper'] div[class='discussion-footer']");
		public static By	pop_AttachmentAndAssociationPopup								= By.cssSelector("div[id='biDirectionAssociationModal'][style*='display: block;']");
		public static By	pop_FileBetaViewAttachmentAndAssociationPopup					= By.cssSelector("div[class='file-comment-wrapper'] ul[class*='dd-list']:nth-child(2) li div[class*='clearfix'] div[class='discussion-footer'] div[class='attach-assoc ng-scope'] ul[class='nav nav-tabs nav-tabs-line'] li[class='ng-scope active'] a");
		public static By	pop_FileBetaViewAttachmentPopup									= By.cssSelector("div[class='file-comment-wrapper'] ul[class*='dd-list']:nth-child(2) li div[class*='clearfix'] div[class='discussion-footer'] div[class*='attach-assoc '] ul[class*='nav nav-tabs '] li[class='ng-scope'][ng-click*='Attachments'] a");
		public static By	lnk_PopAttachmentAndAssociationPopupFiles						= By.cssSelector("div[id='pageLayoutHeader'] div[id*='Files'] a");
		public static By	lnk_PopAttachmentAndAssociationPopupFilesTab					= By.xpath(".//ul[contains(@class,'nav-tabs')]//a//uib-tab-heading[contains(text(),'Files')]");
		public static By	lnk_FileBetaViewAttachmentAndAssociationPopupFiles				= By.cssSelector("ul[class*='dd-list']:nth-child(2) li[class='ng-scope'][ng-click*='Files'] a");
		public static By	lnk_BetaFooterAttachmentAndAssociationPopupFiles				= By.cssSelector("div[class='discussion-footer'] ul[class*='nav-tabs'] li[ng-class*='Files'] a");
		public static By	lnk_BetaViewPopAttachmentAndAssociationPopupFiles				= By.cssSelector("li[class*='uib-tab '][index='0'] a span[class*='badge ']");
		public static By	css_PopAttachmentAndAssociationFilesDocTitles					= By.cssSelector("div[id='biDirectionAssociationModal'][style*='display: block;'] div[id='adTableBody'] div[class*='col-title']");
		public static By	css_PopAttachmentAndAssociationFilesDocRefs						= By.cssSelector("div[id='biDirectionAssociationModal'][style*='display: block;'] div[id='adTableBody'] div[class*='rows'] div[class*='col-docRef-fixed-width'] a");
		public static By	css_FileBetaViewAttachmentAndAssociationFilesDocRefs			= By.cssSelector("ul[class*='dd-list']:nth-child(2) li div[class*='tab-content'] ul[class*='grow ng-scope'] li:nth-child(4) a");
		public static By	css_BetaAttachmentAndAssociationFilesDocRefs					= By.cssSelector("div[class='discussion-footer'] div[class='gbody'] li[class='gfname']:nth-child(4) a");
		public static By	css_BetaViewPopAttachmentAndAssociationFilesDocRefs				= By.cssSelector("ul[class='repeated-item'] li div[class='text-ellipsis'] a");
		public static By	css_AttachmentsAndAssociationsDiv								= By.cssSelector("div[class*='dropdown-menu'] div[class*='ibox'] div[class*='ibox-content clearfix'] form-attach-assoc[class*='ng-scope ng-isolate-scope']");
		public static By	lnk_PopAttachmentAndAssociationPopupAttachments					= By.cssSelector("div[id='biDirectionAssociationModal'][style*='display: block;'] div[id='pageLayoutHeader'] div[id='Attachments'] a");
		public static By	lnk_BetaViewPopAttachmentAndAssociationPopupAttachmentTab		= By.xpath(".//ul[contains(@class,'nav-tabs')]//a//uib-tab-heading[contains(text(),'Attachments')]");
		public static By	lnk_BetaViewPopAttachmentAndAssociationPopupTabs				= By.xpath(".//ul[contains(@class,'nav-tabs')]//li[contains(@class,'active')]//a//uib-tab-heading[text()]");
		public static By	css_BetaViewPopAttachmentAndAssociationPopupTabsType			= By.xpath(".//div[contains(@id,'view-page')]//main[@class='open']//div[@class='ibox']//div[contains(@class,'active')]//div[@class='ghead']//ul//li[@title]");
		public static By	lnk_BetaViewPopAttachmentAndAssociationPopupAttachments			= By.cssSelector("li[class='uib-tab nav-item ng-scope ng-isolate-scope active'] span[class*='badge']");
		public static By	css_PopAttachmentAndAssociationAttachmentsFileNames				= By.cssSelector("div[id='biDirectionAssociationModal'][style*='display: block;'] div[id='adTableBody'] div[class*='rows'] div[class*='col-fileName-fixed-width']");
		public static By	css_FileBetaViewAttachmentAndAssociationAttachmentsFileNames	= By.cssSelector("div[class='clearfix'] div[class='discussion-footer'] div[class*='attach-assoc '] div[class*='tab-content'] div[class*='table-grid'] div[class*='gbody'] ul[class*='grow ng-scope'] li:nth-child(3)");
		public static By	css_BetaViewPopAttachmentAndAssociationAttachmentsFileNames		= By.cssSelector("ul[class='repeated-item'] li:nth-child(3) a[class='text-ellipsis']");
		public static By	lnk_PopAttachmentAndAssociationPopupForms						= By.cssSelector("div[id='biDirectionAssociationModal'] div[id='pageLayoutHeader'] div[id*='Forms'] a");
		public static By	lnk_FileBetaViewAttachmentAndAssociationPopupForms				= By.cssSelector("div[class='file-comment-wrapper'] ul[class*='dd-list']:nth-child(2) li div[class*='clearfix'] div[class='discussion-footer'] div[class='attach-assoc ng-scope'] ul[class='nav nav-tabs nav-tabs-line'] li[class='ng-scope'][ng-click*='Forms'] a");
		public static By	chk_BetaFooterAttachmentsAndAssociationHeaderCheck				= By.cssSelector("div[class='discussion-footer'] div[class='ghead'] li[class='gcheck'] input");
		public static By	lnk_BetaFooterAttachmentAndAssociationPopupForms				= By.cssSelector("div[class='discussion-footer'] ul[class*='nav-tabs'] li[ng-class*='Forms'] a");
		public static By	css_PopAttachmentAndAssociationPopupFormIDs						= By.cssSelector("div[id='biDirectionAssociationModal'][style*='display: block;'] div[id='adTableBody'] div[class*='rows'] div[class*='col-formCode'] a");
		public static By	css_PopAttachmentAndAssociationPopupFormTitles					= By.cssSelector("div[id='biDirectionAssociationModal'][style*='display: block;'] div[id='adTableBody'] div[class*='col-title']");
		public static By	lnk_FileBetaViewAttachmentAndAssociationPopupFirstFormID		= By.cssSelector("div[class='file-comment-wrapper'] ul[class*='dd-list']:nth-child(2) li div[class*='clearfix'] div[class='discussion-footer'] div[class='attach-assoc ng-scope'] div[class='gbody'] div[class='ng-scope']:nth-child(1) ul[class*='grow'] li:nth-child(3) a");
		public static By	css_FileBetaViewAttachmentAndAssociationPopupFormIDs			= By.cssSelector("div[class='file-comment-wrapper'] ul[class*='dd-list']:nth-child(2) li div[class*='clearfix'] div[class='discussion-footer'] div[class='attach-assoc ng-scope'] div[class='gbody'] div[class='ng-scope'] ul[class*='grow'] li:nth-child(3) a");
		public static By	css_FileBetaViewAttachmentAndAssociationFormIDs					= By.cssSelector("div[class='ng-scope'] ul[class*='grow'] li:nth-child(3) a");
		public static By	css_BetaFooterAttachmentAndAssociationPopupFormIDs				= By.cssSelector("div[class='discussion-footer'] div[class='gbody'] li[class='gid'] a");
		public static By	lnk_PopAttachmentAndAssociationPopupDiscussions					= By.cssSelector("div[id='biDirectionAssociationModal'] div[id='pageLayoutHeader'] div[id*='Discussions'] a");
		public static By	lnk_FileBetaViewAttachmentAndAssociationPopupDiscussions		= By.cssSelector("div[class='file-comment-wrapper'] ul[class*='dd-list']:nth-child(2) li div[class*='clearfix'] div[class='discussion-footer'] div[class='attach-assoc ng-scope'] ul[class='nav nav-tabs nav-tabs-line'] li[class='ng-scope'][ng-click*='Discussions'] a");
		public static By	lnk_BetaFooterAttachmentAndAssociationDiscussions				= By.cssSelector("div[class='discussion-footer'] ul[class*='nav-tabs'] li[ng-class*='Discussions'] a");
		public static By	css_PopAttachmentAndAssociationPopupDiscussionIDs				= By.cssSelector("div[id='biDirectionAssociationModal'][style*='display: block;'] div[id='adTableBody'] div[class*='rows'] div[class*='col-commentCode'] a");
		public static By	css_PopAttachmentAndAssociationPopupFormAmessageTitles			= By.cssSelector("div[id='biDirectionAssociationModal'][style*='display: block;'] div[id='adTableBody'] div[class*='rows'] div[class*='col-commentTitle'] a");
		public static By	css_FileBetaViewAttachmentAndAssociationDiscussionIDs			= By.cssSelector("div[class='file-comment-wrapper'] div[class='ng-scope'] ul[class*='grow'] li:nth-child(3)");
		public static By	lnk_FileBetaViewAttachmentAndAssociationFirstDiscussionID		= By.cssSelector("div[class='file-comment-wrapper'] div[class='ng-scope']:nth-child(1) ul[class*='grow'] li:nth-child(3)");
		public static By	css_BetaFooterAttachmentAndAssociationPopupDiscussionIDs		= By.cssSelector("div[class='discussion-footer'] div[class='gbody'] li[class='gid'] a[ng-click*='openDiscussion']");
		public static By	lbl_UnreadDiscussionsCount										= By.cssSelector("div[id='left-nav-blocks'] a[id='sidenav-discuss-unread'] span[title]");
		public static By	lnk_UnreadDiscussions											= By.cssSelector("div[id='left-nav-blocks'] a[id='sidenav-discuss-unread']");
		public static By	opt_DiscussionContextClickMarkAsRead							= By.cssSelector("ul[class*='context-menu-root'] li[class*='CMicon-markasread'] span");
		public static By	css_UnreadDiscussionsCountOnListing								= By.xpath(".//div[@index]//div[contains(@class,'actionName')]//a[@class='completeAction' and text()]");
		public static By	lnk_DiscussionFirstCommentID									= By.cssSelector("div[id ='listing'] div[class*='rows']:nth-child(1) div[class*='col-code-fixed-width'] a");
		public static By	lnk_DiscussionFirstAction										= By.cssSelector("div[id ='listing'] div[class*='rows']:nth-child(1) div[class*='col-actions-actionName-actionTime-fixed-width'] a");
		public static By	lnk_DiscussionCommentIDs										= By.cssSelector("div[id ='listing'] div[class*='rows'] div[class*='col-code-fixed-width'] a");
		public static By	lnk_markAsRead													= By.cssSelector("div[id='myModal-batchforRead'] a[id='btnSubmitForRead']");
		public static By	css_myactionPlus												= By.cssSelector("div[id ='listing'] div[class*='rows']:nth-child(1) div[class*='actionTime-fixed-width'] span");
		public static By	img_RFIActionCompletionIcon										= By.cssSelector("img[alt='completed']");
		public static By	chk_DocListingFirstCheckBox										= By.cssSelector("div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='filelistchkbox'] input");
		public static By	chk_DocListingSecondCheckBox									= By.cssSelector("div[id='adTableBody'] div[class*='rows']:nth-child(2) div[class*='filelistchkbox'] input");
		public static By	lnk_OptionsMarkAsRead											= By.cssSelector("div[id='myModal-moreoptions-icons'] a[id='optionsmore-action-forread']");
		public static By	opt_FileContextClickNew											= By.cssSelector("ul[class*='context-menu-root'][style*='z-index: 10'] li[class='context-menu-item context-menu-submenu icon CMicon-new']");
		public static By	txt_formSearchFilter											= By.cssSelector("div[id='filterFormContainerTextbox'] div[class*='divtr'] input[id='formTypeNameInp'][type='text']");
		public static By	css_formSearchResult											= By.cssSelector("div[id='listing'] div[class*='innerContainer'] div[class*='col-formTypeName-fixed-width'] div");
		public static By	txt_BetaViewFormInputToField									= By.cssSelector("div[id='distInputTo'] div[class='dropdown-toggle']  input[class*='dist-inpt']");
		public static By	txt_FormInputToField											= By.cssSelector("div[id='divDistributeFiles_form'] div[id='s2id_inptDistTo1']  input[id='s2id_autogen1']");
		public static By	txt_CreateFormDescription										= By.cssSelector("div[style*='MARGIN-BOTTOM']:nth-child(4) table[class='xdLayout'] tbody tr:nth-child(4) textarea[class='xdTextBox']");
		public static By	lbl_FormAddSuccessMsg											= By.cssSelector("div[id='adoddleTopMsgBox'] span");
		public static By	txt_CreateFormSubject											= By.cssSelector("div[style*='MARGIN-BOTTOM']:nth-child(4) table[class='xdLayout'] tbody tr:nth-child(2) textarea[class='xdTextBox']");
		public static By	lnk_OptionsStartWorkflow										= By.cssSelector("div[id='myModal-moreoptions-icons'] a[id='optionsmore-associateToApp'][href*='assocDiscussionsFromDiscussionTab']");

		/* New UI Locators */
		public static By	txt_DiscussionSearchFilter										= By.cssSelector("div[id='basicFilterContainer'] form[id='formFilterFiletab'] input[id='fd_containText']");

		/* Sorting Locators */
		public static By	css_DiscussionsTabDocRefList									= By.xpath(".//div[@index]//div[contains(@class,'docRef')]//div");
		public static By	css_DiscussionsTabDocTitleList									= By.xpath(".//div[@index]//div[contains(@class,'docTitle')]//div");
		public static By	css_DiscussionsTabRevList										= By.xpath(".//div[@index]//div[contains(@class,'revisionNum')]//div");
		public static By	css_DiscussionsTabDiscussionTitleList							= By.xpath(".//div[@index]//div[contains(@class,'CommentTitle')]//a");

		/* AMessage Locators */
		final public static By lnk_AMessagesTabCreateGroup                     				= By.cssSelector("div[class*='chat-main'] a[title='Create Group']");
		final public static By txt_AMessageTabCreateGroupName                  				= By.cssSelector("div[class*='channel-body'] input[formcontrolname='channelName']");
		final public static By txt_AMessageTabCreateGroupProjectName           				= By.cssSelector("div[class*='channel-body'] div[class*='field-wrapper']:nth-child(2) input");
		final public static By txt_AMessageTabCreateGroupMembers               				= By.cssSelector("div[class*='channel-body'] div[class*='ng-star-inserted']:nth-child(4) input[class='select2-search__field']");
		final public static By opt_AMessageTabCreateGroupProjectFirstOption    				= By.cssSelector("ul[class='select2-results__options'] li:nth-child(1)");
		final public static By txt_AMessageTabCreateGroupDescription           				= By.cssSelector("textarea[formcontrolname='channelDesc']");
		final public static By btn_AMessageTabCreateGroupSave                  				= By.cssSelector("input[class*='save-channel']");
		final public static By img_AMessageTabSearchOption                     				= By.cssSelector("i[class='fa fa-search']");
		final public static By txt_AMessageTabSearch                           				= By.cssSelector("div[class*='chat-main'] div[class='search-container'] input[placeholder='Search...']");
		final public static By lbl_AMessageTabSearchFirstRecord                				= By.cssSelector("div[class*='search-list'] ul li:nth-child(1) div[class='text-ellipsis']");
		final public static By ele_AMessageTabActiveGroup                     				= By.cssSelector("li[class='active ng-star-inserted']");
		final public static By lbl_AmessageTabChatPanelHeader                  				= By.cssSelector("section[class='chat-panel'] h2 span[class='ng-star-inserted']");
		final public static By btn_AMessageTabChatPanelSend                    				= By.cssSelector("adoddle-messages a[class='send-btn']");
		final public static By opt_AMessageTabCreateGroupMembersFirstOption    				= By.cssSelector("span[class='select2-results'] li:nth-child(1) span");
		final public static By lbl_AMessageTabChatPanelLastMessage             				= By.cssSelector("div[id='messageer-body'] div[class*='scroller-inner'] div[class='message ng-star-inserted']:last-child p");
		final public static By img_AMessageTabChatPanelLastMessageUserCircle   				= By.cssSelector("div[id='messageer-body'] div[class*='scroller-inner'] div[class='message ng-star-inserted']:last-child img[class='circle']");
		final public static By ele_AMessageUserCircleOpenCardDetails           				= By.cssSelector("div[class*='card'][class*='open'][style*='top']");
		final public static By lnk_AMessageTabChatPanelLastMessageReply        				= By.cssSelector("div[id='messageer-body'] div[class*='scroller-inner'] div[class='message ng-star-inserted']:last-child div[class*='message-reply']+p");
		final public static By img_AMesssageTabChatPanelLastMessageReply       				= By.cssSelector("div[id='messageer-body'] div[class*='scroller-inner'] div[class='message ng-star-inserted']:last-child i[class*='reply']");
		final public static By ele_AMessageTabChatPanelReplyBody               				= By.cssSelector("div[class*='hideTaskMsgBtn'] div[class*='message-reply']");
		final public static By btn_AMessageTabChatPanelAttachment              				= By.cssSelector("adoddle-messages div[class*='input-container'] a[class='attachment-btn btn-file'] input");
		final public static By css_AMessageTabChatPanelMessages                				= By.cssSelector("div[id='messageer-body'] div[class*='scroller-inner'] div[class*='ng-star-inserted'] p");
		final public static By css_AMessageTabChatPanelAttachments             				= By.cssSelector("div[id='messageer-body'] div[class*='scroller-inner'] div[class*='ng-star-inserted'] div[class*='attachment-holder'] a[class='attachment-thumb'] img");
		final public static By btn_AMessageTabChatPanelMembers                 				= By.cssSelector("section[class='chat-panel'] div[class='memberDiv ng-star-inserted']");
		final public static By lnk_AMessageTabMembersAddMember                 				= By.cssSelector("div[class='search-list'] a[class='btn-link ng-star-inserted']");
		final public static By css_AMessageTabMembersList                      				= By.cssSelector("div[class='search-list'] ul li");
		final public static By btn_AMessageCreateGroupTypePrivate              				= By.cssSelector("adoddle-channel div[class='channel-details'] input[formcontrolname='channelType'][class*='ng-untouched']+span[class='switch']");
		final public static By btn_AMessageCreateGroupTypePublic               				= By.cssSelector("adoddle-channel div[class='channel-details'] input[formcontrolname='channelType'][class*='ng-untouched']+span[class='switch']");
		final public static By lnk_AMessageTabGroupOptions                     				= By.cssSelector("li[class*='active'] a[class*='more-channel-options']");
		final public static By opt_AMessageTabEditGroupOption                  				= By.cssSelector("a[class*='more-channel-options'] ul[class*='group-more-options-ul'] li:nth-child(1)");
		final public static By btn_AMessageCreateGroupStatusActive             				= By.cssSelector("adoddle-channel div[class='channel-details'] input[formcontrolname='channelStatus'][class*='ng-untouched']+span[class='switch']");
		final public static By btn_AMessageCreateGroupStatusInActive           				= By.cssSelector("adoddle-channel div[class='channel-details'] input[formcontrolname='channelStatus'][class*='ng-touched']+span[class='switch']");

	}

}