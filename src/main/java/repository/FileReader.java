package repository;

import model.Company;
import model.CompanySector;

import java.io.IOException;

public interface FileReader<R> {

    R read(Company company, CompanySector companySector) throws IOException;

    String getFileName();
}
