package vn.demo.demo.utils.file;

import vn.demo.demo.model.Product;

import java.util.List;

public interface IFileReader {
    List<Product> readFile(String path);

}
