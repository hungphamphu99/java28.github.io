package vn.demo.demo.api;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.demo.demo.entity.Movie;
import vn.demo.demo.model.dto.UserDTO;
import vn.demo.demo.model.enums.UserRole;
import vn.demo.demo.model.request.MovieRequest;
import vn.demo.demo.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/movies")
@RequiredArgsConstructor
public class MovieApi {
    private final MovieService movieService;

    private boolean isNotAdmin(HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("currentUser");
        return user == null || user.getRole() != UserRole.ADMIN;
    }

    @GetMapping
    public ResponseEntity<?> getAllMovies(
            HttpSession session,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        if (isNotAdmin(session)) {
            return ResponseEntity.status(403).body("Bạn không có quyền truy cập");
        }

        return ResponseEntity.ok(movieService.findAllPaginated(page, pageSize));
    }


    @PostMapping
    public ResponseEntity<?> createMovie(@Valid @RequestBody MovieRequest request, HttpSession session) {
        if (isNotAdmin(session)) {
            return ResponseEntity.status(403).body("Bạn không có quyền truy cập");
        }
        return ResponseEntity.ok(movieService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Integer id,
                                         @Valid @RequestBody MovieRequest request,
                                         HttpSession session) {
        if (isNotAdmin(session)) {
            return ResponseEntity.status(403).body("Bạn không có quyền truy cập");
        }

        try {
            return ResponseEntity.ok(movieService.update(id, request));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body("Không tìm thấy phim có ID: " + id);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Integer id, HttpSession session) {
        if (isNotAdmin(session)) {
            return ResponseEntity.status(403).body("Bạn không có quyền truy cập");
        }

        try {
            movieService.delete(id);
            return ResponseEntity.ok("Đã xoá thành công phim có ID: " + id);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body("Không tìm thấy phim có ID: " + id);
        }
    }



}
