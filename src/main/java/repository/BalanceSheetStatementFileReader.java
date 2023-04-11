package repository;

import com.google.common.collect.Lists;
import model.BalanceSheetStatementModel;
import model.Company;
import util.BiSupplier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BalanceSheetStatementFileReader {

    private static final String DELIMITER = ",";

    /**
     * Key - row index
     * Value - list of values related to balance sheet attribute
     */
    private static final Map<Integer, BiSupplier<List<String>, BalanceSheetStatementModel>> BALANCE_SHEET_STATEMENT_INITIALIZERS;

    static {
        BALANCE_SHEET_STATEMENT_INITIALIZERS = new HashMap<>();
        BALANCE_SHEET_STATEMENT_INITIALIZERS.put(1, (values, balanceSheetStatement) -> balanceSheetStatement.setPPE(values));
        BALANCE_SHEET_STATEMENT_INITIALIZERS.put(2, (values, balanceSheetStatement) -> balanceSheetStatement.setCurrentAssets(values));
        BALANCE_SHEET_STATEMENT_INITIALIZERS.put(3, (values, balanceSheetStatement) -> balanceSheetStatement.setNonCurrentAssets(values));
        BALANCE_SHEET_STATEMENT_INITIALIZERS.put(4, (values, balanceSheetStatement) -> balanceSheetStatement.setCurrentLiabilities(values));
        BALANCE_SHEET_STATEMENT_INITIALIZERS.put(5, (values, balanceSheetStatement) -> balanceSheetStatement.setNonCurrentLiabilities(values));
    }

    public BalanceSheetStatementModel read(Company company) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("companies/" + company.getDirectoryName() + "/balancesheet_statement.txt");
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            final BalanceSheetStatementModel balanceSheetStatementModel = new BalanceSheetStatementModel();

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            int lineIndex = 0;

            while ((line = bufferedReader.readLine()) != null) {
                lineIndex++;

                List<String> balanceSheetLine = Lists.reverse(
                        new ArrayList<>(List.of(line.split(DELIMITER)))
                );

                BALANCE_SHEET_STATEMENT_INITIALIZERS.get(lineIndex).apply(balanceSheetLine.subList(3, balanceSheetLine.size()), balanceSheetStatementModel);
            }

            return balanceSheetStatementModel;
        }
    }
}
