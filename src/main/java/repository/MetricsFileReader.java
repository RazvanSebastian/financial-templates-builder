package repository;

import model.Company;
import model.statement.MetricsModel;
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

public class MetricsFileReader implements FileReader<MetricsModel> {

    private static final String DELIMITER = ",";

    /**
     * Key - row index
     * Value - list of values related to income attribute
     */
    private static final Map<Integer, BiSupplier<List<String>, MetricsModel>> METRICS_INITIALIZERS;

    static {
        METRICS_INITIALIZERS = new HashMap<>();
        METRICS_INITIALIZERS.put(1, (values, metrics) -> metrics.setROIC(values));
        METRICS_INITIALIZERS.put(2, (values, metrics) -> metrics.setPriceToEarnings(values));
        METRICS_INITIALIZERS.put(3, (values, metrics) -> metrics.setPriceToFCF(values));
        // First value will be PEG title, 2nd the value of PEG and the rest will be 0
        METRICS_INITIALIZERS.put(4, (values, metrics) -> metrics.setPEG(values.get(1)));
    }

    @Override
    public MetricsModel read(Company company) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("companies/" + company.getDirectoryName() + "/metrics.txt");
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            final MetricsModel metricsModel = new MetricsModel();

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            int lineIndex = 0;

            while ((line = bufferedReader.readLine()) != null) {
                lineIndex++;

                List<String> lineList = Stream.of(line.split(DELIMITER)).collect(Collectors.toList());
                lineList.set(0, "0");

                METRICS_INITIALIZERS.get(lineIndex).apply(lineList, metricsModel);
            }

            return metricsModel;
        }
    }
}
