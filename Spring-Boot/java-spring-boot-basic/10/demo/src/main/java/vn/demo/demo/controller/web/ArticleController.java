package vn.demo.demo.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.demo.demo.entity.Article;
import vn.demo.demo.service.ArticleService;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    // Trang chủ cho bài viết
    @GetMapping("/home")
    public String home(Model model) {
        List<Article> latestArticles = articleService.getLatestArticles(5);
        model.addAttribute("latestArticles", latestArticles);
        return "home";  // view: home.html
    }

    // Danh sách bài viết: phân trang, chỉ hiển thị bài có status = true
    @GetMapping("")
    public String articleList(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              Model model) {
        Page<Article> articlePage = articleService.getArticles(page, pageSize);
        model.addAttribute("articlePage", articlePage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", articlePage.getTotalPages());
        return "articles";  // view: articles.html
    }

    // Chi tiết bài viết
    @GetMapping("/article/{id}/{slug}")
    public String articleDetail(@PathVariable Integer id,
                                @PathVariable String slug,
                                Model model) {
        Article article = articleService.getArticleById(id);
        if (article == null) {
            return "404"; // trang lỗi nếu không tìm thấy bài viết
        }
        model.addAttribute("article", article);
        return "article-detail";  // view: article-detail.html
    }
}
