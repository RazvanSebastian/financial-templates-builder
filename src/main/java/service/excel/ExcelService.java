package service.excel;

import model.Company;
import model.CompanySector;
import model.DdmDataModel;
import model.GrahamDataModel;
import model.dcf.DcfModel;
import model.dcf.WaccModel;
import model.statement.FinancialsModel;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.FinancialService;
import service.excel.initializer.ExcelDcfSheetInitializer;
import service.excel.initializer.ExcelDdmSheetInitializer;
import service.excel.initializer.ExcelGrahamSheetInitializer;
import service.excel.initializer.ExcelWaccSheetInitializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static service.excel.ExcelConstants.*;
import static service.excel.initializer.ExcelFinancialSheetInitializers.*;
import static service.excel.initializer.ExcelValuationMetricsSheetInitializer.*;

public class ExcelService {


    private final FinancialService financialService;

    public ExcelService(FinancialService financialService) {
        this.financialService = financialService;
    }

    public void initializeExcel(Company company, CompanySector companySector) throws IOException {
        final File companyTemplateFile = createOutputCompanyTemplate(company, companySector);
        final XSSFWorkbook workbook = getWorkbook(companyTemplateFile);

        initializeFinancialSheet(workbook, company, companySector);
        XSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);

        initializeValuationMetricsSheet(workbook, company, companySector);
        XSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);

        initializeWaccSheet(workbook, company, companySector);
        XSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);

        initializeDcfSheet(workbook, company, companySector);
        initializeGrahamSheet(workbook, company, companySector);
        initializeDdmModel(workbook, company, companySector);

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
        initializeLiabilitiesToEquity(sheet);
        initializeLongDebtToEbidta(sheet);

        // Shares
        initializeNumberOfSharesDecrease(sheet);

        // Evaluation
        initializePER(sheet, companySector);
        initializePFCF(sheet, companySector);
        initializePEG(sheet);

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
        initializeDividendAverageIncrease(sheet);
        initializeCagrDividend5Years(sheet);
        initializeChowder5Years(sheet);
    }

    private void initializeDcfSheet(XSSFWorkbook workbook, Company company, CompanySector companySector) throws IOException {
        final DcfModel dcfModel = financialService.readDcf(company, companySector);
        final XSSFSheet sheet = workbook.getSheet(DCF_SHEET_NAME);

        ExcelDcfSheetInitializer.initialize(sheet, dcfModel);
    }

    private void initializeWaccSheet(XSSFWorkbook workbook, Company company, CompanySector companySector) throws IOException {
        final WaccModel waccModel = financialService.readWacc(company, companySector);
        final XSSFSheet sheet = workbook.getSheet(WACC_SHEET_NAME);

        ExcelWaccSheetInitializer.initialize(sheet, waccModel);
    }

    private void initializeFinancialSheet(XSSFWorkbook workbook, Company company, CompanySector companySector) throws IOException {
        final XSSFSheet sheet = workbook.getSheet(FINANCIALS_SHEET_NAME);

        final FinancialsModel financialsModel = financialService.readFinancials(company, companySector);

        initializeIncomeStatementRows(sheet, financialsModel);
        initializeBalanceSheetRows(sheet, financialsModel);
        initializeCashFlowStatementRows(sheet, financialsModel);
        initializeMetricsRows(sheet, financialsModel);
    }

    private void initializeGrahamSheet(XSSFWorkbook workbook, Company company, CompanySector companySector) throws IOException {
        final XSSFSheet sheet = workbook.getSheet(GRAHAM_SHEET_NAME);
        final GrahamDataModel grahamDataModel = financialService.readGraham(company, companySector);

        ExcelGrahamSheetInitializer.initialize(sheet, grahamDataModel);
    }

    private void initializeDdmModel(XSSFWorkbook workbook, Company company, CompanySector companySector) throws IOException {
        final XSSFSheet sheet = workbook.getSheet(DDM_SHEET_NAME);
        final DdmDataModel ddmDataModel = financialService.readDdm(company, companySector);

        ExcelDdmSheetInitializer.initialize(sheet, ddmDataModel);
    }

    private File createOutputCompanyTemplate(Company company, CompanySector companySector) throws IOException {
        File template = new File(getClass().getClassLoader().getResource("companies/template.xlsx").getFile());
        File destination = new File(getClass().getClassLoader().getResource("companies/" + companySector.getDirectoryName() + "/" + company.getDirectoryName()).getFile() + "/" + company.getTitle() + ".xlsx");
        FileUtils.copyFile(template, destination);
        return destination;
    }

    private XSSFWorkbook getWorkbook(File companyTemplateFile) throws IOException {
        FileInputStream file = new FileInputStream(companyTemplateFile);
        return new XSSFWorkbook(file);
    }
}
