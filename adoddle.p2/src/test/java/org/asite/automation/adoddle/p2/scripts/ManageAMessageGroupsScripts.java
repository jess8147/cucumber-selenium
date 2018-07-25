package org.asite.automation.adoddle.p2.scripts;

import org.asite.automation.CommonLocators.AdoddleDashboardLocators.DashboardTab;
import org.asite.automation.CommonLocators.AdoddleDiscussionsLocators.DiscussionsTab;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.errors.AutomationErrors;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ManageAMessageGroupsScripts extends AdoddleCommonAppMethods{

        private String          message, reply, adminUser                       = null;
        private static int      unreadDiscussionCount                           = 0;
        private int             numberofmessages                                = 0;


    public void createAMessageGroup(String type, String groupName, String projectTitle, String users) {
            adminUser = getElementAttributeValue(LandingPage.ele_LoggedInUser, "title").split(",")[0].trim();
            log.info("Group Administrator :"+adminUser);
            List<String> userList = Arrays.asList(users.split(","));
            clickElementAndWaitForElement(DiscussionsTab.lnk_AMessagesTabCreateGroup, DiscussionsTab.txt_AMessageTabCreateGroupName);

            if("public".equalsIgnoreCase(type))
                clickElementAndWaitForElement(DiscussionsTab.btn_AMessageCreateGroupTypePrivate, DiscussionsTab.btn_AMessageCreateGroupTypePublic);
            if (groupName.contains("timeStamp"))
                sendKeys(DiscussionsTab.txt_AMessageTabCreateGroupName, epoch);
            else
                sendKeys(DiscussionsTab.txt_AMessageTabCreateGroupName, groupName);

            sendKeys(DiscussionsTab.txt_AMessageTabCreateGroupName, Keys.TAB);
            clickElementAndWait(DiscussionsTab.txt_AMessageTabCreateGroupProjectName);
            sendKeys(DiscussionsTab.txt_AMessageTabCreateGroupProjectName, projectTitle);
            waitUntilElementContainsText(DiscussionsTab.opt_AMessageTabCreateGroupProjectFirstOption, projectTitle);
            clickElementAndWait(DiscussionsTab.opt_AMessageTabCreateGroupProjectFirstOption);
            if("private".equalsIgnoreCase(type)) {
                clickElementAndWait(DiscussionsTab.txt_AMessageTabCreateGroupMembers);
                for (String user : userList) {
                    sendKeys(DiscussionsTab.txt_AMessageTabCreateGroupMembers, user);
                    waitUntilElementContainsText(DiscussionsTab.opt_AMessageTabCreateGroupMembersFirstOption, user);
                    clickElementAndWaitForInvisibilityOfElement(DiscussionsTab.opt_AMessageTabCreateGroupMembersFirstOption, DiscussionsTab.opt_AMessageTabCreateGroupMembersFirstOption);
                    sendKeys(DiscussionsTab.txt_AMessageTabCreateGroupMembers, Keys.TAB);
                }
            }
            sendKeys(DiscussionsTab.txt_AMessageTabCreateGroupDescription, resourceUtils.getSpecialCharString());
            clickElementAndWaitForInvisibilityOfElement(DiscussionsTab.btn_AMessageTabCreateGroupSave, DiscussionsTab.btn_AMessageTabCreateGroupSave);
        }

        public boolean verifyAMessageGroupExists(String groupName) {
            try {
                if (groupName.contains("timeStamp")) {
                    searchUserGroupEntity(epoch);
                }
                return true;
            }
            catch(Throwable t) {
                return false;
            }
        }

        public void sendMultipleMessagesToEntity(int count, String msg, String entity, boolean isAttachment) throws InterruptedException {
            numberofmessages = count;
            if(msg.contains("specialchars"))
                    message = resourceUtils.getSpecialCharString().replace("<","").replace(">","").replace("\"","\\\"") + epoch;
            else
                    message = msg + epoch;

            log.info("Message to be send :"+message);
            if(entity.contains("timeStamp"))
                waitUntilElementContainsText(DiscussionsTab.lbl_AmessageTabChatPanelHeader, epoch);
            else
                waitUntilElementContainsText(DiscussionsTab.lbl_AmessageTabChatPanelHeader, entity);

            String query = "$(\"div[class='ql-editor ql-blank'] p\").text(\""+message+"\")";
            log.info("Query to be shoot :"+query);
            for(int index=0; index < count; index++) {
                waitForCompletePageLoad();
                waitUtils.waitInterval(1);
                if(isAttachment) {
                    try {
                        String filePath = sysUtils.createFile(nodeIP + ResourceHandler.loadProperty("auto.create.testdata.filepath")+epoch+AdoddleCommonStringPool.TXT_EXTENSION);
                        uploadFileUsingKeys(By.cssSelector("adoddle-messages div[class*='input-container'] a[class='attachment-btn btn-file'] input"),Arrays.asList(filePath));
                    } catch (IOException e) {
                        log.info("Error while creating file :"+e.getLocalizedMessage());
                    }
                }
                executeJScript(query);
                waitUtils.waitInterval(1);
                clickElement(DiscussionsTab.btn_AMessageTabChatPanelSend);
                log.info("Expected Text: "+message.replace("\\",""));
                waitUntilElementContainsText(DiscussionsTab.lbl_AMessageTabChatPanelLastMessage, message.replace("\\", ""));
            }
        }

        public void verifyAMessageIsSentSucessfully(String msg, String users) {
            List<String> receivers = Arrays.asList(users.split(","));
            log.info("Receivers : "+receivers.toString());
            for(String user: receivers) {
                logOut();
                login(getUserID(user), resourceUtils.getCommonUserPassword());
                if(user.equalsIgnoreCase("Automation UK"))
                    AutomationAssert.verifyTrue(getUnreadDiscusionCounts() > unreadDiscussionCount);
                navigateTab(LandingPage.lnk_Discussion);
                AutomationAssert.verifyTrue(verifyAMessageGroupExists("timeStamp"));
                verifyTextMessageExists(message.replace("\\", ""));
            }
        }

        public void verifyTextMessageExists(String text) {
            int counter =0;
            List<WebElement> messages =findElements(DiscussionsTab.css_AMessageTabChatPanelMessages);
            Collections.reverse(messages);

            for(WebElement e: messages) {
                if(counter == numberofmessages)
                    break;
                AutomationAssert.verifyTrue(eStringUtils.getInEqualityStringError(getElementText(e).trim(), (text)), getElementText(e).trim().equalsIgnoreCase(text));
                counter++;
            }
        }

        public void verifyAMessageAttachments(String receiver) {
            List<WebElement> aMessageAttachments = findElements(DiscussionsTab.css_AMessageTabChatPanelAttachments);
            int counter = 0;
            for(WebElement e: aMessageAttachments) {
                if(counter == numberofmessages)
                    break;
                String parentWindow = clickElementAndSwitchWindow(e);
                try {
                    waitForHTMLFileView();
                } catch (InterruptedException ie) {
                    throw new AutomationErrors("Unable to view Amessage attachment file");
                }
                closeCurrentWindow();
                switchPreviousWindow(parentWindow);
                counter++;
            }
        }

        public void replyLastAmessageWithAttachment(String receiver) throws InterruptedException {
            if(!getElementAttributeValue(LandingPage.ele_LoggedInUser, "title").contains(receiver + AdoddleCommonStringPool.COMMA_STRING)) {
                logOut();
                login(getUserID(receiver), resourceUtils.getCommonUserPassword());
            }
            reply = dateUtils.getEpoch();
            clickElementAndWaitForElement(LandingPage.lnk_Discussion, DiscussionsTab.lbl_AMessageTabChatPanelLastMessage);
            clickElementAndWaitForElement(DiscussionsTab.img_AMessageTabChatPanelLastMessageUserCircle, DiscussionsTab.ele_AMessageUserCircleOpenCardDetails);
            clickElementAndWait(DiscussionsTab.img_AMesssageTabChatPanelLastMessageReply);
            waitUntilElementIsDisplayed(DiscussionsTab.ele_AMessageTabChatPanelReplyBody);
            waitUtils.waitInterval(1);
            String query = "$(\"div[class='ql-editor ql-blank'] p\").text(\""+reply+"\")";
            executeJScript(query);
            uploadFileUsingKeys(DiscussionsTab.btn_AMessageTabChatPanelAttachment, Arrays.asList(ResourceHandler.loadProperty("single.file.path")));
            executeJScript(query);
            waitUtils.waitInterval(1);
            clickElement(DiscussionsTab.btn_AMessageTabChatPanelSend);
            waitUntilElementContainsText(DiscussionsTab.lnk_AMessageTabChatPanelLastMessageReply, reply);
        }

        public void verifyRepliedMessage(String user, String responder) {
            if(!getElementAttributeValue(LandingPage.ele_LoggedInUser, "title").contains(user + AdoddleCommonStringPool.COMMA_STRING)) {
                logOut();
                login(getUserID(user), resourceUtils.getCommonUserPassword());
            }
            navigateTab(LandingPage.lnk_Discussion);
            searchUserGroupEntity(responder);
            AutomationAssert.verifyTrue(getElementText(DiscussionsTab.lnk_AMessageTabChatPanelLastMessageReply).equalsIgnoreCase(reply));
        }



        public void leaveAMessageGroup(String user, String groupName) {
            if(!getElementAttributeValue(LandingPage.ele_LoggedInUser, "title").contains(user + AdoddleCommonStringPool.COMMA_STRING)) {
                logOut();
                login(getUserID(user), resourceUtils.getCommonUserPassword());
            }
            navigateTab(LandingPage.lnk_Discussion);
            verifyAMessageGroupExists(groupName);
            reloadPage();
            waitForCompletePageLoad();
            mouseHover(DiscussionsTab.ele_AMessageTabActiveGroup);
            waitUntilElementIsDisplayed(DiscussionsTab.lnk_AMessageTabGroupOptions);
            mouseHover(DiscussionsTab.lnk_AMessageTabGroupOptions);
            clickElementAndWait(DiscussionsTab.opt_AMessageTabEditGroupOption);
        }

        public void removeUserFromGroup(String user, String groupName) {
            if(!getElementAttributeValue(LandingPage.ele_LoggedInUser, "title").contains(adminUser + AdoddleCommonStringPool.COMMA_STRING)) {
                logOut();
                login(getUserID(adminUser), resourceUtils.getCommonUserPassword());
            }
            navigateTab(LandingPage.lnk_Discussion);
            verifyAMessageGroupExists(groupName);
            clickElementAndWaitForElement(DiscussionsTab.btn_AMessageTabChatPanelMembers,DiscussionsTab.lnk_AMessageTabMembersAddMember);

            for(WebElement e: findElements(DiscussionsTab.css_AMessageTabMembersList)) {
                if(getElementText(e).contains(user)) {
                    clickElementAndWaitForInvisibilityOfElement(e.findElement(By.cssSelector("span[style*='right']")), e);
                    break;
                }
            }

        }

        public void groupIsNotDisplayedTo(String group, String visibility, String user) {
            if(!getElementAttributeValue(LandingPage.ele_LoggedInUser, "title").contains(user + AdoddleCommonStringPool.COMMA_STRING)) {
                logOut();
                login(getUserID(user), resourceUtils.getCommonUserPassword());
            }
            navigateTab(LandingPage.lnk_Discussion);
            if("invisible".equalsIgnoreCase(visibility))
                AutomationAssert.verifyFalse(group+" group should not be visible to "+user, verifyAMessageGroupExists("timeStamp"));
            else
                AutomationAssert.verifyTrue(group+" group should be visible to "+user, verifyAMessageGroupExists("timeStamp"));
        }

        public void verifyUserAvailabilityInGroup(String user) {
            if(!getElementAttributeValue(LandingPage.ele_LoggedInUser, "title").contains(adminUser + AdoddleCommonStringPool.COMMA_STRING)) {
                logOut();
                login(getUserID(adminUser), resourceUtils.getCommonUserPassword());
            }
            navigateTab(LandingPage.lnk_Discussion);
            AutomationAssert.verifyTrue(verifyAMessageGroupExists("timeStamp"));
            clickElementAndWaitForElement(DiscussionsTab.btn_AMessageTabChatPanelMembers, DiscussionsTab.lnk_AMessageTabMembersAddMember);
            List<WebElement> membersList = findElements(DiscussionsTab.css_AMessageTabMembersList);
            for(WebElement member: membersList) {
                AutomationAssert.verifyTrue(! getElementText(member).equalsIgnoreCase(user));
            }
        }

        public void deactivateAMessageGroup(String groupName) {
            if(!getElementAttributeValue(LandingPage.ele_LoggedInUser, "title").contains(adminUser + AdoddleCommonStringPool.COMMA_STRING)) {
                logOut();
                login(getUserID(adminUser), resourceUtils.getCommonUserPassword());
            }
            navigateTab(LandingPage.lnk_Discussion);
            AutomationAssert.verifyTrue(verifyAMessageGroupExists("timeStamp"));
            reloadPage();
            waitForCompletePageLoad();
            mouseHover(DiscussionsTab.ele_AMessageTabActiveGroup);
            waitUntilElementIsDisplayed(DiscussionsTab.lnk_AMessageTabGroupOptions);
            mouseHover(DiscussionsTab.lnk_AMessageTabGroupOptions);
            waitUntilElementIsDisplayed(DiscussionsTab.opt_AMessageTabEditGroupOption);
            clickElementAndWaitForElement(DiscussionsTab.opt_AMessageTabEditGroupOption, DiscussionsTab.btn_AMessageCreateGroupStatusActive);
            clickElementAndWaitForElement(DiscussionsTab.btn_AMessageCreateGroupStatusActive, DiscussionsTab.btn_AMessageCreateGroupStatusInActive);
            clickElementAndWaitForInvisibilityOfElement(DiscussionsTab.btn_AMessageTabCreateGroupSave, DiscussionsTab.btn_AMessageTabCreateGroupSave);
        }

        public void setUnreadDiscusionCounts() {
            unreadDiscussionCount = Integer.parseInt(getToolTipText(DashboardTab.lbl_DashboardIncompleteDiscussionsCount).split(":")[1].trim());
            log.info("Unread dicussions before messages sent count :"+unreadDiscussionCount);
        }

        public int getUnreadDiscusionCounts() {
            int unreadMessageReturnCount;
            waitUntilElementIsDisplayed(DashboardTab.lbl_DashboardIncompleteActionsCount);
            unreadMessageReturnCount = Integer.parseInt(getToolTipText(DashboardTab.lbl_DashboardIncompleteDiscussionsCount).split(":")[1].trim());
            log.info("Unread dicussions after messages sent count :"+unreadMessageReturnCount);
            return unreadMessageReturnCount;
        }

        public void sendDirectMessages(String entity, int msgCount, String isAttachment) throws InterruptedException {
            searchUserGroupEntity(entity);
            if(isAttachment.equalsIgnoreCase("with"))
                sendMultipleMessagesToEntity(msgCount,"specialchars", entity, true);
            else
                sendMultipleMessagesToEntity(msgCount,"specialchars", entity, false);
        }

        public void verifySentMessagesOutbox() {
            verifyTextMessageExists(message.replace("\\", ""));
        }

        public void verifyRecievedMessagesInbox(String receiver, String sender) {
            if(!getElementAttributeValue(LandingPage.ele_LoggedInUser, "title").contains(receiver + AdoddleCommonStringPool.COMMA_STRING)) {
                logOut();
                login(getUserID(receiver), resourceUtils.getCommonUserPassword());
            }
            searchUserGroupEntity(sender);
            verifyTextMessageExists(message.replace("\\", ""));
        }

        public void verifyUnreadDiscussionCounts(int count) {
            log.info("Increament count: "+count);
            int currentCount = getUnreadDiscusionCounts();
            AutomationAssert.verifyTrue(currentCount + "!=" + unreadDiscussionCount + count, currentCount == (unreadDiscussionCount + count));
        }

        private String getUserID(String user) {
            if(user.equalsIgnoreCase("Automation UK"))
                return "auto_uk@atest.com";
            else if(user.equalsIgnoreCase("Automation UKP2"))
                return "auto_ukp2@atest.com";
            else if(user.equalsIgnoreCase("Automation US"))
                return "auto_us@atest.com";
            else if(user.equalsIgnoreCase("Automation USP2"))
                return "auto_usp2@atest.com";
            else if(user.equalsIgnoreCase("Automation AUS"))
                return "auto_aus@atest.com";
            else if(user.equalsIgnoreCase("Automation AUSP2"))
                return "auto_ausp2@atest.com";
            else if(user.equalsIgnoreCase("Auto Test"))
                return "auto@atest.com";
            else
                return System.getProperty("primary.username");
        }

}
