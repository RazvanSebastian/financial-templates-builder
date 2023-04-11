package service;

import model.*;
import repository.*;

import java.io.IOException;

public class FinancialService {

    private final IncomeStatementFileReader incomeStatementFileReader;
    private final BalanceSheetStatementFileReader balanceSheetStatementFileReader;
    private final CashFlowStatementFileReader cashFlowStatementFileReader;
    private final MetricsFileReader metricsFileReader;

    public FinancialService(IncomeStatementFileReader incomeStatementFileReader, BalanceSheetStatementFileReader balanceSheetStatementFileReader, CashFlowStatementFileReader cashFlowStatementFileReader, MetricsFileReader metricsFileReader) {
        this.incomeStatementFileReader = incomeStatementFileReader;
        this.balanceSheetStatementFileReader = balanceSheetStatementFileReader;
        this.cashFlowStatementFileReader = cashFlowStatementFileReader;
        this.metricsFileReader = metricsFileReader;
    }

    public FinancialsModel readFinancials(Company company) throws IOException {
        IncomeStatementModel incomeStatementModel = incomeStatementFileReader.read(company);
        BalanceSheetStatementModel balanceSheetStatementModel = balanceSheetStatementFileReader.read(company);
        CashFlowStatementModel cashFlowStatementModel = cashFlowStatementFileReader.read(company);
        MetricsModel metricsModel = metricsFileReader.read(company);

        return new FinancialsModel(
                incomeStatementModel,
                balanceSheetStatementModel,
                cashFlowStatementModel,
                metricsModel
        );
    }
}
