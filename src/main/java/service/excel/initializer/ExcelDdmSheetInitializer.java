package service.excel.initializer;

import model.DdmDataModel;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ExcelDdmSheetInitializer {

    public static void initialize(XSSFSheet sheet, DdmDataModel ddmDataModel) {
        sheet.getRow(1).getCell(2).setCellValue(Float.parseFloat(ddmDataModel.getDividendPerShare()));
        sheet.getRow(6).getCell(15).setCellValue(Float.parseFloat(ddmDataModel.getConservativeGrow()) / 100);
        sheet.getRow(15).getCell(15).setCellValue(Float.parseFloat(ddmDataModel.getNormalGrow()) / 100);
        sheet.getRow(24).getCell(15).setCellValue(Float.parseFloat(ddmDataModel.getHighGrow()) / 100);
    }
}
