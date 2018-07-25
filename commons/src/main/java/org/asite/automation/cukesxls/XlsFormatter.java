package org.asite.automation.cukesxls;

import gherkin.formatter.Formatter;
import gherkin.formatter.Reporter;
import gherkin.formatter.model.Background;
import gherkin.formatter.model.Examples;
import gherkin.formatter.model.Feature;
import gherkin.formatter.model.Match;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Scenario;
import gherkin.formatter.model.ScenarioOutline;
import gherkin.formatter.model.Step;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class XlsFormatter.
 * @author jasminprajapati
 */
public class XlsFormatter implements Formatter, Reporter {

	/** The Constant DEFAULT_REPORT_DIR. */
	// default report store dir
	private static final String DEFAULT_REPORT_DIR = "report";
	
	/** The Constant PARAM_PATTERN. */
	public static final Pattern PARAM_PATTERN = Pattern
			.compile("\\$\\{(.+)\\}");
	
	/** The logger. */
	private Logger logger = Logger.getLogger(XlsFormatter.class);
	
	/** The Constant FILENAME_FORMAT. */
	private static final SimpleDateFormat FILENAME_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd-HH-mm-ss");
	
	/** The work book. */
	private WritableWorkbook workBook;
	
	/** The start row. */
	private int startRow = 7;

	/**
	 * Instantiates a new xls formatter.
	 */
	public XlsFormatter() {
		this(DEFAULT_REPORT_DIR);
	}

	/**
	 * Instantiates a new xls formatter.
	 *
	 * @param reportStoreDir the report store dir
	 */
	public XlsFormatter(String reportStoreDir) {
		try {
			File templateFile = new File(
					"./src/test/resources/report_template.xls");
			Workbook template = Workbook.getWorkbook(templateFile);
			File storeDir = new File(reportStoreDir);
			if (!storeDir.exists()) {
				storeDir.mkdirs();
			}
			workBook = Workbook.createWorkbook(
					new File(storeDir, getFileName()), template);

			XlsCellStyle.initCellStyle(workBook.getSheet(2));
		} catch (Exception e) {
			logger.error("init ExcelReporter error{} ", e);
		}
	}

	/**
	 * get filename format, like：report_2016-06-06-11-11-11.xls
	 *
	 * @return the file name
	 */
	private String getFileName() {
		String fileName = "report_" + FILENAME_FORMAT.format(new Date())
				+ ".xls";

		return fileName;
	}

	/* (non-Javadoc)
	 * @see gherkin.formatter.Reporter#before(gherkin.formatter.model.Match, gherkin.formatter.model.Result)
	 */
	public void before(Match match, Result result) {
		// NoOp
	}

	/* (non-Javadoc)
	 * @see gherkin.formatter.Reporter#result(gherkin.formatter.model.Result)
	 */
	public void result(Result result) {
		ReporterContext.callbackResult(result, CucumberElement.Type.STEP);
	}

	/* (non-Javadoc)
	 * @see gherkin.formatter.Reporter#after(gherkin.formatter.model.Match, gherkin.formatter.model.Result)
	 */
	public void after(Match match, Result result) {

	}

	/* (non-Javadoc)
	 * @see gherkin.formatter.Reporter#match(gherkin.formatter.model.Match)
	 */
	public void match(Match match) {
		// NoOp
	}

	/* (non-Javadoc)
	 * @see gherkin.formatter.Reporter#embedding(java.lang.String, byte[])
	 */
	public void embedding(String mimeType, byte[] data) {
		// NoOp
	}

	/* (non-Javadoc)
	 * @see gherkin.formatter.Reporter#write(java.lang.String)
	 */
	public void write(String text) {
		// NoOp
	}

	/* (non-Javadoc)
	 * @see gherkin.formatter.Formatter#syntaxError(java.lang.String, java.lang.String, java.util.List, java.lang.String, java.lang.Integer)
	 */
	public void syntaxError(String state, String event,
			List<String> legalEvents, String uri, Integer line) {
		logger.error("feature: " + uri + " ,line: " + line
				+ " syntaxError, please check it!");
	}

	/* (non-Javadoc)
	 * @see gherkin.formatter.Formatter#uri(java.lang.String)
	 */
	public void uri(String uri) {
		logger.info("start execute feature: " + uri);
	}

	/* (non-Javadoc)
	 * @see gherkin.formatter.Formatter#feature(gherkin.formatter.model.Feature)
	 */
	public void feature(Feature feature) {
		ReporterContext.addEle(feature, CucumberElement.Type.FEATURE);
	}

	/* (non-Javadoc)
	 * @see gherkin.formatter.Formatter#scenarioOutline(gherkin.formatter.model.ScenarioOutline)
	 */
	public void scenarioOutline(ScenarioOutline scenarioOutline) {
		// NoOp
	}

	/* (non-Javadoc)
	 * @see gherkin.formatter.Formatter#examples(gherkin.formatter.model.Examples)
	 */
	public void examples(Examples examples) {
		// NoOp
	}

	/* (non-Javadoc)
	 * @see gherkin.formatter.Formatter#startOfScenarioLifeCycle(gherkin.formatter.model.Scenario)
	 */
	public void startOfScenarioLifeCycle(Scenario scenario) {
		// NoOp
	}

	/* (non-Javadoc)
	 * @see gherkin.formatter.Formatter#background(gherkin.formatter.model.Background)
	 */
	public void background(Background background) {
		// NoOp
	}

	/* (non-Javadoc)
	 * @see gherkin.formatter.Formatter#scenario(gherkin.formatter.model.Scenario)
	 */
	public void scenario(Scenario scenario) {
		ReporterContext.addEle(scenario, CucumberElement.Type.SCENARIO);
	}

	/* (non-Javadoc)
	 * @see gherkin.formatter.Formatter#step(gherkin.formatter.model.Step)
	 */
	public void step(Step step) {
		ReporterContext.addEle(step, CucumberElement.Type.STEP);
	}

	/* (non-Javadoc)
	 * @see gherkin.formatter.Formatter#endOfScenarioLifeCycle(gherkin.formatter.model.Scenario)
	 */
	public void endOfScenarioLifeCycle(Scenario scenario) {
		// NoOp
	}

	/* (non-Javadoc)
	 * @see gherkin.formatter.Formatter#done()
	 */
	public void done() {
		try {
			// generate summary sheet
			generateSummary();

			workBook.write();
			logger.info("finish execute all features and generate report success !");
		} catch (Exception e) {
			logger.error("generate report error{} ", e);
		}
	}

	/* (non-Javadoc)
	 * @see gherkin.formatter.Formatter#close()
	 */
	public void close() {
		if (workBook != null) {
			try {
				workBook.close();
			} catch (Exception e) {
				logger.error("close workBook resource error{} ", e);
			}
		}
	}

	/* (non-Javadoc)
	 * @see gherkin.formatter.Formatter#eof()
	 */
	public void eof() {
		// write feature execute info to xls
		try {
			genFeatureRunInfo();
		} catch (Exception e) {
			logger.error("generate feature run info error{} ", e);
		}
	}

	/**
	 * Gen feature run info.
	 *
	 * @throws Exception the exception
	 * @throws WriteException the write exception
	 */
	private void genFeatureRunInfo() throws Exception, WriteException {
		WritableSheet detailSheet = workBook.getSheet(1);
		Map<String, CucumberElement> featureEles = ReporterContext
				.getFeatureEles();
		if (!featureEles.isEmpty()) {
			Iterator<String> idIterator = featureEles.keySet().iterator();
			while (idIterator.hasNext()) {
				String id = idIterator.next();
				CucumberElement ele = featureEles.get(id);
				detailSheet.insertRow(startRow);
				boolean isFailed = Result.FAILED.equals(ele.getResult()
						.getStatus());

				Label nameLabel = null;
				switch (ele.getType()) {
				case FEATURE:
					nameLabel = new Label(1, startRow, ele.getName(),
							XlsCellStyle.FEATURE_STYLE);
					detailSheet.addCell(new Label(2, startRow, "",
							XlsCellStyle.OTHER_STYLE));
					detailSheet.addCell(new Label(3, startRow, "",
							XlsCellStyle.OTHER_STYLE));
					// feature need calculate costtime at this time
					ele.setCosttime(System.currentTimeMillis()
							- ele.getStartTime());
					detailSheet.addCell(new Label(4, startRow, "",
							XlsCellStyle.OTHER_STYLE));
					break;
				case SCENARIO:
					detailSheet.addCell(new Label(1, startRow, "",
							XlsCellStyle.OTHER_STYLE));
					nameLabel = new Label(2, startRow, ele.getName(),
							XlsCellStyle.SCENARIO_STYLE);
					detailSheet.addCell(new Label(3, startRow, "",
							XlsCellStyle.OTHER_STYLE));
					detailSheet.addCell(new Label(4, startRow, "",
							XlsCellStyle.OTHER_STYLE));
					break;
				case STEP:
					detailSheet.addCell(new Label(1, startRow, "",
							XlsCellStyle.OTHER_STYLE));
					detailSheet.addCell(new Label(2, startRow, "",
							XlsCellStyle.OTHER_STYLE));
					nameLabel = new Label(3, startRow, ele.getName(),
							XlsCellStyle.STEP_STYLE);

					detailSheet.addCell(new Label(4, startRow, ele.getResult()
							.getStatus(),
							isFailed ? XlsCellStyle.RESULT_FAIL_STYLE
									: XlsCellStyle.RESULT_PASS_STYLE));
					break;
				}
				detailSheet.addCell(nameLabel);
				String remark = isFailed ? ele.getResult().getErrorMessage()
						: "";
				detailSheet.addCell(new Label(5, startRow, remark,
						XlsCellStyle.OTHER_STYLE));
				detailSheet.addCell(new Label(6, startRow, String.valueOf(ele
						.getCosttime()), XlsCellStyle.OTHER_STYLE));

				startRow++;
			}
		}

		// clear last feature execute info
		ReporterContext.clear();
	}

	/**
	 * Generate summary.
	 *
	 * @throws Exception the exception
	 */
	private void generateSummary() throws Exception {
		WritableSheet summarySheet = workBook.getSheet(0);
		Map<String, String> summaryInfoMap = new HashMap<String, String>();
		int successFeatures = ReporterContext.getTotalFeatures()
				- ReporterContext.getFailFeatures();

		int successScenarios = ReporterContext.getTotalScenarios()
				- ReporterContext.getFailScenarios();

		int successSteps = ReporterContext.getTotalSteps()
				- ReporterContext.getFailSteps();

		summaryInfoMap.put("successFeatures", String.valueOf(successFeatures));
		summaryInfoMap.put("failFeatures",
				String.valueOf(ReporterContext.getFailFeatures()));
		summaryInfoMap.put("totalFeatures",
				String.valueOf(ReporterContext.getTotalFeatures()));
		summaryInfoMap.put(
				"featureRate",
				Util.formatNum(1.0 * successFeatures
						/ ReporterContext.getTotalFeatures()));

		summaryInfoMap
				.put("successScenarios", String.valueOf(successScenarios));
		summaryInfoMap.put("failScenarios",
				String.valueOf(ReporterContext.getFailScenarios()));
		summaryInfoMap.put("totalScenarios",
				String.valueOf(ReporterContext.getTotalScenarios()));
		summaryInfoMap.put(
				"scenarioRate",
				Util.formatNum(1.0 * successScenarios
						/ ReporterContext.getTotalScenarios()));

		summaryInfoMap.put("successSteps", String.valueOf(successSteps));
		summaryInfoMap.put("failSteps",
				String.valueOf(ReporterContext.getFailSteps()));
		summaryInfoMap.put("totalSteps",
				String.valueOf(ReporterContext.getTotalSteps()));
		summaryInfoMap.put(
				"stepRate",
				Util.formatNum(1.0 * successSteps
						/ ReporterContext.getTotalSteps()));

		int rows = summarySheet.getRows();
		int columns = summarySheet.getColumns();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				WritableCell cell = summarySheet.getWritableCell(j, i);
				String contents = cell.getContents();
				String param = extractParam(contents);

				if (param != null) {
					String value = summaryInfoMap.get(param);
					if (value != null) {
						Label label = new Label(j, i, value,
								cell.getCellFormat());
						summarySheet.addCell(label);
					}
				}
			}
		}
	}

	/**
	 * Extract param.
	 *
	 * @param content the content
	 * @return the string
	 */
	private String extractParam(String content) {
		if (content == null || "".equals(content)) {
			return null;
		}
		Matcher matcher = PARAM_PATTERN.matcher(content);
		if (matcher.find()) {
			return matcher.group(1);
		}

		return null;
	}
}
