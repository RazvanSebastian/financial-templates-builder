package repository;

import com.google.common.collect.Lists;
import model.CashFlowStatementModel;
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

public class CashFlowStatementFileReader {

    private static final String DELIMITER = ",";

    /**
     * Key - row index
     * Value - list of values related to cash flow attribute
     */
    private static final Map<Integer, BiSupplier<List<String>, CashFlowStatementModel>> CASH_FLOW_STATEMENT_INITIALIZERS;

    static {
        CASH_FLOW_STATEMENT_INITIALIZERS = new HashMap<>();
        CASH_FLOW_STATEMENT_INITIALIZERS.put(1, (values, cashFlowStatement) -> cashFlowStatement.setDepreciationAmortization(values));
        CASH_FLOW_STATEMENT_INITIALIZERS.put(2, (values, cashFlowStatement) -> cashFlowStatement.setCashFlowFromOperatingActivities(values));
        CASH_FLOW_STATEMENT_INITIALIZERS.put(3, (values, cashFlowStatement) -> cashFlowStatement.setDividendsPaid(values));
    }

    public CashFlowStatementModel read(Company company) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("companies/" + company.getDirectoryName() + "/cashflow_statement.txt");
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            final CashFlowStatementModel cashFlowStatementModel = new CashFlowStatementModel();

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            int lineIndex = 0;

            while ((line = bufferedReader.readLine()) != null) {
                lineIndex++;

                List<String> cashFlowLine = Lists.reverse(
                        new ArrayList<>(List.of(line.split(DELIMITER)))
                );

                CASH_FLOW_STATEMENT_INITIALIZERS.get(lineIndex).apply(cashFlowLine.subList(3, cashFlowLine.size()), cashFlowStatementModel);
            }

            return cashFlowStatementModel;
        }
    }
}
