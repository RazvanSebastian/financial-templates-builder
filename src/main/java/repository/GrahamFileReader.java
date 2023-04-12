package repository;

import model.Company;
import model.CompanySector;
import model.GrahamDataModel;
import util.BiConsumer;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static repository.FileReaderUtil.DELIMITER;
import static repository.FileReaderUtil.getInputStreamCompanyFile;

public class GrahamFileReader implements FileReader<GrahamDataModel> {
    private static final String FILE_NAME = "graham.txt";

    /**
     * Key - row index
     * Value - list of values related to DCF attributes
     */
    private static final Map<Integer, BiConsumer<String, GrahamDataModel>> GRAHAM_INITIALIZERS;

    static {
        GRAHAM_INITIALIZERS = new HashMap<>();
        GRAHAM_INITIALIZERS.put(1, (line, grahamModel) -> grahamModel.setEps(line.split(DELIMITER)[1]));
        GRAHAM_INITIALIZERS.put(2, (line, grahamModel) -> grahamModel.setEpsGrowthRate(line.split(DELIMITER)[1]));
    }

    @Override
    public GrahamDataModel read(Company company, CompanySector companySector) throws IOException {
        final InputStream inputStream = getInputStreamCompanyFile(company, companySector, getFileName());
        final GrahamDataModel grahamDataModel = new GrahamDataModel();
        return FileReaderUtil.mapFileToModel(inputStream, grahamDataModel, GRAHAM_INITIALIZERS);
    }

    @Override
    public String getFileName() {
        return FILE_NAME;
    }
}
