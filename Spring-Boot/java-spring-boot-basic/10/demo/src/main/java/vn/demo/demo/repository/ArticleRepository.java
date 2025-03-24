package vn.demo.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.demo.demo.entity.Article;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    // Lấy danh sách bài viết mới nhất (cho trang chủ), giới hạn số lượng theo Pageable
    @Query("SELECT a FROM Article a WHERE a.status = true ORDER BY a.publishedAt DESC")
    List<Article> findLatestArticles(Pageable pageable);

    // Lấy danh sách bài viết có phân trang (chỉ lấy những bài có status = true)
    Page<Article> findByStatusTrueOrderByPublishedAtDesc(Pageable pageable);
}
