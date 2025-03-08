package vn.demo.demo.repository;

import org.springframework.stereotype.Repository;
import vn.demo.demo.model.Product;
import vn.demo.demo.utils.file.IFileReader;
import vn.demo.demo.utils.file.JsonFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {
    private final List<Product> products = new ArrayList<>();

    // Thay đổi: Thêm IFileReader
    private final IFileReader fileReader;

    // Constructor
    public ProductRepository() {
        this.fileReader = new JsonFileReader();
        loadProducts();
    }

    // Gọi fileReader để đọc file JSON
    private void loadProducts() {
        String path = "src/main/resources/products.json";
        List<Product> productList = fileReader.readFile(path);

        products.clear();
        products.addAll(productList);
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Tìm kiếm sản phẩm
    public List<Product> searchProducts(String keyword) {
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase())
                        || p.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Lọc sản phẩm
    public List<Product> filterProducts(Integer minPrice, Integer maxPrice,
                                        Double minRating, Double maxRating) {
        return products.stream()
                .filter(p -> (minPrice == null || p.getPrice() >= minPrice))
                .filter(p -> (maxPrice == null || p.getPrice() <= maxPrice))
                .filter(p -> (minRating == null || p.getRating() >= minRating))
                .filter(p -> (maxRating == null || p.getRating() <= maxRating))
                .collect(Collectors.toList());
    }

    // Phân trang
    public List<Product> getProductsByPage(int page, int size) {
        int skip = (page - 1) * size;
        return products.stream()
                .skip(skip)
                .limit(size)
                .collect(Collectors.toList());
    }
}
