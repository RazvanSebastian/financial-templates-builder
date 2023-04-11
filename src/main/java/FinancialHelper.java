import model.Company;
import model.CompanySector;
import repository.*;
import service.excel.ExcelService;
import service.FinancialService;

import java.io.IOException;

public class FinancialHelper {

    public static void main(String... args) throws IOException {
        IncomeStatementFileReader incomeStatementFileReader = new IncomeStatementFileReader();
        BalanceSheetStatementFileReader balanceSheetStatementFileReader = new BalanceSheetStatementFileReader();
        CashFlowStatementFileReader cashFlowStatementFileReader = new CashFlowStatementFileReader();
        MetricsFileReader metricsFileReader = new MetricsFileReader();
        WaccFileReader waccFileReader = new WaccFileReader();
        DcfFileReader dcfFileReader = new DcfFileReader();

        FinancialService financialService = new FinancialService(
                incomeStatementFileReader,
                balanceSheetStatementFileReader,
                cashFlowStatementFileReader,
                metricsFileReader,
                waccFileReader,
                dcfFileReader);

        ExcelService excelService = new ExcelService(financialService);

        excelService.initializeExcel(Company.COMCAST, CompanySector.TELECOMMUNICATION);
        excelService.initializeExcel(Company.UNION_PACIFIC, CompanySector.INDUSTRIAL);
    }
}
