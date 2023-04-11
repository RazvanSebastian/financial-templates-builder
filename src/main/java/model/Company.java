package model;

public enum Company {

    COMCAST("Comcast", "CMCA_Comcast"),
    UNION_PACIFIC("UnionPacific", "UNP_UnionPacific");

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
