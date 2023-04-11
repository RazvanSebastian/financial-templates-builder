package service.excel;

import model.Company;
import model.CompanySector;
import model.FinancialsModel;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.FinancialService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static service.excel.ExcelConstants.FINANCIALS_SHEET_NAME;
import static service.excel.ExcelConstants.VALUATION_METRICS_SHEET_NAME;
import static service.excel.ExcelFinancialSheetInitializers.*;
import static service.excel.ExcelValuationMetricsSheetInitializer.*;

public class ExcelService {


    private final FinancialService financialService;

    public ExcelService(FinancialService financialService) {
        this.financialService = financialService;
    }

    public void initializeExcel(Company company, CompanySector companySector) throws IOException {
        final File companyTemplateFile = createOutputCompanyTemplate(company);
        final XSSFWorkbook workbook = getWorkbook(companyTemplateFile);

        initializeFinancialSheet(workbook, company);
        XSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);

        initializeValuationMetricsSheet(workbook, company, companySector);
        XSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);

        workbook.write(new FileOutputStream(companyTemplateFile));
        workbook.close();
    }

    private void initializeValuationMetricsSheet(XSSFWorkbook workbook, Company company, CompanySector companySector) {
        final XSSFSheet sheet = workbook.getSheet(VALUATION_METRICS_SHEET_NAME);

        // Set title
        sheet.getRow(2).getCell(1).setCellValue(company.getTitle());

        // Efficiency
        initializeROA(sheet);
        initializeROE(sheet);
        initializeROIC(sheet);

        // Debt
        initializeCurrentRatio(sheet);
        initializeDebtToEquity(sheet);

        // Shares
        initializeNumberOfSharesDecrease(sheet);

        // Evaluation
        initializePER(sheet, companySector);
        initializePFCF(sheet, companySector);

        // Profitability
        initializeCagrFcf5Years(sheet);
        initializeCagrFcf10Years(sheet);
        initializeCagrEbidta5Years(sheet);
        initializeCagrEbidta10Years(sheet);
        initializeCagrNetIncome5Years(sheet);
        initializeCagrNetIncome10Years(sheet);
        initializeCagrRevenue5Years(sheet);
        initializeCagrRevenue10Years(sheet);

        // Dividends
        initializeNetPayoutRatio(sheet);
        initializeAverageIncrease(sheet);
        initializeCagrDividend5Years(sheet);
        initializeChowder5Years(sheet);
    }

    private void initializeFinancialSheet(XSSFWorkbook workbook, Company company) throws IOException {
        final XSSFSheet sheet = workbook.getSheet(FINANCIALS_SHEET_NAME);

        final FinancialsModel financialsModel = financialService.readFinancials(company);

        initializeIncomeStatementRows(sheet, financialsModel);
        initializeBalanceSheetRows(sheet, financialsModel);
        initializeCashFlowStatementRows(sheet, financialsModel);
        initializeMetricsRows(sheet, financialsModel);
    }

    private File createOutputCompanyTemplate(Company company) throws IOException {
        File template = new File(getClass().getClassLoader().getResource("companies/template.xlsx").getFile());
        File destination = new File(getClass().getClassLoader().getResource("companies/" + company.getDirectoryName()).getFile() + "/" + company.getTitle() + ".xlsx");
        FileUtils.copyFile(template, destination);
        return destination;
    }

    private XSSFWorkbook getWorkbook(File companyTemplateFile) throws IOException {
        FileInputStream file = new FileInputStream(companyTemplateFile);
        return new XSSFWorkbook(file);
    }
}
