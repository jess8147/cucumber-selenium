package org.asite.automation.CommonLocators;

import org.openqa.selenium.By;

public class AdoddleSuppliersLocators {
	
	public static class SuppliersTab {
		/* View Project */
		public static By	txt_OwnerOrgInput	= By.xpath(".//div[@id='divSearchPanel']//input[@id='keyword']");
		public static By	ele_OwnerOrgName	= By.xpath(".//div[@id='companies-blocks']//span[@title][text()][1]");
	}
	
}
