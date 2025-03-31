package vn.demo.demo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.demo.demo.entity.Favorite;
import vn.demo.demo.service.FavoriteService;
import vn.demo.demo.model.request.AddFavoriteRequest;
import vn.demo.demo.model.request.RemoveFavoriteRequest;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteApi {

    private final FavoriteService favoriteService;


    @GetMapping
    public ResponseEntity<?> getFavorites(@RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "10") int pageSize) {
        int userId = 1;

        Page<Favorite> favoritePage = favoriteService.getFavorites(userId, page, pageSize);
        return ResponseEntity.ok(favoritePage);
    }


    @PostMapping("/add")
    public ResponseEntity<?> addFavorite(@RequestBody AddFavoriteRequest request) {
        int userId = 1;
        Favorite favorite = favoriteService.addFavorite(userId, request.getMovieId());
        return ResponseEntity.ok(favorite);
    }


    @DeleteMapping("/remove")
    public ResponseEntity<?> removeFavorite(@RequestBody RemoveFavoriteRequest request) {
        int userId = 1;
        favoriteService.removeFavorite(userId, request.getMovieId());
        return ResponseEntity.ok("Movie removed from favorites");
    }

    @DeleteMapping("/removeAll")
    public ResponseEntity<?> removeAllFavorites() {
        int userId = 1;
        favoriteService.removeAllFavorites(userId);
        return ResponseEntity.ok("All favorites removed");
    }
}
