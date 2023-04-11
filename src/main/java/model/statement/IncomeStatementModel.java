package model.statement;

import java.util.List;

public class IncomeStatementModel {

    private List<String> revenues;
    private List<String> netIncomes;
    private List<String> ebidta;
    private List<String> sharesOutstanding;
    private List<String> earningPerShare;

    public List<String> getRevenues() {
        return revenues;
    }

    public void setRevenues(List<String> revenues) {
        this.revenues = revenues;
    }

    public List<String> getNetIncomes() {
        return netIncomes;
    }

    public void setNetIncomes(List<String> netIncomes) {
        this.netIncomes = netIncomes;
    }

    public List<String> getEbidta() {
        return ebidta;
    }

    public void setEbidta(List<String> ebidta) {
        this.ebidta = ebidta;
    }

    public List<String> getSharesOutstanding() {
        return sharesOutstanding;
    }

    public void setSharesOutstanding(List<String> sharesOutstanding) {
        this.sharesOutstanding = sharesOutstanding;
    }

    public List<String> getEarningPerShare() {
        return earningPerShare;
    }

    public void setEarningPerShare(List<String> earningPerShare) {
        this.earningPerShare = earningPerShare;
    }
}
