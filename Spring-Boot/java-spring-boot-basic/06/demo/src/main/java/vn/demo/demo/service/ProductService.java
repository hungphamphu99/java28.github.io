package vn.demo.demo.service;

import org.springframework.stereotype.Service;
import vn.demo.demo.model.Product;
import vn.demo.demo.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id);
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.searchProducts(keyword);
    }

    public List<Product> filterProducts(Integer minPrice, Integer maxPrice,
                                        Double minRating, Double maxRating) {
        return productRepository.filterProducts(minPrice, maxPrice, minRating, maxRating);
    }

    public List<Product> getProductsByPage(int page, int size) {
        return productRepository.getProductsByPage(page, size);
    }
}
