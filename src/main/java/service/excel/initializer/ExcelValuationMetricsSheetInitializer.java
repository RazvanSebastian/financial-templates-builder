package service.excel.initializer;

import model.CompanySector;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ExcelValuationMetricsSheetInitializer {

    private static final String BAD_EMOJI_LOCATION = "V11";
    private static final String OK_EMOJI_LOCATION = "U11";
    private static final String GOOD_EMOJI_LOCATION = "T11";

    public static void initializeROA(XSSFSheet sheet) {
        XSSFCell cellValue = sheet.getRow(11).getCell(5);
        XSSFCell cellValuation = sheet.getRow(11).getCell(3);
        if (cellValue.getNumericCellValue() < 5) {
            Emoji.BAD.setEmoji(sheet, cellValuation);
        } else if (cellValue.getNumericCellValue() >= 5 && cellValue.getNumericCellValue() < 10) {
            Emoji.OK.setEmoji(sheet, cellValuation);
        } else {
            Emoji.EXCELLENT.setEmoji(sheet, cellValuation);
        }
    }

    public static void initializeROE(XSSFSheet sheet) {
        XSSFCell cellValue = sheet.getRow(13).getCell(5);
        XSSFCell cellValuation = sheet.getRow(13).getCell(3);
        if (cellValue.getNumericCellValue() >= 8) {
            Emoji.EXCELLENT.setEmoji(sheet, cellValuation);
        } else {
            Emoji.BAD.setEmoji(sheet, cellValuation);
        }
    }

    public static void initializeROIC(XSSFSheet sheet) {
        XSSFCell cellValue = sheet.getRow(15).getCell(5);
        XSSFCell cellValuation = sheet.getRow(15).getCell(3);

        XSSFCell waccCellValue = sheet.getRow(15).getCell(4);
        if (cellValue.getNumericCellValue() >= waccCellValue.getNumericCellValue() * 100) {
            Emoji.EXCELLENT.setEmoji(sheet, cellValuation);
        } else {
            Emoji.BAD.setEmoji(sheet, cellValuation);
        }
    }

    public static void initializeCurrentRatio(XSSFSheet sheet) {
        XSSFCell cellValue = sheet.getRow(17).getCell(5);
        XSSFCell cellValuation = sheet.getRow(17).getCell(3);
        if (cellValue.getNumericCellValue() < 1) {
            Emoji.BAD.setEmoji(sheet, cellValuation);
        } else if (cellValue.getNumericCellValue() >= 1 && cellValue.getNumericCellValue() <= 1.5) {
            Emoji.OK.setEmoji(sheet, cellValuation);
        } else {
            Emoji.EXCELLENT.setEmoji(sheet, cellValuation);
        }
    }

    public static void initializeLiabilitiesToEquity(XSSFSheet sheet) {
        XSSFCell cellValue = sheet.getRow(19).getCell(5);
        XSSFCell cellValuation = sheet.getRow(19).getCell(3);
        if (cellValue.getNumericCellValue() < 1) {
            Emoji.EXCELLENT.setEmoji(sheet, cellValuation);
        } else if (cellValue.getNumericCellValue() >= 1 && cellValue.getNumericCellValue() <= 2) {
            Emoji.OK.setEmoji(sheet, cellValuation);
        } else {
            Emoji.BAD.setEmoji(sheet, cellValuation);
        }
    }

    public static void initializeLongDebtToEbidta(XSSFSheet sheet) {
        XSSFCell cellValue = sheet.getRow(21).getCell(5);
        XSSFCell cellValuation = sheet.getRow(21).getCell(3);
        if (cellValue.getNumericCellValue() < 3) {
            Emoji.EXCELLENT.setEmoji(sheet, cellValuation);
        } else if (cellValue.getNumericCellValue() >= 3 && cellValue.getNumericCellValue() <= 4) {
            Emoji.OK.setEmoji(sheet, cellValuation);
        } else {
            Emoji.BAD.setEmoji(sheet, cellValuation);
        }
    }

    public static void initializeNumberOfSharesDecrease(XSSFSheet sheet) {
        XSSFCell oldestYear = sheet.getRow(23).getCell(6);
        XSSFCell newestYear = sheet.getRow(23).getCell(15);
        XSSFCell cellValuation = sheet.getRow(23).getCell(3);
        if (oldestYear.getNumericCellValue() > newestYear.getNumericCellValue()) {
            Emoji.EXCELLENT.setEmoji(sheet, cellValuation);
        } else {
            Emoji.BAD.setEmoji(sheet, cellValuation);
        }
    }

    public static void initializePER(XSSFSheet sheet, CompanySector companySector) {
        XSSFCell cellValue = sheet.getRow(25).getCell(5);
        XSSFCell cellValuation = sheet.getRow(25).getCell(3);

        XSSFCell cellConditionDetails = sheet.getRow(25).getCell(4);
        cellConditionDetails.setCellValue(String.format("sector median (%s)", companySector.getPER()));

        if (cellValue.getNumericCellValue() < companySector.getPER()) {
            Emoji.EXCELLENT.setEmoji(sheet, cellValuation);
        } else {
            Emoji.BAD.setEmoji(sheet, cellValuation);
        }
    }

    public static void initializePFCF(XSSFSheet sheet, CompanySector companySector) {
        XSSFCell cellValue = sheet.getRow(27).getCell(5);
        XSSFCell cellValuation = sheet.getRow(27).getCell(3);

        XSSFCell cellConditionDetails = sheet.getRow(27).getCell(4);
        cellConditionDetails.setCellValue(String.format("sector median (%s)", companySector.getPFCF()));

        if (cellValue.getNumericCellValue() < companySector.getPFCF()) {
            Emoji.EXCELLENT.setEmoji(sheet, cellValuation);
        } else {
            Emoji.BAD.setEmoji(sheet, cellValuation);
        }
    }

    public static void initializePEG(XSSFSheet sheet) {
        XSSFCell cellValue = sheet.getRow(29).getCell(5);
        XSSFCell cellValuation = sheet.getRow(29).getCell(3);

        if (cellValue.getNumericCellValue() <= 1) {
            Emoji.EXCELLENT.setEmoji(sheet, cellValuation);
        } else if (cellValue.getNumericCellValue() > 1 && cellValue.getNumericCellValue() <= 1.5) {
            Emoji.OK.setEmoji(sheet, cellValuation);
        } else {
            Emoji.BAD.setEmoji(sheet, cellValuation);
        }
    }

    public static void initializeCagrFcf5Years(XSSFSheet sheet) {
        XSSFCell cellValue = sheet.getRow(31).getCell(5);
        XSSFCell cellValuation = sheet.getRow(31).getCell(3);

        if (cellValue.getNumericCellValue() * 100 > 0) {
            Emoji.EXCELLENT.setEmoji(sheet, cellValuation);
        } else {
            Emoji.BAD.setEmoji(sheet, cellValuation);
        }
    }

    public static void initializeCagrFcf10Years(XSSFSheet sheet) {
        XSSFCell cellValue = sheet.getRow(32).getCell(5);
        XSSFCell cellValuation = sheet.getRow(32).getCell(3);

        if (cellValue.getNumericCellValue() * 100 > 0) {
            Emoji.EXCELLENT.setEmoji(sheet, cellValuation);
        } else {
            Emoji.BAD.setEmoji(sheet, cellValuation);
        }
    }

    public static void initializeCagrEbidta5Years(XSSFSheet sheet) {
        XSSFCell cellValue = sheet.getRow(34).getCell(5);
        XSSFCell cellValuation = sheet.getRow(34).getCell(3);

        if (cellValue.getNumericCellValue() * 100 > 0) {
            Emoji.EXCELLENT.setEmoji(sheet, cellValuation);
        } else {
            Emoji.BAD.setEmoji(sheet, cellValuation);
        }
    }

    public static void initializeCagrEbidta10Years(XSSFSheet sheet) {
        XSSFCell cellValue = sheet.getRow(35).getCell(5);
        XSSFCell cellValuation = sheet.getRow(35).getCell(3);

        if (cellValue.getNumericCellValue() * 100 > 0) {
            Emoji.EXCELLENT.setEmoji(sheet, cellValuation);
        } else {
            Emoji.BAD.setEmoji(sheet, cellValuation);
        }
    }

    public static void initializeCagrNetIncome5Years(XSSFSheet sheet) {
        XSSFCell cellValue = sheet.getRow(37).getCell(5);
        XSSFCell cellValuation = sheet.getRow(37).getCell(3);

        if (cellValue.getNumericCellValue() * 100 > 0) {
            Emoji.EXCELLENT.setEmoji(sheet, cellValuation);
        } else {
            Emoji.BAD.setEmoji(sheet, cellValuation);
        }
    }

    public static void initializeCagrNetIncome10Years(XSSFSheet sheet) {
        XSSFCell cellValue = sheet.getRow(38).getCell(5);
        XSSFCell cellValuation = sheet.getRow(38).getCell(3);

        if (cellValue.getNumericCellValue() * 100 > 0) {
            Emoji.EXCELLENT.setEmoji(sheet, cellValuation);
        } else {
            Emoji.BAD.setEmoji(sheet, cellValuation);
        }
    }

    public static void initializeCagrRevenue5Years(XSSFSheet sheet) {
        XSSFCell cellValue = sheet.getRow(40).getCell(5);
        XSSFCell cellValuation = sheet.getRow(40).getCell(3);

        if (cellValue.getNumericCellValue() * 100 > 0) {
            Emoji.EXCELLENT.setEmoji(sheet, cellValuation);
        } else {
            Emoji.BAD.setEmoji(sheet, cellValuation);
        }
    }

    public static void initializeCagrRevenue10Years(XSSFSheet sheet) {
        XSSFCell cellValue = sheet.getRow(41).getCell(5);
        XSSFCell cellValuation = sheet.getRow(41).getCell(3);

        if (cellValue.getNumericCellValue() * 100 > 0) {
            Emoji.EXCELLENT.setEmoji(sheet, cellValuation);
        } else {
            Emoji.BAD.setEmoji(sheet, cellValuation);
        }
    }

    public static void initializeNetPayoutRatio(XSSFSheet sheet) {
        XSSFCell cellValue = sheet.getRow(43).getCell(5);
        XSSFCell cellValuation = sheet.getRow(43).getCell(3);

        float cellNumericValue = (float) (cellValue.getNumericCellValue() * 100);

        if (cellNumericValue <= 50) {
            Emoji.EXCELLENT.setEmoji(sheet, cellValuation);
        } else if (cellNumericValue > 50 && cellNumericValue <= 75) {
            Emoji.OK.setEmoji(sheet, cellValuation);
        } else {
            Emoji.BAD.setEmoji(sheet, cellValuation);
        }
    }

    public static void initializeAverageIncrease(XSSFSheet sheet) {
        XSSFCell cellValue = sheet.getRow(44).getCell(5);
        XSSFCell cellValuation = sheet.getRow(44).getCell(3);

        if (cellValue.getNumericCellValue() * 100 > 0) {
            Emoji.EXCELLENT.setEmoji(sheet, cellValuation);
        } else {
            Emoji.BAD.setEmoji(sheet, cellValuation);
        }
    }

    public static void initializeCagrDividend5Years(XSSFSheet sheet) {
        XSSFCell cellValue = sheet.getRow(45).getCell(5);
        XSSFCell cellValuation = sheet.getRow(45).getCell(3);

        if (cellValue.getNumericCellValue() * 100 > 0) {
            Emoji.EXCELLENT.setEmoji(sheet, cellValuation);
        } else {
            Emoji.BAD.setEmoji(sheet, cellValuation);
        }
    }

    public static void initializeChowder5Years(XSSFSheet sheet) {
        XSSFCell cellValue = sheet.getRow(46).getCell(5);
        XSSFCell cellValuation = sheet.getRow(46).getCell(3);

        if (cellValue.getNumericCellValue() * 100 >= 12) {
            Emoji.EXCELLENT.setEmoji(sheet, cellValuation);
        } else {
            Emoji.BAD.setEmoji(sheet, cellValuation);
        }
    }

    enum Emoji {
        EXCELLENT {
            @Override
            void setEmoji(XSSFSheet sheet, XSSFCell cell) {
                XSSFCell emojiCell = sheet.getRow(10).getCell(19);

                cell.setCellStyle(emojiCell.getCellStyle());
                cell.setCellFormula(GOOD_EMOJI_LOCATION);
            }
        },
        OK {
            @Override
            void setEmoji(XSSFSheet sheet, XSSFCell cell) {
                XSSFCell emojiCell = sheet.getRow(10).getCell(20);

                cell.setCellStyle(emojiCell.getCellStyle());
                cell.setCellFormula(OK_EMOJI_LOCATION);
            }
        }, BAD {
            @Override
            void setEmoji(XSSFSheet sheet, XSSFCell cell) {
                XSSFCell emojiCell = sheet.getRow(10).getCell(21);

                cell.setCellStyle(emojiCell.getCellStyle());
                cell.setCellFormula(BAD_EMOJI_LOCATION);
            }
        };

        abstract void setEmoji(XSSFSheet sheet, XSSFCell cell);
    }
}
