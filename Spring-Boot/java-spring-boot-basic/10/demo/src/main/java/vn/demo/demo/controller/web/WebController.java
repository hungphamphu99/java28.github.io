package vn.demo.demo.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import vn.demo.demo.entity.Movie;
import vn.demo.demo.model.enums.MovieType;
import vn.demo.demo.service.MovieService;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final MovieService movieService;

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
                                      Model model) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            return "404"; // or an error page
        }
        // Retrieve 6 related movies (same type, status true, exclude the current movie)
        List<Movie> relatedMovies = movieService.findRelatedMovies(movie.getType(), id, 6);
        model.addAttribute("movie", movie);
        model.addAttribute("relatedMovies", relatedMovies);
        return "chi-tiet-phim";
    }
}
