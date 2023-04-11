package repository;

import model.Company;

import java.io.IOException;

public interface FileReader<R> {

    R read(Company company) throws IOException;
}
