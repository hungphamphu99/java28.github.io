package vn.demo.demo.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.demo.demo.entity.Movie;
import vn.demo.demo.service.MovieService;


@Controller
@RequestMapping("/admin/movies")
@RequiredArgsConstructor
public class AdminMovieController {
    private final MovieService movieService;



   @GetMapping
    public String showMovieList(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "10") int pageSize,
                                Model model) {
        Page<Movie> moviePage = movieService.findAllPaginated(page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        return "admin/movie-list";
    }

}
