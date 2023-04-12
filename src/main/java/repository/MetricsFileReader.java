package repository;

import model.Company;
import model.CompanySector;
import model.statement.MetricsModel;
import util.BiConsumer;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static repository.FileReaderUtil.DELIMITER;
import static repository.FileReaderUtil.getInputStreamCompanyFile;

public class MetricsFileReader implements FileReader<MetricsModel> {
    private static final String FILE_NAME = "metrics.txt";

    /**
     * Key - row index
     * Value - list of values related to income attribute
     */
    private static final Map<Integer, BiConsumer<String, MetricsModel>> METRICS_INITIALIZERS;

    static {
        METRICS_INITIALIZERS = new HashMap<>();
        METRICS_INITIALIZERS.put(1, (line, metrics) -> metrics.setROIC(getLineList(line)));
        METRICS_INITIALIZERS.put(2, (line, metrics) -> metrics.setPriceToEarnings(getLineList(line)));
        METRICS_INITIALIZERS.put(3, (line, metrics) -> metrics.setPriceToFCF(getLineList(line)));
        // First value will be PEG title, 2nd the value of PEG and the rest will be 0
        METRICS_INITIALIZERS.put(4, (line, metrics) -> metrics.setPEG(getLineList(line).get(1)));
    }

    @Override
    public MetricsModel read(Company company, CompanySector companySector) throws IOException {
        final InputStream inputStream = getInputStreamCompanyFile(company, companySector, getFileName());
        final MetricsModel metricsModel = new MetricsModel();
        return FileReaderUtil.mapFileToModel(inputStream, metricsModel, METRICS_INITIALIZERS);
    }

    @Override
    public String getFileName() {
        return FILE_NAME;
    }

    private static List<String> getLineList(String line) {
        List<String> lineList = Stream.of(line.split(DELIMITER)).collect(Collectors.toList());
        lineList.set(0, "0");
        return lineList;
    }
}
