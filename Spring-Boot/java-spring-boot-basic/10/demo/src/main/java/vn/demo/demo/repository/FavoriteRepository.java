package vn.demo.demo.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.demo.demo.entity.Favorite;
import vn.demo.demo.entity.Movie;
import vn.demo.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    Page<Favorite> findAllByUser(User user, Pageable pageable);

    List<Favorite> findAllByUser(User user);

    Optional<Favorite> findByUserAndMovie(User user, Movie movie);
}
