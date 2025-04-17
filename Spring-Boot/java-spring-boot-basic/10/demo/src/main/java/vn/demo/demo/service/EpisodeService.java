package vn.demo.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.demo.demo.entity.Episode;
import vn.demo.demo.entity.Movie;
import vn.demo.demo.model.request.EpisodeRequest;
import vn.demo.demo.repository.EpisodeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EpisodeService {
    private final EpisodeRepository episodeRepository;

    public List<Episode> getEpisodesByMovieId(Integer movieId) {
        return episodeRepository.findByMovie_IdAndStatusTrueOrderByDisplayOrderAsc(movieId);
    }

    public Episode create(EpisodeRequest request) {
        Episode episode = Episode.builder()
                .name(request.getName())
                .duration(request.getDuration())
                .displayOrder(request.getDisplayOrder())
                .videoUrl(request.getVideoUrl())
                .status(request.getStatus() != null ? request.getStatus() : true)
                .publishedAt(request.getPublishedAt())
                .movie(Movie.builder().id(request.getMovieId()).build())
                .createdAt(java.time.LocalDateTime.now())
                .updatedAt(java.time.LocalDateTime.now())
                .build();
        return episodeRepository.save(episode);
    }

    public Episode update(Integer id, EpisodeRequest request) {
        Episode episode = episodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tập phim"));

        episode.setName(request.getName());
        episode.setDisplayOrder(request.getDisplayOrder());
        episode.setStatus(request.getStatus());
        episode.setVideoUrl(request.getVideoUrl());
        episode.setDuration(request.getDuration());
        episode.setPublishedAt(request.getPublishedAt());
        episode.setUpdatedAt(java.time.LocalDateTime.now());

        return episodeRepository.save(episode);
    }


    public void delete(Integer id) {
        Episode episode = episodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tập phim với id = " + id));
        episodeRepository.delete(episode);
    }


    public List<Episode> findByMovieId(Integer movieId) {
        return episodeRepository.findByMovie_IdOrderByDisplayOrderAsc(movieId);
    }


}

