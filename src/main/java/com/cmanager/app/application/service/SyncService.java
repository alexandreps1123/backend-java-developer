package com.cmanager.app.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmanager.app.application.data.ShowCreateRequest;
import com.cmanager.app.application.data.ShowDTO;
import com.cmanager.app.application.domain.Show;
import com.cmanager.app.integration.client.RequestService;
import com.cmanager.app.integration.dto.EpisodeRequestDTO;
import com.cmanager.app.integration.dto.ShowsRequestDTO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SyncService {

    private final RequestService requestService;
    private final ShowService showService;
    private final EpisodeService episodeService;

    public SyncService(RequestService requestService,
                       ShowService showService,
                       EpisodeService episodeService) {
        this.requestService = requestService;
        this.showService = showService;
        this.episodeService = episodeService;
    }

    @Transactional
    public ShowDTO syncShow(ShowCreateRequest req) {
        final ShowsRequestDTO apiShow = requestService.getShow(req.name());
        if (apiShow == null) {
            throw new EntityNotFoundException("Show não encontrado na API externa");
        }

        final Show show = showService.create(apiShow);
        if (apiShow._embedded() != null && apiShow._embedded().episodes() != null) {
            syncEpisodes(show, apiShow._embedded().episodes());
        }

        return ShowDTO.convertEntity(show);
    }

    @Transactional
    public void syncEpisodes(Show show, java.util.List<EpisodeRequestDTO> apiEpisodes) {
        if (apiEpisodes == null || apiEpisodes.isEmpty()) {
            return;
        }

        for (EpisodeRequestDTO apiEpisode : apiEpisodes) {
            if (apiEpisode == null || apiEpisode.id() == null) {
                continue;
            }

            if (!episodeService.existsByIdIntegration(apiEpisode.id())) {
                episodeService.create(show, apiEpisode);
            }
        }
    }
}
