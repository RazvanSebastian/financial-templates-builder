package repository;

import model.Company;
import model.dcf.DcfModel;
import util.BiSupplier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DcfFileReader implements FileReader<DcfModel> {
    private static final String DELIMITER = ",";

    /**
     * Key - row index
     * Value - list of values related to DCF attributes
     */
    private static final Map<Integer, BiSupplier<String, DcfModel>> DCF_INITIALIZERS;

    static {
        DCF_INITIALIZERS = new HashMap<>();
        DCF_INITIALIZERS.put(1, (line, dcfModel) -> dcfModel.setRevenueGrowth(line.split(DELIMITER)[1]));
        DCF_INITIALIZERS.put(2, (line, dcfModel) -> dcfModel.setEbidtaMultiple(line.split(DELIMITER)[1]));
        DCF_INITIALIZERS.put(3, (line, dcfModel) -> dcfModel.setEbidtaGrowthRate(line.split(DELIMITER)[1]));
        DCF_INITIALIZERS.put(4, (line, dcfModel) -> dcfModel.setNumberOfShares(line.split(DELIMITER)[1]));
        DCF_INITIALIZERS.put(5, (line, dcfModel) -> {
            List<String> estimates = List.of(line.split(DELIMITER));
            dcfModel.setRevenueEstimations(estimates.subList(1, estimates.size()));
        });
    }


    @Override
    public DcfModel read(Company company) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("companies/" + company.getDirectoryName() + "/dcf.txt");

        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            final DcfModel dcfModel = new DcfModel();

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            int lineIndex = 0;

            while ((line = bufferedReader.readLine()) != null) {
                lineIndex++;
                DCF_INITIALIZERS.get(lineIndex).apply(line, dcfModel);
            }

            return dcfModel;
        }
    }
}
