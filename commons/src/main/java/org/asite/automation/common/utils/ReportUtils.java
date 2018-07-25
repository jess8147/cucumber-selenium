package org.asite.automation.common.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.resources.AdoddleCommonStringPool;

import net.masterthought.cucumber.ReportBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class AdoddleReportUtils.
 * @author jasminprajapati
 */
public class ReportUtils {

	static SystemUtils	sysUtils	= new SystemUtils();

	/**
	 * Execute report.
	 * 
	 * @throws Throwable
	 *             the throwable
	 */
	public static void executeReport() throws Throwable {
		String buildNumber = null;
		File reportOutputDirectory = new File("target");
		List<String> list = new ArrayList<String>();
		try {
			List<String> reportsList2 = sysUtils.getFileListOfSystemFolder(getCucumberReportLocation());
			for (String report : reportsList2) {
				if (report.contains(AdoddleCommonStringPool.JSON_EXTENSION)) {
					list.add(getCucumberReportLocation() + report);
				}
			}
		}
		catch (Throwable t) {
			System.out.print("");
		}

		try {
			List<String> reportsList1 = sysUtils.getFileListOfSystemFolder(getCucumberParallelReportLocation());
			for (String report : reportsList1) {
				if (report.contains(AdoddleCommonStringPool.JSON_EXTENSION))
					list.add(getCucumberParallelReportLocation() + report);
			}
		}
		catch (Throwable t) {
			System.out.print("");
		}

		String pluginUrlPath = "";

		if (ResourceUtils.getApplicationURL().contains(AdoddleCommonStringPool.ENV_QA1_HOST.toLowerCase()))
			buildNumber = AdoddleCommonStringPool.ENV_QA1 + "(" + ResourceUtils.getApplicationDataCenters() + ")";
		else if (ResourceUtils.getApplicationURL().contains(AdoddleCommonStringPool.ENV_QA2_HOST.toLowerCase()))
			buildNumber = AdoddleCommonStringPool.ENV_QA2 + "(" + ResourceUtils.getApplicationDataCenters() + ")";
		else
			buildNumber = AdoddleCommonStringPool.ENV_LIVE + "(" + ResourceUtils.getApplicationDataCenters() + ")";
			

		String buildProject = "cucumber-jvm";
		boolean skippedFails = false;
		boolean pendingFails = false;
		boolean undefinedFails = false;
		boolean missingFails = true;
		boolean flashCharts = true;
		boolean runWithJenkins = false;
		boolean artifactsEnabled = false;
		String artifactConfig = "";
		boolean highCharts = false;
		boolean parallelTesting = true;

		ReportBuilder reportBuilder = new ReportBuilder(list, reportOutputDirectory, pluginUrlPath, buildNumber, buildProject, skippedFails, pendingFails, undefinedFails, missingFails, flashCharts, runWithJenkins, artifactsEnabled, artifactConfig, highCharts, parallelTesting);
		reportBuilder.generateReports();
	}

	/**
	 * Merge excel reports.
	 * 
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void mergeExcelReports() throws FileNotFoundException, IOException {
		ExcelUtils excelUtils = new ExcelUtils();
		List<String> finalList = new ArrayList<String>();
		String eReportDir = ResourceHandler.getPropertyValue("cucumber.excel.final.report.location");
		try {
			List<String> excelFileList = sysUtils.getFileListOfSystemFolder(eReportDir);

			for (String excel : excelFileList) {
				if (excel.contains(AdoddleCommonStringPool.XLS_EXTENSION) && (!excel.contains(ResourceHandler.getPropertyValue("cucumber.excel.final.report")))) {
					finalList.add(eReportDir + excel);
				}
			}
		}
		catch (Throwable t) {
			System.out.print("missing while merge excel reports again");
		}
		
		try {
			excelUtils.mergeExcelFiles(finalList);
		}
		catch (Throwable t) {
			System.out.print("");
		}
	}

	/**
	 * Gets the cucumber parallel report location.
	 * 
	 * @return the cucumber parallel report location
	 */
	public static String getCucumberParallelReportLocation() {
		return ResourceHandler.getPropertyValue("cucumber.json.paralllel.reports.location");
	}

	/**
	 * Gets the cucumber report location.
	 * 
	 * @return the cucumber report location
	 */
	public static String getCucumberReportLocation() {
		return ResourceHandler.getPropertyValue("cucumber.json.reports.location");
	}
}