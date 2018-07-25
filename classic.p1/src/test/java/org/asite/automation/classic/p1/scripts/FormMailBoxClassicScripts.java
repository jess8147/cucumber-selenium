package org.asite.automation.classic.p1.scripts;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.asite.automation.CommonLocators.ClassicFormListingLocators.FormListingPage;
import org.asite.automation.CommonLocators.ClassicLandingLocators.LandingPage;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.ClassicCommonAppMethods;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.ResourceUtils;

public class FormMailBoxClassicScripts extends ClassicCommonAppMethods{
	
	final private String formName = "Automation-FormMailbox-Title"+epoch;

	public void sendMailToMailBoxUser(String recipient, String mailSubject) throws IOException, InterruptedException
	{
		List<String> receiver 		= new ArrayList<String>();
		sysUtils.authenticateRemoteMachine(nodeIP);
		updateExistingExcel();

		if (getApplicationURL().contains(AdoddleCommonStringPool.ENV_QA1_HOST))
			receiver.add(dataCenter.equals(AdoddleCommonStringPool.UK_DC) ? recipient.replace("mail.asite.com", "mail1.qa.asite.com"): recipient.replace("mailb.asite.com", "mail1b.qa.asite.com"));
		else if (getApplicationURL().contains(AdoddleCommonStringPool.ENV_QA2_HOST))
			receiver.add(dataCenter.equals(AdoddleCommonStringPool.UK_DC) ? recipient.replace(".asite.com", "qa2.asite.com") : recipient.replace("b.asite.com", "qa2b.asite.com"));
		else
			receiver.add(recipient);

		if (trainingFlag)
			receiver.add(recipient.replace("@mail", "@mailtraining"));
		
		emailUtils.sendEmail(getWebMailUser(), receiver, mailSubject + epoch, javaUtils.getRandomString(20), getFormMailboxExcelPath());

		login(System.getProperty("primary.username"), System.getProperty("primary.password"));
	}
	
	public void attachFormExcelToEmail()
	{
		log.info("This test is covered in previous definition");
	}
	
	public void clickOnFormLink(String formNameLink)
	{
		switchDefault();
		waitUntilElementIsDisplayed(LandingPage.frm_AsiteWorkingFrame);
		switchIframe(LandingPage.frm_AsiteWorkingFrame);
		waitUntilElementIsDisplayed(LandingPage.frm_AsiteMainFrame);
		switchIframe(LandingPage.frm_AsiteMainFrame);
		clickLinkWithText(formNameLink);
		waitForCompletePageLoad();
	}
	
	public void verifyFormIsCreated() throws InterruptedException
	{
		waitForCompletePageLoad();
		waitUntilListOfElementIsDisplayed(FormListingPage.css_ProjectFormListingCount);
		searchForms(formName);

		for(int index =0; index < 5; index++)
		{
			searchForms(formName);

			try {
				if (getElementText(FormListingPage.lbl_ProjectFormListingFirstFormTitle).equalsIgnoreCase(formName))
					break;
				else
					waitUtils.waitInterval(60);

			}
			catch(Throwable t) {
				log.error("Form mailbox is not published yet");
			}
		}
		
		AutomationAssert.verifyTrue(getCount(FormListingPage.css_ProjectFormListingCount) > 0);
		AutomationAssert.verifyTrue(getElementText(FormListingPage.lbl_ProjectFormListingFirstFormTitle).equalsIgnoreCase(formName));
		
	}
	
	public void verifyActionAssignedToUser()
	{
		log.info("test data not available; hence implementation pending"); 
	}

	@SuppressWarnings("resource")
	private void updateExistingExcel() throws IOException {
		FileInputStream file = new FileInputStream(new File(getFormMailboxExcelPath()));
		HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheet("Single Value");
		HSSFCell cell;
		cell = sheet.getRow(1).getCell(2);
		cell.setCellValue(formName);
		file.close();
		collectionDataMap.put("Form Mail box file name :", formName);
		FileOutputStream outFile =new FileOutputStream(new File(getFormMailboxExcelPath()));
        workbook.write(outFile);
        outFile.close();
	}

	private String getApplicationURL() {
		return  ResourceUtils.getApplicationURL();
	}

	private String getWebMailUser() {
		return resourceUtils.getWebMailUser();
	}

	private String getFormMailboxExcelPath() {
		return nodeIP + ResourceHandler.loadProperty("form.mailbox.test.excel.filepath");
	}
}