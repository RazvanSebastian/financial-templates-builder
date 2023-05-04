package repository;

import model.Company;
import model.CompanySector;
import model.dcf.DcfModel;
import util.BiConsumer;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static repository.FileReaderUtil.DELIMITER;
import static repository.FileReaderUtil.getInputStreamCompanyFile;

public class DcfFileReader implements FileReader<DcfModel> {
    private static final String FILE_NAME = "dcf.txt";

    /**
     * Key - row index
     * Value - list of values related to DCF attributes
     */
    private static final Map<Integer, BiConsumer<String, DcfModel>> DCF_INITIALIZERS;

    static {
        DCF_INITIALIZERS = new HashMap<>();
        DCF_INITIALIZERS.put(1, (line, dcfModel) -> dcfModel.setRevenueGrowth(line.split(DELIMITER)[1]));
        DCF_INITIALIZERS.put(2, (line, dcfModel) -> dcfModel.setEbidtaMultiple(line.split(DELIMITER)[1]));
        DCF_INITIALIZERS.put(3, (line, dcfModel) -> dcfModel.setEbidtaGrowthRate(line.split(DELIMITER)[1]));
        DCF_INITIALIZERS.put(4, (line, dcfModel) -> dcfModel.setNumberOfShares(line.split(DELIMITER)[1]));
        DCF_INITIALIZERS.put(5, (line, dcfModel) -> {
            List<String> revenueEstimates = List.of(line.split(DELIMITER));
            dcfModel.setRevenueEstimations(revenueEstimates.subList(1, revenueEstimates.size()));
        });
        DCF_INITIALIZERS.put(6, (line, dcfModel) -> {
            List<String> ebidtaEtimates = List.of(line.split(DELIMITER));
            dcfModel.setEbidtaEstimations(ebidtaEtimates.subList(1, ebidtaEtimates.size()));
        });
    }

    @Override
    public DcfModel read(Company company, CompanySector companySector) throws IOException {
        final InputStream inputStream = getInputStreamCompanyFile(company, companySector, getFileName());
        final DcfModel dcfModel = new DcfModel();
        return FileReaderUtil.mapFileToModel(inputStream, dcfModel, DCF_INITIALIZERS);
    }

    @Override
    public String getFileName() {
        return FILE_NAME;
    }
}
