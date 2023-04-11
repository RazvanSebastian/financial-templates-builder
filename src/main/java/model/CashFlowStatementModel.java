package model;

import java.util.List;

public class CashFlowStatementModel {

    private List<String> depreciationAmortization;
    private List<String> cashFlowFromOperatingActivities;
    private List<String> dividendsPaid;

    public List<String> getDepreciationAmortization() {
        return depreciationAmortization;
    }

    public void setDepreciationAmortization(List<String> depreciationAmortization) {
        this.depreciationAmortization = depreciationAmortization;
    }

    public List<String> getCashFlowFromOperatingActivities() {
        return cashFlowFromOperatingActivities;
    }

    public void setCashFlowFromOperatingActivities(List<String> cashFlowFromOperatingActivities) {
        this.cashFlowFromOperatingActivities = cashFlowFromOperatingActivities;
    }

    public List<String> getDividendsPaid() {
        return dividendsPaid;
    }

    public void setDividendsPaid(List<String> dividendsPaid) {
        this.dividendsPaid = dividendsPaid;
    }
}
