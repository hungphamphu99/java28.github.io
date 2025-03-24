package vn.demo.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.demo.demo.entity.Article;
import vn.demo.demo.repository.ArticleRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    // Lấy n bài viết mới nhất (cho trang chủ)
    public List<Article> getLatestArticles(int limit) {
        Pageable pageable = PageRequest.of(0, limit, Sort.by("publishedAt").descending());
        return articleRepository.findLatestArticles(pageable);
    }

    // Lấy danh sách bài viết với phân trang
    public Page<Article> getArticles(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("publishedAt").descending());
        return articleRepository.findByStatusTrueOrderByPublishedAtDesc(pageable);
    }

    // Lấy chi tiết bài viết theo id
    public Article getArticleById(Integer id) {
        return articleRepository.findById(id).orElse(null);
    }
}
