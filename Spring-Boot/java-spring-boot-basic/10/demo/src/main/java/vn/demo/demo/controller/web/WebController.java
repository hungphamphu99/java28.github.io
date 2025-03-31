package vn.demo.demo.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import vn.demo.demo.entity.Episode;
import vn.demo.demo.entity.Favorite;
import vn.demo.demo.entity.Movie;
import vn.demo.demo.entity.Review;
import vn.demo.demo.model.enums.MovieType;
import vn.demo.demo.service.EpisodeService;
import vn.demo.demo.service.FavoriteService;
import vn.demo.demo.service.MovieService;
import vn.demo.demo.service.ReviewService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final MovieService movieService;
    private final ReviewService reviewService;
    private final EpisodeService episodeService;
    private final FavoriteService favoriteService;


    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/phim-bo")
    public String getPhimBoPage(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "18") Integer pageSize,
                                Model model) {
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_BO, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", moviePage.getTotalPages());
        return "phim-bo";
    }

    @GetMapping("/phim-le")
    public String getPhimLePage(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "18") Integer pageSize,
                                Model model) {
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_LE, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", moviePage.getTotalPages());
        return "phim-le";
    }

    @GetMapping("/phim-chieu-rap")
    public String getPhimChieuRapPage(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "18") Integer pageSize,
                                      Model model) {
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_CHIEU_RAP, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", moviePage.getTotalPages());
        return "phim-chieu-rap";
    }

    @GetMapping("/phim/{id}/{slug}")
    public String getMovieDetailsPage(@PathVariable Integer id,
                                      @PathVariable String slug,
                                      @RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "5") int pageSize,
                                      Model model) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            return "404";
        }
        // Lấy danh sách phim liên quan
        List<Movie> relatedMovies = movieService.findRelatedMovies(movie.getType(), id, 6);
        // Lấy Page<Review> cho phần bình luận
        Page<Review> reviewsPage = reviewService.getReviewsByMovie(id, page, pageSize);

        // Nếu là phim bộ (PHIM_BO), lấy danh sách tập
        if ("PHIM_BO".equals(movie.getType().name())) {
            List<Episode> episodes = episodeService.getEpisodesByMovieId(id);
            System.out.println("Tổng số tập: " + episodes.size());
            model.addAttribute("episodes", episodes);
        }

        // Lấy danh sách favorites của user hiện tại (fix userId = 1)
        List<Favorite> favorites = favoriteService.getFavoritesByUserId(1);
        model.addAttribute("favorites", favorites);

        model.addAttribute("movie", movie);
        model.addAttribute("relatedMovies", relatedMovies);
        model.addAttribute("reviewsPage", reviewsPage);
        // Nếu có login, bạn có thể thêm currentUser vào model:
        // model.addAttribute("currentUser", userService.getCurrentUser());

        return "chi-tiet-phim";
    }



}
