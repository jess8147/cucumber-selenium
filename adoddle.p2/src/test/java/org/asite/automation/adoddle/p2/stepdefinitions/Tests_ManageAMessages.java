package org.asite.automation.adoddle.p2.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.asite.automation.adoddle.p2.scripts.ManageAMessageGroupsScripts;

public class Tests_ManageAMessages {

    ManageAMessageGroupsScripts scripts = new ManageAMessageGroupsScripts();

    @When("^I create \"(.*?)\" AMesssage group as \"(.*?)\" with project ([^\"]*) and users ([^\"]*)$")
    public void i_create_AMesssage_group_as_with_project_AutomationTestProject_and_users_Automation_UK(String type, String groupName, String project, String users) throws Throwable {
        scripts.createAMessageGroup(type, groupName, project, users);

    }

    @Then("^AMessage group should get created as \"(.*?)\"$")
    public void amessage_group_should_get_created_as(String groupName) throws Throwable {
        scripts.verifyAMessageGroupExists(groupName);


    }

    @When("^I send (\\d+) message as \"(.*?)\" to AMessage group \"(.*?)\"$")
    public void i_send_message_as_to_AMessage_group(int count, String message, String group) throws Throwable {
        scripts.sendMultipleMessagesToEntity(count, message, group, false);
    }

    @Then("^\"(.*?)\" message should be received to all ([^\"]*)$")
    public void message_should_be_received_to_all_Automation_UK(String message, String users) throws Throwable {
        scripts.verifyAMessageIsSentSucessfully(message, users);

    }

    @When("^([^\"]*) user leaves the group \"(.*?)\"$")
    public void automation_UK_user_leaves_the_group(String user, String group) throws Throwable {
        scripts.leaveAMessageGroup(user, group);
    }

    @When("^Administrator user removes the user ([^\"]*) from group \"(.*?)\"$")
    public void admin_removes_member_user(String user, String group) throws Throwable {
        scripts.removeUserFromGroup(user, group);
    }


    @Then("^\"(.*?)\" group should be \"(.*?)\" to ([^\"]*) user$")
    public void group_should_not_be_visible_to_Automation_UK_user(String group, String visibility, String user) throws Throwable {
        scripts.groupIsNotDisplayedTo(group, visibility, user);
    }

    @Then("^([^\"]*) should not be displayed as part of group to Administrator$")
    public void automation_UK_should_not_be_displayed_as_part_of_group(String user) throws Throwable {
        scripts.verifyUserAvailabilityInGroup(user);
    }

    @Given("^I deactivate AMessage group as \"(.*?)\"$")
    public void i_deactivate_group_as(String groupName) throws Throwable {
        scripts.deactivateAMessageGroup(groupName);
    }

    @Given("^I get count of unread discussions$")
    public void i_get_count_of_unread_discussions() throws Throwable {
        scripts.setUnreadDiscusionCounts();
    }

    @Given("^I logout from adoddle and close browser$")
    public void i_logout_from_adoddle_and_close_browser() throws Throwable {
        scripts.logOut();
        scripts.tearDown();
    }

}
