package vn.demo.demo.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EpisodeRequest {

    @NotNull(message = "movieId không được để trống")
    private Integer movieId;

    @NotBlank(message = "Tên tập phim không được để trống")
    private String name;

    @NotNull(message = "Thời lượng không được để trống")
    @Min(value = 1, message = "Thời lượng phải lớn hơn 0 phút")
    private Integer duration;

    @NotNull(message = "Thứ tự hiển thị không được để trống")
    @Min(value = 1, message = "Thứ tự hiển thị phải >= 1")
    private Integer displayOrder;

    @NotBlank(message = "URL video không được để trống")
    private String videoUrl;

    @NotNull(message = "Trạng thái không được để trống")
    private Boolean status;

    private LocalDateTime publishedAt;
}
