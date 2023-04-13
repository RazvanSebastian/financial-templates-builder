package model.statement;

import java.util.List;

public class CashFlowStatementModel {

    private List<String> freeCashFlow;
    private List<String> dividendsPaid;

    public List<String> getFreeCashFlow() {
        return freeCashFlow;
    }

    public void setFreeCashFlow(List<String> freeCashFlow) {
        this.freeCashFlow = freeCashFlow;
    }

    public List<String> getDividendsPaid() {
        return dividendsPaid;
    }

    public void setDividendsPaid(List<String> dividendsPaid) {
        this.dividendsPaid = dividendsPaid;
    }
}
