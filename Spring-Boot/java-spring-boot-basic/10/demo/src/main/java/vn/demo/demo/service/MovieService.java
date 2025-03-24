package vn.demo.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.demo.demo.entity.Movie;
import vn.demo.demo.model.enums.MovieType;
import vn.demo.demo.repository.MovieRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public Page<Movie> findByType(MovieType type, Boolean status, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("publishedAt").descending());
        return movieRepository.findByTypeAndStatus(type, status, pageable);
    }

    public Movie findById(Integer id) {
        return movieRepository.findById(id).orElse(null);
    }

    public List<Movie> findRelatedMovies(MovieType type, Integer movieId, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return movieRepository.findRelatedMovies(type, movieId, pageable);
    }
}
