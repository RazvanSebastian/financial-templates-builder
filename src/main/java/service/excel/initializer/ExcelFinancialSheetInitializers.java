package service.excel.initializer;

import model.statement.FinancialsModel;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;
import java.util.stream.IntStream;

import static service.excel.ExcelConstants.*;

public class ExcelFinancialSheetInitializers {

    public static void initializeIncomeStatementRows(XSSFSheet sheet, FinancialsModel financialsModel) {
        initializeStatementRow(sheet, financialsModel.getIncomeStatementModel().getRevenues(), REVENUE_ROW_INDEX);
        initializeStatementRow(sheet, financialsModel.getIncomeStatementModel().getNetIncomes(), NET_INCOME_ROW_INDEX);
        initializeStatementRow(sheet, financialsModel.getIncomeStatementModel().getEbidta(), EBIDTA_ROW_INDEX);
        initializeStatementRow(sheet, financialsModel.getIncomeStatementModel().getSharesOutstanding(), SHARES_OUTSTANDING_ROW_INDEX);
        initializeStatementRow(sheet, financialsModel.getIncomeStatementModel().getEarningPerShare(), EPS_ROW_INDEX);
    }

    public static void initializeBalanceSheetRows(XSSFSheet sheet, FinancialsModel financialsModel) {
        initializeStatementRow(sheet, financialsModel.getBalanceSheetStatementModel().getPPE(), PPE_ROW_INDEX);
        initializeStatementRow(sheet, financialsModel.getBalanceSheetStatementModel().getCurrentAssets(), CURRENT_ASSETS_ROW_INDEX);
        initializeStatementRow(sheet, financialsModel.getBalanceSheetStatementModel().getNonCurrentAssets(), NON_CURRENT_ASSETS_ROW_INDEX);
        initializeStatementRow(sheet, financialsModel.getBalanceSheetStatementModel().getCurrentLiabilities(), CURRENT_LIABILITIES_ROW_INDEX);
        initializeStatementRow(sheet, financialsModel.getBalanceSheetStatementModel().getNonCurrentLiabilities(), NON_CURRENT_LIABILITIES_ROW_INDEX);
    }

    public static void initializeCashFlowStatementRows(XSSFSheet sheet, FinancialsModel financialsModel) {
        initializeStatementRow(sheet, financialsModel.getCashFlowStatementModel().getDepreciationAmortization(), DEPRECIATION_AMORTIZATION_ROW_INDEX);
        initializeStatementRow(sheet, financialsModel.getCashFlowStatementModel().getCashFlowFromOperatingActivities(), CASH_FLOW_OPERATING_ACTIVITIES_ROW_INDEX);
        initializeStatementRow(sheet, financialsModel.getCashFlowStatementModel().getDividendsPaid(), DIVIDENDS_PAID_ROW_INDEX);
    }

    public static void initializeMetricsRows(XSSFSheet sheet, FinancialsModel financialsModel) {
        initializeStatementRow(sheet, financialsModel.getMetricsModel().getROIC(), ROIC_ROW_INDEX);
        initializeStatementRow(sheet, financialsModel.getMetricsModel().getPriceToEarnings(), PER_ROW_INDEX);
        initializeStatementRow(sheet, financialsModel.getMetricsModel().getPriceToFCF(), PFCF_ASSETS_ROW_INDEX);
        initializeStatementCell(sheet, StatementCellLocation.PEG, financialsModel.getMetricsModel().getPEG());
    }

    private static void initializeStatementRow(XSSFSheet sheet, List<String> revenue, int rowType) {
        XSSFRow row = sheet.getRow(rowType);
        IntStream.range(0, 11).forEach(lineColIndex -> {
            int cellIndex = lineColIndex + 2;
            XSSFCell cell = row.getCell(cellIndex);

            XSSFCellStyle cellStyle = cell.getCellStyle();
            cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
            cell.setCellStyle(cellStyle);

            cell.setCellValue(Float.parseFloat(revenue.get(lineColIndex)));
        });
    }

    private static void initializeStatementCell(XSSFSheet sheet, StatementCellLocation cellLocation, String value) {
        XSSFCell cell = sheet.getRow(cellLocation.getRow()).getCell(cellLocation.getCol());

        XSSFCellStyle cellStyle = cell.getCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        cell.setCellStyle(cellStyle);

        cell.setCellValue(Float.parseFloat(value));
    }

    enum StatementCellLocation {
        PEG(91, 2);

        private int row;
        private int col;

        StatementCellLocation(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }
    }
}
