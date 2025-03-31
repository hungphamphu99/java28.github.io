package vn.demo.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.demo.demo.entity.Favorite;
import vn.demo.demo.entity.Movie;
import vn.demo.demo.entity.User;
import vn.demo.demo.repository.FavoriteRepository;
import vn.demo.demo.repository.MovieRepository;
import vn.demo.demo.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;


    public Page<Favorite> getFavorites(int userId, int page, int pageSize) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by("createdAt").descending());

        return favoriteRepository.findAllByUser(user, pageRequest);
    }


    public Favorite addFavorite(int userId, int movieId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Optional<Favorite> existing = favoriteRepository.findByUserAndMovie(user, movie);
        if (existing.isPresent()) {
            return existing.get();
        }

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setMovie(movie);
        favorite.setCreatedAt(LocalDateTime.now());

        return favoriteRepository.save(favorite);
    }


    public void removeFavorite(int userId, int movieId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Optional<Favorite> existing = favoriteRepository.findByUserAndMovie(user, movie);
        existing.ifPresent(favoriteRepository::delete);
    }


    public void removeAllFavorites(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Favorite> favorites = favoriteRepository.findAllByUser(user);
        favoriteRepository.deleteAll(favorites);
    }
    public List<Favorite> getFavoritesByUserId(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return favoriteRepository.findAllByUser(user);
    }

}
