package org.asite.automation.cukesxls;

import jxl.Sheet;
import jxl.format.CellFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class XlsCellStyle.
 * @author jasminprajapati
 */
public class XlsCellStyle {

    /** The result pass style. */
    public static CellFormat RESULT_PASS_STYLE = null;
    
    /** The result fail style. */
    public static CellFormat RESULT_FAIL_STYLE = null;
    
    /** The feature style. */
    public static CellFormat FEATURE_STYLE     = null;
    
    /** The scenario style. */
    public static CellFormat SCENARIO_STYLE    = null;
    
    /** The step style. */
    public static CellFormat STEP_STYLE        = null;
    
    /** The other style. */
    public static CellFormat OTHER_STYLE       = null;

    /**
     * Inits the cell style.
     *
     * @param sheet the sheet
     */
    public static void initCellStyle(Sheet sheet) {
        RESULT_PASS_STYLE = sheet.getCell(2, 1).getCellFormat();
        RESULT_FAIL_STYLE = sheet.getCell(2, 2).getCellFormat();
        FEATURE_STYLE = sheet.getCell(2, 4).getCellFormat();
        SCENARIO_STYLE = sheet.getCell(2, 5).getCellFormat();
        STEP_STYLE = sheet.getCell(2, 6).getCellFormat();
        OTHER_STYLE = sheet.getCell(2, 7).getCellFormat();
    }
}
