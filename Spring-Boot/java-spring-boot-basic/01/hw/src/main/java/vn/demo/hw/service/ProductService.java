package vn.demo.hw.service;

import org.springframework.stereotype.Service;
import vn.demo.hw.model.Product;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {
    private final List<Product> products = Arrays.asList(
            new Product("P001", "iPhone 13", "Apple flagship phone", 999, "Apple"),
            new Product("P002", "Samsung Galaxy S22", "Samsung high-end phone", 850, "Samsung"),
            new Product("P003", "MacBook Air M2", "Apple laptop with M2 chip", 1200, "Apple"),
            new Product("P004", "Dell XPS 13", "High-performance laptop", 1100, "Dell"),
            new Product("P005", "Sony WH-1000XM4", "Noise-cancelling headphones", 350, "Sony"),
            new Product("P006", "iPad Pro", "Apple tablet with M2 chip", 1300, "Apple"),
            new Product("P007", "Xiaomi Mi 11", "High-end Xiaomi phone", 700, "Xiaomi"),
            new Product("P008", "Google Pixel 7", "Google's flagship phone", 899, "Google"),
            new Product("P009", "LG OLED TV", "High-quality OLED TV", 1500, "LG"),
            new Product("P010", "Bose QC 45", "Premium noise-cancelling headphones", 399, "Bose")
    );

    public List<Product> getLimitedProducts(int limit) {
        return products.subList(0, Math.min(limit, products.size()));
    }
}
