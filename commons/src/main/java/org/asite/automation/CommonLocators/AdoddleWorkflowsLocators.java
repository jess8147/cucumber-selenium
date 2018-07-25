package org.asite.automation.CommonLocators;

import org.openqa.selenium.By;

public class AdoddleWorkflowsLocators {

	public static class WorkflowsTab {

		public static By lnk__WorkflowsConfigureSystemActionLink = By
				.cssSelector("div[id='workflowListingSection'] div[id='fileContentContainer'] div[id='basicFilterContainer'] :nth-child(3) a[id='config-action-btn']");
		public static By opt_WorkflowsConfigureSystemActionToDropdownFirstResult = By
				.cssSelector("ul[class='select2-results'] li:nth-child(1) div[class='select2-result-label'] span[class='select2-match']");
		public static By opt_WorkflowsConfigureSystemActionToDropdownOrgABC = By
				.xpath(".//ul[@class='select2-results']//li//div[@class='select2-result-label'][contains(text(),'Organisation')]//following::span[contains(text(),'ABC Test Comp')]");
		public static By css_WorkflowsConfigureSystemActionToDropdownFirstResult = By
				.cssSelector("ul[class='select2-result-sub'] li:nth-child(1) div");
		public static By txt_WorkflowsConfigureSystemActionName = By
				.cssSelector("form[id='workflow-action-form'] input[id='actionName']");
		public static By drp_WorkflowsConfigureSystemActionTypeDropdown = By
				.cssSelector("div[id='workflowConfigAction'] select[id='actionType']");
		public static By drp_WorkflowsConfigureSystemTaskContextType = By
				.cssSelector("div[id='workflowConfigAction'] select[id='actionContext']");
		public static By drp_WorkflowsConfigureSystemTaskType = By
				.cssSelector("div[id='workflowConfigAction'] select[id='actionWorkflowFormType']");
		public static By txt_WorkflowsConfigureSystemActionTODropdown = By
				.cssSelector("div[id='workflowConfigAction'] form div[class='action-config-container distribute_doc_action'] div[class='inputRow clearfix'] :nth-child(1) input[id*='s2id_autogen']");
		public static By txt_WorkflowsConfigureSystemTaskAppTODropdown = By
				.cssSelector("div[id='workflowConfigAction'][style*='display: block'] form[id='workflow-action-form'] ul[class='select2-choices'] li[class='select2-search-field'] input[type='text']");
		public static By txt_WorkflowsConfigureSystemTaskAppGroovy = By
				.cssSelector("div[id='workflowConfigAction'][style*='display: block'] form[id='workflow-action-form'] div[class*='groovy_script'] div[class*='CodeMirror-code']");
		public static By txt_WorkflowsConfigureSystemActionSubject = By
				.cssSelector("div[id='workflowConfigAction'] form div[class='action-config-container distribute_doc_action'] div[class='inputRow clearfix'] :nth-child(2) textarea[id='subject']");
		public static By txt_WorkflowsConfigureSystemTaskSubject = By
				.cssSelector("div[id='workflowConfigAction'] form[id='workflow-action-form'] textarea[id='subject']");
		public static By txt_WorkflowsConfigureSystemTaskEmailBody = By
				.cssSelector("div[id='workflowConfigAction'] form[id='workflow-action-form'] div[class*='nicEdit-main']");
		public static By btn_WorkflowsConfigureSystemActionCreateButton = By
				.cssSelector("div[id='workflowConfigAction'] div[class='modal-footer'] button[id='actionSave']");
		public static By lnk_WorkflowsFirstSystemActionName = By
				.cssSelector("div[id='workflowList'] div[id='adTableBody'] div[class*='rows']:nth-child(1) div[class*='col-name-fixed-width'] a");
		public static By img_WorkflowsListingLastModifiedDateAsc = By
				.cssSelector("div[alt='Last Modified Date - Sorted ascending']");
		public static By img_WorkflowsListingLastModifiedDateDesc = By
				.cssSelector("div[alt='Last Modified Date - Sorted descending']");
		public static By css_WorkflowsAllSystemActionList = By
				.cssSelector("div[id='workflowList'] div[class*='innerContainer gray'] div[id='adTableBody'] div[class*='minWidth divtd col-name-fixed'] div[class='tbody-data first'] a");
		public static By sel_workflowsConfigureSystemActionStatusFrom = By
				.cssSelector("div[id='workflowConfigAction'] div[class='modal-body'] form div[class*='action-config-container'] select[id='oldStatus']");
		public static By sel_workflowsConfigureSystemActionStatusTo = By
				.cssSelector("div[id='workflowConfigAction'] div[class='modal-body'] form div[class*='action-config-container'] select[id='newStatus']");
		public static By txt_workflowsConfigureSystemActionReasonforStatusChange = By
				.cssSelector("div[id='workflowConfigAction'] div[class='modal-body'] form div[class*='action-config-container'] textarea[id='statusChangeReason']");
		public static By lnk__WorkflowsCreateWorkflowDefinitionLink = By
				.cssSelector("div[id='workflowListingSection'] div[id='fileContentContainer'] div[id='basicFilterContainer'] :nth-child(3) a[id='new-workflow']");
		public static By txt_WorkflowsCreateWorkflowDefinitionWorkflowName = By
				.cssSelector("div[id='newWorkflow'] form div:nth-child(1) input[id='inputWorkflowName']");
		public static By txt_WorkflowsCreateWorkflowDefinitionWorkflowDescription = By
				.cssSelector("form[id='newWorkflowForm'] textarea[id='inputWorkflowDesc']");
		public static By txt_WorkflowsCreateWorkflowDefinitionWorkflowContext = By
				.cssSelector("form[id='newWorkflowForm'] select[id='inputWorkflowType']");
		public static By txt_WorkflowsCreateWorkflowDefinitionWorkflowAppType = By
				.cssSelector("form[id='newWorkflowForm'] select[id='inputWorkflowFormType']");
		public static By txt_WorkflowsCreateWorkflowDefinitionWorkflowDays = By
				.cssSelector("form[id='newWorkflowForm'] input[id='inputWorkflowDueDays']");
		public static By btn_WorkflowsCreateWorkflowDefinitionCreateButton = By
				.cssSelector("div[id='newWorkflow'] div[class='modal-footer'] button[id='workflowSave']");
		public static By ele_WorkflowsVisualDesigner = By.cssSelector("div[id*='workflowDesign'] h3");
		public static By frm_Iframe = By.cssSelector("iframe[src*='asite.com']");
		public static By ele_IframeWorkflowDesignArea = By.cssSelector("svg[id*='sid-'][width='1485']");
		public static By ele_WorkflowVisualDesignerNodeConnectingEdge = By
				.cssSelector("img[title='Edge - Drag'][src*='sequenceflow.png']");
		public static By ele_WorkflowsVisualDesignerStartNode = By
				.cssSelector("svg[id*='sid-'][width='1485'] g[title='Start event']");
		public static By ele_WorkflowVisualDesignerIframeTasks = By
				.cssSelector("ul[class*='x-tree-node-ct'] div[title='Tasks']");
		public static By ele_WorkflowVisualDesignerIframeEndEvent = By
				.cssSelector("ul[class*='x-tree-node-ct'] div[title='End Event']");
		public static By ele_WorkflowVisualDesignerIframeUserTask = By
				.cssSelector("ul[class*='x-tree-node-ct'] div[title='A manual task assigned to a specific person']");
		public static By ele_WorkflowVisualDesignerIframeSystemTask = By
				.cssSelector("ul[class*='x-tree-node-ct'] div[title='An automatic task with service logic']");
		public static By ele_WorkflowVisualDesignerIframeEndTask = By
				.cssSelector("ul[class*='x-tree-node-ct'] div[title='An end event without a specific trigger']");
		public static By ele_WorkflowVisualDesignerIframeSystemTaskPropertyHeader = By
				.cssSelector("body div[class*=' x-panel-editor-south x-border-panel'] span");
		public static By ele_WorkflowVisualDesignerIframeSystemTaskNameField = By
				.cssSelector("div[class='x-grid3-scroller'] div[id*='gp-popular-true-bd'] div[class*='x-grid3-row']:nth-child(2) table[class='x-grid3-row-table'] td[class*='x-grid3-td-propertywindow_column_value'] div");
		public static By ele_WorkflowVisualDesignerIframeSystemTaskName = By
				.cssSelector("div[id*='ext-comp'][style*='visibility: visible'] input[id*='ext-comp'][class*='x-form-text-validation-field']");
		public static By ele_WorkFlowVisualDesignerXToolCollapse = By
				.cssSelector("div[class*='x-tool-collapse-south']");
		public static By ele_WorkFlowVisualDesignerXToolExpand = By.cssSelector("div[class*='x-tool-expand-south']");
		public static By ele_WorkflowVisualDesignerIframeSystemTaskDescriptionField = By
				.cssSelector("div[class='x-grid3-scroller'] div[id*='gp-popular-true-bd'] div[class*='x-grid3-row']:nth-child(3) table[class='x-grid3-row-table'] td[class*='x-grid3-td-propertywindow_column_value'] div");
		public static By txt_WorkflowVisualDesignerIframeSystemTaskDescription = By
				.cssSelector("div[class*='x-editor'][style*='visibility: visible'] textarea[class*='x-form-textarea-validation-field']");
		public static By ele_WorkflowVisualDesignerIframeSystemTaskTypeField = By
				.cssSelector("div[class='x-grid3-scroller'] div[id*='gp-popular-true-bd'] div[class*='x-grid3-row']:nth-child(19) table[class='x-grid3-row-table'] td[class*='x-grid3-td-propertywindow_column_value'] div");
		public static By sel_WorkflowVisualDesignerIframeSystemTaskType = By
				.cssSelector("div[class*='x-editor'][style*='visibility: visible'] input[class*='x-form-field']");
		public static By opt_WorkflowVisualDesignerIframeSelectedSystemTask = By
				.cssSelector("div[class*='x-combo-list'][style*='visibility: visible']  div[class*='x-combo-selected']");
		public static By pop_WorkflowVisualDesignerIframeSystemTaskPopup = By
				.cssSelector("div[class='x-window'][style*='visibility: visible'] span");
		public static By sel_WorkflowVisualDesignerIframeDistributeSystemTask = By
				.cssSelector("div[class='x-window'][style*='visibility: visible'] table[id='configTable']  tr:nth-child(2) input[type=radio]");
		public static By sel_WorkflowVisualDesignerIframeStatusChangeSystemTask = By
				.cssSelector("div[class='x-window'][style*='visibility: visible'] table[id='configTable']  tr:nth-child(3) input[type=radio]");
		public static By btn_WorkflowVisualDesignerIframeSelectActionPopupOkay = By
				.cssSelector("div[class='x-window'][style*='visibility: visible'] button[class='btn btn-danger']");
		public static By ele_WorkflowVisualDesignerIframeUserTaskNameField = By
				.cssSelector("div[class='x-grid3-scroller'] div[id*='gp-popular-true-bd'] div[class*='x-grid3-row']:nth-child(2) table[class='x-grid3-row-table'] td[class*='x-grid3-td-propertywindow_column_value'] div");
		public static By ele_WorkflowVisualDesignerIframeUserTaskName = By
				.cssSelector("div[id*='ext-comp'][style*='visibility: visible'] input[id*='ext-comp'][class*='x-form-text-validation-field']");
		public static By ele_WorkflowVisualDesignerIframeUserTaskDescriptionField = By
				.cssSelector("div[class='x-grid3-scroller'] div[id*='gp-popular-true-bd'] div[class*='x-grid3-row']:nth-child(3) table[class='x-grid3-row-table'] td[class*='x-grid3-td-propertywindow_column_value'] div");
		public static By txt_WorkflowVisualDesignerIframeUserTaskDescription = By
				.cssSelector("div[class*='x-editor'][style*='visibility: visible'] textarea[class*='x-form-textarea-validation-field']");
		public static By ele_WorkflowVisualDesignerIframeUserActionField = By
				.cssSelector("div[id*='gp-popular-true-bd'] div[class*='x-grid3-row']:nth-child(4) table[class='x-grid3-row-table'] td[class*='x-grid3-td-propertywindow_column_value'] div");
		public static By sel_WorkflowVisualDesignerIframeUserAction = By
				.cssSelector("div[id*='ext-comp'][style*='visibility: visible'] input[id*='ext-comp'][class*='x-form-text']");
		public static By opt_WorkflowVisualDesignerIframeSelectedUserTask = By
				.cssSelector("div[class*='x-combo-list'][style*='visibility: visible']  div[class*='x-combo-selected']");
		public static By ele_WorkflowVisualDesignerIframeUserToField = By
				.cssSelector("div[id*='gp-popular-true-bd'] div[class*='x-grid3-row']:nth-child(5) table[class='x-grid3-row-table'] td[class*='x-grid3-td-propertywindow_column_value'] div");
		public static By ele_WorkflowVisualDesignerIframeUserTo = By
				.cssSelector("div[id*='ext-comp'][style*='visibility: visible'] div[id*='s2id_ext-comp'] ul[class*='select2-choices'] input");
		public static By opt_WorkflowVisualDesignerIframeSelectedUserTo = By
				.cssSelector("div[class*='select2-drop-multi'] ul[class*='select2-result-sub'] div");
		public static By ele_WorkflowVisualDesignerIframeUserDueDaysField = By
				.cssSelector("div[id*='gp-popular-true-bd'] div[class*='x-grid3-row']:nth-child(8) table[class='x-grid3-row-table'] td[class*='x-grid3-td-propertywindow_column_value'] div");
		public static By ele_WorkflowVisualDesignerIframeUserDueDays = By
				.cssSelector("div[id*='ext-comp'][style*='visibility: visible'] input[id*='ext-comp'][class*='x-form-text']");
		public static By ele_WorkflowVisualDesignerIframeUserWorflowTransitionConditonField = By
				.cssSelector("div[id*='gp-popular-true-bd'] div[class*='x-grid3-row']:nth-child(21) table[class='x-grid3-row-table'] td[class*='x-grid3-td-propertywindow_column_value'] div");
		public static By sel_WorkflowVisualDesignerIframeUserWorflowTransitionConditon = By
				.cssSelector("div[id*='ext-comp'][style*='visibility: visible'] input[id*='ext-comp'][class*='x-form-text']");
		public static By opt_WorkflowVisualDesignerIframeSelectedWorflowTransitionConditon = By
				.cssSelector("div[class*='x-combo-list'][style*='visibility: visible']  div[class*='x-combo-selected']");
		public static By btn_WorkflowVisualDesignerIframeWorkflowSave = By
				.cssSelector("div[id*='ext-gen'] div[id*='ext-comp']:nth-child(2) table:nth-child(1) button[title*='Save']");
		public static By ele_WorkflowVisualDesignerIframeWorkflowMessagePopup = By
				.cssSelector("div[id='publishnow']  div[class*='x-window-header'] span");
		public static By btn_WorkflowVisualDesignerIframeWorkflowMessagePopupYes = By
				.cssSelector("div[id='publishnow']  button[class*='btn-danger']");
		public static By btn_WorkflowVisualDesignerIframeWorkflowMessageTrigerPopupLater = By
				.cssSelector("div[id='rulecreation'] button[class*='btn btn-inverse']");
		public static By btn_WorkflowVisualDesignerClose = By
				.cssSelector("div[class*='modal-scrollable'] div[class='modal-header'] button[class='close']");
		public static By lnk_WorkflowsDefinitionFirstWorkflowName = By
				.cssSelector("div[id='workflowList'] div[class*='innerContainer'] div[id*='adTableBody'] div[index='0'] div[title=''] a");
		public static By ele_WorkflowsDefinitionsEditWorkflowDetail = By
				.cssSelector("ul[class*='context-menu-root'][style*='top'] li[class*='CMicon-edit-attributes'] span");
		public static By txt_WorkflowsEditWorkflowDetailWorkflowName = By
				.cssSelector("div[id='newWorkflow'] form div:nth-child(2) input[id='inputWorkflowName']");
		public static By btn_WorkflowsEditWorkflowDetailUpdateButton = By
				.cssSelector("div[id*='newWorkflow'] div[class='modal-footer'] button[id*='workflowSave']");
		public static By lnk__WorkflowsCreateTriggerLink = By
				.cssSelector("div[id='workflowListingSection'] div[id='fileContentContainer'] div[id='basicFilterContainer'] a[id='create-rule-btn']");
		public static By txt_WorkflowsConfigureTriggerName = By
				.cssSelector("div[id='workflowRuleEngine'] div[class='modal-body'] form[id='workflow-rules-form'] div input[id='ruleName'][type='text']");
		public static By txt_WorkflowsConfigureTriggerPriority = By
				.cssSelector("div[id='workflowRuleEngine'] div[class='modal-body'] form[id='workflow-rules-form'] select[id='rulePriority']");
		public static By txt_WorkflowsConfigureTriggerDescription = By
				.cssSelector("div[id='workflowRuleEngine'] div[class='modal-body'] form[id='workflow-rules-form'] div textarea[id='ruleDesc']");
		public static By txt_WorkflowsConfigureTriggerContextType = By
				.cssSelector("div[id='workflowRuleEngine'] div[class='modal-body'] form[id='workflow-rules-form'] select[id='ruleObj']");
		public static By txt_WorkflowsConfigureTriggerAppType = By
				.cssSelector("div[id='workflowRuleEngine'] div[class='modal-body'] form[id='workflow-rules-form'] select[id='ruleWorkflowFormTypeId']");
		public static By txt_WorkflowsConfigureTriggerField = By
				.cssSelector("div[id='workflowRuleEngine'] div[class='modal-body'] form[id='workflow-rules-form'] ul[class*='rules-list'] li:last-child div[class='field'] input[id*='inputMetaDataId']");
		public static By sel_WorkflowsConfigureTriggerLastField = By
				.cssSelector("form[id='workflow-rules-form'] div[class*='rules-container'] ul[class*='rules-list'] li[class*='last-row'] input[class*='field-input']");
		public static By opt_WorkflowsConfigureTriggerFieldSearchResult = By
				.cssSelector("ul[id*='ui-id'][style*='display: block'] li[class='ui-menu-item'] a[id*='ui-id']");
		public static By txt_WorkflowsConfigureAppTriggerValue = By
				.cssSelector("div[id='workflowRuleEngine'] div[class='modal-body'] form[id='workflow-rules-form'] div[class*='rules-container'] select[class='required']");
		public static By txt_WorkflowsConfigureTriggerValue = By
				.cssSelector("div[id='workflowRuleEngine'] div[class='modal-body'] form[id='workflow-rules-form'] div[class*='rules-container'] ul[class*='rules-list'] li div[class='value'] input[id*='s2id_autogen']");
		public static By css_WorkflowsConfigureTriggerValueSearchResult = By
				.cssSelector("div[id='select2-drop'] ul[class*='select2-results'] div");
		public static By sel_WorkflowsConfigureTriggerValue = By
				.cssSelector("div[id='workflowRuleEngine'] div[class='modal-body'] form[id='workflow-rules-form'] div[class*='rules-container'] ul[class*='rules-list'] li div[class='value'] input[id*='s2id_autogen']");
		public static By lnk_WorkflowsConfigureTriggerAddLink = By
				.cssSelector("form[id='workflow-rules-form'] li[class*='rule-item']:last-child div[class='action'] a[class='add-button']");
		public static By lnk_WorkflowsConfigureTriggerRemoveink = By
				.cssSelector("form[id='workflow-rules-form'] li[class*='rule-item']:last-child div[class='action'] a[class='remove-button']");
		public static By opt_WorkflowsConfigureTriggerValueSearchResult = By
				.cssSelector("div[id='select2-drop'] ul[class*='select2-results'] div");
		public static By opt_WorkflowsConfigureTriggerValueFirstSearchResult = By
				.cssSelector("div[id='select2-drop'] ul[class*='select2-results'] li[class]:nth-child(1) div");
		public static By drp_WorkflowsConfigureTriggerEvent = By
				.cssSelector("div[id='workflowRuleEngine'] div[class='modal-body'] form[id='workflow-rules-form'] div[class='control-group'] select[id='ruleTrigger']");
		public static By drp_WorkflowsConfigureTriggerActionMode = By
				.cssSelector("div[id='workflowRuleEngine'] div[class='modal-body'] form[id='workflow-rules-form'] div[class='control-group'] select[id='ruleExecutionMode']");
		public static By drp_WorkflowsConfigureTriggerAction = By
				.cssSelector("div[id='workflowRuleEngine'] div[class='modal-body'] form[id='workflow-rules-form'] div[class='control-group'] select[id='ruleAction']");
		public static By css_WorkflowsConfigureTriggerActionOptions = By
				.cssSelector("select[id='ruleAction'] optgroup[id='2'] option");
		public static By btn_WorkflowsConfigureTriggerCreate = By
				.cssSelector("div[id='workflowRuleEngine'] div[class='modal-footer'] button[id='ruleSave']");
		public static By lnk_WorkflowsTriggersFirstTriggerName = By
				.cssSelector("div[id='workflowList'] div[class*='innerContainer'] div[id*='adTableBody'] div[class*='divtr'][index='0'] div[class*='col-name-fixed-width'] a");
		public static By lnk_WorkflowsTriggersTriggerName = By
				.cssSelector("div[id='workflowList'] div[class*='innerContainer'] div[id*='adTableBody'] div[class*='divtr'][index='0'] :nth-child(2) div[class*='tbody-data'] a");
		public static By lnk_WorkflowsTriggersSecondTriggerName = By
				.cssSelector("div[id='workflowList'] div[class*='innerContainer'] div[id*='adTableBody'] div[class*='divtr'][index='1'] div[class*='col-name-fixed-width'] a");
		public static By lnk_WorkflowsTriggersLHPanelTriggerLink = By
				.cssSelector("div[class='main-workflow'] li a[id='sidenav-workflow-rules']");
		public static By lnk_WorkflowsTriggersLHPanelSystemActionLink = By
				.cssSelector("div[class='main-workflow'] li a[id='sidenav-workflow-action']");
		public static By lnk_WorkflowsTriggersLHPanelDefinitionsLink = By
				.cssSelector("div[class='main-workflow'] li a[id='sidenav-wofkflow-models']");
		public static By css_WorkflowsListingExistingWorkflowDefs = By
				.cssSelector("div[id='workflowList'] div[class*='innerContainer'] div[class*='col-name-fixed-width'] a");
		public static By css_WorkflowsListingExistingPublishedModels = By
				.cssSelector("div[id='workflowList'] div[class*='innerContainer'] div[class*='divtr']");
		public static By css_WorkflowsListingExistingPublishedModelName = By
				.cssSelector("div[class*='col-name-fixed-width'] a");
		public static By css_WorkflowsListingExistingPublishedModelsVersion = By
				.cssSelector("div[class*='col-version']");
		public static By css_WorkflowsListingExistingPublishedModelsRevision = By
				.cssSelector("div[class*='col-revision']");
		public static By css_WorkflowsListingExistingWorkflowTriggers = By
				.cssSelector("div[id='workflowList'] div[class*='innerContainer'] div[class*='col-name-fixed-width'] a");
		public static By css_WorkflowsListingExistingSystemActions = By
				.cssSelector("div[id='adTableBody'] div[class*='col-name-fixed-width'] a");
		public static By pop_EditActionConfigurationUserList = By
				.cssSelector("div[id='s2id_distributionUser'] ul[class='select2-choices'] div");
		public static By btn_EditActionConfigurationToggleDropDown = By.cssSelector("button[class*='dropdown-toggle']");
		public static By sel_EditActionConfigurationToggleDropDownActions = By
				.cssSelector("ul[class*='context-menu-list'] select[name*='input-actions']");
		public static By sel_EditActionConfigurationToggleDropDownActionDueDate = By
				.cssSelector("ul[class*='context-menu-list'] select[name*='input-actionDate']");
		public static By css_EditActionConfigurationToggleDropDownSpanDismiss = By
				.cssSelector("ul[class*='context-menu-list'] li[class*='CMicon-close'] span");

		public static By lnk_EditTriggerValueClear = By
				.cssSelector("form[id='workflow-rules-form'] div[class*='rules-container'] ul[class*='rules-list'] li div[class='value'] a[class='select2-search-choice-close']");
		public static By rad_SystemActionMarkfileVersionPrivate = By
				.cssSelector("div[class*=' update_file_privacy'] input[id='markFileVersion0'][type='radio']");
		public static By rad_SystemActionMarkfileVersionPublic = By
				.cssSelector("div[class*=' update_file_privacy'] input[id='markFileVersion1'][type='radio']");

		/* Pre- Post Trigger Conditions */
		public static By pop_FileUploadGroovyConditionResults = By
				.cssSelector("div[class='modal-scrollable'][style*='z-index'] div[id='fileUploadfundingService'][style*='display: block']");
		public static By btn_PopFileUploadGroovyResultsOK = By
				.cssSelector("div[class='modal-scrollable'][style*='z-index']  div[id='fileUploadfundingService'] button[id='fileUploadfundingCancel']");
		public static By css_PopGroovyResultsFileList = By
				.cssSelector("div[id='fileUploadfundingService'][style*='display: block'] div[id='fileUploadfundingServiceTable'] div[class*='bodyTable'] tr td:nth-child(2)");
		public static By css_PopGroovyResultsFailureReasonList = By
				.cssSelector("div[id='fileUploadfundingService'][style*='display: block'] div[id='fileUploadfundingServiceTable'] div[class*='bodyTable'] tr td:nth-child(3)");
		public static By css_PopGroovyResultsRows = By
				.cssSelector("div[id='fileUploadfundingServiceTable'] table[class*='table-body'] tbody tr td[class='file-name']");
		public static By css_PopGroovyEditActionTextAreaStrings = By
				.cssSelector("div[class='CodeMirror-lines'] div[class='CodeMirror-code'] span[class*='cm-string']");

		/* Template Cloned Inheritance */

		public static By Rad_ClearSystemActionOptActiveVersion = By
				.cssSelector("div[class*='clear_revision_actions'] input[id='allRevisions3']");
		public static By chk_ClearSystemActionsList = By
				.cssSelector("div[class*='clear_revision_actions'] div[class*='selectWrapper'] input");
		public static By chk_ClearSystemActionsForAction = By
				.cssSelector("div[class*='clear_revision_actions'] div[class*='selectWrapper'] input[id='actions1']");
		public static By chk_ClearSystemActionsForInformation = By
				.cssSelector("div[class*='clear_revision_actions'] div[class*='selectWrapper'] input[id='actions6']");
		public static By chk_ClearSystemActionsForStatusChange = By
				.cssSelector("div[class*='clear_revision_actions'] div[class*='selectWrapper'] input[id='actions6']");
		public static By lnk_WorkflowVisualDesignerfieldBoxlist = By
				.cssSelector("div[id*='s2id_ext-comp'] ul[class*='select2-choices'] li[class='select2-search-choice'] a[class*='search-choice-close']");
		public static By css_WorkflowVisualDesignerUserTasksList = By
				.cssSelector("g[class='stencils'] g[class='children'] g[id*='svg-sid'] g[title*='User task']");
		public static By btn_WorkflowsEditWorkflowDetailCancel = By
				.cssSelector("div[id*='newWorkflow'] div[class='modal-footer'] button[id*='workflowCancel']");
		public static By btn_WorkflowsEditTriggerCancel = By
				.cssSelector("div[class='modal-footer'] button[id='ruleCancel']");
		public static By btn_WorkflowsEditActionConfigurationCancel = By
				.cssSelector("div[class='modal-footer'] button[id='actionCancel']");
		public static By sel_WorkflowsConfigureTriggerSecondryValue = By
				.cssSelector("form[id='workflow-rules-form'] div[class*='rules-container'] ul[class*='rules-list'] div[class='value'] select");

		/* Publish Document as PDF */

		public static By rad_PublishAsPDFPoiModify = By
				.cssSelector("div[class*='publish_as_pdf_action'] div[class*='inputWrapper'] input[id*='existing-poi1']");
		public static By sel_PublishAsPDFPoiModifyDropdown = By
				.cssSelector("div[class*='publish_as_pdf_action'] div[class*='inputWrapper'] select[id='modified-poi']");
		public static By rad_PublishAsPDFStatusModify = By
				.cssSelector("div[class*='publish_as_pdf_action'] div[class*='inputWrapper'] input[id*='existing-status1");
		public static By sel_PublishAsPDFStatusModifyDropdown = By
				.cssSelector("div[class*='publish_as_pdf_action'] div[class*='inputWrapper'] select[id='modified-status']");
		public static By chk_PublishAsPDFAttachments = By
				.cssSelector("div[class*='publish_as_pdf_action'] div[class*='inputWrapper'] input[id='document-include0']");
		public static By chk_PublishAsPDFCommentContents = By
				.cssSelector("div[class*='publish_as_pdf_action'] div[class*='inputWrapper'] input[id='document-include3']");
		public static By chk_PublishAsPDFCommentAttachments = By
				.cssSelector("div[class*='publish_as_pdf_action'] div[class*='inputWrapper'] input[id='document-include4']");
		public static By chk_PublishAsPDFStartWorkflowOnFilePublish = By
				.cssSelector("div[class*='publish_as_pdf_action'] div[class*='inputWrapper'] input[id='kickoff-workflow']");

	}

}