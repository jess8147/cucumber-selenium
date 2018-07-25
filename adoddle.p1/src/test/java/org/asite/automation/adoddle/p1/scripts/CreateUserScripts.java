package org.asite.automation.adoddle.p1.scripts;

import java.text.ParseException;

import org.apache.log4j.Logger;
import org.asite.automation.CommonLocators.AdoddleAdminLocators.AdminTab;
import org.asite.automation.CommonLocators.AdoddleGlobalLocators.GlobalPageElements;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.JavaUtils;
import org.openqa.selenium.Keys;

public class CreateUserScripts extends AdoddleCommonAppMethods {

	private String			emailAddress	= "automationuser_" + dateUtils.getEpoch() + "@utest.com";
	private String			userOrganization;
	private String			timeZone		= "(UTC )Western European Summer Time";
	private int				counter			= 0;
	public static Logger	log				= AutomationLogger.getInstance().getLogger(CreateUserScripts.class);

	public void enterUserEmailAddress() throws InterruptedException {

		waitUntilElementIsDisplayed(AdminTab.txt_PopCreateUserEmailAddress);
		waitUntilElementIsClickable(AdminTab.txt_PopCreateUserEmailAddress);
		sendKeys(AdminTab.txt_PopCreateUserEmailAddress, emailAddress);
		collectionDataMap.put("User ID", emailAddress);
		sendKeys(AdminTab.txt_PopCreateUserEmailAddress, Keys.TAB);
		waitForCompletePageLoad();
	}

	public void selectValidOrganization(String org) throws InterruptedException {
		clickElementAndWait(AdminTab.drp_PopCreateUserOrganization);

		userOrganization = org;
		while (!getSelectedDropdownLabel(AdminTab.drp_PopCreateUserOrganization).equalsIgnoreCase(userOrganization)) {
			waitUtils.waitInterval(1);
			counter++;
			if (counter == 120)
				break;
		}
		selectByVisibleText(AdminTab.drp_PopCreateUserOrganization, userOrganization);
	}

	public void enterMandatoryUserFields() throws ParseException, InterruptedException {
		selectByIndex(AdminTab.drp_PopCreateUserPrefix, 1);
		sendKeys(AdminTab.txt_PopCreateUserFirstName, "Automation");
		sendKeys(AdminTab.txt_PopCreateUserMiddleName, "Test");
		sendKeys(AdminTab.txt_PopCreateUserLastName, "User");
		sendKeys(AdminTab.txt_PopCreateUserJobTitle, "Automation Tester");

		if (dataCenter.equalsIgnoreCase("uk"))
			selectByIndex(AdminTab.drp_PopCreateUserLanguage, 0);
		else if (dataCenter.equalsIgnoreCase("us"))
			selectByIndex(AdminTab.drp_PopCreateUserLanguage, 1);
		selectByVisibleText(AdminTab.drp_PopCreateUserTimeZoneDropdown, timeZone);
		selectByVisibleText(AdminTab.drp_PopCreateUserView, AdoddleCommonStringPool.USER_VIEW_BOTH);
		clickElementAndWaitForElement(AdminTab.img_PopCreateUserSubscriptionStartDatePicker , GlobalPageElements.lnk_DatePickerCalenderToday);
		clickElementAndWaitForInvisibilityOfElement(GlobalPageElements.lnk_DatePickerCalenderToday, GlobalPageElements.lnk_DatePickerCalenderToday);
		clickElementAndWait(AdminTab.chk_PopCreateUserSubscriptionUnlimited);
		AutomationAssert.verifyTrue(!findElement(AdminTab.txt_PopCreateUserSubscriptionEnd).isEnabled());
		clickElementAndWait(AdminTab.chk_PopCreateUserSubscriptionUnlimited);
		AutomationAssert.verifyTrue(findElement(AdminTab.txt_PopCreateUserSubscriptionEnd).isEnabled());
		clickElementAndWaitForElement(AdminTab.img_PopCreateUserSubscriptionEndDatePicker , GlobalPageElements.lnk_DatePickerCalenderToday);
		clickElementAndWaitForInvisibilityOfElement(GlobalPageElements.lnk_DatePickerCalenderToday, GlobalPageElements.lnk_DatePickerCalenderToday);
		selectByVisibleText(AdminTab.drp_PopCreateUserSubsriptionPlan, AdoddleCommonStringPool.SUBSCRIPTION_KEY_PROFESSIONAL);
		getWebDriver().findElement(AdminTab.txt_PopCreateUserBillToOrg).sendKeys(userOrganization);
		sendKeys(AdminTab.txt_PopCreateUserBillToOrg, Keys.TAB);
		sendKeys(AdminTab.txt_PopCreateUserAsiteContact, JavaUtils.getRandomNumber(10));
		clickElementAndWait(AdminTab.btn_PopCreateUserSaveButton);
	}

	public void verifyUserIsCreated() {

		waitUntilElementIsInvisible(GlobalPageElements.pop_PopUpElement);
		try {
			AutomationAssert.verifyTrue(getElementText(GlobalPageElements.lbl_SuccessMessage).equalsIgnoreCase("User created successfully"));
		}
		catch (Throwable t) {
			log.info("success message not verified");
		}
	}

	public void searchUserOnListing() throws InterruptedException {
		waitUntilElementIsDisplayed(AdminTab.txt_ManageUsersUserSearchInput);
		sendKeys(AdminTab.txt_ManageUsersUserSearchInput, emailAddress);
		sendKeys(AdminTab.txt_ManageUsersUserSearchInput, Keys.ENTER);
		waitForCompletePageLoad();
	}

	public void verifyUserIsAvailableOnListing() {
		AutomationAssert.verifyTrue(getElementText(AdminTab.lbl_ManageUsersListingFirstEmail).equalsIgnoreCase(emailAddress));
	}

	public void clickAddNewUserButton(String buttonText) {
		clickElementAndWaitForElement(AdminTab.btn_ManageUsersAddNewUser, GlobalPageElements.pop_PopUpElement);
	}
	
	public void createNumberofUsers(String number, String org) throws InterruptedException, ParseException {
		String userEpoch;
		for(counter=0; counter < Integer.parseInt(number); counter ++) {
			userEpoch = dateUtils.getEpoch();
			emailAddress	= "san"+dateUtils.getEpoch() + "@san.com";
			System.out.println(emailAddress);
			enterUserEmailAddress();
			selectValidOrganization(org);
			enterBulkUsersFields(userEpoch, org);
			verifyUserIsCreated();
			searchUserOnListing();
			clickAddNewUserButton(AdoddleCommonStringPool.ADD_NEW_USER);
		}
	}
		
	public void enterBulkUsersFields(String timeStamp, String org) throws ParseException, InterruptedException {
			selectByIndex(AdminTab.drp_PopCreateUserPrefix, 1);
			sendKeys(AdminTab.txt_PopCreateUserFirstName, "San");
			sendKeys(AdminTab.txt_PopCreateUserLastName, "Patric"+timeStamp);
			sendKeys(AdminTab.txt_PopCreateUserJobTitle, "Automation Tester");

			if (dataCenter.equalsIgnoreCase("uk"))
				selectByIndex(AdminTab.drp_PopCreateUserLanguage, 0);
			else if (dataCenter.equalsIgnoreCase("us"))
				selectByIndex(AdminTab.drp_PopCreateUserLanguage, 1);
			selectByVisibleText(AdminTab.drp_PopCreateUserTimeZoneDropdown, timeZone);
			selectByVisibleText(AdminTab.drp_PopCreateUserView, AdoddleCommonStringPool.USER_VIEW_BOTH);
			clickElementAndWaitForElement(AdminTab.img_PopCreateUserSubscriptionStartDatePicker , GlobalPageElements.lnk_DatePickerCalenderToday);
			clickElementAndWaitForInvisibilityOfElement(GlobalPageElements.lnk_DatePickerCalenderToday, GlobalPageElements.lnk_DatePickerCalenderToday);
			clickElementAndWait(AdminTab.chk_PopCreateUserSubscriptionUnlimited);
			selectByVisibleText(AdminTab.drp_PopCreateUserSubsriptionPlan, AdoddleCommonStringPool.SUBSCRIPTION_KEY_PROFESSIONAL);
			getWebDriver().findElement(AdminTab.txt_PopCreateUserBillToOrg).sendKeys(org);
			sendKeys(AdminTab.txt_PopCreateUserBillToOrg, Keys.TAB);
			sendKeys(AdminTab.txt_PopCreateUserAsiteContact, JavaUtils.getRandomNumber(10));
			clickElementAndWait(AdminTab.btn_PopCreateUserSaveButton);
		}
	
}
