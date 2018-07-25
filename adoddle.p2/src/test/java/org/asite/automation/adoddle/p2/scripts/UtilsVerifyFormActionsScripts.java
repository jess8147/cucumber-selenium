package org.asite.automation.adoddle.p2.scripts;

import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;

import java.util.Arrays;
import java.util.List;

public class UtilsVerifyFormActionsScripts extends AdoddleCommonAppMethods {

    public void distributeFormActions(String users, List<String> actions) throws InterruptedException {
        waitAndSwitchIframe(ProjectFormsTab.frm_createFormIframe);
        assignFormActionsToMultipleUsers(Arrays.asList(users), actions);
    }
}
