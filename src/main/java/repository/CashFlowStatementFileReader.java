package repository;

import model.Company;
import model.CompanySector;
import model.statement.CashFlowStatementModel;
import util.BiConsumer;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static repository.FileReaderUtil.getInputStreamCompanyFile;

public class CashFlowStatementFileReader implements FileReader<CashFlowStatementModel> {
    private static final String FILE_NAME = "cashflow_statement.txt";

    /**
     * Key - row index
     * Value - list of values related to cash flow attribute
     */
    private static final Map<Integer, BiConsumer<List<String>, CashFlowStatementModel>> CASH_FLOW_STATEMENT_INITIALIZERS;

    static {
        CASH_FLOW_STATEMENT_INITIALIZERS = new HashMap<>();
        CASH_FLOW_STATEMENT_INITIALIZERS.put(1, (values, cashFlowStatement) -> cashFlowStatement.setDepreciationAmortization(values));
        CASH_FLOW_STATEMENT_INITIALIZERS.put(2, (values, cashFlowStatement) -> cashFlowStatement.setCashFlowFromOperatingActivities(values));
        CASH_FLOW_STATEMENT_INITIALIZERS.put(3, (values, cashFlowStatement) -> cashFlowStatement.setDividendsPaid(values));
    }

    @Override
    public CashFlowStatementModel read(Company company, CompanySector companySector) throws IOException {
        final InputStream inputStream = getInputStreamCompanyFile(company, companySector, getFileName());
        final CashFlowStatementModel cashFlowStatementModel = new CashFlowStatementModel();
        return FileReaderUtil.mapStatementFileToModel(inputStream, cashFlowStatementModel, CASH_FLOW_STATEMENT_INITIALIZERS);
    }

    @Override
    public String getFileName() {
        return FILE_NAME;
    }
}
