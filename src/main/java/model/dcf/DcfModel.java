package model.dcf;

import java.util.List;

public class DcfModel {

    private String revenueGrowth;
    private String ebidtaMultiple;
    private String ebidtaGrowthRate;
    private String numberOfShares;
    private List<String> revenueEstimations;
    private List<String> ebidtaEstimations;

    public String getRevenueGrowth() {
        return revenueGrowth;
    }

    public void setRevenueGrowth(String revenueGrowth) {
        this.revenueGrowth = revenueGrowth;
    }

    public String getEbidtaMultiple() {
        return ebidtaMultiple;
    }

    public void setEbidtaMultiple(String ebidtaMultiple) {
        this.ebidtaMultiple = ebidtaMultiple;
    }

    public String getEbidtaGrowthRate() {
        return ebidtaGrowthRate;
    }

    public void setEbidtaGrowthRate(String ebidtaGrowthRate) {
        this.ebidtaGrowthRate = ebidtaGrowthRate;
    }

    public String getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(String numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public List<String> getRevenueEstimations() {
        return revenueEstimations;
    }

    public void setRevenueEstimations(List<String> revenueEstimations) {
        this.revenueEstimations = revenueEstimations;
    }

    public List<String> getEbidtaEstimations() {
        return ebidtaEstimations;
    }

    public void setEbidtaEstimations(List<String> ebidtaEstimations) {
        this.ebidtaEstimations = ebidtaEstimations;
    }
}
