package vn.demo.hw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.demo.hw.entity.Product;
import vn.demo.hw.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // API 1: Lấy thông tin chi tiết của một sản phẩm
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    // API 2: Lấy sản phẩm với tên bắt đầu bằng prefix nào đó
    @GetMapping("/name-starts/{prefix}")
    public ResponseEntity<List<Product>> getProductsByNamePrefix(@PathVariable String prefix) {
        List<Product> products = productService.getProductsByNamePrefix(prefix);
        return ResponseEntity.ok(products);
    }

    // API 3: Lọc sản phẩm theo khoảng giá
    @GetMapping("/price/{min}/{max}")
    public ResponseEntity<List<Product>> getProductsByPriceRange(@PathVariable int min, @PathVariable int max) {
        List<Product> products = productService.getProductsByPriceRange(min, max);
        return ResponseEntity.ok(products);
    }

    // API 4: Lấy sản phẩm theo thương hiệu
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable String brand) {
        List<Product> products = productService.getProductsByBrand(brand);
        return ResponseEntity.ok(products);
    }

    // API 5: Lấy sản phẩm có giá cao nhất theo thương hiệu
    @GetMapping("/brand/{brand}/max-price")
    public ResponseEntity<Product> getProductWithMaxPrice(@PathVariable String brand) {
        Product product = productService.getProductWithMaxPrice(brand);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }
}
