package repository;

import com.google.common.collect.Lists;
import model.Company;
import model.CompanySector;
import util.BiConsumer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Map a file to a model by a set of initializer rules.
 */
public class FileReaderUtil {
    public static final String DELIMITER = ",";

    /**
     * Used for Income, Balance sheet, Cash flow statement files
     */
    public static <R> R mapStatementFileToModel(InputStream inputStream, R model, Map<Integer, BiConsumer<List<String>, R>> initializerRules) throws IOException {
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            int lineIndex = 0;

            while ((line = bufferedReader.readLine()) != null) {
                lineIndex++;

                List<String> lineList = Stream.of(line.split(DELIMITER)).collect(Collectors.toList());

                // Remove line title
                lineList.remove(0);

                List<String> statementLineList = Lists.reverse(lineList);

                initializerRules.get(lineIndex).accept(statementLineList.subList(3, statementLineList.size()), model);
            }

            return model;
        }
    }

    /**
     * Used for WACC, DCF, DDM, Metrics and Graham files
     */
    public static <R> R mapFileToModel(InputStream inputStream, R model, Map<Integer, BiConsumer<String, R>> initializerRules) throws IOException {
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            int lineIndex = 0;

            while ((line = bufferedReader.readLine()) != null) {
                lineIndex++;
                initializerRules.get(lineIndex).accept(line, model);
            }

            return model;
        }
    }

    public static InputStream getInputStreamCompanyFile(Company company, CompanySector companySector, String fileName) {
        return FileReaderUtil.class.getClassLoader().getResourceAsStream("companies/" + companySector.getDirectoryName() + "/" + company.getDirectoryName() + "/" + fileName);

    }
}
