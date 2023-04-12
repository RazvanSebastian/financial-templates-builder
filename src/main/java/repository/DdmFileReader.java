package repository;

import model.Company;
import model.CompanySector;
import model.DdmDataModel;
import util.BiConsumer;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static repository.FileReaderUtil.DELIMITER;
import static repository.FileReaderUtil.getInputStreamCompanyFile;

public class DdmFileReader implements FileReader<DdmDataModel> {
    private static final String FILE_NAME = "ddm.txt";

    /**
     * Key - row index
     * Value - list of values related to DCF attributes
     */
    private static final Map<Integer, BiConsumer<String, DdmDataModel>> DDM_INITIALIZERS;

    static {
        DDM_INITIALIZERS = new HashMap<>();
        DDM_INITIALIZERS.put(1, (line, ddmDataModel) -> ddmDataModel.setDividendPerShare(line.split(DELIMITER)[1]));
        DDM_INITIALIZERS.put(2, (line, ddmDataModel) -> ddmDataModel.setConservativeGrow(line.split(DELIMITER)[1]));
        DDM_INITIALIZERS.put(3, (line, ddmDataModel) -> ddmDataModel.setNormalGrow(line.split(DELIMITER)[1]));
        DDM_INITIALIZERS.put(4, (line, ddmDataModel) -> ddmDataModel.setHighGrow(line.split(DELIMITER)[1]));
    }

    @Override
    public DdmDataModel read(Company company, CompanySector companySector) throws IOException {
        final InputStream inputStream = getInputStreamCompanyFile(company, companySector, getFileName());
        final DdmDataModel ddmDataModel = new DdmDataModel();
        return FileReaderUtil.mapFileToModel(inputStream, ddmDataModel, DDM_INITIALIZERS);
    }

    @Override
    public String getFileName() {
        return FILE_NAME;
    }
}
