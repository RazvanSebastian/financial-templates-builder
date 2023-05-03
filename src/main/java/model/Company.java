package model;

public enum Company {
    // Communication
    COMCAST("Comcast", "CMCA_Comcast"),
    CISCO("Cisco", "CSCO_Cisco"),

    // Industrial
    UNION_PACIFIC("UnionPacific", "UNP_UnionPacific"),
    CELANESE("Celanese", "CE_Celanese"),
    STANLEY_BLACK_DECKER("StanleyBlackAndDecker", "SWK_StanleyBlackAndDecker"),

    // Medical / Pharma
    JOHNSON_AND_JOHNSON("JohnsonAndJohnson", "JNJ_JohnsonAndJohnson"),
    MEDTRONIC("Medtronic", "MDT_Medtronic"),
    ABBVIE("Abbvie", "ABBV_Abbvie"),

    // Tech
    ALPHABET("Alphabet", "GOOG_Alphabet"),
    MICROSOFT("Microsoft", "MSFT_Microsoft"),
    TEXAS_INSTRUMENTS("TexasInstruments", "TXN_TexasInstruments"),

    // Consumer Defence
    TYSON_FOODS("TysonFoods", "TSN_TysonFoods"),
    BRITISH_AMERICAN_TABACCO("BritishAmericanTabacco", "BTI_BritishAmericanTabacco"),
    UNILEVER("Unilever", "UL_Unilever"),
    PROCTER_GAMBLE("ProcterGamble", "PG_Procter&Gamble"),

    // Utilities
    UGI("UGI", "UGI_UGICorp");

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
