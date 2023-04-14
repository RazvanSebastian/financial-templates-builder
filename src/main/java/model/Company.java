package model;

public enum Company {

    COMCAST("Comcast", "CMCA_Comcast"),
    UNION_PACIFIC("UnionPacific", "UNP_UnionPacific"),
    JOHNSON_AND_JOHNSON("JohnsonAndJohnson", "JNJ_JohnsonAndJohnson"),
    TYSON_FOODS("TysonFoods", "TSN_TysonFoods"),
    CELANESE("Celanese", "CE_Celanese");

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
