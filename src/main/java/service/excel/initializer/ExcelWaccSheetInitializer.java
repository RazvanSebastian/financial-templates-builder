package service.excel.initializer;

import model.dcf.WaccModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import static service.excel.initializer.ExcelWaccSheetInitializer.WaccDataCellLocations.*;

public class ExcelWaccSheetInitializer {

    public static void initialize(XSSFSheet sheet, WaccModel waccModel) {
        initializeWaccDataCell(sheet, MARKET_CAP, waccModel.getMarketCap());
        initializeWaccDataCell(sheet, CURRENT_LIABILITIES, waccModel.getCurrentLiabilities());
        initializeWaccDataCell(sheet, NON_CURRENT_LIABILITIES, waccModel.getNonCurrentLiabilities());
        initializeWaccDataCell(sheet, INTEREST_EXPENSES, waccModel.getInterestExpenses());
        initializeWaccDataCell(sheet, PRETAX_INCOME, waccModel.getPretaxIncome());
        initializeWaccDataCell(sheet, TAX_PROVISION, waccModel.getTaxProvision());
        initializeWaccDataCell(sheet, BETA, waccModel.getBeta());
    }

    private static void initializeWaccDataCell(XSSFSheet sheet, WaccDataCellLocations waccDataCellLocations, String value) {
        XSSFCell cell = sheet.getRow(waccDataCellLocations.getRow()).getCell(waccDataCellLocations.getCol());
        cell.setCellValue(Float.parseFloat(value));
    }

    enum WaccDataCellLocations {
        MARKET_CAP(4, 2),
        CURRENT_LIABILITIES(5, 2),
        NON_CURRENT_LIABILITIES(6, 2),
        INTEREST_EXPENSES(7, 2),
        PRETAX_INCOME(8, 2),
        TAX_PROVISION(9, 2),
        BETA(10, 2);

        private int row;
        private int col;

        WaccDataCellLocations(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }
}
