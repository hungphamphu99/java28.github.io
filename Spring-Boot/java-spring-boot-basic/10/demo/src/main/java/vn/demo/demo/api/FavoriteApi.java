package vn.demo.demo.api;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.demo.demo.entity.Favorite;
import vn.demo.demo.model.dto.UserDTO;
import vn.demo.demo.model.request.AddFavoriteRequest;
import vn.demo.demo.model.request.RemoveFavoriteRequest;
import vn.demo.demo.service.FavoriteService;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteApi {

    private final FavoriteService favoriteService;

    @GetMapping
    public ResponseEntity<?> getFavorites(@RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "10") int pageSize,
                                          HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("currentUser");
        if (user == null) return ResponseEntity.status(401).body("Bạn chưa đăng nhập");

        Page<Favorite> favoritePage = favoriteService.getFavorites(user.getId(), page, pageSize);
        return ResponseEntity.ok(favoritePage);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFavorite(@RequestBody AddFavoriteRequest request,
                                         HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("currentUser");
        if (user == null) return ResponseEntity.status(401).body("Bạn chưa đăng nhập");

        Favorite favorite = favoriteService.addFavorite(user.getId(), request.getMovieId());
        return ResponseEntity.ok(favorite);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeFavorite(@RequestBody RemoveFavoriteRequest request,
                                            HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("currentUser");
        if (user == null) return ResponseEntity.status(401).body("Bạn chưa đăng nhập");

        favoriteService.removeFavorite(user.getId(), request.getMovieId());
        return ResponseEntity.ok("Đã xoá khỏi danh sách yêu thích");
    }

    @DeleteMapping("/removeAll")
    public ResponseEntity<?> removeAllFavorites(HttpSession session) {
        UserDTO user = (UserDTO) session.getAttribute("currentUser");
        if (user == null) return ResponseEntity.status(401).body("Bạn chưa đăng nhập");

        favoriteService.removeAllFavorites(user.getId());
        return ResponseEntity.ok("Đã xoá toàn bộ danh sách yêu thích");
    }
}
