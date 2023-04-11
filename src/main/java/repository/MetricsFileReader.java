package repository;

import model.Company;
import model.MetricsModel;
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

public class MetricsFileReader {

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
    }

    public MetricsModel read(Company company) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("companies/" + company.getDirectoryName() + "/metrics.txt");
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            final MetricsModel metricsModel = new MetricsModel();

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            int lineIndex = 0;

            while ((line = bufferedReader.readLine()) != null) {
                lineIndex++;
                List<String> lineList = new ArrayList<>();
                lineList.add("0");
                lineList.addAll(List.of(line.split(DELIMITER)));

                METRICS_INITIALIZERS.get(lineIndex).apply(lineList, metricsModel);
            }

            return metricsModel;
        }
    }
}
