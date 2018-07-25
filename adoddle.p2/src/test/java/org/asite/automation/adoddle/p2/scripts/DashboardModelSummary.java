package org.asite.automation.adoddle.p2.scripts;

import org.asite.automation.CommonLocators.AdoddleDashboardLocators.DashboardTab;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.openqa.selenium.WebElement;

public class DashboardModelSummary extends AdoddleCommonAppMethods {

	private String beforeArchitectureCount, beforeOtherDisciplinesCount;
	private boolean accessModelFlag = false;
	
	private String getModelCount(String modelType)
	{
		String hoverModelCount = null;

		for (WebElement modelSummary : findElements(DashboardTab.css_ModelSummaryGadgetHighchartsTrackerModelTypeList)) {

			mouseHover(modelSummary);

			String hoverModel = getElementText(DashboardTab.ele_ModelSummaryGadgetOnTooltipModelType);
			
			if(hoverModel.contains(modelType)) {
				hoverModelCount = getElementText(DashboardTab.ele_ModelSummaryGadgetOnTooltipModelTypeCount).replaceAll("\\s+","");
				log.info("hoverModelCount : "+hoverModelCount);
				break;
			}
		}
		return hoverModelCount;
	}

	
	public void getCurrentModelCount()
	{
		beforeArchitectureCount = getModelCount(AdoddleCommonStringPool.Model_ARCHITECTURE);
		log.info("beforeArchitectureCount : "+beforeArchitectureCount);
		beforeOtherDisciplinesCount = getModelCount(AdoddleCommonStringPool.Model_OTHERS);
		log.info("beforeOtherDisciplinesCount : "+beforeOtherDisciplinesCount);
	}

	public void validateUpdatedModelSummaryWidget(String modelType)
	{
		String afterArchitectureCount, afterOtherDisciplinesCount;
		reloadPage();
		waitForCompletePageLoad();
		afterArchitectureCount = getModelCount(AdoddleCommonStringPool.Model_ARCHITECTURE);
		log.info("afterArchitectureCount : "+afterArchitectureCount);
		afterOtherDisciplinesCount = getModelCount(AdoddleCommonStringPool.Model_OTHERS);
		log.info("afterOtherDisciplinesCount : "+afterOtherDisciplinesCount);
		
		if(modelType.contains(AdoddleCommonStringPool.Model_ARCHITECTURE)) {
			
			AutomationAssert.verifyTrue("Model Summary Count Mismatch", Integer.parseInt(afterArchitectureCount) == Integer.parseInt(beforeArchitectureCount) + 1);

			if(!accessModelFlag)
				AutomationAssert.verifyTrue("Model Summary Count Mismatch", Integer.parseInt(afterOtherDisciplinesCount) == Integer.parseInt(beforeOtherDisciplinesCount));
			else
				AutomationAssert.verifyTrue("Model Summary Count Mismatch", Integer.parseInt(afterOtherDisciplinesCount) == Integer.parseInt(beforeOtherDisciplinesCount) + 1);
		}
		else {
			
			AutomationAssert.verifyTrue("Model Summary Count Mismatch", Integer.parseInt(afterOtherDisciplinesCount) == Integer.parseInt(beforeOtherDisciplinesCount) + 1);
			
			if(!accessModelFlag)
				AutomationAssert.verifyTrue("Model Summary Count Mismatch", Integer.parseInt(afterArchitectureCount) == Integer.parseInt(beforeArchitectureCount));
			else
				AutomationAssert.verifyTrue("Model Summary Count Mismatch", Integer.parseInt(afterArchitectureCount) == Integer.parseInt(beforeArchitectureCount) + 1);
		}
		accessModelFlag = true;
	}

}