package vn.demo.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.demo.demo.entity.Episode;
import vn.demo.demo.repository.EpisodeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EpisodeService {
    private final EpisodeRepository episodeRepository;

    public List<Episode> getEpisodesByMovieId(Integer movieId) {
        return episodeRepository.findByMovie_IdAndStatusTrueOrderByDisplayOrderAsc(movieId);
    }
}

