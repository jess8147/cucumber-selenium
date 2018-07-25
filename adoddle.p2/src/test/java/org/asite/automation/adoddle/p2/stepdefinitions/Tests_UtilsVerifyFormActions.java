package org.asite.automation.adoddle.p2.stepdefinitions;

import cucumber.api.java.en.When;
import org.asite.automation.adoddle.p2.scripts.UtilsVerifyFormActionsScripts;

import java.util.List;

public class Tests_UtilsVerifyFormActions {

    UtilsVerifyFormActionsScripts uscripts = new UtilsVerifyFormActionsScripts();

    @When("^I distribute form actions to user ([^\"]*)$")
    public void i_distribute_form_actions(String users, List<String> actions) throws Throwable {
        uscripts.distributeFormActions(users, actions);
    }
}
