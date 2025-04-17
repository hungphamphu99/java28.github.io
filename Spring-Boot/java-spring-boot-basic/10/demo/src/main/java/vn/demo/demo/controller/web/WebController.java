package vn.demo.demo.controller.web;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.demo.demo.entity.Episode;
import vn.demo.demo.entity.Favorite;
import vn.demo.demo.entity.Movie;
import vn.demo.demo.entity.Review;
import vn.demo.demo.model.dto.UserDTO;
import vn.demo.demo.model.enums.MovieType;
import vn.demo.demo.model.request.LoginRequest;
import vn.demo.demo.service.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final MovieService movieService;
    private final ReviewService reviewService;
    private final EpisodeService episodeService;
    private final FavoriteService favoriteService;
    private final AuthService authService;

    @GetMapping("/")
    public String getHomePage() {
        return "web/index";
    }

    @GetMapping("/phim-bo")
    public String getPhimBoPage(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "18") Integer pageSize,
                                Model model) {
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_BO, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", moviePage.getTotalPages());
        return "web/phim-bo";
    }

    @GetMapping("/phim-le")
    public String getPhimLePage(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "18") Integer pageSize,
                                Model model) {
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_LE, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", moviePage.getTotalPages());
        return "web/phim-le";
    }

    @GetMapping("/phim-chieu-rap")
    public String getPhimChieuRapPage(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "18") Integer pageSize,
                                      Model model) {
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_CHIEU_RAP, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", moviePage.getTotalPages());
        return "web/phim-chieu-rap";
    }

    @GetMapping("/phim/{id}/{slug}")
    public String getMovieDetailsPage(@PathVariable Integer id,
                                      @PathVariable String slug,
                                      @RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "5") int pageSize,
                                      Model model,
                                      HttpSession session) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            return "404";
        }

        // Lấy danh sách phim liên quan
        List<Movie> relatedMovies = movieService.findRelatedMovies(movie.getType(), id, 6);

        // Lấy Page<Review> cho phần bình luận
        Page<Review> reviewsPage = reviewService.getReviewsByMovie(id, page, pageSize);

        // Nếu là phim bộ, lấy danh sách tập
        if ("PHIM_BO".equals(movie.getType().name())) {
            List<Episode> episodes = episodeService.getEpisodesByMovieId(id);
            model.addAttribute("episodes", episodes);
        }

        // Lấy danh sách favorites nếu đã đăng nhập
        UserDTO currentUser = (UserDTO) session.getAttribute("currentUser");
        if (currentUser != null) {
            List<Favorite> favorites = favoriteService.getFavoritesBySession(session);
            ;
            model.addAttribute("favorites", favorites);
        }

        model.addAttribute("movie", movie);
        model.addAttribute("relatedMovies", relatedMovies);
        model.addAttribute("reviewsPage", reviewsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", reviewsPage.getTotalPages());


        return "web/chi-tiet-phim";
    }



    @GetMapping("/xem-phim/{movieId}/{episodeNumber}")
    public String getXemPhimPage(@PathVariable Integer movieId,
                                 @PathVariable Integer episodeNumber,
                                 Model model) {
        // Tải phim theo movieId
        Movie movie = movieService.findById(movieId);
        if (movie == null) {
            return "404"; // Hoặc chuyển hướng đến trang lỗi 404
        }

        Episode episode = null;
        // Nếu phim là phim bộ, xử lý lấy tập và danh sách tập
        if (movie.getType() == MovieType.PHIM_BO) {
            // Lấy danh sách các tập của phim (ví dụ: chỉ lấy những tập có status true, sắp xếp theo displayOrder)
            List<Episode> episodes = episodeService.getEpisodesByMovieId(movieId);
            // Tìm tập có displayOrder bằng với episodeNumber
            episode = episodes.stream()
                    .filter(ep -> ep.getDisplayOrder().equals(episodeNumber))
                    .findFirst()
                    .orElse(null);
            if (episode == null) {
                // Nếu không tìm thấy tập nào với số episodeNumber và danh sách không rỗng, chuyển hướng về tập đầu tiên
                if (!episodes.isEmpty()) {
                    return "redirect:/xem-phim/" + movieId + "/" + episodes.get(0).getDisplayOrder();
                } else {
                    // Nếu danh sách tập rỗng, trả về trang lỗi
                    return "404";
                }
            }
            model.addAttribute("episodes", episodes);
        } else {
            // Nếu phim không phải
            // phim bộ, giả sử rằng video được lưu trong trường trailer của Movie
            episode = new Episode();
            episode.setDisplayOrder(1);
            episode.setVideoUrl(movie.getTrailer());
        }

        model.addAttribute("movie", movie);
        model.addAttribute("episode", episode);
        return "web/xem-phim";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "web/login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {
        try {
            LoginRequest request = new LoginRequest(email, password);
            authService.login(request); // hoặc dùng biến nếu bạn cần: UserDTO user = authService.login(request);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", "Email hoặc mật khẩu sai!");
            return "web/login";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        authService.logout();
        return "redirect:/";
    }

    @GetMapping("/my-profile")
    public String myProfile(HttpSession session, Model model) {
        if (session.getAttribute("currentUser") == null) {
            return "redirect:/login";
        }
        return "web/my-profile";
    }

    @GetMapping("/phim-yeu-thich")
    public String favoritePage(Model model, HttpSession session) {
        List<Favorite> favorites = favoriteService.getFavoritesBySession(session); // ✅ dùng hàm trên
        model.addAttribute("favorites", favorites);
        return "web/favorite";
    }


    @GetMapping("/admin")
    public String getAdminPage(HttpSession session) {
        UserDTO currentUser = (UserDTO) session.getAttribute("currentUser");
        if (currentUser == null || !currentUser.getRole().name().equals("ADMIN")) {
            return "redirect:/login"; // hoặc trả về 403
        }
        return "admin/dashboard"; // Giao diện quản trị
    }



}
