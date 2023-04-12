package repository;

import model.Company;
import model.CompanySector;
import model.statement.IncomeStatementModel;
import util.BiConsumer;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static repository.FileReaderUtil.getInputStreamCompanyFile;

public class IncomeStatementFileReader implements FileReader<IncomeStatementModel> {
    private static final String FILE_NAME = "income_statement.txt";

    /**
     * Key - row index
     * Value - list of values related to income attribute
     */
    private static final Map<Integer, BiConsumer<List<String>, IncomeStatementModel>> INCOME_STATEMENT_INITIALIZERS;

    static {
        INCOME_STATEMENT_INITIALIZERS = new HashMap<>();
        INCOME_STATEMENT_INITIALIZERS.put(1, (values, incomeStatement) -> incomeStatement.setRevenues(values));
        INCOME_STATEMENT_INITIALIZERS.put(2, (values, incomeStatement) -> incomeStatement.setNetIncomes(values));
        INCOME_STATEMENT_INITIALIZERS.put(3, (values, incomeStatement) -> incomeStatement.setEbidta(values));
        INCOME_STATEMENT_INITIALIZERS.put(4, (values, incomeStatement) -> incomeStatement.setSharesOutstanding(values));
        INCOME_STATEMENT_INITIALIZERS.put(5, (values, incomeStatement) -> incomeStatement.setEarningPerShare(values));
    }

    @Override
    public IncomeStatementModel read(Company company, CompanySector companySector) throws IOException {
        final InputStream inputStream = getInputStreamCompanyFile(company, companySector, getFileName());
        final IncomeStatementModel incomeStatementModel = new IncomeStatementModel();
        return FileReaderUtil.mapStatementFileToModel(inputStream, incomeStatementModel, INCOME_STATEMENT_INITIALIZERS);
    }

    @Override
    public String getFileName() {
        return FILE_NAME;
    }
}
