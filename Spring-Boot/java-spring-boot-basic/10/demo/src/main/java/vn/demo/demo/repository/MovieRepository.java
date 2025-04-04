package vn.demo.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.demo.demo.entity.Movie;
import vn.demo.demo.model.enums.MovieType;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Movie findByName(String name);

    List<Movie> findByNameContaining(String name);

    // Page bắt đầu = 0 tương ứng với trang 1
    Page<Movie> findByNameContaining(String name, Pageable pageable);

    List<Movie> findByNameContainingIgnoreCase(String name);

    List<Movie> findByRatingBetween(Double min, Double max);

    List<Movie> findByRatingGreaterThan(Double rating);

    List<Movie> findByRatingLessThan(Double rating, Sort sort);

    // C1: Method query: Độc lập với CSDL
    // select * from movies where rating < ? order by rating desc
    List<Movie> findByRatingLessThanOrderByRatingDesc(Double rating);

    // C2: Native query: Phụ thuộc vào CSDL
    @Query(value = "select * from movies where rating < ?1 order by rating desc", nativeQuery = true)
    List<Movie> findByRatingLessThanOrderByRatingDesc_NQ1(Double rating);

    @Query(value = "select * from movies where rating < :rating order by rating desc", nativeQuery = true)
    List<Movie> findByRatingLessThanOrderByRatingDesc_NQ2(@Param("rating") Double rating);

    // C3: JPQL: Độc lập với CSDL
    @Query(value = "select m from Movie m where m.rating < ?1 order by m.rating desc")
    List<Movie> findByRatingLessThanOrderByRatingDesc_JPQL(Double rating);

    boolean existsByName(String name);

    long countByRating(Double rating);

    void deleteByName(String name);

    List<Movie> findByStatusTrue();

    Page<Movie> findByTypeAndStatus(MovieType type, Boolean status, Pageable pageable);

    // Tìm các movie cùng type, status = true, ngoại trừ movieId, sắp xếp theo rating giảm dần
    @Query("select m from Movie m where m.type = :type and m.status = true and m.id <> :movieId order by m.rating desc")
    List<Movie> findRelatedMovies(@Param("type") MovieType type,
                                  @Param("movieId") Integer movieId,
                                  Pageable pageable);

    // Bổ sung phương thức tìm movie theo id và status = true
    Optional<Movie> findByIdAndStatusTrue(Integer id);
}
