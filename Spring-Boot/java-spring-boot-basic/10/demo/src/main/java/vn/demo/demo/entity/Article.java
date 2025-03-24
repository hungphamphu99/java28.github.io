package vn.demo.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    private String slug;

    @Column(columnDefinition = "TEXT")
    private String content;

    // Chỉ hiển thị bài viết có status = true
    private Boolean status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;
}
