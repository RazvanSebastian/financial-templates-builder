package model;

public enum CompanySector {

    TELECOMMUNICATION(19.38, 7.86);

    private double PER;
    private double PFCF;

    CompanySector(double PER, double PFCF) {
        this.PER = PER;
        this.PFCF = PFCF;
    }

    public double getPER() {
        return PER;
    }

    public double getPFCF() {
        return PFCF;
    }
}
