package model;

public class DdmDataModel {

    private String dividendPerShare;
    private String conservativeGrow;
    private String normalGrow;
    private String highGrow;

    public String getDividendPerShare() {
        return dividendPerShare;
    }

    public void setDividendPerShare(String dividendPerShare) {
        this.dividendPerShare = dividendPerShare;
    }

    public String getConservativeGrow() {
        return conservativeGrow;
    }

    public void setConservativeGrow(String conservativeGrow) {
        this.conservativeGrow = conservativeGrow;
    }

    public String getNormalGrow() {
        return normalGrow;
    }

    public void setNormalGrow(String normalGrow) {
        this.normalGrow = normalGrow;
    }

    public String getHighGrow() {
        return highGrow;
    }

    public void setHighGrow(String highGrow) {
        this.highGrow = highGrow;
    }
}
