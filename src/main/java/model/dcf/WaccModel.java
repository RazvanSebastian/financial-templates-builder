package model.dcf;

public class WaccModel {

    private String marketCap;
    private String currentLiabilities;
    private String nonCurrentLiabilities;
    private String interestExpenses;
    private String pretaxIncome;
    private String taxProvision;
    private String beta;

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    public String getCurrentLiabilities() {
        return currentLiabilities;
    }

    public void setCurrentLiabilities(String currentLiabilities) {
        this.currentLiabilities = currentLiabilities;
    }

    public String getNonCurrentLiabilities() {
        return nonCurrentLiabilities;
    }

    public void setNonCurrentLiabilities(String nonCurrentLiabilities) {
        this.nonCurrentLiabilities = nonCurrentLiabilities;
    }

    public String getInterestExpenses() {
        return interestExpenses;
    }

    public void setInterestExpenses(String interestExpenses) {
        this.interestExpenses = interestExpenses;
    }

    public String getPretaxIncome() {
        return pretaxIncome;
    }

    public void setPretaxIncome(String pretaxIncome) {
        this.pretaxIncome = pretaxIncome;
    }

    public String getTaxProvision() {
        return taxProvision;
    }

    public void setTaxProvision(String taxProvision) {
        this.taxProvision = taxProvision;
    }

    public String getBeta() {
        return beta;
    }

    public void setBeta(String beta) {
        this.beta = beta;
    }
}
