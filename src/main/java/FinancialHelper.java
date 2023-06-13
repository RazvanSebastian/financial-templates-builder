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

        singleRun(excelService);
//        runAll(excelService);

    }

    private static void singleRun(ExcelService excelService) throws IOException {
        excelService.initializeExcel(Company.UNITED_PARCEL, CompanySector.INDUSTRIAL);
    }

    private static void runAll(ExcelService excelService) throws IOException {
        // Communication
        excelService.initializeExcel(Company.CISCO, CompanySector.COMMUNICATIONS);
        excelService.initializeExcel(Company.COMCAST, CompanySector.COMMUNICATIONS);

        // Industrial
        excelService.initializeExcel(Company.UNION_PACIFIC, CompanySector.INDUSTRIAL);
        excelService.initializeExcel(Company.CELANESE, CompanySector.INDUSTRIAL);
        excelService.initializeExcel(Company.STANLEY_BLACK_DECKER, CompanySector.INDUSTRIAL);
        excelService.initializeExcel(Company.UNITED_PARCEL, CompanySector.INDUSTRIAL);

        // Consumer defense
        excelService.initializeExcel(Company.TYSON_FOODS, CompanySector.CONSUMER_DEFENSE);
        excelService.initializeExcel(Company.BRITISH_AMERICAN_TABACCO, CompanySector.CONSUMER_DEFENSE);
        excelService.initializeExcel(Company.UNILEVER, CompanySector.CONSUMER_DEFENSE);
        excelService.initializeExcel(Company.PROCTER_GAMBLE, CompanySector.CONSUMER_DEFENSE);

        // TECH
        excelService.initializeExcel(Company.ALPHABET, CompanySector.TECH);
        excelService.initializeExcel(Company.MICROSOFT, CompanySector.TECH);
        excelService.initializeExcel(Company.TEXAS_INSTRUMENTS, CompanySector.TECH);
        excelService.initializeExcel(Company.ADOBE, CompanySector.TECH);

        // PHARMA
        excelService.initializeExcel(Company.CVS, CompanySector.PHARMA);
        excelService.initializeExcel(Company.ABBVIE, CompanySector.PHARMA);
        excelService.initializeExcel(Company.JOHNSON_AND_JOHNSON, CompanySector.PHARMA);
        excelService.initializeExcel(Company.MEDTRONIC, CompanySector.PHARMA);
        excelService.initializeExcel(Company.UnitedHealthCare, CompanySector.PHARMA);

        // Utilities
        excelService.initializeExcel(Company.UGI, CompanySector.UTILITIES);
    }
}
