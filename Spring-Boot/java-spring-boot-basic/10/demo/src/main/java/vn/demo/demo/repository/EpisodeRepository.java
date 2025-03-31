package vn.demo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.demo.demo.entity.Episode;

import java.util.List;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
    // Lấy các tập có movie_id bằng movieId, status = true và sắp xếp theo displayOrder tăng dần
    List<Episode> findByMovie_IdAndStatusTrueOrderByDisplayOrderAsc(Integer movieId);
}
