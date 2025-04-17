package vn.demo.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.demo.demo.entity.Movie;
import vn.demo.demo.model.enums.MovieType;
import vn.demo.demo.model.request.MovieRequest;
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

    public List<Movie> findAll() {
        return movieRepository.findAll(Sort.by(Sort.Direction.DESC, "publishedAt"));
    }

    public Movie create(MovieRequest request) {
        Movie movie = Movie.builder()
                .name(request.getName())
                .slug(request.getSlug())
                .description(request.getDescription())
                .thumbnail(request.getThumbnail())
                .trailer(request.getTrailer())
                .releaseYear(request.getReleaseYear())
                .type(request.getType())
                .status(request.getStatus())
                .publishedAt(request.getPublishedAt())
                .build();
        return movieRepository.save(movie);
    }

    public Movie update(Integer id, MovieRequest request) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phim"));

        movie.setName(request.getName());
        movie.setSlug(request.getSlug());
        movie.setDescription(request.getDescription());
        movie.setThumbnail(request.getThumbnail());
        movie.setTrailer(request.getTrailer());
        movie.setReleaseYear(request.getReleaseYear());
        movie.setType(request.getType());
        movie.setStatus(request.getStatus());
        movie.setPublishedAt(request.getPublishedAt());

        return movieRepository.save(movie);
    }


    public void delete(Integer id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phim"));

        movieRepository.delete(movie);
    }


    public Page<Movie> findAllPaginated(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("publishedAt").descending());
        return movieRepository.findAll(pageable);
    }

}
