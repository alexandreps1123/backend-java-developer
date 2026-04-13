package com.cmanager.app.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmanager.app.application.adapter.ShowAdapter;
import com.cmanager.app.application.domain.Show;
import com.cmanager.app.application.repository.ShowRepository;
import com.cmanager.app.integration.dto.ShowsRequestDTO;

@Service
public class ShowService {

    private final ShowRepository showRepository;
    private final ShowAdapter showAdapter;

    public ShowService(ShowRepository showRepository,
            ShowAdapter showAdapter) {
        this.showRepository = showRepository;
        this.showAdapter = showAdapter;
    }

    @Transactional
    public Show create(ShowsRequestDTO apiShow) {
        return showRepository.findByIdIntegration(apiShow.id())
                .orElseGet(() -> showRepository.save(showAdapter.toShow(apiShow)));
    }

}
