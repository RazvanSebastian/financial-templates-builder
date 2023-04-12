package repository;

import model.Company;
import model.CompanySector;
import model.dcf.WaccModel;
import util.BiConsumer;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static repository.FileReaderUtil.DELIMITER;
import static repository.FileReaderUtil.getInputStreamCompanyFile;

public class WaccFileReader implements FileReader<WaccModel> {
    private static final String FILE_NAME = "wacc.txt";

    /**
     * Key - row index
     * Value - list of values related to wacc attribute
     */
    private static final Map<Integer, BiConsumer<String, WaccModel>> WACC_INITIALIZERS;

    static {
        WACC_INITIALIZERS = new HashMap<>();
        WACC_INITIALIZERS.put(1, (line, waccModel) -> waccModel.setMarketCap(line.split(DELIMITER)[1]));
        WACC_INITIALIZERS.put(2, (line, waccModel) -> waccModel.setCurrentLiabilities(line.split(DELIMITER)[1]));
        WACC_INITIALIZERS.put(3, (line, waccModel) -> waccModel.setNonCurrentLiabilities(line.split(DELIMITER)[1]));
        WACC_INITIALIZERS.put(4, (line, waccModel) -> waccModel.setInterestExpenses(line.split(DELIMITER)[1]));
        WACC_INITIALIZERS.put(5, (line, waccModel) -> waccModel.setPretaxIncome(line.split(DELIMITER)[1]));
        WACC_INITIALIZERS.put(6, (line, waccModel) -> waccModel.setTaxProvision(line.split(DELIMITER)[1]));
        WACC_INITIALIZERS.put(7, (line, waccModel) -> waccModel.setBeta(line.split(DELIMITER)[1]));
    }

    @Override
    public WaccModel read(Company company, CompanySector companySector) throws IOException {
        final InputStream inputStream = getInputStreamCompanyFile(company, companySector, getFileName());
        final WaccModel waccModel = new WaccModel();
        return FileReaderUtil.mapFileToModel(inputStream, waccModel, WACC_INITIALIZERS);
    }

    @Override
    public String getFileName() {
        return FILE_NAME;
    }
}
