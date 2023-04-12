package service;

import model.Company;
import model.CompanySector;
import model.DdmDataModel;
import model.GrahamDataModel;
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
    private final GrahamFileReader grahamFileReader;
    private final DdmFileReader ddmFileReader;

    public FinancialService(IncomeStatementFileReader incomeStatementFileReader, BalanceSheetStatementFileReader balanceSheetStatementFileReader, CashFlowStatementFileReader cashFlowStatementFileReader, MetricsFileReader metricsFileReader, WaccFileReader waccFileReader, DcfFileReader dcfFileReader, GrahamFileReader grahamFileReader, DdmFileReader ddmFileReader) {
        this.incomeStatementFileReader = incomeStatementFileReader;
        this.balanceSheetStatementFileReader = balanceSheetStatementFileReader;
        this.cashFlowStatementFileReader = cashFlowStatementFileReader;
        this.metricsFileReader = metricsFileReader;
        this.waccFileReader = waccFileReader;
        this.dcfFileReader = dcfFileReader;
        this.grahamFileReader = grahamFileReader;
        this.ddmFileReader = ddmFileReader;
    }

    public FinancialsModel readFinancials(Company company, CompanySector companySector) throws IOException {
        IncomeStatementModel incomeStatementModel = incomeStatementFileReader.read(company, companySector);
        BalanceSheetStatementModel balanceSheetStatementModel = balanceSheetStatementFileReader.read(company, companySector);
        CashFlowStatementModel cashFlowStatementModel = cashFlowStatementFileReader.read(company, companySector);
        MetricsModel metricsModel = metricsFileReader.read(company, companySector);

        return new FinancialsModel(
                incomeStatementModel,
                balanceSheetStatementModel,
                cashFlowStatementModel,
                metricsModel
        );
    }

    public WaccModel readWacc(Company company, CompanySector companySector) throws IOException {
        return waccFileReader.read(company, companySector);
    }

    public DcfModel readDcf(Company company, CompanySector companySector) throws IOException {
        return dcfFileReader.read(company, companySector);
    }

    public GrahamDataModel readGraham(Company company, CompanySector companySector) throws IOException {
        return grahamFileReader.read(company, companySector);
    }

    public DdmDataModel readDdm(Company company, CompanySector companySector) throws IOException {
        return ddmFileReader.read(company, companySector);
    }
}
