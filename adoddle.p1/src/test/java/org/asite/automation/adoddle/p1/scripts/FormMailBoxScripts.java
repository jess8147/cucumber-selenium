package org.asite.automation.adoddle.p1.scripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.asite.automation.CommonLocators.AdoddleHomeLocators.LandingPage;
import org.asite.automation.CommonLocators.AdoddleProjectFormsLocators.ProjectFormsTab;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.PropertyUtils;

public class FormMailBoxScripts extends AdoddleCommonAppMethods {

	private String					    mailboxFolder;
	final private String				epoch		= dateUtils.getEpoch();
	final private String				formName	= "Automation-FormMailbox-Title" + epoch;
	private int						    beforeCount;
	final private static Logger	log		            = AutomationLogger.getInstance().getLogger(FormMailBoxScripts.class);

	public void sendMailToMailBoxUser(String recipient, String mailSubject) throws IOException, InterruptedException {
		List<String> receiver = new ArrayList<String>();
		setUp(browser);
		verifyExistingFormCounts();
		updateExistingExcel();

		if (ResourceHandler.getPropertyValue("application.url").contains(AdoddleCommonStringPool.ENV_QA1_HOST))
//			receiver.add(recipient.replace("@mail", "@mail1.qa"));
			receiver.add(dataCenter.equals(AdoddleCommonStringPool.UK_DC) ? recipient.replace(".asite.com", "1.qa.asite.com") : recipient.replace("b.asite.com", "1b.qa.asite.com"));
		else if (ResourceHandler.getPropertyValue("application.url").contains(AdoddleCommonStringPool.ENV_QA2_HOST))
			receiver.add(dataCenter.equals(AdoddleCommonStringPool.UK_DC) ? recipient.replace(".asite.com", "qa2.asite.com") : recipient.replace("b.asite.com", "qa2b.asite.com"));
		else
			receiver.add(recipient);

		if (PropertyUtils.trainingFlag)
			receiver.add(recipient.replace("@mail", "@mailtraining"));

		emailUtils.sendEmail(ResourceHandler.getPropertyValue("asite.web.mail.user"), receiver, mailSubject + epoch, javaUtils.getRandomString(20),nodeIP + ResourceHandler.loadProperty("form.mailbox.test.excel.filepath"));

	}

	public void attachFormExcelToEmail() {
		log.info("This test is covered in previous definition");
	}

	public void clickOnMailBoxFolder(String folderName) {
		mailboxFolder = folderName;
	}

	public void verifyFormIsCreated() throws InterruptedException {
		int afterCount;
		for (int index = 0; index < Integer.parseInt(ResourceHandler.getPropertyValue("mailbox.wait.time.out")); index++) {
			navigateTab(LandingPage.lnk_ProjectForms);
			clickElementWithText(mailboxFolder);
			searchForms(formName);
			if (getCount(ProjectFormsTab.css_ProjectFormListingCount) > 0 && getElementText(ProjectFormsTab.lnk_FormListingFirstFormTitle).equalsIgnoreCase(formName))
				break;
			else
				waitUtils.waitInterval(60);
		}

		AutomationAssert.verifyTrue(getCount(ProjectFormsTab.css_ProjectFormListingCount) > 0);
		navigateTab(LandingPage.lnk_ProjectForms);
		String labelProjectFormCount = getElementText(ProjectFormsTab.lbl_ProjectFormListingCount);
		afterCount = Integer.parseInt(labelProjectFormCount.substring(labelProjectFormCount.lastIndexOf(" ") + 1).replace(")", ""));
		log.info("AfterCount:::" + afterCount);
		try {
			AutomationAssert.verifyTrue(afterCount == (beforeCount + 1));
		}
		catch (Throwable t) {
			log.error("Form count mismatch after form mailbox");
		}

	}

	private void verifyExistingFormCounts() throws InterruptedException {
		propertyUtils.setConfigSytemProperties(dataCenter);
		login(System.getProperty("primary.username"), System.getProperty("primary.password"));
		navigateTab(LandingPage.lnk_ProjectForms);
		String labelProjectFormCount = getElementText(ProjectFormsTab.lbl_ProjectFormListingCount);
		log.info("Project Form Count:::" + labelProjectFormCount);
		beforeCount = Integer.parseInt(labelProjectFormCount.substring(labelProjectFormCount.lastIndexOf(" ") + 1).replace(")", ""));
		log.info("Before Project Form Count:::" + beforeCount);
	}

	public void verifyAssignedActions() {
		log.info("implementation pending");
	}

	private void updateExistingExcel() throws IOException {
		sysUtils.authenticateRemoteMachine(nodeIP);
		FileInputStream file = new FileInputStream(new File(nodeIP + ResourceHandler.loadProperty("form.mailbox.test.excel.filepath")));
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		HSSFSheet sheet = workbook.getSheet("Single Value");
		HSSFCell cell;
		cell = sheet.getRow(1).getCell(2);
		cell.setCellValue(formName);
		file.close();
		FileOutputStream outFile = new FileOutputStream(new File(nodeIP + ResourceHandler.loadProperty("form.mailbox.test.excel.filepath")));
		workbook.write(outFile);
		outFile.close();
		collectionDataMap.put("Mailbox Form Name", formName);
	}
}