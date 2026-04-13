package com.cmanager.app.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmanager.app.application.adapter.EpisodeAdapter;
import com.cmanager.app.application.data.EpisodeAverageDTO;
import com.cmanager.app.application.domain.Episode;
import com.cmanager.app.application.domain.Show;
import com.cmanager.app.application.repository.EpisodeRepository;
import com.cmanager.app.integration.dto.EpisodeRequestDTO;

import jakarta.persistence.EntityNotFoundException;

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
        Episode episode = episodeAdapter.toEpisode(apiEpisode, show);
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

    @Transactional(readOnly = true)
    public List<EpisodeAverageDTO> averageBySeason(String showName) {
        if (showName == null || showName.isBlank()) {
            throw new IllegalArgumentException("Nome do show é obrigatório");
        }

        List<EpisodeAverageDTO> results = episodeRepository.findAverageRatingBySeasonAndShowName(showName);
        if (results == null || results.isEmpty()) {
            throw new EntityNotFoundException("Episódios não encontrados");
        }

        return results;
    }
}
