package vn.demo.demo.utils.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import vn.demo.demo.model.Product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonFileReader implements IFileReader {

    @Override
    public List<Product> readFile(String path) {
        List<Product> products = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(path);
            if (file.exists()) {
                Product[] productArray = objectMapper.readValue(file, Product[].class);
                products.addAll(List.of(productArray));
            } else {
                System.err.println("File not found: " + path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }
}
