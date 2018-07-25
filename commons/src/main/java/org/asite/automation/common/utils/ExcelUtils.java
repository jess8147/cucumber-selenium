package org.asite.automation.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.asite.automation.common.config.ResourceHandler;
import org.asite.automation.common.errors.AutomationAssert;
import org.asite.automation.common.lib.AdoddleCommonAppMethods;
import org.asite.automation.common.resources.AdoddleCommonStringPool;

// TODO: Auto-generated Javadoc
/**
 * The Class AdoddleExcelUtils.
 * @author jasminprajapati
 */
public class ExcelUtils {

	/** The my workbook. */
	final private HSSFWorkbook			myWorkbook;

	/** The store dir. */
	final private File					storeDir;

	/** The header rows. */
	private static List<String>		headerRows				= null;

	/** The max cell per row count. */
	private final String	CUCUMBER_FINAL_REPORT	= null;

	/** The max cell per row count. */
	private final String	DEFAULT_REPORT_DIR		= ResourceHandler.getPropertyValue("cucumber.excel.final.report.location");

	/** The java utils. */
	final private JavaUtils				javaUtils;

	/** The sys utils. */
	final private SystemUtils				sysUtils;

	/**
	 * Instantiates a new adoddle excel utils.
	 */
	public ExcelUtils() {
		myWorkbook = new HSSFWorkbook();
		javaUtils = new JavaUtils();
		sysUtils = new SystemUtils();
		headerRows = AdoddleCommonAppMethods.cucumberReportHeaders;
		storeDir = new File(DEFAULT_REPORT_DIR);
		if (!storeDir.exists()) {
			AutomationAssert.verifyTrue(storeDir.mkdirs());
		}

	}

	/**
	 * Gets the sheet at.
	 * 
	 * @param fs
	 *            the fs
	 * @param index
	 *            the index
	 * @return the sheet at
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("resource")
	public XSSFSheet getXSheetAt(FileInputStream fs, int index) throws IOException {
		return new XSSFWorkbook(fs).getSheetAt(index);
	}

	/**
	 * Gets the sheet at.
	 * 
	 * @param fs
	 *            the fs
	 * @param index
	 *            the index
	 * @return the sheet at
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("resource")
	public HSSFSheet getSheetAt(FileInputStream fs, int index) throws IOException {
		return new HSSFWorkbook(fs).getSheetAt(index);
	}
	
	/**
	 * Gets the sheet at.
	 * 
	 * @param fs
	 *            the fs
	 * @return the sheet at
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("resource")
	public HSSFSheet getSheetByName(FileInputStream fs, String name) throws IOException {
		return new HSSFWorkbook(fs).getSheet(name);
	}

	/**
	 * Gets the cell.
	 * 
	 * @param sheet
	 *            the sheet
	 * @param rowIndex
	 *            the row index
	 * @param cellIndex
	 *            the cell index
	 * @return the cell
	 */
	public HSSFCell getCell(HSSFSheet sheet, int rowIndex, int cellIndex) {
		return sheet.getRow(rowIndex).getCell(cellIndex);
	}

	public int getRowCount(HSSFSheet sheet) {
		return sheet.getPhysicalNumberOfRows();
	}
	
	public int getColumnCount(HSSFSheet sheet) {
		return sheet.getRow(0).getLastCellNum();
	}
	
	/**
	 * Gets the cell.
	 * 
	 * @param sheet
	 *            the sheet
	 * @param rowIndex
	 *            the row index
	 * @param cellIndex
	 *            the cell index
	 * @return the cell
	 */
	public XSSFCell getXCell(XSSFSheet sheet, int rowIndex, int cellIndex) {
		return sheet.getRow(rowIndex).getCell(cellIndex);
	}

	/**
	 * Sets the cell value.
	 * 
	 * @param sheet
	 *            the sheet
	 * @param rowIndex
	 *            the row index
	 * @param cellIndex
	 *            the cell index
	 * @param val
	 *            the val
	 */
	public void setCellValue(HSSFSheet sheet, int rowIndex, int cellIndex, String val) {
		sheet.getRow(rowIndex).getCell(cellIndex).setCellValue(val);
	}

	/**
	 * Write workbook.
	 * 
	 * @param wb
	 *            the wb
	 * @param fos
	 *            the fos
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void writeWorkbook(HSSFWorkbook wb, FileOutputStream fos) throws IOException {
		wb.write(fos);
	}

	/**
	 * Generate cucumber report.
	 * 
	 * @param cucumberReportMap
	 *            the cucumber report map
	 * @param reportTitle
	 *            the report title
	 */
	public void generateCucumberReport(HashMap<String, List<String>> cucumberReportMap, String reportTitle) {
		int i = 0, 			cellnum 				= 0;
		final int 			MAX_CELL_PER_ROW_COUNT 	= 5;
		boolean				cucumberReportFlag;
		FileOutputStream	fileout;
		HSSFSheet			mySheet;
		HSSFCell			cell;
		HSSFRow				row;
		File				finalReportFile			= null;

		try {
			fileout = new FileOutputStream(new File(storeDir + "/" + reportTitle + AdoddleCommonStringPool.XLS_EXTENSION));
			cucumberReportFlag = reportTitle.equalsIgnoreCase(CUCUMBER_FINAL_REPORT);
			mySheet = myWorkbook.createSheet("Cucumber-report");
			setCucumberReportWorkSheetColumnWidth(mySheet);
			if (cucumberReportFlag) {
				createHeaderRow(mySheet, myWorkbook);
				i++;
			}

			Set<String> newRowKeys = cucumberReportMap.keySet();

			for (String key : newRowKeys) {

				clearDuplicateFiles(key);
				/*finalReportFile = new File(DEFAULT_REPORT_DIR + "/" + CUCUMBER_FINAL_REPORT + AdoddleCommonStringPool.XLS_EXTENSION);*/
				cellnum = javaUtils.resetIndex(cellnum, 0);
				row = mySheet.createRow(i);

				List<String> strArr = cucumberReportMap.get(key);
				cell = row.createCell(cellnum);
				cell.setCellValue(key);
				for (String str : strArr) {
					cellnum++;
					cell = row.createCell(cellnum);
					cell.setCellValue(str);
					if (str.equalsIgnoreCase(AdoddleCommonStringPool.RESULT_PASSED))
						cell.setCellStyle(getPassedCellStyle(myWorkbook));
					else if (str.equalsIgnoreCase(AdoddleCommonStringPool.RESULT_FAILED))
						cell.setCellStyle(getFailedCellStyle(myWorkbook));
					if ((cellnum == MAX_CELL_PER_ROW_COUNT) && cucumberReportFlag) {
						row = mySheet.createRow(++i);
						cellnum = javaUtils.resetIndex(cellnum, 0);
					}
				}
				i++;
			}
			myWorkbook.write(fileout);
			fileout.flush();
			fileout.close();
		}
		catch (IOException e) {
			System.out.println("ERROR: File I/O Exception occured");
		}
	}

	/**
	 * Clear duplicate files.
	 * 
	 * @param key
	 *            the key
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void clearDuplicateFiles(String key) throws IOException {
		File fileRemovable = new File(DEFAULT_REPORT_DIR + "/" + key.substring(0, key.length() - 1) + AdoddleCommonStringPool.XLS_EXTENSION);
		if (fileRemovable.exists()) {
			sysUtils.deleteFile(fileRemovable.toString());
		}
		/*fileRemovable = new File(DEFAULT_REPORT_DIR + "/" + key + AdoddleCommonStringPool.XLS_EXTENSION);*/
	}

	/**
	 * Verify key exist in report.
	 * 
	 * @param rptFile
	 *            the rpt file
	 * @param key
	 *            the key
	 * @return true, if successful
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public boolean verifyKeyExistInReport(File rptFile, String key) throws IOException {
		HSSFSheet tempSheet = getSheetAt(new FileInputStream(rptFile), 0);
		for (Row r : tempSheet) {
			if (r.getCell(0).getStringCellValue().contains(key.trim())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Sets the cucumber report work sheet column width.
	 * 
	 * @param workSheet
	 *            the new cucumber report work sheet column width
	 */
	private static void setCucumberReportWorkSheetColumnWidth(HSSFSheet workSheet) {
		workSheet.setColumnWidth(0, 9000);
		workSheet.setColumnWidth(1, 9000);
		workSheet.setColumnWidth(2, 3000);
		workSheet.setColumnWidth(3, 4500);
		workSheet.setColumnWidth(4, 4500);
		workSheet.setColumnWidth(5, 3000);
	}

	/**
	 * Creates the header row.
	 * 
	 * @param mySheet
	 *            the my sheet
	 * @param myWorkbook
	 *            the my workbook
	 */
	private static void createHeaderRow(HSSFSheet mySheet, HSSFWorkbook myWorkbook) {
		HSSFCell headerCell;

		try {
			HSSFRow headerRow = mySheet.createRow(0);

			for (int index = 0; index < headerRows.size(); index++) {
				headerCell = headerRow.createCell(index);
				headerCell.setCellValue(headerRows.get(index));
				headerCell.setCellStyle(getHeaderCellStyle(myWorkbook));
			}
		}
		catch (IllegalArgumentException t) {
			System.out.println("");
		}

	}

	/**
	 * Gets the failed cell style.
	 * 
	 * @param workBook
	 *            the work book
	 * @return the failed cell style
	 */
	private static HSSFCellStyle getFailedCellStyle(HSSFWorkbook workBook) {
		HSSFCellStyle failResultCellStyle = workBook.createCellStyle();
		failResultCellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
		failResultCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		failResultCellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		failResultCellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		failResultCellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		failResultCellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		return failResultCellStyle;
	}

	/**
	 * Gets the passed cell style.
	 * 
	 * @param workBook
	 *            the work book
	 * @return the passed cell style
	 */
	private static HSSFCellStyle getPassedCellStyle(HSSFWorkbook workBook) {
		HSSFCellStyle passResultCellStyle = workBook.createCellStyle();
		passResultCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		passResultCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		passResultCellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		passResultCellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		passResultCellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		passResultCellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		return passResultCellStyle;
	}

	/**
	 * Gets the data cell style.
	 * 
	 * @param workBook
	 *            the work book
	 * @return the data cell style
	 */
	protected static HSSFCellStyle getDataCellStyle(HSSFWorkbook workBook) {
		HSSFCellStyle dataCellStyle = workBook.createCellStyle();
		dataCellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
		dataCellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		dataCellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		dataCellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
		dataCellStyle.setWrapText(true);
		return dataCellStyle;

	}

	/**
	 * Gets the header cell style.
	 * 
	 * @param workBook
	 *            the work book
	 * @return the header cell style
	 */
	private static HSSFCellStyle getHeaderCellStyle(HSSFWorkbook workBook) {
		HSSFCellStyle headerCellStyle = workBook.createCellStyle();
		HSSFFont headerFont = workBook.createFont();
		headerFont.setBold(true);
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerCellStyle.setWrapText(true);
		headerCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THICK);
		headerCellStyle.setBorderTop(HSSFCellStyle.BORDER_THICK);
		headerCellStyle.setBorderRight(HSSFCellStyle.BORDER_THICK);
		headerCellStyle.setBorderRight(HSSFCellStyle.BORDER_THICK);
		return headerCellStyle;
	}

	/**
	 * Merge excel files.
	 * 
	 * @param excelFiles
	 *            the excel files
	 */
	public void mergeExcelFiles(List<String> excelFiles) {
		HSSFSheet tempSheet;
		HashMap<String, List<String>> finalReportMap;
		List<String> dataList;
		String finalKey = null;
		finalReportMap = new HashMap<String, List<String>>();
		try {
			for (String excelFile : excelFiles) {
				dataList = new ArrayList<String>();
				tempSheet = getSheetAt(new FileInputStream(excelFile), 0);
				finalKey = getExcelReportUniqueMapKey(excelFile);

				for (Row r : tempSheet) {
					if (r.getCell(0).getStringCellValue().trim().equalsIgnoreCase(finalKey.trim())) {
						for (Cell c : r) {
							if (c.getColumnIndex() != 0)
								dataList.add(c.getStringCellValue());
						}
					}
				}
				if (finalReportMap.containsKey(finalKey)) {
					List<String> keyList = new ArrayList<String>(finalReportMap.keySet());
					Collections.sort(keyList);
					for (String key : keyList) {
						finalKey = key.contains(finalKey) ? finalKey + " " : finalKey;
					}
				}
				finalReportMap.put(finalKey, dataList);
			}
		}
		catch (Throwable t) {
			System.out.println("No Excel Reports for merging");
		}

		if (finalReportMap.get(finalKey) != null)
			generateCucumberReport(finalReportMap, CUCUMBER_FINAL_REPORT);
	}

	/**
	 * Gets the excel report unique map key.
	 * 
	 * @param sheet
	 *            the sheet
	 * @return the excel report unique map key
	 */
	public static String getExcelReportUniqueMapKey(HSSFSheet sheet) {
		return sheet.getRow(0).getCell(0).getStringCellValue();
	}

	private static String getExcelReportUniqueMapKey(String e) {
		return e.replace(ResourceHandler.loadProperty("cucumber.excel.final.report.location"), "").replace(AdoddleCommonStringPool.XLS_EXTENSION, "");
	}

	public static boolean isCellEmpty(final HSSFCell cell) {
		return cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK || cell.getCellType() == Cell.CELL_TYPE_STRING && cell.getStringCellValue().isEmpty();
	}

}
