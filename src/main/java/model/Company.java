package model;

public enum Company {

    COMCAST("Comcast", "CMCA_Comcast"),
    UNION_PACIFIC("UnionPacific", "UNP_UnionPacific"),
    JOHNSON_AND_JOHNSON("JohnsonAndJohnson", "JNJ_JohnsonAndJohnson"),
    MEDTRONIC("Medtronic", "MDT_Medtronic"),
    TYSON_FOODS("TysonFoods", "TSN_TysonFoods"),
    CELANESE("Celanese", "CE_Celanese"),
    STANLEY_BLACK_DECKER("StanleyBlackAndDecker", "SWK_StanleyBlackAndDecker");

    private final String directoryName;
    private final String title;

    Company(String directoryName, String title) {
        this.directoryName = directoryName;
        this.title = title;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public String getTitle() {
        return title;
    }
}
