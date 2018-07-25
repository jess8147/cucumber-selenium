package org.asite.automation.common.utils;

import org.asite.automation.common.base.TestScenarioCheckBase;
import org.asite.automation.common.resources.AdoddleScenarioMarkers;
import org.asite.automation.common.resources.AdoddleScenariosPool;

import cucumber.api.Scenario;

// TODO: Auto-generated Javadoc
/**
 * The Class AdoddleScenarioCheckUtils.
 * @author jasminprajapati
 */
public class ScenarioCheckUtils implements TestScenarioCheckBase {

	/** The scenario title. */
	static Scenarios scenarioTitle;
	
	/** The Scenario Marker object. */
	static AdoddleScenarioMarkers asm = null;
	
	/**
	 * Instantiates a new adoddle scenario check utils.
	 */
	public ScenarioCheckUtils() {
		asm = new AdoddleScenarioMarkers();
	}
	
	/**
	 * The Enum Scenarios.
	 */
	public enum Scenarios {
		
		/**  The create comment with attachments assocications P1. */
		DOWNLOAD_AND_VALIDATE_COMMENT_ATTACHMENTS (AdoddleScenariosPool.DOWNLOAD_AND_VALIDATE_COMMENT_ATTACHMENTS),
		
		/** The create comment with attachments assocications. */
		CREATE_COMMENT_WITH_ATTACHMENTS_ASSOCICATIONS (AdoddleScenariosPool.CREATE_COMMENT_WITH_ATTACHMENTS_ASSOCICATIONS),
		
		/** The CREAT e_ commentin g_ for m_ p1. */
		CREATE_COMMENTING_FORM_P1(AdoddleScenariosPool.CREATE_COMMENTING_FORM_P1),
		
		/** The reply to private comment. */
		REPLY_TO_PRIVATE_COMMENT (AdoddleScenariosPool.REPLY_TO_PRIVATE_COMMENT),
		
		/** The delegate actions. */
		DELEGATE_ACTIONS (AdoddleScenariosPool.DELEGATE_ACTIONS),
		
		/** The clear actions. */
		CLEAR_ACTIONS (AdoddleScenariosPool.CLEAR_ACTIONS),
		
		/** The create commenting form accepted with comments. */
		CREATE_COMMENTING_FORM_ACCEPTED_WITH_COMMENTS (AdoddleScenariosPool.CREATE_COMMENTING_FORM_ACCEPTED_WITH_COMMENTS),
		
		/** The check workflow all org users. */
		CHECK_WORKFLOW_ALL_ORG_USERS (AdoddleScenariosPool.CHECK_WORKFLOW_ALL_ORG_USERS),
		
		/** The check workflow user. */
		CHECK_WORKFLOW_USER (AdoddleScenariosPool.CHECK_WORKFLOW_USER),
		
		/** The check workflow sys task. */
		CHECK_WORKFLOW_SYS_TASK (AdoddleScenariosPool.CHECK_WORKFLOW_SYS_TASK),
		
		/** The check workflow failed status. */
		CHECK_WORKFLOW_FAILED_STATUS (AdoddleScenariosPool.CHECK_WORKFLOW_FAILED_STATUS),
		
		/** The create fwd with attachments associations. */
		CREATE_FWD_WITH_ATTACHMENTS_ASSOCIATIONS (AdoddleScenariosPool.CREATE_FWD_WITH_ATTACHMENTS_ASSOCIATIONS),
		
		/** The create res with attachments associations. */
		CREATE_RES_WITH_ATTACHMENTS_ASSOCIATIONS (AdoddleScenariosPool.CREATE_RES_WITH_ATTACHMENTS_ASSOCIATIONS),
		
		/** The download attachments associations. */
		DOWNLOAD_ATTACHMENTS_ASSOCIATIONS (AdoddleScenariosPool.DOWNLOAD_ATTACHMENTS_ASSOCIATIONS),
		
		/** The download attachments associations form viewer. */
		DOWNLOAD_ATTACHMENTS_ASSOCIATIONS_FORM_VIEWER (AdoddleScenariosPool.DOWNLOAD_ATTACHMENTS_ASSOCIATIONS_FORM_VIEWER),
		
		/** The edit form template settings yes. */
		EDIT_FORM_TEMPLATE_SETTINGS_YES (AdoddleScenariosPool.EDIT_FORM_TEMPLATE_SETTINGS_YES),
		
		/** The edit form template settings no. */
		EDIT_FORM_TEMPLATE_SETTINGS_NO (AdoddleScenariosPool.EDIT_FORM_TEMPLATE_SETTINGS_NO),
		
		/** The external distribution to users. */
		EXTERNAL_DISTRIBUTION_TO_USERS (AdoddleScenariosPool.EXTERNAL_DISTRIBUTION_TO_USERS),
		
		/** The create field location. */
		CREATE_FIELD_LOCATION (AdoddleScenariosPool.CREATE_FIELD_LOCATION),
		
		/** The create edit workflow verify details. */
		CREATE_EDIT_WORKFLOW_VERIFY_DETAILS (AdoddleScenariosPool.CREATE_EDIT_WORKFLOW_VERIFY_DETAILS),
		
		
		/** The lock unlock activity file distribution. */
		LOCK_UNLOCK_ACTIVITY_FILE_DISTRIBUTION (AdoddleScenariosPool.LOCK_UNLOCK_ACTIVITY_FILE_DISTRIBUTION),
		
		/** The lock unlock activity update status. */
		LOCK_UNLOCK_ACTIVITY_UPDATE_STATUS (AdoddleScenariosPool.LOCK_UNLOCK_ACTIVITY_UPDATE_STATUS),
		
		/** The lock unlock activity commenting. */
		LOCK_UNLOCK_ACTIVITY_COMMENTING (AdoddleScenariosPool.LOCK_UNLOCK_ACTIVITY_COMMENTING),
		
		/** The reactivate document actions. */
		REACTIVATE_DOCUMENT_ACTIONS (AdoddleScenariosPool.REACTIVATE_DOCUMENT_ACTIONS),

		/** The QA Not Accepted status package form. */
		VERIFY_QA_NOT_ACCEPTED_STATUS_PACKAGE_FORM (AdoddleScenariosPool.VERIFY_QA_NOT_ACCEPTED_STATUS_PACKAGE_FORM),
		
		/** Global Federated Search. */
		GLOBAL_FEDERATED_SEARCH (AdoddleScenariosPool.GLOBAL_FEDERATED_SEARCH),
		
		/** Global Federated Search Cleanup */
		GLOBAL_FEDERATED_SEARCH_CLEANUP (AdoddleScenariosPool.GLOBAL_FEDERATED_SEARCH_CLEANUP),

		/** Edit Document Attributes Assignmetadata cleanup */
		EDIT_DOCUMENT_ATTRIB_ASSIGN_METADATA_CLEANUP(AdoddleScenariosPool.EDIT_DOCUMENT_ATTRIB_ASSIGN_METADATA_CLEANUP);
		
		/** The title. */
		private final String title;
		
		/**
		 * Instantiates a new scenarios.
		 *
		 * @param title the title
		 */
		private Scenarios(String title) {
			this.title = title;
		}

		/**
		 * Gets the scenario title.
		 *
		 * @return the scenario title
		 */
		/*public String getScenarioTitle() {
			return title;
		}*/
		
		/**
		 * Gets the enum from string.
		 *
		 * @param str the str
		 * @return the enum from string
		 */
		public static Scenarios getEnumFromString(String str) {
		    for (Scenarios b : Scenarios.values()) {
		      if (b.title.equalsIgnoreCase(str)) {
		        return b;
		      }
		    }
		    return null;
		  }
	}
	
	/**
	 * Checks if is scenario dependent.
	 *
	 * @param scenario
	 *            the scenario
	 * @return true, if is scenario dependent
	 */
	public boolean isScenarioDependent(Scenario scenario) {
		
		initiateScenarioCheckUntilInstance();
		
		scenarioTitle = Scenarios.getEnumFromString(scenario.getName());
		
		if(!(scenarioTitle == null)) {
			
			switch(scenarioTitle) {
		
			case DOWNLOAD_AND_VALIDATE_COMMENT_ATTACHMENTS:
				return asm.getScenarioFlag(AdoddleScenarioMarkers.createCommentP1Flag);
			case REPLY_TO_PRIVATE_COMMENT:
				return asm.getScenarioFlag(AdoddleScenarioMarkers.createPrivateCommentFlag);
			case DELEGATE_ACTIONS:
				return asm.getScenarioFlag(AdoddleScenarioMarkers.clearDelegateActionsScriptFlag);
			case CLEAR_ACTIONS:
				return asm.getScenarioFlag(AdoddleScenarioMarkers.clearDelegateActionsScriptFlag);
			case CHECK_WORKFLOW_ALL_ORG_USERS:
				return asm.getScenarioFlag(AdoddleScenarioMarkers.clonedInheritanceWorkflowFlag);
			case CHECK_WORKFLOW_FAILED_STATUS:
				return asm.getScenarioFlag(AdoddleScenarioMarkers.clonedInheritanceWorkflowFlag);
			case CHECK_WORKFLOW_SYS_TASK:
				return asm.getScenarioFlag(AdoddleScenarioMarkers.clonedInheritanceWorkflowFlag);
			case CHECK_WORKFLOW_USER:
				return asm.getScenarioFlag(AdoddleScenarioMarkers.clonedInheritanceWorkflowFlag);
			case CREATE_COMMENTING_FORM_ACCEPTED_WITH_COMMENTS:
				return asm.getScenarioFlag(AdoddleScenarioMarkers.createPackageFormScriptP2Flag);
			case CREATE_EDIT_WORKFLOW_VERIFY_DETAILS:
				return asm.getScenarioFlag(AdoddleScenarioMarkers.createEditSystemActionsScriptFlag);
			case CREATE_FIELD_LOCATION:
				return asm.getScenarioFlag(AdoddleScenarioMarkers.createSiteLocationFlag);
			case CREATE_FWD_WITH_ATTACHMENTS_ASSOCIATIONS:
				return asm.getScenarioFlag(AdoddleScenarioMarkers.createMultipleRepliesScriptFlag);
			case CREATE_RES_WITH_ATTACHMENTS_ASSOCIATIONS:
				return asm.getScenarioFlag(AdoddleScenarioMarkers.createMultipleRepliesScriptFlag);
			case DOWNLOAD_ATTACHMENTS_ASSOCIATIONS:
				return asm.getScenarioFlag(AdoddleScenarioMarkers.createMultipleRepliesScriptFlag);
			case DOWNLOAD_ATTACHMENTS_ASSOCIATIONS_FORM_VIEWER:
				return asm.getScenarioFlag(AdoddleScenarioMarkers.createMultipleRepliesScriptFlag);
			case EXTERNAL_DISTRIBUTION_TO_USERS:
				return asm.getScenarioFlag(AdoddleScenarioMarkers.commentingExternalDistributionFlag);
			case CREATE_COMMENTING_FORM_P1:
				return asm.getScenarioFlag(AdoddleScenarioMarkers.createCommentingFormP1Flag);
			case LOCK_UNLOCK_ACTIVITY_FILE_DISTRIBUTION:
				return asm.getScenarioFlag(AdoddleScenarioMarkers.lockUnlockActivityFlag);
			case LOCK_UNLOCK_ACTIVITY_UPDATE_STATUS:
				return asm.getScenarioFlag(AdoddleScenarioMarkers.lockUnlockActivityFlag);
			case LOCK_UNLOCK_ACTIVITY_COMMENTING:
				return asm.getScenarioFlag(AdoddleScenarioMarkers.lockUnlockActivityFlag);
			case REACTIVATE_DOCUMENT_ACTIONS:
				return asm.getScenarioFlag(AdoddleScenarioMarkers.deactivateReactivateDocActionsFlag);
			case VERIFY_QA_NOT_ACCEPTED_STATUS_PACKAGE_FORM:
				return asm.getScenarioFlag(AdoddleScenarioMarkers.packageFormSubmittedStatusFlag);
			case GLOBAL_FEDERATED_SEARCH:
				return asm.getScenarioFlag(AdoddleScenarioMarkers.federatedSearchStatusFlag);
			case GLOBAL_FEDERATED_SEARCH_CLEANUP:
				return asm.getScenarioFlag(AdoddleScenarioMarkers.federatedSearchStatusFlag);
			case EDIT_DOCUMENT_ATTRIB_ASSIGN_METADATA_CLEANUP:
				return asm.getScenarioFlag(AdoddleScenarioMarkers.cleanEditDocumentAttributeFlag);
			default:
				return true;
			}
		}
		return true;
	}
	
	/**
	 * Initiate scenario check until instance.
	 */
	public static void initiateScenarioCheckUntilInstance() {
		new ScenarioCheckUtils();
	}
	
	
}
