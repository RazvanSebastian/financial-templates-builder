package model;

public enum CompanySector {

    COMMUNICATIONS(19.38, 7.86, "communications"),
    INDUSTRIAL(19.16, 14.40, "industrial"),
    PHARMA(25.27, 18.34, "pharma"),
    CONSUMER_DEFENSE(22.41, 16.17, "consumer_defense"),
    UTILITIES(20.47, 10.31, "utilities");

    private double PER;
    private double PFCF;

    private String directoryName;

    CompanySector(double PER, double PFCF, String directoryName) {
        this.PER = PER;
        this.PFCF = PFCF;
        this.directoryName = directoryName;
    }

    public double getPER() {
        return PER;
    }

    public double getPFCF() {
        return PFCF;
    }

    public String getDirectoryName() {
        return directoryName;
    }
}
