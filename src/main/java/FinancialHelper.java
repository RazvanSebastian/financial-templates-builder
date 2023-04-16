import model.Company;
import model.CompanySector;
import repository.*;
import service.FinancialService;
import service.excel.ExcelService;

import java.io.IOException;

public class FinancialHelper {

    public static void main(String... args) throws IOException {
        IncomeStatementFileReader incomeStatementFileReader = new IncomeStatementFileReader();
        BalanceSheetStatementFileReader balanceSheetStatementFileReader = new BalanceSheetStatementFileReader();
        CashFlowStatementFileReader cashFlowStatementFileReader = new CashFlowStatementFileReader();
        MetricsFileReader metricsFileReader = new MetricsFileReader();
        WaccFileReader waccFileReader = new WaccFileReader();
        DcfFileReader dcfFileReader = new DcfFileReader();
        GrahamFileReader grahamFileReader = new GrahamFileReader();
        DdmFileReader ddmFileReader = new DdmFileReader();

        FinancialService financialService = new FinancialService(
                incomeStatementFileReader,
                balanceSheetStatementFileReader,
                cashFlowStatementFileReader,
                metricsFileReader,
                waccFileReader,
                dcfFileReader,
                grahamFileReader,
                ddmFileReader);

        ExcelService excelService = new ExcelService(financialService);

//        excelService.initializeExcel(Company.COMCAST, CompanySector.COMMUNICATIONS);
//        excelService.initializeExcel(Company.UNION_PACIFIC, CompanySector.INDUSTRIAL);
//        excelService.initializeExcel(Company.CELANESE, CompanySector.INDUSTRIAL);
//        excelService.initializeExcel(Company.JOHNSON_AND_JOHNSON, CompanySector.PHARMA);
//        excelService.initializeExcel(Company.MEDTRONIC, CompanySector.PHARMA);
//        excelService.initializeExcel(Company.TYSON_FOODS, CompanySector.CONSUMER_DEFENSE);
//        excelService.initializeExcel(Company.STANLEY_BLACK_DECKER, CompanySector.INDUSTRIAL);
    }
}
