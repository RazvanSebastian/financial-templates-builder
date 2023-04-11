package service;

import model.Company;
import model.dcf.DcfModel;
import model.dcf.WaccModel;
import model.statement.*;
import repository.*;

import java.io.IOException;

public class FinancialService {

    private final IncomeStatementFileReader incomeStatementFileReader;
    private final BalanceSheetStatementFileReader balanceSheetStatementFileReader;
    private final CashFlowStatementFileReader cashFlowStatementFileReader;
    private final MetricsFileReader metricsFileReader;
    private final WaccFileReader waccFileReader;
    private final DcfFileReader dcfFileReader;

    public FinancialService(IncomeStatementFileReader incomeStatementFileReader, BalanceSheetStatementFileReader balanceSheetStatementFileReader, CashFlowStatementFileReader cashFlowStatementFileReader, MetricsFileReader metricsFileReader, WaccFileReader waccFileReader, DcfFileReader dcfFileReader) {
        this.incomeStatementFileReader = incomeStatementFileReader;
        this.balanceSheetStatementFileReader = balanceSheetStatementFileReader;
        this.cashFlowStatementFileReader = cashFlowStatementFileReader;
        this.metricsFileReader = metricsFileReader;
        this.waccFileReader = waccFileReader;
        this.dcfFileReader = dcfFileReader;
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

    public WaccModel readWacc(Company company) throws IOException {
        return waccFileReader.read(company);
    }

    public DcfModel readDcf(Company company) throws IOException {
        return dcfFileReader.read(company);
    }
}
