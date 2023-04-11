package service.excel.initializer;

import model.dcf.DcfModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import static service.excel.initializer.DcfSheetInitializer.DcfDataCellLocation.*;

public class DcfSheetInitializer {

    public static void initialize(XSSFSheet sheet, DcfModel dcfModel) {
        initializePercentageCellData(sheet, REVENUE_GROWTH, dcfModel.getRevenueGrowth());
        initializeNumberCellData(sheet, EBIDTA_MULTIPLE, dcfModel.getEbidtaMultiple());
        initializePercentageCellData(sheet, EBIDTA_GROWTH_RATE, dcfModel.getEbidtaGrowthRate());
        initializeNumberCellData(sheet, NUMBER_OF_SHARES, dcfModel.getNumberOfShares());
        initializePercentageCellData(sheet, REVENUE_ESTIMATION_1, dcfModel.getRevenueEstimations().get(0));
        initializePercentageCellData(sheet, REVENUE_ESTIMATION_2, dcfModel.getRevenueEstimations().get(1));
        initializePercentageCellData(sheet, REVENUE_ESTIMATION_3, dcfModel.getRevenueEstimations().get(2));
    }

    private static void initializeNumberCellData(XSSFSheet xssfSheet, DcfDataCellLocation cellLocation, String value) {
        XSSFCell cell = xssfSheet.getRow(cellLocation.getRow()).getCell(cellLocation.getCol());
        cell.setCellValue(Float.parseFloat(value));
    }

    private static void initializePercentageCellData(XSSFSheet xssfSheet, DcfDataCellLocation cellLocation, String value) {
        XSSFCell cell = xssfSheet.getRow(cellLocation.getRow()).getCell(cellLocation.getCol());
        cell.setCellValue(Float.parseFloat(value)/100);
    }

    enum DcfDataCellLocation {
        REVENUE_GROWTH(5, 2),
        EBIDTA_MULTIPLE(8, 2),
        EBIDTA_GROWTH_RATE(9, 2),
        NUMBER_OF_SHARES(10, 2),
        REVENUE_ESTIMATION_1(42, 2),
        REVENUE_ESTIMATION_2(42, 3),
        REVENUE_ESTIMATION_3(42, 4);

        private int row;
        private int col;

        DcfDataCellLocation(int row, int col) {
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
