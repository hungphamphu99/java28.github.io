package vn.demo.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.demo.demo.model.Product;
import vn.demo.demo.service.ProductService;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;
    private static final int PAGE_SIZE = 9;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Trang chủ: hiển thị danh sách sản phẩm phân trang
    @GetMapping("/")
    public String getProducts(@RequestParam(name = "page", defaultValue = "1") int page,
                              Model model) {
        // Lấy sản phẩm trang hiện tại
        List<Product> products = productService.getProductsByPage(page, PAGE_SIZE);
        model.addAttribute("products", products);
        model.addAttribute("page", page);

        // Tính tổng số trang
        int totalProducts = productService.getAllProducts().size();
        int totalPages = (int) Math.ceil(totalProducts * 1.0 / PAGE_SIZE);
        model.addAttribute("totalPages", totalPages);

        return "index";
    }

    // Chi tiết sản phẩm
    @GetMapping("/products/{id}")
    public String getProductDetail(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/"; // Không tìm thấy => quay về trang chủ
        }
        model.addAttribute("product", product);
        return "product-detail";
    }

    // Tìm kiếm theo từ khóa
    @GetMapping("/products/search")
    public String searchProducts(
            @RequestParam("keyword") String keyword,
            @RequestParam(name = "page", defaultValue = "1") int page,
            Model model
    ) {
        // 1. Tìm kiếm
        List<Product> allResults = productService.searchProducts(keyword);

        // 2. Tính totalPages
        int totalProducts = allResults.size();
        int PAGE_SIZE = 9; // hoặc hằng số
        int totalPages = (int) Math.ceil(totalProducts * 1.0 / PAGE_SIZE);

        // 3. Lấy danh sách cho trang hiện tại (skip/limit)
        int skip = (page - 1) * PAGE_SIZE;
        List<Product> productsForPage = allResults.stream()
                .skip(skip)
                .limit(PAGE_SIZE)
                .toList();

        // 4. Đưa vào model
        model.addAttribute("products", productsForPage);
        model.addAttribute("page", page);
        model.addAttribute("totalPages", (totalPages == 0) ? 1 : totalPages);

        return "index";
    }


    // Lọc theo giá & rating
    @GetMapping("/products/filter")
    public String filterProducts(
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Double maxRating,
            @RequestParam(name = "page", defaultValue = "1") int page,
            Model model) {

        // 1. Lọc sản phẩm trước
        List<Product> filteredList = productService.filterProducts(minPrice, maxPrice, minRating, maxRating);

        // 2. Tính tổng số trang dựa trên số sản phẩm đã lọc
        int totalProducts = filteredList.size();
        int totalPages = (int) Math.ceil(totalProducts * 1.0 / PAGE_SIZE);

        // 3. Lấy sản phẩm cho trang hiện tại
        //    skip = (page - 1) * 9, limit = 9
        int skip = (page - 1) * 9;
        List<Product> productsForPage = filteredList.stream()
                .skip(skip)
                .limit(9)
                .toList();

        // 4. Đưa vào model
        model.addAttribute("products", productsForPage);
        model.addAttribute("page", page);
        model.addAttribute("totalPages", (totalPages == 0) ? 1 : totalPages);

        return "index";
    }

}
