package service.excel.initializer;

import model.GrahamDataModel;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ExcelGrahamSheetInitializer {

    public static void initialize(XSSFSheet sheet, GrahamDataModel grahamDataModel) {
        sheet.getRow(14).getCell(2).setCellValue(Float.parseFloat(grahamDataModel.getEps()));
        sheet.getRow(16).getCell(2).setCellValue(Float.parseFloat(grahamDataModel.getEpsGrowthRate()));
    }
}
