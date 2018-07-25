package org.asite.automation.common.resources;



// TODO: Auto-generated Javadoc
/**
 * The Class AdoddleCommonJQueries.
 * @author jasminprajapati
 */
public class AdoddleCommonJQueries {

	public static String 	ajaxCallTypeJQuery						= "return (typeof jQuery() != 'undefined')";

	/** The Upload Dialog Scroll Max Left Script. */
	public static String	scrollMaxLeftJQuery						= "$(\"div[class='clearfix file-attr-table-container']\").scrollLeft(0)";

	/** The Scroll Max Down Script. */
	public static String	scrollDownJquery						= "$('div.innerContainer').scrollTop($('div.innerContainer').innerHeight())";

	/** The action col width expand query. */
	public static String	actionColWidthExpandQuery				= "$(\"div[id='adTableHead'] div[class*='col-actions-actionName-actionTime-fixed-width']\").width(200)";

	/** The scroll window max down j query. */
	public static String	scrollWindowMaxDownJQuery				= "window.scrollTo(0,document.body.scrollHeight)";

	/** The scroll down scroll jquery. */
	public static String	scrollDownScrollJquery					= "$('div.innerContainer').scrollTop($('div.innerContainer').scroll())";

	/** The scroll window max top j query. */
//	public static String	scrollWindowMaxTopJQuery				= "document.getElementsByClassName('container-skin')[0].getElementsByClassName('container-fluid margin-in')[0].scrollTop = 0";
	public static String	scrollWindowMaxTopJQuery				= "document.getElementsByClassName('container-fluid dashboard-container')[0].scrollTop = 0";

	/** The override certification error j query. */
	public static String	overrideCertificationErrorJQuery		= "document.getElementById('overridelink').click()";

	/** The expand first model name j query. */
	public static String	expandFirstModelNameJQuery				= "$(\"div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-userModelName']\").width(300)";

	/** The click field enabled query. */
	public static String	clickFieldEnabledQuery					= "$(\"div[class='modal-scrollable'][style*='z-index'] form[id='editProject'] input[id='chkIsFieldEnabled1']\").click()";

	/** The groovy task count query. */
	public static String	groovyTaskCountQuery					= "return $(\"div[id='workflowList'] div[id='listing'] div[class*='rows']:contains('Execute Groovy Script')\").find(\"div[class*='col-name-fixed-width'] a\").length";

	/** The total task count query. */
	public static String	totalTaskCountQuery						= "return $(\"div[id='workflowList'] div[id='listing'] div[class*='rows']\").find(\"div[class*='col-name-fixed-width'] a\").length";

	/** The scroll max left window j query. */
	public static String	scrollMaxLeftWindowJQuery				= "$('div.innerContainer').scrollLeft(0)";
	
	/** The edit apps modalscroll max down j query. */
	public static String	editAppsModalscrollMaxDownJQuery		= "(function scrollPopup(){var $cache = document.querySelectorAll(\"div[id='editAppsModal'] div[class='modal-body']\")[0];$($cache ).scrollTop($cache.scrollHeight);})()";

	/** The beta view create form scroll max down query. */
	public static String	betaViewCreateFormScrollMaxDownQuery	= "window.scroll(0, 5000)";
	
	/** The expand first form title j query. */
	public static String    expandFirstFormTitleJQuery				= "$(\"div[id='adTableBody'] div[class*='rows']:nth-child(1)  div[class*='col-title-fixed-width']\").width(300)";
																	  
	/** The edit design layout bill to org J query. */
	public static String 	editDesignLayoutBillToOrgJQuery			= "return $(\"div[id='pagelayoutOrgList'] div[id='layoutOrgListDiv'] tbody tr:contains('Automation Bill-To-Org') a[title='Edit']\").click()";

	/** The edit design layout user org J query. */
	public static String	editDesignLayoutUserOrgJQuery			= "return $(\"div[id='pagelayoutOrgList'] div[id='layoutOrgListDiv'] tbody tr:contains('Automation User-Org') a[title='Edit']\").click()";
	
	/** The design layout bill to org size J query. */
	public static String 	designLayoutBillToOrgSizeJQuery			= "return $(\"div[id='pagelayoutOrgList'] div[id='layoutOrgListDiv'] tbody tr td:contains('Automation Bill-To-Org')\").size()";
	
	/** The design layout user org size J query. */
	public static String 	designLayoutUserOrgSizeJQuery			= "return $(\"div[id='pagelayoutOrgList'] div[id='layoutOrgListDiv'] tbody tr td:contains('Automation User-Org')\").size()";

	/** The auto fetch attribute rule J query. */
	public static String 	getAutoFetchAttributeRuleJQuery 		= "$(\"div[class*='rows']:contains('Automation_P1') div[class*='col-editOptionImage-fixed-width'] a img\").click()";
	
	/** The auto fetch attribute rule name verify J query. */
	public static String 	getAutoFetchAttributeRuleNameJQuery 	= "return $(\"div[id='adTableBody'] div[class*='rows']:contains('Automation_P1') div[class*='col-fetchAttributeSetName-fixed-width']\").length";
	
	/** The status change click query. */
	public static String 	statusChangeClickQuery 					= "$(\"a[fieldname='moreOptionBatchstatusChange']\").click()";
	

}
