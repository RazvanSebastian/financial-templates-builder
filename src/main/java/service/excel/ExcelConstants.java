package service.excel;

public class ExcelConstants {

    public static final String VALUATION_METRICS_SHEET_NAME = "Valuation Metrics";
    public static final String FINANCIALS_SHEET_NAME = "Financials";
    public static final String WACC_SHEET_NAME = "WACC";
    public static final String DCF_SHEET_NAME = "DCF";

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
    public static final Integer DEPRECIATION_AMORTIZATION_ROW_INDEX = 19;
    public static final Integer CASH_FLOW_OPERATING_ACTIVITIES_ROW_INDEX = 21;
    public static final Integer DIVIDENDS_PAID_ROW_INDEX = 30;

    /**
     * Balance sheet statement row indexes
     */
    public static final Integer PPE_ROW_INDEX = 35;
    public static final Integer CURRENT_ASSETS_ROW_INDEX = 37;
    public static final Integer NON_CURRENT_ASSETS_ROW_INDEX = 40;
    public static final Integer CURRENT_LIABILITIES_ROW_INDEX = 45;
    public static final Integer NON_CURRENT_LIABILITIES_ROW_INDEX = 48;

    /**
     * Metrics row indexes
     */
    public static final Integer ROIC_ROW_INDEX = 86;
    public static final Integer PER_ROW_INDEX = 89;
    public static final Integer PFCF_ASSETS_ROW_INDEX = 90;
}
