package vn.demo.demo.model.request;

import jakarta.validation.constraints.*;
import lombok.*;
import vn.demo.demo.model.enums.MovieType;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieRequest {

    @NotBlank(message = "Tên phim không được để trống")
    private String name;

    @NotBlank(message = "Slug không được để trống")
    private String slug;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    @NotBlank(message = "Ảnh thumbnail không được để trống")
    private String thumbnail;

    private String trailer;

    @NotNull(message = "Năm phát hành không được để trống")
    @Min(value = 1900, message = "Năm phát hành không hợp lệ")
    private Integer releaseYear;

    @NotNull(message = "Loại phim không được để trống")
    private MovieType type;

    @NotNull(message = "Trạng thái không được để trống")
    private Boolean status;

    @NotNull(message = "Ngày phát hành không được để trống")
    private LocalDateTime publishedAt;
}
