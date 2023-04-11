package repository;

import model.Company;
import model.dcf.WaccModel;
import util.BiSupplier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class WaccFileReader implements FileReader<WaccModel> {
    private static final String DELIMITER = ",";

    /**
     * Key - row index
     * Value - list of values related to wacc attribute
     */
    private static final Map<Integer, BiSupplier<String, WaccModel>> WACC_INITIALIZERS;

    static {
        WACC_INITIALIZERS = new HashMap<>();
        WACC_INITIALIZERS.put(1, (value, waccModel) -> waccModel.setMarketCap(value));
        WACC_INITIALIZERS.put(2, (value, waccModel) -> waccModel.setCurrentLiabilities(value));
        WACC_INITIALIZERS.put(3, (value, waccModel) -> waccModel.setNonCurrentLiabilities(value));
        WACC_INITIALIZERS.put(4, (value, waccModel) -> waccModel.setInterestExpenses(value));
        WACC_INITIALIZERS.put(5, (value, waccModel) -> waccModel.setPretaxIncome(value));
        WACC_INITIALIZERS.put(6, (value, waccModel) -> waccModel.setTaxProvision(value));
        WACC_INITIALIZERS.put(7, (value, waccModel) -> waccModel.setBeta(value));
    }

    @Override
    public WaccModel read(Company company) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("companies/" + company.getDirectoryName() + "/wacc.txt");

        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            final WaccModel waccModel = new WaccModel();

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            int lineIndex = 0;

            while ((line = bufferedReader.readLine()) != null) {
                lineIndex++;
                String value = line.split(DELIMITER)[1];

                WACC_INITIALIZERS.get(lineIndex).apply(value, waccModel);
            }
            return waccModel;
        }
    }
}
