package repository;

import model.Company;
import model.CompanySector;
import model.statement.BalanceSheetStatementModel;
import util.BiConsumer;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static repository.FileReaderUtil.getInputStreamCompanyFile;

public class BalanceSheetStatementFileReader implements FileReader<BalanceSheetStatementModel> {
    private static final String FILE_NAME = "balancesheet_statement.txt";

    /**
     * Key - row index
     * Value - list of values related to balance sheet attribute
     */
    private static final Map<Integer, BiConsumer<List<String>, BalanceSheetStatementModel>> BALANCE_SHEET_STATEMENT_INITIALIZERS;

    static {
        BALANCE_SHEET_STATEMENT_INITIALIZERS = new HashMap<>();
        BALANCE_SHEET_STATEMENT_INITIALIZERS.put(1, (values, balanceSheetStatement) -> balanceSheetStatement.setPPE(values));
        BALANCE_SHEET_STATEMENT_INITIALIZERS.put(2, (values, balanceSheetStatement) -> balanceSheetStatement.setCurrentAssets(values));
        BALANCE_SHEET_STATEMENT_INITIALIZERS.put(3, (values, balanceSheetStatement) -> balanceSheetStatement.setNonCurrentAssets(values));
        BALANCE_SHEET_STATEMENT_INITIALIZERS.put(4, (values, balanceSheetStatement) -> balanceSheetStatement.setCurrentLiabilities(values));
        BALANCE_SHEET_STATEMENT_INITIALIZERS.put(5, (values, balanceSheetStatement) -> balanceSheetStatement.setNonCurrentLiabilities(values));
    }

    @Override
    public BalanceSheetStatementModel read(Company company, CompanySector companySector) throws IOException {
        final InputStream inputStream = getInputStreamCompanyFile(company, companySector, getFileName());
        final BalanceSheetStatementModel balanceSheetStatementModel = new BalanceSheetStatementModel();
        return FileReaderUtil.mapStatementFileToModel(inputStream, balanceSheetStatementModel, BALANCE_SHEET_STATEMENT_INITIALIZERS);
    }

    @Override
    public String getFileName() {
        return FILE_NAME;
    }
}
