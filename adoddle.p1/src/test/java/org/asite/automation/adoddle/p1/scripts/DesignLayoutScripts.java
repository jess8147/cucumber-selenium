package org.asite.automation.adoddle.p1.scripts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleAdminLocators.AdminTab;
import org.asite.automation.CommonLocators.AdoddleDiscussionsLocators.DiscussionsTab;
import org.asite.automation.CommonLocators.AdoddleFilesLocators.FilesTab;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleModelsLocators.ModelsTab;
import org.asite.automation.CommonLocators.AdoddleProjectsLocators.ProjectsTab;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonJQueries;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

// TODO: Auto-generated Javadoc
/**
 * The Class DesignLayoutScripts.
 */
public class DesignLayoutScripts extends AdoddleCommonAppMethods {

	/** The is editable. */
	private static boolean							isEditable					= false;
	
	/** The project tab listing view. */
	private static boolean							projectTabListingView		= false;
	
	/** The models tab listing view. */
	private static boolean							modelsTabListingView		= false;
	
	/** The procurement tab listing view. */
	private static boolean							procurementTabListingView	= false;
	
	/** The j query. */
	private static String							jQuery						= null;
	
	/** The outer map. */
	final private static Map<String, Map<String, Object>>	outerMap					= new HashMap<String, Map<String, Object>>();
	
	/** The inner map. */
	private Map<String, Object>						innerMap;

	private List<String> fFilters												= null;

	/** The log. */
	final private static Logger						log							= AutomationLogger.getInstance().getLogger(DesignLayoutScripts.class);

	/**
	 * Verify layout for org.
	 *
	 * @param orgTitle the org title
	 */
	public void verifyLayoutForOrg(String orgTitle) {

		navigateLayoutOrgPage(orgTitle);
	}

	/**
	 * Click org layout edit icon.
	 *
	 * @param orgTitle the org title
	 */
	public void clickOrgLayoutEditIcon(String orgTitle) {
		if (orgTitle.equalsIgnoreCase("Automation Bill-To-Org"))
			jQuery = AdoddleCommonJQueries.editDesignLayoutBillToOrgJQuery;
		else
			jQuery = AdoddleCommonJQueries.editDesignLayoutUserOrgJQuery;
		executeJScript(jQuery);
	}

	/**
	 * Collect layout configurations.
	 *
	 * @param orgTitle the org title
	 */
	public void collectLayoutConfigurations(String orgTitle) {

		clickElementAndWaitForElement(AdminTab.btn_PopEditLayoutApplyTo, AdminTab.chk_PopEditLayoutApplyToBillOrgUsers);
		if (!isSelected(AdminTab.chk_PopEditLayoutApplyToBillOrgUsers))
			clickElementAndWait(AdminTab.chk_PopEditLayoutApplyToBillOrgUsers);
		if (!isSelected(AdminTab.chk_PopEditLayoutApplyToOrgUsers))
			clickElementAndWait(AdminTab.chk_PopEditLayoutApplyToOrgUsers);

		innerMap = new HashMap<String, Object>();

		if (isSelected(AdminTab.rad_PopEditLayoutEditableYes))
			isEditable = true;
		else if (isSelected(AdminTab.rad_PopEditLayoutEditableNo))
			isEditable = false;

		innerMap.put("isEditable", isEditable);
		AutomationAssert.verifyTrue(getElementText(AdminTab.lbl_PopEditLayoutActiveHeader) + " should contain " + ("Projects"), getElementText(AdminTab.lbl_PopEditLayoutActiveHeader).contains("Projects"));
		setProjectLayoutAttributes();
		clickElementAndWait(AdminTab.ele_PopEditLayoutFilesExpandArrow);
		setFilesLayoutAttrubutes();
		clickElementAndWait(AdminTab.ele_PopEditLayoutDiscussionsExpandArrow);
		setDiscussionsLayoutAttrubutes();
		clickElementAndWait(AdminTab.ele_PopEditLayoutModelsExpandArrow);
		setModelsLayoutAttrubutes();
		clickElementAndWait(AdminTab.ele_PopEditLayoutProcureMentExpandArrow);
		setProcureMentLayoutAttrubutes();
		clickElementAndWait(AdminTab.btn_PopEditLayoutSave);
		/* if(orgTitle.contains("Bill-To-Org")) { billToOrgFlag = true; } else if(orgTitle.contains("User-Org")) { userOrgFlag = true; } */
		outerMap.put(orgTitle, innerMap);
		log.info("Outer Map# " + outerMap.toString());
	}

	/**
	 * Sets the project layout attributes.
	 */
	private void setProjectLayoutAttributes() {

		List<String> projectTabFiltersList = new ArrayList<String>();
		List<String> projectTabFieldsList = new ArrayList<String>();

		List<WebElement> projectTabLayoutSelectedFields = findElements(AdminTab.css_PopEditLayoutProjectsSelectedFields);
		for (WebElement e : projectTabLayoutSelectedFields)
			projectTabFieldsList.add(e.getText());

		List<WebElement> projectTabLayoutDefaultFilters = findElements(AdminTab.css_PopEditLayoutProjectsDefaultFilters);
		for (WebElement e : projectTabLayoutDefaultFilters)
			projectTabFiltersList.add(e.getText().replace("x", "").trim());

		if (isSelected(AdminTab.rad_PopEditLayoutProjectsViewSettingsTabular))
			projectTabListingView = true;
		else if (isSelected(AdminTab.rad_PopEditLayoutProjectsViewSettingsTile))
			projectTabListingView = false;

		String projectTabRecordPerPage = getSelectedDropdownLabel(AdminTab.drp_PopEditLayoutProjectsViewSettingsRecPerPage);
		String projectTabDefaultSorting = getSelectedDropdownLabel(AdminTab.drp_PopEditLayoutProjectsViewSettingsDefaultSorting);

		innerMap.put("ProjectFields", projectTabFieldsList);
		innerMap.put("ProjectFilters", projectTabFiltersList);
		innerMap.put("ProjectListView", projectTabListingView);
		innerMap.put("ProjectRecords", projectTabRecordPerPage);
		innerMap.put("ProjectDefaultSort", projectTabDefaultSorting);

	}

	/**
	 * Sets the files layout attrubutes.
	 */
	private void setFilesLayoutAttrubutes() {
		boolean	filesTabListingView;
		List<String> filesTabFiltersList = new ArrayList<String>();
		List<String> filesTabFieldsList = new ArrayList<String>();

		List<WebElement> filesTabLayoutSelectedFields = findElements(AdminTab.css_PopEditLayoutFilesSelectedFields);
		for (WebElement e : filesTabLayoutSelectedFields)
			filesTabFieldsList.add(e.getText());

		List<WebElement> filesTabLayoutDefaultFilters = findElements(AdminTab.css_PopEditLayoutFilesDefaultFilters);
		for (WebElement e : filesTabLayoutDefaultFilters)
			filesTabFiltersList.add(e.getText().replace("x", "").trim());

		filesTabListingView = true;

		String filesTabRecordPerPage = getSelectedDropdownLabel(AdminTab.drp_PopEditLayoutFilesViewSettingsRecPerPage);
		String filesTabDefaultSorting = getSelectedDropdownLabel(AdminTab.drp_PopEditLayoutFilesViewSettingsDefaultSorting);

		innerMap.put("FilesFields", filesTabFieldsList);
		innerMap.put("FilesFilters", filesTabFiltersList);
		innerMap.put("FilesListView", filesTabListingView);
		innerMap.put("FilesRecords", filesTabRecordPerPage);
		innerMap.put("FilesDefaultSort", filesTabDefaultSorting);
	}

	/**
	 * Sets the discussions layout attrubutes.
	 */
	private void setDiscussionsLayoutAttrubutes() {
		List<String> discussionsTabFiltersList = new ArrayList<String>();
		List<String> discussionsTabFieldsList = new ArrayList<String>();

		List<WebElement> filesTabLayoutSelectedFields = findElements(AdminTab.css_PopEditLayoutDiscussionsSelectedFields);
		for (WebElement e : filesTabLayoutSelectedFields)
			discussionsTabFieldsList.add(e.getText());

		List<WebElement> filesTabLayoutDefaultFilters = findElements(AdminTab.css_PopEditLayoutDiscussionsDefaultFilters);
		for (WebElement e : filesTabLayoutDefaultFilters)
			discussionsTabFiltersList.add(e.getText().replace("x", "").trim());

		String discussionsTabRecordPerPage = getSelectedDropdownLabel(AdminTab.drp_PopEditLayoutDiscussionsViewSettingsRecPerPage);
		String discussionsTabDefaultSorting = getSelectedDropdownLabel(AdminTab.drp_PopEditLayoutFilesViewSettingsDefaultSorting);

		innerMap.put("DiscussionsFields", discussionsTabFieldsList);
		innerMap.put("DiscussionsFilters", discussionsTabFiltersList);
		innerMap.put("DiscussionsRecords", discussionsTabRecordPerPage);
		innerMap.put("DiscussionsDefaultSort", discussionsTabDefaultSorting);
	}

	/**
	 * Sets the models layout attrubutes.
	 */
	private void setModelsLayoutAttrubutes() {
		List<String> modelsTabFiltersList = new ArrayList<String>();
		List<String> modelsTabFieldsList = new ArrayList<String>();

		List<WebElement> filesTabLayoutSelectedFields = findElements(AdminTab.css_PopEditLayoutModelsSelectedFields);
		for (WebElement e : filesTabLayoutSelectedFields)
			modelsTabFieldsList.add(e.getText());

		List<WebElement> filesTabLayoutDefaultFilters = findElements(AdminTab.css_PopEditLayoutModelsDefaultFilters);
		for (WebElement e : filesTabLayoutDefaultFilters)
			modelsTabFiltersList.add(e.getText().replace("x", "").trim());

		if (isSelected(AdminTab.rad_PopEditLayoutModelsViewSettingsTabular))
			modelsTabListingView = true;
		else if (isSelected(AdminTab.rad_PopEditLayoutModelsViewSettingsTile))
			modelsTabListingView = false;

		String modelsTabRecordPerPage = getSelectedDropdownLabel(AdminTab.drp_PopEditLayoutModelsViewSettingsRecPerPage);
		String modelsTabDefaultSorting = getSelectedDropdownLabel(AdminTab.drp_PopEditLayoutModelsViewSettingsDefaultSorting);

		innerMap.put("ModelsFields", modelsTabFieldsList);
		innerMap.put("ModelsFilters", modelsTabFiltersList);
		innerMap.put("ModelsListView", modelsTabListingView);
		innerMap.put("ModelsRecords", modelsTabRecordPerPage);
		innerMap.put("ModelsDefaultSort", modelsTabDefaultSorting);
	}

	/**
	 * Sets the procure ment layout attrubutes.
	 */
	private void setProcureMentLayoutAttrubutes() {
		List<String> procurementTabFiltersList = new ArrayList<String>();
		List<String> procurementTabFieldsList = new ArrayList<String>();

		List<WebElement> filesTabLayoutSelectedFields = findElements(AdminTab.css_PopEditLayoutProcurementSelectedFields);
		for (WebElement e : filesTabLayoutSelectedFields)
			procurementTabFieldsList.add(e.getText());

		List<WebElement> filesTabLayoutDefaultFilters = findElements(AdminTab.css_PopEditLayoutProcurementDefaultFilters);
		for (WebElement e : filesTabLayoutDefaultFilters)
			procurementTabFiltersList.add(e.getText().replace("x", "").trim());

		if (isSelected(AdminTab.rad_PopEditLayoutModelsViewSettingsTabular))
			procurementTabListingView = true;
		else if (isSelected(AdminTab.rad_PopEditLayoutModelsViewSettingsTile))
			procurementTabListingView = false;

		String procurementTabRecordPerPage = getSelectedDropdownLabel(AdminTab.drp_PopEditLayoutProcurementViewSettingsRecPerPage);
		String procurementTabDefaultSorting = getSelectedDropdownLabel(AdminTab.drp_PopEditLayoutProcurementViewSettingsDefaultSorting);

		innerMap.put("ProcurementFields", procurementTabFieldsList);
		innerMap.put("ProcurementFilters", procurementTabFiltersList);
		innerMap.put("ProcurementListView", procurementTabListingView);
		innerMap.put("ProcurementRecords", procurementTabRecordPerPage);
		innerMap.put("ProcurementDefaultSort", procurementTabDefaultSorting);
	}

	/**
	 * Sets the editable option to layout.
	 *
	 * @param editOption the edit option
	 * @param orgTitle the org title
	 */
	public void setEditableOptionToLayout(String editOption, String orgTitle) {

		navigateLayoutOrgPage(orgTitle);

		if (orgTitle.equalsIgnoreCase("Automation Bill-To-Org"))
			jQuery = AdoddleCommonJQueries.editDesignLayoutBillToOrgJQuery;
		else
			jQuery = AdoddleCommonJQueries.editDesignLayoutUserOrgJQuery;

		executeJScript(jQuery);

		waitUntilElementIsDisplayed(AdminTab.rad_PopEditLayoutEditableNo);

		if (editOption.equalsIgnoreCase("yes")) {
			clickElementAndWait(AdminTab.rad_PopEditLayoutEditableYes);
			outerMap.get(orgTitle).put("isEditable", true);
		}
		else {
			clickElementAndWait(AdminTab.rad_PopEditLayoutEditableNo);
			outerMap.get(orgTitle).put("isEditable", false);

		}

		clickElementAndWait(AdminTab.btn_PopEditLayoutSave);

	}

	/**
	 * Verify user layout.
	 *
	 * @param org the org
	 */
	public void verifyUserLayout(String org) {
		verifyProjectTabLayout(org);
		verifyFilesTabLayout(org);
		verifyDiscussionTabLayout(org);
		verifyModelsTabLayout(org);
		navigateTab(LandingPage.lnk_Files);
	}

	/**
	 * Verify project tab layout.
	 *
	 * @param orgTitle the org title
	 */
	private void verifyProjectTabLayout(String orgTitle) {
		navigateTab(LandingPage.lnk_Projects);

		boolean allowUserEdit = (Boolean) outerMap.get(orgTitle).get("isEditable");
		boolean listViewFlag = (Boolean) outerMap.get(orgTitle).get("ProjectListView");

		List<WebElement> pHeadersElements = findElements(AdminTab.css_ProjectListingColumnHeaders);

		if (listViewFlag) {
			@SuppressWarnings("unchecked")
			List<String> pFields = (List<String>) outerMap.get(orgTitle).get("ProjectFields");
			log.info("Project Fields# " + pFields.toString());
			for (WebElement e : pHeadersElements) {
				log.info("Projects tab column title# " + e.getText());
				AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(pFields.toString(), (e.getText())), pFields.contains(e.getText()));
			}

			AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(ProjectsTab.lnk_ProjectsFirstProject, true), isDisplayed(ProjectsTab.lnk_ProjectsFirstProject));
		}
		else {

			for (WebElement e : pHeadersElements)
				AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(e, false), !e.isDisplayed());
				AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(AdminTab.lnk_ProjectListingFirstProjectThumbView, true), isDisplayed(AdminTab.lnk_ProjectListingFirstProjectThumbView));
		}

		@SuppressWarnings("unchecked")
		List<String> pFilters = (List<String>) outerMap.get(orgTitle).get("ProjectFilters");
		List<WebElement> pFilterElements = findElements(AdminTab.css_GlobalListingDefaultFiltersText);
		log.info("Project Filters# " + pFilters.toString());
		for (WebElement e : pFilterElements) {
			log.info("Projects tab filter title# " + e.getText());
			AutomationAssert.verifyTrue(eStringUtils.getContainsStringError(pFilters.toString(), e.getText().split(":")[0].trim()), pFilters.contains(e.getText().split(":")[0].trim()));
		}

		String pShowRecords = (String) outerMap.get(orgTitle).get("ProjectRecords");

		if (allowUserEdit) {
			AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getSelectedDropdownLabel(AdminTab.drp_GlobalListingShowRecords), (pShowRecords)), getSelectedDropdownLabel(AdminTab.drp_GlobalListingShowRecords).equalsIgnoreCase(pShowRecords));
			AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(AdminTab.drp_GlobalListingCustomize, true), isDisplayed(AdminTab.drp_GlobalListingCustomize));
		}
		else {
			AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(AdminTab.drp_GlobalListingShowRecords, false), !isDisplayed(AdminTab.drp_GlobalListingShowRecords));
			AutomationAssert.verifyTrue(eStringUtils.getVisibilityStringError(AdminTab.drp_GlobalListingCustomize, false), !isDisplayed(AdminTab.drp_GlobalListingCustomize));
		}

	}

	/**
	 * Verify files tab layout.
	 *
	 * @param orgTitle the org title
	 */
	private void verifyFilesTabLayout(String orgTitle) {

		navigateTab(LandingPage.lnk_Files);

		boolean allowUserEdit = (Boolean) outerMap.get(orgTitle).get("isEditable");
		boolean listViewFlag = (Boolean) outerMap.get(orgTitle).get("FilesListView");
		List<WebElement> fHeadersElements = findElements(AdminTab.css_DocumentListingColumnHeaders);

		if (listViewFlag) {

			AutomationAssert.verifyTrue(getCount(FilesTab.css_DocListingFilesCount) > 0);

			@SuppressWarnings("unchecked")
			List<String> fFields = (List<String>) outerMap.get(orgTitle).get("FilesFields");
			log.info("Files Fields# " + fFields.toString());
			for (WebElement e : fHeadersElements) {
				log.info("Files tab column title# " + e.getText());
				AutomationAssert.verifyTrue(fFields.contains(e.getText()));
			}
		}
		else {
			AutomationAssert.verifyTrue(!isDisplayed(FilesTab.lnk_DocListingFirstDocRef));
			for (WebElement e : fHeadersElements)
				AutomationAssert.verifyTrue(!e.isDisplayed());
		}

		fFilters = (List<String>) outerMap.get(orgTitle).get("FilesFilters");
		List<WebElement> fFilterElements = findElements(AdminTab.css_GlobalListingDefaultFiltersText);
		log.info("Files Tab Filters# " + fFilters.toString());
		for (WebElement e : fFilterElements) {
			log.info("Files tab filter title# " + e.getText());
			AutomationAssert.verifyTrue(fFilters.contains(e.getText().split(":")[0].trim()));
		}

		String fShowRecords = (String) outerMap.get(orgTitle).get("FilesRecords");

		if (allowUserEdit) {
			AutomationAssert.verifyTrue(getSelectedDropdownLabel(AdminTab.drp_GlobalListingShowRecords).equalsIgnoreCase(fShowRecords));
			AutomationAssert.verifyTrue(isDisplayed(AdminTab.drp_GlobalListingCustomize));
		}
		else {
			AutomationAssert.verifyTrue(!isDisplayed(AdminTab.drp_GlobalListingShowRecords));
			AutomationAssert.verifyTrue(!isDisplayed(AdminTab.drp_GlobalListingCustomize));
		}
	}

	/**
	 * Verify discussion tab layout.
	 *
	 * @param orgTitle the org title
	 */
	private void verifyDiscussionTabLayout(String orgTitle) {

		navigateToDiscussionTab();

		boolean allowUserEdit = (Boolean) outerMap.get(orgTitle).get("isEditable");
		List<WebElement> dHeadersElements = findElements(AdminTab.css_DiscussionsListingColumnHeaders);

		AutomationAssert.verifyTrue(getCount(DiscussionsTab.css_DiscussionListingDiscussionsCount) > 0);
		@SuppressWarnings("unchecked")
		List<String> dFields = (List<String>) outerMap.get(orgTitle).get("DiscussionsFields");
		log.info("Discussions Fields# " + dFields.toString());
		for (WebElement e : dHeadersElements) {
			log.info("Discussions tab column title# " + e.getText());
			AutomationAssert.verifyTrue(dFields.contains(e.getText()));
		}

		@SuppressWarnings("unchecked")
		List<String> dFilters = (List<String>) outerMap.get(orgTitle).get("DiscussionsFilters");
		dFilters.addAll(fFilters);
		List<WebElement> dFilterElements = findElements(AdminTab.css_GlobalListingDefaultFiltersText);
		log.info("Discussions Tab Filters# " + dFilters.toString());
		for (WebElement e : dFilterElements) {
			log.info("Discussions tab filter title# " + e.getText());
			AutomationAssert.verifyTrue(dFilters.contains(e.getText().split(":")[0].trim()));
		}

		String dShowRecords = (String) outerMap.get(orgTitle).get("DiscussionsRecords");

		if (allowUserEdit) {
			AutomationAssert.verifyTrue(getSelectedDropdownLabel(AdminTab.drp_GlobalListingShowRecords).equalsIgnoreCase(dShowRecords));
			AutomationAssert.verifyTrue(isDisplayed(AdminTab.drp_GlobalListingCustomize));
		}
		else {
			AutomationAssert.verifyTrue(!isDisplayed(AdminTab.drp_GlobalListingShowRecords));
			AutomationAssert.verifyTrue(!isDisplayed(AdminTab.drp_GlobalListingCustomize));
		}
	}

	/**
	 * Verify models tab layout.
	 *
	 * @param orgTitle the org title
	 */
	private void verifyModelsTabLayout(String orgTitle) {

//		navigateTab(LandingPage.lnk_Models);
		navigateTabByText("Models");

		boolean allowUserEdit = (Boolean) outerMap.get(orgTitle).get("isEditable");
		boolean listViewFlag = (Boolean) outerMap.get(orgTitle).get("ModelsListView");

		List<WebElement> mHeadersElements = findElements(AdminTab.css_ModelsListingColumnHeaders);

		if (listViewFlag) {

			AutomationAssert.verifyTrue(getCount(ModelsTab.css_ModelsListingModels) > 0);

			@SuppressWarnings("unchecked")
			List<String> mFields = (List<String>) outerMap.get(orgTitle).get("ModelsFields");
			log.info("Models Fields# " + mFields.toString());
			for (WebElement e : mHeadersElements) {
				log.info("Models tab column title# " + e.getText());
				AutomationAssert.verifyTrue(mFields.contains(e.getText()));
			}
		}
		else {
			AutomationAssert.verifyTrue(!isDisplayed(ModelsTab.lnk_firstModelName));
			for (WebElement e : mHeadersElements)
				AutomationAssert.verifyTrue(!e.isDisplayed());
		}

		@SuppressWarnings("unchecked")
		List<String> mFilters = (List<String>) outerMap.get(orgTitle).get("ModelsFilters");
		List<WebElement> mFilterElements = findElements(AdminTab.css_GlobalListingDefaultFiltersText);
		log.info("Models Tab Filters# " + mFilters.toString());
		for (WebElement e : mFilterElements) {
			log.info("Models tab filter title# " + e.getText());
			AutomationAssert.verifyTrue(mFilters.contains(e.getText().split(":")[0].trim()));
		}

		String mShowRecords = (String) outerMap.get(orgTitle).get("ModelsRecords");

		if (allowUserEdit) {
			AutomationAssert.verifyTrue(getSelectedDropdownLabel(AdminTab.drp_GlobalListingShowRecords).equalsIgnoreCase(mShowRecords));
			AutomationAssert.verifyTrue(isDisplayed(AdminTab.drp_GlobalListingCustomize));
		}
		else {
			AutomationAssert.verifyTrue(!isDisplayed(AdminTab.drp_GlobalListingShowRecords));
			AutomationAssert.verifyTrue(!isDisplayed(AdminTab.drp_GlobalListingCustomize));
		}
	}

	/**
	 * Verify customized options.
	 *
	 * @param flag the flag
	 */
	public void verifyCustomizedOptions(String flag) {
		if (flag.equalsIgnoreCase("yes")) {
			navigateTab(LandingPage.lnk_Projects);
			clickElementAndWaitForElement(ProjectsTab.ele_CustomizeListingOptions, AdminTab.drp_GlobalListingShowRecords);
			AutomationAssert.verifyTrue(isDisplayed(AdminTab.drp_GlobalListingShowRecords));
			AutomationAssert.verifyTrue(isDisplayed(AdminTab.drp_GlobalListingCustomize));

			navigateTab(LandingPage.lnk_Files);
			clickElementAndWaitForElement(FilesTab.ele_CustomizeListingOptions, AdminTab.drp_GlobalListingShowRecords);
			AutomationAssert.verifyTrue(isDisplayed(AdminTab.drp_GlobalListingShowRecords));
			AutomationAssert.verifyTrue(isDisplayed(AdminTab.drp_GlobalListingCustomize));
		}
		else {

			navigateTab(LandingPage.lnk_Projects);
			AutomationAssert.verifyTrue("Show Records should not be displayed", !isDisplayed(AdminTab.drp_GlobalListingShowRecords));
			AutomationAssert.verifyTrue("Customize Drop down should not be displayed", !isDisplayed(AdminTab.drp_GlobalListingCustomize));

			navigateTab(LandingPage.lnk_Files);
			AutomationAssert.verifyTrue(!isDisplayed(AdminTab.drp_GlobalListingShowRecords));
			AutomationAssert.verifyTrue(!isDisplayed(AdminTab.drp_GlobalListingCustomize));
		}

	}

	/**
	 * Edits the apply to option in org layout.
	 *
	 * @param orgTitle the org title
	 * @param applyTo the apply to
	 */
	public void editApplyToOptionInOrgLayout(String orgTitle, String applyTo) {

		navigateLayoutOrgPage(orgTitle);
		if (orgTitle.equalsIgnoreCase("Automation Bill-To-Org"))
			jQuery = AdoddleCommonJQueries.editDesignLayoutBillToOrgJQuery;
		else
			jQuery = AdoddleCommonJQueries.editDesignLayoutUserOrgJQuery;

		executeJScript(jQuery);

		clickElementAndWaitForElement(AdminTab.btn_PopEditLayoutApplyTo, AdminTab.chk_PopEditLayoutApplyToBillOrgUsers);

		if (applyTo.equalsIgnoreCase("Organization Users")) {

			if (!isSelected(AdminTab.chk_PopEditLayoutApplyToOrgUsers))
				clickElement(AdminTab.chk_PopEditLayoutApplyToOrgUsers);
			if (isSelected(AdminTab.chk_PopEditLayoutApplyToBillOrgUsers))
				clickElement(AdminTab.chk_PopEditLayoutApplyToBillOrgUsers);
		}
		else if (applyTo.equalsIgnoreCase("Bill To Org Users")) {

			if (!isSelected(AdminTab.chk_PopEditLayoutApplyToBillOrgUsers))
				clickElement(AdminTab.chk_PopEditLayoutApplyToBillOrgUsers);
			if (isSelected(AdminTab.chk_PopEditLayoutApplyToOrgUsers))
				clickElement(AdminTab.chk_PopEditLayoutApplyToOrgUsers);
		}
		else if (applyTo.equalsIgnoreCase("All")) {
			if (!isSelected(AdminTab.chk_PopEditLayoutApplyToBillOrgUsers))
				clickElement(AdminTab.chk_PopEditLayoutApplyToBillOrgUsers);
			if (!isSelected(AdminTab.chk_PopEditLayoutApplyToOrgUsers))
				clickElement(AdminTab.chk_PopEditLayoutApplyToOrgUsers);
		}
		else if (applyTo.equalsIgnoreCase("None")) {
			if (isSelected(AdminTab.chk_PopEditLayoutApplyToBillOrgUsers))
				clickElement(AdminTab.chk_PopEditLayoutApplyToBillOrgUsers);
			if (isSelected(AdminTab.chk_PopEditLayoutApplyToOrgUsers))
				clickElement(AdminTab.chk_PopEditLayoutApplyToOrgUsers);
		}

		clickElementAndWait(AdminTab.btn_PopEditLayoutSave);
	}

	/**
	 * Navigate layout org page.
	 *
	 * @param orgTitle the org title
	 */
	private void navigateLayoutOrgPage(String orgTitle) {
		if (orgTitle.equalsIgnoreCase("Automation Bill-To-Org"))
			jQuery = AdoddleCommonJQueries.designLayoutBillToOrgSizeJQuery;
		else
			jQuery = AdoddleCommonJQueries.designLayoutUserOrgSizeJQuery;

		while (((Long) ((JavascriptExecutor) getWebDriver()).executeScript(jQuery) != 1) && isEnabled(AdminTab.lnk_DesignLayoutListingPaginationNext)) {
			clickElementAndWait(AdminTab.lnk_DesignLayoutListingPaginationNext);
			if ((Long) ((JavascriptExecutor) getWebDriver()).executeScript(jQuery) == 1)
				break;
		}
	}
}
