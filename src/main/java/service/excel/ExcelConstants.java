package service.excel;

public class ExcelConstants {

    public static final String VALUATION_METRICS_SHEET_NAME = "Valuation Metrics";
    public static final String FINANCIALS_SHEET_NAME = "Financials";
    public static final String WACC_SHEET_NAME = "WACC";
    public static final String DCF_SHEET_NAME = "DCF";
    public static final String GRAHAM_SHEET_NAME = "Graham";
    public static final String DDM_SHEET_NAME = "DDM";

    /**
     * Income statement row indexes
     */
    public static final Integer REVENUE_ROW_INDEX = 2;
    public static final Integer NET_INCOME_ROW_INDEX = 5;
    public static final Integer EBIDTA_ROW_INDEX = 8;
    public static final Integer SHARES_OUTSTANDING_ROW_INDEX = 11;
    public static final Integer EPS_ROW_INDEX = 14;

    /**
     * Cash Flow statement row indexes
     */
    public static final Integer FREE_CASH_FLOW_ROW_INDEX = 19;
    public static final Integer DIVIDENDS_PAID_ROW_INDEX = 22;

    /**
     * Balance sheet statement row indexes
     */
    public static final Integer CURRENT_ASSETS_ROW_INDEX = 27;
    public static final Integer NON_CURRENT_ASSETS_ROW_INDEX = 30;
    public static final Integer CURRENT_LIABILITIES_ROW_INDEX = 35;
    public static final Integer NON_CURRENT_LIABILITIES_ROW_INDEX = 38;

    /**
     * Metrics row indexes
     */
    public static final Integer ROIC_ROW_INDEX = 76;
    public static final Integer PER_ROW_INDEX = 79;
    public static final Integer PFCF_ASSETS_ROW_INDEX = 80;
}
