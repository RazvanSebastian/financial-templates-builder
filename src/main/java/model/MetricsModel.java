package model;

import java.util.List;

public class MetricsModel {

    private List<String> ROIC;
    private List<String> priceToEarnings;
    private List<String> priceToFCF;

    public List<String> getROIC() {
        return ROIC;
    }

    public void setROIC(List<String> ROIC) {
        this.ROIC = ROIC;
    }

    public List<String> getPriceToEarnings() {
        return priceToEarnings;
    }

    public void setPriceToEarnings(List<String> priceToEarnings) {
        this.priceToEarnings = priceToEarnings;
    }

    public List<String> getPriceToFCF() {
        return priceToFCF;
    }

    public void setPriceToFCF(List<String> priceToFCF) {
        this.priceToFCF = priceToFCF;
    }
}
