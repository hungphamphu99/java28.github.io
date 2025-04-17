package vn.demo.demo.api;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.demo.demo.entity.Episode;
import vn.demo.demo.model.dto.UserDTO;
import vn.demo.demo.model.enums.UserRole;
import vn.demo.demo.model.request.EpisodeRequest;
import vn.demo.demo.service.EpisodeService;
import jakarta.validation.Valid;


import java.util.List;

@RestController
@RequestMapping("/api/admin/episodes")
@RequiredArgsConstructor
public class EpisodeApi {
    private final EpisodeService episodeService;

    private boolean isNotAdmin(HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("currentUser");
        return user == null || user.getRole() != UserRole.ADMIN;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody EpisodeRequest request, HttpSession session) {
        if (isNotAdmin(session)) {
            return ResponseEntity.status(403).body("Bạn không có quyền tạo tập phim");
        }
        return ResponseEntity.ok(episodeService.create(request));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Integer id,
            @Valid @RequestBody EpisodeRequest request,
            HttpSession session) {

        if (isNotAdmin(session)) {
            return ResponseEntity.status(403).body("Bạn không có quyền cập nhật tập phim");
        }

        try {
            return ResponseEntity.ok(episodeService.update(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Không tìm thấy tập phim với id = " + id);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id, HttpSession session) {
        if (isNotAdmin(session)) {
            return ResponseEntity.status(403).body("Bạn không có quyền xoá tập phim");
        }

        try {
            episodeService.delete(id);
            return ResponseEntity.ok("Đã xoá thành công");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }


    @GetMapping("/movie/{movieId}")
    public List<Episode> getEpisodesByMovie(@PathVariable Integer movieId) {
        return episodeService.findByMovieId(movieId); // Không cần check admin để xem danh sách
    }
}
