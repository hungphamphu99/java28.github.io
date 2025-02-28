package vn.demo.hw.service;

import org.springframework.stereotype.Service;
import vn.demo.hw.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>();

    // Constructor: Tạo một số sản phẩm mẫu
    public ProductService() {
        products.add(new Product("1", "iPhone 15", "The latest iPhone", 999, "Apple"));
        products.add(new Product("2", "Galaxy S24", "Samsung flagship", 899, "Samsung"));
        products.add(new Product("3", "iPhone 14", "New iPhone with 5G", 1099, "Apple"));
        products.add(new Product("4", "MacBook Pro", "Apple's laptop", 2500, "Apple"));
        products.add(new Product("5", "Galaxy S24 FE", "Samsung flagship", 1199, "Samsung"));
    }

    // API 1: Lấy thông tin chi tiết của một sản phẩm
    public Product getProductById(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    // API 2: Lấy sản phẩm với tên bắt đầu bằng prefix nào đó
    public List<Product> getProductsByNamePrefix(String prefix) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().startsWith(prefix)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    // API 3: Lọc sản phẩm theo khoảng giá
    public List<Product> getProductsByPriceRange(int min, int max) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getPrice() >= min && product.getPrice() <= max) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    // API 4: Lấy sản phẩm theo thương hiệu
    public List<Product> getProductsByBrand(String brand) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getBrand().equalsIgnoreCase(brand)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    // API 5: Lấy sản phẩm có giá cao nhất theo thương hiệu
    public Product getProductWithMaxPrice(String brand) {
        Product maxPriceProduct = null;
        for (Product product : products) {
            if (product.getBrand().equalsIgnoreCase(brand)) {
                if (maxPriceProduct == null || product.getPrice() > maxPriceProduct.getPrice()) {
                    maxPriceProduct = product;
                }
            }
        }
        return maxPriceProduct;
    }
}
