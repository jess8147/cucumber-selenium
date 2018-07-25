package org.asite.automation.adoddle.p2.stepdefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.asite.automation.adoddle.p2.scripts.ManageAMessageGroupsScripts;

public class Tests_SendDirectMessages {

    ManageAMessageGroupsScripts scripts = new ManageAMessageGroupsScripts();

    @When("^I search ([^\"]*) on AMessage LH panel and send (\\d+) direct messages \"([^\"]*)\" attachment$")
    public void i_send_direct_messages_to_Automation_UK(String receiverEntity, int count, String attachmentFlag) throws Throwable {
        scripts.sendDirectMessages(receiverEntity, count, attachmentFlag);
    }

    @Then("^Sent messages should be available in chat panel$")
    public void sent_messages_should_be_available_in_chat_panel() throws Throwable {
        scripts.verifySentMessagesOutbox();
    }

    @Then("^([^\"]*) should get messages sent from ([^\"]*)$")
    public void automation_UK_should_get_messages_sent_from_Automation_UKP(String receiver, String sender) throws Throwable {
        scripts.verifyRecievedMessagesInbox(receiver, sender);
    }

    @Then("Unread discussion counts should increase by (\\d+)$")
    public void unread_discussion_counts_should_increase_by(int increaseCount) {
        scripts.verifyUnreadDiscussionCounts(increaseCount);
    }

    @Then("^([^\"]*) should be able to view attachments in message$")
    public void receiver_should_be_able_to_view_attachments_in_message(String receiver) throws Throwable {
        scripts.verifyAMessageAttachments(receiver);
    }

    @When("^([^\"]*) replies the last message with attachment$")
    public void automation_UK_replies_the_message_with_attachment(String receiver) throws Throwable {
        scripts.replyLastAmessageWithAttachment(receiver);
    }

    @Then("^([^\"]*) should see the reply with attachment by ([^\"]*)$")
    public void automation_UKP_should_receive_the_replied_response_with_attachment(String user, String responder) throws Throwable {
        scripts.verifyRepliedMessage(user, responder);
    }


}
