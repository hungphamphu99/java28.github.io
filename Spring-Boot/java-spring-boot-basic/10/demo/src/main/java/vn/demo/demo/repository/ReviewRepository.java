package vn.demo.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.demo.demo.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Page<Review> findByMovie_Id(Integer movieId, Pageable pageable);
}