package repository;

import com.google.common.collect.Lists;
import model.Company;
import model.statement.IncomeStatementModel;
import util.BiSupplier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IncomeStatementFileReader implements FileReader<IncomeStatementModel> {
    private static final String DELIMITER = ",";

    /**
     * Key - row index
     * Value - list of values related to income attribute
     */
    private static final Map<Integer, BiSupplier<List<String>, IncomeStatementModel>> INCOME_STATEMENT_INITIALIZERS;

    static {
        INCOME_STATEMENT_INITIALIZERS = new HashMap<>();
        INCOME_STATEMENT_INITIALIZERS.put(1, (values, incomeStatement) -> incomeStatement.setRevenues(values));
        INCOME_STATEMENT_INITIALIZERS.put(2, (values, incomeStatement) -> incomeStatement.setNetIncomes(values));
        INCOME_STATEMENT_INITIALIZERS.put(3, (values, incomeStatement) -> incomeStatement.setEbidta(values));
        INCOME_STATEMENT_INITIALIZERS.put(4, (values, incomeStatement) -> incomeStatement.setSharesOutstanding(values));
        INCOME_STATEMENT_INITIALIZERS.put(5, (values, incomeStatement) -> incomeStatement.setEarningPerShare(values));
    }

    @Override
    public IncomeStatementModel read(Company company) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("companies/" + company.getDirectoryName() + "/income_statement.txt");
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            final IncomeStatementModel incomeStatementModel = new IncomeStatementModel();

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            int lineIndex = 0;

            while ((line = bufferedReader.readLine()) != null) {
                lineIndex++;

                List<String> lineList = Stream.of(line.split(DELIMITER)).collect(Collectors.toList());
                lineList.remove(0);

                List<String> incomeLine = Lists.reverse(lineList);

                INCOME_STATEMENT_INITIALIZERS.get(lineIndex).apply(incomeLine.subList(3, incomeLine.size()), incomeStatementModel);
            }

            return incomeStatementModel;
        }
    }
}
