package model;

public class FinancialsModel {

    private IncomeStatementModel incomeStatementModel;
    private BalanceSheetStatementModel balanceSheetStatementModel;
    private CashFlowStatementModel cashFlowStatementModel;
    private MetricsModel metricsModel;

    public FinancialsModel(IncomeStatementModel incomeStatementModel, BalanceSheetStatementModel balanceSheetStatementModel, CashFlowStatementModel cashFlowStatementModel, MetricsModel metricsModel) {
        this.incomeStatementModel = incomeStatementModel;
        this.balanceSheetStatementModel = balanceSheetStatementModel;
        this.cashFlowStatementModel = cashFlowStatementModel;
        this.metricsModel = metricsModel;
    }

    public IncomeStatementModel getIncomeStatementModel() {
        return incomeStatementModel;
    }

    public void setIncomeStatementModel(IncomeStatementModel incomeStatementModel) {
        this.incomeStatementModel = incomeStatementModel;
    }

    public BalanceSheetStatementModel getBalanceSheetStatementModel() {
        return balanceSheetStatementModel;
    }

    public void setBalanceSheetStatementModel(BalanceSheetStatementModel balanceSheetStatementModel) {
        this.balanceSheetStatementModel = balanceSheetStatementModel;
    }

    public CashFlowStatementModel getCashFlowStatementModel() {
        return cashFlowStatementModel;
    }

    public void setCashFlowStatementModel(CashFlowStatementModel cashFlowStatementModel) {
        this.cashFlowStatementModel = cashFlowStatementModel;
    }

    public MetricsModel getMetricsModel() {
        return metricsModel;
    }

    public void setMetricsModel(MetricsModel metricsModel) {
        this.metricsModel = metricsModel;
    }
}
