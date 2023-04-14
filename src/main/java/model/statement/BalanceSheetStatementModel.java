package model.statement;

import java.util.List;

public class BalanceSheetStatementModel {

    private List<String> currentAssets;
    private List<String> nonCurrentAssets;
    private List<String> currentLiabilities;
    private List<String> nonCurrentLiabilities;
    private List<String> longTermDebt;

    public List<String> getCurrentAssets() {
        return currentAssets;
    }

    public void setCurrentAssets(List<String> currentAssets) {
        this.currentAssets = currentAssets;
    }

    public List<String> getNonCurrentAssets() {
        return nonCurrentAssets;
    }

    public void setNonCurrentAssets(List<String> nonCurrentAssets) {
        this.nonCurrentAssets = nonCurrentAssets;
    }

    public List<String> getCurrentLiabilities() {
        return currentLiabilities;
    }

    public void setCurrentLiabilities(List<String> currentLiabilities) {
        this.currentLiabilities = currentLiabilities;
    }

    public List<String> getNonCurrentLiabilities() {
        return nonCurrentLiabilities;
    }

    public void setNonCurrentLiabilities(List<String> nonCurrentLiabilities) {
        this.nonCurrentLiabilities = nonCurrentLiabilities;
    }

    public List<String> getLongTermDebt() {
        return longTermDebt;
    }

    public void setLongTermDebt(List<String> longTermDebt) {
        this.longTermDebt = longTermDebt;
    }
}
