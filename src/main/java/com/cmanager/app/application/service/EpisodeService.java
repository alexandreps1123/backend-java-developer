package com.cmanager.app.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmanager.app.application.adapter.EpisodeAdapter;
import com.cmanager.app.application.domain.Episode;
import com.cmanager.app.application.domain.Show;
import com.cmanager.app.application.repository.EpisodeRepository;
import com.cmanager.app.integration.dto.EpisodeRequestDTO;

@Service
public class EpisodeService {

    private final EpisodeRepository episodeRepository;
    private final EpisodeAdapter episodeAdapter;

    public EpisodeService(EpisodeRepository episodeRepository,
                          EpisodeAdapter episodeAdapter) {
        this.episodeRepository = episodeRepository;
        this.episodeAdapter = episodeAdapter;
    }

    @Transactional
    public Episode create(Show show, EpisodeRequestDTO apiEpisode) {
        var episode = episodeAdapter.toEpisode(apiEpisode, show);
        if (episode == null) {
            return null;
        }

        return episodeRepository.findByIdIntegration(episode.getIdIntegration())
                .orElseGet(() -> episodeRepository.save(episode));
    }

    @Transactional(readOnly = true)
    public boolean existsByIdIntegration(Integer idIntegration) {
        return episodeRepository.findByIdIntegration(idIntegration).isPresent();
    }
}
